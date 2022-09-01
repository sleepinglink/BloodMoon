package lotr.common.world.structure2;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityWindDwarfSmith;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.world.World;

public class LOTRWorldGenWindMountainsSmithy extends LOTRWorldGenDwarfSmithy {
	   public LOTRWorldGenWindMountainsSmithy(boolean flag) {
		      super(flag);
		      this.baseBrickBlock = LOTRMod.brick7;
		      this.baseBrickMeta = 6;
		      this.carvedBrickBlock = LOTRMod.brick7;
		      this.carvedBrickMeta = 6;
		      this.pillarBlock = LOTRMod.pillar5;
		      this.pillarMeta = 0;
		      this.tableBlock = LOTRMod.wind_dwarven_table;
		      this.barsBlock = LOTRMod.wind_dwarf_bars;
		   }

		   protected LOTREntityDwarf createSmith(World world) {
		      return new LOTREntityWindDwarfSmith(world);
		   }

		   protected LOTRChestContents getChestContents() {
		      return LOTRChestContents.WIND_MOUNTAINS_SMITHY;
		   }
		}
