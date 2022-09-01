package lotr.common.world.village;

import java.util.Random;
import com.google.common.math.IntMath;
import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.*;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.map.LOTRRoadType;
import lotr.common.world.structure2.*;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LOTRVillageGenSouthron extends LOTRVillageGen {
    public LOTRVillageGenSouthron(LOTRBiome biome, float f) {
        super(biome);
        this.gridScale = 14;
        this.gridRandomDisplace = 1;
        this.spawnChance = f;
        this.villageChunkSize = 5;
    }

    @Override
    protected LOTRVillageGen.AbstractInstance createVillageInstance(World world, int i, int k, Random random) {
        return new Instance(world, i, k, random);
    }

    public class Instance extends LOTRVillageGen.AbstractInstance {
        public VillageType villageType;
        public String[] villageName;
        private static final int roadWidth = 5;
        private static final int pathFuzz = 3;
        private static final int rInnerSquare = 14;
        private static final int rOuterSquare = 45;
        private static final int tavernEdge = 7;

        public Instance(World world, int i, int k, Random random) {
            super(world, i, k, random);
        }

        @Override
        protected void setupVillageProperties(Random random) {
            this.villageType = random.nextInt(4) == 0 ? VillageType.FORT : (random.nextInt(3) == 0 ? VillageType.TOWN : VillageType.VILLAGE);
            this.villageName = LOTRNames.getHaradVillageName(random);
        }

        @Override
        public boolean isFlat() {
            return false;
        }

        @Override
        protected void addVillageStructures(Random random) {
            if(this.villageType == VillageType.VILLAGE) {
                this.setupVillage(random);
            }
            else if(this.villageType == VillageType.TOWN) {
                this.setupTown(random);
            }
            else if(this.villageType == VillageType.FORT) {
                this.setupFort(random);
            }
        }

        private void setupVillage(Random random) {
            this.addStructure(new LOTRWorldGenNPCRespawner(false) {

                @Override
                public void setupRespawner(LOTREntityNPCRespawner spawner) {
                    Instance.this.setCivilianSpawnClass(spawner);
                    spawner.setCheckRanges(64, -12, 12, 24);
                    spawner.setSpawnRanges(32, -6, 6, 32);
                    spawner.setBlockEnemySpawnRange(64);
                }
            }, 0, 0, 0);
            this.addStructure(new LOTRWorldGenNPCRespawner(false) {

                @Override
                public void setupRespawner(LOTREntityNPCRespawner spawner) {
                    Instance.this.setWarriorSpawnClasses(spawner);
                    spawner.setCheckRanges(64, -12, 12, 12);
                    spawner.setSpawnRanges(32, -6, 6, 32);
                    spawner.setBlockEnemySpawnRange(64);
                }
            }, 0, 0, 0);
            this.addStructure(this.getWell(random), 0, -2, 0, true);
            this.addStructure(this.getSignpost(random).setSignText(this.villageName), 0, -8, 0, true);
            int rSquareEdge = 17;
            this.addStructure(this.getTavern(random), 0, rSquareEdge, 0, true);
            this.addStructure(this.getMansion(random), -3, -rSquareEdge, 2, true);
            this.addStructure(this.getMansion(random), -rSquareEdge, 3, 1, true);
            this.addStructure(this.getMansion(random), rSquareEdge, -3, 3, true);
            int backFenceX = 0;
            int backFenceZ = rSquareEdge + 19;
            int backFenceWidth = 12;
            int sideFenceX = 13;
            int sideFenceZ = rSquareEdge + 11;
            int sideFenceWidth = 8;
            int frontPostZ = sideFenceZ - sideFenceWidth - 1;
            this.addStructure(new LOTRWorldGenSouthronVillageFence(false).setLeftRightExtent(backFenceWidth, backFenceWidth), backFenceX, -backFenceZ, 0);
            this.addStructure(new LOTRWorldGenSouthronVillageFence(false).setLeftRightExtent(sideFenceWidth, sideFenceWidth - 1), -sideFenceX, -sideFenceZ, 1);
            this.addStructure(new LOTRWorldGenSouthronVillageFence(false).setLeftRightExtent(sideFenceWidth - 1, sideFenceWidth), sideFenceX, -sideFenceZ, 3);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), -sideFenceX, -frontPostZ, 0);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), sideFenceX, -frontPostZ, 0);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), -sideFenceX, -backFenceZ, 0);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), sideFenceX, -backFenceZ, 0);
            this.addStructure(new LOTRWorldGenSouthronVillageFence(false).setLeftRightExtent(backFenceWidth, backFenceWidth), -backFenceZ, backFenceX, 1);
            this.addStructure(new LOTRWorldGenSouthronVillageFence(false).setLeftRightExtent(sideFenceWidth, sideFenceWidth - 1), -sideFenceZ, sideFenceX, 0);
            this.addStructure(new LOTRWorldGenSouthronVillageFence(false).setLeftRightExtent(sideFenceWidth - 1, sideFenceWidth), -sideFenceZ, -sideFenceX, 2);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), -frontPostZ, sideFenceX, 0);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), -frontPostZ, -sideFenceX, 0);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), -backFenceZ, sideFenceX, 0);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), -backFenceZ, -sideFenceX, 0);
            this.addStructure(new LOTRWorldGenSouthronVillageFence(false).setLeftRightExtent(backFenceWidth, backFenceWidth), backFenceZ, backFenceX, 3);
            this.addStructure(new LOTRWorldGenSouthronVillageFence(false).setLeftRightExtent(sideFenceWidth, sideFenceWidth - 1), sideFenceZ, -sideFenceX, 2);
            this.addStructure(new LOTRWorldGenSouthronVillageFence(false).setLeftRightExtent(sideFenceWidth - 1, sideFenceWidth), sideFenceZ, sideFenceX, 0);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), frontPostZ, -sideFenceX, 0);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), frontPostZ, sideFenceX, 0);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), backFenceZ, -sideFenceX, 0);
            this.addStructure(new LOTRWorldGenSouthronVillagePost(false), backFenceZ, sideFenceX, 0);
            int farmRange = 3;
            int farmStep = 14;
            int farmX = 55;
            for(int l = -farmRange; l <= farmRange; ++l) {
                int k = l * farmStep;
                int i = -farmX;
                int r = 1;
                if(random.nextInt(3) == 0) {
                    this.addStructure(new LOTRWorldGenHayBales(false), i, k, r);
                }
                else {
                    this.addStructure(this.getRandomFarm(random), i, k, r);
                }
                i = farmX;
                r = 3;
                if(random.nextInt(3) == 0) {
                    this.addStructure(new LOTRWorldGenHayBales(false), i, k, r);
                    continue;
                }
                this.addStructure(this.getRandomFarm(random), i, k, r);
            }
            int houseRange = 3;
            int houseStep = 17;
            int houseZ = 55;
            for(int l = -houseRange; l <= houseRange; ++l) {
                int i = l * houseStep;
                int k = -houseZ;
                int r = 2;
                this.addStructure(this.getRandomHouse(random), i, k, r);
                k = houseZ;
                r = 0;
                if(Math.abs(i) < 7) continue;
                this.addStructure(this.getRandomHouse(random), i, k, r);
            }
        }

        protected void setCivilianSpawnClass(LOTREntityNPCRespawner spawner) {
            spawner.setSpawnClass(LOTREntityNearHaradrim.class);
        }

        protected void setWarriorSpawnClasses(LOTREntityNPCRespawner spawner) {
            spawner.setSpawnClasses(LOTREntityNearHaradrimWarrior.class, LOTREntityNearHaradrimArcher.class);
        }

        protected LOTRWorldGenStructureBase2 getWell(Random random) {
            return new LOTRWorldGenSouthronWell(false);
        }

        protected LOTRWorldGenSouthronVillageSign getSignpost(Random random) {
            return new LOTRWorldGenSouthronVillageSign(false);
        }

        protected LOTRWorldGenStructureBase2 getMansion(Random random) {
            return new LOTRWorldGenSouthronMansion(false);
        }

        protected LOTRWorldGenStructureBase2 getTavern(Random random) {
            return new LOTRWorldGenSouthronTavern(false);
        }

        protected LOTRWorldGenStructureBase2 getRandomHouse(Random random) {
            if(random.nextInt(6) == 0) {
                return new LOTRWorldGenSouthronSmithy(false);
            }
            if(random.nextInt(6) == 0) {
                return new LOTRWorldGenSouthronStables(false);
            }
            return new LOTRWorldGenSouthronHouse(false);
        }

        protected LOTRWorldGenStructureBase2 getRandomFarm(Random random) {
            if(random.nextBoolean()) {
                return new LOTRWorldGenSouthronFarm(false);
            }
            return new LOTRWorldGenSouthronPasture(false);
        }

        private void setupTown(Random random) {
            int k;
            int l;
            int i;
            int r;
            this.addStructure(new LOTRWorldGenNPCRespawner(false) {

                @Override
                public void setupRespawner(LOTREntityNPCRespawner spawner) {
                    Instance.this.setCivilianSpawnClass(spawner);
                    spawner.setCheckRanges(80, -12, 12, 100);
                    spawner.setSpawnRanges(40, -6, 6, 64);
                    spawner.setBlockEnemySpawnRange(60);
                }
            }, 0, 0, 0);
            for(int i1 : new int[] {-30, 30}) {
                for(int k1 : new int[] {-30, 30}) {
                    this.addStructure(new LOTRWorldGenNPCRespawner(false) {

                        @Override
                        public void setupRespawner(LOTREntityNPCRespawner spawner) {
                            Instance.this.setWarriorSpawnClasses(spawner);
                            spawner.setCheckRanges(40, -12, 12, 16);
                            spawner.setSpawnRanges(20, -6, 6, 64);
                            spawner.setBlockEnemySpawnRange(60);
                        }
                    }, i1, k1, 0);
                }
            }
            this.addStructure(this.getBazaar(random), 1, -2, 0, true);
            this.addStructure(this.getLamp(random), 15, -2, 0, true);
            this.addStructure(this.getLamp(random), -13, -2, 0, true);
            this.addStructure(this.getLamp(random), 15, 18, 0, true);
            this.addStructure(this.getLamp(random), -13, 18, 0, true);
            this.addStructure(this.getWell(random), -16, 12, 1, true);
            this.addStructure(this.getWell(random), -16, 4, 1, true);
            this.addStructure(this.getFlowers(random), 18, 13, 3, true);
            this.addStructure(this.getFlowers(random), 18, 3, 3, true);
            for(l = 0; l <= 3; ++l) {
                i = -41 + l * 19;
                k = -7;
                r = 2;
                this.addStructure(this.getMansion(random), i, k, r, true);
                this.addStructure(this.getLamp(random), i + 6, k - 1, r, true);
                i = 24 - l * 19;
                k = 23;
                r = 0;
                this.addStructure(this.getMansion(random), i, k, r, true);
                this.addStructure(this.getLamp(random), i - 6, k + 1, r, true);
            }
            this.addStructure(this.getSmithy(random), -25, 9, 1, true);
            this.addStructure(this.getHouse(random), -25, 18, 1, true);
            this.addStructure(this.getHouse(random), -25, -2, 1, true);
            this.addStructure(this.getTree(random), -45, 8, 1, true);
            this.addStructure(this.getHouse(random), -50, 18, 3, true);
            this.addStructure(this.getHouse(random), -50, -2, 3, true);
            this.addStructure(this.getWell(random), -51, -14, 2, true);
            this.addStructure(this.getTree(random), -46, -29, 2, true);
            this.addStructure(this.getFlowers(random), -42, -32, 3, true);
            this.addStructure(this.getTree(random), -50, 30, 0, true);
            for(l = -3; l <= 3; ++l) {
                i = -56;
                k = -2 + l * 10;
                r = 1;
                this.addStructure(this.getHouse(random), i, k, r, true);
            }
            this.addStructure(this.getStatue(random), 26, 8, 3, true);
            this.addStructure(this.getHouse(random), 26, 18, 3, true);
            this.addStructure(this.getHouse(random), 26, -2, 3, true);
            for(l = -3; l <= 2; ++l) {
                i = 52;
                k = 8 + l * 10;
                r = 1;
                this.addStructure(this.getHouse(random), i, k, r, true);
            }
            this.addStructure(this.getSmithy(random), 41, -33, 3, true);
            for(l = -2; l <= 2; ++l) {
                i = 65;
                k = 3 + l * 14;
                r = 2;
                this.addStructure(this.getHouse(random), i, k, r, true);
            }
            this.addStructure(this.getWell(random), 57, -19, 2, true);
            this.addStructure(this.getLamp(random), 57, -16, 2, true);
            this.addStructure(this.getLamp(random), 57, -8, 2, true);
            this.addStructure(this.getTree(random), 57, 1, 2, true);
            this.addStructure(this.getLamp(random), 57, 4, 2, true);
            this.addStructure(this.getLamp(random), 57, 12, 2, true);
            this.addStructure(this.getTree(random), 57, 21, 2, true);
            this.addStructure(this.getLamp(random), 57, 24, 2, true);
            this.addStructure(this.getLamp(random), 57, 32, 2, true);
            for(l = 0; l <= 3; ++l) {
                i = 41 + l * 8;
                k = 34;
                r = 0;
                this.addStructure(this.getFlowers(random), i, k, r, true);
            }
            this.addStructure(this.getTree(random), 34, 25, 0, true);
            this.addStructure(this.getStables(random), -20, -30, 1, true);
            this.addStructure(this.getTavern(random), 17, -32, 1, true);
            this.addStructure(this.getLamp(random), 19, -28, 1, true);
            this.addStructure(this.getLamp(random), 19, -36, 1, true);
            this.addStructure(this.getLamp(random), -16, -32, 3, true);
            this.addStructure(this.getFlowers(random), 25, -32, 3, true);
            this.addStructure(this.getTree(random), 34, -29, 2, true);
            this.addStructure(this.getLamp(random), 34, -26, 2, true);
            this.addStructure(this.getLamp(random), 34, -18, 2, true);
            this.addStructure(this.getTree(random), 34, -9, 2, true);
            this.addStructure(this.getTownGate(random).setSignText(this.villageName), 34, -47, 0, true);
            this.addStructure(this.getTownWallCorner(random), 73, -47, 0, true);
            this.addStructure(this.getTownWallCorner(random), -77, -43, 3, true);
            this.addStructure(this.getTownWallCorner(random), -73, 47, 2, true);
            this.addStructure(this.getTownWallCorner(random), 77, 43, 1, true);
            for(l = 0; l <= 6; ++l) {
                i = 68 - l * 4;
                k = -44;
                r = 0;
                if(l % 2 == 0) {
                    this.addStructure(this.getTownWallShort(random), i, k, r, true);
                    continue;
                }
                this.addStructure(this.getTownWallLong(random), i, k, r, true);
            }
            this.addStructure(this.getTownWallExtra(random), 24, -44, 0, true);
            for(l = 0; l <= 22; ++l) {
                i = 20 - l * 4;
                k = -44;
                r = 0;
                if(l % 2 == 0) {
                    this.addStructure(this.getTownWallShort(random), i, k, r, true);
                    continue;
                }
                this.addStructure(this.getTownWallLong(random), i, k, r, true);
            }
            this.addStructure(this.getTownWallSideMid(random), 74, 0, 1, true);
            this.addStructure(this.getTownWallSideMid(random), -74, 0, 3, true);
            for(l = 1; l <= 9; ++l) {
                i = 74;
                k = 2 + l * 4;
                if(l % 2 == 1) {
                    this.addStructure(this.getTownWallShort(random), i, k, 1, true);
                    this.addStructure(this.getTownWallShort(random), i, -k, 1, true);
                    this.addStructure(this.getTownWallShort(random), -i, k, 3, true);
                    this.addStructure(this.getTownWallShort(random), -i, -k, 3, true);
                    continue;
                }
                this.addStructure(this.getTownWallLong(random), i, k, 1, true);
                this.addStructure(this.getTownWallLong(random), i, -k, 1, true);
                this.addStructure(this.getTownWallLong(random), -i, k, 3, true);
                this.addStructure(this.getTownWallLong(random), -i, -k, 3, true);
            }
            for(l = -17; l <= 17; ++l) {
                i = 0 + l * 4;
                k = 44;
                r = 2;
                if(IntMath.mod(l, 2) == 1) {
                    this.addStructure(this.getTownWallShort(random), i, k, r, true);
                    continue;
                }
                this.addStructure(this.getTownWallLong(random), i, k, r, true);
            }
        }

        protected LOTRWorldGenStructureBase2 getHouse(Random random) {
            return new LOTRWorldGenSouthronHouse(false);
        }

        protected LOTRWorldGenStructureBase2 getSmithy(Random random) {
            return new LOTRWorldGenSouthronSmithy(false);
        }

        protected LOTRWorldGenStructureBase2 getBazaar(Random random) {
            return new LOTRWorldGenSouthronBazaar(false);
        }

        protected LOTRWorldGenStructureBase2 getLamp(Random random) {
            return new LOTRWorldGenSouthronLamp(false);
        }

        protected LOTRWorldGenStructureBase2 getFlowers(Random random) {
            return new LOTRWorldGenSouthronTownFlowers(false);
        }

        protected LOTRWorldGenStructureBase2 getTree(Random random) {
            return new LOTRWorldGenSouthronTownTree(false);
        }

        protected LOTRWorldGenStructureBase2 getStatue(Random random) {
            return new LOTRWorldGenSouthronStatue(false);
        }

        protected LOTRWorldGenSouthronTownGate getTownGate(Random random) {
            return new LOTRWorldGenSouthronTownGate(false);
        }

        protected LOTRWorldGenStructureBase2 getTownWallShort(Random random) {
            return new LOTRWorldGenSouthronTownWall.Short(false);
        }

        protected LOTRWorldGenStructureBase2 getTownWallLong(Random random) {
            return new LOTRWorldGenSouthronTownWall.Long(false);
        }

        protected LOTRWorldGenStructureBase2 getTownWallSideMid(Random random) {
            return new LOTRWorldGenSouthronTownWall.SideMid(false);
        }

        protected LOTRWorldGenStructureBase2 getTownWallExtra(Random random) {
            return new LOTRWorldGenSouthronTownWall.Extra(false);
        }

        protected LOTRWorldGenStructureBase2 getTownWallCorner(Random random) {
            return new LOTRWorldGenSouthronTownCorner(false);
        }

        private void setupFort(Random random) {
            int k;
            int l;
            int i;
            int r;
            this.addStructure(new LOTRWorldGenNPCRespawner(false) {

                @Override
                public void setupRespawner(LOTREntityNPCRespawner spawner) {
                    Instance.this.setCivilianSpawnClass(spawner);
                    spawner.setCheckRanges(60, -12, 12, 16);
                    spawner.setSpawnRanges(24, -6, 6, 40);
                    spawner.setBlockEnemySpawnRange(60);
                }
            }, 0, 0, 0);
            for(int i1 : new int[] {-25, 25}) {
                for(int k1 : new int[] {-25, 25}) {
                    this.addStructure(new LOTRWorldGenNPCRespawner(false) {

                        @Override
                        public void setupRespawner(LOTREntityNPCRespawner spawner) {
                            Instance.this.setWarriorSpawnClasses(spawner);
                            spawner.setCheckRanges(35, -12, 12, 16);
                            spawner.setSpawnRanges(15, -6, 6, 40);
                            spawner.setBlockEnemySpawnRange(35);
                        }
                    }, i1, k1, 0);
                }
            }
            this.placeChampionRespawner();
            this.addStructure(this.getFortress(random), 0, -15, 0, true);
            this.addStructure(this.getBarracks(random), -33, -8, 0, true);
            this.addStructure(this.getBarracks(random), 32, -8, 0, true);
            this.addStructure(this.getTower(random), -43, -36, 2, true);
            this.addStructure(this.getTower(random), 43, -36, 2, true);
            this.addStructure(this.getTower(random), -43, 36, 0, true);
            this.addStructure(this.getTower(random), 43, 36, 0, true);
            for(l = 0; l <= 2; ++l) {
                i = 10 + l * 11;
                k = -28;
                r = 2;
                this.addStructure(this.getRandomFarm(random), i, k, r);
                this.addStructure(this.getRandomFarm(random), -i, k, r);
            }
            this.addStructure(this.getTraining(random), 0, 27, 0, true);
            this.addStructure(this.getStables(random), -29, 33, 3, true);
            this.addStructure(this.getStables(random), 29, 37, 1, true);
            this.addStructure(this.getFortGate(random), 0, -47, 0, true);
            for(l = 0; l <= 9; ++l) {
                i = 8 + l * 4;
                k = -46;
                r = 0;
                if(l % 2 == 0) {
                    this.addStructure(this.getFortWallLong(random), -i, k, r, true);
                    this.addStructure(this.getFortWallLong(random), i, k, r, true);
                    continue;
                }
                this.addStructure(this.getFortWallLong(random), -i, k, r, true);
                this.addStructure(this.getFortWallLong(random), i, k, r, true);
            }
            for(l = -11; l <= 11; ++l) {
                i = l * 4;
                k = 46;
                r = 2;
                if(l % 2 == 0) {
                    this.addStructure(this.getFortWallLong(random), i, k, r, true);
                    continue;
                }
                this.addStructure(this.getFortWallLong(random), i, k, r, true);
            }
            for(l = -10; l <= 10; ++l) {
                i = -50;
                k = l * 4;
                r = 3;
                if(l % 2 == 0) {
                    this.addStructure(this.getFortWallLong(random), i, k, r, true);
                }
                else {
                    this.addStructure(this.getFortWallLong(random), i, k, r, true);
                }
                r = 1;
                if(l % 2 == 0) {
                    this.addStructure(this.getFortWallLong(random), -i, k, r, true);
                    continue;
                }
                this.addStructure(this.getFortWallLong(random), -i, k, r, true);
            }
            this.addStructure(this.getFortCorner(random), -50, -46, 0, true);
            this.addStructure(this.getFortCorner(random), 50, -46, 1, true);
            this.addStructure(this.getFortCorner(random), -50, 46, 3, true);
            this.addStructure(this.getFortCorner(random), 50, 46, 2, true);
        }

        protected void placeChampionRespawner() {
            this.addStructure(new LOTRWorldGenNPCRespawner(false) {

                @Override
                public void setupRespawner(LOTREntityNPCRespawner spawner) {
                    spawner.setSpawnClass(LOTREntitySouthronChampion.class);
                    spawner.setCheckRanges(60, -12, 12, 4);
                    spawner.setSpawnRanges(24, -6, 6, 32);
                }
            }, 0, 0, 0);
        }

        protected LOTRWorldGenStructureBase2 getFortress(Random random) {
            return new LOTRWorldGenSouthronFortress(false);
        }

        protected LOTRWorldGenStructureBase2 getTower(Random random) {
            return new LOTRWorldGenSouthronTower(false);
        }

        protected LOTRWorldGenStructureBase2 getStables(Random random) {
            return new LOTRWorldGenSouthronStables(false);
        }

        protected LOTRWorldGenStructureBase2 getBarracks(Random random) {
            return new LOTRWorldGenSouthronBarracks(false);
        }

        protected LOTRWorldGenStructureBase2 getTraining(Random random) {
            return new LOTRWorldGenSouthronTraining(false);
        }

        protected LOTRWorldGenStructureBase2 getFortGate(Random random) {
            return new LOTRWorldGenSouthronFortGate(false);
        }

        protected LOTRWorldGenStructureBase2 getFortWallShort(Random random) {
            return new LOTRWorldGenSouthronFortWall.Short(false);
        }

        protected LOTRWorldGenStructureBase2 getFortWallLong(Random random) {
            return new LOTRWorldGenSouthronFortWall.Long(false);
        }

        protected LOTRWorldGenStructureBase2 getFortCorner(Random random) {
            return new LOTRWorldGenSouthronFortCorner(false);
        }

        @Override
        protected LOTRRoadType getPath(Random random, int i, int k) {
            int i1 = Math.abs(i);
            int k1 = Math.abs(k);
            if(this.villageType == VillageType.VILLAGE) {
                int dSq = i * i + k * k;
                int imn = 2;
                int imx = 14 + random.nextInt(3);
                int kmn = 2;
                int kmx = 14 + random.nextInt(3);
                if(i1 <= imx && k1 <= kmx && (i1 > imn || k1 > kmn)) {
                    return LOTRRoadType.PATH;
                }
                imn = 45 - random.nextInt(3);
                imx = 50 + random.nextInt(3);
                kmn = 45 - random.nextInt(3);
                kmx = 50 + random.nextInt(3);
                if(!(i1 > imx || k1 > kmx || i1 <= imn && k1 <= kmn || k >= 0 && i1 <= 7)) {
                    return LOTRRoadType.PATH;
                }
                if(k < 0) {
                    int d1;
                    imn = 14;
                    imx = 45;
                    if(i1 + k1 >= imn + imn && i1 + k1 <= imx + imx && (d1 = Math.abs(i1 - k1)) <= (int) (2.5f + random.nextInt(3) * 2.0f)) {
                        return LOTRRoadType.PATH;
                    }
                }
                if(k > 0) {
                    imn = 10;
                    imx = imn + 5 + random.nextInt(3);
                    kmn = 14;
                    kmx = 45;
                    if(k1 >= kmn && k1 <= kmx && i1 >= (imn -= random.nextInt(3)) && i1 <= imx) {
                        return LOTRRoadType.PATH;
                    }
                }
            }
            if(this.villageType == VillageType.TOWN && i1 <= 72 && k1 <= 42) {
                return LOTRRoadType.HARAD_TOWN;
            }
            if(this.villageType == VillageType.FORT) {
                if(i1 <= 3 && k >= -45 && k <= -15) {
                    return LOTRRoadType.PATH;
                }
                if(i1 <= 36 && k >= -27 && k <= -20) {
                    return LOTRRoadType.PATH;
                }
                if(i1 >= 29 && i1 <= 36 && k >= -27 && k <= 39 && (k < -7 || k > 7)) {
                    return LOTRRoadType.PATH;
                }
                if(i1 <= 36 && k >= 20 && k <= 27) {
                    return LOTRRoadType.PATH;
                }
            }
            return null;
        }

        @Override
        public boolean isVillageSurface(World world, int i, int j, int k) {
            if(this.villageType == VillageType.TOWN) {
                Block block = world.getBlock(i, j, k);
                int meta = world.getBlockMetadata(i, j, k);
                if(block == LOTRMod.brick && meta == 15 || block == LOTRMod.brick3 && meta == 11 || block == LOTRMod.pillar && meta == 5) {
                    return true;
                }
            }
            return false;
        }

    }

    public static enum VillageType {
        VILLAGE, TOWN, FORT;

    }

}
