package lotr.common.world.biome;

import java.util.Random;

import lotr.common.LOTRAchievement;
import lotr.common.LOTRMod;
import lotr.common.entity.animal.LOTREntityBear;
import lotr.common.entity.animal.LOTREntityHorse;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.feature.LOTRWorldGenDoubleFlower;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.LOTRBiomeSpawnList;
import lotr.common.world.spawning.LOTRSpawnList;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRBiomeGenSemidesertKhand extends LOTRBiomeGenNearHarad{
    private static NoiseGeneratorPerlin noiseDirt = new NoiseGeneratorPerlin(new Random(3869098386927266L), 1);
    private static NoiseGeneratorPerlin noiseSand = new NoiseGeneratorPerlin(new Random(92726978206783582L), 1);
	    public LOTRBiomeGenSemidesertKhand(int i, boolean major) {
	    	 super(i, major);
	         this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityHorse.class, 20, 2, 6));
	         this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityBear.class, 5, 1, 4));
	         LOTRBiomeSpawnList.SpawnListContainer[] arrspawnListContainer = new LOTRBiomeSpawnList.SpawnListContainer[2];
	         arrspawnListContainer[0] = LOTRBiomeSpawnList.entry(LOTRSpawnList.KHAND_SOLDIER, 10);
	         arrspawnListContainer[1] = LOTRBiomeSpawnList.entry(LOTRSpawnList.KHAND_ARCHER, 10);
	         this.npcSpawnList.newFactionList(100).add(arrspawnListContainer);
	         this.variantChance = 0.3f;
	         this.clearBiomeVariants();
	         this.addBiomeVariant(LOTRBiomeVariant.FOREST_LIGHT);
	         this.addBiomeVariant(LOTRBiomeVariant.STEPPE);
	         this.addBiomeVariant(LOTRBiomeVariant.STEPPE_BARREN);
	         this.addBiomeVariant(LOTRBiomeVariant.HILLS);
	         this.addBiomeVariant(LOTRBiomeVariant.HILLS_FOREST);
	         this.addBiomeVariant(LOTRBiomeVariant.DEADFOREST_OAK);
	         this.addBiomeVariant(LOTRBiomeVariant.SHRUBLAND_OAK);
	         this.decorator.clearTrees();
	         this.decorator.addTree(LOTRTreeType.OAK_DEAD, 500);
	         this.decorator.addTree(LOTRTreeType.OAK_DESERT, 500);
	         this.topBlock = Blocks.sand;
	         this.topBlockMeta = 1;
	         this.fillerBlock = Blocks.sand;
	         this.fillerBlockMeta = 1;
	         this.decorator.grassPerChunk = 5;
	         this.decorator.doubleGrassPerChunk = 1;
	         this.decorator.cactiPerChunk = 1;
	         this.decorator.deadBushPerChunk = 1;
	         this.registerRhunPlainsFlowers();
	     }

	    @Override
	    public void decorate(World world, Random random, int i, int k) {
	        super.decorate(world, random, i, k);
	        if(random.nextInt(24) == 0) {
	            int i1 = i + random.nextInt(16) + 8;
	            int j1 = random.nextInt(128);
	            int k1 = k + random.nextInt(16) + 8;
	            new WorldGenFlowers(LOTRMod.pipeweedPlant).generate(world, random, i1, j1, k1);
	        }
	    }
	    @Override
	    public void generateBiomeTerrain(World world, Random random, Block[] blocks, byte[] meta, int i, int k, double stoneNoise, int height, LOTRBiomeVariant variant) {
	        double d4;
	        Block topBlock_pre = this.topBlock;
	        int topBlockMeta_pre = this.topBlockMeta;
	        Block fillerBlock_pre = this.fillerBlock;
	        int fillerBlockMeta_pre = this.fillerBlockMeta;
	        double d1 = noiseDirt.func_151601_a(i * 0.09, k * 0.09);
	        double d2 = noiseDirt.func_151601_a(i * 0.4, k * 0.4);
	        double d3 = noiseSand.func_151601_a(i * 0.09, k * 0.09);
	        if(d3 + (d4 = noiseSand.func_151601_a(i * 0.4, k * 0.4)) > 0.6) {
	            this.topBlock = Blocks.sand;
	            this.topBlockMeta = 0;
	            this.fillerBlock = this.topBlock;
	            this.fillerBlockMeta = this.topBlockMeta;
	        }
	        else if(d1 + d2 > 0.2) {
	            this.topBlock = Blocks.dirt;
	            this.topBlockMeta = 1;
	            this.fillerBlock = this.topBlock;
	            this.fillerBlockMeta = this.topBlockMeta;
	        }
	        super.generateBiomeTerrain(world, random, blocks, meta, i, k, stoneNoise, height, variant);
	        this.topBlock = topBlock_pre;
	        this.topBlockMeta = topBlockMeta_pre;
	        this.fillerBlock = fillerBlock_pre;
	        this.fillerBlockMeta = fillerBlockMeta_pre;
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
	        return LOTRMusicRegion.NEAR_HARAD.getSubregion("gondor");
	    }

	    @Override
	    public LOTRRoadType getRoadBlock() {
	        return LOTRRoadType.HARAD;
	    }
	    
	    @Override
	    public float getChanceToSpawnAnimals() {
	        return 0.25f;
	    }

	    @Override
	    public float getTreeIncreaseChance() {
	        return 0.02f;
	    }

	    @Override
	    public int spawnCountMultiplier() {
	        return 3;
	    }
	}
