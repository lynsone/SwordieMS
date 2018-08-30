# 1402 - Magicians of Ellinia

def init():
    sm.setSpeakerID(9000025) # Grendel the Really Old
    sm.sendAskYesNo("So you want to become a Magician?")

def action(response, answer):
    if response == 1:
        sm.completeQuestNoRewards(parentID)
        sm.setJob(200) # Magician
        sm.addSP(5)
        sm.addAP(5)
        sm.getChr().addStatAndSendPacket(Stat.str, -40)
        sm.getChr().addStatAndSendPacket(Stat.inte, 40)
        sm.giveItem(1372043)
        sm.sendSayOkay("You are now a #bMagician#k.")
    sm.dispose()