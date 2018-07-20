field = {

}

portal = {

}

def init():
    currentmap = sm.getFieldID()
    warp = True

    if currentmap == 141050200:
        warp = False
        if sm.checkParty() and sm.getPartySize() == 1:
            sm.warpPartyIn(141050300)
        else:
            sm.sendSayOkay("You have to be in a party of 1 to enter.")
        sm.dispose()

    if warp:
        sm.warp(field[currentmap], portal[currentmap])
        sm.dispose()