package lotr.common.network;

import java.util.HashSet;
import java.util.Set;
import cpw.mods.fml.common.network.simpleimpl.*;
import io.netty.buffer.ByteBuf;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.fac.LOTRFaction;
import net.minecraft.entity.player.EntityPlayerMP;

public class LOTRPacketAlignmentChoices implements IMessage {
    private Set<LOTRFaction> setZeroFacs = new HashSet<LOTRFaction>();

    public LOTRPacketAlignmentChoices() {
    }

    public LOTRPacketAlignmentChoices(Set<LOTRFaction> facs) {
        this.setZeroFacs = facs;
    }

    @Override
    public void toBytes(ByteBuf data) {
        for(LOTRFaction fac : this.setZeroFacs) {
            data.writeByte(fac.ordinal());
        }
        data.writeByte(-1);
    }

    @Override
    public void fromBytes(ByteBuf data) {
        byte facID = 0;
        while((facID = data.readByte()) >= 0) {
            LOTRFaction fac = LOTRFaction.forID(facID);
            if(fac == null) continue;
            this.setZeroFacs.add(fac);
        }
    }

    public static class Handler implements IMessageHandler<LOTRPacketAlignmentChoices, IMessage> {
        @Override
        public IMessage onMessage(LOTRPacketAlignmentChoices packet, MessageContext context) {
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            LOTRPlayerData playerData = LOTRLevelData.getData(entityplayer);
            playerData.chooseUnwantedAlignments(entityplayer, packet.setZeroFacs);
            return null;
        }
    }

}
