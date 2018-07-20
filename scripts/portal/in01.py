status = -1

if sm.getFieldID() == 863010000:
    # Gollux
    def init():
        sm.sendAskYesNo("Would you like to battle Gollux?")

    def action(response, answer):
        if response == 1:
            if sm.getParty() is None:
                sm.sendSayOkay("Please create a party before going in.")
            elif not sm.isPartyLeader():
                sm.sendSayOkay("Please have your party leader enter if you wish to face Gollux.")
            elif sm.checkParty():
                sm.warpPartyIn(863010100)
        sm.dispose()



elif sm.getFieldID() == 951000000:
    # Monster Park

    minLv = 115
    maxLv = 160

    maps = [
        ["Mossy Tree Forest (Lv.115-124", 953030000],
        ["Sky Forest Training Center (Lv.125-129)", 953040000],
        ["Secret Pirate Hideout (Lv.125-134)", 953060000],
        ["Otherworld Battlefield (Lv.135-144)", 953070000],
        ["Dangerously Isolated Forest (Lv.140-149)", 953080000],
        ["Forbidden Time (Lv.145-154)", 953050000],
        ["Clandestine Ruins (Lv.150-159)", 953090000],
    ]
    sm.setSpeakerID(9071004)

    def init():
        if sm.getParty() is None or sm.getPartySize() > 1:
            sm.sendSayOkay("You must be in a party of 1 to enter Monster Park.")
            sm.dispose()
        else:
            if sm.getChr().getLevel() < minLv or sm.getChr().getLevel() > maxLv:
                sm.sendSayOkay("You need to be between Level "+ str(minLv) +" and "+ str(maxLv) +" to enter.")
                sm.dispose()
            else:
                string = "#eToday is #b[Day]#k.\r\nToday's Clear Count #b"+ str(0) +"/7#k (Per Maple account)\r\n\r\nYou have #b"+ str(2) +"#k free clears left for today.\r\n\r\n#n#b"
                i = 0
                while i < len(maps):
                    string += "#L"+ str(i) +"#"+ maps[i][0] +"#l\r\n"
                    i += 1
                sm.sendNext(string)

    def action(response, answer):
        global status, selection
        status += 1

        if status == 0:
            selection = answer
            sm.sendAskYesNo("#eToday is #b[Day]#k.\r\n\r\n"
                            "Selected Dungeon: #b"+ maps[selection][0] +"#k\r\n"
                            "Clearing the dungeion will use up #bone of your free clears#k \r\nfor today.\r\n\r\n"
                            "Would you like to enter the dungeon?")

        elif status == 1:
            if response == 1:
                sm.warpPartyIn(maps[selection][1])
            sm.dispose()



else:

    field = {
        220070400 : 922020000,
        222020400 : 300000100,
        104020100 : 104020120,
        100000200 : 100000202, # Henesys Park : Pet-Walking Road
        130020000 : 913001000,
        865000000 : 865000002,
    }

    portal = {
        220070400 : 0,
        222020400 : 1,
        104020100 : 2,
        100000200 : 5,
        130020000 : 0,
        865000000 : 1,
    }

    def init():
        warp = True
        fieldID = sm.getFieldID()

        if fieldID == 130020000:  # Entrance to Drill Hall
            if not sm.hasQuest(20873):  # WA 2nd job quest
                sm.chat("Sorry, only knights looking to job advance to the second job may enter here.")
                warp = False

        # Default
        if field[fieldID] is None:
            sm.chat("(Portal) This script (in01.py) is not coded for this map. (ID: " + str(fieldID) + ")")
            warp = False

        if warp:
            sm.warp(field[fieldID], portal[fieldID])
        sm.dispose()