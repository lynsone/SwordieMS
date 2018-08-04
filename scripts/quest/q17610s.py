# [Commerci Republic] Berry Concerned 1

status = -1
def init():
    sm.setSpeakerID(9390201) # Mayor Berry
    sm.sendNext("Hm...")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Mayor Berry, are you all right?")

    elif status == 1:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendNext("I'm worried about my fish... There are these monsters that were stealing my fish the other day..")

    elif status == 2:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("I'll take care of those monster for you, Mayor.")

    elif status == 3:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendAskYesNo("Will you really?")

    elif status == 4:
        if response == 1:
            sm.sendNext("If you could eliminate 100 #r#o9390807##k, I would be very grateful.")
            sm.startQuestNoCheck(parentID)
        else:
            sm.sendSayOkay("Oh, alright.. that's too bad.")
        sm.dispose()