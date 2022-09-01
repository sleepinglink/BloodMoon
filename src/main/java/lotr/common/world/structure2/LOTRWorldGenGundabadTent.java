package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.init.Blocks;

public class LOTRWorldGenGundabadTent extends LOTRWorldGenTentBase {
    public LOTRWorldGenGundabadTent(boolean flag) {
        super(flag);
    }

    @Override
    protected void setupRandomBlocks(Random random) {
        super.setupRandomBlocks(random);
        int randomWool = random.nextInt(3);
        if(randomWool == 0) {
            this.tentBlock = Blocks.wool;
            this.tentMeta = 15;
        }
        else if(randomWool == 1) {
            this.tentBlock = Blocks.wool;
            this.tentMeta = 12;
        }
        else if(randomWool == 2) {
            this.tentBlock = Blocks.wool;
            this.tentMeta = 7;
        }
        this.fenceBlock = LOTRMod.fence;
        this.fenceMeta = 3;
        this.tableBlock = LOTRMod.gundabadTable;
        this.chestContents = LOTRChestContents.GUNDABAD_TENT;
        this.hasOrcTorches = true;
    }
}
