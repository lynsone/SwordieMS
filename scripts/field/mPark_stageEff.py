# Monster Park Maps

from net.swordie.ms.constants import WzConstants

def init():
    stage = ((sm.getFieldID() % 1000) / 100) + 1
    if stage == 6:
        sm.getEffect(WzConstants.EFFECT_FINAL_STAGE)
    else:
        sm.getEffect(""+ WzConstants.EFFECT_STAGE_NUMBER +""+ str(stage))
        sm.getEffect(WzConstants.EFFECT_STAGE)
    sm.dispose()