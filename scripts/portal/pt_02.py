field = {
    141060000 : 141020000   #Middle of Strait : Ice Station 2
}

portal = {
    141060000 : 0
}

def init():
    currentmap = sm.getFieldID()
    warp = True

    if warp:
        sm.warp(field[currentmap], portal[currentmap])
        sm.dispose()