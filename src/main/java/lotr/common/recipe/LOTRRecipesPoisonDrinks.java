package lotr.common.recipe;

import java.lang.reflect.Field;
import cpw.mods.fml.common.FMLLog;
import lotr.common.LOTRMod;
import lotr.common.item.LOTRPoisonDebuffs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class LOTRRecipesPoisonDrinks implements IRecipe {
    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        ItemStack drink = null;
        ItemStack poison = null;
        for(int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if(itemstack == null) continue;
            if(LOTRPoisonDebuffs.canPoison(itemstack)) {
                if(drink != null) {
                    return false;
                }
                drink = itemstack;
                continue;
            }
            if(LOTRMod.isOreNameEqual(itemstack, "poison")) {
                if(poison != null) {
                    return false;
                }
                poison = itemstack;
                continue;
            }
            return false;
        }
        return drink != null && poison != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        EntityPlayer craftingPlayer;
        ItemStack result;
        block12: {
            ItemStack drink = null;
            ItemStack poison = null;
            for(int i = 0; i < inv.getSizeInventory(); ++i) {
                ItemStack itemstack = inv.getStackInSlot(i);
                if(itemstack == null) continue;
                if(LOTRPoisonDebuffs.canPoison(itemstack)) {
                    if(drink != null) {
                        return null;
                    }
                    drink = itemstack.copy();
                    continue;
                }
                if(LOTRMod.isOreNameEqual(itemstack, "poison")) {
                    if(poison != null) {
                        return null;
                    }
                    poison = itemstack.copy();
                    continue;
                }
                return null;
            }
            if(drink == null || poison == null) {
                return null;
            }
            result = drink.copy();
            LOTRPoisonDebuffs.setDrinkPoisoned(result, true);
            craftingPlayer = null;
            try {
                Container cwb = null;
                for(Field f : inv.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    Object obj = f.get(inv);
                    if(!(obj instanceof Container)) continue;
                    cwb = (Container) obj;
                    break;
                }
                if(cwb == null) break block12;
                for(Object obj : cwb.inventorySlots) {
                    Slot slot = (Slot) obj;
                    IInventory slotInv = slot.inventory;
                    if(!(slotInv instanceof InventoryPlayer)) continue;
                    InventoryPlayer playerInv = (InventoryPlayer) slotInv;
                    craftingPlayer = playerInv.player;
                    break;
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(craftingPlayer != null) {
            LOTRPoisonDebuffs.setPoisonerPlayer(result, craftingPlayer);
        }
        else {
            FMLLog.bigWarning("LOTR Warning! Poisoned drink was crafted, player could not be found!", new Object[0]);
        }
        return result;
    }

    @Override
    public int getRecipeSize() {
        return 2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
