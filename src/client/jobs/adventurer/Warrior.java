package client.jobs.adventurer;

import client.Client;
import client.character.Char;
import client.character.skills.AttackInfo;
import client.jobs.Job;
import connection.InPacket;
import constants.JobConstants;
import enums.ChatMsgColour;

/**
 * Created on 12/14/2017.
 */
public class Warrior extends Job {

    private final int[] buffs = new int[]{
            1101013, // Combo attack
            1101004, // Weapon booster

    };

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

    }

    @Override
    public void handleSkill(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int skillID = inPacket.decodeInt();
        chr.chatMessage(ChatMsgColour.YELLOW, "SkillID: " + skillID);
        byte slv = inPacket.decodeByte();
    }

    @Override
    public boolean isHandlerOfJob(short jobId) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(jobId);
        switch(job) {
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
}
