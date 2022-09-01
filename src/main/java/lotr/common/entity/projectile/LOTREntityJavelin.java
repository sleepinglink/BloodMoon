package lotr.common.entity.projectile;

import lotr.common.item.LOTRItemJavelin;
import lotr.common.item.LOTRItemThrowingAxe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTREntityJavelin extends LOTREntityProjectileBase {
	
	
    public LOTREntityJavelin(World world) {
        super(world);
    }

    public LOTREntityJavelin(World world, ItemStack item, double d, double d1, double d2) {
        super(world, item, d, d1, d2);
        item.stackSize = 1;
    }

    public LOTREntityJavelin(World world, EntityLivingBase entityliving, ItemStack item, float charge) {
        super(world, entityliving, item, charge);
        item.stackSize = 1;
    }

    public LOTREntityJavelin(World world, EntityLivingBase entityliving, EntityLivingBase target, ItemStack item, float charge, float inaccuracy) {
        super(world, entityliving, target, item, charge, inaccuracy);
        item.stackSize = 1;
    }


    @Override
    public float getBaseImpactDamage(Entity entity, ItemStack itemstack) {
        float speed = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        float damage = ((LOTRItemJavelin) itemstack.getItem()).getRangedDamageMultiplier(itemstack, this.shootingEntity, entity);
        return speed * damage;
    }
}
