package lotr.common.block;

import java.util.ArrayList;
import java.util.Random;

import lotr.common.LOTRMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRBlockLeaves10 extends LOTRBlockLeavesBase {
    public LOTRBlockLeaves10() {
        this.setLeafNames("boarishnik", "jacaranda");
    }

    @Override
    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock(LOTRMod.sapling10);
    }

    @Override
    protected void addSpecialLeafDrops(ArrayList drops, World world, int i, int j, int k, int meta, int fortune) {
        int fruitChance;
        if((meta & 3) == 0 && world.rand.nextInt(fruitChance = this.calcFortuneModifiedDropChance(16, fortune)) == 0) {
            drops.add(new ItemStack(LOTRMod.boyarberry));
        }
    }
}
