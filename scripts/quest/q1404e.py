# 1404 - Thieves of Kerning City
from net.swordie.ms.enums import Stat

sm.setSpeakerID(1052001)
response = sm.sendAskYesNo("So you want to become a Thief?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.setJob(400) # Thief
    sm.setSP(5)
    sm.resetAP(False)
    sm.giveItem(2070000, 500)
    sm.giveItem(1332063, 1)
    sm.giveItem(1472061, 1)
    sm.sendSayOkay("You are now a #bThief#k.")
sm.dispose()
