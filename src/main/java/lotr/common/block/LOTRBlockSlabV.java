package lotr.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRCreativeTabs;
import lotr.common.LOTRMod;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class LOTRBlockSlabV extends LOTRBlockSlabBase {
    public LOTRBlockSlabV(boolean flag) {
        super(flag, Material.rock, 8);
        this.setCreativeTab(LOTRCreativeTabs.tabBlock);
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        if((j &= 7) == 0) {
            return Blocks.stonebrick.getIcon(i, 1);
        }
        if(j == 1) {
            return Blocks.stonebrick.getIcon(i, 2);
        }
        if(j == 2) {
            return LOTRMod.redBrick.getIcon(i, 0);
        }
        if(j == 3) {
            return LOTRMod.redBrick.getIcon(i, 1);
        }
        if(j == 4) {
            return Blocks.mossy_cobblestone.getIcon(i, 0);
        }
        if(j == 5) {
            return Blocks.stone.getIcon(i, 0);
        }
        if(j == 6) {
            return LOTRMod.brick7.getIcon(i, 0);
        }   
        if(j == 7) {
            return LOTRMod.brick7.getIcon(i, 1);
        } 
        
        return super.getIcon(i, j);
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
    }
}
