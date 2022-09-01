package lotr.common.block;

import lotr.common.LOTRCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;

public class LOTRBlockGravel extends BlockGravel {
    public LOTRBlockGravel() {
        this.setCreativeTab(LOTRCreativeTabs.tabBlock);
        this.setHardness(0.6f);
        this.setStepSound(Block.soundTypeGravel);
    }
}
