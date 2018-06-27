def init():
	currentmap = sm.getFieldID()
	if currentmap == 863010100:
		map = 863010220
		portal = 1
	elif currentmap == 310000000:
		if sm.hasQuest(23023): # 2nd job advancement for BaM
			sm.giveItem(4032737, 1)
		map = 310000010
		portal = 1
	else:
		map = 103000002
		portal = 8
	
	sm.warp(map, portal)
	sm.dispose()