#Middle of the Strait | 141060000
from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat
mount = CharacterTemporaryStat.RideVehicle
shipMount = 1932102

def init():
    if sm.hasCTS(mount) and sm.getnOptionByCTS(mount) != shipMount:
        sm.removeCTS(mount)
    #sm.rideVehicle(shipMount)  TODO Uncomment once RideVehicle doesn't 38
    sm.dispose()