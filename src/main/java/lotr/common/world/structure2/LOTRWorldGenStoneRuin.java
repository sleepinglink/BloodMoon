package lotr.common.world.structure2;

import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class LOTRWorldGenStoneRuin extends LOTRWorldGenStructureBase2 {
    private int minWidth;
    private int maxWidth;

    private LOTRWorldGenStoneRuin(int i, int j) {
        super(false);
        this.minWidth = i;
        this.maxWidth = j;
    }

    @Override
    public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
        boolean generateColumn;
        this.setOriginAndRotation(world, i, j, k, rotation, 0);
        int width = MathHelper.getRandomIntegerInRange(random, this.minWidth, this.maxWidth);
        boolean bl = generateColumn = random.nextInt(3) > 0;
        if(generateColumn) {
            int minHeight = 0;
            int maxHeight = 0;
            int columnX = 0 - width / 2;
            int columnZ = 0 - width / 2;
            if(this.restrictions) {
                block0: for(int i1 = columnX; i1 < columnX + width; ++i1) {
                    for(int k1 = columnZ; k1 < columnZ + width; ++k1) {
                        int j1 = this.getTopBlock(world, i1, k1);
                        if(j1 < minHeight) {
                            minHeight = j1;
                        }
                        if(j1 > maxHeight) {
                            maxHeight = j1;
                        }
                        if(maxHeight - minHeight > 8) {
                            generateColumn = false;
                            break block0;
                        }
                        if(this.isSurface(world, i1, j1 - 1, k1)) continue;
                        generateColumn = false;
                        break block0;
                    }
                }
            }
            if(generateColumn) {
                int baseHeight = 4 + random.nextInt(4) + random.nextInt(width * 3);
                for(int i1 = columnX; i1 < columnX + width; ++i1) {
                    for(int k1 = columnZ; k1 < columnZ + width; ++k1) {
                        int height;
                        for(int j1 = height = (int) (baseHeight * (1.0f + random.nextFloat())); j1 >= minHeight; --j1) {
                            this.placeRandomBrick(world, random, i1, j1, k1);
                            this.setGrassToDirt(world, i1, j1 - 1, k1);
                        }
                    }
                }
            }
        }
        int radius = width * 2;
        int ruinParts = 2 + random.nextInt(4) + random.nextInt(width * 3);
        for(int l = 0; l < ruinParts; ++l) {
            int i1 = MathHelper.getRandomIntegerInRange(random, -radius * 2, radius * 2);
            int k1 = MathHelper.getRandomIntegerInRange(random, -radius * 2, radius * 2);
            int j1 = this.getTopBlock(world, i1, k1);
            if(this.restrictions && !this.isSurface(world, i1, j1 - 1, k1)) continue;
            int randomFeature = random.nextInt(4);
            boolean flag = true;
            if(randomFeature == 0) {
                if(!this.isOpaque(world, i1, j1, k1)) {
                    this.placeRandomSlab(world, random, i1, j1, k1);
                }
            }
            else {
                int j2;
                for(j2 = j1; j2 < j1 + randomFeature && flag; ++j2) {
                    flag = !this.isOpaque(world, i1, j2, k1);
                }
                if(flag) {
                    for(j2 = j1; j2 < j1 + randomFeature; ++j2) {
                        this.placeRandomBrick(world, random, i1, j2, k1);
                    }
                }
            }
            if(!flag) continue;
            this.setGrassToDirt(world, i1, j1 - 1, k1);
        }
        return true;
    }

    protected abstract void placeRandomBrick(World var1, Random var2, int var3, int var4, int var5);

    protected abstract void placeRandomSlab(World var1, Random var2, int var3, int var4, int var5);

    public static class TAUREDAIN extends LOTRWorldGenStoneRuin {
        public TAUREDAIN(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(3);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick4, 0);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick4, 1);
                    break;
                }
                case 2: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick4, 2);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle8, 5);
            }
            else {
                int l = random.nextInt(3);
                switch(l) {
                    case 0: {
                        this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle8, 0);
                        break;
                    }
                    case 1: {
                        this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle8, 1);
                        break;
                    }
                    case 2: {
                        this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle8, 2);
                    }
                }
            }
        }
    }

    public static class RHUN extends LOTRWorldGenStoneRuin {
        public RHUN(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(3);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick5, 11);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick5, 13);
                    break;
                }
                case 2: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick5, 14);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle12, 4);
            }
            else {
                int l = random.nextInt(3);
                switch(l) {
                    case 0: {
                        this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle12, 0);
                        break;
                    }
                    case 1: {
                        this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle12, 1);
                        break;
                    }
                    case 2: {
                        this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle12, 2);
                    }
                }
            }
        }
    }

    public static class NUMENOR extends LOTRWorldGenStoneRuin {
        public NUMENOR(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick2, 11);
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle5, 3);
        }
    }

    public static class UMBAR extends LOTRWorldGenStoneRuin {
        public UMBAR(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(2);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick6, 6);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick6, 7);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle13, 2);
            }
            else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle13, 3);
            }
        }
    }

    public static class NEAR_HARAD extends LOTRWorldGenStoneRuin {
        public NEAR_HARAD(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(2);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick, 15);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick3, 11);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle4, 0);
            }
            else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle7, 1);
            }
        }
    }

    public static class MORDOR extends LOTRWorldGenStoneRuin {
        public MORDOR(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(2);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick, 0);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick, 7);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle, 1);
            }
            else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle2, 2);
            }
        }
    }

    public static class HIGH_ELVEN extends LOTRWorldGenStoneRuin {
        public HIGH_ELVEN(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(3);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick3, 2);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick3, 3);
                    break;
                }
                case 2: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick3, 4);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle6, 0 + random.nextInt(2));
            }
            else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle5, 5 + random.nextInt(3));
            }
        }
    }

    public static class GALADHRIM extends LOTRWorldGenStoneRuin {
        public GALADHRIM(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(3);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick, 11);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick, 12);
                    break;
                }
                case 2: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick, 13);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle2, 6 + random.nextInt(2));
            }
            else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle2, 3 + random.nextInt(3));
            }
        }
    }

    public static class DWARVEN extends LOTRWorldGenStoneRuin {
        public DWARVEN(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(2);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick, 6);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick4, 5);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle7, 6);
            }
            else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle, 7);
            }
        }
    }

    public static class DORWINION extends LOTRWorldGenStoneRuin {
        public DORWINION(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick5, 2);
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle9, 7);
        }
    }

    public static class DOL_GULDUR extends LOTRWorldGenStoneRuin {
        public DOL_GULDUR(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(2);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick2, 8);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick2, 9);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle4, 6);
            }
            else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle4, 5);
            }
        }
    }

    public static class ARNOR extends LOTRWorldGenStoneRuin {
        public ARNOR(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(3);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick2, 3);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick2, 4);
                    break;
                }
                case 2: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick2, 5);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle4, 2 + random.nextInt(2));
            }
            else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle4, 1);
            }
        }
    }

    public static class ANGMAR extends LOTRWorldGenStoneRuin {
        public ANGMAR(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(2);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick2, 0);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, LOTRMod.brick2, 1);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle3, 4);
            }
            else {
                this.setBlockAndMetadata(world, i, j, k, LOTRMod.slabSingle3, 3);
            }
        }
    }

    public static class STONE extends LOTRWorldGenStoneRuin {
        public STONE(int i, int j) {
            super(i, j);
        }

        @Override
        protected void placeRandomBrick(World world, Random random, int i, int j, int k) {
            int l = random.nextInt(3);
            switch(l) {
                case 0: {
                    this.setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 0);
                    break;
                }
                case 1: {
                    this.setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 1);
                    break;
                }
                case 2: {
                    this.setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 2);
                }
            }
        }

        @Override
        protected void placeRandomSlab(World world, Random random, int i, int j, int k) {
            if(random.nextInt(4) == 0) {
                this.setBlockAndMetadata(world, i, j, k, Blocks.stone_slab, 0);
            }
            else {
                this.setBlockAndMetadata(world, i, j, k, Blocks.stone_slab, 5);
            }
        }
    }

}
