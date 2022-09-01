package lotr.common.world.spawning;

import java.util.*;
import cpw.mods.fml.common.eventhandler.Event;
import lotr.common.*;
import lotr.common.entity.LOTREntities;
import lotr.common.entity.LOTREntityInvasionSpawner;
import lotr.common.entity.npc.LOTREntityBandit;
import lotr.common.world.LOTRWorldProvider;
import lotr.common.world.biome.LOTRBiome;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.ForgeEventFactory;

public class LOTREventSpawner {
    private static Set<ChunkCoordIntPair> eligibleSpawnChunks = new HashSet<ChunkCoordIntPair>();
    private static final int playerRange = 32;
    private static final int expectedChunks = 16;
    public static List<LOTRTravellingTraderSpawner> travellingTraders = new ArrayList<LOTRTravellingTraderSpawner>();
    private static Set<Class> traderClasses = new HashSet<Class>();

    
        

    public static void performSpawning(World world) {
       
        if(world.getTotalWorldTime() % 20L == 0L) {
            LOTRSpawnerNPCs.getSpawnableChunksWithPlayerInRange(world, eligibleSpawnChunks, 32);
            List<ChunkCoordIntPair> shuffled = LOTRSpawnerNPCs.shuffle(eligibleSpawnChunks);
            if(LOTRConfig.enableBandits && world.difficultySetting != EnumDifficulty.PEACEFUL) {
                LOTREventSpawner.spawnBandits(world, shuffled);
            }
            if(LOTRConfig.enableInvasions && world.difficultySetting != EnumDifficulty.PEACEFUL) {
                LOTREventSpawner.spawnInvasions(world, shuffled);
            }
        }
        LOTRGollumSpawner.performSpawning(world);
        LOTRGreyWandererTracker.performSpawning(world);
    }

    private static void spawnInvasions(World world, List<ChunkCoordIntPair> spawnChunks) {
        Random rand = world.rand;
        block0: for(ChunkCoordIntPair chunkCoords : spawnChunks) {
            BiomeGenBase biome;
            int k;
            int i;
            ChunkPosition chunkposition = LOTRSpawnerNPCs.getRandomSpawningPointInChunk(world, chunkCoords);
            if(chunkposition == null || !((biome = world.getBiomeGenForCoords(i = chunkposition.chunkPosX, k = chunkposition.chunkPosZ)) instanceof LOTRBiome)) continue;
            LOTRBiomeInvasionSpawns invasionSpawns = ((LOTRBiome) biome).invasionSpawns;
            for(EventChance invChance : EventChance.values()) {
                List nearbyPlayers;
                AxisAlignedBB playerCheckBox;
                int range;
                List<LOTRInvasions> invList = invasionSpawns.getInvasionsForChance(invChance);
                if(invList.isEmpty()) continue;
                final LOTRInvasions invasionType = invList.get(rand.nextInt(invList.size()));
                double chance = invChance.chancesPerSecondPerChunk[16];
                if(!world.isDaytime() && LOTRWorldProvider.isLunarEclipse()) {
                    chance *= 5.0;
                }
                if(!(rand.nextDouble() < chance) || (nearbyPlayers = world.selectEntitiesWithinAABB(EntityPlayer.class, playerCheckBox = AxisAlignedBB.getBoundingBox(i - (range = 48), 0.0, k - range, i + range, world.getHeight(), k + range), new IEntitySelector() {

                    @Override
                    public boolean isEntityApplicable(Entity entity) {
                        EntityPlayer entityplayer;
                        if(entity instanceof EntityPlayer && (entityplayer = (EntityPlayer) entity).isEntityAlive() && !entityplayer.capabilities.isCreativeMode) {
                            return LOTRLevelData.getData(entityplayer).getAlignment(invasionType.invasionFaction) < 0.0f;
                        }
                        return false;
                    }
                })).isEmpty()) continue;
                for(int attempts = 0; attempts < 16; ++attempts) {
                    Block block;
                    int k1;
                    int i1 = i + MathHelper.getRandomIntegerInRange(rand, -32, 32);
                    int j1 = world.getHeightValue(i1, k1 = k + MathHelper.getRandomIntegerInRange(rand, -32, 32));
                    if(j1 <= 60 || (block = world.getBlock(i1, j1 - 1, k1)) != biome.topBlock && block != biome.fillerBlock || world.getBlock(i1, j1, k1).isNormalCube() || world.getBlock(i1, j1 + 1, k1).isNormalCube()) continue;
                    LOTREntityInvasionSpawner invasion = new LOTREntityInvasionSpawner(world);
                    invasion.setInvasionType(invasionType);
                    invasion.setLocationAndAngles(i1 + 0.5, j1 += 3 + rand.nextInt(3), k1 + 0.5, 0.0f, 0.0f);
                    if(!invasion.canInvasionSpawnHere()) continue;
                    world.spawnEntityInWorld(invasion);
                    invasion.selectAppropriateBonusFactions();
                    invasion.announceInvasionToEnemies();
                    continue block0;
                }
            }
        }
    }

    private static void spawnBandits(World world, List<ChunkCoordIntPair> spawnChunks) {
        Random rand = world.rand;
        block0: for(ChunkCoordIntPair chunkCoords : spawnChunks) {
            AxisAlignedBB playerCheckBox;
            BiomeGenBase biome;
            int k;
            int i;
            int range;
            List nearbyPlayers;
            ChunkPosition chunkposition = LOTRSpawnerNPCs.getRandomSpawningPointInChunk(world, chunkCoords);
            if(chunkposition == null || !((biome = world.getBiomeGenForCoords(i = chunkposition.chunkPosX, k = chunkposition.chunkPosZ)) instanceof LOTRBiome)) continue;
            LOTRBiome lotrbiome = (LOTRBiome) biome;
            Class<? extends LOTREntityBandit> banditClass = lotrbiome.getBanditEntityClass();
            double chance = lotrbiome.getBanditChance().chancesPerSecondPerChunk[16];
            if(!(chance > 0.0) || !(world.rand.nextDouble() < chance) || (nearbyPlayers = world.selectEntitiesWithinAABB(EntityPlayer.class, playerCheckBox = AxisAlignedBB.getBoundingBox(i - (range = 48), 0.0, k - range, i + range, world.getHeight(), k + range), LOTRMod.selectNonCreativePlayers())).isEmpty()) continue;
            int banditsSpawned = 0;
            int maxBandits = MathHelper.getRandomIntegerInRange(world.rand, 1, 4);
            for(int attempts = 0; attempts < 32; ++attempts) {
                Block block;
                LOTREntityBandit bandit;
                int k1;
                String entityName;
                int i1 = i + MathHelper.getRandomIntegerInRange(rand, -32, 32);
                int j1 = world.getHeightValue(i1, k1 = k + MathHelper.getRandomIntegerInRange(rand, -32, 32));
                if(j1 <= 60 || (block = world.getBlock(i1, j1 - 1, k1)) != biome.topBlock && block != biome.fillerBlock || world.getBlock(i1, j1, k1).isNormalCube() || world.getBlock(i1, j1 + 1, k1).isNormalCube() || (bandit = (LOTREntityBandit) EntityList.createEntityByName(entityName = LOTREntities.getStringFromClass(banditClass), world)) == null) continue;
                bandit.setLocationAndAngles(i1 + 0.5, j1, k1 + 0.5, world.rand.nextFloat() * 360.0f, 0.0f);
                Event.Result canSpawn = ForgeEventFactory.canEntitySpawn(bandit, world, (float) bandit.posX, (float) bandit.posY, (float) bandit.posZ);
                if(canSpawn != Event.Result.ALLOW && (canSpawn != Event.Result.DEFAULT || !bandit.getCanSpawnHere())) continue;
                bandit.onSpawnWithEgg(null);
                world.spawnEntityInWorld(bandit);
                bandit.isNPCPersistent = false;
                if(++banditsSpawned >= maxBandits) continue block0;
            }
        }
    }

    public static enum EventChance {
        NEVER(0.0f, 0),
        RARE(0.1f, 3600),
        UNCOMMON(0.3f, 3600),
        COMMON(0.9f, 3600),
        BANDIT_RARE(0.1f, 3600),
        BANDIT_UNCOMMON(0.3f, 3600),
        BANDIT_COMMON(0.8f, 3600);

        public final double chancePerSecond;
        public final double[] chancesPerSecondPerChunk;

        private EventChance(float prob, int s) {
            this.chancePerSecond = EventChance.getChance(prob, s);
            this.chancesPerSecondPerChunk = new double[64];
            for(int i = 0; i < this.chancesPerSecondPerChunk.length; ++i) {
                this.chancesPerSecondPerChunk[i] = EventChance.getChance(this.chancePerSecond, i);
            }
        }

        public static double getChance(double prob, int trials) {
            if(prob == 0.0 || trials == 0) {
                return 0.0;
            }
            double d = prob;
            d = 1.0 - d;
            d = Math.pow(d, 1.0 / trials);
            d = 1.0 - d;
            return d;
        }
    }

}
