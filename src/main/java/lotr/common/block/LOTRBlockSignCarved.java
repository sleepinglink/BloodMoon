package lotr.common.block;

import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntitySign;
import lotr.common.util.LOTRCommonIcons;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSign;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LOTRBlockSignCarved extends BlockSign {
    public LOTRBlockSignCarved(Class<? extends LOTRTileEntitySign> cls) {
        super(cls, false);
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(0.5f);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k) {
        super.setBlockBoundsBasedOnState(world, i, j, k);
        this.setBlockBounds((float) this.minX, 0.0f, (float) this.minZ, (float) this.maxX, 1.0f, (float) this.maxZ);
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        return LOTRCommonIcons.iconEmptyBlock;
    }

    public IIcon getOnBlockIcon(IBlockAccess world, int i, int j, int k, int side) {
        int onX = i - Facing.offsetsXForSide[side];
        int onY = j - Facing.offsetsYForSide[side];
        int onZ = k - Facing.offsetsZForSide[side];
        Block onBlock = world.getBlock(onX, onY, onZ);
        IIcon icon = onBlock.getIcon(world, onX, onY, onZ, side);
        if(icon == null) {
            icon = Blocks.stone.getIcon(0, 0);
        }
        return icon;
    }

    @Override
    public Item getItemDropped(int i, Random random, int j) {
        return null;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public Item getItem(World world, int i, int j, int k) {
        if(this == LOTRMod.signCarvedIthildin) {
            return LOTRMod.chiselIthildin;
        }
        return LOTRMod.chisel;
    }
}
