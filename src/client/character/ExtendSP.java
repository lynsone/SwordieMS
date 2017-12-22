package client.character;

import connection.OutPacket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2/18/2017.
 */
@Entity
@Table(name = "extendSP")
public class ExtendSP {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToMany
    @JoinColumn(name = "extendSP_id")
    private List<SPSet> spSet;

    public ExtendSP() {
        this(0);
    }

    public ExtendSP(int subJobs) {
        spSet = new ArrayList<>();
        for(int i = 1; i <= subJobs; i++) {
            spSet.add(new SPSet((byte) i, 0));
        }
    }

    public List<SPSet> getSpSet() {
        return spSet;
    }

    public int getTotalSp() {
        return spSet.stream().mapToInt(SPSet::getSp).sum();
    }

    public void setSpSet(List<SPSet> spSet) {
        this.spSet = spSet;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getSpSet().size());
        for(SPSet spSet : getSpSet()) {
            outPacket.encodeByte(spSet.getJobLevel());
            outPacket.encodeInt(spSet.getSp());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateDB(Session session, Transaction tx) {
        for(SPSet spSet : getSpSet()) {
            spSet.updateDB(session, tx);
        }
        session.update(this);
    }

    public void createInDB(Session session, Transaction tx) {
        for(SPSet spSet : getSpSet()) {
            spSet.createInDB(session, tx);
        }
        session.save(this);
    }

    public void deleteFromDB(Session session, Transaction tx) {
        for(SPSet spSet : getSpSet()) {
            spSet.deleteFromDB(session, tx);
        }
        session.delete(this);
    }
}
