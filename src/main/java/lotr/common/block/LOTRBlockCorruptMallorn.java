package lotr.common.block;

import java.util.ArrayList;
import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityMallornEnt;
import lotr.common.tileentity.LOTRTileEntityCorruptMallorn;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LOTRBlockCorruptMallorn extends LOTRBlockFlower {
    public static int ENT_KILLS = 3;

    public LOTRBlockCorruptMallorn() {
        float f = 0.4f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 2.0f, 0.5f + f);
        this.setCreativeTab(LOTRCreativeTabs.tabDeco);
        this.setLightLevel(0.625f);
    }

    @Override
    public boolean hasTileEntity(int meta) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        return new LOTRTileEntityCorruptMallorn();
    }

    public static void summonEntBoss(World world, int i, int j, int k) {
        world.setBlockToAir(i, j, k);
        LOTREntityMallornEnt ent = new LOTREntityMallornEnt(world);
        ent.setLocationAndAngles(i + 0.5, j, k + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
        ent.onSpawnWithEgg(null);
        world.spawnEntityInWorld(ent);
        ent.sendEntBossSpeech("summon");
    }

    @Override
    public void updateTick(World world, int i, int j, int k, Random random) {
        if(!world.isRemote) {
            super.updateTick(world, i, j, k, random);
        }
    }

    @Override
    public ArrayList getDrops(World world, int i, int j, int k, int meta, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(LOTRMod.sapling, 1, 1));
        return drops;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        for(int l = 0; l < 2; ++l) {
            double d = i + 0.25 + random.nextFloat() * 0.5f;
            double d1 = j + 0.5;
            double d2 = k + 0.25 + random.nextFloat() * 0.5f;
            double d3 = -0.05 + random.nextFloat() * 0.1;
            double d4 = 0.1 + random.nextFloat() * 0.1;
            double d5 = -0.05 + random.nextFloat() * 0.1;
            LOTRMod.proxy.spawnParticle("morgulPortal", d, d1, d2, d3, d4, d5);
        }
    }

    @Override
    public int getRenderType() {
        return 1;
    }
}
