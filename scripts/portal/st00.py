def init():
    fieldID = sm.getFieldID()
    oldFieldID = 0 # TODO: Save maps
    warp = True
    # if oldFieldID == 0:
    #     map = 100000000
    #     portal = 0
    #     sm.chat("(Portal) Cannot find your previous map ID, warping to Henesys.")
    # elif oldFieldID == 910000000:
    #     sm.chat("(Portal) Cannot find your previous map ID, warping to Henesys.")
    #     map = 100000000
    #     portal = 0
    if fieldID == 932200300 or fieldID == 932200100:
        # 0 = start, 1 = before the ski jump, 2 = close to mid portal, 3 = flag, 4 = under lap portal,
        # 5 = bottom right, 6 = lap portal, 7 = mid, 8 = stuck in mid, ...
        sm.teleportInField(-1978, 2558)
        warp = False
        sm.dispose()
    else:
        sm.chat("(Portal) This script (st00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        warp = False

    if warp:
        sm.warp(map, portal)
        sm.dispose()