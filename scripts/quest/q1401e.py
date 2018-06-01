#   Warriors of Perion

def init():
    #sm.setSpeakerID(10202) #Dances with Balrog
    sm.sendAskYesNo("So you want to become a mighty Warrior?")

def action(response, answer):
    if response == 1:
        sm.completeQuestNoRewards(parentID)
        sm.jobAdvance(100)
        sm.sendSayOkay("You are now a #bWarrior#k.")

        sm.startQuestNoCheck(1410) #2nd Job Quest
    sm.dispose()