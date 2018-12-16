# 1401 - Warriors of Perion

sm.setSpeakerID(10202) # Dances with Balrog
response = sm.sendAskYesNo("So you want to become a Warrior?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.setJob(100)
    sm.setSp(5)
    sm.setAp(28)
    sm.sendSayOkay("You are now a #bWarrior#k.")
    sm.giveItem(1302182)
    sm.completeQuest(parentID)
sm.dispose()
