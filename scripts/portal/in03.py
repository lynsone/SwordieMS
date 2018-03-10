def init():
	if sm.getMap() == 103000000:
		map = 103000003
		portal = 3
	else:
		map = 104000004
		portal = 1
	
	sm.warp(map, portal)