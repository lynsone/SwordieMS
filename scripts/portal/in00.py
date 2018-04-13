def init():
	currentMap = sm.getFieldID()
	warp = True
	if currentMap == 103030100:
		sm.chatRed("There seems to be a mysterious presence blocking you from entering.")
	
	elif currentMap == 102010100:
		sm.chatRed("There seems to be a mysterious presence blocking you from entering.")
	
	elif currentMap == 103020000:
		map = 103020100
		portal = 2
	
	elif currentMap == 103020310: 
		map = 103020320
		portal = 2
	elif currentMap == 260010601:
		map = 915020100
		portal = 1
	elif currentMap == 915020100:
		map = 915020101
		portal = 1
	elif currentMap == 106030100:
		map = 106030000
		portal = 2
	elif currentMap == 106030200:
		map = 106030300
		portal = 2
	elif currentMap == 120041800:
	    map = 120041900
	    portal = 2
	elif currentMap == 106030501:
		map = 106030600
		portal = 2
	elif currentMap == 271030600:
		map = 271040000
		portal = 5
	elif currentMap == 863010300:
		map = 863010310
		portal = 1
	elif currentMap == 863010400:
		map = 863010410
		portal = 1
	elif currentMap == 863010220:
		map = 863010230
		portal = 1
	elif currentMap == 863010230:
		map = 863010240
		portal = 0
	elif currentMap == 863010210:
		map = 863010240
		portal = 0
	elif currentMap == 863010240:
		map = 863010500
		portal = 0
	elif currentMap == 863010500:
		map = 863010600
		portal = 0
	elif currentMap == 863010320:
		map = 863010330
		portal = 0
	elif currentMap == 863010420:
		map = 863010430
		portal = 0
	elif currentMap == 221023200:
		map = 221023300
		portal = 0
	elif currentMap == 223000000:
		map = 223010000
		portal = 1
	elif currentMap == 223010100:
		map = 223010110
		portal = 0
	elif currentMap == 200020001:
		map = 915020000
		portal = 2
	elif currentMap == 915020000:
		map = 915020001
		portal = 2
	elif currentMap == 915020200:
		map = 915020201
		portal = 2
	elif currentMap == 240010102:
		map = 915020200
		portal = 1

	elif currentMap == 223030200:
		sm.sendAskYesNo("Would you like to battle scarlion and targa?")
		warp = False

	elif currentMap == 271040000:
		sm.sendAskYesNo("Would you like to battle cygnus?")
		warp = False
	else:
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	if warp:
		sm.warp(map, portal)
		sm.dispose()

def action(response, answer):
	currentMap = sm.getFieldID()
	if response == 1:
		if sm.getParty() is None:
			sm.sendSayOkay("Please create a party before going in.")
		elif not sm.isPartyLeader():
			sm.sendSayOkay("Please have your party leader enter if you wish to face Cygnus.")
		elif sm.checkParty():
			if currentMap == 271040000:
				sm.warpParty(271040100)
			elif currentMap == 223030200:
				sm.warpParty(223030210)
	sm.dispose()