package lotr.common.entity.npc;


import lotr.common.LOTRMod;
import lotr.common.LOTRShields;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityNumenorSoldier extends LOTREntityNumenor {
	  public LOTREntityNumenorSoldier(World world) {
	        super(world);
	        this.addTargetTasks(true);
	        this.spawnRidingHorse = this.rand.nextInt(6) == 0;
	        this.npcShield = LOTRShields.ALIGNMENT_NUMENOR;
	    }

	    @Override
	    protected void applyEntityAttributes() {
	        super.applyEntityAttributes();
	        this.getEntityAttribute(npcRangedAccuracy).setBaseValue(0.75);
	    }

	    @Override
	    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
	        data = super.onSpawnWithEgg(data);
	        int i = this.rand.nextInt(10);
	        if(i == 0 || i == 1 || i == 2) {
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.swordNumenor));
	        }
	        else if(i == 3 || i == 4 || i == 5) {
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.hammerNumenor));
	        }
	        else if(i == 6) {
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerNumenor));
	        }
	        else if(i == 7) {
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerNumenorPoisoned));
	        }
	        else if(i == 8) {
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.pikeNumenor));
	        }
	        if(this.rand.nextInt(5) == 0) {
	            this.npcItemsInv.setSpearBackup(this.npcItemsInv.getMeleeWeapon());
	            this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.spearNumenor));
	        }
	        this.npcItemsInv.setIdleItem(this.npcItemsInv.getMeleeWeapon());
	        this.setCurrentItemOrArmor(1, new ItemStack(LOTRMod.bootsNumenor));
	        this.setCurrentItemOrArmor(2, new ItemStack(LOTRMod.legsNumenor));
	        this.setCurrentItemOrArmor(3, new ItemStack(LOTRMod.bodyNumenor));
	        this.setCurrentItemOrArmor(4, new ItemStack(LOTRMod.helmetNumenor));
	        return data;
	    }
	    @Override
	    protected void dropFewItems(boolean flag, int i) {
	        super.dropFewItems(flag, i);
	        int bones = this.rand.nextInt(2) + this.rand.nextInt(i + 1);
	        for(int l = 0; l < bones; ++l) {
	            this.dropItem(Items.bone, 1);
	        }
	        this.dropRhunItems(flag, i);
	    }

	    protected void dropRhunItems(boolean flag, int i) {
	        if(this.rand.nextInt(6) == 0) {
	            this.dropChestContents(LOTRChestContents.NUMENOR_SOLDIER, 1, 2 + i);
	        }
	    }
	}



