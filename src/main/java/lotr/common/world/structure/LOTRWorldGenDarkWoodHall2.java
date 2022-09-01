package lotr.common.world.structure;

import java.util.Random;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.*;
import lotr.common.item.LOTRItemBanner;
import net.minecraft.world.World;

public class LOTRWorldGenDarkWoodHall2 extends LOTRWorldGenDarkWoodHall {
    public LOTRWorldGenDarkWoodHall2(boolean flag) {
        super(flag);
        this.tableBlock = LOTRMod.darkwood_table;
        this.bannerType = LOTRItemBanner.BannerType.DARK_WOOD;
        this.chestContents = LOTRChestContents2.DARKWOOD_HALL;
    }

    @Override
    protected LOTREntityDarkWoodElf createElf(World world) {
        return new LOTREntityDarkWoodElf(world);
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k) {
        if(super.generate(world, random, i, j, k)) {
            LOTREntityDarkWoodCommander elfLord = new LOTREntityDarkWoodCommander(world);
            elfLord.setLocationAndAngles(i + 6, j + 6, k + 6, 0.0f, 0.0f);
            elfLord.spawnRidingHorse = false;
            ((LOTREntityNPC) elfLord).onSpawnWithEgg(null);
            elfLord.isNPCPersistent = true;
            world.spawnEntityInWorld(elfLord);
            elfLord.setHomeArea(i + 7, j + 3, k + 7, 16);
        }
        return false;
    }
}
