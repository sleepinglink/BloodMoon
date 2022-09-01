package lotr.common.world.structure2;

import java.util.Random;

import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityRedDwarf;
import lotr.common.entity.npc.LOTREntityWindDwarf;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenWindMountainsHouse extends LOTRWorldGenDwarfHouse {
	public LOTRWorldGenWindMountainsHouse(boolean flag) {
	      super(flag);
	   }

	   protected LOTREntityWindDwarf createDwarf(World world) {
	        return new LOTREntityWindDwarf(world);
	   }

	   protected void setupRandomBlocks(Random random) {
	      super.setupRandomBlocks(random);
	      this.stoneBlock = Blocks.stone;
	      this.stoneMeta = 0;
	      this.fillerBlock = Blocks.stone;
	      this.fillerMeta = 0;
	      this.topBlock = Blocks.stone;
	      this.topMeta = 0;
	      this.brick2Block = LOTRMod.brick7;
	      this.brick2Meta = 6;
	      this.pillarBlock = LOTRMod.pillar5;
	      this.pillarMeta = 0;
	      this.chandelierBlock = LOTRMod.chandelier;
	      this.chandelierMeta = 1;
	      this.tableBlock = LOTRMod.wind_dwarven_table;
	      this.barsBlock = LOTRMod.wind_dwarf_bars;
	      this.larderContents = LOTRChestContents.WIND_DWARF_HOUSE_LARDER;
	      this.personalContents = LOTRChestContents.WIND_MOUNTAINS_STRONGHOLD;
	      this.plateFoods = LOTRFoods.DWARF;
	      this.drinkFoods = LOTRFoods.DWARF_DRINK;
	   }

	   protected ItemStack getRandomWeaponItem(Random random) {
	      ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.sword_wind_dwarven), new ItemStack(LOTRMod.dagger_wind_dwarven), new ItemStack(LOTRMod.hammer_wind_dwarven), new ItemStack(LOTRMod.battleaxe_wind_dwarven), new ItemStack(LOTRMod.pickaxe_wind_dwarven), new ItemStack(LOTRMod.mattock_wind_dwarven), new ItemStack(LOTRMod.throwing_axe_wind_dwarven), new ItemStack(LOTRMod.pike_wind_dwarven)};
	      return items[random.nextInt(items.length)].copy();
	   }

	   protected ItemStack getRandomOtherItem(Random random) {
	      ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.helmet_wind_dwarven), new ItemStack(LOTRMod.body_wind_dwarven), new ItemStack(LOTRMod.legs_wind_dwarven), new ItemStack(LOTRMod.boots_wind_dwarven), new ItemStack(LOTRMod.dwarfSteel), new ItemStack(LOTRMod.bronze), new ItemStack(LOTRMod.silver), new ItemStack(LOTRMod.silverNugget)};
	      return items[random.nextInt(items.length)].copy();
	   }
	}
