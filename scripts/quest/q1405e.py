# 1405 - Pirates of the Nautilus

response = sm.sendAskYesNo("So you want to become a Pirate?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.setJob(500) # Pirate
    sm.addSP(5)
    sm.addAP(45)
    sm.getChr().addStatAndSendPacket(Stat.str, -40)
    sm.sendSayOkay("You are now a #bPirate#k.")
sm.dispose()
