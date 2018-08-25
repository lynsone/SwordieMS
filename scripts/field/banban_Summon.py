# Root Abyss - Temporal Crevasse (Von Bon's Map)
from net.swordie.ms.enums import WeatherEffNoticeType

def init():
    sm.showWeatherNotice("Summon Von Bon in the Dimensional Schism.", WeatherEffNoticeType.SnowySnowAndSprinkledFlowerAndSoapBubbles, 10000)

def onMobDeath(mob):
    if mob.getTemplateId() == 9303154 and sm.hasQuest(30009):
        sm.completeQuest(30009) #[Root Abyss] Defeat the First Guardian