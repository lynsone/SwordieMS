def init():
	warp = True

	if sm.getFieldID() == 350060160: # Black Heaven Core (Lotus Stage 1)
		warp = False
		sm.teleportInField(5) #Portal Id
		sm.dispose()

	else:
		map = 101020407
		portal = 7


	if warp:
		sm.warp(map, portal)
		sm.dispose()