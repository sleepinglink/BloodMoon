package lotr.common.entity.npc;

import lotr.common.*;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityNumenorBartender extends LOTREntityNumenor implements LOTRTradeable.Bartender {
    public LOTREntityNumenorBartender(World world) {
        super(world);
        this.addTargetTasks(false);
        this.npcLocationName = "entity.lotr.GondorBartender.locationName";
    }

    @Override
    public LOTRTradeEntries getBuyPool() {
        return LOTRTradeEntries.GONDOR_BARTENDER_BUY;
    }

    @Override
    public LOTRTradeEntries getSellPool() {
        return LOTRTradeEntries.GONDOR_BARTENDER_SELL;
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        this.npcItemsInv.setIdleItem(new ItemStack(LOTRMod.mug));
        return data;
    }

    @Override
    public void dropFewItems(boolean flag, int i) {
        super.dropFewItems(flag, i);
        int drinks = 1 + this.rand.nextInt(4) + i;
        for(int l = 0; l < drinks; ++l) {
            ItemStack drink = LOTRFoods.GONDOR_DRINK.getRandomFood(this.rand);
            this.entityDropItem(drink, 0.0f);
        }
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }

    @Override
    public boolean canTradeWith(EntityPlayer entityplayer) {
        return this.isFriendly(entityplayer);
    }

    @Override
    public void onPlayerTrade(EntityPlayer entityplayer, LOTRTradeEntries.TradeType type, ItemStack itemstack) {
        LOTRLevelData.getData(entityplayer).addAchievement(LOTRAchievement.tradeNumenorBartender);
    }

    @Override
    public boolean shouldTraderRespawn() {
        return true;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if(this.isFriendly(entityplayer)) {
            return "gondor/bartender/friendly";
        }
        return "gondor/bartender/hostile";
    }
}
