fields = {
	101020400 : [101020401, 10],
	101050000 : [101050000, 9],
	106030600 : [106030600, 3],
	230030200 : [224000001, 0],
	270000000 : [272000000, 1],
	272000100 : [272000200, 1],
	272000200 : [272000300, 1],
	272000300 : [272000310, 1],
	272000310 : [272000400, 1],
	272000500 : [272000600, 1],
	272010000 : [272010100, 1],
	272010100 : [272010200, 1],
	272020100 : [272020110, 1],
	106030201 : [106030210, 4],
	865000000 : [865020000, 3],
	865020000 : [865020100, 3],
	865030200 : [865030201, 2],
	863010500 : [863010420, 2],
	863010320 : [863010500, 1],
	863010430 : [863010500, 0],
	100020200 : [100040000, 3],
	211042200 : [211042300, 0],
	310040200 : [310050000, 1],
	101050100 : [101050000, 9],
}

def init():
	currentMap = sm.getFieldID()
	warp = True


	if currentMap == 350060160: # Black Heaven Core (Lotus Stage 1)
		warp = False
		sm.teleportInField(3) #portal Id
		sm.dispose()

	if currentMap not in fields:
		sm.chat("(Portal - east00) This script has currently not been coded for this map. Map ID "+ currentMap)
		warp = False

	if warp:
		sm.warp(fields[currentMap][0], fields[currentMap][1])
	sm.dispose()