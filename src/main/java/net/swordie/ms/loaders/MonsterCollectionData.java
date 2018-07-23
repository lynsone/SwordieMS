package net.swordie.ms.loaders;

import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.constants.MonsterCollectionRegion;
import net.swordie.ms.loaders.containerclasses.MonsterCollectionGroupRewardInfo;
import net.swordie.ms.loaders.containerclasses.MonsterCollectionMobInfo;
import net.swordie.ms.loaders.containerclasses.MonsterCollectionSessionRewardInfo;
import net.swordie.ms.util.container.Triple;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

/**
 * @author Sjonnie
 * Created on 7/23/2018.
 */
public class MonsterCollectionData {
    private static final Logger log = Logger.getLogger(MonsterCollectionData.class);

    private static Map<Integer, MonsterCollectionRegion> monsterCollectionInfo = new HashMap<>();
    private static Map<Integer, Triple<Integer, Integer, Integer>> monsterInfo = new HashMap<>();

    public static void loadFromSQL() {
        long start = System.currentTimeMillis();
        Session session = DatabaseManager.getSession();
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("from MonsterCollectionSessionRewardInfo");
        List<MonsterCollectionSessionRewardInfo> sessionRewardInfos = new ArrayList<>(q.list());
        q = session.createQuery("from MonsterCollectionGroupRewardInfo");
        List<MonsterCollectionGroupRewardInfo> groupRewardInfos = new ArrayList<>(q.list());
        q = session.createQuery("from MonsterCollectionMobInfo");
        List<MonsterCollectionMobInfo> mobInfos = new ArrayList<>(q.list());
        t.commit();
        session.close();
        mobInfos.forEach(mi -> {
            put(mi);
            monsterInfo.put(mi.getMobID(), new Triple(mi.getRegion(), mi.getSession(), mi.getPosition()));
        });
        for (MonsterCollectionSessionRewardInfo mcsri : sessionRewardInfos) {
            monsterCollectionInfo.get(mcsri.getRegion()).getMonsterCollectionSessions().get(mcsri.getSession())
                    .setReward(mcsri.getRewardID());
            monsterCollectionInfo.get(mcsri.getRegion()).getMonsterCollectionSessions().get(mcsri.getSession())
                    .setRewardQuantity(mcsri.getRewardID());
        }
        for (MonsterCollectionGroupRewardInfo mcgri : groupRewardInfos) {
            monsterCollectionInfo.get(mcgri.getRegion()).getMonsterCollectionSessions().get(mcgri.getSession())
                    .getMonsterCollectionGroups().get(mcgri.getGroupID())
                    .setReward(mcgri.getRewardID());
            monsterCollectionInfo.get(mcgri.getRegion()).getMonsterCollectionSessions().get(mcgri.getSession())
                    .getMonsterCollectionGroups().get(mcgri.getGroupID())
                    .setRewardQuantity(mcgri.getRewardID());
        }
        log.info("Loaded MonsterCollectionData in " + (System.currentTimeMillis() - start) + "ms.");
    }

    public static void put(MonsterCollectionMobInfo mcmi) {
        if (!monsterCollectionInfo.containsKey(mcmi.getRegion())) {
            monsterCollectionInfo.put(mcmi.getRegion(), new MonsterCollectionRegion());
        }
        monsterCollectionInfo.get(mcmi.getRegion()).addMob(mcmi);
    }


    public static MonsterCollectionMobInfo getMobInfoByID(int templateID) {
        Triple<Integer, Integer, Integer> info = monsterInfo.get(templateID);
        if (info == null) {
            return null;
        }
        return new MonsterCollectionMobInfo(templateID, info.getLeft(), info.getMiddle(), info.getRight());
    }
}
