package lotr.common.entity.ai;

import lotr.common.entity.npc.LOTREntityGollum;
import net.minecraft.entity.ai.EntityAIAvoidEntity;

public class LOTREntityAIGollumAvoidEntity extends EntityAIAvoidEntity {
    private LOTREntityGollum theGollum;

    public LOTREntityAIGollumAvoidEntity(LOTREntityGollum gollum, Class entityClass, float f, double d, double d1) {
        super(gollum, entityClass, f, d, d1);
        this.theGollum = gollum;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.theGollum.setGollumFleeing(true);
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.theGollum.setGollumFleeing(false);
    }
}
