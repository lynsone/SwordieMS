def init():
    fieldID = sm.getFieldID()
    warp = True
    if fieldID == 272000000:
        map = 272000100
        portal = 0

    else:
        sm.chat("(Portal) This script (crack00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        map = sm.getChr().getField().getReturnMap()
        portal = 0
    if warp:
        sm.warp(map, portal)
        sm.dispose()