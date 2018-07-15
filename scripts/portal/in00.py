status = -1

if sm.getFieldID() == 951000000:
	#Monster Park

	minLv = 105
	maxLv = 115

	maps = [
		["Auto Security Area (Lv.105-114)", 953020000]
	]
	sm.setSpeakerID(9071004)

	def init():
		if sm.getParty() is None or sm.getPartySize() > 1:
			sm.sendSayOkay("You must be in a party of 1 to enter Monster Park.")
			sm.dispose()
		else:
			if sm.getChr().getLevel() < minLv or sm.getChr().getLevel() > maxLv:
				sm.sendSayOkay("You need to be between Level "+ str(minLv) +" and "+ str(maxLv) +" to enter.")
				sm.dispose()
			else:
				string = "#eToday is #b[Day]#k.\r\nToday's Clear Count #b"+ str(0) +"/7#k (Per Maple account)\r\n\r\nYou have #b"+ str(2) +"#k free clears left for today.\r\n\r\n#n#b"
				i = 0
				while i < len(maps):
					string += "#L"+ str(i) +"#"+ maps[i][0] +"#l\r\n"
					i += 1
				sm.sendNext(string)

	def action(response, answer):
		global status, selection
		status += 1

		if status == 0:
			selection = answer
			sm.sendAskYesNo("#eToday is #b[Day]#k.\r\n\r\n"
							"Selected Dungeon: #b"+ maps[selection][0] +"#k\r\n"
						 	"Clearing the dungeion will use up #bone of your free clears#k \r\nfor today.\r\n\r\n"
						 	"Would you like to enter the dungeon?")

		elif status == 1:
			if response == 1:
				sm.warpPartyIn(maps[selection][1])
			sm.dispose()




else:
	field = {
		240010500 : 240010501,
		103020000 : 103020100,
		200000300 : 200000301,
		103020310 : 103020320,
		260010601 : 915020100,
		915020100 : 915020101,
		106030100 : 106030000, # Mush Castle Castle Entrace : Banquet Hall
		106030200 : 106030300,
		120041800 : 120041900,
		106030501 : 106030600,
		271030600 : 271040000,
		863010300 : 863010310,
		863010400 : 863010410,
		863010220 : 863010230,
		863010230 : 863010240,
		863010210 : 863010240,
		863010240 : 863010500,
		863010500 : 863010600,
		863010320 : 863010330,
		863010420 : 863010430,
		221023200 : 221023300,
		223000000 : 223010000,
		223010100 : 223010110,
		200020001 : 915020000,
		915020000 : 915020001,
		915020200 : 915020201,
		240010102 : 915020200,
		200090510 : 270000100, # Dragon Flight 2nd Map : Temple of Time
		310050100 : 931000200,
		310060221 : 931000300,
		222020000 : 922030400,
		270000000 : 270010000,
		270010100 : 270010110,
		270010200 : 270010300,
		270010300 : 270010400,
		270010400 : 270010500,
	}

	portal = {
		240010500 : 1,
		103020000 : 2,
		200000300 : 3,
		103020310 : 2,
		260010601 : 1,
		915020100 : 1,
		106030100 : 2,
		106030200 : 2,
		120041800 : 2,
		106030501 : 2,
		271030600 : 5,
		863010300 : 1,
		863010400 : 1,
		863010220 : 1,
		863010230 : 0,
		863010210 : 0,
		863010240 : 0,
		863010500 : 0,
		863010320 : 0,
		863010420 : 0,
		221023200 : 0,
		223000000 : 1,
		223010100 : 0,
		200020001 : 2,
		915020000 : 2,
		915020200 : 2,
		240010102 : 1,
		200090510 : 2,
		310050100 : 1,
		310060221 : 0,
		222020000 : 0,
		270000000 : 3,
		270010100 : 0,
		270010200 : 0,
		270010300 : 5,
		270010400 : 0,
	}

	def init():
		currentMap = sm.getFieldID()
		warp = True

		if currentMap == 103030100:
			warp = False
			sm.chatRed("There seems to be a mysterious presence blocking you from entering.")
			sm.dispose()

		elif currentMap == 102010100:
			warp = False
			sm.chatRed("There seems to be a mysterious presence blocking you from entering.")
			sm.dispose()

		elif currentMap == 310050100: # Verne Mine : Power Plant Lobby
			sm.chat("Destroy the Energy Conducting Device!")
			#warp is meant to stay True

		elif currentMap == 310060221: # Hidden Street : Leery Corridor
			if sm.hasQuest(23043):
				sm.completeQuest(23043)

		elif currentMap == 222020000: # Ludi tower: Helios Tower <Library> (CoK 3rd job portal)
			if not sm.hasQuest(20880): # 3rd job quest
				warp = False
				sm.chat("Only knights looking to job advance to the third job may enter here.")
				sm.dispose()


		#ToT Portals
		elif currentMap == 270000000: # Time Lane: Three doors
			if not sm.hasQuestCompleted(3500): # time lane quest
				warp = False
				sm.chat("You have not completed the appropriate quest to enter here.")
				sm.dispose()

		elif currentMap == 270010100: # Time Lane: Memory Lane 1
			if not sm.hasQuestCompleted(3501): # time lane quest
				warp = False
				sm.chat("You have not completed the appropriate quest to enter here.")
				sm.dispose()

		elif currentMap == 270010200: # Time Lane: Memory Lane 2
			if not sm.hasQuestCompleted(3502): # time lane quest
				warp = False
				sm.chat("You have not completed the appropriate quest to enter here.")
				sm.dispose()

		elif currentMap == 270010300: # Time Lane: Memory Lane 3
			if not sm.hasQuestCompleted(3503): # time lane quest
				warp = False
				sm.chat("You have not completed the appropriate quest to enter here.")
				sm.dispose()

		elif currentMap == 270010400: # Time Lane: Memory Lane 4
			if not sm.hasQuestCompleted(3504): # time lane quest
				warp = False
				sm.chat("You have not completed the appropriate quest to enter here.")
				sm.dispose()


		#Boss Portal
		elif currentMap == 223030200:
			warp = False
			sm.sendAskYesNo("Would you like to battle scarlion and targa?")

		elif currentMap == 271040000:
			warp = False
			sm.sendAskYesNo("Would you like to battle cygnus?")


		#Default script
		else:
			warp = False
			sm.chat("(Portal - in00) This script isn't coded for this map.")

		if warp:
			sm.warp(field[currentMap], portal[currentMap])
			sm.dispose()

	def action(response, answer):
		currentMap = sm.getFieldID()
		if response == 1:
			if sm.getParty() is None:
				sm.sendSayOkay("Please create a party before going in.")
			elif not sm.isPartyLeader():
				sm.sendSayOkay("Please have your party leader enter if you wish to face Cygnus.")
			elif sm.checkParty():
				if currentMap == 271040000:
					sm.warpPartyIn(271040100)
				elif currentMap == 223030200:
					sm.warpPartyIn(223030210)
		sm.dispose()