def init():
	currentMap = sm.getFieldID()
	if currentMap == 103030100:
		sm.chatRed("There seems to be a mysterious presence blocking you from entering.")
	
	elif currentMap == 102010100:
		sm.chatRed("There seems to be a mysterious presence blocking you from entering.")
	
	elif currentMap == 103020000:
		map = 103020100
		portal = 2
	
	elif currentMap == 103020310: 
		map = 103020320
		portal = 2
		
	elif currentMap == 106030100:
		map = 106030000
		portal = 2
		
	elif currentMap == 106030200:
		map = 106030300
		portal = 2
	
	elif currentMap == 106030501:
		map = 106030600
		portal = 2
	else :	
		map = 100020401
		portal = 1
	

	sm.warp(map, portal) 
	sm.dispose()	