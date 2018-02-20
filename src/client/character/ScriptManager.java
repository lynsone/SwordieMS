package client.character;

import client.field.Field;
import constants.ServerConstants;
import enums.NpcMessageType;
import enums.ScriptType;
import packet.ScriptMan;
import packet.WvsContext;
import util.Util;

import javax.script.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
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

    private Char chr;
    private NpcScriptInfo npcScriptInfo;
    private Map<ScriptType, ScriptInfo> scripts;

    public ScriptManager(Char chr) {
        this.chr = chr;
        npcScriptInfo = new NpcScriptInfo();
        scripts = new HashMap<>();
    }

    public ScriptEngine getScriptEngineByType(ScriptType scriptType) {
        return getScriptInfoByType(scriptType).getScriptEngine();
    }

    public ScriptInfo getScriptInfoByType(ScriptType scriptType) {
        return scripts.get(scriptType);
    }

    public Char getChr() {
        return chr;
    }

    public String getScriptNameByType(ScriptType scriptType) {
        return getScriptInfoByType(scriptType).getScriptName();
    }

    public Invocable getInvocableByType(ScriptType scriptType) {
        return getScriptInfoByType(scriptType).getInvocable();
    }

    public int getParentIDByScriptType(ScriptType scriptType) {
        return getScriptInfoByType(scriptType).getParentID();
    }

    public void startScript(int parentID, String scriptName, ScriptType scriptType) {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(SCRIPT_ENGINE_NAME);
        scriptEngine.put("sm", this);
        ScriptInfo scriptInfo = new ScriptInfo(scriptType, scriptEngine, parentID, scriptName);
        getScripts().put(scriptType, scriptInfo);
        Invocable inv = getInvocableFromScriptNameAndType(scriptName, scriptType);
        getScripts().get(scriptType).setInvocable(inv);
        try {
            getInvocableByType(scriptType).invokeFunction("init");
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private Invocable getInvocableFromScriptNameAndType(String name, ScriptType scriptType) {
        String dir = String.format("%s\\%s\\%s%s", ServerConstants.SCRIPT_DIR,
                scriptType.toString().toLowerCase(), name, SCRIPT_ENGINE_EXTENSION);
        boolean exists = new File(dir).exists();
        if(!exists) {
            System.err.printf("[Error] Could not find script %s.%n", name);
            chr.chatMessage(YELLOW, "[Script] Could not find script " + name);
            dir = DEFAULT_SCRIPT;
        }
        CompiledScript cs;
        ScriptEngine se = getScriptEngineByType(scriptType);
        try {
            dir = Util.readFile(dir, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            cs = ((Compilable) se).compile(dir);
            cs.eval();
        } catch (ScriptException e) {
            System.err.printf("[Error] Unable to compile script %s!%n", name);
            e.printStackTrace();
        }
        return (Invocable) se;
    }

    public void stop(ScriptType scriptType) {
        getScriptInfoByType(scriptType).reset();
        WvsContext.dispose(chr);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO listening for updates if needed
    }

    public void handleAction(ScriptType scriptType, byte lastType, byte response, int answer) {
        switch(response) {
            case -1:
                stop(scriptType);
                break;
            case 0:
            case 1:
                try {
                    if(isActive(scriptType)) {
                        getInvocableByType(scriptType).invokeFunction("action", response, answer);
                    }
                } catch (ScriptException | NoSuchMethodException e) {
                    e.printStackTrace();
                    stop(scriptType);
                }
        }
    }

    private boolean isActive(ScriptType scriptType) {
        return getScriptInfoByType(scriptType).isActive();
    }

    public NpcScriptInfo getNpcScriptInfo() {
        return npcScriptInfo;
    }

    public Map<ScriptType, ScriptInfo> getScripts() {
        return scripts;
    }


    // Start of the sends/asks ----------------------------------------------------------------------

    /**
     * Sends a normal chat window with prev/next buttons enabled.
     * @param text The text to say.
     */
    public void sendSay(String text) {
        sendGeneralSay(text, Say);
    }

    /**
     * Helper function that ensures that selections have the appropriate type (AskMenu).
     * @param text
     * @param nmt
     */
    private void sendGeneralSay(String text, NpcMessageType nmt) {
        getNpcScriptInfo().setText(text);
        if(text.contains("#L")) {
            nmt = AskMenu;
        }
        getNpcScriptInfo().setMessageType(nmt);
        chr.write(ScriptMan.scriptMessage(this, nmt));
    }

    /**
     * Sends a normal chat window with just the next button enabled.
     * @param text The text to say.
     */
    public void sendNext(String text) {
        sendGeneralSay(text, SayNext);
    }

    /**
     * Sends a normal chat window with just the prev button enabled.
     * @param text The text to say.
     */
    public void sendPrev(String text) {
        sendGeneralSay(text, SayPrev);
    }

    /**
     * Sends a normal chat window with just an Ok button.
     * @param text The text to say.
     */
    public void sendSayOkay(String text) {
        sendGeneralSay(text, SayOk);
    }

    /**
     * Sends a chat window with a single image from wz.
     * @param image The image location in wz.
     */
    public void sendSayImage(String image) {
        sendSayImage(new String[]{image});
    }

    /**
     * Sends a chat window with a list of images from wz.
     * @param images The window location in wz.
     */
    public void sendSayImage(String[] images) {
        getNpcScriptInfo().setImages(images);
        getNpcScriptInfo().setMessageType(SayImage);
        chr.write(ScriptMan.scriptMessage(this, SayImage));
    }

    /**
     * Sends a chat window with a yes/no option.
     * @param text The text to display.
     */
    public void sendAskYesNo(String text) {
        sendGeneralSay(text, AskYesNo);
    }

    /**
     * Sends a chat window with a text box the client can enter text in.
     * @param text The text to display.
     * @param defaultText The default text of the text box for client input.
     * @param minLength The minimum length of the input.
     * @param maxLength The maxmium length of the input.
     */
    public void sendAskText(String text, String defaultText, short minLength, short maxLength) {
        getNpcScriptInfo().setMin(minLength);
        getNpcScriptInfo().setMax(maxLength);
        getNpcScriptInfo().setDefaultText(defaultText);
        sendGeneralSay(text, AskText);
    }

    /**
     * Sends a chat window with a text box the client can enter numbers in.
     * @param text The text to display.
     * @param defaultNum The default number displayed in the text box.
     * @param min The minimum number required to enter.
     * @param max The maximum number required to enter.
     */
    public void sendAskNumber(String text, int defaultNum, int min, int max) {
        getNpcScriptInfo().setDefaultNumber(defaultNum);
        getNpcScriptInfo().setMin(min);
        getNpcScriptInfo().setMax(max);
        sendGeneralSay(text, AskNumber);
    }

    /**
     * Sends a chat window for a quiz.
     * @param type The type (0 for question, 1 for nothing)
     * @param title The title of the quiz.
     * @param problem The question of the quiz.
     * @param hint The hint of the quiz.
     * @param min The minimum length of the answer.
     * @param max The maximum length of the answer.
     * @param time The time allowed to answer the question, in seconds.
     */
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

    /**
     * Sends a chat window for an initial speed quiz.
     * @param type The type (0 for question, 1 for nothing).
     * @param quizType The type of quiz (expirement with this).
     * @param answer The correct answer.
     * @param correctAnswers Current amount of correct answers.
     * @param remaining The remaining amount of questions.
     * @param time The remaining amount of time, in seconds.
     */
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

    /**
     * Sends an IC quiz.
     * @param type The type (0 for question, 1 for nothing).
     * @param text The question for the quiz.
     * @param hintText The hint of the quiz.
     * @param time The remaining amount of time, in seconds.
     */
    public void sendICQuiz(byte type, String text, String hintText, int time) {
        getNpcScriptInfo().setType(type);
        getNpcScriptInfo().setHintText(hintText);
        getNpcScriptInfo().setTime(time);
        sendGeneralSay(text, ICQuiz);
    }

    /**
     * Sends a chat window with the user's avatar as speaker.
     * @param text The text to display.
     * @param angelicBuster Whether or not the avatar should be in its dress up form.
     * @param zeroBeta Whether or not the avatar should be in its beta form.
     */
    public void sendAskAvatar(String text, boolean angelicBuster, boolean zeroBeta) {
        getNpcScriptInfo().setAngelicBuster(angelicBuster);
        getNpcScriptInfo().setZeroBeta(zeroBeta);
        sendGeneralSay(text, AskAvatar);
    }

    // Start helper methods for scripts -------------------------------------------------------------

    /**
     * Fully disposes the npc script.
     */
    public void dispose() {
        stop(ScriptType.NPC);
    }

    /**
     * Warps the client to a given {@link Field} ID.
     * @param id The id of the Field.
     */
    public void warp(int id) {
        Field field = chr.getClient().getChannelInstance().getField(id);
        chr.warp(field);
    }


}
