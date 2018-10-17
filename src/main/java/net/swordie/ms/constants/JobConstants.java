package net.swordie.ms.constants;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.WeaponType;
import net.swordie.ms.util.Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Itzik
 */
public class JobConstants {

    public static final boolean enableJobs = true;
    public static final int jobOrder = 8;

    public static boolean isXenon(short jobId) {
        return jobId / 100 == 36 || jobId == 3002;
    }

    public static boolean isBeastTamer(short job) {
        return job / 1000 == 11;
    }

    public static boolean isPinkBean(short job) {
        return job == JobEnum.PINK_BEAN_0.getJobId() || job == JobEnum.PINK_BEAN_1.getJobId();
    }

    public static JobEnum getJobEnumById(short jobId) {
        return Arrays.stream(JobEnum.values()).filter(job -> job.getJobId() == jobId)
                .findFirst().orElse(null);
    }

    public static boolean isWildHunter(short job) {
        return job / 100 == 33;
    }

    public static boolean isAngelicBuster(int id) {
        return id == JobConstants.JobEnum.ANGELIC_BUSTER.getJobId() || id / 100 == 65;
    }

    public static boolean isBlazeWizard(short job) {
        return job / 100 == 12;
    }

    public static boolean isDawnWarrior(short job) {
        return job / 100 == 11;
    }

    public static boolean isShadower(short job) {
        return job / 10 == 42;
    }

    public static boolean isNightLord(short job) {
        return job / 10 == 41;
    }

    public static boolean isDualBlade(short job) {
        return job / 10 == 43;
    }

    public static boolean isHero(short job) {
        return job / 10 == 11;
    }

    public static boolean isPage(short job) {
        return job / 10 == 12;
    }

    public static boolean isDarkKnight(short job) {
        return job / 10 == 13;
    }

    public static boolean isFirePoison(short job) {
        return job / 10 == 21;
    }

    public static boolean isIceLightning(short job) {
        return job / 10 == 22;
    }

    public static boolean isCleric(short job) {
        return job / 10 == 23;
    }

    public static boolean isBuccaneer(short job) {
        return job / 10 == 51;
    }

    public static boolean isCorsair(short job) {
        return job / 10 == 52;
    }

    public static boolean isJett(short job) {
        return job / 10 == 57 || job == JobEnum.JETT1.getJobId();
    }

    public static boolean isDemonSlayer(short job) {
        return job / 10 == 311;
    }

    public static boolean isDemonAvenger(short job) {
        return job / 10 == 312;
    }

    public static boolean isKanna(short id) {
        return id == JobConstants.JobEnum.KANNA.getJobId() || id / 100 == 42;
    }

    public static boolean isHayato(short id) {
        return id == JobConstants.JobEnum.HAYATO.getJobId() || id / 100 == 41;
    }

    public static boolean isNightWalker(short id) {
        return id / 100 == 14;
    }

    public static boolean isThunderBreaker(short id) {
        return id / 100 == 15;
    }

    public static boolean isBlaster(short id) {
        return id / 100 == 37;
    }

    public static boolean isShade(short id) {
        return id == JobEnum.SHADE.getJobId() || id / 100 == 25;
    }

    public static boolean isWindArcher(short id) {
        return id / 100 == 13;
    }

    public static boolean isMihile(short id) {
        return id / 100 == 51;
    }

    public static double getDamageConstant(short job) {
        // get_job_damage_const 
        if (job > 222) {
            if (job > 1200) {
                if (job >= 1210 && job <= 1212)
                    return 0.2;
            } else if (job == 1200 || job >= 230 && job <= 232) {
                return 0.2;
            }
            return 0.0;
        }
        if (job < 220) {
            switch (job) {
                case 110:
                case 111:
                case 112:
                    return 0.1;
                case 200:
                case 210:
                case 211:
                case 212:
                    return 0.2;
                default:
                    return 0.0;
            }
        }
        return 0.2;
    }

    public static int getJobCategory(short job) {
        int res = 0;
        switch (job / 100) {
            case 27:
            case 140:
            case 142:
                res = 2;
                break;
            case 36:
                res = 4;
                break;
            case 37:
                res = 1;
                break;
            default:
                res = job % 1000 / 100;
        }
        return res;
    }

    public static boolean isKoC(short job) {
        return job / 1000 == 1;
    }

    public static byte getJobLevelByZeroSkillID(int skillID) {
        int prefix = (skillID % 1000) / 100;
        return (byte) (prefix == 1 ? 2
                : prefix == 2 ? 1
                : 3);
    }

    public static boolean isMechanic(short id) {
        return id >= JobConstants.JobEnum.MECHANIC_1.getJobId() && id <= JobConstants.JobEnum.MECHANIC_4.getJobId();
    }

    public static boolean isBattleMage(short id) {
        return id >= JobConstants.JobEnum.BATTLE_MAGE_1.getJobId() && id <= JobConstants.JobEnum.BATTLE_MAGE_4.getJobId();
    }

    public static boolean isGmJob(short id) {
        return isGm(id) || isSuperGm(id);
    }

    public static boolean isGm(short id) {
        return id == JobEnum.GM.getJobId();
    }

    public static boolean isSuperGm(short id) {
        return id == JobEnum.SUPERGM.getJobId();
    }

    public enum JobEnum {
        BEGINNER(0),
        WARRIOR(100),
        FIGHTER(110),
        CRUSADER(111),
        HERO(112),
        PAGE(120),
        WHITEKNIGHT(121),
        PALADIN(122),
        SPEARMAN(130),
        DRAGONKNIGHT(131),
        DARKKNIGHT(132),
        MAGICIAN(200),
        FP_WIZARD(210),
        FP_MAGE(211),
        FP_ARCHMAGE(212),
        IL_WIZARD(220),
        IL_MAGE(221),
        IL_ARCHMAGE(222),
        CLERIC(230),
        PRIEST(231),
        BISHOP(232),
        BOWMAN(300),
        HUNTER(310),
        RANGER(311),
        BOWMASTER(312),
        CROSSBOWMAN(320),
        SNIPER(321),
        MARKSMAN(322),
        THIEF(400),
        ASSASSIN(410),
        HERMIT(411),
        NIGHTLORD(412),
        BANDIT(420),
        CHIEFBANDIT(421),
        SHADOWER(422),
        BLADE_RECRUIT(430),
        BLADE_ACOLYTE(431),
        BLADE_SPECIALIST(432),
        BLADE_LORD(433),
        BLADE_MASTER(434),
        PIRATE(500),
        PIRATE_CANNONNEER(501),
        JETT1(508),
        BRAWLER(510),
        MARAUDER(511),
        BUCCANEER(512),
        GUNSLINGER(520),
        OUTLAW(521),
        CORSAIR(522),
        CANNONEER(530),
        CANNON_BLASTER(531),
        CANNON_MASTER(532),
        JETT2(570),
        JETT3(571),
        JETT4(572),
        MANAGER(800),
        GM(900),
        SUPERGM(910),
        NOBLESSE(1000),
        DAWNWARRIOR1(1100),
        DAWNWARRIOR2(1110),
        DAWNWARRIOR3(1111),
        DAWNWARRIOR4(1112),
        BLAZEWIZARD1(1200),
        BLAZEWIZARD2(1210),
        BLAZEWIZARD3(1211),
        BLAZEWIZARD4(1212),
        WINDARCHER1(1300),
        WINDARCHER2(1310),
        WINDARCHER3(1311),
        WINDARCHER4(1312),
        NIGHTWALKER1(1400),
        NIGHTWALKER2(1410),
        NIGHTWALKER3(1411),
        NIGHTWALKER4(1412),
        THUNDERBREAKER1(1500),
        THUNDERBREAKER2(1510),
        THUNDERBREAKER3(1511),
        THUNDERBREAKER4(1512),
        LEGEND(2000),
        EVAN_NOOB(2001),
        ARAN1(2100),
        ARAN2(2110),
        ARAN3(2111),
        ARAN4(2112),
        EVAN1(2210),
        EVAN2(2212),
        EVAN3(2214),
        EVAN4(2218),
        MERCEDES(2002),
        MERCEDES1(2300),
        MERCEDES2(2310),
        MERCEDES3(2311),
        MERCEDES4(2312),
        PHANTOM(2003),
        PHANTOM1(2400),
        PHANTOM2(2410),
        PHANTOM3(2411),
        PHANTOM4(2412),
        SHADE(2005),
        SHADE1(2500),
        SHADE2(2510),
        SHADE3(2511),
        SHADE4(2512),
        LUMINOUS(2004),
        LUMINOUS1(2700),
        LUMINOUS2(2710),
        LUMINOUS3(2711),
        LUMINOUS4(2712),
        CITIZEN(3000),
        DEMON_SLAYER(3001),
        XENON(3002),
        DEMON_SLAYER1(3100),
        DEMON_SLAYER2(3110),
        DEMON_SLAYER3(3111),
        DEMON_SLAYER4(3112),
        DEMON_AVENGER1(3101),
        DEMON_AVENGER2(3120),
        DEMON_AVENGER3(3121),
        DEMON_AVENGER4(3122),
        BATTLE_MAGE_1(3200),
        BATTLE_MAGE_2(3210),
        BATTLE_MAGE_3(3211),
        BATTLE_MAGE_4(3212),
        WILD_HUNTER_1(3300),
        WILD_HUNTER_2(3310),
        WILD_HUNTER_3(3311),
        WILD_HUNTER_4(3312),
        MECHANIC_1(3500),
        MECHANIC_2(3510),
        MECHANIC_3(3511),
        MECHANIC_4(3512),
        BLASTER_1(3700),
        BLASTER_2(3710),
        BLASTER_3(3711),
        BLASTER_4(3712),
        XENON1(3600),
        XENON2(3610),
        XENON3(3611),
        XENON4(3612),
        HAYATO(4001),
        KANNA(4002),
        HAYATO1(4100),
        HAYATO2(4110),
        HAYATO3(4111),
        HAYATO4(4112),
        KANNA1(4200),
        KANNA2(4210),
        KANNA3(4211),
        KANNA4(4212),
        NAMELESS_WARDEN(5000),
        MIHILE1(5100),
        MIHILE2(5110),
        MIHILE3(5111),
        MIHILE4(5112),
        KAISER(6000),
        ANGELIC_BUSTER(6001),
        KAISER1(6100),
        KAISER2(6110),
        KAISER3(6111),
        KAISER4(6112),
        ANGELIC_BUSTER1(6500),
        ANGELIC_BUSTER2(6510),
        ANGELIC_BUSTER3(6511),
        ANGELIC_BUSTER4(6512),
        ADDITIONAL_SKILLS(9000),
        ZERO(10000),
        ZERO1(10100),
        ZERO2(10110),
        ZERO3(10111),
        ZERO4(10112),
        BEAST_TAMER(11000),
        BEAST_TAMER_1(11200),
        BEAST_TAMER_2(11210),
        BEAST_TAMER_3(11211),
        BEAST_TAMER_4(11212),
        PINK_BEAN_0(13000),
        PINK_BEAN_1(13100),
        KINESIS_0(14000),
        KINESIS_1(14200),
        KINESIS_2(14210),
        KINESIS_3(14211),
        KINESIS_4(14212),
        EMPTY_0(30000),
        V_SKILLS(40000),
        EMPTY_2(40001),
        EMPTY_3(40002),
        EMPTY_4(40003),
        EMPTY_5(40004),
        EMPTY_6(40005),
        PINK_BEAN_EMPTY_0(800000),
        PINK_BEAN_EMPTY_1(800001),
        PINK_BEAN_EMPTY_2(800002),
        PINK_BEAN_EMPTY_3(800003),
        PINK_BEAN_EMPTY_4(800004),
        PINK_BEAN_EMPTY_5(800010),
        PINK_BEAN_EMPTY_6(800011),
        PINK_BEAN_EMPTY_7(800012),
        PINK_BEAN_EMPTY_8(800013),
        PINK_BEAN_EMPTY_9(800014),
        PINK_BEAN_EMPTY_10(800015),
        PINK_BEAN_EMPTY_11(800016),
        PINK_BEAN_EMPTY_12(800017),
        PINK_BEAN_EMPTY_13(800018),
        PINK_BEAN_EMPTY_14(800019),
        PINK_BEAN_EMPTY_15(800022);

        private short jobId;

        JobEnum(short jobId) {
            this.jobId = jobId;
        }

        JobEnum(int jobId) {
            this((short) jobId);
        }

        public short getJobId() {
            return jobId;
        }

        public static JobEnum getJobById(short id) {
            return Util.findWithPred(values(), j -> j.getJobId() == id);
        }

        public Set<WeaponType> getUsingWeapons() {
            Set<WeaponType> wts = new HashSet<>();
            switch (this) {
                case BEGINNER:
                case WARRIOR:
                case NOBLESSE:
                case LEGEND:
                case CITIZEN:
                    wts.add(WeaponType.OneHandedSword);
                    wts.add(WeaponType.OneHandedAxe);
                    wts.add(WeaponType.OneHandedMace);
                    wts.add(WeaponType.TwoHandedSword);
                    wts.add(WeaponType.TwoHandedAxe);
                    wts.add(WeaponType.TwoHandedMace);
                    break;
                case FIGHTER:
                case CRUSADER:
                case HERO:
                    wts.add(WeaponType.OneHandedSword);
                    wts.add(WeaponType.OneHandedAxe);
                    wts.add(WeaponType.TwoHandedSword);
                    wts.add(WeaponType.TwoHandedAxe);
                    break;
                case PAGE:
                case WHITEKNIGHT:
                case PALADIN:
                    wts.add(WeaponType.OneHandedSword);
                    wts.add(WeaponType.OneHandedMace);
                    wts.add(WeaponType.TwoHandedSword);
                    wts.add(WeaponType.TwoHandedMace);
                    break;
                case SPEARMAN:
                case DRAGONKNIGHT:
                case DARKKNIGHT:
                    wts.add(WeaponType.Spear);
                    wts.add(WeaponType.Polearm);
                    break;
                case MAGICIAN:
                case FP_WIZARD:
                case FP_MAGE:
                case FP_ARCHMAGE:
                case IL_WIZARD:
                case IL_MAGE:
                case IL_ARCHMAGE:
                case CLERIC:
                case PRIEST:
                case BISHOP:
                case EVAN_NOOB:
                case EVAN1:
                case EVAN2:
                case EVAN3:
                case EVAN4:
                case BLAZEWIZARD1:
                case BLAZEWIZARD2:
                case BLAZEWIZARD3:
                case BLAZEWIZARD4:
                    wts.add(WeaponType.Wand);
                    wts.add(WeaponType.Staff);
                    break;
                case BOWMAN:
                case HUNTER:
                case RANGER:
                case BOWMASTER:
                case WINDARCHER1:
                case WINDARCHER2:
                case WINDARCHER3:
                case WINDARCHER4:
                    wts.add(WeaponType.Bow);
                    break;
                case CROSSBOWMAN:
                case SNIPER:
                case MARKSMAN:
                case WILD_HUNTER_1:
                case WILD_HUNTER_2:
                case WILD_HUNTER_3:
                case WILD_HUNTER_4:
                    wts.add(WeaponType.Crossbow);
                    break;
                case THIEF:
                    wts.add(WeaponType.Dagger);
                    wts.add(WeaponType.Claw);
                    break;
                case ASSASSIN:
                case HERMIT:
                case NIGHTLORD:
                case NIGHTWALKER1:
                case NIGHTWALKER2:
                case NIGHTWALKER3:
                case NIGHTWALKER4:
                    wts.add(WeaponType.Claw);
                    break;
                case BANDIT:
                case CHIEFBANDIT:
                case SHADOWER:
                case BLADE_RECRUIT:
                case BLADE_ACOLYTE:
                case BLADE_SPECIALIST:
                case BLADE_LORD:
                case BLADE_MASTER:
                    wts.add(WeaponType.Dagger);
                    break;
                case PIRATE:
                    wts.add(WeaponType.Knuckle);
                    wts.add(WeaponType.Gun);
                    break;
                case BRAWLER:
                case MARAUDER:
                case BUCCANEER:
                case SHADE:
                case SHADE1:
                case SHADE2:
                case SHADE3:
                case SHADE4:
                case THUNDERBREAKER1:
                case THUNDERBREAKER2:
                case THUNDERBREAKER3:
                case THUNDERBREAKER4:
                    wts.add(WeaponType.Knuckle);
                    break;
                case GUNSLINGER:
                case OUTLAW:
                case CORSAIR:
                case JETT1:
                case JETT2:
                case JETT3:
                case JETT4:
                case MECHANIC_1:
                case MECHANIC_2:
                case MECHANIC_3:
                case MECHANIC_4:
                    wts.add(WeaponType.Gun);
                    break;
                case PIRATE_CANNONNEER:
                case CANNONEER:
                case CANNON_BLASTER:
                case CANNON_MASTER:
                    wts.add(WeaponType.HandCannon);
                    break;
                case DAWNWARRIOR1:
                case DAWNWARRIOR2:
                case DAWNWARRIOR3:
                case DAWNWARRIOR4:
                case KAISER:
                case KAISER1:
                case KAISER2:
                case KAISER3:
                case KAISER4:
                    wts.add(WeaponType.TwoHandedSword);
                    break;
                case ARAN1:
                case ARAN2:
                case ARAN3:
                case ARAN4:
                    wts.add(WeaponType.Polearm);
                    break;
                case MERCEDES:
                case MERCEDES1:
                case MERCEDES2:
                case MERCEDES3:
                case MERCEDES4:
                    wts.add(WeaponType.DualBowgun);
                    break;
                case PHANTOM:
                case PHANTOM1:
                case PHANTOM2:
                case PHANTOM3:
                case PHANTOM4:
                    wts.add(WeaponType.Cane);
                    break;
                case LUMINOUS:
                case LUMINOUS1:
                case LUMINOUS2:
                case LUMINOUS3:
                case LUMINOUS4:
                    wts.add(WeaponType.ShiningRod);
                    break;
                case DEMON_SLAYER:
                    wts.add(WeaponType.OneHandedAxe);
                    wts.add(WeaponType.OneHandedMace);
                    wts.add(WeaponType.Desperado);
                    break;
                case DEMON_SLAYER1:
                case DEMON_SLAYER2:
                case DEMON_SLAYER3:
                case DEMON_SLAYER4:
                    wts.add(WeaponType.OneHandedAxe);
                    wts.add(WeaponType.OneHandedMace);
                    break;
                case DEMON_AVENGER1:
                case DEMON_AVENGER2:
                case DEMON_AVENGER3:
                case DEMON_AVENGER4:
                    wts.add(WeaponType.Desperado);
                    break;
                case BATTLE_MAGE_1:
                case BATTLE_MAGE_2:
                case BATTLE_MAGE_3:
                case BATTLE_MAGE_4:
                    wts.add(WeaponType.Staff);
                    break;
                case BLASTER_1:
                case BLASTER_2:
                case BLASTER_3:
                case BLASTER_4:
                    wts.add(WeaponType.ArmCannon);
                    break;
                case XENON:
                case XENON1:
                case XENON2:
                case XENON3:
                case XENON4:
                    wts.add(WeaponType.ChainSword);
                    break;
                case HAYATO:
                case HAYATO1:
                case HAYATO2:
                case HAYATO3:
                case HAYATO4:
                    wts.add(WeaponType.Katana);
                    break;
                case KANNA:
                case KANNA1:
                case KANNA2:
                case KANNA3:
                case KANNA4:
                    wts.add(WeaponType.Fan);
                    break;
                case NAMELESS_WARDEN:
                case MIHILE1:
                case MIHILE2:
                case MIHILE3:
                case MIHILE4:
                    wts.add(WeaponType.OneHandedSword);
                    break;
                case ANGELIC_BUSTER:
                case ANGELIC_BUSTER1:
                case ANGELIC_BUSTER2:
                case ANGELIC_BUSTER3:
                case ANGELIC_BUSTER4:
                    wts.add(WeaponType.SoulShooter);
                    break;
                case ZERO:
                case ZERO1:
                case ZERO2:
                case ZERO3:
                case ZERO4:
                    wts.add(WeaponType.LongSword);
                    wts.add(WeaponType.BigSword);
                    break;
                case BEAST_TAMER:
                case BEAST_TAMER_1:
                case BEAST_TAMER_2:
                case BEAST_TAMER_3:
                case BEAST_TAMER_4:
                    wts.add(WeaponType.Scepter);
                    break;
                case KINESIS_0:
                case KINESIS_1:
                case KINESIS_2:
                case KINESIS_3:
                case KINESIS_4:
                    wts.add(WeaponType.PsyLimiter);
                    break;
            }
            return wts;
        }
    }

    public enum LoginJob {
        RESISTANCE(0, JobFlag.ENABLED, JobEnum.CITIZEN),
        EXPLORER(1, JobFlag.ENABLED, JobEnum.BEGINNER),
        CYGNUS(2, JobFlag.ENABLED, JobEnum.NOBLESSE),
        ARAN(3, JobFlag.ENABLED, JobEnum.LEGEND),
        EVAN(4, JobFlag.ENABLED, JobEnum.EVAN_NOOB),
        MERCEDES(5, JobFlag.ENABLED, JobEnum.MERCEDES),
        DEMON(6, JobFlag.ENABLED, JobEnum.DEMON_SLAYER),
        PHANTOM(7, JobFlag.ENABLED, JobEnum.PHANTOM),
        DUAL_BLADE(8, JobFlag.ENABLED, JobEnum.BEGINNER),
        MIHILE(9, JobFlag.ENABLED, JobEnum.NAMELESS_WARDEN),
        LUMINOUS(10, JobFlag.ENABLED, JobEnum.LUMINOUS),
        KAISER(11, JobFlag.ENABLED, JobEnum.KAISER),
        ANGELIC(12, JobFlag.ENABLED, JobEnum.ANGELIC_BUSTER),
        CANNONER(13, JobFlag.ENABLED, JobEnum.BEGINNER),
        XENON(14, JobFlag.ENABLED, JobEnum.XENON),
        ZERO(15, JobFlag.ENABLED, JobEnum.ZERO),
        SHADE(16, JobFlag.ENABLED, JobEnum.SHADE),
        JETT(17, JobFlag.ENABLED, JobEnum.BEGINNER),
        HAYATO(18, JobFlag.ENABLED, JobEnum.HAYATO),
        KANNA(19, JobFlag.ENABLED, JobEnum.KANNA),
        CHASE(20, JobFlag.ENABLED, JobEnum.BEAST_TAMER),
        PINK_BEAN(21, JobFlag.ENABLED, JobEnum.PINK_BEAN_0),
        KINESIS(22, JobFlag.ENABLED, JobEnum.KINESIS_0);

        private final int jobType, flag;
        private JobEnum beginJob;

        LoginJob(int jobType, JobFlag flag, JobEnum beginJob) {
            this.jobType = jobType;
            this.flag = flag.getFlag();
            this.beginJob = beginJob;
        }

        public int getJobType() {
            return jobType;
        }

        public int getFlag() {
            return flag;
        }

        public JobEnum getBeginJob() {
            return beginJob;
        }

        public enum JobFlag {

            DISABLED(0),
            ENABLED(1);
            private final int flag;

            JobFlag(int flag) {
                this.flag = flag;
            }

            public int getFlag() {
                return flag;
            }
        }

        public static LoginJob getLoginJobById(int id) {
            return Arrays.stream(LoginJob.values()).filter(j -> j.getJobType() == id).findFirst().orElse(null);
        }
    }

    public static void encode(OutPacket outPacket) {
        outPacket.encodeByte(enableJobs);
        outPacket.encodeByte(jobOrder);
        for (LoginJob loginJobId : LoginJob.values()) {
            outPacket.encodeByte(loginJobId.getFlag());
            outPacket.encodeShort(loginJobId.getFlag());
        }
    }

    public static boolean isAdventurerWarrior(short jobId) {
        return jobId == 100
                || jobId == 110
                || jobId == 111
                || jobId == 112
                || jobId == 120
                || jobId == 121
                || jobId == 122
                || jobId == 130
                || jobId == 131
                || jobId == 132;
    }

    public static boolean isAdventurerMage(short jobId) {
        return jobId == 200
                || jobId == 210
                || jobId == 211
                || jobId == 212
                || jobId == 220
                || jobId == 221
                || jobId == 222
                || jobId == 230
                || jobId == 231
                || jobId == 232;
    }

    public static boolean isAdventurerArcher(short jobId) {
        return jobId == 300 || jobId == 310 || jobId == 311 || jobId == 312 || jobId == 320 || jobId == 321 || jobId == 322;
    }

    public static boolean isAdventurerThief(short jobId) {
        return jobId == 400
                || jobId == 420
                || jobId == 421
                || jobId == 422
                || jobId == 410
                || jobId == 411
                || jobId == 412
                || jobId / 10 == 43;
    }

    public static boolean isAdventurerPirate(short jobId) {
        return jobId == 500
                || jobId == 510
                || jobId == 511
                || jobId == 512
                || jobId == 520
                || jobId == 521
                || jobId == 522
                || isCannonShooter(jobId);
    }

    public static boolean isCannonShooter(short jobId) {
        return jobId / 10 == 53 || jobId == 501;
    }

    public static boolean isCygnus(short jobId) {
        return jobId / 1000 == 1;
    }

    public static boolean isResistance(short jobId) {
        return jobId / 1000 == 3;
    }

    public static boolean isEvan(short jobId) {
        return jobId / 100 == 22 || jobId == 2001;
    }

    public static boolean isMercedes(short jobId) {
        return jobId / 100 == 23 || jobId == 2002;
    }

    public static boolean isPhantom(short jobId) {
        return jobId / 100 == 24 || jobId == 2003;
    }

    public static boolean isLeader(short jobId) {
        return jobId / 1000 == 5;
    }

    public static boolean isLuminous(short jobId) {
        return jobId / 100 == 27 || jobId == 2004;
    }

    public static boolean isKaiser(short jobId) {
        return jobId == JobConstants.JobEnum.KAISER.getJobId() || jobId / 100 == 61;
    }

    public static boolean isZero(short jobId) {
        return jobId == 10000 || jobId == 10100 || jobId == 10110 || jobId == 10111 || jobId == 10112;
    }

    public static boolean isHidden(short jobId) {
        return jobId / 100 == 25 || jobId == 2005;
    }

    public static boolean isAran(short jobId) {
        return jobId / 100 == 21 || jobId == 2000;
    }

    public static boolean isKinesis(short jobId) {
        return jobId == 14000 || jobId == 14200 || jobId == 14210 || jobId == 14211 || jobId == 14212;
    }

    public static boolean isExtendSpJob(short jobId) {
        return !isBeastTamer(jobId) && !isPinkBean(jobId);
    }

    public static boolean isDemon(short jobId) {
        return jobId / 100 == 31 || jobId == 3001;
    }

    public static boolean isBeginnerJob(short jobId) {
        switch (jobId) {
            case 8001:
            case 13000:
            case 14000:
            case 6000:
            case 6001:
            case 5000:
            case 4001:
            case 4002:
            case 3001:
            case 3002:
            case 2001:
            case 2002:
            case 2003:
            case 2004:
            case 2005:
                return true;
            default:
                return jobId % 1000 == 0 || jobId / 100 == 8000;
        }
    }

    public static int getJobLevel(short jobId) {
        int prefix;
        if (isBeginnerJob(jobId) || (jobId % 100 == 0) || jobId == 501 || jobId == 3101) {
            return 1;
        }
        if (isEvan(jobId)) {
            return getEvanJobLevel(jobId);
        }
        if (isDualBlade(jobId)) {
            prefix = (jobId % 10) + 2;
            if (prefix < 2) {
                return 0;
            } else if (prefix <= 6) {
                return jobId % 10 + 2;
            }
        } else {
            prefix = jobId % 10;
        }
        return prefix <= 2 ? prefix + 2 : 0;
    }

    public static int getJobLevelByCharLevel(short job, int charLevel) {
        if (JobConstants.isDualBlade(job)) {
            if (charLevel <= 10) {
                return 0;
            } else if (charLevel <= 20) {
                return 1;
            } else if (charLevel <= 30) {
                return 2;
            } else if (charLevel <= 45) {
                return 3;
            } else if (charLevel <= 60) {
                return 4;
            } else if (charLevel <= 100) {
                return 5;
            } else {
                return 6;
            }
        }
        if (charLevel <= 10) {
            return 0;
        } else if (charLevel <= 30) {
            return 1;
        } else if (charLevel <= 60) {
            return 2;
        } else if (charLevel <= 100) {
            return 3;
        } else {
            return 4;
        }
    }

    private static int getEvanJobLevel(short jobId) {
        int result;
        switch (jobId) {
            case 2200:
            case 2210:
                result = 1;
                break;
            case 2211:
            case 2212:
            case 2213:
                result = 2;
                break;
            case 2214:
            case 2215:
            case 2216:
                result = 3;
                break;
            case 2217:
            case 2218:
                result = 4;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

    public static boolean isNoManaJob(short job) {
        return isDemon(job) || isAngelicBuster(job) || isZero(job) || isKinesis(job) || isKanna(job);
    }

    public boolean isWarriorEquipJob(short jobID) {
        return isAdventurerWarrior(jobID) || isPinkBean(jobID) || isDawnWarrior(jobID) || isMihile(jobID) ||
                isAran(jobID) || isKaiser(jobID) || isBlaster(jobID) || isDemon(jobID) || isHayato(jobID) ||
                isZero(jobID);

    }

    public boolean isMageEquipJob(short jobID) {
        return isBeastTamer(jobID) || isKinesis(jobID) || isAdventurerMage(jobID) || isBlazeWizard(jobID) ||
                isEvan(jobID) || isLuminous(jobID) || isBattleMage(jobID) || isKanna(jobID);
    }

    public boolean isArcherEquipJob(short jobID) {
        return isAdventurerArcher(jobID) || isWindArcher(jobID) || isMercedes(jobID) || isWildHunter(jobID);
    }

    public boolean isThiefEquipJob(short jobID) {
        return isAdventurerThief(jobID) || isNightWalker(jobID) || isPhantom(jobID) || isXenon(jobID);
    }

    public boolean isPirateEquipJob(short jobID) {
        return isAdventurerPirate(jobID) || isThunderBreaker(jobID) || isShade(jobID) || isAngelicBuster(jobID) ||
                isXenon(jobID) || isMechanic(jobID) || isJett(jobID);
    }

}
