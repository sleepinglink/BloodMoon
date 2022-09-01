package lotr.common.entity.ai;

import java.lang.reflect.Field;
import cpw.mods.fml.common.FMLLog;
import lotr.common.LOTRReflection;
import lotr.common.entity.npc.LOTREntityHuornBase;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;

public class LOTREntityAIAvoidHuorn extends EntityAIAvoidEntity {
    public LOTREntityAIAvoidHuorn(final EntityCreature entity, float range, double near, double far) {
        super(entity, LOTREntityHuornBase.class, range, near, far);
        try {
            Field[] fs;
            IEntitySelector replaceSelect = new IEntitySelector() {

                @Override
                public boolean isEntityApplicable(Entity target) {
                    if(target.isEntityAlive() && entity.getEntitySenses().canSee(target)) {
                        LOTREntityHuornBase huorn = (LOTREntityHuornBase) target;
                        return huorn.isHuornActive();
                    }
                    return false;
                }
            };
            for(Field f : fs = EntityAIAvoidEntity.class.getFields()) {
                Object inst = f.get(this);
                if(inst != this.field_98218_a) continue;
                LOTRReflection.unlockFinalField(f);
                f.set(this, replaceSelect);
                break;
            }
        }
        catch(Exception e) {
            FMLLog.warning("LOTR: Error constructing Avoid Huorn AI", new Object[0]);
            e.printStackTrace();
        }
    }

}
