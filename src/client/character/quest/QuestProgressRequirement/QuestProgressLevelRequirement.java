package client.character.quest.QuestProgressRequirement;

import loaders.DatSerializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
@Entity
@Table(name = "questProgressLevelRequirements")
public class QuestProgressLevelRequirement extends QuestProgressRequirement {

    @Column(name = "level")
    private int level;
    @Column(name = "curLevel")
    private int curLevel;

    public QuestProgressLevelRequirement() {
    }

    public QuestProgressLevelRequirement(int level){
        this.level = level;
    }

    @Override
    public boolean isComplete() {
        return getCurLevel() >= getLevel();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurLevel() {
        return curLevel;
    }

    public void setCurLevel(int curLevel) {
        this.curLevel = curLevel;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(getLevel());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        return new QuestProgressLevelRequirement(dis.readInt());
    }
}
