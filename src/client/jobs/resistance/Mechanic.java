package client.jobs.resistance;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.AffectedArea;
import client.life.Life;
import client.life.Mob;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.ForceAtomEnum;
import enums.MoveAbility;
import enums.TSIndex;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Position;
import util.Rect;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */

//TODO  DCes (38 in SET_FIELD)

public class Mechanic extends Job {

    public static final int SECRET_ASSEMBLY = 30001281;
    public static final int MECHANIC_DASH = 30001068;
    public static final int HIDDEN_PEACE = 30000227;

    public static final int HUMANOID_MECH = 35001002; //Mech Suit
    public static final int TANK_MECH = 35111003; //Tank Mech Suit

    public static final int MECHANIC_RAGE = 35101006; //Buff
    public static final int PERFECT_ARMOR = 35101007; //Buff (ON/OFF)
    public static final int OPEN_PORTAL_GX9 = 35101005; //Special Skill (Summon/Portal)
    public static final int ROBO_LAUNCHER_RM7 = 35101012; //Summon
    public static final int HOMING_BEACON = 35101002;

    public static final int ROCK_N_SHOCK = 35111002; //Special Summon
    public static final int ROLL_OF_THE_DICE = 35111013; //Special Buff
    public static final int SUPPORT_UNIT_HEX = 35111008; //Summon
    public static final int ADV_HOMING_BEACON = 35110017;

    public static final int BOTS_N_TOTS = 35121009; //Special Summon
    public static final int MAPLE_WARRIOR_MECH = 35121007; //Buff
    public static final int ENHANCED_SUPPORT_UNIT = 35120002;

    public static final int FOR_LIBERTY_MECH = 35121053;
    public static final int FULL_SPREAD = 35121055;
    public static final int DISTORTION_BOMB = 35121055;

    private int[] addedSkills = new int[] {
            SECRET_ASSEMBLY,
            MECHANIC_DASH,
            HIDDEN_PEACE,
    };

    private int[] buffs = new int[] {
            HUMANOID_MECH,
            TANK_MECH,

            MECHANIC_RAGE,
            PERFECT_ARMOR,
            ROLL_OF_THE_DICE,
            MAPLE_WARRIOR_MECH,

            OPEN_PORTAL_GX9, //Summon
            SUPPORT_UNIT_HEX, //Summon
            ROBO_LAUNCHER_RM7, //Summon
            ROCK_N_SHOCK, //Summon
            BOTS_N_TOTS, //Summon
            FULL_SPREAD, //Summon
            FOR_LIBERTY_MECH,
    };


    private Summon botsNtotsSummons;

    public Mechanic(Char chr) {
        super(chr);
        if(isHandlerOfJob(chr.getJob())) {
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
        TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case HUMANOID_MECH:
                //o1.nOption = 1;
                //o1.rOption = skillID;
                //o1.tOption = 30;
                //tsm.putCharacterStatValue(Mechanic, o1);
                //1932016 - Humanoid Mech
                tsb.setNOption(1932016);
                tsb.setROption(skillID);
                tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
                tsm.sendResetStatPacket();
                break;

            case TANK_MECH:
                o1.nOption = 2;
                o1.rOption = skillID;
                o1.tOption = 30;
                //tsm.putCharacterStatValue(Mechanic, o1);
                break;

            case MECHANIC_RAGE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case PERFECT_ARMOR:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = 0; //(ON/OFF)
                tsm.putCharacterStatValue(PowerGuard, o1);
                break;
            case ROLL_OF_THE_DICE:
                //TODO
                break;
            case MAPLE_WARRIOR_MECH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;

            case OPEN_PORTAL_GX9:   //TODO WVS_CRAHS_CALLBACK           Special Summon (Creates Portal)
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.STATIC.getVal());
                summon.setAssistType((byte) 0);
                summon.setAttackActive(false);
                //field.spawnSummon(summon);
                break;
            case SUPPORT_UNIT_HEX:
            case ENHANCED_SUPPORT_UNIT:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.STATIC.getVal());
                summon.setAssistType((byte) 0);
                summon.setAttackActive(false);
                field.spawnSummon(summon);
                break;
            case ROBO_LAUNCHER_RM7:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.STATIC.getVal());
                field.spawnSummon(summon);
                break;
            case ROCK_N_SHOCK:      //TODO TeslaCoil
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.STATIC.getVal());
                summon.setAssistType((byte) 0);
                summon.setAttackActive(false);
                //field.spawnAddSummon(summon);
                break;
            case BOTS_N_TOTS:       //TODO spawn other summons from this summon pos.
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.STATIC.getVal());
                summon.setAssistType((byte) 0);
                summon.setAttackActive(false);
                field.spawnSummon(summon);
                break;

            case FOR_LIBERTY_MECH:
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

            case FULL_SPREAD:   //TODO  WVS_CRASH_CALLBACK
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.FOLLOW.getVal());
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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case DISTORTION_BOMB:
                AffectedArea aa = AffectedArea.getAffectedArea(attackInfo);
                aa.setMobOrigin((byte) 0);
                aa.setCharID(chr.getId());
                int x = attackInfo.forcedX;
                int y = attackInfo.forcedY;
                aa.setPosition(new Position(x, y));
                aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                chr.getField().spawnAffectedArea(aa);
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
                case SECRET_ASSEMBLY:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
                case HOMING_BEACON:
                    for(int i=0; i<4; i++) {
                        handleMechRockets();
                    }
                    break;
            }
        }
    }

    private void handleMechRockets() { //TODO Needs to attack multiple enemies if can
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(HOMING_BEACON);
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        List<Life> lifes = field.getLifesInRect(rect);
        for(Life life : lifes) {
            if(life instanceof Mob) {
                int ran = new Random().nextInt(41)+39;
                int mobID = ((Mob) life).getRefImgMobID(); //
                int inc = ForceAtomEnum.MECH_MEGA_ROCKET_1.getInc();
                int type = ForceAtomEnum.MECH_MEGA_ROCKET_1.getForceAtomType();
                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, ran, 30,
                        0, 200, (int) System.currentTimeMillis(), 1, 0,
                        new Position(0, 0));
                chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                        true, mobID, HOMING_BEACON, forceAtomInfo, new Rect(), 0, 300,
                        life.getPosition(), 0, life.getPosition()));
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id >= JobConstants.JobEnum.MECHANIC_1.getJobId() && id <= JobConstants.JobEnum.MECHANIC_4.getJobId();
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
