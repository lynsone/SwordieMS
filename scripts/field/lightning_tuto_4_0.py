# Hidden Street : Black Mage's Antechamber (927020060)  |  Used in Luminous' Tutorial

sm.lockInGameUI(True)
sm.removeEscapeButton()

sm.setPlayerAsSpeaker()
sm.sendNext("Looks like Freud and Mercedes are already inside. "
            "I hope I'm not too late.")


sm.forcedMove(False, 340)
sm.sendNext("The air feels like black sludge in my lungs. "
            "The Black Mage is close...")

sm.lockInGameUI(False)
sm.warp(927020080)
