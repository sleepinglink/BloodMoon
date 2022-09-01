package lotr.common.entity.ai;

import lotr.common.entity.npc.LOTREntityBandit;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class LOTREntityAINearestAttackableTargetBandit extends LOTREntityAINearestAttackableTargetBasic {
    public LOTREntityAINearestAttackableTargetBandit(EntityCreature entity, Class targetClass, int chance, boolean flag) {
        super(entity, targetClass, chance, flag);
    }

    public LOTREntityAINearestAttackableTargetBandit(EntityCreature entity, Class targetClass, int chance, boolean flag, IEntitySelector selector) {
        super(entity, targetClass, chance, flag, selector);
    }

    @Override
    public boolean shouldExecute() {
        LOTREntityBandit bandit = (LOTREntityBandit) this.taskOwner;
        if(!bandit.banditInventory.isEmpty()) {
            return false;
        }
        return super.shouldExecute();
    }

    @Override
    protected boolean isSuitableTarget(EntityLivingBase entity, boolean flag) {
        return entity instanceof EntityPlayer && super.isSuitableTarget(entity, flag);
    }

    @Override
    protected boolean isPlayerSuitableTarget(EntityPlayer entityplayer) {
        LOTREntityBandit bandit = (LOTREntityBandit) this.taskOwner;
        if(bandit.canStealFromPlayerInv(entityplayer)) {
            return false;
        }
        return super.isPlayerSuitableTarget(entityplayer);
    }
}
