def init():
	warp = True

	if sm.getFieldID() == 350060160: # Black Heaven Core (Lotus Stage 1)
		warp = False
		sm.teleportInField(4) #Portal Id
		sm.dispose()

	else:
		map = 310070230
		portal = 15


	if warp:
		sm.warp(map, portal)
		sm.dispose()