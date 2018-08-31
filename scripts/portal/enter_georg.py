# 141050200 - Riena Strait boss

def init():
    if sm.checkParty() and sm.getPartySize() == 1:
        sm.warpPartyIn(141050300)
    else:
        sm.sendSayOkay("You have to be in a party of 1 to enter.")
    sm.dispose()