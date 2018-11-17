# 140000000
LILIN = 1201000

sm.setSpeakerID(LILIN)
if sm.sendAskYesNo("#b(Are you certain that you were the hero that wielded the #p1201001#? Yes, you're sure. You better grab the #p1201001# really tightly. Surely it will react to you.)#k"):
    sm.giveItem(1142129)
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.removeSkill(20000297)
    sm.setJob(2100)
    sm.addSP(5, True)
    # 444
    sm.removeSkill(20001296)
    sm.giveSkill(20001296)
    sm.chatScript("You learned the Back to Rien skill.")

    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(You might be starting to remember something...)#k")

    sm.lockInGameUI(True, False)
    sm.warp(914090100, 0)
else:
    sm.sendNext("#b(You need to think about this for a second...)#k")