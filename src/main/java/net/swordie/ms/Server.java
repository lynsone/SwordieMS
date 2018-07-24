package net.swordie.ms;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.loaders.*;
import net.swordie.ms.connection.crypto.MapleCrypto;
import net.swordie.ms.connection.db.DatabaseConnection;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.netty.ChannelAcceptor;
import net.swordie.ms.connection.netty.ChatAcceptor;
import net.swordie.ms.connection.netty.LoginAcceptor;
import net.swordie.ms.world.Channel;
import net.swordie.ms.world.World;
import net.swordie.ms.world.shop.cashshop.CashShop;
import net.swordie.ms.world.shop.cashshop.CashShopCategory;
import net.swordie.ms.world.shop.cashshop.CashShopItem;
import org.apache.log4j.Category;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import net.swordie.ms.util.Loader;
import net.swordie.ms.util.container.Tuple;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.*;

/**
 * Created on 2/18/2017.
 */
public class Server extends Properties {

	final Logger log = LogManager.getRootLogger();

	private static final Server server = new Server();

	private List<World> worldList = new ArrayList<>();
	private List<Account> accounts = new ArrayList<>();
	private CashShop cashShop;

	public static Server getInstance() {
		return server;
	}

	public List<World> getWorlds() {
		return worldList;
	}

	public World getWorldById(int id) {
		return getWorlds().stream().filter(w -> w.getWorldId() == id).findFirst().orElse(null);
	}

	private void init(String[] args) {
		log.info("Starting server.");
		long start = System.currentTimeMillis();
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String s : properties.stringPropertyNames()) {
			System.setProperty(s, properties.getProperty(s));
		}

		ServerConfig.SQL_SCHEMA = properties.getProperty("SQL_SCHEMA");
		ServerConfig.SQL_PORT = properties.getProperty("SQL_PORT");
		ServerConfig.SQL_USERNAME = properties.getProperty("SQL_USERNAME");
		ServerConfig.SQL_PASSWORD = properties.getProperty("SQL_PASSWORD");


		long startNow = System.currentTimeMillis();
		DatabaseManager.init();
		log.info("Loaded Hibernate in " + (System.currentTimeMillis() - startNow) + "ms");

		try {
			checkAndCreateDat();
			loadWzData();
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		StringData.loadItemStrings();
		StringData.loadSkillStrings();

		MapleCrypto.initialize(ServerConstants.VERSION);
		new Thread(new LoginAcceptor()).start();
		new Thread(new ChatAcceptor()).start();
		worldList.add(new World(1, "Je Moeder", 3));
		startNow = System.currentTimeMillis();
		initCashShop();
		log.info("Loaded Cash Shop in " + (System.currentTimeMillis() - startNow) + "ms");

		MonsterCollectionData.loadFromSQL();

		for (World world : getWorlds()) {
			for (Channel channel : world.getChannels()) {
				ChannelAcceptor ca = new ChannelAcceptor();
				ca.channel = channel;
				new Thread(ca).start();
			}
		}
		long end = System.currentTimeMillis();
		log.info(String.format("Finished loading server in %dms", end - start));
	}

	private void checkAndCreateDat() {
		File file = new File(ServerConstants.DAT_DIR + "/equips");
		boolean exists = file.exists();
		if (!exists) {
			log.info("Dat files cannot be found (at least not the equip dats). All dats will now be generated. This may take a long while.");
			for (Class c : DataClasses.datCreators) {
				try {
					Method m = c.getMethod("generateDatFiles");
					m.invoke(null);
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void loadWzData() throws IllegalAccessException, InvocationTargetException {
		String datFolder = ServerConstants.DAT_DIR;
		for (Class c : DataClasses.dataClasses) {
			for (Method method : c.getMethods()) {
				String name;
				Loader annotation = method.getAnnotation(Loader.class);
				if (annotation != null) {
					name = annotation.varName();
					File file = new File(datFolder, name + ".dat");
					boolean exists = file.exists();
					long start = System.currentTimeMillis();
					method.invoke(c, file, exists);
					long total = System.currentTimeMillis() - start;
					if (exists) {
						log.info(String.format("Took %dms to load from %s", total, file.getName()));
					} else {
						log.info(String.format("Took %dms to load using %s", total, method.getName()));
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		getInstance().init(args);
	}

	public Connection getDatabaseConnection() {
		return DatabaseConnection.getConnection();
	}

	public Session getNewDatabaseSession() {
		cleanSessions();
		return DatabaseManager.getSession();
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public Tuple<Byte, Client> getChannelFromTransfer(int charId, int worldId) {
		for (Channel c : getWorldById(worldId).getChannels()) {
			if (c.getTransfers().containsKey(charId)) {
				return c.getTransfers().get(charId);
			}
		}
		return null;
	}

	public void cleanSessions() {
		DatabaseManager.cleanUpSessions();
	}

	public void clearCache() {
		DropData.clear();
		FieldData.clear();
		ItemData.clear();
		MobData.clear();
		NpcData.clear();
		QuestData.clear();
		SkillData.clear();
		ReactorData.clear();
	}

	public void initCashShop() {
		cashShop = new CashShop();
		try(Session session = DatabaseManager.getSession()) {
			Transaction transaction = session.beginTransaction();

			Query query = session.createQuery("FROM CashShopCategory");
			List<CashShopCategory> categories = query.list();
			categories.sort(Comparator.comparingInt(CashShopCategory::getIdx));
			cashShop.setCategories(new ArrayList<>(categories));

			query = session.createQuery("FROM CashShopItem");
			List<CashShopItem> items = query.list();
			items.forEach(item -> cashShop.addItem(item));

			transaction.commit();
		}

	}

	public CashShop getCashShop() {
		return this.cashShop;
	}
}
