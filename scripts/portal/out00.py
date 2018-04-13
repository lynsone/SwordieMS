def init():
	warp = True
	fieldID = sm.getFieldID()

	if fieldID == 101050010:
		map = 101050000
		portal = 1
	elif fieldID == 106030200:
		map = 106030000
		portal = 1
	elif fieldID == 106030302:
		map = 106030102
		portal = 1
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
	elif fieldID == 271040000:
		sm.sendAskYesNo("Would you like to leave?")
		warp = False
	elif fieldID == 223030210:
		sm.sendAskYesNo("Would you like to leave?")
		warp = False
	elif fieldID ==272030400:
		sm.sendAskYesNo("Would you like to leave?")
		warp = False

		#fm
	elif fieldID == 910000000:
		oldFieldID = sm.getOldFieldID()
		if oldFieldID == 0:
			sm.chat("(Portal) Cannot find your previous map ID, warping to Henesys.")
			map = 100000000
			portal = 0
		elif oldFieldID == 910000000:
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
		sm.clearPartyInfo()
		if fieldID == 271040100 | 271040000:
			sm.warpPartyOut(271040000)
			sm.dispose()
		elif fieldID == 223030210:
			sm.warpParty(223030200)
			sm.dispose()
		elif fieldID == 272030400:
			sm.warpParty(272030300)
			sm.dispose()
		else:
			sm.chat("(Portal) This script (out00.py) is not coded for the exit of this map. (ID: " + str(fieldID) + ")")
			map = sm.getChr().getField().getReturnMap()
			portal = 0
			sm.warpParty(map, portal)
			sm.dispose()