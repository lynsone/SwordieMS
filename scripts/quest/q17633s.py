# [Commerci Republic] Back to Town
def init():
    sm.setSpeakerID(9390256) # Leon Daniella
    sm.sendAskYesNo("Should we go right now?")

def action(response, answer):
    if response == 1:
        sm.warp(865000000, 0) # San Commerci
        sm.startQuest(parentID)
    sm.dispose()