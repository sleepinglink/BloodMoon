package lotr.common.command;

import java.util.List;
import com.mojang.authlib.GameProfile;
import lotr.common.LOTRLevelData;
import lotr.common.LOTRPlayerData;
import lotr.common.network.LOTRPacketAlignmentSee;
import lotr.common.network.LOTRPacketHandler;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class LOTRCommandAlignmentSee extends CommandBase {
    @Override
    public String getCommandName() {
        return "alignmentsee";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.alignmentsee.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if(args.length >= 1) {
            String username = args[0];
            GameProfile profile = null;
            EntityPlayerMP entityplayer = MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
            profile = entityplayer != null ? entityplayer.getGameProfile() : MinecraftServer.getServer().func_152358_ax().func_152655_a(username);
            if(profile == null || profile.getId() == null) {
                throw new PlayerNotFoundException("commands.lotr.alignmentsee.noPlayer", username);
            }
            if(sender instanceof EntityPlayerMP) {
                LOTRPlayerData playerData = LOTRLevelData.getData(profile.getId());
                LOTRPacketAlignmentSee packet = new LOTRPacketAlignmentSee(username, playerData);
                LOTRPacketHandler.networkWrapper.sendTo(packet, (EntityPlayerMP) sender);
                return;
            }
        }
        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if(args.length == 1) {
            return CommandBase.getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int i) {
        return i == 0;
    }
}
