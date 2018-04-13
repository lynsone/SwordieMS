def init():
	fieldID = sm.getFieldID()
	if fieldID == 103000000:
		map = 103000003
		portal = 3
	elif fieldID == 863010100:
		map = 863010400
		portal = 2
	else:
		sm.chat("(Portal) This script (in03.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	
	sm.warp(map, portal)