drop table if exists damageskinsavedatas;
drop table if exists friends;
drop table if exists linkskills;
drop table if exists accounts;
drop table if exists macroskills;
drop table if exists macros;
drop table if exists characterpotentials;
drop table if exists test;
drop table if exists skills;
drop table if exists characters;
drop table if exists avatardata;
drop table if exists keymaps;
drop table if exists funckeymap;
drop table if exists characterstats;
drop table if exists hairequips;
drop table if exists unseenequips;
drop table if exists petids;
drop table if exists totems;
drop table if exists spset;
drop table if exists extendsp;
drop table if exists noncombatstatdaylimit;
drop table if exists systemtimes;
drop table if exists charactercards;
drop table if exists avatarlook;
drop table if exists sockets;
drop table if exists options;
drop table if exists equips;
drop table if exists petitems;
drop table if exists items;
drop table if exists inventories;
drop table if exists questprogressrequirements;
drop table if exists questprogressitemrequirements;
drop table if exists questprogresslevelrequirements;
drop table if exists questprogressmoneyrequirements;
drop table if exists questprogressmobrequirements;
drop table if exists questlists;
drop table if exists questmanagers;
drop table if exists quests;
drop table if exists guildrequestors;
drop table if exists gradenames;
drop table if exists guildmembers;
drop table if exists guildrequestors;
drop table if exists guildskills;
drop table if exists guildskill;
drop table if exists guilds;
drop table if exists monsterbookcards;
drop table if exists monsterbookinfos;
drop table if exists filetimes;
drop table if exists trunks;

create table trunks(
	id int not null auto_increment,
    slotcount tinyint,
    money bigint,
    primary key (id)
);

create table filetimes (
	id bigint not null auto_increment,
    lowdatetime int,
    highdatetime int,
    primary key (id)
);

create table quests (
	id bigint not null auto_increment,
    qrkey int,
    qrvalue varchar(255),
    status int,
    completedtime bigint,
	primary key (id),
    foreign key (completedtime) references filetimes(id)
);

create table questmanagers (
	id bigint not null auto_increment,
    primary key(id)
);

create table questlists (
	questlist_id bigint not null auto_increment,
	questmanager_id bigint,
    questid int,
    fk_questid bigint,
    primary key (questlist_id),
    foreign key (questmanager_id) references questmanagers(id)  on delete cascade,
    foreign key (fk_questid) references quests(id)
);

create table questprogressrequirements (
	id bigint not null auto_increment,
    progresstype varchar(255),
	questid bigint,
    unitid int,
    requiredcount int,
    currentcount int,
    primary key (id),
    foreign key (questid) references quests(id)  on delete cascade
);

create table inventories (
	id int not null auto_increment,
    type int,
    slots tinyint,
    primary key (id)
);

create table items (
	id bigint not null auto_increment,
    inventoryid int, # item can be inside an inventory or storage, so cannot be a foreign key :(
    trunkid int,
    itemid int,
    bagindex int,
    cashitemserialnumber bigint,
    dateexpire bigint,
    invtype int,
    type int,
    iscash boolean,
    quantity int,
    owner varchar(255),
    primary key (id)
);

create table petitems (
	itemid bigint,
    name varchar(255),
    level tinyint,
    tameness smallint,
    repleteness tinyint,
    petattribute smallint,
    petskill int,
    datedead bigint,
    remainlife int,
    attribute smallint,
    activestate tinyint,
    autobuffskill int,
    pethue int,
    giantrate smallint,
    primary key (itemid),
    foreign key (itemid) references items(id) on delete cascade,
    foreign key (datedead) references filetimes(id)
);

create table equips (
	serialnumber bigint,
    itemid bigint,
    title varchar(255),
    equippeddate bigint,
    prevbonusexprate int,
    ruc smallint,
    cuc smallint,
    istr smallint,
    idex smallint,
    iint smallint,
    iluk smallint,
    imaxhp smallint,
    imaxmp smallint,
    ipad smallint,
    imad smallint,
    ipdd smallint,
    imdd smallint,
    iacc smallint,
    ieva smallint,
    icraft smallint,
    ispeed smallint,
    ijump smallint,
    attribute smallint,
    leveluptype smallint,
    level smallint,
    exp smallint,
    durability smallint,
    iuc smallint,
    ipvpdamage smallint,
    ireducereq smallint,
    specialattribute smallint,
    durabilitymax smallint,
    iincreq smallint,
    growthenchant smallint,
    psenchant smallint,
    bdr smallint,
    imdr smallint,
    damr smallint,
    statr smallint,
    cuttable smallint,
    exgradeoption smallint,
    itemstate smallint,
    grade smallint,
    chuc smallint,
    souloptionid smallint,
    soulsocketid smallint,
    souloption smallint,
    rstr smallint,
    rdex smallint,
    rint smallint,
    rluk smallint,
    rlevel smallint,
    rjob smallint,
    rpop smallint,
    specialgrade int,
    fixedpotential boolean,
    tradeblock boolean,
    isonly boolean,
    notsale boolean,
    attackspeed int,
    price int,
    tuc int,
    charmexp int,
    expireonlogout boolean,
    setitemid int,
    exitem boolean,
    equiptradeblock boolean,
    islot varchar(255),
    vslot varchar(255),
    fixedgrade int,
    primary key (itemid),
    foreign key (itemid) references items(id) on delete cascade,
    foreign key (equippeddate) references filetimes(id)
);

create table options (
	id int not null auto_increment,
    equipid bigint,
    optionid int,
    primary key (id),
    foreign key (equipid) references equips(itemid) on delete cascade
);

create table sockets (
	id bigint not null auto_increment,
    equipid bigint,
    ord int,
    socketid int,
    primary key (id),
    foreign key (equipid) references equips(itemid) on delete cascade
);

create table monsterbookinfos (
	id int not null auto_increment,
    setid int,
    coverid int,
    primary key (id)
);

create table monsterbookcards (
	id bigint not null auto_increment,
    bookid int,
    cardid int,
    primary key (id),
    foreign key (bookid) references monsterbookinfos(id)
);

create table avatarlook (
	id int not null auto_increment,
    gender int,
    skin int,
    face int,
    hair int,
    weaponstickerid int,
    weaponid int,
    subweaponid int,
    job int,
    drawelfear boolean,
    demonslayerdeffaceacc int,
    xenondeffaceacc int,
	beasttamerdeffaceacc int,
    iszerobetalook boolean,
    mixedhaircolor int,
    mixhairpercent int,
    ears int,
    tail int,
    primary key (id)
);

create table hairequips (
	id int not null auto_increment,
    alid int,
    equipid int,
    primary key (id),
    foreign key (alid) references avatarlook(id) on delete cascade
);

create table unseenequips (
	id int not null auto_increment,
    alid int,
    equipid int,
    primary key (id),
    foreign key (alid) references avatarlook(id) on delete cascade
);

create table petids (
	id int not null auto_increment,
    alid int,
    petid int,
    primary key (id),
    foreign key (alid) references avatarlook(id) on delete cascade
);

create table totems (
	id int not null auto_increment,
    alid int,
    totemid int,
    primary key (id),
    foreign key (alid) references avatarlook(id) on delete cascade
);

create table extendsp (
	id int not null auto_increment,
    primary key (id)
);

create table spset (
	id int not null auto_increment,
    extendsp_id int,
    joblevel tinyint,
    sp int,
    primary key (id),
    foreign key (extendsp_id) references extendsp(id) on delete cascade
);

create table systemtimes (
	id int not null auto_increment,
    yr int,
    mnth int,
    primary key (id)
);

create table noncombatstatdaylimit (
	id int not null auto_increment,
    charisma smallint,
    charm smallint,
    insight smallint,
    will smallint,
    craft smallint,
    sense smallint,
    ftlastupdatecharmbycashpr bigint,
    charmbycashpr tinyint,
    primary key (id),
    foreign key (ftlastupdatecharmbycashpr) references filetimes(id)
);

create table charactercards (
	id int not null auto_increment,
    characterid int,
    job int,
    level tinyint,
    primary key (id)
);

create table characterstats (
	id int not null auto_increment,
    characterid int,
    characteridforlog int,
    worldidforlog int,
    name varchar(255),
    gender int,
    skin int,
    face int,
    hair int,
    mixbasehaircolor int,
    mixaddhaircolor int,
    mixhairbaseprob int,
    level int,
    job int,
    str int,
    dex int,
    inte int,
    luk int,
    hp int,
    maxhp int,
    mp int,
    maxmp int,
    ap int,
    sp int,
    exp long,
    pop int,
    money long,
    wp int,
    extendsp int,
    posmap long,
    portal int,
    subjob int,
    deffaceacc int,
	fatigue int,
    lastfatigueupdatetime int,
    charismaexp int,
    insightexp int,
    willexp int,
    craftexp int,
    senseexp int,
    charmexp int,
    noncombatstatdaylimit int,
    pvpexp int,
    pvpgrade int,
    pvppoint int,
    pvpmodelevel int,
    pvpmodetype int,
    eventpoint int,
    albaactivityid int,
    albastarttime bigint,
    albaduration int,
    albaspecialreward int,
    burning boolean,
    charactercard int,
    accountlastlogout int,
    lastlogout bigint,
    gachexp int,
    honorexp int,
    primary key (id),
    foreign key (extendsp) references extendsp(id),
    foreign key (noncombatstatdaylimit) references noncombatstatdaylimit(id),
    foreign key (albastarttime) references filetimes(id),
    foreign key (charactercard) references charactercards(id),
    foreign key (accountlastlogout) references systemtimes(id),
    foreign key (lastlogout) references filetimes(id)
);

create table avatardata (
	id int not null auto_increment,
    characterstat int,
    avatarlook int,
    zeroavatarlook int,
    primary key (id),
    foreign key (characterstat) references characterstats(id),
    foreign key (avatarlook) references avatarlook(id),
    foreign key (zeroavatarlook) references avatarlook(id)
);

create table funckeymap (
	id int not null auto_increment,
    primary key (id)
);

create table keymaps (
	id int not null auto_increment,
    fkmapid int,
    idx int,
    type tinyint,
    val int,
    primary key (id),
    foreign key (fkmapid) references funckeymap(id)
);


create table guilds (
	id int not null auto_increment,
    name varchar(255),
    leaderid int,
    worldid int,
    markbg int,
    markbgcolor int,
    mark int,
    markcolor int,
    maxmembers int,
    notice varchar(255),
    points int,
    seasonpoints int,
    allianceid int,
    level int,
    rank int,
    ggp int,
    appliable boolean,
    joinsetting int,
    reqlevel int,
    primary key (id)
);

create table characters (
	id int not null auto_increment,
    accid int,
	avatardata int,
    equippedinventory int,
    equipinventory int,
    consumeinventory int,
    etcinventory int,
    installinventory int,
    cashinventory int,
    funckeymap_id int,
    fieldid int,
    questmanager bigint,
    guild int,
    monsterbook int,
	primary key (id),
    foreign key (avatardata) references avatardata(id),
    foreign key (equippedinventory) references inventories(id),
    foreign key (equipinventory) references inventories(id),
    foreign key (consumeinventory) references inventories(id),
    foreign key (etcinventory) references inventories(id),
    foreign key (installinventory) references inventories(id),
    foreign key (cashinventory) references inventories(id),
    foreign key (funckeymap_id) references funckeymap(id),
    foreign key (questmanager) references questmanagers(id),
    foreign key (guild) references guilds(id),
    foreign key (monsterbook) references monsterbookinfos(id)
);

create table characterpotentials (
	id bigint not null auto_increment,
    potkey tinyint,
    skillid int,
    slv tinyint,
    grade tinyint,
    charid int,
    primary key (id),
    foreign key (charid) references characters(id)
);

create table guildskill (
	id int not null auto_increment,
    skillid int,
    level int,
    expiredate bigint,
    buycharactername varchar(255),
    extendcharactername varchar(255),
    primary key (id),
    foreign key (expiredate) references filetimes(id) on delete cascade
);

create table guildskills (
	guildskill_id int not null auto_increment,
    skills_id int,
    guild_id int,
    skillid int,
    fk_guildskillid int,
    primary key (guildskill_id),
	foreign key (guild_id) references guilds(id) on delete cascade,
    foreign key (fk_guildskillid) references guildskill(id) on delete cascade
);

create table guildmembers (
	id int not null auto_increment,
    charid int,
    guildid int,
    grade int,
    alliancegrade int,
    commitment int,
    daycommitment int,
    igp int,
    commitmentinctime bigint,
    name varchar(255),
    job int,
    level int,
    loggedin boolean,
	primary key (id),
    foreign key (guildid) references guilds(id),
    foreign key (commitmentinctime) references filetimes(id) on delete cascade
);

create table guildrequestors (
	id int not null auto_increment,
    requestors_id int,
    charid int,
    guildid int,
    name varchar(255),
    job int,
    level int,
    loggedin boolean,
	primary key (id),
    foreign key (guildid) references guilds(id) on delete cascade
);

create table gradenames (
	id int not null auto_increment,
	gradename varchar(255),
    guildid int,
    primary key (id),
    foreign key (guildid) references guilds(id) on delete cascade
);


create table skills (
	id int not null auto_increment,
    charid int,
    skillid int,
    rootid int,
    maxlevel int,
    currentlevel int,
    masterlevel int,
    primary key (id),
    foreign key (charid) references characters(id)  on delete cascade
);

create table macros (
	id bigint not null auto_increment,
    charid int,
    muted boolean,
    name varchar(255),
    primary key (id),
    foreign key (charid) references characters(id)
);

create table macroskills (
	id bigint not null auto_increment,
    ordercol int,
    skillid int,
    macroid bigint,
    primary key (id),
    foreign key (macroid) references macros(id) on delete cascade
);

create table accounts (
	id int not null auto_increment,
	username varchar(255),
	password varchar(255),
	pic varchar(255),
	mac varchar(255),
	gmlevel int default 0,
	accounttypemask int default 2,
	age int default 0,
	vipgrade int default 0,
	nblockreason int default 0,
	gender tinyint default 0,
	msg2 tinyint default 0,
	purchaseexp tinyint default 0,
	pblockreason tinyint default 3,
	chatunblockdate long,
	hascensorednxloginid boolean default 0,
	gradecode tinyint default 0,
	censorednxloginid varchar(255),
	characterslots int default 4,
	creationdate long,
    lastloggedin varchar(255),
    trunkid int,
	primary key (id),
    foreign key (trunkid) references trunks(id)
);

create table linkskills (
	id bigint not null auto_increment,
    accid int,
    ownerid int,
    linkskillid int,
    level int,
    primary key (id),
    foreign key (accid) references accounts(id)
);

create table damageskinsavedatas (
	id bigint not null auto_increment,
    damageskinid int,
    itemid int,
    notsave boolean,
    description varchar(255),
    accid int,
    primary key (id),
    foreign key (accid) references accounts(id) on delete cascade
);

create table friends (
	id int not null auto_increment,
    ownerid int,
    owneraccid int,
    friendid int,
    friendaccountid int,
    name varchar(255),
    flag tinyint,
    groupname varchar(255),
    mobile tinyint,
    nickname varchar(255),
    memo varchar(255),
    primary key (id)
);


insert into `accounts` (`username`, `password`, `gmlevel`, `chatunblockdate`, `creationdate`, `pic`, `characterslots`) values ('admin', 'admin', '7', '0', '0', '111111', '40');
insert into `accounts` (`username`, `password`, `gmlevel`, `chatunblockdate`, `creationdate`, `pic`, `characterslots`) values ('asura', 'admin', '7', '0', '0', '111111', '40');
insert into `accounts` (`username`, `password`, `gmlevel`, `chatunblockdate`, `creationdate`, `pic`, `characterslots`) values ('maigal', 'admin', '7', '0', '0', '111111', '40');