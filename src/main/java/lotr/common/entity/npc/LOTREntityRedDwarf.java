package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.fac.LOTRFaction;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTREntityRedDwarf extends LOTREntityDwarf{
	 public LOTREntityRedDwarf(World world) {
	        super(world);
	        this.familyInfo.marriageEntityClass = LOTREntityRedDwarf.class;
	        this.familyInfo.marriageAchievement = LOTRAchievement.marryRedDwarf;
	    }

	    @Override
	    protected LOTRFoods getDwarfFoods() {
	        return LOTRFoods.BLUE_DWARF;
	    }

	    @Override
	    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
	        data = super.onSpawnWithEgg(data);
	        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.dagger_red_dwarven));
	        this.npcItemsInv.setIdleItem(null);
	        return data;
	    }

	    @Override
	    public LOTRFaction getFaction() {
	        return LOTRFaction.RED_MOUNTAINS;
	    }



	    @Override
	    protected LOTRChestContents getLarderDrops() {
	        return LOTRChestContents.BLUE_DWARF_HOUSE_LARDER;
	    }

	    @Override
	    protected LOTRChestContents getGenericDrops() {
	        return LOTRChestContents.BLUE_MOUNTAINS_STRONGHOLD;
	    }

	    @Override
	    protected LOTRAchievement getKillAchievement() {
	        return LOTRAchievement.killRedDwarf;
	    }

	    @Override
	    public float getAlignmentBonus() {
	        return 1.0f;
	    }

	    @Override
	    public String getSpeechBank(EntityPlayer entityplayer) {
	        if(this.isFriendly(entityplayer)) {
	            if(this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
	                return "redDwarf/dwarf/hired";
	            }
	            return this.isChild() ? "redDwarf/child/friendly" : "redDwarf/dwarf/friendly";
	        }
	        return this.isChild() ? "redDwarf/child/hostile" : "redDwarf/dwarf/hostile";
	    }

	    @Override
	    public LOTRMiniQuest createMiniQuest() {
	        return LOTRMiniQuestFactory.RED_MOUNTAINS.createQuest(this);
	    }

	    @Override
	    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
	        return LOTRMiniQuestFactory.RED_MOUNTAINS;
	    }
	}