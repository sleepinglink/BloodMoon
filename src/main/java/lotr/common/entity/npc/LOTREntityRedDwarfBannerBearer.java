package lotr.common.entity.npc;

import lotr.common.item.LOTRItemBanner;
import net.minecraft.world.World;

public class LOTREntityRedDwarfBannerBearer extends LOTREntityRedDwarfWarrior implements LOTRBannerBearer {
    public LOTREntityRedDwarfBannerBearer(World world) {
        super(world);
    }

    @Override
    public LOTRItemBanner.BannerType getBannerType() {
        return LOTRItemBanner.BannerType.RED_MOUNTAINS;
    }
}
