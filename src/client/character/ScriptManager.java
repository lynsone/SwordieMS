package client.character;

import client.field.Field;
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

    public NpcScriptInfo getNpcScriptInfo() {
        return npcScriptInfo;
    }

    // Start of the sends/asks ----------------------------------------------------------------------

    public void sendSay(String text) {
        if(text.contains("#L")) {
            sendGeneralSay(text, AskMenu);
        } else {
            sendGeneralSay(text, Say);
        }
    }

    private void sendGeneralSay(String text, NpcMessageType nmt) {
        getNpcScriptInfo().setText(text);
        if(text.contains("#L")) {
            nmt = AskMenu;
        }
        getNpcScriptInfo().setMessageType(nmt);
        chr.write(ScriptMan.scriptMessage(this, nmt));
    }

    public void sendNext(String text) {
        sendGeneralSay(text, SayNext);
    }

    public void sendPrev(String text) {
        sendGeneralSay(text, SayPrev);
    }

    public void sendSayOkay(String text) {
        sendGeneralSay(text, SayOk);
    }

    public void sendSayImage(String[] images) {
        getNpcScriptInfo().setImages(images);
        getNpcScriptInfo().setMessageType(SayImage);
        chr.write(ScriptMan.scriptMessage(this, SayImage));
    }

    public void sendAskYesNo(String text) {
        sendGeneralSay(text, AskYesNo);
    }

    public void sendAskText(String text, String defaultText, short minLength, short maxLength) {
        getNpcScriptInfo().setMin(minLength);
        getNpcScriptInfo().setMax(maxLength);
        getNpcScriptInfo().setDefaultText(defaultText);
        sendGeneralSay(text, AskText);
    }

    public void sendAskNumber(String text, int defaultNum, int min, int max) {
        getNpcScriptInfo().setDefaultNumber(defaultNum);
        getNpcScriptInfo().setMin(min);
        getNpcScriptInfo().setMax(max);
        sendGeneralSay(text, AskNumber);
    }

    public void sendInitialQuiz(byte type, String title, String problem, String hint, int min, int max, int time) {
        NpcScriptInfo nsi = getNpcScriptInfo();
        nsi.setType(type);
        if(type != 1) {
            nsi.setTitle(title);
            nsi.setProblemText(problem);
            nsi.setHintText(hint);
            nsi.setMin(min);
            nsi.setMax(max);
            nsi.setTime(time);
        }
        chr.write(ScriptMan.scriptMessage(this, InitialQuiz));
    }

    public void sendInitialSpeedQuiz(byte type, int quizType, int answer, int correctAnswers, int remaining, int time) {
        NpcScriptInfo nsi = getNpcScriptInfo();
        nsi.setType(type);
        if(type != 1) {
            nsi.setQuizType(quizType);
            nsi.setAnswer(answer);
            nsi.setCorrectAnswers(correctAnswers);
            nsi.setRemaining(remaining);
            nsi.setTime(time);
        }
        chr.write(ScriptMan.scriptMessage(this, InitialSpeedQuiz));
    }

    public void sendICQuiz(byte type, String text, String hintText, int time) {
        getNpcScriptInfo().setType(type);
        getNpcScriptInfo().setHintText(hintText);
        getNpcScriptInfo().setTime(time);
        sendGeneralSay(text, ICQuiz);
    }

    public void sendAskAvatar(String text, boolean angelicBuster, boolean zeroBeta) {
        getNpcScriptInfo().setAngelicBuster(angelicBuster);
        getNpcScriptInfo().setZeroBeta(zeroBeta);
        sendGeneralSay(text, AskAvatar);
    }

    // Start helper methods for scripts -------------------------------------------------------------

    public void dispose() {
        WvsContext.dispose(chr);
    }

    public void warp(int id) {
        Field field = chr.getClient().getChannelInstance().getField(id);
        chr.warp(field);
    }


}
