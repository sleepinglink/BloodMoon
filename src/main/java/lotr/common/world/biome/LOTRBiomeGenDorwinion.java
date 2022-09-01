package lotr.common.world.biome;

import java.util.Random;
import com.google.common.math.IntMath;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.*;
import lotr.common.entity.npc.*;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.*;
import lotr.common.world.map.*;
import lotr.common.world.spawning.*;
import lotr.common.world.structure2.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenDorwinion extends LOTRBiome {
    private WorldGenerator boulderGen = new LOTRWorldGenBoulder(LOTRMod.rock, 5, 1, 2);
    private LOTRBiomeSpawnList vineyardSpawnList = new LOTRBiomeSpawnList(this);

    public LOTRBiomeGenDorwinion(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityKineAraw.class, 6, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 2, 1, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[4];
        arrspawnListContainer[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DORWINION_MEN, 30);
        arrspawnListContainer[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DORWINION_GUARDS, 10);
        arrspawnListContainer[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DORWINION_ELVES, 5);
        arrspawnListContainer[3] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DORWINION_ELF_WARRIORS, 2);
        this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[3];
        arrspawnListContainer2[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DOL_GULDUR_ORCS, 10);
        arrspawnListContainer2[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MIRKWOOD_SPIDERS, 2).setConquestThreshold(50.0f);
        arrspawnListContainer2[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MIRK_TROLLS, 1).setConquestThreshold(200.0f);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[2];
        arrspawnListContainer3[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 10);
        arrspawnListContainer3[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_MEN, 5).setConquestThreshold(100.0f);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[4];
        arrspawnListContainer4[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 10);
        arrspawnListContainer4[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_WARGS, 2);
        arrspawnListContainer4[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 2).setConquestThreshold(50.0f);
        arrspawnListContainer4[3] = LOTRBiomeSpawnList.entry(LOTRSpawnList.OLOG_HAI, 1).setConquestThreshold(200.0f);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer4);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer5 = new LOTRBiomeSpawnList.SpawnListContainer[4];
        arrspawnListContainer5[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_WARRIORS, 10);
        arrspawnListContainer5[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 1);
        arrspawnListContainer5[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 2).setConquestThreshold(50.0f);
        arrspawnListContainer5[3] = LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLINGS, 5).setConquestThreshold(200.0f);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer5);
        this.npcSpawnList.conquestGainRate = 0.75f;
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer6 = new LOTRBiomeSpawnList.SpawnListContainer[1];
        arrspawnListContainer6[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DORWINION_VINEYARDS, 10);
        this.vineyardSpawnList.newFactionList(100).add(arrspawnListContainer6);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer7 = new LOTRBiomeSpawnList.SpawnListContainer[2];
        arrspawnListContainer7[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.RED_MOUNTAIN_WARRIOR, 10);
        arrspawnListContainer7[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.RED_MOUNTAIN_WARRIOR, 10);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer7);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer8 = new LOTRBiomeSpawnList.SpawnListContainer[2];
        arrspawnListContainer8[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.WIND_MOUNTAIN_WARRIOR, 10);
        arrspawnListContainer8[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.WIND_MOUNTAIN_AXE_THROWER, 10);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer8);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer9 = new LOTRBiomeSpawnList.SpawnListContainer[1];
        arrspawnListContainer9[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DARK_WOOD_SOLDIER, 10);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer9);
        this.variantChance = 0.3f;
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_NORMAL_OAK);
        this.addBiomeVariant(LOTRBiomeVariant.VINEYARD, 8.0f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BEECH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_BIRCH, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_OLIVE, 0.5f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_APPLE_PEAR, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_ALMOND, 0.2f);
        this.addBiomeVariant(LOTRBiomeVariant.ORCHARD_PLUM, 0.2f);
        this.decorator.setTreeCluster(8, 20);
        this.decorator.willowPerChunk = 1;
        this.decorator.flowersPerChunk = 6;
        this.decorator.doubleFlowersPerChunk = 1;
        this.decorator.grassPerChunk = 8;
        this.decorator.doubleGrassPerChunk = 2;
        this.decorator.addTree(LOTRTreeType.OAK, 200);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 100);
        this.decorator.addTree(LOTRTreeType.BIRCH, 50);
        this.decorator.addTree(LOTRTreeType.BIRCH_TALL, 50);
        this.decorator.addTree(LOTRTreeType.BIRCH_LARGE, 50);
        this.decorator.addTree(LOTRTreeType.BEECH, 20);
        this.decorator.addTree(LOTRTreeType.BEECH_LARGE, 20);
        this.decorator.addTree(LOTRTreeType.CYPRESS, 500);
        this.decorator.addTree(LOTRTreeType.CYPRESS_LARGE, 50);
        this.decorator.addTree(LOTRTreeType.OAK_SHRUB, 800);
        this.decorator.addTree(LOTRTreeType.APPLE, 5);
        this.decorator.addTree(LOTRTreeType.PEAR, 5);
        this.decorator.addTree(LOTRTreeType.OLIVE, 20);
        this.decorator.addTree(LOTRTreeType.OLIVE_LARGE, 20);
        this.decorator.addTree(LOTRTreeType.ALMOND, 10);
        this.decorator.addTree(LOTRTreeType.PLUM, 10);
        this.registerRhunPlainsFlowers();
        this.biomeColors.setGrass(10538541);
        this.decorator.addRandomStructure(new LOTRWorldGenStoneRuin.DORWINION(1, 4), 1000);
        this.decorator.addRandomStructure(new LOTRWorldGenDorwinionGarden(false), 300);
        this.decorator.addRandomStructure(new LOTRWorldGenDorwinionCamp(false), 400);
        this.decorator.addRandomStructure(new LOTRWorldGenDorwinionHouse(false), 200);
        this.decorator.addRandomStructure(new LOTRWorldGenDorwinionBrewery(false), 500);
        this.decorator.addRandomStructure(new LOTRWorldGenDorwinionElfHouse(false), 400);
        this.decorator.addRandomStructure(new LOTRWorldGenDorwinionBath(false), 600);
        
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.DOL_GULDUR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.MORDOR, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.RED_MOUNTAINS, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.WIND_MOUNTAINS, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.DARK_WOOD, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRBiomeSpawnList getNPCSpawnList(World world, Random random, int i, int j, int k, LOTRBiomeVariant variant) {
        if(variant == LOTRBiomeVariant.VINEYARD) {
            return this.vineyardSpawnList;
        }
        return super.getNPCSpawnList(world, random, i, j, k, variant);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterDorwinion;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.DORWINION;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.DORWINION.getSubregion("dorwinion");
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.DORWINION;
    }

    @Override
    public boolean hasDomesticAnimals() {
        return true;
    }

    @Override
    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
        boolean vineyard;
        super.generateBiomeTerrain(world, random, blocks, meta, i, k, stoneNoise, height, variant);
        int chunkX = i & 0xF;
        int chunkZ = k & 0xF;
        int xzIndex = chunkX * 16 + chunkZ;
        int ySize = blocks.length / 256;
        boolean bl = vineyard = variant == LOTRBiomeVariant.VINEYARD;
        if(vineyard && !LOTRRoads.isRoadAt(i, k)) {
            for(int j = 128; j >= 0; --j) {
                int index = xzIndex * ySize + j;
                Block above = blocks[index + 1];
                Block block = blocks[index];
                if(block == null || !block.isOpaqueCube() || above != null && above.getMaterial() != Material.air) continue;
                int i1 = IntMath.mod(i, 6);
                int i2 = IntMath.mod(i, 24);
                int k1 = IntMath.mod(k, 32);
                int k2 = IntMath.mod(k, 64);
                if((i1 == 0 || i1 == 5) && k1 != 0) {
                    double d;
                    blocks[index] = Blocks.farmland;
                    meta[index] = 0;
                    int h = 2;
                    if(biomeTerrainNoise.func_151601_a(i, k) > 0.0) {
                        ++h;
                    }
                    boolean red = biomeTerrainNoise.func_151601_a(i * (d = 0.01), k * d) > 0.0;
                    Block vineBlock = red ? LOTRMod.grapevineRed : LOTRMod.grapevineWhite;
                    for(int j1 = 1; j1 <= h; ++j1) {
                        blocks[index + j1] = vineBlock;
                        meta[index + j1] = 7;
                    }
                    break;
                }
                if(i1 >= 2 && i1 <= 3) {
                    blocks[index] = LOTRMod.dirtPath;
                    meta[index] = 0;
                    if(i1 != i2 || (i1 != 2 || k2 != 16) && (i1 != 3 || k2 != 48)) break;
                    int h = 3;
                    for(int j1 = 1; j1 <= h; ++j1) {
                        if(j1 == h) {
                            blocks[index + j1] = Blocks.torch;
                            meta[index + j1] = 5;
                            continue;
                        }
                        blocks[index + j1] = LOTRMod.fence2;
                        meta[index + j1] = 10;
                    }
                    break;
                }
                blocks[index] = this.topBlock;
                meta[index] = (byte) this.topBlockMeta;
                break;
            }
        }
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        if(random.nextInt(50) == 0) {
            for(int l = 0; l < 3; ++l) {
                int i1 = i + random.nextInt(16) + 8;
                int k1 = k + random.nextInt(16) + 8;
                this.boulderGen.generate(world, random, i1, world.getHeightValue(i1, k1), k1);
            }
        }
    }

    @Override
    public WorldGenerator getRandomWorldGenForDoubleFlower(Random random) {
        if(random.nextInt(3) == 0) {
            LOTRWorldGenDoubleFlower doubleFlowerGen = new LOTRWorldGenDoubleFlower();
            doubleFlowerGen.setFlowerType(0);
            return doubleFlowerGen;
        }
        return super.getRandomWorldGenForDoubleFlower(random);
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.1f;
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.25f;
    }

    @Override
    public int spawnCountMultiplier() {
        return 3;
    }
}
