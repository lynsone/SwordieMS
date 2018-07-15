field = {
    141060000 : 141040000   #Middle of Strait : Barbara's House
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