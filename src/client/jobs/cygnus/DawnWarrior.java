package client.jobs.cygnus;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.Life;
import client.life.Mob;
import client.life.MobTemporaryStat;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import enums.MobStat;
import loaders.SkillData;
import packet.WvsContext;
import util.Rect;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class DawnWarrior extends Job {

    public static final int IMPERIAL_RECALL = 10001245;
    public static final int ELEMENTAL_EXPERT = 10000250;
    public static final int ELEMENTAL_SLASH = 10001244;
    public static final int NOBLE_MIND = 10000202;
    public static final int ELEMENTAL_SHIFT = 10001254;
    public static final int ELEMENTAL_HARMONY_STR = 10000246;


    public static final int SOUL_ELEMENT = 11001022; //Buff  (Immobility Debuff)

    public static final int SOUL_SPEED = 11101024; //Buff
    public static final int FALLING_MOON = 11101022; //Buff (Unlimited Duration)

    public static final int RISING_SUN = 11111022; //Buff (Unlimited Duration)
    public static final int TRUE_SIGHT = 11111023; //Buff (Mob Def Debuff & Final DmgUp Debuff)

    public static final int EQUINOX_CYCLE = 11121005; //Buff
    public static final int EQUINOX_CYCLE_MOON = 11121011;
    public static final int EQUINOX_CYCLE_SUN = 11121012;
    public static final int IMPALING_RAYS = 11121004; //Special Attack (Incapacitate Debuff)
    public static final int CALL_OF_CYGNUS_DW = 11121000; //Buff

    public static final int SOUL_FORGE = 11121054;
    public static final int GLORY_OF_THE_GUARDIANS_DW = 11121053;


    private int[] addedSkills = new int[] {
            ELEMENTAL_HARMONY_STR,
            IMPERIAL_RECALL,
            ELEMENTAL_EXPERT,
            ELEMENTAL_SLASH,
            NOBLE_MIND,
            ELEMENTAL_SHIFT,
    };

    private int[] buffs = new int[] {
            SOUL_ELEMENT,
            SOUL_SPEED,
            FALLING_MOON,
            RISING_SUN,
            EQUINOX_CYCLE,
            CALL_OF_CYGNUS_DW,
            SOUL_FORGE,
            GLORY_OF_THE_GUARDIANS_DW,
    };

    public DawnWarrior(Char chr) {
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
        Option o5 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case SOUL_ELEMENT:
                //TODO
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SoulMasterFinal, o1);
                break;
            case SOUL_SPEED:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case FALLING_MOON:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(PoseType, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieCr, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 0;
                tsm.putCharacterStatValue(IndieCr, o2); //Indie
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(AttackCountX, o3); //TODO  doubles lines, but even without Master of the Sword
                break;
            case RISING_SUN:
                o1.nOption = 2;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(PoseType, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieDamR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 0;
                tsm.putCharacterStatValue(IndieDamR, o2); //Indie
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieBooster, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = 0;
                tsm.putCharacterStatValue(IndieBooster, o3); //Indie
                break;
            case EQUINOX_CYCLE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBooster, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o2); //Indie //TODO something wrong
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieCr, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o3); //Indie
                o4.nOption = si.getValue(x, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BuckShot, o4);
                o5.nOption = 1;
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(GlimmeringTime, o5);
                break;
            case EQUINOX_CYCLE_MOON:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(PoseType, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieCr, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 0;
                tsm.putCharacterStatValue(IndieCr, o2); //Indie
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(AttackCountX, o3);
                break;
            case EQUINOX_CYCLE_SUN:
                o1.nOption = 2;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(PoseType, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieDamR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 0;
                tsm.putCharacterStatValue(IndieDamR, o2); //Indie
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieBooster, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = 0;
                tsm.putCharacterStatValue(IndieBooster, o3);
                break;
            case CALL_OF_CYGNUS_DW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;

            case SOUL_FORGE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOver, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOver, o2);
                break;
            case GLORY_OF_THE_GUARDIANS_DW:
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
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleEquinox(TemporaryStatManager tsm) {
        if(tsm.hasStat(GlimmeringTime)) {
            int posetype = tsm.getOption(PoseType).nOption;
            if (posetype == 1) {
                posetype = 2;
            } else if (posetype == 2) {
                posetype = 1;
            }
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
        if(tsm.hasStat(SoulMasterFinal)) {
            handleSoulElement(attackInfo, slv);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case IMPALING_RAYS:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        o2.nOption = 1;
                        o2.rOption = skill.getSkillId();
                        o2.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.SeperateSoulC, o2);
                    }
                }
                break;
        }
    }

    private void handleSoulElement(AttackInfo attackInfo, byte slv) {
        Option o1 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(SOUL_ELEMENT);
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                MobTemporaryStat mts = mob.getTemporaryStat();
                o1.nOption = 1;
                o1.rOption = SOUL_ELEMENT;
                o1.tOption = si.getValue(subTime, slv);
                mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
            }
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
                case TRUE_SIGHT:
                    // Mob Def = v
                    // Final Dmg on mob = s
                    Rect rect = new Rect(inPacket.decodeShort(), inPacket.decodeShort()
                            , inPacket.decodeShort(), inPacket.decodeShort());

                    if(!chr.isLeft()) {
                        rect = rect.moveRight();
                    }
                    for(Life life : chr.getField().getLifesInRect(rect)) {
                        if(life instanceof Mob && ((Mob) life).getHp() > 0) {
                            Mob mob = (Mob) life;
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            if(Util.succeedProp(si.getValue(prop, slv))) {
                                o1.nOption = -(si.getValue(v, slv));
                                o1.rOption = skillID;
                                o1.tOption = si.getValue(time, slv);
                                mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                                mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                            }
                        }
                    }
                    break;
                case IMPERIAL_RECALL:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
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
        return JobConstants.isDawnWarrior(id);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
