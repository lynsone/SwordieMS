def init():
	fieldID = sm.getFieldID()
	warp = True
	if fieldID == 103050100:
		map = 103050200
		portal = 4
	elif fieldID == 863100002:
		map = 863100006
		portal = 2

	else:
		sm.chat("(Portal) This script (down00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	if warp:
		sm.warp(map, portal)
		sm.dispose()