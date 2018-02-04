DROP TABLE IF EXISTS accounts;
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
DROP TABLE IF EXISTS options;
DROP TABLE IF EXISTS equips;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS inventories;
DROP TABLE IF EXISTS filetimes;

CREATE TABLE filetimes (
	id int NOT NULL AUTO_INCREMENT,
    lowDateTime int,
    highDateTime int,
    PRIMARY KEY (id)
);


CREATE TABLE inventories (
	id int NOT NULL AUTO_INCREMENT,
    type int,
    slots tinyint,
    PRIMARY KEY (id)
);

CREATE TABLE items (
	id bigint NOT NULL AUTO_INCREMENT,
    inventoryId int,
    itemId int,
    bagIndex int,
    cashItemSerialNumber bigint,
    dateExpire int,
    invType int,
    type int,
    isCash boolean,
    quantity int,
    owner varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (inventoryId) REFERENCES inventories(id) ON DELETE CASCADE
);

CREATE TABLE equips (
	serialNumber bigint,
    itemId bigint,
    title varchar(255),
    equippedDate int,
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
    FOREIGN KEY (itemId) REFERENCES items(id),
    FOREIGN KEY (equippedDate) REFERENCES filetimes(id)
);

CREATE TABLE options (
	id int NOT NULL AUTO_INCREMENT,
    equipId bigint,
    optionId int,
    PRIMARY KEY (id),
    FOREIGN KEY (equipId) REFERENCES equips(itemId)
);

CREATE TABLE avatarLook (
	id int NOT NULL AUTO_INCREMENT,
    gender int,
	secondGender int,
    skin int,
	secondSkin int,
    face int,
	secondFace int,
    hair int,
	secondHair int,
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
    FOREIGN KEY (alId) REFERENCES avatarlook(id)
);

CREATE TABLE unseenEquips (
	id int NOT NULL AUTO_INCREMENT,
    alId int,
    equipId int,
    PRIMARY KEY (id),
    FOREIGN KEY (alId) REFERENCES avatarlook(id)
);

CREATE TABLE petIDs (
	id int NOT NULL AUTO_INCREMENT,
    alId int,
    petId int,
    PRIMARY KEY (id),
    FOREIGN KEY (alId) REFERENCES avatarlook(id)
);

CREATE TABLE totems (
	id int NOT NULL AUTO_INCREMENT,
    alId int,
    totemId int,
    PRIMARY KEY (id),
    FOREIGN KEY (alId) REFERENCES avatarlook(id)
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
    FOREIGN KEY (extendSP_id) REFERENCES extendSP(id)
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
    ftLastUpdateCharmByCashPR int,
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
    albaStartTime int,
    albaDuration int,
    albaSpecialReward int,
    burning boolean,
    characterCard int,
    accountLastLogout int,
    lastLogout int,
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
    PRIMARY KEY (id),
    FOREIGN KEY (characterStat) REFERENCES characterstats(id),
    FOREIGN KEY (avatarLook) REFERENCES avatarlook(id)
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


CREATE TABLE characters (
	id int NOT NULL AUTO_INCREMENT,
    accId int,
	avatarData int,
    equippedInventory int,
    equipInventory int,
    consumeInventory int,
    etcInventory int,
    installInventory int,
    cashInventory int,
    funcKeyMap_id int,
    fieldID int,
	PRIMARY KEY (id),
    FOREIGN KEY (avatarData) REFERENCES avatarData(id) ON DELETE CASCADE,
    FOREIGN KEY (equippedInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (equipInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (consumeInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (etcInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (installInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (cashInventory) REFERENCES inventories(id) ON DELETE CASCADE,
    FOREIGN KEY (funcKeyMap_id) REFERENCES funckeymap(id) ON DELETE CASCADE
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
    FOREIGN KEY (charId) REFERENCES characters(id) ON DELETE CASCADE
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
	PRIMARY KEY (id)
);

#CREATE TABLE test (
#	id int NOT NULL AUTO_INCREMENT,
#    userId int,
#    val int,
#    PRIMARY KEY (id),
#    FOREIGN KEY (userId) REFERENCES users(id)
#);

#CREATE TABLE test (
#	id int NOT NULL AUTO_INCREMENT,
#    val1 int,
#    val2 int,
#    PRIMARY KEY (id)
#);


INSERT INTO `accounts` (`username`, `password`, `gmLevel`, `chatUnblockDate`, `creationDate`, `pic`, `characterSlots`) VALUES ('admin', 'admin', '7', '0', '0', '111111', '40');