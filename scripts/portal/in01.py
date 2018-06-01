if sm.getFieldID() == 863010000:
    def init():
        sm.sendAskYesNo("Would you like to battle Gollux?")

    def action(response, answer):
        if response == 1:
            if sm.getParty() is None:
                sm.sendSayOkay("Please create a party before going in.")
            elif not sm.isPartyLeader():
                sm.sendSayOkay("Please have your party leader enter if you wish to face Gollux.")
            elif sm.checkParty():
                sm.warpParty(863010100)
        sm.dispose()




else:
    def init():
        warp = True
        fieldID = sm.getFieldID()
        if fieldID == 220070400:
            map = 922020000
            portal = 0
        elif fieldID == 222020400:
            map = 300000100
            portal = 1
        elif fieldID == 104020100:
            map = 104020120
            portal = 2
        elif fieldID == 130020000: # Entrance to Drill Hall
            if sm.hasQuest(20873): # WA 2nd job quest
                map = 913001000
                portal = 0
            else:
                sm.chat("Sorry, only knights looking to job advance to the second job may enter here.")
                warp = False
        else:
            sm.chat("(Portal) This script (in01.py) is not coded for this map. (ID: " + str(fieldID) + ")")
            map = sm.getChr().getField().getReturnMap()
            portal = 0
            warp = False
        if warp:
            sm.warp(map, portal)
        sm.dispose()