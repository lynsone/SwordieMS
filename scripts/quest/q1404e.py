#   Thieves of Kerning City
from net.swordie.ms.enums import Stat

def init():
    sm.sendAskYesNo("So you want to become a sneaky Thief?")

def action(response, answer):
    if response == 1:
        sm.completeQuestNoRewards(parentID)
        sm.jobAdvance(400) # Thief
        sm.addAP(40)
        sm.getChr().addStatAndSendPacket(Stat.str, -40)
        sm.sendSayOkay("You are now a #bThief#k.")
        sm.giveItem(2070000, 500)
        sm.giveItem(1472000, 1)
        sm.giveItem(1332007, 1)
        sm.startQuestNoCheck(1421)
    sm.dispose()