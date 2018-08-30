# 1405 - Pirates of the Nautilus

def init():
    sm.sendAskYesNo("So you want to become a Pirate?")

def action(response, answer):
    if response == 1:
        sm.completeQuestNoRewards(parentID)
        sm.setJob(500) # Pirate
        sm.addSP(5)
        sm.addAP(45)
        sm.getChr().addStatAndSendPacket(Stat.str, -40)
        sm.sendSayOkay("You are now a #bPirate#k.")
    sm.dispose()