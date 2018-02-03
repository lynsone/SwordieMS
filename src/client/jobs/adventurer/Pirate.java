package client.jobs.adventurer;

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
import packet.CField;
import packet.WvsContext;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

//TODO Stun Mastery
//TODO Dash & Monkey Magic  38
//TODO Scurvy Summons
//TODO Roll of Dice
//TODO Octo-Cannon
//TODO CM - Barrel Roulette

/**
 * Created on 12/14/2017.
 */
public class Pirate extends Job {

    //Pirate
    public static final int DASH = 5001005; //Buff


    //Buccaneer
    public static final int TORNADO_UPPERCUT = 5101012; //Special Attack TODO (Uses Force)
    public static final int KNUCKLE_BOOSTER = 5101006; //Buff
    public static final int ENERGY_CHARGE = 5100015; //Energy Gauge TODO fix UI + system

    public static final int ROLL_OF_THE_DICE_BUCC = 5111007; //Buff
    public static final int ENERGY_BURST = 5111002; //Special Attack  TODO (Uses Force)
    public static final int STATIC_THUMPER = 5111012; //Special Attack TODO (Uses Force)

    public static final int OCTOPUNCH = 5121007; //Special Attack  TODO (Uses Force)
    public static final int NAUTILUS_STRIKE_BUCC = 5121013; //Special Attack / Buff TODO Special Buff
    public static final int DRAGON_STRIKE = 5121001; //Special Attack  TODO (Uses Force)
    public static final int BUCCANEER_BLAST = 5121016; //Special Attack   TODO (Uses Force)
    public static final int CROSSBONES = 5121015; //Buff
    public static final int SPEED_INFUSION = 5121009; //Buff
    public static final int TIME_LEAP = 5121010; //Special Move / Buff
    public static final int MAPLE_WARRIOR_BUCC = 5121000; //Buff


    //Corsair
    public static final int SCURVY_SUMMONS = 5201012; //Summon
    public static final int INFINITY_BLAST = 5201008; //Buff
    public static final int GUN_BOOSTER = 5201003; //Buff

    public static final int ROLL_OF_THE_DICE_SAIR = 5211007; //Buff
    public static final int OCTO_CANNON = 5211014; //Summon

    public static final int QUICKDRAW = 5221021; //Buff TODO Special Buff
    public static final int PARROTARGETTING = 5221015; //Special Attack
    public static final int NAUTILUS_STRIKE_SAIR = 5221013; //Special Attack / Buff TODO Special Buff
    public static final int MAPLE_WARRIOR_SAIR = 5221000; //Buff
    public static final int JOLLY_ROGER = 5221018; //Buff


    //Cannoneer
    public static final int BLAST_BACK = 5011002; //Special Attack

    public static final int MONKEY_MAGIC = 5301003; //Buff
    public static final int CANNON_BOOSTER = 5301002; //Buff

    public static final int MONKEY_WAVE = 5311002; //Special Attack
    public static final int BARREL_ROULETTE = 5311004; //Buff
    public static final int LUCK_OF_THE_DIE = 5311005; //Buff

    public static final int ANCHOR_AWEIGH = 5321003; //Summon
    public static final int MONKEY_MALITIA = 5320011; //Summon
    public static final int NAUTILUS_STRIKE_CANNON = 5321001; //Special Attack / Buff TODO Special Buff
    public static final int PIRATE_SPIRIT = 5321010; //Buff
    public static final int MAPLE_WARRIOR_CANNON = 5321005; //Buff

    //Jett
    public static final int GALACTIC_MIGHT = 5081023; //Buff

    public static final int BOUNTY_CHASER = 5701013; //Buff
    public static final int STARLINE_TWO = 5701010; //Special Attack (Stun Debuff)

    public static final int TURRET_DEPLOYMENT = 5711001; //Summon
    public static final int SLIPSTREAM_SUIT = 5711024; //Buff

    public static final int HIGH_GRAVITY = 5721066; //Buff
    public static final int MAPLE_WARRIOR_JETT = 5721000; //Buff


    private int[] buffs = new int[]{
            DASH,
            KNUCKLE_BOOSTER,
            ROLL_OF_THE_DICE_BUCC, //TODO
            CROSSBONES,
            SPEED_INFUSION,
            TIME_LEAP, //TODO
            MAPLE_WARRIOR_BUCC,

            SCURVY_SUMMONS,
            INFINITY_BLAST,
            GUN_BOOSTER,
            ROLL_OF_THE_DICE_SAIR, //TODO
            OCTO_CANNON,
            QUICKDRAW, //TODO
            PARROTARGETTING,
            MAPLE_WARRIOR_SAIR,
            JOLLY_ROGER,

            MONKEY_MAGIC,
            CANNON_BOOSTER,
            BARREL_ROULETTE, //TODO
            LUCK_OF_THE_DIE, //TODO
            ANCHOR_AWEIGH,
            MONKEY_MALITIA,
            PIRATE_SPIRIT,
            MAPLE_WARRIOR_CANNON,

            GALACTIC_MIGHT,
            BOUNTY_CHASER,
            TURRET_DEPLOYMENT,
            SLIPSTREAM_SUIT,
            HIGH_GRAVITY,
            MAPLE_WARRIOR_JETT,
    };


    public Pirate(Char chr) {
        super(chr);
    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Option o7 = new Option();
        switch (skillID) {
            case DASH:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1); //TODO 38s
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2); //TODO 38s
                break;
            case KNUCKLE_BOOSTER:
            case GUN_BOOSTER:
            case CANNON_BOOSTER:
            case GALACTIC_MIGHT:
            case SPEED_INFUSION:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case INFINITY_BLAST:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NoBulletConsume, o1);
                break;
            case MAPLE_WARRIOR_BUCC:
            case MAPLE_WARRIOR_CANNON:
            case MAPLE_WARRIOR_JETT:
            case MAPLE_WARRIOR_SAIR:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case MONKEY_MAGIC:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieAcc, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieACC, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieAllStat, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieAllStat, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieEva, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieEVA, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieJump, slv);
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieJump, o4); //TODO 38s
                o5.nReason = skillID;
                o5.nValue = si.getValue(indieMhp, slv);
                o5.tStart = (int) System.currentTimeMillis();
                o5.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHP, o5);
                o6.nReason = skillID;
                o6.nValue = si.getValue(indieMmp, slv);
                o6.tStart = (int) System.currentTimeMillis();
                o6.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMP, o6);
                o7.nReason = skillID;
                o7.nValue = si.getValue(indieSpeed, slv);
                o7.tStart = (int) System.currentTimeMillis();
                o7.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieSpeed, o7); //TODO 38s
                break;
            case BOUNTY_CHASER:
                o1.nOption = si.getValue(dexX, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DEX, o1);
                o2.nOption = si.getValue(strX, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(STR, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieCr, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieDamR, slv);
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o4);
                break;
            case SLIPSTREAM_SUIT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DEXR, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVAR, o2);
                o3.nOption = si.getValue(y, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ACCR, o3);
                break;
            case HIGH_GRAVITY:
                o1.nOption = si.getValue(prop, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieAllStat, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieAllStat, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieCr, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o3);
                break;
            case PIRATE_SPIRIT:
                o1.nOption = si.getValue(prop, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o1);
                break;
            case JOLLY_ROGER:
                o1.nOption = si.getValue(eva, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVA, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePadR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o2);
                o3.nOption = si.getValue(z, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o3);
                o4.nOption = si.getValue(x, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o4);
                o5.nOption = si.getValue(x, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o5);
                break;
            case CROSSBONES:
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePadR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o2);
                break;

            case OCTO_CANNON: //Stationary, Attacks
                Summon OctoCannon = Summon.getSummonBy(c.getChr(), skillID, slv);
                Field field = c.getChr().getField();
                field.addLife(OctoCannon);
                OctoCannon.setCharLevel((byte) chr.getStat(Stat.level));
                OctoCannon.setPosition(chr.getPosition().deepCopy());
                OctoCannon.setMoveAction((byte) 1);
                OctoCannon.setCurFoothold((short) field.findFootHoldBelow(OctoCannon.getPosition()).getId());
                OctoCannon.setMoveAbility((byte) 0);
                OctoCannon.setAssistType((byte) 1);
                OctoCannon.setEnterType((byte) 1);
                OctoCannon.setBeforeFirstAttack(false);
                OctoCannon.setTemplateId(skillID);
                OctoCannon.setAttackActive(true);
                c.write(CField.summonedCreated(chr.getId(), OctoCannon));
                break;
            case MONKEY_MALITIA: //Stationary, Attacks
                Summon MonkeyMalitia = Summon.getSummonBy(c.getChr(), skillID, slv);
                Field field2 = c.getChr().getField();
                field2.addLife(MonkeyMalitia);
                MonkeyMalitia.setCharLevel((byte) chr.getStat(Stat.level));
                MonkeyMalitia.setPosition(chr.getPosition().deepCopy());
                MonkeyMalitia.setMoveAction((byte) 1);
                MonkeyMalitia.setCurFoothold((short) field2.findFootHoldBelow(MonkeyMalitia.getPosition()).getId());
                MonkeyMalitia.setMoveAbility((byte) 0);
                MonkeyMalitia.setAssistType((byte) 1);
                MonkeyMalitia.setEnterType((byte) 1);
                MonkeyMalitia.setBeforeFirstAttack(false);
                MonkeyMalitia.setTemplateId(skillID);
                MonkeyMalitia.setAttackActive(true);
                c.write(CField.summonedCreated(chr.getId(), MonkeyMalitia));
                break;
            case TURRET_DEPLOYMENT: //Stationary, Attacks
                Summon TurretDeployment = Summon.getSummonBy(c.getChr(), skillID, slv);
                Field field5 = c.getChr().getField();
                field5.addLife(TurretDeployment);
                TurretDeployment.setCharLevel((byte) chr.getStat(Stat.level));
                TurretDeployment.setPosition(chr.getPosition().deepCopy());
                TurretDeployment.setMoveAction((byte) 1);
                TurretDeployment.setCurFoothold((short) field5.findFootHoldBelow(TurretDeployment.getPosition()).getId());
                TurretDeployment.setMoveAbility((byte) 0);
                TurretDeployment.setAssistType((byte) 1);
                TurretDeployment.setEnterType((byte) 1);
                TurretDeployment.setBeforeFirstAttack(false);
                TurretDeployment.setTemplateId(skillID);
                TurretDeployment.setAttackActive(true);
                c.write(CField.summonedCreated(chr.getId(), TurretDeployment));
                break;
            case SCURVY_SUMMONS: //Moves, Attacks
                Summon ScurvySummons = Summon.getSummonBy(c.getChr(), skillID, slv);
                Field field3 = c.getChr().getField();
                field3.addLife(ScurvySummons);
                ScurvySummons.setCharLevel((byte) chr.getStat(Stat.level));
                ScurvySummons.setPosition(chr.getPosition().deepCopy());
                ScurvySummons.setMoveAction((byte) 1);
                ScurvySummons.setCurFoothold((short) field3.findFootHoldBelow(ScurvySummons.getPosition()).getId());
                ScurvySummons.setMoveAbility((byte) 1);
                ScurvySummons.setAssistType((byte) 1);
                ScurvySummons.setEnterType((byte) 1);
                ScurvySummons.setBeforeFirstAttack(false);
                ScurvySummons.setTemplateId(skillID);
                ScurvySummons.setAttackActive(true);
                c.write(CField.summonedCreated(chr.getId(), ScurvySummons));
                break;
            case ANCHOR_AWEIGH: //Stationary, Pulls mobs
                Summon AnchorAweigh = Summon.getSummonBy(c.getChr(), skillID, slv);
                Field field4 = c.getChr().getField();
                field4.addLife(AnchorAweigh);
                AnchorAweigh.setCharLevel((byte) chr.getStat(Stat.level));
                AnchorAweigh.setPosition(chr.getPosition().deepCopy());
                AnchorAweigh.setMoveAction((byte) 1);
                AnchorAweigh.setCurFoothold((short) field4.findFootHoldBelow(AnchorAweigh.getPosition()).getId());
                AnchorAweigh.setMoveAbility((byte) 0);
                AnchorAweigh.setAssistType((byte) 1);
                AnchorAweigh.setEnterType((byte) 1);
                AnchorAweigh.setBeforeFirstAttack(false);
                AnchorAweigh.setTemplateId(skillID);
                AnchorAweigh.setAttackActive(false);
                c.write(CField.summonedCreated(chr.getId(), AnchorAweigh));
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
            case BLAST_BACK:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = si.getValue(z, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case MONKEY_WAVE:
            case STARLINE_TWO:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
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
                case TIME_LEAP:
                    //TODO
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case PIRATE:
            case PIRATE_CANNONNEER:
            case JETT1:
            case BRAWLER:
            case MARAUDER:
            case BUCCANEER:
            case GUNSLINGER:
            case OUTLAW:
            case CORSAIR:
            case CANNONEER:
            case CANNON_BLASTER:
            case CANNON_MASTER:
            case JETT2:
            case JETT3:
            case JETT4:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
