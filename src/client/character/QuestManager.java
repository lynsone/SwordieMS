package client.character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/20/2017.
 */
public class QuestManager {
    private List<Quest> questList;

    public QuestManager() {
        questList = new ArrayList<>();
    }

    public List<Quest> getEx() {
        return questList;
    }
    public List<Quest> getCompletedQuests() {
        return questList;
    }

    public int getSize() {
        return questList.size();
    }

    public List<Quest> getQuests() {
        return questList;
    }

    public List<Quest> getQuestList() {
        return questList;
    }

    public void setQuestList(List<Quest> questList) {
        this.questList = questList;
    }

    public boolean hasQuestInProgress(int questReq) {
        // TODO
        return false;
    }
}
