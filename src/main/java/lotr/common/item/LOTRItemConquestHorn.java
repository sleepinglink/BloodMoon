package lotr.common.item;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.*;
import lotr.common.entity.LOTREntityInvasionSpawner;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.fac.LOTRAlignmentValues;
import lotr.common.fac.LOTRFaction;
import lotr.common.world.spawning.LOTRInvasions;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class LOTRItemConquestHorn extends Item {
    @SideOnly(value = Side.CLIENT)
    private IIcon baseIcon;
    @SideOnly(value = Side.CLIENT)
    private IIcon overlayIcon;

    public LOTRItemConquestHorn() {
        this.setMaxStackSize(1);
        this.setCreativeTab(LOTRCreativeTabs.Pieces);
    }

    public static LOTRInvasions getInvasionType(ItemStack itemstack) {
        String s;
        LOTRInvasions invasionType = null;
        if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("InvasionType")) {
            s = itemstack.getTagCompound().getString("InvasionType");
            invasionType = LOTRInvasions.forName(s);
        }
        if(invasionType == null && itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("HornFaction")) {
            s = itemstack.getTagCompound().getString("HornFaction");
            invasionType = LOTRInvasions.forName(s);
        }
        if(invasionType == null) {
            invasionType = LOTRInvasions.HOBBIT;
        }
        return invasionType;
    }

    public static void setInvasionType(ItemStack itemstack, LOTRInvasions type) {
        if(itemstack.getTagCompound() == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setString("InvasionType", type.codeName());
    }

    public static ItemStack createHorn(LOTRInvasions type) {
        ItemStack itemstack = new ItemStack(LOTRMod.conquestHorn);
        LOTRItemConquestHorn.setInvasionType(itemstack, type);
        return itemstack;
    }

    private boolean canUseHorn(ItemStack itemstack, World world, EntityPlayer entityplayer, boolean sendMessage) {
        if(LOTRDimension.getCurrentDimension(world) == LOTRDimension.SHADOW) {
            if(sendMessage && !world.isRemote) {
                entityplayer.addChatMessage(new ChatComponentTranslation("chat.lotr.conquestHornProtected", new Object[0]));
            }
            return false;
        }
        LOTRInvasions invasionType = LOTRItemConquestHorn.getInvasionType(itemstack);
        LOTRFaction invasionFaction = invasionType.invasionFaction;
        float alignmentRequired = 1500.0f;
        if(LOTRLevelData.getData(entityplayer).getAlignment(invasionFaction) >= alignmentRequired) {
            boolean blocked = false;
            if(LOTRBannerProtection.isProtectedByBanner(world, entityplayer, LOTRBannerProtection.forFaction(invasionFaction), false)) {
                blocked = true;
            }
            if(LOTREntityNPCRespawner.isSpawnBlocked(entityplayer, invasionFaction)) {
                blocked = true;
            }
            if(blocked) {
                if(sendMessage && !world.isRemote) {
                    entityplayer.addChatMessage(new ChatComponentTranslation("chat.lotr.conquestHornProtected", invasionFaction.factionName()));
                }
                return false;
            }
            return true;
        }
        if(sendMessage && !world.isRemote) {
            LOTRAlignmentValues.notifyAlignmentNotHighEnough(entityplayer, alignmentRequired, invasionType.invasionFaction);
        }
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        boolean canUse = this.canUseHorn(itemstack, world, entityplayer, false);
        entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        LOTRInvasions invasionType = LOTRItemConquestHorn.getInvasionType(itemstack);
        if(this.canUseHorn(itemstack, world, entityplayer, true)) {
            if(!world.isRemote) {
                LOTREntityInvasionSpawner invasion = new LOTREntityInvasionSpawner(world);
                invasion.setInvasionType(invasionType);
                invasion.spawnsPersistent = true;
                invasion.setLocationAndAngles(entityplayer.posX, entityplayer.posY + 3.0, entityplayer.posZ, 0.0f, 0.0f);
                world.spawnEntityInWorld(invasion);
                invasion.announceInvasionToEnemies();
                invasion.announceInvasionTo(entityplayer);
            }
            if(!entityplayer.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }
            return itemstack;
        }
        return itemstack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 40;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.bow;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconregister) {
        this.baseIcon = iconregister.registerIcon(this.getIconString() + "_base");
        this.overlayIcon = iconregister.registerIcon(this.getIconString() + "_overlay");
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public IIcon getIconFromDamageForRenderPass(int i, int pass) {
        return pass > 0 ? this.overlayIcon : this.baseIcon;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemstack, int pass) {
        if(pass == 0) {
            LOTRFaction faction = LOTRItemConquestHorn.getInvasionType(itemstack).invasionFaction;
            return faction.getFactionColor();
        }
        return 16777215;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemstack) {
        LOTRInvasions type = LOTRItemConquestHorn.getInvasionType(itemstack);
        if(type != null) {
            return StatCollector.translateToLocal(type.codeNameHorn());
        }
        return super.getItemStackDisplayName(itemstack);
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
        LOTRInvasions type = LOTRItemConquestHorn.getInvasionType(itemstack);
        list.add(type.invasionName());
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for(LOTRInvasions type : LOTRInvasions.values()) {
            ItemStack itemstack = new ItemStack(item);
            LOTRItemConquestHorn.setInvasionType(itemstack, type);
            list.add(itemstack);
        }
    }
}
