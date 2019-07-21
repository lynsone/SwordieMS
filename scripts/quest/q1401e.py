# 1401 - Warriors of Perion

sm.setSpeakerID(1022000) # Dances with Balrog
response = sm.sendAskYesNo("So you want to become a Warrior?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.setJob(100)
    sm.setSP(5)
    sm.resetAP(False)
    sm.giveItem(1302182)
    sm.sendSayOkay("You are now a #bWarrior#k.")
sm.dispose()
