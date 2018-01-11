package client.jobs.sengoku;

import client.Client;
import client.character.Char;
import client.character.skills.AttackInfo;
import client.jobs.Job;
import connection.InPacket;
import constants.JobConstants;

/**
 * Created on 12/14/2017.
 */
public class Hayato extends Job {
    public Hayato(Char chr) {
        super(chr);
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.HAYATO.getJobId() ||
                (id >= JobConstants.JobEnum.HAYATO1.getJobId() && id <= JobConstants.JobEnum.HAYATO4.getJobId());
    }
}
