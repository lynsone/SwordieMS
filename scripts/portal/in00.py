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
	elif currentMap == 200000300:
		map = 200000301
		portal = 3
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
	elif currentMap == 222020000: # Ludi tower: Helios Tower <Library> (CoK 3rd job portal)
		if sm.hasQuest(20880): # 3rd job quest
			map = 922030400
			portal = 0
		else:
			sm.chat("Only knights looking to job advance to the third job may enter here.")
	elif currentMap == 270000000: # Time Lane: Three doors
		if sm.hasQuestCompleted(3500): # time lane quest
			map = 270010000
			portal = 3
		else:
			sm.chat("You have not completed the appropriate quest to enter here.")
	elif currentMap == 270010100: # Time Lane: Memory Lane 1
		if sm.hasQuestCompleted(3501): # time lane quest
			map = 270010110
			portal = 0
		else:
			sm.chat("You have not completed the appropriate quest to enter here.")
	elif currentMap == 270010200: # Time Lane: Memory Lane 2
		if sm.hasQuestCompleted(3502): # time lane quest
			map = 270010300
			portal = 0
		else:
			sm.chat("You have not completed the appropriate quest to enter here.")
	elif currentMap == 270010300: # Time Lane: Memory Lane 3
		if sm.hasQuestCompleted(3503): # time lane quest
			map = 270010400
			portal = 5
		else:
			sm.chat("You have not completed the appropriate quest to enter here.")
	elif currentMap == 270010400: # Time Lane: Memory Lane 4
		if sm.hasQuestCompleted(3504): # time lane quest
			map = 270010500
			portal = 0
		else:
			sm.chat("You have not completed the appropriate quest to enter here.")

	elif currentMap == 223030200:
		sm.sendAskYesNo("Would you like to battle scarlion and targa?")
		warp = False
	elif currentMap == 271040000:
		sm.sendAskYesNo("Would you like to battle cygnus?")
		warp = False
	else:
		sm.chat("(Portal - in00) This script isn't coded for this map.")
		map = sm.getChr().getField().getReturnMap()
		warp = False
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
				sm.warpPartyIn(271040100)
			elif currentMap == 223030200:
				sm.warpPartyIn(223030210)
	sm.dispose()