package net.db;

import client.Account;
import client.Test;
import client.character.*;
import client.character.items.Equip;
import client.character.items.Inventory;
import client.character.items.Item;
import client.character.quest.Quest;
import client.character.quest.QuestManager;
import client.character.quest.QuestProgressRequirement.*;
import client.character.skills.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import util.FileTime;
import util.SystemTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 12/12/2017.
 */
public class DatabaseManager {

    private static SessionFactory sessionFactory;
    private static List<Session> sessions;

    public static void init() {
        Configuration configuration = new Configuration().configure();
        Class[] dbClasses = new Class[] {
                FileTime.class,
                SystemTime.class,
                NonCombatStatDayLimit.class,
                CharacterCard.class,
                Item.class,
                Equip.class,
                Inventory.class,
                Test.class,
                Skill.class,
                FuncKeyMap.class,
                Keymapping.class,
                SPSet.class,
                ExtendSP.class,
                CharacterStat.class,
                AvatarLook.class,
                AvatarData.class,
                Char.class,
                Account.class,
                QuestManager.class,
                Quest.class,
                QuestProgressRequirement.class,
                QuestProgressItemRequirement.class,
                QuestProgressLevelRequirement.class,
                QuestProgressMobRequirement.class,
                QuestProgressMoneyRequirement.class
        };
        for(Class clazz : dbClasses) {
            configuration.addAnnotatedClass(clazz);
        }
        sessionFactory = configuration.buildSessionFactory();
        sessions = new ArrayList<>();
    }

    public static Session getSession() {
        Session session = sessionFactory.openSession();
        sessions.add(session);
        return session;
    }

    public static void cleanUpSessions() {
        for(Session session : sessions) {
            if(session.isOpen() && !session.isJoinedToTransaction()) {
                session.close();
            }
        }
        sessions.removeAll(sessions.stream().filter(s -> !s.isOpen()).collect(Collectors.toList()));
    }
}
