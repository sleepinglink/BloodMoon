package lotr.common.block;

import java.util.ArrayList;
import java.util.Random;
import lotr.common.LOTRMod;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LOTRBlockFruitLeaves extends LOTRBlockLeavesBase {
    public LOTRBlockFruitLeaves() {
        this.setLeafNames("apple", "pear", "cherry", "mango");
    }

    @Override
    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock(LOTRMod.fruitSapling);
    }

    @Override
    protected void addSpecialLeafDrops(ArrayList drops, World world, int i, int j, int k, int meta, int fortune) {
        int fruitChance;
        if((meta & 3) == 0 && world.rand.nextInt(fruitChance = this.calcFortuneModifiedDropChance(16, fortune)) == 0) {
            if(world.rand.nextBoolean()) {
                drops.add(new ItemStack(Items.apple));
            }
            else {
                drops.add(new ItemStack(LOTRMod.appleGreen));
            }
        }
        if((meta & 3) == 1 && world.rand.nextInt(fruitChance = this.calcFortuneModifiedDropChance(16, fortune)) == 0) {
            drops.add(new ItemStack(LOTRMod.pear));
        }
        if((meta & 3) == 2 && world.rand.nextInt(fruitChance = this.calcFortuneModifiedDropChance(8, fortune)) == 0) {
            drops.add(new ItemStack(LOTRMod.cherry));
        }
        if((meta & 3) == 3 && world.rand.nextInt(fruitChance = this.calcFortuneModifiedDropChance(16, fortune)) == 0) {
            drops.add(new ItemStack(LOTRMod.mango));
        }
    }
}
