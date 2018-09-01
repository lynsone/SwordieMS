# Hidden Street : Temple of Time Corridor 2 (927030010)  |  Used for Luminous tutorial (so far)

ARAN = 2159441
WHITE_PRIEST = 2159360
BLACK_PRIEST = 2159361

status = -1
def init():
    sm.removeEscapeButton()

    sm.lockInGameUI(True)

    sm.setCameraOnNpc(ARAN)
    sm.teleportInField(1000, 0)

    sm.spawnNpc(WHITE_PRIEST, 1320, 69)
    sm.flipNpcByTemplateId(WHITE_PRIEST, False)
    sm.spawnNpc(BLACK_PRIEST, 1240, 69)
    sm.flipNpcByTemplateId(BLACK_PRIEST, False)

    sm.invokeAfterDelay(2000, "showBalloonMsg", "Effect/Direction8.img/effect/tuto/BalloonMsg0/5", 2000)
    sm.invokeAfterDelay(3500, "showNpcSpecialActionByTemplateId", ARAN, "attack")
    sm.invokeAfterDelay(5000, "showNpcSpecialActionByTemplateId", WHITE_PRIEST, "die")
    sm.invokeAfterDelay(5000, "showNpcSpecialActionByTemplateId", BLACK_PRIEST, "die")

    sm.invokeAfterDelay(6500, "hideNpcByTemplateId", WHITE_PRIEST, True)
    sm.invokeAfterDelay(6500, "hideNpcByTemplateId", BLACK_PRIEST, True)

    sm.invokeAfterDelay(6500, "forcedMove", False, 250)
    sm.setSpeakerID(ARAN)
    sm.invokeAfterDelay(7000, "sendNext", "I figured you'd have your hands full right now, but here you are goofing around!")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Aran! You're wounded! Where are Mercedes and Freud?")

    elif status == 1:
        sm.setSpeakerID(ARAN)
        sm.sendNext("Ah, it's nothing. Those two got a head start on me. "
                    "They might even be fighting the Black Mage right now...")

    elif status == 2:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Can you go on?")

    elif status == 3:
        sm.setSpeakerID(ARAN)
        sm.sendNext("Aww, are you worried about me? "
                    "I'm fine! Just... get in there, okay? "
                    "You don't want to let Mercedes and Freud hog all the glory. "
                    "I'll stay here and fend off anybody trying to get in...")

    elif status == 4:
        sm.setPlayerAsSpeaker()
        sm.sendNext("...Be careful")

    elif status == 5:
        sm.forcedMove(False, 500)
        sm.invokeAfterDelay(4000, "showBalloonMsg", "Effect/Direction8.img/effect/tuto/BalloonMsg0/7", 2000)
        sm.invokeAfterDelay(4500, "showEffect", "Effect/Direction8.img/effect/tuto/BalloonMsg0/6", 2000, -300, -100, 0, 0, True, 0)
        sm.invokeAfterDelay(6900, "lockInGameUI", False)
        sm.invokeAfterDelay(7000, "warp", 927020060)