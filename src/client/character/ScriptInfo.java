package client.character;

import enums.ScriptType;

import javax.script.Invocable;
import javax.script.ScriptEngine;

/**
 * Created on 2/19/2018.
 */
public class ScriptInfo {
    private ScriptType scriptType;
    private ScriptEngine scriptEngine;
    private int parentID;
    private String scriptName;
    private Invocable invocable;

    public ScriptInfo(ScriptType scriptType, ScriptEngine scriptEngine, int parentID, String scriptName) {
        this.scriptType = scriptType;
        this.scriptEngine = scriptEngine;
        this.parentID = parentID;
        this.scriptName = scriptName;
    }

    public ScriptInfo(ScriptType scriptType, ScriptEngine scriptEngine, int parentID, String scriptName, Invocable invocable) {
        this.scriptType = scriptType;
        this.scriptEngine = scriptEngine;
        this.parentID = parentID;
        this.scriptName = scriptName;
        this.invocable = invocable;
    }

    public ScriptType getScriptType() {
        return scriptType;
    }

    public void setScriptType(ScriptType scriptType) {
        this.scriptType = scriptType;
    }

    public ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    public void setScriptEngine(ScriptEngine scriptEngine) {
        this.scriptEngine = scriptEngine;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public Invocable getInvocable() {
        return invocable;
    }

    public void setInvocable(Invocable invocable) {
        this.invocable = invocable;
    }

    public boolean isActive() {
        return getScriptEngine() != null;
    }

    public void reset() {
        setScriptEngine(null);
        setParentID(0);
        setScriptName("");
        setInvocable(null);
    }
}
