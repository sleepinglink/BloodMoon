package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.*;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRMod;
import net.minecraft.world.EnumDifficulty;

public class LOTRPacketLogin implements IMessage {
    public int ringPortalX;
    public int ringPortalY;
    public int ringPortalZ;
    public int ftCooldownMax;
    public int ftCooldownMin;
    public EnumDifficulty difficulty;
    public boolean difficultyLocked;
    public boolean alignmentZones;
    public boolean feastMode;
    public boolean enchanting;
    public boolean enchantingLOTR;
    public boolean conquestDecay;

    @Override
    public void toBytes(ByteBuf data) {
        data.writeInt(this.ringPortalX);
        data.writeInt(this.ringPortalY);
        data.writeInt(this.ringPortalZ);
        data.writeInt(this.ftCooldownMax);
        data.writeInt(this.ftCooldownMin);
        int diff = this.difficulty == null ? -1 : this.difficulty.getDifficultyId();
        data.writeByte(diff);
        data.writeBoolean(this.difficultyLocked);
        data.writeBoolean(this.alignmentZones);
        data.writeBoolean(this.feastMode);
        data.writeBoolean(this.enchanting);
        data.writeBoolean(this.enchantingLOTR);
        data.writeBoolean(this.conquestDecay);
    }

    @Override
    public void fromBytes(ByteBuf data) {
        this.ringPortalX = data.readInt();
        this.ringPortalY = data.readInt();
        this.ringPortalZ = data.readInt();
        this.ftCooldownMax = data.readInt();
        this.ftCooldownMin = data.readInt();
        byte diff = data.readByte();
        this.difficulty = diff >= 0 ? EnumDifficulty.getDifficultyEnum(diff) : null;
        this.difficultyLocked = data.readBoolean();
        this.alignmentZones = data.readBoolean();
        this.feastMode = data.readBoolean();
        this.enchanting = data.readBoolean();
        this.enchantingLOTR = data.readBoolean();
        this.conquestDecay = data.readBoolean();
    }

    public static class Handler implements IMessageHandler<LOTRPacketLogin, IMessage> {
        @Override
        public IMessage onMessage(LOTRPacketLogin packet, MessageContext context) {
            if(!LOTRMod.proxy.isSingleplayer()) {
                LOTRLevelData.destroyAllPlayerData();
            }
            LOTRLevelData.middleEarthPortalX = packet.ringPortalX;
            LOTRLevelData.middleEarthPortalY = packet.ringPortalY;
            LOTRLevelData.middleEarthPortalZ = packet.ringPortalZ;
            LOTRLevelData.setFTCooldown(packet.ftCooldownMax, packet.ftCooldownMin);
            EnumDifficulty diff = packet.difficulty;
            if(diff != null) {
                LOTRLevelData.setSavedDifficulty(diff);
                LOTRMod.proxy.setClientDifficulty(diff);
            }
            else {
                LOTRLevelData.setSavedDifficulty(null);
            }
            LOTRLevelData.setDifficultyLocked(packet.difficultyLocked);
            LOTRLevelData.setEnableAlignmentZones(packet.alignmentZones);
            LOTRLevelData.clientside_thisServer_feastMode = packet.feastMode;
            LOTRLevelData.clientside_thisServer_enchanting = packet.enchanting;
            LOTRLevelData.clientside_thisServer_enchantingLOTR = packet.enchantingLOTR;
            return null;
        }
    }

}
