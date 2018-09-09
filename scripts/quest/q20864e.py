# The Path of a Night Walker - Completion
from net.swordie.ms.enums import Stat

sm.setJob(1400) # Night Walker 1st Job
curLevel = sm.getChr().getLevel()
if sm.getChr().getStat(Stat.str) >= 50:
    sm.getChr().addStatAndSendPacket(Stat.str, -(curLevel * 5)) # deduct automatic AP given to str while leveling
    sm.getChr().addStatAndSendPacket(Stat.ap, (curLevel * 5)) # and add it back to AP
sm.setSpeakerID(1101006) # Eckhart
sm.sendSayOkay("Congratulations, you are now a night walker! I have added 5 AP and 5 SP, enjoy your journey!")
sm.addSP(5)
sm.addAP(5)
sm.completeQuest(parentID)
sm.giveItem(1472000) # Garnier
sm.giveItem(2070000, 500) # Subi Throwing Stars
sm.dispose()
