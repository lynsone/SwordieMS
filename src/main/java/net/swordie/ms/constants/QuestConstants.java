package net.swordie.ms.constants;

/**
 * @author Sjonnie
 * Created on 8/19/2018.
 */
public class QuestConstants {
    public static final int WILD_HUNTER_JAGUAR_STORAGE_ID = 23008;
    public static final int DIMENSION_LIBRARY = 32600;

    public static final int SILENT_CRUSADE_WANTED_TAB_1 = 1648;
    public static final int SILENT_CRUSADE_WANTED_TAB_2 = 1649;
    public static final int SILENT_CRUSADE_WANTED_TAB_3 = 1650;
    public static final int SILENT_CRUSADE_WANTED_TAB_4 = 1651;

    public static final int MEDAL_REISSUE_QUEST = 29949;

    public static String getWhStorageQuestValByTemplateID(int templateId) {
        if (templateId >= 9304000 && templateId <= 9304008) {
            return String.valueOf((templateId % 10) + 1);
        }
        return null;
    }
}
