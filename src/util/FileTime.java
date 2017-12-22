package util;

import connection.OutPacket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2/18/2017.
 */
@Entity
@Table(name = "filetimes")
public class FileTime implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "lowDateTime")
    private int lowDateTime;
    @Column(name = "highDateTime")
    private int highDateTime;

    public enum Type {
        // Mushy
        MAX_TIME(150842304000000000L),
        ZERO_TIME(94354848000000000L),
        PERMANENT(150841440000000000L),
        ;
        private long val;

        Type(long val) {
            this.val = val;
        }

        public long getVal() {
            return val;
        }
    }

    public FileTime(int lowDateTime, int highDateTime) {
        this.lowDateTime = lowDateTime;
        this.highDateTime = highDateTime;
    }

    public FileTime(){

    }

    public FileTime deepCopy() {
        return new FileTime(getLowDateTime(), getHighDateTime());
    }

    public static FileTime getFileTimeFromType(Type type) {
        return new FileTime(type.getVal());
    }

    public FileTime(long time) {
        lowDateTime = (int) time;
        highDateTime = (int) (time >> 32);
    }


    public int getLowDateTime() {
        return lowDateTime;
    }

    public int getHighDateTime() {
        return highDateTime;
    }

    public static FileTime getTime() {
        return getFTFromLong(System.currentTimeMillis());
    }

    public static FileTime getFTFromLong(long value) {
        return new FileTime((int) value, (int) (value >> 32));
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getHighDateTime());
        outPacket.encodeInt(getLowDateTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateDB(Session session, Transaction tx) {
        session.saveOrUpdate(this);
    }

    public void createInDB(Session session, Transaction tx) {
        session.save(this);
    }

    public void deleteFromDB(Session session, Transaction tx) {
        session.delete(this);
    }
}
