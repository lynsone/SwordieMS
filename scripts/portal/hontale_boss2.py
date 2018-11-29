# horntail entrace - The Cave of Trial 1
from net.swordie.ms.constants import GameConstants

if sm.getFieldID() == 240060100:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 3:
        sm.spawnMob(8810001, -317,230, False)
        for partyMember in sm.getParty().getMembers():
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "4")
