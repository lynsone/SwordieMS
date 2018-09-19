package net.swordie.ms.constants;

/**
 * @author Sjonnie
 * Created on 8/19/2018.
 */
public class QuestConstants {
    public static final int WILD_HUNTER_JAGUAR_STORAGE_ID = 23008;

    public static String getWhStorageQuestValByTemplateID(int templateId) {
        if (templateId >= 9304000 && templateId <= 9304008) {
            return String.valueOf((templateId % 10) + 1);
        }
        return null;
    }
}
