package client.jobs.legend;

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
import enums.LeaveType;
import enums.MobStat;
import enums.MoveAbility;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Util;

import java.util.Arrays;
import java.util.Random;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Mercedes extends Job {
    //Link Skill = return skill

    public static final int ELVEN_GRACE = 20020112;
    public static final int UPDRAFT = 20020111;
    public static final int ELVEN_HEALING = 20020109;

    public static final int DUAL_BOWGUN_BOOSTER = 23101002; //Buff

    public static final int STUNNING_STRIKES = 23111000; //Special Attack
    public static final int UNICORN_SPIKE = 23111002; //Special Attack
    public static final int IGNIS_ROAR = 23111004; //Buff
    public static final int WATER_SHIELD = 23111005; //Buff
    public static final int ELEMENTAL_KNIGHTS_BLUE = 23111008; //Summon
    public static final int ELEMENTAL_KNIGHTS_RED = 23111009; //Summon
    public static final int ELEMENTAL_KNIGHTS_PURPLE = 23111010; //Summon

    public static final int SPIKES_ROYALE = 23121002;  //Special Attack
    public static final int STAGGERING_STRIKES = 23120013; //Special Attack
    public static final int ANCIENT_WARDING = 23121004; //Buff
    public static final int MAPLE_WARRIOR_MERC = 23121005; //Buff

    public static final int HEROIC_MEMORIES_MERC = 23121053;
    public static final int ELVISH_BLESSING = 23121054;

    public static int getOriginalSkillByID(int skillID) {
        switch(skillID) {
            case STAGGERING_STRIKES:
                return STUNNING_STRIKES;
        }
        return skillID; // no original skill linked with this one
    }

    private int[] addedSkills = new int[] {
            ELVEN_GRACE,
            UPDRAFT,
            ELVEN_HEALING,
    };

    private final int[] buffs = new int[]{
            DUAL_BOWGUN_BOOSTER,
            IGNIS_ROAR,
            WATER_SHIELD,
            ELEMENTAL_KNIGHTS_BLUE, //Summon
            ELEMENTAL_KNIGHTS_RED, //Summon
            ELEMENTAL_KNIGHTS_PURPLE, //Summon
            ANCIENT_WARDING,
            MAPLE_WARRIOR_MERC,
            HEROIC_MEMORIES_MERC,
            ELVISH_BLESSING,
    };

    private final int[] summonAttacks = new int[] {
            ELEMENTAL_KNIGHTS_BLUE,
            ELEMENTAL_KNIGHTS_RED,
            ELEMENTAL_KNIGHTS_PURPLE,
    };

    private int summonBoolean = 0;

    public Mercedes(Char chr) {
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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case DUAL_BOWGUN_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case IGNIS_ROAR:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnisRore, o1);
                o2.nValue = si.getValue(indiePad, slv);
                o2.nReason = skillID;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;
            case WATER_SHIELD:
                o1.nOption = si.getValue(asrR, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o1);
                o2.nOption = si.getValue(terR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o2);
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamAbsorbShield, o3);   //IgnoreMobDamR
                break;
            case ANCIENT_WARDING:
                o1.nOption = si.getValue(emhp, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMHP, o1);
                o2.nValue = si.getValue(indiePadR, slv);
                o2.nReason = skillID;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o2);
                break;
            case MAPLE_WARRIOR_MERC:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_MERC:
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
            case ELVISH_BLESSING:
                o1.nValue = si.getValue(indiePad, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o2);
                break;
            case ELEMENTAL_KNIGHTS_BLUE:
                summonEleKnight(slv);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void summonEleKnight(byte slv) {    //TODO Need to remove current summon, and spawn a new summon

        Summon summonBlue = Summon.getSummonBy(c.getChr(), ELEMENTAL_KNIGHTS_BLUE, slv);
        Summon summonRed = Summon.getSummonBy(c.getChr(), ELEMENTAL_KNIGHTS_RED, slv);
        Summon summonPurple = Summon.getSummonBy(c.getChr(), ELEMENTAL_KNIGHTS_PURPLE, slv);
        Field field;
        field = c.getChr().getField();
        summonBlue.setFlyMob(true);
        summonBlue.setMoveAbility(MoveAbility.FLY_AROUND_CHAR.getVal());
        summonRed.setFlyMob(true);
        summonRed.setMoveAbility(MoveAbility.FLY_AROUND_CHAR.getVal());
        summonPurple.setFlyMob(true);
        summonPurple.setMoveAbility(MoveAbility.FLY_AROUND_CHAR.getVal());


        int chance = new Random().nextInt(100) + 1;
        if (chance < 32) {                                      // Ice Knight
            //if(summonBoolean == 1) {
                c.write(CField.summonedRemoved(summonRed, LeaveType.ANIMATION));
                c.write(CField.summonedRemoved(summonPurple, LeaveType.ANIMATION));
            //}
            field.spawnSummon(summonBlue);
            summonBoolean = 1;


        } else if (chance > 32 && chance < 67) {                // Fire Knight
            //if(summonBoolean == 1) {
                c.write(CField.summonedRemoved(summonBlue, LeaveType.ANIMATION));
                c.write(CField.summonedRemoved(summonPurple, LeaveType.ANIMATION));
            //}
            field.spawnSummon(summonRed);
            summonBoolean = 1;


        } else if (chance > 67 && chance < 100) {               // Dark Knight
            //if(summonBoolean == 1) {
                c.write(CField.summonedRemoved(summonBlue, LeaveType.ANIMATION));
                c.write(CField.summonedRemoved(summonRed, LeaveType.ANIMATION));
            //}
            field.spawnSummon(summonPurple);
            summonBoolean = 1;
        }

    }

    // y = stack | lasts subTime,  Final Dmg increase per stack = x
    private void handleIgnisRoar(int skillID, TemporaryStatManager tsm, Client c) { //TODO Gain 1 stack by using Combo Skills
        if (Arrays.asList(summonAttacks).contains(skillID)) {
            return;
        } else {
            Option o = new Option();
            Option o1 = new Option();
            Option o2 = new Option();
            SkillInfo ignisRoarInfo = SkillData.getSkillInfoById(IGNIS_ROAR);
            Skill skill = chr.getSkill(IGNIS_ROAR);
            byte slv = (byte) skill.getCurrentLevel();
            int amount = 1;
            if (tsm.hasStat(IgnisRore)) {
                if (tsm.hasStat(AddAttackCount)) {
                    amount = tsm.getOption(AddAttackCount).nOption;
                    if (amount < ignisRoarInfo.getValue(y, slv)) {
                        amount++;
                    }
                }

                o.nOption = amount;
                o.rOption = IGNIS_ROAR;
                o.tOption = ignisRoarInfo.getValue(subTime, slv);
                tsm.putCharacterStatValue(AddAttackCount, o);
                o1.nOption = (amount * ignisRoarInfo.getValue(x, slv));
                o1.rOption = IGNIS_ROAR;
                o1.tOption = ignisRoarInfo.getValue(subTime, slv);
                tsm.putCharacterStatValue(DamR, o1);
            }
            c.write(WvsContext.temporaryStatSet(tsm));
        }
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
        if (hasHitMobs) {
            handleIgnisRoar(getOriginalSkillByID(skillID), tsm, c);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case STUNNING_STRIKES:
            case STAGGERING_STRIKES:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(/*si.getValue(prop, slv)*/100)) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = getOriginalSkillByID(skillID);
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case UNICORN_SPIKE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = si.getValue(x, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.AddDamSkill, o1); // ?
                        // TODO Unsure about MobStat, Desc: Additional Damage Debuff
                    }
                }
                break;
            case SPIKES_ROYALE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = si.getValue(x, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Weakness, o1); // ?
                        // TODO Unsure about MobStat, Desc: Enemy DEF Debuff
                    }
                }
                break;
            case ELEMENTAL_KNIGHTS_BLUE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(subTime, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                    }
                }
                break;
            /*case ELEMENTAL_KNIGHTS_RED:           //TODO fix
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr.getId(), skill, 1);

                }
                break;*/
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
            switch(skillID) {

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
            case MERCEDES:
            case MERCEDES1:
            case MERCEDES2:
            case MERCEDES3:
            case MERCEDES4:
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
