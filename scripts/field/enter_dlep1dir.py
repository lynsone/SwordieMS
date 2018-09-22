# this map share same script with 302090100 so I changed the map to 302010000
if sm.getFieldID() == 302090000:
    # [Grand Athenaeum] Stormy Forest : White Mage Prologue
    sm.lockInGameUI(True)
    sm.blind(1, 255, 0, 0)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/0", 100)
    sm.sayMonologue("I am a wandering mercenary.\r\n", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/1", 100)
    sm.sayMonologue("Looking back, it was a life that could end anywhere, at anytime.", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/2", 100)
    sm.sayMonologue("The sun will set and the wind will blow, and someday I will be lost to history.", False)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/3", 100)
    sm.sayMonologue("\r\n\r\nAnd perhaps...today will be that day.", True)

    sm.blind(1, 255, 0, 0)
    sm.sendDelay(1200)

    sm.blind(0, 0, 0, 1000)
    sm.sendDelay(1400)

    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("...")

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/4", 100)
    sm.sendSay("What happened to everyone? His followers trusted him...!")

    sm.moveCamera(300, 450, 185)
    sm.sendDelay(4520)

    sm.moveCameraBack(300)
    sm.sendDelay(4520)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/5", 100)
    sm.sendNext("Were his words all lies?")

    sm.blind(1, 200, 0, 1300)
    sm.sendDelay(1600)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/7", 100)
    sm.sayMonologue("Where shall I start...?", True)

    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/8", 100)
    sm.sayMonologue("...Yes. Let's begin when I first heard about the White Mage.", True)

    sm.blind(1, 255, 0, 500)
    sm.sendDelay(500)

    sm.sendDelay(1200)
    sm.sayMonologue("#fs40#Episode I: The White Mage", True)

    sm.lockInGameUI(False)
    sm.warp(302010000, 0)