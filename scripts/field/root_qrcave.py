# Root Abyss : Abyssal Cave (910700300)  Map for Root Abyss Quest Line

VELLUM = 1064017
VELLUM_STONE = 1064028
status = -1
def init():
    if sm.hasQuest(30006):
        sm.removeEscapeButton()
        sm.lockInGameUI(True)

        sm.spawnNpc(VELLUM_STONE, 692, 443)
        sm.flipNpcByTemplateId(VELLUM_STONE, True)

        sm.spawnNpc(VELLUM, 1000, 443)
        sm.flipNpcByTemplateId(VELLUM, True)
        sm.hideNpcByTemplateId(VELLUM, True)

        sm.sendDelay(2000)
    else:
        sm.dispose()

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("What.. is this place?")

    elif status == 1:
        sm.forcedMove(False, 577)
        sm.sendDelay(3000)

    elif status == 2:
        sm.moveCamera(200, 667, 443)
        sm.sendDelay(3000)

    elif status == 3:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("Foolish creature! You dare challenge #rhis#k will?!")

    elif status == 4:
        sm.setPlayerAsSpeaker()
        sm.sendNext("W-who's there?!")

    elif status == 5:
        sm.showNpcSpecialActionByTemplateId(VELLUM_STONE, "appearance")
        sm.sendDelay(5000)

    elif status == 6:
        sm.hideNpcByTemplateId(VELLUM_STONE, True)
        sm.sendDelay(3000)

    elif status == 7:
        sm.showEffectOnPosition("Effect/Direction11.img/bellum/appearance", 1500, 1060, 443)
        sm.sendDelay(1450)

    elif status == 8:
        sm.hideNpcByTemplateId(VELLUM, False)
        sm.sendDelay(3000)

    elif status == 9:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("#rHis#k majesty trusted me to be his Seal Guardian and YOU dare sully his plans. "
                    "I am called #bVellum#k. You will not live to remember it.")

    elif status == 10:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Are you the one who put a seal on the World Tree?")

    elif status == 11:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("The seal was #rhis majesty's#k idea. I am merely acting on his will.")

    elif status == 12:
        sm.setPlayerAsSpeaker()
        sm.sendNext("You keep saying #rhim#k. Are you talking about that demon with the eyepatch?")

    elif status == 13:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("Silence! Your filthy mouth shall not even reference #rhis#k might!")

    elif status == 14:
        sm.setPlayerAsSpeaker()
        sm.sendNext("I'm not looking for a fight. The Demon Slayer is our ally. Why can't you just join us as well?")

    elif status == 15:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("You dare put that filthy traitor on the same level as #rhim#k? I will grant your wish for a slow death!")
        sm.sendDelay(900)

    elif status == 16:
        sm.showEffectOnPosition("Effect/Direction11.img/effect/stone/0", 1320, 250, 443)
        sm.showEffectOnPosition("Effect/Direction11.img/effect/stone/0", 1320, 380, 443)
        sm.sendDelay(500)

    elif status == 17:
        sm.showEffectOnPosition("Effect/Direction11.img/effect/stone/0", 1320, 420, 443)
        sm.showEffectOnPosition("Effect/Direction11.img/effect/stone/0", 1320, 220, 443)
        sm.sendDelay(400)

    elif status == 18:
        sm.showEffectOnPosition("Effect/Direction11.img/effect/stone/0", 1320, 270, 443)
        sm.showEffectOnPosition("Effect/Direction11.img/effect/stone/0", 1320, 370, 443)
        sm.sendDelay(600)

    elif status == 19:
        sm.showEffectOnPosition("Effect/Direction11.img/effect/stone/0", 1320, 450, 443)
        sm.showEffectOnPosition("Effect/Direction11.img/effect/stone/0", 1320, 520, 443)
        sm.sendDelay(600)

    elif status == 20:
        sm.showEffectOnPosition("Effect/Direction11.img/effect/stone/0", 1320, 360, 443)
        sm.showEffectOnPosition("Effect/Direction11.img/effect/stone/0", 1320, 240, 443)
        sm.sendDelay(600)

    elif status == 21:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Looks like I'm going to have to use force.")

    elif status == 22:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("YOU use force on ME? Ha!")

    elif status == 23:
        sm.showNpcSpecialActionByTemplateId(VELLUM, "attack")
        sm.sendDelay(2100)

    elif status == 24:
        sm.showEffectOnPosition("Effect/Direction11.img/effect/skill/fire/0", 1620, 385, 443)
        sm.forcedAction(4, 1500)
        sm.sendDelay(2000)

    elif status == 25:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("Do you now understand the power before you?")

    elif status == 26:
        sm.sendNext("I am but ONE of the four Seal Guardians. You stand no chance against us.")

    elif status == 27:
        sm.sendNext("Now that you have fully grasped the meaningless nature of your existence, leave. "
                    "And do not seek to return. I am only merciful once.")

    elif status == 28:
        sm.lockInGameUI(False)
        sm.warpInstanceOut(910700200, 0) # Quest Field (Colossal Root)
        sm.dispose()