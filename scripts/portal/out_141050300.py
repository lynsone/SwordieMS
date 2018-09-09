# 141050300 - Out

if sm.checkParty() and sm.getPartySize() == 1:
    sm.warpPartyOut(141050200)
else:
    sm.sendSayOkay("You have to be in a party of 1 to leave.")
sm.dispose()
