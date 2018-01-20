package client.jobs.cygnus;

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

import java.util.Arrays;

import static client.character.skills.CharacterTemporaryStat.*;
import static client.character.skills.SkillStat.time;
import static client.character.skills.SkillStat.x;

/**
 * Created on 12/14/2017.
 */
public class ThunderBreaker extends Job {

    public static final int LIGHTNING_ELEMENTAL = 15001022; //Buff (Charge)

    public static final int KNUCKLE_BOOSTER = 15101022; //Buff

    public static final int GALE = 15111022; //Special Attack (Charge)
    public static final int LINK_MASTERY = 15110025; //Special Passive TODO  (Activates upon linking Attack skills, gains Icon)

    public static final int ARC_CHARGER = 15121004; //Buff
    public static final int SPEED_INFUSION = 15121005; //Buff
    public static final int CALL_OF_CYGNUS_TB = 15121000; //Buff

    private int[] buffs = new int[] {
            LIGHTNING_ELEMENTAL,
            KNUCKLE_BOOSTER,
            LINK_MASTERY,
            ARC_CHARGER,
            SPEED_INFUSION,
            CALL_OF_CYGNUS_TB,
    };

    public ThunderBreaker(Char chr) {
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
            case LIGHTNING_ELEMENTAL:
                //TODO
                break;
            case KNUCKLE_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case ARC_CHARGER: // y = - y seconds Cooltime per Lightning Buff
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ShadowPartner, o1);
                break;
            case SPEED_INFUSION:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv); // TODO
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case CALL_OF_CYGNUS_TB:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;

            case LINK_MASTERY:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o1);
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
            case GALE:
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
            case NOBLESSE:
            case THUNDERBREAKER1:
            case THUNDERBREAKER2:
            case THUNDERBREAKER3:
            case THUNDERBREAKER4:
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
