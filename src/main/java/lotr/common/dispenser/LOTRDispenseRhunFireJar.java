package lotr.common.dispenser;

import lotr.common.block.LOTRBlockRhunFireJar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class LOTRDispenseRhunFireJar extends BehaviorDefaultDispenseItem {
    private final BehaviorDefaultDispenseItem dispenseDefault = new BehaviorDefaultDispenseItem();

    @Override
    protected ItemStack dispenseStack(IBlockSource dispenser, ItemStack itemstack) {
        int k;
        int j;
        int i;
        EnumFacing enumfacing = BlockDispenser.func_149937_b(dispenser.getBlockMetadata());
        World world = dispenser.getWorld();
        if(world.getBlock(i = dispenser.getXInt() + enumfacing.getFrontOffsetX(), j = dispenser.getYInt() + enumfacing.getFrontOffsetY(), k = dispenser.getZInt() + enumfacing.getFrontOffsetZ()).isReplaceable(world, i, j, k)) {
            LOTRBlockRhunFireJar.explodeOnAdded = false;
            world.setBlock(i, j, k, Block.getBlockFromItem(itemstack.getItem()), itemstack.getItemDamage(), 3);
            LOTRBlockRhunFireJar.explodeOnAdded = true;
            --itemstack.stackSize;
            return itemstack;
        }
        return this.dispenseDefault.dispense(dispenser, itemstack);
    }
}
