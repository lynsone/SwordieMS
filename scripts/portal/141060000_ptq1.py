# 141060000
from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

if sm.hasQuest(32170):
    # Ride Vehicle Check
    if sm.getnOptionByCTS(CharacterTemporaryStat.RideVehicle) == 1930000: # Riena Skiff used in Riena Strait
        sm.removeCTS(CharacterTemporaryStat.RideVehicle)
    sm.warp(141060000,0)
sm.dispose()

