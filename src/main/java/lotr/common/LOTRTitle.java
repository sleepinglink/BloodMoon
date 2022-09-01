package lotr.common;

import java.util.*;
import lotr.common.fac.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;

public class LOTRTitle implements Comparable<LOTRTitle> {
    public static List<LOTRTitle> allTitles = new ArrayList<LOTRTitle>();
    public static LOTRTitle adventurer;
    public static LOTRTitle rogue;
    public static LOTRTitle bartender;
    public static LOTRTitle gaffer;
    public static LOTRTitle scholar;
    public static LOTRTitle minstrel;
    public static LOTRTitle bard;
    public static LOTRTitle artisan;
    public static LOTRTitle warrior;
    public static LOTRTitle scourge;
    public static LOTRTitle wanderer;
    public static LOTRTitle peacekeeper;
    public static LOTRTitle warmongerer;
    public static LOTRTitle hunter;
    public static LOTRTitle raider;
    public static LOTRTitle explorer;
    public static LOTRTitle mercenary;
    public static LOTRTitle shipwright;
    public static LOTRTitle marksman;
    public static LOTRTitle merchant;
    public static LOTRTitle farmer;
    public static LOTRTitle burglar;
    public static LOTRTitle ruffian;
    public static LOTRTitle architect;
    public static LOTRTitle miner;
    public static LOTRTitle swordsman;
    public static LOTRTitle scout;
    public static LOTRTitle spearman;
    public static LOTRTitle rider;
    public static LOTRTitle watcher;
    public static LOTRTitle creator;
    public static LOTRTitle creator2;
    public static LOTRTitle moderator;
    public static LOTRTitle gruk;
    public static LOTRTitle boyd;
    public static LOTRTitle translator;
    public static LOTRTitle patron;
    public static LOTRTitle loremaster;
    public static LOTRTitle builder;
    public static LOTRTitle ANY_10000;
    public static LOTRTitle MULTI_freePeoples;
    public static LOTRTitle MULTI_elf;
    public static LOTRTitle MULTI_orc;
    public static LOTRTitle MULTI_snaga;
    public static LOTRTitle MULTI_dwarf;
    public static LOTRTitle MULTI_dunedain;
    public static LOTRTitle MULTI_moria;
    public static LOTRTitle MULTI_rhun;
    public static LOTRTitle MULTI_easterling;
    public static LOTRTitle MULTI_harad;
    public static LOTRTitle MULTI_haradrim;
    public static LOTRTitle MULTI_southron;
    public static LOTRTitle MULTI_farHarad;
    public static LOTRTitle MULTI_farHaradrim;
    public static LOTRTitle MULTI_silvanElf;
    public static LOTRTitle MULTI_sindar;
    public static LOTRTitle MULTI_greyElf;
    public static LOTRTitle MULTI_noldor;
    public static LOTRTitle MULTI_avari;
    public static LOTRTitle MULTI_sunlands;
    public static LOTRTitle MULTI_swerting;
    public static LOTRTitle MULTI_sutherland;
    public static LOTRTitle HOBBIT_hobbit;
    public static LOTRTitle HOBBIT_halfling;
    public static LOTRTitle HOBBIT_shire;
    public static LOTRTitle HOBBIT_hobbiton;
    public static LOTRTitle HOBBIT_buckland;
    public static LOTRTitle HOBBIT_tookland;
    public static LOTRTitle HOBBIT_bywater;
    public static LOTRTitle HOBBIT_longbottom;
    public static LOTRTitle HOBBIT_michelDelving;
    public static LOTRTitle HOBBIT_northfarthing;
    public static LOTRTitle HOBBIT_westfarthing;
    public static LOTRTitle HOBBIT_southfarthing;
    public static LOTRTitle HOBBIT_eastfarthing;
    public static LOTRTitle HOBBIT_mathomKeeper;
    public static LOTRTitle RANGER_ranger;
    public static LOTRTitle RANGER_northDunedain;
    public static LOTRTitle RANGER_arnor;
    public static LOTRTitle RANGER_annuminas;
    public static LOTRTitle RANGER_fornost;
    public static LOTRTitle BLUE_MOUNTAINS_blueDwarf;
    public static LOTRTitle BLUE_MOUNTAINS_blueMountains;
    public static LOTRTitle BLUE_MOUNTAINS_firebeard;
    public static LOTRTitle BLUE_MOUNTAINS_broadbeam;
    public static LOTRTitle BLUE_MOUNTAINS_belegost;
    public static LOTRTitle BLUE_MOUNTAINS_nogrod;
    public static LOTRTitle HIGH_ELF_highElf;
    public static LOTRTitle HIGH_ELF_lindon;
    public static LOTRTitle HIGH_ELF_mithlond;
    public static LOTRTitle HIGH_ELF_rivendell;
    public static LOTRTitle HIGH_ELF_eregion;
    public static LOTRTitle HIGH_ELF_forlindon;
    public static LOTRTitle HIGH_ELF_harlindon;
    public static LOTRTitle HIGH_ELF_imladris;
    public static LOTRTitle GUNDABAD_gundabad;
    public static LOTRTitle GUNDABAD_gundabadOrc;
    public static LOTRTitle GUNDABAD_moriaOrc;
    public static LOTRTitle GUNDABAD_goblin;
    public static LOTRTitle GUNDABAD_goblinTown;
    public static LOTRTitle GUNDABAD_mountGundabad;
    public static LOTRTitle GUNDABAD_mountGram;
    public static LOTRTitle GUNDABAD_gundabadUruk;
    public static LOTRTitle ANGMAR_angmar;
    public static LOTRTitle ANGMAR_angmarOrc;
    public static LOTRTitle ANGMAR_troll;
    public static LOTRTitle ANGMAR_carnDum;
    public static LOTRTitle ANGMAR_hillman;
    public static LOTRTitle ANGMAR_rhudaur;
    public static LOTRTitle ANGMAR_ettenmoors;
    public static LOTRTitle ANGMAR_coldfells;
    public static LOTRTitle WOOD_ELF_woodElf;
    public static LOTRTitle WOOD_ELF_woodlandRealm;
    public static LOTRTitle DOL_GULDUR_dolGuldur;
    public static LOTRTitle DOL_GULDUR_dolGuldurOrc;
    public static LOTRTitle DOL_GULDUR_necromancer;
    public static LOTRTitle DOL_GULDUR_sorcerer;
    public static LOTRTitle DOL_GULDUR_spiderRider;
    public static LOTRTitle DALE_dale;
    public static LOTRTitle DALE_northman;
    public static LOTRTitle DALE_barding;
    public static LOTRTitle DALE_esgaroth;
    public static LOTRTitle DWARF_durin;
    public static LOTRTitle DWARF_greyDwarf;
    public static LOTRTitle DWARF_ironHills;
    public static LOTRTitle DWARF_erebor;
    public static LOTRTitle DWARF_khazadDum;
    public static LOTRTitle GALADHRIM_galadhrim;
    public static LOTRTitle GALADHRIM_lothlorien;
    public static LOTRTitle GALADHRIM_carasGaladhon;
    public static LOTRTitle DUNLAND_dunland;
    public static LOTRTitle DUNLAND_dunlending;
    public static LOTRTitle DUNLAND_wildman;
    public static LOTRTitle DUNLAND_barbarian;
    public static LOTRTitle DUNLAND_adorn;
    public static LOTRTitle DUNLAND_berserker;
    public static LOTRTitle URUK_uruk;
    public static LOTRTitle URUK_urukHai;
    public static LOTRTitle URUK_isengard;
    public static LOTRTitle URUK_whiteHand;
    public static LOTRTitle FANGORN_fangorn;
    public static LOTRTitle FANGORN_ent;
    public static LOTRTitle ROHAN_rohan;
    public static LOTRTitle ROHAN_rohirrim;
    public static LOTRTitle ROHAN_eorlingas;
    public static LOTRTitle ROHAN_strawhead;
    public static LOTRTitle ROHAN_edoras;
    public static LOTRTitle ROHAN_helmsDeep;
    public static LOTRTitle ROHAN_grimslade;
    public static LOTRTitle ROHAN_aldburg;
    public static LOTRTitle ROHAN_westfold;
    public static LOTRTitle ROHAN_eastfold;
    public static LOTRTitle ROHAN_westemnet;
    public static LOTRTitle ROHAN_eastemnet;
    public static LOTRTitle ROHAN_wold;
    public static LOTRTitle ROHAN_shieldmaiden;
    public static LOTRTitle ROHAN_horselord;
    public static LOTRTitle GONDOR_gondor;
    public static LOTRTitle GONDOR_gondorian;
    public static LOTRTitle GONDOR_southDunedain;
    public static LOTRTitle GONDOR_dolAmroth;
    public static LOTRTitle GONDOR_swanKnight;
    public static LOTRTitle GONDOR_ithilien;
    public static LOTRTitle GONDOR_ithilienRanger;
    public static LOTRTitle GONDOR_minasTirith;
    public static LOTRTitle GONDOR_towerGuard;
    public static LOTRTitle GONDOR_osgiliath;
    public static LOTRTitle GONDOR_lebennin;
    public static LOTRTitle GONDOR_anorien;
    public static LOTRTitle GONDOR_lossarnach;
    public static LOTRTitle GONDOR_imlothMelui;
    public static LOTRTitle GONDOR_pelargir;
    public static LOTRTitle GONDOR_blackrootVale;
    public static LOTRTitle GONDOR_mornan;
    public static LOTRTitle GONDOR_pinnathGelin;
    public static LOTRTitle GONDOR_lamedon;
    public static LOTRTitle GONDOR_anfalas;
    public static LOTRTitle GONDOR_belfalas;
    public static LOTRTitle GONDOR_linhir;
    public static LOTRTitle GONDOR_edhellond;
    public static LOTRTitle GONDOR_tarnost;
    public static LOTRTitle GONDOR_calembel;
    public static LOTRTitle GONDOR_ethring;
    public static LOTRTitle GONDOR_erech;
    public static LOTRTitle GONDOR_ethirAnduin;
    public static LOTRTitle MORDOR_mordor;
    public static LOTRTitle MORDOR_mordorOrc;
    public static LOTRTitle MORDOR_blackUruk;
    public static LOTRTitle MORDOR_nurn;
    public static LOTRTitle MORDOR_baradDur;
    public static LOTRTitle MORDOR_morannon;
    public static LOTRTitle MORDOR_minasMorgul;
    public static LOTRTitle MORDOR_cirithUngol;
    public static LOTRTitle MORDOR_blackNumenorean;
    public static LOTRTitle MORDOR_nanUngol;
    public static LOTRTitle DORWINION_dorwinion;
    public static LOTRTitle DORWINION_vintner;
    public static LOTRTitle DORWINION_dorwinrim;
    public static LOTRTitle DORWINION_dorwinionElf;
    public static LOTRTitle DORWINION_bladorthin;
    public static LOTRTitle DORWINION_wineTaster;
    public static LOTRTitle RHUN_rhudel;
    public static LOTRTitle RHUN_rhunaer;
    public static LOTRTitle RHUN_rhunaerim;
    public static LOTRTitle NEAR_HARAD_nearHarad;
    public static LOTRTitle NEAR_HARAD_nearHaradrim;
    public static LOTRTitle NEAR_HARAD_umbar;
    public static LOTRTitle NEAR_HARAD_corsair;
    public static LOTRTitle NEAR_HARAD_harnedor;
    public static LOTRTitle NEAR_HARAD_ninzayan;
    public static LOTRTitle NEAR_HARAD_belkadar;
    public static LOTRTitle NEAR_HARAD_southronCoasts;
    public static LOTRTitle NEAR_HARAD_azrazain;
    public static LOTRTitle NEAR_HARAD_ain;
    public static LOTRTitle NEAR_HARAD_aj;
    public static LOTRTitle NEAR_HARAD_nomad;
    public static LOTRTitle NEAR_HARAD_gulf;
    public static LOTRTitle NEAR_HARAD_khopazul;
    public static LOTRTitle NEAR_HARAD_khopakadar;
    public static LOTRTitle NEAR_HARAD_yaphu;
    public static LOTRTitle NEAR_HARAD_serpent;
    public static LOTRTitle NEAR_HARAD_gulfing;
    public static LOTRTitle NEAR_HARAD_coastling;
    public static LOTRTitle MOREDAIN_moredain;
    public static LOTRTitle MOREDAIN_lion;
    public static LOTRTitle MOREDAIN_lioness;
    public static LOTRTitle TAUREDAIN_tauredain;
    public static LOTRTitle HALF_TROLL_halfTroll;
    public static LOTRTitle HALF_TROLL_pertorogwaith;
    private static int nextTitleID;
    public final int titleID;
    private String name;
    private boolean isHidden = false;
    private TitleType titleType = TitleType.STARTER;
    private UUID[] uuids;
    private List<LOTRFaction> alignmentFactions = new ArrayList<LOTRFaction>();
    private float alignmentRequired;
    private boolean anyAlignment = false;
    private LOTRAchievement titleAchievement;
    private boolean useAchievementName = false;
    private LOTRFactionRank titleRank;
    private boolean isFeminineRank;

    public LOTRTitle(String s) {
        this.titleID = nextTitleID++;
        this.name = s;
        allTitles.add(this);
    }

    public LOTRTitle(String s, LOTRAchievement ach) {
        this(s == null ? ach.getCodeName() : s);
        this.titleType = TitleType.ACHIEVEMENT;
        this.titleAchievement = ach;
        if(s == null) {
            this.useAchievementName = true;
        }
    }

    public LOTRTitle(LOTRFactionRank rank, boolean fem) {
        this(fem ? rank.getCodeNameFem() : rank.getCodeName());
        this.titleType = TitleType.RANK;
        this.titleRank = rank;
        this.isFeminineRank = fem;
    }

    public LOTRTitle setPlayerExclusive(UUID... players) {
        this.titleType = TitleType.PLAYER_EXCLUSIVE;
        this.uuids = players;
        this.isHidden = true;
        return this;
    }

    public LOTRTitle setPlayerExclusive(String... players) {
        UUID[] us = new UUID[players.length];
        for(int i = 0; i < players.length; ++i) {
            us[i] = UUID.fromString(players[i]);
        }
        return this.setPlayerExclusive(us);
    }

    public LOTRTitle setPlayerExclusive(UUID[]... playersArrays) {
        ArrayList<UUID> allPlayers = new ArrayList<UUID>();
        for(UUID[] players : playersArrays) {
            allPlayers.addAll(Arrays.asList(players));
        }
        return this.setPlayerExclusive(allPlayers.toArray(new UUID[0]));
    }

    public LOTRTitle setShieldExclusive(LOTRShields... shields) {
        ArrayList<UUID> allPlayers = new ArrayList<UUID>();
        for(LOTRShields shield : shields) {
            allPlayers.addAll(Arrays.asList(shield.exclusiveUUIDs));
        }
        return this.setPlayerExclusive(allPlayers.toArray(new UUID[0]));
    }

    public LOTRTitle setAlignment(LOTRFaction faction) {
        return this.setAlignment(faction, faction.getPledgeAlignment());
    }

    public LOTRTitle setAlignment(LOTRFaction faction, float alignment) {
        return this.setMultiAlignment(alignment, faction);
    }

    public LOTRTitle setMultiAlignment(float alignment, LOTRFaction... factions) {
        return this.setMultiAlignment(alignment, Arrays.asList(factions));
    }

    public LOTRTitle setMultiAlignment(float alignment, List<LOTRFaction> factions) {
        this.titleType = TitleType.ALIGNMENT;
        this.alignmentFactions.addAll(factions);
        this.alignmentRequired = alignment;
        return this;
    }

    public LOTRTitle setAnyAlignment(float alignment) {
        this.setMultiAlignment(alignment, LOTRFaction.getPlayableAlignmentFactions());
        this.anyAlignment = true;
        return this;
    }

    public String getTitleName() {
        return this.name;
    }

    public static LOTRTitle forName(String name) {
        for(LOTRTitle title : allTitles) {
            if(!title.getTitleName().equals(name)) continue;
            return title;
        }
        return null;
    }

    public static LOTRTitle forID(int ID) {
        for(LOTRTitle title : allTitles) {
            if(title.titleID != ID) continue;
            return title;
        }
        return null;
    }

    public String getUntranslatedName() {
        if(this.useAchievementName && this.titleAchievement != null) {
            return this.titleAchievement.getUntranslatedTitle();
        }
        if(this.titleType == TitleType.RANK) {
            if(this.isFeminineRank) {
                return this.titleRank.getCodeFullNameFem();
            }
            return this.titleRank.getCodeFullName();
        }
        return "lotr.title." + this.name;
    }

    public String getDisplayName() {
        if(this.titleType == TitleType.RANK) {
            if(this.isFeminineRank) {
                return this.titleRank.getDisplayFullNameFem();
            }
            return this.titleRank.getDisplayFullName();
        }
        return StatCollector.translateToLocal(this.getUntranslatedName());
    }

    public String getDescription(EntityPlayer entityplayer) {
        switch(this.titleType) {
            case STARTER: {
                return StatCollector.translateToLocal("lotr.titles.unlock.starter");
            }
            case PLAYER_EXCLUSIVE: {
                return StatCollector.translateToLocal("lotr.titles.unlock.exclusive");
            }
            case ALIGNMENT: {
                String alignLevel = LOTRAlignmentValues.formatAlignForDisplay(this.alignmentRequired);
                if(this.anyAlignment) {
                    return StatCollector.translateToLocalFormatted("lotr.titles.unlock.alignment.any", alignLevel);
                }
                String s = "";
                if(this.alignmentFactions.size() > 1) {
                    for(int i = 0; i < this.alignmentFactions.size(); ++i) {
                        LOTRFaction f = this.alignmentFactions.get(i);
                        if(i > 0) {
                            s = s + " / ";
                        }
                        s = s + f.factionName();
                    }
                }
                else {
                    LOTRFaction f = this.alignmentFactions.get(0);
                    s = f.factionName();
                }
                return StatCollector.translateToLocalFormatted("lotr.titles.unlock.alignment", s, alignLevel);
            }
            case ACHIEVEMENT: {
                return this.titleAchievement.getDescription(entityplayer);
            }
            case RANK: {
                String alignS = LOTRAlignmentValues.formatAlignForDisplay(this.titleRank.alignment);
                if(this.titleRank.isAbovePledgeRank()) {
                    return StatCollector.translateToLocalFormatted("lotr.titles.unlock.alignment.pledge", this.titleRank.fac.factionName(), alignS);
                }
                return StatCollector.translateToLocalFormatted("lotr.titles.unlock.alignment", this.titleRank.fac.factionName(), alignS);
            }
        }
        return "If you can read this, something has gone hideously wrong";
    }

    public boolean isFeminineRank() {
        return this.titleType == TitleType.RANK && this.isFeminineRank;
    }

    @Override
    public int compareTo(LOTRTitle other) {
        return this.getDisplayName().compareTo(other.getDisplayName());
    }

    public boolean canPlayerUse(EntityPlayer entityplayer) {
        switch(this.titleType) {
            case STARTER: {
                return true;
            }
            case PLAYER_EXCLUSIVE: {
                for(UUID player : this.uuids) {
                    if(!entityplayer.getUniqueID().equals(player)) continue;
                    return true;
                }
                return false;
            }
            case ALIGNMENT: {
                for(LOTRFaction f : this.alignmentFactions) {
                    if(!(LOTRLevelData.getData(entityplayer).getAlignment(f) >= this.alignmentRequired)) continue;
                    return true;
                }
                return false;
            }
            case ACHIEVEMENT: {
                return LOTRLevelData.getData(entityplayer).hasAchievement(this.titleAchievement);
            }
            case RANK: {
                LOTRPlayerData pd = LOTRLevelData.getData(entityplayer);
                LOTRFaction fac = this.titleRank.fac;
                float align = pd.getAlignment(fac);
                if(align >= this.titleRank.alignment) {
                    return !this.titleRank.isAbovePledgeRank() || pd.isPledgedTo(fac);
                }
                return false;
            }
        }
        return true;
    }

    public boolean canDisplay(EntityPlayer entityplayer) {
        return !this.isHidden || this.canPlayerUse(entityplayer);
    }

    public static void createTitles() {
        adventurer = new LOTRTitle("adventurer");
        rogue = new LOTRTitle("rogue");
        bartender = new LOTRTitle("bartender");
        gaffer = new LOTRTitle("gaffer");
        scholar = new LOTRTitle("scholar");
        minstrel = new LOTRTitle("minstrel");
        bard = new LOTRTitle("bard");
        artisan = new LOTRTitle("artisan");
        warrior = new LOTRTitle("warrior");
        scourge = new LOTRTitle("scourge");
        wanderer = new LOTRTitle("wanderer");
        peacekeeper = new LOTRTitle("peacekeeper");
        warmongerer = new LOTRTitle("warmongerer");
        hunter = new LOTRTitle("hunter");
        raider = new LOTRTitle("raider");
        explorer = new LOTRTitle("explorer");
        mercenary = new LOTRTitle("mercenary");
        shipwright = new LOTRTitle("shipwright");
        marksman = new LOTRTitle("marksman");
        merchant = new LOTRTitle("merchant");
        farmer = new LOTRTitle("farmer");
        burglar = new LOTRTitle("burglar");
        ruffian = new LOTRTitle("ruffian");
        architect = new LOTRTitle("architect");
        miner = new LOTRTitle("miner");
        swordsman = new LOTRTitle("swordsman");
        scout = new LOTRTitle("scout");
        spearman = new LOTRTitle("spearman");
        rider = new LOTRTitle("rider");
        watcher = new LOTRTitle("watcher");
        creator = new LOTRTitle("creator").setPlayerExclusive(new UUID[0]);
        creator2 = new LOTRTitle("creator2").setPlayerExclusive("7bc56da6-f133-4e47-8d0f-a2776762bca6");
        moderator = new LOTRTitle("moderator").setShieldExclusive(LOTRShields.MOD);
        gruk = new LOTRTitle("gruk").setShieldExclusive(LOTRShields.GRUK);
        boyd = new LOTRTitle("boyd").setShieldExclusive(LOTRShields.BOYD);
        translator = new LOTRTitle("translator").setPlayerExclusive(LOTRTranslatorList.playerUUIDs);
        patron = new LOTRTitle("patron").setPlayerExclusive(LOTRPatron.getTitlePlayers());
        loremaster = new LOTRTitle("loremaster").setShieldExclusive(LOTRShields.LOREMASTER_2013, LOTRShields.LOREMASTER_2014, LOTRShields.LOREMASTER_2015, LOTRShields.LOREMASTER_2016);
        builder = new LOTRTitle("builder").setShieldExclusive(LOTRShields.ELVEN_CONTEST, LOTRShields.EVIL_CONTEST, LOTRShields.SHIRE_CONTEST, LOTRShields.GONDOR_CONTEST, LOTRShields.HARAD_CONTEST, LOTRShields.RHUN_CONTEST);
        ANY_10000 = new LOTRTitle("ANY_10000").setAnyAlignment(10000.0f);
        MULTI_freePeoples = new LOTRTitle("MULTI_freePeoples").setMultiAlignment(100.0f, LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_FREE));
        MULTI_elf = new LOTRTitle("MULTI_elf").setMultiAlignment(100.0f, LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ELF));
        MULTI_orc = new LOTRTitle("MULTI_orc").setMultiAlignment(100.0f, LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC));
        MULTI_snaga = new LOTRTitle("MULTI_snaga").setMultiAlignment(100.0f, LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_ORC));
        MULTI_dwarf = new LOTRTitle("MULTI_dwarf").setMultiAlignment(100.0f, LOTRFaction.getAllOfType(LOTRFaction.FactionType.TYPE_DWARF));
        MULTI_dunedain = new LOTRTitle("MULTI_dunedain").setMultiAlignment(100.0f, LOTRFaction.RANGER_NORTH, LOTRFaction.GONDOR);
        MULTI_moria = new LOTRTitle("MULTI_moria").setMultiAlignment(100.0f, LOTRFaction.GUNDABAD, LOTRFaction.DWARF);
        MULTI_rhun = new LOTRTitle("MULTI_rhun").setMultiAlignment(100.0f, LOTRFaction.getAllRhun());
        MULTI_easterling = new LOTRTitle("MULTI_easterling").setMultiAlignment(100.0f, LOTRFaction.getAllRhun());
        MULTI_harad = new LOTRTitle("MULTI_harad").setMultiAlignment(100.0f, LOTRFaction.getAllHarad());
        MULTI_haradrim = new LOTRTitle("MULTI_haradrim").setMultiAlignment(100.0f, LOTRFaction.getAllHarad());
        MULTI_southron = new LOTRTitle("MULTI_southron").setMultiAlignment(100.0f, LOTRFaction.getAllHarad());
        MULTI_farHarad = new LOTRTitle("MULTI_farHarad").setMultiAlignment(100.0f, LOTRFaction.MOREDAIN, LOTRFaction.TAUREDAIN, LOTRFaction.HALF_TROLL);
        MULTI_farHaradrim = new LOTRTitle("MULTI_farHaradrim").setMultiAlignment(100.0f, LOTRFaction.MOREDAIN, LOTRFaction.TAUREDAIN, LOTRFaction.HALF_TROLL);
        MULTI_silvanElf = new LOTRTitle("MULTI_silvanElf").setMultiAlignment(100.0f, LOTRFaction.WOOD_ELF, LOTRFaction.GALADHRIM);
        MULTI_sindar = new LOTRTitle("MULTI_sindar").setMultiAlignment(100.0f, LOTRFaction.HIGH_ELF, LOTRFaction.WOOD_ELF, LOTRFaction.GALADHRIM);
        MULTI_greyElf = new LOTRTitle("MULTI_greyElf").setMultiAlignment(100.0f, LOTRFaction.HIGH_ELF, LOTRFaction.WOOD_ELF, LOTRFaction.GALADHRIM);
        MULTI_noldor = new LOTRTitle("MULTI_noldor").setMultiAlignment(100.0f, LOTRFaction.HIGH_ELF);
        MULTI_avari = new LOTRTitle("MULTI_avari").setMultiAlignment(100.0f, LOTRFaction.DORWINION);
        MULTI_sunlands = new LOTRTitle("MULTI_sunlands").setMultiAlignment(100.0f, LOTRFaction.getAllHarad());
        MULTI_swerting = new LOTRTitle("MULTI_swerting").setMultiAlignment(100.0f, LOTRFaction.getAllHarad());
        MULTI_sutherland = new LOTRTitle("MULTI_sutherland").setMultiAlignment(100.0f, LOTRFaction.getAllHarad());
        HOBBIT_hobbit = new LOTRTitle("HOBBIT_hobbit").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_halfling = new LOTRTitle("HOBBIT_halfling").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_shire = new LOTRTitle("HOBBIT_shire").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_hobbiton = new LOTRTitle("HOBBIT_hobbiton").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_buckland = new LOTRTitle("HOBBIT_buckland").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_tookland = new LOTRTitle("HOBBIT_tookland").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_bywater = new LOTRTitle("HOBBIT_bywater").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_longbottom = new LOTRTitle("HOBBIT_longbottom").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_michelDelving = new LOTRTitle("HOBBIT_michelDelving").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_northfarthing = new LOTRTitle("HOBBIT_northfarthing").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_westfarthing = new LOTRTitle("HOBBIT_westfarthing").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_southfarthing = new LOTRTitle("HOBBIT_southfarthing").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_eastfarthing = new LOTRTitle("HOBBIT_eastfarthing").setAlignment(LOTRFaction.HOBBIT);
        HOBBIT_mathomKeeper = new LOTRTitle("HOBBIT_mathomKeeper").setAlignment(LOTRFaction.HOBBIT);
        RANGER_ranger = new LOTRTitle("RANGER_ranger").setAlignment(LOTRFaction.RANGER_NORTH);
        RANGER_northDunedain = new LOTRTitle("RANGER_northDunedain").setAlignment(LOTRFaction.RANGER_NORTH);
        RANGER_arnor = new LOTRTitle("RANGER_arnor").setAlignment(LOTRFaction.RANGER_NORTH);
        RANGER_annuminas = new LOTRTitle("RANGER_annuminas").setAlignment(LOTRFaction.RANGER_NORTH);
        RANGER_fornost = new LOTRTitle("RANGER_fornost").setAlignment(LOTRFaction.RANGER_NORTH);
        BLUE_MOUNTAINS_blueDwarf = new LOTRTitle("BLUE_MOUNTAINS_blueDwarf").setAlignment(LOTRFaction.BLUE_MOUNTAINS);
        BLUE_MOUNTAINS_blueMountains = new LOTRTitle("BLUE_MOUNTAINS_blueMountains").setAlignment(LOTRFaction.BLUE_MOUNTAINS);
        BLUE_MOUNTAINS_firebeard = new LOTRTitle("BLUE_MOUNTAINS_firebeard").setAlignment(LOTRFaction.BLUE_MOUNTAINS);
        BLUE_MOUNTAINS_broadbeam = new LOTRTitle("BLUE_MOUNTAINS_broadbeam").setAlignment(LOTRFaction.BLUE_MOUNTAINS);
        BLUE_MOUNTAINS_belegost = new LOTRTitle("BLUE_MOUNTAINS_belegost").setAlignment(LOTRFaction.BLUE_MOUNTAINS);
        BLUE_MOUNTAINS_nogrod = new LOTRTitle("BLUE_MOUNTAINS_nogrod").setAlignment(LOTRFaction.BLUE_MOUNTAINS);
        HIGH_ELF_highElf = new LOTRTitle("HIGH_ELF_highElf").setAlignment(LOTRFaction.HIGH_ELF);
        HIGH_ELF_lindon = new LOTRTitle("HIGH_ELF_lindon").setAlignment(LOTRFaction.HIGH_ELF);
        HIGH_ELF_mithlond = new LOTRTitle("HIGH_ELF_mithlond").setAlignment(LOTRFaction.HIGH_ELF);
        HIGH_ELF_rivendell = new LOTRTitle("HIGH_ELF_rivendell").setAlignment(LOTRFaction.HIGH_ELF);
        HIGH_ELF_eregion = new LOTRTitle("HIGH_ELF_eregion").setAlignment(LOTRFaction.HIGH_ELF);
        HIGH_ELF_forlindon = new LOTRTitle("HIGH_ELF_forlindon").setAlignment(LOTRFaction.HIGH_ELF);
        HIGH_ELF_harlindon = new LOTRTitle("HIGH_ELF_harlindon").setAlignment(LOTRFaction.HIGH_ELF);
        HIGH_ELF_imladris = new LOTRTitle("HIGH_ELF_imladris").setAlignment(LOTRFaction.HIGH_ELF);
        GUNDABAD_gundabad = new LOTRTitle("GUNDABAD_gundabad").setAlignment(LOTRFaction.GUNDABAD);
        GUNDABAD_gundabadOrc = new LOTRTitle("GUNDABAD_gundabadOrc").setAlignment(LOTRFaction.GUNDABAD);
        GUNDABAD_moriaOrc = new LOTRTitle("GUNDABAD_moriaOrc").setAlignment(LOTRFaction.GUNDABAD);
        GUNDABAD_goblin = new LOTRTitle("GUNDABAD_goblin").setAlignment(LOTRFaction.GUNDABAD);
        GUNDABAD_goblinTown = new LOTRTitle("GUNDABAD_goblinTown").setAlignment(LOTRFaction.GUNDABAD);
        GUNDABAD_mountGundabad = new LOTRTitle("GUNDABAD_mountGundabad").setAlignment(LOTRFaction.GUNDABAD);
        GUNDABAD_mountGram = new LOTRTitle("GUNDABAD_mountGram").setAlignment(LOTRFaction.GUNDABAD);
        GUNDABAD_gundabadUruk = new LOTRTitle("GUNDABAD_gundabadUruk").setAlignment(LOTRFaction.GUNDABAD);
        ANGMAR_angmar = new LOTRTitle("ANGMAR_angmar").setAlignment(LOTRFaction.ANGMAR);
        ANGMAR_angmarOrc = new LOTRTitle("ANGMAR_angmarOrc").setAlignment(LOTRFaction.ANGMAR);
        ANGMAR_troll = new LOTRTitle("ANGMAR_troll").setAlignment(LOTRFaction.ANGMAR);
        ANGMAR_carnDum = new LOTRTitle("ANGMAR_carnDum").setAlignment(LOTRFaction.ANGMAR);
        ANGMAR_hillman = new LOTRTitle("ANGMAR_hillman").setAlignment(LOTRFaction.ANGMAR);
        ANGMAR_rhudaur = new LOTRTitle("ANGMAR_rhudaur").setAlignment(LOTRFaction.ANGMAR);
        ANGMAR_ettenmoors = new LOTRTitle("ANGMAR_ettenmoors").setAlignment(LOTRFaction.ANGMAR);
        ANGMAR_coldfells = new LOTRTitle("ANGMAR_coldfells").setAlignment(LOTRFaction.ANGMAR);
        WOOD_ELF_woodElf = new LOTRTitle("WOOD_ELF_woodElf").setAlignment(LOTRFaction.WOOD_ELF);
        WOOD_ELF_woodlandRealm = new LOTRTitle("WOOD_ELF_woodlandRealm").setAlignment(LOTRFaction.WOOD_ELF);
        DOL_GULDUR_dolGuldur = new LOTRTitle("DOL_GULDUR_dolGuldur").setAlignment(LOTRFaction.DOL_GULDUR);
        DOL_GULDUR_dolGuldurOrc = new LOTRTitle("DOL_GULDUR_dolGuldurOrc").setAlignment(LOTRFaction.DOL_GULDUR);
        DOL_GULDUR_necromancer = new LOTRTitle("DOL_GULDUR_necromancer").setAlignment(LOTRFaction.DOL_GULDUR);
        DOL_GULDUR_sorcerer = new LOTRTitle("DOL_GULDUR_sorcerer").setAlignment(LOTRFaction.DOL_GULDUR);
        DOL_GULDUR_spiderRider = new LOTRTitle("DOL_GULDUR_spiderRider").setAlignment(LOTRFaction.DOL_GULDUR);
        DALE_dale = new LOTRTitle("DALE_dale").setAlignment(LOTRFaction.DALE);
        DALE_northman = new LOTRTitle("DALE_northman").setAlignment(LOTRFaction.DALE);
        DALE_barding = new LOTRTitle("DALE_barding").setAlignment(LOTRFaction.DALE);
        DALE_esgaroth = new LOTRTitle("DALE_esgaroth").setAlignment(LOTRFaction.DALE);
        DWARF_durin = new LOTRTitle("DWARF_durin").setAlignment(LOTRFaction.DWARF);
        DWARF_greyDwarf = new LOTRTitle("DWARF_greyDwarf").setAlignment(LOTRFaction.DWARF);
        DWARF_ironHills = new LOTRTitle("DWARF_ironHills").setAlignment(LOTRFaction.DWARF);
        DWARF_erebor = new LOTRTitle("DWARF_erebor").setAlignment(LOTRFaction.DWARF);
        DWARF_khazadDum = new LOTRTitle("DWARF_khazadDum").setAlignment(LOTRFaction.DWARF);
        GALADHRIM_galadhrim = new LOTRTitle("GALADHRIM_galadhrim").setAlignment(LOTRFaction.GALADHRIM);
        GALADHRIM_lothlorien = new LOTRTitle("GALADHRIM_lothlorien").setAlignment(LOTRFaction.GALADHRIM);
        GALADHRIM_carasGaladhon = new LOTRTitle("GALADHRIM_carasGaladhon").setAlignment(LOTRFaction.GALADHRIM);
        DUNLAND_dunland = new LOTRTitle("DUNLAND_dunland").setAlignment(LOTRFaction.DUNLAND);
        DUNLAND_dunlending = new LOTRTitle("DUNLAND_dunlending").setAlignment(LOTRFaction.DUNLAND);
        DUNLAND_wildman = new LOTRTitle("DUNLAND_wildman").setAlignment(LOTRFaction.DUNLAND);
        DUNLAND_barbarian = new LOTRTitle("DUNLAND_barbarian").setAlignment(LOTRFaction.DUNLAND);
        DUNLAND_adorn = new LOTRTitle("DUNLAND_adorn").setAlignment(LOTRFaction.DUNLAND);
        DUNLAND_berserker = new LOTRTitle("DUNLAND_berserker").setAlignment(LOTRFaction.DUNLAND);
        URUK_uruk = new LOTRTitle("URUK_uruk").setAlignment(LOTRFaction.URUK_HAI);
        URUK_urukHai = new LOTRTitle("URUK_urukHai").setAlignment(LOTRFaction.URUK_HAI);
        URUK_isengard = new LOTRTitle("URUK_isengard").setAlignment(LOTRFaction.URUK_HAI);
        URUK_whiteHand = new LOTRTitle("URUK_whiteHand").setAlignment(LOTRFaction.URUK_HAI);
        FANGORN_fangorn = new LOTRTitle("FANGORN_fangorn").setAlignment(LOTRFaction.FANGORN);
        FANGORN_ent = new LOTRTitle("FANGORN_ent").setAlignment(LOTRFaction.FANGORN);
        ROHAN_rohan = new LOTRTitle("ROHAN_rohan").setAlignment(LOTRFaction.ROHAN);
        ROHAN_rohirrim = new LOTRTitle("ROHAN_rohirrim").setAlignment(LOTRFaction.ROHAN);
        ROHAN_eorlingas = new LOTRTitle("ROHAN_eorlingas").setAlignment(LOTRFaction.ROHAN);
        ROHAN_strawhead = new LOTRTitle("ROHAN_strawhead").setAlignment(LOTRFaction.ROHAN);
        ROHAN_edoras = new LOTRTitle("ROHAN_edoras").setAlignment(LOTRFaction.ROHAN);
        ROHAN_helmsDeep = new LOTRTitle("ROHAN_helmsDeep").setAlignment(LOTRFaction.ROHAN);
        ROHAN_grimslade = new LOTRTitle("ROHAN_grimslade").setAlignment(LOTRFaction.ROHAN);
        ROHAN_aldburg = new LOTRTitle("ROHAN_aldburg").setAlignment(LOTRFaction.ROHAN);
        ROHAN_westfold = new LOTRTitle("ROHAN_westfold").setAlignment(LOTRFaction.ROHAN);
        ROHAN_eastfold = new LOTRTitle("ROHAN_eastfold").setAlignment(LOTRFaction.ROHAN);
        ROHAN_westemnet = new LOTRTitle("ROHAN_westemnet").setAlignment(LOTRFaction.ROHAN);
        ROHAN_eastemnet = new LOTRTitle("ROHAN_eastemnet").setAlignment(LOTRFaction.ROHAN);
        ROHAN_wold = new LOTRTitle("ROHAN_wold").setAlignment(LOTRFaction.ROHAN);
        ROHAN_shieldmaiden = new LOTRTitle("ROHAN_shieldmaiden").setAlignment(LOTRFaction.ROHAN);
        ROHAN_horselord = new LOTRTitle("ROHAN_horselord").setAlignment(LOTRFaction.ROHAN);
        GONDOR_gondor = new LOTRTitle("GONDOR_gondor").setAlignment(LOTRFaction.GONDOR);
        GONDOR_gondorian = new LOTRTitle("GONDOR_gondorian").setAlignment(LOTRFaction.GONDOR);
        GONDOR_southDunedain = new LOTRTitle("GONDOR_southDunedain").setAlignment(LOTRFaction.GONDOR);
        GONDOR_dolAmroth = new LOTRTitle("GONDOR_dolAmroth").setAlignment(LOTRFaction.GONDOR);
        GONDOR_swanKnight = new LOTRTitle("GONDOR_swanKnight").setAlignment(LOTRFaction.GONDOR);
        GONDOR_ithilien = new LOTRTitle("GONDOR_ithilien").setAlignment(LOTRFaction.GONDOR);
        GONDOR_ithilienRanger = new LOTRTitle("GONDOR_ithilienRanger").setAlignment(LOTRFaction.GONDOR);
        GONDOR_minasTirith = new LOTRTitle("GONDOR_minasTirith").setAlignment(LOTRFaction.GONDOR);
        GONDOR_towerGuard = new LOTRTitle("GONDOR_towerGuard").setAlignment(LOTRFaction.GONDOR);
        GONDOR_osgiliath = new LOTRTitle("GONDOR_osgiliath").setAlignment(LOTRFaction.GONDOR);
        GONDOR_lebennin = new LOTRTitle("GONDOR_lebennin").setAlignment(LOTRFaction.GONDOR);
        GONDOR_anorien = new LOTRTitle("GONDOR_anorien").setAlignment(LOTRFaction.GONDOR);
        GONDOR_lossarnach = new LOTRTitle("GONDOR_lossarnach").setAlignment(LOTRFaction.GONDOR);
        GONDOR_imlothMelui = new LOTRTitle("GONDOR_imlothMelui").setAlignment(LOTRFaction.GONDOR);
        GONDOR_pelargir = new LOTRTitle("GONDOR_pelargir").setAlignment(LOTRFaction.GONDOR);
        GONDOR_blackrootVale = new LOTRTitle("GONDOR_blackrootVale").setAlignment(LOTRFaction.GONDOR);
        GONDOR_mornan = new LOTRTitle("GONDOR_mornan").setAlignment(LOTRFaction.GONDOR);
        GONDOR_pinnathGelin = new LOTRTitle("GONDOR_pinnathGelin").setAlignment(LOTRFaction.GONDOR);
        GONDOR_lamedon = new LOTRTitle("GONDOR_lamedon").setAlignment(LOTRFaction.GONDOR);
        GONDOR_anfalas = new LOTRTitle("GONDOR_anfalas").setAlignment(LOTRFaction.GONDOR);
        GONDOR_belfalas = new LOTRTitle("GONDOR_belfalas").setAlignment(LOTRFaction.GONDOR);
        GONDOR_linhir = new LOTRTitle("GONDOR_linhir").setAlignment(LOTRFaction.GONDOR);
        GONDOR_edhellond = new LOTRTitle("GONDOR_edhellond").setAlignment(LOTRFaction.GONDOR);
        GONDOR_tarnost = new LOTRTitle("GONDOR_tarnost").setAlignment(LOTRFaction.GONDOR);
        GONDOR_calembel = new LOTRTitle("GONDOR_calembel").setAlignment(LOTRFaction.GONDOR);
        GONDOR_ethring = new LOTRTitle("GONDOR_ethring").setAlignment(LOTRFaction.GONDOR);
        GONDOR_erech = new LOTRTitle("GONDOR_erech").setAlignment(LOTRFaction.GONDOR);
        GONDOR_ethirAnduin = new LOTRTitle("GONDOR_ethirAnduin").setAlignment(LOTRFaction.GONDOR);
        MORDOR_mordor = new LOTRTitle("MORDOR_mordor").setAlignment(LOTRFaction.MORDOR);
        MORDOR_mordorOrc = new LOTRTitle("MORDOR_mordorOrc").setAlignment(LOTRFaction.MORDOR);
        MORDOR_blackUruk = new LOTRTitle("MORDOR_blackUruk").setAlignment(LOTRFaction.MORDOR);
        MORDOR_nurn = new LOTRTitle("MORDOR_nurn").setAlignment(LOTRFaction.MORDOR);
        MORDOR_baradDur = new LOTRTitle("MORDOR_baradDur").setAlignment(LOTRFaction.MORDOR);
        MORDOR_morannon = new LOTRTitle("MORDOR_morannon").setAlignment(LOTRFaction.MORDOR);
        MORDOR_minasMorgul = new LOTRTitle("MORDOR_minasMorgul").setAlignment(LOTRFaction.MORDOR);
        MORDOR_cirithUngol = new LOTRTitle("MORDOR_cirithUngol").setAlignment(LOTRFaction.MORDOR);
        MORDOR_blackNumenorean = new LOTRTitle("MORDOR_blackNumenorean").setAlignment(LOTRFaction.MORDOR);
        MORDOR_nanUngol = new LOTRTitle("MORDOR_nanUngol").setAlignment(LOTRFaction.MORDOR);
        DORWINION_dorwinion = new LOTRTitle("DORWINION_dorwinion").setAlignment(LOTRFaction.DORWINION);
        DORWINION_vintner = new LOTRTitle("DORWINION_vintner").setAlignment(LOTRFaction.DORWINION);
        DORWINION_dorwinrim = new LOTRTitle("DORWINION_dorwinrim").setAlignment(LOTRFaction.DORWINION);
        DORWINION_dorwinionElf = new LOTRTitle("DORWINION_dorwinionElf").setAlignment(LOTRFaction.DORWINION);
        DORWINION_bladorthin = new LOTRTitle("DORWINION_bladorthin").setAlignment(LOTRFaction.DORWINION);
        DORWINION_wineTaster = new LOTRTitle("DORWINION_wineTaster").setAlignment(LOTRFaction.DORWINION);
        RHUN_rhudel = new LOTRTitle("RHUN_rhudel").setAlignment(LOTRFaction.RHUN);
        RHUN_rhunaer = new LOTRTitle("RHUN_rhunaer").setAlignment(LOTRFaction.RHUN);
        RHUN_rhunaerim = new LOTRTitle("RHUN_rhunaerim").setAlignment(LOTRFaction.RHUN);
        NEAR_HARAD_nearHarad = new LOTRTitle("NEAR_HARAD_nearHarad").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_nearHaradrim = new LOTRTitle("NEAR_HARAD_nearHaradrim").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_umbar = new LOTRTitle("NEAR_HARAD_umbar").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_corsair = new LOTRTitle("NEAR_HARAD_corsair").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_harnedor = new LOTRTitle("NEAR_HARAD_harnedor").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_ninzayan = new LOTRTitle("NEAR_HARAD_ninzayan").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_belkadar = new LOTRTitle("NEAR_HARAD_belkadar").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_southronCoasts = new LOTRTitle("NEAR_HARAD_southronCoasts").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_azrazain = new LOTRTitle("NEAR_HARAD_azrazain").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_ain = new LOTRTitle("NEAR_HARAD_ain").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_aj = new LOTRTitle("NEAR_HARAD_aj").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_nomad = new LOTRTitle("NEAR_HARAD_nomad").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_gulf = new LOTRTitle("NEAR_HARAD_gulf").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_khopazul = new LOTRTitle("NEAR_HARAD_khopazul").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_khopakadar = new LOTRTitle("NEAR_HARAD_khopakadar").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_yaphu = new LOTRTitle("NEAR_HARAD_yaphu").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_serpent = new LOTRTitle("NEAR_HARAD_serpent").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_gulfing = new LOTRTitle("NEAR_HARAD_gulfing").setAlignment(LOTRFaction.NEAR_HARAD);
        NEAR_HARAD_coastling = new LOTRTitle("NEAR_HARAD_coastling").setAlignment(LOTRFaction.NEAR_HARAD);
        MOREDAIN_moredain = new LOTRTitle("MOREDAIN_moredain").setAlignment(LOTRFaction.MOREDAIN);
        MOREDAIN_lion = new LOTRTitle("MOREDAIN_lion").setAlignment(LOTRFaction.MOREDAIN);
        MOREDAIN_lioness = new LOTRTitle("MOREDAIN_lioness").setAlignment(LOTRFaction.MOREDAIN);
        TAUREDAIN_tauredain = new LOTRTitle("TAUREDAIN_tauredain").setAlignment(LOTRFaction.TAUREDAIN);
        HALF_TROLL_halfTroll = new LOTRTitle("HALF_TROLL_halfTroll").setAlignment(LOTRFaction.HALF_TROLL);
        HALF_TROLL_pertorogwaith = new LOTRTitle("HALF_TROLL_pertorogwaith").setAlignment(LOTRFaction.HALF_TROLL);
    }

    static {
        nextTitleID = 0;
    }

    public static class PlayerTitle {
        private final LOTRTitle theTitle;
        private final EnumChatFormatting theColor;

        public PlayerTitle(LOTRTitle title) {
            this(title, null);
        }

        public PlayerTitle(LOTRTitle title, EnumChatFormatting color) {
            this.theTitle = title;
            if(color == null || !color.isColor()) {
                color = EnumChatFormatting.WHITE;
            }
            this.theColor = color;
        }

        public IChatComponent getFullTitleComponent() {
            IChatComponent component = new ChatComponentText("[").appendSibling(new ChatComponentTranslation(this.theTitle.getUntranslatedName(), new Object[0])).appendText("]").appendText(" ");
            component.getChatStyle().setColor(this.theColor);
            return component;
        }

        public String getFormattedTitle() {
            return this.getFullTitleComponent().getFormattedText();
        }

        public LOTRTitle getTitle() {
            return this.theTitle;
        }

        public EnumChatFormatting getColor() {
            return this.theColor;
        }

        public static EnumChatFormatting colorForID(int ID) {
            for(EnumChatFormatting color : EnumChatFormatting.values()) {
                if(color.getFormattingCode() != ID) continue;
                return color;
            }
            return null;
        }
    }

    public static enum TitleType {
        STARTER, PLAYER_EXCLUSIVE, ALIGNMENT, ACHIEVEMENT, RANK;

    }

}
