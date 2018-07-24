package net.swordie.ms.scripts;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.enums.UIType;
import net.swordie.ms.enums.WeatherEffNoticeType;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.client.party.Party;

/**
 * Script manager {@code interface} used in all scripts.
 *
 * @author NullByte
 */
public interface ScriptManager {

	/**
	 * The character instance, can be null if none is implied.<br>
	 * Example: "sm.getChr()"
	 *
	 * @return The {@link Char} linked with the current script.
	 */
	Char getChr();

	/**
	 * The field instance, cannot be null, it's either set or as the {@link Char}'s field.<br>
	 * Example: "sm.getChar()"
	 *
	 * @return The {@link Field} linked with the current script.
	 */
	Field getField();

	/**
	 * This is the method to use to determine whether or not a script has a {@link Char}.<br>
	 * Example: "if(sm.isField()) {}"
	 *
	 * @return True if the script is field related and has no character.
	 */
	boolean isField();

	/**
	 * Sends a message box with previous / next buttons enabled.<br>
	 * Example: "sm.sendSay("Hello character!")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	void sendSay(String text);

	/**
	 * Sends a message box with the next button enabled only.<br>
	 * Example: "sm.sendNext("Click next!")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	void sendNext(String text);

	/**
	 * Sends a message box with the previous button enabled only.<br>
	 * Example: "sm.sendPrev("Click previous!")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	void sendPrev(String text);

	/**
	 * Sends a message box with the ok button enabled only.<br>
	 * Example: "sm.sendSayOkay("Click ok to continue.")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	void sendSayOkay(String text);

	/**
	 * Sends a message box with the defined image from wz.
	 * Example: unknown, ask Sjonnie for help here.
	 *
	 * @param image
	 * 		The image to display in the message box.
	 */
	void sendSayImage(String image);

	/**
	 * Sends a message box with the defined images from wz.
	 * Example: unknown, ask Sjonnie for help here.
	 *
	 * @param images
	 * 		The images to display in the message box.
	 */
	void sendSayImage(String[] images);

	/**
	 * Sends a message box with yes / no buttons.<br>
	 * Example: "sm.sendAskYesNo("Please click yes or no.")"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 */
	void sendAskYesNo(String text);

	/**
	 * Sends a message box asking a user for some text.<br>
	 * Example: "sm.sendAskText("Please input some text.", "Hello World!", 1, 20)"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 * @param defaultText
	 * 		The default text shown in the box.
	 * @param minLength
	 * 		The minimum length of the text. "Hi" would be a length of 2. "Hello" would be 5.
	 * @param maxLength
	 * 		The maximum length of the text. "Hi" would be a length of 2.
	 */
	void sendAskText(String text, String defaultText, short minLength, short maxLength);

	/**
	 * Sends a message box asking a user for a number.
	 * Example: "sm.sendAskNumber("Select a number between 1 and 10", 1, 1, 10)"
	 *
	 * @param text
	 * 		The text to display inside of the message box.
	 * @param defaultNum
	 * 		The default number shown.
	 * @param min
	 * 		The minimum number.
	 * @param max
	 * 		The maximum number.
	 */
	void sendAskNumber(String text, int defaultNum, int min, int max);

	/**
	 * Sends a chat window for a quiz.
	 * Example: "sm.sendInitialQuiz(0, "Quest Title", "What is 1 + 1", 1, 1, 30)"
	 *
	 * @param type
	 * 		The type (0 for question, 1 for nothing)
	 * @param title
	 * 		The title of the quiz.
	 * @param problem
	 * 		The question of the quiz.
	 * @param hint
	 * 		The hint of the quiz.
	 * @param min
	 * 		The minimum length of the answer.
	 * @param max
	 * 		The maximum length of the answer.
	 * @param time
	 * 		The time allowed to answer the question, in seconds.
	 */
	void sendInitialQuiz(byte type, String title, String problem, String hint, int min, int max, int time);

	/**
	 * Sends a chat window for an initial speed quiz.
	 * Example: "sm.sendInitialSpeedQuiz(0, 0, 10, 2, 1, 10)"
	 *
	 * @param type
	 * 		The type (0 for question, 1 for nothing).
	 * @param quizType
	 * 		The type of quiz (experiment with this).
	 * @param answer
	 * 		The correct answer.
	 * @param correctAnswers
	 * 		Current amount of correct answers.
	 * @param remaining
	 * 		The remaining amount of questions.
	 * @param time
	 * 		The remaining amount of time, in seconds.
	 */
	void sendInitialSpeedQuiz(byte type, int quizType, int answer, int correctAnswers, int remaining, int time);

	/**
	 * Sends an IC quiz.
	 * Example: "sm.sendICQuiz(0, "What is 1 + 1", "It isn't 3", 30)"
	 *
	 * @param type
	 * 		The type (0 for question, 1 for nothing).
	 * @param text
	 * 		The question for the quiz.
	 * @param hintText
	 * 		The hint of the quiz.
	 * @param time
	 * 		The remaining amount of time, in seconds.
	 */
	void sendICQuiz(byte type, String text, String hintText, int time);

	/**
	 * Sends a chat window with the user's avatar as speaker.
	 * Example: "sm.sendAskAvatar("What's up buddy boi", false, false)"
	 *
	 * @param text
	 * 		The text to display.
	 * @param angelicBuster
	 * 		Whether or not the avatar should be in its dress up form.
	 * @param zeroBeta
	 * 		Whether or not the avatar should be in its beta form.
	 */
	void sendAskAvatar(String text, boolean angelicBuster, boolean zeroBeta);



	// Start helper methods for scripts --------------------------------------------------------------------------------

	/**
	 * Fully disposes the script.
	 * Example: "sm.dispose()"
	 */
	void dispose();



	// Character Stat-related methods ----------------------------------------------------------------------------------

	/**
	 * Sets the {@link Char} to the specified job.
	 * Example: "sm.setJob(112)"
	 *
	 * @param jobID
	 * 		The id of the job.
	 */
	void setJob(short jobID);

	/**
	 * Add Skill Points to {@link Char}.
	 * Example: "sm.addSP(3)"
	 *
	 * @param amount
	 * 		The amount of Skill Points added.
	 */
	void addSP(int amount);

	/**
	 * Sets the Skill Points of {@link Char} to the specified amount.
	 * Example: "sm.setSP(15)"
	 *
	 * @param amount
	 * 		The amount to which the Skill Points will be set.
	 */
	void setSP(int amount);

	/**
	 * Add Ability Points to {@link Char}.
	 * Example: "sm.addAP(5)"
	 *
	 * @param amount
	 * 		The amount of Ability Points added
	 */
	void addAP(int amount);

	/**
	 * Sets the Ability Points of {@link Char} to the specified amount.
	 * Example: "sm.setAP(15)"
	 *
	 * @param amount
	 * 		The amount to which the Ability Points will be set.
	 */
	void setAP(int amount);

	/**
	 * A Combined method, that sets the {@link Char} JobID to the specified job ID
	 * as well as add 5 AP  and  3 SP.
	 * Example: "sm.jobAdvance(112)"
	 *
	 * @param jobID
	 * 		The id of the Job.
	 */
	void jobAdvance(short jobID);

	/**
	 * Gives the {@link Char} the specified Exp.
	 * Example: "sm.giveExp(5000)"
	 *
	 * @param expGiven
	 * 		The amount of Exp given
	 */
	void giveExp(long expGiven);

	/**
	 * Gives the {@link Char} the specified Exp, however no Exp Message is given
	 * Example: "sm.giveExp(5000)"
	 *
	 * @param expGiven
	 * 		The amount of Exp given
	 */
	void giveExpNoMsg(long expGiven);



	// Field-related methods -------------------------------------------------------------------------------------------

	/**
	 * Warps the {@link Char} to the specified field.
	 * Example: "sm.warp(100)"
	 *
	 * @param fieldID
	 * 		The id of the field to warp the {@link Char} to.
	 */
	void warp(int fieldID);

	/**
	 * Warps the linked {@link Char} to the specified field at the specified portal.
	 * Example: "sm.warp(100, 10)"
	 *
	 * @param fieldID
	 * 		The id of the field.
	 * @param portalID
	 * 		The id of the portal.
	 */
	void warp(int fieldID, int portalID);

	/**
	 * Teleports {@link Char} to the portal ID specified.
	 * Example: "sm.teleportToPortal(0)"
	 *
	 * @param portalId
	 * 		The id of the Portal
	 */
	void teleportToPortal(int portalId);

	/**
	 * Gets the id of the linked {@link Field}
	 * Example: "sm.getFieldID()"
	 *
	 * @return The id of the linked {@link Field}.
	 */
	int getFieldID();

	/**
	 * Warps a whole party to a given field id. Immediately sets the field instance type to PARTY.
	 * Example: "sm.warpParty(100)"
	 *
	 * @param fieldID
	 * 		The id of the field to warp to.
	 */
	void warpPartyIn(int fieldID);

	/**
	 * Warps a whole party to a given field id. Immediately sets the field instance type to CHANNEL.
	 * Example: "sm.warpPartyOut(100)"
	 *
	 * @param fieldID
	 * 		The id of the field to warp to.
	 */
	void warpPartyOut(int fieldID);

	/**
	 * Resets the party's field instance info, ensuring new field instances are created.
	 * Example: "sm.clearPartyInfo()"
	 *
	 * @param warpToID
	 * 		The field id that all chars should be warped to
	 */
	void clearPartyInfo(int warpToID);

	/**
	 * Warp {@link Char} into the instanced field of the specified id.
	 * Example: "sm.warpInstanceIn(100000000)"
	 *
	 * @param id
	 * 		The id of the field to be instanced
	 */
	void warpInstanceIn(int id);

	/**
	 * Warp {@link Char} out of the instanced field into a non-instanced field of the specified id.
	 * Example: "sm.warpInstanceOut(100000000)"
	 *
	 * @param id
	 * 		The id of the non-instanced field to be warped into
	 */
	void warpInstanceOut(int id);

	/**
	 * Gets the ID of the current return {@link Field}.
	 * Example: "sm.getReturnField()"
	 *
	 * @return The ID of the linked return {@link Field}.
	 */
	int getReturnField();

	/**
	 * Sets the return {@link Field} to the {@link Field} linked to the {@link ScriptManager}.
	 * Example: "sm.setReturnField()"
	 */
	void setReturnField();

	/**
	 * Sets the return {@link Field}.
	 * Example: "sm.setReturnField(100)"
	 *
	 * @param fieldID
	 * 		The id of the {@link Field}.
	 */
	void setReturnField(int fieldID);

	/**
	 * Determines if there are mobs present in the {@link Field} linked to the {@link
	 * ScriptManager}.
	 * Example: "if(sm.mobsPresentInField()) {}"
	 *
	 * @return True if there are mobs in the linked {@link Field}.
	 */
	boolean mobsPresentInField();

	/**
	 * Determines if there are mobs present in a defined {@link Field}.
	 * Example: "if(sm.mobsPresentInField(100)) {}"
	 *
	 * @param fieldID
	 * 		The id of the {@link Field} to receive info from.
	 *
	 * @return True if there are mobs in the {@link Field}.
	 */
	boolean mobsPresentInField(int fieldID);

	/**
	 * Gets the number of mobs in the {@link Field} linked to the {@link ScriptManager}
	 * Example: "sm.numberMobsInField()"
	 *
	 * @return The number of mobs in the linked {@link Field}.
	 */
	int numberMobsInField();

	/**
	 * Gets the number of mobs from a selected {@link Field}.
	 * Example: "sm.numberMobsInField(100)"
	 *
	 * @param fieldID
	 * 		The fieldID of the {@link Field} to get the number of mobs in.
	 *
	 * @return The number of mobs in the selected {@link Field}.
	 */
	int numberMobsInField(int fieldID);



	// Life-related methods --------------------------------------------------------------------------------------------


	// NPC methods

	/**
	 * Spawns an NPC on the {@link Char} field, of the specified template id, and with the specified x and y co-ordinates.
	 * Example: "sm.spawnNpc(1000000)"
	 *
	 * @param npcId
	 * 		The id of the NPC to be spawned.
	 * @param x
	 * 		The x co-ordinate in the Field for the NPC to be spawned at.
	 * @param y
	 * 		The y co-ordinate in the Field for the NPC to be spawned at.
	 */
	void spawnNpc(int npcId, int x, int y);

	/**
	 * Removes the NPC on the {@link Char} field, with the specified template id.
	 * Example: "sm.spawnNpc(1000000)"
	 *
	 * @param npcId
	 * 		The id of the NPC to be spawned.
	 */
	void removeNpc(int npcId);

	/**
	 * Opens a dialogue with the npc specified.
	 * Example: "sm.openNpc(1000000)"
	 *
	 * @param npcId
	 * 		The id of the NPC's dialogue to be opened
	 */
	void openNpc(int npcId);

	/**
	 * Opens a shop defined by the shop ID.
	 * Example: "sm.openShop(100)"
	 *
	 * @param shopID
	 * 		The shop ID to define which shop to open.
	 */
	void openShop(int shopID);

	/**
	 * Opens a trunk (storage) with a given NPC.
	 * Example: "sm.openTrunk(100)"
	 *
	 * @param npcTemplateID
	 * 		The npc template ID to open the trunk with.
	 */
	void openTrunk(int npcTemplateID);

	/**
	 * Sets the current speaker template id for npc chat.
	 * Example: "sm.setSpeakerID(1010100)"
	 * @param templateID the speaker's template id
	 */
	void setSpeakerID(int templateID);



	// Mob methods

	/**
	 * Spawns a mob on the {@link Char} linked to the {@link ScriptManager}.
	 * Example: "sm.spawnMobOnChar(100)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 */
	void spawnMobOnChar(int mobID);

	/**
	 * Spawns a mob on the {@link Char} linked to the {@link ScriptManager}.
	 * Example: "sm.spawnMobOnChar(100, false)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 * @param respawnable
	 * 		The true/false defining if the mob is respawnable.
	 */
	void spawnMobOnChar(int mobID, boolean respawnable);

	/**
	 * Spawns a defined mob.
	 * Example: "sm.spawnMob(100)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 */
	void spawnMob(int mobID);

	/**
	 * Spawns a defined mob.
	 * Example: "sm.spawnMob(100, false)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 * @param respawnable
	 * 		The true/false defining if the mob is respawnable.
	 */
	void spawnMob(int mobID, boolean respawnable);

	/**
	 * Spawns a defined mob at an x/y location.
	 * Example: "sm.spawnMob(100, 0, 0, false)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 * @param x
	 * 		The x coordinate of the mob.
	 * @param y
	 * 		The y coordinate of the mob.
	 * @param respawnable
	 * 		The true/false defining if the mob is respawnable.
	 */
	void spawnMob(int mobID, int x, int y, boolean respawnable);

	/**
	 * Spawns a defined mob at an x/y location.
	 * Example: "sm.spawnMob(100100, 0, 0, false, 1000)"
	 *
	 * @param mobID
	 * 		The ID of the mob to spawn.
	 * @param x
	 * 		The x coordinate of the mob.
	 * @param y
	 * 		The y coordinate of the mob.
	 * @param respawnable
	 * 		The true/false defining if the mob is respawnable.
	 * @param hp
	 * 		The hp that the mob should start out with
	 */
	void spawnMob(int mobID, int x, int y, boolean respawnable, long hp);

	/**
	 * Removes the specified mob from the {@link Char}'s field.
	 *
	 * @param id
	 * 		The Object Id of the monster.
	 */
	void removeMobByObjId(int id);

	/**
	 * Removes the specified mob(s) from the {@link Char}'s field.
	 *
	 * @param id
	 * 		The template id of the monster(s).
	 */
	void removeMobsByTemplateId(int id);

	/**
	 * remove the specified monster(s) after the specified seconds.
	 *
	 * @param templateID
	 * 		The template id of the monster(s).
	 * @param seconds
	 * 		The amount of time (in seconds) until the specified mobs get removed.
	 */
	void removeMobsAfterTimer(int templateID, int seconds);


	/**
	 * Shows the {@link Char}'s HP with the pre-defined template.
	 * Example: "sm.showHP()"
	 */
	void showHP();

	/**
	 * Shows the {@link Char}'s HP with the defined template.
	 * Example: "sm.showHP(100)"
	 *
	 * @param templateID
	 * 		The template to show the HP with.
	 */
	void showHP(int templateID);



	// Reactor methods

	/**
	 * Removes the reactor in the {@link Field}.
	 * Example: "sm.removeReactor()"
	 */
	void removeReactor();



	// Party-related methods -------------------------------------------------------------------------------------------

	/**
	 * Gets the {@link Char} linked to the {@link ScriptManager}'s {@link Party}.
	 * Example: "sm.getParty()"
	 *
	 * @return The {@link Party} of the linked {@link Char}.
	 */
	Party getParty();

	/**
	 * Gets the {@link Char} linked's Party size
	 *
	 * @return The amount of members in {@link Char} linked's Party
	 */
	int getPartySize();

	/**
	 * Sets the {@link Field} instance type to PARTY, making all maps unique per party.
	 * Example: "sm.setPartyField()"
	 */
	void setPartyField();

	/**
	 * Checks if the {@link Char} linked to the {@link ScriptManager} is the {@link Party} leader.
	 * Example: "if(sm.isPartyLeader()) {}"
	 *
	 * @return True if the linked {@link Char} is the {@link Party} leader.
	 */
	boolean isPartyLeader();

	/**
	 * Checks whether or not the whole party is in the same map, and in the same channel.
	 * Also checks if the {@link Char} linked to the {@link ScriptManager} is the leader.
	 * Example: "if(sm.checkParty()) {}"
	 *
	 * @return True if the entire party is in the same location/channel and the leader is the linked
	 * {@link Char}.
	 */
	boolean checkParty();



	// Guild-related methods -------------------------------------------------------------------------------------------

	/**
	 * Shows a window to the linked {@link Char} for creating a guild.
	 * Example: "sm.showGuildCreateWindow()"
	 */
	void showGuildCreateWindow();



	// Chat-related methods --------------------------------------------------------------------------------------------

	/**
	 * Redirected to {@link #chatRed(String)}
	 * Example: "sm.chat("Hello User.")"
	 *
	 * @param text
	 * 		The text sent to the linked {@link Char}.
	 *
	 * @see #chatRed(String)
	 */
	void chat(String text);

	/**
	 * Sends a red message in the main chat box.
	 * Example: "sm.chatRed("Hello User.")"
	 *
	 * @param text
	 * 		The text sent to the linked {@link Char}.
	 */
	void chatRed(String text);

	/**
	 * Sends a blue message in the main chat box.
	 * Example: "sm.chatBlue("Hello User.")"
	 *
	 * @param text
	 * 		The text sent to the linked {@link Char}.
	 */
	void chatBlue(String text);

	/**
	 * Sends a script message with the specified text
	 * Example: "sm.chatScript("Hello User.")"
	 *
	 * @param text
	 * 		The text send to the linked {@link Char}.
	 */
	void chatScript(String text);



	// Inventory-related methods ---------------------------------------------------------------------------------------

	/**
	 * Changes the linked {@link Char}'s mesos.
	 * Example: "sm.giveMesos(100000)"
	 *
	 * @param mesos
	 * 		The amount of mesos to change the {@link Char}'s mesos by.
	 */
	void giveMesos(long mesos);

	/**
	 * Changes the linked {@link Char}'s mesos.
	 * Example: "sm.deductMesos(100000)"
	 *
	 * @param mesos
	 * 		The amount of mesos to change the {@link Char}'s mesos by.
	 */
	void deductMesos(long mesos);

	/**
	 * Gets the mesos of the linked {@link Char}.
	 * Example: "sm.getMesos()"
	 *
	 * @return The mesos of the linked {@link Char}.
	 */
	long getMesos();

	/**
	 * Gives the linked {@link Char} the defined item.
	 * Example: "sm.giveItem(100)"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 */
	void giveItem(int itemID);

	/**
	 * Gives the linked {@link Char} the specified quantity of the defined item.
	 * Example: "sm.giveItem(100, 1)"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 * @param quantity
	 * 		The quantity of the item.
	 */
	void giveItem(int itemID, int quantity);

	/**
	 * Determines if the linked {@link Char} has the specified item.
	 * Example: "if(sm.hasItem(100)) {}"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 *
	 * @return True if the link {@link Char} has the item.
	 */
	boolean hasItem(int itemID);

	/**
	 * Determines if the linked {@link Char} has the specified item with the specified quantity.
	 * Example: "if(sm.hasItem(100, 3)) {}"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 * @param quantity
	 * 		The quantity of the item.
	 *
	 * @return True if the link {@link Char} has the item with the specified quantity.
	 */
	boolean hasItem(int itemID, int quantity);

	/**
	 * Consumes 1 of a specified item.
	 * Example: "sm.consumeItem(100)"
	 *
	 * @param itemID
	 * 		The ID of the consumed item.
	 */
	void consumeItem(int itemID);

	/**
	 * Consumes a specified item with a specified quantity.
	 * Example: "sm.consumeItem(100, 1)"
	 *
	 * @param itemID
	 * 		The ID of the consumed item.
	 * @param quantity
	 * 		The quantity of the amount of items consumed.
	 */
	void consumeItem(int itemID, int quantity);

	/**
	 * Gives the CTS to the linked {@link Char} of the specified item id
	 *
	 * @param id
	 * 		The ID of the item
	 */
	void useItem(int id);

	/**
	 * Returns the amount of items the linked {@link Char} has of the specified id.
	 * Example: "if(getQuantityOfitem(100) > 50) {}"
	 *
	 * @param id
	 * 		The ID of the item.
	 *
	 * @return Quantity of specified id in the link {@link Char} inventory.
	 */
	int getQuantityOfItem(int id);

	/**
	 * Determines if the linked {@link Char} can hold a specific item.
	 * Example: "if(sm.canHold(100)) {}"
	 *
	 * @param itemID
	 * 		The ID of the item.
	 *
	 * @return True if the linked {@link Char} can hold the item.
	 */
	boolean canHold(int itemID);

	/**
	 * Returns the amount of empty slots {@link Char} has in the specified Inventory Type.
	 * Example: "sm.getEmptyInventorySlots(InvType.EQUIP)"
	 *
	 * @param invType
	 * 		The Inventory Type to be used.
	 * @return
	 * 		The amount of empty slots in the specified Inventory Type.
	 */
	int getEmptyInventorySlots(InvType invType);



	// Quest-related methods -------------------------------------------------------------------------------------------

	/**
	 * Completes a quest, and gives the rewards to the character.
	 * Example: "sm.completeQuest(10001)"
	 *
	 * @param questID
	 * 		The ID of the quest.
	 */
	void completeQuest(int questID);

	/**
	 * Completes a quest without giving quest rewards.
	 * Example: "sm.completeQuestNoRewards(10001)"
	 *
	 * @param questID
	 * 		The ID of the quest.
	 */
	void completeQuestNoRewards(int questID);

	/**
	 * Starts a quest without checking quest requirements.
	 * Example: "sm.startQuestNoCheck(100)"
	 *
	 * @param questID
	 * 		The ID of the quest.
	 */
	void startQuestNoCheck(int questID);

	/**
	 * Starts a quest and checks the quest requirements.
	 * Example: "sm.startQuest(100)"
	 *
	 * @param id
	 * 		The id of the quest.
	 */
	void startQuest(int id);

	/**
	 * Returns whether or not {@link Char} has the specified quest in progress.
	 * Example: "if sm.hasQuest(100):"
	 *
	 * @param id
	 * 		The id of the quest.
	 * @return
	 * 		True if {@link Char} has the quest in progress. False if the quest is done or not started.
	 */
	boolean hasQuest(int id);

	/**
	 * Returns whether or not {@link Char} has completed the specified quest.
	 * Example: "if sm.hasQuestCompleted(100):"
	 *
	 * @param id
	 * 		The id of the quest.
	 * @return
	 * 		True if {@link Char} has the quest completed. False if the quest isn't started or in progress.
	 */
	boolean hasQuestCompleted(int id);



	// Boss-related methods --------------------------------------------------------------------------------------------

	/**
	 * Sets the {@link Char}'s Death Count to the specified amount.
	 *
	 * @param deathCount
	 * 		The Death Count set
	 */
	void setDeathCount(int deathCount);


	/**
	 * Sets the Party of the {@link Char} Death Count to the specified amount.
	 *
	 * @param deathCount
	 * 		The Death Count set
	 */
	void setPartyDeathCount(int deathCount);



	// Character Temporary Stat-related methods ------------------------------------------------------------------------

	/**
	 * Gives the CTS to the linked {@link Char} with specified CTS, nOption, rOption, time
	 *
	 * @param cts
	 * 		The CTS of the buff to be given
	 * @param nOption
	 * 		The nOption of the buff to be given
	 * @param rOption
	 * 		The rOption of the buff to be given
	 * @param time
	 * 		The tOption of the buff to be given
	 */
	void giveCTS(CharacterTemporaryStat cts, int nOption, int rOption, int time);

	/**
	 * Removes a CTS from the linked {@link Char} specified by CTS
	 *
	 * @param cts
	 * 		The CTS to be removed
	 */
	void removeCTS(CharacterTemporaryStat cts);

	/**
	 * Removes a whole buff from the linked {@link Char} specified by the Skill ID
	 * @param skillId
	 * 		The ID of the Skill
	 */
	void removeBuffBySkill(int skillId);

	/**
	 * Return boolean if the linked {@link Char} has the specified CTS
	 *
	 * @param cts
	 * 		CTS to be checked
	 *
	 * @return
	 * 		return true if the linked {@link Char} has the specified CTS
	 */
	boolean hasCTS(CharacterTemporaryStat cts);

	/**
	 * Return nOption from the linked {@link Char} based on the specified CTS
	 *
	 * @param cts
	 * 		CTS to return the nOption from
	 *
	 * @return
	 * 		return nOption from the CTS specified
	 */
	int getnOptionByCTS(CharacterTemporaryStat cts);

	/**
	 * Makes the {@link Char} ride a mount with the specified id.
	 *
	 * @param mountID
	 * 		The id of the Mount
	 */
	void rideVehicle(int mountID);



	// Other methods ---------------------------------------------------------------------------------------------------

	/**
	 * Adds a damage skin to the {@link Char} linked to the {@link ScriptManager}.
	 * Example: "sm.addDamageSkin(100)"
	 *
	 * @param skinID
	 * 		The ID of the damage skin.
	 *
	 * @return True if the damage skin was successfully applied.
	 */
	boolean addDamageSkin(int skinID);


	/**
	 * Opens an ui with a given ID.
	 * Look at enums/UIType for all ID's.
	 * Add from net.swordie.ms.enums import UIType at the top of the npc script to use this.
	 * Example: "sm.openUI(UIType.UI_EQUIP)"
	 *
	 * @param uiID
	 * 		The ui id to show.
	**/
	void openUI(UIType uiID);

	/**
	 * Shows the {@link Char} the "Clear Stage with Exp" window, as well as giving the specified Exp.
	 *
	 * @param expGiven
	 * 		The amount of Exp given to the {@link Char}
	 */
	void showClearStageExpWindow(long expGiven);

	/**
	 * Returns a random integer below the number specified.
	 *
	 * @param upBound
	 * 		The number above the maximum random number
	 * @return
	 * 		Returns a random integer below the number specified.
	 */
	int getRandomIntBelow(int upBound);

	/**
	 * Grabs an effect from the WzFiles and displays it.
	 *
	 * @param dir
	 * 		The Directory to the Effect.
	 *
	 * @param delay
	 * 		The delay in ms before the effect shows
	 */
	void getEffect(String dir, int delay);

	/**
	 * Gives the user a weather notice with specified variables
	 *
	 *
	 * @param text
	 * 		Text in the weather notice
	 * @param type
	 * 		Type of weather notice
	 * @param duration
	 * 		Duration the weather notice stays on the screen
	 */
	void weatherNotice(String text, WeatherEffNoticeType type, int duration);
}
