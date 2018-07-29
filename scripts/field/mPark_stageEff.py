# Monster Park Maps

from net.swordie.ms.enums import WeatherEffNoticeType
from net.swordie.ms.constants import WzConstants
from net.swordie.ms.constants import GameConstants

def init():
    stage = ((sm.getFieldID() % 1000) / 100) + 1
    if stage == 6:
        sm.getEffect(WzConstants.EFFECT_MONSTER_PARK_FINAL_STAGE)
    else:
        sm.getEffect("" + WzConstants.EFFECT_MONSTER_PARK_STAGE_NUMBER + "" + str(stage))
        sm.getEffect(WzConstants.EFFECT_MONSTER_PARK_STAGE)
    sm.chatScript("All monsters in the field must be eliminated before you can move to the next stage")
    sm.dispose()

def onMobDeath(mob):
    #Exp based on mobTemplateId
    exp = sm.getMPExpByMobId(mob.getTemplateId())

    #Stores Exp from killing mobs
    sm.setQRValue(GameConstants.MONSTER_PARK_EXP_QUEST, str(int(sm.getQRValue(GameConstants.MONSTER_PARK_EXP_QUEST)) + exp))

    #displays the EXP message
    sm.fieldWeatherNotice("EXP reward "+ sm.formatNumber(sm.getQRValue()) +" earned!", WeatherEffNoticeType.MonsterPark_ExpMsg)
