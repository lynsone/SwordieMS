package net.swordie.ms.client.character;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;

/**
 * Created on 2/18/2017.
 */
@Entity
@Table(name = "spSet")
public class SPSet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "jobLevel")
    private byte jobLevel;
    @Column(name = "sp")
    private int sp;

    public SPSet() {
    }

    public SPSet(byte jobLevel, int sp) {
        this.jobLevel = jobLevel;
        this.sp = sp;
    }

    public void setJobLevel(byte jobLevel) {
        this.jobLevel = jobLevel;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public byte getJobLevel() {
        return jobLevel;
    }

    public int getSp() {
        return sp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
