package client.jobs;

import client.Client;
import client.character.Char;
import client.character.skills.AttackInfo;
import connection.InPacket;
import constants.JobConstants;

/**
 * Created on 12/14/2017.
 */
public class Zero extends Job {
    public Zero(Char chr) {
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
        return id >= JobConstants.JobEnum.ZERO.getJobId() && id <= JobConstants.JobEnum.ZERO4.getJobId();
    }
}
