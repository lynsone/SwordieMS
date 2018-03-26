def init():
	warp = True
	fieldID = sm.getFieldID()
	if fieldID == 101050010:
		map = 101050000
		portal = 7
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
	else:
		sm.chat("(Portal) This script (out00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	if warp:
		sm.warp(map, portal)
		sm.dispose()

def action(response, answer):
	if response == 1:
		sm.clearPartyInfo()
		sm.warpPartyOut(271040000)
	sm.dispose()