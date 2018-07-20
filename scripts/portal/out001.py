def init():
    currentMap = sm.getFieldID()

    if currentMap / 10000 == 92507 and not currentMap == 925074100:
        sm.warp(currentMap+100, 0)
        sm.dispose()

    elif currentMap == 925074100:
        sm.warp(925020003, 1)
        sm.dispose()

    else:
        sm.chat("This portal has not yet been coded  -  (out001)")
        sm.dispose()