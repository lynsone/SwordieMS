# Root Abyss | Abyssal Cave  (Vellum's Boss Map)

from net.swordie.ms.enums import WeatherEffNoticeType

sm.showWeatherNotice("Vellum is nowhere to be seen. Take a look around the altar.", WeatherEffNoticeType.SnowySnowAndSprinkledFlowerAndSoapBubbles, 10000)

def onMobDeath(mob):
    if mob.getTemplateId() == 9400942 and sm.hasQuest(30012):
        sm.completeQuest(30012) #[Root Abyss] Defeat the Final Guardian
        sm.dispose()

