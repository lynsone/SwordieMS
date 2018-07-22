package net.swordie.ms.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sjonnie
 * Created on 7/22/2018.
 */
public class MonsterCollectionGroup {
    private List<Integer> mobs = new ArrayList<>();
    private int reward;
    private boolean rewardClaimed;


    public MonsterCollectionGroup() {
    }

    public MonsterCollectionGroup(Integer... mobs) {
        this.mobs.addAll(Arrays.asList(mobs));
    }

    public List<Integer> getMobs() {
        return mobs;
    }

    public void setMobs(List<Integer> mobs) {
        this.mobs = mobs;
    }

    public void addMob(int mob) {
        getMobs().add(mob);
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public boolean isRewardClaimed() {
        return rewardClaimed;
    }

    public void setRewardClaimed(boolean rewardClaimed) {
        this.rewardClaimed = rewardClaimed;
    }
}
