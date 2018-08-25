package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * @author Sjonnie
 * Created on 7/26/2018.
 */
public enum InstanceTableType {

    HyperPassiveSkill("hyper", 28, 0),
    HyperActiveSkill("hyper", 28, 1),
    HyperStatIncAmount("incHyperStat", 0, 0),
    NeedHyperStatLv("needHyperStatLv", 0, 0),

    ;

    private String tableName;
    private int type;
    private int subType;

    InstanceTableType(String tableName, int type, int subType) {
        this.tableName = tableName;
        this.type = type;
        this.subType = subType;
    }

    public static InstanceTableType getByStr(String requestStr) {
        return Util.getFromCollectionWithPred(Arrays.asList(values()), itt -> itt.getTableName().equals(requestStr));
    }

    public String getTableName() {
        return tableName;
    }

    public int getType() {
        return type;
    }

    public int getSubType() {
        return subType;
    }
}
