package client.jobs.cygnus;

import client.Client;
import client.character.Char;
import client.character.skills.AttackInfo;
import client.jobs.Job;
import connection.InPacket;
import constants.JobConstants;

/**
 * Created on 12/14/2017.
 */
public class NightWalker extends Job {
    public NightWalker(Char chr) {
        super(chr);
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

    }

    @Override
    public void handleSkill(Client c, InPacket inPacket) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id >= JobConstants.JobEnum.NIGHTWALKER1.getJobId() && id <= JobConstants.JobEnum.NIGHTWALKER4.getJobId();
    }
}
