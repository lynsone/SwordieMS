# 1401 - Warriors of Perion

def init():
    sm.setSpeakerID(10202) # Dances with Balrog
    sm.sendAskYesNo("So you want to become a Warrior?")

def action(response, answer):
    if response == 1:
        sm.completeQuestNoRewards(parentID)
        sm.jobAdvance(100)
        sm.sendSayOkay("You are now a #bWarrior#k.")
        sm.giveItem(1302182)
        sm.completeQuest(parentID)
    sm.dispose()