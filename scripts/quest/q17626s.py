# [Commerci Republic] Delfino Deleter 1

status = -1
def init():
    sm.setSpeakerID(9390256) # Leon Daniella
    sm.sendNext("#h0#! I knew my loyal sidekick wouldn't abandon me! How'd you get around daddy?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("I didn't. He said I could come. As an official ally.")

    elif status == 1:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("You must really have a way with words, like me. I'm glad you made it. You can learn a lot!")

    elif status == 2:
        sm.sendAskYesNo("Now, are we ready to hunt some fish?")

    elif status == 3:
        if response == 1:
            sm.sendNext("Let's see who's faster!")
            sm.startQuestNoCheck(parentID)
        else:
            sm.sendSayOkay("What?.. You're not ready yet?")
        sm.dispose()