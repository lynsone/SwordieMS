# 807300110
inBattle = sm.getFieldID() == 807300110
if inBattle:
    response = sm.sendAskYesNo("Would you like to leave?")
else:
    response = sm.sendAskYesNo("Would you like to battle Ranmaru?")

if inBattle:
    if response == 1:
        sm.clearPartyInfo()
        sm.warpPartyOut(807300100)
    else:
        if sm.getParty() is None:
            sm.sendSayOkay("Please create a party before going in.")
        elif not sm.isPartyLeader():
            sm.sendSayOkay("Please have your party leader enter if you wish to face Ranmaru.")
        elif sm.checkParty():
            sm.warpPartyIn(807300110)
sm.dispose()
