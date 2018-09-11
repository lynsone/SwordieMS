package net.swordie.ms.scripts;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2/19/2018.
 */
public class ScriptInfo {
    private ScriptType scriptType;
    private ScriptEngine scriptEngine;
    private int parentID;
    private String scriptName;
    private Invocable invocable;
    private final Lock lock = new ReentrantLock();
    private Object response;
    private int objectID;
    private String fileDir;
    private boolean isActive;

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
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void reset() {
        setResponse(null);
        setParentID(0);
        setScriptName("");
        setInvocable(null);
        setActive(false);
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public void setResponse(Object response) {
        this.response = response;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public Object awaitResponse() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                // intended
            }
        }
        return response;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFileDir() {
        return fileDir;
    }
}
