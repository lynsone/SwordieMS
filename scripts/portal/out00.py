def init():
    warp = True
    fieldID = sm.getFieldID()

    if fieldID == 101050010:
        map = 101050000
        portal = 1
    elif fieldID == 106030200:
        map = 106030000
        portal = 1
    elif fieldID == 310000010:
        map = 310000000
        portal = 15
    elif fieldID == 200000301:
        map = 200000300
        portal = 11
    elif fieldID == 106030302:
        map = 106030102
        portal = 1
    elif fieldID == 104020120:
        map = 104020100
        portal = 15
    elif fieldID == 350060300:
        map = 310070490
        portal = 4
    elif fieldID == 271040100:
        sm.sendAskYesNo("Are you sure you want to leave?")
        warp = False
    elif fieldID == 271040000:
        map = 271030600
        portal = 0
    elif fieldID == 866000220:
        map = 865000000
        portal = 0
    elif fieldID == 863010220:
        map = 863010100
        portal = 9
    elif fieldID == 863010300:
        map = 863010100
        portal = 7
    elif fieldID == 863010310:
        map = 863010300
        portal = 1
    elif fieldID == 863010400:
        map = 863010100
        portal = 6
    elif fieldID == 863010410:
        map = 863010400
        portal = 1
    elif fieldID == 863010230:
        map =863010220
        portal = 2
    elif fieldID == 863010240:
        map =863010210
        portal = 2
    elif fieldID == 863010500:
        map = 863010240
        portal = 0
    elif fieldID == 863010600:
        map = 863010700
        portal = 0
    elif fieldID == 863010430:
        map = 863010420
        portal = 3
    elif fieldID == 863010330:
        map = 863010320
        portal = 3
    elif fieldID == 930100500:
        map = 930100000
        portal = 0
    elif fieldID == 221023300:
        map = 221023200
        portal = 1
    elif fieldID == 223010110:
        map = 223010100
        portal = 3
    elif fieldID == 240010102:
        map = 240010101
        portal = 0
    elif fieldID == 260010601:
        map = 260010600
        portal = 0
    elif fieldID == 106030200: #castle corridor 1 mid portal does not respond to script
        map = 106030400
        portal = 1
    elif fieldID == 106030402:
        map = 106030101
        portal = 1


    elif fieldID == 141010000 or fieldID == 141020000 or fieldID == 141040000 or fieldID == 141050000: # [Riena Strait] Ice Station 1  or [Riena Strait] Ice Station 2  or  [Riena Strait] Barbara's House
        sm.openNPC(1510006)

        #ToT -> Leafre START
    elif fieldID == 270000100: # Time Lane : ToT
        map = 200090510 # Leafre -> ToT  Dragon Flight  2nd Map
        portal = 0
        sm.useItem(2210016)

        #ToT -> Leafre END
    elif fieldID == 271040000:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False
    elif fieldID == 223030210:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False
    elif fieldID == 272030400:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False

    #From CQueen
    elif fieldID == 105200310:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False

    #From Pierre
    elif fieldID == 105200210:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False

    #From VonBon
    elif fieldID == 105200110:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False

    #From Vellum
    elif fieldID == 105200410:
        sm.sendAskYesNo("Would you like to leave?")
        warp = False



        #fm
    elif fieldID == 910000000:
        oldFieldID = sm.getReturnField()
        if oldFieldID == 0 or oldFieldID == 910000000:
            sm.chat("(Portal) Cannot find your previous map ID, warping to Henesys.")
            map = 100000000
            portal = 0
        else:
            map = oldFieldID
            portal = 0
    else:
        sm.chat("(Portal) This script (out00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        map = sm.getChr().getField().getReturnMap()
        portal = 0
    if warp:
        sm.warp(map, portal)
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