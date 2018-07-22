package net.swordie.ms.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sjonnie
 * Created on 7/22/2018.
 */
public class MonsterCollectionRegion {
    private List<MonsterCollectionSession> monsterCollectionSessions = new ArrayList<>();

    public MonsterCollectionRegion() {
    }

    public MonsterCollectionRegion(MonsterCollectionSession... sessions) {
        monsterCollectionSessions.addAll(Arrays.asList(sessions));
    }

    public List<MonsterCollectionSession> getMonsterCollectionSessions() {
        return monsterCollectionSessions;
    }

    public void setMonsterCollectionSessions(List<MonsterCollectionSession> monsterCollectionSessions) {
        this.monsterCollectionSessions = monsterCollectionSessions;
    }
}
