def init():
	if sm.getFieldID() == 106030200:
		map = 106030300
		portal = 2
	else:
		map = 101050100
		portal = 2
	
	sm.warp(map, portal)
	sm.dispose()