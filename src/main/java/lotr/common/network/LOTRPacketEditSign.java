package lotr.common.network;

import com.google.common.base.Charsets;
import cpw.mods.fml.common.network.simpleimpl.*;
import io.netty.buffer.ByteBuf;
import lotr.common.tileentity.LOTRTileEntitySign;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.world.World;

public class LOTRPacketEditSign implements IMessage {
    private int posX;
    private int posY;
    private int posZ;
    private String[] signText;

    public LOTRPacketEditSign() {
    }

    public LOTRPacketEditSign(LOTRTileEntitySign sign) {
        this.posX = sign.xCoord;
        this.posY = sign.yCoord;
        this.posZ = sign.zCoord;
        this.signText = sign.signText;
    }

    @Override
    public void toBytes(ByteBuf data) {
        data.writeInt(this.posX);
        data.writeInt(this.posY);
        data.writeInt(this.posZ);
        data.writeByte(this.signText.length);
        for(String line : this.signText) {
            if(line == null) {
                data.writeShort(-1);
                continue;
            }
            byte[] lineBytes = line.getBytes(Charsets.UTF_8);
            data.writeShort(lineBytes.length);
            data.writeBytes(lineBytes);
        }
    }

    @Override
    public void fromBytes(ByteBuf data) {
        this.posX = data.readInt();
        this.posY = data.readInt();
        this.posZ = data.readInt();
        byte lines = data.readByte();
        this.signText = new String[lines];
        for(int i = 0; i < this.signText.length; ++i) {
            String line;
            short length = data.readShort();
            this.signText[i] = length > -1 ? (line = data.readBytes(length).toString(Charsets.UTF_8)) : "";
        }
    }

    public static class Handler implements IMessageHandler<LOTRPacketEditSign, IMessage> {
        @Override
        public IMessage onMessage(LOTRPacketEditSign packet, MessageContext context) {
            TileEntity te;
            EntityPlayerMP entityplayer = context.getServerHandler().playerEntity;
            entityplayer.func_143004_u();
            World world = entityplayer.worldObj;
            int i = packet.posX;
            int j = packet.posY;
            int k = packet.posZ;
            String[] newText = packet.signText;
            if(world.blockExists(i, j, k) && (te = world.getTileEntity(i, j, k)) instanceof LOTRTileEntitySign) {
                LOTRTileEntitySign sign = (LOTRTileEntitySign) te;
                if(!sign.isEditable() || sign.getEditingPlayer() != entityplayer) {
                    MinecraftServer.getServer().logWarning("Player " + entityplayer.getCommandSenderName() + " just tried to change non-editable LOTR sign");
                    return null;
                }
                for(int l = 0; l < sign.getNumLines(); ++l) {
                    String line = newText[l];
                    boolean valid = true;
                    if(line.length() > 15) {
                        valid = false;
                    }
                    else {
                        for(int c = 0; c < line.length(); ++c) {
                            if(ChatAllowedCharacters.isAllowedCharacter(line.charAt(c))) continue;
                            valid = false;
                        }
                    }
                    if(valid) continue;
                    newText[l] = "!?";
                }
                System.arraycopy(newText, 0, sign.signText, 0, sign.getNumLines());
                sign.markDirty();
                world.markBlockForUpdate(i, j, k);
            }
            return null;
        }
    }

}
