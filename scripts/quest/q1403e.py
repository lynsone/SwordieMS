# 1403 - Bowmen of Henesys

def init():
    sm.sendAskYesNo("So you want to become a Bowman?")

def action(response, answer):
    if response == 1:
        sm.completeQuestNoRewards(parentID)
        sm.jobAdvance(300) # Archer
        sm.addSP(5)
        sm.addAP(5)
        sm.getChr().addStatAndSendPacket(Stat.str, -40)
        sm.getChr().addStatAndSendPacket(Stat.dex, 40)
        sm.sendSayOkay("You are now an #bArcher#k.")
    sm.dispose()