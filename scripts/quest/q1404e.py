#   Thieves of Kerning City

def init():
    sm.sendAskYesNo("So you want to become a sneaky Thief?")

def action(response, answer):
    if response == 1:
        sm.completeQuestNoRewards(parentID)
        sm.jobAdvance(400) #Thief
        sm.sendSayOkay("You are now a #bThief#k.")

        sm.startQuestNoCheck(1421)
    sm.dispose()