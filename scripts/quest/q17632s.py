# [Commerci Republic] The Blocked Canal

status = -1

def init():
    sm.setPlayerAsSpeaker() # Has to be Player Avatar
    sm.sendNext("Should we head back to town?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("Not yet...")

    elif status == 1:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Don't tell me you're going to look for her...")

    elif status == 2:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("Of course I am.")

    elif status == 3:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("She's long gone, Leon.")

    elif status == 4:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendAskYesNo("You can go back to town if you want.")

    elif status == 5:
        if response == 1:
            sm.setPlayerAsSpeaker() # Has to be Player Avatar
            sm.sendNext("And leave you here to get jumped by more fishmen? I'd never earn your father's trust that way.")
            sm.startQuest(parentID)
        sm.dispose()