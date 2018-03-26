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
		
	elif currentMap == 106030100:
		map = 106030000
		portal = 2
		
	elif currentMap == 106030200:
		map = 106030300
		portal = 2
	
	elif currentMap == 106030501:
		map = 106030600
		portal = 2
	elif currentMap == 271030600:
		map = 271040000
		portal = 2
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
	if response == 1:
		if sm.getParty() is None:
			sm.sendSayOkay("Please create a party before going in.")
		elif not sm.isPartyLeader():
			sm.sendSayOkay("Please have your party leader enter if you wish to face Cygnus.")
		elif sm.checkParty():
			sm.warpParty(271040100)
	sm.dispose()