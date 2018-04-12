def init():
    fieldID = sm.getFieldID()
    warp = True
    if fieldID == 863100007:
        map = 863100100
        portal = 2

    else:
        sm.chat("(Portal) This script (west000.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        map = sm.getChr().getField().getReturnMap()
        portal = 0
    if warp:
        sm.warp(map, portal)
        sm.dispose()