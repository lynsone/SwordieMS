status = -1

from net.swordie.ms.constants import GameConstants
from net.swordie.ms.enums import QuestStatus
from net.swordie.ms.client.character.quest import Quest

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
        if not sm.getParty() is None:
            sm.sendSayOkay("Please leave your party to enter Monster Park.")
            sm.dispose()
        else:
            if sm.getChr().getLevel() < minLv or sm.getChr().getLevel() > maxLv:
                sm.sendSayOkay("You need to be between Level "+ str(minLv) +" and "+ str(maxLv) +" to enter.")
                sm.dispose()
            else:
                if sm.getMonsterParkCount() >= GameConstants.MAX_MONSTER_PARK_RUNS:
                    colour = "#r"
                else:
                    colour = "#b"
                string = "#eToday is #b"+ sm.getDay() +"#k.\r\nToday's Clear Count "+ colour +""+ str(sm.getMonsterParkCount()) +"/"+ str(GameConstants.MAX_MONSTER_PARK_RUNS) +"#k (per Maple Character)\r\n\r\nYou have #b"+ str(2) +"#k free clears left for today.\r\n\r\n#n#b"
                i = 0
                while i < len(maps):
                    string += "#L"+ str(i) +"#"+ maps[i][0] +"#l\r\n"
                    i += 1
                sm.sendNext(string)

    def action(response, answer):
        global status, selection
        status += 1

        if status == 0:
            if sm.getMonsterParkCount() >= GameConstants.MAX_MONSTER_PARK_RUNS:
                sm.sendSayOkay("I'm sorry, but you've used up all your clears for today.")
                sm.dispose()
            else:
                selection = answer
                sm.sendAskYesNo("#eToday is #b"+ sm.getDay() +"#k.\r\n\r\n"
                                "Selected Dungeon: #b"+ maps[selection][0] +"#k\r\n"
                                "Clearing the dungeon will use up #bone of your free clears#k \r\nfor today.\r\n\r\n"
                                "Would you like to enter the dungeon?")


        elif status == 1:
            if response == 1:
                sm.warpInstanceIn(maps[selection][1])
                sm.incrementMonsterParkCount()
                sm.createQuestWithQRValue(GameConstants.MONSTER_PARK_EXP_QUEST, "0")
            sm.dispose()



else:

    field = {
        220070400 : [922020000, 0],
        222020400 : [300000100, 1],
        104020100 : [104020120, 2],
        100000200 : [100000202, 5], # Henesys Park : Pet-Walking Road
        130020000 : [913001000, 0],
        865000000 : [865000002, 1],

        252010300 : [925120000, 0],
    }

    def init():
        warp = True
        fieldID = sm.getFieldID()

        if fieldID == 130020000:  # Entrance to Drill Hall
            if not sm.hasQuest(20873):  # WA 2nd job quest
                sm.chat("Sorry, only knights looking to job advance to the second job may enter here.")
                warp = False

        if fieldID == 252010300:  # Golden Temple Training Ground 4
            warp = False
            if sm.checkParty():
                sm.warpPartyIn(925120000) # SnowFro's Lair

        # Default
        if field[fieldID] is None:
            sm.chat("(Portal) This script (in01.py) is not coded for this map. (ID: " + str(fieldID) + ")")
            warp = False

        if warp:
            sm.warp(field[fieldID][0], field[fieldID][1])
        sm.dispose()