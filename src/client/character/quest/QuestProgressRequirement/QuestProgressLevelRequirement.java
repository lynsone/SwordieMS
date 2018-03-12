package client.character.quest.QuestProgressRequirement;

import loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
public class QuestProgressLevelRequirement implements QuestProgressRequirement {

    private int level;
    private int curLevel;

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
