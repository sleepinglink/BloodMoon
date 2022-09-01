package lotr.common.world.biome;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityCamel;
import lotr.common.entity.animal.LOTREntityDesertScorpion;
import lotr.common.entity.npc.LOTREntityBanditHarad;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.*;
import lotr.common.world.structure.LOTRWorldGenHaradObelisk;
import lotr.common.world.structure2.*;
import lotr.common.world.village.LOTRVillageGenHaradNomad;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.*;

public class LOTRBiomeGenNearHarad extends LOTRBiome {
    private static NoiseGeneratorPerlin noiseAridGrass = new NoiseGeneratorPerlin(new Random(62926025827260L), 1);
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(Blocks.stone, 0, 1, 3);
    private WorldGenerator boulderGenSandstone = new LOTRWorldGenBoulder(Blocks.sandstone, 0, 1, 3);

    public LOTRBiomeGenNearHarad(int i, boolean major) {
        super(i, major);
        this.setDisableRain();
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityCamel.class, 10, 2, 6));
        this.spawnableLOTRAmbientList.clear();
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDesertScorpion.class, 10, 4, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[2];
        arrspawnListContainer[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.NOMADS, 20).setSpawnChance(10000);
        arrspawnListContainer[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.NOMAD_WARRIORS, 15).setSpawnChance(10000);
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        this.variantChance = 0.8f;
        this.addBiomeVariant(LOTRBiomeVariant.DUNES, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.STEPPE);
        this.addBiomeVariant(LOTRBiomeVariant.HILLS);
        this.addBiomeVariant(LOTRBiomeVariant.BOULDERS_RED);
        this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.SCRUBLAND_SAND);
        this.decorator.addOre(new WorldGenMinable(Blocks.lapis_ore, 6), 1.0f, 0, 48);
        this.decorator.grassPerChunk = 0;
        this.decorator.doubleGrassPerChunk = 0;
        this.decorator.cactiPerChunk = 0;
        this.decorator.deadBushPerChunk = 0;
        this.decorator.addTree(LOTRTreeType.OAK_DEAD, 800);
        this.decorator.addTree(LOTRTreeType.OAK_DESERT, 200);
        this.registerHaradFlowers();
        this.biomeColors.setFog(16180681);
        this.decorator.addRandomStructure(new LOTRWorldGenHaradObelisk(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenHaradPyramid(false), 3000);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.NEAR_HARAD(1, 4), 2000);
        this.decorator.addRandomStructure(new LOTRWorldGenMumakSkeleton(false), 1500);
        this.decorator.addRandomStructure(new LOTRWorldGenHaradRuinedFort(false), 3000);
        this.decorator.addVillage(new LOTRVillageGenHaradNomad(this, 0.05f));
        this.clearTravellingTraders();
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.setBanditEntityClass(LOTREntityBanditHarad.class);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterNearHarad;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.HARAD_DESERT;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.NEAR_HARAD.getSubregion("desert");
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.HARAD.setRepair(0.5f);
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int preGrasses;
        int j1;
        int j12;
        int l;
        int i1;
        int k1;
        int k12;
        int i12;
        int grasses = preGrasses = this.decorator.grassPerChunk;
        double d1 = noiseAridGrass.func_151601_a(i * 0.002, k * 0.002);
        if(d1 > 0.5) {
            ++grasses;
        }
        this.decorator.grassPerChunk = grasses;
        super.decorate(world, random, i, k);
        this.decorator.grassPerChunk = preGrasses;
        if(random.nextInt(50) == 0) {
            i1 = i + random.nextInt(16) + 8;
            k12 = k + random.nextInt(16) + 8;
            j12 = world.getHeightValue(i1, k12);
            new WorldGenCactus().generate(world, random, i1, j12, k12);
        }
        if(random.nextInt(16) == 0) {
            i1 = i + random.nextInt(16) + 8;
            k12 = k + random.nextInt(16) + 8;
            j12 = world.getHeightValue(i1, k12);
            new WorldGenDeadBush(Blocks.deadbush).generate(world, random, i1, j12, k12);
        }
        if(random.nextInt(120) == 0) {
            int boulders = 1 + random.nextInt(4);
            for(l = 0; l < boulders; ++l) {
                i12 = i + random.nextInt(16) + 8;
                k1 = k + random.nextInt(16) + 8;
                j1 = world.getHeightValue(i12, k1);
                if(random.nextBoolean()) {
                    this.boulderGen.generate(world, random, i12, j1, k1);
                    continue;
                }
                this.boulderGenSandstone.generate(world, random, i12, j1, k1);
            }
        }
        if(random.nextInt(2000) == 0) {
            int trees = 1 + random.nextInt(4);
            for(l = 0; l < trees; ++l) {
                i12 = i + random.nextInt(8) + 8;
                k1 = k + random.nextInt(8) + 8;
                j1 = world.getHeightValue(i12, k1);
                this.decorator.genTree(world, random, i12, j1, k1);
            }
        }
    }

    @Override
    public float getTreeIncreaseChance() {
        return 5.0E-4f;
    }

    @Override
    public LOTRBiome.GrassBlockAndMeta getRandomGrass(Random random) {
        return new LOTRBiome.GrassBlockAndMeta(LOTRMod.aridGrass, 0);
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.05f;
    }

    public static interface ImmuneToHeat {
    }

}
