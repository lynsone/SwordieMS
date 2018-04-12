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
	elif currentMap == 230030200:
		map = 224000001
		portal = 0
	elif currentMap == 270000000:
		map = 272000000
		portal = 1
	elif currentMap == 272000100:
		map = 272000200
		portal = 1
	elif currentMap == 272000200:
		map = 272000300
		portal = 1
	elif currentMap == 272000300:
		map = 272000310
		portal = 1
	elif currentMap == 272000310:
		map = 272000400
		portal = 1
	elif currentMap == 272000500:
		map = 272000600
		portal = 1
	elif currentMap == 272010000:
		map = 272010100
		portal = 1
	elif currentMap == 272010100:
		map = 272010200
		portal = 1
	elif currentMap == 272020100:
		map = 272020110
		portal = 1
	elif currentMap == 106030201:
		map = 106030210
		portal = 4
	else:
		map = sm.getChr().getField().getReturnMap()
		portal = 0
	
	sm.warp(map, portal)
	sm.dispose()