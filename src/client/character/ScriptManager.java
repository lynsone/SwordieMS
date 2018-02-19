package client.character;

import constants.ServerConstants;
import enums.MessageType;
import enums.NpcMessageType;
import enums.ScriptType;
import packet.ScriptMan;
import packet.WvsContext;
import util.Util;

import javax.script.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Observable;
import java.util.Observer;

import static enums.ChatMsgColour.YELLOW;
import static enums.NpcMessageType.*;

/**
 * Created on 2/19/2018.
 */
public class ScriptManager implements Observer {
    public static final String SCRIPT_ENGINE_NAME = "python";
    public static final String SCRIPT_ENGINE_EXTENSION = ".py";
    private static final String DEFAULT_SCRIPT = "D:\\SwordieMS\\Swordie\\scripts\\npc\\undefined.py";

    private ScriptEngine scriptEngine;
    private Char chr;
    private String scriptName;
    private int parentID;
    private ScriptType scriptType;
    private boolean running;
    private Invocable invocable;
    private NpcScriptInfo npcScriptInfo;

    public ScriptManager(Char chr) {
        this.chr = chr;
        scriptEngine = new ScriptEngineManager().getEngineByName(SCRIPT_ENGINE_NAME);
        if(scriptEngine == null) {
            System.err.println("--------------------------------------------------------------------------------------");
        }
        npcScriptInfo = new NpcScriptInfo();
    }

    public Invocable getInvocable() {
        return invocable;
    }

    public void setInvocable(Invocable invocable) {
        this.invocable = invocable;
    }

    public ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    public Char getChr() {
        return chr;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public void startScript(int parentID, String scriptName, ScriptType scriptType) {
        setParentID(parentID);
        setScriptName(scriptName);
        setScriptType(scriptType);
        setInvocable(getInvocableFromScriptName(scriptName));
        getScriptEngine().put("sm", this);
        try {
            getInvocable().invokeFunction("init");
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private Invocable getInvocableFromScriptName(String name) {
        String dir = String.format("%s\\%s\\%s%s", ServerConstants.SCRIPT_DIR,
                getScriptType().toString().toLowerCase(), name, SCRIPT_ENGINE_EXTENSION);
        boolean exists = new File(dir).exists();
        if(!exists) {
            System.err.printf("[Error] Could not find script %s.%n", name);
            chr.chatMessage(YELLOW, "[Script] Could not find script " + name);
            dir = DEFAULT_SCRIPT;
        }
        CompiledScript cs = null;
        try {
            dir = Util.readFile(dir, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            cs = ((Compilable) getScriptEngine()).compile(dir);
            cs.eval();
        } catch (ScriptException e) {
            System.err.printf("[Error] Unable to compile script %s!%n", name);
            e.printStackTrace();
        }
        return (Invocable) getScriptEngine();
    }

    public void stop() {
        setParentID(0);
        setScriptName("");
        setScriptType(ScriptType.NONE);
        dispose();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public ScriptType getScriptType() {
        return scriptType;
    }

    public void setScriptType(ScriptType scriptType) {
        this.scriptType = scriptType;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void handleAction(byte lastType, byte response, int answer) {
        switch(response) {
            case -1:
                stop();
                break;
            case 0:
            case 1:
                try {
                    getInvocable().invokeFunction("action", response, answer);
                } catch (ScriptException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
        }
    }

    // Start helper methods for scripts -------------------------------------------------------------

    public void dispose() {
        WvsContext.dispose(chr);
    }

    public NpcScriptInfo getNpcScriptInfo() {
        return npcScriptInfo;
    }

    public void sendSay(String text) {
        getNpcScriptInfo().setText(text);
        NpcMessageType mt = Say;
        if(text.contains("#L")) {
            mt = AskMenu;
        }
        getNpcScriptInfo().setMessageType(mt);
        chr.write(ScriptMan.scriptMessage(this, mt));
    }

}
