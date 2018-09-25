# 1401 - Warriors of Perion

sm.setSpeakerID(10202) # Dances with Balrog
response = sm.sendAskYesNo("So you want to become a Warrior?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.jobAdvance(100)
    sm.sendSayOkay("You are now a #bWarrior#k.")
    sm.giveItem(1302182)
    sm.completeQuest(parentID)
sm.dispose()
