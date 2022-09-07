package lotr.common.enchant;

import lotr.common.LOTRDamage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentProtectionPoison extends LOTREnchantmentProtectionSpecial {
    public LOTREnchantmentProtectionPoison(String s, int level) {
        super(s, level);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted("lotr.enchant.protectPoisonWound.desc", this.formatAdditiveInt(this.calcIntProtection()));
    }

    @Override
    protected boolean protectsAgainst(DamageSource source) {
        return source == LOTRDamage.poisonedWound && source == LOTRDamage.poisonDrink;
    }

    @Override
    protected int calcIntProtection() {;
        return 1 + protectLevel;
    }
}
