package net.swordie.ms.client.character;

import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.User;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.MonsterCollectionGroup;
import net.swordie.ms.constants.MonsterCollectionRegion;
import net.swordie.ms.constants.MonsterCollectionSession;
import net.swordie.ms.loaders.MonsterCollectionData;
import net.swordie.ms.loaders.containerclasses.MonsterCollectionMobInfo;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Sjonnie
 * Created on 7/23/2018.
 */
@Entity
@Table(name = "monster_collections")
public class MonsterCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private Map<Integer, MonsterCollectionRegion> collection = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "monster_collection_mobs", joinColumns = @JoinColumn(name = "collectionid"))
    @Column(name = "mobid")
    private Set<Integer> mobs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "collectionid")
    private Set<MonsterCollectionReward> monsterCollectionRewards = new HashSet<>();

    public void init(Char chr) {
        for (int mob : getMobs()) {
            addMob(mob);
        }
        for (MonsterCollectionReward mcr : getMonsterCollectionRewards()) {
            int region = mcr.getRegion();
            int session = mcr.getSession();
            int group = mcr.getGroup();
            if (session == -1) {
                collection.get(region).setClaimedReward(true);
            } else if (group == -1) {
                collection.get(region).getMonsterCollectionSessions().get(session).setRewardClaimed(true);
            } else {
                collection.get(region).getMonsterCollectionSessions().get(session)
                        .getMonsterCollectionGroups().get(group).setRewardClaimed(true);
            }
        }
        sendCompleteInfo(chr);
    }

    public boolean hasMob(int templateID) {
        MonsterCollectionMobInfo mcmi = MonsterCollectionData.getMobInfoByID(templateID);
        return collection.containsKey(mcmi.getRegion()) &&
                collection.get(mcmi.getRegion()).hasMob(templateID, mcmi);
    }

    public void addMob(int templateID) {
        MonsterCollectionMobInfo mcmi = MonsterCollectionData.getMobInfoByID(templateID);
        if (mcmi == null) {
            return;
        }
        if (!collection.containsKey(mcmi.getRegion())) {
            collection.put(mcmi.getRegion(), new MonsterCollectionRegion());
        }
        collection.get(mcmi.getRegion()).addMob(mcmi);
        getMobs().add(templateID);
    }

    public Set<Integer> getMobs() {
        return mobs;
    }

    public void setMobs(Set<Integer> mobs) {
        this.mobs = mobs;
    }

    public void sendCompleteInfo(Char chr) {
        for (Map.Entry<Integer, MonsterCollectionRegion> entry : collection.entrySet()) {
            entry.getValue().sendCompleteInfo(chr, entry.getKey());
        }
    }

    public void addMobAndUpdateClient(int templateID, Char chr) {
        addMob(templateID);
        MonsterCollectionMobInfo mcmi = MonsterCollectionData.getMobInfoByID(templateID);
        if (mcmi == null) {
            return;
        }
        collection.get(mcmi.getRegion()).getMonsterCollectionSessions().get(mcmi.getSession()).sendCompletionInfo(chr,
                100000 + mcmi.getRegion() * 100 + mcmi.getSession());
        chr.write(User.effect(Effect.effectFromWZ("Effect/BasicEff.img/monsterCollectionGet", false, 0, 4, 0)));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<MonsterCollectionReward> getMonsterCollectionRewards() {
        return monsterCollectionRewards;
    }

    public void setMonsterCollectionRewards(Set<MonsterCollectionReward> monsterCollectionRewards) {
        this.monsterCollectionRewards = monsterCollectionRewards;
    }

    public MonsterCollectionRegion getRegion(int region) {
        return collection.getOrDefault(region, null);
    }

    public MonsterCollectionSession getSession(int region, int session) {
        if (getRegion(region) != null) {
            return getRegion(region).getMonsterCollectionSessions().getOrDefault(session, null);
        }
        return null;
    }

    public MonsterCollectionGroup getGroup(int region, int session, int group) {
        if (getSession(region, session) != null) {
            return getSession(session, region).getMonsterCollectionGroups().getOrDefault(group, null);
        }
        return null;
    }

    public boolean isComplete(int region, int session, int groupID) {
        MonsterCollectionGroup group = getGroup(region, session, groupID);
        if (groupID == -1) {
            boolean complete = true;
            for (Map.Entry<Integer, MonsterCollectionGroup> entry : getSession(region, session).getMonsterCollectionGroups().entrySet()) {
                complete &= isComplete(region, session, entry.getKey());
            }
            return complete;
        }
        return group != null && group.getMobs().size() >= MonsterCollectionData.getRequiredMobs(region, session, groupID);
    }
}
