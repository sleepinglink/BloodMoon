package lotr.common.world.structure2;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityRedDwarfSmith;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.world.World;

public class LOTRWorldGenRedMountainsSmithy extends LOTRWorldGenDwarfSmithy{
	   public LOTRWorldGenRedMountainsSmithy(boolean flag) {
		      super(flag);
		      this.baseBrickBlock = LOTRMod.brick2;
		      this.baseBrickMeta = 2;
		      this.carvedBrickBlock = LOTRMod.brick2;
		      this.carvedBrickMeta = 2;
		      this.pillarBlock = LOTRMod.pillar;
		      this.pillarMeta = 4;
		      this.tableBlock = LOTRMod.red_dwarven_table;
		      this.barsBlock = LOTRMod.red_dwarf_bars;
		   }

		   protected LOTREntityDwarf createSmith(World world) {
		      return new LOTREntityRedDwarfSmith(world);
		   }

		   protected LOTRChestContents getChestContents() {
		      return LOTRChestContents.RED_MOUNTAINS_SMITHY;
		   }
		}
