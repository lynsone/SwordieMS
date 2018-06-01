#   Bowmen of Henesys

def init():
    sm.sendAskYesNo("So you want to become an accurate Bowmen?")

def action(response, answer):
    if response == 1:
        sm.completeQuestNoRewards(parentID)
        sm.jobAdvance(300) #Archer
        sm.sendSayOkay("You are now an #bArcher#k.")

        sm.startQuestNoCheck(1418)
    sm.dispose()