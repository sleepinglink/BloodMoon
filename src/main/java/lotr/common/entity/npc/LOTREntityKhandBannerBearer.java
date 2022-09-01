package lotr.common.entity.npc;

import lotr.common.item.LOTRItemBanner;
import net.minecraft.world.World;

public class LOTREntityKhandBannerBearer extends LOTREntityKhandSoldier implements LOTRBannerBearer {
    public LOTREntityKhandBannerBearer(World world) {
        super(world);
    }

    @Override
    public LOTRItemBanner.BannerType getBannerType() {
        return LOTRItemBanner.BannerType.KHAND;
    }
}