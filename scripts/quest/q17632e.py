# [Commerci Republic] The Blocked Canal

status = -1

def init():
    sm.setSpeakerID(9390256) # Leon Daniella
    sm.sendNext("Hey, what's that?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("It looks like.. a barrier?")

    elif status == 1:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("A barrier for what? Do you think the fishhead set it up?")

    elif status == 2:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Maybe, but I'm not busting through to check")

    elif status == 3:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("Me neither. Let's go tell pops.")
        sm.completeQuest(parentID)
        sm.warpInstanceOut(865020300) # Canal 4