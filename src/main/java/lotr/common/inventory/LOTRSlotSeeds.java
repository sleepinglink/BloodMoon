package lotr.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class LOTRSlotSeeds extends Slot {
    private World worldObj;

    public LOTRSlotSeeds(IInventory inv, int i, int j, int k, World world) {
        super(inv, i, j, k);
        this.worldObj = world;
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        Item item = itemstack.getItem();
        return item instanceof IPlantable && ((IPlantable) (item)).getPlantType(this.worldObj, -1, -1, -1) == EnumPlantType.Crop;
    }
}
