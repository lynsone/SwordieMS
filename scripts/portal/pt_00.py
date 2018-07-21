from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

field = {
    141060000 : 141000000   # Middle of Strait : Glacial Observatory
}

portal = {
    141060000 : 1
}

def init():
    currentmap = sm.getFieldID()
    warp = True

    if currentmap == 141060000:
        # Ride Vehicle Check
        if sm.getnOptionByCTS(CharacterTemporaryStat.RideVehicle) == 1930000: # Riena Skiff used in Riena Strait
            sm.removeCTS(CharacterTemporaryStat.RideVehicle)

    if warp:
        sm.warp(field[currentmap], portal[currentmap])
        sm.dispose()