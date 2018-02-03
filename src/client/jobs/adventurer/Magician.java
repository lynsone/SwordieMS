package client.jobs.adventurer;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.jobs.Job;
import client.life.AffectedArea;
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
import util.Position;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

//TODO Bishop Skills
//TODO FP - Ignite doens't create AoE
//TODO FP - Viral Slime shouldnt follow like a Summon
//TODO IL&FP - Elemental Adaptation - Buff

/**
 * Created on 12/14/2017.
 */
public class Magician extends Job {
    public static final int MAGIC_GUARD = 2001002;
    public static final int MP_EATER_FP = 2100000;
    public static final int POISON_BREATH = 2101005;
    public static final int MAGIC_BOOSTER_FP = 2101008;
    public static final int MEDITATION_FP = 2101001;
    public static final int IGNITE = 2101010;
    public static final int BURNING_MAGIC = 2110000;
    public static final int POISON_MIST = 2111003;
    public static final int TELEPORT_MASTERY_FP = 2111007;
    public static final int ELEMENTAL_DECREASE_FP = 2111008;
    public static final int VIRAL_SLIME = 2111010;
    public static final int PARALYZE = 2121006;
    public static final int MIST_ERUPTION = 2121003;
    public static final int FLAME_HAZE = 2121011;
    public static final int INFINITY_FP = 2121004;
    public static final int IFRIT = 2121005;
    public static final int MAPLE_WARRIOR_FP = 2121000;
    public static final int CHILLING_STEP = 2201009;
    public static final int COLD_BEAM = 2201008;
    public static final int MAGIC_BOOSTER_IL = 2201010;
    public static final int MEDITATION_IL = 2201001;
    public static final int ICE_STRIKE = 2211002;
    public static final int GLACIER_CHAIN = 2211010;
    public static final int THUNDER_STORM = 2211011;
    public static final int TELEPORT_MASTERY_IL = 2211007;
    public static final int ELEMENTAL_DECREASE_IL = 2211008;
    public static final int CHAIN_LIGHTNING = 2221006;
    public static final int FREEZING_BREATH = 2221011;
    public static final int INFINITY_IL = 2221004;
    public static final int ELQUINES = 2221005;
    public static final int HEAL = 2301002;
    public static final int MAGIC_BOOSTER_BISH = 2301008;
    public static final int BLESS = 2301004;
    public static final int HOLY_MAGIC_SHELL = 2311009;
    public static final int TELEPORT_MASTERY_BISH = 2311007;
    public static final int HOLY_FOUNTAIN = 2311011;
    public static final int DIVINE_PROTECTION = 2311012;
    public static final int MYSTIC_DOOR = 2311002;
    public static final int HOLY_SYMBOL = 2311003;
    public static final int ADV_BLESSING = 2321005;
    public static final int BAHAMUT = 2321003;
    public static final int INFINITY_BISH = 2321004;
    public static final int MAPLE_WARRIOR_BISH = 2321000;
    public static final int MAPLE_WARRIOR_IL = 2221000;
    public static final int ARCANE_AIM_FP = 2120010;
    public static final int ARCANE_AIM_IL = 2220010;
    public static final int ARCANE_AIM_BISH = 2320011;


    private final int[] buffs = new int[]{
            MAGIC_GUARD,
            IGNITE,
            MAGIC_BOOSTER_FP,
            MEDITATION_FP,
            TELEPORT_MASTERY_FP,
            ELEMENTAL_DECREASE_FP,
            INFINITY_FP,
            IFRIT,
            MAPLE_WARRIOR_FP,
            MEDITATION_FP,
            CHILLING_STEP,
            MAGIC_BOOSTER_IL,
            MEDITATION_IL,
            THUNDER_STORM,
            TELEPORT_MASTERY_IL,
            ELEMENTAL_DECREASE_IL,
            INFINITY_IL,
            ELQUINES,
            MAPLE_WARRIOR_IL,
            VIRAL_SLIME,
            MAGIC_BOOSTER_BISH,
            BLESS,
            HOLY_MAGIC_SHELL,
            TELEPORT_MASTERY_BISH,
            HOLY_FOUNTAIN,
            DIVINE_PROTECTION,
            MYSTIC_DOOR,
            HOLY_SYMBOL,
            ADV_BLESSING,
            MAPLE_WARRIOR_BISH,
            INFINITY_BISH,
    };

    public Magician(Char chr) {
        super(chr);
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
        handleIgnite(attackInfo, chr, tsm, slv);

        int arcaneAimProp = SkillData.getSkillInfoById(2320011).getValue(prop, skill.getCurrentLevel());
        if (hasHitMobs && Util.succeedProp(arcaneAimProp)) {
            handleArcaneAim(2320011, tsm, c);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case POISON_BREATH:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }
                }
                break;
            case POISON_MIST:
                AffectedArea aa = AffectedArea.getAffectedArea(attackInfo);
                aa.setMobOrigin((byte) 0);
                aa.setCharID(chr.getId());
                int x = attackInfo.forcedX;
                int y = attackInfo.forcedY;
                aa.setPosition(new Position(x, y));
                aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                chr.getField().spawnAffectedArea(aa);
                break;
            case TELEPORT_MASTERY_FP:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptions(MobStat.Stun, o1);
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }
                }
                break;
            case FLAME_HAZE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptions(MobStat.Invincible, o1);
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptions(MobStat.Speed, o1);
                        mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    }
                }
                break;
            case IFRIT:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                }
                break;
            case PARALYZE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr.getId(), skill, 1);
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case COLD_BEAM:
            case ICE_STRIKE:
            case GLACIER_CHAIN:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 5;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                }
                break;
            case TELEPORT_MASTERY_IL:
            case CHAIN_LIGHTNING:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skillID;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case MIST_ERUPTION:
                for(int id : attackInfo.mists) {
                    Field field = chr.getField();
                    field.removeLife(id);
                }
                break;
            case HEAL:
                //TODO doesn't heal
                break;
        }

    }

    private void handleArcaneAim(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo arcaneAimInfo = SkillData.getSkillInfoById(2320011);
        int amount = 1;
        if (chr.hasSkill(2120010) || chr.hasSkill(2220010) || chr.hasSkill(2320011)) {
            if (tsm.hasStat(ArcaneAim)) {
                amount = tsm.getOption(ArcaneAim).nOption;
                if (amount < arcaneAimInfo.getValue(y, arcaneAimInfo.getCurrentLevel())) {
                    amount++;
                }
            }
            o.nOption = amount;
            o.rOption = 2320011;
            o.tOption = 5; // No Time Variable
            tsm.putCharacterStatValue(ArcaneAim, o);
            o1.nOption = arcaneAimInfo.getValue(ignoreMobpdpR, arcaneAimInfo.getCurrentLevel());
            o1.rOption = 2320011;
            o1.tOption = 5; // No Time Variable
            tsm.putCharacterStatValue(IgnoreMobpdpR, o1);
            o2.nOption = ( amount * arcaneAimInfo.getValue(x, arcaneAimInfo.getCurrentLevel()));
            o2.rOption = 2320011;
            o2.tOption = 5; // No Time Variable
            tsm.putCharacterStatValue(DamR, o2);
            c.write(WvsContext.temporaryStatSet(tsm));
        }
    }

    private void handleIgnite(AttackInfo attackInfo, Char chr, TemporaryStatManager tsm, int slv) {
        if(tsm.hasStat(WizardIgnite)) {
            SkillInfo igniteInfo = getInfo(IGNITE);
            for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(igniteInfo.getValue(prop, slv))) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    AffectedArea aa = AffectedArea.getAffectedArea(attackInfo);
                    aa.setMobOrigin((byte) 1);
                    aa.setCharID(chr.getId());
                    aa.setPosition(mob.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(igniteInfo.getRects().get(0)));
                    chr.getField().spawnAffectedArea(aa);
                }
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
            switch(skillID) {
                case FREEZING_BREATH:

                    break;
                case CHILLING_STEP:
                    break;

            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(MagicGuard)) {
            Skill skill = chr.getSkill(MAGIC_GUARD);
            SkillInfo si = SkillData.getSkillInfoById(MAGIC_GUARD);
            int dmgPerc = si.getValue(x, skill.getCurrentLevel());
            int dmg = hitInfo.HPDamage;
            int mpDmg = (int) (dmg * (dmgPerc / 100D));
            mpDmg = chr.getStat(Stat.mp) - mpDmg < 0 ? chr.getStat(Stat.mp) : mpDmg;
            hitInfo.HPDamage = dmg - mpDmg;
            hitInfo.MPDamage = mpDmg;
        }
    }

    private boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case MAGIC_GUARD:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(MagicGuard, o1);
                break;
            case MAGIC_BOOSTER_FP:
            case MAGIC_BOOSTER_IL:
            case MAGIC_BOOSTER_BISH:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
//                o1.nValue = si.getValue(x, slv);
//                o1.nReason = skillID;
//                o1.tStart= (int) System.currentTimeMillis();
//                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case MEDITATION_FP:
            case MEDITATION_IL:
                o1.nValue = si.getValue(indieMad, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMAD, o1);
                break;
            case IGNITE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(WizardIgnite, o1);
                break;
            case ELEMENTAL_DECREASE_FP:
            case ELEMENTAL_DECREASE_IL:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o1);
                break;
            case TELEPORT_MASTERY_FP:
            case TELEPORT_MASTERY_IL:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(TeleportMasteryOn, o1);
                break;
            case INFINITY_FP:
            case INFINITY_IL:
                o1.nOption = si.getValue(damage, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Infinity, o1);
                break;
            case IFRIT:
            case ELQUINES:
            case VIRAL_SLIME:
            case BAHAMUT:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
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
            case MAPLE_WARRIOR_FP:
            case MAPLE_WARRIOR_IL:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case THUNDER_STORM:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
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
                summon.setFlyMob(true);
                field.spawnSummon(summon);
//                o1.nReason = skillID;
//                o1.tTerm = si.getValue(time, slv);
//                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case CHILLING_STEP:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(ChillingStep, o1);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum jobEnum = JobConstants.JobEnum.getJobById(id);
        switch(jobEnum) {
            case MAGICIAN:
            case FP_WIZARD:
            case FP_MAGE:
            case FP_ARCHMAGE:
            case IL_WIZARD:
            case IL_MAGE:
            case IL_ARCHMAGE:
            case CLERIC:
            case PRIEST:
            case BISHOP:
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
