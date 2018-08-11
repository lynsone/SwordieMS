package net.swordie.ms.scripts;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.MonsterPark;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.damage.DamageSkinSaveData;
import net.swordie.ms.client.character.damage.DamageSkinType;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.items.ItemBuffs;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.guild.result.GuildResult;
import net.swordie.ms.client.guild.result.GuildType;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.client.trunk.TrunkOpen;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.DeathType;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Reactor;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.life.npc.NpcMessageType;
import net.swordie.ms.life.npc.NpcScriptInfo;
import net.swordie.ms.loaders.*;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.FieldInstanceType;
import net.swordie.ms.world.field.Foothold;
import net.swordie.ms.world.field.Portal;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;
import net.swordie.ms.world.field.obtacleatom.ObtacleAtomInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleInRowInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleRadianInfo;
import net.swordie.ms.world.shop.NpcShopDlg;
import org.apache.log4j.LogManager;

import javax.script.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.RideVehicle;
import static net.swordie.ms.enums.ChatMsgColour.*;
import static net.swordie.ms.life.npc.NpcMessageType.*;

/**
 * Created on 2/19/2018.
 *
 * @see ScriptManager
 */
public class ScriptManagerImpl implements ScriptManager {

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
	private ScriptType lastActiveScriptType;
	private Map<ScriptType, Set<ScheduledFuture>> eventsByScriptType = new HashMap<>();
	private Set<ScheduledFuture> scheduledFutureSet;

	private ScriptManagerImpl(Char chr, Field field) {
		this.chr = chr;
		this.field = field;
		this.npcScriptInfo = new NpcScriptInfo();
		this.scripts = new HashMap<>();
		this.isField = chr == null;
		this.lastActiveScriptType = ScriptType.NONE;
	}

	public ScriptManagerImpl(Char chr) {
		this(chr, chr.getField());
	}

	public ScriptManagerImpl(Field field) {
		this(null, field);
	}

	public ScriptEngine getScriptEngineByType(ScriptType scriptType) {
		return getScriptInfoByType(scriptType).getScriptEngine();
	}

	public ScriptInfo getScriptInfoByType(ScriptType scriptType) {
		return scripts.getOrDefault(scriptType, null);
	}

	@Override
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
		setLastActiveScriptType(scriptType);
		if (scriptType == ScriptType.NONE) {
			return;
		}
		if (!isField()) {
			chr.chatMessage(YELLOW, String.format("Starting script %s, scriptType %s.", scriptName, scriptType));
			log.debug(String.format("Starting script %s, scriptType %s.", scriptName, scriptType));
		}
		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(SCRIPT_ENGINE_NAME);
		scriptEngine.put("sm", this);
		scriptEngine.put("parentID", parentID);
		scriptEngine.put("scriptType", scriptType);
		scriptEngine.put("objectID", objID);
		scriptEngine.put("chr", chr);
		if (scriptType == ScriptType.QUEST) {
			scriptEngine.put("startQuest",
					scriptName.charAt(scriptName.length() - 1) == QUEST_START_SCRIPT_END_TAG.charAt(0)); // biggest hack eu
		}
		ScriptInfo scriptInfo = new ScriptInfo(scriptType, scriptEngine, parentID, scriptName);
		scriptInfo.setObjectID(objID);
		getScripts().put(scriptType, scriptInfo);
		Invocable inv = getInvocableFromScriptNameAndType(scriptName, scriptType);
		getScripts().get(scriptType).setInvocable(inv);
		EventManager.addEvent(() -> getInvocableByType(scriptType).invokeFunction(initFuncName), 0);
	}

	public void notifyMobDeath(Mob mob) {
		if (isActive(ScriptType.FIELD)) {
			try {
				getInvocableByType(ScriptType.FIELD).invokeFunction("onMobDeath", mob);
			} catch (ScriptException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// Intended: no method is no problem
			}
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
		setSpeakerID(0);
		if (getScriptInfoByType(scriptType) != null) {
			getScriptInfoByType(scriptType).reset();
		}
		if (getLastActiveScriptType() == scriptType) {
			setLastActiveScriptType(ScriptType.NONE);
		}
		WvsContext.dispose(chr);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Mob) {
			notifyMobDeath((Mob) o);
		}
	}

	public void handleAction(byte lastType, byte response, int answer) {
		handleAction(getLastActiveScriptType(), lastType, response, answer);
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

	public ScriptType getLastActiveScriptType() {
		return lastActiveScriptType;
	}

	public void setLastActiveScriptType(ScriptType lastActiveScriptType) {
		this.lastActiveScriptType = lastActiveScriptType;
	}

	// Start of the sends/asks -----------------------------------------------------------------------------------------

	@Override
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

	@Override
	public void sendNext(String text) {
		sendGeneralSay(text, SayNext);
	}

	@Override
	public void sendPrev(String text) {
		sendGeneralSay(text, SayPrev);
	}

	@Override
	public void sendSayOkay(String text) {
		sendGeneralSay(text, SayOk);
	}

	@Override
	public void sendSayImage(String image) {
		sendSayImage(new String[]{image});
	}

	@Override
	public void sendSayImage(String[] images) {
		getNpcScriptInfo().setImages(images);
		getNpcScriptInfo().setMessageType(SayImage);
		chr.write(ScriptMan.scriptMessage(this, SayImage));
	}

	@Override
	public void sendAskYesNo(String text) {
		sendGeneralSay(text, AskYesNo);
	}

	@Override
	public void sendAskText(String text, String defaultText, short minLength, short maxLength) {
		getNpcScriptInfo().setMin(minLength);
		getNpcScriptInfo().setMax(maxLength);
		getNpcScriptInfo().setDefaultText(defaultText);
		sendGeneralSay(text, AskText);
	}

	@Override
	public void sendAskNumber(String text, int defaultNum, int min, int max) {
		getNpcScriptInfo().setDefaultNumber(defaultNum);
		getNpcScriptInfo().setMin(min);
		getNpcScriptInfo().setMax(max);
		sendGeneralSay(text, AskNumber);
	}

	@Override
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

	@Override
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

	@Override
	public void sendICQuiz(byte type, String text, String hintText, int time) {
		getNpcScriptInfo().setType(type);
		getNpcScriptInfo().setHintText(hintText);
		getNpcScriptInfo().setTime(time);
		sendGeneralSay(text, ICQuiz);
	}

	@Override
	public void sendAskAvatar(String text, boolean angelicBuster, boolean zeroBeta, int... options) {
		getNpcScriptInfo().setAngelicBuster(angelicBuster);
		getNpcScriptInfo().setZeroBeta(zeroBeta);
		getNpcScriptInfo().setOptions(options);
		sendGeneralSay(text, AskAvatar);
	}

	public void sendAskSlideMenu(int dlgType) {
		getNpcScriptInfo().setDlgType(dlgType);
		chr.write(ScriptMan.scriptMessage(this, AskSlideMenu));
	}

	// Start of param methods ------------------------------------------------------------------------------------------

	public void setPlayerAsSpeaker() {
		getNpcScriptInfo().addParam(NpcScriptInfo.Param.PlayerAsSpeaker);
	}

	public void resetParam() {
		getNpcScriptInfo().resetParam();
	}

	// Start helper methods for scripts --------------------------------------------------------------------------------

	@Override
	public void dispose() {
		stop(ScriptType.NPC);
		stop(ScriptType.PORTAL);
		stop(ScriptType.ITEM);
		stop(ScriptType.QUEST);
		stop(ScriptType.REACTOR);
		setLastActiveScriptType(ScriptType.NONE);
	}

	public void dispose(ScriptType scriptType) {
		stop(scriptType);
	}

	public Position getPosition(int objId) {
		return chr.getField().getLifeByObjectID(objId).getPosition();
	}



	// Character Stat-related methods ----------------------------------------------------------------------------------

	@Override
	public void setJob(short jobID) {
		chr.setJob(jobID);
		Map<Stat, Object> stats = new HashMap<>();
		stats.put(Stat.subJob, jobID);
		chr.getClient().write(WvsContext.statChanged(stats, true, (byte) -1,
				(byte) 0, (byte) 0, (byte) 0, false, 0, 0));
	}

	@Override
	public void addSP(int amount) {
		byte jobLevel = (byte) JobConstants.getJobLevel(chr.getJob());
		int currentSP = chr.getAvatarData().getCharacterStat().getExtendSP().getSpByJobLevel(jobLevel);
		setSP(currentSP + amount);
	}

	@Override
	public void setSP(int amount) {
		chr.setSpToCurrentJob(amount);
		Map<Stat, Object> stats = new HashMap<>();
		stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
		chr.getClient().write(WvsContext.statChanged(stats, true, (byte) -1,
				(byte) 0, (byte) 0, (byte) 0, false, 0, 0));
	}

	@Override
	public void addAP(int amount) {
		int currentAP = chr.getAvatarData().getCharacterStat().getAp();
		setAP(currentAP + amount);
	}

	@Override
	public void setAP(int amount) {
		chr.setStat(Stat.ap, (short) amount);
		Map<Stat, Object> stats = new HashMap<>();
		stats.put(Stat.ap, (short) amount);
		chr.getClient().write(WvsContext.statChanged(stats));
	}

	@Override
	public void jobAdvance(short jobID) {
		setJob(jobID);
		addAP(5); //Standard added AP upon Job Advancing
		addSP(3); //Standard added SP upon Job Advancing
	}

	@Override
	public void giveExp(long expGiven) {
		chr.addExp(expGiven);
	}

	@Override
	public void giveExpNoMsg(long expGiven) {
		chr.addExpNoMsg(expGiven);
	}

	@Override
	public void changeCharacterLook(int look) {
		AvatarLook al = chr.getAvatarData().getAvatarLook();
		if (look < 100) { // skin
			al.setSkin(look);
			chr.setStatAndSendPacket(Stat.skin, look);
		} else if (look < 30000) {
			al.setFace(look);
			chr.setStatAndSendPacket(Stat.face, look);
		} else if (look < 1000000) {
			al.setHair(look);
			chr.setStatAndSendPacket(Stat.hair, look);
		} else {
			log.error(String.format("Tried changing a look with invalid id (%d)", look));
		}
	}

	public void giveSkill(int skillId) {
		giveSkill(skillId, 1);
	}

	@Override
	public void giveSkill(int skillId, int slv) {
		chr.addSkill(skillId, slv, slv);
	}

	public int getSkillByItem() {
		return getSkillByItem(getParentID());
	}

	public int getSkillByItem(int itemId) {
		ItemInfo itemInfo = ItemData.getItemInfoByID(itemId);
		return itemInfo.getSkillId();
	}

	public boolean hasSkill(int skillId) {
		return chr.hasSkill(skillId);
	}


	// Field-related methods -------------------------------------------------------------------------------------------

	@Override
	public void warp(int mid, int pid) {
		Field field = chr.getOrCreateFieldByCurrentInstanceType(mid);
		Portal portal = chr.getField().getPortalByID(pid);
		chr.warp(field, portal);
	}

	@Override
	public void warp(int id) {
		Field field = chr.getClient().getChannelInstance().getField(id);
		chr.warp(field);
	}

	@Override
	public void teleportToPortal(int portalId) {
		Portal portal = chr.getField().getPortalByID(portalId);
		if (portal != null) {
			Position position = new Position(portal.getX(), portal.getY());
			chr.write(CField.teleport(position, chr));
		}
	}

	@Override
	public int getFieldID() {
		return chr.getField().getId();
	}

	@Override
	public void warpPartyIn(int id) {
		warpParty(id, true);
	}

	@Override
	public void warpPartyOut(int id) {
		warpParty(id, false);
	}

	public void warpParty(int id, boolean in) {
		if (chr.getParty() == null) {
			chr.setFieldInstanceType(in ? FieldInstanceType.PARTY : FieldInstanceType.CHANNEL);
			Field field = chr.getOrCreateFieldByCurrentInstanceType(id);
			chr.warp(field);
		} else {
			if (!in) {
				clearPartyInfo(GameConstants.NO_MAP_ID);
			}
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

	public void clearPartyInfo() {
		clearPartyInfo(0);
	}

	@Override
	public void clearPartyInfo(int warpToID) {
		if (chr.getParty() != null) {
			for (PartyMember pm : chr.getParty().getOnlineMembers()) {
				pm.getChr().setDeathCount(-1);
			}
			chr.getParty().clearFieldInstances(warpToID);
		}
	}

	public void warpInstanceIn(int id, int portal) {
		warpInstance(id, true, portal);
	}

	public void warpInstanceOut(int id, int portal) {
		warpInstance(id, false, portal);
	}

	@Override
	public void warpInstanceIn(int id) {
		warpInstance(id, true, 0);
	}

	@Override
	public void warpInstanceOut(int id) {
		warpInstance(id, false, 0);
	}

	public void warpInstance(int id, boolean in, int portalID) {
		chr.setFieldInstanceType(in ? FieldInstanceType.SOLO : FieldInstanceType.CHANNEL);
		if (!in) {
			chr.getFields().clear();
		}
		Field field = chr.getOrCreateFieldByCurrentInstanceType(id);
		Portal portal = field.getPortalByID(portalID);
		chr.warp(field, portal);
	}

	@Override
	public int getReturnField() {
		return returnField;
	}

	@Override
	public void setReturnField(int returnField) {
		this.returnField = returnField;
	}

	@Override
	public void setReturnField() {
		setReturnField(chr.getFieldID());
	}

	@Override
	public boolean mobsPresentInField() {
		return mobsPresentInField(chr.getFieldID());
	}

	@Override
	public boolean mobsPresentInField(int fieldid) {
		Field field = chr.getOrCreateFieldByCurrentInstanceType(fieldid);
		return field.getMobs().size() > 0;
	}

	@Override
	public int numberMobsInField() {
		return numberMobsInField(chr.getFieldID());
	}

	@Override
	public int numberMobsInField(int fieldid) {
		Field field = FieldData.getFieldById(fieldid);
		return field.getMobs().size();
	}

	public void showWeatherNoticeToField(String text, WeatherEffNoticeType type) {
		showWeatherNoticeToField(text, type, 7000); // 7 seconds
	}

	public void showWeatherNoticeToField(String text, WeatherEffNoticeType type, int duration) {
		Field field = chr.getField();
		field.broadcastPacket(WvsContext.weatherEffectNotice(type, text, duration));
	}

	public void showEffectToField(String dir) {
		showEffectToField(dir, 0);
	}

	public void showEffectToField(String dir, int delay) {
		Field field = chr.getField();
		field.broadcastPacket(User.effect(Effect.effectFromWZ(dir, false, delay, 4, 0)));
	}

	public void showFieldEffect(String dir) {
		showFieldEffect(dir, 0);
	}

	@Override
	public void showFieldEffect(String dir, int delay) {
		Field field = chr.getField();
		field.broadcastPacket(CField.fieldEffect(FieldEffect.getFieldEffectFromWz(dir, delay)));
	}

	@Override
	public void dropItem(int itemId, int x, int y) {
		Field field = chr.getField();
		Drop drop = new Drop(field.getNewObjectID());
		drop.setItem(ItemData.getItemDeepCopy(itemId));
		Position position = new Position(x, y);
		drop.setPosition(position);
		field.drop(drop, position);
	}

	@Override
	public void teleportInField(int portalId) {
		Field field = chr.getField();
		Portal portal = field.getPortalByID(portalId);
		if (portal != null) {
			chr.write(CField.teleport(new Position(portal.getX(), portal.getY()), chr));
		}
	}



	// Life-related methods --------------------------------------------------------------------------------------------


	// NPC methods
	@Override
	public void spawnNpc(int npcId, int x, int y) {
		Npc npc = NpcData.getNpcDeepCopyById(npcId);
		chr.getField().spawnLife(npc, chr);
	}

	@Override
	public void removeNpc(int npcId) { //TODO Fix
		List<Life> removeLifeSet = new ArrayList<>();
		List<Life> lifes = chr.getField().getLifes();
		if(lifes.size() > 0) {
			for (Life life : lifes) {
				if (life instanceof Npc) {
					if (life.getTemplateId() != npcId) {
						continue;
					}
					removeLifeSet.add(life);
				}
			}
		}
		//TODO
		if(removeLifeSet.size()>1) {
			chr.getField().removeLife(removeLifeSet.get(0).getObjectId());
		}
	}

	@Override
	public void openNpc(int npcId) {
		Npc npc = NpcData.getNpcDeepCopyById(npcId);
		String script;
		if(npc.getScripts().size() > 0) {
			script = npc.getScripts().get(0);
		} else {
			script = String.valueOf(npc.getTemplateId());
		}
		chr.getScriptManager().startScript(npc.getTemplateId(), npcId, script, ScriptType.NPC);
	}

	@Override
	public void openShop(int shopID) {
		NpcShopDlg nsd = NpcData.getShopById(shopID);
		if (nsd != null) {
			chr.setShop(nsd);
			chr.write(ShopDlg.openShop(0, nsd));
		} else {
			chat(String.format("Could not find shop with id %d.", shopID));
			log.error(String.format("Could not find shop with id %d.", shopID));
		}
	}

	@Override
	public void openTrunk(int npcTemplateID) {
		chr.write(CField.trunkDlg(new TrunkOpen(npcTemplateID, chr.getAccount().getTrunk())));
	}

	@Override
	public void setSpeakerID(int templateID) {
		getNpcScriptInfo().resetParam();
		getNpcScriptInfo().setOverrideSpeakerTemplateID(templateID);
	}



	// Mob methods
	@Override
	public void spawnMob(int id) {
		spawnMob(id, 0, 0, false);
	}

	@Override
	public void spawnMob(int id, boolean respawnable) {
		spawnMob(id, 0, 0, respawnable);
	}

	@Override
	public void spawnMobOnChar(int id) {
		spawnMob(id, chr.getPosition().getX(), chr.getPosition().getY(), false);
	}

	@Override
	public void spawnMobOnChar(int id, boolean respawnable) {
		spawnMob(id, chr.getPosition().getX(), chr.getPosition().getY(), respawnable);
	}

	@Override
	public void spawnMob(int id, int x, int y, boolean respawnable) {
		spawnMob(id, x, y, respawnable, 0);
	}

	public void spawnMob(int id, int x, int y, boolean respawnable, long hp) {
		chr.getField().spawnMob(id, x, y, respawnable, hp);
	}

	@Override
	public void removeMobByObjId(int id) {
		chr.getField().removeLife(id);
		chr.getField().broadcastPacket(MobPool.mobLeaveField(id, DeathType.ANIMATION_DEATH));
	}

	@Override
	public void removeMobsByTemplateId(int id) {
		List<Mob> mobList = chr.getField().getMobs();
		if (mobList.size() > 0) {
			for (Mob mob : mobList) {
				if (mob.getTemplateId() != id) {
					continue;
				}
				removeMobByObjId(mob.getObjectId());
			}
		}
	}

	@Override
	public void removeMobsAfterTimer(int templateID, int seconds) {
		EventManager.addEvent(() -> removeMobsByTemplateId(templateID), seconds, TimeUnit.SECONDS);
	}

	@Override
	public void showHP(int templateID) {
		chr.getField().getMobs().stream()
				.filter(m -> m.getTemplateId() == templateID)
				.findFirst()
				.ifPresent(mob -> chr.getField().broadcastPacket(CField.fieldEffect(FieldEffect.mobHPTagFieldEffect(mob))));
	}

	@Override
	public void showHP() {
		chr.getField().getMobs().stream()
				.filter(m -> m.getHp() > 0)
				.findFirst()
				.ifPresent(mob -> chr.getField().broadcastPacket(CField.fieldEffect(FieldEffect.mobHPTagFieldEffect(mob))));
	}



	// Reactor methods
	@Override
	public void removeReactor() {
		Field field = chr.getField();
		Reactor reactor = field.getReactors().stream()
				.filter(r -> r.getObjectId() == getObjectIDByScriptType(ScriptType.REACTOR))
				.findAny().orElse(null);
		if (reactor != null) {
			field.removeReactor(reactor);
			field.broadcastPacket(ReactorPool.reactorLeaveField(reactor));
		}
	}

	@Override
	public void spawnReactor(int reactorId, int x, int y) {
		Field field = chr.getField();
		Reactor reactor = ReactorData.getReactorByID(reactorId);
		Position position = new Position(x, y);
		reactor.setPosition(position);
		field.addReactor(reactor);
	}

	@Override
	public boolean hasReactors() {
		Field field = chr.getField();
		return field.getReactors().size() > 0;
	}

	@Override
	public int getReactorQuantity() {
		Field field = chr.getField();
		return field.getReactors().size();
	}



	// Party-related methods -------------------------------------------------------------------------------------------

	@Override
	public Party getParty() {
		return chr.getParty();
	}

	@Override
	public int getPartySize() {return getParty().getMembers().size();}

	@Override
	public void setPartyField() {
		chr.setFieldInstanceType(FieldInstanceType.PARTY);
	}

	@Override
	public boolean isPartyLeader() {
		return chr.getParty() != null && chr.getParty().getPartyLeaderID() == chr.getId();
	}

	@Override
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



	// Guild-related methods -------------------------------------------------------------------------------------------

	@Override
	public void showGuildCreateWindow() {
		chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Req_InputGuildName)));
	}



	// Chat-related methods --------------------------------------------------------------------------------------------

	@Override
	public void chat(String text) {
		chatRed(text);
	}

	@Override
	public void chatRed(String text) {
		chr.chatMessage(GAME_MESSAGE, text);
	}

	@Override
	public void chatBlue(String text) {
		chr.chatMessage(GAME_NOTICE, text);
	}

	@Override
	public void chatScript(String text) {chr.chatScriptMessage(text);}

	public void showWeatherNotice(String text, WeatherEffNoticeType type) {
		showWeatherNotice(text, type, 7000); // 7 seconds
	}

	@Override
	public void showWeatherNotice(String text, WeatherEffNoticeType type, int duration) {
		chr.write(WvsContext.weatherEffectNotice(type, text, duration));
	}


	// Inventory-related methods ---------------------------------------------------------------------------------------

	@Override
	public void giveMesos(long mesos) {
		chr.addMoney(mesos);
	}

	@Override
	public void deductMesos(long mesos) {
		chr.deductMoney(mesos);
	}

	@Override
	public long getMesos() {
		return chr.getMoney();
	}

	@Override
	public void giveItem(int id) {
		giveItem(id, 1);
	}

	@Override
	public void giveItem(int id, int quantity) {
		chr.addItemToInventory(id, quantity);
	}

	@Override
	public boolean hasItem(int id) {
		return hasItem(id, 1);
	}

	@Override
	public boolean hasItem(int id, int quantity) {
		return getQuantityOfItem(id) >= quantity;
	}

	public void consumeItem() {
		consumeItem(getParentID());
	}

	@Override
	public void consumeItem(int itemID) {
		chr.consumeItem(itemID, 1);
	}

	@Override
	public void consumeItem(int itemID, int amount) {
		chr.consumeItem(itemID, amount);
	}

	@Override
	public void useItem(int id) {
		ItemBuffs.giveItemBuffsFromItemID(chr, chr.getTemporaryStatManager(), id);
	}

	@Override
	public int getQuantityOfItem(int id) {
		if (ItemConstants.isEquip(id)) {
			Item equip = chr.getInventoryByType(InvType.EQUIP).getItemByItemID(id);
			if (equip == null) {
				return 0;
			}
			return equip.getQuantity();
		} else {
			Item item2 = ItemData.getItemDeepCopy(id);
			InvType invType = item2.getInvType();
			Item item = chr.getInventoryByType(invType).getItemByItemID(id);
			if (item == null) {
				return 0;
			}
			return item.getQuantity();
		}
	}

	@Override
	public boolean canHold(int id) {
		return chr.canHold(id);
	}

	@Override
	public int getEmptyInventorySlots(InvType invType) {
		return chr.getInventoryByType(invType).getEmptySlots();
	}



	// Quest-related methods -------------------------------------------------------------------------------------------

	@Override
	public void completeQuest(int id) {
		if (hasQuest(id) && isComplete(id)) {
			chr.getQuestManager().completeQuest(id);
		}
	}

	@Override
	public void completeQuestNoRewards(int id) {
		QuestManager qm = chr.getQuestManager();
		Quest quest = qm.getQuests().get(id);
		if (quest == null) {
			quest = QuestData.createQuestFromId(id);
		}
		quest.setCompletedTime(FileTime.currentTime());
		quest.setStatus(QuestStatus.COMPLETE);
		qm.addQuest(quest);
		chr.write(WvsContext.questRecordMessage(quest));
	}

	@Override
	public void startQuestNoCheck(int id) {
		QuestManager qm = chr.getQuestManager();
		qm.addQuest(QuestData.createQuestFromId(id));
	}

	@Override
	public void startQuest(int id) {
		QuestManager qm = chr.getQuestManager();
		if (qm.canStartQuest(id)) {
			qm.addQuest(QuestData.createQuestFromId(id));
		}
	}

	@Override
	public boolean hasQuest(int id) {
		return chr.getQuestManager().hasQuestInProgress(id);
	}

	@Override
	public boolean hasQuestCompleted(int id) {
		return chr.getQuestManager().hasQuestCompleted(id);
	}

	public void createQuestWithQRValue(int questId, String qrValue) {
		createQuestWithQRValue(chr, questId, qrValue);
	}

	public void createQuestWithQRValue(Char character, int questId, String qrValue) {
		QuestManager qm = character.getQuestManager();
		Quest quest = qm.getQuests().get(questId);
		if (quest == null) {
			quest = new Quest(questId, QuestStatus.STARTED);
			quest.setQrValue(qrValue);
			qm.addCustomQuest(quest);
		}
		quest.setQrValue(qrValue);
	}

	public void deleteQuest(int questId) {
		deleteQuest(chr, questId);
	}

	public void deleteQuest(Char character, int questId) {
		QuestManager qm = chr.getQuestManager();
		Quest quest = qm.getQuests().get(questId);
		if(quest == null) {
			return;
		}
		qm.removeQuest(quest.getQRKey());
	}

	public String getQRValue(int questId) {
		return getQRValue(chr, questId);
	}

	public String getQRValue(Char character, int questId) {
		Quest quest = chr.getQuestManager().getQuests().get(questId);
		if (quest == null) {
			return "Quest is Null";
		}
		return quest.getQRValue();
	}

	public void setQRValue(int questId, String qrValue) {
		setQRValue(chr, questId, qrValue);
	}

	public void setQRValue(Char character, int questId, String qrValue) {
		Quest quest = chr.getQuestManager().getQuests().get(questId);
		quest.setQrValue(qrValue);
	}

	public boolean isComplete(int questID) {
		return chr.getQuestManager().isComplete(questID);
	}
	

	// Party Quest-related methods -------------------------------------------------------------------------------------

	public void incrementMonsterParkCount() {
		chr.setMonsterParkCount( (byte) (chr.getMonsterParkCount() + 1));
	}

	public byte getMonsterParkCount() {
		return chr.getMonsterParkCount();
	}

	public String getDay() {
		return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
	}

	public int getMPExpByMobId(int templateId) {
		return MonsterPark.getExpByMobId(templateId);
	}

	public int getMPReward() {
		return MonsterPark.getRewardByDay();
	}

	public long getPQExp() {
		return getPQExp(chr);
	}

	public long getPQExp(Char chr) {
		return GameConstants.PARTY_QUEST_EXP_FORMULA(chr);
	}


	// Boss-related methods --------------------------------------------------------------------------------------------

	@Override
	public void setDeathCount(int deathCount) {
		chr.setDeathCount(deathCount);
		chr.write(UserLocal.deathCountInfo(deathCount));
	}

	@Override
	public void setPartyDeathCount(int deathCount) {
		if (chr.getParty() != null) {
			for (PartyMember pm : chr.getParty().getOnlineMembers()) {
				pm.getChr().setDeathCount(deathCount);
			}
		}
	}

	public void createObstacleAtom(ObtacleAtomEnum oae, int key, int damage, int velocity, int amount, int proc) {
		createObstacleAtom(oae, key, damage, velocity, 0, amount, proc);
	}

	@Override
	public void createObstacleAtom(ObtacleAtomEnum oae, int key, int damage, int velocity, int angle, int amount, int proc) {
		Field field = chr.getField();
		int xLeft = field.getVrLeft();
		int yTop = field.getVrTop();

		ObtacleInRowInfo obtacleInRowInfo = new ObtacleInRowInfo(4, false, 5000, 0, 0, 0);
		ObtacleRadianInfo obtacleRadianInfo = new ObtacleRadianInfo(4, 0, 0, 0, 0);
		Set<ObtacleAtomInfo> obtacleAtomInfosSet = new HashSet<>();

		for(int i = 0; i < amount; i++) {
			if(Util.succeedProp(proc)) {
				int randomX = new Random().nextInt(field.getWidth()) + xLeft;
				Position position = new Position(randomX, yTop);
				Foothold foothold = field.findFootHoldBelow(position);
				if (foothold != null) {
					int footholdY = foothold.getYFromX(position.getX());
					int height = position.getY() - footholdY;
					height = height < 0 ? -height : height;

					obtacleAtomInfosSet.add(new ObtacleAtomInfo(oae.getType(), key, position, new Position(), oae.getHitBox(),
							damage, 0, 0, height, 0, velocity, height, angle));
				}
			}
		}

		field.broadcastPacket(CField.createObtacle(ObtacleAtomCreateType.NORMAL, obtacleInRowInfo, obtacleRadianInfo, obtacleAtomInfosSet));
	}

	public void stopEventsByScriptType(ScriptType scriptType) {
		Set<ScheduledFuture> events = eventsByScriptType.get(scriptType);
		if (events != null) {
			events.forEach(st -> st.cancel(true));
			events.clear();
		}
	}



	// Character Temporary Stat-related methods ------------------------------------------------------------------------

	@Override
	public void giveCTS(CharacterTemporaryStat cts, int nOption, int rOption, int time) {
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		Option o = new Option();
		o.nOption = nOption;
		o.rOption = rOption;
		o.tOption = time;
		tsm.putCharacterStatValue(cts, o);
		tsm.sendSetStatPacket();
	}

	@Override
	public void removeCTS(CharacterTemporaryStat cts) {
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		tsm.removeStat(cts, false);
	}

	@Override
	public void removeBuffBySkill(int skillId) {
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		tsm.removeStatsBySkill(skillId);
	}

	@Override
	public boolean hasCTS(CharacterTemporaryStat cts) {
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		return tsm.hasStat(cts);
	}

	@Override
	public int getnOptionByCTS(CharacterTemporaryStat cts) {
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		return hasCTS(cts) ? tsm.getOption(cts).nOption : 0;
	}

	@Override
	public void rideVehicle(int mountID) {
		TemporaryStatManager tsm = chr.getTemporaryStatManager();
		TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);

		tsb.setNOption(mountID);
		tsb.setROption(0);
		tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
		tsm.sendSetStatPacket();
	}



	// Other methods ---------------------------------------------------------------------------------------------------

	@Override
	public boolean addDamageSkin(int itemID) {
		Account acc = chr.getAccount();
		DamageSkinType error = null;
		if (acc.getDamageSkins().size() >= GameConstants.DAMAGE_SKIN_MAX_SIZE) {
			error = DamageSkinType.DamageSkinSave_Fail_SlotCount;
		} else if (acc.getDamageSkinByItemID(itemID) != null) {
//            error = DamageSkinType.DamageSkinSave_Fail_AlreadyExist;
		}
		if (error != null) {
			chr.write(UserLocal.damageSkinSaveResult(DamageSkinType.DamageSkinSaveReq_Reg, error, null));
		} else {
			QuestManager qm = chr.getQuestManager();
			Quest q = qm.getQuests().getOrDefault(7291, null);
			if (q == null) {
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

	@Override
	public void openUI(UIType uiID){
		int uiIDValue = uiID.getVal();
		chr.write(CField.openUI(uiIDValue));
	}

	@Override
	public void showClearStageExpWindow(long expGiven) {
		chr.write(CField.fieldEffect(FieldEffect.showClearStageExpWindow((int) expGiven)));
		giveExpNoMsg(expGiven);
	}

	@Override
	public int getRandomIntBelow(int upBound) {
		return new Random().nextInt(upBound);
	}

	public void showEffect(String dir) {
		showEffect(dir, 0);
	}

	@Override
	public void showEffect(String dir, int delay) {
		chr.write(User.effect(Effect.effectFromWZ(dir, false, delay, 4, 0)));
	}

	public String formatNumber(String number) {
		return Util.formatNumber(number);
	}

	private Object invoke(Object invokeOn, String methodName, Object... args) {
		List<Class<?>> classList = Arrays.stream(args).map(Object::getClass).collect(Collectors.toList());
		Class<?>[] classes = classList.stream().map(Util::convertBoxedToPrimitiveClass).toArray(Class<?>[]::new);
		Method func;
		try {
			func = getClass().getMethod(methodName, classes);
			return func.invoke(invokeOn, args);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void invokeForParty(String methodName, Object... args) {
		for (PartyMember pm : chr.getParty().getMembers()) {
			boolean fromDB = false;
			Char chr = pm.getChr();
			if (chr == null) {
				chr = Char.getFromDBById(pm.getCharID());
				fromDB = true;
			}
			invoke(chr.getScriptManager(), methodName, args);
			if (fromDB) {
				DatabaseManager.saveToDB(chr);
			}
		}
	}

	public ScheduledFuture invokeAfterDelay(long delay, String methodName, Object...args) {
		return EventManager.addEvent(() -> invoke(this, methodName, args), delay);
	}

	public ScheduledFuture invokeAtFixedRate(long initialDelay, long delayBetweenExecutions,
											 int executes, String methodName, Object...args) {
		ScheduledFuture scheduledFuture;
		if (executes == 0) {
			scheduledFuture =  EventManager.addFixedRateEvent(() -> invoke(this, methodName, args), initialDelay,
					delayBetweenExecutions);
		} else {
			scheduledFuture = EventManager.addFixedRateEvent(() -> invoke(this, methodName, args), initialDelay,
					delayBetweenExecutions, executes);
		}
		if (scheduledFutureSet == null) {
			scheduledFutureSet = new HashSet<>();
		}
		scheduledFutureSet.add(scheduledFuture);
		eventsByScriptType.put(getLastActiveScriptType(), scheduledFutureSet);
		return scheduledFuture;
	}
}
