
from net.swordie.ms.scripts import ScriptType

field = {
    101050010 : [101050000, 1],
    106030200 : [106030000, 1],
    310000010 : [310000000, 15],
    200000301 : [200000300, 11],
    200000300 : [100000000, 0],
    106030302 : [106030102, 1],
    104020120 : [104020100, 15],
    350060300 : [310070490, 4],
    271040000 : [271030600, 0],
    866000220 : [865000000, 0],
    863010220 : [863010100, 9],
    863010300 : [863010100, 7],
    863010310 : [863010300, 1],
    863010400 : [863010100, 6],
    863010410 : [863010400, 1],
    863010230 : [863010220, 2],
    863010240 : [863010210, 2],
    863010500 : [863010240, 0],
    863010600 : [863010700, 0],
    863010430 : [863010420, 3],
    863010330 : [863010320, 3],
    930100500 : [930100000, 0],
    221023300 : [221023200, 1],
    223010110 : [223010100, 3],
    240010102 : [240010101, 0],
    260010601 : [260010600, 0],
    106030402 : [106030101, 1],
    270000100 : [200090510, 0], # ToT -> Dragon Flight-2nd Map
    211040401 : [211040300, 0], # Hidden Street -> Holy Ground at the Snowfield
    252030000 : [252020700, 0], # Entrance to Ravana's Altar -> Room of Suffering
    910150002 : [910150003, 0], # Path of the Glowcaves -> Blooming Forest  (Frozen Fairy Forest)

}

soloInstances = [

]

def init():
    sm.stopEventsByScriptType(ScriptType.FIELD) #Stops the FixedRate Event from the Field Script
    warp = True
    fieldID = sm.getFieldID()

    # Party Quest

    # Escape! - PQ
    if fieldID == 921160700: # Prison Guard Ani's Room (Escape! Boss Room)
        warp = False
        if sm.mobsPresentInField():
            sm.chat("The portal is not opened.")

        else:
            sm.warpPartyOut(910002000) # Party Quest Map
            sm.giveExp(sm.getPQExp()) #Gives player PQ exp
        sm.dispose()


    # Dojo Floors
    elif fieldID / 10000 == 92507:
        warp = False
        if sm.mobsPresentInField():
            sm.chat("Eliminate the boss before continuing")
        else:
            sm.teleportToPortal(6)
        sm.dispose()

    elif fieldID == 865020051: # Canal Battle Ground 5
        warp = False
        sm.warpInstanceOut(865020300) # Canal 4

    # Cygnus' Chamber
    elif fieldID == 271040100:
        sm.sendAskYesNo("Are you sure you want to leave?")
        warp = False

    # Riena Strait Portals
    elif fieldID == 141010000 or fieldID == 141020000 or fieldID == 141040000 or fieldID == 141050000:
        # [Riena Strait] Ice Station 1  or [Riena Strait] Ice Station 2  or  [Riena Strait] Barbara's House
        sm.openNpc(1510006)
        warp = False

    # ToT -> Leafre START
    elif fieldID == 270000100: # Time Lane : ToT
        sm.useItem(2210016)

    # Scarlion & Targa
    elif fieldID == 223030210:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False

    # Arkarium
    elif fieldID == 272030400:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False

    # Root Abyss Quest Line
    elif fieldID == 910700200: # Root Abyss : Colossal Root  (Quest Map)
        warp = False
        if sm.hasQuest(30002):
            sm.setPlayerAsSpeaker()
            sm.sendNext("I should tell that girl about the exit first.")
            sm.completeQuest(30002)
        else:
            sm.warp(105010200, 0) # Secret Swamp
        sm.dispose()

    # CQueen
    elif fieldID == 105200310:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False

    # Lotus
    elif fieldID == 350060200: # Lotus Stage 1
        if sm.getParty() is None:
            sm.warpInstanceOut(350060000) # Entrance Core
        else:
            sm.warpPartyOut(350060000) # Entrace Core
        sm.dispose()
        warp = False

    elif fieldID == 350060180: # Lotus Stage 2
        if sm.getParty() is None:
            sm.warpInstanceOut(350060000) # Entrance Core
        else:
            sm.warpPartyOut(350060000) # Entrace Core
        sm.dispose()
        warp = False

    elif fieldID == 350060160: # Lotus Stage 3
        sm.stopEventsByScriptType(ScriptType.FIELD) #Stops the FixedRate Event from the Field Script
        if sm.getParty() is None:
            sm.warpInstanceOut(350060000) # Entrance Core
        else:
            sm.warpPartyOut(350060000) # Entrace Core
        sm.dispose()
        warp = False

    # Pierre
    elif fieldID == 105200210:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False

    # VonBon
    elif fieldID == 105200110:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False

    # Vellum
    elif fieldID == 105200410:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False

    # FM
    elif fieldID == 910000000:
        warp = False
        oldFieldID = sm.getReturnField()
        if oldFieldID == 0 or oldFieldID == 910000000:
            sm.chat("(Portal) Cannot find your previous map ID, warping to Henesys.")
            map = 100000000
            portals = 0
        else:
            map = oldFieldID
            portals = 0
        sm.warp(map, portals)
        sm.dispose()

    elif not fieldID in field:
        sm.chat("(Portal) This script (out00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        warp = False
        sm.dispose()


    if warp:
        if fieldID in soloInstances:
            sm.warpInstanceOut(field[fieldID])
        else:
            sm.warp(field[fieldID][0], field[fieldID][1])
        sm.dispose()

def action(response, answer):
    fieldID = sm.getFieldID()
    if response == 1:
        if fieldID == 271040100 or fieldID == 271040000:
            sm.clearPartyInfo(271040000)
            sm.dispose()
        elif fieldID == 223030210:
            sm.clearPartyInfo(223030200)
            sm.dispose()
        elif fieldID == 272030400:
            sm.clearPartyInfo(272030300)
            sm.dispose()
        elif fieldID == 105200310:
            sm.clearPartyInfo(105200000)
            sm.dispose()
        elif fieldID == 105200210:
            sm.clearPartyInfo(105200000)
            sm.dispose()
        elif fieldID == 105200110:
            sm.clearPartyInfo(105200000)
            sm.dispose()
        elif fieldID == 105200410:
            sm.clearPartyInfo(105200000)
            sm.dispose()
        else:
            sm.chat("(Portal) This script (out00.py) is not coded for the exit of this map. (ID: " + str(fieldID) + ")")
            map = sm.getChr().getField().getReturnMap()
            portal = 0
            sm.warpPartyOut(map, portal)
            sm.dispose()