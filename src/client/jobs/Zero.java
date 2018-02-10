package client.jobs;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.field.Field;
import client.life.Mob;
import client.life.MobTemporaryStat;
import client.life.Summon;
import connection.InPacket;
import constants.JobConstants;
import constants.SkillConstants;
import enums.ChatMsgColour;
import enums.MobStat;
import enums.MoveAbility;
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
public class Zero extends Job {

    public static final int DUAL_COMBAT = 100001270;
    public static final int DUAL_COMBAT_2 = 100000282;
    public static final int TEMPLE_RECALL = 100001262;
    public static final int RESOLUTION_TIME = 100000279;
    public static final int BURST_JUMP = 100001265;
    public static final int BURST_STEP = 100001266;
    public static final int BURST_LEAP = 100001269;


    public static final int DIVINE_FORCE = 100001263; //Aura (Unlimited Duration)
    public static final int DIVINE_SPEED = 100001264; //Aura (Unlimited Duration)
    public static final int RHINNES_PROTECTION = 100001268; //Buff

    public static final int TIME_HOLDING = 100001274;
    public static final int TIME_DISTORTION = 100001261; //TODO; AoE
    public static final int REWIND = 100001272;
    public static final int FOCUSED_TIME = 100001005;


    public static final int AIR_RIOT = 101000101; //Special Attack (Stun Debuff)
    public static final int THROWING_WEAPON = 101100100; //Special Attack (Throw Sword)
    public static final int ADVANCED_THROWING_WEAPON = 101100101; //Special Attack (Throw Sword)
    public static final int ADV_EARTH_BREAK = 101120105; //Special Attack (Shock Field)
    public static final int ADV_STORM_BREAK = 101120204; //Special Attack (Shock Field)
    public static final int DIVINE_LEER = 101120207;
    public static final int CRITICAL_BIND = 101120110;
    public static final int IMMUNE_BARRIER = 101120109;

    private int[] addedSkills = new int[] {
            DUAL_COMBAT,
            DUAL_COMBAT_2,
            TEMPLE_RECALL,
            RESOLUTION_TIME,
            BURST_STEP,
            BURST_JUMP,
            BURST_LEAP,
    };

    private int[] buffs = new int[] {
            DIVINE_FORCE,
            DIVINE_SPEED,
            RHINNES_PROTECTION,
            TIME_HOLDING,
            REWIND,
            FOCUSED_TIME,
    };

    public Zero(Char chr) {
        super(chr);
        if(isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if (chr.getZeroInfo() == null) {
                chr.initZeroInfo();
            }
        }
    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Option o7 = new Option();
        switch (skillID) {
            case DIVINE_FORCE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieAsrR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = 0;
                tsm.putCharacterStatValue(IndieAsrR, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMad, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 0;
                tsm.putCharacterStatValue(IndieMAD, o2); //Indie
                o3.nReason = skillID;
                o3.nValue = si.getValue(indiePad, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = 0;
                tsm.putCharacterStatValue(IndiePAD, o3); //Indie
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieMdd, slv);
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = 0;
                tsm.putCharacterStatValue(IndieMDD, o4); //Indie
                o5.nReason = skillID;
                o5.nValue = si.getValue(indiePdd, slv);
                o5.tStart = (int) System.currentTimeMillis();
                o5.tTerm = 0;
                tsm.putCharacterStatValue(IndiePDD, o5); //Indie
                o6.nReason = skillID;
                o6.nValue = si.getValue(indieTerR, slv);
                o6.tStart = (int) System.currentTimeMillis();
                o6.tTerm = 0;
                tsm.putCharacterStatValue(IndieTerR, o6); //Indie
                o7.nOption = 1;
                o7.rOption = skillID;
                o7.tOption = 0;
                tsm.putCharacterStatValue(ZeroAuraStr, o7);
                break;
            case DIVINE_SPEED:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieAcc, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieACC, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBooster, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o2); //Indie
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieEva, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieEVA, o3); //Indie
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieJump, slv);
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieJump, o4); //Indie
                o5.nReason = skillID;
                o5.nValue = si.getValue(indieSpeed, slv);
                o5.tStart = (int) System.currentTimeMillis();
                o5.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieSpeed, o5); //Indie
                o6.nOption = 1;
                o6.rOption = skillID;
                o6.tOption = 0;
                tsm.putCharacterStatValue(ZeroAuraSpd, o6);
                break;
            case RHINNES_PROTECTION:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;

            case TIME_HOLDING:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1); //

                //TODO Lv200+ only
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(x, slv);
                tsm.putCharacterStatValue(DamR, o2); //
                o3.nReason = skillID;
                o3.nValue = si.getValue(z, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = si.getValue(x, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOverR, o3);
                break;
            case REWIND:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(HeavensDoor, o1);
                break;
            case FOCUSED_TIME:
                o1.nReason = skillID;
                o1.nValue = 4;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = 2400;
                tsm.putCharacterStatValue(IndiePADR, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = 4;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 2400;
                tsm.putCharacterStatValue(IndieMADR, o2); //Indie
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
        byte slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = (byte) skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        System.out.println(SkillConstants.isZeroAlphaSkill(skillID));
        System.out.println(!chr.getZeroInfo().isZeroBetaState());
        boolean isAlpha = SkillConstants.isZeroAlphaSkill(skillID) || !chr.getZeroInfo().isZeroBetaState();
        if(isAlpha) {
            handleDivineLeer(attackInfo);
        } else {
            handleCriticalBind(attackInfo); //TODO
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case AIR_RIOT:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
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
            case ADV_EARTH_BREAK:
            case ADV_STORM_BREAK:
                //TODO
                break;
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
                case THROWING_WEAPON:
                case ADVANCED_THROWING_WEAPON:
                    Summon summon = Summon.getSummonBy(chr, skillID, slv);
                    summon.setFlyMob(true);
                    summon.setMoveAbility(MoveAbility.THROW.getVal());
                    chr.getField().spawnSummon(summon);
                    break;
                case TEMPLE_RECALL:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill immuneBarrier = chr.getSkill(IMMUNE_BARRIER);
        if(immuneBarrier == null) {
            return;
        }
        byte slv = (byte) immuneBarrier.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(IMMUNE_BARRIER);
        if(Util.succeedProp(si.getValue(prop, slv))) {
            Option o = new Option(IMMUNE_BARRIER, slv);
            int max = (int) (chr.getStat(Stat.mhp) * (si.getValue(x, slv) / 100D));
            o.nOption = max;
            o.xOption = max;
            chr.getTemporaryStatManager().putCharacterStatValue(ImmuneBarrier, o);
        }
        if(tsm.hasStat(ImmuneBarrier)) {
            Option o = tsm.getOption(ImmuneBarrier);
            int maxSoakDamage = o.nOption;
            int newDamage = hitInfo.HPDamage - maxSoakDamage < 0 ? 0 : hitInfo.HPDamage - maxSoakDamage;
            o.nOption = maxSoakDamage - (hitInfo.HPDamage - newDamage); // update soak value
            hitInfo.HPDamage = newDamage;
            tsm.putCharacterStatValue(ImmuneBarrier, o);
            tsm.sendSetStatPacket();
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id >= JobConstants.JobEnum.ZERO.getJobId() && id <= JobConstants.JobEnum.ZERO4.getJobId();
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    private boolean isBeta() {
        return chr.getZeroInfo().isZeroBetaState();
    }

    private void handleDivineLeer(AttackInfo ai) {
        Skill skill = chr.getSkill(DIVINE_LEER);
        if(skill == null) {
            return;
        }
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(DIVINE_LEER);
        for(MobAttackInfo mai : ai.mobAttackInfo) {
            if(Util.succeedProp(si.getValue(prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                mob.getTemporaryStat().createAndAddBurnedInfo(chr.getId(), skill, 1);
            }
        }
    }

    private void handleCriticalBind(AttackInfo ai) {
        Skill skill = chr.getSkill(CRITICAL_BIND);
        if(skill == null) {
            return;
        }
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(CRITICAL_BIND);
        for(MobAttackInfo mai : ai.mobAttackInfo) {
            if(Util.succeedProp(si.getValue(prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                MobTemporaryStat mts = mob.getTemporaryStat();
                Option o = new Option();
                o.nOption = 1;
                o.rOption = CRITICAL_BIND;
                o.tOption = si.getValue(time, slv);
                mts.addStatOptionsAndBroadcast(MobStat.Stun, o);
            }
        }
    }
}
