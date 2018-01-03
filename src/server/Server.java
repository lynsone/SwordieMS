package server;

import client.Account;
import client.character.Char;
import constants.ServerConfig;
import constants.ServerConstants;
import net.crypto.MapleCrypto;
import net.db.DatabaseConnection;
import net.db.DatabaseManager;
import net.netty.ChannelAcceptor;
import net.netty.LoginAcceptor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.Loader;
import loaders.DataClasses;
import util.Tuple;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.*;

/**
 * Created on 2/18/2017.
 */
public class Server extends Properties{

    private static final Server server = new Server();

    private List<World> worldList = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();

    public static Server getInstance() {
        return server;
    }

    public List<World> getWorlds() {
        return worldList;
    }

    public World getWorldById(int id) {
        return getWorlds().stream().filter(w -> w.getWorldId() == id).findFirst().orElse(null);
    }

    private void init() {
        System.out.println("[Info] Starting server.");
        long start = System.currentTimeMillis();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String s : properties.stringPropertyNames()) {
            System.setProperty(s, properties.getProperty(s));
        }

        ServerConfig.SQL_SCHEMA = properties.getProperty("SQL_SCHEMA");
        ServerConfig.SQL_PORT = properties.getProperty("SQL_PORT");
        ServerConfig.SQL_USERNAME = properties.getProperty("SQL_USERNAME");
        ServerConfig.SQL_PASSWORD = properties.getProperty("SQL_PASSWORD");


        long startNow = System.currentTimeMillis();
        DatabaseManager.init();
        System.out.println("[Info] Finished loading Hibernate in " +  (System.currentTimeMillis() - startNow) +  "ms");

//        Char chr = new Char();
//        Session session = getNewDatabaseSession();
//        Transaction tx = session.beginTransaction();
//        session.save(chr);
//        tx.commit();
//        session.close();
//
//        session = getNewDatabaseSession();
//        tx = session.beginTransaction();
//        String query = "FROM " + Char.class.getName() + " WHERE id = 1";
//        Query sql = session.createQuery(query);
//        List res = sql.list();
//        tx.commit();
//        session.close();
//
//        chr = (Char) res.get(0);

        try {
            loadWzData();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
//        ItemData.init();

        MapleCrypto.initialize(ServerConstants.VERSION);
        new Thread(new LoginAcceptor()).start();
        worldList.add(new World(1, "Je Moeder", 3));

        for(World world : getWorlds()) {
            for(Channel channel : world.getChannels()) {
                ChannelAcceptor ca = new ChannelAcceptor();
                ca.channel = channel;
                new Thread(ca).start();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("[Info] Finished loading server in " +  (end - start) +  "ms");
    }

    public void loadWzData() throws IllegalAccessException, InvocationTargetException {
        String datFolder = "D:\\SwordieMS\\Swordie\\dat";
        for(Class c : DataClasses.dataClasses) {
            for(Method method : c.getMethods()) {
                String name;
                Loader annotation = method.getAnnotation(Loader.class);
                if(annotation != null) {
                    name = annotation.varName();
                    File file = new File(datFolder, name + ".dat");
                    boolean exists = file.exists();
                    long start = System.currentTimeMillis();
                    method.invoke(c, file, exists);
                    long total = System.currentTimeMillis() - start;
                    if(exists) {
                        System.out.println("[Info] Took " + total + "ms to load from " + file.getName());
                    } else {
                        System.out.println("[Info] Took " + total +"ms to load using " + method.getName());
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        getInstance().init();
    }

    public Connection getDatabaseConnection() {
        return DatabaseConnection.getConnection();
    }

    public Session getNewDatabaseSession() {
        return DatabaseManager.getSession();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Tuple<Byte, Account> getChannelFromTransfer(int charId, int worldId) {
        for(Channel c : getWorldById(worldId).getChannels()) {
            if(c.getTransfers().containsKey(charId)) {
                return c.getTransfers().get(charId);
            }
        }
        return null;
    }
}
