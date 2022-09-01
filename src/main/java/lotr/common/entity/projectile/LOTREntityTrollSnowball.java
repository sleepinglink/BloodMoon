package lotr.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LOTREntityTrollSnowball extends EntitySnowball {
    public LOTREntityTrollSnowball(World world) {
        super(world);
    }

    public LOTREntityTrollSnowball(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public LOTREntityTrollSnowball(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    @Override
    protected void onImpact(MovingObjectPosition target) {
        if(target.entityHit != null) {
            float damage = 3.0f;
            target.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
        }
        for(int i = 0; i < 8; ++i) {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
        }
        if(!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
