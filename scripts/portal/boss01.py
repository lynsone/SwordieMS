def init():
    sm.sendAskYesNo("Would you like to fight Von Bon?")

def action(response, answer):
    if response == 1:
        if sm.getParty() is None:
            sm.sendSayOkay("Please create a party before going in.")
        elif not sm.isPartyLeader():
            sm.sendSayOkay("Please have your party leader enter if you wish to face Von Bon.")
        elif sm.checkParty():
            sm.warpParty(105200100) #East Garden
    sm.dispose()
