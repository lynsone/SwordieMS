package client.jobs.adventurer;

import client.Client;
import client.character.Char;
import client.character.skills.*;
import client.jobs.Job;
import client.life.BurnedInfo;
import client.life.Mob;
import client.life.MobTemporaryStat;
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
import java.util.Random;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Warrior extends Job {

    private static final int WEAPON_BOOSTER_FIGHTER = 1101004;
    private static final int COMBO_ATTACK = 1101013;
    private static final int RAGE = 1101006;
    private static final int MAPLE_WARRIOR_HERO = 1121000;
    private static final int WEAPON_BOOSTER_PAGE = 1201004;
    private static final int COMBO_FURY = 1101012;
    private static final int COMBO_FURY_DOWN = 1100012;
    private static final int PANIC = 1111003;
    private static final int CLOSE_COMBAT = 1201013;
    private static final int ELEMENTAL_CHARGE = 1200014;
    private static final int FLAME_CHARGE = 1201011;
    private static final int BLIZZARD_CHARGE = 1201012;
    private static final int LIGHTNING_CHARGE = 1211008;
    private static final int HP_RECOVERY = 1211010;
    private static final int COMBAT_ORDERS = 1211011;
    private static final int PARASHOCK_GUARD = 1211014;
    private static final int DIVINE_CHARGE = 1221004;
    private static final int ELEMENTAL_FORCE = 1221015;
    private static final int MAPLE_WARRIOR_PALADIN = 1221000;
    private static final int GUARDIAN = 1221016;
    private static final int BLAST = 1221009;
    private static final int SPEAR_SWEEP = 1301012;
    private static final int WEAPON_BOOSTER_SPEARMAN = 1301004;
    private static final int IRON_WILL = 1301006;
    private static final int HYPER_BODY = 1301007;
    private static final int EVIL_EYE = 1301013;
    private static final int CROSS_SURGE = 1311015;
    private static final int LORD_OF_DARKNESS = 1310009;
    private static final int MAPLE_WARRIOR_DARK_KNIGHT = 1321000;
    private static final int FINAL_PACT = 1320016;

    private final int[] buffs = new int[]{
            WEAPON_BOOSTER_FIGHTER, // Weapon Booster - Fighter
            COMBO_ATTACK, // Combo Attack
            RAGE, // Rage
            MAPLE_WARRIOR_HERO, // Maple Warrior
            MAPLE_WARRIOR_PALADIN,
            WEAPON_BOOSTER_PAGE, // Weapon Booster - Page
            COMBAT_ORDERS,
            PARASHOCK_GUARD,
            ELEMENTAL_FORCE,
            GUARDIAN,
            EVIL_EYE,
    };
    private long lastPanicHit = Long.MIN_VALUE;
    private long lastHpRecovery = Long.MIN_VALUE;
    private int lastCharge = 0;
    private int recoveryAmount = 0;

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case WEAPON_BOOSTER_FIGHTER:
            case WEAPON_BOOSTER_PAGE:
            case WEAPON_BOOSTER_SPEARMAN:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(Booster, o1);
                break;
            case RAGE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.addCharacterStatValue(IndiePAD, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(PowerGuard, o2);
                break;
            case COMBO_ATTACK:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.addCharacterStatValue(ComboCounter, o1);
                break;
            case COMBAT_ORDERS:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(CombatOrders, o1);
                break;
            case PARASHOCK_GUARD:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.addCharacterStatValue(IndiePAD, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePddR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.addCharacterStatValue(IndiePDDR, o1);
                o3.nOption = si.getValue(z, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(Guard, o3);
                break;
            case ELEMENTAL_FORCE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.addCharacterStatValue(IndieDamR, o1);
                break;
            case GUARDIAN:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(Invincible, o1);
                break;
            case IRON_WILL:
                o1.nOption = si.getValue(pdd, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(PDD, o1);
                o2.nOption = si.getValue(mdd, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(MDD, o2);
                break;
            case HYPER_BODY:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(MaxHP, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(MaxMP, o2);
                break;
            case CROSS_SURGE:
                int total = c.getChr().getStat(Stat.mhp);
                int current = c.getChr().getStat(Stat.hp);
                o1.nOption = (int) ((si.getValue(x, slv) * ((double) current) / total) * 100);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(DamR, o1);
                o2.nOption = (int) Math.min((0.08 * total - current), si.getValue(z, slv));
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.addCharacterStatValue(PDD, o2);
                break;
            case EVIL_EYE:

                break;
            case MAPLE_WARRIOR_HERO:
            case MAPLE_WARRIOR_PALADIN:
            case MAPLE_WARRIOR_DARK_KNIGHT:
//                o1.nOption = si.getValue(x, slv);
//                o1.rOption = skillID;
//                o1.tOption = si.getValue(time, slv);
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.addCharacterStatValue(AsrR, o1);
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
        int comboProp = getComboProp(chr);
        if (hasHitMobs && Util.succeedProp(comboProp)) {
            addCombo(chr);
            Skill advCombo = chr.getSkill(COMBO_ATTACK);
            int secondProp = SkillData.getSkillInfoById(advCombo.getSkillId()).getValue(prop, slv);
            if (advCombo != null && Util.succeedProp(secondProp)) {
                addCombo(chr);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case COMBO_FURY:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (Util.succeedProp(si.getValue(prop, skill.getCurrentLevel()))) {
                        if (!mob.isBoss()) {
                            o1.nOption = 1;
                            o1.rOption = skill.getSkillId();
                            o1.tOption = si.getValue(time, skill.getCurrentLevel());
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                        addCombo(chr);
                    }
                }
                break;
            case COMBO_FURY_DOWN:
                if (hasHitMobs) {
                    removeCombo(chr);
                }
                break;
            case PANIC:
                if (hasHitMobs) {
                    removeCombo(chr);
                    removeCombo(chr);
                    int allowedTime = si.getValue(subTime, slv);
                    if (lastPanicHit + allowedTime * 1000 > System.currentTimeMillis()) {
                        removeCombo(chr);
                    }
                    lastPanicHit = System.currentTimeMillis();
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        lastPanicHit = System.currentTimeMillis();
                        o1.nOption = si.getValue(z, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = 0;
                        mts.addStatOptions(MobStat.PAD, o1);
                        if (Util.succeedProp(si.getValue(prop, slv))) {
                            o2.nOption = -si.getValue(x, slv); // minus?
                            o2.rOption = skill.getSkillId();
                            o2.tOption = si.getValue(time, slv);
                            mts.addStatOptions(MobStat.ACC, o2);
                        }
                        c.write(CField.mobStatSet(mob, (short) 0));
                    }
                }
                break;
            case CLOSE_COMBAT:
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case FLAME_CHARGE:
                handleCharges(skill.getSkillId(), tsm, c);
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr.getId(), skill, o1);
                    }
                }
                break;
            case BLIZZARD_CHARGE:
                handleCharges(skill.getSkillId(), tsm, c);
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case LIGHTNING_CHARGE:
                handleCharges(skill.getSkillId(), tsm, c);
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    } else {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr.getId(), skill, o1);
                    }
                }
                break;
            case DIVINE_CHARGE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Seal, o1);
                    }
                }
                handleCharges(skill.getSkillId(), tsm, c);
                break;
            case BLAST:
                int charges = tsm.getOption(ElementalCharge).mOption;
                if(charges == SkillData.getSkillInfoById(ELEMENTAL_CHARGE).getValue(z, 1)) {
                    resetCharges(c, tsm);
                    int t = si.getValue(time, slv);
                    o1.nOption = si.getValue(cr, slv);
                    o1.rOption = skillID;
                    o1.tOption = t;
                    tsm.addCharacterStatValue(CriticalBuff, o1);
                    o2.nOption = si.getValue(ignoreMobpdpR, slv);
                    o2.rOption = skillID;
                    o2.tOption = t;
                    tsm.addCharacterStatValue(IgnoreMobpdpR, o2);
                    o3.nOption = si.getValue(damR, slv);
                    o3.rOption = skillID;
                    o3.tOption = t;
                    tsm.addCharacterStatValue(DamR, o3);
                    c.write(WvsContext.temporaryStatReset(tsm, false));
                }
                break;
            case SPEAR_SWEEP:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                handleCharges(skill.getSkillId(), tsm, c);
                break;
        }
    }

    @Override
    public void handleSkill(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int skillID = inPacket.decodeInt();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if(skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        chr.chatMessage(ChatMsgColour.YELLOW, "SkillID: " + skillID);
        byte slv = inPacket.decodeByte();
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            switch(skillID) {
                case HP_RECOVERY:
                    int t = 1000 * si.getValue(time, slv);
                    long cur = System.currentTimeMillis();
                    if(lastHpRecovery + t < cur) {
                        recoveryAmount = si.getValue(x, slv);
                    } else {
                        recoveryAmount = Math.max(si.getValue(y, slv), (int) (recoveryAmount * (si.getValue(z, slv)/100D)));
                    }
                    lastHpRecovery = cur;
                    break;
            }
        }
    }

    private void addCombo(Char chr) {
        int currentCount = getComboCount(chr);
        if (currentCount <= 0) {
            return;
        }
        if (currentCount < getMaxCombo(chr)) {
            Option o = new Option();
            o.nOption = currentCount + 1;
            o.rOption = 1101013;
            chr.getTemporaryStatManager().addCharacterStatValue(ComboCounter, o);
            chr.getClient().write(WvsContext.temporaryStatSet(chr.getTemporaryStatManager()));
        }
    }

    private void removeCombo(Char chr) {
        int currentCount = getComboCount(chr);
        Option o = new Option();
        if (currentCount > 1) {
            o.nOption = currentCount - 1;
        }
        o.rOption = 1101013;
        chr.getTemporaryStatManager().addCharacterStatValue(ComboCounter, o);
        chr.getClient().write(WvsContext.temporaryStatSet(chr.getTemporaryStatManager()));
    }

    private int getComboProp(Char chr) {
        Skill skill = null;
        if (chr.hasSkill(1110013)) {
            skill = chr.getSkill(1110013);
        } else if (chr.hasSkill(1101013)) {
            skill = chr.getSkill(1101013);
        }
        if (skill == null) {
            return 0;
        }
        return SkillData.getSkillInfoById(skill.getSkillId()).getValue(prop, skill.getCurrentLevel());
    }



    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case WARRIOR:
            case FIGHTER:
            case CRUSADER:
            case HERO:
            case PAGE:
            case WHITEKNIGHT:
            case PALADIN:
            case SPEARMAN:
            case DRAGONKNIGHT:
            case DARKKNIGHT:
                return true;
            default:
                return false;
        }
    }

    public int getComboCount(Char c) {
        TemporaryStatManager tsm = c.getTemporaryStatManager();
        if (tsm.hasStat(ComboCounter)) {
            return tsm.getOption(ComboCounter).nOption;
        }
        return -1;
    }

    private int getMaxCombo(Char chr) {
        int num = 0;
        if (chr.hasSkill(1101013)) {
            num = 6;
        }
        if (chr.hasSkill(1120003)) {
            num = 11;
        }
        return num;
    }

    private void handleCharges(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        SkillInfo chargeInfo = SkillData.getSkillInfoById(1200014);
        int amount = 1;
        if(tsm.hasStat(ElementalCharge)) {
            if (lastCharge == skillId) {
                return;
            }
            if(amount < chargeInfo.getValue(z, 1)) {
                amount = tsm.getOption(ElementalCharge).mOption + 1;
            } else {
                amount = tsm.getOption(ElementalCharge).mOption;
            }
        }
        lastCharge = skillId;
        o.nOption = 1;
        o.rOption = 1200014;
        o.tOption = chargeInfo.getValue(time, 1); // elemental charge
        o.mOption = amount;
        o.wOption = amount * chargeInfo.getValue(w, 1); // elemental charge
        o.uOption = amount * chargeInfo.getValue(u, 1);
        o.zOption = amount * chargeInfo.getValue(z, 1);
        tsm.addCharacterStatValue(ElementalCharge, o);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void resetCharges(Client c, TemporaryStatManager tsm) {
        tsm.removeStat(ElementalCharge, false);
        c.write(WvsContext.temporaryStatReset(tsm, false));
    }
}
