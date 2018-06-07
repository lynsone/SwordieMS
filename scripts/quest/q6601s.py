# Elven Blessing link skill quest

from net.swordie.ms.constants import SkillConstants

def init():
    sm.getChr().addSkill(20021110, 1, 3)
    sm.sendSayOkay("I've added your link skill, enjoy!.")
    sm.completeQuest(6601)
    sm.dispose()