# Portal to Manon
if sm.hasQuest(1451) or sm.hasQuest(1453) or sm.hasQuest(1455) or sm.hasQuest(1457) or sm.hasQuest(1459):
    if sm.checkParty():
        if sm.getPartySize() > 1:
            sm.chat("Please be in a party of one.")
            sm.dispose()
        sm.clearPartyInfo(924000200)
        sm.warpPartyIn(924000200)
    sm.dispose()
sm.warp(240020401, 3)
