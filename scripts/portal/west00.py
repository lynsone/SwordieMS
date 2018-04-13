def init():
    warp = True
    fieldID = sm.getFieldID()
	if fieldID == 106030200:
		map = 106030300
		portal = 2
	elif fieldID == 401060000:
		map = 401053002
		portal = 2
	elif fieldID == 865020000:
		map = 865000000
		portal = 10
	elif fieldID == 865020100:
		map = 865020000
		portal = 4
	elif fieldID == 863010420:
		map = 863010500
		portal = 5
	elif fieldID == 863010500:
		map = 863010320
		portal = 1
	elif fieldID == 863010330:
		map = 863010500
		portal = 0
    elif fieldID == 101050000:
        map = 101050100
        portal = 2
    elif fieldID == 272000000:
        map = 270000000
        portal = 6
    elif fieldID == 272000600:
        map = 272000500
        portal = 2
    elif fieldID == 272010000:
        map = 272000600
        portal = 2
    elif fieldID == 272010200:
        map = 272010100
        portal = 2
    elif fieldID == 106030211:
        map = 106030210
        portal = 2
    else:
        smc.chat("(portal) This script (west00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        map = sm.getChr().getField().getReturnMap()
        portal = 0

    if warp:
        sm.warp(map, portal)
        sm.dispose()