# The Path of a Blaze Wizard - Completion
from net.swordie.ms.enums import Stat

sm.setJob(1200) # Blaze Wizard 1st Job
curLevel = sm.getChr().getLevel()
if sm.getChr().getStat(Stat.str) >= 50:
    sm.getChr().addStatAndSendPacket(Stat.str, -(curLevel * 5)) # deduct automatic AP given to str while leveling
    sm.getChr().addStatAndSendPacket(Stat.ap, (curLevel * 5)) # and add it back to AP
sm.setSpeakerID(1101004) # Oz
sm.sendSayOkay("Congratulations, you are now a blaze wizard! I have added 5 AP and 5 SP, enjoy your journey!")
sm.addSP(5)
sm.addAP(5)
sm.completeQuest(parentID)
sm.giveItem(1382000) # Wooden Staff
sm.dispose()
