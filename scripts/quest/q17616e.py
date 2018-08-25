# [Commerci Republic] Stolem Items

status = -1
def init():
    sm.setSpeakerID(9390220) # Maestra Fiametta
    sm.sendNext("Yes? What do you want?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("What can you tell me about the items that were stolen from the Daniella Merchant Union?")

    elif status == 1:
        sm.setSpeakerID(9390220) # Maestra Fiametta
        sm.sendNext("Not much to tell. A few days ago, a Daniella merchant deposited some items. A little while ago, he picked them up.")

    elif status == 2:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Are you sure it was the same guy?")

    elif status == 3:
        sm.setSpeakerID(9390220) # Maestra Fiametta
        sm.sendNext("Are you sure you have a brain in your skull? Yes, it was the same guy.")

    elif status == 4:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("No need to bite my head off.")
        sm.completeQuest(parentID)
        sm.dispose()