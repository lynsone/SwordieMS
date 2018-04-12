def init():
    fieldID = sm.getFieldID()
    warp = True
    if fieldID == 223000000:
        map = 220000000
        portal = 12

    else:
        sm.chat("(Portal) This script (Ludi00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        map = sm.getChr().getField().getReturnMap()
        portal = 0
    if warp:
        sm.warp(map, portal)
        sm.dispose()