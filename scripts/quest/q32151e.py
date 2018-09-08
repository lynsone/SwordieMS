# [Theme Dungeon] Ellinel Fairy Academy

FANZY = 1040002 # NPC ID
FAIRYNAPPERS = 32101 # QUEST ID
status = -1

def init():
    sm.setSpeakerID(FANZY)
    sm.sendNext("Are you the one I invited to help out with the ruckus at the Ellinel Fairy Academy?")

def action(response, answer):
    global status, wasAskYesNo
    if response == 0 and not wasAskYesNo:
        status -= 1
    else:
        status += 1
        wasAskYesNo = False

    if status == -1:
        init()

    elif status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendSay("Um, of course?")

    elif status == 1:
        sm.setSpeakerID(FANZY)
        sm.sendNext("You don't look as strong as I'd hoped. But, you're famous, so I'll leave it to you.")

    elif status == 2:
        sm.startQuestNoCheck(FAIRYNAPPERS)
        sm.completeQuest(parentID)
        sm.dispose()
