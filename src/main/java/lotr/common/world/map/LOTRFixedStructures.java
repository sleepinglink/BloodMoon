package lotr.common.world.map;

import lotr.common.LOTRConfig;
import lotr.common.LOTRMod;
import net.minecraft.world.World;

public enum LOTRFixedStructures {
    SPAWN(809.5, 729.5), UTUMNO_ENTRANCE(1139.0, 394.0), MORDOR_CHERRY_TREE(1630.0, 1170.0);

    public int xCoord;
    public int zCoord;
    public static long nanoTimeElapsed;

    private LOTRFixedStructures(double x, double z) {
        this.xCoord = LOTRWaypoint.mapToWorldX(x);
        this.zCoord = LOTRWaypoint.mapToWorldZ(z);
    }

    public boolean isAt(int x, int z) {
        return this.xCoord == x && this.zCoord == z;
    }

    public static boolean generatesAt(int i, int k, int x, int z) {
        return i >> 4 == x >> 4 && k >> 4 == z >> 4;
    }

    public static boolean generatesAtMapImageCoords(int i, int k, int x, int z) {
        x = LOTRWaypoint.mapToWorldX(x);
        z = LOTRWaypoint.mapToWorldZ(z);
        return LOTRFixedStructures.generatesAt(i, k, x, z);
    }

    public static boolean[] _mountainNear_structureNear(World world, int x, int z) {
        long l = System.nanoTime();
        boolean mountainNear = false;
        boolean structureNear = false;
        if(LOTRFixedStructures.hasMapFeatures(world)) {
            if(LOTRMountains.mountainAt(x, z)) {
                mountainNear = true;
            }
            if(!(structureNear = LOTRFixedStructures.structureNear(world, x, z, 256))) {
                for(LOTRWaypoint wp : LOTRWaypoint.values()) {
                    double dz;
                    double rangeSq;
                    double range;
                    double dx = x - wp.getXCoord();
                    double distSq = dx * dx + (dz = z - wp.getZCoord()) * dz;
                    if(!(distSq < (rangeSq = (range = 256.0) * range))) continue;
                    structureNear = true;
                    break;
                }
            }
            if(!structureNear && LOTRRoads.isRoadNear(x, z, 32) >= 0.0f) {
                structureNear = true;
            }
        }
        nanoTimeElapsed += System.nanoTime() - l;
        return new boolean[] {mountainNear, structureNear};
    }

    public static boolean structureNear(World world, int x, int z, int range) {
        for(LOTRFixedStructures str : LOTRFixedStructures.values()) {
            double dx = x - str.xCoord;
            double dz = z - str.zCoord;
            double distSq = dx * dx + dz * dz;
            double rangeSq = range * range;
            if(!(distSq < rangeSq)) continue;
            return true;
        }
        return false;
    }

    public static boolean hasMapFeatures(World world) {
        if(!LOTRConfig.generateMapFeatures) {
            return false;
        }
        return world.getWorldInfo().getTerrainType() != LOTRMod.worldTypeMiddleEarthClassic;
    }
}
