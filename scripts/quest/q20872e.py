# BW - End of Knight-in-Training - Complete

def init():
    sm.setSpeakerID(1101004)
    sm.sendAskYesNo("You've defeated the 30 Tigurus! I like how hard you work! Are you ready work even harder by "
                    "accepting the responsibilities of an official knight?")

def action(response, answer):
    if response == 1:
        sm.setJob(1210)
        sm.addAP(5)
        sm.addSP(5)
        sm.completeQuest(parentID)
        sm.sendSayOkay("You have been officially promoted to a Cygnus Knight! I've also given you some AP and SP to work with.")
    else:
        sm.sendSayOkay("Okay, come back later when you've changed your mind.")
    sm.dispose()