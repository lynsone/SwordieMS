# 271040000 (outside) / 271040100 (inside)
from net.swordie.ms.scripts import ScriptType

inBattle = sm.getFieldID() == 271040100
def init():
    if inBattle: # in battle
        sm.sendAskYesNo("Are you sure you want to leave?")
    else:
        sm.sendAskYesNo("Would you like to battle cygnus?")

def action(response, answer):
    if response == 1:
        if inBattle:
            sm.stopEventsByScriptType(ScriptType.FIELD) #Stops the FixedRate Event from the Field Script
            sm.clearPartyInfo(271040000)
            sm.dispose()
        else:
            if sm.getParty() is None:
                sm.sendSayOkay("Please create a party before going in.")
            elif not sm.isPartyLeader():
                sm.sendSayOkay("Please have your party leader enter to battle Cygnus.")
            elif sm.checkParty():
                sm.warpPartyIn(223030210)
        sm.dispose()