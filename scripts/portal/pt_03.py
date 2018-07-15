field = {
    141060000 : 141030000   #Middle of Strait : Ice Station 3
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