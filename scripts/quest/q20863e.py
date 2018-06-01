# Path of a Wind Archer - Completion
from net.swordie.ms.enums import Stat

def init():
    sm.setJob(1300)
    curLevel = sm.getChr().getLevel()
    if Stat.str >= 50:
        sm.getChr().addStatAndSendPacket(Stat.str, -(curLevel * 5) - 5) # deduct automatic sp given to str while leveling
        sm.getChr().addStatAndSendPacket(Stat.ap, curLevel * 5) # and add it back to AP
    sm.setSpeakerID(1101005)
    sm.sendSayOkay("Congratulations, you are now a wind archer! I have added 5 AP and 5 SP, enjoy your journey!")
    sm.setSP(5)
    sm.completeQuest(20863)
    sm.giveItem(1452002, 1)
    sm.giveItem(2060000, 1000)