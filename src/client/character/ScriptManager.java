package client.character;

import client.Account;
import client.character.items.Equip;
import client.character.items.Item;
import client.character.quest.Quest;
import client.character.quest.QuestManager;
import client.field.Field;
import client.field.Portal;
import client.field.fieldeffect.MobHPTagFieldEffect;
import client.guild.GuildMsg;
import client.life.Mob;
import client.life.Reactor;
import client.party.Party;
import client.party.PartyMember;
import client.shop.NpcShopDlg;
import client.trunk.TrunkOpen;
import constants.GameConstants;
import constants.ItemConstants;
import constants.ServerConstants;
import enums.*;
import enums.NpcMessageType;
import loaders.*;
import org.apache.log4j.LogManager;
import packet.*;
import util.FileTime;
import util.Position;
import util.Util;

import javax.script.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import static enums.ChatMsgColour.*;
import static enums.InventoryOperation.ADD;
import static enums.NpcMessageType.*;

/**
 * Created on 2/19/2018.
 */
@SuppressWarnings("unused")
public class ScriptManager implements Observer {
    public static final String SCRIPT_ENGINE_NAME = "python";
    public static final String SCRIPT_ENGINE_EXTENSION = ".py";
    private static final String DEFAULT_SCRIPT = "undefined";
    public static final String QUEST_START_SCRIPT_END_TAG = "s";
    public static final String QUEST_COMPLETE_SCRIPT_END_TAG = "e";
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    private Char chr;
    private Field field;
    private final boolean isField;
    private NpcScriptInfo npcScriptInfo;
    private Map<ScriptType, ScriptInfo> scripts;
    private int returnField = 0;

    public ScriptManager(Char chr) {
        this.chr = chr;
        field = chr.getField();
        npcScriptInfo = new NpcScriptInfo();
        scripts = new HashMap<>();
        isField = false;
    }

    public ScriptManager(Field field) {
        this.field = field;
        npcScriptInfo = new NpcScriptInfo();
        scripts = new HashMap<>();
        isField = true;
    }

    public ScriptEngine getScriptEngineByType(ScriptType scriptType) {
        return getScriptInfoByType(scriptType).getScriptEngine();
    }

    public ScriptInfo getScriptInfoByType(ScriptType scriptType) {
        return scripts.getOrDefault(scriptType, null);
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
        return getScriptInfoByType(scriptType) != null ? getScriptInfoByType(scriptType).getParentID() : 2007;
    }

    public int getObjectIDByScriptType(ScriptType scriptType) {
        return getScriptInfoByType(scriptType) != null ? getScriptInfoByType(scriptType).getObjectID() : 0;
    }

    public void startScript(int parentID, ScriptType scriptType) {
        startScript(parentID, 0, scriptType);
    }

    public void startScript(int parentID, String scriptName, ScriptType scriptType) {
        startScript(parentID, 0, scriptName, scriptType);
    }

    public void startScript(int parentID, int objID, ScriptType scriptType) {
        startScript(parentID, objID, parentID + ".py", scriptType);
    }

    private void startScript(int parentID, int objID, ScriptType scriptType, String initFuncName) {
        startScript(parentID, objID, parentID + ".py", scriptType, initFuncName);
    }

    public void startScript(int parentID, int objID, String scriptName, ScriptType scriptType) {
        startScript(parentID, objID, scriptName, scriptType, "init");
    }

    private void startScript(int parentID, int objID, String scriptName, ScriptType scriptType, String initFuncName) {
        if (!isField()) {
            chr.chatMessage(YELLOW, String.format("Starting script %s, scriptType %s.", scriptName, scriptType));
            log.debug(String.format("Starting script %s, scriptType %s.", scriptName, scriptType));
        }
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(SCRIPT_ENGINE_NAME);
        scriptEngine.put("sm", this);
        scriptEngine.put("parentID", parentID);
        scriptEngine.put("scriptType", scriptType);
        scriptEngine.put("objectID", objID);
        if (scriptType == ScriptType.QUEST) {
            chat(scriptName.charAt(scriptName.length() - SCRIPT_ENGINE_EXTENSION.length() - 1) + "");
            scriptEngine.put("startQuest",
                    scriptName.charAt(scriptName.length() - SCRIPT_ENGINE_EXTENSION.length() - 1) ==
                            QUEST_START_SCRIPT_END_TAG.charAt(0)); // biggest hack eu
        }
        ScriptInfo scriptInfo = new ScriptInfo(scriptType, scriptEngine, parentID, scriptName);
        scriptInfo.setObjectID(objID);
        getScripts().put(scriptType, scriptInfo);
        Invocable inv = getInvocableFromScriptNameAndType(scriptName, scriptType);
        getScripts().get(scriptType).setInvocable(inv);
        try {
            getInvocableByType(scriptType).invokeFunction(initFuncName);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private Invocable getInvocableFromScriptNameAndType(String name, ScriptType scriptType) {
        String dir = String.format("%s/%s/%s%s", ServerConstants.SCRIPT_DIR,
                scriptType.toString().toLowerCase(), name, SCRIPT_ENGINE_EXTENSION);
        boolean exists = new File(dir).exists();
        if (!exists) {
            log.error(String.format("[Error] Could not find script %s/%s", scriptType.toString().toLowerCase(), name));
            chr.chatMessage(YELLOW, String.format("[Script] Could not find script %s/%s", scriptType.toString().toLowerCase(), name));
            dir = String.format("%s/%s/%s%s", ServerConstants.SCRIPT_DIR,
                    scriptType.toString().toLowerCase(), DEFAULT_SCRIPT, SCRIPT_ENGINE_EXTENSION);
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
            log.error(String.format("Unable to compile script %s!", name));
            e.printStackTrace();
        }
        return (Invocable) se;
    }

    public void stop(ScriptType scriptType) {
        if (getScriptInfoByType(scriptType) != null) {
            getScriptInfoByType(scriptType).reset();
        }
        WvsContext.dispose(chr);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO listening for updates if needed
    }

    public void handleAction(ScriptType scriptType, byte lastType, byte response, int answer) {
        switch (response) {
            case -1:
            case 5:
                stop(scriptType);
                break;
            case 0:
            case 1:
            case 2:
                try {
                    if (isActive(scriptType)) {
                        getInvocableByType(scriptType).invokeFunction("action", response, answer);
                    } else if (!isActive(scriptType) && isActive(ScriptType.PORTAL)) {
                        getInvocableByType(ScriptType.PORTAL).invokeFunction("action", response, answer);
                    }
                } catch (ScriptException | NoSuchMethodException e) {
                    e.printStackTrace();
                    stop(scriptType);
                }
        }
    }

    public boolean isActive(ScriptType scriptType) {
        return getScriptInfoByType(scriptType) != null && getScriptInfoByType(scriptType).isActive();
    }

    public NpcScriptInfo getNpcScriptInfo() {
        return npcScriptInfo;
    }

    public Map<ScriptType, ScriptInfo> getScripts() {
        return scripts;
    }

    public int getParentID() {
        int res = 0;
        for (ScriptType type : ScriptType.values()) {
            if (getScriptInfoByType(type) != null) {
                res = getScriptInfoByType(type).getParentID();
            }
        }
        return res;
    }

    public boolean isField() {
        return isField;
    }

    public Field getField() {
        return field;
    }

    // Start of the sends/asks -----------------------------------------------------------------------------------------

    /**
     * Sends a normal chat window with prev/next buttons enabled.
     *
     * @param text The text to say.
     */
    public void sendSay(String text) {
        sendGeneralSay(text, Say);
    }

    /**
     * Helper function that ensures that selections have the appropriate type (AskMenu).
     *
     * @param text
     * @param nmt
     */
    private void sendGeneralSay(String text, NpcMessageType nmt) {
        getNpcScriptInfo().setText(text);
        if (text.contains("#L")) {
            nmt = AskMenu;
        }
        getNpcScriptInfo().setMessageType(nmt);
        chr.write(ScriptMan.scriptMessage(this, nmt));
    }

    /**
     * Sends a normal chat window with just the next button enabled.
     *
     * @param text The text to say.
     */
    public void sendNext(String text) {
        sendGeneralSay(text, SayNext);
    }

    /**
     * Sends a normal chat window with just the prev button enabled.
     *
     * @param text The text to say.
     */
    public void sendPrev(String text) {
        sendGeneralSay(text, SayPrev);
    }

    /**
     * Sends a normal chat window with just an Ok button.
     *
     * @param text The text to say.
     */
    public void sendSayOkay(String text) {
        sendGeneralSay(text, SayOk);
    }

    /**
     * Sends a chat window with a single image from wz.
     *
     * @param image The image location in wz.
     */
    public void sendSayImage(String image) {
        sendSayImage(new String[]{image});
    }

    /**
     * Sends a chat window with a list of images from wz.
     *
     * @param images The window location in wz.
     */
    public void sendSayImage(String[] images) {
        getNpcScriptInfo().setImages(images);
        getNpcScriptInfo().setMessageType(SayImage);
        chr.write(ScriptMan.scriptMessage(this, SayImage));
    }

    /**
     * Sends a chat window with a yes/no option.
     *
     * @param text The text to display.
     */
    public void sendAskYesNo(String text) {
        sendGeneralSay(text, AskYesNo);
    }

    /**
     * Sends a chat window with a text box the client can enter text in.
     *
     * @param text        The text to display.
     * @param defaultText The default text of the text box for client input.
     * @param minLength   The minimum length of the input.
     * @param maxLength   The maxmium length of the input.
     */
    public void sendAskText(String text, String defaultText, short minLength, short maxLength) {
        getNpcScriptInfo().setMin(minLength);
        getNpcScriptInfo().setMax(maxLength);
        getNpcScriptInfo().setDefaultText(defaultText);
        sendGeneralSay(text, AskText);
    }

    /**
     * Sends a chat window with a text box the client can enter numbers in.
     *
     * @param text       The text to display.
     * @param defaultNum The default number displayed in the text box.
     * @param min        The minimum number required to enter.
     * @param max        The maximum number required to enter.
     */
    public void sendAskNumber(String text, int defaultNum, int min, int max) {
        getNpcScriptInfo().setDefaultNumber(defaultNum);
        getNpcScriptInfo().setMin(min);
        getNpcScriptInfo().setMax(max);
        sendGeneralSay(text, AskNumber);
    }

    /**
     * Sends a chat window for a quiz.
     *
     * @param type    The type (0 for question, 1 for nothing)
     * @param title   The title of the quiz.
     * @param problem The question of the quiz.
     * @param hint    The hint of the quiz.
     * @param min     The minimum length of the answer.
     * @param max     The maximum length of the answer.
     * @param time    The time allowed to answer the question, in seconds.
     */
    public void sendInitialQuiz(byte type, String title, String problem, String hint, int min, int max, int time) {
        NpcScriptInfo nsi = getNpcScriptInfo();
        nsi.setType(type);
        if (type != 1) {
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
     *
     * @param type           The type (0 for question, 1 for nothing).
     * @param quizType       The type of quiz (expirement with this).
     * @param answer         The correct answer.
     * @param correctAnswers Current amount of correct answers.
     * @param remaining      The remaining amount of questions.
     * @param time           The remaining amount of time, in seconds.
     */
    public void sendInitialSpeedQuiz(byte type, int quizType, int answer, int correctAnswers, int remaining, int time) {
        NpcScriptInfo nsi = getNpcScriptInfo();
        nsi.setType(type);
        if (type != 1) {
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
     *
     * @param type     The type (0 for question, 1 for nothing).
     * @param text     The question for the quiz.
     * @param hintText The hint of the quiz.
     * @param time     The remaining amount of time, in seconds.
     */
    public void sendICQuiz(byte type, String text, String hintText, int time) {
        getNpcScriptInfo().setType(type);
        getNpcScriptInfo().setHintText(hintText);
        getNpcScriptInfo().setTime(time);
        sendGeneralSay(text, ICQuiz);
    }

    /**
     * Sends a chat window with the user's avatar as speaker.
     *
     * @param text          The text to display.
     * @param angelicBuster Whether or not the avatar should be in its dress up form.
     * @param zeroBeta      Whether or not the avatar should be in its beta form.
     */
    public void sendAskAvatar(String text, boolean angelicBuster, boolean zeroBeta) {
        getNpcScriptInfo().setAngelicBuster(angelicBuster);
        getNpcScriptInfo().setZeroBeta(zeroBeta);
        sendGeneralSay(text, AskAvatar);
    }

    // Start helper methods for scripts --------------------------------------------------------------------------------

    /**
     * Fully disposes the npc script.
     */
    public void dispose() {
        stop(ScriptType.NPC);
        stop(ScriptType.PORTAL);
        stop(ScriptType.ITEM);
        stop(ScriptType.QUEST);
        stop(ScriptType.REACTOR);
    }

    public void dispose(ScriptType scriptType) {
        stop(scriptType);
    }

    /**
     * Warps the client to a given {@link Field} ID.
     *
     * @param mid The id of the Field.
     * @param pid The portal
     */
    public void warp(int mid, int pid) {
        Field field = chr.getClient().getChannelInstance().getField(mid);
        Portal portal = chr.getField().getPortalByID(pid);
        chr.warp(field, portal);
    }

    /**
     * Warps the client to a given {@link Field} ID.
     *
     * @param id The id of the Field.
     */
    public void warp(int id) {
        Field field = chr.getClient().getChannelInstance().getField(id);
        chr.warp(field);
    }

    /**
     * Sends a red message in the main chat box.
     *
     * @param text the text to display
     */
    public void chat(String text) {
        chatRed(text);
    }

    /**
     * Sends a red message in the main chat box.
     *
     * @param text the text to display
     */
    public void chatRed(String text) {
        chr.chatMessage(GAME_MESSAGE, text);
    }

    /**
     * Sends a blue message in the main chat box.
     *
     * @param text the text to display
     */
    public void chatBlue(String text) {
        chr.chatMessage(GAME_NOTICE, text);
    }

    /**
     * Gives the client mesos. Positive amount = add, Negative amount = deduct.
     *
     * @param mesos the amount of mesos to give
     */
    public void giveMesos(int mesos) {
        chr.addMoney(mesos);
    }

    /**
     * Completes a quest without giving rewards
     * @param id the quest ID to complete
     */
    public void completeQuestNoRewards(int id) {
        QuestManager qm = chr.getQuestManager();
        Quest quest = qm.getQuests().get(id);
        if (quest == null) {
            quest = QuestData.createQuestFromId(id);
        }
        quest.setCompletedTime(FileTime.getTime());
        quest.setStatus(QuestStatus.COMPLETE);
        qm.addQuest(quest);
        chr.write(WvsContext.questRecordMessage(quest));
    }

    /**
     * Start a quest, without checking quest start requirements.
     * @param id the quest ID to start
     */
    public void startQuestNoCheck(int id) {
        QuestManager qm = chr.getQuestManager();
        qm.addQuest(QuestData.createQuestFromId(id));
    }

    public int getFieldID() {
        return chr.getField().getId();
    }

    public long getMesos() {
        return chr.getMoney();
    }

    /**
     * Gives the client a certain quantity of an item.
     *
     * @param id the item id to give
     * @param quantity the amount of items the client gets. Does not do anything for equips
     */
    public void giveItem(int id, int quantity) {
        double isEquip = Math.floor((id / 1000000));
        if (isEquip == 1) {  //Equip
            Equip equip = ItemData.getEquipDeepCopyFromID(id);
            chr.addItemToInventory(equip.getInvType(), equip, false);
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) equip.getBagIndex(), (byte) -1, 0, equip));

        } else {    //Item
            Item item = ItemData.getItemDeepCopy(id);
            item.setQuantity(quantity);
            chr.addItemToInventory(item);
            chr.getClient().write(WvsContext.inventoryOperation(true, false,
                    ADD, (short) item.getBagIndex(), (byte) -1, 0, item));

        }
    }

    /**
     * Checks if the client has a certain quantity of an item.
     *
     * @param id the item id
     * @param quantity the quantity of the item
     * @return whether or not the client has <code>quantity</code> of the given item
     */
    public boolean hasItem(int id, int quantity) {
        int q = 1;
        if (ItemConstants.isEquip(id)) {  //Equip
            Item equip = chr.getInventoryByType(InvType.EQUIP).getItemByItemID(id);
            if(equip == null) {
                return false;
            }
            q = equip.getQuantity();
        } else {
            Item item2 = ItemData.getItemDeepCopy(id);
            InvType invType = item2.getInvType();
            Item item = chr.getInventoryByType(invType).getItemByItemID(id);
            if(item == null) {
                return false;
            }
            q = item.getQuantity();
        }

        return q >= quantity;
    }

    /**
     * Checks if the client has enough space in their inventory to hold an item.
     *
     * @param id the id to check
     * @return Whether or not the chr can hold some item
     */
    public boolean canHold(int id) {
        return chr.canHold(id);
    }

    /**
     * Shows a window to the Client that is used to create a guild.
     */
    public void showGuildCreateWindow() {
        chr.write(WvsContext.guildResult(new GuildMsg(GuildResultType.InputGuildName)));
    }

    /**
     * Gets the Party of the chr.
     * @return the Party of the chr
     */
    public Party getParty() {
        return chr.getParty();
    }

    /**
     * Sets the chr's field instance type to Party, so all maps are unique per party.
     */
    public void setPartyField() {
        chr.setFieldInstanceType(FieldInstanceType.PARTY);
    }

    /**
     * Checks if this chr is the party leader, if they have a party.
     * @return
     */
    public boolean isPartyLeader() {
        return chr.getParty() != null & chr.getParty().getPartyLeaderID() == chr.getId();
    }

    /**
     * Checks whether or not all the party members are online and in the same channel, and whether or not this chr
     * is the party leader
     * @return see description
     */
    public boolean checkParty() {
        if (chr.getParty() == null) {
            chat("You are not in a party.");
            return false;
        } else if (!isPartyLeader()) {
            chat("You are not the party leader.");
            return false;
        }
        boolean res = true;
        Char leader = chr.getParty().getPartyLeader().getChr();
        if (leader == null) {
            chat("Your leader is currently offline.");
        } else {
            int fieldID = leader.getFieldID();
            for (PartyMember pm : chr.getParty().getPartyMembers()) {
                if (pm != null) {
                    res &= pm.getChr() != null && pm.isOnline() && pm.getFieldID() == fieldID;
                }
            }
        }
        if (!res) {
            chat("Make sure that your whole party is online and in the same map as the party leader.");
        }
        return res;
    }

    /**
     * Warps a whole party to a given field id. Immediately sets the field instance type to PARTY.
     * @param id the field id to warp to
     */
    public void warpParty(int id) {
        warpParty(id, true);
    }

    /**
     * Warps a whole party to a given field id. Immediately sets the field instance type to CHANNEL.
     * @param id the field id to warp to
     */
    public void warpPartyOut(int id) {
        warpParty(id, false);
    }

    public void warpParty(int id, boolean in) {
        if (chr.getParty() == null) {
            chr.setFieldInstanceType(in ? FieldInstanceType.PARTY : FieldInstanceType.CHANNEL);
            Field field = chr.getOrCreateFieldByCurrentInstanceType(id);
            chr.warp(field);
        } else {

            for (PartyMember pm : chr.getParty().getPartyMembers()) {
                if (pm != null && pm.getChr() != null) {
                    Char partyChr = pm.getChr();
                    partyChr.setFieldInstanceType(in ? FieldInstanceType.PARTY : FieldInstanceType.CHANNEL);
                    Field field = partyChr.getOrCreateFieldByCurrentInstanceType(id);
                    partyChr.warp(field);
                }
            }
        }
    }

    /**
     * Resets the party's field instance info, ensuring new field instances are created.
     */
    public void clearPartyInfo() {
        if (chr.getParty() != null) {
            chr.getParty().clearFieldInstances();
        }
    }

    /**
     * Spawns a mob at position 0,0 in the map.
     * @param id the mob template id.
     */
    public void spawnMob(int id) {
        spawnMob(id, 0, 0, false);
    }

    /**
     * Spawns a mob at position 0,0 in the map.
     * @param id the mob template id
     * @param respawnable whether or not the mob should respawn
     */
    public void spawnMob(int id, boolean respawnable) {
        spawnMob(id, 0, 0, respawnable);
    }

    /**
     * Spawns a mob on the Char's current position.
     * @param id the mob template id
     */
    public void spawnMobOnChar(int id) {
        spawnMob(id, chr.getPosition().getX(), chr.getPosition().getY(), false);
    }

    /**
     * Spawns a mob on the Char's current position.
     * @param id the mob template id
     * @param respawnable whether or not the mob should respawn
     */
    public void spawnMobOnChar(int id, boolean respawnable) {
        spawnMob(id, chr.getPosition().getX(), chr.getPosition().getY(), respawnable);
    }

    /**
     * Spawns a mob on a given position.
     * @param id the mob template id
     * @param x the starting x position
     * @param y the starting y position
     * @param respawnable whether or not the mob should respawn
     */
    public void spawnMob(int id, int x, int y, boolean respawnable) {
        chr.getField().spawnMob(id, x, y, respawnable);
    }

    /**
     * Shows the hp of a given mob.
     * @param templateID the template id of the mob
     */
    public void showHP(int templateID) {
        chr.getField().getMobs().stream()
                .filter(m -> m.getTemplateId() == templateID)
                .findFirst()
                .ifPresent(mob -> chr.getField().broadcastPacket(CField.fieldEffect(new MobHPTagFieldEffect(mob))));
    }

    /**
     * Shows the hp of the first alive mob.
     */
    public void showHP() {
        chr.getField().getMobs().stream()
                .filter(m -> m.getHp() > 0)
                .findFirst()
                .ifPresent(mob -> chr.getField().broadcastPacket(CField.fieldEffect(new MobHPTagFieldEffect(mob))));
    }

    /**
     * Opens a shop given an id
     * @param shopID the shop id
     */
    public void openShop(int shopID) {
        NpcShopDlg nsd = NpcData.getShopById(shopID);
        if(nsd != null) {
            chr.setShop(nsd);
            chr.write(ShopDlg.openShop(0, nsd));
        } else {
            chat(String.format("Could not find shop with id %d.", shopID));
            log.error(String.format("Could not find shop with id %d.", shopID));
        }
    }

    /**
     * Removes a single item from the Char's inventory.
     * @param itemID the item's itemid
     */
    public void consumeItem(int itemID) {
        chr.consumeItem(itemID, 1);
    }

    /**
     * Removes a <code>quantity</code> amount of items from the Char's inventory.
     * @param itemID the item's itemid
     * @param amount the amount of items to remove
     */
    public void consumeItem(int itemID, int amount) {
        chr.consumeItem(itemID, amount);
    }

    /**
     * Adds a damage skin to the Char's Account.
     * @param itemID the damage skin's itemID
     * @return Whether or not the damage skin was added
     */
    public boolean addDamageSkin(int itemID) {
        Account acc = chr.getAccount();
        DamageSkinType error = null;
        if(acc.getDamageSkins().size() >= GameConstants.DAMAGE_SKIN_MAX_SIZE) {
            error = DamageSkinType.DamageSkinSave_Fail_SlotCount;
        } else if(acc.getDamageSkinByItemID(itemID) != null) {
//            error = DamageSkinType.DamageSkinSave_Fail_AlreadyExist;
        }
        if(error != null) {
            chr.write(UserLocal.damageSkinSaveResult(DamageSkinType.DamageSkinSaveReq_Reg, error, null));
        } else {
            QuestManager qm = chr.getQuestManager();
            Quest q = qm.getQuests().getOrDefault(7291, null);
            if(q == null) {
                q = new Quest(7291, QuestStatus.STARTED);
                qm.addQuest(q);
            }
            DamageSkinSaveData dssd = DamageSkinSaveData.getByItemID(itemID);
            q.setQrValue(String.valueOf(dssd.getDamageSkinID()));
            acc.addDamageSkin(dssd);
            chr.setDamageSkin(dssd);
            chr.write(UserLocal.damageSkinSaveResult(DamageSkinType.DamageSkinSaveReq_Reg,
                    DamageSkinType.DamageSkinSave_Success, chr));
//            chr.write(User.setDamageSkin(chr));
            chr.write(WvsContext.questRecordMessage(q));
        }
        return error == null;
    }

    /**
     * Opens a trunk (storage) with a given npc.
     * @param npcTemplateID the npc's template id
     */
    public void openTrunk(int npcTemplateID) {
        chr.write(CField.trunkDlg(new TrunkOpen(npcTemplateID, chr.getAccount().getTrunk())));
    }

    /**
     * Gets the return field of the Char, for FM/Ardentmill.
     * @return the return field of the Char, for FM/Ardentmill
     */
    public int getReturnField() {
        return returnField;
    }

    /**
     * Sets the return field of the Char, for FM/Ardentmill.
     * @param returnField the return fieldID of the Char, for FM/Ardentmill
     */
    public void setReturnField(int returnField) {
        this.returnField = returnField;
    }

    /**
     * Sets the return field of this Char to the Char's current field.
     */
    public void setReturnField() {
        setReturnField(chr.getFieldID());
    }

    /**
     * Checks whether there are any mobs present in the Char's current field.
     * @return whether there are any mobs present in the Char's current field
     */
    public boolean mobsPresentInField() {
        return mobsPresentInField(chr.getFieldID());
    }

    /**
     * Checks whether there are any mobs present in a given field.
     * @param fieldid the field's id
     * @return whether there are any mobs present in a given field
     */
    public boolean mobsPresentInField(int fieldid) {
        Field field = chr.getClient().getChannelInstance().getField(fieldid);
        return field.getMobs().size() > 0;
    }

    /**
     * Returns the number of mobs in the Char's current field.
     * @return the number of mobs in the Char's current field.
     */
    public int numberMobsInField() {
        return numberMobsInField(chr.getFieldID());
    }

    /**
     * Returns the number of mobs in a given field.
     * @param fieldid the field's id
     * @return the number of mobs in a given field.
     */
    public int numberMobsInField(int fieldid) {
        Field field = FieldData.getFieldById(fieldid);
        return field.getMobs().size();
    }

    /**
     * Removes the current active reactor from the map.
     */
    public void removeReactor() {
        Field field = chr.getField();
        Reactor reactor = field.getReactors().stream()
                .filter(r -> r.getObjectId() == getObjectIDByScriptType(ScriptType.REACTOR))
                .findAny().orElse(null);
        if(reactor != null) {
            field.removeReactor(reactor);
            field.broadcastPacket(ReactorPool.reactorLeaveField(reactor));
        }
    }
}
