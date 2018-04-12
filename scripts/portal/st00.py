def init():
    fieldID = sm.getFieldID()
    oldFieldID = sm.getOldFieldID()
    warp = True
    if oldFieldID == 0:
        map = 100000000
        portal = 0
        sm.chat("(Portal) Cannot find your previous map ID, warping to Henesys.")
    elif oldFieldID == 910000000:
        sm.chat("(Portal) Cannot find your previous map ID, warping to Henesys.")
        map = 100000000
        portal = 0
    else:
        #sm.chat("(Portal) This script (st00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        map = oldFieldID
        portal = 0
    if warp:
        sm.warp(map, portal)
        sm.dispose()