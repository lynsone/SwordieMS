package constants;

import connection.OutPacket;

import java.util.Arrays;

/**
 *
 * @author Itzik
 */
public class JobConstants {

    public static final boolean enableJobs = true;
    public static final int jobOrder = 8;

    public static boolean isXenon(short jobId) {
        return jobId / 100 == 36 || jobId == 3002;
    }

    public static boolean isBeastTamer(short job) {
        return job == JobEnum.BEAST_TAMER_1.getJobId() || job == JobEnum.BEAST_TAMER_2.getJobId() ||
                job == JobEnum.BEAST_TAMER_2.getJobId() || job == JobEnum.BEAST_TAMER_3.getJobId();
    }

    public static JobEnum getJobEnumById(short jobId) {
        return Arrays.stream(JobEnum.values()).filter(job -> job.getJobId() == jobId)
                .findFirst().orElse(null);
    }

    public static boolean isWildHunter(short job) {
        return job / 100 == 33;
    }

    public static boolean isAngelicBuster(int id) {
        return id == JobConstants.JobEnum.ANGELIC_BUSTER.getJobId() ||
                (id >= JobConstants.JobEnum.ANGELIC_BUSTER1.getJobId() && id <= JobConstants.JobEnum.ANGELIC_BUSTER4.getJobId());
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
        CRASH_1(11000),
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
        ;

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
            return Arrays.stream(values()).filter(j -> j.getJobId() == id).findFirst().orElse(null);
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
        CHASE(20, JobFlag.ENABLED, JobEnum.BEAST_TAMER_1),
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
        for(LoginJob loginJobId : LoginJob.values()) {
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
        return jobId / 1000 == 6;
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

    public static boolean isKenesis(short jobId) {
        return jobId == 14000 || jobId == 14200 || jobId == 14210 || jobId == 14211 || jobId == 14212;
    }

    public static boolean isExtendSpJob(short jobId) {
        return isAdventurerWarrior(jobId)
                || isAdventurerMage(jobId)
                || isAdventurerArcher(jobId)
                || isAdventurerThief(jobId)
                || isAdventurerPirate(jobId)
                || isCygnus(jobId)
                || isResistance(jobId)
                || isEvan(jobId)
                || isMercedes(jobId)
                || isPhantom(jobId)
                || isLeader(jobId)
                || isLuminous(jobId)
                || isKaiser(jobId)
                || isZero(jobId)
                || isHidden(jobId)
                || isAran(jobId)
                || isKenesis(jobId);
    }

    public static boolean isDemon(short jobId) {
        return jobId / 100 == 31 || jobId == 3001 || jobId == 3002 || jobId / 100 == 36;
    }

    public static boolean isBeginnerJob(short jobId) {
        if ( jobId > 6001 ) {
            return jobId == 13000 || jobId == 14000;
        }
        if ( jobId >= 6000 )
            return true;
        if ( jobId > 3002 )
        {
            return jobId == 5000;
        }
        if ( jobId >= 3001 || jobId >= 2001 && jobId <= 2005 )
            return true;
        if ( (jobId % 1000 == 0) )
            return true;
        return jobId / 100 == 8000;
    }

    public static int getJobLevel(short jobId) {
        int prefix;
        if ( isBeginnerJob(jobId) || (jobId % 100 == 0) || jobId == 501 || jobId == 3101 )
            return 1;
        if ( isEvan(jobId) )
            return getEvanJobLevel(jobId);
        if ( isDualJob(jobId) )
            prefix = jobId % 10 / 2;
        else
            prefix = jobId % 10;
        return prefix <= 2 ? prefix + 2 : 0;
    }

    private static boolean isDualJob(short jobId) {
        return jobId / 10 == 43;
    }

    private static int getEvanJobLevel(short jobId) {
        int result;
        switch(jobId) {
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

    public boolean isZeroJob(int jobId) {
        return false;
    }

}
