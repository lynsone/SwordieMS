# [Commerci Republic] Delfino Deleter 3

status = -1
def init():
    sm.setSpeakerID(9390256) # Leon Daniella
    sm.sendAskYesNo("Shall we?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        if response == 1:
            sm.setSpeakerID(0) # Has to be Player Avatar
            sm.sendNext("#b(I have a bad feeling about this whole thing...)")
            sm.startQuestNoCheck(parentID)
        else:
            sm.sendNext("Alright, let me know when you are ready!")
        sm.dispose()