def init():
	if sm.getFieldID() == 106030200:
		map = 106030300
		portal = 2
	elif sm.getFieldID() == 401060000:
		map = 401053002
		portal = 2
	else:
		sm.chat("(Portal) This script (out00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	
	sm.warp(map, portal)
	sm.dispose()