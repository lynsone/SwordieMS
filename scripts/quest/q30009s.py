# [Root Abyss] Defeat the First Seal Guardian

ALICIA = 1064002 # npc Id
status = -1
def init():
    sm.setSpeakerID(ALICIA)
    sm.sendNext("I don't know what you did, but there have been more people through here than the Free Market")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("The Maple Alliance is going to help me get you out of here.")

    elif status == 1:
        sm.sendNext("Unfortunately, that means I have to take out all these Seal Guardians. Do you have any ideas to help?")

    elif status == 2:
        sm.setSpeakerID(ALICIA)
        sm.sendNext("I've been trapped in here since they arrived. I don't know anything, but I can feel their power.")

    elif status == 3:
        sm.sendAskYesNo("The door with the clock on it seems to be the least threatening. Maybe that should be your first stop.")

    elif status == 4:
        if response == 1:
            sm.sendNext("I know you're strong, but I don't think you can do this alone. Make sure to find an #rally that will help you#k!")
            sm.startQuestNoCheck(parentID)
        sm.dispose()