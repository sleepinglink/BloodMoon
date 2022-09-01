package lotr.common.entity.npc;

import lotr.common.item.LOTRItemBanner;
import net.minecraft.world.World;

public class LOTREntityDarkWoodBannerBearer extends LOTREntityDarkWoodSoldierElf implements LOTRBannerBearer {
    public LOTREntityDarkWoodBannerBearer(World world) {
        super(world);
    }

    @Override
    public LOTRItemBanner.BannerType getBannerType() {
        return LOTRItemBanner.BannerType.DARK_WOOD;
    }
}