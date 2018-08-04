
from net.swordie.ms.constants import GameConstants

def init():
	warp = True
	fieldID = sm.getFieldID()
	if fieldID == 930100400:
		map = 930100500
		portal = 0

	# Party Quest - Lord Pirate PQ
	elif fieldID / 10000 == 92510:

		#First Map  of the Lord Pirate PQ
		if fieldID == 925100000:
			warp = False
			if sm.getReactorQuantity() > 7 and sm.mobsPresentInField():
				sm.chat("The portal is not opened.")
				sm.dispose()
			else:
				sm.warpPartyIn(sm.getFieldID() + 100)

		#Second Map  of the Lord Pirate PQ
		elif fieldID == 925100100:
			warp = False

			if int(sm.getQRValue(GameConstants.LORD_PIRATE_QUEST)) < 3:
				sm.chat("The portal is not opened.")
				sm.dispose()
			else:
				sm.warpPartyIn(sm.getFieldID() + 100)

		#Third Map & Fourth Map  of the Lord Pirate PQ
		elif fieldID == 925100200 or fieldID == 925100300:
			warp = False
			if sm.getReactorQuantity() > 8 or sm.mobsPresentInField(): # due to invisible reactor hidden on the map
				sm.chat("The portal is not opened.")
				sm.dispose()
			else:
				sm.warpPartyIn(sm.getFieldID() + 100)

		#Fifth Map  of the Lord Pirate PQ
		elif fieldID == 925100400:
			warp = False
			if sm.getReactorQuantity() > 1:
				sm.chat("The portal is not opened.")
			else:
				sm.warpPartyIn(sm.getFieldID() + 100) #Boss Map of Lord Pirate PQ
			sm.dispose()


		map = fieldID + 100
		portal = 0



	# Party Quest - Escape! PQ
	elif fieldID / 10000 == 92116:

		# Hidden Street : Path to the Aerial Prison
		if fieldID == 921160200:
			warp = False
			if sm.mobsPresentInField():
				sm.chat("The portal is not opened.")
				sm.dispose()
			else:
				warp = True

		# Dark Tower 1
		elif fieldID == 921160300:
			warp = False
			if sm.mobsPresentInField():
				sm.chat("The portal is not opened.")
				sm.dispose()
			else:
				warp = True

		# Hidden Street : A Secret Door to the Aerial Prison
		elif fieldID == 921160400:
			warp = False
			if sm.mobsPresentInField():
				sm.chat("The portal is not opened.")
				sm.dispose()
			else:
				warp = True

		# Hidden Street : Aerial Prison
		elif fieldID == 921160600:
			warp = False
			if sm.getReactorQuantity() > 1: # due to invisible reactor hidden on the map
				sm.chat("Unlock all the prison doors.")
				sm.dispose()
			else:
				warp = True

		map = fieldID + 100
		portal = 0



	# Monster Park
	elif fieldID / 1000000 == 952 or fieldID / 1000000 == 953 or fieldID / 1000000 == 954:
		map = fieldID + 100
		portal = 0

	# To Crimson Queen
	elif fieldID == 105200300:
		if sm.mobsPresentInField():
			sm.chat("Eliminate all monster before proceeding.")
		else:
			map = 105200310
			portal = 0

	# To Pierre
	elif fieldID == 105200200:
		if sm.mobsPresentInField():
			sm.chat("Eliminate all monster before proceeding.")
		else:
			map = 105200210
			portal = 0

	# To VonBon
	elif fieldID == 105200100:
		if sm.mobsPresentInField():
			sm.chat("Eliminate all monster before proceeding.")
		else:
			map = 105200110
			portal = 0

	# To VonBon
	elif fieldID == 105200400:
		if sm.mobsPresentInField():
			sm.chat("Eliminate all monster before proceeding.")
		else:
			map = 105200410
			portal = 0

	else:
		sm.chat("(Portal) This script (next00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
		map = sm.getChr().getField().getReturnMap()
		portal = 0

	if warp:
		sm.warp(map, portal)
		sm.dispose()