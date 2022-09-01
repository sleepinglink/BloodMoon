package lotr.common.block;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class LOTRBlockGoran extends Block {
    @SideOnly(value = Side.CLIENT)
    private IIcon[] goranIcons;
    private static final String[] goranNames = new String[] {"", "rock"};
    public static final String[] displayNames = new String[] {"Goran", "Sarngoran"};

    public LOTRBlockGoran() {
        super(Material.rock);
        this.setCreativeTab(null);
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float f, float f1, float f2) {
        if(!world.isRemote) {
            if(!MinecraftServer.getServer().getConfigurationManager().func_152596_g(entityplayer.getGameProfile())) {
                return false;
            }
            for(int i1 = i - 32; i1 <= i + 32; ++i1) {
                for(int j1 = j - 32; j1 <= j + 32; ++j1) {
                    for(int k1 = k - 32; k1 <= k + 32; ++k1) {
                        if(!world.blockExists(i1, j1, k1) || !world.isAirBlock(i1, j1, k1)) continue;
                        world.setBlock(i1, j1, k1, Blocks.water);
                    }
                }
            }
        }
        return true;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIcon(int i, int j) {
        if(j >= goranNames.length) {
            j = 0;
        }
        return this.goranIcons[j];
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
        this.goranIcons = new IIcon[goranNames.length];
        for(int i = 0; i < goranNames.length; ++i) {
            String iconName = this.getTextureName();
            if(!goranNames[i].equals("")) {
                iconName = iconName + "_" + goranNames[i];
            }
            this.goranIcons[i] = iconregister.registerIcon(iconName);
        }
    }

    @Override
    public int damageDropped(int i) {
        return i;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < goranNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
