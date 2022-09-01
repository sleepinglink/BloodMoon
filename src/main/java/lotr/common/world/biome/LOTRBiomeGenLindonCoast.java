package lotr.common.world.biome;

import net.minecraft.init.Blocks;

public class LOTRBiomeGenLindonCoast extends LOTRBiomeGenLindon {
    public LOTRBiomeGenLindonCoast(int i, boolean major) {
        super(i, major);
        this.topBlock = Blocks.stone;
        this.topBlockMeta = 0;
        this.fillerBlock = this.topBlock;
        this.fillerBlockMeta = this.topBlockMeta;
        this.biomeTerrain.setXZScale(30.0);
        this.clearBiomeVariants();
        this.decorator.clearRandomStructures();
    }
}
