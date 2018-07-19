#[Commerci Republic] Delfino Deleter 4

status = -1
def init():
    sm.setSpeakerID(9390256) #Leon Daniella
    sm.sendAskYesNo("I'll be honest.. These fish were definitely a lot tougher! It's what I was born to do!")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("At least we made it. Now let's get out of here before the really tough fish come out.")

    elif status == 1:
        sm.completeQuest(parentID)
        sm.dispose()