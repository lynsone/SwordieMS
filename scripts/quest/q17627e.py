# [Commerci Republic] Delfino Deleter 2

status = -1
def init():
    sm.setSpeakerID(9390256) # Leon Daniella
    sm.sendNext("I was so much faster than you! But you're the sidekick for a reason.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendNext("C'mon! We can't let them get their buddies. We have to finish this now! I'll be waiting for you at #m865020200#") # Canal 3

    elif status == 1:
        sm.sendNext("What are you waiting for, my loyal sidekick?")

    elif status == 2:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("Hold up. I have a really bad feeling about this...")

    elif status == 3:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("Don't feel bad. I'm here for you, pal.")

    elif status == 4:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("No, listen. These fishmen seem like they're barely even trying...")

    elif status == 5:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("That's only because I'm totally awesome. So they look weak in comparison.")

    elif status == 6:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("But...")

    elif status == 7:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("Let's go!")
        sm.completeQuest(parentID)
        sm.dispose()