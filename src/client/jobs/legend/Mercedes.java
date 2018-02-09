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
import enums.MobStat;
import enums.Stat;
import loaders.SkillData;
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

    public static final int ELVEN_GRACE = 20020112;
    public static final int UPDRAFT = 20020111;
    public static final int ELVEN_HEALING = 20020109;

    public static final int DUAL_BOWGUN_BOOSTER = 23101002; //Buff

    public static final int STUNNING_STRIKES = 23111000; //Special Attack
    public static final int UNICORN_SPIKE = 23111002; //Special Attack
    public static final int IGNIS_ROAR = 23111004; //Buff //TODO Stacks
    public static final int WATER_SHIELD = 23111005; //Buff
    public static final int ELEMENTAL_KNIGHTS_BLUE = 23111008; //Summon
    public static final int ELEMENTAL_KNIGHTS_RED = 23111009; //Summon
    public static final int ELEMENTAL_KNIGHTS_PURPLE = 23111010; //Summon

    public static final int SPIKES_ROYALE = 23121002;  //Special Attack
    public static final int STAGGERING_STRIKES = 23120013; //Special Attack
    public static final int ANCIENT_WARDING = 23121004; //Buff
    public static final int MAPLE_WARRIOR_MERC = 23121005; //Buff

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
    };

    private final int[] summonAttacks = new int[] {
            ELEMENTAL_KNIGHTS_BLUE,
            ELEMENTAL_KNIGHTS_RED,
            ELEMENTAL_KNIGHTS_PURPLE,
    };

    public Mercedes(Char chr) {
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
                break;
            case ANCIENT_WARDING: //TODO 38s unsure what TempStat causes it
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
            case ELEMENTAL_KNIGHTS_BLUE: //TODO allow for different summons (blue/red/purple)
                int element = ELEMENTAL_KNIGHTS_BLUE;
                int chance = new Random().nextInt(100) +1;
                if (chance < 32) {
                    element = ELEMENTAL_KNIGHTS_BLUE;
                } else if (chance > 32 && chance < 67) {
                    element = ELEMENTAL_KNIGHTS_RED;
                } else if (chance > 67 && chance < 100) {
                    element = ELEMENTAL_KNIGHTS_PURPLE;
                }
                summon = Summon.getSummonBy(c.getChr(), element, slv);
                field = c.getChr().getField();
                summon.setCharLevel((byte) chr.getStat(Stat.level));
                summon.setPosition(chr.getPosition().deepCopy());
                summon.setMoveAction((byte) 1);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setMoveAbility((byte) 1);
                summon.setAssistType((byte) 1);
                summon.setEnterType((byte) 1);
                summon.setBeforeFirstAttack(false);
                summon.setTemplateId(skillID);
                summon.setAttackActive(true);
                field.spawnSummon(summon);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    // y = stack | lasts subTime,  Final Dmg increase per stack = x
    private void handleIgnisRoar(int skillID, TemporaryStatManager tsm, Client c) { //TODO Gain 1 stack by using Combo Skills
        if (Arrays.asList(summonAttacks).contains(skillID)) {
            return;
        } else {
            Option o = new Option();
            Option o1 = new Option();
            Option o2 = new Option();
            SkillInfo ignisRoarInfo = SkillData.getSkillInfoById(23111004);
            int amount = 1;
            if (tsm.hasStat(IgnisRore)) {
                if (tsm.hasStat(AddAttackCount)) {
                    amount = tsm.getOption(AddAttackCount).nOption;
                    if (amount < ignisRoarInfo.getValue(y, ignisRoarInfo.getCurrentLevel())) {
                        amount++;
                    }
                }

                o.nOption = amount;
                o.rOption = 23111004;
                o.tOption = ignisRoarInfo.getValue(subTime, ignisRoarInfo.getCurrentLevel());
                tsm.putCharacterStatValue(AddAttackCount, o);
                o1.nOption = (amount * ignisRoarInfo.getValue(x, ignisRoarInfo.getCurrentLevel()));
                o1.rOption = 23111004;
                o1.tOption = ignisRoarInfo.getValue(subTime, ignisRoarInfo.getCurrentLevel());
                tsm.putCharacterStatValue(DamR, o1);
            }
            c.write(WvsContext.temporaryStatSet(tsm));
        }
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
