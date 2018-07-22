field = {
    101050010 : 101050000,
    106030200 : 106030000,
    310000010 : 310000000,
    200000301 : 200000300,
    200000300 : 100000000,
    106030302 : 106030102,
    104020120 : 104020100,
    350060300 : 310070490,
    271040000 : 271030600,
    866000220 : 865000000,
    863010220 : 863010100,
    863010300 : 863010100,
    863010310 : 863010300,
    863010400 : 863010100,
    863010410 : 863010400,
    863010230 : 863010220,
    863010240 : 863010210,
    863010500 : 863010240,
    863010600 : 863010700,
    863010430 : 863010420,
    863010330 : 863010320,
    930100500 : 930100000,
    221023300 : 221023200,
    223010110 : 223010100,
    240010102 : 240010101,
    260010601 : 260010600,
    106030402 : 106030101,
    270000100 : 200090510, # ToT : Dragon Flight-2nd Map
    865020051 : 865020300, # Canal Battle Ground 5 : Canal 4
}

portal = {
    101050010 : 1,
    106030200 : 1,
    310000010 : 15,
    200000301 : 11,
    200000300 : 2,
    106030302 : 1,
    104020120 : 15,
    350060300 : 4,
    271040000 : 0,
    866000220 : 0,
    863010220 : 9,
    863010300 : 7,
    863010310 : 1,
    863010400 : 6,
    863010410 : 1,
    863010230 : 2,
    863010240 : 2,
    863010500 : 0,
    863010600 : 0,
    863010430 : 3,
    863010330 : 3,
    930100500 : 0,
    221023300 : 1,
    223010110 : 3,
    240010102 : 0,
    260010601 : 0,
    106030402 : 1,
    270000100 : 0,
    865020051 : 0,
}

def init():
    warp = True
    fieldID = sm.getFieldID()


    # Dojo Floors
    if fieldID / 10000 == 92507:
        warp = False
        if sm.mobsPresentInField():
            sm.chat("Eliminate the boss before continuing")
        else:
            sm.teleportToPortal(6)
        sm.dispose()

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

    # CQueen
    elif fieldID == 105200310:
        sm.sendAskYesNo("Would you like to leave?")
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

    else:
        sm.chat("(Portal) This script (out00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        sm.dispose()

    if warp:
        sm.warp(field[fieldID], portal[fieldID])
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