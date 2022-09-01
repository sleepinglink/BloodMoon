package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRFoods;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.fac.LOTRFaction;
import lotr.common.item.LOTRItemMug;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.biome.LOTRBiomeGenLothlorien;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityDarkWoodElf extends LOTREntityElf {
	 public LOTREntityDarkWoodElf(World world) {
	        super(world);
	    }

	    @Override
	    public void setupNPCName() {
	        this.familyInfo.setName(LOTRNames.getSindarinOrQuenyaName(this.rand, this.familyInfo.isMale()));
	    }

	    @Override
	    public LOTRNPCMount createMountToRide() {
	        LOTREntityHorse horse = (LOTREntityHorse) super.createMountToRide();
	        horse.setMountArmor(new ItemStack(LOTRMod.horseArmorDarkWood));
	        return horse;
	    }

	    @Override
	    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
	        data = super.onSpawnWithEgg(data);
	        this.npcItemsInv.setMeleeWeapon(new ItemStack(LOTRMod.daggerDarkwood));
	        this.npcItemsInv.setRangedWeapon(new ItemStack(LOTRMod.darkwoodBow));
	        this.npcItemsInv.setIdleItem(null);
	        return data;
	    }

	    @Override
	    public LOTRFaction getFaction() {
	        return LOTRFaction.DARK_WOOD;
	    }

	    @Override
	    protected LOTRAchievement getKillAchievement() {
	        return LOTRAchievement.killElf;
	    }

	    @Override
	    public float getAlignmentBonus() {
	        return 1.0f;
	    }

	    @Override
	    protected void dropElfItems(boolean flag, int i) {
	        super.dropElfItems(flag, i);
	        if(flag) {
	            int dropChance = 20 - i * 4;
	            if(this.rand.nextInt(dropChance = Math.max(dropChance, 1)) == 0) {
	                ItemStack elfDrink = new ItemStack(LOTRMod.mugRedWine);
	                elfDrink.setItemDamage(1 + this.rand.nextInt(3));
	                LOTRItemMug.setVessel(elfDrink, LOTRFoods.ELF_DRINK.getRandomVessel(this.rand), true);
	                this.entityDropItem(elfDrink, 0.0f);
	            }
	        }
	        if(this.rand.nextInt(6) == 0) {
	            this.dropChestContents(LOTRChestContents.ELF_HOUSE, 1, 1 + i);
	        }
	    }

	    @Override
	    public boolean canElfSpawnHere() {
	        int i = MathHelper.floor_double(this.posX);
	        int j = MathHelper.floor_double(this.boundingBox.minY);
	        int k = MathHelper.floor_double(this.posZ);
	        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
	        return j > 62 && this.worldObj.getBlock(i, j - 1, k) == biome.topBlock;
	    }

	    @Override
	    public float getBlockPathWeight(int i, int j, int k) {
	        float f = 0.0f;
	        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
	        if(biome instanceof LOTRBiomeGenLothlorien) {
	            f += 20.0f;
	        }
	        return f;
	    }

	    @Override
	    public String getSpeechBank(EntityPlayer entityplayer) {
	        if(this.isFriendly(entityplayer)) {
	            if(this.hiredNPCInfo.getHiringPlayer() == entityplayer) {
	                return "darkwood/elf/hired";
	            }
	            if(LOTRLevelData.getData(entityplayer).getAlignment(this.getFaction()) >= LOTREntityWoodElf.getWoodlandTrustLevel()) {
	                return "darkwood/elf/friendly";
	            }
	            return "darkwood/elf/neutral";
	        }
	        return "darkwood/elf/hostile";
	    }

	    @Override
	    public LOTRMiniQuest createMiniQuest() {
	        return LOTRMiniQuestFactory.DARK_WOOD.createQuest(this);
	    }

	    @Override
	    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
	        return LOTRMiniQuestFactory.DARK_WOOD;
	    }
	}
