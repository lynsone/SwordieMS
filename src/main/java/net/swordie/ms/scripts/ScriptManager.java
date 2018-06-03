package net.swordie.ms.scripts;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.enums.UIType;
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

	/**
	 * Fully disposes the script.
	 * Example: "sm.dispose()"
	 */
	void dispose();

	/**
	 * Warps the {@link Char} to the specified field.
	 * Example: "sm.warp(100)"
	 *
	 * @param fieldID
	 * 		The ID of the field to warp the {@link Char} to.
	 */
	void warp(int fieldID);

	/**
	 * Warps the linked {@link Char} to the specified field at the specified portal.
	 * Example: "sm.warp(100, 10)"
	 *
	 * @param fieldID
	 * 		The ID of the field.
	 * @param portalID
	 * 		The ID of the portal.
	 */
	void warp(int fieldID, int portalID);

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
	 * Gets the id of the linked {@link Field}
	 * Example: "sm.getFieldID()"
	 *
	 * @return The ID of the linked {@link Field}.
	 */
	int getFieldID();

	/**
	 * Gets the mesos of the linked {@link Char}.
	 * Example: "sm.getMesos()"
	 *
	 * @return The mesos of the linked {@link Char}.
	 */
	long getMesos();

	/**
	 * Changes the linked {@link Char}'s mesos, negative = give, positive = take.
	 * Example: "sm.giveMesos(100000)"
	 *
	 * @param mesos
	 * 		The amount of mesos to change the {@link Char}'s mesos by.
	 */
	void giveMesos(long mesos);

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
	 * Shows a window to the linked {@link Char} for creating a guild.
	 * Example: "sm.showGuildCreateWindow()"
	 */
	void showGuildCreateWindow();

	/**
	 * Gets the {@link Char} linked to the {@link ScriptManager}'s {@link Party}.
	 * Example: "sm.getParty()"
	 *
	 * @return The {@link Party} of the linked {@link Char}.
	 */
	Party getParty();

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

	/**
	 * Warps a whole party to a given field id. Immediately sets the field instance type to PARTY.
	 * Example: "sm.warpParty(100)"
	 *
	 * @param fieldID
	 * 		The ID of the field to warp to.
	 */
	void warpPartyIn(int fieldID);

	/**
	 * Warps a whole party to a given field id. Immediately sets the field instance type to CHANNEL.
	 * Example: "sm.warpPartyOut(100)"
	 *
	 * @param fieldID
	 * 		The ID of the field to warp to.
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

	/**
	 * Opens a shop defined by the shop ID.
	 * Example: "sm.openShop(100)"
	 *
	 * @param shopID
	 * 		The shop ID to define which shop to open.
	 */
	void openShop(int shopID);

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
	 * Opens a trunk (storage) with a given NPC.
	 * Example: "sm.openTrunk(100)"
	 *
	 * @param npcTemplateID
	 * 		The npc template ID to open the trunk with.
	 */
	void openTrunk(int npcTemplateID);

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

	/**
	 * Removes the reactor in the {@link Field}.
	 * Example: "sm.removeReactor()"
	 */
	void removeReactor();

	/**
	 * Sets the current speaker template id for npc chat.
	 * Example: "sm.setSpeakerID(1010100)"
	 * @param templateID the speaker's template id
	 */
	void setSpeakerID(int templateID);


}
