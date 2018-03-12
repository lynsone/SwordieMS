def init():
    map = sm.getFieldID()
    if map == 401000002: # Heliseum : Transitional Dimensional Door
        sm.warp(401000000)
        sm.dipose()
    else:
        sm.chat("Unhandled portal from script " + os.path.basename(__file__) + ", on map " + map + ".")
        sm.dipose()