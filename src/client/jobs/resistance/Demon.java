package client.jobs.resistance;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.jobs.Job;
import client.life.Mob;
import client.life.MobTemporaryStat;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.MobStat;
import loaders.SkillData;
import packet.WvsContext;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Demon extends Job {

    //Demon Slayer
    public static final int GRIM_SCYTHE = 31001000; //Special Attack            //TODO (Demon Force)
    public static final int BATTLE_PACT_DS = 31001001; //Buff

    public static final int SOUL_EATER = 31101000; //Special Attack             //TODO (Demon Force)
    public static final int DARK_THRUST = 31101001; //Special Attack            //TODO (Demon Force)
    public static final int CHAOS_LOCK = 31101002; //Special Attack  -Stun-     //TODO (Demon Force)
    public static final int VENGEANCE = 31101003; //Buff (Stun Debuff)

    public static final int JUDGEMENT = 31111000; //Special Attack              //TODO (Demon Force)
    public static final int VORTEX_OF_DOOM = 31111001; //Special Attack  -Stun- //TODO (Demon Force)
    public static final int RAVEN_STORM = 31111003; //Special Attack -GainHP-   //TODO (Demon Force)
    public static final int CARRION_BREATH = 31111005; //Special Attack  -DoT-  //TODO (Demon Force)

    public static final int INFERNAL_CONCUSSION = 31121000; //Special Attack    //TODO (Demon Force)
    public static final int DEMON_IMPACT = 31121001; //Special Attack  -Slow-   //TODO (Demon Force)
    public static final int DEMON_CRY = 31121003; //Special Attack -DemonCry-   //TODO (Demon Force)
    public static final int BINDING_DARKNESS = 31121006; //Special Attack -Bind-//TODO (Demon Force)
    public static final int DARK_METAMORPHOSIS = 31121005; //Buff               //TODO (Demon Force)
    public static final int BOUNDLESS_RAGE = 31121007; //Buff                   //TODO (Demon Force)
    public static final int LEECH_AURA = 31121002; //Buff                       //TODO (Demon Force)
    public static final int MAPLE_WARRIOR_DS = 31121004; //Buff


    //Demon Avenger
    public static final int EXCEED_DOUBLE_SLASH_1 = 31011000; //Special Attack  //TODO (EXCEED System)
    public static final int EXCEED_DOUBLE_SLASH_2 = 31011004; //Special Attack  //TODO (EXCEED System)
    public static final int EXCEED_DOUBLE_SLASH_3 = 31011005; //Special Attack  //TODO (EXCEED System)
    public static final int EXCEED_DOUBLE_SLASH_4 = 31011006; //Special Attack  //TODO (EXCEED System)
    public static final int EXCEED_DOUBLE_SLASH_PURPLE = 31011007; //Special Attack //TODO (EXCEED System)
    public static final int OVERLOAD_RELEASE = 31011001; // Special Buff

    public static final int EXCEED_DEMON_STRIKE_1 = 31201000; //Special Attack  //TODO (EXCEED System)
    public static final int EXCEED_DEMON_STRIKE_2 = 31201007; //Special Attack  //TODO (EXCEED System)
    public static final int EXCEED_DEMON_STRIKE_3 = 31201008; //Special Attack  //TODO (EXCEED System)
    public static final int EXCEED_DEMON_STRIKE_4 = 31201009; //Special Attack  //TODO (EXCEED System)
    public static final int EXCEED_DEMON_STRIKE_PURPLE = 31201010; //Special Attack //TODO (EXCEED System)
    public static final int BATTLE_PACT_DA = 31201002; //Buff

    public static final int EXCEED_LUNAR_SLASH_1 = 31211000; //Special Attack   //TODO (EXCEED System)
    public static final int EXCEED_LUNAR_SLASH_2 = 31211007; //Special Attack   //TODO (EXCEED System)
    public static final int EXCEED_LUNAR_SLASH_3 = 31211008; //Special Attack   //TODO (EXCEED System)
    public static final int EXCEED_LUNAR_SLASH_4 = 31211009; //Special Attack   //TODO (EXCEED System)
    public static final int EXCEED_LUNAR_SLASH_PURPLE = 31211010; //Special Attack //TODO (EXCEED System)
    public static final int SHIELD_CHARGE = 31211011; //Special Attack (Stun Debuff)
    public static final int DIABOLIC_RECOVERY = 31211004; //Buff

    public static final int EXCEED_EXECUTION_1 = 31221000; //Special Attack     //TODO (EXCEED System)
    public static final int EXCEED_EXECUTION_2 = 31221009; //Special Attack     //TODO (EXCEED System)
    public static final int EXCEED_EXECUTION_3 = 31221010; //Special Attack     //TODO (EXCEED System)
    public static final int EXCEED_EXECUTION_4 = 31221011; //Special Attack     //TODO (EXCEED System)
    public static final int EXCEED_EXECUTION_PURPLE = 31221012; //Special Attack//TODO (EXCEED System)
    public static final int NETHER_SHIELD = 31220013; //Special Attack          //TODO
    public static final int NETHER_SLICE = 31221002; // Special Attack (DefDown Debuff)
    public static final int BLOOD_PRISON = 31221003; // Special Attack (Stun Debuff)
    public static final int MAPLE_WARRIOR_DA = 31221008; //Buff

    private int[] buffs = new int[] {
            BATTLE_PACT_DS,
            BATTLE_PACT_DA,
            VENGEANCE,
            DARK_METAMORPHOSIS,
            BOUNDLESS_RAGE,
            LEECH_AURA,
            MAPLE_WARRIOR_DS,
            OVERLOAD_RELEASE,
            DIABOLIC_RECOVERY,
            MAPLE_WARRIOR_DA,
    };

    public Demon(Char chr) {
        super(chr);
    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case OVERLOAD_RELEASE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1);
                o2.nOption = si.getValue(indiePMdR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o2);
                // TODO  Resets EXCEED to 0
                break;

            case BATTLE_PACT_DA:
            case BATTLE_PACT_DS:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case VENGEANCE: //stun chance = prop | stun dur. = subTime
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PowerGuard, o1);
                break;
            case DARK_METAMORPHOSIS:
                o1.nOption = si.getValue(damR, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMhpR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o2);
                o3.nOption = si.getValue(damage, slv); //?
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PowerGuard, o3);
                break;
            case BOUNDLESS_RAGE:
                // TODO
                break;
            case LEECH_AURA: //TODO hp recover = x | w = max recovery | y = requires sec cooldown
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Regen, o1); //TODO HP regen?
                break;
            case DIABOLIC_RECOVERY: // x = HP restored at interval
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMhpR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DiabolikRecovery, o2); //TODO HP regen?
                break;
            case MAPLE_WARRIOR_DA:
            case MAPLE_WARRIOR_DS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
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
            case CHAOS_LOCK: //prop Stun/Bind
            case VENGEANCE: //prop
            case VORTEX_OF_DOOM: //prop
            case SHIELD_CHARGE: //prop
            case BLOOD_PRISON: //prop
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
            case CARRION_BREATH: //DoT
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                }
                break;
            case BINDING_DARKNESS: //stun + DoT
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptions(MobStat.Stun, o1);
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }
                }
            case DEMON_CRY:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = si.getValue(y, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.PAD, o1);
                        mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                        mts.addStatOptionsAndBroadcast(MobStat.MAD, o1);
                        mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                        o2.nOption = si.getValue(v, slv);
                        o2.rOption = skill.getSkillId();
                        o2.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.ACC, o2);
                        o3.nOption = si.getValue(v, slv);
                        o3.rOption = skill.getSkillId();
                        o3.tOption = si.getValue(time, slv);
                        //mts.addStatOptionsAndBroadcast(MobStat.X, o3); //TODO Item Drop Buff
                    }
                }
                break;
            case DEMON_IMPACT:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;
            case NETHER_SLICE:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = si.getValue(x, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
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
        if (skill != null) {
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
        return id >= JobConstants.JobEnum.DEMON_SLAYER.getJobId() && id <= JobConstants.JobEnum.DEMON_AVENGER4.getJobId();
    }
}
