field = {

}

portal = {

}

def init():
    currentmap = sm.getFieldID()
    warp = True

    if currentmap == 141050300:
        warp = False
        if sm.checkParty() and sm.getPartySize() == 1:
            sm.warpPartyOut(141050200)
        else:
            sm.sendSayOkay("You have to be in a party of 1 to leave.")
        sm.dispose()

    if warp:
        sm.warp(field[currentmap], portal[currentmap])
        sm.dispose()