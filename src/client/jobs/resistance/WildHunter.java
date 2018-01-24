package client.jobs.resistance;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.Mob;
import client.life.MobTemporaryStat;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.MobStat;
import enums.Stat;
import loaders.SkillData;
import packet.WvsContext;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class WildHunter extends Job {

    //Jaguars       Unknown which ID stands for which jaguar (just guesses atm)
    public static final int JAGUAR_RIDER_GREY = 33001007;           //No Special Jaguar Stats
    public static final int JAGUAR_RIDER_YELLOW = 33001008;         //No Special Jaguar Stats
    public static final int JAGUAR_RIDER_RED = 33001009;            //No Special Jaguar Stats
    public static final int JAGUAR_RIDER_PURPLE = 33001010;         //No Special Jaguar Stats
    public static final int JAGUAR_RIDER_BLUE = 33001011;           //No Special Jaguar Stats
    public static final int JAGUAR_RIDER_JAIRA = 33001012;          //Critical Rate +5%
    public static final int JAGUAR_RIDER_SNOW_WHITE = 33001013;     //Buff Duration +10%
    public static final int JAGUAR_RIDER_ONYX = 33001014;           //Buff Duration +10%
    public static final int JAGUAR_RIDER_CRIMSON = 33001015;        //Dmg Absorption +10%


    public static final int SECRET_ASSEMBLY = 30001281;
    public static final int CAPTURE = 30001068;
    public static final int CALL_OF_THE_HUNTER = 30001062;

    public static final int SUMMON_JAGUAR = 33001001; //Special Buff
    public static final int SWIPE = 33001016 ; //Special Attack (Bite Debuff)

    public static final int SOUL_ARROW_CROSSBOW = 33101003; //Buff
    public static final int CROSSBOW_BOOSTER = 33101012; //Buff
    public static final int CALL_OF_THE_WILD = 33101005; //Buff
    public static final int DASH_N_SLASH_JAGUAR_OFF = 33101115; //Special Attack (Stun Debuff) + (Bite Debuff)
    public static final int DASH_N_SLASH_JAGUAR_ON = 33101215; //Special Attack (Stun Debuff) + (Bite Debuff)

    public static final int FELINE_BERSERK = 33111007; //Buff
    public static final int BACKSTEP = 33111011; //Special Buff (ON/OFF)
    public static final int HUNTING_ASSISTANT_UNIT = 33111013; //Summon
    public static final int SONIC_ROAR = 33111015; //Special Attack (Bite Debuff)

    public static final int JAGUAR_SOUL = 33121017; //Special Attack (Stun Debuff) + (Bite Debuff) + (Magic Crash Debuff)
    public static final int DRILL_SALVO = 33121016; //Summon
    public static final int SHARP_EYES = 33121004; //Buff
    public static final int MAPLE_WARRIOR_WH = 33121007; //Buff

    private int[] addedSkills = new int[] {
            SECRET_ASSEMBLY,
            CAPTURE,
            CALL_OF_THE_HUNTER,
    };

    private int[] buffs = new int[] {
            JAGUAR_RIDER_GREY,
            JAGUAR_RIDER_YELLOW,
            JAGUAR_RIDER_RED,
            JAGUAR_RIDER_PURPLE,
            JAGUAR_RIDER_BLUE,
            JAGUAR_RIDER_JAIRA,
            JAGUAR_RIDER_SNOW_WHITE,
            JAGUAR_RIDER_ONYX,
            JAGUAR_RIDER_CRIMSON,

            SUMMON_JAGUAR,
            SOUL_ARROW_CROSSBOW,
            CROSSBOW_BOOSTER,
            CALL_OF_THE_WILD,
            FELINE_BERSERK,
            BACKSTEP,
            SHARP_EYES,
            MAPLE_WARRIOR_WH,
    };

    public WildHunter(Char chr) {
        super(chr);
        for (int id : addedSkills) {
            if (!chr.hasSkill(id)) {
                Skill skill = SkillData.getSkillDeepCopyById(id);
                skill.setCurrentLevel(skill.getMasterLevel());
                chr.addSkill(skill);
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
            case JAGUAR_RIDER_GREY:
            case JAGUAR_RIDER_YELLOW:
            case JAGUAR_RIDER_RED:
            case JAGUAR_RIDER_PURPLE:
            case JAGUAR_RIDER_BLUE:
            case JAGUAR_RIDER_JAIRA:
            case JAGUAR_RIDER_SNOW_WHITE:
            case JAGUAR_RIDER_ONYX:
            case JAGUAR_RIDER_CRIMSON:
                //TODO
                break;
            case SUMMON_JAGUAR:
                //TODO
                break;


            case SOUL_ARROW_CROSSBOW:
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
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case CALL_OF_THE_WILD: // z = attack power&m.att%  |  DmgAbsorb & EVAR & MaxMPR = x
                o1.nOption = si.getValue(z, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o1);
                tsm.putCharacterStatValue(MAD, o1); //Matt%?
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
                //TODO
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


            case HUNTING_ASSISTANT_UNIT:
            case DRILL_SALVO:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setCharLevel((byte) chr.getStat(Stat.level));
                summon.setPosition(chr.getPosition().deepCopy());
                summon.setMoveAction((byte) 1);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setMoveAbility((byte) 0); // 0 = Stationary | 1 = Moves with Player
                summon.setAssistType((byte) 1);
                summon.setEnterType((byte) 1);
                summon.setBeforeFirstAttack(false);
                summon.setTemplateId(skillID);
                summon.setAttackActive(true); // false = Doesn't Attack | true = Attacks
                field.spawnSummon(summon);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private boolean isBuff(int skillID) {
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
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case DASH_N_SLASH_JAGUAR_ON: //(33101115)  //Stun + Bite Debuff
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = 5; //si.getValue(time, slv);  //TODO time isn't given in the WzFiles, but in the 'Summon Jaguar' descr. it says that the debuff is 5sec
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
            case DASH_N_SLASH_JAGUAR_OFF: //(33101215)   //Stun Debuff
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
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = 5; //si.getValue(time, slv);  //TODO time isn't given in the WzFiles, but in the 'Summon Jaguar' descr. it says that the debuff is 5sec
                        mts.addStatOptionsAndBroadcast(MobStat.JaguarBleeding, o1);
                    }
                }
                break;
            case JAGUAR_SOUL: //(Stun Debuff) + (Bite Debuff) + (Magic Crash Debuff)
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = 5; //si.getValue(time, slv);  //TODO time isn't given in the WzFiles, but in the 'Summon Jaguar' descr. it says that the debuff is 5sec
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

            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id >= JobConstants.JobEnum.WILD_HUNTER_1.getJobId() && id <= JobConstants.JobEnum.WILD_HUNTER_4.getJobId();
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
