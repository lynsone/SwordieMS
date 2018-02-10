package client.jobs.legend;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
import client.jobs.Job;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;
import loaders.SkillData;
import packet.WvsContext;
import util.Util;

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Luminous extends Job {
    public static final int SUNFIRE = 20040216;
    public static final int ECLIPSE = 20040217;
    public static final int EQUILIBRIUM = 20040219;
    public static final int EQUILIBRIUM2 = 20040220; //test
    public static final int INNER_LIGHT = 20040221;
    public static final int FLASH_BLINK = 20041222;

    public static final int MAGIC_BOOSTER = 27101004; //Buff

    public static final int SHADOW_SHELL = 27111004; //Buff
    public static final int DUSK_GUARD = 27111005; //Buff
    public static final int PHOTIC_MEDITATION = 27111006; //Buff

    public static final int DARK_CRESCENDO = 27121005; //Buff
    public static final int ARCANE_PITCH = 27121006; //Buff
    public static final int MAPLE_WARRIOR_LUMI = 27121009; //Buff
    public static final int ENDER = 27121303;

    public static final int HEROIC_MEMORIES_LUMI = 27121053;

    private int[] addedSkills = new int[] {
            SUNFIRE,
            ECLIPSE,
            EQUILIBRIUM,
            //EQUILIBRIUM2,
            INNER_LIGHT,
            FLASH_BLINK,
    };

    private final int[] buffs = new int[]{
            MAGIC_BOOSTER,
            SHADOW_SHELL,
            DUSK_GUARD,
            PHOTIC_MEDITATION,
            DARK_CRESCENDO,
            ARCANE_PITCH,
            MAPLE_WARRIOR_LUMI,
            HEROIC_MEMORIES_LUMI,
    };

    public Luminous(Char chr) {
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
        switch (skillID) {
            case MAGIC_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case SHADOW_SHELL:
                //TODO
                break;
            case DUSK_GUARD:
                o1.nValue = si.getValue(indieMdd, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMDD, o1);
                o2.nValue = si.getValue(indiePdd, slv);
                o2.nReason = skillID;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePDD, o2);
                break;
            case PHOTIC_MEDITATION:
                o1.nOption = si.getValue(emad, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMAD, o1);
                break;
            case DARK_CRESCENDO:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(StackBuff, o1);
                break;
            case ARCANE_PITCH:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o1);
                // Unsure about Temp Stat.  > Ignored Monster Elemental Resistance <
                break;
            case MAPLE_WARRIOR_LUMI:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_LUMI:
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

    private void handleLarkness(int skillId, TemporaryStatManager tsm, Client c) {  //TODO
        Option o = new Option();
        SkillInfo larknessInfo = SkillData.getSkillInfoById(20040216);
        int amount = 1;
        if(chr.hasSkill(20040216)) {
            amount = tsm.getOption(Larkness).nOption;
            if(amount < 10000/*larknessInfo.getValue(y, 1)*/) {
                amount = tsm.getOption(Larkness).nOption + 1000;
            }
        }

        o.nOption = amount;
        o.rOption = 20040216;
        o.tOption = 0;
        tsm.putCharacterStatValue(Larkness, o);

        c.write(WvsContext.temporaryStatSet(tsm));
    }

    //TODO handle LifeTidal (Skill : LunarTide)

    private void handleDarkCrescendo(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo crescendoInfo = SkillData.getSkillInfoById(27121005);
        int MaxStack = getMaxStack(chr);
        int amount = 1;
        if(tsm.hasStat(StackBuff)) {
            amount = tsm.getOption(StackBuff).mOption;
            if(amount < getMaxStack(chr)) {
                amount++;
            }
        }
        o.nOption = 1;
        o.rOption = 27121005;
        o.tOption = crescendoInfo.getValue(time, crescendoInfo.getCurrentLevel());
        o.mOption = amount;
        tsm.putCharacterStatValue(StackBuff, o);
        o1.nOption = (amount * crescendoInfo.getValue(damR, crescendoInfo.getCurrentLevel()));
        o1.rOption = 27121005;
        o1.tOption = crescendoInfo.getValue(time, crescendoInfo.getCurrentLevel());
        tsm.putCharacterStatValue(DamR, o1);
        c.write(WvsContext.temporaryStatSet(tsm));

            // m = count
            // x = max stack
            // prop%
            // damR%
    }

    private int getCrescendoProp(Char chr) {
        Skill skill = null;
        if (chr.hasSkill(27121005)) {
            skill = chr.getSkill(27121005);
        }
        return SkillData.getSkillInfoById(27121005).getValue(prop, chr.getSkill(27121005).getCurrentLevel());
    }

    private int getMaxStack(Char chr) {
        Skill skill = null;
        if (chr.hasSkill(27121005)) {
            skill = chr.getSkill(27121005);
        }
        return SkillData.getSkillInfoById(skill.getSkillId()).getValue(x, skill.getCurrentLevel());
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
        handleLarkness(skill.getSkillId(), tsm, c);
        int crescendoProp = getCrescendoProp(chr);
        if (tsm.hasStat(StackBuff)) {
            if (hasHitMobs && Util.succeedProp(crescendoProp)) {
                handleDarkCrescendo(skill.getSkillId(), tsm, c);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {

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
            case LUMINOUS:
            case LUMINOUS1:
            case LUMINOUS2:
            case LUMINOUS3:
            case LUMINOUS4:
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