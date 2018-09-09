# Path of a Wind Archer - Completion
from net.swordie.ms.enums import Stat

sm.setJob(1300) # Wind Archer 1st Job
curLevel = sm.getChr().getLevel()
if sm.getChr().getStat(Stat.str) >= 50:
    sm.getChr().addStatAndSendPacket(Stat.str, -(curLevel * 5)) # deduct automatic AP given to str while leveling
    sm.getChr().addStatAndSendPacket(Stat.ap, (curLevel * 5)) # and add it back to AP
sm.setSpeakerID(1101005) # Irena
sm.sendSayOkay("Congratulations, you are now a wind archer! I have added 5 AP and 5 SP, enjoy your journey!")
sm.addSP(5)
sm.addAP(5)
sm.completeQuest(parentID)
sm.giveItem(1452002) # War Bow
sm.giveItem(2060000, 1000) # Bow Arrow
sm.dispose()
