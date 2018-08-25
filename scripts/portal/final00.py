from net.swordie.ms.enums import QuestStatus
from net.swordie.ms.client.character.quest import Quest
from net.swordie.ms.constants import GameConstants

qm = sm.getChr().getQuestManager()
quest = qm.getQuests().get(GameConstants.MONSTER_PARK_EXP_QUEST)
if quest is None:
    quest = Quest(GameConstants.MONSTER_PARK_EXP_QUEST, QuestStatus.STARTED)
    quest.setQrValue("0")
    qm.addQuest(quest)
quest.setQrValue(str(int(quest.getQRValue())))


def init():
    fieldID = sm.getFieldID()
    warp = True

    if fieldID / 1000000 == 952 or fieldID / 1000000 == 953 or fieldID / 1000000 == 954:
        warp = False
        sm.setSpeakerID(9071005)
        sm.sendNext("Did you have a blast in Monster Park? Here's your reward\r\n"
                    "\r\n"
                    "#e#bDay-of-the-week reward: #v"+ str(sm.getMPReward()) +"##t"+ str(sm.getMPReward()) +"#\r\n"
                    "EXP reward: "+ sm.formatNumber(quest.getQRValue()))


    else:
        map = fieldID
        portal = 0

    if warp:
        sm.warp(map, portal)
        sm.dispose()

def action(response, answer):
    if sm.canHold(sm.getMPReward()):
        #Warp out instance | Monster Park
        sm.warpInstanceOut(951000000) #Monster Park
        sm.giveExp(long(quest.getQRValue()))
        sm.giveItem(sm.getMPReward())
    else:
        sm.sendSayOkay("Please make some space before leaving.")
    sm.dispose()