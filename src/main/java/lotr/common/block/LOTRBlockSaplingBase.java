package lotr.common.block;

import java.util.List;
import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

public abstract class LOTRBlockSaplingBase extends LOTRBlockFlower {
    @SideOnly(value = Side.CLIENT)
    private IIcon[] saplingIcons;
    private String[] saplingNames;

    public LOTRBlockSaplingBase() {
        float f = 0.4f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 2.0f, 0.5f + f);
        this.setCreativeTab(LOTRCreativeTabs.tabDeco);
    }

    public void setSaplingNames(String... s) {
        this.saplingNames = s;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        if((j &= 7) >= this.saplingNames.length) {
            j = 0;
        }
        return this.saplingIcons[j];
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
        this.saplingIcons = new IIcon[this.saplingNames.length];
        for(int i = 0; i < this.saplingNames.length; ++i) {
            this.saplingIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.saplingNames[i]);
        }
    }

    @Override
    public void updateTick(World world, int i, int j, int k, Random random) {
        if(!world.isRemote) {
            super.updateTick(world, i, j, k, random);
            if(world.getBlockLightValue(i, j + 1, k) >= 9 && random.nextInt(7) == 0) {
                this.incrementGrowth(world, i, j, k, random);
            }
        }
    }

    public void incrementGrowth(World world, int i, int j, int k, Random random) {
        int meta = world.getBlockMetadata(i, j, k);
        if((meta & 8) == 0) {
            world.setBlockMetadataWithNotify(i, j, k, meta | 8, 4);
        }
        else {
            if(!TerrainGen.saplingGrowTree(world, random, i, j, k)) {
                return;
            }
            this.growTree(world, i, j, k, random);
        }
    }

    public abstract void growTree(World var1, int var2, int var3, int var4, Random var5);

    public boolean isSameSapling(World world, int i, int j, int k, int meta) {
        return LOTRBlockSaplingBase.isSameSapling(world, i, j, k, this, meta);
    }

    public static boolean isSameSapling(World world, int i, int j, int k, Block block, int meta) {
        if(world.getBlock(i, j, k) == block) {
            int blockMeta = world.getBlockMetadata(i, j, k);
            return (blockMeta & 7) == meta;
        }
        return false;
    }

    public static int[] findPartyTree(World world, int i, int j, int k, Block block, int meta) {
        return LOTRBlockSaplingBase.findSaplingSquare(world, i, j, k, block, meta, -1, 1, -2, 2);
    }

    public static int[] findSaplingSquare(World world, int i, int j, int k, Block block, int meta, int squareMin, int squareMax, int searchMin, int searchMax) {
        for(int i1 = searchMin; i1 <= searchMax; ++i1) {
            for(int k1 = searchMin; k1 <= searchMax; ++k1) {
                boolean canGenerate = true;
                block2: for(int i2 = squareMin; i2 <= squareMax; ++i2) {
                    for(int k2 = squareMin; k2 <= squareMax; ++k2) {
                        int i3 = i + i1 + i2;
                        int k3 = k + k1 + k2;
                        if(LOTRBlockSaplingBase.isSameSapling(world, i3, j, k3, block, meta)) continue;
                        canGenerate = false;
                        break block2;
                    }
                }
                if(!canGenerate) continue;
                return new int[] {i1, k1};
            }
        }
        return null;
    }

    public static int[] findCrossShape(World world, int i, int j, int k, Block block, int meta) {
        for(int i1 = -2; i1 <= 2; ++i1) {
            for(int k1 = -2; k1 <= 2; ++k1) {
                if(Math.abs(i1) != 0 && Math.abs(k1) != 0) continue;
                boolean canGenerate = true;
                block2: for(int i2 = -1; i2 <= 1; ++i2) {
                    for(int k2 = -1; k2 <= 1; ++k2) {
                        int k3;
                        int i3;
                        if(Math.abs(i2) != 0 && Math.abs(k2) != 0 || LOTRBlockSaplingBase.isSameSapling(world, i3 = i + i1 + i2, j, k3 = k + k1 + k2, block, meta)) continue;
                        canGenerate = false;
                        break block2;
                    }
                }
                if(!canGenerate) continue;
                return new int[] {i1, k1};
            }
        }
        return null;
    }

    @Override
    public int damageDropped(int i) {
        return i & 7;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < this.saplingNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int getRenderType() {
        return 1;
    }
}
