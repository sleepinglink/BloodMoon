package lotr.common.entity.npc;

import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityDunlendingWarrior extends LOTREntityDunlending {
    public LOTREntityDunlendingWarrior(World world) {
        super(world);
        this.npcShield = LOTRShields.ALIGNMENT_DUNLAND;
    }

    @Override
    public EntityAIBase getDunlendingAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.6, false);
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(true);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(9);
        if(i == 0 || i == 1) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordDunland));
        }
        else if(i == 2 || i == 3) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.dunlendingTrident));
        }
        else if(i == 4) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(Items.wooden_sword));
        }
        else if(i == 5) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.battleaxeDunland));
        }
        else if(i == 6) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.pikeDunland));
        }
        else if(i == 7) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(Items.stone_hoe));
        }
        else if(i == 8) {
            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearDunland));
        }
        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsDunlending));
        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsDunlending));
        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyDunlending));
        if(this.rand.nextInt(10) != 0) {
            this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetDunlending));
        }
        return data;
    }

    @Override
    public float getAlignmentBonus() {
        return 2.0f;
    }
}
