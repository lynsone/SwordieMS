DROP TABLE IF EXISTS damageskinsavedatas;
DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS macroSkills;
DROP TABLE IF EXISTS macros;
DROP TABLE IF EXISTS test;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS characters;
DROP TABLE IF EXISTS avatarData;
DROP TABLE IF EXISTS keymaps;
DROP TABLE IF EXISTS funckeymap;
DROP TABLE IF EXISTS characterStats;
DROP TABLE IF exists hairEquips;
DROP TABLE IF EXISTS unseenEquips;
DROP TABLE IF EXISTS petIDs;
DROP TABLE IF EXISTS totems;
DROP TABLE IF EXISTS spSet;
DROP TABLE IF EXISTS extendSP;
DROP TABLE IF EXISTS nonCombatStatDayLimit;
DROP TABLE IF EXISTS systemtimes;
DROP TABLE IF EXISTS characterCards;
DROP TABLE IF EXISTS avatarLook;
DROP TABLE IF EXISTS sockets;
DROP TABLE IF EXISTS options;
DROP TABLE IF EXISTS equips;
DROP TABLE IF EXISTS petitems;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS inventories;
DROP TABLE IF EXISTS questProgressRequirements;
DROP TABLE IF EXISTS questProgressItemRequirements;
DROP TABLE IF EXISTS questProgressLevelRequirements;
DROP TABLE IF EXISTS questProgressMoneyRequirements;
DROP TABLE IF EXISTS questProgressMobRequirements;
DROP TABLE IF EXISTS questlists;
DROP TABLE IF EXISTS questManagers;
DROP TABLE IF EXISTS quests;
DROP TABLE IF EXISTS guildrequestors;
DROP TABLE IF EXISTS gradeNames;
DROP TABLE IF EXISTS guildmembers;
DROP TABLE IF EXISTS guildrequestors;
DROP TABLE IF EXISTS guildskills;
DROP TABLE IF EXISTS guildskill;
DROP TABLE IF EXISTS guilds;
DROP TABLE IF EXISTS filetimes;
DROP TABLE IF EXISTS trunks;

CREATE TABLE trunks(
	id int not null auto_increment,
    slotcount tinyint,
    money bigint,
    primary key (id)
);

CREATE TABLE filetimes (
	id bigint NOT NULL AUTO_INCREMENT,
    lowDateTime int,
    highDateTime int,
    PRIMARY KEY (id)
);

CREATE TABLE quests (
	id bigint NOT NULL AUTO_INCREMENT,
    qrKey int,
    qrValue VARCHAR(255),
    status int,
    completedTime bigint,
	PRIMARY KEY (id),
    FOREIGN KEY (completedTime) REFERENCES filetimes(id)
);

CREATE TABLE questManagers (
	id bigint NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(id)
);

CREATE TABLE questlists (
	questList_id bigint NOT NULL AUTO_INCREMENT,
	QuestManager_id bigint,
    questID int,
    fk_questID bigint,
    PRIMARY KEY (questList_id),
    FOREIGN KEY (QuestManager_id) REFERENCES questManagers(id)  on delete cascade,
    FOREIGN KEY (fk_questID) REFERENCES quests(id) 
);

CREATE TABLE questProgressRequirements (
	id bigint NOT NULL AUTO_INCREMENT,
    progressType VARCHAR(255),
	questID bigint,
    unitID int,
    requiredCount int,
    currentCount int,
    PRIMARY KEY (id),
    FOREIGN KEY (questID) REFERENCES quests(id)  on delete cascade
);

CREATE TABLE inventories (
	id int NOT NULL AUTO_INCREMENT,
    type int,
    slots tinyint,
    PRIMARY KEY (id)
);

CREATE TABLE items (
	id bigint NOT NULL AUTO_INCREMENT,
    inventoryId int, # item can be inside an inventory OR storage, so cannot be a foreign key :(
    trunkID int,
    itemId int,
    bagIndex int,
    cashItemSerialNumber bigint,
    dateExpire bigint,
    invType int,
    type int,
    isCash boolean,
    quantity int,
    owner varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE petitems (
	itemId bigint,
    name varchar(255),
    level tinyint,
    tameness smallint,
    repleteness tinyint,
    petAttribute smallint,
    petSkill int,
    dateDead bigint,
    remainLife int,
    attribute smallint,
    activeState tinyint,
    autoBuffSkill int,
    petHue int,
    giantRate smallint,
    primary key (itemId),
    foreign key (itemId) references items(id) on delete cascade,
    foreign key (dateDead) references filetimes(id)
);

CREATE TABLE equips (
	serialNumber bigint,
    itemId bigint,
    title varchar(255),
    equippedDate bigint,
    prevBonusExpRate int,
    ruc smallint,
    cuc smallint,
    iStr smallint,
    iDex smallint,
    iInt smallint,
    iLuk smallint,
    iMaxHp smallint,
    iMaxMp smallint,
    iPad smallint,
    iMad smallint,
    iPDD smallint,
    iMDD smallint,
    iAcc smallint,
    iEva smallint,
    iCraft smallint,
    iSpeed smallint,
    iJump smallint,
    attribute smallint,
    levelUpType smallint,
    level smallint,
    exp smallint,
    durability smallint,
    iuc smallint,
    iPvpDamage smallint,
    iReduceReq smallint,
    specialAttribute smallint,
    durabilityMax smallint,
    iIncReq smallint,
    growthEnchant smallint,
    psEnchant smallint,
    bdr smallint,
    imdr smallint,
    damR smallint,
    statR smallint,
    cuttable smallint,
    exGradeOption smallint,
    itemState smallint,
    grade smallint,
    chuc smallint,
    soulOptionId smallint,
    soulSocketId smallint,
    soulOption smallint,
    rStr smallint,
    rDex smallint,
    rInt smallint,
    rLuk smallint,
    rLevel smallint,
    rJob smallint,
    rPop smallint,
    specialGrade int,
    fixedPotential boolean,
    tradeBlock boolean,
    isOnly boolean,
    notSale boolean,
    attackSpeed int,
    price int,
    tuc int,
    charmEXP int,
    expireOnLogout boolean,
    setItemID int,
    exItem boolean,
    equipTradeBlock boolean,
    iSlot varchar(255),
    vSlot varchar(255),
    fixedGrade int,
    PRIMARY KEY (itemId),
    FOREIGN KEY (itemId) REFERENCES items(id) on delete cascade,
    FOREIGN KEY (equippedDate) REFERENCES filetimes(id)
);

CREATE TABLE options (
	id int NOT NULL AUTO_INCREMENT,
    equipID bigint,
    optionID int,
    PRIMARY KEY (id),
    FOREIGN KEY (equipID) REFERENCES equips(itemID) on delete cascade
);

CREATE TABLE sockets (
	id bigint NOT NULL AUTO_INCREMENT,
    equipID bigint,
    ord int,
    socketID int,
    PRIMARY KEY (id),
    FOREIGN KEY (equipID) REFERENCES equips(itemID) on delete cascade
);

CREATE TABLE avatarLook (
	id int NOT NULL AUTO_INCREMENT,
    gender int,
    skin int,
    face int,
    hair int,
    weaponStickerId int,
    weaponId int,
    subWeaponId int,
    job int,
    drawElfEar boolean,
    demonSlayerDefFaceAcc int,
    xenonDefFaceAcc int,
	beastTamerDefFaceAcc int,
    isZeroBetaLook boolean,
    mixedHairColor int,
    mixHairPercent int,
    ears int,
    tail int,
    PRIMARY KEY (id)
);

CREATE TABLE hairEquips (
	id int NOT NULL AUTO_INCREMENT,
    alId int,
    equipId int,
    PRIMARY KEY (id),
    FOREIGN KEY (alId) REFERENCES avatarlook(id) on delete cascade
);

CREATE TABLE unseenEquips (
	id int NOT NULL AUTO_INCREMENT,
    alId int,
    equipId int,
    PRIMARY KEY (id),
    FOREIGN KEY (alId) REFERENCES avatarlook(id) on delete cascade
);

CREATE TABLE petIDs (
	id int NOT NULL AUTO_INCREMENT,
    alId int,
    petId int,
    PRIMARY KEY (id),
    FOREIGN KEY (alId) REFERENCES avatarlook(id) on delete cascade
);

CREATE TABLE totems (
	id int NOT NULL AUTO_INCREMENT,
    alId int,
    totemId int,
    PRIMARY KEY (id),
    FOREIGN KEY (alId) REFERENCES avatarlook(id) on delete cascade
);

CREATE TABLE extendSP (
	id int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE spSet (
	id int NOT NULL AUTO_INCREMENT,
    extendSP_id int,
    jobLevel tinyint,
    sp int,
    PRIMARY KEY (id),
    FOREIGN KEY (extendSP_id) REFERENCES extendSP(id) on delete cascade
);

CREATE TABLE systemtimes (
	id int NOT NULL AUTO_INCREMENT,
    yr int,
    mnth int,
    PRIMARY KEY (id)
);

CREATE TABLE nonCombatStatDayLimit (
	id int NOT NULL AUTO_INCREMENT,
    charisma smallint,
    charm smallint,
    insight smallint,
    will smallint,
    craft smallint,
    sense smallint,
    ftLastUpdateCharmByCashPR bigint,
    charmByCashPR tinyint,
    PRIMARY KEY (id),
    FOREIGN KEY (ftLastUpdateCharmByCashPR) REFERENCES filetimes(id)
);

CREATE TABLE characterCards (
	id int NOT NULL AUTO_INCREMENT,
    characterId int,
    job int,
    level tinyint,
    PRIMARY KEY (id)
);

CREATE TABLE characterStats (
	id int NOT NULL AUTO_INCREMENT,
    characterId int,
    characterIdForLog int,
    worldIdForLog int,
    name VARCHAR(255),
    gender int,
    skin int,
    face int,
    hair int,
    mixBaseHairColor int,
    mixAddHairColor int,
    mixHairBaseProb int,
    level int,
    job int,
    str int,
    dex int,
    inte int,
    luk int,
    hp int, 
    maxHp int,
    mp int,
    maxMp int,
    ap int,
    sp int,
    exp long,
    pop int,
    money long,
    wp int,
    extendSP int,
    posMap long,
    portal int,
    subJob int,
    defFaceAcc int,
	fatigue int,
    lastFatigueUpdateTime int,
    charismaExp int,
    insightExp int,
    willExp int,
    craftExp int,
    senseExp int,
    charmExp int,
    nonCombatStatDayLimit int,
    pvpExp int,
    pvpGrade int,
    pvpPoint int,
    pvpModeLevel int,
    pvpModeType int,
    eventPoint int,
    albaActivityID int,
    albaStartTime bigint,
    albaDuration int,
    albaSpecialReward int,
    burning boolean,
    characterCard int,
    accountLastLogout int,
    lastLogout bigint,
    gachExp int,
    PRIMARY KEY (id),
    FOREIGN KEY (extendSP) REFERENCES extendSP(id),
    FOREIGN KEY (nonCombatStatDayLimit) REFERENCES nonCombatStatDayLimit(id),
    FOREIGN KEY (albaStartTime) REFERENCES filetimes(id),
    FOREIGN KEY (characterCard) REFERENCES characterCards(id),
    FOREIGN KEY (accountLastLogout) REFERENCES systemtimes(id),
    FOREIGN KEY (lastLogout) REFERENCES filetimes(id)
);

CREATE TABLE avatarData (
	id int NOT NULL AUTO_INCREMENT,
    characterStat int,
    avatarLook int,
    zeroAvatarLook int,
    PRIMARY KEY (id),
    FOREIGN KEY (characterStat) REFERENCES characterstats(id),
    FOREIGN KEY (avatarLook) REFERENCES avatarlook(id),
    FOREIGN KEY (zeroAvatarLook) REFERENCES avatarlook(id)
);

CREATE TABLE funckeymap (
	id int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE keymaps (
	id int NOT NULL AUTO_INCREMENT,
    fkMapId int,
    idx int,
    type tinyint,
    val int,
    PRIMARY KEY (id),
    FOREIGN KEY (fkMapId) REFERENCES funckeymap(id)
);


CREATE TABLE guilds (
	id int NOT NULL AUTO_INCREMENT,
    name varchar(255),
    leaderID int,
    worldID int,
    markBg int,
    markBgColor int,
    mark int,
    markColor int,
    maxMembers int,
    notice varchar(255),
    points int,
    seasonPoints int,
    allianceID int,
    level int,
    rank int,
    ggp int,
    appliable boolean,
    joinSetting int,
    reqLevel int,
    PRIMARY KEY (id)    
);

CREATE TABLE characters (
	id int NOT NULL AUTO_INCREMENT,
    accID int,
	avatarData int,
    equippedInventory int,
    equipInventory int,
    consumeInventory int,
    etcInventory int,
    installInventory int,
    cashInventory int,
    funcKeyMap_id int,
    fieldID int,
    questManager bigint,
    guild int,
	PRIMARY KEY (id),
    FOREIGN KEY (avatarData) REFERENCES avatarData(id) ON DELETE CASCADE,
    FOREIGN KEY (equippedInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (equipInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (consumeInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (etcInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (installInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (cashInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (funcKeyMap_id) REFERENCES funckeymap(id) ON DELETE CASCADE,
    FOREIGN KEY (questManager) REFERENCES questmanagers(id) ON DELETE CASCADE,
    FOREIGN KEY (guild) REFERENCES guilds(id)
);

CREATE TABLE GuildSkill (
	id int not null auto_increment,
    skillID int,
    level int,
    expireDate bigint,
    buyCharacterName varchar(255),
    extendCharacterName varchar(255),
    primary key (id),
    foreign key (expireDate) references filetimes(id) on delete cascade
);

CREATE TABLE guildskills (
	guildSkill_id int not null auto_increment,
    skills_id int,
    Guild_id int,
    skillID int,
    fk_GuildSkillID int,
    primary key (guildSkill_id),
	foreign key (Guild_id) references guilds(id) on delete cascade,
    FOREIGN KEY (fk_GuildSkillID) REFERENCES GuildSkill(id) on delete cascade
);

CREATE TABLE guildmembers (
	id int NOT NULL AUTO_INCREMENT,
    charID int,
    guildID int,
    grade int,
    allianceGrade int,
    commitment int,
    dayCommitment int,
    igp int,
    commitmentIncTime bigint,
    name varchar(255),
    job int,
    level int,
    loggedIn boolean,
	PRIMARY KEY (id),
    FOREIGN KEY (guildID) REFERENCES guilds(id),
    FOREIGN KEY (commitmentIncTime) REFERENCES filetimes(id) on delete cascade
);

CREATE TABLE guildrequestors (
	id int NOT NULL AUTO_INCREMENT,
    requestors_id int,
    charID int,
    guildID int,
    name varchar(255),
    job int,
    level int,
    loggedIn boolean,
	PRIMARY KEY (id),
    FOREIGN KEY (guildID) REFERENCES guilds(id) on delete cascade
);

CREATE TABLE gradeNames (
	id int NOT NULL AUTO_INCREMENT,
	gradeName varchar(255),
    guildID int,
    PRIMARY KEY (id),
    FOREIGN KEY (guildID) references guilds(id) on delete cascade
);


CREATE TABLE skills (
	id int NOT NULL AUTO_INCREMENT,
    charId int,
    skillId int,
    rootId int,
    maxLevel int,
    currentLevel int,
    masterLevel int,
    PRIMARY KEY (id),
    FOREIGN KEY (charId) REFERENCES characters(id)  on delete cascade
);

CREATE TABLE macros (
	id bigint not null auto_increment,
    charID int,
    muted boolean,
    name varchar(255),
    primary key (id),
    foreign key (charID) references characters(id)
);

CREATE TABLE macroSkills (
	id bigint not null auto_increment,
    orderCol int,
    skillID int,
    macroID bigint,
    primary key (id),
    foreign key (macroID) references macros(id) on delete cascade
);

CREATE TABLE accounts (
	id int NOT NULL AUTO_INCREMENT,
	username VARCHAR(255),
	password VARCHAR(255),
	pic VARCHAR(255),
	mac VARCHAR(255),
	gmLevel int DEFAULT 0,
	accountTypeMask int DEFAULT 2,
	age int DEFAULT 0,
	vipGrade int DEFAULT 0,
	nBlockReason int DEFAULT 0,
	gender tinyint DEFAULT 0,
	msg2 tinyint DEFAULT 0,
	purchaseExp tinyint DEFAULT 0,
	pBlockReason tinyint DEFAULT 3,
	chatUnblockDate long,
	hasCensoredNxLoginID boolean DEFAULT 0,
	gradeCode tinyint DEFAULT 0,
	censoredNxLoginID VARCHAR(255),
	characterSlots int DEFAULT 4,
	creationDate long,
    lastLoggedIn varchar(255),
    trunkID int,
	PRIMARY KEY (id),
    foreign key (trunkID) references trunks(id)
);

CREATE TABLE damageskinsavedatas (
	id bigint not null auto_increment,
    damageSkinID int,
    itemID int,
    notSave boolean,
    description varchar(255),
    accID int,
    primary key (id),
    foreign key (accID) references accounts(id) on delete cascade
);

CREATE TABLE friends (
	id int not null auto_increment,
    ownerID int,
    ownerAccID int,
    friendID int,
    friendAccountID int,
    name varchar(255),
    flag tinyint,
    groupName varchar(255),
    mobile tinyint,
    nickname varchar(255),
    memo varchar(255),
    primary key (id)   
);


INSERT INTO `accounts` (`username`, `password`, `gmLevel`, `chatUnblockDate`, `creationDate`, `pic`, `characterSlots`) VALUES ('admin', 'admin', '7', '0', '0', '111111', '40');
INSERT INTO `accounts` (`username`, `password`, `gmLevel`, `chatUnblockDate`, `creationDate`, `pic`, `characterSlots`) VALUES ('asura', 'admin', '7', '0', '0', '111111', '40');
INSERT INTO `accounts` (`username`, `password`, `gmLevel`, `chatUnblockDate`, `creationDate`, `pic`, `characterSlots`) VALUES ('maigal', 'admin', '7', '0', '0', '111111', '40');