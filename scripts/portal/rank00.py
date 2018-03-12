def init():
	currentMap = sm.getFieldID()
	if currentMap == 100000201:	#Archers
		map = 100000205
		portal = 2
	
	if currentMap == 103000003:	#Thieves
		map = 103000008
		portal = 1
	
	if currentMap == 102000003:	#Warriors
		map = 102000004
		portal = 1
		
	if currentMap == 101000003:	#Mages
		map = 101000004 
		portal = 2
	
	if currentMap == 120000101:	#Pirates

		sm.chatBlue("[WIP] no MapID for Hall of Pirates")
		
	if currentMap != 120000101:
		sm.warp(map, portal)
	sm.dispose()