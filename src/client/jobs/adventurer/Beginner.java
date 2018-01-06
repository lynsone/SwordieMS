package client.jobs.adventurer;

import client.Client;
import client.character.skills.AttackInfo;
import client.jobs.Job;
import connection.InPacket;
import constants.JobConstants;

/**
 * Created on 12/14/2017.
 */
public class Beginner extends Job {
    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {


    }

    @Override
    public void handleSkill(Client c, InPacket inPacket) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        switch (job) {
            case BEGINNER:
                return true;
            default:
                return false;
        }
    }
}
