package lotr.common.block;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.util.IIcon;

public class LOTRBlockStainedGlass extends LOTRBlockGlass {
    private IIcon[] glassIcons = new IIcon[16];

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        return this.glassIcons[j % this.glassIcons.length];
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
        for(int i = 0; i < this.glassIcons.length; ++i) {
            this.glassIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + ItemDye.field_150921_b[BlockStainedGlass.func_149997_b(i)]);
        }
    }

    @Override
    public int damageDropped(int i) {
        return i;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < this.glassIcons.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
