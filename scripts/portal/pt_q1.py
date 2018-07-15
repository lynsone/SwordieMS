field = {
    141060000 : 141010400   #Middle of Strait : Nora's Cove
}

portal = {
    141060000 : 0
}

def init():
    currentmap = sm.getFieldID()
    warp = True

    if currentmap == 141060000:
        if not sm.hasQuest(32170):
            warp = False
            sm.dispose()

    if warp:
        sm.warp(field[currentmap], portal[currentmap])
        sm.dispose()