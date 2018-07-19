#[Commerci Republic] Delfino Deleter 3

status = -1
def init():
    sm.setSpeakerID(9390256) #Leon Daniella
    sm.sendNext("So easy!")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("I really have a bad feeling about this...")

    elif status == 1:
        sm.setSpeakerID(9390256) #Leon Daniella
        sm.sendNext("Geez, Loosen up a bit. We'll be fine!")
        sm.completeQuest(parentID)
        sm.dispose()