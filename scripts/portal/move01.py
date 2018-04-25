def init():
    warp = True
    currentMap = sm.getFieldID()
    if currentMap == 200090600:
        map = 310000010
        portal = 0
    elif currentMap == 200090701:
        map = 310000010
        portal = 0
    if warp:
        sm.warp(map, portal)
sm.dispose()