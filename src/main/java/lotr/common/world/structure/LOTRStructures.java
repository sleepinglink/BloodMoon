package lotr.common.world.structure;

import java.util.HashMap;
import java.util.LinkedHashMap;
import cpw.mods.fml.common.FMLLog;
import lotr.common.LOTRConfig;
import lotr.common.world.biome.LOTRBiome;
import lotr.common.world.mapgen.dwarvenmine.LOTRMapGenDwarvenMine;
import lotr.common.world.mapgen.tpyr.LOTRMapGenTauredainPyramid;
import lotr.common.world.structure2.*;
import lotr.common.world.village.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LOTRStructures {
    private static HashMap<Integer, IStructureProvider> idToClassMapping = new HashMap();
    private static HashMap<Integer, String> idToStringMapping = new HashMap();
    public static HashMap<Integer, StructureColorInfo> structureItemSpawners = new LinkedHashMap<Integer, StructureColorInfo>();

    private static void registerStructure(int id, IStructureProvider str, String name, int colorBG, int colorFG, boolean hide) {
        idToClassMapping.put(id, str);
        idToStringMapping.put(id, name);
        structureItemSpawners.put(id, new StructureColorInfo(id, colorBG, colorFG, str.isVillage(), hide));
    }

    public static IStructureProvider getStructureForID(int ID) {
        return idToClassMapping.get(ID);
    }

    public static String getNameFromID(int ID) {
        return idToStringMapping.get(ID);
    }

    public static void registerStructures() {
        LOTRStructures.registerStructure(1, LOTRWorldGenHobbitHole.class, "HobbitHole", 2727977, 8997164);
        LOTRStructures.registerStructure(2, LOTRWorldGenHobbitTavern.class, "HobbitTavern", 9324081, 15975807);
        LOTRStructures.registerStructure(3, LOTRWorldGenHobbitPicnicBench.class, "HobbitPicnicBench", 7032622, 13882323);
        LOTRStructures.registerStructure(4, LOTRWorldGenHobbitWindmill.class, "HobbitWindmill", 9324081, 15975807);
        LOTRStructures.registerStructure(5, LOTRWorldGenHobbitFarm.class, "HobbitFarm", 9324081, 15975807);
        LOTRStructures.registerStructure(6, LOTRWorldGenHayBales.class, "HayBale", 14863437, 11499334);
        LOTRStructures.registerStructure(7, LOTRWorldGenHobbitHouse.class, "HobbitHouse", 9324081, 15975807);
        LOTRStructures.registerStructure(20, LOTRWorldGenBlueMountainsHouse.class, "BlueMountainsHouse", 10397380, 7633815);
        LOTRStructures.registerStructure(21, LOTRWorldGenBlueMountainsStronghold.class, "BlueMountainsStronghold", 10397380, 7633815);
        LOTRStructures.registerStructure(22, LOTRWorldGenBlueMountainsSmithy.class, "BlueMountainsSmithy", 10397380, 7633815);
        LOTRStructures.registerStructure(30, LOTRWorldGenHighElvenTurret.class, "HighElvenTurret", 13419962, 11380637);
        LOTRStructures.registerStructure(31, LOTRWorldGenRuinedHighElvenTurret.class, "RuinedHighElvenTurret", 13419962, 11380637);
        LOTRStructures.registerStructure(32, LOTRWorldGenHighElvenHall.class, "HighElvenHall", 13419962, 11380637);
        LOTRStructures.registerStructure(33, LOTRWorldGenUnderwaterElvenRuin.class, "UnderwaterElvenRuin", 13419962, 11380637);
        LOTRStructures.registerStructure(34, LOTRWorldGenHighElvenForge.class, "HighElvenForge", 13419962, 11380637);
        LOTRStructures.registerStructure(35, LOTRWorldGenRuinedEregionForge.class, "RuinedEregionForge", 13419962, 11380637);
        LOTRStructures.registerStructure(36, LOTRWorldGenHighElvenTower.class, "HighElvenTower", 13419962, 11380637);
        LOTRStructures.registerStructure(37, LOTRWorldGenTowerHillsTower.class, "TowerHillsTower", 16250346, 14211019);
        LOTRStructures.registerStructure(38, LOTRWorldGenHighElfHouse.class, "HighElfHouse", 13419962, 11380637);
        LOTRStructures.registerStructure(39, LOTRWorldGenRivendellHouse.class, "RivendellHouse", 13419962, 11380637);
        LOTRStructures.registerStructure(40, LOTRWorldGenRivendellHall.class, "RivendellHall", 13419962, 11380637);
        LOTRStructures.registerStructure(41, LOTRWorldGenRivendellForge.class, "RivendellForge", 13419962, 11380637);
        LOTRStructures.registerStructure(50, LOTRWorldGenRuinedDunedainTower.class, "RuinedDunedainTower", 8947848, 6052956);
        LOTRStructures.registerStructure(51, LOTRWorldGenRuinedHouse.class, "RuinedHouse", 8355197, 6838845);
        LOTRStructures.registerStructure(52, LOTRWorldGenRangerTent.class, "RangerTent", 3755037, 4142111);
        LOTRStructures.registerStructure(53, LOTRWorldGenNumenorRuin.class, "NumenorRuin", 8947848, 6052956);
        LOTRStructures.registerStructure(54, LOTRWorldGenBDBarrow.class, "BDBarrow", 6586202, 6505786);
        LOTRStructures.registerStructure(55, LOTRWorldGenRangerWatchtower.class, "RangerWatchtower", 5982252, 13411436);
        LOTRStructures.registerStructure(56, LOTRWorldGenBurntHouse.class, "BurntHouse", 1117449, 3288357);
        LOTRStructures.registerStructure(57, LOTRWorldGenRottenHouse.class, "RottenHouse", 3026204, 5854007);
        LOTRStructures.registerStructure(58, LOTRWorldGenRangerHouse.class, "RangerHouse", 5982252, 13411436);
        LOTRStructures.registerStructure(59, LOTRWorldGenRangerLodge.class, "RangerLodge", 5982252, 13411436);
        LOTRStructures.registerStructure(60, LOTRWorldGenRangerStables.class, "RangerStables", 5982252, 13411436);
        LOTRStructures.registerStructure(61, LOTRWorldGenRangerSmithy.class, "RangerSmithy", 5982252, 13411436);
        LOTRStructures.registerStructure(62, LOTRWorldGenRangerWell.class, "RangerWell", 5982252, 13411436);
        LOTRStructures.registerStructure(63, LOTRWorldGenRangerVillageLight.class, "RangerVillageLight", 5982252, 13411436);
        LOTRStructures.registerVillage(64, new LOTRVillageGenDunedain(LOTRBiome.angle, 1.0f), "DunedainVillage", 5982252, 13411436, new IVillageProperties<LOTRVillageGenDunedain.Instance>() {

            @Override
            public void apply(LOTRVillageGenDunedain.Instance instance) {
                instance.villageType = LOTRVillageGenDunedain.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerStructure(65, LOTRWorldGenRangerCamp.class, "RangerCamp", 3755037, 4142111);
        LOTRStructures.registerStructure(80, LOTRWorldGenOrcDungeon.class, "OrcDungeon", 8947848, 6052956);
        LOTRStructures.registerStructure(81, LOTRWorldGenGundabadTent.class, "GundabadTent", 2301210, 131586);
        LOTRStructures.registerStructure(82, LOTRWorldGenGundabadForgeTent.class, "GundabadForgeTent", 2301210, 131586);
        LOTRStructures.registerStructure(83, LOTRWorldGenGundabadCamp.class, "GundabadCamp", 2301210, 131586);
        LOTRStructures.registerStructure(100, LOTRWorldGenAngmarTower.class, "AngmarTower", 3815994, 1644825);
        LOTRStructures.registerStructure(101, LOTRWorldGenAngmarShrine.class, "AngmarShrine", 3815994, 1644825);
        LOTRStructures.registerStructure(102, LOTRWorldGenAngmarWargPit.class, "AngmarWargPit", 3815994, 1644825);
        LOTRStructures.registerStructure(103, LOTRWorldGenAngmarTent.class, "AngmarTent", 2301210, 131586);
        LOTRStructures.registerStructure(104, LOTRWorldGenAngmarForgeTent.class, "AngmarForgeTent", 3815994, 1644825);
        LOTRStructures.registerStructure(105, LOTRWorldGenAngmarCamp.class, "AngmarCamp", 2301210, 131586);
        LOTRStructures.registerStructure(110, LOTRWorldGenAngmarHillmanHouse.class, "AngmarHillmanHouse", 6705465, 3813154);
        LOTRStructures.registerStructure(111, LOTRWorldGenAngmarHillmanChieftainHouse.class, "AngmarHillmanChieftainHouse", 6705465, 3813154);
        LOTRStructures.registerStructure(112, LOTRWorldGenRhudaurCastle.class, "RhudaurCastle", 3815994, 1644825);
        LOTRStructures.registerStructure(120, LOTRWorldGenWoodElfPlatform.class, "WoodElfLookoutPlatform", 2498840, 4932405);
        LOTRStructures.registerStructure(121, LOTRWorldGenWoodElfHouse.class, "WoodElfHouse", 2498840, 1004574);
        LOTRStructures.registerStructure(122, LOTRWorldGenWoodElfTower.class, "WoodElfTower", 12692892, 9733494);
        LOTRStructures.registerStructure(123, LOTRWorldGenRuinedWoodElfTower.class, "RuinedWoodElfTower", 12692892, 9733494);
        LOTRStructures.registerStructure(124, LOTRWorldGenWoodElvenForge.class, "WoodElvenForge", 12692892, 9733494);
        LOTRStructures.registerStructure(130, LOTRWorldGenDolGuldurAltar.class, "DolGuldurAltar", 4408654, 2040101);
        LOTRStructures.registerStructure(131, LOTRWorldGenDolGuldurTower.class, "DolGuldurTower", 4408654, 2040101);
        LOTRStructures.registerStructure(132, LOTRWorldGenDolGuldurSpiderPit.class, "DolGuldurSpiderPit", 4408654, 2040101);
        LOTRStructures.registerStructure(133, LOTRWorldGenDolGuldurTent.class, "DolGuldurTent", 2301210, 131586);
        LOTRStructures.registerStructure(134, LOTRWorldGenDolGuldurForgeTent.class, "DolGuldurForgeTent", 4408654, 2040101);
        LOTRStructures.registerStructure(135, LOTRWorldGenDolGuldurCamp.class, "DolGuldurCamp", 2301210, 131586);
        LOTRStructures.registerStructure(140, LOTRWorldGenDaleWatchtower.class, "DaleWatchtower", 13278568, 6836795);
        LOTRStructures.registerStructure(141, LOTRWorldGenDaleFortress.class, "DaleFortress", 13278568, 6836795);
        LOTRStructures.registerStructure(142, LOTRWorldGenDaleHouse.class, "DaleHouse", 13278568, 6836795);
        LOTRStructures.registerStructure(143, LOTRWorldGenDaleSmithy.class, "DaleSmithy", 13278568, 6836795);
        LOTRStructures.registerStructure(144, LOTRWorldGenDaleVillageTower.class, "DaleVillageTower", 13278568, 6836795);
        LOTRStructures.registerStructure(145, LOTRWorldGenDaleBakery.class, "DaleBakery", 13278568, 6836795);
        LOTRStructures.registerStructure(150, LOTRWorldGenDwarvenMineEntrance.class, "DwarvenMineEntrance", 4935761, 2961971);
        LOTRStructures.registerStructure(151, LOTRWorldGenDwarvenTower.class, "DwarvenTower", 4935761, 2961971);
        LOTRStructures.registerStructure(152, LOTRWorldGenDwarfHouse.class, "DwarfHouse", 4935761, 2961971);
        LOTRStructures.registerStructure(153, LOTRWorldGenDwarvenMineEntranceRuined.class, "DwarvenMineEntranceRuined", 4935761, 2961971);
        LOTRStructures.registerStructure(154, LOTRWorldGenDwarfSmithy.class, "DwarfSmithy", 4935761, 2961971);
        LOTRStructures.registerStructure(155, LOTRWorldGenRuinedDwarvenTower.class, "DwarvenTowerRuined", 4935761, 2961971);
        LOTRStructures.registerStructure(200, LOTRWorldGenElfHouse.class, "ElfHouse", 15325615, 2315809);
        LOTRStructures.registerStructure(201, LOTRWorldGenElfLordHouse.class, "ElfLordHouse", 15325615, 2315809);
        LOTRStructures.registerStructure(202, LOTRWorldGenGaladhrimForge.class, "GaladhrimForge", 14407118, 10854552);
        LOTRStructures.registerStructure(300, LOTRWorldGenMeadHall.class, "RohanMeadHall", 5982252, 13411436);
        LOTRStructures.registerStructure(301, LOTRWorldGenRohanWatchtower.class, "RohanWatchtower", 5982252, 13411436);
        LOTRStructures.registerStructure(302, LOTRWorldGenRohanBarrow.class, "RohanBarrow", 9016133, 16775901);
        LOTRStructures.registerStructure(303, LOTRWorldGenRohanFortress.class, "RohanFortress", 5982252, 13411436);
        LOTRStructures.registerStructure(304, LOTRWorldGenRohanHouse.class, "RohanHouse", 5982252, 13411436);
        LOTRStructures.registerStructure(305, LOTRWorldGenRohanSmithy.class, "RohanSmithy", 5982252, 13411436);
        LOTRStructures.registerStructure(306, LOTRWorldGenRohanVillageFarm.class, "RohanVillageFarm", 7648578, 8546111);
        LOTRStructures.registerStructure(307, LOTRWorldGenRohanStables.class, "RohanStables", 5982252, 13411436);
        LOTRStructures.registerStructure(308, LOTRWorldGenRohanBarn.class, "RohanBarn", 5982252, 13411436);
        LOTRStructures.registerStructure(309, LOTRWorldGenRohanWell.class, "RohanWell", 5982252, 13411436);
        LOTRStructures.registerStructure(310, LOTRWorldGenRohanVillageGarden.class, "RohanVillageGarden", 7648578, 8546111);
        LOTRStructures.registerStructure(311, LOTRWorldGenRohanMarketStall.Blacksmith.class, "RohanMarketBlacksmith", 2960684, 13411436);
        LOTRStructures.registerStructure(312, LOTRWorldGenRohanMarketStall.Farmer.class, "RohanMarketFarmer", 15066597, 13411436);
        LOTRStructures.registerStructure(313, LOTRWorldGenRohanMarketStall.Lumber.class, "RohanMarketLumber", 5981994, 13411436);
        LOTRStructures.registerStructure(314, LOTRWorldGenRohanMarketStall.Builder.class, "RohanMarketBuilder", 7693401, 13411436);
        LOTRStructures.registerStructure(315, LOTRWorldGenRohanMarketStall.Brewer.class, "RohanMarketBrewer", 13874218, 13411436);
        LOTRStructures.registerStructure(316, LOTRWorldGenRohanMarketStall.Butcher.class, "RohanMarketButcher", 16358066, 13411436);
        LOTRStructures.registerStructure(317, LOTRWorldGenRohanMarketStall.Fish.class, "RohanMarketFish", 9882879, 13411436);
        LOTRStructures.registerStructure(318, LOTRWorldGenRohanMarketStall.Baker.class, "RohanMarketBaker", 14725995, 13411436);
        LOTRStructures.registerStructure(319, LOTRWorldGenRohanMarketStall.Orcharder.class, "RohanMarketOrcharder", 9161006, 13411436);
        LOTRStructures.registerStructure(320, LOTRWorldGenRohanVillagePasture.class, "RohanVillagePasture", 7648578, 8546111);
        LOTRStructures.registerStructure(321, LOTRWorldGenRohanVillageSign.class, "RohanVillageSign", 5982252, 13411436);
        LOTRStructures.registerStructure(322, LOTRWorldGenRohanGatehouse.class, "RohanGatehouse", 5982252, 13411436);

        
        
        LOTRStructures.registerStructure(325, LOTRWorldGenRedMountainsHouse.class, "RedMountainsHouse", 5701632, 5701632);
        LOTRStructures.registerStructure(326, LOTRWorldGenRedMountainsSmithy.class, "RedMountainsSmithy", 5701632, 5701632);
        LOTRStructures.registerStructure(327, LOTRWorldGenRedMountainsStronghold.class, "RedMountainsStronghold", 5701632, 5701632);
        LOTRStructures.registerStructure(328, LOTRWorldGenWindMountainsHouse.class, "WindMountainsHouse", 13543523, 13543523);
        LOTRStructures.registerStructure(329, LOTRWorldGenWindMountainsSmithy.class, "WindMountainsSmithy", 13543523, 13543523);
        LOTRStructures.registerStructure(330, LOTRWorldGenWindMountainsStronghold.class, "WindMountainsStronghold", 13543523, 13543523);
        
        LOTRStructures.registerVillage(323, new LOTRVillageGenRohan(LOTRBiome.rohan, 1.0f), "RohanVillage", 5982252, 13411436, new IVillageProperties<LOTRVillageGenRohan.Instance>() {

            @Override
            public void apply(LOTRVillageGenRohan.Instance instance) {
                instance.villageType = LOTRVillageGenRohan.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(324, new LOTRVillageGenRohan(LOTRBiome.rohan, 1.0f), "RohanFortVillage", 5982252, 13411436, new IVillageProperties<LOTRVillageGenRohan.Instance>() {

            @Override
            public void apply(LOTRVillageGenRohan.Instance instance) {
                instance.villageType = LOTRVillageGenRohan.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(350, LOTRWorldGenUrukTent.class, "UrukTent", 2301210, 131586);
        LOTRStructures.registerStructure(351, LOTRWorldGenRuinedRohanWatchtower.class, "RuinedRohanWatchtower", 1117449, 3288357);
        LOTRStructures.registerStructure(352, LOTRWorldGenUrukForgeTent.class, "UrukForgeTent", 3682596, 2038547);
        LOTRStructures.registerStructure(353, LOTRWorldGenUrukWargPit.class, "UrukWargPit", 3682596, 2038547);
        LOTRStructures.registerStructure(354, LOTRWorldGenUrukCamp.class, "UrukCamp", 2301210, 131586);
        LOTRStructures.registerStructure(380, LOTRWorldGenDunlendingHouse.class, "DunlendingHouse", 6705465, 3813154);
        LOTRStructures.registerStructure(381, LOTRWorldGenDunlendingTavern.class, "DunlendingTavern", 6705465, 3813154);
        LOTRStructures.registerStructure(382, LOTRWorldGenDunlendingCampfire.class, "DunlendingCampfire", 9539472, 6837299);
        LOTRStructures.registerStructure(383, LOTRWorldGenDunlandHillFort.class, "DunlandHillFort", 6705465, 3813154);
        LOTRStructures.registerStructure(400, LOTRWorldGenBeaconTower.class, "BeaconTower", 14869218, 11513775);
        LOTRStructures.registerStructure(401, LOTRWorldGenGondorWatchfort.class, "GondorWatchfort", 14869218, 2367263);
        LOTRStructures.registerStructure(402, LOTRWorldGenGondorSmithy.class, "GondorSmithy", 14869218, 2367263);
        LOTRStructures.registerStructure(403, LOTRWorldGenGondorTurret.class, "GondorTurret", 14869218, 11513775);
        LOTRStructures.registerStructure(404, LOTRWorldGenIthilienHideout.class, "IthilienHideout", 8882055, 7365464);
        LOTRStructures.registerStructure(405, LOTRWorldGenGondorHouse.class, "GondorHouse", 14869218, 9861961);
        LOTRStructures.registerStructure(406, LOTRWorldGenGondorCottage.class, "GondorCottage", 14869218, 9861961);
        LOTRStructures.registerStructure(407, LOTRWorldGenGondorStoneHouse.class, "GondorStoneHouse", 14869218, 2367263);
        LOTRStructures.registerStructure(408, LOTRWorldGenGondorWatchtower.class, "GondorWatchtower", 14869218, 11513775);
        LOTRStructures.registerStructure(409, LOTRWorldGenGondorStables.class, "GondorStables", 14869218, 9861961);
        LOTRStructures.registerStructure(410, LOTRWorldGenGondorBarn.class, "GondorBarn", 14869218, 9861961);
        LOTRStructures.registerStructure(411, LOTRWorldGenGondorFortress.class, "GondorFortress", 14869218, 2367263);
        LOTRStructures.registerStructure(412, LOTRWorldGenGondorTavern.class, "GondorTavern", 14869218, 9861961);
        LOTRStructures.registerStructure(413, LOTRWorldGenGondorWell.class, "GondorWell", 14869218, 11513775);
        LOTRStructures.registerStructure(414, LOTRWorldGenGondorVillageFarm.Crops.class, "GondorFarmCrops", 7047232, 15066597);
        LOTRStructures.registerStructure(415, LOTRWorldGenGondorVillageFarm.Animals.class, "GondorFarmAnimals", 7047232, 15066597);
        LOTRStructures.registerStructure(416, LOTRWorldGenGondorVillageFarm.Tree.class, "GondorFarmTree", 7047232, 15066597);
        LOTRStructures.registerStructure(417, LOTRWorldGenGondorMarketStall.Greengrocer.class, "GondorMarketGreengrocer", 8567851, 9861961);
        LOTRStructures.registerStructure(418, LOTRWorldGenGondorMarketStall.Lumber.class, "GondorMarketLumber", 5981994, 9861961);
        LOTRStructures.registerStructure(419, LOTRWorldGenGondorMarketStall.Mason.class, "GondorMarketMason", 10526621, 9861961);
        LOTRStructures.registerStructure(420, LOTRWorldGenGondorMarketStall.Brewer.class, "GondorMarketBrewer", 13874218, 9861961);
        LOTRStructures.registerStructure(421, LOTRWorldGenGondorMarketStall.Flowers.class, "GondorMarketFlowers", 16243515, 9861961);
        LOTRStructures.registerStructure(422, LOTRWorldGenGondorMarketStall.Butcher.class, "GondorMarketButcher", 14521508, 9861961);
        LOTRStructures.registerStructure(423, LOTRWorldGenGondorMarketStall.Fish.class, "GondorMarketFish", 6862591, 9861961);
        LOTRStructures.registerStructure(424, LOTRWorldGenGondorMarketStall.Farmer.class, "GondorMarketFarmer", 14401433, 9861961);
        LOTRStructures.registerStructure(425, LOTRWorldGenGondorMarketStall.Blacksmith.class, "GondorMarketBlacksmith", 2960684, 9861961);
        LOTRStructures.registerStructure(426, LOTRWorldGenGondorMarketStall.Baker.class, "GondorMarketBaker", 13543009, 9861961);
        LOTRStructures.registerStructure(427, LOTRWorldGenGondorVillageSign.class, "GondorVillageSign", 5982252, 13411436);
        LOTRStructures.registerStructure(428, LOTRWorldGenGondorBath.class, "GondorBath", 14869218, 2367263);
        LOTRStructures.registerStructure(429, LOTRWorldGenGondorGatehouse.class, "GondorGatehouse", 14869218, 2367263);
        LOTRStructures.registerStructure(430, LOTRWorldGenGondorLampPost.class, "GondorLampPost", 14869218, 11513775);
        LOTRStructures.registerStructure(431, LOTRWorldGenGondorTownGarden.class, "GondorTownGarden", 7047232, 15066597);
        LOTRStructures.registerStructure(432, LOTRWorldGenGondorTownTrees.class, "GondorTownTrees", 7047232, 15066597);
        LOTRStructures.registerStructure(433, LOTRWorldGenGondorTownBench.class, "GondorTownBench", 14869218, 11513775);
        LOTRStructures.registerVillage(434, new LOTRVillageGenGondor(LOTRBiome.gondor, LOTRWorldGenGondorStructure.GondorFiefdom.GONDOR, 1.0f), "GondorVillage", 14869218, 2367263, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(435, new LOTRVillageGenGondor(LOTRBiome.gondor, LOTRWorldGenGondorStructure.GondorFiefdom.GONDOR, 1.0f), "GondorTown", 14869218, 2367263, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(436, new LOTRVillageGenGondor(LOTRBiome.gondor, LOTRWorldGenGondorStructure.GondorFiefdom.GONDOR, 1.0f), "GondorFortVillage", 14869218, 2367263, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(450, LOTRWorldGenRuinedBeaconTower.class, "RuinedBeaconTower", 14869218, 11513775);
        LOTRStructures.registerStructure(451, LOTRWorldGenRuinedGondorTower.class, "RuinedGondorTower", 14869218, 11513775);
        LOTRStructures.registerStructure(452, LOTRWorldGenGondorObelisk.class, "GondorObelisk", 14869218, 11513775);
        LOTRStructures.registerStructure(453, LOTRWorldGenGondorRuin.class, "GondorRuin", 14869218, 11513775);
        LOTRStructures.registerStructure(500, LOTRWorldGenDolAmrothStables.class, "DolAmrothStables", 15002613, 2709918);
        LOTRStructures.registerStructure(501, LOTRWorldGenDolAmrothWatchtower.class, "DolAmrothWatchtower", 14869218, 11513775);
        LOTRStructures.registerStructure(502, LOTRWorldGenDolAmrothWatchfort.class, "DolAmrothWatchfort", 15002613, 2709918);
        LOTRStructures.registerVillage(503, new LOTRVillageGenGondor(LOTRBiome.dorEnErnil, LOTRWorldGenGondorStructure.GondorFiefdom.DOL_AMROTH, 1.0f), "DolAmrothVillage", 15002613, 2709918, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(504, new LOTRVillageGenGondor(LOTRBiome.dorEnErnil, LOTRWorldGenGondorStructure.GondorFiefdom.DOL_AMROTH, 1.0f), "DolAmrothTown", 15002613, 2709918, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(505, new LOTRVillageGenGondor(LOTRBiome.dorEnErnil, LOTRWorldGenGondorStructure.GondorFiefdom.DOL_AMROTH, 1.0f), "DolAmrothFortVillage", 15002613, 2709918, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(510, LOTRWorldGenLossarnachFortress.class, "LossarnachFortress", 14869218, 15138816);
        LOTRStructures.registerStructure(511, LOTRWorldGenLossarnachWatchtower.class, "LossarnachWatchtower", 14869218, 11513775);
        LOTRStructures.registerStructure(512, LOTRWorldGenLossarnachWatchfort.class, "LossarnachWatchfort", 14869218, 15138816);
        LOTRStructures.registerVillage(513, new LOTRVillageGenGondor(LOTRBiome.lossarnach, LOTRWorldGenGondorStructure.GondorFiefdom.LOSSARNACH, 1.0f), "LossarnachVillage", 14869218, 15138816, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(514, new LOTRVillageGenGondor(LOTRBiome.lossarnach, LOTRWorldGenGondorStructure.GondorFiefdom.LOSSARNACH, 1.0f), "LossarnachTown", 14869218, 15138816, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(515, new LOTRVillageGenGondor(LOTRBiome.lossarnach, LOTRWorldGenGondorStructure.GondorFiefdom.LOSSARNACH, 1.0f), "LossarnachFortVillage", 14869218, 15138816, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(520, LOTRWorldGenLebenninFortress.class, "LebenninFortress", 14869218, 621750);
        LOTRStructures.registerStructure(521, LOTRWorldGenLebenninWatchtower.class, "LebenninWatchtower", 14869218, 11513775);
        LOTRStructures.registerStructure(522, LOTRWorldGenLebenninWatchfort.class, "LebenninWatchfort", 14869218, 621750);
        LOTRStructures.registerVillage(523, new LOTRVillageGenGondor(LOTRBiome.lebennin, LOTRWorldGenGondorStructure.GondorFiefdom.LEBENNIN, 1.0f), "LebenninVillage", 14869218, 621750, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(524, new LOTRVillageGenGondor(LOTRBiome.lebennin, LOTRWorldGenGondorStructure.GondorFiefdom.LEBENNIN, 1.0f), "LebenninTown", 14869218, 621750, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(525, new LOTRVillageGenGondor(LOTRBiome.lebennin, LOTRWorldGenGondorStructure.GondorFiefdom.LEBENNIN, 1.0f), "LebenninFortVillage", 14869218, 621750, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(530, LOTRWorldGenPelargirFortress.class, "PelargirFortress", 14869218, 2917253);
        LOTRStructures.registerStructure(531, LOTRWorldGenPelargirWatchtower.class, "PelargirWatchtower", 14869218, 11513775);
        LOTRStructures.registerStructure(532, LOTRWorldGenPelargirWatchfort.class, "PelargirWatchfort", 14869218, 2917253);
        LOTRStructures.registerVillage(533, new LOTRVillageGenGondor(LOTRBiome.pelargir, LOTRWorldGenGondorStructure.GondorFiefdom.PELARGIR, 1.0f), "PelargirVillage", 14869218, 2917253, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(534, new LOTRVillageGenGondor(LOTRBiome.pelargir, LOTRWorldGenGondorStructure.GondorFiefdom.PELARGIR, 1.0f), "PelargirTown", 14869218, 2917253, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(535, new LOTRVillageGenGondor(LOTRBiome.pelargir, LOTRWorldGenGondorStructure.GondorFiefdom.PELARGIR, 1.0f), "PelargirFortVillage", 14869218, 2917253, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(540, LOTRWorldGenPinnathGelinFortress.class, "PinnathGelinFortress", 14869218, 1401651);
        LOTRStructures.registerStructure(541, LOTRWorldGenPinnathGelinWatchtower.class, "PinnathGelinWatchtower", 14869218, 11513775);
        LOTRStructures.registerStructure(542, LOTRWorldGenPinnathGelinWatchfort.class, "PinnathGelinWatchfort", 14869218, 1401651);
        LOTRStructures.registerVillage(543, new LOTRVillageGenGondor(LOTRBiome.pinnathGelin, LOTRWorldGenGondorStructure.GondorFiefdom.PINNATH_GELIN, 1.0f), "PinnathGelinVillage", 14869218, 1401651, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(544, new LOTRVillageGenGondor(LOTRBiome.pinnathGelin, LOTRWorldGenGondorStructure.GondorFiefdom.PINNATH_GELIN, 1.0f), "PinnathGelinTown", 14869218, 1401651, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(545, new LOTRVillageGenGondor(LOTRBiome.pinnathGelin, LOTRWorldGenGondorStructure.GondorFiefdom.PINNATH_GELIN, 1.0f), "PinnathGelinFortVillage", 14869218, 1401651, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(550, LOTRWorldGenBlackrootFortress.class, "BlackrootFortress", 14869218, 2367263);
        LOTRStructures.registerStructure(551, LOTRWorldGenBlackrootWatchtower.class, "BlackrootWatchtower", 14869218, 11513775);
        LOTRStructures.registerStructure(552, LOTRWorldGenBlackrootWatchfort.class, "BlackrootWatchfort", 14869218, 2367263);
        LOTRStructures.registerVillage(553, new LOTRVillageGenGondor(LOTRBiome.blackrootVale, LOTRWorldGenGondorStructure.GondorFiefdom.BLACKROOT_VALE, 1.0f), "BlackrootVillage", 14869218, 2367263, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(554, new LOTRVillageGenGondor(LOTRBiome.blackrootVale, LOTRWorldGenGondorStructure.GondorFiefdom.BLACKROOT_VALE, 1.0f), "BlackrootTown", 14869218, 2367263, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(555, new LOTRVillageGenGondor(LOTRBiome.blackrootVale, LOTRWorldGenGondorStructure.GondorFiefdom.BLACKROOT_VALE, 1.0f), "BlackrootFortVillage", 14869218, 2367263, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(560, LOTRWorldGenLamedonFortress.class, "LamedonFortress", 14869218, 1784649);
        LOTRStructures.registerStructure(561, LOTRWorldGenLamedonWatchtower.class, "LamedonWatchtower", 14869218, 11513775);
        LOTRStructures.registerStructure(562, LOTRWorldGenLamedonWatchfort.class, "LamedonWatchfort", 14869218, 1784649);
        LOTRStructures.registerVillage(563, new LOTRVillageGenGondor(LOTRBiome.lamedon, LOTRWorldGenGondorStructure.GondorFiefdom.LAMEDON, 1.0f), "LamedonVillage", 14869218, 1784649, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(564, new LOTRVillageGenGondor(LOTRBiome.lamedon, LOTRWorldGenGondorStructure.GondorFiefdom.LAMEDON, 1.0f), "LamedonTown", 14869218, 1784649, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(565, new LOTRVillageGenGondor(LOTRBiome.lamedon, LOTRWorldGenGondorStructure.GondorFiefdom.LAMEDON, 1.0f), "LamedonFortVillage", 14869218, 1784649, new IVillageProperties<LOTRVillageGenGondor.Instance>() {

            @Override
            public void apply(LOTRVillageGenGondor.Instance instance) {
                instance.villageType = LOTRVillageGenGondor.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(600, LOTRWorldGenMordorTower.class, "MordorTower", 2631720, 328965);
        LOTRStructures.registerStructure(601, LOTRWorldGenMordorTent.class, "MordorTent", 2301210, 131586);
        LOTRStructures.registerStructure(602, LOTRWorldGenMordorForgeTent.class, "MordorForgeTent", 2631720, 328965);
        LOTRStructures.registerStructure(603, LOTRWorldGenMordorWargPit.class, "MordorWargPit", 2631720, 328965);
        LOTRStructures.registerStructure(604, LOTRWorldGenMordorCamp.class, "MordorCamp", 2301210, 131586);
        LOTRStructures.registerStructure(605, LOTRWorldGenBlackUrukFort.class, "BlackUrukFort", 2631720, 328965);
        LOTRStructures.registerStructure(650, LOTRWorldGenNurnWheatFarm.class, "NurnWheatFarm", 4469796, 328965);
        LOTRStructures.registerStructure(651, LOTRWorldGenOrcSlaverTower.class, "OrcSlaverTower", 1117449, 3288357);
        LOTRStructures.registerStructure(670, LOTRWorldGenMordorSpiderPit.class, "MordorSpiderPit", 1511181, 12917534);
        LOTRStructures.registerStructure(700, LOTRWorldGenDorwinionGarden.class, "DorwinionGarden", 16572875, 13418417);
        LOTRStructures.registerStructure(701, LOTRWorldGenDorwinionTent.class, "DorwinionTent", 6706573, 15058766);
        LOTRStructures.registerStructure(702, LOTRWorldGenDorwinionCaptainTent.class, "DorwinionCaptainTent", 6706573, 15058766);
        LOTRStructures.registerStructure(703, LOTRWorldGenDorwinionHouse.class, "DorwinionHouse", 7167128, 15390149);
        LOTRStructures.registerStructure(704, LOTRWorldGenDorwinionBrewery.class, "DorwinionBrewery", 7167128, 15390149);
        LOTRStructures.registerStructure(705, LOTRWorldGenDorwinionElfHouse.class, "DorwinionElfHouse", 7167128, 15390149);
        LOTRStructures.registerStructure(706, LOTRWorldGenDorwinionBath.class, "DorwinionBath", 7167128, 15390149);
        LOTRStructures.registerStructure(750, LOTRWorldGenEasterlingHouse.class, "EasterlingHouse", 12693373, 7689786);
        LOTRStructures.registerStructure(751, LOTRWorldGenEasterlingStables.class, "EasterlingStables", 12693373, 7689786);
        LOTRStructures.registerStructure(752, LOTRWorldGenEasterlingTownHouse.class, "EasterlingTownHouse", 6304287, 12693373);
        LOTRStructures.registerStructure(753, LOTRWorldGenEasterlingLargeTownHouse.class, "EasterlingLargeTownHouse", 6304287, 12693373);
        LOTRStructures.registerStructure(754, LOTRWorldGenEasterlingFortress.class, "EasterlingFortress", 6304287, 12693373);
        LOTRStructures.registerStructure(755, LOTRWorldGenEasterlingTower.class, "EasterlingTower", 6304287, 12693373);
        LOTRStructures.registerStructure(756, LOTRWorldGenEasterlingSmithy.class, "EasterlingSmithy", 6304287, 12693373);
        LOTRStructures.registerStructure(757, LOTRWorldGenEasterlingMarketStall.Blacksmith.class, "EasterlingMarketBlacksmith", 2960684, 12693373);
        LOTRStructures.registerStructure(758, LOTRWorldGenEasterlingMarketStall.Lumber.class, "EasterlingMarketLumber", 5981994, 12693373);
        LOTRStructures.registerStructure(759, LOTRWorldGenEasterlingMarketStall.Mason.class, "EasterlingMarketMason", 7039594, 12693373);
        LOTRStructures.registerStructure(760, LOTRWorldGenEasterlingMarketStall.Butcher.class, "EasterlingMarketButcher", 12544103, 12693373);
        LOTRStructures.registerStructure(761, LOTRWorldGenEasterlingMarketStall.Brewer.class, "EasterlingMarketBrewer", 11891243, 12693373);
        LOTRStructures.registerStructure(762, LOTRWorldGenEasterlingMarketStall.Fish.class, "EasterlingMarketFish", 4882395, 12693373);
        LOTRStructures.registerStructure(763, LOTRWorldGenEasterlingMarketStall.Baker.class, "EasterlingMarketBaker", 14725995, 12693373);
        LOTRStructures.registerStructure(764, LOTRWorldGenEasterlingMarketStall.Hunter.class, "EasterlingMarketHunter", 4471854, 12693373);
        LOTRStructures.registerStructure(765, LOTRWorldGenEasterlingMarketStall.Farmer.class, "EasterlingMarketFarmer", 8893759, 12693373);
        LOTRStructures.registerStructure(766, LOTRWorldGenEasterlingMarketStall.Gold.class, "EasterlingMarketGold", 16237060, 12693373);
        LOTRStructures.registerStructure(767, LOTRWorldGenEasterlingTavern.class, "EasterlingTavern", 12693373, 7689786);
        LOTRStructures.registerStructure(768, LOTRWorldGenEasterlingTavernTown.class, "EasterlingTavernTown", 6304287, 12693373);
        LOTRStructures.registerStructure(769, LOTRWorldGenEasterlingStatue.class, "EasterlingStatue", 12693373, 7689786);
        LOTRStructures.registerStructure(770, LOTRWorldGenEasterlingGarden.class, "EasterlingGarden", 4030994, 12693373);
        LOTRStructures.registerStructure(771, LOTRWorldGenEasterlingVillageSign.class, "EasterlingVillageSign", 12693373, 7689786);
        LOTRStructures.registerStructure(772, LOTRWorldGenEasterlingWell.class, "EasterlingWell", 12693373, 7689786);
        LOTRStructures.registerStructure(773, LOTRWorldGenEasterlingVillageFarm.Crops.class, "EasterlingFarmCrops", 4030994, 12693373);
        LOTRStructures.registerStructure(774, LOTRWorldGenEasterlingVillageFarm.Animals.class, "EasterlingFarmAnimals", 4030994, 12693373);
        LOTRStructures.registerStructure(775, LOTRWorldGenEasterlingVillageFarm.Tree.class, "EasterlingFarmTree", 4030994, 12693373);
        LOTRStructures.registerStructure(776, LOTRWorldGenEasterlingGatehouse.class, "EasterlingGatehouse", 6304287, 12693373);
        LOTRStructures.registerStructure(777, LOTRWorldGenEasterlingLamp.class, "EasterlingLamp", 6304287, 12693373);
        LOTRStructures.registerVillage(778, new LOTRVillageGenRhun(LOTRBiome.rhunLand, 1.0f, true), "EasterlingVillage", 6304287, 12693373, new IVillageProperties<LOTRVillageGenRhun.Instance>() {

            @Override
            public void apply(LOTRVillageGenRhun.Instance instance) {
                instance.villageType = LOTRVillageGenRhun.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(779, new LOTRVillageGenRhun(LOTRBiome.rhunLand, 1.0f, true), "EasterlingTown", 6304287, 12693373, new IVillageProperties<LOTRVillageGenRhun.Instance>() {

            @Override
            public void apply(LOTRVillageGenRhun.Instance instance) {
                instance.villageType = LOTRVillageGenRhun.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(780, new LOTRVillageGenRhun(LOTRBiome.rhunLand, 1.0f, true), "EasterlingFortVillage", 6304287, 12693373, new IVillageProperties<LOTRVillageGenRhun.Instance>() {

            @Override
            public void apply(LOTRVillageGenRhun.Instance instance) {
                instance.villageType = LOTRVillageGenRhun.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(1000, LOTRWorldGenHaradObelisk.class, "HaradObelisk", 10854007, 15590575);
        LOTRStructures.registerStructure(1001, LOTRWorldGenHaradPyramid.class, "HaradPyramid", 10854007, 15590575);
        LOTRStructures.registerStructure(1002, LOTRWorldGenMumakSkeleton.class, "MumakSkeleton", 14737111, 16250349);
        LOTRStructures.registerStructure(1003, LOTRWorldGenHaradRuinedFort.class, "HaradRuinedFort", 10854007, 15590575);
        LOTRStructures.registerStructure(1050, LOTRWorldGenHarnedorHouse.class, "HarnedorHouse", 4994339, 12814421);
        LOTRStructures.registerStructure(1051, LOTRWorldGenHarnedorSmithy.class, "HarnedorSmithy", 4994339, 12814421);
        LOTRStructures.registerStructure(1052, LOTRWorldGenHarnedorTavern.class, "HarnedorTavern", 4994339, 12814421);
        LOTRStructures.registerStructure(1053, LOTRWorldGenHarnedorMarket.class, "HarnedorMarket", 4994339, 12814421);
        LOTRStructures.registerStructure(1054, LOTRWorldGenHarnedorTower.class, "HarnedorTower", 4994339, 12814421);
        LOTRStructures.registerStructure(1055, LOTRWorldGenHarnedorFort.class, "HarnedorFort", 4994339, 12814421);
        LOTRStructures.registerStructure(1056, LOTRWorldGenNearHaradTent.class, "NearHaradTent", 13519170, 1775897);
        LOTRStructures.registerStructure(1057, LOTRWorldGenHarnedorFarm.class, "HarnedorFarm", 10073953, 12814421);
        LOTRStructures.registerStructure(1058, LOTRWorldGenHarnedorPasture.class, "HarnedorPasture", 10073953, 12814421);
        LOTRStructures.registerVillage(1059, new LOTRVillageGenHarnedor(LOTRBiome.harnedor, 1.0f), "HarnedorVillage", 4994339, 12814421, new IVillageProperties<LOTRVillageGenHarnedor.Instance>() {

            @Override
            public void apply(LOTRVillageGenHarnedor.Instance instance) {
                instance.villageType = LOTRVillageGenHarnedor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerStructure(1060, LOTRWorldGenHarnedorStables.class, "HarnedorStables", 4994339, 12814421);
        LOTRStructures.registerStructure(1061, LOTRWorldGenHarnedorVillageSign.class, "HarnedorVillageSign", 4994339, 12814421);
        LOTRStructures.registerVillage(1062, new LOTRVillageGenHarnedor(LOTRBiome.harnedor, 1.0f), "HarnedorFortVillage", 4994339, 12814421, new IVillageProperties<LOTRVillageGenHarnedor.Instance>() {

            @Override
            public void apply(LOTRVillageGenHarnedor.Instance instance) {
                instance.villageType = LOTRVillageGenHarnedor.VillageType.FORTRESS;
            }
        });
        LOTRStructures.registerStructure(1080, LOTRWorldGenHarnedorHouseRuined.class, "HarnedorHouseRuined", 5519919, 10059372);
        LOTRStructures.registerStructure(1081, LOTRWorldGenHarnedorTavernRuined.class, "HarnedorTavernRuined", 5519919, 10059372);
        LOTRStructures.registerVillage(1082, new LOTRVillageGenHarnedor(LOTRBiome.harondor, 1.0f).setRuined(), "HarnedorVillageRuined", 5519919, 10059372, new IVillageProperties<LOTRVillageGenHarnedor.Instance>() {

            @Override
            public void apply(LOTRVillageGenHarnedor.Instance instance) {
                instance.villageType = LOTRVillageGenHarnedor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerStructure(1100, LOTRWorldGenSouthronHouse.class, "SouthronHouse", 15063989, 10052655);
        LOTRStructures.registerStructure(1101, LOTRWorldGenSouthronTavern.class, "SouthronTavern", 15063989, 10052655);
        LOTRStructures.registerStructure(1102, LOTRWorldGenSouthronSmithy.class, "SouthronSmithy", 15063989, 10052655);
        LOTRStructures.registerStructure(1103, LOTRWorldGenSouthronTower.class, "SouthronTower", 15063989, 10052655);
        LOTRStructures.registerStructure(1104, LOTRWorldGenSouthronMansion.class, "SouthronMansion", 15063989, 10052655);
        LOTRStructures.registerStructure(1105, LOTRWorldGenSouthronStables.class, "SouthronStables", 15063989, 10052655);
        LOTRStructures.registerStructure(1106, LOTRWorldGenSouthronFarm.class, "SouthronFarm", 9547581, 10052655);
        LOTRStructures.registerStructure(1107, LOTRWorldGenSouthronFortress.class, "SouthronFortress", 15063989, 10052655);
        LOTRStructures.registerStructure(1108, LOTRWorldGenSouthronWell.class, "SouthronWell", 15063989, 10052655);
        LOTRStructures.registerStructure(1109, LOTRWorldGenSouthronBazaar.class, "SouthronBazaar", 15063989, 10052655);
        LOTRStructures.registerStructure(1110, LOTRWorldGenSouthronPasture.class, "SouthronPasture", 9547581, 10052655);
        LOTRStructures.registerStructure(1111, LOTRWorldGenSouthronVillageSign.class, "SouthronVillageSign", 15063989, 10052655);
        LOTRStructures.registerVillage(1112, new LOTRVillageGenSouthron(LOTRBiome.nearHaradFertile, 1.0f), "SouthronVillage", 15063989, 10052655, new IVillageProperties<LOTRVillageGenSouthron.Instance>() {

            @Override
            public void apply(LOTRVillageGenSouthron.Instance instance) {
                instance.villageType = LOTRVillageGenSouthron.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerStructure(1113, LOTRWorldGenSouthronStatue.class, "SouthronStatue", 15063989, 10052655);
        LOTRStructures.registerStructure(1114, LOTRWorldGenSouthronBarracks.class, "SouthronBarracks", 15063989, 10052655);
        LOTRStructures.registerStructure(1115, LOTRWorldGenSouthronTraining.class, "SouthronTraining", 15063989, 10052655);
        LOTRStructures.registerStructure(1116, LOTRWorldGenSouthronFortGate.class, "SouthronFortGate", 15063989, 10052655);
        LOTRStructures.registerVillage(1117, new LOTRVillageGenSouthron(LOTRBiome.nearHaradFertile, 1.0f), "SouthronFortVillage", 15063989, 10052655, new IVillageProperties<LOTRVillageGenSouthron.Instance>() {

            @Override
            public void apply(LOTRVillageGenSouthron.Instance instance) {
                instance.villageType = LOTRVillageGenSouthron.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(1118, LOTRWorldGenSouthronLamp.class, "SouthronLamp", 15063989, 10052655);
        LOTRStructures.registerStructure(1119, LOTRWorldGenSouthronTownTree.class, "SouthronTownTree", 9547581, 10052655);
        LOTRStructures.registerStructure(1120, LOTRWorldGenSouthronTownFlowers.class, "SouthronTownFlowers", 9547581, 10052655);
        LOTRStructures.registerVillage(1121, new LOTRVillageGenSouthron(LOTRBiome.nearHaradFertile, 1.0f), "SouthronTown", 15063989, 10052655, new IVillageProperties<LOTRVillageGenSouthron.Instance>() {

            @Override
            public void apply(LOTRVillageGenSouthron.Instance instance) {
                instance.villageType = LOTRVillageGenSouthron.VillageType.TOWN;
            }
        });
        LOTRStructures.registerStructure(1122, LOTRWorldGenSouthronTownGate.class, "SouthronTownGate", 15063989, 10052655);
        LOTRStructures.registerStructure(1123, LOTRWorldGenSouthronTownCorner.class, "SouthronTownCorner", 15063989, 10052655);
        LOTRStructures.registerStructure(1140, LOTRWorldGenMoredainMercTent.class, "MoredainMercTent", 12845056, 2949120);
        LOTRStructures.registerStructure(1141, LOTRWorldGenMoredainMercCamp.class, "MoredainMercCamp", 12845056, 2949120);
        LOTRStructures.registerStructure(1150, LOTRWorldGenUmbarHouse.class, "UmbarHouse", 14407104, 3354926);
        LOTRStructures.registerStructure(1151, LOTRWorldGenUmbarTavern.class, "UmbarTavern", 14407104, 3354926);
        LOTRStructures.registerStructure(1152, LOTRWorldGenUmbarSmithy.class, "UmbarSmithy", 14407104, 3354926);
        LOTRStructures.registerStructure(1153, LOTRWorldGenUmbarTower.class, "UmbarTower", 14407104, 3354926);
        LOTRStructures.registerStructure(1154, LOTRWorldGenUmbarMansion.class, "UmbarMansion", 14407104, 3354926);
        LOTRStructures.registerStructure(1155, LOTRWorldGenUmbarStables.class, "UmbarStables", 14407104, 3354926);
        LOTRStructures.registerStructure(1156, LOTRWorldGenUmbarFarm.class, "UmbarFarm", 9547581, 3354926);
        LOTRStructures.registerStructure(1157, LOTRWorldGenUmbarFortress.class, "UmbarFortress", 14407104, 3354926);
        LOTRStructures.registerStructure(1158, LOTRWorldGenUmbarWell.class, "UmbarWell", 14407104, 3354926);
        LOTRStructures.registerStructure(1159, LOTRWorldGenUmbarBazaar.class, "UmbarBazaar", 14407104, 3354926);
        LOTRStructures.registerStructure(1160, LOTRWorldGenUmbarPasture.class, "UmbarPasture", 9547581, 3354926);
        LOTRStructures.registerStructure(1161, LOTRWorldGenUmbarVillageSign.class, "UmbarVillageSign", 14407104, 3354926);
        LOTRStructures.registerVillage(1162, new LOTRVillageGenUmbar(LOTRBiome.umbar, 1.0f), "UmbarVillage", 14407104, 3354926, new IVillageProperties<LOTRVillageGenUmbar.InstanceUmbar>() {

            @Override
            public void apply(LOTRVillageGenUmbar.InstanceUmbar instance) {
                instance.villageType = LOTRVillageGenSouthron.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerStructure(1163, LOTRWorldGenUmbarStatue.class, "UmbarStatue", 14407104, 3354926);
        LOTRStructures.registerStructure(1164, LOTRWorldGenUmbarBarracks.class, "UmbarBarracks", 14407104, 3354926);
        LOTRStructures.registerStructure(1165, LOTRWorldGenUmbarTraining.class, "UmbarTraining", 14407104, 3354926);
        LOTRStructures.registerStructure(1166, LOTRWorldGenUmbarFortGate.class, "UmbarFortGate", 14407104, 3354926);
        LOTRStructures.registerVillage(1167, new LOTRVillageGenUmbar(LOTRBiome.umbar, 1.0f), "UmbarFortVillage", 14407104, 3354926, new IVillageProperties<LOTRVillageGenSouthron.Instance>() {

            @Override
            public void apply(LOTRVillageGenSouthron.Instance instance) {
                instance.villageType = LOTRVillageGenSouthron.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(1168, LOTRWorldGenUmbarLamp.class, "UmbarLamp", 14407104, 3354926);
        LOTRStructures.registerStructure(1169, LOTRWorldGenUmbarTownTree.class, "UmbarTownTree", 9547581, 3354926);
        LOTRStructures.registerStructure(1170, LOTRWorldGenUmbarTownFlowers.class, "UmbarTownFlowers", 9547581, 3354926);
        LOTRStructures.registerVillage(1171, new LOTRVillageGenUmbar(LOTRBiome.umbar, 1.0f), "UmbarTown", 14407104, 3354926, new IVillageProperties<LOTRVillageGenSouthron.Instance>() {

            @Override
            public void apply(LOTRVillageGenSouthron.Instance instance) {
                instance.villageType = LOTRVillageGenSouthron.VillageType.TOWN;
            }
        });
        LOTRStructures.registerStructure(1172, LOTRWorldGenUmbarTownGate.class, "UmbarTownGate", 14407104, 3354926);
        LOTRStructures.registerStructure(1173, LOTRWorldGenUmbarTownCorner.class, "UmbarTownCorner", 14407104, 3354926);
        LOTRStructures.registerStructure(1180, LOTRWorldGenCorsairCove.class, "CorsairCove", 8355711, 1644825);
        LOTRStructures.registerStructure(1181, LOTRWorldGenCorsairTent.class, "CorsairTent", 5658198, 657930);
        LOTRStructures.registerStructure(1182, LOTRWorldGenCorsairCamp.class, "CorsairCamp", 5658198, 657930);
        LOTRStructures.registerStructure(1200, LOTRWorldGenNomadTent.class, "NomadTent", 16775927, 8345150);
        LOTRStructures.registerStructure(1201, LOTRWorldGenNomadTentLarge.class, "NomadTentLarge", 16775927, 8345150);
        LOTRStructures.registerStructure(1202, LOTRWorldGenNomadChieftainTent.class, "NomadChieftainTent", 16775927, 8345150);
        LOTRStructures.registerStructure(1203, LOTRWorldGenNomadWell.class, "NomadWell", 5478114, 15391151);
        LOTRStructures.registerVillage(1204, new LOTRVillageGenHaradNomad(LOTRBiome.nearHaradSemiDesert, 1.0f), "NomadVillageSmall", 16775927, 8345150, new IVillageProperties<LOTRVillageGenHaradNomad.Instance>() {

            @Override
            public void apply(LOTRVillageGenHaradNomad.Instance instance) {
                instance.villageType = LOTRVillageGenHaradNomad.VillageType.SMALL;
            }
        });
        LOTRStructures.registerVillage(1205, new LOTRVillageGenHaradNomad(LOTRBiome.nearHaradSemiDesert, 1.0f), "NomadVillageBig", 16775927, 8345150, new IVillageProperties<LOTRVillageGenHaradNomad.Instance>() {

            @Override
            public void apply(LOTRVillageGenHaradNomad.Instance instance) {
                instance.villageType = LOTRVillageGenHaradNomad.VillageType.BIG;
            }
        });
        LOTRStructures.registerStructure(1206, LOTRWorldGenNomadBazaarTent.class, "NomadBazaarTent", 16775927, 8345150);
        LOTRStructures.registerStructure(1250, LOTRWorldGenGulfWarCamp.class, "GulfWarCamp", 12849937, 4275226);
        LOTRStructures.registerStructure(1251, LOTRWorldGenGulfHouse.class, "GulfHouse", 9335899, 5654831);
        LOTRStructures.registerStructure(1252, LOTRWorldGenGulfAltar.class, "GulfAltar", 12849937, 4275226);
        LOTRStructures.registerStructure(1253, LOTRWorldGenGulfSmithy.class, "GulfSmithy", 9335899, 5654831);
        LOTRStructures.registerStructure(1254, LOTRWorldGenGulfBazaar.class, "GulfBazaar", 9335899, 5654831);
        LOTRStructures.registerStructure(1255, LOTRWorldGenGulfTotem.class, "GulfTotem", 12849937, 4275226);
        LOTRStructures.registerStructure(1256, LOTRWorldGenGulfPyramid.class, "GulfPyramid", 15721151, 12873038);
        LOTRStructures.registerStructure(1257, LOTRWorldGenGulfFarm.class, "GulfFarm", 9547581, 12849937);
        LOTRStructures.registerStructure(1258, LOTRWorldGenGulfTower.class, "GulfTower", 12849937, 4275226);
        LOTRStructures.registerStructure(1259, LOTRWorldGenGulfTavern.class, "GulfTavern", 9335899, 5654831);
        LOTRStructures.registerStructure(1260, LOTRWorldGenGulfVillageSign.class, "GulfVillageSign", 14737111, 16250349);
        LOTRStructures.registerStructure(1261, LOTRWorldGenGulfVillageLight.class, "GulfVillageLight", 14737111, 16250349);
        LOTRStructures.registerVillage(1262, new LOTRVillageGenGulfHarad(LOTRBiome.gulfHarad, 1.0f), "GulfVillage", 9335899, 5654831, new IVillageProperties<LOTRVillageGenGulfHarad.Instance>() {

            @Override
            public void apply(LOTRVillageGenGulfHarad.Instance instance) {
                instance.villageType = LOTRVillageGenGulfHarad.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerStructure(1263, LOTRWorldGenGulfPasture.class, "GulfPasture", 9547581, 12849937);
        LOTRStructures.registerVillage(1264, new LOTRVillageGenGulfHarad(LOTRBiome.gulfHarad, 1.0f), "GulfTown", 15721151, 12873038, new IVillageProperties<LOTRVillageGenGulfHarad.Instance>() {

            @Override
            public void apply(LOTRVillageGenGulfHarad.Instance instance) {
                instance.villageType = LOTRVillageGenGulfHarad.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(1265, new LOTRVillageGenGulfHarad(LOTRBiome.gulfHarad, 1.0f), "GulfFortVillage", 12849937, 4275226, new IVillageProperties<LOTRVillageGenGulfHarad.Instance>() {

            @Override
            public void apply(LOTRVillageGenGulfHarad.Instance instance) {
                instance.villageType = LOTRVillageGenGulfHarad.VillageType.FORT;
            }
        });
        LOTRStructures.registerStructure(1500, LOTRWorldGenMoredainHutVillage.class, "MoredainHutVillage", 8873812, 12891279);
        LOTRStructures.registerStructure(1501, LOTRWorldGenMoredainHutChieftain.class, "MoredainHutChieftain", 8873812, 12891279);
        LOTRStructures.registerStructure(1502, LOTRWorldGenMoredainHutTrader.class, "MoredainHutTrader", 8873812, 12891279);
        LOTRStructures.registerStructure(1503, LOTRWorldGenMoredainHutHunter.class, "MoredainHutHunter", 8873812, 12891279);
        LOTRStructures.registerStructure(1550, LOTRWorldGenTauredainPyramid.class, "TauredainPyramid", 6513746, 4803646);
        LOTRStructures.registerStructure(1551, LOTRWorldGenTauredainHouseSimple.class, "TauredainHouseSimple", 4796447, 8021303);
        LOTRStructures.registerStructure(1552, LOTRWorldGenTauredainHouseStilts.class, "TauredainHouseStilts", 4796447, 8021303);
        LOTRStructures.registerStructure(1553, LOTRWorldGenTauredainWatchtower.class, "TauredainWatchtower", 4796447, 8021303);
        LOTRStructures.registerStructure(1554, LOTRWorldGenTauredainHouseLarge.class, "TauredainHouseLarge", 4796447, 14593598);
        LOTRStructures.registerStructure(1555, LOTRWorldGenTauredainChieftainPyramid.class, "TauredainChieftainPyramid", 6513746, 4803646);
        LOTRStructures.registerStructure(1556, LOTRWorldGenTauredainVillageTree.class, "TauredainVillageTree", 9285414, 4796447);
        LOTRStructures.registerStructure(1557, LOTRWorldGenTauredainVillageFarm.class, "TauredainVillageFarm", 9285414, 4796447);
        
        LOTRStructures.registerVillage(1558, new LOTRVillageGenTauredain(LOTRBiome.tauredainClearing, 1.0f), "TauredainVillage", 6840658, 5979708, new IVillageProperties<LOTRVillageGenTauredain.Instance>() {

            @Override
            public void apply(LOTRVillageGenTauredain.Instance instance) {
            }
        });
        LOTRStructures.registerStructure(1559, LOTRWorldGenTauredainSmithy.class, "TauredainSmithy", 4796447, 8021303);
        LOTRStructures.registerStructure(1700, LOTRWorldGenHalfTrollHouse.class, "HalfTrollHouse", 10058344, 5325111);
        LOTRStructures.registerStructure(1701, LOTRWorldGenHalfTrollWarlordHouse.class, "HalfTrollWarlordHouse", 10058344, 5325111);
        LOTRStructures.registerStructure(1994, LOTRWorldGenTicketBooth.class, "TicketBooth", 15313961, 1118481, true);
        LOTRStructures.registerStructure(2000, LOTRWorldGenKhandHouse.class, "KhandHouse", 15313961, 1118481);
        LOTRStructures.registerStructure(2001, LOTRWorldGenKhandHorse.class, "KhandHorse", 15313961, 1118481);
        LOTRStructures.registerStructure(2002, LOTRWorldGenKhandForge.class, "KhandForge", 15313961, 1118481);
        LOTRStructures.registerStructure(2003, LOTRWorldGenDarkWoodForges.class, "DarkWoodForges", 6373034, 1118481);
        LOTRStructures.registerStructure(2004, LOTRWorldGenDarkWoodHouse.class, "DarkWoodHouse", 6373034, 1118481);
        LOTRStructures.registerStructure(2005, LOTRWorldGenDarkWoodHall2.class, "DarkWoodHall", 6373034, 1118481);
        LOTRStructures.registerStructure(2006, LOTRWorldGenNumenorWatchfort.class, "NumenorWatchfort", 14869218, 11513775);
        LOTRStructures.registerStructure(2007, LOTRWorldGenNumenorWatchtower.class, "NumenorWatchtower", 14869218, 11513775);
        LOTRStructures.registerStructure(2008, LOTRWorldGenNumenorFortress.class, "NumenorFortress", 14869218, 11513775);
        LOTRStructures.registerStructure(2009, LOTRWorldGenNumenorTavern.class, "NumenorTavern", 14869218, 11513775);
        LOTRStructures.registerStructure(2010, LOTRWorldGenNumenorSmithy.class, "NumenorSmithy", 14869218, 11513775);
        LOTRStructures.registerStructure(2011, LOTRWorldGenNumenorStoneHouse.class, "NumenorStoneHouse", 14869218, 11513775);
        LOTRStructures.registerStructure(2012, LOTRWorldGenNumenorCottage.class, "NumenorCottage", 14869218, 11513775);
        LOTRStructures.registerStructure(2013, LOTRWorldGenNumenorVillageFarm.class, "NumenorVillageFarm", 14869218, 11513775);
        LOTRStructures.registerStructure(2014, LOTRWorldGenNumenorGatehouse.class, "NumenorGatehouse", 14869218, 11513775);
        LOTRStructures.registerStructure(2015, LOTRWorldGenNumenorBarn.class, "NumenorBarn", 14869218, 11513775);
        LOTRStructures.registerStructure(2016, LOTRWorldGenNumenorVillageFarm.Crops.class, "NumenorFarmCrops", 7047232, 15066597);
        LOTRStructures.registerStructure(2017, LOTRWorldGenNumenorVillageFarm.Animals.class, "NumenorFarmAnimals", 7047232, 15066597);
        LOTRStructures.registerStructure(2018, LOTRWorldGenNumenorVillageFarm.Tree.class, "NumenorFarmTree", 7047232, 15066597);
        LOTRStructures.registerStructure(2019, LOTRWorldGenNumenorMarketStall.Greengrocer.class, "NumenorMarketGreengrocer", 8567851, 9861961);
        LOTRStructures.registerStructure(2020, LOTRWorldGenNumenorMarketStall.Lumber.class, "NumenorMarketLumber", 5981994, 9861961);
        LOTRStructures.registerStructure(2021, LOTRWorldGenNumenorMarketStall.Mason.class, "NumenorMarketMason", 10526621, 9861961);
        LOTRStructures.registerStructure(2022, LOTRWorldGenNumenorMarketStall.Brewer.class, "NumenorMarketBrewer", 13874218, 9861961);
        LOTRStructures.registerStructure(2023, LOTRWorldGenNumenorMarketStall.Flowers.class, "NumenorMarketFlowers", 16243515, 9861961);
        LOTRStructures.registerStructure(2024, LOTRWorldGenNumenorMarketStall.Butcher.class, "NumenorMarketButcher", 14521508, 9861961);
        LOTRStructures.registerStructure(2025, LOTRWorldGenNumenorMarketStall.Fish.class, "NumenorMarketFish", 6862591, 9861961);
        LOTRStructures.registerStructure(2026, LOTRWorldGenNumenorMarketStall.Farmer.class, "NumenorMarketFarmer", 14401433, 9861961);
        LOTRStructures.registerStructure(2027, LOTRWorldGenNumenorMarketStall.Blacksmith.class, "NumenorMarketBlacksmith", 2960684, 9861961);
        LOTRStructures.registerStructure(2028, LOTRWorldGenNumenorMarketStall.Baker.class, "NumenorMarketBaker", 13543009, 9861961);
        LOTRStructures.registerStructure(2029, LOTRWorldGenNumenorHouse.class, "NumenorHouse", 14869218, 11513775);
        LOTRStructures.registerStructure(2030, LOTRWorldGenNumenorBath.class, "NumenorBath", 14869218, 11513775);
        LOTRStructures.registerStructure(2031, LOTRWorldGenNumenorStables.class, "NumenorStables", 14869218, 11513775);

        
        LOTRStructures.registerVillage(2040, new LOTRVillageGenNumenor(LOTRBiome.menel, LOTRWorldGenNumenorStructure.GondorFiefdom.NUMENOR, 1.0f), "NumenorVillage", 14869218, 2367263, new IVillageProperties<LOTRVillageGenNumenor.Instance>() {

            @Override
            public void apply(LOTRVillageGenNumenor.Instance instance) {
                instance.villageType = LOTRVillageGenNumenor.VillageType.VILLAGE;
            }
        });
        LOTRStructures.registerVillage(2041, new LOTRVillageGenNumenor(LOTRBiome.menel, LOTRWorldGenNumenorStructure.GondorFiefdom.NUMENOR, 1.0f), "NumenorTown", 14869218, 2367263, new IVillageProperties<LOTRVillageGenNumenor.Instance>() {

            @Override
            public void apply(LOTRVillageGenNumenor.Instance instance) {
                instance.villageType = LOTRVillageGenNumenor.VillageType.TOWN;
            }
        });
        LOTRStructures.registerVillage(2042, new LOTRVillageGenNumenor(LOTRBiome.menel, LOTRWorldGenNumenorStructure.GondorFiefdom.NUMENOR, 1.0f), "NumenorFortVillage", 14869218, 2367263, new IVillageProperties<LOTRVillageGenNumenor.Instance>() {

            @Override
            public void apply(LOTRVillageGenNumenor.Instance instance) {
                instance.villageType = LOTRVillageGenNumenor.VillageType.FORT;
            }
        });
        
        LOTRMapGenDwarvenMine.register();
        LOTRMapGenTauredainPyramid.register();
    }

    private static void registerStructure(int id, Class<? extends WorldGenerator> strClass, String name, int colorBG, int colorFG) {
        LOTRStructures.registerStructure(id, strClass, name, colorBG, colorFG, false);
    }

    private static void registerStructure(int id, final Class<? extends WorldGenerator> strClass, String name, int colorBG, int colorFG, boolean hide) {
        IStructureProvider strProvider = new IStructureProvider() {

            @Override
            public boolean generateStructure(World world, EntityPlayer entityplayer, int i, int j, int k) {
                WorldGenerator generator = null;
                try {
                    generator = strClass.getConstructor(Boolean.TYPE).newInstance(true);
                }
                catch(Exception e) {
                    FMLLog.warning("Failed to build LOTR structure " + strClass.getName(), new Object[0]);
                    e.printStackTrace();
                }
                if(generator != null) {
                    boolean timelapse = LOTRConfig.strTimelapse;
                    if(generator instanceof LOTRWorldGenStructureBase2) {
                        LOTRWorldGenStructureBase2 strGen = (LOTRWorldGenStructureBase2) generator;
                        strGen.restrictions = false;
                        strGen.usingPlayer = entityplayer;
                        if(timelapse) {
                            LOTRStructureTimelapse.start(strGen, world, i, j, k);
                            return true;
                        }
                        return strGen.generateWithSetRotation(world, world.rand, i, j, k, strGen.usingPlayerRotation());
                    }
                    if(generator instanceof LOTRWorldGenStructureBase) {
                        LOTRWorldGenStructureBase strGen = (LOTRWorldGenStructureBase) generator;
                        strGen.restrictions = false;
                        strGen.usingPlayer = entityplayer;
                        if(timelapse) {
                            LOTRStructureTimelapse.start(strGen, world, i, j, k);
                            return true;
                        }
                        return strGen.generate(world, world.rand, i, j, k);
                    }
                }
                return false;
            }

            @Override
            public boolean isVillage() {
                return false;
            }
        };
        LOTRStructures.registerStructure(id, strProvider, name, colorBG, colorFG, hide);
    }

    private static void registerVillage(int id, final LOTRVillageGen village, String name, int colorBG, int colorFG, final IVillageProperties properties) {
        IStructureProvider strProvider = new IStructureProvider() {

            @Override
            public boolean generateStructure(World world, EntityPlayer entityplayer, int i, int j, int k) {
                LOTRVillageGen.AbstractInstance instance = village.createAndSetupVillageInstance(world, i, k, world.rand);
                instance.setRotation((LOTRStructures.getRotationFromPlayer(entityplayer) + 2) % 4);
                properties.apply(instance);
                village.generateCompleteVillageInstance(instance, world, i, k);
                return true;
            }

            @Override
            public boolean isVillage() {
                return true;
            }
        };
        LOTRStructures.registerStructure(id, strProvider, name, colorBG, colorFG, false);
    }

    public static int getRotationFromPlayer(EntityPlayer entityplayer) {
        return MathHelper.floor_double(entityplayer.rotationYaw * 4.0f / 360.0f + 0.5) & 3;
    }

    private static interface IVillageProperties<V> {
        public void apply(V var1);
    }

    public static class StructureColorInfo {
        public final int spawnedID;
        public final int colorBackground;
        public final int colorForeground;
        public final boolean isVillage;
        public final boolean isHidden;

        public StructureColorInfo(int i, int colorBG, int colorFG, boolean vill, boolean hide) {
            this.spawnedID = i;
            this.colorBackground = colorBG;
            this.colorForeground = colorFG;
            this.isVillage = vill;
            this.isHidden = hide;
        }
    }

    public static interface IStructureProvider {
        public boolean generateStructure(World var1, EntityPlayer var2, int var3, int var4, int var5);

        public boolean isVillage();
    }

}
