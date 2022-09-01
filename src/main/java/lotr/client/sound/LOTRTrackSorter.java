package lotr.client.sound;

import java.util.ArrayList;
import java.util.List;
import lotr.common.world.biome.LOTRMusicRegion;

public class LOTRTrackSorter {
    public static List<LOTRMusicTrack> sortTracks(List<LOTRMusicTrack> tracks, Filter filter) {
        ArrayList<LOTRMusicTrack> sorted = new ArrayList<LOTRMusicTrack>();
        for(LOTRMusicTrack track : tracks) {
            if(!filter.accept(track)) continue;
            sorted.add(track);
        }
        return sorted;
    }

    public static Filter forRegionAndCategory(final LOTRMusicRegion reg, final LOTRMusicCategory cat) {
        return new Filter() {

            @Override
            public boolean accept(LOTRMusicTrack track) {
                return track.getRegionInfo(reg).getCategories().contains(cat);
            }
        };
    }

    public static Filter forAny() {
        return new Filter() {

            @Override
            public boolean accept(LOTRMusicTrack track) {
                return true;
            }
        };
    }

    public static interface Filter {
        public boolean accept(LOTRMusicTrack var1);
    }

}
