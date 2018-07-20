#   Pirates of the Nautilus

def init():
    sm.sendAskYesNo("So you want to become a flashy Pirate?")

def action(response, answer):
    if response == 1:
        sm.completeQuestNoRewards(parentID)
        sm.jobAdvance(500) # Pirate
        sm.sendSayOkay("You are now a #bPirate#k.")

        sm.startQuestNoCheck(1424)
    sm.dispose()