package lotr.common.world.biome;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenMountainsideBush;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenBlueMountainsStronghold;
import lotr.common.world.structure2.LOTRWorldGenBlueMountainsHouse;
import lotr.common.world.structure2.LOTRWorldGenBlueMountainsSmithy;
import lotr.common.world.structure2.LOTRWorldGenWindMountainsHouse;
import lotr.common.world.structure2.LOTRWorldGenWindMountainsSmithy;
import lotr.common.world.structure2.LOTRWorldGenWindMountainsStronghold;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LOTRBiomeGenWindMountains extends LOTRBiome {
    public LOTRBiomeGenWindMountains(int i, boolean major) {
        super(i, major);
        
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[3];
        arrspawnListContainer[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.WIND_MOUNTAIN, 50);
        arrspawnListContainer[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.WIND_MOUNTAIN_WARRIOR, 20);
        arrspawnListContainer[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.WIND_MOUNTAIN_AXE_THROWER, 20);
        this.npcSpawnList.newFactionList(100, 0.0f).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[2];
        arrspawnListContainer2[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.WIND_MOUNTAIN_WARRIOR, 10);
        arrspawnListContainer2[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.WIND_MOUNTAIN_AXE_THROWER, 10);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[2];
        arrspawnListContainer3[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DALE_SOLDIERS, 10);
        arrspawnListContainer3[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DALE_MEN, 5).setConquestThreshold(200.0f);
        this.npcSpawnList.newFactionList(0).add(arrspawnListContainer3);
        this.npcSpawnList.conquestGainRate = 0.2f;
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_MOUNTAINS);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_LARCH, 0.3f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_PINE, 0.3f);
        this.addBiomeVariant(LOTRBiomeVariant.FOREST_MAPLE, 0.3f);
        this.decorator.biomeGemFactor = 1.0f;
        this.variantChance = 0.2f;
        this.decorator.treesPerChunk = 1;
        this.decorator.willowPerChunk = 1;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 4;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.generateWater = false;
        this.decorator.generateLava = false;
        this.decorator.generateCobwebs = false;
        this.decorator.addTree(LOTRTreeType.SPRUCE, 400);
        this.decorator.addTree(LOTRTreeType.SPRUCE_THIN, 400);
        this.decorator.addTree(LOTRTreeType.SPRUCE_MEGA, 50);
        this.decorator.addTree(LOTRTreeType.SPRUCE_MEGA_THIN, 10);
        this.decorator.addTree(LOTRTreeType.LARCH, 500);
        this.decorator.addTree(LOTRTreeType.FIR, 500);
        this.decorator.addTree(LOTRTreeType.PINE, 500);
        this.decorator.addTree(LOTRTreeType.MAPLE, 300);
        this.registerMountainsFlowers();
        this.biomeColors.setSky(11653858);
        this.decorator.addRandomStructure(new LOTRWorldGenWindMountainsStronghold(false), 400);
        this.decorator.addRandomStructure(new LOTRWorldGenWindMountainsSmithy(false), 150);
        this.setBanditChance(LOTREventSpawner.EventChance.NEVER);
        this.invasionSpawns.addInvasion(LOTRInvasions.DALE, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.DWARF, LOTREventSpawner.EventChance.RARE);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterMountainsWind;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.WIND_MOUNTAINS;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.RHUN.getSubregion("windMountains");
    }

    @Override
    public boolean getEnableRiver() {
        return false;
    }

    @Override
    protected void generateMountainTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, int xzIndex, int ySize, int height, int rockDepth, LOTRBiomeVariant variant) {
        int snowHeight = 150 - rockDepth;
        int stoneHeight = snowHeight - 40;
        for(int j = ySize - 1; j >= stoneHeight; --j) {
            int index = xzIndex * ySize + j;
            Block block = blocks[index];
            if(j >= snowHeight && block == this.topBlock) {
                blocks[index] = Blocks.snow;
                meta[index] = 0;
            }
            else if(block == this.topBlock || block == this.fillerBlock) {
                blocks[index] = Blocks.stone;
                meta[index] = 0;
            }
            block = blocks[index];
            if(block != Blocks.stone) continue;
            if(random.nextInt(6) == 0) {
                int h = 1 + random.nextInt(6);
                for(int j1 = j; j1 > j - h && j1 > 0; --j1) {
                    int indexH = xzIndex * ySize + j1;
                    if(blocks[indexH] != Blocks.stone) continue;
                    blocks[indexH] = Blocks.stained_hardened_clay;
                    meta[indexH] = 9;
                }
                continue;
            }
            if(random.nextInt(16) != 0) continue;
            blocks[index] = Blocks.clay;
            meta[index] = 0;
        }
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        super.decorate(world, random, i, k);
        for(int l = 0; l < 4; ++l) {
            int i1 = i + random.nextInt(16) + 8;
            int j1 = 70 + random.nextInt(80);
            int k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenWindMountainsHouse(false).generate(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.0f;
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.2f;
    }
}
