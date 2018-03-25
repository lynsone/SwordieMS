package client.jobs.sengoku;

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
import packet.UserLocal;
import packet.WvsContext;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Hayato extends Job {

    //Blade Energy
    public static final int QUICK_DRAW = 40011288;
    public static final int NORMAL_STANCE_BONUS = 40011291;
    public static final int QUICK_DRAW_STANCE_BONUS = 40011292;
    public static final int SUMMER_RAIN = 40011289;
    public static final int HITOKIRI_HUNDRED_STRIKE = 40011290;


    public static final int MASTER_OF_BLADES = 40010000;
    public static final int SHIMADA_HEART = 40010067;

    public static final int BATTOUJUTSU_ADVANCE = 41001010; //not sure what this skill does

    public static final int KATANA_BOOSTER = 41101005; //Buff
    public static final int MILITARY_MIGHT = 41101003; //Buff

    public static final int WILLOW_DODGE = 41110006;

    public static final int IRON_SKIN = 41121003; //Buff
    public static final int AKATSUKI_HERO_HAYATO = 41121005; //Buff
    public static final int TORNADO_BLADE = 41121017; //Attack (Stun Debuff)
    public static final int HITOKIRI_STRIKE = 41121002; //Crit% buff
    public static final int EYE_FOR_AN_EYE = 41121015; //  ON/OFF

    public static final int GOD_OF_BLADES = 41121054;
    public static final int PRINCESS_VOW_HAYATO = 41121053;

    private int[] addedSkills = new int[] {
            QUICK_DRAW,
            SUMMER_RAIN,
            MASTER_OF_BLADES,
            SHIMADA_HEART,
    };

    private int[] buffs = new int[] {
            QUICK_DRAW,
            BATTOUJUTSU_ADVANCE,
            KATANA_BOOSTER,
            MILITARY_MIGHT,
            IRON_SKIN,
            AKATSUKI_HERO_HAYATO,
            EYE_FOR_AN_EYE,
            GOD_OF_BLADES,
            PRINCESS_VOW_HAYATO,
    };

    private int swordEnergy = 0;
    private int stance = 1;

    public Hayato(Char chr) {
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
        switch (skillID) {
            case QUICK_DRAW:
                if(stance == 2) {
                    resetQuickDrawStanceBonus();
                    stance = 1;
                } else {
                    if(swordEnergy < 150) {
                        chr.chatMessage(ChatMsgColour.GAME_MESSAGE, "You need 150 Sword Energy to switch into Quick draw stance.");
                    } else {
                        resetNormalStanceBonus();
                        stance = 2;
                        handleSwordEnergy(QUICK_DRAW);
                    }
                }
                if(swordEnergy >= 150 || stance == 2) {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = 0;
                    tsm.putCharacterStatValue(HayatoStance, o1);
                    handleQuickDrawStanceBonus();
                }
                break;
            case KATANA_BOOSTER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1); //Indie
                break;
            case MILITARY_MIGHT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(y, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMPR, o1); //Indie
                o3.nOption = si.getValue(speed, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o3);
                o4.nOption = si.getValue(jump, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o4);
                o5.nOption = si.getValue(padX, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PAD, o5);
                break;
            case IRON_SKIN:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o1);
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o1);
                break;
            case AKATSUKI_HERO_HAYATO:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case EYE_FOR_AN_EYE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(EyeForEye, o1);
                break;
            case PRINCESS_VOW_HAYATO:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOver, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOver, o2);
                break;

            case GOD_OF_BLADES:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1); //Indie
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o2);
                tsm.putCharacterStatValue(TerR, o2);
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
        if(stance == 2) {
            if(tsm.getOptByCTSAndSkill(Stance, NORMAL_STANCE_BONUS) == null) {
                resetNormalStanceBonus();
            }
            handleQuickDrawStanceBonus();
            handleQuickDrawStanceStunChance(attackInfo);
        } else {
            if(tsm.getOptByCTSAndSkill(Booster, QUICK_DRAW_STANCE_BONUS) == null) {
                resetQuickDrawStanceBonus();
                resetHayatoStance();
            }
            handleNormalStanceBonus();
        }
        for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
            handleSwordEnergy(skillID);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case TORNADO_BLADE:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(subProp, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case SUMMER_RAIN:
            case HITOKIRI_HUNDRED_STRIKE:
                o1.nReason = skillID;
                o1.nValue = 15;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = 120;
                tsm.putCharacterStatValue(IndieDamR, o1); //Indie
                c.write(WvsContext.temporaryStatSet(tsm));
                break;
            case HITOKIRI_STRIKE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(HayatoCr, o1);
                c.write(WvsContext.temporaryStatSet(tsm));
                break;
        }
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
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
        handleWillowDodge();
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.HAYATO.getJobId() ||
                (id >= JobConstants.JobEnum.HAYATO1.getJobId() && id <= JobConstants.JobEnum.HAYATO4.getJobId());
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    public void handleSwordEnergy(int skillID) {
        if(skillID == QUICK_DRAW) {
            int swordEnergy2 = (swordEnergy - 150);
            swordEnergy = swordEnergy2;
        } else if(skillID == SUMMER_RAIN || skillID == HITOKIRI_HUNDRED_STRIKE) {
            swordEnergy = 0;
        } else {
            if (swordEnergy < 1000) {
                if (swordEnergy + 2 > 1000 || swordEnergy + 5 > 1000) {
                    swordEnergy = 1000;
                } else {
                    if (stance == 2) {
                        int curEnergy = swordEnergy += 2;
                        swordEnergy = curEnergy;
                    } else {
                        int curEnergy = swordEnergy += 5;
                        swordEnergy = curEnergy;
                    }

                }
            }
        }
        c.write(UserLocal.ModHayatoCombo(swordEnergy));
    }

    public void handleNormalStanceBonus() {
        stance = 1;
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();

        o2.nOption = 20;
        o2.rOption = NORMAL_STANCE_BONUS;
        o2.tOption = 0;
        tsm.putCharacterStatValue(MHPCutR, o2);
        tsm.putCharacterStatValue(MMPCutR, o2);
        o4.nOption = 80;
        o4.rOption = NORMAL_STANCE_BONUS;
        o4.tOption = 0;
        tsm.putCharacterStatValue(Stance, o4);

        if(swordEnergy >= 0 && swordEnergy < 200) { //              1
            o1.nOption = 1;
            o1.rOption = NORMAL_STANCE_BONUS;
            o1.tOption = 0;
            tsm.putCharacterStatValue(DamR, o1);

            o3.nOption = 9;
            o3.rOption = NORMAL_STANCE_BONUS;
            o3.tOption = 0;
            tsm.putCharacterStatValue(IgnoreMobpdpR, o3);
        }

        else if (swordEnergy >= 200 && swordEnergy < 400) { //      2
            o1.nOption = 2;
            o1.rOption = NORMAL_STANCE_BONUS;
            o1.tOption = 0;
            tsm.putCharacterStatValue(DamR, o1);

            o3.nOption = 13;
            o3.rOption = NORMAL_STANCE_BONUS;
            o3.tOption = 0;
            tsm.putCharacterStatValue(IgnoreMobpdpR, o3);
        }

        else if (swordEnergy >= 400 && swordEnergy < 700) { //      3
            o1.nOption = 4;
            o1.rOption = NORMAL_STANCE_BONUS;
            o1.tOption = 0;
            tsm.putCharacterStatValue(DamR, o1);

            o3.nOption = 17;
            o3.rOption = NORMAL_STANCE_BONUS;
            o3.tOption = 0;
            tsm.putCharacterStatValue(IgnoreMobpdpR, o3);
        }

        else if (swordEnergy >= 700 && swordEnergy < 1000) { //     4
            o1.nOption = 6;
            o1.rOption = NORMAL_STANCE_BONUS;
            o1.tOption = 0;
            tsm.putCharacterStatValue(DamR, o1);

            o3.nOption = 21;
            o3.rOption = NORMAL_STANCE_BONUS;
            o3.tOption = 0;
            tsm.putCharacterStatValue(IgnoreMobpdpR, o3);
        }

        else if (swordEnergy == 1000) { //                          5
            o1.nOption = 8;
            o1.rOption = NORMAL_STANCE_BONUS;
            o1.tOption = 0;
            tsm.putCharacterStatValue(DamR, o1);

            o3.nOption = 25;
            o3.rOption = NORMAL_STANCE_BONUS;
            o3.tOption = 0;
            tsm.putCharacterStatValue(IgnoreMobpdpR, o3);
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public void handleQuickDrawStanceBonus() {
        stance = 2;
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();

        o2.nOption = 1;
        o2.rOption = QUICK_DRAW_STANCE_BONUS;
        o2.tOption = 0;
        tsm.putCharacterStatValue(Booster, o2); //Indie

        if(swordEnergy >= 0 && swordEnergy < 200) { //              1
            o2.nOption = 6;
            o2.rOption = QUICK_DRAW_STANCE_BONUS;
            o2.tOption = 0;
            tsm.putCharacterStatValue(BdR, o1);

            o3.nOption = 30;
            o3.rOption = QUICK_DRAW_STANCE_BONUS;
            o3.tOption = 0;
            tsm.putCharacterStatValue(CriticalBuff, o3);
        }

        else if (swordEnergy >= 200 && swordEnergy < 400) { //      2
            o2.nOption = 6;
            o2.rOption = QUICK_DRAW_STANCE_BONUS;
            o2.tOption = 0;
            tsm.putCharacterStatValue(BdR, o1);

            o3.nOption = 35;
            o3.rOption = QUICK_DRAW_STANCE_BONUS;
            o3.tOption = 0;
            tsm.putCharacterStatValue(CriticalBuff, o3);
        }

        else if (swordEnergy >= 400 && swordEnergy < 700) { //      3
            o2.nOption = 8;
            o2.rOption = QUICK_DRAW_STANCE_BONUS;
            o2.tOption = 0;
            tsm.putCharacterStatValue(BdR, o1);

            o3.nOption = 40;
            o3.rOption = QUICK_DRAW_STANCE_BONUS;
            o3.tOption = 0;
            tsm.putCharacterStatValue(CriticalBuff, o3);
        }

        else if (swordEnergy >= 700 && swordEnergy < 1000) { //     4
            o2.nOption = 8;
            o2.rOption = QUICK_DRAW_STANCE_BONUS;
            o2.tOption = 0;
            tsm.putCharacterStatValue(BdR, o1);

            o3.nOption = 45;
            o3.rOption = QUICK_DRAW_STANCE_BONUS;
            o3.tOption = 0;
            tsm.putCharacterStatValue(CriticalBuff, o3);
        }

        else if (swordEnergy == 1000) { //                          5
            o2.nOption = 10;
            o2.rOption = QUICK_DRAW_STANCE_BONUS;
            o2.tOption = 0;
            tsm.putCharacterStatValue(BdR, o1);

            o3.nOption = 50;
            o3.rOption = QUICK_DRAW_STANCE_BONUS;
            o3.tOption = 0;
            tsm.putCharacterStatValue(CriticalBuff, o3);
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public void resetHayatoStance() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(HayatoStance, false);
        c.write(WvsContext.temporaryStatReset(tsm, false));
    }

    public void resetNormalStanceBonus() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(IgnoreMobpdpR, false);
        tsm.removeStat(DamR, false);
        tsm.removeStat(MHPCutR, false);
        tsm.removeStat(MMPCutR, false);
        tsm.removeStat(Stance, false);
        c.write(WvsContext.temporaryStatReset(tsm, false));
    }

    public void resetQuickDrawStanceBonus() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(CriticalBuff, false);
        tsm.removeStat(BdR, false);
        tsm.removeStat(Booster, false);
        c.write(WvsContext.temporaryStatReset(tsm, false));
    }

    public void handleQuickDrawStanceStunChance(AttackInfo attackInfo) {
        Option o1 = new Option();
        int stunprop = 30;
        if(swordEnergy >= 0 && swordEnergy < 200) { //         1
            stunprop += 0;
        }
        if (swordEnergy >= 200 && swordEnergy < 400) { //      2
            stunprop += 5;
        }
        if (swordEnergy >= 400 && swordEnergy < 700) { //      3
            stunprop += 5;
        }
        if (swordEnergy >= 700 && swordEnergy < 1000) { //     4
            stunprop += 5;
        }
        if (swordEnergy == 1000) { //                          5
            stunprop += 5;
        }
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(stunprop)) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                MobTemporaryStat mts = mob.getTemporaryStat();
                o1.nOption = 1;
                o1.rOption = 0;
                o1.tOption = 3;
                mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
            }
        }
    }

    public void handleWillowDodge() {   //TODO
        Skill skill = chr.getSkill(WILLOW_DODGE);
        if (skill == null) {
            return;
        }
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(WILLOW_DODGE);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Option o1 = new Option();
        int amount = 1;
        if (tsm.hasStat(WillowDodge)) {
            amount = tsm.getOption(WillowDodge).nOption;
            if (amount < 5) {
                amount++;
            }
        }
        o.nOption = 100000;
        o.rOption = WILLOW_DODGE;
        o.tOption = 20;
        tsm.putCharacterStatValue(HayatoPAD, o);

        o1.nOption = si.getValue(damR, slv);
        o1.rOption = WILLOW_DODGE;
        o1.tOption = 20;
        tsm.putCharacterStatValue(DamR, o1);

        c.write(WvsContext.temporaryStatSet(tsm));
    }
}
