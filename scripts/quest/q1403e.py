# 1403 - Bowmen of Henesys

sm.setSpeakerID(1012100) # Athena
response = sm.sendAskYesNo("So you want to become a Bowman?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.setJob(300) # Archer
    sm.setSP(5)
    sm.resetAP(False)
    sm.giveItem(1452051, 1)
    sm.giveItem(2060000, 500)
    sm.giveItem(2061000, 500)
    sm.sendSayOkay("You are now an #bArcher#k.")
sm.dispose()
