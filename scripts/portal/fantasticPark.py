def init():
    fieldID = sm.getFieldID()
    warp = True
    if fieldID == 220000000:
        map = 223000000
        portal = 0

    else:
        sm.chat("(Portal) This script (fantasticPark.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        map = sm.getChr().getField().getReturnMap()
        portal = 0
    if warp:
        sm.warp(map, portal)
        sm.dispose()