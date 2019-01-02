drop table if exists equip_drops;

create table equip_drops (
	id int,
    job varchar(255),
    level int,
	primary key (id)
);

insert into equip_drops (id, job, level) values 
(1452002, "BOWMAN", 10), # War Bow - Lv 10
(1452003, "BOWMAN", 10), # Composite Bow - Lv 15
(1452001, "BOWMAN", 20), # Hunter's Bow - Lv 20
(1452000, "BOWMAN", 20), # Battle Bow - Lv 25
(1452005, "BOWMAN", 30), # Ryden - Lv 30
(1452006, "BOWMAN", 30), # Red Viper - Lv 35
(1452016, "BOWMAN", 30), # Maple Bow - Lv 35
(1452007, "BOWMAN", 40), # Vaulter 2000 - Lv 40
(1452022, "BOWMAN", 40), # Maple Soul SeBOWMAN - Lv 45
(1452008, "BOWMAN", 50), # Olympus - Lv 50
(1452023, "BOWMAN", 50), # Cao Cao Bow - Lv 53
(1452004, "BOWMAN", 60), # Asianic Bow - Lv 60
(1452018, "BOWMAN", 60), # Bow of Magic Destruction - Lv 65
(1452045, "BOWMAN", 60), # Maple Kandiva Bow - Lv 65
(1452009, "BOWMAN", 70), # Red Hinkel - Lv 70
(1452010, "BOWMAN", 70), # Blue Hinkel - Lv 70
(1452011, "BOWMAN", 70), # Golden Hinkel - Lv 70
(1452100, "BOWMAN", 70), # Maple Pyrope Bow - Lv 77
(1452012, "BOWMAN", 80), # Marine Arund - Lv 80
(1452013, "BOWMAN", 80), # Fire Arund - Lv 80
(1452014, "BOWMAN", 80), # Golden Arund - Lv 80
(1452017, "BOWMAN", 90), # Metus - Lv 90
(1452025, "BOWMAN", 90), # Blue Metus - Lv 90
(1452026, "BOWMAN", 90), # Black Metus - Lv 90
(1452019, "BOWMAN", 100), # White Nisrock - Lv 100
(1452020, "BOWMAN", 100), # Golden Nisrock - Lv 100
(1452021, "BOWMAN", 100), # Dark Nisrock - Lv 100
(1452044, "BOWMAN", 110), # Dragon Shiner Bow - Lv 110
(1452237, "BOWMAN", 130), # Jaihin Bow - Lv 130
(1452238, "BOWMAN", 140), # Utgard Bow - Lv 140
(1462001, "BOWMAN", 10), # Crossbow - Lv 12
(1462002, "BOWMAN", 10), # Battle Crossbow - Lv 18
(1462003, "BOWMAN", 20), # Balanche - Lv 22
(1462000, "BOWMAN", 20), # Mountain Crossbow - Lv 28
(1462004, "BOWMAN", 30), # Eagle Crow - Lv 32
(1462014, "BOWMAN", 30), # Maple Crow - Lv 35
(1462005, "BOWMAN", 30), # Heckler - Lv 38
(1462006, "BOWMAN", 40), # Silver Crow - Lv 42
(1462019, "BOWMAN", 40), # Maple Crossbow - Lv 43
(1462007, "BOWMAN", 50), # Rower - Lv 50
(1462008, "BOWMAN", 60), # Golden Crow - Lv 60
(1462040, "BOWMAN", 60), # Maple Nishada - Lv 64
(1462009, "BOWMAN", 70), # Gross Jaeger - Lv 70
(1462085, "BOWMAN", 70), # Maple Pyrope Crow - Lv 77
(1462013, "BOWMAN", 80), # Dark Raven - Lv 80
(1462011, "BOWMAN", 80), # Fire Raven - Lv 80
(1462012, "BOWMAN", 80), # Golden Raven - Lv 80
(1462010, "BOWMAN", 80), # Marine Raven - Lv 80
(1462018, "BOWMAN", 90), # Casa Crow - Lv 90
(1462021, "BOWMAN", 90), # Dark Crow - Lv 90
(1462022, "BOWMAN", 90), # Yellow Crow - Lv 90
(1462017, "BOWMAN", 100), # Dark Neschere - Lv 100
(1462016, "BOWMAN", 100), # Golden Neschere - Lv 100
(1462039, "BOWMAN", 110), # Dragon Shiner Cross - Lv 110
(1462224, "BOWMAN", 130), # Jaihin Crossbow - Lv 130
(1462225, "BOWMAN", 140), # Utgard Crossbow - Lv 140
(1522000, "BOWMAN", 10), # Trusty & Faithful - Lv 10
(1522002, "BOWMAN", 20), # Elven Diplomacy - Lv 20
(1522003, "BOWMAN", 20), # Songs of Nature - Lv 25
(1522004, "BOWMAN", 30), # Twin Angels - Lv 30
(1522005, "BOWMAN", 30), # Proud Blossoms - Lv 35
(1522006, "BOWMAN", 40), # The Argents - Lv 40
(1522007, "BOWMAN", 50), # Judge & Jury - Lv 50
(1522008, "BOWMAN", 60), # Lunar Glory - Lv 60
(1522009, "BOWMAN", 70), # Twin Zephyrs - Lv 70
(1522086, "BOWMAN", 70), # Stellar Argents - Lv 75
(1522010, "BOWMAN", 80), # Sylvan Hunters - Lv 80
(1522011, "BOWMAN", 90), # Royal Decrees - Lv 90
(1522012, "BOWMAN", 100), # Final Retorts - Lv 100
(1522014, "BOWMAN", 110), # Dragon Majesty - Lv 110
(1522123, "BOWMAN", 130), # Jaihin Dual Bowguns - Lv 130
(1522124, "BOWMAN", 140), # Utgard Dual Bowguns - Lv 140
(1542000, "WARRIOR", 10), # Simple Iron Sword - Lv 10
(1542001, "WARRIOR", 20), # Tempered Steel Sword - Lv 20
(1542002, "WARRIOR", 30), # Iron Guardian - Lv 30
(1542003, "WARRIOR", 40), # Bare Blade - Lv 40
(1542004, "WARRIOR", 50), # Iron Savior - Lv 50
(1542006, "WARRIOR", 60), # Helix - Lv 60
(1542007, "WARRIOR", 80), # Heat Wave - Lv 80
(1542008, "WARRIOR", 90), # Battle Cry - Lv 90
(1542009, "WARRIOR", 100), # Gilded Sword - Lv 100
(1542011, "WARRIOR", 100), # Mist Cutter - Lv 100
(1542010, "WARRIOR", 110), # Phoenix Rising - Lv 110
(1542100, "WARRIOR", 130), # Jaihin Katana - Lv 130
(1542101, "WARRIOR", 140), # Utgard Katana - Lv 140
(1232000, "WARRIOR", 10), # Blue Ravager - Lv 10
(1232002, "WARRIOR", 30), # Dim Memory - Lv 30
(1232003, "WARRIOR", 50), # Eternal Wanderer - Lv 50
(1232004, "WARRIOR", 60), # Plum Sadness - Lv 60
(1232005, "WARRIOR", 70), # Cardinal Omen - Lv 70
(1232006, "WARRIOR", 80), # Brand Edge - Lv 80
(1232007, "WARRIOR", 90), # Nightmare - Lv 90
(1232008, "WARRIOR", 100), # Cruel Vengeance - Lv 100
(1232010, "WARRIOR", 110), # Dragon Rage - Lv 110
(1232095, "WARRIOR", 130), # Utgard Desperado - Lv 130
(1442000, "WARRIOR", 10), # PoleArm - Lv 10
(1442006, "WARRIOR", 10), # Iron Ball - Lv 15
(1442007, "WARRIOR", 20), # Studded Polearm - Lv 20
(1442004, "WARRIOR", 20), # Janitor's Mop - Lv 25
(1442001, "WARRIOR", 30), # Mithril Pole Arm - Lv 30
(1442003, "WARRIOR", 30), # Axe Pole Arm - Lv 35
(1442009, "WARRIOR", 40), # Crescent Polearm - Lv 40
(1442024, "WARRIOR", 40), # Maple Scorpio - Lv 43
(1442005, "WARRIOR", 50), # The Nine Dragons - Lv 50
(1442010, "WARRIOR", 60), # Skylar - Lv 60
(1442051, "WARRIOR", 60), # Maple Karstan - Lv 64
(1442008, "WARRIOR", 70), # The Gold Dragon - Lv 70
(1442104, "WARRIOR", 70), # Maple Pyrope Hellslayer - Lv 77
(1442019, "WARRIOR", 80), # Eclipse - Lv 80
(1442020, "WARRIOR", 90), # Hellslayer - Lv 90
(1442044, "WARRIOR", 100), # Zedbug - Lv 100
(1442045, "WARRIOR", 110), # Dragon Hellslayer - Lv 110
(1442169, "WARRIOR", 120), # Eviscerator - Lv 120
(1442253, "WARRIOR", 130), # Jaihin Hellslayer - Lv 130
(1442254, "WARRIOR", 140), # Utgard Hellslayer - Lv 140
(1432000, "WARRIOR", 10), # Spear - Lv 10
(1432001, "WARRIOR", 10), # Fork on a Stick - Lv 15
(1432002, "WARRIOR", 30), # Forked Spear - Lv 30
(1432003, "WARRIOR", 30), # Nakamaki - Lv 35
(1432005, "WARRIOR", 40), # Zeco - Lv 40
(1432012, "WARRIOR", 40), # Maple Impaler - Lv 43
(1432004, "WARRIOR", 50), # Serpent's Tongue - Lv 50
(1432006, "WARRIOR", 60), # Holy Spear - Lv 60
(1432040, "WARRIOR", 60), # Maple Soul Spear - Lv 64
(1432007, "WARRIOR", 70), # Redemption - Lv 70
(1432075, "WARRIOR", 70), # Maple Pyrope Spear - Lv 77
(1432010, "WARRIOR", 80), # Omega Spear - Lv 80
(1432011, "WARRIOR", 90), # Fairfrozen - Lv 90
(1432030, "WARRIOR", 100), # Pinaka - Lv 100
(1432038, "WARRIOR", 110), # Dragon Faltizan - Lv 110
(1432199, "WARRIOR", 130), # Jaihin Spear - Lv 130
(1432200, "WARRIOR", 140), # Utgard Spear - Lv 140
(1302005, "WARRIOR", 10), # Sabre - Lv 10
(1302006, "WARRIOR", 20), # Machete - Lv 20
(1302002, "WARRIOR", 20), # Viking Sword - Lv 20
(1302003, "WARRIOR", 20), # Eloon - Lv 25
(1302008, "WARRIOR", 30), # Gladius - Lv 30
(1302004, "WARRIOR", 30), # Cutlass - Lv 35
(1302020, "WARRIOR", 30), # Maple Sword - Lv 35
(1302009, "WARRIOR", 40), # Traus - Lv 40
(1302030, "WARRIOR", 40), # Maple Soul Singer - Lv 43
(1302010, "WARRIOR", 50), # Jeweled Katar - Lv 50
(1302011, "WARRIOR", 60), # Neocora - Lv 60
(1302012, "WARRIOR", 70), # Red Katana - Lv 70
(1302142, "WARRIOR", 70), # Maple Pyrope Sword - Lv 77
(1302018, "WARRIOR", 80), # Khan - Lv 80
(1302023, "WARRIOR", 90), # Fraute - Lv 90
(1302056, "WARRIOR", 100), # Sparta - Lv 100
(1302059, "WARRIOR", 110), # Dragon Carabella - Lv 110
(1302315, "WARRIOR", 140), # Utgard Saber - Lv 140
(1402001, "WARRIOR", 10), # Wooden Sword - Lv 10
(1402018, "WARRIOR", 10), # Wooden Samurai Sword - Lv 15
(1402000, "WARRIOR", 20), # Two-Handed Sword - Lv 20
(1402008, "WARRIOR", 20), # Broadsword - Lv 25
(1402002, "WARRIOR", 30), # Scimitar - Lv 30
(1402006, "WARRIOR", 30), # Lionheart - Lv 35
(1402007, "WARRIOR", 40), # Zard - Lv 40
(1402003, "WARRIOR", 50), # Lion's Fang - Lv 50
(1402013, "WARRIOR", 50), # Japanese Map - Lv 50
(1402011, "WARRIOR", 60), # Sparta - Lv 60
(1402039, "WARRIOR", 60), # Maple Soul Rohen - Lv 64
(1402012, "WARRIOR", 70), # Doombringer - Lv 70
(1402085, "WARRIOR", 70), # Maple Pyrope Rohen - Lv 77
(1402015, "WARRIOR", 80), # Heaven's Gate - Lv 80
(1402004, "WARRIOR", 80), # Blue Screamer - Lv 80
(1402005, "WARRIOR", 90), # Berzerker - Lv 90
(1402016, "WARRIOR", 90), # Devil's Sunrise - Lv 90
(1402035, "WARRIOR", 100), # The Beheader - Lv 100
(1402037, "WARRIOR", 100), # Stonetooth Sword - Lv 100
(1402036, "WARRIOR", 110), # Dragon Claymore - Lv 110
(1402235, "WARRIOR", 130), # Jaihin Two-handed Sword - Lv 130
(1402236, "WARRIOR", 140), # Utgard Two-handed Sword - Lv 140
(1322000, "WARRIOR", 10), # Mace - Lv 10
(1322002, "WARRIOR", 20), # Iron Mace - Lv 20
(1322004, "WARRIOR", 20), # Fusion Mace - Lv 25
(1322014, "WARRIOR", 30), # War Hammer - Lv 30
(1322016, "WARRIOR", 40), # Jacker - Lv 40
(1322017, "WARRIOR", 50), # Knuckle Mace - Lv 50
(1322018, "WARRIOR", 60), # Tamus - Lv 60
(1322054, "WARRIOR", 60), # Maple Havoc Hammer - Lv 64
(1322019, "WARRIOR", 70), # The Judgement - Lv 70
(1322020, "WARRIOR", 70), # Bent Judgement - Lv 75
(1322084, "WARRIOR", 70), # Maple Pyrope Hammer - Lv 77
(1322028, "WARRIOR", 80), # Heaven's Justice - Lv 80
(1322029, "WARRIOR", 90), # Ruin Hammer - Lv 90
(1322045, "WARRIOR", 100), # Battle Hammer - Lv 100
(1322235, "WARRIOR", 130), # Jaihin Hair - Lv 130
(1322236, "WARRIOR", 140), # Utgard Hair - Lv 140
(1422000, "WARRIOR", 10), # Wooden Mallet - Lv 10
(1422003, "WARRIOR", 20), # Square Hammer - Lv 20
(1422001, "WARRIOR", 30), # Mithril Maul - Lv 30
(1422008, "WARRIOR", 30), # Sledgehammer - Lv 35
(1422007, "WARRIOR", 40), # Titan - Lv 40
(1422005, "WARRIOR", 50), # Golden Mole - Lv 50
(1422009, "WARRIOR", 60), # The Blessing - Lv 60
(1422010, "WARRIOR", 70), # Gigantic Sledge - Lv 70
(1422057, "WARRIOR", 70), # Maple Pyrope Maul - Lv 77
(1422018, "WARRIOR", 80), # The Morningstar - Lv 80
(1422013, "WARRIOR", 90), # Leomite - Lv 90
(1422027, "WARRIOR", 100), # Golden Smith Hammer - Lv 100
(1422028, "WARRIOR", 110), # Dragon Flame - Lv 110
(1422170, "WARRIOR", 130), # Jaihin Two-handed Hammer - Lv 130
(1422171, "WARRIOR", 140), # Utgard Two-handed Hammer - Lv 140
(1312000, "WARRIOR", 10), # Double Axe - Lv 10
(1312001, "WARRIOR", 10), # Battle Axe - Lv 15
(1312003, "WARRIOR", 20), # Mithril Axe - Lv 25
(1312005, "WARRIOR", 30), # Fireman's Axe - Lv 30
(1312006, "WARRIOR", 30), # Dankke - Lv 35
(1312007, "WARRIOR", 40), # Blue Counter - Lv 40
(1312008, "WARRIOR", 50), # Buck - Lv 50
(1312009, "WARRIOR", 60), # Hawkhead - Lv 60
(1312032, "WARRIOR", 60), # Maple Steel Axe - Lv 64
(1312010, "WARRIOR", 70), # Mikhail - Lv 70
(1312056, "WARRIOR", 70), # Maple Pyrope Axe - Lv 77
(1312011, "WARRIOR", 80), # Gaea - Lv 80
(1312015, "WARRIOR", 90), # Vifennis - Lv 90
(1312030, "WARRIOR", 100), # Tomahawk - Lv 100
(1312031, "WARRIOR", 110), # Dragon Axe - Lv 110
(1312184, "WARRIOR", 130), # Jaihin Axe - Lv 130
(1312185, "WARRIOR", 140), # Utgard Axe - Lv 140
(1412001, "WARRIOR", 10), # Metal Axe - Lv 10
(1412012, "WARRIOR", 10), # Iron Axe - Lv 15
(1412000, "WARRIOR", 20), # Two-Handed Axe - Lv 25
(1412006, "WARRIOR", 30), # Blue Axe - Lv 30
(1412004, "WARRIOR", 30), # Niam - Lv 35
(1412005, "WARRIOR", 40), # Sabretooth - Lv 40
(1412011, "WARRIOR", 40), # Maple Dragon Axe - Lv 43
(1412003, "WARRIOR", 50), # The Rising - Lv 50
(1412007, "WARRIOR", 60), # The Shining - Lv 60
(1412027, "WARRIOR", 60), # Maple Demon Axe - Lv 64
(1412008, "WARRIOR", 70), # Chrono - Lv 70
(1412055, "WARRIOR", 70), # Maple Pyrope Battle Axe - Lv 77
(1412009, "WARRIOR", 80), # Helios - Lv 80
(1412010, "WARRIOR", 90), # Colonian Axe - Lv 90
(1412021, "WARRIOR", 100), # Tavar - Lv 100
(1412026, "WARRIOR", 110), # Dragon Battle Axe - Lv 110
(1412163, "WARRIOR", 130), # Jaihin Two-handed Axe - Lv 130
(1412164, "WARRIOR", 140), # Utgard Two-handed Axe - Lv 140
(1582000, "WARRIOR", 10), # Hand Buster - Lv 10
(1582001, "WARRIOR", 30), # Hand Crusher - Lv 30
(1582002, "WARRIOR", 50), # Blow Bomb - Lv 50
(1582003, "WARRIOR", 60), # Giant Hand - Lv 60
(1582004, "WARRIOR", 70), # Taurus - Lv 70
(1582005, "WARRIOR", 80), # Ogre Grip - Lv 80
(1582006, "WARRIOR", 90), # Gigantic Arm Cannon - Lv 90
(1582007, "WARRIOR", 100), # Titan Arms - Lv 100
(1582008, "WARRIOR", 110), # Valore Punch - Lv 110
(1282000, "MAGICIAN", 10), # Luna Lucent Gauntlet - Lv 10
(1282001, "MAGICIAN", 30), # Matis Lucent Gauntlet - Lv 30
(1282002, "MAGICIAN", 50), # Mercury Lucent Gauntlet - Lv 50
(1282003, "MAGICIAN", 60), # Robis Lucent Gauntlet - Lv 60
(1282004, "MAGICIAN", 70), # Veneris Lucent Gauntlet - Lv 70
(1282005, "MAGICIAN", 80), # Saturni Lucent Gauntlet - Lv 80
(1282006, "MAGICIAN", 90), # Solis Lucent Gauntlet - Lv 90
(1282007, "MAGICIAN", 100), # Perias Lucent Gauntlet - Lv 100
(1282008, "MAGICIAN", 110), # Dragon Lucent Gauntlet - Lv 110
(1282011, "MAGICIAN", 130), # Jaihin Lucent Gauntlet - Lv 130
(1282013, "MAGICIAN", 140), # Utgard Lucent Gauntlet - Lv 140
(1552000, "MAGICIAN", 10), # Iron Fan - Lv 10
(1552001, "MAGICIAN", 20), # Metallic Fan - Lv 20
(1552002, "MAGICIAN", 30), # Triple Fan - Lv 30
(1552003, "MAGICIAN", 40), # Crane Fan - Lv 40
(1552004, "MAGICIAN", 50), # Fan of Enlightenment - Lv 50
(1552005, "MAGICIAN", 60), # Melodic Fan - Lv 60
(1552006, "MAGICIAN", 70), # Unique Fan - Lv 70
(1552007, "MAGICIAN", 80), # Leaf-Patterned Fan - Lv 80
(1552008, "MAGICIAN", 90), # Erupting Fan - Lv 90
(1552009, "MAGICIAN", 100), # Clear Skies - Lv 100
(1552011, "MAGICIAN", 100), # Florist's Fan - Lv 100
(1552010, "MAGICIAN", 110), # Crossed Fan - Lv 110
(1552101, "MAGICIAN", 130), # Jaihin Fan - Lv 130
(1552102, "MAGICIAN", 140), # Utgard Fan - Lv 140
(1382000, "MAGICIAN", 10), # Wooden Staff - Lv 10
(1382003, "MAGICIAN", 10), # Sapphire Staff - Lv 15
(1382005, "MAGICIAN", 10), # Emerald Staff - Lv 15
(1382004, "MAGICIAN", 20), # Old Wooden Staff - Lv 20
(1382002, "MAGICIAN", 20), # Wizard Staff - Lv 25
(1382017, "MAGICIAN", 30), # Circle-Winded Staff - Lv 30
(1382009, "MAGICIAN", 30), # Maple Staff - Lv 35
(1382018, "MAGICIAN", 30), # Petal Staff - Lv 35
(1382019, "MAGICIAN", 40), # Hall Staff - Lv 40
(1382012, "MAGICIAN", 40), # Maple Lama Staff - Lv 43
(1382020, "MAGICIAN", 40), # Arc Staff - Lv 45
(1382011, "MAGICIAN", 50), # Mystic Cane - Lv 51
(1382026, "MAGICIAN", 50), # Thorns - Lv 55
(1382039, "MAGICIAN", 60), # Maple Wisdom Staff - Lv 64
(1382027, "MAGICIAN", 60), # Evil Wings - Lv 65
(1382023, "MAGICIAN", 70), # Dark Ritual - Lv 75
(1382093, "MAGICIAN", 70), # Maple Pyrope Staff - Lv 77
(1382024, "MAGICIAN", 80), # Kage - Lv 85
(1382035, "MAGICIAN", 90), # Blue Marine - Lv 95
(1382037, "MAGICIAN", 100), # Doomsday Staff - Lv 102
(1382036, "MAGICIAN", 110), # Dragon Staff - Lv 110
(1382244, "MAGICIAN", 130), # Jaihin Staff - Lv 130
(1382245, "MAGICIAN", 140), # Utgard Staff - Lv 140
(1372005, "MAGICIAN", 0), # Wooden Wand - Lv 8
(1372002, "MAGICIAN", 10), # Metal Wand - Lv 18
(1372004, "MAGICIAN", 20), # Ice Wand - Lv 23
(1372003, "MAGICIAN", 20), # Mithril Wand - Lv 28
(1372001, "MAGICIAN", 30), # Wizard Wand - Lv 33
(1372012, "MAGICIAN", 30), # Crystal Wand - Lv 38
(1372000, "MAGICIAN", 30), # Fairy Wand - Lv 38
(1372007, "MAGICIAN", 40), # Cromi - Lv 48
(1372014, "MAGICIAN", 50), # Evil Tale - Lv 58
(1372008, "MAGICIAN", 60), # Hinomaru Fan - Lv 60
(1372034, "MAGICIAN", 60), # Maple Shine Wand - Lv 64
(1372015, "MAGICIAN", 60), # Angel Wings - Lv 68
(1372016, "MAGICIAN", 70), # Phoenix Wand - Lv 78
(1372009, "MAGICIAN", 80), # Magicodar - Lv 88
(1372010, "MAGICIAN", 90), # Dimon Wand - Lv 98
(1372032, "MAGICIAN", 100), # Dragon Wand - Lv 108
(1372206, "MAGICIAN", 130), # Jaihin Wand - Lv 130
(1372207, "MAGICIAN", 140), # Utgard Wand - Lv 140
(1212001, "MAGICIAN", 10), # Plain - Lv 10
(1212002, "MAGICIAN", 30), # Golden Shine - Lv 30
(1212003, "MAGICIAN", 50), # Decor - Lv 50
(1212004, "MAGICIAN", 60), # Vallentier - Lv 60
(1212005, "MAGICIAN", 70), # Sharp Slaver - Lv 70
(1212006, "MAGICIAN", 80), # Light Sage Wing - Lv 80
(1212007, "MAGICIAN", 90), # Shining Twin Star - Lv 90
(1212010, "MAGICIAN", 110), # Shining Dragon Rod - Lv 110
(1212101, "MAGICIAN", 140), # Utgard Shining Rod - Lv 140
(1262000, "MAGICIAN", 10), # Prototype Psy-limiter - Lv 10
(1262001, "MAGICIAN", 30), # Trial Psy-limiter - Lv 30
(1262002, "MAGICIAN", 50), # First Psy-limiter - Lv 50
(1262003, "MAGICIAN", 60), # Strategic Psy-limiter - Lv 60
(1262004, "MAGICIAN", 70), # Engaging Psy-limiter - Lv 70
(1262005, "MAGICIAN", 80), # Technical Psy-limiter - Lv 80
(1262006, "MAGICIAN", 90), # Mad Psy-limiter - Lv 90
(1262007, "MAGICIAN", 100), # Daemon Psy-limiter - Lv 100
(1262008, "MAGICIAN", 110), # Dragon Psy-limiter - Lv 110
(1262009, "MAGICIAN", 120), # Briser Psy-limiter - Lv 120
(1262011, "MAGICIAN", 140), # Utgard Psy-limiter - Lv 140
(1252001, "MAGICIAN", 10), # Beast Tamer Scepter - Lv 10
(1252048, "MAGICIAN", 10), # Hardwood Beast Tamer Scepter - Lv 15
(1252049, "MAGICIAN", 20), # Metal Beast Tamer Scepter - Lv 20
(1252050, "MAGICIAN", 30), # Assign Scepter - Lv 30
(1252051, "MAGICIAN", 40), # High Assign Scepter - Lv 40
(1252052, "MAGICIAN", 50), # Talon Scepter - Lv 50
(1252053, "MAGICIAN", 60), # Panther Scepter - Lv 60
(1252054, "MAGICIAN", 70), # Grizzly Scepter - Lv 70
(1252006, "MAGICIAN", 80), # Tigress Scepter - Lv 80
(1252007, "MAGICIAN", 90), # Eagle Scepter - Lv 90
(1252008, "MAGICIAN", 100), # Falcon Scepter - Lv 100
(1252009, "MAGICIAN", 100), # Lion Glass Scepter - Lv 105
(1252010, "MAGICIAN", 110), # Dragon Kitty Soul Scepter - Lv 110
(1252086, "MAGICIAN", 140), # Utgard Shining Stick - Lv 140
(1272000, "THIEF", 10), # De Mercurio - Lv 10
(1272001, "THIEF", 30), # De Venus - Lv 30
(1272002, "THIEF", 50), # De Tierra - Lv 50
(1272003, "THIEF", 60), # De Marte - Lv 60
(1272004, "THIEF", 70), # De Jupiter - Lv 70
(1272005, "THIEF", 80), # De Saturno - Lv 80
(1272006, "THIEF", 90), # De Urano - Lv 90
(1272007, "THIEF", 100), # De Neptuno - Lv 100
(1272008, "THIEF", 110), # Dragon Chain - Lv 110
(1272011, "THIEF", 130), # Jaihin Chain - Lv 130
(1272013, "THIEF", 140), # Utgard Chain - Lv 140
(1362002, "THIEF", 10), # Iron Cane - Lv 15
(1362003, "THIEF", 20), # Noble Stick - Lv 20
(1362004, "THIEF", 20), # Croix Canne - Lv 25
(1362005, "THIEF", 30), # Rouge Way - Lv 30
(1362006, "THIEF", 30), # Arc-en-ciel - Lv 35
(1362007, "THIEF", 40), # Twisted - Lv 40
(1362008, "THIEF", 50), # Oriental Royal Cane - Lv 50
(1362009, "THIEF", 60), # Bijou Canne - Lv 60
(1362010, "THIEF", 70), # Regne - Lv 70
(1362011, "THIEF", 70), # Majesty Cane - Lv 70
(1362012, "THIEF", 90), # Phantom Originality - Lv 90
(1362013, "THIEF", 100), # Celestial Cane - Lv 100
(1362015, "THIEF", 110), # Dragon Permanche - Lv 110
(1362120, "THIEF", 130), # Jaihin Cane - Lv 130
(1362121, "THIEF", 140), # Utgard Cane - Lv 140
(1332007, "THIEF", 0), # Fruit Knife - Lv 8
(1332000, "THIEF", 10), # Triangular Zamadar - Lv 12
(1332006, "THIEF", 10), # Field Dagger - Lv 15
(1332002, "THIEF", 10), # Triple-Tipped Zamadar - Lv 17
(1332008, "THIEF", 20), # Coconut Knife - Lv 20
(1332013, "THIEF", 20), # Stinger - Lv 22
(1332010, "THIEF", 20), # Iron Dagger - Lv 25
(1332004, "THIEF", 20), # Forked Dagger - Lv 27
(1332009, "THIEF", 30), # Cass - Lv 30
(1332012, "THIEF", 30), # Reef Claw - Lv 30
(1332001, "THIEF", 30), # Halfmoon Zamadar - Lv 32
(1332014, "THIEF", 30), # Gephart - Lv 35
(1332011, "THIEF", 40), # Bazlud - Lv 40
(1332031, "THIEF", 40), # Dragon Toenail - Lv 40
(1332025, "THIEF", 40), # Maple Wagner - Lv 43
(1332003, "THIEF", 50), # Shinkita - Lv 50
(1332016, "THIEF", 50), # Sai - Lv 50
(1332024, "THIEF", 50), # Bushido - Lv 55
(1332015, "THIEF", 60), # Deadly Fin - Lv 60
(1332017, "THIEF", 60), # Serpent's Coil - Lv 60
(1332056, "THIEF", 60), # Maple Asura Dagger - Lv 64
(1332055, "THIEF", 60), # Maple Dark Mate - Lv 64
(1332030, "THIEF", 60), # Fan - Lv 65
(1332019, "THIEF", 70), # Golden River - Lv 70
(1332018, "THIEF", 70), # Kandine - Lv 70
(1332114, "THIEF", 70), # Maple Pyrope Halfmoon - Lv 77
(1332022, "THIEF", 80), # Angelic Betrayal - Lv 80
(1332023, "THIEF", 80), # Dragon's Tail - Lv 80
(1332026, "THIEF", 90), # Cursayer - Lv 90
(1332027, "THIEF", 90), # Varkit - Lv 90
(1332052, "THIEF", 100), # Blood Dagger - Lv 100
(1332049, "THIEF", 110), # Dragon Kanzir - Lv 110
(1332050, "THIEF", 110), # Dragon Kreda - Lv 110
(1332259, "THIEF", 130), # Jaihin Dagger - Lv 130
(1332260, "THIEF", 140), # Utgard Dagger - Lv 140
(1472000, "THIEF", 10), # Garnier - Lv 10
(1472001, "THIEF", 10), # Steel Titans - Lv 15
(1472002, "THIEF", 10), # Mithril Titans - Lv 15
(1472003, "THIEF", 10), # Gold Titans - Lv 15
(1472004, "THIEF", 20), # Bronze Igor - Lv 20
(1472005, "THIEF", 20), # Steel Igor - Lv 20
(1472006, "THIEF", 20), # Adamantium Igor - Lv 20
(1472007, "THIEF", 30), # Meba - Lv 35
(1472008, "THIEF", 30), # Steel Guards - Lv 30
(1472009, "THIEF", 30), # Mithril Guards - Lv 30
(1472010, "THIEF", 30), # Adamantium Guards - Lv 30
(1472011, "THIEF", 30), # Bronze Guardian - Lv 35
(1472012, "THIEF", 30), # Silver Guardian - Lv 35
(1472013, "THIEF", 30), # Dark Guardian - Lv 35
(1472030, "THIEF", 30), # Maple Claw - Lv 35
(1472014, "THIEF", 40), # Steel Avarice - Lv 40
(1472015, "THIEF", 40), # Blood Avarice - Lv 40
(1472016, "THIEF", 40), # Adamantium Avarice - Lv 40
(1472017, "THIEF", 40), # Dark Avarice - Lv 40
(1472032, "THIEF", 40), # Maple Kandayo - Lv 43
(1472018, "THIEF", 50), # Steel Slain - Lv 50
(1472019, "THIEF", 50), # Blood Slain - Lv 50
(1472020, "THIEF", 50), # Sapphire Slain - Lv 50
(1472021, "THIEF", 50), # Dark Slain - Lv 50
(1472022, "THIEF", 60), # Bronze Gigantic - Lv 60
(1472023, "THIEF", 60), # Blood Gigantic - Lv 60
(1472024, "THIEF", 60), # Sapphire Gigantic - Lv 60
(1472025, "THIEF", 60), # Dark Gigantic - Lv 60
(1472055, "THIEF", 60), # Maple Skanda - Lv 64
(1472026, "THIEF", 70), # Brown Scarab - Lv 70
(1472027, "THIEF", 70), # Green Scarab - Lv 70
(1472028, "THIEF", 70), # Blue Scarab - Lv 70
(1472029, "THIEF", 70), # Black Scarab - Lv 70
(1472111, "THIEF", 70), # Maple Pyrope Skanda - Lv 77
(1472031, "THIEF", 80), # Black Mamba - Lv 80
(1472033, "THIEF", 90), # Casters - Lv 90
(1472053, "THIEF", 100), # Red Craven - Lv 100
(1472051, "THIEF", 110), # Dragon Green Sleeve - Lv 110
(1472052, "THIEF", 110), # Dragon Purple Sleeve - Lv 110
(1472246, "THIEF", 130), # Jaihin Guards - Lv 130
(1472247, "THIEF", 140), # Utgard Guards - Lv 140
(1342000, "THIEF", 20), # Champion Katara - Lv 20
(1342001, "THIEF", 30), # Guardian Katara - Lv 30
(1342025, "THIEF", 30), # Maple Katara - Lv 35
(1342002, "THIEF", 40), # Justice Katara - Lv 40
(1342026, "THIEF", 40), # Maple Duke Katara - Lv 43
(1342003, "THIEF", 50), # Majestic Katara - Lv 50
(1342004, "THIEF", 60), # Ascalon Katara - Lv 60
(1342027, "THIEF", 60), # Maple Cleat Katara - Lv 64
(1342005, "THIEF", 70), # Heavenly Katara - Lv 70
(1342028, "THIEF", 70), # Maple Pyrope Katara - Lv 77
(1342006, "THIEF", 80), # Blazing Dragon Katara - Lv 80
(1342007, "THIEF", 90), # Bloodsoaked Katara - Lv 90
(1342008, "THIEF", 100), # Meteor Katara - Lv 100
(1342099, "THIEF", 130), # Jaihin Katara - Lv 130
(1342100, "THIEF", 140), # Utgard Katara - Lv 140
(1492000, "PIRATE", 10), # Pistol - Lv 10
(1492001, "PIRATE", 10), # Dellinger Special - Lv 15
(1492002, "PIRATE", 20), # The Negotiator - Lv 20
(1492003, "PIRATE", 20), # Golden Hook - Lv 25
(1492004, "PIRATE", 30), # Cold Mind - Lv 30
(1492020, "PIRATE", 30), # Maple Gun - Lv 35
(1492005, "PIRATE", 30), # Shooting Star - Lv 35
(1492006, "PIRATE", 40), # Lunar Shooter - Lv 40
(1492021, "PIRATE", 40), # Maple Storm Pistol - Lv 43
(1492007, "PIRATE", 50), # Mr. Rasfelt - Lv 50
(1492008, "PIRATE", 60), # Burning Hell - Lv 60
(1492022, "PIRATE", 60), # Maple Cannon Shooter - Lv 64
(1492009, "PIRATE", 70), # Abyss Shooter - Lv 70
(1492073, "PIRATE", 70), # Maple Pyrope Shooter - Lv 77
(1492010, "PIRATE", 80), # Infinity's Wrath - Lv 80
(1492011, "PIRATE", 90), # The Peacemaker - Lv 90
(1492012, "PIRATE", 100), # Concerto - Lv 100
(1492013, "PIRATE", 110), # Dragon Revolver - Lv 110
(1492211, "PIRATE", 130), # Jaihin Pistol - Lv 130
(1492212, "PIRATE", 140), # Utgard Pistol - Lv 140
(1482000, "PIRATE", 10), # Steel Knuckler - Lv 10
(1482001, "PIRATE", 10), # Leather Arms - Lv 15
(1482002, "PIRATE", 20), # Double Tail Knuckler - Lv 20
(1482003, "PIRATE", 20), # Norman Grip - Lv 25
(1482004, "PIRATE", 30), # Prime Hands - Lv 30
(1482005, "PIRATE", 30), # Silver Maiden - Lv 35
(1482020, "PIRATE", 30), # Maple Knuckle - Lv 35
(1482006, "PIRATE", 40), # Neozard - Lv 40
(1482021, "PIRATE", 40), # Maple Storm Finger - Lv 43
(1482007, "PIRATE", 50), # Fury Claw - Lv 50
(1482008, "PIRATE", 60), # Seraphims - Lv 60
(1482022, "PIRATE", 60), # Maple Golden Claw - Lv 64
(1482009, "PIRATE", 70), # Beia Crash - Lv 70
(1482073, "PIRATE", 70), # Maple Pyrope Knuckle - Lv 77
(1482010, "PIRATE", 80), # Steel Renault - Lv 80
(1482011, "PIRATE", 90), # White Fangz - Lv 90
(1482012, "PIRATE", 100), # King Cent - Lv 100
(1482013, "PIRATE", 110), # Dragon Slash Claw - Lv 110
(1482201, "PIRATE", 130), # Jaihin Claw - Lv 130
(1482202, "PIRATE", 140), # Utgard Claw - Lv 140
(1222001, "PIRATE", 10), # Purple Haze - Lv 10
(1222002, "PIRATE", 30), # Pink Haze - Lv 30
(1222003, "PIRATE", 50), # Purple Hatchling - Lv 50
(1222004, "PIRATE", 60), # Jade Hatchling - Lv 60
(1222005, "PIRATE", 70), # Pink Wormhead - Lv 70
(1222006, "PIRATE", 80), # Red Wormhead - Lv 80
(1222007, "PIRATE", 90), # Jade Wormhead - Lv 90
(1222008, "PIRATE", 100), # Green Dragon Soul - Lv 100
(1222009, "PIRATE", 100), # Jade Worm - Lv 105
(1222010, "PIRATE", 110), # Iron Dragon - Lv 110
(1222011, "PIRATE", 120), # Timeless Purple Dragon - Lv 120
(1222012, "PIRATE", 120), # Reverse Purple Dragon - Lv 120
(1222095, "PIRATE", 140), # Utgard Dragon Soul - Lv 140
(1532001, "PIRATE", 10), # Solid Cannon - Lv 15
(1532002, "PIRATE", 20), # Ironside - Lv 20
(1532003, "PIRATE", 20), # Steel Beast - Lv 25
(1532004, "PIRATE", 30), # Heavy Artillery - Lv 30
(1532005, "PIRATE", 30), # Titan Cannon - Lv 35
(1532006, "PIRATE", 40), # Grand Cannon - Lv 40
(1532007, "PIRATE", 50), # Dreadnought - Lv 50
(1532008, "PIRATE", 60), # The Bigshot - Lv 60
(1532009, "PIRATE", 70), # Infernalizer - Lv 70
(1532010, "PIRATE", 80), # Deck Sweeper - Lv 80
(1532011, "PIRATE", 90), # Bilge Breaker - Lv 90
(1532012, "PIRATE", 100), # Gilded Giant - Lv 100
(1532014, "PIRATE", 110), # Dragon Breaker - Lv 110
(1532129, "PIRATE", 130), # Jaihin Siege Gun - Lv 130
(1532130, "PIRATE", 140), # Utgard Siege Gun - Lv 140
(1242001, "PIRATE", 10), # Horned Blade - Lv 10
(1242002, "PIRATE", 30), # Ridge Cutter - Lv 30
(1242003, "PIRATE", 50), # Rough Violet - Lv 50
(1242004, "PIRATE", 60), # Macro Knife - Lv 60
(1242005, "PIRATE", 70), # Scorpio Tail - Lv 70
(1242006, "PIRATE", 80), # Diode - Lv 80
(1242007, "PIRATE", 90), # Green Fairy - Lv 90
(1242008, "PIRATE", 100), # Crimson Skull - Lv 100
(1242009, "PIRATE", 100), # Fish Fin - Lv 105
(1242010, "PIRATE", 110), # Dragon Energy Skull - Lv 110

# Armor
(1002043, "WARRIOR", 10), # Bronze Coif - Lv 10
(1002059, "WARRIOR", 20), # Bronze Viking Helmet - Lv 20
(1002023, "WARRIOR", 30), # Jousting Helmet - Lv 30
(1002025, "WARRIOR", 40), # Red Duke - Lv 40
(1002028, "WARRIOR", 50), # Silver Crusader Helm - Lv 50
(1002029, "WARRIOR", 60), # Red Oriental Helmet - Lv 60
(1002030, "WARRIOR", 70), # Silver Planet - Lv 70
(1002340, "WARRIOR", 80), # Dark Dragon Barbute - Lv 80
(1002532, "WARRIOR", 90), # Dark Grace Helmet - Lv 90
(1002379, "WARRIOR", 100), # Dark Valhalla Helmet - Lv 100
(1002551, "WARRIOR", 110), # Blue Dragon Helmet - Lv 110
(1004219, "WARRIOR", 120), # Eclectic Fennel - Lv 120
(1004224, "WARRIOR", 130), # Muspell Warrior Hat - Lv 130
(1004229, "WARRIOR", 140), # Pensalir Battle Helm - Lv 140
(1040015, "WARRIOR", 10), # Brown Lolico Armor - Lv 10
(1041014, "WARRIOR", 10), # Orange Lolica Armor - Lv 10
(1040012, "WARRIOR", 20), # Blue Sergeant - Lv 20
(1041021, "WARRIOR", 20), # Brown Lamelle - Lv 20
(1040021, "WARRIOR", 30), # Red Hwarang Shirt - Lv 30
(1040085, "WARRIOR", 40), # Maroon Jangoon Armor - Lv 40
(1041085, "WARRIOR", 40), # Brown Jangoon Armor - Lv 40
(1040087, "WARRIOR", 50), # Blue Shouldermail - Lv 50
(1002017, "MAGICIAN", 10), # Brown Apprentice Hat - Lv 10
(1002073, "MAGICIAN", 20), # Red Wizardry Hat - Lv 20
(1002036, "MAGICIAN", 30), # Green Jester - Lv 30
(1002155, "MAGICIAN", 40), # White Guiltian - Lv 40
(1002217, "MAGICIAN", 50), # Orange Golden Circlet - Lv 50
(1002246, "MAGICIAN", 60), # Dark Seraphis - Lv 60
(1002254, "MAGICIAN", 70), # Dark Infinium Circlet - Lv 70
(1002274, "MAGICIAN", 80), # Dark Galaxy - Lv 80
(1002366, "MAGICIAN", 90), # Black Oriental Fury Hat - Lv 90
(1002401, "MAGICIAN", 100), # Dark Varr Hat - Lv 100
(1002773, "MAGICIAN", 110), # Gold Dragon Crown - Lv 110
(1004220, "MAGICIAN", 120), # Eclectic Coral - Lv 120
(1004225, "MAGICIAN", 130), # Muspell Magician Hat - Lv 130
(1004230, "MAGICIAN", 140), # Pensalir Mage Sallet - Lv 140
(1002010, "BOWMAN", 10), # Brown Winter Hat - Lv 10
(1002119, "BOWMAN", 20), # Green Robin Hat - Lv 20
(1002163, "BOWMAN", 30), # Green Hawkeye - Lv 30
(1002168, "BOWMAN", 40), # Green Distinction - Lv 40
(1002214, "BOWMAN", 50), # Black Maro - Lv 50
(1002270, "BOWMAN", 60), # Black Polyfeather Hat - Lv 60
(1002289, "BOWMAN", 70), # Dark Patriot - Lv 70
(1002278, "BOWMAN", 80), # Dark Falcon - Lv 80
(1002405, "BOWMAN", 90), # Dark Arlic Helmet - Lv 90
(1002407, "BOWMAN", 100), # Blue Arnah Cap - Lv 100
(1002547, "BOWMAN", 110), # Red Hunter - Lv 110
(1004221, "BOWMAN", 120), # Eclectic Rapid - Lv 120
(1004226, "BOWMAN", 130), # Muspell Bowman Hat - Lv 130
(1004231, "BOWMAN", 140), # Pensalir Sentinel Cap - Lv 140
(1002125, "THIEF", 10), # Black Ghetto Beanie - Lv 10
(1002130, "THIEF", 20), # Black Loosecap - Lv 20
(1002175, "THIEF", 30), # Dark Guise - Lv 30
(1002185, "THIEF", 40), # Dark Pilfer - Lv 40
(1002207, "THIEF", 50), # Red Sonata - Lv 50
(1002249, "THIEF", 60), # Dark Identity - Lv 60
(1002284, "THIEF", 70), # White Nightfox - Lv 70
(1002330, "THIEF", 80), # Dark Pireta Hat - Lv 80
(1002326, "THIEF", 90), # Red Osfa Hat - Lv 90
(1002383, "THIEF", 100), # Dark Canal Hood - Lv 100
(1002550, "THIEF", 110), # Black Garina Hood - Lv 110
(1004222, "THIEF", 120), # Eclectic Chive - Lv 120
(1004227, "THIEF", 130), # Muspell Thief Hat - Lv 130
(1004232, "THIEF", 140), # Pensalir Chaser Hat - Lv 140
(1002610, "PIRATE", 10), # Brown Rocky Bandana - Lv 10
(1002616, "PIRATE", 20), # Brown Double Marine - Lv 20
(1002622, "PIRATE", 30), # White Oceania Cap - Lv 30
(1002628, "PIRATE", 40), # Red Misty - Lv 40
(1002631, "PIRATE", 50), # Brown Leather Ocean Hat - Lv 50
(1002634, "PIRATE", 60), # Purple Cast Linen - Lv 60
(1002637, "PIRATE", 70), # Black Pirate's Bandana - Lv 70
(1002640, "PIRATE", 80), # Blue Sun Boat Hat - Lv 80
(1002643, "PIRATE", 90), # Red Brave Hamal - Lv 90
(1002646, "PIRATE", 100), # Black Polax Hat - Lv 100
(1002649, "PIRATE", 110), # Canopus Hat - Lv 110
(1004223, "PIRATE", 120), # Eclectic Conrad Henkel - Lv 120
(1004228, "PIRATE", 130), # Muspell Pirate Hat - Lv 130
(1004233, "PIRATE", 140); # Pensalir Skipper Hat - Lv 140

