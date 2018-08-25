# [Root Abyss] The Mysterious Girl

MYSTERIOUS_GIRL = 1064001 # npc Id

status = -1
def init():
    sm.setSpeakerID(MYSTERIOUS_GIRL)
    sm.sendNext("I want to get out of here.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("What?")

    elif status == 1:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("I said I want to get out of here.")

    elif status == 2:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Oh, well I need to stay. I'm supposed to find out more about this place before I go.")

    elif status == 3:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("This place? This is Root Abyss. And you don't want to be here. "
                    "Let's leave together. Follow me")

    elif status == 4:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Wha- Where are you going?!")

    elif status == 5:
        sm.sendAskYesNo("#b(She looks lost... maybe I should help her out?)")

    elif status == 6:
        if response == 1:
            sm.sendNext("All right, fine. I'll show you how to get out.")
            sm.startQuestNoCheck(parentID)
        else:
            sm.dispose()

    elif status == 7:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("You are really going to help, right! You promised!")
        sm.dispose()