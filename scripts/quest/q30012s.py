# [Root Abyss] Defeat the Final Seal Guardian

ALICIA = 1064002 # npc Id
status = -1
def init():
    sm.setSpeakerID(ALICIA)
    sm.sendNext("We're almost there! Only the final seal guardian remains.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendAskYesNo("Can you handle the seal guardian behind the dragon door?")

    elif status == 1:
        if response == 1:
            sm.sendNext("Be prepared. The energy behind the dragon door feels enormous.")
            sm.startQuest(parentID)
        sm.dispose()