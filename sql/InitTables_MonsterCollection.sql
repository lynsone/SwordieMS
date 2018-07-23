drop table if exists monster_collection_session_rewards;
drop table if exists monster_collection_group_rewards;
drop table if exists monster_collection;

create table monster_collection_session_rewards (
	region int,
    session int,
	rewardid int,
    quantity int,
    primary key (region, session)
);

create table monster_collection_group_rewards (
	region int,
    session int,
    groupid int,
	rewardid int,
    quantity int,
    primary key (region, session, groupid)
);

create table monster_collection (
	id int not null auto_increment,
    mobid int,
    region int,
    session int,
    position int,
    primary key (id)
);

# (monsterID, region, session, position)
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (0, 0, 2028048, 1);
# /**
# * Victoria Island 1 - Reward: 2028048
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100004, 0, 0, 0); # OrangeMushroom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600208, 0, 0, 1); # Mushmom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600204, 0, 0, 2); # BlueMushroom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600205, 0, 0, 3); # CryingBlueMushroom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9305103, 0, 0, 4); # BlueMushmom
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600200, 0, 0, 5); # GreenMushroom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600203, 0, 0, 6); # HornyMushroom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600206, 0, 0, 7); # ZombieMushroom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600207, 0, 0, 8); # AnnoyedZombieMushroom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9305104, 0, 0, 9); # ZombieMushmom
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1210101, 0, 0, 10); # RibbonPig
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1210104, 0, 0, 11); # BlueRibbionPig
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1210111, 0, 0, 12); # StrangePig
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230102, 0, 0, 13); # WildBoar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 0, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 0, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4090000, 0, 0, 15); # IronHog
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230400, 0, 0, 16); # IronBoar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210100, 0, 0, 17); # FireBoar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300655, 0, 0, 18); # PortlyPig
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300680, 0, 0, 19); # MrChomps
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 0, 4, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3000001, 0, 0, 20); # Fiary
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3000007, 0, 0, 21); # RoyalFairy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (210100, 0, 0, 22); # Slime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1210103, 0, 0, 23); # Bubbling
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 0, 24); # Empty
# Reward ID: 2434929
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (0, 1, 2433928, 1);
# /**
# * Victoria Island 2 - Reward: 2433928
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 1, 0, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230100, 0, 1, 0); # EvilEye
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230100, 0, 1, 1); # CurseEye
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230100, 0, 1, 2); # ColdEye
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230113, 0, 1, 3); # SurgeonEye
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 1, 4); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 1, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (130100, 0, 1, 5); # Stump
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1110101, 0, 1, 6); # DarkStump
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1130100, 0, 1, 7); # AxeStump
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2130100, 0, 1, 8); # DarkAxeStump
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 1, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1140100, 0, 1, 10); # GhostStump
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1140130, 0, 1, 11); # SmirkingGhostStump
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3220000, 0, 1, 12); # Stumpy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230110, 0, 1, 13); # WoodenMask
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230111, 0, 1, 14); # RockyMask
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 1, 3, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501000, 0, 1, 15); # FireflySlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501001, 0, 1, 16); # FairySlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501009, 0, 1, 17); # MysticWisp
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501002, 0, 1, 18); # WaterSprite
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501003, 0, 1, 19); # ForestSprite
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 1, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600209, 0, 1, 20); # StoneGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600210, 0, 1, 21); # DarkStoneGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600212, 0, 1, 22); # IcyMixedGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600213, 0, 1, 23); # FlamingMixedGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9500150, 0, 1, 24); # IceGolem
# Reward ID: 2434931
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (0, 2, 3017000, 1);
# /**
# * Victoria Island 3 - Reward: 3017000
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 2, 0, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501004, 0, 2, 0); # GrumpyTome
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501005, 0, 2, 1); # RagingTome
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501006, 0, 2, 2); # Oniony
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501007, 0, 2, 3); # Turnipy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501008, 0, 2, 4); # MoleKing
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 2, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2300100, 0, 2, 5); # Stirge
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230101, 0, 2, 6); # JrWraith
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230102, 0, 2, 7); # Wraith
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5090000, 0, 2, 8); # Shade
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 2, 9); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 2, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401000, 0, 2, 10); # PalmTreeSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401001, 0, 2, 11); # CoconutSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401002, 0, 2, 12); # EmeraldClamSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401003, 0, 2, 13); # VioletClamSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 2, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 2, 3, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401004, 0, 2, 15); # SeagullSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401005, 0, 2, 16); # RedTubeSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401006, 0, 2, 17); # BlueTubeSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401007, 0, 2, 18); # ShrimpSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401008, 0, 2, 19); # FlyingFishSlime
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 2, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401011, 0, 2, 20); # CaptainDarkgoo
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401009, 0, 2, 21); # StarfishOctopusSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401010, 0, 2, 22); # SeashellOctopusSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 2, 23); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 2, 24); # Empty
# Reward ID: 2434931
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (0, 3, 2435509, 1);

# /**
# * Victoria Island 4 - Reward: 2435509
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 3, 0, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2130103, 0, 3, 0); # JrNecki
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3110100, 0, 3, 1); # Ligator
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5130103, 0, 3, 2); # Croco
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230115, 0, 3, 3); # MuddySproutMonster
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6220000, 0, 3, 4); # Dyle
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 3, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230125, 0, 3, 5); # Skeledog
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5150001, 0, 3, 6); # SkeletonSolider
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6230602, 0, 3, 7); # OfficerSkeleton
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7130103, 0, 3, 8); # CommanderSkeleton
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300471, 0, 3, 9); # LordSkeleton
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 3, 2, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300100, 0, 3, 10); # MushroomChandelier
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300101, 0, 3, 11); # MushroomKnightArmor
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300102, 0, 3, 12); # ExhaustedViking
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300103, 0, 3, 13); # TiredViking
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 3, 14); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 3, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300710, 0, 3, 15); # PrimeMinister
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300104, 0, 3, 16); # WarmViking
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300105, 0, 3, 17); # GenerousViking
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300106, 0, 3, 18); # SolemnViking
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 3, 19); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 3, 4, 2434959, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300110, 0, 3, 20); # BlackViking
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300107, 0, 3, 21); # SeriousViking
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300108, 0, 3, 22); # MasterSquid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300109, 0, 3, 23); # VikingSquad
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 3, 24); # Empty
# Reward ID: 2434959
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (0, 4, 2350000, 1);
# /**
# * Victoria - 5 Sleepywood 1 - Reward: 2350000
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 4, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4130100, 0, 4, 0); # CopperDrake
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5130100, 0, 4, 1); # Drake
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6130100, 0, 4, 2); # RedDrake
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6230600, 0, 4, 3); # IceDrake
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6230601, 0, 4, 4); # DarkDrake
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 4, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6230100, 0, 4, 5); # WildKargo
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7130100, 0, 4, 6); # Tauromacis
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7130101, 0, 4, 7); # Taurospear
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8130100, 0, 4, 8); # JrBalrog
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8830000, 0, 4, 9); # Balrog
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 4, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7120110, 0, 4, 10); # BlazingImp
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7120111, 0, 4, 11); # PointyImp
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7120112, 0, 4, 12); # EliteBlazingImp
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7120113, 0, 4, 13); # ElitePointyImp
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 4, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 4, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8900100, 0, 4, 15); # Pierre
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8910100, 0, 4, 16); # VonBon
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8920100, 0, 4, 17); # CrimsonQueen
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8930100, 0, 4, 18); # Vellum
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 4, 19); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 4, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8900000, 0, 4, 20); # ChaosPierre
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8910000, 0, 4, 21); # ChaosVonBon
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8920000, 0, 4, 22); # ChaosCrimsonQueen
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8930000, 0, 4, 23); # ChaosVellum
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 0, 4, 24); # Empty
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (1, 0, 3017001, 1);

# /**
# * Near Victoria 1 - Reward: 3017001
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (1, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100120, 1, 0, 0); # Tino
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100121, 1, 0, 1); # Tiv
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100122, 1, 0, 2); # Timu
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100123, 1, 0, 3); # Tiru
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100124, 1, 0, 4); # Tiguru
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (1, 0, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100130, 1, 0, 5); # Muru
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100131, 1, 0, 6); # Murupa
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100132, 1, 0, 7); # Murupia
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100133, 1, 0, 8); # Murumuru
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100134, 1, 0, 9); # Murukun
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (1, 0, 2, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2220000, 1, 0, 10); # Mano
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100100, 1, 0, 11); # Snail
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100101, 1, 0, 12); # BlueSnail
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100002, 1, 0, 13); # RedSnail
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (120100, 1, 0, 14); # Shroom
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (1, 0, 3, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3502000, 1, 0, 15); # AmmoniteGrumpil
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3502001, 1, 0, 16); # FishGrumpil
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3502002, 1, 0, 17); # CorrupterBarrels
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3502003, 1, 0, 18); # PolluterBarrel
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 1, 0, 19); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (1, 0, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3502004, 1, 0, 20); # PossiblyEvilSeal
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3502005, 1, 0, 21); # PossiblyEvilWalrus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3502006, 1, 0, 22); # WarmerBot
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3502007, 1, 0, 23); # ShaverBot
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 1, 0, 24); # Empty
# Reward ID: 2434931
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (2, 0, 3017002, 0);

# /**
# * Edelstein - Reward: 3017002
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (150000, 2, 0, 0); # PottedSprout
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (150001, 2, 0, 1); # PottedMorningGlory
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (150002, 2, 0, 2); # GrapeJuiceBottle
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1150000, 2, 0, 3); # PatrolRobot
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1150001, 2, 0, 4); # StrangeSign
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 0, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1150002, 2, 0, 5); # Serpent
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2150000, 2, 0, 6); # WaterThiefMonster
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2150001, 2, 0, 7); # DustBox
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2150002, 3, 0, 8); # Streetlight
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2150003, 2, 0, 9); # PatrolRobotS
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600409, 2, 0, 10); # SafetyFirst
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600410, 2, 0, 11); # BabyBoulderMucher
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600411, 2, 0, 12); # BigBoulderMucher
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6150000, 2, 0, 13); # GuardRobot
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7150004, 2, 0, 14); # GuardRobotL
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 0, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600414, 2, 0, 15); # BigSpider
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600415, 2, 0, 16); # CartBear
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600413, 2, 0, 17); # Racoco
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600416, 2, 0, 18); # Racaroni
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600417, 2, 0, 19); # Raco
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 0, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600421, 2, 0, 20); # SecuritySystem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600422, 2, 0, 21); # EnhancedSecuritySystem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600423, 2, 0, 22); # AFAndroid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600424, 2, 0, 23); # BrokenDFAndroid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600418, 2, 0, 24); # OreMuncher
# Reward ID: 2434931
# 



insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (2, 1, 2435779, 1);

# /**
# * Scrapyard - Reward: 2435779
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250000, 2, 1, 0); # ModdedScaredroid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250001, 2, 1, 1); # ModdedBrokenAndroid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250002, 2, 1, 2); # ModdedLaseroid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250003, 2, 1, 3); # ChaseroidRed
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250004, 2, 1, 4); # ChaseroidBlue
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 1, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250005, 2, 1, 5); # HunterizerRed
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250006, 2, 1, 6); # HunterizerBlue
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250008, 2, 1, 7); # ModdedBuffroid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250009, 2, 1, 8); # SalvoroidRed
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250028, 2, 1, 9); # SalvoroidBlue
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250007, 2, 1, 10); # ModdedDeliverbot
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250010, 2, 1, 11); # OuterGuardEX
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250011, 2, 1, 12); # InnerGuardEX
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250012, 2, 1, 13); # Demolishizer
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250013, 2, 1, 14); # Repairoid
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 1, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250016, 2, 1, 15); # SteelXendroidDX
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250018, 2, 1, 16); # ScrapXendroidDX
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250022, 2, 1, 17); # SteelXenoroidEX
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250023, 2, 1, 18); # SteelXendroidEX
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250024, 2, 1, 19); # ScrapXendroidEX
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 1, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8950001, 2, 1, 20); # Lotus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250014, 2, 1, 21); # AlloyXendroidDX
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250021, 2, 1, 22); # AlloyXendroidEX
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250026, 2, 1, 23); # ModdedMegaroid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 2, 1, 24); # Empty
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (3, 0, 2434993, 1);
# /**
# * Orbis - Reward: 2434993
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5200000, 3, 0, 0); # JrSentinel
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3000000, 3, 0, 1); # Sentinel
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5200001, 3, 0, 2); # IceSentinel
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5200002, 3, 0, 3); # FireSentinel
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6400006, 3, 0, 4); # CrimsonBalrog
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210200, 3, 0, 5); # JrCellion
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210201, 3, 0, 6); # JrLioner
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210202, 3, 0, 7); # JrGrupin
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6230401, 3, 0, 8); # JrLucida
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 3, 0, 9); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5120001, 3, 0, 10); # Cellion
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5120002, 3, 0, 11); # Lioner
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5120003, 3, 0, 12); # Grupin
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7130000, 3, 0, 13); # Lucida
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8220000, 3, 0, 14); # Eliza
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 0, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230106, 3, 0, 15); # LunarPixie
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5120000, 3, 0, 16); # LusterPixie
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230200, 3, 0, 17); # StarPixie
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300038, 3, 0, 18); # GhostPixie
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300039, 3, 0, 19); # PapaPixie
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 0, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4130102, 3, 0, 20); # DarkNependeath
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230105, 3, 0, 21); # Nependeath
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5160003, 3, 0, 22); # GoldenScorpie
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6160002, 3, 0, 23); # GoldenMammoth
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6160003, 3, 0, 24); # Xerxes
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (3, 1, 2433943, 500);


# /**
# * El Nath 1 - Reward: 2433943 (x500)
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5300000, 3, 1, 0); # Leatty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5300001, 3, 1, 1); # DarkLeatty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5400000, 3, 1, 2); # JrPepe
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9200018, 3, 1, 3); # JrYeti
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9800010, 3, 1, 4); # DarkJrYeti
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 1, 1, 2434958, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6230200, 3, 1, 5); # DarkPepe
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5130104, 3, 1, 6); # Hector
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5140000, 3, 1, 7); # WhiteFang
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6090001, 3, 1, 8); # SnowWitch
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 3, 1, 9); # Empty
# Reward ID: 2434958
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6300000, 3, 1, 10); # Yeti
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6400000, 3, 1, 11); # DarkYeti
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6130102, 3, 1, 12); # SeperatedPepe
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7130102, 3, 1, 13); # YetiAndPepe
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8140100, 3, 1, 14); # DarkYetiAndPepe
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 1, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8220001, 3, 1, 15); # Snowman
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7130200, 3, 1, 16); # Werewolf
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8140000, 3, 1, 17); # Lycanthrope
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6090000, 3, 1, 18); # Riche
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 3, 1, 19); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 1, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300276, 3, 1, 20); # HoblinHector
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300277, 3, 1, 21); # EliteHoblin
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300279, 3, 1, 22); # CombatHoblin
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300280, 3, 1, 23); # FerociousHoblin
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300281, 3, 1, 24); # Rex
# Reward ID: 2434932
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (3, 2, 3017003, 1);

# /**
# * El Nath 2 - Reward: 3017003
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 2, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210000, 3, 2, 0); # CrockyTheGatekeeper
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210001, 3, 2, 1); # Reindeer
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210002, 3, 2, 2); # BloodReindeer
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210003, 3, 2, 3); # Bearwolf
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210004, 3, 2, 4); # GreyVulture
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 2, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8211003, 3, 2, 5); # GoldenBee
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8211000, 3, 2, 6); # FrozenRose
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8211002, 3, 2, 7); # GardenGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210005, 3, 2, 8); # CastleGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8211004, 3, 2, 9); # KingCastleGolem
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 2, 2, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210006, 3, 2, 10); # PrisonGuardBoar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210007, 3, 2, 11); # PrisonGuardRhino
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210013, 3, 2, 12); # PrisonGuardAni
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8211001, 3, 2, 13); # KeymasterRousseau
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8840000, 3, 2, 14); # VonLeon
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 2, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230107, 3, 2, 15); # Flyeye
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5130107, 3, 2, 16); # CoolieZombie
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5130108, 3, 2, 17); # MinerZombie
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7130001, 3, 2, 18); # Cerebes
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8140500, 3, 2, 19); # Bain
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 2, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8800013, 3, 2, 20); # Punco
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6400004, 3, 2, 21); # Opachu
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8800114, 3, 2, 22); # ChaosKusko
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8800002, 3, 2, 23); # Zakum
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8800102, 3, 2, 24); # ChaosZakum
# Reward ID: 2434931
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (4, 0, 3017004, 1);


# /**
# * Ludas Lake (no Near Ludas Lake) - Reward: 3017004
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230400, 4, 0, 0); # DrummingBunny
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3000005, 4, 0, 1); # BrownTeddy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3110101, 4, 0, 2); # PinkTeddy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210203, 4, 0, 3); # PandaTeddy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210204, 4, 0, 4); # Roloduck
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210206, 4, 0, 5); # Helly
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230303, 4, 0, 6); # Propelly
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230304, 4, 0, 7); # Planey
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230307, 4, 0, 8); # Chirppy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230308, 4, 0, 9); # Tweeter
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230302, 4, 0, 10); # Bloctopus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230103, 4, 0, 11); # KingBloctopus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4130103, 4, 0, 12); # Rombot
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230109, 4, 0, 13); # BlockGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230110, 4, 0, 14); # KingBlockGolem
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 0, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600604, 4, 0, 15); # ToyTrojan
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600605, 4, 0, 16); # Robo
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600606, 4, 0, 17); # MasterRobo
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600613, 4, 0, 18); # Timer
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 4, 0, 19); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 0, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3110102, 4, 0, 20); # Ratz
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210205, 4, 0, 21); # BlackRatz
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230103, 4, 0, 22); # Trixter
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300014, 4, 0, 23); # DarkEyeFromDimension
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300012, 4, 0, 24); # Alishar
# Reward ID: 2434931
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (4, 1, 2450042, 3);


# /**
# * Ludas Lake 2 - Reward: 2450042 (x3)
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600608, 4, 1, 0); # Tick
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600609, 4, 1, 1); # TickTock
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600610, 4, 1, 2); # Chronos
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600611, 4, 1, 3); # PlatoonChronos
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600612, 4, 1, 4); # MasterChronos
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 1, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600620, 4, 1, 5); # Buffy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600621, 4, 1, 6); # LazyBuffy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600618, 4, 1, 7); # Buffoon
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600619, 4, 1, 8); # DeepBuffoon
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 4, 1, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600616, 4, 1, 10); # SoulTeddy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600617, 4, 1, 11); # MasterSoulTeddy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600614, 4, 1, 12); # DeathTeddy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600615, 4, 1, 13); # MasterDeathTeddy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 4, 1, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 1, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600623, 4, 1, 15); # GhostPirate
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600624, 4, 1, 16); # DualGhostPirate
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600627, 4, 1, 17); # SpiritViking
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600628, 4, 1, 18); # GiganticSpiritViking
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 4, 1, 19); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 1, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600629, 4, 1, 20); # PhantomWatch
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600630, 4, 1, 21); # GrimPhantomWatch
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8160000, 4, 1, 22); # Gatekepper
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8170000, 4, 1, 23); # Thantos
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600631, 4, 1, 24); # Paplatus
# Reward ID: 2434931
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (4, 2, 2432158, 3);

# /**
# * Ellin Forest - Reward: 2432158 (x3)
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 2, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4250000, 4, 2, 0); # MossySnail
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4250001, 4, 2, 1); # TreeRod
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5250000, 4, 2, 2); # MossyMushroom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5250001, 4, 2, 3); # StoneBug
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 4, 2, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 2, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5250002, 4, 2, 5); # PrimitiveBoar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5250003, 4, 2, 6); # ViolentPrimitiveBoar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5250004, 4, 2, 7); # ChaosCrimsonQueen
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 4, 2, 8); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 4, 2, 9); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 2, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8230003, 4, 2, 10); # EvilPoacher
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8230004, 4, 2, 11); # PoachersHawk
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8230005, 4, 2, 12); # HiddenGraveRobber
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8230006, 4, 2, 13); # GraveRobbersHuntingDog
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 4, 2, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 2, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300172, 4, 2, 15); # PoisonedLordTree
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300173, 4, 2, 16); # PoisonedStoneBug
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300174, 4, 2, 17); # PoisonFlower
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300175, 4, 2, 18); # PoisonedSpright
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300176, 4, 2, 19); # PoisonGolem
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 2, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8230007, 4, 2, 20); # NeonBat
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8230009, 4, 2, 21); # PeaceSpirit
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5250005, 4, 2, 22); # AncientFairy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5250006, 4, 2, 23); # ShiningFairy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5250007, 4, 2, 24); # Ephenia
# Reward ID: 2434932
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (5, 0, 3017005, 1);

# * Aqua Road - Reward: 3017005
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (5, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230105, 5, 0, 0); # Seacle
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230106, 5, 0, 1); # Cico
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210450, 5, 0, 2); # ScubaPepe
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230405, 5, 0, 3); # JrSeal
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230108, 5, 0, 4); # Pinboom
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (5, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230109, 5, 0, 5); # BubbleFish
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230200, 5, 0, 6); # FlowerFish
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230104, 5, 0, 7); # MaskFish
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230200, 5, 0, 8); # Poopa
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230201, 5, 0, 9); # PoisonPoopa
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (5, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230107, 5, 0, 10); # Krappy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3000006, 5, 0, 11); # Krip
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4220000, 5, 0, 12); # Seruf
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230123, 5, 0, 13); # Sparker
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230124, 5, 0, 14); # Freezer
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (5, 0, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8140555, 5, 0, 15); # BombingFishHouse
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7130020, 5, 0, 16); # Goby
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8140600, 5, 0, 17); # BoneFish
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8141300, 5, 0, 18); # Squid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8142100, 5, 0, 19); # RisellSquid
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (5, 0, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8150100, 5, 0, 20); # Shark
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8150101, 5, 0, 21); # ColdShark
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8510000, 5, 0, 22); # Pianus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8510100, 5, 0, 23); # BloodyBoom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 5, 0, 24); # Empty
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (6, 0, 3017006, 1);
# /**
# /**
# * Nihal Desert - Reward: 3017006
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2100103, 6, 0, 0); # Cactus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2100104, 6, 0, 1); # RoyalCactus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600105, 6, 0, 2); # Deo
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600100, 6, 0, 3); # WhiteDesertRabbit
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600101, 6, 0, 4); # BrownDesertRabbit
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600106, 6, 0, 5); # Bellamoa
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600107, 6, 0, 6); # EarPlugPlead
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600108, 6, 0, 7); # ScarfPlead
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600109, 6, 0, 8); # Meerkat
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600114, 6, 0, 9); # Kiyo
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600110, 6, 0, 10); # SandRat
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600112, 6, 0, 11); # Scorpion
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600113, 6, 0, 12); # SandDwarf
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600115, 6, 0, 13); # DarkSandDwarf
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600117, 6, 0, 14); # DesertGiant
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 0, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600122, 6, 0, 15); # Horus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600123, 6, 0, 16); # AdvisorMummy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9305408, 6, 0, 17); # PharaohYeti
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9305413, 6, 0, 18); # PharaohSnake
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9305422, 6, 0, 19); # PharaohMummy
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 0, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2500500, 6, 0, 20); # DeadlyAlter
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2500100, 6, 0, 21); # SpearmanSkeleknight
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2500200, 6, 0, 22); # Bloodfang
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8870100, 6, 0, 23); # SilverHairedHilla
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300627, 6, 0, 24); # GiantDarkheart
# Reward ID: 2434932
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (6, 1, 2028048, 1);

# /**
# * Magatia - Reward: 2028048
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600500, 6, 1, 0); # CubeSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600502, 6, 1, 1); # Rumo
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600503, 6, 1, 2); # TripleRumo
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600504, 6, 1, 3); # Rurumo
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 6, 1, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 1, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600505, 6, 1, 5); # IronMutae
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600506, 6, 1, 6); # ReinforcedIronMutae
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600507, 6, 1, 7); # MithrilMutae
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600508, 6, 1, 8); # ReinforcedMithrilMutae
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 6, 1, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600510, 6, 1, 10); # Homuns
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600511, 6, 1, 11); # Homunculus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600512, 6, 1, 12); # Homunscullo
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 6, 1, 13); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 6, 1, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 1, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600515, 6, 1, 15); # Roid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600516, 6, 1, 16); # NeoHuroid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600509, 6, 1, 17); # SecurityCamera
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600518, 6, 1, 18); # DeetandRoi
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600517, 6, 1, 19); # DRoy
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 1, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600514, 6, 1, 20); # Chimera
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600513, 6, 1, 21); # Saitie
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300141, 6, 1, 22); # HomunofClosedLaboratory
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300139, 6, 1, 23); # Frankenroid
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300140, 6, 1, 24); # Angry
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (7, 0, 3017007, 1);

# /**
# * Mu Lung Garden 1 - Reward: 3017007
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600306, 7, 0, 0); # Chipmunk
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600307, 7, 0, 1); # RedPorky
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600308, 7, 0, 2); # BlackPorky
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 0, 3); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 0, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600313, 7, 0, 5); # Reindeer
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600314, 7, 0, 6); # PeachMonkey
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600302, 7, 0, 7); # TheBookGhost
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600303, 7, 0, 8); # SageCat
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600305, 7, 0, 9); # KingSageCat
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600309, 7, 0, 10); # BlueFlowerSerpent
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600310, 7, 0, 11); # RedFlowerSerpent
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600311, 7, 0, 12); # GiantCentipede
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 0, 13); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 0, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 0, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600317, 7, 0, 15); # Jar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600318, 7, 0, 16); # GinsengJar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600319, 7, 0, 17); # BellflowerRoot
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600320, 7, 0, 18); # SrBellflowerRoot
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600321, 7, 0, 19); # GiganticBellflowerRoot
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 0, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600312, 7, 0, 20); # Grizzly
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600315, 7, 0, 21); # Panda
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600316, 7, 0, 22); # TaeRoon
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300215, 7, 0, 23); # MuGong
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 0, 24); # Empty
# Reward ID: 2434932
# 


insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (7, 1, 2050004, 100);

# /**
# * Mu Lung Garden 2 - Reward: 2050004 (x100)
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144000, 7, 1, 0); # WildMonkey
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144001, 7, 1, 1); # MamaMonkey
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144002, 7, 1, 2); # TeenyWhiteMonkey
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144003, 7, 1, 3); # MeanMamaMonkey
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 1, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 1, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144004, 7, 1, 5); # BlueGoblin
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144005, 7, 1, 6); # RedGoblin
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144006, 7, 1, 7); # StoneGoblin
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 1, 8); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 1, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8800200, 7, 1, 10); # Ravana
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144007, 7, 1, 11); # StrongStoneGoblin
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144008, 7, 1, 12); # Ganapati
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 1, 13); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 1, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 1, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600300, 7, 1, 15); # StrawTargetDummy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600301, 7, 1, 16); # WoodenTargetDummy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600304, 7, 1, 17); # MasterDummy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6090002, 7, 1, 18); # BambooWarrior
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 1, 19); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 1, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600326, 7, 1, 20); # LordPirate
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600322, 7, 1, 21); # MrAlli
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600323, 7, 1, 22); # Kru
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600324, 7, 1, 23); # Captain
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 7, 1, 24); # Empty
# Reward ID: 2434931
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (8, 0, 2436272, 1);

# /**
# * Minar Forest - Reward: 2436272
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600000, 8, 0, 0); # Beetle
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600001, 8, 0, 1); # DualBeetle
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600003, 8, 0, 2); # Rash
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600004, 8, 0, 3); # DarkRash
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 0, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600002, 8, 0, 5); # Hankie
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600005, 8, 0, 6); # Hobi
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600006, 8, 0, 7); # GreenHobi
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300479, 8, 0, 8); # MasterHoblin
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 0, 9); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600008, 8, 0, 10); # Harp
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600009, 8, 0, 11); # BloodHarp
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300480, 8, 0, 12); # MasterHarp
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600007, 8, 0, 13); # Griffey
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 0, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 0, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600010, 8, 0, 15); # BlackKentaurus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600011, 8, 0, 16); # RedKentaurus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600012, 8, 0, 17); # BlueKentaurus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600015, 8, 0, 18); # KentaurusKing
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 0, 19); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 0, 4, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600013, 8, 0, 20); # Birk
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600014, 8, 0, 21); # DualBirk
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300481, 8, 0, 22); # MasterBirk
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600022, 8, 0, 23); # Manon
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 0, 24); # Empty
# Reward ID: 2434930
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (8, 1, 3017008, 1);

# /**
# * Dragon Forest - Reward: 3017008
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600016, 8, 1, 0); # BlueDragonTurtle
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600017, 8, 1, 1); # RedDragonTurtle
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600018, 8, 1, 2); # Rexton
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600019, 8, 1, 3); # Brexton
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 1, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 1, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600020, 8, 1, 5); # JrNewtie
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600021, 8, 1, 6); # NestGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600026, 8, 1, 7); # GreenCornian
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600027, 8, 1, 8); # DarkCornian
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 1, 9); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 1, 2, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600023, 8, 1, 10); # RedWyvern
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600024, 8, 1, 11); # BlueWyvern
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600025, 8, 1, 12); # DarkWyvern
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600030, 8, 1, 13); # Leviathan
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 1, 14); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 1, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8300000, 8, 1, 15); # SoaringHawk
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8300001, 8, 1, 16); # SoaringEagle
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8300005, 8, 1, 17); # SoaringGriffey
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8300006, 8, 1, 18); # Dragonoir
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8300007, 8, 1, 19); # Dragon
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 1, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8810018, 8, 1, 20); # Horntail
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8810118, 8, 1, 21); # ChaosHorntail
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600028, 8, 1, 22); # Skelegon
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600029, 8, 1, 23); # Skelosaurus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 1, 24); # Empty
# Reward ID: 2434932
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (8, 2, 2450042, 3);

# /**
# * Kritias - Reward: 2450042 (x3)
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 2, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630000, 8, 2, 0); # FrozenSolitude
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630005, 8, 2, 1); # BurningSolitude
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630010, 8, 2, 2); # PermeatingSolitude
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 2, 3); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 2, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 2, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630021, 8, 2, 5); # FrozenTerror
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630026, 8, 2, 6); # BurningTerror
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630031, 8, 2, 7); # PermeatingTerror
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 2, 8); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 2, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 2, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630022, 8, 2, 10); # FrozenRage
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630027, 8, 2, 11); # BurningRage
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630032, 8, 2, 12); # PermeatingRage
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 2, 13); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 2, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 2, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630023, 8, 2, 15); # FrozenAnxiety
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630028, 8, 2, 16); # BurningAnxiety
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630033, 8, 2, 17); # PermeatingAnxiety
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630035, 8, 2, 18); # CorruptedBasicMagician
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 8, 2, 19); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 2, 4, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630024, 8, 2, 20); # FrozenVanity
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630029, 8, 2, 21); # BurningVainity
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630034, 8, 2, 22); # PermeatingVainity
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630036, 8, 2, 23); # CorruptedIntermediateMagician
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630037, 8, 2, 24); # CorruptedAdvancedMagician
# Reward ID: 2434930
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (9, 0, 2432300, 1);

# /**
# * Gate of the Past (Temple of Time) - Reward: 2432300
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600701, 9, 0, 0); # MemoryMonk
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600702, 9, 0, 1); # MemoryMonkTrainee
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600703, 9, 0, 2); # MemoryGuardian
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600704, 9, 0, 3); # ChiefMemoryGuardian
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600700, 9, 0, 4); # EyeofTime
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600706, 9, 0, 5); # QualmMonk
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600707, 9, 0, 6); # QualmMonkTrainee
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600708, 9, 0, 7); # QualmGuardian
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600709, 9, 0, 8); # ChiefQualmGuardian
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600705, 9, 0, 9); # Dodo
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600711, 9, 0, 10); # OblivionMonk
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600712, 9, 0, 11); # OblivionMonkTrainee
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600713, 9, 0, 12); # OblivionGuard
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600714, 9, 0, 13); # ChiefOblivionGuardian
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600710, 9, 0, 14); # Lilynouch
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 0, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8860000, 9, 0, 15); # Arkarium
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8220020, 9, 0, 16); # CorruptedChiefMemoryGuardian
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8220021, 9, 0, 17); # CorruptedTimeMonkTrainee
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8860002, 9, 0, 18); # NetherworldMonk
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 9, 0, 19); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 0, 4, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8820000, 9, 0, 20); # PinkBean
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8820100, 9, 0, 21); # ChaosPinkBean
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600715, 9, 0, 22); # Lyka
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8820003, 9, 0, 23); # SolomontheWise
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8820006, 9, 0, 24); # Munin
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (9, 1, 2432158, 1);

# /**
# * Temple of Time: Gate to the Future - Reward: 2432158
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8600000, 9, 1, 0); # MutantSnail
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8600001, 9, 1, 1); # MutantOrangeMushroom
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8600002, 9, 1, 2); # MutantSlime
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8600003, 9, 1, 3); # MutantRibbonPig
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 9, 1, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 1, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620000, 9, 1, 5); # SwollenStump
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620001, 9, 1, 6); # SwollenDarkStump
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620002, 9, 1, 7); # SwollenAxeStump
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620012, 9, 1, 8); # GhostwoodStumpy
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 9, 1, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620003, 9, 1, 10); # PillagingWildBoar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620004, 9, 1, 11); # PillagingIronBoar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620005, 9, 1, 12); # PillagingFireBoars
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620006, 9, 1, 13); # SinisterWoodenMask
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620007, 9, 1, 14); # SinisterRockyMask
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 1, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620008, 9, 1, 15); # SinisterSteelMask
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620009, 9, 1, 16); # AncientGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620010, 9, 1, 17); # AncientDarkGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620011, 9, 1, 18); # AncientMixedGolem
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 9, 1, 19); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 1, 4, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8600004, 9, 1, 20); # MutantTino
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8600005, 9, 1, 21); # MutantTiru
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8600006, 9, 1, 22); # MutantTiguru
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 9, 1, 23); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 9, 1, 24); # Empty
# Reward ID: 2434929
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (9, 2, 3017009, 1);

# /**
# * Temple of Time 1 - Reward: 3017009
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 2, 0, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610000, 9, 2, 0); # Dawn
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610001, 9, 2, 1); # Blaze
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610002, 9, 2, 2); # Wind
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610003, 9, 2, 3); # Night
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610004, 9, 2, 4); # Thunder
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 2, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610005, 9, 2, 5); # OfficialKnightA
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610006, 9, 2, 6); # OfficialKnightB
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610007, 9, 2, 7); # OfficialKnightC
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610008, 9, 2, 8); # OfficialKnightD
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610009, 9, 2, 9); # OfficialKnightE
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 2, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610010, 9, 2, 10); # AdvancedKnightA
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610011, 9, 2, 11); # AdvancedKnightB
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610012, 9, 2, 12); # AdvancedKnightC
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610013, 9, 2, 13); # AdvancedKnightD
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610014, 9, 2, 14); # AdvancedKnightE
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 2, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8850000, 9, 2, 15); # Mihile
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8850001, 9, 2, 16); # Oz
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8850002, 9, 2, 17); # Irena
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8850003, 9, 2, 18); # Eckhart
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8850004, 9, 2, 19); # Hawkeye
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 2, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8850011, 9, 2, 20); # Cygnus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8850013, 9, 2, 21); # Shinsoo
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610015, 9, 2, 22); # Ifrit
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 9, 2, 23); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 9, 2, 24); # Empty
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (10, 0, 2434993, 1);

# /**
# * Grandis 1 - Reward: 2434993
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400200, 10, 0, 0); # Caterpillar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400201, 10, 0, 1); # LadyBug
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400202, 10, 0, 2); # Sparrow
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400203, 10, 0, 3); # BalloonMouse
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400204, 10, 0, 4); # RedPoisonFrog
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 0, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400205, 10, 0, 5); # GreenPoisonFrog
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400206, 10, 0, 6); # DelinquentChick
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400207, 10, 0, 7); # PunkChick
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400208, 10, 0, 8); # ManeFurHyena
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400209, 10, 0, 9); # FangHyena
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 0, 2, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400000, 10, 0, 10); # SleepyGrobbler
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400001, 10, 0, 11); # AlertGrobbler
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400002, 10, 0, 12); # GrumpyGrobbler
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400003, 10, 0, 13); # LimestoneTokka
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400004, 10, 0, 14); # AmethystTokka
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 0, 3, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400005, 10, 0, 15); # Laloong
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400006, 10, 0, 16); # Kaloong
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400007, 10, 0, 17); # NefariousMonkInitiate
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400008, 10, 0, 18); # NefariousMonkApprentice
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400009, 10, 0, 19); # NefariousMonkMaster
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 0, 4, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400010, 10, 0, 20); # HereticMonkInitiate
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400011, 10, 0, 21); # HereticMonkApprentice
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400012, 10, 0, 22); # HereticLeader
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400013, 10, 0, 23); # OnyxStonegar
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400014, 10, 0, 24); # Gravi
# Reward ID: 2434930
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (10, 1, 3017010, 1);
# /**
# * Grandis 2 - Reward: 3017010
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400100, 10, 1, 0); # BlueSpeeyor
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400101, 10, 1, 1); # RedSpeeyor
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400102, 10, 1, 2); # YellowSpeeyor
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 10, 1, 3); # Empty
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (0, 10, 1, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 1, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400103, 10, 1, 5); # Dinogoth
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400104, 10, 1, 6); # Dinoram
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400105, 10, 1, 7); # Dinodon
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400106, 10, 1, 8); # SpecterBattleHound
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400107, 10, 1, 9); # SpecterTamer
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400108, 10, 1, 10); # SpecterMiner
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400109, 10, 1, 11); # SpecterShieldbearer
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400110, 10, 1, 12); # GuerrillaSpecter
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400111, 10, 1, 13); # PowerSpecter
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400112, 10, 1, 14); # SpecterEngineer
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 1, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400113, 10, 1, 15); # WarriorSpecter
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400114, 10, 1, 16); # MagicianSpecter
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400115, 10, 1, 17); # ReaperSpecter
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400118, 10, 1, 18); # PurpleReagentRock
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400116, 10, 1, 19); # RedTotemStaff
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 1, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2700029, 10, 1, 20); # Magnus
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8880004, 10, 1, 21); # Velderoth
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8880008, 10, 1, 22); # Treglow
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8880006, 10, 1, 23); # Victor
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400116, 10, 1, 24); # RedTotemStaff
# Reward ID: 2434932

