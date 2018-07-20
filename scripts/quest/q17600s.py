# [Commerci Republic] Neinheart's Call

status = -1
sm.setSpeakerID(1064026) # Neinheart
def init():
    sm.sendAskYesNo("Ah good, I've managed to reach you. The Empress has been asking for you. Could you come to Ereve?\r\n"
                    "#b(You will be moved to Ereve if you accept.)")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        if response == 1:
            sm.sendNext("I will be waiting for you.")
        else:
            sm.sendSayOkay("Let me know once you are ready.")
            sm.dispose()

    elif status == 1:
        sm.startQuestNoCheck(parentID)
        sm.warp(130000000, 0)