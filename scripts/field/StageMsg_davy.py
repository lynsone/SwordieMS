# Maps in  Lord Pirate PQ  |  Used to show the Weather Notice on the maps
from net.swordie.ms.enums import WeatherEffNoticeType

def init():
    stage = ((sm.getFieldID() % 1000) / 100) + 1

    # Field Messages
    if stage == 1:
        sm.fieldWeatherNotice("Eliminate all monsters and break all the chests!", WeatherEffNoticeType.LordPiratePQ)
    elif stage == 2:
        sm.fieldWeatherNotice("Please collect the Pirate Marks and bring them to me", WeatherEffNoticeType.LordPiratePQ)
    elif stage == 3:
        sm.fieldWeatherNotice("Eliminate all monsters and break all the chests!", WeatherEffNoticeType.LordPiratePQ)
    elif stage == 4:
        sm.fieldWeatherNotice("Eliminate all monsters and break all the chests!", WeatherEffNoticeType.LordPiratePQ)
    elif stage == 5:
        sm.fieldWeatherNotice("Unlock all the doors!", WeatherEffNoticeType.LordPiratePQ)
    elif stage == 6:
        sm.fieldWeatherNotice("Eliminate the Captain!", WeatherEffNoticeType.LordPiratePQ)
    sm.dispose()