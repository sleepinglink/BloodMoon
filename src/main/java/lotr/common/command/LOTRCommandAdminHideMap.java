package lotr.common.command;

import java.util.List;
import lotr.common.LOTRLevelData;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class LOTRCommandAdminHideMap extends CommandBase {
    @Override
    public String getCommandName() {
        return "opHideMap";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.lotr.opHideMap.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if(sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            if(MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile())) {
                if(player.capabilities.isCreativeMode) {
                    LOTRLevelData.getData(player).setAdminHideMap(true);
                    CommandBase.func_152373_a(sender, this, "commands.lotr.opHideMap.hiding", new Object[0]);
                    return;
                }
                throw new WrongUsageException("commands.lotr.opHideMap.notCreative", new Object[0]);
            }
            throw new WrongUsageException("commands.lotr.opHideMap.notOp", new Object[0]);
        }
        throw new WrongUsageException("commands.lotr.opHideMap.notOp", new Object[0]);
    }

    public static void notifyUnhidden(EntityPlayer entityplayer) {
        entityplayer.addChatMessage(new ChatComponentTranslation("commands.lotr.opHideMap.unhide", new Object[0]));
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int i) {
        return false;
    }
}
