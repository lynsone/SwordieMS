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

