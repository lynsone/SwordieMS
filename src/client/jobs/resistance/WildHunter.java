package client.jobs.resistance;

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
import enums.ChatMsgColour;
import enums.MobStat;
import enums.MoveAbility;
import enums.TSIndex;
import loaders.SkillData;
import packet.UserLocal;
import packet.WvsContext;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class WildHunter extends Job {

    //Jaguar Summon
    public static final int SUMMON_JAGUAR_GREY = 33001007;           //No Special Jaguar Stats
    public static final int SUMMON_JAGUAR_YELLOW = 33001008;         //No Special Jaguar Stats
    public static final int SUMMON_JAGUAR_RED = 33001009;            //No Special Jaguar Stats
    public static final int SUMMON_JAGUAR_PURPLE = 33001010;         //No Special Jaguar Stats
    public static final int SUMMON_JAGUAR_BLUE = 33001011;           //No Special Jaguar Stats
    public static final int SUMMON_JAGUAR_JAIRA = 33001012;          //Critical Rate +5%
    public static final int SUMMON_JAGUAR_SNOW_WHITE = 33001013;     //Buff Duration +10%
    public static final int SUMMON_JAGUAR_ONYX = 33001014;           //Buff Duration +10%
    public static final int SUMMON_JAGUAR_CRIMSON = 33001015;        //Dmg Absorption +10%

    //Jaguar Mount
    public static final int MOUNT_JAGUAR_GREY = 1932015;
    public static final int MOUNT_JAGUAR_YELLOW = 1932030;
    public static final int MOUNT_JAGUAR_RED = 1932031;
    public static final int MOUNT_JAGUAR_PURPLE = 1932032;
    public static final int MOUNT_JAGUAR_BLUE = 1932033;
    public static final int MOUNT_JAGUAR_JAIRA = 1932036;
    public static final int MOUNT_JAGUAR_SNOW_WHITE = 1932100;
    public static final int MOUNT_JAGUAR_ONYX = 1932149;
    public static final int MOUNT_JAGUAR_CRIMSON = 1932215;



    public static final int SECRET_ASSEMBLY = 30001281;
    public static final int CAPTURE = 30001061;
    public static final int CALL_OF_THE_HUNTER = 30001062;

    public static final int RIDE_JAGUAR = 33001001; //Special Buff
    public static final int SWIPE = 33001016 ; //Special Attack (Bite Debuff)
    public static final int WILD_LURE = 33001025 ;
    public static final int ANOTHER_BITE = 33000036;

    public static final int SOUL_ARROW_CROSSBOW = 33101003; //Buff
    public static final int CROSSBOW_BOOSTER = 33101012; //Buff
    public static final int CALL_OF_THE_WILD = 33101005; //Buff
    public static final int DASH_N_SLASH_JAGUAR_SUMMONED = 33101115; //Special Attack (Stun Debuff) + (Bite Debuff)
    public static final int DASH_N_SLASH_JAGUAR_ON = 33101215; //Special Attack (Stun Debuff) + (Bite Debuff)

    public static final int FELINE_BERSERK = 33111007; //Buff
    public static final int BACKSTEP = 33111011; //Special Buff (ON/OFF)
    public static final int HUNTING_ASSISTANT_UNIT = 33111013; //Area of Effect
    public static final int SONIC_ROAR = 33111015; //Special Attack (Bite Debuff)
    public static final int FLURRY = 33110008; //Dodge

    public static final int JAGUAR_SOUL = 33121017; //Special Attack (Stun Debuff) + (Bite Debuff) + (Magic Crash Debuff)
    public static final int DRILL_SALVO = 33121016; //Summon
    public static final int SHARP_EYES = 33121004; //Buff
    public static final int MAPLE_WARRIOR_WH = 33121007; //Buff

    //Final Attack
    public static final int FINAL_ATTACK_WH = 33100009;
    public static final int ADVANCED_FINAL_ATTACK_WH = 33120011;

    public static final int FOR_LIBERTY_WH = 33121053;
    public static final int SILENT_RAMPAGE = 33121054;

    private int[] addedSkills = new int[] {
            SECRET_ASSEMBLY,
            CAPTURE,
            CALL_OF_THE_HUNTER,
    };

    private int[] buffs = new int[] {
            SUMMON_JAGUAR_GREY,
            SUMMON_JAGUAR_YELLOW,
            SUMMON_JAGUAR_RED,
            SUMMON_JAGUAR_PURPLE,
            SUMMON_JAGUAR_BLUE,
            SUMMON_JAGUAR_JAIRA,
            SUMMON_JAGUAR_SNOW_WHITE,
            SUMMON_JAGUAR_ONYX,
            SUMMON_JAGUAR_CRIMSON,

            RIDE_JAGUAR,

            SOUL_ARROW_CROSSBOW,
            CROSSBOW_BOOSTER,
            CALL_OF_THE_WILD,
            FELINE_BERSERK,
            BACKSTEP,
            SHARP_EYES,
            MAPLE_WARRIOR_WH,
            FOR_LIBERTY_WH,
            SILENT_RAMPAGE,
            DRILL_SALVO,
    };

    private int lastUsedSkill = 0;

    public WildHunter(Char chr) {
        super(chr);
        if(isHandlerOfJob(chr.getJob())) {
            if (chr.getWildHunterInfo() == null) {
                chr.setWildHunterInfo(new WildHunterInfo());
            }
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
        }
    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case SUMMON_JAGUAR_GREY:
            case SUMMON_JAGUAR_YELLOW:
            case SUMMON_JAGUAR_RED:
            case SUMMON_JAGUAR_PURPLE:
            case SUMMON_JAGUAR_BLUE:
            case SUMMON_JAGUAR_JAIRA:
            case SUMMON_JAGUAR_SNOW_WHITE:
            case SUMMON_JAGUAR_ONYX:
            case SUMMON_JAGUAR_CRIMSON:
                summon = Summon.getSummonBy(chr, skillID, (byte) 1);
                summon.setSummonTerm(0);
                field = c.getChr().getField();
                field.spawnSummon(summon);
                c.write(UserLocal.jaguarActive(true));
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(JaguarSummoned, o1);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(JaguarCount, o1);
                break;
            case RIDE_JAGUAR:
                o1.nOption = 0;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(JaguarSummoned, o1);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(JaguarCount, o1);
//                o1.nOption =
                break;
            case SOUL_ARROW_CROSSBOW:
                summon = Summon.getSummonBy(chr, SUMMON_JAGUAR_JAIRA, (byte) 1);
                summon.setSummonTerm(0);
                field = c.getChr().getField();
                field.spawnSummon(summon);
                c.write(UserLocal.jaguarActive(true));

                o1.nOption = 10; //si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SoulArrow, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;
            case CROSSBOW_BOOSTER:

                TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);
                tsb.setNOption(MOUNT_JAGUAR_CRIMSON);
                tsb.setROption(RIDE_JAGUAR);
                tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
                tsm.sendSetStatPacket();

                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case CALL_OF_THE_WILD:
                o1.nReason = skillID;
                o1.nValue = si.getValue(z, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o1);
                tsm.putCharacterStatValue(IndieMADR, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o2);
                tsm.putCharacterStatValue(EVAR, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(x, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMPR, o3);
                break;
            case FELINE_BERSERK:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieBooster, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o1.nOption = si.getValue(z, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o1);
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                break;
            case BACKSTEP:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(DrawBack, o1);
                break;
            case SHARP_EYES: // x = crit rate%  |  y = max crit dmg%
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SharpEyes, o1);
                break;
            case MAPLE_WARRIOR_WH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;

            case FOR_LIBERTY_WH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOverR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOverR, o2);
                break;

            case SILENT_RAMPAGE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case DRILL_SALVO:
                summon = Summon.getSummonBy(c.getChr(), DRILL_SALVO, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.STATIC.getVal());
                field.spawnSummon(summon);
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
        if(attackInfo.skillId >= SUMMON_JAGUAR_GREY && attackInfo.skillId <= SUMMON_JAGUAR_CRIMSON) {
            attackInfo.skillId = lastUsedSkill;
            lastUsedSkill = 0;
        }
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillID = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int jaguarBleedingTime = SkillData.getSkillInfoById(SUMMON_JAGUAR_GREY).getValue(time, 1);
        switch (attackInfo.skillId) {
            case DASH_N_SLASH_JAGUAR_ON: //(33101115)  //Stun + Bite Debuff
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        int amount = 0;
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        if(mts.hasCurrentMobStat(MobStat.JaguarBleeding)) {
                            amount = mts.getCurrentOptionsByMobStat(MobStat.JaguarBleeding).nOption;
                        }
                        amount = amount + 1 > 3 ? 3 : amount + 1;
                        o1.nOption = amount;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = jaguarBleedingTime;
                        mts.addStatOptionsAndBroadcast(MobStat.JaguarBleeding, o1);
                        o2.nOption = 1;
                        o2.rOption = skill.getSkillId();
                        o2.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o2);
                    } else {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case DASH_N_SLASH_JAGUAR_SUMMONED: //(33101215)   //Stun Debuff
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case SWIPE: //Bite Debuff
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        int amount = 0;
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        if(mts.hasCurrentMobStat(MobStat.JaguarBleeding)) {
                            amount = mts.getCurrentOptionsByMobStat(MobStat.JaguarBleeding).nOption;
                        }
                        amount = amount + 1 > 3 ? 3 : amount + 1;
                        o1.nOption = amount;
                        o1.rOption = ANOTHER_BITE;
                        o1.tOption = jaguarBleedingTime;
                        mts.addStatOptionsAndBroadcast(MobStat.JaguarBleeding, o1);
                    }
                }
                break;
            case JAGUAR_SOUL: //(Stun Debuff) + (Bite Debuff) + (Magic Crash Debuff)
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        int amount = 0;
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        if(mts.hasCurrentMobStat(MobStat.JaguarBleeding)) {
                            amount = mts.getCurrentOptionsByMobStat(MobStat.JaguarBleeding).nOption;
                        }
                        amount = amount + 1 > 3 ? 3 : amount + 1;
                        o1.nOption = amount;
                        o1.rOption = ANOTHER_BITE;
                        o1.tOption = jaguarBleedingTime;
                        mts.addStatOptionsAndBroadcast(MobStat.JaguarBleeding, o1);
                        o2.nOption = 1;
                        o2.rOption = skill.getSkillId();
                        o2.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o2);
                    }
                }
                break;

        }
    }


    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if(skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        chr.chatMessage(ChatMsgColour.YELLOW, "SkillID: " + skillID);
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            switch (skillID) {
                case WILD_LURE:
                case SWIPE:
                case DASH_N_SLASH_JAGUAR_SUMMONED:
                case SONIC_ROAR:
                    lastUsedSkill = skillID;
                    c.write(UserLocal.jaguarSkill(skillID));
                    break;
                case SECRET_ASSEMBLY:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
                case HUNTING_ASSISTANT_UNIT:
                    AffectedArea aa = AffectedArea.getPassiveAA(skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setCharID(chr.getId());
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    if(chr.isLeft()) {
                        aa.setFlip(false);
                    } else {
                        aa.setFlip(true);
                    }
                    aa.setDelay((short) 4);
                    chr.getField().spawnAffectedArea(aa);
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        if(hitInfo.HPDamage == 0 && hitInfo.MPDamage == 0) {
            // Dodged
            if(chr.hasSkill(FLURRY)) {
                Skill skill = chr.getSkill(FLURRY);
                byte slv = (byte) skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                TemporaryStatManager tsm = chr.getTemporaryStatManager();
                Option o = new Option();
                o.nOption = 100;
                o.rOption = skill.getSkillId();
                o.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o);
                c.write(WvsContext.temporaryStatSet(tsm));
            }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id >= JobConstants.JobEnum.WILD_HUNTER_1.getJobId() && id <= JobConstants.JobEnum.WILD_HUNTER_4.getJobId();
    }

    @Override
    public int getFinalAttackSkill() {
        if(Util.succeedProp(getFinalAttackProc())) {
            int fas = 0;
            if (chr.hasSkill(FINAL_ATTACK_WH)) {
                fas = FINAL_ATTACK_WH;
            }
            if (chr.hasSkill(ADVANCED_FINAL_ATTACK_WH)) {
                fas = ADVANCED_FINAL_ATTACK_WH;
            }
            return fas;
        } else {
            return 0;
        }
    }

    private Skill getFinalAtkSkill(Char chr) {
        Skill skill = null;
        if(chr.hasSkill(FINAL_ATTACK_WH)) {
            skill = chr.getSkill(FINAL_ATTACK_WH);
        }
        if(chr.hasSkill(ADVANCED_FINAL_ATTACK_WH)) {
            skill = chr.getSkill(ADVANCED_FINAL_ATTACK_WH);
        }
        return skill;
    }

    private int getFinalAttackProc() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.getOptByCTSAndSkill(IndieDamR, SILENT_RAMPAGE) != null) {
            return 100;
        }
        Skill skill = getFinalAtkSkill(chr);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) chr.getSkill(skill.getSkillId()).getCurrentLevel();
        int proc = si.getValue(prop, slv);

        return proc;
    }
}
