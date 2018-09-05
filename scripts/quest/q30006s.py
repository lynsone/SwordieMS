# [Root Abyss] Guardians of the World Tree

MYSTERIOUS_GIRL = 1064001 # npc Id
status = -1
def init():
    sm.removeEscapeButton()
    sm.lockInGameUI(True)
    sm.setPlayerAsSpeaker()
    sm.sendNext("We need to find those baddies if we want to get you out of here.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("But... they all left")

    elif status == 1:
        sm.setPlayerAsSpeaker()
        sm.sendNext("They had to have left some clues behind. "
                    "What about those weird doors over there?")

    elif status == 2:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("They showed up when the bad guys left, but I can't get through them.")

    elif status == 3:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Then that sounds like a good place to start. Maybe I should-")

    elif status == 4:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("Y-you're glowing!")
        sm.invokeAtFixedRate(0, 2450, 3, "showEffect", "Effect/Direction11.img/effect/Aura/0", 3, 0)

    elif status == 5:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Ah! What is this?! Don't let it take all my fr00dz!!")

    elif status == 6:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("#h0#!!!")

    elif status == 7:
        sm.startQuest(parentID)
        sm.lockInGameUI(False)
        sm.warpInstanceIn(910700300, 0) # Fake Vellum Cave for QuestLine