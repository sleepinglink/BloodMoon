package lotr.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.LOTRDamage;
import lotr.common.LOTRMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class LOTRPotionPoisonedWound extends Potion {
    public LOTRPotionPoisonedWound() {
        super(29, true, Potion.poison.getLiquidColor());
        this.setPotionName("potion.lotr.woundPoison");
        this.setEffectiveness(Potion.poison.getEffectiveness());
        this.setIconIndex(0, 0);
    }

    @Override
    public void performEffect(EntityLivingBase entity, int level) {
        entity.attackEntityFrom(LOTRDamage.poisonedWound, 1.0f);
    }

    @Override
    public boolean isReady(int tick, int level) {
        int freq = 5 >> level;
        return freq > 0 ? tick % freq == 0 : true;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        LOTRMod.proxy.renderCustomPotionEffect(x, y, effect, mc);
    }
}
