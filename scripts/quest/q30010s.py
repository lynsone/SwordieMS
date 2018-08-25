# [Root Abyss] Defeat the Second Seal Guardian

ALICIA = 1064002 # npc Id
status = -1
def init():
    sm.setSpeakerID(ALICIA)
    sm.sendNext("Oh I feel the dark energy getting weaker!")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendAskYesNo("Can you handle the seal guardian behind the teapot door?")

    elif status == 1:
        if response == 1:
            sm.sendNext("You may want to prepare a bit. The energy behind that door feels strong.")
            sm.startQuest(parentID)
        sm.dispose()