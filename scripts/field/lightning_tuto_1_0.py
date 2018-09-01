# Hidden Street : Destroyed Temple of Time Entrance (927020000) | Used in Luminous' Intro

PHANTOM = 2159353
GUWARU = 2159354
MAGNUS = 2159355


status = -1
def init():
    sm.lockInGameUI(True)
    sm.removeEscapeButton()

    sm.setPlayerAsSpeaker()
    sm.invokeAfterDelay(4000, "sendNext", "The heavens have set the perfect stage for our final confrontation.")

    sm.spawnNpc(GUWARU, 142, 18)
    sm.flipNpcByTemplateId(GUWARU, False)
    sm.hideNpcByTemplateId(GUWARU, True)

    sm.spawnNpc(PHANTOM, 1242, 18)
    sm.hideNpcByTemplateId(PHANTOM, True)

    sm.spawnNpc(MAGNUS, 14, 18)
    sm.flipNpcByTemplateId(MAGNUS, False)
    sm.hideNpcByTemplateId(MAGNUS, True)


def action(response, answer):
    global status
    status += 1

    if status == 0:
        # Force Move to the Left
        sm.forcedMove(True, 260)

        sm.setSpeakerID(PHANTOM)
        sm.invokeAfterDelay(4000, "sendNext", "A little over-dramatic, don't you think?")

        sm.invokeAfterDelay(4350, "showEffect", "Effect/BasicEff.img/pang", 500, 360, -40, 0, 0, True, 0)
        sm.invokeAfterDelay(4500, "hideNpcByTemplateId", PHANTOM, False)

    elif status == 1:
        # Flip User to face right
        sm.forcedFlip(False)

        sm.setPlayerAsSpeaker()
        sm.sendNext("You're late. Typical. One would think the greatest thief in the world could steal a watch, at least.")

    elif status == 2:
        sm.setSpeakerID(PHANTOM)
        sm.sendNext("There's such a thing as showing up fashionable late, you know. "
                    "Besides, you're the big hero. "
                    "I'm just along for the ride.")

    elif status == 3:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Call me what you will. "
                    "We must all stand together, or perish.")

    elif status == 4:
        sm.setSpeakerID(PHANTOM)
        sm.sendNext("I knew I wasn't going to like you from the start. You're too stuffy.")

    elif status == 5:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Sure. Right back at you.")

    elif status == 6:
        sm.setSpeakerID(PHANTOM)
        sm.sendNext("I'm glad we're on the same page. "
                    "And yet we were sent here together to wait for the end... "
                    "Maybe Freud has a better sense of humour than I thought.")

    elif status == 5:
        sm.setPlayerAsSpeaker()
        sm.sendNext("I still don't understand why I must stand idly by here with YOU. "
                    "Perhaps he thought the situation would be enough to make us set aside our differences.")

    elif status == 6:
        sm.setSpeakerID(PHANTOM)
        sm.sendNext("That's the kind of good-hearted nonsense that gets people killed...")

    elif status == 7:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Enough chatter. I sense a dark presence")

    elif status == 8:
        sm.setSpeakerID(PHANTOM)
        sm.sendNext("Don't waste your time moping around up here, staring into the distance. "
                    "It's not as romantic as it seems...")

    elif status == 9:
        # Phantom Disappears w/ Effect
        sm.invokeAfterDelay(250, "showEffect", "Effect/BasicEff.img/pang", 500, 360, -40, 0, 0, True, 0)
        sm.invokeAfterDelay(400, "hideNpcByTemplateId", PHANTOM, True)

        sm.setPlayerAsSpeaker()
        sm.invokeAfterDelay(1500, "sendNext", "Just one more step...")

    elif status == 10:
        sm.forcedFlip(True)
        sm.forcedMove(True, 110)

        sm.invokeAfterDelay(700, "hideNpcByTemplateId", GUWARU, False)

        sm.setSpeakerID(GUWARU)
        sm.invokeAfterDelay(700, "sendNext", "Halt! This battlefield is for you and I")

    elif status == 11:
        sm.moveCamera(450, 338, 18)
        sm.invokeAfterDelay(2000, "forcedMove", True, 443)
        sm.setSpeakerID(GUWARU)
        sm.invokeAfterDelay(5500, "sendNext", "The light you possess is like a warm ray of sunshine to the spirits. "
                                              "It would be painful to see it extinguished.")

    elif status == 12:
        sm.setPlayerAsSpeaker()
        sm.sendNext("If you feel so strongly about my preservation, turn away from this insanity. "
                    "Turn away from the Black Mage!")

    elif status == 13:
        sm.setSpeakerID(GUWARU)
        sm.sendNext("If doing what I believe is labeled as 'insanity'. Then I will gladly bear the stigma. "
                    "Though you and your kind will bear it with me...")

    elif status == 12:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Enough with the sophisty, Guwaru!")

    elif status == 13:
        sm.setSpeakerID(GUWARU)
        sm.sendNext("I thought I would enjoy some pre-dinner conversation, but I will be happy to end you now.")

    elif status == 14:
        sm.invokeAfterDelay(1000, "showNpcSpecialActionByTemplateId", GUWARU, "special")
        sm.invokeAfterDelay(5000, "hideNpcByTemplateId", GUWARU, True)
        sm.invokeAfterDelay(4500, "hideNpcByTemplateId", MAGNUS, False)

        sm.setSpeakerID(MAGNUS)
        sm.invokeAfterDelay(6000, "sendNext", "You served that fool up on a platter for me!")

    elif status == 15:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Magnus! Y-you destroyed him!")

    elif status == 16:
        sm.forceAction(438, 4500)
        sm.showEffect("Skill/2004.img/skill/20041226/prepare", 540, -50, -15, 0, 0, True, 0)
        sm.invokeAfterDelay(540, "showEffect", "Skill/2004.img/skill/20041226/keydown", 3000, -25, -15, 0, 0, True, 0)
        sm.invokeAfterDelay(300, "showNpcSpecialActionByTemplateId", MAGNUS, "barrier", 3000)

        sm.setSpeakerID(MAGNUS)
        sm.invokeAfterDelay(7000, "sendNext", "How cute. Well, I've got to kill your friends! See you!")

    elif status == 17:
        sm.setPlayerAsSpeaker()
        sm.sendNext("What are you talking about?!")

    elif status == 18:
        sm.setSpeakerID(MAGNUS)
        sm.sendNext("I don't have time to play with you! Ha. I'm done with this world, anyway.")

    elif status == 19:
        sm.invokeAfterDelay(500, "showNpcSpecialActionByTemplateId", MAGNUS, "teleportation")
        sm.invokeAfterDelay(800, "hideNpcByTemplateId", MAGNUS, True)

        sm.setPlayerAsSpeaker()
        sm.invokeAfterDelay(2000, "sendNext", "I've no time left to worry about Magnus. The Black Mage awaits!")

    elif status == 20:
        sm.lockInGameUI(False)
        sm.warp(927020010, 0)