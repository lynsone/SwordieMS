package client.jobs.cygnus;

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
public class NightWalker extends Job {

    public static final int IMPERIAL_RECALL = 10001245;
    public static final int ELEMENTAL_EXPERT = 10000250;
    public static final int ELEMENTAL_SLASH = 10001244;
    public static final int NOBLE_MIND = 10000202;
    public static final int ELEMENTAL_SHIFT = 10001254;
    public static final int ELEMENTAL_HARMONY_LUK = 10000249;

    public static final int LUCKY_SEVEN = 14001020;
    public static final int DARK_ELEMENTAL = 14001021; //Buff (Mark of Darkness)
    public static final int HASTE = 14001022; //Buff
    public static final int DARK_SIGHT = 14001023; //Buff
    public static final int SHADOW_BAT = 14001027; //Buff (Shadow Bats) (ON/OFF)

    public static final int THROWING_BOOSTER = 14101022; //Buff
    public static final int TRIPLE_THROW = 14101020;

    public static final int DARK_SERVANT = 14111024; //Buff
    public static final int SPIRIT_PROJECTION = 14111025; //Buff
    public static final int DARKNESS_ASCENDING = 14110030; //Special Buff
    public static final int QUAD_STAR = 14111020;
    public static final int SHADOW_SPARK = 14111023;

    public static final int DARK_OMEN = 14121003; //Summon
    public static final int SHADOW_STITCH = 14121004; //Special Attack (Bind Debuff)
    public static final int CALL_OF_CYGNUS_NW = 14121000; //Buff
    public static final int QUINT_THROW = 14121001;
    public static final int VITALITY_SIPHON = 14120009;

    private int[] addedSkills = new int[] {
            ELEMENTAL_HARMONY_LUK,
            IMPERIAL_RECALL,
            ELEMENTAL_EXPERT,
            ELEMENTAL_SLASH,
            NOBLE_MIND,
            ELEMENTAL_SHIFT,
    };

    private int[] buffs = new int[] {
            DARK_ELEMENTAL,
            HASTE,
            DARK_SIGHT,
            SHADOW_BAT,
            THROWING_BOOSTER,
            DARK_SERVANT,
            SPIRIT_PROJECTION,
            DARKNESS_ASCENDING,
            CALL_OF_CYGNUS_NW,
            DARK_OMEN,
    };

    public NightWalker(Char chr) {
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
            case DARK_ELEMENTAL:
                // 'prop' % to create a mark of darkness (Debuff on Mobs)
                // stacks 'x' times
                // 'y' %DEF ignored
                // 'time' duration
                //TODO

                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementDarkness, o1);
                break;
            case HASTE:
                o1.nOption = si.getValue(speed, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(jump, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2);
                o3.nOption = si.getValue(er, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVAR, o3);
                // SpeedMax
                break;
            case DARK_SIGHT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarkSight, o1);
                break;
            case THROWING_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case DARK_SERVANT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ShadowServant, o1);
                break;
            case SPIRIT_PROJECTION:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NoBulletConsume, o1);
                break;
            case DARKNESS_ASCENDING:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarknessAscension, o1);
                break;
            case CALL_OF_CYGNUS_NW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case SHADOW_BAT:
                //TODO
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(NightWalkerBat, o1);
                break;

            case DARK_OMEN:
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

    private boolean isBuff(int skillID) {
        return Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

/*
    private void handleBat(int skillID, int slv, AttackInfo attackInfo) {
        SkillInfo si = SkillData.getSkillInfoById(SHADOW_BAT);
        for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(prop, slv))) {
                int mobID = mai.mobId;
                ForceAtomEnum fae = ForceAtomEnum.NIGHT_WALKER_BAT;
                int curTime = Util.getCurrentTime();
                ForceAtomInfo fai = new ForceAtomInfo(1, fae.getInc(), 15, 15,
                        0, 0, curTime, 0, skillID, new Position(0, 0));
                c.write(CField.createForceAtom(false, 0, chr.getId(), fae.getForceAtomType(), true,
                        mobID, SHADOW_BAT, fai, new Rect(), 0, 0, null, 0, null));
            }
        }
    } //TODO gives Error
*/

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
      //if(hasHitMobs) {
      //    handleBat(skillID, slv, attackInfo);
      //} //TODO uncomment once bats are fixed

        if (chr.hasSkill(14120009)) {
            if (hasHitMobs && Util.succeedProp(SkillData.getSkillInfoById(14001021).getValue(prop, slv))) { //TODO get Mark of Darkness Prop (needs a handler)
                handleSiphonVitality(skill.getSkillId(), tsm, c);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case SHADOW_STITCH:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);

                        handleSiphonVitality(skill.getSkillId(), tsm, c);
                }
                break;

            //TODO case All attacks give DoT debuff
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


    private void handleSiphonVitality(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo siphonInfo = SkillData.getSkillInfoById(14120009);
        int amount = 1;
        if(tsm.hasStat(ElementDarkness)) {
            if (tsm.hasStat(SiphonVitality)) {
                amount = tsm.getOption(SiphonVitality).nOption;
                if (amount < getMaxSiphon(chr)) {
                    amount++;
                }
            }
            o.nOption = amount;
            o.rOption = 14120009;
            o.tOption = siphonInfo.getValue(time, siphonInfo.getCurrentLevel());
            tsm.putCharacterStatValue(SiphonVitality, o);
            o1.nOption = (amount * siphonInfo.getValue(y, siphonInfo.getCurrentLevel()));
            o1.rOption = 14120009;
            o1.tOption = siphonInfo.getValue(time, siphonInfo.getCurrentLevel());
            tsm.putCharacterStatValue(MaxHP, o1);
            c.write(WvsContext.temporaryStatSet(tsm));
        }
    }

    private int getMaxSiphon(Char chr) { //TODO Doesn't function correctly      stays at 5 stacks
        Skill skill = null;
        if (chr.hasSkill(14120009)) {
            skill = chr.getSkill(14120009);
        }
        return SkillData.getSkillInfoById(skill.getSkillId()).getValue(x, skill.getCurrentLevel());
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case NIGHTWALKER1:
            case NIGHTWALKER2:
            case NIGHTWALKER3:
            case NIGHTWALKER4:
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
