#   Magicians of Ellinia

def init():
    sm.sendAskYesNo("So you want to become a wise Magician?")

def action(response, answer):
    if response == 1:
        sm.completeQuestNoRewards(parentID)
        sm.jobAdvance(200) # Magician
        sm.sendSayOkay("You are now a #bMagician#k.")

        sm.startQuestNoCheck(1414)
    sm.dispose()