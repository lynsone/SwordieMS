def init():
	currentMap = sm.getFieldID()
	if currentMap == 101020400:
		map = 101020401
		portal = 10
		
	elif currentMap == 101050000:
		map = 101050000
		portal = 9
		
	elif currentMap == 106030600:
		map = 106030600
		portal = 3
		
	else:	
		map = 100040000
		portal = 3
	
	sm.warp(map, portal)
	sm.dispose()