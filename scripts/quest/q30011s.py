# [Root Abyss] Defeat the Third Seal Guardian

ALICIA = 1064002 # npc Id
status = -1
def init():
    sm.setSpeakerID(ALICIA)
    sm.sendNext("I believe it's working. I can really feel the darkness weaken.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendAskYesNo("Please take care of the seal guardian behind the crown door?")

    elif status == 1:
        if response == 1:
            sm.sendNext("The door with the crown is radiating evil! Better not let your guard down")
            sm.startQuest(parentID)
        sm.dispose()