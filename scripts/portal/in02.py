status = -1

if sm.getFieldID() == 951000000:
	# Monster Park

	minLv = 160
	maxLv = 250

	maps = [
		["Ruined City (Lv.160-169", 954000000],
		["Dead Tree Forest (Lv.170-179)", 954010000],
		["Watchman's Tower (Lv.175-184)", 954020000],
		["Dragon Nest (Lv.180-189)", 954030000],
		["Temple of Oblivion (Lv.185-194)", 954040000],
		["Knight Stronghold (Lv.190-199)", 954050000],
		["Spirit Valley (Lv.200-209)", 954060000],
	]
	sm.setSpeakerID(9071004)

	def init():
		if not sm.getParty() is None:
			sm.sendSayOkay("Please leave your party to enter Monster Park.")
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
							"Clearing the dungeon will use up #bone of your free clears#k \r\nfor today.\r\n\r\n"
							"Would you like to enter the dungeon?")

		elif status == 1:
			if response == 1:
				sm.warpInstanceIn(maps[selection][1])
			sm.dispose()

else:

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