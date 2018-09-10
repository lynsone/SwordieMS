# 141060000
from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

# Ride Vehicle Check
if sm.getnOptionByCTS(CharacterTemporaryStat.RideVehicle) == 1930000: # Riena Skiff used in Riena Strait
    sm.removeCTS(CharacterTemporaryStat.RideVehicle)

sm.warp(141000000, 1) # Middle of Strait : Glacial Observatory
if sm.hasQuestCompleted(32170):
    sm.hideNpcByTemplateId(1510007, False)
sm.dispose()
