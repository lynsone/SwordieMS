package client.character;

import connection.OutPacket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;

/**
 * Created on 2/18/2017.
 */
@Entity
@Table(name = "characterCards")
public class CharacterCard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "characterId")
    private int characterId;
    @Column(name = "job")
    private int job;
    @Column(name = "level")
    private byte level;

    public CharacterCard(){}

    public CharacterCard(int characterId, int job, byte level) {
        this.characterId = characterId;
        this.job = job;
        this.level = level;
    }

    public int getCharacterId() {
        return getId();
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public void encode(OutPacket outPacket) {
        //CHARACTERCARD::Decode
        for(int i = 0; i < 9; i++) {
            outPacket.encodeInt(getCharacterId());
            outPacket.encodeByte(getLevel());
            outPacket.encodeInt(getJob());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
