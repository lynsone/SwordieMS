package net.swordie.ms.client.character.quest.QuestProgressRequirement;

import net.swordie.ms.loaders.DatSerializable;

import javax.persistence.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
@Entity
@DiscriminatorValue("level")
public class QuestProgressLevelRequirement extends QuestProgressRequirement {

    @Column(name = "requiredCount")
    private int level;
    @Column(name = "currentCount")
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
