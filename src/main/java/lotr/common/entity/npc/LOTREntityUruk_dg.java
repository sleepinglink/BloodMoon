package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityUruk_dg extends LOTREntityDolGuldurOrc {
	   private static ItemStack[] urukWeapons = new ItemStack[] {new ItemStack(LOTRMod.swordDolGuldur),
	new ItemStack(LOTRMod.battleaxeDolGuldur), new ItemStack(LOTRMod.hammerDolGuldur), 
	new ItemStack(LOTRMod.daggerDolGuldur), new ItemStack(LOTRMod.daggerDolGuldurPoisoned),
	new ItemStack(LOTRMod.pikeDolGuldur)};

	    public LOTREntityUruk_dg(World world) {
	        super(world);
	        this.setSize(0.6f, 1.8f);
	        this.isWeakOrc = false;
	        this.npcShield = LOTRShields.ALIGNMENT_DOL_GULDUR;
	    }

	    @Override
	    public EntityAIBase createOrcAttackAI() {
	        return new LOTREntityAIAttackOnCollide(this, 1.5, false);
	    }

	    @Override
	    protected void applyEntityAttributes() {
	        super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
	        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0);
	        this.getEntityAttribute(npcRangedAccuracy).setBaseValue(0.75);
	    }

	    @Override
	    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
	        data = super.onSpawnWithEgg(data);
	        int i = this.rand.nextInt(urukWeapons.length);
	        this.npcItemsInv.setMeleeWeapon(urukWeapons[i].copy());
	        if(this.rand.nextInt(6) == 0) {
	            this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearDolGuldur));
	        }
	        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
	        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsUrukdg));
	        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsUrukdg));
	        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyUrukdg));
	        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetUrukdg));
	        return data;
	    }

	    @Override
	    public float getAlignmentBonus() {
	        return 2.0f;
	    }

	    @Override
	    protected LOTRAchievement getKillAchievement() {
	        return LOTRAchievement.killUruk_dg;
	    }

	    @Override
	    protected float getSoundPitch() {
	        return super.getSoundPitch() * 0.75f;
	    }
	}