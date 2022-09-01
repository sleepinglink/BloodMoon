package lotr.common.world.structure2;

import java.util.Random;

import lotr.common.LOTRFoods;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityDwarf;
import lotr.common.entity.npc.LOTREntityRedDwarf;
import lotr.common.world.structure.LOTRChestContents;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRWorldGenRedMountainsHouse extends LOTRWorldGenDwarfHouse {
	public LOTRWorldGenRedMountainsHouse(boolean flag) {
	      super(flag);
	   }

	   protected LOTREntityRedDwarf createDwarf(World world) {
	        return new LOTREntityRedDwarf(world);
	   }

	   protected void setupRandomBlocks(Random random) {
	      super.setupRandomBlocks(random);
	      this.stoneBlock = Blocks.stone;
	      this.stoneMeta = 0;
	      this.fillerBlock = LOTRMod.rock;
	      this.fillerMeta = 4;
	      this.topBlock = LOTRMod.rock;
	      this.topMeta = 4;
	      this.brick2Block = LOTRMod.brick2;
	      this.brick2Meta = 2;
	      this.pillarBlock = LOTRMod.pillar;
	      this.pillarMeta = 4;
	      this.chandelierBlock = LOTRMod.chandelier;
	      this.chandelierMeta = 0;
	      this.tableBlock = LOTRMod.red_dwarven_table;
	      this.barsBlock = LOTRMod.red_dwarf_bars;
	      this.larderContents = LOTRChestContents.RED_DWARF_HOUSE_LARDER;
	      this.personalContents = LOTRChestContents.RED_MOUNTAINS_STRONGHOLD;
	      this.plateFoods = LOTRFoods.DWARF;
	      this.drinkFoods = LOTRFoods.DWARF_DRINK;
	   }

	   protected ItemStack getRandomWeaponItem(Random random) {
	      ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.sword_red_dwarven), new ItemStack(LOTRMod.dagger_red_dwarven), new ItemStack(LOTRMod.hammer_red_dwarven), new ItemStack(LOTRMod.battleaxe_red_dwarven), new ItemStack(LOTRMod.pickaxe_red_dwarven), new ItemStack(LOTRMod.mattock_red_dwarven), new ItemStack(LOTRMod.throwing_axe_red_dwarven), new ItemStack(LOTRMod.pike_red_dwarven)};
	      return items[random.nextInt(items.length)].copy();
	   }

	   protected ItemStack getRandomOtherItem(Random random) {
	      ItemStack[] items = new ItemStack[]{new ItemStack(LOTRMod.helmet_red_dwarven), new ItemStack(LOTRMod.body_red_dwarven), new ItemStack(LOTRMod.legs_red_dwarven), new ItemStack(LOTRMod.boots_red_dwarven), new ItemStack(LOTRMod.dwarfSteel), new ItemStack(LOTRMod.bronze), new ItemStack(LOTRMod.silver), new ItemStack(LOTRMod.silverNugget)};
	      return items[random.nextInt(items.length)].copy();
	   }
	}

