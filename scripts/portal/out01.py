def init():
	warp = True
	currentmap = sm.getFieldID()
	if currentmap == 863010240:
		map = 863010230
		portal = 2
	elif currentmap == 200090020:
		map = 130000210
		portal = 0
	else:
		sm.chat("(Portal) This script (out01.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	if warp:
		sm.warp(map, portal)
	sm.dispose()