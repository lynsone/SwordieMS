# [Commerci Republic] Delfino Deleter 4

status = -1
def init():
    sm.setSpeakerID(9390256) # Leon Daniella
    sm.sendAskYesNo("There's a lot of #r#o9390810##k monsters here too! Okay, it'll be #e#b30#k#n for each of us again!")

def action(response, answer):
    if response == 1:
        sm.startQuestNoCheck(parentID)
    sm.dispose()