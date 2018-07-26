# Hidden Tower (921160100) | Part of the  Escape Party Quest

from net.swordie.ms.enums import WeatherEffNoticeType

def init():
    sm.fieldWeatherNotice("Sshh! You must escape the tower by quietly avoiding the obstacles.", WeatherEffNoticeType.EscapePQ)
    sm.dispose()