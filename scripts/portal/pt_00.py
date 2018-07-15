field = {
    141060000 : 141000000   #Middle of Strait : Glacial Observatory
}

portal = {
    141060000 : 1
}

def init():
    currentmap = sm.getFieldID()
    warp = True

    if warp:
        sm.warp(field[currentmap], portal[currentmap])
        sm.dispose()