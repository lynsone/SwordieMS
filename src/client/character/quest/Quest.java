package client.character.quest;

import client.character.quest.QuestProgressRequirement.QuestProgressItemRequirement;
import client.character.quest.QuestProgressRequirement.QuestProgressMobRequirement;
import client.character.quest.QuestProgressRequirement.QuestProgressRequirement;
import client.character.quest.QuestProgressRequirement.QuestValueRequirement;
import enums.QuestStatus;
import org.python.antlr.op.In;
import util.FileTime;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created on 12/20/2017.
 */
public class Quest {
    private int QRKey;
    private QuestStatus status;
    private List<QuestProgressRequirement> progressRequirements;
    private FileTime completedTime;

    public Quest() {
        progressRequirements = new ArrayList<>();
    }

    public Quest(int QRKey, QuestStatus status) {
        this();
        this.QRKey = QRKey;
        this.status = status;
    }

    public int getQRKey() {
        return QRKey;
    }

    public void setQRKey(int QRKey) {
        this.QRKey = QRKey;
    }

    public String getQRValue() {
        StringBuilder sb = new StringBuilder();
        for(QuestProgressRequirement qpr : getProgressRequirements()) {
            if(qpr instanceof QuestValueRequirement) {
                sb.append(Util.leftPaddedString(3, '0', ((QuestValueRequirement) qpr).getValue()));
            }
        }
        return sb.toString();
    }

    public QuestStatus getStatus() {
        return status;
    }

    public void setStatus(QuestStatus status) {
        this.status = status;
    }

    public Quest deepCopy() {
        Quest quest = new Quest();
        quest.setQRKey(getQRKey());
        for(QuestProgressRequirement qpr : getProgressRequirements()) {
            quest.addQuestProgressRequirement(qpr);
        }
        quest.setStatus(getStatus());
        return quest;
    }

    public List<QuestProgressRequirement> getProgressRequirements() {
        return progressRequirements;
    }

    public void addQuestProgressRequirement(QuestProgressRequirement qpr) {
        getProgressRequirements().add(qpr);
    }

    public List<QuestProgressMobRequirement> getMobReqs() {
        return getProgressRequirements().stream().filter(qpr -> qpr instanceof QuestProgressMobRequirement)
                .map(qpr -> (QuestProgressMobRequirement) qpr).collect(Collectors.toList());
    }

    public List<QuestProgressItemRequirement> getItemReqs() {
        return getProgressRequirements().stream().filter(qpr -> qpr instanceof QuestProgressItemRequirement)
                .map(qpr -> (QuestProgressItemRequirement) qpr).collect(Collectors.toList());
    }

    public QuestProgressMobRequirement getMobReqByMobID(int mobID) {
        return getMobReqs().stream().filter(qpmr -> qpmr.getId() == mobID).findFirst().orElse(null);
    }

    public boolean hasMobReq(int mobID) {
        return getMobReqByMobID(mobID) != null;
    }

    public FileTime getCompletedTime() {
        return completedTime;
    }

    public void completeQuest() {
        setStatus(QuestStatus.COMPLETE);
        setCompletedTime(FileTime.getTime());
    }

    public void setCompletedTime(FileTime completedTime) {
        this.completedTime = completedTime;
    }

    public boolean isComplete() {
        return getProgressRequirements().stream().allMatch(QuestProgressRequirement::isComplete);
    }

    public void handleMobKill(int mobID) {
        QuestProgressMobRequirement qpmr = (QuestProgressMobRequirement) getProgressRequirements()
                .stream()
                .filter(q -> q instanceof QuestProgressMobRequirement &&
                        ((QuestProgressMobRequirement) q).getId() == mobID)
                .findFirst().get();
        // should never return null, as this method should only be called when this quest indeed has this mob
        qpmr.incCurrentCount(1);
    }

    @Override
    public String toString() {
        return String.format("%d, %s", getQRKey(), getQRValue());
    }
}
