package lotr.common.item;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class LOTRPoisonDebuffs {
    private static final int POISON_DURATION = 300;
    public static Potion drinkPoison;
    public static Potion woundPoison;

    public static void registerDrinkPotion() {
        drinkPoison = new LOTRPotionPoisonedDrink();
    }

    public static void registerWoundPotion() {
        woundPoison = new LOTRPotionPoisonedWound();
    }

    public static void addPoisonEffect(EntityPlayer entityplayer, ItemStack itemstack) {
        int duration = 300;
        entityplayer.addPotionEffect(new PotionEffect(LOTRPoisonDebuffs.drinkPoison.id, duration));
    }

    public static boolean canPoison(ItemStack itemstack) {
        if(itemstack == null) {
            return false;
        }
        return LOTRItemMug.isItemFullDrink(itemstack);
    }

    public static boolean isDrinkPoisoned(ItemStack itemstack) {
        if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("PoisonDrink")) {
            return itemstack.getTagCompound().getBoolean("PoisonDrink");
        }
        return false;
    }

    public static void setDrinkPoisoned(ItemStack itemstack, boolean flag) {
        if(itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setBoolean("PoisonDrink", flag);
    }

    public static UUID getPoisonerUUID(ItemStack itemstack) {
        if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("PoisonerUUID")) {
            String s = itemstack.getTagCompound().getString("PoisonerUUID");
            return UUID.fromString(s);
        }
        return null;
    }

    public static void setPoisonerPlayer(ItemStack itemstack, EntityPlayer entityplayer) {
        LOTRPoisonDebuffs.setPoisonerUUID(itemstack, entityplayer.getUniqueID());
    }

    public static void setPoisonerUUID(ItemStack itemstack, UUID uuid) {
        if(itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setString("PoisonerUUID", uuid.toString());
    }

    public static boolean canPlayerSeePoisoned(ItemStack itemstack, EntityPlayer entityplayer) {
        UUID uuid = LOTRPoisonDebuffs.getPoisonerUUID(itemstack);
        if(uuid == null) {
            return true;
        }
        return entityplayer.getUniqueID().equals(uuid) || entityplayer.capabilities.isCreativeMode;
    }
}
