package net.swordie.ms.scripts;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;
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
import net.swordie.ms.client.guild.result.GuildMsg;
import net.swordie.ms.client.guild.result.GuildType;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.client.trunk.TrunkOpen;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.DeathType;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Reactor;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.life.npc.NpcMessageType;
import net.swordie.ms.life.npc.NpcScriptInfo;
import net.swordie.ms.loaders.FieldData;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.NpcData;
import net.swordie.ms.loaders.QuestData;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.FieldInstanceType;
import net.swordie.ms.world.field.Portal;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;
import net.swordie.ms.world.shop.NpcShopDlg;
import org.apache.log4j.LogManager;

import javax.script.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.RideVehicle;
import static net.swordie.ms.enums.ChatMsgColour.*;
import static net.swordie.ms.life.npc.NpcMessageType.*;

/**
 * Created on 2/19/2018.
 *
 * @see ScriptManager
 */
public class ScriptManagerImpl implements ScriptManager, Observer {

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
		// TODO listening for updates if needed
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
	public void sendAskAvatar(String text, boolean angelicBuster, boolean zeroBeta) {
		getNpcScriptInfo().setAngelicBuster(angelicBuster);
		getNpcScriptInfo().setZeroBeta(zeroBeta);
		sendGeneralSay(text, AskAvatar);
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

	@Override
	public void warpInstanceIn(int id) {
		warpInstance(id, true);
	}

	@Override
	public void warpInstanceOut(int id) {
		warpInstance(id, false);
	}

	public void warpInstance(int id, boolean in) {
		chr.setFieldInstanceType(in ? FieldInstanceType.SOLO : FieldInstanceType.CHANNEL);
		Field field = chr.getOrCreateFieldByCurrentInstanceType(id);
		chr.warp(field);
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

	public void fieldWeatherNotice(String text, WeatherEffNoticeType type) {
		fieldWeatherNotice(text, type, chr.getFieldID());
	}

	public void fieldWeatherNotice(String text, WeatherEffNoticeType type, int fieldId) {
		Field field = FieldData.getFieldById(fieldId);
		for (Char chr : field.getChars()) {
			chr.write(WvsContext.weatherEffectNotice(type, text, 7000)); // 7 seconds
		}
	}

	public void fieldGetEffect(String dir) {
		fieldGetEffect(dir, 0);
	}

	public void fieldGetEffect(String dir, int delay) {
		Field field = FieldData.getFieldById(chr.getFieldID());
		for (Char chr : field.getChars()) {
			chr.write(User.effect(Effect.effectFromWZ(dir, false, delay, 4, 0)));
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
		chr.write(WvsContext.guildResult(new GuildMsg(GuildType.Req_InputGuildName)));
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

	public void weatherNotice(String text, WeatherEffNoticeType type) {
		weatherNotice(text, type, 7000); // 7 seconds
	}

	@Override
	public void weatherNotice(String text, WeatherEffNoticeType type, int duration) {
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
		chr.getQuestManager().completeQuest(id);
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

	public void getEffect(String dir) {
		getEffect(dir, 0);
	}

	@Override
	public void getEffect(String dir, int delay) {
		chr.write(User.effect(Effect.effectFromWZ(dir, false, delay, 4, 0)));
	}
}
