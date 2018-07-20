# [Commerci Republic] The Trade Kingdom

status = -1
def init():
    sm.setSpeakerID(0) # Has to be Player Avatar
    sm.sendNext("Excuse me.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("Yes?")

    if status == 1:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("Could you point me toward the Daniella merchant Union Office?")

    if status == 2:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("I happen to be a Daniella Merchant myself. If you want a job, you can talk to me right here.")

    if status == 3:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("I don't need a job. I'm looking for #e#bGilberto Daniella#k#n.")

    if status == 4:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("Ha, do you think the Prime Minister has time for you? He's a busy man. Now, off with you.")

    if status == 5:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("#b(Hmm, now what?)")
        sm.completeQuest(parentID)
        sm.dispose()