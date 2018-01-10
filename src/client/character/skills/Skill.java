package client.character.skills;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Map;

/**
 * Created on 12/20/2017.
 */
@Entity
@Table(name = "skills")
public class Skill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "charId")
    private int charId;
    @Column(name = "skillId")
    private int skillId;
    @Column(name = "rootId")
    private int rootId;
    @Column(name = "maxLevel")
    private int maxLevel;
    @Column(name = "currentLevel")
    private int currentLevel;
    @Column(name = "masterLevel")
    private int masterLevel;

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }

    public void updateDB(Session session, Transaction tx) {
        session.saveOrUpdate(this);
    }

    public void setMasterLevel(int masterLevel) {
        this.masterLevel = masterLevel;
    }

    public int getMasterLevel() {
        return masterLevel;
    }

    @Override
    public String toString() {
        return "id = " + getSkillId() + ", cur = " + getCurrentLevel() + ", master = " + getMasterLevel();
    }
}
