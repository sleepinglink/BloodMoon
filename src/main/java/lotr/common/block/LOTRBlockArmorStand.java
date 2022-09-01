package lotr.common.block;

import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRMod;
import lotr.common.tileentity.LOTRTileEntityArmorStand;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRBlockArmorStand extends Block {
    public LOTRBlockArmorStand() {
        super(Material.circuits);
        this.setHardness(0.5f);
        this.setResistance(1.0f);
        this.setStepSound(Block.soundTypeStone);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        if(this.hasTileEntity(world.getBlockMetadata(i, j, k))) {
            return AxisAlignedBB.getBoundingBox(i, j, k, i + 1.0, j + 0.125, k + 1.0);
        }
        return null;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return (metadata & 4) == 0;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        if(this.hasTileEntity(metadata)) {
            return new LOTRTileEntityArmorStand();
        }
        return null;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        return Blocks.planks.getIcon(i, 0);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        if(!this.hasTileEntity(world.getBlockMetadata(i, j, k))) {
            --j;
        }
        if(this.hasTileEntity(world.getBlockMetadata(i, j, k))) {
            if(!world.isRemote) {
                entityplayer.openGui(LOTRMod.instance, 17, world, i, j, k);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int i, int j, int k) {
        return world.getBlock(i, j - 1, k).isSideSolid(world, i, j - 1, k, ForgeDirection.UP) && j < 255;
    }

    @Override
    public boolean canBlockStay(World world, int i, int j, int k) {
        int meta = world.getBlockMetadata(i, j, k);
        if(this.hasTileEntity(meta)) {
            return world.getBlock(i, j - 1, k).isSideSolid(world, i, j - 1, k, ForgeDirection.UP) && world.getBlock(i, j + 1, k) == this;
        }
        return world.getBlock(i, j - 1, k) == this;
    }

    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block block) {
        int meta = world.getBlockMetadata(i, j, k);
        if(this.hasTileEntity(meta)) {
            if(!this.canBlockStay(world, i, j, k)) {
                world.setBlockToAir(i, j, k);
                if(!world.isRemote) {
                    this.dropBlockAsItem(world, i, j, k, meta, 0);
                }
            }
        }
        else if(!this.canBlockStay(world, i, j, k)) {
            world.setBlockToAir(i, j, k);
        }
    }

    @Override
    public void onBlockHarvested(World world, int i, int j, int k, int meta, EntityPlayer entityplayer) {
        if(entityplayer.capabilities.isCreativeMode && !this.hasTileEntity(meta) && world.getBlock(i, j - 1, k) == this) {
            world.setBlockToAir(i, j - 1, k);
        }
    }

    @Override
    public Item getItemDropped(int i, Random random, int j) {
        return this.hasTileEntity(i) ? LOTRMod.armorStandItem : null;
    }

    @Override
    public void breakBlock(World world, int i, int j, int k, Block block, int meta) {
        LOTRTileEntityArmorStand stand = (LOTRTileEntityArmorStand) world.getTileEntity(i, j, k);
        if(stand != null) {
            LOTRMod.dropContainerItems(stand, world, i, j, k);
        }
        super.breakBlock(world, i, j, k, block, meta);
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public Item getItem(World world, int i, int j, int k) {
        return LOTRMod.armorStandItem;
    }
}
