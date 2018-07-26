# Monster Park Maps

from net.swordie.ms.enums import WeatherEffNoticeType
from net.swordie.ms.constants import WzConstants

def init():
    stage = ((sm.getFieldID() % 1000) / 100) + 1
    if stage == 6:
        sm.getEffect(WzConstants.EFFECT_FINAL_STAGE)
    else:
        sm.getEffect(""+ WzConstants.EFFECT_STAGE_NUMBER +""+ str(stage))
        sm.getEffect(WzConstants.EFFECT_STAGE)
    sm.chatScript("All monsters in the field must be eliminated before you can move to the next stage")
    sm.dispose()

def onMobDeath():
    sm.fieldWeatherNotice("EXP reward "+ str(5) +" earned!", WeatherEffNoticeType.MonsterPark_ExpMsg)