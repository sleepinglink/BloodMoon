package lotr.common.entity.npc;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.ai.LOTREntityAIAttackOnCollide;
import lotr.common.entity.ai.LOTREntityAIDrink;
import lotr.common.entity.ai.LOTREntityAIEat;
import lotr.common.entity.ai.LOTREntityAIFollowHiringPlayer;
import lotr.common.entity.ai.LOTREntityAIHiredRemainStill;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.fac.LOTRFaction;
import lotr.common.quest.LOTRMiniQuest;
import lotr.common.quest.LOTRMiniQuestFactory;
import lotr.common.world.biome.LOTRBiomeGenRhunLand;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTREntityKhand extends LOTREntityMan{
    private static ItemStack[] weapons = new ItemStack[] {new ItemStack(LOTRMod.daggerKhand), new ItemStack(LOTRMod.daggerIron), new ItemStack(LOTRMod.daggerBronze)};

    public LOTREntityKhand(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new LOTREntityAIHiredRemainStill(this));
        this.tasks.addTask(2, this.createEasterlingAttackAI());
        this.tasks.addTask(3, new LOTREntityAIFollowHiringPlayer(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0));
        this.tasks.addTask(6, new LOTREntityAIEat(this, LOTRFoods.RHUN, 8000));
        this.tasks.addTask(6, new LOTREntityAIDrink(this, LOTRFoods.RHUN_DRINK, 8000));
        this.tasks.addTask(7, new EntityAIWatchClosest2(this, EntityPlayer.class, 8.0f, 0.02f));
        this.tasks.addTask(7, new EntityAIWatchClosest2(this, LOTREntityNPC.class, 5.0f, 0.02f));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLiving.class, 8.0f, 0.02f));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.addTargetTasks(false);
    }

    protected EntityAIBase createEasterlingAttackAI() {
        return new LOTREntityAIAttackOnCollide(this, 1.4, false);
    }

    @Override
    public LOTRNPCMount createMountToRide() {
        LOTREntityHorse horse = (LOTREntityHorse) super.createMountToRide();
        horse.setMountArmor(new ItemStack(LOTRMod.horseArmorKhand));
        return horse;
    }

    @Override
    public void setupNPCGender() {
        this.familyInfo.setMale(this.rand.nextBoolean());
    }

    @Override
    public void setupNPCName() {
        this.familyInfo.setName(LOTRNames.getRhunicName(this.rand, this.familyInfo.isMale()));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        data = super.onSpawnWithEgg(data);
        int i = this.rand.nextInt(weapons.length);
        this.npcItemsInv.setMeleeWeapon(weapons[i].copy());
        this.npcItemsInv.setIdleItem(null);
        return data;
    }

    @Override
    public LOTRFaction getFaction() {
        return LOTRFaction.KHAND;
    }

    @Override
    public String getNPCName() {
        return this.familyInfo.getName();
    }

    @Override
    protected void onAttackModeChange(LOTREntityNPC.AttackMode mode, boolean mounted) {
        if(mode == LOTREntityNPC.AttackMode.IDLE) {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getIdleItem());
        }
        else {
            this.setCurrentItemOrArmor(0, this.npcItemsInv.getMeleeWeapon());
        }
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
            this.dropChestContents(LOTRChestContents.EASTERLING_HOUSE, 1, 2 + i);
        }
    }

    @Override
    protected LOTRAchievement getKillAchievement() {
        return LOTRAchievement.killKhanding;
    }

    @Override
    public float getAlignmentBonus() {
        return 1.0f;
    }

    @Override
    public boolean getCanSpawnHere() {
        if(super.getCanSpawnHere()) {
            if(this.liftSpawnRestrictions) {
                return true;
            }
            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.boundingBox.minY);
            int k = MathHelper.floor_double(this.posZ);
            if(j > 62 && this.worldObj.getBlock(i, j - 1, k) == this.worldObj.getBiomeGenForCoords(i, k).topBlock) {
                return true;
            }
        }
        return false;
    }

    @Override
    public float getBlockPathWeight(int i, int j, int k) {
        float f = 0.0f;
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(i, k);
        if(biome instanceof LOTRBiomeGenRhunLand) {
            f += 20.0f;
        }
        return f;
    }

    @Override
    public String getSpeechBank(EntityPlayer entityplayer) {
        if(this.isDrunkard()) {
            return "khand/drunkard/neutral";
        }
        if(this.isFriendly(entityplayer)) {
            return "khand/man/friendly";
        }
        return "khand/man/hostile";
    }

    @Override
    public LOTRMiniQuest createMiniQuest() {
        return LOTRMiniQuestFactory.KHAND.createQuest(this);
    }

    @Override
    public LOTRMiniQuestFactory getBountyHelpSpeechDir() {
        return LOTRMiniQuestFactory.KHAND;
    }
}
