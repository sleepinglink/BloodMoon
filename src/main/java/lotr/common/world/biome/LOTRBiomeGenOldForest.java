package lotr.common.world.biome;

import lotr.common.LOTRAchievement;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.feature.LOTRTreeType;
import lotr.common.world.map.LOTRWaypoint;
import lotr.common.world.spawning.*;

public class LOTRBiomeGenOldForest extends LOTRBiome {
    public LOTRBiomeGenOldForest(int i, boolean major) {
        super(i, major);
       
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_FOREST);
        this.decorator.treesPerChunk = 16;
        this.decorator.willowPerChunk = 2;
        this.decorator.flowersPerChunk = 1;
        this.decorator.grassPerChunk = 12;
        this.decorator.doubleGrassPerChunk = 5;
        this.decorator.enableFern = true;
        this.decorator.mushroomsPerChunk = 2;
        this.decorator.addTree(LOTRTreeType.OAK, 500);
        this.decorator.addTree(LOTRTreeType.OAK_TALL, 1000);
        this.decorator.addTree(LOTRTreeType.OAK_TALLER, 200);
        this.decorator.addTree(LOTRTreeType.OAK_LARGE, 500);
        this.decorator.addTree(LOTRTreeType.DARK_OAK, 1000);
        this.decorator.addTree(LOTRTreeType.FIR, 500);
        this.decorator.addTree(LOTRTreeType.PINE, 500);
        this.registerForestFlowers();
        this.biomeColors.setGrass(4686398);
        this.biomeColors.setFoliage(3172394);
        this.biomeColors.setFog(1651225);
        this.biomeColors.setFoggy(true);
        this.setBanditChance(LOTREventSpawner.EventChance.NEVER);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterOldForest;
    }

    @Override
    public LOTRWaypoint.Region getBiomeWaypoints() {
        return LOTRWaypoint.Region.OLD_FOREST;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.OLD_FOREST.getSubregion("oldForest");
    }

    @Override
    public float getChanceToSpawnAnimals() {
        return 0.0f;
    }
}
