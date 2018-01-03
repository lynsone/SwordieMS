package net.db;

import client.Account;
import client.Test;
import client.character.*;
import client.character.items.Equip;
import client.character.items.Inventory;
import client.character.items.Item;
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
                Account.class,
                Char.class,
                AvatarData.class,
                AvatarLook.class,
                CharacterStat.class,
                ExtendSP.class,
                SPSet.class,
                NonCombatStatDayLimit.class,
                FileTime.class,
                SystemTime.class,
                CharacterCard.class,
                Inventory.class,
                Item.class,
                Equip.class,
                Test.class,
                Skill.class

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
