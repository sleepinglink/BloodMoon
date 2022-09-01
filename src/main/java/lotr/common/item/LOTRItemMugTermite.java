package lotr.common.item;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class LOTRItemMugTermite extends LOTRItemMug {
    public LOTRItemMugTermite(float f) {
        super(f);
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        ItemStack result = super.onEaten(itemstack, world, entityplayer);
        if(!world.isRemote && world.rand.nextInt(6) == 0) {
            world.createExplosion(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, 3.0f, false);
        }
        return result;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        super.addInformation(itemstack, entityplayer, list, flag);
        list.add((EnumChatFormatting.DARK_GRAY) + StatCollector.translateToLocal("item.lotr.drink.explode"));
    }
}
