package net.db;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.Server;

/**
 * Created on 12/13/2017.
 */
public class DBObject {
    public void updateDB(Session session, Transaction tx) {
        session.update(this);
    }

    public void updateDB(Session session) {
        Transaction tx = session.beginTransaction();
        session.update(this);
        tx.commit();
    }

    public void updateDB() {
        Session session = Server.getInstance().getNewDatabaseSession();
        Transaction tx = session.beginTransaction();
        session.update(this);
        tx.commit();
        session.close();
    }
}
