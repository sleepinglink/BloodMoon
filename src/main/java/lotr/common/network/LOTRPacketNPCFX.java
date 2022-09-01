package lotr.common.network;

import cpw.mods.fml.common.network.simpleimpl.*;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class LOTRPacketNPCFX implements IMessage {
    private int entityID;
    private FXType type;

    public LOTRPacketNPCFX() {
    }

    public LOTRPacketNPCFX(int i, FXType t) {
        this.entityID = i;
        this.type = t;
    }

    @Override
    public void toBytes(ByteBuf data) {
        data.writeInt(this.entityID);
        data.writeByte(this.type.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf data) {
        this.entityID = data.readInt();
        byte typeID = data.readByte();
        this.type = FXType.values()[typeID];
    }

    public static class Handler implements IMessageHandler<LOTRPacketNPCFX, IMessage> {
        @Override
        public IMessage onMessage(LOTRPacketNPCFX packet, MessageContext context) {
            World world = LOTRMod.proxy.getClientWorld();
            Entity entity = world.getEntityByID(packet.entityID);
            if(entity instanceof LOTREntityNPC) {
                LOTREntityNPC npc = (LOTREntityNPC) entity;
                if(packet.type == FXType.HEARTS) {
                    npc.spawnHearts();
                }
                else if(packet.type == FXType.EATING) {
                    npc.spawnFoodParticles();
                }
                else if(packet.type == FXType.SMOKE) {
                    npc.spawnSmokes();
                }
            }
            return null;
        }
    }

    public static enum FXType {
        HEARTS, EATING, SMOKE;

    }

}
