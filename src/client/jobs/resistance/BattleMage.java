package client.jobs.resistance;

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
import loaders.SkillData;
import packet.CField;
import packet.WvsContext;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class BattleMage extends Job {

    public static final int SECRET_ASSEMBLY = 30001281;

    public static final int CONDEMNATION = 32001014; //Special Buff (ON/OFF) //TODO
    public static final int HASTY_AURA = 32001016; //Buff (Unlimited Duration)

    public static final int CONDEMNATION_I = 32100010; //Special Buff (ON/OFF) //TODO
    public static final int DRAINING_AURA = 32101009; //Buff (Unlimited Duration)
    public static final int STAFF_BOOST = 32101005; //Buff
    public static final int DARK_CHAIN = 32101001; //Special Attack (Stun Debuff)

    public static final int CONDEMNATION_II = 32110017; //Special Buff (ON/OFF) //TODO
    public static final int BLUE_AURA = 32111012; //Buff (Unlimited Duration
    public static final int DARK_SHOCK = 32110016; //Buff (ON/OFF)

    public static final int CONDEMNATION_III = 32120019; //Special Buff (ON/OFF) //TODO
    public static final int DARK_GENESIS = 32121004; //Special Attack (Stun Debuff) (Special Properties if on Cooldown)
    public static final int DARK_AURA = 32121017; //Buff (Unlimited Duration)
    public static final int WEAKENING_AURA = 32121018; //Buff (Unlimited Duration)
    public static final int PARTY_SHIELD = 32121006; //TODO Area of Effect Skill
    public static final int BATTLE_RAGE = 32121010; //Buff (ON/OFF)
    public static final int MAPLE_WARRIOR_BAM = 32121007; //Buff

    public static final int FOR_LIBERTY_BAM = 32121053;
    public static final int MASTER_OF_DEATH = 32121056; //TODO

    private int[] addedSkills = new int[] {
            SECRET_ASSEMBLY,
    };

    private int[] buffs = new int[] {
            CONDEMNATION,
            CONDEMNATION_I,
            CONDEMNATION_II,
            CONDEMNATION_III,
            HASTY_AURA,
            DRAINING_AURA,
            BLUE_AURA,
            DARK_AURA,
            DARK_SHOCK,
            WEAKENING_AURA,
            STAFF_BOOST,
            BATTLE_RAGE,
            MAPLE_WARRIOR_BAM,
            FOR_LIBERTY_BAM,
            MASTER_OF_DEATH,
    };

    private Summon death;

    public BattleMage(Char chr) {
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
        Summon summon;
        Field field;
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (skillID) {


            case CONDEMNATION:
            case CONDEMNATION_I:
            case CONDEMNATION_II:
            case CONDEMNATION_III:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                o1.xOption = 10;
                tsm.putCharacterStatValue(BMageDeath, o1);
                spawnDeath(skillID, slv);
                break;


            case STAFF_BOOST:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                c.write(CField.summonedAssistAttackRequest(chr.getId(), death.getObjectId()));
                break;

            case HASTY_AURA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieSpeed, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = 0;
                tsm.putCharacterStatValue(IndieSpeed, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBooster, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 0;
                tsm.putCharacterStatValue(IndieBooster, o2);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o3);
                break;
            case DRAINING_AURA:
                o1.nOption = si.getValue(killRecoveryR, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(Regen, o1); //TODO  HP Recovery on Kill
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o3);
                break;
            case BLUE_AURA:
                o1.nOption = si.getValue(asrR, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(AsrR, o1);
                o2.nOption = si.getValue(terR, slv);
                o2.rOption = skillID;
                o2.tOption = 0;
                tsm.putCharacterStatValue(TerR, o2);
                o3.nOption = si.getValue(y, slv);
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(IgnoreMobDamR, o3);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o3);
                break;
            case DARK_AURA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o3);
                break;
            case WEAKENING_AURA:
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o3);
                //TODO
                break;


            case BATTLE_RAGE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(Enrage, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = 0;
                tsm.putCharacterStatValue(DamR, o2);
                o3.nOption = si.getValue(z, slv);
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(CriticalBuff, o3);
                o4.nOption = si.getValue(y, slv);
                o4.rOption = skillID;
                o4.tOption = 0;
                tsm.putCharacterStatValue(IncCriticalDamMin, o4);
                break;
            case MAPLE_WARRIOR_BAM:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case FOR_LIBERTY_BAM:
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

                //TODO Master of Death;

            case DARK_SHOCK:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(DarkLighting, o1);
                break;
        }
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    public void spawnDeath(int skillID, byte slv) {
        Field field;
        death = Summon.getSummonBy(c.getChr(), skillID, slv);
        field = c.getChr().getField();
        death.setFlyMob(true);
        death.setSummonTerm(0);
        death.setMoveAction((byte) 0);
        death.setAssistType((byte) 0);  //0
        death.setAttackActive(false); //true
        death.setBeforeFirstAttack(false);
        field.spawnSummon(death);
    }

    public static void deathAttack() {

    }

    private void handleCondemnation(int skillID, TemporaryStatManager tsm) {
        Option o = new Option();
        SkillInfo condemnationInfo = SkillData.getSkillInfoById(CONDEMNATION);
        int amount = 1;
        if (chr.hasSkill(CONDEMNATION)) {
            amount = tsm.getOption(BMageDeath).nOption;
            if (amount < condemnationInfo.getValue(x, condemnationInfo.getCurrentLevel())) {
                amount++;
            } else if (amount >= 7 /*condemnationInfo.getValue(x, condemnationInfo.getCurrentLevel())*/) {



                //c.write(CField.summonedSummonAttackActive(chr.getId(), death, true));


                death.setAssistType((byte) 1);
                //c.write(CField.summonedAssistAttackRequest(chr.getId(), death));


                //amount = 1;
            }
        }
        o.nOption = amount;
        o.rOption = getIcon(chr); //gets correct Icon for the passive upgrades
        o.tOption = 0;
        tsm.putCharacterStatValue(BMageDeath, o);
        c.write(WvsContext.temporaryStatSet(tsm));
    }

    private int getIcon(Char chr) {
        int skillinfo = CONDEMNATION;
        if (chr.hasSkill(CONDEMNATION)) {
            skillinfo = CONDEMNATION;
        }
        if (chr.hasSkill(CONDEMNATION_I)) {
            skillinfo = CONDEMNATION_I;
        }
        if (chr.hasSkill(CONDEMNATION_II)) {
            skillinfo = CONDEMNATION_II;
        }
        if (chr.hasSkill(CONDEMNATION_III)) {
            skillinfo = CONDEMNATION_III;
        }
        return skillinfo;
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
        //handleCondemnation(skill.getSkillId(), tsm);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case DARK_CHAIN:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(hcProp, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case DARK_GENESIS:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
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
        short charlevel = chr.getAvatarData().getCharacterStat().getLevel();
        chr.chatMessage(ChatMsgColour.YELLOW, "SkillID: " + skillID);
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            switch (skillID) {
                case PARTY_SHIELD:
                    AffectedArea aa = AffectedArea.getPassiveAA(skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setCharID(chr.getId());
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 16);
                    chr.getField().spawnAffectedArea(aa);

                    break;
                case SECRET_ASSEMBLY:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = c.getChannelInstance().getField(o1.nValue);
                    chr.warp(toField);
                    break;
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id >= JobConstants.JobEnum.BATTLE_MAGE_1.getJobId() && id <= JobConstants.JobEnum.BATTLE_MAGE_4.getJobId();
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}