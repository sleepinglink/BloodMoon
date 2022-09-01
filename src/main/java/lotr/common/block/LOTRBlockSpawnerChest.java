package lotr.common.block;

import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.entity.animal.LOTREntityDesertScorpion;
import lotr.common.entity.npc.LOTREntityHaradPyramidWraith;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.tileentity.LOTRTileEntitySpawnerChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRBlockSpawnerChest extends BlockChest {
    private static boolean dropChestItems = true;
    public final Block chestModel;

    public LOTRBlockSpawnerChest(Block block) {
        super(0);
        this.chestModel = block;
        this.setStepSound(block.stepSound);
        this.setCreativeTab(null);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new LOTRTileEntitySpawnerChest();
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        return this.chestModel.getIcon(i, j);
    }

    @Override
    public int getRenderType() {
        return this.chestModel.getRenderType();
    }

    @Override
    public float getBlockHardness(World world, int i, int j, int k) {
        return this.chestModel.getBlockHardness(world, i, j, k);
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int i, int j, int k, double explosionX, double explosionY, double explosionZ) {
        return this.chestModel.getExplosionResistance(entity, world, i, j, k, explosionX, explosionY, explosionZ);
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        this.spawnEntity(world, i, j, k);
        dropChestItems = false;
        if(!world.isRemote) {
            int l;
            ItemStack[] chestInv = new ItemStack[27];
            TileEntity tileentity = world.getTileEntity(i, j, k);
            if(tileentity instanceof IInventory) {
                for(l = 0; l < chestInv.length; ++l) {
                    chestInv[l] = ((IInventory) (tileentity)).getStackInSlot(l);
                }
            }
            world.setBlock(i, j, k, this.chestModel, world.getBlockMetadata(i, j, k), 3);
            for(l = 0; l < 27; ++l) {
                ((IInventory) (world.getTileEntity(i, j, k))).setInventorySlotContents(l, chestInv[l]);
            }
        }
        dropChestItems = true;
        return true;
    }

    @Override
    public void breakBlock(World world, int i, int j, int k, Block block, int meta) {
        if(dropChestItems) {
            this.spawnEntity(world, i, j, k);
            super.breakBlock(world, i, j, k, block, meta);
        }
        else {
            world.removeTileEntity(i, j, k);
        }
    }

    private void spawnEntity(World world, int i, int j, int k) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if(!(tileentity instanceof LOTRTileEntitySpawnerChest)) {
            return;
        }
        Entity entity = ((LOTRTileEntitySpawnerChest) tileentity).createMob();
        if(!(entity instanceof EntityLiving)) {
            return;
        }
        EntityLiving entityliving = (EntityLiving) entity;
        entityliving.setLocationAndAngles(i + 0.5, j + 1, k + 0.5, 0.0f, 0.0f);
        entityliving.spawnExplosionParticle();
        if(!world.isRemote) {
            entityliving.onSpawnWithEgg(null);
            if(entityliving instanceof LOTREntityNPC) {
                ((LOTREntityNPC) entityliving).isNPCPersistent = true;
            }
            world.spawnEntityInWorld(entityliving);
            world.playSoundAtEntity(entityliving, "lotr:wraith.spawn", 1.0f, 0.7f + world.rand.nextFloat() * 0.6f);
            if(entityliving instanceof LOTREntityHaradPyramidWraith) {
                for(int l = 0; l < 4; ++l) {
                    LOTREntityDesertScorpion desertScorpion = new LOTREntityDesertScorpion(world);
                    desertScorpion.setSpawningFromMobSpawner(true);
                    double d = entityliving.posX - world.rand.nextDouble() * 3.0 + world.rand.nextDouble() * 3.0;
                    double d1 = entityliving.posY;
                    double d2 = entityliving.posZ - world.rand.nextDouble() * 3.0 + world.rand.nextDouble() * 3.0;
                    desertScorpion.setLocationAndAngles(d, d1, d2, world.rand.nextFloat() * 360.0f, 0.0f);
                    if(!desertScorpion.getCanSpawnHere()) continue;
                    world.spawnEntityInWorld(desertScorpion);
                    desertScorpion.setSpawningFromMobSpawner(false);
                }
                world.addWeatherEffect(new EntityLightningBolt(world, entityliving.posX, entityliving.posY, entityliving.posZ));
            }
        }
    }

    @Override
    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock(this.chestModel);
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public Item getItem(World world, int i, int j, int k) {
        return Item.getItemFromBlock(this.chestModel);
    }
}
