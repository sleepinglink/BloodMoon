package lotr.common.item;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lotr.common.*;
import lotr.common.entity.npc.LOTREntityNPC;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class LOTRItemCommandHorn extends Item implements LOTRSquadrons.SquadronItem {
    public LOTRItemCommandHorn() {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        this.setCreativeTab(LOTRCreativeTabs.Pieces);
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if(!world.isRemote) {
            List entities = world.loadedEntityList;
            for(Object entitie : entities) {
                if(!(entitie instanceof LOTREntityNPC)) continue;
                LOTREntityNPC npc = (LOTREntityNPC) entitie;
                if(!npc.hiredNPCInfo.isActive || npc.hiredNPCInfo.getHiringPlayer() != entityplayer || !LOTRSquadrons.areSquadronsCompatible(npc, itemstack)) continue;
                if(itemstack.getItemDamage() == 1 && npc.hiredNPCInfo.getObeyHornHaltReady()) {
                    npc.hiredNPCInfo.halt();
                    continue;
                }
                if(itemstack.getItemDamage() == 2 && npc.hiredNPCInfo.getObeyHornHaltReady()) {
                    npc.hiredNPCInfo.ready();
                    continue;
                }
                if(itemstack.getItemDamage() != 3 || !npc.hiredNPCInfo.getObeyHornSummon()) continue;
                npc.hiredNPCInfo.tryTeleportToHiringPlayer(true);
            }
        }
        if(itemstack.getItemDamage() == 1) {
            itemstack.setItemDamage(2);
        }
        else if(itemstack.getItemDamage() == 2) {
            itemstack.setItemDamage(1);
        }
        world.playSoundAtEntity(entityplayer, "lotr:item.horn", 4.0f, 1.0f);
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

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if(itemstack.getItemDamage() == 0) {
            entityplayer.openGui(LOTRMod.instance, 9, world, 0, 0, 0);
        }
        else {
            entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        String s = "";
        if(itemstack.getItemDamage() == 1) {
            s = ".halt";
        }
        else if(itemstack.getItemDamage() == 2) {
            s = ".ready";
        }
        else if(itemstack.getItemDamage() == 3) {
            s = ".summon";
        }
        return super.getUnlocalizedName(itemstack) + s;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for(int j = 0; j <= 3; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
}
