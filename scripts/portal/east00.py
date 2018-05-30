def init():
	currentMap = sm.getFieldID()
	warp = True
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
	elif currentMap == 865000000:
		map = 865020000
		portal = 3
	elif currentMap == 865020000:
		map = 865020100
		portal = 3
	elif currentMap == 865030200:
		map = 865030201
		portal = 2
	elif currentMap == 863010500:
		map = 863010420
		portal = 2
	elif currentMap == 863010320:
		map = 863010500
		portal = 1
	elif currentMap == 863010430:
		map = 863010500
		portal = 0
	elif currentMap == 100020200:
		map = 100040000
		portal = 3
	else:
		sm.chat("(Portal - east00) This script has currently not been coded for this map.")
		map = sm.getChr().getField().getReturnMap()
		portal = 0
		warp = False

	if warp:
		sm.warp(map, portal)
	sm.dispose()