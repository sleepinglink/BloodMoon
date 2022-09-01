package lotr.common.entity.npc;

import lotr.common.*;
import lotr.common.world.spawning.LOTRInvasions;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityGaladhrimLord extends LOTREntityGaladhrimWarrior implements LOTRUnitTradeable {
    public LOTREntityGaladhrimLord(World world) {
        super(world);
        this.addTargetTasks(false);
        this.npcCape = LOTRCapes.GALADHRIM;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordElven));
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsElven));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsElven));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyElven));
        this.setCurrentItemOrArmor(4, null);
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 5.0f;
    }

    @Override
    public LOTRUnitTradeEntries getUnits() {
        return LOTRUnitTradeEntries.ELF_LORD;
    }

    @Override
    public LOTRInvasions getConquestHorn() {
        return LOTRInvasions.GALADHRIM;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= 300.0f && this.isFriendly(entityplayer);
    }

    @Override
    public void onUnitTrade(EntityPlayer entityplayer) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeElfLord);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return true;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if(this.isFriendly(entityplayer)) {
            if(this.canTradeWith(entityplayer)) {
                return "galadhrim/lord/friendly";
            }
            return "galadhrim/lord/neutral";
        }
        return "galadhrim/warrior/hostile";
    }
}
