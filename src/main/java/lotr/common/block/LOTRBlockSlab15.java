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

public class LOTRBlockSlab15 extends LOTRBlockSlabBase {
    public LOTRBlockSlab15(boolean flag) {
        super(flag, Material.rock, 5);
        this.setCreativeTab(LOTRCreativeTabs.tabBlock);
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        if((j &= 7) == 0) {
            return LOTRMod.brick7.getIcon(i, 4);
        }   
        if(j == 1) {
            return LOTRMod.brick7.getIcon(i, 5);
        } 
        if(j == 2) {
            return LOTRMod.brick7.getIcon(i, 6);
        } 
        if(j == 3) {
            return LOTRMod.brick7.getIcon(i, 7);
        }    
        if(j == 4) {
            return LOTRMod.smoothStone.getIcon(i, 6);
        }
        return super.getIcon(i, j);
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
    }
}