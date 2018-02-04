package client.jobs;

import client.Client;
import client.character.Char;
import client.character.HitInfo;
import client.character.skills.*;
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
public class Zero extends Job {

    public static final int DUAL_COMBAT = 100001270;
    public static final int DUAL_COMBAT_2 = 100001282;
    public static final int TEMPLE_RECALL = 100001262;
    public static final int RESOLUTION_TIME = 100000279;
    public static final int BURST_JUMP = 100001265;
    public static final int BURST_STEP = 100001266;
    public static final int BURST_LEAP = 100001269;


    public static final int DIVINE_FORCE = 100001263; //Aura (Unlimited Duration)
    public static final int DIVINE_SPEED = 100001264; //Aura (Unlimited Duration)
    public static final int RHINNES_PROTECTION = 100001268; //Buff

    public static final int AIR_RIOT = 101000101; //Special Attack (Stun Debuff)
    public static final int THROWING_WEAPON = 101100100; //Special Attack (Throw Sword)
    public static final int ADV_EARTH_BREAK = 101120105; //Special Attack (Shock Field)
    public static final int ADV_STORM_BREAK = 101120204; //Special Attack (Shock Field)

    private int[] addedSkills = new int[] {
      /*      DUAL_COMBAT,
            DUAL_COMBAT_2,
            TEMPLE_RECALL,
            RESOLUTION_TIME,
            BURST_STEP,
            BURST_JUMP,
            BURST_LEAP,*/
    };

    private int[] buffs = new int[] {
            DIVINE_FORCE,
            DIVINE_SPEED,
            RHINNES_PROTECTION,
    };

    public Zero(Char chr) {
        super(chr);
        for (int id : addedSkills) {
            if (!chr.hasSkill(id)) {
                Skill skill = SkillData.getSkillDeepCopyById(id);
                skill.setCurrentLevel(skill.getMasterLevel());
                chr.addSkill(skill);
            }
        }
        if(chr.getZeroInfo() == null) {
            chr.initZeroInfo();
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
        switch (skillID) {
            case DIVINE_FORCE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieAsrR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieAsrR, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMad, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMAD, o2); //Indie
                o3.nReason = skillID;
                o3.nValue = si.getValue(indiePad, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o3); //Indie
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieMdd, slv);
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMDD, o4); //Indie
                o5.nReason = skillID;
                o5.nValue = si.getValue(indiePdd, slv);
                o5.tStart = (int) System.currentTimeMillis();
                o5.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePDD, o5); //Indie
                o6.nReason = skillID;
                o6.nValue = si.getValue(indieTerR, slv);
                o6.tStart = (int) System.currentTimeMillis();
                o6.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieTerR, o6); //Indie
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
                tsm.putCharacterStatValue(IndieJump, o5); //Indie
                break;
            case RHINNES_PROTECTION:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie

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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case AIR_RIOT:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(u, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(s, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;

            case THROWING_WEAPON:
                //TODO
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
                //case
            }
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id >= JobConstants.JobEnum.ZERO.getJobId() && id <= JobConstants.JobEnum.ZERO4.getJobId();
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }
}
