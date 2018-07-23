def init():
    fieldID = sm.getFieldID()
    warp = True

    if fieldID / 1000000 == 952 or fieldID / 1000000 == 953 or fieldID / 1000000 == 954:
        map = 951000000
        portal = 0

    else:
        map = fieldID
        portal = 0

    if warp:
        sm.warp(map, portal)
        sm.dispose()