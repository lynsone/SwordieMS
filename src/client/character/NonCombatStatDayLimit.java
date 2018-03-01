package client.character;


import connection.OutPacket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FileTime;

import javax.persistence.*;

/**
 * Created on 2/18/2017.
 */
@Entity
@Table(name = "nonCombatStatDayLimit")
public class NonCombatStatDayLimit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "charisma")
    private short charisma;
    @Column(name = "charm")
    private short charm;
    @Column(name = "insight")
    private short insight;
    @Column(name = "will")
    private short will;
    @Column(name = "craft")
    private short craft;
    @Column(name = "sense")
    private short sense;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ftLastUpdateCharmByCashPR")
    private FileTime ftLastUpdateCharmByCashPR;
    @Column(name = "charmByCashPR")
    private byte charmByCashPR;

    public NonCombatStatDayLimit(short charisma, short charm, byte charmByCashPR, short insight, short will, short craft, short sense, FileTime ftLastUpdateCharmByCashPR) {
        this.charisma = charisma;
        this.charm = charm;
        this.charmByCashPR = charmByCashPR;
        this.insight = insight;
        this.will = will;
        this.craft = craft;
        this.sense = sense;
        this.ftLastUpdateCharmByCashPR = ftLastUpdateCharmByCashPR;
    }

    public NonCombatStatDayLimit() {
        this((short) 0, (short) 0, (byte) 0,(short) 0,(short) 0,(short) 0,(short) 0, FileTime.getFTFromLong(0));
    }

    public short getCharm() {
        return charm;
    }

    public void setCharm(short charm) {
        this.charm = charm;
    }

    public byte getCharmByCashPR() {
        return charmByCashPR;
    }

    public void setCharmByCashPR(byte charmByCashPR) {
        this.charmByCashPR = charmByCashPR;
    }

    public short getInsight() {
        return insight;
    }

    public void setInsight(short insight) {
        this.insight = insight;
    }

    public short getWill() {
        return will;
    }

    public void setWill(short will) {
        this.will = will;
    }

    public short getCraft() {
        return craft;
    }

    public void setCraft(short craft) {
        this.craft = craft;
    }

    public short getSense() {
        return sense;
    }

    public void setSense(short sense) {
        this.sense = sense;
    }

    public FileTime getFtLastUpdateCharmByCashPR() {
        return ftLastUpdateCharmByCashPR;
    }

    public void setFtLastUpdateCharmByCashPR(FileTime ftLastUpdateCharmByCashPR) {
        this.ftLastUpdateCharmByCashPR = ftLastUpdateCharmByCashPR;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeShort(getCharisma());
        outPacket.encodeShort(getInsight());
        outPacket.encodeShort(getWill());
        outPacket.encodeShort(getCraft());
        outPacket.encodeShort(getSense());
        outPacket.encodeShort(getCharm());
        outPacket.encodeByte(getCharmByCashPR());
        getFtLastUpdateCharmByCashPR().encode(outPacket);
    }

    public short getCharisma() {
        return charisma;
    }

    public void setCharisma(short charisma) {
        this.charisma = charisma;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateDB(Session session, Transaction tx) {
        getFtLastUpdateCharmByCashPR().updateDB(session, tx);
        session.saveOrUpdate(this);
    }

    public void createInDB(Session session, Transaction tx) {
        getFtLastUpdateCharmByCashPR().createInDB(session, tx);
        session.save(this);
    }

    public void deleteFromDB(Session session, Transaction tx) {
        getFtLastUpdateCharmByCashPR().deleteFromDB(session, tx);
        session.delete(this);
    }
}
