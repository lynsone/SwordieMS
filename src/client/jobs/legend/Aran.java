package client.jobs.legend;

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
public class Aran extends Job {

    public static final int COMBAT_STEP = 20001295;
    public static final int REGAINED_MEMORY = 20000194;
    public static final int RETURN_TO_RIEN = 20001296;

    public static final int POLEARM_BOOSTER = 21001003; //Buff
    public static final int BODY_PRESSURE = 21001008; //Buff (ON/OFF)

    public static final int SNOW_CHARGE = 21101006; //Buff
    public static final int DRAIN = 21101005; //Special Skill (HP Recovery) (ON/OFF)

    public static final int MAHA_BLESSING = 21111012; //Buff
    public static final int AERO_SWING = 21110026; //Passive that activates when Combo'ing in Air TODO

    public static final int MAPLE_WARRIOR_ARAN = 21121000; //Buff



    //Attacking Skills:
    public static final int SMASH_WAVE = 21001009;
    public static final int SMASH_WAVE_COMBO = 21000004;

    public static final int SMASH_SWING_1 = 21001010;
    public static final int SMASH_SWING_2 = 21000006;
    public static final int SMASH_SWING_3 = 21000007;


    public static final int FINAL_CHARGE = 21101011;
    public static final int FINAL_CHARGE_COMBO = 21100002; //Special Attack (Stun Debuff) (Special Skill from Key-Command)

    public static final int FINAL_TOSS = 21100015;
    public static final int FINAL_TOSS_COMBO = 21100012;

    public static final int ROLLING_SPIN = 21101017;
    public static final int ROLLING_SPIN_COMBO = 21100013; //Special Attack (Stun Debuff) (Special Skill from Key-Command)


    public static final int GATHERING_HOOK = 21111019;
    public static final int GATHERING_HOOK_COMBO = 21110018;

    public static final int FINAL_BLOW = 21111021;
    public static final int FINAL_BLOW_COMBO = 21110020; //Special Attack (Stun Debuff) (Special Skill from Key-Command)
    public static final int FINAL_BLOW_SWING_COMBO = 21110028; //Special Attack (Stun Debuff) (Special Skill from Key-Command)

    public static final int JUDGEMENT_DRAW = 21111017;
    public static final int JUDGEMENT_DRAW_COMBO_DOWN = 21110011; //Special Attack (Freeze Debuff) (Special Skill from Key-Command)
    public static final int JUDGEMENT_DRAW_COMBO_LEFT = 21110024; //Special Attack (Freeze Debuff) (Special Skill from Key-Command)
    public static final int JUDGEMENT_DRAW_COMBO_RIGHT = 21110025; //Special Attack (Freeze Debuff) (Special Skill from Key-Command)

    public static final int BEYOND_BLADE_1 = 21120022;
    public static final int BEYOND_BLADE_2 = 21121016;
    public static final int BEYOND_BLADE_3 = 21121017;



    private int[] addedSkills = new int[] {
            COMBAT_STEP,
            REGAINED_MEMORY,
            RETURN_TO_RIEN,
    };

    private final int[] buffs = new int[] {
            POLEARM_BOOSTER,
            BODY_PRESSURE,
            SNOW_CHARGE,
            DRAIN,
            MAHA_BLESSING,
            MAPLE_WARRIOR_ARAN,
    };

    public Aran(Char chr) {
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
        switch (skillID) {
            case POLEARM_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case BODY_PRESSURE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(BodyPressure, o1); // TODO Unsure
                break;
            case DRAIN:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(AranDrain, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(mhpR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 0;
                tsm.putCharacterStatValue(IndieMHPR, o2);
                break;
            case SNOW_CHARGE:
                //TODO
                break;
            case MAHA_BLESSING:
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
            case MAPLE_WARRIOR_ARAN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleComboAbility(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        SkillInfo comboInfo = SkillData.getSkillInfoById(21000000);
        int amount = 1;
        if(chr.hasSkill(21000000)) {
            amount = tsm.getOption(ComboAbilityBuff).nOption;
            if(amount < 30000) {
                amount++;
            } else {
            }
        }
        o.nOption = amount;
        o.rOption = 21000000;
        o.tOption = 0;
        tsm.putCharacterStatValue(ComboAbilityBuff, o);
        c.write(WvsContext.updateCombo(amount));
    }

    private void handleAdrenalinRush(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        int amount = tsm.getOption(ComboAbilityBuff).nOption;
            o.nOption = 1;
            o.rOption = 21110016;
            o.tOption = 15;
            tsm.putCharacterStatValue(AdrenalinBoost, o);
            c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void handleSwingStudies(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        SkillInfo swingStudiesInfo = SkillData.getSkillInfoById(21100015);
        if (chr.hasSkill(21100015)) {
            o.nOption = 1;
            o.rOption = 21100015;
            o.tOption = 5;
            tsm.putCharacterStatValue(NextAttackEnhance, o);
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
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        //handleComboAbility(skill.getSkillId(), tsm, c);       // TODO  causes NPE
        if(chr.hasSkill(21110016)) {
            if (tsm.getOption(ComboAbilityBuff).nOption > 999) {
                handleAdrenalinRush(skill.getSkillId(), tsm, c);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {

            case FINAL_CHARGE:
                handleSwingStudies(21101011, tsm, c);
                break;
            case FINAL_CHARGE_COMBO:
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
                handleSwingStudies(21100002, tsm, c);
                break;

            case ROLLING_SPIN:
                handleSwingStudies(21101017, tsm, c);
                break;
            case ROLLING_SPIN_COMBO:
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
                handleSwingStudies(21100013, tsm, c);
                break;

            case FINAL_BLOW:
                handleSwingStudies(21111021, tsm, c);
                break;
            case FINAL_BLOW_COMBO:
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
                handleSwingStudies(21110020, tsm, c);
                break;
            case FINAL_BLOW_SWING_COMBO:
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
                handleSwingStudies(21110028, tsm, c);
                break;

            case JUDGEMENT_DRAW:
                handleSwingStudies(21111017, tsm, c);
                break;
            case JUDGEMENT_DRAW_COMBO_DOWN:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                    } else {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }
                }
                handleSwingStudies(21110011, tsm, c);
                break;
            case JUDGEMENT_DRAW_COMBO_LEFT:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                    } else {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }
                }
                handleSwingStudies(21110024,tsm, c);
                break;
            case JUDGEMENT_DRAW_COMBO_RIGHT:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                    } else {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }
                }
                handleSwingStudies(21110025, tsm, c);
                break;

            case SMASH_WAVE:
                handleSwingStudies(21001009, tsm, c);
                break;
            case SMASH_WAVE_COMBO:
                handleSwingStudies(21000004, tsm ,c);
                break;

            case SMASH_SWING_1:
                handleSwingStudies(21001010, tsm, c);
                break;
            case SMASH_SWING_2:
                handleSwingStudies(21000006, tsm, c);
                break;
            case SMASH_SWING_3:
                handleSwingStudies(21000007, tsm, c);
                break;

            case FINAL_TOSS:
                handleSwingStudies(21100015, tsm, c);
                break;
            case FINAL_TOSS_COMBO:
                handleSwingStudies(21100012, tsm, c);
                break;

            case GATHERING_HOOK:
                handleSwingStudies(21111019, tsm, c);
                break;
            case GATHERING_HOOK_COMBO:
                handleSwingStudies(21110018, tsm, c);
                break;

            case BEYOND_BLADE_1:
                handleSwingStudies(21120022, tsm, c);
                break;
            case BEYOND_BLADE_2:
                handleSwingStudies(21121016, tsm, c);
                break;
            case BEYOND_BLADE_3:
                handleSwingStudies(21121017, tsm, c);
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
                case AERO_SWING:
                    // TODO  idk
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
            case LEGEND:
            case ARAN1:
            case ARAN2:
            case ARAN3:
            case ARAN4:
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
