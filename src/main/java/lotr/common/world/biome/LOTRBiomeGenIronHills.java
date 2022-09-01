package lotr.common.world.biome;

import java.util.Random;
import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.animal.LOTREntityWildBoar;
import lotr.common.entity.npc.*;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.*;
import lotr.common.world.structure2.*;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class LOTRBiomeGenIronHills extends LOTRBiome {
    public LOTRBiomeGenIronHills(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityWildBoar.class, 50, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 8, 1, 4));
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[1];
        arrspawnListContainer[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.DWARVES, 10);
        this.npcSpawnList.newFactionList(500).add(arrspawnListContainer);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[4];
        arrspawnListContainer2[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_ORCS, 10);
        arrspawnListContainer2[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_WARGS, 2).setConquestOnly();
        arrspawnListContainer2[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_URUKS, 1).setConquestThreshold(50.0f);
        arrspawnListContainer2[3] = LOTRBiomeSpawnList.entry(LOTRSpawnList.GUNDABAD_URUKS, 1).setConquestThreshold(100.0f);
        this.npcSpawnList.newFactionList(0, 1.0f).add(arrspawnListContainer2);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer3 = new LOTRBiomeSpawnList.SpawnListContainer[4];
        arrspawnListContainer3[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_ORCS, 10);
        arrspawnListContainer3[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.MORDOR_WARGS, 2);
        arrspawnListContainer3[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.BLACK_URUKS, 2).setConquestThreshold(50.0f);
        arrspawnListContainer3[3] = LOTRBiomeSpawnList.entry(LOTRSpawnList.OLOG_HAI, 1).setConquestThreshold(200.0f);
        this.npcSpawnList.newFactionList(0, 1.0f).add(arrspawnListContainer3);
        LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer4 = new LOTRBiomeSpawnList.SpawnListContainer[3];
        arrspawnListContainer4[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_WARRIORS, 10);
        arrspawnListContainer4[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 1);
        arrspawnListContainer4[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.EASTERLING_GOLD_WARRIORS, 2).setConquestThreshold(50.0f);
        this.npcSpawnList.newFactionList(0, 1.0f).add(arrspawnListContainer4);
        this.variantChance = 0.3f;
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_MOUNTAINS);
        this.decorator.biomeGemFactor = 0.75f;
        this.decorator.addOre(new WorldGenMinable(Blocks.iron_ore, 4), 20.0f, 0, 96);
        this.decorator.addOre(new WorldGenMinable(Blocks.gold_ore, 4), 2.0f, 0, 48);
        this.decorator.addOre(new WorldGenMinable(LOTRMod.oreSilver, 4), 2.0f, 0, 48);
        this.decorator.addOre(new WorldGenMinable(LOTRMod.oreGlowstone, 4), 8.0f, 0, 48);
        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 8;
        this.decorator.doubleGrassPerChunk = 1;
        this.decorator.generateWater = false;
        this.decorator.generateLava = false;
        this.decorator.generateCobwebs = false;
        this.decorator.addTree(LOTRTreeType.SPRUCE, 500);
        this.decorator.addTree(LOTRTreeType.SPRUCE_MEGA, 200);
        this.decorator.addTree(LOTRTreeType.SPRUCE_MEGA_THIN, 50);
        this.decorator.addTree(LOTRTreeType.FIR, 400);
        this.decorator.addTree(LOTRTreeType.PINE, 400);
        this.registerMountainsFlowers();
        this.addFlower(LOTRMod.dwarfHerb, 0, 1);
        this.decorator.addRandomStructure(new LOTRWorldGenDwarvenTower(false), 300);
        this.decorator.addRandomStructure(new LOTRWorldGenDwarfSmithy(false), 150);
        
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD, LOTREventSpawner.EventChance.RARE);
        this.invasionSpawns.addInvasion(LOTRInvasions.GUNDABAD_WARG, LOTREventSpawner.EventChance.UNCOMMON);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterIronHills;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.IRON_HILLS;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.DWARVEN.getSubregion("ironHills");
    }

    @Override
    public LOTRRoadType getRoadBlock() {
        return LOTRRoadType.DWARVEN;
    }

    @Override
    public void decorate(World world, Random random, int i, int k) {
        int i1;
        int l;
        super.decorate(world, random, i, k);
        for(l = 0; l < 4; ++l) {
            i1 = i + random.nextInt(16) + 8;
            int j1 = 70 + random.nextInt(60);
            int k1 = k + random.nextInt(16) + 8;
            new LOTRWorldGenDwarfHouse(false).generate(world, random, i1, j1, k1);
        }
        for(l = 0; l < 8; ++l) {
            int k1;
            i1 = i + random.nextInt(16) + 8;
            int j1 = world.getHeightValue(i1, k1 = k + random.nextInt(16) + 8);
            if(j1 <= 80) continue;
            this.decorator.genTree(world, random, i1, j1, k1);
        }
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.25f;
    }

    @Override
    public float getTreeIncreaseChance() {
        return 0.1f;
    }
}
