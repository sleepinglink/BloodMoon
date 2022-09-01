package lotr.common.world.biome;

import java.util.Random;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.entity.npc.LOTREntityNomadMerchant;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenBoulder;
import lotr.common.world.feature.LOTRWorldGenDoubleFlower;
import lotr.common.world.feature.LOTRWorldGenSand;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTREventSpawner;
import lotr.common.world.spawning.LOTRInvasions;
import lotr.common.world.spawning.LOTRSpawnList;
import lotr.common.world.structure.LOTRWorldGenHaradObelisk;
import lotr.common.world.structure.LOTRWorldGenHighElvenHall;
import lotr.common.world.structure.LOTRWorldGenRuinedGondorTower;
import lotr.common.world.structure2.*;
import lotr.common.world.village.LOTRVillageGenHaradNomad;
import lotr.common.world.village.LOTRVillageGenHarnedor;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenKhand extends LOTRBiome {
    protected static NoiseGeneratorPerlin noiseDirt = new NoiseGeneratorPerlin(new Random(35952060662L), 1);
    protected static NoiseGeneratorPerlin noiseSand = new NoiseGeneratorPerlin(new Random(5925366672L), 1);


    public LOTRBiomeGenKhand(int i, boolean major) {
    	 super(i, major);
         this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 20, 2, 6));
         this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 5, 1, 4));
         LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[3];
         arrspawnListContainer[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.KHAND, 2);
         arrspawnListContainer[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.KHAND_SOLDIER, 3);
         arrspawnListContainer[2] = LOTRBiomeSpawnList.entry(LOTRSpawnList.KHAND_ARCHER, 3);
         this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
         LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer2 = new LOTRBiomeSpawnList.SpawnListContainer[2];
         arrspawnListContainer2[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.GONDOR_SOLDIERS, 10);
         arrspawnListContainer2[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.RANGERS_ITHILIEN, 3);
         this.npcSpawnList.newFactionList(0).add(arrspawnListContainer2);
         this.variantChance = 0.3f;
         this.addBiomeVariant(LOTRBiomeVariant.FOREST);
         this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
         this.addBiomeVariant(LOTRBiomeVariant.STEPPE);
         this.addBiomeVariant(LOTRBiomeVariant.STEPPE_BARREN);
         this.addBiomeVariant(LOTRBiomeVariant.HILLS);
         this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
         this.addBiomeVariant(LOTRBiomeVariant.SHRUBLAND_OAK);
         this.addBiomeVariant(LOTRBiomeVariant.SCRUBLAND);
         this.addBiomeVariant(LOTRBiomeVariant.HILLS_SCRUBLAND);
         this.addBiomeVariant(LOTRBiomeVariant.WASTELAND);
         this.decorator.clayPerChunk = 1;
         this.decorator.grassPerChunk = 10;
         this.decorator.doubleGrassPerChunk = 12;
         this.decorator.flowersPerChunk = 3;
         this.decorator.doubleFlowersPerChunk = 1;
         this.decorator.melonPerChunk = 0.01f;
         this.decorator.addTree(LOTRTreeType.OAK, 100);
         this.decorator.addTree(LOTRTreeType.CEDAR, 250);
         this.decorator.addTree(LOTRTreeType.LEMON, 5);
         this.decorator.addTree(LOTRTreeType.ORANGE, 5);
         this.decorator.addTree(LOTRTreeType.LIME, 5);
         this.decorator.addTree(LOTRTreeType.OLIVE, 5);
         this.decorator.addTree(LOTRTreeType.OLIVE_LARGE, 5);
         this.decorator.addTree(LOTRTreeType.ALMOND, 5);
         this.decorator.addTree(LOTRTreeType.PLUM, 5);
         this.registerRhunPlainsFlowers();
         this.decorator.addRandomStructure(new LOTRWorldGenKhandHouse(false), 300);
         this.decorator.addRandomStructure(new LOTRWorldGenKhandHorse(false), 400);
         this.decorator.addRandomStructure(new LOTRWorldGenKhandForge(false), 300);
     }

     @Override
     public LOTRAchievement getBiomeAchievement() {
         return LOTRAchievement.enterKhand;
     }

     @Override
     public LOTRWaypoint.Region getBiomeWaypoints() {
         return LOTRWaypoint.Region.KHAND;
     }

     @Override
     public LOTRMusicRegion.Sub getBiomeMusic() {
         return LOTRMusicRegion.RHUN.getSubregion("rhun");
     }

    
     
     @Override
     public WorldGenerator getRandomWorldGenForDoubleFlower(Random random) {
         if(random.nextInt(4) == 0) {
             LOTRWorldGenDoubleFlower doubleFlowerGen = new LOTRWorldGenDoubleFlower();
             doubleFlowerGen.setFlowerType(0);
             return doubleFlowerGen;
         }
         return super.getRandomWorldGenForDoubleFlower(random);
     }

     @Override
     public float getTreeIncreaseChance() {
         return 0.03f;
     }

     @Override
     public float getChanceToSpawnAnimals() {
         return 0.25f;
     }
 }