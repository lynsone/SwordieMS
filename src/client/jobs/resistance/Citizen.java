package client.jobs.resistance;

import client.jobs.Job;
import constants.JobConstants;

/**
 * Created on 12/14/2017.
 */
public class Citizen extends Job {

    @Override
    public JobConstants.JobEnum getJobEnum() {
        return JobConstants.JobEnum.CITIZEN;
    }
}
