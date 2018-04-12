def init():
	warp = True
	fieldID = sm.getFieldID()
	if fieldID == 100000000:
		map = 931050800
		portal = 0
	elif fieldID == 240000000:
	    map = 931050820
	    portal = 0
	elif fieldID == 400000000:
	    map = 931050810
	    portal = 0
	else:
		sm.chat("(Portal) This script (mistic00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	if warp:
		sm.warp(map, portal)
		sm.dispose()