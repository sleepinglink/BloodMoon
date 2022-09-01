package lotr.common;

import java.util.*;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class LOTRGreyWandererTracker {
    private static Map<UUID, Integer> activeGreyWanderers = new HashMap<UUID, Integer>();
    private static final int greyWandererCooldown_MAX = 3600;
    private static final int spawnInterval = 2400;
    private static int spawnCooldown;

    public static void save(NBTTagCompound levelData) {
        NBTTagList greyWandererTags = new NBTTagList();
        for(Map.Entry<UUID, Integer> e : activeGreyWanderers.entrySet()) {
            UUID id = e.getKey();
            int cd = e.getValue();
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("ID", id.toString());
            nbt.setInteger("CD", cd);
            greyWandererTags.appendTag(nbt);
        }
        levelData.setTag("GreyWanderers", greyWandererTags);
        levelData.setInteger("GWSpawnTick", spawnCooldown);
    }

    public static void load(NBTTagCompound levelData) {
        activeGreyWanderers.clear();
        NBTTagList greyWandererTags = levelData.getTagList("GreyWanderers", 10);
        for(int i = 0; i < greyWandererTags.tagCount(); ++i) {
            NBTTagCompound nbt = greyWandererTags.getCompoundTagAt(i);
            try {
                UUID id = UUID.fromString(nbt.getString("ID"));
                int cd = nbt.getInteger("CD");
                activeGreyWanderers.put(id, cd);
                continue;
            }
            catch(Exception e) {
                FMLLog.severe("Error loading LOTR data: invalid Grey Wanderer", new Object[0]);
                e.printStackTrace();
            }
        }
        spawnCooldown = levelData.hasKey("GWSpawnTick") ? levelData.getInteger("GWSpawnTick") : 2400;
    }

    private static void markDirty() {
        LOTRLevelData.markDirty();
    }

    public static boolean isWandererActive(UUID id) {
        return activeGreyWanderers.containsKey(id) && activeGreyWanderers.get(id) > 0;
    }

    public static void addNewWanderer(UUID id) {
        activeGreyWanderers.put(id, 3600);
        LOTRGreyWandererTracker.markDirty();
    }

    public static void setWandererActive(UUID id) {
        if(activeGreyWanderers.containsKey(id)) {
            activeGreyWanderers.put(id, 3600);
            LOTRGreyWandererTracker.markDirty();
        }
    }

    public static void updateCooldowns() {
        HashSet<UUID> removes = new HashSet<UUID>();
        for(UUID id : activeGreyWanderers.keySet()) {
            int cd = activeGreyWanderers.get(id);
            activeGreyWanderers.put(id, --cd);
            if(cd > 0) continue;
            removes.add(id);
        }
        if(!removes.isEmpty()) {
            for(UUID id : removes) {
                activeGreyWanderers.remove(id);
            }
            LOTRGreyWandererTracker.markDirty();
        }
    }

    public static void performSpawning(World world) {
        if(!activeGreyWanderers.isEmpty()) {
            return;
        }
        if(!world.playerEntities.isEmpty() && --spawnCooldown <= 0) {
            spawnCooldown = 2400;
            ArrayList players = new ArrayList(world.playerEntities);
            Collections.shuffle(players);
            Random rand = world.rand;
            boolean spawned = false;
            for(Object obj : players) {
                EntityPlayer entityplayer = (EntityPlayer) obj;
                if(LOTRLevelData.getData(entityplayer).hasAnyGWQuest()) continue;
                for(int attempts = 0; attempts < 32; ++attempts) {
                    int k;
                    float angle = rand.nextFloat() * 3.1415927f * 2.0f;
                    int r = MathHelper.getRandomIntegerInRange(rand, 4, 16);
                    int i = MathHelper.floor_double(entityplayer.posX + r * MathHelper.cos(angle));
                    int j = world.getHeightValue(i, k = MathHelper.floor_double(entityplayer.posZ + r * MathHelper.sin(angle)));
                 
                }
            }
        }
    }
}
