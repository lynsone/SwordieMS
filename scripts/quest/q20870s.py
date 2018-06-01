# WA - End of Knight-in-Training - Start
def init():
    sm.setSpeakerID(1101002)
    sm.sendAskYesNo("#h #, you have done surprisingly well. Do you wish to take the #b Knighthood Exam#k? If you pass, "
                    "you will become a full-fledged knight. #b\r\n(Note, if you accept, you will be ported to Ereve. Talk to your instructor there.)")

def action(response, answer):
    if response == 1:
       sm.warp(130000000)
       sm.completeQuest(20870)
    else:
        sm.sendSayOkay("Okay, maybe next time.")
    sm.dispose()
