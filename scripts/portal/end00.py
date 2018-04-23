def init():
    warp = True
    currentMap = sm.getFieldID()
    if currentMap ==  200090300:
        map = 250000100
        portal = 0
    elif currentMap == 200090310:
        map = 200000141
        portal = 0
    if warp:
        sm.warp(map, portal)
    sm.dispose()
