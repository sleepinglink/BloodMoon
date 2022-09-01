package lotr.common.world.biome;

import lotr.common.LOTRAchievement;
import lotr.common.entity.animal.LOTREntityDeer;
import lotr.common.world.biome.variant.LOTRBiomeVariant;
import lotr.common.world.spawning.LOTREventSpawner;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRBiomeGenChetwood extends LOTRBiomeGenBreeland {
    public LOTRBiomeGenChetwood(int i, boolean major) {
        super(i, major);
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 4, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(LOTREntityDeer.class, 20, 4, 6));
        this.clearBiomeVariants();
        this.addBiomeVariantSet(LOTRBiomeVariant.SET_FOREST);
        this.decorator.treesPerChunk = 4;
        this.decorator.flowersPerChunk = 4;
        this.decorator.doubleFlowersPerChunk = 2;
        this.decorator.grassPerChunk = 6;
        this.decorator.doubleGrassPerChunk = 1;
        this.registerForestFlowers();
        this.setBanditChance(LOTREventSpawner.EventChance.BANDIT_UNCOMMON);
    }

    @Override
    public LOTRAchievement getBiomeAchievement() {
        return LOTRAchievement.enterChetwood;
    }

    @Override
    public LOTRMusicRegion.Sub getBiomeMusic() {
        return LOTRMusicRegion.BREE.getSubregion("chetwood");
    }
}
