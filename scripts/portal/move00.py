def init():
    warp = True
    currentMap = sm.getFieldID()
    if currentMap == 200090710:
        map = 104020130
        portal = 0
    elif currentMap == 200090610:
        map = 200000100
        portal = 0
    if warp:
        sm.warp(map, portal)
sm.dispose()