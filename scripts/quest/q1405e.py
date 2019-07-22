# 1405 - Pirates of the Nautilus

sm.setSpeakerID(1090000)
response = sm.sendAskYesNo("So you want to become a Pirate?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.setJob(500) # Pirate
    sm.setSP(5)
    sm.resetAP(False)
    sm.giveItem(1492014)
    sm.giveItem(1482014)
    sm.giveItem(2330006, 500)
    sm.sendSayOkay("You are now a #bPirate#k.")
sm.dispose()
