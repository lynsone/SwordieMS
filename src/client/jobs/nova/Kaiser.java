package client.jobs.nova;

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
public class Kaiser extends Job {

    public static final int REALIGN_ATTACKER_MODE = 60001217; //Unlimited Duration
    public static final int REALIGN_DEFENDER_MODE = 60001216; //Unlimited Duration
    public static final int VERTICAL_GRAPPLE = 60001218;
    public static final int TRANSFIGURATION = 60000219; //Morph Gauge (SmashStack)
    public static final int DRAGON_LINK = 60001225;

    public static final int TEMPEST_BLADES_THREE = 61101002;    //TODO Special Buff (w/ Icon) 3
    public static final int BLAZE_ON = 61101004; //Buff

    public static final int FINAL_FORM_THIRD = 61111008; //Buff 3rd Job
    public static final int STONE_DRAGON = 61111002; //Summon (Speed Debuff)
    public static final int STONE_DRAGON_FINAL_FORM = 61111220; //Summon (Speed Debuff)
    public static final int CURSEBITE = 61111003; //Buff

    public static final int FINAL_FORM_FOURTH = 61120008; //Buff 4rd Job
    public static final int TEMPEST_BLADES_FIVE = 61120007;     //TODO  Special Buff (w/ Icon) 5
    public static final int GRAND_ARMOR = 61121009; //Buff
    public static final int NOVA_WARRIOR_KAISER = 61121014; //Buff



    //Attacking Skills
    public static final int DRAGON_SLASH_1 = 61001000; //First Swing
    public static final int DRAGON_SLASH_2 = 61001004; //2nd Swing
    public static final int DRAGON_SLASH_3 = 61001005; //Last Swing
    public static final int DRAGON_SLASH_1_FINAL_FORM = 61120219; //Swing

    public static final int FLAME_SURGE = 61001101;
    public static final int FLAME_SURGE_FINAL_FORM = 61111215;


    public static final int IMPACT_WAVE = 61101100;
    public static final int IMPACT_WAVE_FINAL_FORM = 61111216;
    public static final int PIERCING_BLAZE = 61101101; //Special Attack (Stun Debuff)
    public static final int PIERCING_BLAZE_FINAL_FORM = 61111217;


    public static final int WING_BEAT = 61111100; //Special Attack (Speed Debuff)
    public static final int WING_BEAT_FINAL_FORM = 61111111;
    public static final int PRESSURE_CHAIN = 61111101; //Special Attack (Stun Debuff)
    public static final int PRESSURE_CHAIN_FINAL_FORM = 61111219;


    public static final int GIGA_WAVE = 61121100; //Special Attack (Speed Debuff)
    public static final int GIGA_WAVE_FINAL_FORM = 61121201;
    public static final int INFERNO_BREATH = 61121105; //Special Attack //TODO (Flames AoE)
    public static final int INFERNO_BREATH_FINAL_FORM = 61121222;
    public static final int DRAGON_BARRAGE = 61121102;
    public static final int DRAGON_BARRAGE_FINAL_FORM = 61121203;
    public static final int BLADE_BURST = 61121104;
    public static final int BLADE_BURST_FINAL_FORM = 61121221;


    private final int[] addedSkills = new int[]{
            REALIGN_ATTACKER_MODE,
            REALIGN_DEFENDER_MODE,
            VERTICAL_GRAPPLE,
            TRANSFIGURATION,
            DRAGON_LINK,
    };

    private final int[] buffs = new int[]{
            REALIGN_ATTACKER_MODE,
            REALIGN_DEFENDER_MODE,
            TEMPEST_BLADES_THREE,
            BLAZE_ON,
            FINAL_FORM_THIRD,
            STONE_DRAGON,
            STONE_DRAGON_FINAL_FORM,
            CURSEBITE,
            FINAL_FORM_FOURTH,
            TEMPEST_BLADES_FIVE,
            GRAND_ARMOR,
            NOVA_WARRIOR_KAISER,
    };

    public Kaiser(Char chr) {
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
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case REALIGN_ATTACKER_MODE:
                o1.nOption = si.getValue(bdR, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(BdR, o1);
                o2.nOption = si.getValue(cr, slv);
                o2.rOption = skillID;
                o2.tOption = 0;
                tsm.putCharacterStatValue(CriticalBuff, o2);
                o3.nOption = si.getValue(padX, slv);
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(PAD, o3);
                break;
            case REALIGN_DEFENDER_MODE:
                o1.nOption = si.getValue(accX, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(ACC, o1);
                o2.nOption = si.getValue(mddX, slv);
                o2.rOption = skillID;
                o2.tOption = 0;
                tsm.putCharacterStatValue(MDD, o2);
                o3.nOption = si.getValue(pddX, slv);
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(PDD, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(mhpR, slv);
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = 0;
                tsm.putCharacterStatValue(IndieMHPR, o4);
                break;
            case BLAZE_ON:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case CURSEBITE:
                o1.nOption = si.getValue(asrR, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o1);
                o2.nOption = si.getValue(terR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o2);
                break;
            case GRAND_ARMOR:
                // w = party dmg taken  v = self dmg taken
                o1.nOption = si.getValue(w, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o1); //TODO DamageReduce Party-wide
                o2.nOption = si.getValue(v, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o2);
                break;
            case NOVA_WARRIOR_KAISER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case TEMPEST_BLADES_THREE:
            case TEMPEST_BLADES_FIVE:
                // TODO
                break;

            case FINAL_FORM_THIRD:
                o6.nOption = 1200;
                o6.rOption = skillID;
                o6.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Morph, o6);
                o1.nOption = si.getValue(cr, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePMdR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o2);
                /*o3.nOption = si.getValue(jump, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o3);*/  //TODO 38
                o4.nOption = si.getValue(prop, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o4);
                /*o5.nOption = si.getValue(speed, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o5);*/ //TODO 38
                resetGauge(c, tsm);
                break;
            case FINAL_FORM_FOURTH:
                o6.nOption = 1201;
                o6.rOption = skillID;
                o6.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Morph, o6);
                o1.nOption = si.getValue(cr, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePMdR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o2);
                /*o3.nOption = si.getValue(jump, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o3);*/ //TODO 38
                o4.nOption = si.getValue(prop, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o4);
                /*o5.nOption = si.getValue(speed, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o5);*/ //TODO 38
                resetGauge(c, tsm);
                break;

                // Note:  Higher Morph IDs = Different Colour Trimmings on Kaiser's Final Form

            case STONE_DRAGON:      // Also Pulls mobs
            case STONE_DRAGON_FINAL_FORM:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setCharLevel((byte) chr.getStat(Stat.level));
                summon.setPosition(chr.getPosition().deepCopy());
                summon.setMoveAction((byte) 1);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setMoveAbility((byte) 0);
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

    private void handleMorphGauge(int skillId, TemporaryStatManager tsm, Client c, int increment) {
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        SkillInfo gaugeInfo = SkillData.getSkillInfoById(60000219);
        int amount = 1;
        int stage = 0;
        if(chr.hasSkill(60000219)) {
            amount = tsm.getOption(SmashStack).nOption;
                if (amount <= (getKaiserGauge(chr))) {
                    if(amount + increment > getKaiserGauge(chr)) {
                        amount = getKaiserGauge(chr);
                    } else {
                        amount = tsm.getOption(SmashStack).nOption + increment;
                    }
                }
                if (amount >= gaugeInfo.getValue(s, 1)) {
                    stage = 1;
                }
                if (amount >= (gaugeInfo.getValue(v, 1)) - 1) {
                    stage = 2;
                }
        }
        o.nOption = amount;
        o.rOption = 0;
        o.tOption = 0;
        tsm.putCharacterStatValue(SmashStack, o);
        o1.nOption = (stage * gaugeInfo.getValue(prop, 1));
        o1.rOption = 0;
        o1.tOption = 0;
        tsm.putCharacterStatValue(Stance, o1);
        o2.nOption = (stage * gaugeInfo.getValue(psdJump, 1));
        o2.rOption = 0;
        o2.tOption = 0;
        tsm.putCharacterStatValue(Jump, o2);
        o3.nOption = (stage * gaugeInfo.getValue(psdSpeed, 1));
        o3.rOption = 0;
        o3.tOption = 0;
        tsm.putCharacterStatValue(Speed, o3);
        o4.nReason = 0;
        o4.nValue = (stage * gaugeInfo.getValue(actionSpeed, 1));
        o4.tStart = (int) System.currentTimeMillis();
        o4.tTerm = 0;
        tsm.putCharacterStatValue(IndieBooster, o4); //Indie
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void resetGauge(Client c, TemporaryStatManager tsm) {
        tsm.removeStat(SmashStack, false);
        c.write(WvsContext.temporaryStatReset(tsm, false));
    }

    private int getKaiserGauge(Char chr) {
        int maxGauge;
        switch (chr.getJob()){
            case 6100:
                maxGauge = SkillData.getSkillInfoById(60000219).getValue(s, 1);
                break;
            case 6110:
                maxGauge = SkillData.getSkillInfoById(60000219).getValue(u, 1);
                break;
            case 6111:
            case 6112:
                maxGauge = SkillData.getSkillInfoById(60000219).getValue(v, 1);
                break;
            default: maxGauge = 0;
        }
        return maxGauge;
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
            case DRAGON_SLASH_1:
                if(hasHitMobs) {
                    handleMorphGauge(61001000, tsm, c, 3 * attackInfo.mobAttackInfo.size());    //morph gauge increases by    [increment]    every mob hit with this skill
                }
                break;
            case DRAGON_SLASH_2:
                if(hasHitMobs) {
                    handleMorphGauge(61001004, tsm, c, 3 * attackInfo.mobAttackInfo.size());
                }
                break;
            case DRAGON_SLASH_3:
                if(hasHitMobs) {
                    handleMorphGauge(61001005, tsm, c, 4 * attackInfo.mobAttackInfo.size());
                }
                break;
            case DRAGON_SLASH_1_FINAL_FORM:
                if(hasHitMobs) {
                    handleMorphGauge(61120219, tsm, c, 2 * attackInfo.mobAttackInfo.size());
                }
                break;
            case FLAME_SURGE:
                if(hasHitMobs) {
                    handleMorphGauge(61001101, tsm, c, 4 * attackInfo.mobAttackInfo.size());
                }
                break;
            case FLAME_SURGE_FINAL_FORM:
                if(hasHitMobs) {
                    handleMorphGauge(61111215, tsm, c, 0 * attackInfo.mobAttackInfo.size());
                }
                break;
            case IMPACT_WAVE:
                if(hasHitMobs) {
                    handleMorphGauge(61101100, tsm, c, 5 * attackInfo.mobAttackInfo.size());
                }
                break;
            case IMPACT_WAVE_FINAL_FORM:
                if(hasHitMobs) {
                    handleMorphGauge(61111216, tsm, c, 0 * attackInfo.mobAttackInfo.size());
                }
                break;
            case PIERCING_BLAZE:
                if(hasHitMobs) {
                    handleMorphGauge(61101101, tsm, c, 5 * attackInfo.mobAttackInfo.size());
                }
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = 61101101;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case PIERCING_BLAZE_FINAL_FORM:
                if(hasHitMobs) {
                    handleMorphGauge(61111217, tsm, c, 0 * attackInfo.mobAttackInfo.size());
                }
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = 61111217;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case WING_BEAT:
                if(hasHitMobs) {
                    handleMorphGauge(61111100, tsm, c, 3 * attackInfo.mobAttackInfo.size());
                }
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {     //TODO creates NPE
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = 61111100;
                        o1.tOption = 5;
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case WING_BEAT_FINAL_FORM:
                if(hasHitMobs) {
                    handleMorphGauge(61111111, tsm, c, 1 * attackInfo.mobAttackInfo.size());
                }
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = 61111111;
                    o1.tOption = 5;
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;
            case PRESSURE_CHAIN:
                if(hasHitMobs) {
                    handleMorphGauge(61111101, tsm, c, 12 * attackInfo.mobAttackInfo.size());
                }
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = 61111101;
                        o1.tOption = 3;
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case PRESSURE_CHAIN_FINAL_FORM:
                if(hasHitMobs) {
                    handleMorphGauge(61111219, tsm, c, 0 * attackInfo.mobAttackInfo.size());
                }
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = 61111219;
                    o1.tOption = 3;
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case GIGA_WAVE:
                if(hasHitMobs) {
                    handleMorphGauge(61121100, tsm, c, 10 * attackInfo.mobAttackInfo.size());
                }
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = 61121100;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case GIGA_WAVE_FINAL_FORM:
                if(hasHitMobs) {
                    handleMorphGauge(61121201, tsm, c, 0 * attackInfo.mobAttackInfo.size());
                }
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = 61121201;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;
            case DRAGON_BARRAGE:
                if(hasHitMobs) {
                    handleMorphGauge(61121102, tsm, c, 10 * attackInfo.mobAttackInfo.size());
                }
                break;
            case DRAGON_BARRAGE_FINAL_FORM:
                if(hasHitMobs) {
                    handleMorphGauge(61121203, tsm, c, 0 * attackInfo.mobAttackInfo.size());
                }
                break;
            case BLADE_BURST:
                if(hasHitMobs) {
                    handleMorphGauge(61121104, tsm, c, 8 * attackInfo.mobAttackInfo.size());
                }
                break;
            case BLADE_BURST_FINAL_FORM:
                if(hasHitMobs) {
                    handleMorphGauge(61121221, tsm, c, 0 * attackInfo.mobAttackInfo.size());
                }
                break;
            case INFERNO_BREATH:
                if(hasHitMobs) {
                    handleMorphGauge(61121105, tsm, c, 20 * attackInfo.mobAttackInfo.size());
                }
                break;
            case INFERNO_BREATH_FINAL_FORM:
                if(hasHitMobs) {
                    handleMorphGauge(61121222, tsm, c, 5 * attackInfo.mobAttackInfo.size());
                }
                break;


            case STONE_DRAGON:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = 61111002;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case STONE_DRAGON_FINAL_FORM:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = 61111220;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
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
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case KAISER:
            case KAISER1:
            case KAISER2:
            case KAISER3:
            case KAISER4:
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


// TODO  SmashStack is kaiser's Gauge TempStat