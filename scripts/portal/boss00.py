if sm.getFieldID() == 105200000:
	def init():
		sm.sendAskYesNo("Would you like to fight Pierre?")

	def action(response, answer):
		if response == 1:
			if sm.getParty() is None:
				sm.sendSayOkay("Please create a party before going in.")
			elif not sm.isPartyLeader():
				sm.sendSayOkay("Please have your party leader enter if you wish to face Pierre.")
			elif sm.checkParty():
				sm.warpPartyIn(105200200) # West Garden
		sm.dispose()

else:

	def init():
		warp = True
		fieldID = sm.getFieldID()
		if fieldID == 230040410:
			map = 230040420
			portal = 2
		elif fieldID == 240020100:
			map = 240020101
			portal = 3
		elif fieldID == 240020400:
			map = 240020401
			portal = 3
		else:
			sm.chat("(Portal) This script (boss00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
			map = sm.getChr().getField().getReturnMap()
			portal = 0
		if warp:
			sm.warp(map, portal)
			sm.dispose()

