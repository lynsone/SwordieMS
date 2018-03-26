def init():
    sm.sendAskYesNo("Would you like to fight magnus?")

def action(response, answer):
    #sm.sendSay("Response was " + str(response) + "\r\rAnswer was " + str(answer))
    if response == 1:
        if sm.getParty() is None:
            sm.sendSayOkay("Please create a party before going in.")
        elif not sm.isPartyLeader():
            sm.sendSayOkay("Please have your party leader enter if you wish to face Magnus.")
        elif sm.checkParty():
            sm.warpParty(401060300)
    sm.dispose()
