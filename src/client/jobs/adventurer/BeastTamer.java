package client.jobs.adventurer;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.AffectedArea;
import client.life.Mob;
import client.life.MobTemporaryStat;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.MoveAbility;
import loaders.SkillData;
import packet.WvsContext;
import util.Position;
import util.Util;

import java.util.Arrays;
import java.util.Random;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class BeastTamer extends Job {
    //Common
    public static final int HOMEWARD_BOUND = 110001514;
    public static final int MAPLE_GUARDIAN = 110001511;
    public static final int BEAR_MODE = 110001501;
    public static final int SNOW_LEOPARD_MODE = 110001502;
    public static final int HAWK_MODE = 110001503;
    public static final int CAT_MODE = 110001504;

    //Bear Mode
    public static final int LIL_FORT = 112001007;
    public static final int FORT_FOLLOW_UP = 112000015;
    public static final int MAJESTIC_TRUMPET = 112001006;
    public static final int BEAR_ASSAULT = 112001009;

    //Snow Leopard Mode
    public static final int BRO_ATTACK = 112101016;
    public static final int THUNDER_DASH = 112101007;
    public static final int ADV_THUNDER_DASH = 112100012;
    public static final int THUNDER_TRAIL = 112100008; //tile

    //Hawk Mode
    public static final int EKA_EXPRESS = 112111010;    //Door skill
    public static final int FLY = 112111000;
    public static final int HAWK_FLOCK = 112111007;
    public static final int RAPTOR_TALONS = 112111006;
    public static final int BIRDS_EYE_VIEW = 112111009;
    public static final int RAZOR_BEAK = 112111008;
    public static final int REGROUP = 112111011;    //Warp Party to player
    public static final int DEFENSIVE_FORMATION = 112110005;

    //Cat Mode
    public static final int MEOW_HEAL = 112121013;
    public static final int PURR_ZONE = 112121005; //Tile
    public static final int MEOW_CARD = 112121006; //Meow Card
    public static final int MEOW_CARD_RED = 112121007; //Red
    public static final int MEOW_CARD_BLUE = 112121008; //Blue
    public static final int MEOW_CARD_GREEN = 112121009; //Green
    public static final int MEOW_CARD_GOLD = 112120009; //Gold
    public static final int FIRE_KITTY = 112121004;
    public static final int CATS_CRADLE_BLITZKRIEG = 112121057;
    public static final int KITTY_BATTLE_SQUAD = 112120021;
    public static final int KITTY_TREATS = 112120023;
    public static final int STICKY_PAWS = 112120017;
    public static final int CAT_CLAWS = 112120018;
    public static final int MOUSERS_INSIGHT = 112120022;
    public static final int FRIENDS_OF_ARBY = 112120016;
    public static final int MEOW_CURE = 112121010;


    //Hyper
    public static final int TEAM_ROAR = 112121056;

    private int[] buffs = new int[]{
            MAPLE_GUARDIAN,
            BEAR_MODE,
            SNOW_LEOPARD_MODE,
            HAWK_MODE,
            CAT_MODE,

            LIL_FORT,
            BEAR_ASSAULT,

            BRO_ATTACK,

            EKA_EXPRESS,
            FLY,
            HAWK_FLOCK,
            RAPTOR_TALONS,
            BIRDS_EYE_VIEW,
            RAZOR_BEAK,
            DEFENSIVE_FORMATION,

            MEOW_CARD,
            MEOW_CARD_RED,
            MEOW_CARD_BLUE,
            MEOW_CARD_GREEN,
            MEOW_CARD_GOLD,
            KITTY_BATTLE_SQUAD,
            KITTY_TREATS,
            STICKY_PAWS,
            CAT_CLAWS,
            MOUSERS_INSIGHT,
            FRIENDS_OF_ARBY,

            TEAM_ROAR,
    };

    private int[] addedSkills = new int[]{
            BEAR_MODE,
            SNOW_LEOPARD_MODE,
            HAWK_MODE,
            CAT_MODE,
            HOMEWARD_BOUND,
    };

    public BeastTamer(Char chr) {
        super(chr);
        if (isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
        }
    }

    private void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            //Common
            case BEAR_MODE:
            case SNOW_LEOPARD_MODE:
            case HAWK_MODE:
            case CAT_MODE:
                o1.nOption = (skillID - 110001500);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(BeastMode, o1);
                break;
            case MAPLE_GUARDIAN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;

            //Bear Mode
            case LIL_FORT:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setSummonTerm(si.getValue(time, slv));
                summon.setMoveAbility(MoveAbility.STATIC.getVal());
                field.spawnSummon(summon);
                break;
            case BEAR_ASSAULT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(q, slv);
                tsm.putCharacterStatValue(DamR, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(q, slv);
                tsm.putCharacterStatValue(IncCriticalDamMin, o2);
                o3.nOption = si.getValue(z, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(q, slv);
                tsm.putCharacterStatValue(CriticalBuff, o3);
                o4.nOption = si.getValue(mobCount, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(q, slv);
                tsm.putCharacterStatValue(Enrage, o4);
                break;

            //Leopard Mode
            case BRO_ATTACK:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ACC, o1);
                break;

            //Hawk Mode
            case FLY:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(NewFlying, o1);

                if (chr.hasSkill(DEFENSIVE_FORMATION)) {
                    summon = Summon.getSummonBy(c.getChr(), DEFENSIVE_FORMATION, slv);
                    field = c.getChr().getField();
                    summon.setFlyMob(true);
                    summon.setSummonTerm(si.getValue(time, slv));
                    summon.setMoveAbility(MoveAbility.FOLLOW.getVal());
                    field.spawnSummon(summon);  //TODO
                }
                break;
            case HAWK_FLOCK:
                o1.nOption = si.getValue(speed, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(jump, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2);
                break;
            case RAPTOR_TALONS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMAD, o1);
                break;
            case BIRDS_EYE_VIEW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieCr, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o1);
                o2.nOption = si.getValue(emdd, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMDD, o2);
                o3.nOption = si.getValue(epdd, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EPDD, o3);
                o4.nOption = si.getValue(acc, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ACC, o4);
                o5.nOption = si.getValue(eva, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVA, o5);
                break;
            case RAZOR_BEAK:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMAD, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;

            //Cat Mode
            case MEOW_CARD:
                handleMeowCard(slv);    //TODO
                break;

            //Hyper
            case TEAM_ROAR:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nOption = 1;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o2);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TeamRoar, o3);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillID = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        byte slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = (byte) skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }

        if (tsm.getOption(BeastMode).nOption == 2) { // Leopard
            if (hasHitMobs) {
                if(skillID != 112101016) {
                    handleBroAttack(skillID, slv, attackInfo);
                }
            }
        }

        if (tsm.getOption(BeastMode).nOption == 3) { // Hawk
            if (hasHitMobs) {
                handleRaptorTalons(slv, attackInfo);
            }
        }

        if (tsm.getOption(BeastMode).nOption == 4) { // Cat
            handleKittyBattleSquad();
            handleKittyTreats();
            handleStickyPaws();
            handleCatClaws();
            handleMouserInsight();
            handleFriendsOfArby();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case MAJESTIC_TRUMPET:
                SkillInfo rca = SkillData.getSkillInfoById(skillID);
                AffectedArea aa = AffectedArea.getAffectedArea(attackInfo);
                aa.setMobOrigin((byte) 0);
                aa.setCharID(chr.getId());
                aa.setSkillID(skillID);
                int x = chr.getPosition().getX();
                int y = chr.getPosition().getY() + 41;
                aa.setPosition(new Position(x, y));
                aa.setRect(aa.getPosition().getRectAround(rca.getRects().get(0)));
                aa.setDelay((short) 4);
                chr.getField().spawnAffectedArea(aa);
                break;
            case THUNDER_DASH:
            case ADV_THUNDER_DASH:
                SkillInfo tdi = SkillData.getSkillInfoById(THUNDER_TRAIL);
                AffectedArea aa2 = AffectedArea.getAffectedArea(attackInfo);
                aa2.setMobOrigin((byte) 0);
                aa2.setCharID(chr.getId());
                aa2.setSkillID(THUNDER_TRAIL);
                //int x = chr.getPosition().getX();
                //int y = chr.getPosition().getY() + 41;
                //aa.setPosition(new Position(x, y));
                aa2.setPosition(chr.getPosition());
                aa2.setRect(aa2.getPosition().getRectAround(tdi.getRects().get(0)));
                aa2.setDelay((short) 4);
                chr.getField().spawnAffectedArea(aa2);
                break;
            case PURR_ZONE: //TODO
                SkillInfo pz = SkillData.getSkillInfoById(PURR_ZONE);
                AffectedArea aa3 = AffectedArea.getAffectedArea(attackInfo);
                aa3.setMobOrigin((byte) 0);
                aa3.setCharID(chr.getId());
                aa3.setSkillID(skillID);
                aa3.setPosition(chr.getPosition());
                aa3.setRect(aa3.getPosition().getRectAround(pz.getRects().get(0)));
                aa3.setSlv((byte)skill.getCurrentLevel());
                chr.getField().spawnAffectedArea(aa3);
                break;
        }
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            switch (skillID) {
                case HOMEWARD_BOUND:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
                case MEOW_CURE:
                    tsm.removeAllDebuffs();
                    break;
                case MEOW_HEAL:
                    chr.heal((int) (chr.getMaxHP() / ((double) 100 / si.getValue(hp, slv))));
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case BEAST_TAMER:
            case BEAST_TAMER_1:
            case BEAST_TAMER_2:
            case BEAST_TAMER_3:
            case BEAST_TAMER_4:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    public void handleBroAttack(int skillID, byte slv, AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.getOptByCTSAndSkill(ACC, BRO_ATTACK) != null) {
            Summon summon;
            Field field;
            SkillInfo si = SkillData.getSkillInfoById(BRO_ATTACK);
            int summonProp = si.getValue(prop, slv);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (Util.succeedProp(summonProp)) {
                    summon = Summon.getSummonBy(c.getChr(), BRO_ATTACK, slv);
                    field = c.getChr().getField();
                    summon.setFlyMob(false);
                    summon.setPosition(mob.getPosition());
                    summon.setSummonTerm(si.getValue(x, slv));
                    summon.setMoveAbility(MoveAbility.SLOW_FORWARD.getVal());
                    field.spawnAddSummon(summon);
                }
            }
        }
    }

    public void handleRaptorTalons(byte slv, AttackInfo attackInfo) {
        Option o1 = new Option();
        Skill skill = chr.getSkill(attackInfo.skillId);
        SkillInfo si = SkillData.getSkillInfoById(RAPTOR_TALONS);
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            MobTemporaryStat mts = mob.getTemporaryStat();
            if (Util.succeedProp(si.getValue(prop, slv))) {
                mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
            }
        }
    }

    public void handleMeowCard(byte slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo mc = SkillData.getSkillInfoById(MEOW_CARD);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();

        switch (getMeowCardChance()) {
            case MEOW_CARD_RED:
                o1.nReason = MEOW_CARD_RED;
                o1.nValue = mc.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case MEOW_CARD_GREEN:
                o1.nReason = MEOW_CARD_GREEN;
                o1.nValue = mc.getValue(indieBooster, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o1.nReason = MEOW_CARD_GREEN;
                o1.nValue = mc.getValue(indieSpeed, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieSpeed, o1);
                break;
            case MEOW_CARD_BLUE:
                o1.nReason = MEOW_CARD_BLUE;
                o1.nValue = mc.getValue(pdd, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePDD, o1);
                break;
            case MEOW_CARD_GOLD:
                o1.nReason = MEOW_CARD_GOLD;
                o1.nValue = mc.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = MEOW_CARD_GOLD;
                o2.nValue = mc.getValue(indieBooster, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o2);
                o3.nReason = MEOW_CARD_GOLD;
                o3.nValue = mc.getValue(indieSpeed, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieSpeed, o3);
                o4.nReason = MEOW_CARD_GOLD;
                o4.nValue = mc.getValue(pdd, slv);
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePDD, o4);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public int getMeowCardChance() {
        int rng = new Random().nextInt(100);
        if (chr.hasSkill(MEOW_CARD_GOLD)) {

            if (rng > 0 && rng <= 25) {
                return MEOW_CARD_RED;
            } else if (rng > 25 && rng <= 50) {
                return MEOW_CARD_GREEN;
            } else if (rng > 50 && rng <= 75) {
                return MEOW_CARD_BLUE;
            } else if (rng > 75 && rng <= 100) {
                return MEOW_CARD_GOLD;
            }

        } else {

            if (rng > 0 && rng <= 33) {
                return MEOW_CARD_RED;
            } else if (rng > 33 && rng <= 66) {
                return MEOW_CARD_GREEN;
            } else if (rng > 66 && rng <= 100) {
                return MEOW_CARD_BLUE;
            }

        }
        return 0;
    }

    public void handleKittyBattleSquad() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(KITTY_BATTLE_SQUAD);
        int slv = si.getCurrentLevel();
        o1.nReason = KITTY_BATTLE_SQUAD;
        o1.nValue = si.getValue(indiePad, slv);
        o1.tStart = (int) System.currentTimeMillis();
        o1.tTerm = 0;
        tsm.putCharacterStatValue(IndiePAD, o1);
        tsm.putCharacterStatValue(IndieMAD, o1);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public void handleKittyTreats() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(KITTY_TREATS);
        int slv = si.getCurrentLevel();
        o1.nReason = KITTY_TREATS;
        o1.nValue = si.getValue(indieMhp, slv);
        o1.tStart = (int) System.currentTimeMillis();
        o1.tTerm = 0;
        tsm.putCharacterStatValue(IndieMHP, o1);
        o2.nReason = KITTY_TREATS;
        o2.nValue = si.getValue(indieMmp, slv);
        o2.tStart = (int) System.currentTimeMillis();
        o2.tTerm = 0;
        tsm.putCharacterStatValue(IndieMMP, o2);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public void handleStickyPaws() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(STICKY_PAWS);
        int slv = si.getCurrentLevel();
        o1.nOption = si.getValue(v, slv);
        o1.rOption = STICKY_PAWS;
        o1.tOption = 0;
        tsm.putCharacterStatValue(DropRate, o1);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public void handleCatClaws() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(CAT_CLAWS);
        int slv = si.getCurrentLevel();
        o1.nOption = si.getValue(x, slv);
        o1.rOption = CAT_CLAWS;
        o1.tOption = 0;
        tsm.putCharacterStatValue(CriticalBuff, o1);
        o2.nOption = si.getValue(y, slv);
        o2.rOption = CAT_CLAWS;
        o2.tOption = 0;
        tsm.putCharacterStatValue(IncCriticalDamMin, o2);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public void handleMouserInsight() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(MOUSERS_INSIGHT);
        int slv = si.getCurrentLevel();
        o1.nOption = si.getValue(x, slv);
        o1.rOption = MOUSERS_INSIGHT;
        o1.tOption = 0;
        tsm.putCharacterStatValue(IgnoreMobpdpR, o1);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public void handleFriendsOfArby() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(FRIENDS_OF_ARBY);
        int slv = si.getCurrentLevel();
        o1.nOption = si.getValue(x, slv);
        o1.rOption = FRIENDS_OF_ARBY;
        o1.tOption = 0;
        tsm.putCharacterStatValue(HolySymbol, o1);
        c.write(WvsContext.temporaryStatSet(tsm));
    }


}
