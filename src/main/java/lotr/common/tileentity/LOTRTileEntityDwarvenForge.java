package lotr.common.tileentity;

import lotr.common.LOTRMod;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LOTRTileEntityDwarvenForge extends LOTRTileEntityAlloyForgeBase {
    @Override
    public String getForgeName() {
        return StatCollector.translateToLocal("container.lotr.dwarvenForge");
    }

    @Override
    public ItemStack getSmeltingResult(ItemStack itemstack) {
        if(itemstack.getItem() == Item.getItemFromBlock(LOTRMod.oreMithril)) {
            return new ItemStack(LOTRMod.mithril);
        }
        return super.getSmeltingResult(itemstack);
    }

    @Override
    protected ItemStack getAlloySmeltingResult(ItemStack itemstack, ItemStack alloyItem) {
        if(this.isIron(itemstack) && this.isCoal(alloyItem)) {
            return new ItemStack(LOTRMod.dwarfSteel);
        }
        if(this.isIron(itemstack) && alloyItem.getItem() == LOTRMod.quenditeCrystal) {
            return new ItemStack(LOTRMod.galvorn);
        }
        return super.getAlloySmeltingResult(itemstack, alloyItem);
    }
}
