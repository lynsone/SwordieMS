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
@Table(name = "questProgressItemRequirements")
public class QuestProgressItemRequirement extends QuestProgressRequirement implements QuestValueRequirement{

    @Column(name = "itemID")
    private int itemID;
    @Column(name = "requiredCount")
    private int requiredCount;
    @Column(name = "currentCount")
    private int currentCount;

    public QuestProgressItemRequirement() {
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
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
        dos.writeInt(getItemID());
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
}
