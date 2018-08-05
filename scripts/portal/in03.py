def init():
	warp = True
	fieldID = sm.getFieldID()

	if fieldID == 103000000:
		map = 103000003
		portal = 3
	elif fieldID == 863010100:
		map = 863010400
		portal = 2
	elif fieldID == 951000000:
		warp = False
		sm.setSpeakerID(9071006)
		sm.sendSayOkay("Monster Park Extreme is off limits at the moment.")
		sm.dispose()
	elif fieldID == 310000000: #Edel
		map = 310000003 # Edel Hair Salon
		portal = 1
	else:
		sm.chat("(Portal) This script (in03.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0

	if warp:
		sm.warp(map, portal)
		sm.dispose()