field = {
    141060000 : 141050000   #Middle of Strait : Glacier Cutter Base
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