package lotr.common.entity.npc;

import lotr.common.item.LOTRItemBanner;
import net.minecraft.world.World;

public class LOTREntityWindDwarfBannerBearer extends LOTREntityWindDwarfWarrior implements LOTRBannerBearer {
    public LOTREntityWindDwarfBannerBearer(World world) {
        super(world);
    }

    @Override
    public LOTRItemBanner.BannerType getBannerType() {
        return LOTRItemBanner.BannerType.WIND_MOUNTAINS;
    }
}

