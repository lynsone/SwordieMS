from net.swordie.ms.enums import WeatherEffNoticeType
hitCount = 0

def action(type):
    if type == 0:
        global hitCount
        hitCount += 1
        sm.showWeatherNotice("Please allow me to mourn over your imminent demise.", WeatherEffNoticeType.BossCrimsonQueenCrownPink, 10000)
        if hitCount == 5:
            sm.spawnMob(8920100, 37, 135, False)
            sm.removeReactor()
            sm.dispose()
