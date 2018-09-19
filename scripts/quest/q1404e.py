# 1404 - Thieves of Kerning City
from net.swordie.ms.enums import Stat

response = sm.sendAskYesNo("So you want to become a Thief?")

if response == 1:
    sm.completeQuestNoRewards(parentID)
    sm.jobAdvance(400) # Thief
    sm.getChr().addStatAndSendPacket(Stat.str, -40)
    sm.getChr().addStatAndSendPacket(Stat.luk, 40)
    sm.sendSayOkay("You are now a #bThief#k.")
    sm.giveItem(2070000, 500)
    sm.giveItem(1472000, 1)
    sm.giveItem(1332007, 1)
sm.dispose()
