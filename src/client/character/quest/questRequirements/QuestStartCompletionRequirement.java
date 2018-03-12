package client.character.quest.questRequirements;

import client.character.Char;
import client.character.quest.QuestManager;
import loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
public class QuestStartCompletionRequirement implements QuestStartRequirement {
    private int questID;
    private byte questStatus;

    public QuestStartCompletionRequirement() {}

    public QuestStartCompletionRequirement(int questID, byte questStatus) {
        this.questID = questID;
        this.questStatus = questStatus;
    }

    public int getQuestID() {
        return questID;
    }

    public byte getQuestStatus() {
        return questStatus;
    }

    public void setQuestID(int questID) {
        this.questID = questID;
    }

    public void setQuestStatus(byte questStatus) {
        this.questStatus = questStatus;
    }

    @Override
    public boolean hasRequirements(Char chr) {
        QuestManager qm = chr.getQuestManager();
        switch(getQuestStatus()) {
//            case 0: // Not started
//                return !qm.hasQuestInProgress(getQuestID()) && !qm.hasQuestCompleted(getQuestID());
//            case 1: // In progress
//                return qm.hasQuestInProgress(getQuestID());
            case 0: // Completed
                return qm.hasQuestCompleted(getQuestID());
            default:
                System.err.printf("[Error] Unknown status %d%n.", getQuestStatus());
                return true;
        }
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(getQuestID());
        dos.writeByte(getQuestStatus());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        QuestStartCompletionRequirement qscr = new QuestStartCompletionRequirement();
        qscr.setQuestID(dis.readInt());
        qscr.setQuestStatus(dis.readByte());
        return qscr;
    }
}
