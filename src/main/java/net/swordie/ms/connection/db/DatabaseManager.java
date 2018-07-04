package net.swordie.ms.connection.db;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.LinkSkill;
import net.swordie.ms.client.character.*;
import net.swordie.ms.client.character.avatar.AvatarData;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.cards.CharacterCard;
import net.swordie.ms.client.character.cards.MonsterBookInfo;
import net.swordie.ms.client.character.damage.DamageSkinSaveData;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Inventory;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.items.PetItem;
import net.swordie.ms.client.character.keys.FuncKeyMap;
import net.swordie.ms.client.character.keys.Keymapping;
import net.swordie.ms.client.character.potential.CharacterPotential;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.quest.progress.*;
import net.swordie.ms.client.character.skills.ChosenSkill;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.StolenSkill;
import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.guild.GuildMember;
import net.swordie.ms.client.guild.GuildRequestor;
import net.swordie.ms.client.guild.GuildSkill;
import net.swordie.ms.client.trunk.Trunk;
import net.swordie.ms.life.Familiar;
import net.swordie.ms.world.shop.cashshop.CashItemInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.SystemTime;

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
        configuration.setProperty("autoReconnect", "true");
        Class[] dbClasses = new Class[] {
                FileTime.class,
                SystemTime.class,
                NonCombatStatDayLimit.class,
                CharacterCard.class,
                Item.class,
                Equip.class,
                Inventory.class,
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
                QuestProgressLevelRequirement.class,
                QuestProgressItemRequirement.class,
                QuestProgressMobRequirement.class,
                QuestProgressMoneyRequirement.class,
                Guild.class,
                GuildMember.class,
                GuildRequestor.class,
                GuildSkill.class,
                Friend.class,
                Macro.class,
                DamageSkinSaveData.class,
                Trunk.class,
                PetItem.class,
                MonsterBookInfo.class,
                CharacterPotential.class,
                LinkSkill.class,
                Familiar.class,
                StolenSkill.class,
                ChosenSkill.class,
                CashItemInfo.class

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
        sessions.removeAll(sessions.stream().filter(s -> !s.isOpen()).collect(Collectors.toList()));
    }

    public static void saveToDB(Object obj) {
        synchronized (obj) {
            try (Session session = getSession()) {
                Transaction t = session.beginTransaction();
                session.saveOrUpdate(obj);
                t.commit();
            }
        }
        cleanUpSessions();
    }

    public static void deleteFromDB(Object obj) {
        synchronized (obj) {
            try (Session session = getSession()) {
                Transaction t = session.beginTransaction();
                session.delete(obj);
                t.commit();
            }
        }
        cleanUpSessions();
    }

    public static Object getObjFromDB(Class clazz, int id) {
        Object o;
        try(Session session = getSession()) {
            Transaction t = session.beginTransaction();
            o = session.get(clazz, id);
            t.commit();
        }
        return o;
    }
}
