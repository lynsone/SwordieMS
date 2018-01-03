package client.jobs.sengoku;

import client.Client;
import client.character.skills.AttackInfo;
import client.jobs.Job;
import connection.InPacket;

/**
 * Created on 12/14/2017.
 */
public class Hayato extends Job {
    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

    }

    @Override
    public void handleSkill(Client c, InPacket inPacket) {

    }

    @Override
    public boolean isHandlerOfJob(short job) {
        return false;
    }
}
