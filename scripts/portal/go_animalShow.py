# 223030200
def init():
    sm.sendAskYesNo("Would you like to battle scarlion and targa?")

def action(response, answer):
    currentMap = sm.getFieldID()
    if response == 1:
        if sm.getParty() is None:
            sm.sendSayOkay("Please create a party before going in.")
        elif not sm.isPartyLeader():
            sm.sendSayOkay("Please have your party leader enter the portal.")
        elif sm.checkParty():
            sm.warpPartyIn(223030210)
    sm.dispose()