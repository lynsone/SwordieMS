package client.character.quest.QuestProgressRequirement;

import loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
public class QuestProgressItemRequirement implements QuestProgressRequirement, QuestValueRequirement {

    private int id;
    private int requiredCount;
    private int currentCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequiredCount() {
        return requiredCount;
    }

    public void setRequiredCount(int requiredCount) {
        this.requiredCount = requiredCount;
    }

    public void incCurrentCount(int amount) {
        requiredCount += amount;
        if(requiredCount < 0) {
            requiredCount = 0;
        }
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    @Override
    public boolean isComplete() {
        return getCurrentCount() >= getRequiredCount();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(getId());
        dos.writeInt(getRequiredCount());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        QuestProgressItemRequirement qpir = new QuestProgressItemRequirement();
        qpir.setId(dis.readInt());
        qpir.setRequiredCount(dis.readInt());
        return qpir;
    }

    @Override
    public String getValue() {
        return String.valueOf(getCurrentCount());
    }

    public void addItem(int quantity) {
        setCurrentCount(getCurrentCount() + quantity);
    }
}
