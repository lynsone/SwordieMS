package client.jobs.nova;

import client.Client;
import client.character.skills.AttackInfo;
import client.jobs.Job;
import connection.InPacket;
import constants.JobConstants;

/**
 * Created on 12/14/2017.
 */
public class AngelicBuster extends Job {
    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

    }

    @Override
    public void handleSkill(Client c, InPacket inPacket) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.ANGELIC_BUSTER.getJobId() ||
                (id >= JobConstants.JobEnum.ANGELIC_BUSTER1.getJobId() && id <= JobConstants.JobEnum.ANGELIC_BUSTER4.getJobId());
    }
}
