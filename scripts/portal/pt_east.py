field = {

}

portal = {

}

def init():
    currentmap = sm.getFieldID()
    warp = True

    if currentmap == 141000000:
        warp = False
        sm.openNPC(1510006)

    if warp:
        sm.warp(field[currentmap], portal[currentmap])
        sm.dispose()