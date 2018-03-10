def init():
	if sm.getMap() == 100000201:	#Archers
		map = 100000205
		portal = 2
	
	if sm.getMap() == 103000003:	#Thieves
		map = 103000008
		portal = 1
	
	if sm.getMap() == 102000003:	#Warriors
		map = 102000004
		portal = 1
		
	if sm.getMap() == 101000003:	#Mages
		map = 101000004 
		portal = 2
	
	if sm.getMap() == 120000101:	#Pirates

		sm.chatBlue("[WIP] no MapID for Hall of Pirates")
		
	if sm.getMap() != 120000101:
		sm.warp(map, portal)
	sm.dispose()