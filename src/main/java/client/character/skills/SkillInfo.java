package client.character.skills;

import util.Rect;
import util.Util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private boolean invisible;
    private int masterLevel;
    private int fixLevel;
    private List<Rect> rects = new ArrayList<>();
    private boolean massSpell;

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
        if(value == null) {
            return 0;
        }
        if(Util.isNumber(value)) {
            result = Integer.parseInt(value);
        } else {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            try {
                value = value.replace("u", "Math.ceil");
                value = value.replace("d", "Math.floor");
                Object res = engine.eval(value.replace("x", slv + ""));
                if(res instanceof Integer) {
                    result = (Integer) res;
                } else if(res instanceof Double) {
                    result = ((Double) res).intValue();
                }
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public int getMasterLevel() {
        return masterLevel;
    }

    public void setMasterLevel(int masterLevel) {
        this.masterLevel = masterLevel;
    }

    public int getFixLevel() {
        return fixLevel;
    }

    public void setFixLevel(int fixLevel) {
        this.fixLevel = fixLevel;
    }

    public void addRect(Rect rect) {
        getRects().add(rect);
    }

    public List<Rect> getRects() {
        return rects;
    }

    public Rect getLastRect() {
        return rects != null && rects.size() > 0 ? rects.get(rects.size() - 1) : null;
    }

    public Rect getFirstRect() {
        return rects != null && rects.size() > 0 ? rects.get(0) : null;
    }

    public boolean isMassSpell() {
        return massSpell;
    }

    public void setMassSpell(boolean massSpell) {
        this.massSpell = massSpell;
    }
}
