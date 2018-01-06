package client.jobs.sengoku;

import client.Client;
import client.character.skills.AttackInfo;
import client.jobs.Job;
import connection.InPacket;
import constants.JobConstants;

/**
 * Created on 12/14/2017.
 */
public class Kanna extends Job {
    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

    }

    @Override
    public void handleSkill(Client c, InPacket inPacket) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.KANNA.getJobId() ||
                (id >= JobConstants.JobEnum.KANNA1.getJobId() && id <= JobConstants.JobEnum.KANNA4.getJobId());
    }
}
