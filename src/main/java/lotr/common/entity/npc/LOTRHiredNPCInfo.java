package lotr.common.entity.npc;

import java.util.List;
import java.util.UUID;
import lotr.common.*;
import lotr.common.entity.LOTRMountFunctions;
import lotr.common.fac.LOTRFaction;
import lotr.common.inventory.LOTRInventoryNPC;
import lotr.common.network.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRHiredNPCInfo {
    private LOTREntityNPC theEntity;
    private UUID hiringPlayerUUID;
    public boolean isActive;
    public float alignmentRequiredToCommand;
    public LOTRUnitTradeEntry.PledgeType pledgeType = LOTRUnitTradeEntry.PledgeType.NONE;
    private Task hiredTask = Task.WARRIOR;
    private boolean canMove = true;
    public int mobKills;
    public boolean teleportAutomatically = true;
    private String hiredSquadron;
    public boolean guardMode;
    public static int GUARD_RANGE_MIN = 1;
    public static int GUARD_RANGE_DEFAULT = 8;
    public static int GUARD_RANGE_MAX = 64;
    private int guardRange = GUARD_RANGE_DEFAULT;
    private LOTRInventoryNPC hiredInventory;
    public boolean inCombat;
    private boolean prevInCombat;
    public boolean isGuiOpen;
    private boolean targetFromCommandSword;
    public boolean wasAttackCommanded = false;
    private boolean doneFirstUpdate = false;
    private boolean resendData = true;

    public LOTRHiredNPCInfo(LOTREntityNPC npc) {
        this.theEntity = npc;
    }

    public void hireUnit(EntityPlayer entityplayer, boolean setLocation, LOTRFaction hiringFaction, LOTRUnitTradeEntry tradeEntry, String squadron, Entity mount) {
        float alignment = tradeEntry.alignmentRequired;
        LOTRUnitTradeEntry.PledgeType pledge = tradeEntry.getPledgeType();
        Task task = tradeEntry.task;
        if(setLocation) {
            this.theEntity.setLocationAndAngles(entityplayer.posX, entityplayer.boundingBox.minY, entityplayer.posZ, this.theEntity.getRNG().nextFloat() * 360.0f, 0.0f);
        }
        this.isActive = true;
        this.alignmentRequiredToCommand = alignment;
        this.pledgeType = pledge;
        this.setHiringPlayer(entityplayer);
        this.setTask(task);
        this.setSquadron(squadron);
        if(hiringFaction != null) {
            LOTRLevelData.getData(entityplayer).getFactionData(hiringFaction).addHire();
        }
        if(mount != null) {
            mount.setLocationAndAngles(this.theEntity.posX, this.theEntity.boundingBox.minY, this.theEntity.posZ, this.theEntity.rotationYaw, 0.0f);
            if(mount instanceof LOTREntityNPC) {
                LOTREntityNPC hiredMountNPC = (LOTREntityNPC) mount;
                hiredMountNPC.hiredNPCInfo.hireUnit(entityplayer, setLocation, hiringFaction, tradeEntry, squadron, null);
            }
            this.theEntity.mountEntity(mount);
            if(mount instanceof LOTRNPCMount && !(mount instanceof LOTREntityNPC)) {
                this.theEntity.setRidingHorse(true);
                LOTRNPCMount hiredHorse = (LOTRNPCMount) (mount);
                hiredHorse.setBelongsToNPC(true);
                LOTRMountFunctions.setNavigatorRangeFromNPC(hiredHorse, this.theEntity);
            }
        }
    }

    public void setHiringPlayer(EntityPlayer entityplayer) {
        this.hiringPlayerUUID = entityplayer == null ? null : entityplayer.getUniqueID();
        this.markDirty();
    }

    public EntityPlayer getHiringPlayer() {
        if(this.hiringPlayerUUID == null) {
            return null;
        }
        return this.theEntity.worldObj.func_152378_a(this.hiringPlayerUUID);
    }

    public UUID getHiringPlayerUUID() {
        return this.hiringPlayerUUID;
    }

    public Task getTask() {
        return this.hiredTask;
    }

    public void setTask(Task t) {
        this.hiredTask = t;
        if(this.hiredTask == Task.FARMER) {
            this.hiredInventory = new LOTRInventoryNPC("HiredInventory", this.theEntity, 4);
        }
    }

    public LOTRInventoryNPC getHiredInventory() {
        return this.hiredInventory;
    }

    private void markDirty() {
        if(!this.theEntity.worldObj.isRemote) {
            if(this.theEntity.ticksExisted > 0) {
                this.resendData = true;
            }
            else {
                this.sendDataToAllWatchers();
            }
        }
    }

    public void onUpdate() {
        if(!this.theEntity.worldObj.isRemote) {
            EntityPlayer entityplayer;
            if(!this.doneFirstUpdate) {
                this.doneFirstUpdate = true;
            }
            if(this.resendData) {
                this.sendDataToAllWatchers();
                this.resendData = false;
            }
            if(this.isActive && (entityplayer = this.getHiringPlayer()) != null) {
                LOTRFaction fac = this.theEntity.getHiringFaction();
                LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
                boolean canCommand = true;
                if(pd.getAlignment(fac) < this.alignmentRequiredToCommand) {
                    canCommand = false;
                }
                if(!this.pledgeType.canAcceptPlayer(entityplayer, fac)) {
                    canCommand = false;
                }
                if(!canCommand) {
                    this.dismissUnit(true);
                }
            }
            boolean bl = this.inCombat = this.theEntity.getAttackTarget() != null;
            if(this.inCombat != this.prevInCombat) {
                this.sendClientPacket(false);
            }
            this.prevInCombat = this.inCombat;
            if(this.getTask() == Task.WARRIOR && !this.inCombat && this.shouldFollowPlayer() && this.theEntity.getRNG().nextInt(4000) == 0) {
                EntityPlayer hiringPlayer = this.getHiringPlayer();
                double range = 16.0;
                if(hiringPlayer != null && this.theEntity.getDistanceSqToEntity(hiringPlayer) < range * range) {
                    this.theEntity.sendSpeechBank(hiringPlayer, this.theEntity.getSpeechBank(hiringPlayer));
                }
            }
        }
    }

    public void dismissUnit(boolean isDesertion) {
        if(isDesertion) {
            this.getHiringPlayer().addChatMessage(new ChatComponentTranslation("lotr.hiredNPC.desert", this.theEntity.getCommandSenderName()));
        }
        else {
            this.getHiringPlayer().addChatMessage(new ChatComponentTranslation("lotr.hiredNPC.dismiss", this.theEntity.getCommandSenderName()));
        }
        if(this.hiredTask == Task.FARMER && this.hiredInventory != null) {
            this.hiredInventory.dropAllItems();
        }
        this.isActive = false;
        this.canMove = true;
        this.sendClientPacket(false);
        this.setHiringPlayer(null);
    }

    public void onDeath(DamageSource damagesource) {
        EntityPlayer hiringPlayer;
        if(!this.theEntity.worldObj.isRemote && this.isActive && this.getHiringPlayer() != null && LOTRLevelData.getData(hiringPlayer = this.getHiringPlayer()).getEnableHiredDeathMessages()) {
            hiringPlayer.addChatMessage(new ChatComponentTranslation("lotr.hiredNPC.death", this.theEntity.func_110142_aN().func_151521_b()));
        }
        if(!this.theEntity.worldObj.isRemote && this.hiredInventory != null) {
            this.hiredInventory.dropAllItems();
        }
    }

    public void halt() {
        this.canMove = false;
        this.theEntity.setAttackTarget(null);
        this.sendClientPacket(false);
    }

    public void ready() {
        this.canMove = true;
        this.sendClientPacket(false);
    }

    public boolean isHalted() {
        return !this.guardMode && !this.canMove;
    }

    public boolean shouldFollowPlayer() {
        return !this.guardMode && this.canMove;
    }

    public boolean getObeyHornHaltReady() {
        if(this.hiredTask != Task.WARRIOR) {
            return false;
        }
        return !this.guardMode;
    }

    public boolean getObeyHornSummon() {
        if(this.hiredTask != Task.WARRIOR) {
            return false;
        }
        return !this.guardMode;
    }

    public boolean getObeyCommandSword() {
        if(this.hiredTask != Task.WARRIOR) {
            return false;
        }
        return !this.guardMode;
    }

    public boolean isGuardMode() {
        return this.guardMode;
    }

    public void setGuardMode(boolean flag) {
        this.guardMode = flag;
        if(flag) {
            int i = MathHelper.floor_double(this.theEntity.posX);
            int j = MathHelper.floor_double(this.theEntity.posY);
            int k = MathHelper.floor_double(this.theEntity.posZ);
            this.theEntity.setHomeArea(i, j, k, this.guardRange);
        }
        else {
            this.theEntity.detachHome();
        }
    }

    public int getGuardRange() {
        return this.guardRange;
    }

    public void setGuardRange(int range) {
        this.guardRange = MathHelper.clamp_int(range, GUARD_RANGE_MIN, GUARD_RANGE_MAX);
        if(this.guardMode) {
            int i = MathHelper.floor_double(this.theEntity.posX);
            int j = MathHelper.floor_double(this.theEntity.posY);
            int k = MathHelper.floor_double(this.theEntity.posZ);
            this.theEntity.setHomeArea(i, j, k, this.guardRange);
        }
    }

    public String getSquadron() {
        return this.hiredSquadron;
    }

    public void setSquadron(String s) {
        this.hiredSquadron = s;
        this.markDirty();
    }

    public String getStatusString() {
        String status = "";
        if(this.hiredTask == Task.WARRIOR) {
            status = this.inCombat ? StatCollector.translateToLocal("lotr.hiredNPC.status.combat") : (this.isHalted() ? StatCollector.translateToLocal("lotr.hiredNPC.status.halted") : (this.guardMode ? StatCollector.translateToLocal("lotr.hiredNPC.status.guard") : StatCollector.translateToLocal("lotr.hiredNPC.status.ready")));
        }
        else if(this.hiredTask == Task.FARMER) {
            status = this.guardMode ? StatCollector.translateToLocal("lotr.hiredNPC.status.farming") : StatCollector.translateToLocal("lotr.hiredNPC.status.following");
        }
        String s = StatCollector.translateToLocalFormatted("lotr.hiredNPC.status", status);
        return s;
    }

    public void onSetTarget(EntityLivingBase newTarget, EntityLivingBase prevTarget) {
        if(newTarget == null || newTarget != prevTarget) {
            this.targetFromCommandSword = false;
            this.wasAttackCommanded = false;
        }
    }

    public void commandSwordAttack(EntityLivingBase target) {
        if(target != null && LOTRMod.canNPCAttackEntity(this.theEntity, target, true)) {
            this.theEntity.getNavigator().clearPathEntity();
            this.theEntity.setRevengeTarget(target);
            this.theEntity.setAttackTarget(target);
            this.targetFromCommandSword = true;
        }
    }

    public void commandSwordCancel() {
        if(this.targetFromCommandSword) {
            this.theEntity.getNavigator().clearPathEntity();
            this.theEntity.setRevengeTarget(null);
            this.theEntity.setAttackTarget(null);
            this.targetFromCommandSword = false;
        }
    }

    public void onKillEntity(EntityLivingBase target) {
        if(!this.theEntity.worldObj.isRemote && this.isActive) {
            ++this.mobKills;
            this.sendClientPacket(false);
            if(this.getTask() == Task.WARRIOR) {
                boolean wasEnemy = false;
                LOTRFaction unitFaction = this.theEntity.getFaction();
                if(target instanceof EntityPlayer && LOTRLevelData.getData((EntityPlayer) target).getAlignment(unitFaction) < 0.0f) {
                    wasEnemy = true;
                }
                if(LOTRMod.getNPCFaction(target).isBadRelation(unitFaction)) {
                    wasEnemy = true;
                }
                if(wasEnemy && this.theEntity.getRNG().nextInt(3) == 0) {
                    EntityPlayer hiringPlayer = this.getHiringPlayer();
                    double range = 16.0;
                    if(hiringPlayer != null && this.theEntity.getDistanceSqToEntity(hiringPlayer) < range * range) {
                        this.theEntity.sendSpeechBank(hiringPlayer, this.theEntity.getSpeechBank(hiringPlayer));
                    }
                }
            }
        }
    }

    public boolean tryTeleportToHiringPlayer(boolean failsafe) {
        World world = this.theEntity.worldObj;
        if(!world.isRemote) {
            EntityPlayer entityplayer = this.getHiringPlayer();
            if(this.isActive && entityplayer != null && this.theEntity.riddenByEntity == null) {
                int i = MathHelper.floor_double(entityplayer.posX);
                int j = MathHelper.floor_double(entityplayer.boundingBox.minY);
                int k = MathHelper.floor_double(entityplayer.posZ);
                float minDist = 3.0f;
                float maxDist = 6.0f;
                float extraDist = this.theEntity.width / 2.0f;
                if(this.theEntity.ridingEntity instanceof EntityLiving) {
                    extraDist = Math.max(this.theEntity.width, this.theEntity.ridingEntity.width) / 2.0f;
                }
                minDist += extraDist;
                maxDist += extraDist;
                int attempts = 120;
                for(int l = 0; l < attempts; ++l) {
                    float height;
                    float yExtra;
                    double d2;
                    float angle = world.rand.nextFloat() * 3.1415927f * 2.0f;
                    float sin = MathHelper.sin(angle);
                    float cos = MathHelper.cos(angle);
                    float r = MathHelper.randomFloatClamp(world.rand, minDist, maxDist);
                    int i1 = MathHelper.floor_double(i + 0.5 + cos * r);
                    int k1 = MathHelper.floor_double(k + 0.5 + sin * r);
                    double d = i1 + 0.5;
                    float halfWidth = this.theEntity.width / 2.0f;
                    int j1 = MathHelper.getRandomIntegerInRange(world.rand, j - 4, j + 4);
                    double d1 = j1;
                    AxisAlignedBB npcBB = AxisAlignedBB.getBoundingBox(d - halfWidth, d1 + (yExtra = -this.theEntity.yOffset + this.theEntity.ySize), (d2 = k1 + 0.5) - halfWidth, d + halfWidth, d1 + yExtra + (height = this.theEntity.height), d2 + halfWidth);
                    if(!world.func_147461_a(npcBB).isEmpty() || !world.getBlock(i1, j1 - 1, k1).isSideSolid(world, i1, j1 - 1, k1, ForgeDirection.UP)) continue;
                    if(this.theEntity.ridingEntity instanceof EntityLiving) {
                        EntityLiving mount = (EntityLiving) this.theEntity.ridingEntity;
                        float mHalfWidth = mount.width / 2.0f;
                        float mYExtra = -mount.yOffset + mount.ySize;
                        float mHeight = mount.height;
                        AxisAlignedBB mountBB = AxisAlignedBB.getBoundingBox(d - mHalfWidth, d1 + mYExtra, d2 - mHalfWidth, d + mHalfWidth, d1 + mYExtra + mHeight, d2 + mHalfWidth);
                        if(!world.func_147461_a(mountBB).isEmpty()) continue;
                        mount.setLocationAndAngles(d, d1, d2, this.theEntity.rotationYaw, this.theEntity.rotationPitch);
                        mount.fallDistance = 0.0f;
                        mount.getNavigator().clearPathEntity();
                        mount.setAttackTarget(null);
                        this.theEntity.fallDistance = 0.0f;
                        this.theEntity.getNavigator().clearPathEntity();
                        this.theEntity.setAttackTarget(null);
                        return true;
                    }
                    this.theEntity.setLocationAndAngles(d, d1, d2, this.theEntity.rotationYaw, this.theEntity.rotationPitch);
                    this.theEntity.fallDistance = 0.0f;
                    this.theEntity.getNavigator().clearPathEntity();
                    this.theEntity.setAttackTarget(null);
                    return true;
                }
                if(failsafe) {
                    double d = i + 0.5;
                    double d1 = j;
                    double d2 = k + 0.5;
                    if(world.getBlock(i, j - 1, k).isSideSolid(world, i, j - 1, k, ForgeDirection.UP)) {
                        if(this.theEntity.ridingEntity instanceof EntityLiving) {
                            EntityLiving mount = (EntityLiving) this.theEntity.ridingEntity;
                            mount.setLocationAndAngles(d, d1, d2, this.theEntity.rotationYaw, this.theEntity.rotationPitch);
                            mount.fallDistance = 0.0f;
                            mount.getNavigator().clearPathEntity();
                            mount.setAttackTarget(null);
                            this.theEntity.fallDistance = 0.0f;
                            this.theEntity.getNavigator().clearPathEntity();
                            this.theEntity.setAttackTarget(null);
                            return true;
                        }
                        this.theEntity.setLocationAndAngles(d, d1, d2, this.theEntity.rotationYaw, this.theEntity.rotationPitch);
                        this.theEntity.fallDistance = 0.0f;
                        this.theEntity.getNavigator().clearPathEntity();
                        this.theEntity.setAttackTarget(null);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagCompound data = new NBTTagCompound();
        data.setBoolean("IsActive", this.isActive);
        if(this.hiringPlayerUUID != null) {
            data.setString("HiringPlayerUUID", this.hiringPlayerUUID.toString());
        }
        data.setFloat("AlignReqF", this.alignmentRequiredToCommand);
        data.setByte("PledgeType", (byte) this.pledgeType.typeID);
        data.setBoolean("CanMove", this.canMove);
        data.setBoolean("TeleportAutomatically", this.teleportAutomatically);
        data.setInteger("MobKills", this.mobKills);
        data.setBoolean("GuardMode", this.guardMode);
        data.setInteger("GuardRange", this.guardRange);
        data.setInteger("Task", this.hiredTask.ordinal());
        if(!StringUtils.isNullOrEmpty(this.hiredSquadron)) {
            data.setString("Squadron", this.hiredSquadron);
        }
        if(this.hiredInventory != null) {
            this.hiredInventory.writeToNBT(data);
        }
        nbt.setTag("HiredNPCInfo", data);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagCompound data = nbt.getCompoundTag("HiredNPCInfo");
        if(data != null) {
            String savedUUID;
            if(data.hasKey("HiringPlayerName")) {
                String name = data.getString("HiringPlayerName");
                this.hiringPlayerUUID = UUID.fromString(PreYggdrasilConverter.func_152719_a(name));
            }
            else if(data.hasKey("HiringPlayerUUID") && !StringUtils.isNullOrEmpty(savedUUID = data.getString("HiringPlayerUUID"))) {
                this.hiringPlayerUUID = UUID.fromString(savedUUID);
            }
            this.isActive = data.getBoolean("IsActive");
            this.alignmentRequiredToCommand = data.hasKey("AlignmentRequired") ? (float) data.getInteger("AlignmentRequired") : data.getFloat("AlignReqF");
            if(data.hasKey("PledgeType")) {
                byte pledgeID = data.getByte("PledgeType");
                this.pledgeType = LOTRUnitTradeEntry.PledgeType.forID(pledgeID);
            }
            this.canMove = data.getBoolean("CanMove");
            if(data.hasKey("TeleportAutomatically")) {
                this.teleportAutomatically = data.getBoolean("TeleportAutomatically");
                this.mobKills = data.getInteger("MobKills");
                this.setGuardMode(data.getBoolean("GuardMode"));
                this.setGuardRange(data.getInteger("GuardRange"));
            }
            this.setTask(Task.forID(data.getInteger("Task")));
            if(data.hasKey("Squadron")) {
                this.hiredSquadron = data.getString("Squadron");
            }
            if(this.hiredInventory != null) {
                this.hiredInventory.readFromNBT(data);
            }
        }
    }

    public void sendData(EntityPlayerMP entityplayer) {
        LOTRPacketHiredInfo packet = new LOTRPacketHiredInfo(this.theEntity.getEntityId(), this.hiringPlayerUUID, this.getSquadron());
        LOTRPacketHandler.networkWrapper.sendTo(packet, entityplayer);
    }

    private void sendDataToAllWatchers() {
        int x = MathHelper.floor_double(this.theEntity.posX) >> 4;
        int z = MathHelper.floor_double(this.theEntity.posZ) >> 4;
        PlayerManager playermanager = ((WorldServer) this.theEntity.worldObj).getPlayerManager();
        List players = this.theEntity.worldObj.playerEntities;
        for(Object obj : players) {
            EntityPlayerMP entityplayer = (EntityPlayerMP) obj;
            if(!playermanager.isPlayerWatchingChunk(entityplayer, x, z)) continue;
            this.sendData(entityplayer);
        }
    }

    public void receiveData(LOTRPacketHiredInfo packet) {
        this.hiringPlayerUUID = packet.hiringPlayer;
        this.setSquadron(packet.squadron);
    }

    public void sendClientPacket(boolean shouldOpenGui) {
        if(this.theEntity.worldObj.isRemote || this.getHiringPlayer() == null) {
            return;
        }
        LOTRPacketHiredGui packet = new LOTRPacketHiredGui(this.theEntity.getEntityId(), shouldOpenGui);
        packet.isActive = this.isActive;
        packet.canMove = this.canMove;
        packet.teleportAutomatically = this.teleportAutomatically;
        packet.mobKills = this.mobKills;
        packet.alignmentRequired = this.alignmentRequiredToCommand;
        packet.pledgeType = this.pledgeType;
        packet.inCombat = this.inCombat;
        packet.guardMode = this.guardMode;
        packet.guardRange = this.guardRange;
        packet.task = this.hiredTask;
        LOTRPacketHandler.networkWrapper.sendTo(packet, (EntityPlayerMP) this.getHiringPlayer());
        if(shouldOpenGui) {
            this.isGuiOpen = true;
        }
    }

    public void receiveClientPacket(LOTRPacketHiredGui packet) {
        this.isActive = packet.isActive;
        this.canMove = packet.canMove;
        this.teleportAutomatically = packet.teleportAutomatically;
        this.mobKills = packet.mobKills;
        this.alignmentRequiredToCommand = packet.alignmentRequired;
        this.pledgeType = packet.pledgeType;
        this.inCombat = packet.inCombat;
        this.guardMode = packet.guardMode;
        this.guardRange = packet.guardRange;
        this.setTask(packet.task);
    }

    public static enum Task {
        WARRIOR, FARMER;

        public static Task forID(int id) {
            for(Task task : Task.values()) {
                if(task.ordinal() != id) continue;
                return task;
            }
            return WARRIOR;
        }
    }

}
