field = {
    141060000 : 141010000   #Middle of Strait : Ice Station 1
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