def init():
	fieldID = sm.getFieldID()
	warp = True
	if fieldID == 401050000:
		map = 401050001
		portal = 0
	elif fieldID == 401040001:
		map = 401040000
		portal = 3
	else:
		sm.chat("(Portal) This script (mid00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	if warp:
		sm.warp(map, portal)
		sm.dispose()