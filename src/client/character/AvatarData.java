package client.character;

import client.character.items.Inventory;
import connection.OutPacket;
import constants.JobConstants;
import net.db.DBObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import server.Server;

import javax.persistence.*;

/**
 * Created on 2/18/2017.
 */
@Entity
@Table(name = "avatarData")
public class AvatarData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "characterStat")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CharacterStat characterStat;
    @JoinColumn(name = "avatarLook")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AvatarLook avatarLook;
    @JoinColumn(name = "zeroAvatarLook")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AvatarLook zeroAvatarLook;

    public AvatarLook getAvatarLook() {
        return avatarLook;
    }

    public CharacterStat getCharacterStat() {
        return characterStat;
    }

    public AvatarLook getZeroAvatarLook() {
        return zeroAvatarLook;
    }

    public void setZeroAvatarLook(AvatarLook zeroAvatarLook) {
        this.zeroAvatarLook = zeroAvatarLook;
    }

    public void encode(OutPacket outPacket) {
        characterStat.encode(outPacket);
        avatarLook.encode(outPacket);
        if(JobConstants.isZero(getCharacterStat().getJob())) {
            zeroAvatarLook.encode(outPacket);
        }
    }

    public void setCharacterStat(CharacterStat characterStat) {
        this.characterStat = characterStat;
    }

    public void setAvatarLook(AvatarLook avatarLook) {
        this.avatarLook = avatarLook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateDB(Session session, Transaction tx) {
        getAvatarLook().updateDB(session, tx);
        if(getZeroAvatarLook() != null) {
            getZeroAvatarLook().updateDB(session, tx);
        }
        getCharacterStat().updateDB(session, tx);
        session.saveOrUpdate(this);
    }

    public void createInDB(Session session, Transaction tx) {
        getAvatarLook().createInDB(session, tx);
        if(getZeroAvatarLook() != null) {
            getZeroAvatarLook().createInDB(session, tx);
        }
        getCharacterStat().createInDB(session, tx);
        session.save(this);
    }

    public void deleteFromDB(Session session, Transaction tx) {
        getAvatarLook().deleteFromDB(session, tx);
        if(getZeroAvatarLook() != null) {
            getZeroAvatarLook().deleteFromDB(session, tx);
        }
        getCharacterStat().deleteFromDB(session, tx);
        session.delete(this);
    }

    public AvatarLook getAvatarLook(boolean zeroBetaState) {
        return zeroBetaState ? getZeroAvatarLook() : getAvatarLook();
    }
}
