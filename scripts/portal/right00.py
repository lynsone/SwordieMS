def init():
    map = sm.getFieldID()
    warp=True
    if map == 401000002: # Heliseum : Transitional Dimensional Door
        map = 401000000
        portal = 1
    elif map == 401030600:
        map = 401040001
        portal = 1
    if warp:
            sm.warp(map, portal)
            sm.dispose()
    else:
        sm.chat("Unhandled portal from script " + os.path.basename(__file__) + ", on map " + map + ".")
        sm.dipose()