package lotr.common.world.structure2;

import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityDarkWoodfSmith;
import lotr.common.entity.npc.LOTREntityElf;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class LOTRWorldGenDarkWoodForges extends LOTRWorldGenDarkWoodForge {
    public LOTRWorldGenDarkWoodForges(boolean flag) {
        super(flag);
        this.brickBlock = LOTRMod.brick7;
        this.brickMeta = 5;
        this.pillarBlock = LOTRMod.pillar4;
        this.pillarMeta = 0;
        this.slabBlock = LOTRMod.slabSingle15;
        this.slabMeta = 1;
        this.carvedBrickBlock = LOTRMod.brick7;
        this.carvedBrickMeta = 5;
        this.wallBlock = LOTRMod.wallStoneV;
        this.wallMeta = 12;
        
        this.stairBlock = LOTRMod.stairsDarkwoodBrick1;
        this.torchBlock = Blocks.torch;
        this.tableBlock = LOTRMod.darkwood_table;
        this.barsBlock = LOTRMod.highElfBars;
        this.woodBarsBlock = LOTRMod.highElfWoodBars;
        this.roofBlock = LOTRMod.clayTileDyed;
        this.roofMeta = 7;
        this.roofStairBlock = LOTRMod.stairsClayTileDyedGray;
    }

    @Override
    protected LOTREntityElf getElf(World world) {
        return new LOTREntityDarkWoodfSmith(world);
    }
}
