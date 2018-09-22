# [Grand Athenaeum] Ariant : Middle of the Desert
if not sm.hasQuestCompleted(32629):
    HATSAR = 2510001
    sm.lockInGameUI(True)
    sm.moveCamera(0, -205, -500)

    sm.blind(1, 255, 0, 0)
    sm.sendDelay(1200)

    sm.blind(0, 0, 0, 1000)
    sm.sendDelay(1400)

    sm.blind(1, 200, 0, 1300)
    sm.sendDelay(1600)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/9", 100)
    sm.sayMonologue("I was always alone.", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/10", 100)
    sm.sayMonologue("My weapons were the only thing I trusted. I had to put my life on the line just to eat.", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/11", 100)
    sm.sayMonologue("\r\nI am lucky enough to be alive today, but...I can't say that about all my friends.", True)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/12", 100)
    sm.sayMonologue("As I learned to fight, I also learned not to fear the end.", True)

    sm.moveCameraBack(100)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/13", 100)
    sm.sayMonologue("War and famine, plagues and disasters... The world was surely falling apart.", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/14", 100)
    sm.sayMonologue("And the rich lined their pockets with comfort as they watched it happen. The worst of them was Hatsar, the mogul of Ariant.", True)

    sm.blind(0, 0, 0, 1300)
    sm.sendDelay(1600)

    sm.removeEscapeButton()
    sm.setSpeakerID(HATSAR)
    sm.sendNext("*Huff huff* Hey, are you the servant that Hatsar sent? To escort me to Ariant?")

    sm.flipDialoguePlayerAsSpeaker()
    sm.sendSay("...What? It's mercenary, not servant.")

    sm.setSpeakerID(HATSAR)
    sm.sendSay("Hey, nothing wrong with being a servant! Anyway, good. Do you have some water? It's rough having to carry all these myself.")

    sm.lockInGameUI(False)
    sm.startQuest(32629)
    sm.completeQuest(32629)
    sm.warp(302010000, 0)
else:
    sm.createQuestWithQRValue(32603, "ep1=" + str(sm.getFieldID()))