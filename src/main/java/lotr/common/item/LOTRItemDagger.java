package lotr.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;

public class LOTRItemDagger extends LOTRItemSword {
    private DaggerEffect effect;

    public LOTRItemDagger(LOTRMaterial material) {
        this(material, DaggerEffect.NONE);
    }

    public LOTRItemDagger(Item.ToolMaterial material) {
        this(material, DaggerEffect.NONE);
    }

    public LOTRItemDagger(LOTRMaterial material, DaggerEffect e) {
        this(material.toToolMaterial(), e);
    }

    public LOTRItemDagger(Item.ToolMaterial material, DaggerEffect e) {
        super(material);
        this.lotrWeaponDamage -= 3.0f;
        this.effect = e;
    }

    public DaggerEffect getDaggerEffect() {
        return this.effect;
    }

    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase hitEntity, EntityLivingBase user) {
        itemstack.damageItem(1, user);
        if(this.effect == DaggerEffect.NONE) {
            return true;
        }
        if(this.effect == DaggerEffect.POISONED_WOUND) {
            LOTRItemDagger.applyPoisonedWoundEffect(hitEntity);
        }
        return true;
    }

    public static void applyPoisonedWoundEffect(EntityLivingBase entity) {
        EnumDifficulty difficulty = entity.worldObj.difficultySetting;
        int duration = 1 + difficulty.getDifficultyId() * 2;
        PotionEffect poisonedWound = new PotionEffect(LOTRPoisonDebuffs.woundPoison.id, (duration + itemRand.nextInt(duration)) * 20);
        entity.addPotionEffect(poisonedWound);

        /*public static void addPoisonEffect(EntityPlayer entityplayer, ItemStack itemstack) {
            int duration = 300;
            entityplayer.addPotionEffect(new PotionEffect(LOTRPoisonDebuffs.drinkPoison.id, duration));
        }*/
    }


    public static enum DaggerEffect {
        NONE, POISONED_WOUND;

    }

}
