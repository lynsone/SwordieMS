package client.character.skills;

import util.Util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 12/20/2017.
 */
public class SkillInfo {
    private int skillId;
    private int rootId;
    private int maxLevel;
    private int currentLevel;
    private Map<SkillStat, String> skillStatInfo = new HashMap<>();

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Map<SkillStat, String> getSkillStatInfo() {
        return skillStatInfo;
    }

    public void setSkillStatInfo(Map<SkillStat, String> skillStatInfo) {
        this.skillStatInfo = skillStatInfo;
    }

    public void addSkillStatInfo(SkillStat sc, String value) {
        getSkillStatInfo().put(sc, value);
    }

    public int getValue(SkillStat skillStat, int slv) {
        int result = 0;
        String value = getSkillStatInfo().get(skillStat);
        if(Util.isNumber(value)) {
            result = Integer.parseInt(value);
        } else {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            try {
                result = (Integer) engine.eval(value.replace("x", slv + ""));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
