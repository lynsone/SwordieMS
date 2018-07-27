# Monster Park Maps

from net.swordie.ms.enums import WeatherEffNoticeType
from net.swordie.ms.constants import WzConstants
from net.swordie.ms.enums import QuestStatus
from net.swordie.ms.client.character.quest import Quest
from net.swordie.ms.constants import GameConstants

def init():
    stage = ((sm.getFieldID() % 1000) / 100) + 1
    if stage == 6:
        sm.getEffect(WzConstants.EFFECT_FINAL_STAGE)
    else:
        sm.getEffect(""+ WzConstants.EFFECT_STAGE_NUMBER +""+ str(stage))
        sm.getEffect(WzConstants.EFFECT_STAGE)
    sm.chatScript("All monsters in the field must be eliminated before you can move to the next stage")
    sm.dispose()

def onMobDeath(mob):
    exp = sm.getMPExpByMobId(mob.getTemplateId())

    qm = sm.getChr().getQuestManager()
    quest = qm.getQuests().get(GameConstants.MONSTER_PARK_EXP_QUEST)
    if quest is None:
        quest = Quest(GameConstants.MONSTER_PARK_EXP_QUEST, QuestStatus.STARTED)
        quest.setQrValue("0")
        qm.addQuest(quest)
    quest.setQrValue( str(int(quest.getQRValue()) + exp) )

    sm.fieldWeatherNotice("EXP reward "+ sm.formatNumber(quest.getQRValue()) +" earned!", WeatherEffNoticeType.MonsterPark_ExpMsg)
