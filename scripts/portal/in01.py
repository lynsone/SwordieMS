def init():
    warp = True
    fieldID = sm.getFieldID()
    if fieldID == 220070400:
        map = 922020000
        portal = 0
    elif fieldID == 222020400:
        map = 300000100
        portal = 1
    elif fieldID == 104020100:
        map = 104020120
        portal = 2
    else:
        sm.chat("(Portal) This script (in00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        map = sm.getChr().getField().getReturnMap()
        portal = 0
    if warp:
        sm.warp(map, portal)
        sm.dispose()