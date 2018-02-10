package client.jobs.adventurer;

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
import enums.Stat;
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Rect;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;
//TODO Paladin Divine Shield & Guardian
//TODO DarkKnight Sacrifice & Evil Eye

/**
 * Created on 12/14/2017.
 */
public class Warrior extends Job {
    //Hero
    public static final int WEAPON_BOOSTER_FIGHTER = 1101004;
    public static final int COMBO_ATTACK = 1101013;
    public static final int RAGE = 1101006;
    public static final int FINAL_ATTACK_FIGHTER = 1100002;
    public static final int FINAL_ATTACK_PAGE = 1200002;
    public static final int FINAL_ATTACK_SPEARMAN = 1300002;
    public static final int MAPLE_WARRIOR_HERO = 1121000;
    public static final int WEAPON_BOOSTER_PAGE = 1201004;
    public static final int COMBO_FURY = 1101012;
    public static final int COMBO_FURY_DOWN = 1100012;
    public static final int PANIC = 1111003;
    public static final int SHOUT = 1111008;
    public static final int SHOUT_DOWN = 1111014;
    public static final int ADVANCED_FINAL_ATTACK = 1120013;
    public static final int ENRAGE = 1121010;
    public static final int PUNCTURE = 1121015;
    public static final int MAGIC_CRASH_HERO = 1121016;

    //Paladin
    public static final int CLOSE_COMBAT = 1201013;
    public static final int ELEMENTAL_CHARGE = 1200014;
    public static final int FLAME_CHARGE = 1201011;
    public static final int BLIZZARD_CHARGE = 1201012;
    public static final int LIGHTNING_CHARGE = 1211008;
    public static final int HP_RECOVERY = 1211010;
    public static final int COMBAT_ORDERS = 1211011;
    public static final int PARASHOCK_GUARD = 1211014;
    public static final int DIVINE_CHARGE = 1221004;
    public static final int THREATEN = 1211013;
    public static final int ELEMENTAL_FORCE = 1221015;
    public static final int MAPLE_WARRIOR_PALADIN = 1221000;
    public static final int GUARDIAN = 1221016;
    public static final int BLAST = 1221009;
    public static final int MAGIC_CRASH_PALLY = 1221014;

    //Dark Knight
    public static final int SPEAR_SWEEP = 1301012;
    public static final int WEAPON_BOOSTER_SPEARMAN = 1301004;
    public static final int IRON_WILL = 1301006;
    public static final int HYPER_BODY = 1301007;
    public static final int EVIL_EYE = 1301013;
    public static final int CROSS_SURGE = 1311015;
    public static final int LORD_OF_DARKNESS = 1310009;
    public static final int MAPLE_WARRIOR_DARK_KNIGHT = 1321000;
    public static final int FINAL_PACT = 1320016;
    public static final int MAGIC_CRASH_DRK = 1321014;


    //Hyper Skills
    public static final int EPIC_ADVENTURE_HERO = 1121053; //Lv200
    public static final int EPIC_ADVENTURE_PALA = 1221053; //Lv200
    public static final int EPIC_ADVENTURE_DRK = 1321053; //Lv200
    public static final int CRY_VALHALLA = 1121054; //Lv150
    public static final int SACROSANCTITY = 1221054; //Lv150
    public static final int DARK_THIRST = 1321054; //Lv150


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
            IRON_WILL,
            HYPER_BODY,
            CROSS_SURGE,
            BLAST,
            ENRAGE,

            EPIC_ADVENTURE_DRK,
            EPIC_ADVENTURE_HERO,
            EPIC_ADVENTURE_PALA,
            CRY_VALHALLA,
            SACROSANCTITY,
            DARK_THIRST,
    };
    private long lastPanicHit = Long.MIN_VALUE;
    private long lastHpRecovery = Long.MIN_VALUE;
    private int lastCharge = 0;
    private int recoveryAmount = 0;

    public Warrior(Char chr) {
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
            case WEAPON_BOOSTER_FIGHTER:
            case WEAPON_BOOSTER_PAGE:
            case WEAPON_BOOSTER_SPEARMAN:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case RAGE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PowerGuard, o2);
                break;
            case COMBO_ATTACK:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(ComboCounter, o1);
                break;
            case ENRAGE:
                removeCombo(chr, 1);
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(Enrage, o1); // max mobs hit
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                tsm.putCharacterStatValue(EnrageCrDamMin, o2);
                o3.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                tsm.putCharacterStatValue(EnrageCr, o3);
                break;
            case COMBAT_ORDERS:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CombatOrders, o1);
                break;
            case PARASHOCK_GUARD:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePddR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePDDR, o1);
                o3.nOption = si.getValue(z, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Guard, o3);
                break;
            case ELEMENTAL_FORCE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case GUARDIAN:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
            case IRON_WILL:
                o1.nOption = si.getValue(pdd, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PDD, o1);
                o2.nOption = si.getValue(mdd, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MDD, o2);
                break;
            case HYPER_BODY:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MaxHP, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MaxMP, o2);
                break;
            case CROSS_SURGE:
                int total = c.getChr().getStat(Stat.mhp);
                int current = c.getChr().getStat(Stat.hp);
                o1.nOption = (int) ((si.getValue(x, slv) * ((double) current) / total) * 100);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o1);
                o2.nOption = (int) Math.min((0.08 * total - current), si.getValue(z, slv));
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PDD, o2);
                break;
            case EVIL_EYE:
                Summon summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                Field field = c.getChr().getField();
                field.addLife(summon);
                summon.setCharLevel((byte) chr.getStat(Stat.level));
                summon.setPosition(chr.getPosition().deepCopy());
                summon.setMoveAction((byte) 1);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setMoveAbility((byte) 1);
                summon.setAssistType((byte) 0);
                summon.setEnterType((byte) 1);
                summon.setBeforeFirstAttack(false);
                summon.setTemplateId(skillID);
                summon.setAttackActive(false);
                c.write(CField.summonedCreated(chr.getId(), summon));
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PDD, o2);
                break;
            case MAPLE_WARRIOR_HERO:
            case MAPLE_WARRIOR_PALADIN:
            case MAPLE_WARRIOR_DARK_KNIGHT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case BLAST:
                o1.nOption = si.getValue(cr, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o1);
                o2.nOption = si.getValue(damR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o2);
                o3.nOption = si.getValue(ignoreMobpdpR, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnoreMobpdpR, o3);
                break;

                //Hypers
            case EPIC_ADVENTURE_DRK:
            case EPIC_ADVENTURE_HERO:
            case EPIC_ADVENTURE_PALA:
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
            case SACROSANCTITY:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
            case CRY_VALHALLA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieCr, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o2);
                tsm.putCharacterStatValue(TerR, o2);
                break;
            case DARK_THIRST:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                //TODO Recovery  x %
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
                    removeCombo(chr, 1);
                }
                break;
            case PANIC:
                if (hasHitMobs) {
                    removeCombo(chr, 2);
                    int allowedTime = si.getValue(subTime, slv);
                    if (lastPanicHit + allowedTime * 1000 > System.currentTimeMillis()) {
                        removeCombo(chr, 1);
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
            case SHOUT_DOWN:
                Skill orig = chr.getSkill(SHOUT);
                slv = orig.getCurrentLevel();
                si = SkillData.getSkillInfoById(SHOUT_DOWN);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    removeCombo(chr, 1);
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if(mob.isBoss()) {
                        o1.nOption = si.getValue(x, slv);
                        o1.rOption = SHOUT_DOWN;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Weakness, o1);
                    } else {
                        o1.nOption = 1;
                        o1.rOption = SHOUT_DOWN;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case PUNCTURE:
                if(hasHitMobs) {
                    removeCombo(chr, si.getValue(y, slv));
                }
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = si.getValue(y, slv);
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptions(MobStat.AddDamParty, o1);
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
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
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
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
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
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
                    if(tsm.getOptByCTSAndSkill(DamR, 1221009) == null) {
                    resetCharges(c, tsm);
                    int t = si.getValue(time, slv);
                    o1.nOption = si.getValue(cr, slv);
                    o1.rOption = skillID;
                    o1.tOption = t;
                    tsm.putCharacterStatValue(CriticalBuff, o1);
                    o2.nOption = si.getValue(ignoreMobpdpR, slv);
                    o2.rOption = skillID;
                    o2.tOption = t;
                    tsm.putCharacterStatValue(IgnoreMobpdpR, o2);
                    o3.nOption = si.getValue(damR, slv);
                    o3.rOption = skillID;
                    o3.tOption = t;
                    tsm.putCharacterStatValue(DamR, o3);
                    c.write(WvsContext.temporaryStatSet(tsm));
                    }
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
                break;
            case FINAL_ATTACK_FIGHTER:
            case FINAL_ATTACK_SPEARMAN:
            case FINAL_ATTACK_PAGE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    long dmg = 0;
                    for (int i = 0; i < mai.damages.length; i++) {
                        dmg += mai.damages[i];
                    }
                    c.write(CField.mobDamaged(mob.getObjectId(),dmg, mob.getTemplateId(), (byte) 1,(int)  mob.getHp(), (int) mob.getMaxHp()));
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
                case THREATEN:
                    Rect rect = new Rect(inPacket.decodeShort(), inPacket.decodeShort()
                    , inPacket.decodeShort(), inPacket.decodeShort());
                    for(Life life : chr.getField().getLifesInRect(rect)) {
                        if(life instanceof Mob && ((Mob) life).getHp() > 0) {
                            Mob mob = (Mob) life;
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            if(Util.succeedProp(si.getValue(prop, slv))) {
                                o1.nOption = si.getValue(x, slv);
                                o1.rOption = skillID;
                                o1.tOption = si.getValue(time, slv);
                                mts.addStatOptions(MobStat.PAD, o1);
                                mts.addStatOptions(MobStat.MAD, o1);
                                mts.addStatOptions(MobStat.PDR, o1);
                                mts.addStatOptions(MobStat.MDR, o1);
                                o2.nOption = -si.getValue(z, slv);
                                o2.rOption = skillID;
                                o2.tOption = si.getValue(subTime, slv);
                                mts.addStatOptionsAndBroadcast(MobStat.Darkness, o2);
                            }
                        }
                    }
                    break;
                case MAGIC_CRASH_DRK:
                case MAGIC_CRASH_HERO:
                case MAGIC_CRASH_PALLY:
                    Rect rect2 = new Rect(inPacket.decodeShort(), inPacket.decodeShort()
                    , inPacket.decodeShort(), inPacket.decodeShort());
                    for(Life life : chr.getField().getLifesInRect(rect2)) {
                        if(life instanceof Mob && ((Mob) life).getHp() > 0) {
                            Mob mob = (Mob) life;
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            if(Util.succeedProp(si.getValue(prop, slv))) {
                                o1.nOption = 1;
                                o1.rOption = skillID;
                                o1.tOption = si.getValue(time, slv);
                                mts.addStatOptionsAndBroadcast(MobStat.MagicCrash, o1);
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    private void addCombo(Char chr) {
        int currentCount = getComboCount(chr);
        if (currentCount < 0) {
            return;
        }
        if (currentCount < getMaxCombo(chr)) {
            Option o = new Option();
            o.nOption = currentCount + 1;
            o.rOption = 1101013;
            chr.getTemporaryStatManager().putCharacterStatValue(ComboCounter, o);
            chr.getClient().write(WvsContext.temporaryStatSet(chr.getTemporaryStatManager()));
        }
    }

    private void removeCombo(Char chr, int count) {
        int currentCount = getComboCount(chr);
        Option o = new Option();
        if (currentCount > count + 1) {
            o.nOption = currentCount - count;
        } else {
            o.nOption = 0;
        }
        o.rOption = 1101013;
        chr.getTemporaryStatManager().putCharacterStatValue(ComboCounter, o);
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

    @Override
    public int getFinalAttackSkill() {
        if(chr.hasSkill(FINAL_ATTACK_FIGHTER)) {
            return FINAL_ATTACK_FIGHTER;
        }
        if(chr.hasSkill(FINAL_ATTACK_PAGE)) {
            return FINAL_ATTACK_PAGE;
        }
        if(chr.hasSkill(FINAL_ATTACK_SPEARMAN)) {
            return FINAL_ATTACK_SPEARMAN;
        }
        return 0;
    }

    private void handleCharges(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        SkillInfo chargeInfo = SkillData.getSkillInfoById(1200014);
        int amount = 1;
        if(tsm.hasStat(ElementalCharge)) {
            amount = tsm.getOption(ElementalCharge).mOption;
            if (lastCharge == skillId) {
                return;
            }
            if(amount < chargeInfo.getValue(z, 1)) {
                amount++;
            }
        }
        lastCharge = skillId;
        o.nOption = 1;
        o.rOption = 1200014;
        o.tOption = (10 * chargeInfo.getValue(time, 1)); // elemental charge  // 10x actual duration
        o.mOption = amount;
        o.wOption = amount * chargeInfo.getValue(w, 1); // elemental charge
        o.uOption = amount * chargeInfo.getValue(u, 1);
        o.zOption = amount * chargeInfo.getValue(z, 1);
        tsm.putCharacterStatValue(ElementalCharge, o);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private void resetCharges(Client c, TemporaryStatManager tsm) {
        tsm.removeStat(ElementalCharge, false);
        c.write(WvsContext.temporaryStatReset(tsm, false));
    }

    private Skill getFinalAttackSkill(Char chr) {
        Skill skill = null;
        if(chr.hasSkill(FINAL_ATTACK_FIGHTER)) {
            skill = chr.getSkill(FINAL_ATTACK_FIGHTER);
        } else if(chr.hasSkill(FINAL_ATTACK_PAGE)) {
            skill = chr.getSkill(FINAL_ATTACK_PAGE);
        } else if(chr.hasSkill(FINAL_ATTACK_SPEARMAN)) {
            skill = chr.getSkill(FINAL_ATTACK_SPEARMAN);
        }
        return skill;
    }

    private void handleFinalAttack(Char chr, AttackInfo attackInfo) {
        Skill skill = getFinalAttackSkill(chr);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        if(skill == null || attackInfo.skillId == skill.getSkillId()) {
            return;
        }
        for(MobAttackInfo mai: attackInfo.mobAttackInfo) {
            if(Util.succeedProp(si.getValue(prop, skill.getCurrentLevel()))) {
                chr.getClient().write(CField.finalAttackRequest(chr, attackInfo.skillId, skill.getSkillId(), 10000,
                        mai.mobId, (int) System.currentTimeMillis()));
                Skill adv = chr.getSkill(ADVANCED_FINAL_ATTACK);
                if(adv != null) {
                    SkillInfo siAdv = SkillData.getSkillInfoById(ADVANCED_FINAL_ATTACK);
                    if(Util.succeedProp(siAdv.getValue(prop, adv.getCurrentLevel()))) {
                        chr.getClient().write(CField.finalAttackRequest(chr, attackInfo.skillId, adv.getSkillId(), 10000,
                                mai.mobId, (int) System.currentTimeMillis()));
                    }
                }
            }
        }
    }
}
