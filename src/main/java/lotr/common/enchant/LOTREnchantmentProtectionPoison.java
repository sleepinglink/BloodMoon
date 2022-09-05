package lotr.common.enchant;

import lotr.common.LOTRDamage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

public class LOTREnchantmentProtectionPoison extends LOTREnchantmentProtectionSpecial {
    public LOTREnchantmentProtectionPoison(String s, int level) {
        super(s, level);
    }

    @Override
    public String getDescription(ItemStack itemstack) {
        return StatCollector.translateToLocalFormatted("lotr.enchant.protectFire.desc", this.formatAdditiveInt(this.calcIntProtection()));
    }

    @Override
    protected boolean protectsAgainst(DamageSource source) {
        return source == LOTRDamage.poisonedWound;
    }

    @Override
    protected int calcIntProtection() {
        float f = (float) this.protectLevel * (float) (this.protectLevel + 1) / 2.0f;
        return 3 + MathHelper.floor_float(f);
    }
}
