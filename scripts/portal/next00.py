def init():
	warp = True
	fieldID = sm.getFieldID()
	if fieldID == 930100400:
		map = 930100500
		portal = 0
	else:
		sm.chat("(Portal) This script (next00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	if warp:
		sm.warp(map, portal)
		sm.dispose()

