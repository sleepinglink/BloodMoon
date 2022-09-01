package lotr.common.world.structure2;

import java.util.Random;

import lotr.common.LOTRMod;
import lotr.common.entity.LOTREntityNPCRespawner;
import lotr.common.entity.npc.LOTREntityKhand;
import lotr.common.world.structure.LOTRChestContents;
import lotr.common.world.structure.LOTRChestContents2;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class LOTRWorldGenKhandHouse extends LOTRWorldGenStructureBase2  {
	   public LOTRWorldGenKhandHouse(boolean flag) {
		      super(flag);
		   }
	   
	   @Override
	   public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) {
		      this.setupRandomBlocks(random);
		      int j1;
		      int k1;
		      int i1; 
		      int i12;
		      int j12;
		      int width = 5;
		      int height = 7;
		      int minHeight;
		      int maxHeight;
		      this.setOriginAndRotation(world, i, j, k, rotation, 5);
		        if(this.restrictions) {
		            minHeight = 0;
		            maxHeight = 0;
		            for(int i13 = -4; i13 <= 4; ++i13) {
		                for(int k12 = -6; k12 <= 6; ++k12) {
		                    int j13 = this.getTopBlock(world, i13, k12);
		                    Block block = this.getBlock(world, i13, j13 - 1, k12);
		                    if(block != Blocks.grass && block != Blocks.stone) {
		                        return false;
		                    }
		                    if(j13 < minHeight) {
		                        minHeight = j13;
		                    }
		                    if(j13 > maxHeight) {
		                        maxHeight = j13;
		                    }
		                    if(maxHeight - minHeight <= 4) continue;
		                    return false;
		                }
		            }
		        }

		        for(i12 = -12; i12 <= 1; ++i12) {
		            for(k1 = -10; k1 <= 1; ++k1) {
		                for(j1 = 1; j1 <= 11; ++j1) {
		                    this.setAir(world, i12, j1, k1);
		                }
		                for(j1 = 0; !(j1 != 0 && this.isOpaque(world, i12, j1, k1) || this.getY(j1) < 0); --j1) {
		                    if(this.getBlock(world, i12, j1 + 1, k1).isOpaqueCube()) {
		                        this.setBlockAndMetadata(world, i12, j1, k1, Blocks.dirt, 0);
		                    }
		                    else {
		                        this.setBlockAndMetadata(world, i12, j1, k1, Blocks.grass, 0);
		                    }
		                    this.setGrassToDirt(world, i12, j1 - 1, k1);
		                }
		            }
		        }
		        
		      this.loadStrScan("khandHouse");
		      this.placeChest(world, random, -10, 2, -6, 4, LOTRChestContents2.KHAND_HOUSE);
		      this.generateStrScan(world, random, 0, 1, 0);
		      for(i1 = -width; i1 <= width; ++i1) {
		         for(minHeight =- height - 2; minHeight <= height + 1; ++minHeight) {
		            for(maxHeight = 0; !this.isOpaque(world, i1, maxHeight, minHeight) && this.getY(maxHeight) >= 0 && this.getBlock(world, i1, maxHeight + 1, minHeight) != Blocks.air; --maxHeight) {
		               this.setGrassToDirt(world, i1, maxHeight, minHeight);
		            }
		         }
		      }
		      int men = 3;
		      for(maxHeight = 0; maxHeight < men; ++maxHeight) {
		         LOTREntityKhand norscaMan = new LOTREntityKhand(world);
		         this.spawnNPCAndSetHome(norscaMan, world, 0, 2, 0, 16);
		      }
		      return true;
		   }
		}