package client.jobs;

import client.character.CharacterStat;
import constants.JobConstants;

/**
 * Created on 12/14/2017.
 */
public abstract class Job {

    private short id;

    public JobConstants.JobEnum getJobEnum() {
        return JobConstants.getJobEnumById(getId());
    }
    public void setDefaultCharStatValues(CharacterStat characterStat) {
        characterStat.setLevel(1);
        characterStat.setStr(4);
        characterStat.setDex(4);
        characterStat.setInt(4);
        characterStat.setLuk(4);
        characterStat.setMaxHp(50);
        characterStat.setHp(50);
        characterStat.setMaxMp(50);
        characterStat.setMp(50);
    }

    public static Job getJobById(short id) {
        Job result = null;
        if(JobConstants.isXenon(id)) {

        }
        return null;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }
}
