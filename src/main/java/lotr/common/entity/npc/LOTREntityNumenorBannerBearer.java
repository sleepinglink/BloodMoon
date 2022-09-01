package lotr.common.entity.npc;

import lotr.common.item.LOTRItemBanner;
import net.minecraft.world.World;

public class LOTREntityNumenorBannerBearer extends LOTREntityNumenorSoldier implements LOTRBannerBearer {
    public LOTREntityNumenorBannerBearer(World world) {
        super(world);
    }

    @Override
    public LOTRItemBanner.BannerType getBannerType() {
        return LOTRItemBanner.BannerType.NUMENOR;
    }
}