# 1403 - Bowmen of Henesys

response = sm.sendAskYesNo("So you want to become a Bowman?")

if response == 1:
    sm.completeQuestNoRewards(parentID)
    sm.jobAdvance(300) # Archer
    sm.addSP(5)
    sm.addAP(5)
    sm.getChr().addStatAndSendPacket(Stat.str, -40)
    sm.getChr().addStatAndSendPacket(Stat.dex, 40)
    sm.sendSayOkay("You are now an #bArcher#k.")
sm.dispose()
