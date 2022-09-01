package lotr.common.tileentity;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.lang3.ArrayUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.block.LOTRBlockForgeBase;
import lotr.common.inventory.LOTRSlotStackSize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public abstract class LOTRTileEntityForgeBase extends TileEntity implements IInventory, ISidedInventory {
    protected ItemStack[] inventory = new ItemStack[this.getForgeInvSize()];
    private String specialForgeName;
    public int forgeSmeltTime = 0;
    public int currentItemFuelValue = 0;
    public int currentSmeltTime = 0;
    public int[] inputSlots;
    public int[] outputSlots;
    public int fuelSlot;

    public LOTRTileEntityForgeBase() {
        this.setupForgeSlots();
    }

    public abstract int getForgeInvSize();

    public abstract void setupForgeSlots();

    public abstract int getSmeltingDuration();

    protected boolean canMachineInsertInput(ItemStack itemstack) {
        return true;
    }

    protected boolean canMachineInsertFuel(ItemStack itemstack) {
        return TileEntityFurnace.isItemFuel(itemstack);
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if(this.inventory[i] != null) {
            if(this.inventory[i].stackSize <= j) {
                ItemStack itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            }
            ItemStack itemstack = this.inventory[i].splitStack(j);
            if(this.inventory[i].stackSize == 0) {
                this.inventory[i] = null;
            }
            return itemstack;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if(this.inventory[i] != null) {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.inventory[i] = itemstack;
        if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.specialForgeName : this.getForgeName();
    }

    public abstract String getForgeName();

    @Override
    public boolean hasCustomInventoryName() {
        return this.specialForgeName != null && this.specialForgeName.length() > 0;
    }

    public void setSpecialForgeName(String s) {
        this.specialForgeName = s;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList items = nbt.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];
        for(int i = 0; i < items.tagCount(); ++i) {
            NBTTagCompound itemData = items.getCompoundTagAt(i);
            byte slot = itemData.getByte("Slot");
            if(slot < 0 || slot >= this.inventory.length) continue;
            this.inventory[slot] = ItemStack.loadItemStackFromNBT(itemData);
        }
        this.forgeSmeltTime = nbt.getShort("BurnTime");
        this.currentSmeltTime = nbt.getShort("SmeltTime");
        this.currentItemFuelValue = TileEntityFurnace.getItemBurnTime(this.inventory[this.fuelSlot]);
        if(nbt.hasKey("CustomName")) {
            this.specialForgeName = nbt.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList items = new NBTTagList();
        for(int i = 0; i < this.inventory.length; ++i) {
            if(this.inventory[i] == null) continue;
            NBTTagCompound itemData = new NBTTagCompound();
            itemData.setByte("Slot", (byte) i);
            this.inventory[i].writeToNBT(itemData);
            items.appendTag(itemData);
        }
        nbt.setTag("Items", items);
        nbt.setShort("BurnTime", (short) this.forgeSmeltTime);
        nbt.setShort("SmeltTime", (short) this.currentSmeltTime);
        if(this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.specialForgeName);
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(value = Side.CLIENT)
    public int getSmeltProgressScaled(int i) {
        return this.currentSmeltTime * i / this.getSmeltingDuration();
    }

    @SideOnly(value = Side.CLIENT)
    public int getSmeltTimeRemainingScaled(int i) {
        if(this.currentItemFuelValue == 0) {
            this.currentItemFuelValue = this.getSmeltingDuration();
        }
        return this.forgeSmeltTime * i / this.currentItemFuelValue;
    }

    public boolean isSmelting() {
        return this.forgeSmeltTime > 0;
    }

    protected void toggleForgeActive() {
        LOTRBlockForgeBase.toggleForgeActive(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    protected abstract boolean canDoSmelting();

    protected abstract void doSmelt();

    @Override
    public void updateEntity() {
        boolean smelting = this.forgeSmeltTime > 0;
        boolean needUpdate = false;
        if(this.forgeSmeltTime > 0) {
            --this.forgeSmeltTime;
        }
        if(!this.worldObj.isRemote) {
            if(this.forgeSmeltTime == 0 && this.canDoSmelting()) {
                this.currentItemFuelValue = this.forgeSmeltTime = TileEntityFurnace.getItemBurnTime(this.inventory[this.fuelSlot]);
                if(this.forgeSmeltTime > 0) {
                    needUpdate = true;
                    if(this.inventory[this.fuelSlot] != null) {
                        --this.inventory[this.fuelSlot].stackSize;
                        if(this.inventory[this.fuelSlot].stackSize == 0) {
                            this.inventory[this.fuelSlot] = this.inventory[this.fuelSlot].getItem().getContainerItem(this.inventory[this.fuelSlot]);
                        }
                    }
                }
            }
            if(this.isSmelting() && this.canDoSmelting()) {
                ++this.currentSmeltTime;
                if(this.currentSmeltTime == this.getSmeltingDuration()) {
                    this.currentSmeltTime = 0;
                    this.doSmelt();
                    needUpdate = true;
                }
            }
            else {
                this.currentSmeltTime = 0;
            }
            if(smelting != this.forgeSmeltTime > 0) {
                needUpdate = true;
                this.toggleForgeActive();
            }
        }
        if(needUpdate) {
            this.markDirty();
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityplayer.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64.0;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        if(ArrayUtils.contains(this.inputSlots, slot)) {
            return this.canMachineInsertInput(itemstack);
        }
        if(slot == this.fuelSlot) {
            return this.canMachineInsertFuel(itemstack);
        }
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if(side == 0) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i : this.outputSlots) {
                list.add(i);
            }
            list.add(this.fuelSlot);
            int[] temp = new int[list.size()];
            for(int i = 0; i < temp.length; ++i) {
                temp[i] = list.get(i);
            }
            return temp;
        }
        if(side == 1) {
            ArrayList<LOTRSlotStackSize> list = new ArrayList<LOTRSlotStackSize>();
            for(int slot : this.inputSlots) {
                int size = this.getStackInSlot(slot) == null ? 0 : this.getStackInSlot(slot).stackSize;
                list.add(new LOTRSlotStackSize(slot, size));
            }
            Collections.sort(list);
            int[] temp = new int[this.inputSlots.length];
            for(int i = 0; i < temp.length; ++i) {
                LOTRSlotStackSize obj = list.get(i);
                temp[i] = obj.slot;
            }
            return temp;
        }
        return new int[] {this.fuelSlot};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
        return this.isItemValidForSlot(slot, itemstack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
        if(side == 0) {
            if(slot == this.fuelSlot) {
                return itemstack.getItem() == Items.bucket;
            }
            return true;
        }
        return true;
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        if(packet.func_148857_g() != null && packet.func_148857_g().hasKey("CustomName")) {
            this.specialForgeName = packet.func_148857_g().getString("CustomName");
        }
    }
}
