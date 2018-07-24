# Mu Lung Dojo Entrance (925020000) | Used for dojo weather notice

from net.swordie.ms.enums import WeatherEffNoticeType

def init():
    sm.weatherNotice("If you want to taste the bitterness of defeat, come on in!", WeatherEffNoticeType.MuLungDojo)
    sm.dispose()