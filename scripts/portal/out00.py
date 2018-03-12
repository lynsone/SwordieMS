def init():
	if sm.getFieldID() == 101050010:
		map = 101050000
		portal = 7
		
	elif sm.getFieldID() == 106030200:
		map = 106030000
		portal = 1
		
	elif sm.getFieldID() == 106030302:
		map = 106030102
		portal = 1
	
	elif sm.getFieldID() == 350060300:
		map = 310070490
		portal = 4
		
	else:
		map = 104000000
		portal = 6
	
	sm.warp(map, portal)
	sm.dispose()