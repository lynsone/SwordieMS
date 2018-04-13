def init():
	currentmap = sm.getFieldID()
	if currentmap == 863010240:
		map = 863010230
		portal = 2
	else:
		sm.chat("(Portal) This script (out01.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	sm.warp(map, portal)
	sm.dispose()