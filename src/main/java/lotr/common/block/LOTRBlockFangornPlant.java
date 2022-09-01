package lotr.common.block;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LOTRBlockFangornPlant extends LOTRBlockFlower {
    @SideOnly(value = Side.CLIENT)
    private IIcon[] plantIcons;
    private String[] plantNames = new String[] {"green", "brown", "gold", "yellow", "red", "silver"};

    public LOTRBlockFangornPlant() {
        this.setFlowerBounds(0.2f, 0.0f, 0.2f, 0.8f, 0.8f, 0.8f);
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        if(j >= this.plantNames.length) {
            j = 0;
        }
        return this.plantIcons[j];
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
        this.plantIcons = new IIcon[this.plantNames.length];
        for(int i = 0; i < this.plantNames.length; ++i) {
            this.plantIcons[i] = iconregister.registerIcon(this.getTextureName() + "_" + this.plantNames[i]);
        }
    }

    @Override
    public int damageDropped(int i) {
        return i;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int j = 0; j < this.plantNames.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
}
