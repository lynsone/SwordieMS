# 141060000
from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

# Ride Vehicle Check
if sm.getnOptionByCTS(CharacterTemporaryStat.RideVehicle) == 1930000: # Riena Skiff used in Riena Strait
    sm.removeCTS(CharacterTemporaryStat.RideVehicle)

sm.warp(141050000, 0) # Middle of Strait : Glacial Observatory
sm.dispose()
