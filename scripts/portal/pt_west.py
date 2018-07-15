def init():
	warp = True


	if sm.getFieldID() == 141030000:
		warp = False
		sm.openNPC(1510006)
	else:
		map = 240090801
		portal = 0

	if warp:
		sm.warp(map, portal)
		sm.dispose()