# [Commerci Republic] Eye for an Eye

status = -1
def init():
    sm.setSpeakerID(0) # Has to be Player Avatar
    sm.sendNext("Hi Gilberto!")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(9390203) # Gilberto Daniella
        sm.sendNext("Er, Yes. I am Gilberto Daniella. Do I know you?")

    elif status == 1:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("You sure don't! But my name is...")

    elif status == 2:
        sm.setSpeakerID(9390203) # Gilberto Daniella
        sm.sendNext("I'm sorry, but I'm quite busy. If you'll make an appointment. I can meet with you later.")
        sm.completeQuest(parentID)
        sm.warp(865090002, 1) # Hidden  Daniella Merchant Union Office
        sm.dispose()