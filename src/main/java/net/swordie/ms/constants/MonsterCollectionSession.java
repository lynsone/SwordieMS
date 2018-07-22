package net.swordie.ms.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author Sjonnie
 * Created on 7/22/2018.
 */
public class MonsterCollectionSession {
    private int reward;
    private List<MonsterCollectionGroup> monsterCollectionGroups;
    private boolean rewardClaimed;

    public MonsterCollectionSession() {
    }

    public MonsterCollectionSession(MonsterCollectionGroup... groups) {
        monsterCollectionGroups.addAll(Arrays.asList(groups));
    }
    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public List<MonsterCollectionGroup> getMonsterCollectionGroups() {
        return monsterCollectionGroups;
    }

    public void setMonsterCollectionGroups(List<MonsterCollectionGroup> monsterCollectionGroups) {
        this.monsterCollectionGroups = monsterCollectionGroups;
    }

    public boolean isRewardClaimed() {
        return rewardClaimed;
    }

    public void setRewardClaimed(boolean rewardClaimed) {
        this.rewardClaimed = rewardClaimed;
    }
}
