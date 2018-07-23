drop table if exists cs_categories;
drop table if exists cs_items;

create table cs_categories (
	id int not null auto_increment,
    idx int,
    name varchar(255),
    flag int,
    parentIdx int,
    stock int,
    primary key (id)
);

create table cs_items (
	id int not null auto_increment,
    itemID int not null,
    stock int default 1,
    shopItemFlag int default 0,
    idk1 int default 0,
    idk2 int default 0,
    oldPrice int default 0,
    newPrice int default 0,
    idkTime1 bigint,
    idkTime2 bigint,
    idkTime3 bigint,
    idkTime4 bigint,
    idk3 int default 0,
    bundleQuantity int default 0,
    availableDays int default 0,
    buyableWithMaplePoints smallint default 1,
    buyableWithCredit smallint default 1,
    buyableWithPrepaid smallint default 1,
    likable smallint default 1,
    meso smallint default 0,
    favoritable smallint default 1,
    gender int default 2,
    likes int default 0,
    requiredLevel int default 0,
    idk10 varchar(255),
    idk11 int default 0,
    idk13 int default 0,
    idk14 int default 0,
    category varchar(255),
    primary key (id)
);

insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('2000000', 'Favourite', '0', '100');


insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1010000', 'Special Promotions', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1010100', 'New Arrivals', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1010200', 'Discounted', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1010300', 'Limited Time', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1010400', 'Limited Quantity', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1010500', 'Daily Deals', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1010600', 'Maple Rewards Shop', '0', '100');


insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1020000', 'Time Savers', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1020100', 'Teleport Rocks', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1020200', 'Item Stores', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1020300', 'Quest Helpers', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1020400', 'Dungeon Passes', '0', '100');


insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1030000', 'Random Rewards', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1030100', 'Gachapon Tickets', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1030200', 'Surprise Boxes', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1030300', 'Special Items', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1030400', 'Meso Sacks', '0', '100');


insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040000', 'Equipment Modifications', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040100', 'Miracle Cubes', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040200', 'Scrolls', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040300', 'Upgrade Slots', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040400', 'Trade', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040500', 'Other', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040501', 'Item Tag', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040502', 'Item Guards', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040600', 'Duration', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040700', 'Bypass Keys', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1040800', 'Fusion Anvils', '0', '100');


insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1050000', 'Character Modifications', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1050100', 'SP/AP modifications', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1050200', 'EXP Coupons', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1050300', 'Drop Coupons', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1050400', 'Inventory slots', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1050500', 'Skill Modifications', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1050600', 'Protection', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1050700', 'Wedding', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1050800', 'Other', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1050900', 'Mount', '0', '100');


insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1060000', 'Equipment', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1060100', 'Weapon', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1060200', 'Weapon 2', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1060300', 'Weapon 3', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1060400', 'Hat', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1060500', 'Hat 2', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1060600', 'Hat 3', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1060700', 'Hat 4', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1060800', 'Hat 5', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1060900', 'Hat 6', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1061000', 'Face', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1061100', 'Eye', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1061200', 'Accessory', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1061300', 'Earrings', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1061400', 'Overall', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1061500', 'Overall 2', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1061600', 'Overall 3', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1061700', 'Overall 4', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1061800', 'Top', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1061900', 'Top 2', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1062000', 'Bottom', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1062100', 'Bottom 2', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1062200', 'Shoes', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1062300', 'Shoes 2', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1062400', 'Glove', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1062500', 'Ring', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1062600', 'Ring 2', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1062700', 'Cape', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1062800', 'Cape 2', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1062900', 'Transparent', '0', '100');


insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1070000', 'Appearance', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1070100', 'Beauty Parlor', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1070101', 'Hair', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1070102', 'Face', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1070103', 'Skin', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1070200', 'Facial Expressions', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1070300', 'Effect', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1070400', 'Transformations', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1070500', 'Special', '0', '100');


insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1080000', 'Pet', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1080100', 'Pets', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1080200', 'Pets 2', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1080300', 'Pet Appearance', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1080400', 'Pet Appearance 2', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1080500', 'Pet Use', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1080600', 'Pet Food', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1080700', 'Pet Skills', '0', '100');


insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1090000', 'Messenger and Social', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1090100', 'Megaphones', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1090200', 'Messengers', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1090300', 'Weather Effects', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1090301', 'Stats', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1090302', 'Non-Stat', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1090400', 'Other', '0', '100');





/*		TIME SAVERS		*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5040004', '9900', 'Teleport Rocks');      /*Hyper Teleport Rock*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5450004', '1000', 'Item Stores');      /*Traveling Merchant (30-day)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5450005', '1000', 'Item Stores');      /*Portable Storage (30-day)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5450006', '400', 'Item Stores');      /*Traveling Merchant (1-day)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5450008', '400', 'Item Stores');      /*Portable Storage (1-day)*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5252030', '300', 'Dungeon Passes');      /*Monster Park Additional Entry Ticket*/





/*		RANDOM REWARDS		*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5680021', '2500', 'Gachapon Tickets');      /*Chair Gachapon Ticket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5680021', '25000', 'Gachapon Tickets', '11');      /*Chair Gachapon Ticket (11)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5451000', '1050', 'Gachapon Tickets');      /*Remote Gachapon Ticket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5451000', '10500', 'Gachapon Tickets', '11');      /*Remote Gachapon Ticket (11)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5220000', '10000', 'Gachapon Tickets', '11');      /*Gachapon Ticket (11)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5220000', '1000', 'Gachapon Tickets');      /*Gachapon Ticket*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5222060', '3400', 'Surprise Boxes');      /*Premium Surprise Style Box*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5222060', '34000', 'Surprise Boxes', '11');      /*Premium Surprise Style Box (11)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5222006', '2100', 'Surprise Boxes');      /*Surprise Style Box*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5222006', '21000', 'Surprise Boxes', '11');      /*Surprise Style Box (11)*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5202000', '1000', 'Meso Sacks');      /*Rare Meso Sack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5202001', '3000', 'Meso Sacks');      /*Unique Meso Sack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5202002', '2000', 'Meso Sacks');      /*Epic Meso Sack*/





/*		EQUIPMENT MODIFICATIONS		*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5700000', '1200', 'Other');      /*Android Naming Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5060000', '1800', 'Other');      /*Item Tag*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5700000', '1200', 'Item Tag');      /*Android Naming Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5060000', '1800', 'Item Tag');      /*Item Tag*/





/*		CHARACTER MODIFICATIONS		*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5430000', '6900', 'Inventory slots');      /*Extra Character Slot Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('9110000', '5000', 'Inventory slots');      /*Add Storage Slots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('9111000', '5000', 'Inventory slots');      /*Add Equip Slots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('9112000', '5000', 'Inventory slots');      /*Add Use Slots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('9114000', '5000', 'Inventory slots');      /*Add ETC Slots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('9113000', '5000', 'Inventory slots');      /*Add Set-up Slots*/





/*		EQUIPMENT		*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1701000', '2700', 'Weapon');      /*Elizabeth Fan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702000', '4700', 'Weapon');      /*Dual Plasma Blade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702001', '5600', 'Weapon');      /*Bouquet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702002', '3700', 'Weapon');      /*Wooden Slingshot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702003', '4300', 'Weapon');      /*Plastic Slingshot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702004', '7400', 'Weapon');      /*Angel Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702005', '6800', 'Weapon');      /*Yellow Candy Cane*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702006', '2700', 'Weapon');      /*Red Candy Cane*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702007', '5600', 'Weapon');      /*Green Candy Cane*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702008', '5600', 'Weapon');      /*Santa Sack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702009', '4700', 'Weapon');      /*Tiger Paw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702010', '6800', 'Weapon');      /*Orange Toy Hammer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702011', '4700', 'Weapon');      /*Pink Toy Hammer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702012', '6300', 'Weapon');      /*Yellow Spatula*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702013', '4700', 'Weapon');      /*Teddy Bear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702014', '5600', 'Weapon');      /*Toy RIfle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702015', '6300', 'Weapon');      /*Bug Net*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702016', '2700', 'Weapon');      /*Picnic Basket*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702017', '5000', 'Weapon');      /*Pink Rabbit Puppet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702018', '6300', 'Weapon');      /*Vanilla Ice Cream*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702019', '4300', 'Weapon');      /*Pillow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702020', '2700', 'Weapon');      /*Lollipop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702021', '4300', 'Weapon');      /*Black Electric Guitar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702022', '7400', 'Weapon');      /*Brown Electric Guitar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702023', '6300', 'Weapon');      /*Green Electric Guitar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702024', '4300', 'Weapon');      /*Cupid's Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702025', '2700', 'Weapon');      /*Cherub's Bow*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702026', '4300', 'Weapon');      /*Cupid's Crossbow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702027', '6800', 'Weapon');      /*Blazing Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702028', '5000', 'Weapon');      /*Donut*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702029', '6300', 'Weapon');      /*White Rabbit's Foot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702030', '7400', 'Weapon');      /*Diao Chan Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702031', '3700', 'Weapon');      /*Liu Bei Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702032', '4700', 'Weapon');      /*Zhu-Ge-Liang Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702033', '3700', 'Weapon');      /*Sun Quan Staff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702034', '5000', 'Weapon');      /*Guan Yu Spear*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702035', '4700', 'Weapon');      /*Cao Cao Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702036', '2700', 'Weapon');      /*Witch's Broomstick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702037', '7400', 'Weapon');      /*Coffee Pot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702038', '2700', 'Weapon');      /*Horoscope Claw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702039', '3700', 'Weapon');      /*Horoscope Net*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702040', '4700', 'Weapon');      /*Horoscope Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702041', '6300', 'Weapon');      /*Horoscope Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702042', '6800', 'Weapon');      /*Microphone*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702043', '3200', 'Weapon');      /*Poo Stick*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702044', '6800', 'Weapon');      /*Toy Machine Gun*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702045', '4700', 'Weapon');      /*Sunflower Stalk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702046', '6300', 'Weapon');      /*Horoscope Crossbow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702047', '4300', 'Weapon');      /*Snowflake Staff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702048', '5600', 'Weapon');      /*Green Wash Cloth*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702049', '3200', 'Weapon');      /*Snowman Claw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702050', '7400', 'Weapon');      /*Cellphone*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702051', '4700', 'Weapon');      /*Hong Bao*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702052', '5600', 'Weapon');      /*In-Hand FB Helmet(Home)*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702053', '4700', 'Weapon');      /*In-Hand FB Helmet(Away)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702054', '2700', 'Weapon');      /*Football Claw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702055', '7400', 'Weapon');      /*Ancient Korean Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702056', '3200', 'Weapon');      /*Guitar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702057', '3200', 'Weapon');      /*Blue Guitar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702058', '4300', 'Weapon');      /*Big Hand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702059', '4300', 'Weapon');      /*Cactus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702060', '3700', 'Weapon');      /*Shiner*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702061', '6800', 'Weapon');      /*Red Fist of Fury*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702062', '6800', 'Weapon');      /*Blue Fist of Fury*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702063', '4700', 'Weapon');      /*Scissor Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702064', '3700', 'Weapon');      /*Rock Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702065', '4300', 'Weapon');      /*Paper Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702066', '6300', 'Weapon');      /*Canvas Tote Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702067', '6300', 'Weapon');      /*England Cheer Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702068', '4700', 'Weapon');      /*France Cheer Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702069', '5600', 'Weapon');      /*Brazil Cheer Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702070', '5000', 'Weapon');      /*Sporty Band*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702071', '2700', 'Weapon');      /*Japan Cheer Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702072', '4700', 'Weapon');      /*Laser Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702073', '7400', 'Weapon');      /*Blue Shiner Crossbow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702074', '3200', 'Weapon');      /*Pink Shiner Crossbow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702075', '3700', 'Weapon');      /*USA Cheer Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702076', '6800', 'Weapon');      /*Mexico Cheer Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702077', '2700', 'Weapon');      /*Australia Cheer Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702078', '3200', 'Weapon');      /*Fairy Fan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702079', '3700', 'Weapon');      /*Blue Blazing Sword*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702080', '4300', 'Weapon');      /*Green Blazing Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702081', '5000', 'Weapon');      /*Purple Blazing Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702082', '7400', 'Weapon');      /*Harp*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702083', '3200', 'Weapon');      /*Foam Hand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702084', '5000', 'Weapon');      /*Toy Pinwheel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702085', '3700', 'Weapon');      /*Frog Claw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702086', '4300', 'Weapon');      /*Chicken Smackaroo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702087', '6800', 'Weapon');      /*Red Pencil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702088', '6300', 'Weapon');      /*Super Scrubber*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702089', '4700', 'Weapon');      /*Candy Hammer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702090', '7400', 'Weapon');      /*Feather Scimitar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702091', '7400', 'Weapon');      /*Tennis Racquet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702092', '7400', 'Weapon');      /*Glowing Pumpkin Basket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702093', '5000', 'Weapon');      /*Okie Donkie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702094', '6800', 'Weapon');      /*Mad Cow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702095', '4700', 'Weapon');      /*Frog Glove*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702096', '3200', 'Weapon');      /*Pizza Pan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702097', '3700', 'Weapon');      /*Fire Katana*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702098', '5000', 'Weapon');      /*Violin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702099', '4700', 'Weapon');      /*Transparent Claw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702100', '7400', 'Weapon');      /*Christmas Bell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702101', '5000', 'Weapon');      /*Meso Gunner*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702102', '5600', 'Weapon');      /*Starblade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702103', '6300', 'Weapon');      /*Pink Ribbon Umbrella*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702104', '7400', 'Weapon');      /*Deluxe Cone*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702105', '5000', 'Weapon');      /*Heart Key*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702106', '3200', 'Weapon');      /*Melting Chocolate*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702107', '4300', 'Weapon');      /*Chocolate*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702108', '5000', 'Weapon');      /*Giant Lollipop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702112', '3200', 'Weapon');      /*Celestial Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702113', '2700', 'Weapon');      /*Maoster Pole Arm*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702114', '5000', 'Weapon');      /*Wonky's Leaf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702115', '7400', 'Weapon');      /*Red Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702116', '4300', 'Weapon');      /*Jie 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702117', '7400', 'Weapon');      /*Jie 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702118', '3200', 'Weapon');      /*Janus Sword*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702119', '5000', 'Weapon');      /*Sachiel Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702120', '3700', 'Weapon');      /*Veamoth Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702121', '3700', 'Weapon');      /*Seal Pillow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702122', '5600', 'Weapon');      /*Dragon's Fury*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702123', '7400', 'Weapon');      /*Forked Pork*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702124', '2700', 'Weapon');      /*Kitty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702125', '4700', 'Weapon');      /*Heart Cane*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702126', '5000', 'Weapon');      /*Blue Shiner */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702127', '2700', 'Weapon');      /*Water Gun*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702128', '7400', 'Weapon');      /*Green Shiner*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702129', '7400', 'Weapon');      /*Purple Shiner*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702130', '7400', 'Weapon');      /*Red Shiner*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702131', '3200', 'Weapon');      /*Pepe Beak*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702132', '6800', 'Weapon');      /*Slime Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702133', '4700', 'Weapon');      /*Smackdown Fist */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702134', '4300', 'Weapon');      /*Serpent Staff */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702135', '4700', 'Weapon');      /*Vengence Claw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702136', '5000', 'Weapon');      /*Ice Flower*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702138', '5000', 'Weapon');      /*Spanish Ham*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702139', '4300', 'Weapon');      /*Hook Hand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702140', '6800', 'Weapon');      /*Giant Orchid*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702141', '7400', 'Weapon');      /*My Buddy Max*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702142', '5000', 'Weapon');      /*Pink Angel Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702143', '3200', 'Weapon');      /*Combat Syringe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702144', '4300', 'Weapon');      /*Broken Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702145', '5600', 'Weapon');      /*Bionic Claw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702146', '4300', 'Weapon');      /*Skull Staff*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702147', '6800', 'Weapon');      /*Skull Axe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702148', '3200', 'Weapon');      /*Moon Baton*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702149', '2700', 'Weapon');      /*Tania Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702150', '3200', 'Weapon');      /*Mercury Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702151', '3700', 'Weapon');      /*Royal Oaken Staff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702152', '4700', 'Weapon');      /*Flame Tongue*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702153', '4700', 'Weapon');      /*Crissagrim Blade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702154', '6300', 'Weapon');      /*Plasma Saber*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702155', '5600', 'Weapon');      /*Shooting Star*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702156', '6800', 'Weapon');      /*Forked Turkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702157', '6300', 'Weapon');      /*Burning Marshmellow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702158', '6300', 'Weapon');      /*The Jackal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702159', '2700', 'Weapon');      /*Blackbeard's Knuckle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702160', '6300', 'Weapon');      /*Tiger Paw Knuckle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702161', '5000', 'Weapon');      /*Dogged Out*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702162', '6800', 'Weapon');      /*Koala Doll*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702163', '6800', 'Weapon');      /*Hot Dog Fork*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702164', '6300', 'Weapon');      /*Bunny Nunchucks*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702165', '3700', 'Weapon');      /*My Buddy DJ*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702166', '6800', 'Weapon');      /*Holiday Candy Cane*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702167', '3200', 'Weapon');      /*Glow Fingers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702168', '3200', 'Weapon');      /*Holiday Tree Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702169', '4300', 'Weapon');      /*My Buddy Tina*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702170', '3700', 'Weapon');      /*Electric Knuckle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702171', '4700', 'Weapon');      /*Party Popper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702172', '7400', 'Weapon');      /*Bluebird*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702173', '3700', 'Weapon');      /*Hessonite Saber*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702174', '7400', 'Weapon');      /*Butterfly Staff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702175', '3700', 'Weapon');      /*Hot Dog Link*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702177', '6800', 'Weapon');      /*Power Pesticide*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702178', '3700', 'Weapon');      /*MDAS Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702179', '6300', 'Weapon');      /*Cloud 9 Pillow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702180', '7400', 'Weapon');      /*Dark Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702181', '6300', 'Weapon');      /*Picky Ducky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702182', '6300', 'Weapon');      /*Giant Pop with a Swirl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702183', '5600', 'Weapon');      /*Sunset Seraphim*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702184', '3200', 'Weapon');      /*Aqua Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702185', '6300', 'Weapon');      /*White & Yellow Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702186', '7400', 'Weapon');      /*3rd Anniversary Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702187', '3200', 'Weapon');      /*Patriot Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702188', '6300', 'Weapon');      /*Pink Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702189', '6800', 'Weapon');      /*Crabby*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702190', '5600', 'Weapon');      /*Transparent Knuckle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702191', '6300', 'Weapon');      /*Rainbow Sabre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702193', '5000', 'Weapon');      /*Towel Whip*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702195', '5000', 'Weapon');      /*MapleGirl Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702196', '4300', 'Weapon');      /*Fly Blue Bird*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702197', '2700', 'Weapon');      /*Tsunami Wave*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702198', '4300', 'Weapon');      /*Bullseye Board*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702200', '6800', 'Weapon');      /*Plastic umbrella*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702201', '7400', 'Weapon');      /*Bone Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702202', '7400', 'Weapon');      /*Baby Ellie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702203', '3700', 'Weapon');      /*Halloween Teddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702204', '4700', 'Weapon');      /*Japanese War Fan*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702207', '2700', 'Weapon');      /*Musical Violin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702208', '5600', 'Weapon');      /*Alligator Tube*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702209', '3200', 'Weapon');      /*Rudolph Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702210', '4700', 'Weapon');      /*Santa Buddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702211', '4300', 'Weapon');      /*Blizzard Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702212', '3700', 'Weapon');      /*Galactic Legend*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702213', '3200', 'Weapon');      /*Heartbreak Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702214', '6800', 'Weapon');      /*Whip*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702215', '6800', 'Weapon');      /*Boleadoras*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702216', '3700', 'Weapon');      /*Magic Heart Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702217', '4300', 'Weapon');      /*Ducky Tube*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702218', '5000', 'Weapon');      /*Dumbell Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702219', '4300', 'Weapon');      /*Knockout Boxing Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702220', '3700', 'Weapon');      /*Transparent Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702221', '4300', 'Weapon');      /*Mini Bean Propeller*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702222', '4300', 'Weapon');      /*Fanfare Firecracker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702223', '2700', 'Weapon');      /*Sparkler*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702224', '4300', 'Weapon');      /*Transparent Weapon*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702226', '4300', 'Weapon');      /*My Buddy Whale*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702228', '4300', 'Weapon');      /*Choco Banana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702229', '6300', 'Weapon');      /*Demon Sickle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702230', '4700', 'Weapon');      /*Popsicle Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702231', '3700', 'Weapon');      /*We Care! Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702232', '5600', 'Weapon');      /*My friend Gold Bulldog*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702233', '5600', 'Weapon');      /*Rainbow Brush*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702234', '2700', 'Weapon');      /*Pluto Legend Hall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702235', '4300', 'Weapon');      /*Metallic Arm*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702236', '5600', 'Weapon');      /*Death Note*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702237', '2700', 'Weapon');      /*Inari the White Fox*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702238', '3200', 'Weapon');      /*Soft Plush Dolphin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702239', '3700', 'Weapon');      /*Holy Mystics*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702240', '7400', 'Weapon');      /*Holy Mystics*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702246', '5000', 'Weapon');      /*Ghost Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702248', '5000', 'Weapon');      /*Rudolph*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702249', '7400', 'Weapon');      /*Gosling Cushion*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702250', '2700', 'Weapon');      /*Steel Briefcase*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702251', '7400', 'Weapon');      /*Saw Machine Gun*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702252', '4300', 'Weapon');      /*Hunting Hawk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702253', '7400', 'Weapon');      /*Bunny Umbrella*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702254', '6800', 'Weapon');      /*Rudolph*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702256', '4700', 'Weapon');      /*Mini Wind Archer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702257', '2700', 'Weapon');      /*Mini Dawn Warrior*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702258', '7400', 'Weapon');      /*Mini Blaze Wizard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702259', '3700', 'Weapon');      /*Mini Thunder Breaker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702260', '3700', 'Weapon');      /*Mini Night Walker*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702261', '6800', 'Weapon');      /*Cherry Blossom Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702262', '4300', 'Weapon');      /*Green Leaf Guards*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702263', '2700', 'Weapon');      /*Kitty Spirit Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702264', '3200', 'Weapon');      /*Strawberry Basket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702266', '3700', 'Weapon');      /*Sushine Pan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702268', '4300', 'Weapon');      /*Evan Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702269', '6300', 'Weapon');      /*Mini Dawn Warrior*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702270', '3200', 'Weapon');      /*Mini Blaze Wizard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702271', '7400', 'Weapon');      /*Mini Wind Archer*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702272', '6300', 'Weapon');      /*Mini Night Walker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702273', '7400', 'Weapon');      /*Mini Thunder Breaker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702274', '5000', 'Weapon');      /*Dragon Lord Gloves*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702275', '4300', 'Weapon 2');      /*Rainbow Umbrella*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702276', '4700', 'Weapon 2');      /*Rainbow Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702277', '6300', 'Weapon 2');      /*Test Pen*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702278', '5000', 'Weapon 2');      /*King Crow Fan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702279', '3700', 'Weapon 2');      /*Shining Feather Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702280', '6300', 'Weapon 2');      /*Shining Feather Slayer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702281', '3200', 'Weapon 2');      /*Shining Feather Lord*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702282', '4700', 'Weapon 2');      /*Shining Feather Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702283', '3700', 'Weapon 2');      /*Shining Feather Knuckle*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702284', '5000', 'Weapon 2');      /*Handbag (Pink)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702285', '3700', 'Weapon 2');      /*Handbag (Blue)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702286', '3200', 'Weapon 2');      /*Marchosias*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702287', '2700', 'Weapon 2');      /*Battle Mage Staff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702288', '6800', 'Weapon 2');      /*Wild Hunter Crossbow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702289', '5600', 'Weapon 2');      /*Royal Marine Flag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702291', '6300', 'Weapon 2');      /*Elizabeth Fan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702293', '2700', 'Weapon 2');      /*Suitcase*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702295', '5600', 'Weapon 2');      /*Playing Cards*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702296', '6300', 'Weapon 2');      /*Yo Yo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702299', '3200', 'Weapon 2');      /*Chocolate Dipped Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702301', '3200', 'Weapon 2');      /*Rabbit Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702302', '2700', 'Weapon 2');      /*Alien Mug*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702303', '7400', 'Weapon 2');      /*Baby Bottle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702304', '5000', 'Weapon 2');      /*Funny Punch Yo-yo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702305', '7400', 'Weapon 2');      /*Carrot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702306', '5000', 'Weapon 2');      /*Burning Breeze Fan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702308', '3700', 'Weapon 2');      /*Spring Blossoms*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702309', '6800', 'Weapon 2');      /*Rainbow Sparkle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702310', '5600', 'Weapon 2');      /*6th Anniversary Party Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702311', '4700', 'Weapon 2');      /*MSE 4 Years & Unstoppable Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702313', '3700', 'Weapon 2');      /*Orange Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702314', '5600', 'Weapon 2');      /*Heaven's Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702315', '5600', 'Weapon 2');      /*Stellar Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702316', '3200', 'Weapon 2');      /*Dynamic Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702317', '7400', 'Weapon 2');      /*Bloody Ruby Sabre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702318', '5000', 'Weapon 2');      /*Twilight Sabre*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702319', '7400', 'Weapon 2');      /*Evergreen Sabre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702320', '3200', 'Weapon 2');      /*Slate Thunder Sabre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702321', '4700', 'Weapon 2');      /*Dark Magenta Sabre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702322', '5000', 'Weapon 2');      /*Soild Black Sabre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702323', '6800', 'Weapon 2');      /*Ombra & Luce Sabre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702324', '2700', 'Weapon 2');      /*Shock Wave*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702328', '5600', 'Weapon 2');      /*Pink Angel Syringe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702329', '5600', 'Weapon 2');      /*Strawberry Delight*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702330', '4300', 'Weapon 2');      /*Milky Way*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702333', '3200', 'Weapon 2');      /*Strawberry Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702334', '3700', 'Weapon 2');      /*Crystal Fantasia Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702335', '4700', 'Weapon 2');      /*Alchemist Potion Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702336', '2700', 'Weapon 2');      /*Lord Tempest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702337', '6300', 'Weapon 2');      /*Lightning Soul*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702340', '5000', 'Weapon 2');      /*Rabbit in a Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702341', '6300', 'Weapon 2');      /*Sweet Lollipop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702342', '7400', 'Weapon 2');      /*Orchid's Bunny Doll*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702344', '3200', 'Weapon 2');      /*Boom Box*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702345', '4700', 'Weapon 2');      /*Fierce Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702346', '7400', 'Weapon 2');      /*Lucky Pouch Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702347', '5000', 'Weapon 2');      /*Fortune Flash*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702348', '4700', 'Weapon 2');      /*Snowflake Rod*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702350', '6800', 'Weapon 2');      /*Chocolatier Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702351', '5000', 'Weapon 2');      /*Tedtacular Bearingtons*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702352', '4700', 'Weapon 2');      /*Magic Herb Teaspoon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702355', '7400', 'Weapon 2');      /*Lucky Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702356', '2700', 'Weapon 2');      /*Legendary Weapon*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702357', '5600', 'Weapon 2');      /*Starfall Magic Square*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702358', '3200', 'Weapon 2');      /*Pink Bean Buddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702359', '5600', 'Weapon 2');      /*Blue Angel Syringe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702360', '4700', 'Weapon 2');      /*Coin Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702361', '2700', 'Weapon 2');      /*Hunter Hawk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702362', '5600', 'Weapon 2');      /*Pink Bean Buddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702363', '5000', 'Weapon 2');      /*Crystalline Sheen*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702364', '5000', 'Weapon 2');      /*Dragon Familiar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702365', '7400', 'Weapon 2');      /*Tedimus Beartaculous*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702366', '4300', 'Weapon 2');      /*Shark-sicle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702367', '7400', 'Weapon 2');      /*Rose Butterwand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702368', '4300', 'Weapon 2');      /*Iris Butterwand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702371', '5600', 'Weapon 2');      /*Pimp Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702372', '7400', 'Weapon 2');      /*Pimp Chalice*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702374', '2700', 'Weapon 2');      /*Bladed Falcon's Katana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702375', '7400', 'Weapon 2');      /*Atlantis*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702376', '4700', 'Weapon 2');      /*Onmyouji Fan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702377', '6300', 'Weapon 2');      /*Strawberry Delight*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702379', '3200', 'Weapon 2');      /*Arabian Magic Lamp*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702380', '7400', 'Weapon 2');      /*Azure Crystal Crusher*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702381', '4700', 'Weapon 2');      /*Twin Crescent Blade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702382', '6800', 'Weapon 2');      /*Persimmon Branch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702385', '4300', 'Weapon 2');      /*[MS Special] Hunting Hawk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702386', '7400', 'Weapon 2');      /*[MS Special] Crystalline Sheen*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702387', '3200', 'Weapon 2');      /*[MS Special] Dragon Familiar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702388', '3200', 'Weapon 2');      /*[MS Special] Tedimus Beartaculous*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702389', '4700', 'Weapon 2');      /*[MS Special] Fly Blue Bird*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702390', '7400', 'Weapon 2');      /*Halloween Leopard Umbrella*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702392', '3700', 'Weapon 2');      /*Dark Devil Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702393', '5000', 'Weapon 2');      /*Slither Style Snake Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702394', '2700', 'Weapon 2');      /*Golden Holy Cup*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702395', '6300', 'Weapon 2');      /*Baller Cane*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702397', '4700', 'Weapon 2');      /*Twinkle Sparkle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702398', '5000', 'Weapon 2');      /*Fairy Lamp*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702399', '5600', 'Weapon 2');      /*Neo Light Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702400', '4700', 'Weapon 2');      /*Lotus's Bunny Doll*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702401', '6300', 'Weapon 2');      /*Rabbit with Carrot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702402', '5000', 'Weapon 2');      /*Stylish Iron*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702403', '7400', 'Weapon 2');      /*Sherlock's Magnifier*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702404', '5000', 'Weapon 2');      /*Muffin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702405', '6800', 'Weapon 2');      /*Starlight Heart Scepter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702406', '2700', 'Weapon 2');      /*Starfall Magic Square*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702408', '6300', 'Weapon 2');      /*Francis's Puppet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702409', '5000', 'Weapon 2');      /*Hilla Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702410', '6800', 'Weapon 2');      /*Mini Dawn Warrior*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702411', '2700', 'Weapon 2');      /*Mini Blaze Wizard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702412', '5000', 'Weapon 2');      /*Mini Wind Archer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702413', '5000', 'Weapon 2');      /*Mini Night Walker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702414', '4300', 'Weapon 2');      /*Mini Thunder Breaker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702415', '5000', 'Weapon 2');      /*Dreamy Candy Pillow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702416', '7400', 'Weapon 2');      /*Lord of the Carrots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702417', '4300', 'Weapon 2');      /*Blue Rose Parasol*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702418', '4300', 'Weapon 2');      /*Hunter Hawk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702419', '6800', 'Weapon 2');      /*Pink Bean Buddy*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702420', '2700', 'Weapon 2');      /*Crystalline Sheen*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702421', '5000', 'Weapon 2');      /*Dragon Familiar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702423', '3700', 'Weapon 2');      /*Goblin Fire*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702424', '7400', 'Weapon 2');      /*Stylish Iron*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702426', '6800', 'Weapon 2');      /*Lord Tempest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702427', '3200', 'Weapon 2');      /*Ombra & Luce Sabre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702428', '6800', 'Weapon 2');      /*Bloody Ruby Sabre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702429', '7400', 'Weapon 2');      /*Heaven's Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702430', '6800', 'Weapon 2');      /*Dynamic Seraphim*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702431', '5600', 'Weapon 2');      /*GM Nori's Syringe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702433', '4700', 'Weapon 2');      /*Salamander*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702436', '7400', 'Weapon 2');      /*Galactic Legend*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702437', '6800', 'Weapon 2');      /*Spring Blossoms*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702438', '3200', 'Weapon 2');      /*Stellar Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702439', '7400', 'Weapon 2');      /*Evergreen Sabre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702442', '6800', 'Weapon 2');      /*Baseball Bat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702443', '3200', 'Weapon 2');      /*Puppeteer's Promise*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702444', '2700', 'Weapon 2');      /*Fermata*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702445', '7400', 'Weapon 2');      /*Detective Glass*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702446', '3700', 'Weapon 2');      /*Sea Otter Slammer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702448', '5600', 'Weapon 2');      /*Funny Punch Yo-yo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702449', '4700', 'Weapon 2');      /*Strawberry Delight*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702450', '4700', 'Weapon 2');      /*Tedimus Beartaculous*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702451', '4700', 'Weapon 2');      /*Superstar Microphone*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702453', '3200', 'Weapon 2');      /*Astral Bolt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702454', '7400', 'Weapon 2');      /*Seal Wave Snuggler*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702455', '6800', 'Weapon 2');      /*RED Paint Bucket*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702456', '5000', 'Weapon 2');      /*Fairy Pico*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702457', '2700', 'Weapon 2');      /*Fantastic Ice Pop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702458', '2700', 'Weapon 2');      /*Fireworks Fan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702459', '6800', 'Weapon 2');      /*Cotton Candy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702460', '6300', 'Weapon 2');      /*Star Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702461', '3700', 'Weapon 2');      /*Chicky-Chicky Boom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702462', '3700', 'Weapon 2');      /*Fantasy Butterfly Flower*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702464', '4300', 'Weapon 2');      /*Sparkling Buddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702466', '6800', 'Weapon 2');      /*Mint Chocolatier Stick*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702467', '4300', 'Weapon 2');      /*Cotton Candy Cloud*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702468', '3700', 'Weapon 2');      /*Soft Chocolate Fondue Scepter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702469', '3700', 'Weapon 2');      /*Arachne*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702470', '4300', 'Weapon 2');      /*Free Spirit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702471', '6300', 'Weapon 2');      /*Dark Devil Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702472', '7400', 'Weapon 2');      /*Vampire Phantom's Fate*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702473', '4700', 'Weapon 2');      /*Shadow Executor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702474', '3200', 'Weapon 2');      /*Evan Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702475', '3700', 'Weapon 2');      /*Maha*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702476', '3200', 'Weapon 2');      /*Sweet Snake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702477', '4700', 'Weapon 2');      /*Evil Skull*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702478', '5000', 'Weapon 2');      /*Cat Soul*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702479', '7400', 'Weapon 2');      /*バットソード*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702480', '4300', 'Weapon 2');      /*Celena*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702485', '2700', 'Weapon 2');      /*Goodie Bundle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702486', '5600', 'Weapon 2');      /*Fluttering Camellia Flower*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702487', '4700', 'Weapon 2');      /*Red Flower*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702488', '5600', 'Weapon 2');      /*Pony's Carrot*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702489', '5000', 'Weapon 2');      /*Sweet Chocolate Fondue Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702491', '3200', 'Weapon 2');      /*Bubble Cleaner*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702492', '2700', 'Weapon 2');      /*Red Lantern*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702497', '3700', 'Weapon 2');      /*Sparkling Luck Sack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702499', '3200', 'Weapon 2');      /*Guardian Scepter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702501', '6300', 'Weapon 2');      /*Flower Dance*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702502', '6800', 'Weapon 2');      /*Cane From the Stars*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702503', '5000', 'Weapon 2');      /*Bubbling Shot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702504', '4300', 'Weapon 2');      /*Frozen Heart*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702505', '4700', 'Weapon 2');      /*Breezy Bamboo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702506', '6300', 'Weapon 2');      /*Perfect Cooking */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702507', '5000', 'Weapon 2');      /*Contemporary Chic Fan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702509', '2700', 'Weapon 2');      /*Sunny Rainbow */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702510', '6300', 'Weapon 2');      /*Rabbit and Bear Flashlight*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702512', '3700', 'Weapon 2');      /*Crown Rod*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702519', '6300', 'Weapon 2');      /*Pink Antique Parasol*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702521', '7400', 'Weapon 2');      /*Blue Swallow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702522', '3200', 'Weapon 2');      /*Viking Sword for Transformation*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702523', '7400', 'Weapon 2');      /*Sunny Day Rainbow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702524', '5600', 'Weapon 2');      /*Plump Tomato*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702525', '4300', 'Weapon 2');      /*Final Ingredient*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702526', '5600', 'Weapon 2');      /*Rifle Blade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702528', '6300', 'Weapon 2');      /*Xylophone Melody*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702529', '3700', 'Weapon 2');      /*Shadow Lamp*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702530', '6800', 'Weapon 2');      /*Sweet Summer Hammer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702533', '6300', 'Weapon 2');      /*Photo-op*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702534', '3200', 'Weapon 2');      /*Baby Paci*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702535', '7400', 'Weapon 2');      /*Hula Hula Penglyn*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702538', '6300', 'Weapon 2');      /*Dewdrop Lantern*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702540', '7400', 'Weapon 2');      /*Here's the Flashlight!*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702541', '2700', 'Weapon 2');      /*Perfect Baby*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702547', '2700', 'Weapon 2');      /*Sweet Persimmon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702549', '3700', 'Weapon 2');      /*Pom-pom Power*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702550', '7400', 'Weapon 2');      /*Peach Trio*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702551', '6300', 'Weapon 2');      /*Korean Thanksgiving Persimmon Branch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702553', '2700', 'Weapon 2');      /*Dangerous Medicine Bottle*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702554', '2700', 'Weapon 2');      /*Scary Huge Hand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702555', '4300', 'Weapon 2');      /*Noble Lady's Black Fan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702556', '6800', 'Weapon 2');      /*Blade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702557', '3700', 'Weapon 2');      /*Duster*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702559', '3700', 'Weapon 2');      /*Puppy Pal Weapon (White)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702560', '2700', 'Weapon 2');      /*Puppy Pal Weapon (Brown)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702561', '4300', 'Weapon 2');      /*Sweet Fork Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702562', '2700', 'Weapon 2');      /*Winter Snowman*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702564', '5600', 'Weapon 2');      /*Funny Punch Yo-yo*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702565', '4700', 'Weapon 2');      /*Death's Scythe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702566', '3700', 'Weapon 2');      /*Rammy Scepter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702567', '3700', 'Weapon 2');      /*Rawrin' Tiger Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702570', '7400', 'Weapon 2');      /*Fluffy Snow Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702571', '5000', 'Weapon 2');      /*Top Snow Shovel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702572', '3700', 'Weapon 2');      /*Red Rose Umbrella*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702574', '3200', 'Weapon 2');      /*Beast Trainer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702575', '2700', 'Weapon 2');      /*Lovely Chocolate Basket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702576', '5000', 'Weapon 2');      /*Ground Pounder*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702577', '5600', 'Weapon 2');      /*Lalala Goldfish Fishing Net*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702579', '4700', 'Weapon 2');      /*Crystal Cat Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702581', '5000', 'Weapon 2');      /*Sweetie Bros*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702583', '7400', 'Weapon 2');      /*Kitty Pringles*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702584', '3200', 'Weapon 2');      /*Cutie Puppy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702585', '4700', 'Weapon 2');      /*Universal Transparent Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702586', '5600', 'Weapon 2');      /*Dreaming Dandelion*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702587', '5000', 'Weapon 2');      /*Rockin' Guitar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702588', '5000', 'Weapon 2');      /*Black Cat Plush*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702589', '3700', 'Weapon 2');      /*Fairy Blossom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702590', '3200', 'Weapon 2');      /*자이언트 돼지바*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702591', '6300', 'Weapon 2');      /*Grand Romance*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702593', '2700', 'Weapon 2');      /*Winding Sky Bamboo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702594', '7400', 'Weapon 2');      /*Sweepy Orchid*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702595', '6300', 'Weapon 2');      /*Mint Kitty Tea Time*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702597', '3200', 'Weapon 2');      /*Rainbow Seashell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702599', '5000', 'Weapon 2');      /*Hoya Roar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702600', '5600', 'Weapon 2');      /*Pasta*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702601', '7400', 'Weapon 2');      /*Bacon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702602', '6300', 'Weapon 2');      /*Hamburger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702603', '3700', 'Weapon 2');      /*Rib Steak*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702604', '6800', 'Weapon 2');      /*Parfait*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702605', '3700', 'Weapon 2');      /*Donut*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702606', '5000', 'Weapon 2');      /*Squid*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702607', '4700', 'Weapon 2');      /*Cheese Carrot Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702608', '4300', 'Weapon 2');      /*Marine Stripe Umbrella*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702611', '3200', 'Weapon 2');      /*Duckling Cross Bag Weapon*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702612', '7400', 'Weapon 2');      /*Fairy Pico*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702613', '2700', 'Weapon 2');      /*Crown Rod*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702614', '5000', 'Weapon 2');      /*Baseball Bat*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702616', '4300', 'Weapon 3');      /*Ducky Candy Bar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702617', '4300', 'Weapon 3');      /*Lotus Fantasy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702619', '5000', 'Weapon 3');      /*Musical Green Onion*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702620', '5000', 'Weapon 3');      /*Mystery Dice*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702623', '4700', 'Weapon 3');      /*Today Jay*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702624', '4700', 'Weapon 3');      /*Master Time*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702625', '6800', 'Weapon 3');      /*Sparking Bluebird*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702626', '6300', 'Weapon 3');      /*British Handbag Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702627', '3200', 'Weapon 3');      /*Sakura Sword*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702628', '5000', 'Weapon 3');      /*Farmer's Glorious Egg Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702629', '6300', 'Weapon 3');      /*Vintage Cellphone*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702630', '3200', 'Weapon 3');      /*Striking Lantern*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702631', '7400', 'Weapon 3');      /*Bloody Fairytale*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702632', '6300', 'Weapon 3');      /*Zakum Arms*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702633', '2700', 'Weapon 3');      /*Banana Monkey Attacker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702634', '3200', 'Weapon 3');      /*Maple Zombies*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702635', '6800', 'Weapon 3');      /*Mr. Orlov Coin Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702637', '7400', 'Weapon 3');      /*Hard Carrier Suitcase*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702638', '2700', 'Weapon 3');      /*Blue Marine Thirst For Knowledge*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702639', '3200', 'Weapon 3');      /*Kitty Bangle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702640', '6800', 'Weapon 3');      /*Bunny Snowman Attacker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702641', '7400', 'Weapon 3');      /*Dragon Master's Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702643', '6300', 'Weapon 3');      /*Elven Monarch's Dual Bowguns*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702645', '3700', 'Weapon 3');      /*Phantom's Cane*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702647', '5600', 'Weapon 3');      /*Maha the Polearm*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702649', '5600', 'Weapon 3');      /*Shining Rod of Equilibrium*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702651', '2700', 'Weapon 3');      /*Forgotten Hero's Knuckle*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702653', '3200', 'Weapon 3');      /*Transparent Arm Cannon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702654', '2700', 'Weapon 3');      /*Mr. Hot Spring Kitty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702655', '4700', 'Weapon 3');      /*Lil Mercedes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702656', '3700', 'Weapon 3');      /*Lil Luminous*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702657', '6300', 'Weapon 3');      /*Lil Shade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702658', '4700', 'Weapon 3');      /*Holiday Tree Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702659', '3200', 'Weapon 3');      /*Timemaster*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702665', '6300', 'Weapon 3');      /*Lil Evan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702666', '6800', 'Weapon 3');      /*Lil Aran*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702667', '3200', 'Weapon 3');      /*Lil Phantom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702668', '5600', 'Weapon 3');      /*Winter Deer Tambourine*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702671', '3200', 'Weapon 3');      /*Magic Tome Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702672', '2700', 'Weapon 3');      /*Duckling Cross Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702673', '7400', 'Weapon 3');      /*Monkey Banana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702675', '3200', 'Weapon 3');      /*Smile Seed Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702676', '6800', 'Weapon 3');      /*Muse Crystal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702677', '7400', 'Weapon 3');      /*Lil Damien*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702678', '4700', 'Weapon 3');      /*Lil Alicia*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702679', '2700', 'Weapon 3');      /*Playful Black Nyanya*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702682', '6300', 'Weapon 3');      /*Triple Fish Skewer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702684', '5600', 'Weapon 3');      /*Blue Phoenix Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702685', '3200', 'Weapon 3');      /*Red Phoenix Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702686', '2700', 'Weapon 3');      /*Sweet Pig Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702692', '5000', 'Weapon 3');      /*Chicken Cutie Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702693', '4300', 'Weapon 3');      /*Bubble Leaf Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702697', '6300', 'Weapon 3');      /*Cup Cat Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702698', '3700', 'Weapon 3');      /*Blaster Weapon*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702699', '5000', 'Weapon 3');      /*Colorful Beach Ball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702710', '5000', 'Weapon 3');      /*Kamaitachi's Sickle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702711', '5000', 'Weapon 3');      /*Owl Spellbook*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702712', '5000', 'Weapon 3');      /*Moon Bunny Bell Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702713', '4300', 'Weapon 3');      /*Bichon Paw Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702714', '6800', 'Weapon 3');      /*Witch's Staff*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000000', '4700', 'Hat');      /*Blue Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000001', '5600', 'Hat');      /*Fine Black Hanbok Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000002', '3200', 'Hat');      /*Fine Blue Hanbok Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000003', '3700', 'Hat');      /*Scream Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000004', '7400', 'Hat');      /*Old School Uniform Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000005', '3200', 'Hat');      /*Men's Ninja Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000006', '4700', 'Hat');      /*Samurai Hair-do*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000007', '4300', 'Hat');      /*Hat of Death*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000008', '6300', 'Hat');      /*Detective Hat*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000009', '4300', 'Hat');      /*Mesoranger Red Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000010', '3700', 'Hat');      /*Mesoranger Blue Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000011', '6300', 'Hat');      /*Mesoranger Green Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000012', '3700', 'Hat');      /*Mesoranger Black Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000013', '5000', 'Hat');      /*Yellow Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000014', '2700', 'Hat');      /*Green Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000015', '4300', 'Hat');      /*Blue Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000016', '3700', 'Hat');      /*Red Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000017', '6300', 'Hat');      /*Van Hat*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000018', '7400', 'Hat');      /*Kuniragi Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000019', '6300', 'Hat');      /*Green Goya Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000020', '3700', 'Hat');      /*Chief Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000021', '7400', 'Hat');      /*General's Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000022', '5000', 'Hat');      /*General's Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000023', '6800', 'Hat');      /*Race Ace Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000024', '4300', 'Hat');      /*Oriental Bridegroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000026', '3700', 'Hat');      /*Santa Boy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000027', '3200', 'Hat');      /*Lunar Celebration Cap*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000028', '7400', 'Hat');      /*Korean Official Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000029', '6300', 'Hat');      /*Wedding veil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000030', '6800', 'Hat');      /*Sachiel Wig (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000031', '2700', 'Hat');      /*Veamoth Wig (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000032', '6300', 'Hat');      /*Janus Wig (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000035', '3700', 'Hat');      /*White Floral Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000041', '5000', 'Hat');      /*Napoleon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000042', '5600', 'Hat');      /*Napoleon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000043', '3700', 'Hat');      /*Santa Hat*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000044', '3700', 'Hat');      /*Twinkling Boy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000045', '6800', 'Hat');      /*Dark Force Horns (M) */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000046', '7400', 'Hat');      /*Elven Spirit Band (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000050', '3200', 'Hat');      /*Mint Snow Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000051', '6300', 'Hat');      /*Aerial Elven Spirit Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000058', '5000', 'Hat');      /*Evergreen Magistrate Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000059', '5000', 'Hat');      /*[MS Custom] Mesoranger Black Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000060', '7400', 'Hat');      /*Dark Force Horns*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000061', '5600', 'Hat');      /*Alps Boy Hat*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000062', '2700', 'Hat');      /*Cool Carrot Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000069', '6800', 'Hat');      /*Moonlight Floral Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000070', '3700', 'Hat');      /*Bon-Bon Pony Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000071', '4700', 'Hat');      /*Blue Pedora*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000072', '6800', 'Hat');      /*Jumpy Blue*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000074', '2700', 'Hat');      /*Yellow Picnic Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000076', '6300', 'Hat');      /*Red Dusk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000077', '5600', 'Hat');      /*Dylan's Silk Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000079', '3700', 'Hat');      /*Mad Doctor Bolt*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000080', '6800', 'Hat');      /*Santa Boy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002314', '2700', 'Hat');      /*Zombie Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000082', '6300', 'Hat');      /*Fashionista Wig (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000083', '6300', 'Hat');      /*Maple Festival Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000084', '3700', 'Hat');      /*Little Wing Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000085', '5000', 'Hat');      /*Aquamarine Gem*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000086', '3700', 'Hat');      /*Team Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000087', '3700', 'Hat');      /*Ribbon Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000088', '4700', 'Hat');      /*Kinesis Wig*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000089', '2700', 'Hat');      /*Kinesis Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000090', '6800', 'Hat');      /*Penguin Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000091', '4300', 'Hat');      /*Bloody Guardian Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001000', '3200', 'Hat');      /*Orange Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001001', '4700', 'Hat');      /*Hanbok Jobawi*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001002', '3700', 'Hat');      /*Witch Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001003', '5600', 'Hat');      /*Pink Nurse Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001004', '6300', 'Hat');      /*White Nurse Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001005', '6300', 'Hat');      /*Women's Ninja Hat*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001006', '5000', 'Hat');      /*SF Ninja Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001007', '4700', 'Hat');      /*Miko Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001008', '6300', 'Hat');      /*A Ladylike Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001009', '3700', 'Hat');      /*Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001010', '3200', 'Hat');      /*Teddy Bear Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001011', '3700', 'Hat');      /*Strawberry Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001012', '2700', 'Hat');      /*Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001013', '2700', 'Hat');      /*Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001014', '2700', 'Hat');      /*Mesoranger Pink Helmet*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001015', '2700', 'Hat');      /*Mesoranger Yellow Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001016', '4300', 'Hat');      /*Mesoranger Black Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001017', '7400', 'Hat');      /*Princess Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001018', '6300', 'Hat');      /*Lady Blue*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001019', '5000', 'Hat');      /*Lady Pink*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001020', '3200', 'Hat');      /*Lady Yellow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001021', '3200', 'Hat');      /*The Gabera Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001022', '3700', 'Hat');      /*Van Hat with Heart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001023', '6300', 'Hat');      /*Picnic Hat*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001024', '3200', 'Hat');      /*Diamond Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001025', '4700', 'Hat');      /*Ruby Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001026', '4700', 'Hat');      /*Red-Feathered Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001027', '6300', 'Hat');      /*Blue-Feathered Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001028', '3700', 'Hat');      /*Jami Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001029', '3200', 'Hat');      /*Yellow Bride's Veil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001030', '4700', 'Hat');      /*Diao Chan Headpiece*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001031', '2700', 'Hat');      /*White Cat Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001032', '3700', 'Hat');      /*Black Cat Ears*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001033', '3700', 'Hat');      /*Maid Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001034', '2700', 'Hat');      /*Oriental Princess Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001036', '3700', 'Hat');      /*Santa Girl Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001037', '6800', 'Hat');      /*Leopard Print Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001038', '3700', 'Hat');      /*Korean Dress Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001039', '3200', 'Hat');      /*Lunar Celebration Ornament*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001040', '4300', 'Hat');      /*Royal Maid Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001041', '5600', 'Hat');      /*Royal Nurse Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001042', '4300', 'Hat');      /*Purple Bride's Veil*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001043', '3200', 'Hat');      /*Royal Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001044', '6300', 'Hat');      /*Green Bride's Veil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001045', '5600', 'Hat');      /*Sachiel Wig (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001046', '3700', 'Hat');      /*Veamoth Wig (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001047', '3200', 'Hat');      /*Janus Wig (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001048', '4700', 'Hat');      /*Gothic Mini Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001049', '2700', 'Hat');      /*Gothic Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001055', '5000', 'Hat');      /*Strawberry Milk Frill Bonnet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001058', '3200', 'Hat');      /*Native American Chief Hat*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001061', '6800', 'Hat');      /*Elizabeth Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001062', '3200', 'Hat');      /*Elizabeth Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001063', '2700', 'Hat');      /*Dear Christmas*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001064', '2700', 'Hat');      /*Twinkling Girl Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001065', '7400', 'Hat');      /*Pink Angel Wing Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001066', '3700', 'Hat');      /*Red Hood Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001068', '3700', 'Hat');      /*Dark Force Horns (F) */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001069', '7400', 'Hat');      /*Elven Spirit Band (F) */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001070', '3700', 'Hat');      /*Gold Angora Gatsby*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001071', '6300', 'Hat');      /*Silver Angora Gatsby*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001075', '4700', 'Hat');      /*Star of Ereve*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001076', '3700', 'Hat');      /*Cherry Snow Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001077', '5600', 'Hat');      /*Aerial Elven Spirit Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001082', '3700', 'Hat');      /*Red Hood Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001083', '3200', 'Hat');      /*Angelic Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001084', '6800', 'Hat');      /*Angelic Navy Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001085', '5000', 'Hat');      /*Pinky Butterfly Hair Pin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001087', '4300', 'Hat');      /*Dark Force Horns*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001088', '6300', 'Hat');      /*Alps Girl Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001089', '3200', 'Hat');      /*Warm Carrot Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001090', '5000', 'Hat');      /*Fluffy Cat Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001091', '5000', 'Hat');      /*Dumpling Head Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001092', '2700', 'Hat');      /*Moonlight Floral Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001093', '3200', 'Hat');      /*Bon-Bon Pony Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001094', '5600', 'Hat');      /*Lace Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001095', '3700', 'Hat');      /*Jumpy Pink*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001097', '7400', 'Hat');      /*White Picnic Beret*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001098', '3700', 'Hat');      /*Blue Twilight*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001099', '4700', 'Hat');      /*Rosalia's Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001100', '7400', 'Hat');      /*Ribbon Angel Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001101', '7400', 'Hat');      /*Santa Girl Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001103', '4700', 'Hat');      /*Fashionista Wig (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001104', '4700', 'Hat');      /*Maple Festival Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001105', '7400', 'Hat');      /*Little Wing Fedora*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001106', '7400', 'Hat');      /*Pink Diamond Gem*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001107', '4700', 'Hat');      /*Momo Wig*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001108', '4300', 'Hat');      /*Ribbon Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001109', '2700', 'Hat');      /*Odette Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001110', '6800', 'Hat');      /*Kinesis Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001111', '7400', 'Hat');      /*Kinesis Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001112', '6300', 'Hat');      /*Penguin Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1001113', '6800', 'Hat');      /*Bloody Veil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002000', '3700', 'Hat');      /*Brown Flight Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002015', '6300', 'Hat');      /*Red Swimming Goggle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002018', '6300', 'Hat');      /*Green Camping Hat*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002031', '7400', 'Hat');      /*Cat Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002032', '6300', 'Hat');      /*Puffy Brown Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002070', '3200', 'Hat');      /*Green Swimming Goggle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002071', '4300', 'Hat');      /*Blue Swimming Goggle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002076', '4700', 'Hat');      /*Red Flight Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002077', '5600', 'Hat');      /*Blue Flight Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002078', '6800', 'Hat');      /*Sky Blue Camping Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002079', '4700', 'Hat');      /*Pink Camping Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002186', '7400', 'Hat');      /*Transparent Hat*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002187', '4700', 'Hat');      /*Blue Cowboy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002188', '3700', 'Hat');      /*Red Cowboy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002189', '5000', 'Hat');      /*Dark Cowboy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002190', '4700', 'Hat');      /*Blue Pre-School Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002191', '6800', 'Hat');      /*Red Pre-School Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002192', '3200', 'Hat');      /*Blue Chinese Undead Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002193', '6800', 'Hat');      /*Maroon Chinese Undead Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002194', '5600', 'Hat');      /*Rosy Swimming Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002195', '5000', 'Hat');      /*Flowery Swimming Cap*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002196', '6300', 'Hat');      /*Blue Baseball Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002197', '4700', 'Hat');      /*Red Baseball Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002198', '7400', 'Hat');      /*Indigo Baseball Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002199', '4700', 'Hat');      /*Black Baseball Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002200', '2700', 'Hat');      /*Green Visor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002201', '4700', 'Hat');      /*Sky Blue Visor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002202', '6800', 'Hat');      /*Orange Visor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002203', '3700', 'Hat');      /*Yellow Rain Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002204', '6300', 'Hat');      /*Red Rain Cap*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002205', '2700', 'Hat');      /*Sky Blue Rain Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002206', '7400', 'Hat');      /*Green Rain Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002219', '5000', 'Hat');      /*Destreza Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002220', '4300', 'Hat');      /*Black Slanted Visor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002221', '5000', 'Hat');      /*Purple Slanted Visor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002222', '4300', 'Hat');      /*Red Upside-Down Visor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002223', '7400', 'Hat');      /*Blue Upside-Down Visor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002224', '5000', 'Hat');      /*Tiger Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002225', '7400', 'Hat');      /*Santa Hat*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002226', '7400', 'Hat');      /*Fashionable Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002227', '2700', 'Hat');      /*Blue Fisherman Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002228', '4700', 'Hat');      /*Cabbie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002229', '2700', 'Hat');      /*Goggled Red Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002230', '2700', 'Hat');      /*Goggled Black Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002231', '4700', 'Hat');      /*Goggled Blue Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002232', '2700', 'Hat');      /*Starry Red Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002233', '6300', 'Hat');      /*Starry Pink Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002234', '6300', 'Hat');      /*Starry Sky Blue Beanie*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002235', '4300', 'Hat');      /*Sky Blue Goggled Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002236', '7400', 'Hat');      /*Khaki Goggled Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002237', '5600', 'Hat');      /*Blue Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002238', '4300', 'Hat');      /*Construction Hardhat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002239', '5600', 'Hat');      /*The Legendary Gold Poop Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002240', '6300', 'Hat');      /*Hajimaki*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002241', '7400', 'Hat');      /*Techwin Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002250', '5000', 'Hat');      /*Headphone Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002251', '4700', 'Hat');      /*The Graduation Hat*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002255', '4300', 'Hat');      /*Circus Cowboy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002256', '6300', 'Hat');      /*Orange Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002257', '5000', 'Hat');      /*Blue Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002258', '4300', 'Hat');      /*Blue Diamondy Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002259', '2700', 'Hat');      /*Black Top Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002260', '7400', 'Hat');      /*Yellow Trucker Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002261', '5600', 'Hat');      /*Blue Trucker Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002262', '2700', 'Hat');      /*Red Trucker Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002263', '5000', 'Hat');      /*Green Trucker Hat*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002264', '6300', 'Hat');      /*Hardhat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002265', '3700', 'Hat');      /*Elf's Ear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002266', '4700', 'Hat');      /*Basic Earmuff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002279', '4700', 'Hat');      /*Bunny Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002280', '4700', 'Hat');      /*Ducky Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002290', '6300', 'Hat');      /*Camouflaged Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002291', '6300', 'Hat');      /*Starred Hunting Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002292', '5000', 'Hat');      /*Pink Frill Pajama Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002293', '6300', 'Hat');      /*Blue Pajama Hat*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002294', '6800', 'Hat');      /*Red Frill Pajama Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002295', '2700', 'Hat');      /*Chef's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002296', '5000', 'Hat');      /*Slime Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002297', '3200', 'Hat');      /*Brown Bucket Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002298', '5000', 'Hat');      /*Blue Bucket Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002299', '2700', 'Hat');      /*Cubic Newsie Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002300', '7400', 'Hat');      /*Green Picnic Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002301', '2700', 'Hat');      /*Yellow Picnic Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002302', '6300', 'Hat');      /*Pink Picnic Hat*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002303', '5000', 'Hat');      /*Blue Picnic Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002304', '3200', 'Hat');      /*Silver Chain Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002305', '4700', 'Hat');      /*Blue Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002306', '7400', 'Hat');      /*Brown Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002307', '3700', 'Hat');      /*Blue B-Ball Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002308', '6300', 'Hat');      /*Orange B-Ball Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002309', '6300', 'Hat');      /*Watermelon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002310', '6800', 'Hat');      /*Flower Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002311', '3200', 'Hat');      /*Traveler's Hat*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002312', '4300', 'Hat');      /*Evil Watermelon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002313', '7400', 'Hat');      /*Palm Tree Hat*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002315', '5000', 'Hat 2');      /*Red Straw Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002316', '5600', 'Hat 2');      /*Blue Straw Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002317', '4700', 'Hat 2');      /*Grey Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002318', '5600', 'Hat 2');      /*Red Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002319', '3700', 'Hat 2');      /*Whale Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002320', '7400', 'Hat 2');      /*Fuji Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002321', '3200', 'Hat 2');      /*Crow Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002322', '3700', 'Hat 2');      /*Lobster Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002331', '6800', 'Hat 2');      /*Wind Goblin*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002332', '4700', 'Hat 2');      /*Cloud Goblin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002333', '4300', 'Hat 2');      /*Big Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002334', '2700', 'Hat 2');      /*Raccoon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002335', '3200', 'Hat 2');      /*Triangular Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002336', '6300', 'Hat 2');      /*Noble Moca*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002337', '4700', 'Hat 2');      /*Laurel Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002341', '6300', 'Hat 2');      /*Starry Olive Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002342', '6300', 'Hat 2');      /*Olive Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002343', '3200', 'Hat 2');      /*White Beanie*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002344', '2700', 'Hat 2');      /*Woodsman Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002345', '6800', 'Hat 2');      /*Party Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002346', '5000', 'Hat 2');      /*Blue Corporal Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002347', '6300', 'Hat 2');      /*Brown Corporal Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002348', '3700', 'Hat 2');      /*Bamboo Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002349', '6800', 'Hat 2');      /*Black Cowboy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002350', '7400', 'Hat 2');      /*Red Cowboy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002351', '2700', 'Hat 2');      /*Yellow Cowboy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002352', '3700', 'Hat 2');      /*Red Knitted Hat*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002353', '5000', 'Hat 2');      /*Purple Knitted Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002354', '3200', 'Hat 2');      /*Yellow Knitted Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002355', '5000', 'Hat 2');      /*Blue Kitty Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002356', '7400', 'Hat 2');      /*Yellow Kitty Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002358', '4700', 'Hat 2');      /*Green Knitted Gumball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002359', '5600', 'Hat 2');      /*Blue Knitted Gumball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002360', '3200', 'Hat 2');      /*Pink Knitted Gumball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002361', '3200', 'Hat 2');      /*Red Festive Gumball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002362', '5600', 'Hat 2');      /*White Festive Gumball*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002367', '2700', 'Hat 2');      /*Angel Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002368', '7400', 'Hat 2');      /*Reindeer Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002369', '5600', 'Hat 2');      /*Antenna Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002370', '7400', 'Hat 2');      /*Black-Striped Feathered Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002371', '5600', 'Hat 2');      /*Red-Dotted Feathered Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002372', '2700', 'Hat 2');      /*Feathered Bandana with Hearts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002373', '7400', 'Hat 2');      /*Cloth Wrapper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002374', '7400', 'Hat 2');      /*Red Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002375', '3200', 'Hat 2');      /*Yellow Beret*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002376', '5600', 'Hat 2');      /*Pink Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002384', '3700', 'Hat 2');      /*Casual Cowboy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002385', '4700', 'Hat 2');      /*Red Eskimo Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002386', '7400', 'Hat 2');      /*Brown Eskimo Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002387', '2700', 'Hat 2');      /*Green Eskimo Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002388', '7400', 'Hat 2');      /*Peter Pan Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002389', '5000', 'Hat 2');      /*Devilish Horns*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002396', '5000', 'Hat 2');      /*Hawaiian Flower*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002397', '4300', 'Hat 2');      /*Sunflower Petal*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002409', '5000', 'Hat 2');      /*Tin Bucket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002410', '3700', 'Hat 2');      /*Pink Turban*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002411', '4300', 'Hat 2');      /*Yellow Turban*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002412', '5000', 'Hat 2');      /*Skyblue Turban*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002413', '4700', 'Hat 2');      /*Octopus Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002414', '4300', 'Hat 2');      /*Orange Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002415', '6300', 'Hat 2');      /*Zombie Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002416', '6300', 'Hat 2');      /*Slime Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002417', '2700', 'Hat 2');      /*Drake Hat*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002420', '2700', 'Hat 2');      /*Biker Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002421', '6800', 'Hat 2');      /*Pink Knitted Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002422', '5000', 'Hat 2');      /*Blue Knitted Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002423', '3700', 'Hat 2');      /*Yellow Knitted Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002426', '7400', 'Hat 2');      /*Beige Goya Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002427', '2700', 'Hat 2');      /*Green Goya Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002428', '5000', 'Hat 2');      /*Beige Checkered Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002429', '3700', 'Hat 2');      /*Meshcap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002431', '5000', 'Hat 2');      /*Bull's Horn*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002432', '6800', 'Hat 2');      /*Spring Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002433', '2700', 'Hat 2');      /*Summer Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002434', '5000', 'Hat 2');      /*Autumn Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002435', '3200', 'Hat 2');      /*Korean Flower Petal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002437', '6300', 'Hat 2');      /*Guan Yu Headpiece*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002438', '3700', 'Hat 2');      /*Zhu-Ge-Liang Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002439', '6800', 'Hat 2');      /*Blue Jelly Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002440', '6300', 'Hat 2');      /*Pink Jelly Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002442', '5600', 'Hat 2');      /*Rainbow Afro Wig*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002443', '3200', 'Hat 2');      /*Patissier Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002444', '6300', 'Hat 2');      /*Liu Bei Headpiece*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002445', '3200', 'Hat 2');      /*Cao Cao Headpiece*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002446', '3200', 'Hat 2');      /*Sun Quan Headpiece*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002447', '4300', 'Hat 2');      /*Rolled Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002449', '6800', 'Hat 2');      /*Winged Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002450', '2700', 'Hat 2');      /*Conch Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002451', '2700', 'Hat 2');      /*Starfish*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002456', '7400', 'Hat 2');      /*Horoscope Hat (Aquarius)*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002457', '5600', 'Hat 2');      /*Horoscope Hat (Pisces)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002458', '7400', 'Hat 2');      /*Horoscope Hat (Aries)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002459', '3700', 'Hat 2');      /*Horoscope Hat (Taurus)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002460', '3700', 'Hat 2');      /*Horoscope Hat (Gemini)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002461', '6300', 'Hat 2');      /*Horoscope Hat (Cancer)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002462', '7400', 'Hat 2');      /*Horoscope Hat (Leo)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002463', '5600', 'Hat 2');      /*Horoscope Hat (Virgo)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002464', '3700', 'Hat 2');      /*Horoscope Hat (Libra)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002465', '4700', 'Hat 2');      /*Horoscope Hat (Scorpius)*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002466', '4700', 'Hat 2');      /*Horoscope Hat (Sagittarius)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002467', '5600', 'Hat 2');      /*Horoscope Hat (Capricorn)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002468', '7400', 'Hat 2');      /*Golden Bulldog Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002469', '5600', 'Hat 2');      /*Jester Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002470', '4300', 'Hat 2');      /*Welding Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002472', '2700', 'Hat 2');      /*Cabbage Patch Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002476', '3200', 'Hat 2');      /*Rough Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002477', '7400', 'Hat 2');      /*Slime Hair Pin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002478', '6300', 'Hat 2');      /*Mushroom Hair Pin*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002479', '6300', 'Hat 2');      /*Snowman Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002480', '7400', 'Hat 2');      /*White Wig Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002481', '7400', 'Hat 2');      /*Black Snowboard Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002482', '6800', 'Hat 2');      /*Red Snowboard Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002484', '4300', 'Hat 2');      /*Polar Bear Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002485', '4700', 'Hat 2');      /*Grey Visor Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002486', '5000', 'Hat 2');      /*Green Visor Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002487', '4300', 'Hat 2');      /*Rainbow Visor Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002488', '7400', 'Hat 2');      /*Military Fur Hat*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002489', '4700', 'Hat 2');      /*Football Helmet (Home)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002490', '2700', 'Hat 2');      /*Football Helmet (Away)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002491', '3200', 'Hat 2');      /*Musashi Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002493', '2700', 'Hat 2');      /*Teddy Bear Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002495', '5000', 'Hat 2');      /*Angora Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002496', '7400', 'Hat 2');      /*Black Skull Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002497', '3700', 'Hat 2');      /*Hunting Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002498', '5000', 'Hat 2');      /*Bald Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002499', '4300', 'Hat 2');      /*White Tiger Hat*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002500', '4300', 'Hat 2');      /*Korean Flag Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002501', '2700', 'Hat 2');      /*Reggae Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002502', '7400', 'Hat 2');      /*Vintage Denim Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002503', '3700', 'Hat 2');      /*Vintage Pink Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002504', '3200', 'Hat 2');      /*Old Fisherman Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002505', '3700', 'Hat 2');      /*Sergeant Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002506', '2700', 'Hat 2');      /*Flower Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002507', '6800', 'Hat 2');      /*Soccer Ball Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002512', '2700', 'Hat 2');      /*Red Spirit Bandana*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002513', '6800', 'Hat 2');      /*Maple Party Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002519', '4700', 'Hat 2');      /*White Felt Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002520', '2700', 'Hat 2');      /*Red Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002521', '5000', 'Hat 2');      /*White Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002522', '4700', 'Hat 2');      /*Pink-Dotted Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002523', '3200', 'Hat 2');      /*Paper Boat Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002524', '6800', 'Hat 2');      /*Coke Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002525', '4700', 'Hat 2');      /*Mummy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002526', '3700', 'Hat 2');      /*Skull Hat*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002534', '6300', 'Hat 2');      /*White Puppy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002536', '6300', 'Hat 2');      /*Brown Paperbag Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002542', '5000', 'Hat 2');      /*Acorn Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002543', '4300', 'Hat 2');      /*Acorn Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002544', '2700', 'Hat 2');      /*Pumpkin Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002545', '4300', 'Hat 2');      /*Yellow Slime Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002548', '4300', 'Hat 2');      /*White Rabbit Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002549', '7400', 'Hat 2');      /*Black Cat Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002552', '2700', 'Hat 2');      /*Moon Bunny Headgear*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002555', '3700', 'Hat 2');      /*Demon Goblin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002556', '2700', 'Hat 2');      /*Maple-Stein Head*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002557', '2700', 'Hat 2');      /*Jr. Lioner Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002558', '6800', 'Hat 2');      /*Werebeast*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002559', '2700', 'Hat 2');      /*Nordic Knitted Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002560', '3200', 'Hat 2');      /*Striped Knitted Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002565', '2700', 'Hat 2');      /*Fur Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002566', '4700', 'Hat 2');      /*Skull Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002567', '4300', 'Hat 2');      /*Elf Hat*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002568', '5600', 'Hat 2');      /*Tweed Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002569', '6300', 'Hat 2');      /*Candlelight hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002570', '3700', 'Hat 2');      /*Pastel Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002575', '5000', 'Hat 2');      /*Angel Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002576', '3200', 'Hat 2');      /*Fallen Angel Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002582', '3700', 'Hat 2');      /*Maximus Galea*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002583', '7400', 'Hat 2');      /*Wrestling Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002590', '7400', 'Hat 2');      /*Star Baseball Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002591', '3700', 'Hat 2');      /*Leatty Hat*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002592', '6800', 'Hat 2');      /*Sun Wu Kong Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002593', '4700', 'Hat 2');      /*Smiley Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002594', '5600', 'Hat 2');      /*Goggled Smiley Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002596', '6300', 'Hat 2');      /*Bulldog Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002597', '5000', 'Hat 2');      /*Husky Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002598', '6300', 'Hat 2');      /*Rabbit Ear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002599', '3700', 'Hat 2');      /*Golden Trench Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002605', '2700', 'Hat 2');      /*Jet Black Head Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002607', '6300', 'Hat 2');      /*Zhu Ba Jie Hat*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002608', '5000', 'Hat 2');      /*Superstar Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002609', '6300', 'Hat 2');      /*Crazy Bunny Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002650', '3200', 'Hat 2');      /*Vintage Grey Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002653', '3700', 'Hat 2');      /*Stack of Books*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002654', '7400', 'Hat 2');      /*Orange Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002660', '5000', 'Hat 2');      /*Orange Cap with Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002661', '4300', 'Hat 2');      /*Bird Nest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002665', '2700', 'Hat 2');      /*Tomato Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002666', '4700', 'Hat 2');      /*White Basic Cap*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002667', '7400', 'Hat 2');      /*Star Hair Pin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002672', '3200', 'Hat 2');      /*Helm of the Golden Monk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002673', '6800', 'Hat 2');      /*Helm of the Silver Monk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002674', '4700', 'Hat 2');      /*Helm of the Bronze Monk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002678', '3700', 'Hat 2');      /*Old Hockey Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002679', '6800', 'Hat 2');      /*Eye Poppers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002691', '6800', 'Hat 2');      /*Centaurus Horns*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002692', '7400', 'Hat 2');      /*Centaurus Horns (Ghost)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002693', '5000', 'Hat 2');      /*Centaurus Horns (Green)*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002694', '4700', 'Hat 2');      /*Centaurus Horns (Light)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002695', '5000', 'Hat 2');      /*Soul Teddy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002696', '3700', 'Hat 2');      /*Stoplight Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002697', '5600', 'Hat 2');      /*Devilfish Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002698', '5600', 'Hat 2');      /*Vintage Khaki Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002700', '5000', 'Hat 2');      /*Big Green Eye*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002701', '6300', 'Hat 2');      /*Huge Green Lips*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002703', '4300', 'Hat 2');      /*Big Blue Eye - Blue Skin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002704', '6800', 'Hat 2');      /*Big Blue Eye - Normal Skin*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002705', '2700', 'Hat 2');      /*Huge Blue Lips*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002706', '2700', 'Hat 2');      /*Huge Red Lips*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002708', '4700', 'Hat 2');      /*Red Vintage Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002709', '2700', 'Hat 2');      /*Snowy Knitted Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002710', '6800', 'Hat 2');      /*Pink Kitty Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002711', '6800', 'Hat 2');      /*White Kitty Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002712', '4300', 'Hat 2');      /*Black Kitty Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002713', '6800', 'Hat 2');      /*Black Bubble Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002714', '3200', 'Hat 2');      /*Christmas Tree Hat*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002715', '3700', 'Hat 2');      /*Military Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002720', '4700', 'Hat 2');      /*Lovely Christmas*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002721', '2700', 'Hat 2');      /*Raccoon Earmuffs*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002722', '4700', 'Hat 2');      /*Teddy Earmuffs*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002724', '3200', 'Hat 2');      /*Cat Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002725', '3700', 'Hat 2');      /*Pierced Apple*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002726', '5000', 'Hat 2');      /*Umbrella Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002727', '2700', 'Hat 2');      /*Huge Pink Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002734', '6800', 'Hat 2');      /*Chinese Lion Headgear*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002735', '2700', 'Hat 2');      /*Glowy Smile Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002736', '5000', 'Hat 2');      /*Glowy Patterned Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002738', '6300', 'Hat 2');      /*Bunny Earmuffs*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002741', '5000', 'Hat 2');      /*Yellow Baby Dragon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002742', '3700', 'Hat 2');      /*Baby Turkey Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002745', '4700', 'Hat 2');      /*Baby Gold Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002746', '5000', 'Hat 2');      /*Sleepy Turkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002747', '6300', 'Hat 2');      /*Superstar Headphones*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002748', '7400', 'Hat 2');      /*Apple-Green Hood*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002752', '6800', 'Hat 2');      /*Celestial Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002753', '6800', 'Hat 2');      /*Stylish Pink Cotton Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002754', '6800', 'Hat 2');      /*Orange Mushroom Scholar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002755', '2700', 'Hat 2');      /*Hero's Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002756', '3200', 'Hat 2');      /*Hero's Casket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002759', '4700', 'Hat 2');      /*Maple Hood Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002760', '4700', 'Hat 2');      /*Globe Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002761', '4300', 'Hat 2');      /*Maple Leaf eye mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002770', '4700', 'Hat 2');      /*Cone Ears*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002771', '3200', 'Hat 2');      /*Tiger Cub Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002774', '6800', 'Hat 2');      /*Victory Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002775', '4300', 'Hat 2');      /*3rd Anniversary Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002784', '6300', 'Hat 2');      /*"A" Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002785', '5000', 'Hat 2');      /*Prismatic Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002785', '4300', 'Hat 2');      /*Prismatic Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002785', '4700', 'Hat 2');      /*Prismatic Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002785', '6300', 'Hat 2');      /*Prismatic Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002785', '5000', 'Hat 2');      /*Prismatic Sun Cap*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002785', '3700', 'Hat 2');      /*Prismatic Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002785', '2700', 'Hat 2');      /*Prismatic Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002796', '3700', 'Hat 2');      /*Cutie Birk Hat*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002803', '3200', 'Hat 3');      /*Mrs. Octopus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002804', '4700', 'Hat 3');      /*Brown Felt Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002811', '6300', 'Hat 3');      /*Striped Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002820', '3200', 'Hat 3');      /*Inferno Horns*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002821', '2700', 'Hat 3');      /*Violet Heart Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002822', '4700', 'Hat 3');      /*Bird Nest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002823', '5000', 'Hat 3');      /*Scarface Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002824', '3200', 'Hat 3');      /*Noob Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002831', '6300', 'Hat 3');      /*Leo Hairpin*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002834', '3700', 'Hat 3');      /*Scorpius Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002835', '3200', 'Hat 3');      /*Sagittarius Hair Clip*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002836', '6800', 'Hat 3');      /*Capricorn Hair Clip*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002837', '6800', 'Hat 3');      /*Tengu Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002839', '3200', 'Hat 3');      /*Pumpkin Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002840', '7400', 'Hat 3');      /*Hatched Bird Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002842', '3700', 'Hat 3');      /*Golden Fox Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002843', '7400', 'Hat 3');      /*Silver Fox Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002844', '3200', 'Hat 3');      /*Chipmunk Ears*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002845', '6300', 'Hat 3');      /*Pink Bunny Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002846', '5000', 'Hat 3');      /*Blue Bow Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002847', '6800', 'Hat 3');      /*Frog Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002849', '6800', 'Hat 3');      /*Panda Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002863', '3700', 'Hat 3');      /*Bear Tassel Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002870', '6800', 'Hat 3');      /*Moon Bunny Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002876', '4700', 'Hat 3');      /*Holly Hair Clip*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002877', '5000', 'Hat 3');      /*Cow Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002878', '3700', 'Hat 3');      /*Snow Flake Hat*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002882', '5000', 'Hat 3');      /*Owl Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002884', '6800', 'Hat 3');      /*Red Panda Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002885', '4300', 'Hat 3');      /*Pink Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002886', '6300', 'Hat 3');      /*Strawberry Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002887', '3200', 'Hat 3');      /*Pink Ribbon Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002888', '4700', 'Hat 3');      /*Red Ribbon Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002889', '5600', 'Hat 3');      /*Purple Ribbon Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002890', '6800', 'Hat 3');      /*Blue Ribbon Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002891', '6300', 'Hat 3');      /*Green Ribbon Hairband*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002903', '5000', 'Hat 3');      /*Pink Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002907', '3700', 'Hat 3');      /*Checkered Fedora*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002912', '3200', 'Hat 3');      /*Iljimae Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002913', '3200', 'Hat 3');      /*Miranda Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002919', '5600', 'Hat 3');      /*Courageous Little Lamb Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002920', '3700', 'Hat 3');      /*Pink Mini Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002921', '3700', 'Hat 3');      /*Blue Mini Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002922', '6300', 'Hat 3');      /*Navy Hoodie Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002923', '4700', 'Hat 3');      /*Treacherous Wolf Hat*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002928', '4700', 'Hat 3');      /*Pink Star Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002929', '6800', 'Hat 3');      /*Colorful Striped Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002930', '4700', 'Hat 3');      /*6th Anniversary Cone Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002937', '2700', 'Hat 3');      /*Felt Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002941', '5000', 'Hat 3');      /*Moon Bloom Hair Pin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002942', '4700', 'Hat 3');      /*Green Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002943', '6800', 'Hat 3');      /*Sailor Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002944', '4700', 'Hat 3');      /*Honey Bee Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002945', '7400', 'Hat 3');      /*Heart Hairband*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002950', '2700', 'Hat 3');      /*Pink Flower Headwrap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002951', '6800', 'Hat 3');      /*Yellow Flower Headwrap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002952', '4700', 'Hat 3');      /*Purple Flower Headwrap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002953', '2700', 'Hat 3');      /*Fluttering Sunhat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002954', '4700', 'Hat 3');      /*Aran Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002955', '6300', 'Hat 3');      /*Brave Musashi Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002956', '5000', 'Hat 3');      /*Blue Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002957', '4300', 'Hat 3');      /*Pink Bean Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002960', '6800', 'Hat 3');      /*Black Crown*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002961', '3200', 'Hat 3');      /*Gray Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002962', '3700', 'Hat 3');      /*Peony Flower Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002967', '4700', 'Hat 3');      /*Teru Teru Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002968', '5000', 'Hat 3');      /*Pancake Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002969', '5000', 'Hat 3');      /*Brown Puppy Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002970', '2700', 'Hat 3');      /*Moon Bunny Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002973', '4700', 'Hat 3');      /*Mysterious Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002974', '7400', 'Hat 3');      /*Jr. Lucida Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002975', '4700', 'Hat 3');      /*Aviator Pilot Goggles*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002976', '4700', 'Hat 3');      /*Maid Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002978', '2700', 'Hat 3');      /*Cute Mouse Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002979', '2700', 'Hat 3');      /*Marbum Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002983', '4700', 'Hat 3');      /*We Care! Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002984', '7400', 'Hat 3');      /*Spiegelmann's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002985', '4700', 'Hat 3');      /*Pachinko Marble-box Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002987', '5600', 'Hat 3');      /*Cursed Golden trench helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002995', '6300', 'Hat 3');      /*Royal Navy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002998', '4300', 'Hat 3');      /*Edwin Wig*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002999', '5600', 'Hat 3');      /*Fire Shadow Hair*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003000', '2700', 'Hat 3');      /*Cherry Blossom Hair*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003001', '4300', 'Hat 3');      /*Chaos Metallic Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003005', '4300', 'Hat 3');      /*Maple Racing Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003006', '5000', 'Hat 3');      /*Kitty Camping Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003008', '4700', 'Hat 3');      /*Pharaoh Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003009', '2700', 'Hat 3');      /*Christmas Light Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003010', '3700', 'Hat 3');      /*Dancing Blue Butterfly*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003013', '4700', 'Hat 3');      /*Red Loose-Fit Beanie*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003014', '5000', 'Hat 3');      /*Pink Scooter Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003015', '7400', 'Hat 3');      /*Blue Scooter Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003022', '6800', 'Hat 3');      /*Devil Horns*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003029', '4700', 'Hat 3');      /*Former Hero Female Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003030', '6800', 'Hat 3');      /*Former Hero Male Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003038', '6300', 'Hat 3');      /*Doll Face Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003043', '5600', 'Hat 3');      /*Christmas Bell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003044', '3200', 'Hat 3');      /*Clown Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003047', '4300', 'Hat 3');      /*Bear Hat*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003048', '3700', 'Hat 3');      /*Christmas Wreath*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003049', '6300', 'Hat 3');      /*Giant Bear Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003050', '6800', 'Hat 3');      /*Bunny Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003051', '4300', 'Hat 3');      /*Desert Fox*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003052', '5000', 'Hat 3');      /*Tilted Fedora*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003053', '5000', 'Hat 3');      /*Pink Fur Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003054', '3200', 'Hat 3');      /*White Fur Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003057', '4300', 'Hat 3');      /*Mini Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003058', '4300', 'Hat 3');      /*Christmas Hairpin*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003059', '6800', 'Hat 3');      /*Qi-pao Hair*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003060', '4300', 'Hat 3');      /*Silver Coronet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003070', '2700', 'Hat 3');      /*Tiger-Print Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003071', '4700', 'Hat 3');      /*Pinky Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003072', '4700', 'Hat 3');      /*Black-Lace Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003074', '4700', 'Hat 3');      /*Strawberry Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003077', '5000', 'Hat 3');      /*Knitted Corsage Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003078', '6800', 'Hat 3');      /*Sparkling Butterfly*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003079', '4700', 'Hat 3');      /*Green Leaf Hat*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003080', '5000', 'Hat 3');      /*Cat Set Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003082', '3200', 'Hat 3');      /*Wolf Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003083', '7400', 'Hat 3');      /*Sprout Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003084', '3200', 'Hat 3');      /*Royal Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003089', '4700', 'Hat 3');      /*Evan Wing Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003092', '4300', 'Hat 3');      /*Hawkeye Captain Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003101', '4700', 'Hat 3');      /*Dunas Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003103', '5600', 'Hat 3');      /*6th Anniversary Top Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003109', '5000', 'Hat 3');      /*Royal Rainbow Hood*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003120', '4300', 'Hat 3');      /*Oz Magic Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003121', '2700', 'Hat 3');      /*Evan Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003122', '5000', 'Hat 3');      /*Yellow Petite Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003123', '2700', 'Hat 3');      /*Black Petite Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003130', '4700', 'Hat 3');      /*Shining Feather*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003131', '4700', 'Hat 3');      /*Black Dressy Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003132', '2700', 'Hat 3');      /*Red Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003133', '6300', 'Hat 3');      /*White Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003135', '3700', 'Hat 3');      /*Wild Hunter's Hat*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003136', '2700', 'Hat 3');      /*Battle Mage Goggles*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003141', '3700', 'Hat 3');      /*Straw Sun Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003144', '3700', 'Hat 3');      /*King Crow Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003145', '4700', 'Hat 3');      /*Dragon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003146', '5000', 'Hat 3');      /*Lace Ribbon (Pink)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003147', '3200', 'Hat 3');      /*Maid Headband (Blue)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003148', '3700', 'Hat 3');      /*Pilot Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003149', '4300', 'Hat 3');      /*Rabbit Ear Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003161', '5000', 'Hat 3');      /*Sanctus Combat Veil*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003163', '5600', 'Hat 3');      /*Brown Hunting Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003170', '6300', 'Hat 3');      /*Star Head Wrap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003171', '6800', 'Hat 3');      /*Leather Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003182', '7400', 'Hat 3');      /*Paypal Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003185', '6300', 'Hat 3');      /*Rabbit hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003186', '5600', 'Hat 3');      /*Cat Hood (Pink)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003187', '5000', 'Hat 3');      /*Gray Cat Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003192', '4700', 'Hat 3');      /*Blue Pre-School Uniform Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003193', '4300', 'Hat 3');      /*Red Pre-School Uniform Hat*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003194', '3700', 'Hat 3');      /*Rookie Bobble Heart Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003196', '3700', 'Hat 3');      /*Rudolph Santa Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003202', '3700', 'Hat 3');      /*Golden Beetle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003203', '3200', 'Hat 3');      /*Red Riding Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003204', '3700', 'Hat 3');      /*Courageous Bunny Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003207', '7400', 'Hat 3');      /*Curly Rabbit Poof*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003208', '5600', 'Hat 3');      /*Magic Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003210', '7400', 'Hat 3');      /*Earmuffs and Pom Pom Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003211', '6300', 'Hat 3');      /*Winter 2010 Moon Bunny Hat*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003214', '6300', 'Hat 3');      /*Blue Snowdrop Cunning Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003215', '4700', 'Hat 3');      /*Pink Snowdrop Cunning Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003216', '3700', 'Hat 3');      /*Pirate Captain's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003217', '6300', 'Hat 3');      /*Flower Heir Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003218', '6300', 'Hat 3');      /*Flower Heiress Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003220', '6800', 'Hat 3');      /*Knit Flower Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003221', '3700', 'Hat 3');      /*Pink Polka Dot Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003222', '5000', 'Hat 3');      /*Blue Polka Dot Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003223', '4700', 'Hat 3');      /*Lost Baby Chick*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003226', '3700', 'Hat 3');      /*Rookie Hatchling Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003232', '4700', 'Hat 3');      /*Pretty Teddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003233', '6300', 'Hat 3');      /*Honey Rabbit */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003234', '6300', 'Hat 3');      /*Pink Jeweled Chaplain Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003235', '5000', 'Hat 3');      /*Blue Jeweled Chaplain Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003237', '4700', 'Hat 3');      /*Lion Head*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003238', '4300', 'Hat 3');      /*Gray Puppy Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003239', '2700', 'Hat 3');      /*Raspberry Candy Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003240', '2700', 'Hat 3');      /*Blueberry Candy Hoodie*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003241', '6800', 'Hat 3');      /*6th Anniversary Party Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003247', '6800', 'Hat 3');      /*Mad Hatter's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003249', '2700', 'Hat 3');      /*Topaz Musical Note*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003250', '7400', 'Hat 3');      /*Ruby Musical Note*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003251', '4700', 'Hat 3');      /*Citrine Musical Note*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003252', '6300', 'Hat 3');      /*Amethyst Musical Note*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003253', '4300', 'Hat 3');      /*Amber Musical Note*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003254', '2700', 'Hat 3');      /*Sapphire Musical Note*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003255', '2700', 'Hat 3');      /*Quartz Musical Note*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003256', '6800', 'Hat 3');      /*Emerald Musical Note*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003263', '3700', 'Hat 3');      /*MSE 4 Years & Unstoppable Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003264', '6300', 'Hat 3');      /*Rose Tinia Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003265', '3200', 'Hat 3');      /*Marine Tinia Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003268', '3700', 'Hat 3');      /*Button-a-holic Sugar Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003269', '5600', 'Hat 3');      /*Button-a-holic Toy Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003271', '6800', 'Hat 3');      /*Pink Heart Transparent Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003272', '3700', 'Hat 3');      /*Bastille Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003276', '5000', 'Hat 3');      /*Blue Heart Transparent Hat*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003277', '3200', 'Hat 3');      /*Grass Spirit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003278', '7400', 'Hat 3');      /*Mermaid Shell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003279', '3200', 'Hat 3');      /*Chain Crusher Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003295', '2700', 'Hat 3');      /*Lazy Chicken Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003352', '3200', 'Hat 3');      /*Tic Toc Red Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003353', '7400', 'Hat 3');      /*Dear Orange Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003354', '5000', 'Hat 3');      /*Fresh Lemon Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003355', '6800', 'Hat 3');      /*Lime Green Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003356', '5600', 'Hat 3');      /*Crystal Blue Sun Cap*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003357', '5600', 'Hat 3');      /*Night Navy Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003358', '4300', 'Hat 3');      /*Sweet Purple Sun Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003362', '7400', 'Hat 3');      /*Rosy Pink Twin Ribbons*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003367', '3700', 'Hat 3');      /*Crown of Flowers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003368', '5000', 'Hat 3');      /*Western Cowboy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003376', '6800', 'Hat 3');      /*Memorial Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003377', '7400', 'Hat 3');      /*Alchemist Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003386', '6300', 'Hat 3');      /*Bat Costume Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003387', '6300', 'Hat 3');      /*Beanie Headphone*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003390', '6300', 'Hat 3');      /*Orchid's Black Wing Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003392', '2700', 'Hat 3');      /*Honeybee Antenna Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003393', '4300', 'Hat 3');      /*Imperial Duke Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003398', '3700', 'Hat 3');      /*Dark Mihile*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003399', '3200', 'Hat 3');      /*Dark Oz*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003400', '3700', 'Hat 3');      /*Dark Irena*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003401', '6300', 'Hat 3');      /*Dark Eckhart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003402', '5600', 'Hat 3');      /*Dark Hawkeye*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003403', '5600', 'Hat 3');      /*Dark Cygnus's Hairband*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003404', '4700', 'Hat 3');      /*Imp Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003416', '3200', 'Hat 3');      /*Christmas Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003416', '7400', 'Hat 3');      /*Christmas Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003417', '5000', 'Hat 3');      /*Dino Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003421', '5600', 'Hat 3');      /*Noblesse Gold Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003422', '4300', 'Hat 3');      /*Garnet Raven Persona*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003459', '3700', 'Hat 3');      /*Lucia Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003460', '4300', 'Hat 3');      /*Milk Chocolate Cone*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003461', '6300', 'Hat 3');      /*Lania's Flower Crown*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003462', '5600', 'Hat 3');      /*Kitty Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003463', '6800', 'Hat 3');      /*Pixiemom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003482', '6300', 'Hat 3');      /*Green Zodiac Dragon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003483', '2700', 'Hat 3');      /*Pink Zodiac Dragon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003484', '4700', 'Hat 3');      /*White Zodiac Dragon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003485', '2700', 'Hat 3');      /*Green Zodiac Dragon Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003486', '3200', 'Hat 3');      /*Yellow Zodiac Dragon Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003487', '5600', 'Hat 3');      /*White Zodiac Dragon Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003489', '3700', 'Hat 3');      /*Gas Mask and Helmet*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003490', '6800', 'Hat 3');      /*Maid Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003492', '7400', 'Hat 3');      /*Crisp Egg Nigiri*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003493', '6800', 'Hat 3');      /*Fresh Salmon Nigiri*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003494', '4300', 'Hat 3');      /*Chewy Octopus Nigiri*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003495', '3700', 'Hat 3');      /*Tangy Fish Egg Nigiri*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003496', '4300', 'Hat 3');      /*Cute Shrimp Nigiri*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003503', '6800', 'Hat 3');      /*Alice's Teacup*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003504', '4300', 'Hat 3');      /*Blue Dragon Horn*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003505', '6800', 'Hat 3');      /*Red Dragon Horn*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003506', '2700', 'Hat 3');      /*Intergalactic Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003509', '7400', 'Hat 3');      /*Sausage Hat*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003510', '3700', 'Hat 4');      /*Alice Rabbit Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003516', '3700', 'Hat 4');      /*Honeybee Antenna Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003517', '3200', 'Hat 4');      /*Ebony Pimpernel Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003518', '4300', 'Hat 4');      /*Small Black Devil Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003519', '4700', 'Hat 4');      /*Sunset-colored Straw Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003520', '3200', 'Hat 4');      /*Wire Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003531', '6800', 'Hat 4');      /*Dainty Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003532', '5000', 'Hat 4');      /*Lucky Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003533', '7400', 'Hat 4');      /*Legendary Hat*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003536', '3200', 'Hat 4');      /*Lucia Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003538', '2700', 'Hat 4');      /*Button-A-Holic Toy Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003539', '6300', 'Hat 4');      /*GM Nori's Wing Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003541', '4300', 'Hat 4');      /*Country Rabbit Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003542', '2700', 'Hat 4');      /*Strawberry Macaroon Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003543', '7400', 'Hat 4');      /*Macaroon Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003544', '7400', 'Hat 4');      /*Strawberry Cupcake Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003545', '7400', 'Hat 4');      /*Melon Cupcake Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003546', '4700', 'Hat 4');      /*Chocolate Cupcake Hairpin*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003547', '4300', 'Hat 4');      /*Parfait Cupcake Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003548', '2700', 'Hat 4');      /*Aerial Mystic Black Silk Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003549', '6300', 'Hat 4');      /*Aerial Mystic Black Silk Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003559', '4700', 'Hat 4');      /*Blue Cat Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003560', '5600', 'Hat 4');      /*Yellow Cat Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003586', '6300', 'Hat 4');      /*Mint Star Marine Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003587', '3700', 'Hat 4');      /*Pink Ribbon Marine Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003588', '5600', 'Hat 4');      /*Pink Teddy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003594', '5600', 'Hat 4');      /*Cool Summer Snorkeling*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003595', '7400', 'Hat 4');      /*Curly Rabbit Poof*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003596', '6800', 'Hat 4');      /*Metal Pink Baseball Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003597', '3700', 'Hat 4');      /*Metal Crown Nuera*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003626', '2700', 'Hat 4');      /*Jett's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003636', '2700', 'Hat 4');      /*Aqua Shell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003639', '4300', 'Hat 4');      /*Cheering Pink*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003640', '2700', 'Hat 4');      /*Cheering Blue*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003641', '7400', 'Hat 4');      /*Cheering Green*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003642', '4700', 'Hat 4');      /*Cheering Gold*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003643', '2700', 'Hat 4');      /*Yin-Yang Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003654', '3700', 'Hat 4');      /*Yukimura's Helm*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003655', '5000', 'Hat 4');      /*Kanetsuku's Helm*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003656', '2700', 'Hat 4');      /*Hideyoshi's Helm*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003657', '6300', 'Hat 4');      /*Shingen's Helm*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003658', '3700', 'Hat 4');      /*Muneshige's Helm*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003666', '6300', 'Hat 4');      /*Blue Arabian Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003667', '5600', 'Hat 4');      /*Red Arabian Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003668', '6800', 'Hat 4');      /*Hyper Lost Baby Chick*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003669', '5000', 'Hat 4');      /*Hyper Honeybee Antenna Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003670', '5600', 'Hat 4');      /*Maple Red Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003671', '7400', 'Hat 4');      /*Maple Blue Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003672', '3700', 'Hat 4');      /*Maple Black Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003673', '7400', 'Hat 4');      /*Maple Green Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003681', '3700', 'Hat 4');      /*Scream Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003682', '5600', 'Hat 4');      /*Zombuddy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003683', '6800', 'Hat 4');      /*Cow Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003684', '5000', 'Hat 4');      /*Tiger Cub Hat*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003685', '7400', 'Hat 4');      /*Angel Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003686', '3700', 'Hat 4');      /*Brown Paperbag Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003687', '5600', 'Hat 4');      /*Hyper Teddy Earmuffs*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003688', '4700', 'Hat 4');      /*Hyper Cat Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003699', '7400', 'Hat 4');      /*Hidden Street Red Husky Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003700', '5600', 'Hat 4');      /*[MS Custom] Red Festive Gumball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003701', '4700', 'Hat 4');      /*[MS Custom] Yellow Rain Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003702', '4300', 'Hat 4');      /*[MS Custom] Sky Blue Rain Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003703', '3200', 'Hat 4');      /*[MS Custom] Patissier Hat*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003704', '5600', 'Hat 4');      /*[MS Custom] Red Red Rain Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003705', '5600', 'Hat 4');      /*[MS Custom] Green Picnic Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003706', '2700', 'Hat 4');      /*[MS Custom] Zombie Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003707', '2700', 'Hat 4');      /*[MS Custom] Black Snowboard Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003708', '6800', 'Hat 4');      /*[MS Custom] The Chinese Undead's Hat (Maroon)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003709', '5000', 'Hat 4');      /*[MS Custom] Werebeast*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003710', '3700', 'Hat 4');      /*[MS Custom] Maple-Stein*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003711', '4700', 'Hat 4');      /*[MS Custom] Doll Face Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003712', '4700', 'Hat 4');      /*[MS Discount] Chain Crusher Cap*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003713', '6800', 'Hat 4');      /*Seal Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003714', '2700', 'Hat 4');      /*Halloween Leopard Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003727', '2700', 'Hat 4');      /*Red Pierre Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003728', '5600', 'Hat 4');      /*Blue Pierre Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003729', '6800', 'Hat 4');      /*Hyper Bunny Earmuffs*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003730', '5000', 'Hat 4');      /*Cat Lolita Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003735', '7400', 'Hat 4');      /*Scarlion Boss Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003736', '3700', 'Hat 4');      /*Reindeer Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003737', '7400', 'Hat 4');      /*Snowman*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003738', '3200', 'Hat 4');      /*Santa Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003739', '3200', 'Hat 4');      /*Decked Out Santa Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003742', '4300', 'Hat 4');      /*Dark Devil Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003743', '5600', 'Hat 4');      /*Slither Style Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003749', '4300', 'Hat 4');      /*Zodiac Snake Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003750', '6300', 'Hat 4');      /*Ribbon Kitty Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003756', '3700', 'Hat 4');      /*Polar Bear Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003759', '4700', 'Hat 4');      /*Blue Point Kitty Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003760', '3700', 'Hat 4');      /*Kitty Headphones*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003761', '5000', 'Hat 4');      /*Featherly Angel Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003763', '2700', 'Hat 4');      /*Black Wing Master's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003775', '4700', 'Hat 4');      /*GM Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003776', '7400', 'Hat 4');      /*Harp Seal Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003777', '4700', 'Hat 4');      /*Goth Cat Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003778', '5000', 'Hat 4');      /*Fluffy Cat Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003779', '7400', 'Hat 4');      /*White Rabbit Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003789', '3700', 'Hat 4');      /*Zombie Hunter Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003790', '7400', 'Hat 4');      /*Visor*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003792', '6800', 'Hat 4');      /*Inkwell Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003802', '5600', 'Hat 4');      /*Green Dinosaur Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003803', '2700', 'Hat 4');      /*Purple Dinosaur Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003804', '6800', 'Hat 4');      /*Ducky Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003807', '3700', 'Hat 4');      /*Heart Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003808', '3200', 'Hat 4');      /*Mystic Black Silk Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003809', '6800', 'Hat 4');      /*Mystic Black Silk Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003816', '3200', 'Hat 4');      /*Dark Mihile*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003817', '6800', 'Hat 4');      /*Dark Oz*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003818', '2700', 'Hat 4');      /*Dark Irena*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003819', '6300', 'Hat 4');      /*Dark Eckhart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003820', '2700', 'Hat 4');      /*Dark Hawkeye*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003825', '3700', 'Hat 4');      /*The Bladed Falcon's Helm*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003826', '6800', 'Hat 4');      /*Samurai Hair-do*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003827', '7400', 'Hat 4');      /*Miko Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003829', '3700', 'Hat 4');      /*Bunny Top Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003830', '6300', 'Hat 4');      /*Blue Love Bonnet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003831', '4700', 'Hat 4');      /*Ramling Hair Pin*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003836', '4300', 'Hat 4');      /*Wild Spike Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003837', '3200', 'Hat 4');      /*Colorstream Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003838', '3200', 'Hat 4');      /*Wacky Olympus Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003839', '3200', 'Hat 4');      /*Goin' Nuclear Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003842', '3700', 'Hat 4');      /*Succubus Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003843', '6300', 'Hat 4');      /*Bizarre Fox Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003844', '3200', 'Hat 4');      /*Nao Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003845', '3200', 'Hat 4');      /*Lorna and Pan Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003846', '3200', 'Hat 4');      /*Danjin Hat*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003847', '4300', 'Hat 4');      /*Slayer Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003848', '5600', 'Hat 4');      /*Crisp Egg Nigiri*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003849', '3700', 'Hat 4');      /*Fresh Salmon Nigiri*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003850', '5000', 'Hat 4');      /*Chewy Octopus Nigiri*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003851', '6800', 'Hat 4');      /*Tangy Fish Egg Nigiri*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003852', '4300', 'Hat 4');      /*Cute Shrimp Nigiri*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003853', '3200', 'Hat 4');      /*Blavy Angel Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003855', '6800', 'Hat 4');      /*Leaf Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003859', '6800', 'Hat 4');      /*Iris Psyche*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003860', '4300', 'Hat 4');      /*Seria Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003861', '5000', 'Hat 4');      /*Funky Mini Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003862', '2700', 'Hat 4');      /*Teddy Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003865', '2700', 'Hat 4');      /*Starlight Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003867', '6800', 'Hat 4');      /*Nice Shot Visor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003873', '6800', 'Hat 4');      /*Water Thief Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003874', '4300', 'Hat 4');      /*Blue Mossy Mom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003875', '6800', 'Hat 4');      /*Jr. Cellion Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003876', '5600', 'Hat 4');      /*Lupin Hat*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003877', '4700', 'Hat 4');      /*Yeti Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003878', '4300', 'Hat 4');      /*Pepe Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003881', '3200', 'Hat 4');      /*Paper Boat Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003882', '4300', 'Hat 4');      /*Giant Bear Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003883', '3200', 'Hat 4');      /*Blue Bow Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003884', '2700', 'Hat 4');      /*Cute Wire Hair Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003889', '3700', 'Hat 4');      /*I'm Controlled!*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003890', '3200', 'Hat 4');      /*GM Sori's Fedora*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003892', '5600', 'Hat 4');      /*Leaf Diamond*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003897', '5600', 'Hat 4');      /*Indian Chief Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003899', '3200', 'Hat 4');      /*Pirate Captain's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003900', '6300', 'Hat 4');      /*Blue Heart Transparent Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003901', '3700', 'Hat 4');      /*Courageous Bunny Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003902', '2700', 'Hat 4');      /*Pretty Teddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003903', '3700', 'Hat 4');      /*Angelic Navy Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003904', '6800', 'Hat 4');      /*Triumphant Ribbon Pig Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003904', '2700', 'Hat 4');      /*Triumphant Ribbon Pig Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003905', '3700', 'Hat 4');      /*Tenacious Ribbon Pig Hat*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003905', '6300', 'Hat 4');      /*Tenacious Ribbon Pig Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003906', '7400', 'Hat 4');      /*Triumphant Zakum Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003906', '5000', 'Hat 4');      /*Triumphant Zakum Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003907', '4700', 'Hat 4');      /*Tenacious Zakum Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003907', '3700', 'Hat 4');      /*Tenacious Zakum Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003909', '2700', 'Hat 4');      /*Pink Soda Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003910', '5000', 'Hat 4');      /*Petite Diablo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003912', '4300', 'Hat 4');      /*Puppy Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003913', '4300', 'Hat 4');      /*Red Bow*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003914', '4300', 'Hat 4');      /*Marine Tinia Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003915', '4700', 'Hat 4');      /*Pancake Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003917', '6300', 'Hat 4');      /*Pink Sunglasses Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003918', '6800', 'Hat 4');      /*Winged Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003919', '2700', 'Hat 4');      /*Plait-Knitted Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003920', '5000', 'Hat 4');      /*Hawaiian Sunhat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003934', '3200', 'Hat 4');      /*Shadow Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003935', '7400', 'Hat 4');      /*Anima Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003936', '2700', 'Hat 4');      /*Azalea Hair Pin*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003937', '3700', 'Hat 4');      /*Romantic Bamboo Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003940', '5000', 'Hat 4');      /*Curly Rabbit Poof*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003941', '7400', 'Hat 4');      /*Pink Cheer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003942', '7400', 'Hat 4');      /*Blue Cheer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003943', '4300', 'Hat 4');      /*Sleepy Turkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003944', '3700', 'Hat 4');      /*Blue Polka Dot Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003945', '5000', 'Hat 4');      /*Superstar Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003948', '5000', 'Hat 4');      /*フューチャーロイドヘッドセンサー*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003949', '4300', 'Hat 4');      /*フューチャーロイドヘッドセンサー*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003950', '4700', 'Hat 4');      /*Plump Bear Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003951', '3200', 'Hat 4');      /*Odette Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003952', '4700', 'Hat 4');      /*Odile Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003953', '7400', 'Hat 4');      /*Rhinne Luster*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003954', '6300', 'Hat 4');      /*Head Cooler*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003955', '6800', 'Hat 4');      /*Romance Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003957', '3700', 'Hat 4');      /*Mint Mochi Ice*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003958', '4700', 'Hat 4');      /*Pink Mochi Ice*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003962', '4700', 'Hat 4');      /*Checkered Bonnet*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003963', '3700', 'Hat 4');      /*PSY Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003964', '6300', 'Hat 4');      /*Star Checkered Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003965', '6300', 'Hat 4');      /*Chicken Hataroo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003966', '3700', 'Hat 4');      /*Camellia Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003967', '2700', 'Hat 4');      /*Chocoram Doll Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003968', '3200', 'Hat 4');      /*Puffram Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003971', '6800', 'Hat 4');      /*Powder Fedora*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003972', '5000', 'Hat 4');      /*Powder Lace Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003975', '6800', 'Hat 4');      /*Princess of Time Veil*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1003998', '6300', 'Hat 4');      /*White Choco Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004000', '3700', 'Hat 4');      /*Dark Devil Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004001', '5000', 'Hat 4');      /*Vampire Phantom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004002', '5000', 'Hat 4');      /*Shadow Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004003', '4300', 'Hat 4');      /*Pink Nero Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004004', '5600', 'Hat 4');      /*Grey Nero Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004014', '2700', 'Hat 4');      /*Grab N' Pull*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004015', '5600', 'Hat 4');      /*Freud's Face(M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004016', '4300', 'Hat 4');      /*Freud's Face(F)*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004017', '4700', 'Hat 4');      /*Aran's Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004018', '3700', 'Hat 4');      /*Brave Aran's Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004024', '4700', 'Hat 4');      /*Cheese Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004026', '4700', 'Hat 4');      /*Black Cat Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004027', '3700', 'Hat 4');      /*Sky Blue Cat Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004028', '2700', 'Hat 4');      /*Orange Cat Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004029', '5000', 'Hat 4');      /*Snow Bear Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004034', '7400', 'Hat 4');      /*Study Break*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004035', '6800', 'Hat 4');      /*Snake Snapback Hat*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004036', '6300', 'Hat 4');      /*Mr. K's Cat Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004038', '6800', 'Hat 4');      /*Ice Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004039', '5000', 'Hat 4');      /*Eunwol Fox Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004040', '6300', 'Hat 4');      /*Red Panda Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004041', '4700', 'Hat 4');      /*Chipmunk Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004042', '3200', 'Hat 4');      /*Deluxe Rabbit Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004043', '4300', 'Hat 4');      /*Puppy Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004044', '4700', 'Hat 4');      /*Bear Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004045', '2700', 'Hat 4');      /*Beast Tamer Animal Ears 6*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004046', '6300', 'Hat 4');      /*Beast Tamer Animal Ears 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004047', '4700', 'Hat 4');      /*Beast Tamer Animal Ears 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004048', '3700', 'Hat 4');      /*Rudi's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004110', '3700', 'Hat 4');      /*Blue Ribbon Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004073', '4300', 'Hat 4');      /*Year of Horse Hat (Peach)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004074', '2700', 'Hat 4');      /*Year of Horse Hat (Blue)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004081', '4300', 'Hat 4');      /*Dawn Bear Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004111', '6300', 'Hat 4');      /*Red Ribbon Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004113', '6300', 'Hat 4');      /*Ghost Bride's Antique Wedding Veil*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004090', '5600', 'Hat 4');      /*Explorer Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004091', '3200', 'Hat 4');      /*Deer Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004092', '4300', 'Hat 4');      /*Cutie Horse Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004093', '4700', 'Hat 4');      /*Yellow Knitted Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004094', '3700', 'Hat 4');      /*White Choco Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004099', '4300', 'Hat 4');      /*Christmas Antlers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004106', '4300', 'Hat 4');      /*Guardian Head Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004108', '5600', 'Hat 4');      /*Fancy Magician Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004109', '5000', 'Hat 4');      /*Transparent Hat*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004117', '3200', 'Hat 5');      /*Candy Candy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004120', '2700', 'Hat 5');      /*Strawberry Fairy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004122', '3700', 'Hat 5');      /*Chef Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004123', '3200', 'Hat 5');      /*Contemporary Chic Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004124', '5000', 'Hat 5');      /*Strawberry Headgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004125', '5600', 'Hat 5');      /*Pineapple Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004126', '3200', 'Hat 5');      /*Rainbow Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004136', '2700', 'Hat 5');      /*Nurse Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004137', '7400', 'Hat 5');      /*Rabbit and Bear Hat*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004139', '5000', 'Hat 5');      /*Pink Panda Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004140', '3200', 'Hat 5');      /*Commander Lotus Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004141', '4700', 'Hat 5');      /*Commander Damien Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004142', '5600', 'Hat 5');      /*Commander Lucid Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004143', '5000', 'Hat 5');      /*Commander Magnus Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004144', '5600', 'Hat 5');      /*Commander Von Leon Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004145', '6800', 'Hat 5');      /*Commander Arkarium Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004146', '2700', 'Hat 5');      /*Commander Orchid Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004147', '2700', 'Hat 5');      /*Commander Will Mask*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004148', '6800', 'Hat 5');      /*Commander Hilla Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004156', '3700', 'Hat 5');      /*Starry Earmuff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004157', '3200', 'Hat 5');      /*Heart Headset*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004158', '3700', 'Hat 5');      /*LED Mouse Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004164', '2700', 'Hat 5');      /*Targa Silk Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004165', '4300', 'Hat 5');      /*Spring Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004166', '7400', 'Hat 5');      /*Black Butterfly Ribbon Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004167', '4700', 'Hat 5');      /*Dinosaur Snapback*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004168', '7400', 'Hat 5');      /*Cat Hat*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004169', '6300', 'Hat 5');      /*Fried Egg Head*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004170', '7400', 'Hat 5');      /*Colorful Marble Parfait*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004171', '2700', 'Hat 5');      /*Dancing Carousel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004175', '5600', 'Hat 5');      /*Angelic Melody*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004176', '4300', 'Hat 5');      /*Rabbit Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004177', '4700', 'Hat 5');      /*Dark Cygnus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004178', '7400', 'Hat 5');      /*Slab*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004179', '5000', 'Hat 5');      /*Red Elf Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004180', '4700', 'Hat 5');      /*Disease Control STAR*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004181', '4300', 'Hat 5');      /*Candy Party Ribbon Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004190', '4700', 'Hat 5');      /*Island Travel Headphones*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004191', '7400', 'Hat 5');      /*粉红天使翅膀帽*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004192', '4300', 'Hat 5');      /*Do-re-mi Headphone*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004193', '2700', 'Hat 5');      /*Sparkling Goggles Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004194', '3200', 'Hat 5');      /*Prim Ribbon Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004195', '5600', 'Hat 5');      /*축구공 머리띠*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004197', '6300', 'Hat 5');      /*Stop It Mr. Shark*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004198', '2700', 'Hat 5');      /*태극 머리띠*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004199', '6300', 'Hat 5');      /*Ayame's Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004200', '4700', 'Hat 5');      /*Sweet Summer Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004201', '7400', 'Hat 5');      /*暗夜精灵战盔*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004202', '4700', 'Hat 5');      /*隐武士战盔*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004203', '2700', 'Hat 5');      /*Kitty Kitty Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004204', '3200', 'Hat 5');      /*Blue Pony Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004205', '4700', 'Hat 5');      /*Red Pony Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004209', '6300', 'Hat 5');      /*Peach Camellia Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004211', '6800', 'Hat 5');      /*Baby Earmuffs*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004212', '6300', 'Hat 5');      /*[[FROZEN CONTENT]] Frozen Dressy Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004213', '5600', 'Hat 5');      /*Hula Feather Decoration*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004239', '3700', 'Hat 5');      /*Peach Fairy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004250', '6800', 'Hat 5');      /*Star Candy Popsicle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004251', '5600', 'Hat 5');      /*Bright Angel's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004252', '3200', 'Hat 5');      /*Dark Devil Horns*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004253', '7400', 'Hat 5');      /*Old School Uniform Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004254', '2700', 'Hat 5');      /*Master-o-Bingo Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004268', '6800', 'Hat 5');      /*Flower of Life*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004269', '4700', 'Hat 5');      /*Apple Stalk Puffy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004275', '5000', 'Hat 5');      /*Lucky Lucky Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004276', '7400', 'Hat 5');      /*Kemdi Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004279', '4300', 'Hat 5');      /*Squirrel Fedora*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004281', '3200', 'Hat 5');      /*竹蜻蜓帽子*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004282', '6300', 'Hat 5');      /*Polka-Dot Red Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004283', '4700', 'Hat 5');      /*Aqua Mustache Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004284', '7400', 'Hat 5');      /*Orange Mustache Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004285', '6300', 'Hat 5');      /*Pink Mustache Cap*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004294', '6300', 'Hat 5');      /*Sweet Persimmon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004295', '7400', 'Hat 5');      /*Singing Chick Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004296', '7400', 'Hat 5');      /*Lovey Chick Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004298', '4300', 'Hat 5');      /*White Puppy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004299', '7400', 'Hat 5');      /*Brown Puppy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004301', '4700', 'Hat 5');      /*Disease Control STAR*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004302', '2700', 'Hat 5');      /*Neville, the Legend*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004303', '6800', 'Hat 5');      /*Slab*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004304', '6300', 'Hat 5');      /*Scarface Mask*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004305', '3700', 'Hat 5');      /*Old Hockey Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004306', '6800', 'Hat 5');      /*Werebeast*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004307', '3200', 'Hat 5');      /*Disease Control STAR*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004308', '4700', 'Hat 5');      /*Neville, the Legend*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004309', '6800', 'Hat 5');      /*Slab*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004310', '5600', 'Hat 5');      /*Scarface Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004311', '3200', 'Hat 5');      /*Old Hockey Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004312', '4700', 'Hat 5');      /*Werebeast*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004313', '5000', 'Hat 5');      /*Commander Lotus Mask*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004314', '5000', 'Hat 5');      /*Commander Damien Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004315', '7400', 'Hat 5');      /*Commander Lucid Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004316', '4300', 'Hat 5');      /*Commander Magnus Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004317', '4300', 'Hat 5');      /*Commander Von Leon Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004318', '6300', 'Hat 5');      /*Commander Arkarium Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004319', '3200', 'Hat 5');      /*Commander Orchid Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004320', '6800', 'Hat 5');      /*Commander Will Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004321', '5000', 'Hat 5');      /*Commander Hilla Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004322', '2700', 'Hat 5');      /*Rose Hat*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004323', '3200', 'Hat 5');      /*Slab*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004324', '2700', 'Hat 5');      /*Gas Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004325', '5600', 'Hat 5');      /*Disease Control STAR*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004326', '4300', 'Hat 5');      /*Neville, the Legend*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004327', '4700', 'Hat 5');      /*Starry Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004328', '3700', 'Hat 5');      /*Pink Baseball Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004329', '2700', 'Hat 5');      /*Blue Baseball Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004332', '7400', 'Hat 5');      /*Brown Puppy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004336', '6300', 'Hat 5');      /*Raging Lotus Wig*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004337', '7400', 'Hat 5');      /*Ill Orchid Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004338', '7400', 'Hat 5');      /*Worn Messy Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004339', '6800', 'Hat 5');      /*Worn Witch Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004340', '6300', 'Hat 5');      /*Worn Skull Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004341', '5000', 'Hat 5');      /*Messy Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004342', '4300', 'Hat 5');      /*Witch Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004343', '4300', 'Hat 5');      /*Skull Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004384', '4700', 'Hat 5');      /*Dinofrog Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004385', '5000', 'Hat 5');      /*Pumpkin Cake Hat*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004386', '4700', 'Hat 5');      /*Reindeer Fawn Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004393', '5000', 'Hat 5');      /*Eren Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004394', '5000', 'Hat 5');      /*Mikasa Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004395', '4300', 'Hat 5');      /*Armin Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004396', '4300', 'Hat 5');      /*Levi Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004397', '3700', 'Hat 5');      /*Cleaning Bandanna*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004398', '5000', 'Hat 5');      /*Sitting Eren*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004399', '3200', 'Hat 5');      /*Sitting Mikasa*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004400', '4700', 'Hat 5');      /*Sitting Armin*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004401', '6300', 'Hat 5');      /*Sitting Levi*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004402', '4300', 'Hat 5');      /*Sitting Colossal Titan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004403', '5000', 'Hat 5');      /*Hip Hop Rabbit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004405', '4300', 'Hat 5');      /*Rawrin' Tiger Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004406', '4700', 'Hat 5');      /*Humanity's Strongest Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004407', '6800', 'Hat 5');      /*Ear Muffs and Pom Pom Beanie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004408', '3700', 'Hat 5');      /*Icy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004411', '3200', 'Hat 5');      /*Whipping Strawberry*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004413', '2700', 'Hat 5');      /*Red Rudolph Horns*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004414', '2700', 'Hat 5');      /*Warm Bao*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004416', '6300', 'Hat 5');      /*Cutie Birk Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004417', '4300', 'Hat 5');      /*Pinnacle Snow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004418', '2700', 'Hat 5');      /*Unleashed Snow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004419', '7400', 'Hat 5');      /*Aether Snow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004428', '3200', 'Hat 5');      /*Blue Bird Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004429', '3700', 'Hat 5');      /*GS25 Tuna Mayo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004430', '4300', 'Hat 5');      /*GS25 Hot Fire Chicken*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004431', '4700', 'Hat 5');      /*GS25 Jeonju Bibimbap*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004432', '6300', 'Hat 5');      /*GS25 Tuna Mayo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004433', '3700', 'Hat 5');      /*GS25 Hot Fire Chicken*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004434', '3700', 'Hat 5');      /*GS25 Jeonju Bibimbap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004438', '3700', 'Hat 5');      /*Fluffy Ram Earmuff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004439', '4300', 'Hat 5');      /*Silver Wolf Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004440', '6800', 'Hat 5');      /*Zodiac Ram Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004441', '3700', 'Hat 5');      /*Friendly Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004442', '3200', 'Hat 5');      /*Loyal Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004443', '2700', 'Hat 5');      /*Snowman Mask*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004445', '5000', 'Hat 5');      /*Aurora Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004446', '4300', 'Hat 5');      /*Loyal Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004447', '6800', 'Hat 5');      /*Friendly Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004448', '3200', 'Hat 5');      /*Happy Mouse Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004450', '5000', 'Hat 5');      /*Cross Wing Hair Pin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004453', '5600', 'Hat 5');      /*Snow Bunny Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004454', '3700', 'Hat 5');      /*Snow Raccoon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004455', '6800', 'Hat 5');      /*Cottontail Rabbit Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004456', '7400', 'Hat 5');      /*Lovely Princess Bonnet*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004458', '3200', 'Hat 5');      /*네네 스노윙 치킨*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004459', '6800', 'Hat 5');      /*네네 쇼킹핫양념치킨*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004460', '2700', 'Hat 5');      /*네네 오리엔탈파닭*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004461', '6300', 'Hat 5');      /*Blue Ram Horn Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004462', '4700', 'Hat 5');      /*Pink Ram Horn Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004463', '5000', 'Hat 5');      /*Star Planet Mascot Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004467', '4700', 'Hat 5');      /*Giant Floppy Heart Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004468', '7400', 'Hat 5');      /*Bubblecone Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004469', '4700', 'Hat 5');      /*Love Message*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004470', '6800', 'Hat 5');      /*Fluffy Trapper Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004471', '5000', 'Hat 5');      /*Crystal Cat Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004472', '4300', 'Hat 5');      /*Devil Wolf Seduction*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004478', '3200', 'Hat 5');      /*BOY Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004479', '2700', 'Hat 5');      /*Hoi Poi Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004480', '3700', 'Hat 5');      /*Naughty Boy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004482', '3200', 'Hat 5');      /*Akarin's Butterfly Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004483', '3200', 'Hat 5');      /*Akatsuki's Hair-Tie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004486', '6800', 'Hat 5');      /*Spring Crown*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004487', '4300', 'Hat 5');      /*Starlight Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004488', '4700', 'Hat 5');      /*Healing Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004489', '6300', 'Hat 5');      /*Skull Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004490', '3700', 'Hat 5');      /*Spike Headphone*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004499', '4700', 'Hat 5');      /*Puffy Blue Carp Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004500', '3200', 'Hat 5');      /*Puffy Red Carp Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004501', '3200', 'Hat 5');      /*The Kindom Crown of King*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004502', '5600', 'Hat 5');      /*Moonbeam Fox Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004503', '3700', 'Hat 5');      /*Cat Hood*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004504', '4300', 'Hat 5');      /*Noble Blossom Casquette*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004505', '4300', 'Hat 5');      /*Pink Blossom Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004506', '5000', 'Hat 5');      /*Cottontail Rabbit Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004508', '6300', 'Hat 5');      /*The Kingdom Crown of Queen*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004510', '3200', 'Hat 5');      /*Bold Slime Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004511', '5000', 'Hat 5');      /*Orange Mushroom Cap Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004512', '3700', 'Hat 5');      /*Happy Pink Bean Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004513', '3200', 'Hat 5');      /*Clingy Pepe Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004515', '6800', 'Hat 5');      /*Candy Party Ribbon Hairpin*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004524', '7400', 'Hat 5');      /*한입 덥썩 돼지바*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004525', '5600', 'Hat 5');      /*Hair Roll*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004526', '6800', 'Hat 5');      /*(Boiling)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004527', '3700', 'Hat 5');      /*Soaring Goggles*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004528', '5000', 'Hat 5');      /*Silver Lotus Wig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004530', '3700', 'Hat 5');      /*Blue Panda Doll Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004532', '5600', 'Hat 5');      /*The Empress is Watching*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004533', '5600', 'Hat 5');      /*Gaming Moonbeam*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004534', '4300', 'Hat 5');      /*Modern Farm Straw Hat*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004535', '6800', 'Hat 5');      /*Schwarzer Beret*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004536', '5600', 'Hat 5');      /*Triumphant Zakum Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004537', '2700', 'Hat 5');      /*Tenacious Zakum Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004538', '2700', 'Hat 5');      /*Triumphant Ribbon Pig Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004539', '2700', 'Hat 5');      /*Tenacious Ribbon Pig Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004540', '6800', 'Hat 5');      /*Evening Orchid Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004541', '5600', 'Hat 5');      /*Tea Party Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004543', '5600', 'Hat 5');      /*Polka-Dot Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004544', '4700', 'Hat 5');      /*Fedora Hat Cat*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004545', '6800', 'Hat 5');      /*Pink Ribbon Sheep Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004546', '6300', 'Hat 5');      /*Gentleman Bunny Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004547', '6300', 'Hat 5');      /*Red Ribbon Panda Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004548', '6800', 'Hat 5');      /*Crown Hat Tiger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004557', '4700', 'Hat 5');      /*Twinkling Star Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004557', '6300', 'Hat 5');      /*Twinkling Star Helmet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004558', '5000', 'Hat 5');      /*Hoya Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004558', '4700', 'Hat 5');      /*Hoya Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004559', '7400', 'Hat 5');      /*Beginner Chef Hat*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004560', '5600', 'Hat 5');      /*Intermediate Chef Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004561', '4300', 'Hat 5');      /*Advanced Chef Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004562', '3700', 'Hat 5');      /*Sous-Chef Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004563', '3200', 'Hat 5');      /*Chef Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004564', '3700', 'Hat 5');      /*Melon Shaved Ice Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004565', '5000', 'Hat 5');      /*Mango Shaved Ice Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004566', '4700', 'Hat 5');      /*Strawberry Shaved Ice Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004568', '7400', 'Hat 5');      /*Bunny Mouse*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004569', '4300', 'Hat 5');      /*Rainbow Flower Pin*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004570', '5600', 'Hat 5');      /*Black Sailor Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004571', '7400', 'Hat 5');      /*Black Sailor Ribbon Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004574', '2700', 'Hat 5');      /*Reboot Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004575', '6300', 'Hat 5');      /*Romantic Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004576', '6800', 'Hat 5');      /*LED Mouse Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004577', '4300', 'Hat 5');      /*Pink Soda Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004578', '2700', 'Hat 5');      /*Royal Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004580', '5600', 'Hat 5');      /*Yeonhwa School Sapphire Ornament*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004581', '3700', 'Hat 5');      /*Dango Dango Hat*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004589', '5000', 'Hat 5');      /*Jay's Sterilized Kitty Eye Patch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004590', '4700', 'Hat 5');      /*Blueberry Jewel Pin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004591', '6300', 'Hat 5');      /*White Time*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004592', '4700', 'Hat 5');      /*Black Time*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004597', '6800', 'Hat 5');      /*White Ursus Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004598', '7400', 'Hat 5');      /*Brown Ursus Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004599', '5600', 'Hat 5');      /*Black Ursus Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004600', '3200', 'Hat 5');      /*British Marine Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004601', '3700', 'Hat 5');      /*Baby Penguin Hat*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004602', '6300', 'Hat 5');      /*Farmer's Treasure*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004603', '7400', 'Hat 5');      /*Star-Spangled Banner Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004609', '7400', 'Hat 5');      /*Head Sakura*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004610', '7400', 'Hat 6');      /*Flower Butterfly*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004612', '2700', 'Hat 6');      /*Eel Bowl Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004613', '3200', 'Hat 6');      /*Pork Bowl Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004614', '6800', 'Hat 6');      /*Salmon Bowl Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004618', '4300', 'Hat 6');      /*Honey Rice Cake Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004619', '5000', 'Hat 6');      /*Bean Rice Cake Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004620', '5600', 'Hat 6');      /*Chestnut Rice Cake Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004843', '4300', 'Hat 6');      /*Pumpkin-Colored Witch Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004633', '5000', 'Hat 6');      /*Ghost Fedora*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004634', '3700', 'Hat 6');      /*Midnight Black Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004635', '2700', 'Hat 6');      /*Festive Gumball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004636', '3200', 'Hat 6');      /*Banana Outing Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004638', '7400', 'Hat 6');      /*Mr. Orlov Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004640', '2700', 'Hat 6');      /*Block Party Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004641', '7400', 'Hat 6');      /*Fairy's Flower Bud*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004642', '4300', 'Hat 6');      /*Shining Light*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004643', '3700', 'Hat 6');      /*Blue Marine Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004842', '3700', 'Hat 6');      /*Jack-o'-lantern Hat*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004841', '6800', 'Hat 6');      /*Ghost Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004840', '7400', 'Hat 6');      /*Floral Veil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004659', '3200', 'Hat 6');      /*Polar Bear Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004660', '3200', 'Hat 6');      /*Fluffy Fox Ears (Gold)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004661', '3700', 'Hat 6');      /*Fluffy Fox Ears (Silver)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004662', '4700', 'Hat 6');      /*Monkey Mochi Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004665', '3700', 'Hat 6');      /*Yarn Bunny Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004671', '2700', 'Hat 6');      /*Modern Farm Straw Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004672', '3700', 'Hat 6');      /*Time Master Hat*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004673', '3700', 'Hat 6');      /*Time Mistress Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004676', '7400', 'Hat 6');      /*Evan Golden Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004677', '6800', 'Hat 6');      /*Evan Golden Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004678', '6800', 'Hat 6');      /*Royal Mercedes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004679', '3200', 'Hat 6');      /*Royal Mercedes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004680', '5000', 'Hat 6');      /*Mystic Phantom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004681', '3200', 'Hat 6');      /*Mystic Phantom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004682', '3200', 'Hat 6');      /*Winter Aran*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004683', '4300', 'Hat 6');      /*Winter Aran*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004684', '6300', 'Hat 6');      /*Split Luminous*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004685', '3200', 'Hat 6');      /*Split Luminous*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004686', '5600', 'Hat 6');      /*Secret Shade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004687', '7400', 'Hat 6');      /*Secret Shade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004688', '3700', 'Hat 6');      /*Slumbering Dragon Snapback*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004689', '2700', 'Hat 6');      /*Rolled Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004690', '6300', 'Hat 6');      /*Facewashing Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004691', '7400', 'Hat 6');      /*Fantastic Blue Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004692', '4700', 'Hat 6');      /*Kid Snowman*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004693', '5000', 'Hat 6');      /*Santa Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004694', '3700', 'Hat 6');      /*Reindeer Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004697', '5000', 'Hat 6');      /*White Time*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004698', '7400', 'Hat 6');      /*Black Time*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004700', '3200', 'Hat 6');      /*Beaky Owl Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004701', '6300', 'Hat 6');      /*Winter Deer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004702', '7400', 'Hat 6');      /*Fairy Knit Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004703', '3700', 'Hat 6');      /*Damien Snapback*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004711', '5600', 'Hat 6');      /*Oz Doll Hat*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004712', '5000', 'Hat 6');      /*Smile Seed Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004713', '5600', 'Hat 6');      /*Kurama Ear Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004714', '7400', 'Hat 6');      /*Black Mage Snapback*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004716', '3200', 'Hat 6');      /*Concert Muse Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004717', '7400', 'Hat 6');      /*Baby Binkie Bonnet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004718', '5600', 'Hat 6');      /*Eckhart Doll Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004720', '3700', 'Hat 6');      /*Umbral Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004721', '6800', 'Hat 6');      /*Flower Dancer's Butterfly Pin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004722', '6800', 'Hat 6');      /*Moon Dancer's Bandana*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004723', '3200', 'Hat 6');      /*Transcendence Stone Snapback*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004724', '3700', 'Hat 6');      /*Bright New Year Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004725', '6800', 'Hat 6');      /*Pink Bean Likes Meat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004726', '6800', 'Hat 6');      /*Moonbeam's Game of Yut*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004727', '5000', 'Hat 6');      /*Shade's Game of Yut*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004728', '5600', 'Hat 6');      /*Lady Moon Bunny's Rice Drop Soup*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004729', '6300', 'Hat 6');      /*Wee Moon Bunny's Rice Drop Soup*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004730', '5000', 'Hat 6');      /*Hungry Moon Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004731', '5000', 'Hat 6');      /*Adorable Gold Nyanya*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004732', '3200', 'Hat 6');      /*Calico Head Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004733', '3200', 'Hat 6');      /*Gaming Moonbeam*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004734', '4300', 'Hat 6');      /*Melon Shaved Ice Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004756', '6800', 'Hat 6');      /*Shark Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004757', '2700', 'Hat 6');      /*Cat in a Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004758', '5600', 'Hat 6');      /*Blue Flame Phoenix Plume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004759', '2700', 'Hat 6');      /*Red Flame Phoenix Plume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004760', '2700', 'Hat 6');      /*Monster Kindergarten Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004761', '3200', 'Hat 6');      /*Mischievous Sweet Pig Hat*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004762', '2700', 'Hat 6');      /*Cunning Sweet Pig Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004787', '7400', 'Hat 6');      /*Chicken Cutie Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004788', '3700', 'Hat 6');      /*Bubble Leaf Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004789', '7400', 'Hat 6');      /*Chicky Suds Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004790', '3200', 'Hat 6');      /*Detective Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004799', '3700', 'Hat 6');      /*Carrot Rabbit Hairpin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004800', '3700', 'Hat 6');      /*Watermelon Headphone Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004801', '2700', 'Hat 6');      /*Banana Headphone Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004802', '5600', 'Hat 6');      /*Strawberry Headphone Hat*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004803', '6800', 'Hat 6');      /*Blaster Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004804', '4300', 'Hat 6');      /*Blaster Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004805', '4300', 'Hat 6');      /*Sky-blue Straw Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004806', '3200', 'Hat 6');      /*Villain's Mask (Hat)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004807', '2700', 'Hat 6');      /*Starfish and Clam*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004825', '4300', 'Hat 6');      /*Time-Traveling Anniversary Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004826', '6800', 'Hat 6');      /*Straw Cat Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004827', '2700', 'Hat 6');      /*Kamaitachi Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004828', '6800', 'Hat 6');      /*Green Beret (F)*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004829', '5600', 'Hat 6');      /*Red Beret (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004830', '7400', 'Hat 6');      /*Moon Bunny Bell Wig (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004831', '4700', 'Hat 6');      /*Moon Bunny Bell Wig (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004832', '3200', 'Hat 6');      /*White Combat Veil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004833', '5000', 'Hat 6');      /*Rudi's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004834', '2700', 'Hat 6');      /*Dark Musician Headphones*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004835', '5600', 'Hat 6');      /*Chained Princess Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1004839', '6300', 'Hat 6');      /*Diamond Veil*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1010000', '4300', 'Face');      /*Long Brown Beard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1010001', '4300', 'Face');      /*Goatee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1010002', '4700', 'Face');      /*Ninja Mask for Men*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1010003', '2700', 'Face');      /*5 O'Clock Shadow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1010004', '5600', 'Face');      /*General's Mustache (1)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1010005', '4300', 'Face');      /*General's Mustache (2)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1010006', '3200', 'Face');      /*Yakuza Scar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1010007', '3200', 'Face');      /*Cold Make-up*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1011000', '3700', 'Face');      /*Ninja Mask for Women*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1011001', '3200', 'Face');      /*SF Ninja Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1011002', '6800', 'Face');      /*Heart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1011003', '6300', 'Face');      /*Freckles*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1011006', '5000', 'Face');      /*Soulful Make-up*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012000', '4700', 'Face');      /*Battle Scar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012001', '5600', 'Face');      /*Bindi*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012002', '3200', 'Face');      /*Leather Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012003', '4300', 'Face');      /*Rouge*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012004', '2700', 'Face');      /*Camo Face Paint*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012005', '3200', 'Face');      /*Bruise*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012006', '3700', 'Face');      /*Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012007', '5000', 'Face');      /*Santa Beard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012008', '6300', 'Face');      /*Censor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012009', '2700', 'Face');      /*Kiss Mark*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012010', '3700', 'Face');      /*Hinomaru*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012021', '5000', 'Face');      /*White Kabuki Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012022', '7400', 'Face');      /*Red Kabuki Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012023', '6300', 'Face');      /*Yellow Kabuki Mask*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012024', '6300', 'Face');      /*Gentleman's Mustache*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012025', '4700', 'Face');      /*War Paint*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012026', '2700', 'Face');      /*Guan Yu Beard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012027', '6800', 'Face');      /*Bandage Strip*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012028', '4700', 'Face');      /*Blush*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012029', '3700', 'Face');      /*Jester Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012030', '5000', 'Face');      /*Eye Scar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012031', '7400', 'Face');      /*Leaf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012032', '4300', 'Face');      /*White Bread*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012033', '6800', 'Face');      /*England Face Painting*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012034', '6300', 'Face');      /*Tri-color Paint (France)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012035', '4700', 'Face');      /*Brazillian Paint (Brazil)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012036', '2700', 'Face');      /*Bundes Paint (Germany)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012037', '5600', 'Face');      /*Armillary Shield Paint (Portugal)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012038', '6300', 'Face');      /*Rising Sun Paint (Japan)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012039', '5000', 'Face');      /*Taegeuk Paint (Korea)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012040', '7400', 'Face');      /*Heart Face Painting*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012041', '6800', 'Face');      /*Star Spangled Paint (USA)*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012042', '4700', 'Face');      /*Aztec Paint (Mexico)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012043', '6800', 'Face');      /*Australia Face Painting*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012044', '6300', 'Face');      /*Mummy Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012047', '2700', 'Face');      /*Fu Manchu*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012048', '7400', 'Face');      /*Dark Jack's Scar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012049', '6800', 'Face');      /*Ogre Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012050', '6300', 'Face');      /*Maple-Stein Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012051', '3200', 'Face');      /*Dark Jester*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012052', '5000', 'Face');      /*Tongue Twister Scroll*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012053', '3700', 'Face');      /*Unmanaged Anger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012054', '7400', 'Face');      /*Purple Rage*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012055', '4300', 'Face');      /*Allergic Reaction*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012056', '4700', 'Face');      /*Doggy Mouth*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012057', '3700', 'Face');      /*Transparent Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012062', '5000', 'Face');      /*Mild Pink Lipstick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012063', '4700', 'Face');      /*Kitty Paint*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012074', '3700', 'Face');      /*Mocking Laughter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012075', '6800', 'Face');      /*Cold Sweat*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012080', '3200', 'Face');      /*Fat Lips*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012081', '5000', 'Face');      /*MV Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012082', '3700', 'Face');      /*Ice Cold Red*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012083', '5600', 'Face');      /*Dollish Pink*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012085', '6800', 'Face');      /*Cherry Bubblegum*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012090', '7400', 'Face');      /*Facial Powder*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012096', '6300', 'Face');      /*Apple Bubble Gum*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012097', '4700', 'Face');      /*Purple Noisemaker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012099', '7400', 'Face');      /*Facial Powder(blue)*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012100', '5600', 'Face');      /*Facial Powder(red)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012104', '2700', 'Face');      /*Transparent Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012105', '7400', 'Face');      /*Super Sucker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012112', '4700', 'Face');      /*Bauhinia Paint (Hong Kong)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012113', '5600', 'Face');      /*ROC Paint (Taiwan)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012114', '6800', 'Face');      /*5-Starred Red Paint (China)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012121', '2700', 'Face');      /*Coat of Arms Paint (Spain)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012122', '5000', 'Face');      /*Gold Nordic Paint (Sweden)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012123', '2700', 'Face');      /*Holland Paint (Netherlands)*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012124', '2700', 'Face');      /*Union Paint (UK)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012125', '6300', 'Face');      /*Chakra Paint (Thailand)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012126', '5600', 'Face');      /*Yellow Star Paint (Vietnam)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012127', '6800', 'Face');      /*Crescent Paint (Singapore)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012128', '5600', 'Face');      /*Jalur Gemilang Paint (Malaysia)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012129', '2700', 'Face');      /*Maple Leaf Paint  (Canada)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012131', '3200', 'Face');      /*Smiling Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012572', '6300', 'Face');      /*Little Kitten Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012134', '7400', 'Face');      /*Tear Drop Face Tattoo*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012137', '4300', 'Face');      /*Star Face Painting*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012147', '6800', 'Face');      /*Immortal Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012159', '3200', 'Face');      /*Foxy Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012562', '6300', 'Face');      /*Heartbeam Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012165', '2700', 'Face');      /*Clown Nose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012166', '5000', 'Face');      /*Villain Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012176', '4700', 'Face');      /*Orange Blush*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012179', '5000', 'Face');      /*Reindeer Red Nose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012180', '6300', 'Face');      /*Chocolate Heart*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012192', '5000', 'Face');      /*Shadow Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012208', '6300', 'Face');      /*Lovely Smile*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012253', '6800', 'Face');      /*Heart Pounding Lip Gloss*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012275', '5000', 'Face');      /*6th Anniversary Party Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012289', '5600', 'Face');      /*Transparent Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012298', '6300', 'Face');      /*Hand Mark*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012315', '4300', 'Face');      /*Adhesive Bandage*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012366', '5600', 'Face');      /*Zombie Hunter Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012374', '4300', 'Face');      /*Heartbreaker Lips*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012379', '3700', 'Face');      /*Flushed Cheeks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012384', '5000', 'Face');      /*Playful Band*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012388', '3700', 'Face');      /*Clown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012390', '2700', 'Face');      /*Peruvian Flag Face Paint*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012412', '6800', 'Face');      /*Bloody Tears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012413', '4300', 'Face');      /*Naked Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012415', '6300', 'Face');      /*Blingin' Red Lipstick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012427', '3700', 'Face');      /*Surprised Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012428', '3700', 'Face');      /*Thick Eyebrows Face Accessory*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012429', '5000', 'Face');      /*Round Eyes-And-Mouth Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012430', '7400', 'Face');      /*Bear Nose Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012431', '5600', 'Face');      /*Straight Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012432', '2700', 'Face');      /*Cat-Mouth Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012433', '5600', 'Face');      /*Animal Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012434', '4300', 'Face');      /*Mustache Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012435', '7400', 'Face');      /*Gross Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012436', '4300', 'Face');      /*Enlightened Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012437', '4700', 'Face');      /*Palm Print Mask*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012450', '2700', 'Face');      /*Choco Candy Cookie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012462', '4700', 'Face');      /*Ghost Bride's Shining Dark Eyes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012468', '2700', 'Face');      /*Yummy Candy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012472', '2700', 'Face');      /*레인보우 페인팅*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012473', '4700', 'Face');      /*So Angry!!! Face*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012474', '4300', 'Face');      /*Clobber*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012475', '5600', 'Face');      /*태극 페인팅*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012479', '2700', 'Face');      /*Ruddy Kitten Nose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012482', '4700', 'Face');      /*Quack Quack*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012485', '5600', 'Face');      /*Sweet Persimmon Blush*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012486', '4300', 'Face');      /*Hothead*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012487', '5000', 'Face');      /*Oozer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012488', '6300', 'Face');      /*Clobber*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012489', '5600', 'Face');      /*LaLaLa Megaphone*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012494', '6300', 'Face');      /*Worn Skull Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012495', '4700', 'Face');      /*Skull Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012501', '6300', 'Face');      /*No Biting!*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012502', '6300', 'Face');      /*Frosty Frozen Face*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012569', '4700', 'Face');      /*Baby Binkie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012567', '3200', 'Face');      /*Touched Tears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012568', '7400', 'Face');      /*Shocked Eyes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012509', '4300', 'Face');      /*Hange's Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012510', '7400', 'Face');      /*Sasha's Delicious Bread*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012511', '4300', 'Face');      /*Cleaning Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012514', '3700', 'Face');      /*Heart-Shaped Chocolate*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012515', '6300', 'Face');      /*Strawberry Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012517', '2700', 'Face');      /*Vellum Mask*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012518', '2700', 'Face');      /*Von Bon Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012525', '7400', 'Face');      /*Smile! It's the Sweet Maple Festival!*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012526', '3200', 'Face');      /*So Delish Ice Cream*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012527', '3700', 'Face');      /*Blushing Yeti*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012528', '7400', 'Face');      /*Boss Lotus Eyes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012533', '6300', 'Face');      /*Spring Cloud Piece*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012534', '5000', 'Face');      /*Rainbow Face Paint*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012544', '3700', 'Face');      /*Culnesis*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012551', '4700', 'Face');      /*The Mighty Face*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012552', '5600', 'Face');      /*Pink Bean Sadface*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012555', '5600', 'Face');      /*Vampire Eyes (Sapphire)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012556', '6800', 'Face');      /*Vampire Eyes (Ruby)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012557', '3700', 'Face');      /*Nom Nom Oz*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1020000', '5000', 'Eye');      /*Aqua Toy Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1021000', '5600', 'Eye');      /*Pink Toy Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022000', '6300', 'Eye');      /*Orange Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022001', '2700', 'Eye');      /*Blue Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022002', '3700', 'Eye');      /*Yellow Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022003', '7400', 'Eye');      /*Green Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022004', '3700', 'Eye');      /*Black Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022005', '6300', 'Eye');      /*Red Hard-Rimmed Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022006', '7400', 'Eye');      /*Blue Hard-Rimmed Glasses*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022007', '4300', 'Eye');      /*Green Hard-Rimmed Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022008', '4700', 'Eye');      /*Orange Hard-Rimmed Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022009', '6800', 'Eye');      /*Dark Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022010', '3700', 'Eye');      /*Blue & Red Eye Guard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022011', '3700', 'Eye');      /*Red Eye Guard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022012', '3200', 'Eye');      /*Blue Eye Guard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022013', '5000', 'Eye');      /*Black Eye Guard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022014', '5000', 'Eye');      /*Brown Aviator Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022015', '7400', 'Eye');      /*Black Aviator Shades*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022016', '3700', 'Eye');      /*Blue Aviator Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022017', '4300', 'Eye');      /*Purple Aviator Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022018', '6800', 'Eye');      /*Classic Masquerade Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022019', '4300', 'Eye');      /*Old-School Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022020', '3700', 'Eye');      /*Metal Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022021', '3200', 'Eye');      /*Red Head-Spinning Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022022', '5600', 'Eye');      /*Blue Head-Spinning Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022023', '3200', 'Eye');      /*Crested Eye Patch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022024', '4300', 'Eye');      /*Skull Patch*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022025', '5000', 'Eye');      /*Red Hearted Eye Patch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022026', '3700', 'Eye');      /*Purple Starred Eye Patch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022027', '5600', 'Eye');      /*Medical Eye Patch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022028', '6300', 'Eye');      /*Spinning Groucho*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022029', '2700', 'Eye');      /*Spinning Piglet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022030', '6300', 'Eye');      /*Hot Teacher Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022031', '7400', 'Eye');      /*White Toy Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022032', '6800', 'Eye');      /*Yellow Toy Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022033', '4700', 'Eye');      /*Politician Glasses*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022034', '4700', 'Eye');      /*Bizarre Monocle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022035', '3700', 'Eye');      /*Orange Sports Goggle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022036', '5600', 'Eye');      /*Green Sports Goggle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022037', '6800', 'Eye');      /*Frameless Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022038', '7400', 'Eye');      /*Purple Round Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022039', '6800', 'Eye');      /*Orange Round Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022040', '7400', 'Eye');      /*Lead Monocle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022041', '5600', 'Eye');      /*Cyclist Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022042', '5600', 'Eye');      /*Scouter*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022043', '3200', 'Eye');      /*Head Bandage*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022044', '4300', 'Eye');      /*Nerdy Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022045', '3700', 'Eye');      /*Red Bushido Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022046', '6300', 'Eye');      /*Butterfly Ball Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022047', '4700', 'Eye');      /*Owl Ball Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022048', '6300', 'Eye');      /*Transparent Eye Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022049', '3700', 'Eye');      /*Green-Rimmed Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022050', '5600', 'Eye');      /*Vintage Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022051', '4700', 'Eye');      /*Red Half-Rim Glasses*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022052', '7400', 'Eye');      /*Future Vision Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022053', '5000', 'Eye');      /*Futuristic Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022054', '7400', 'Eye');      /*Round Shield Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022055', '2700', 'Eye');      /*Pink Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022056', '3200', 'Eye');      /*Pink Aviator Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022057', '3700', 'Eye');      /*Pop-Eye*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022059', '2700', 'Eye');      /*Black Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022061', '6300', 'Eye');      /*Redbeard's Pirate Eye Patch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022062', '7400', 'Eye');      /*Black Skull Eye Patch*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022063', '3200', 'Eye');      /*Flat Mini Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022064', '3200', 'Eye');      /*Big Red Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022065', '5600', 'Eye');      /*Alphabet Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022066', '4300', 'Eye');      /*Star Spectacles*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022068', '6300', 'Eye');      /*White Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022069', '5600', 'Eye');      /*Orange Shutter Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022070', '4300', 'Eye');      /*Green Shutter Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022071', '3200', 'Eye');      /*Red Shutter Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022072', '3700', 'Eye');      /*Yellow Shutter Shades*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022074', '4300', 'Eye');      /*Gaga Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022075', '6300', 'Eye');      /*Twinkling Eyes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022079', '4300', 'Eye');      /*Clear Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022081', '3700', 'Eye');      /*Cracked Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022083', '4700', 'Eye');      /*Hitman Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022084', '5600', 'Eye');      /*Eye Mask (Red)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022085', '3200', 'Eye');      /*Pink Eye Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022086', '6300', 'Eye');      /*Blue Eye Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022087', '6800', 'Eye');      /*Green Eye Mask*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022090', '6800', 'Eye');      /*Gaga Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022095', '5600', 'Eye');      /*I Like Money*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022102', '4700', 'Eye');      /*LED Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022104', '3700', 'Eye');      /*3D Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022108', '6800', 'Eye');      /*Yellow Two-Toned Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022109', '3200', 'Eye');      /*Pink Two-Toned Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022110', '3200', 'Eye');      /*Big White Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022121', '6800', 'Eye');      /*X-Ray Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022122', '6300', 'Eye');      /*6th B-Day Party Glasses*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022142', '4700', 'Eye');      /*Yellow Shutter Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022158', '3200', 'Eye');      /*[MS Discount] Black Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022173', '5000', 'Eye');      /*Silky Black Eye Patch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022174', '6800', 'Eye');      /*3D Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022176', '6300', 'Eye');      /*Cyclops Bandana*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022177', '6800', 'Eye');      /*Star Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022183', '2700', 'Eye');      /*Blazing Eyes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022184', '6300', 'Eye');      /*Frozen Eye*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022187', '4300', 'Eye');      /*Broken Up Today*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022188', '5000', 'Eye');      /*Blank Eye Patch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022194', '3200', 'Eye');      /*Blaze Black Eye*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022196', '6800', 'Eye');      /*Money Lover*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022201', '5000', 'Eye');      /*フューチャーロイド VR バイザー*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022207', '4300', 'Eye');      /*PSY Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022223', '2700', 'Eye');      /*Romantic LED Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022227', '6300', 'Eye');      /*Aviator Shades*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022229', '2700', 'Eye');      /*VIP Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022230', '3700', 'Eye');      /*Bunny Glasses*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022243', '4700', 'Eye');      /*Donut Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022244', '6300', 'Eye');      /*Damien's Eyepatch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022247', '2700', 'Eye');      /*Black Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022248', '3700', 'Eye');      /*Pineapple Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022249', '3700', 'Eye');      /*스마트안경*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022250', '2700', 'Eye');      /*투시안경*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022257', '4300', 'Eye');      /*Scouter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022258', '2700', 'Eye');      /*Bat Wing Monocle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022259', '6800', 'Eye');      /*Bandage Blindfold*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022262', '5600', 'Eye');      /*Scouter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022263', '2700', 'Eye');      /* Sleepy Eye Patch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022266', '4700', 'Eye');      /*Strange Uncle Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022269', '4300', 'Eye');      /*Chained Princess Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022270', '5000', 'Eye');      /*Oversized Sunglasses*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032024', '5600', 'Earrings');      /*Transparent Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032029', '5600', 'Earrings');      /*Silver Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032034', '4700', 'Earrings');      /*Coke Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032036', '5600', 'Earrings');      /*Beaded Cross Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032038', '6800', 'Earrings');      /*Snow Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032051', '2700', 'Earrings');      /*Diamond Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032052', '5000', 'Earrings');      /*Slime Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032053', '2700', 'Earrings');      /*Clover Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032054', '4300', 'Earrings');      /*Rainbow Earrings*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032063', '6800', 'Earrings');      /*Wireless Headset*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032071', '3200', 'Earrings');      /*Altair Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032072', '5000', 'Earrings');      /*Shiny Altair Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032073', '4700', 'Earrings');      /*Wind Bell Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032074', '4700', 'Earrings');      /*Heart Rainbow Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032138', '4300', 'Earrings');      /*Dragon Spirit Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032145', '5000', 'Earrings');      /*Crab Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032175', '2700', 'Earrings');      /*Faraway Earring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032192', '6800', 'Earrings');      /*Broken Up Today*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032204', '2700', 'Earrings');      /*フューチャーロイドサイバーイヤリング*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032228', '6800', 'Earrings');      /*Halloweenroid Sensor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032233', '7400', 'Earrings');      /*Warm-hearted Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032234', '4700', 'Earrings');      /*Cold-hearted Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032255', '6800', 'Earrings');      /*White Earphones*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032260', '2700', 'Earrings');      /*Golden Bell Drops*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032262', '4300', 'Earrings');      /*Umbral Earrings*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050004', '5600', 'Overall');      /*Blue Officer Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050012', '3700', 'Overall');      /*Grey Skull Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050013', '2700', 'Overall');      /*Red Skull Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050014', '4700', 'Overall');      /*Green Skull Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050015', '3200', 'Overall');      /*Blue Skull Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050016', '2700', 'Overall');      /*Orange Skull Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050017', '7400', 'Overall');      /*Yellow Tights*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050019', '3200', 'Overall');      /*Santa Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050020', '6300', 'Overall');      /*Paper Box*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050032', '4300', 'Overall');      /*Silver Officer Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050033', '5000', 'Overall');      /*Black Officer Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050034', '3200', 'Overall');      /*Red Officer Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050040', '4700', 'Overall');      /*Red Swimming Trunk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050041', '2700', 'Overall');      /*Blue Swimming Trunk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050042', '5000', 'Overall');      /*Fine Brown Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050043', '3700', 'Overall');      /*Fine Black Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050044', '7400', 'Overall');      /*Fine Blue Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050050', '3200', 'Overall');      /*Dark Suit*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050057', '7400', 'Overall');      /*Ghost Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050065', '4700', 'Overall');      /*Blue Celebration Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050066', '3200', 'Overall');      /*Green Celebration Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050071', '3200', 'Overall');      /*Men's Ninja Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050079', '5000', 'Overall');      /*Black Coat of Death*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050084', '5600', 'Overall');      /*Red Mesoranger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050085', '5000', 'Overall');      /*Blue Mesoranger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050086', '2700', 'Overall');      /*Green Mesoranger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050087', '5000', 'Overall');      /*Black Mesoranger*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050101', '2700', 'Overall');      /*Western Cowboy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050109', '3200', 'Overall');      /*Green Picnicwear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050110', '4700', 'Overall');      /*Sky Blue Picnicwear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050111', '6800', 'Overall');      /*Boxing Trunks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050112', '3700', 'Overall');      /*Wedding Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050113', '3700', 'Overall');      /*Wedding Tuxedo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050114', '7400', 'Overall');      /*Poseidon Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050115', '5600', 'Overall');      /*Sea Hermit Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050116', '4300', 'Overall');      /*Race Ace Suit*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050117', '3700', 'Overall');      /*Tiny Blue Swimshorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050118', '5000', 'Overall');      /*Tiny Black Swimshorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050119', '2700', 'Overall');      /*Santa Boy Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050120', '4300', 'Overall');      /*Horoscope Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050121', '6800', 'Overall');      /*Oriental Bridegroom Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050122', '6800', 'Overall');      /*Unseemly Wedding Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050123', '7400', 'Overall');      /*Royal Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050124', '6800', 'Overall');      /*Lunar Celebration Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050125', '6300', 'Overall');      /*Brown Casual Look*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050126', '4300', 'Overall');      /*Imperial Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050128', '5600', 'Overall');      /*Go! Korea!*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050129', '4700', 'Overall');      /*Korean Martial Art Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050135', '5000', 'Overall');      /*Beau Tuxedo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050136', '4300', 'Overall');      /*Black Male Fur Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050137', '6800', 'Overall');      /*White Male Fur Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050138', '6300', 'Overall');      /*School Uniform with Hoody Jumper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050139', '2700', 'Overall');      /*Boys Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050140', '5600', 'Overall');      /*Thai Formal Dress*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050141', '4300', 'Overall');      /*Blue Kitty Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050142', '5000', 'Overall');      /*Hooded Korean Traditional Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050143', '5600', 'Overall');      /*Retro School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050145', '6300', 'Overall');      /*Violet Tunic*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050146', '4300', 'Overall');      /*Buddy Overall Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050147', '2700', 'Overall');      /*Princess Korean Traditional Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050148', '6300', 'Overall');      /*Shin-Hwa High Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050152', '2700', 'Overall');      /*Sailor Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050153', '2700', 'Overall');      /*Exotic Festival Outfit*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050154', '4300', 'Overall');      /*Seraphim Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050156', '6300', 'Overall');      /*Blue Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050157', '7400', 'Overall');      /*Cutie Boy Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050158', '5000', 'Overall');      /*Brown Casual Look*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050159', '3200', 'Overall');      /*Black Male Fur Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050160', '6800', 'Overall');      /*Nya-ong's Long Hood T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050161', '2700', 'Overall');      /*Bunny Boy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050168', '6300', 'Overall');      /*Evan Elegant Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050170', '3700', 'Overall');      /*Napoleon Uniform*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050171', '6800', 'Overall');      /*Evan Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050177', '5000', 'Overall');      /*Maple Boy School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050178', '3200', 'Overall');      /*Napoleon Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050179', '6300', 'Overall');      /*Holiday Party Gear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050186', '5000', 'Overall');      /*Rookie Maple Boy School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050187', '5000', 'Overall');      /*Blue Snow Flower Wear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050188', '4300', 'Overall');      /*Flower Heir Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050190', '2700', 'Overall');      /*Military Pop Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050193', '6300', 'Overall');      /*Red Overall Pants*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050208', '5000', 'Overall');      /*Schoolboy Formals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050209', '3200', 'Overall');      /*Moonlight Serenade Get-Up*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050210', '3700', 'Overall');      /*Light Cotton Candy Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050215', '3200', 'Overall');      /*Maple Doctor's Scrubs (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050220', '7400', 'Overall');      /*Dark Force Mail (M) */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050221', '4300', 'Overall');      /*Elven Spirit Coat (M) */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050226', '2700', 'Overall');      /*Imperial Garnet Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050227', '6300', 'Overall');      /*Mint Snow Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050228', '6800', 'Overall');      /*Elven Spirit Coat*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050229', '7400', 'Overall');      /*Gentle Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050232', '2700', 'Overall');      /*Chamomile Tea Time*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050234', '2700', 'Overall');      /*Magic Star Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050235', '3200', 'Overall');      /*Prince Charming*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050241', '3700', 'Overall');      /*Jett's Outfit(M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050242', '2700', 'Overall');      /*Opening Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050246', '4700', 'Overall');      /*Saint Luminous*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050247', '6800', 'Overall');      /*Evergreen Magistrate Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050248', '4700', 'Overall');      /*Halloween Leopard Costume*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050255', '6800', 'Overall');      /*Dark Force Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050256', '4700', 'Overall');      /*Alps Boy Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050283', '6300', 'Overall');      /*Magic Star Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050284', '7400', 'Overall');      /*Golden Bell Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050285', '6800', 'Overall');      /*Thermidor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050291', '7400', 'Overall');      /*Tinky Baseball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050292', '6300', 'Overall');      /*Blue Swimming Trunks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050293', '7400', 'Overall');      /*Beach Bum Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050296', '6300', 'Overall');      /*Superstar Suit*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050297', '3700', 'Overall');      /*Rising Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050298', '2700', 'Overall');      /*Alpha Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050299', '7400', 'Overall');      /*Baby Doll Puka*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050300', '6300', 'Overall');      /*Fresh Ice*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050301', '7400', 'Overall');      /*Blue Checkered Vacation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050302', '3700', 'Overall');      /*Powder Butler's Digs (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050303', '5000', 'Overall');      /*Ribbon Boy School Look*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050304', '5000', 'Overall');      /*Splash Choco Boy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050305', '3700', 'Overall');      /*Bloody Leo*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050310', '3700', 'Overall');      /*Shiny Shopper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050311', '6300', 'Overall');      /*Moonlight Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050312', '5600', 'Overall');      /*Grand Pony*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050314', '3700', 'Overall');      /*Plop! Chocolate Boy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050315', '5000', 'Overall');      /*Blue Shiny Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050316', '6800', 'Overall');      /*Balloon Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050318', '5600', 'Overall');      /*White Proposal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050319', '2700', 'Overall');      /*Sky Blue Picnic*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050321', '2700', 'Overall');      /*[[FROZEN CONTENT]] Kristoff Coat*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050322', '3200', 'Overall');      /*Party Prince*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050335', '4700', 'Overall');      /*Melody Boy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050336', '7400', 'Overall');      /*축구선수 유니폼*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050337', '7400', 'Overall');      /*Hawaiian Couple*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050338', '7400', 'Overall');      /*Maple Leaf High Swimsuit (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050339', '7400', 'Overall');      /*Glowy Light*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050340', '5600', 'Overall');      /*Gentle Ice Boy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050341', '5000', 'Overall');      /*In-forest Camping Look*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050343', '6800', 'Overall');      /*Gentle Dylan*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050346', '5600', 'Overall');      /*Cheer Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050351', '5000', 'Overall');      /*Mad Doctor Gown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050353', '5600', 'Overall');      /*Sweet Patissier*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050354', '7400', 'Overall');      /*Blue Snow Flower Wear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050355', '6300', 'Overall');      /*Santa Boy Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050356', '6300', 'Overall');      /*Gothic Boys Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050359', '3200', 'Overall');      /*Cool Snow Flower*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050360', '3200', 'Overall');      /*Ryan D Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050361', '6300', 'Overall');      /*Mr. Love Messenger Outfit*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050362', '5000', 'Overall');      /*Little Trainer Outfit (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050364', '2700', 'Overall');      /*Leafy Love Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050365', '6300', 'Overall');      /*Starlight Boy outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050366', '6800', 'Overall');      /*The Kingdom Suit of King*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051132', '7400', 'Overall');      /*White Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050368', '5600', 'Overall');      /*Romantic Sky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050370', '5000', 'Overall');      /*Mint Kitty Tea Party*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050371', '6300', 'Overall');      /*Blue Crystal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050372', '3200', 'Overall');      /*Gentle Mickey*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050373', '4700', 'Overall');      /*Tim Gentleman Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050375', '3200', 'Overall');      /*Baby Doll Puka*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050376', '2700', 'Overall');      /*Party Prince*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050377', '3200', 'Overall');      /*Tinky Baseball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050378', '3200', 'Overall');      /*Yeonhwa School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050380', '4300', 'Overall');      /*Kinesis Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050381', '4300', 'Overall');      /*Kinesis Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050382', '6300', 'Overall');      /*Millionaire Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050383', '7400', 'Overall');      /*Teddy Suspenders*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050384', '4700', 'Overall');      /*Penguin Doll Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050385', '3700', 'Overall');      /*Time Getup*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050386', '5600', 'Overall');      /*Sparkling Bluebird (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050387', '2700', 'Overall');      /*British Marine Outfit (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050388', '5000', 'Overall');      /*Ursus Gentleman's Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050389', '5000', 'Overall');      /*Cutie Farmer Apron*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050390', '6800', 'Overall');      /*Polka-Dot Bell Bottoms*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051131', '4300', 'Overall');      /*Santa Girl Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050392', '6800', 'Overall');      /*Bloody Guardian*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050393', '3200', 'Overall');      /*Banana Outing Clothes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050394', '4700', 'Overall');      /*Bubbly Traveler*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050395', '3200', 'Overall');      /*Blue Marine Uniform (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051125', '4700', 'Overall');      /*Black Cat Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051126', '4300', 'Overall');      /*Red Chinese Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051127', '5000', 'Overall');      /*Maid Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051128', '5000', 'Overall');      /*Horoscope Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050401', '4300', 'Overall');      /*Time Master*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050402', '7400', 'Overall');      /*Evan Dragon Suit*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050403', '4700', 'Overall');      /*Evan Dragon Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050404', '4300', 'Overall');      /*Royal Mercedes Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050405', '6800', 'Overall');      /*Royal Mercedes Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050406', '6300', 'Overall');      /*Mystic Phantom Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050407', '3200', 'Overall');      /*Mystic Phantom Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050408', '5600', 'Overall');      /*Winter Aran Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050409', '5000', 'Overall');      /*Winter Aran Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050410', '5000', 'Overall');      /*Chiaroscuro Luminous Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050411', '7400', 'Overall');      /*Chiaroscuro Luminous Suit*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050412', '3700', 'Overall');      /*Secret Shade Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050413', '5600', 'Overall');      /*Secret Shade Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050414', '4300', 'Overall');      /*Cozy Bathrobe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050415', '3700', 'Overall');      /*Santa Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050416', '3200', 'Overall');      /*Time Tailcoat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050417', '4700', 'Overall');      /*Ice Deer Parka*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051130', '2700', 'Overall');      /*Unseemly Wedding Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051129', '3200', 'Overall');      /*Oriental Princess Gown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050422', '4700', 'Overall');      /*Concert Muse (Tenor)*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051002', '2700', 'Overall');      /*Cat Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051018', '5000', 'Overall');      /*Purple Skull Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051019', '4700', 'Overall');      /*Orange Skull Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051020', '6300', 'Overall');      /*Green Skull Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051021', '6800', 'Overall');      /*Blue Skull Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051022', '3200', 'Overall');      /*Grey Skull Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051028', '5000', 'Overall');      /*White Swimming Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051029', '2700', 'Overall');      /*Red Swimming Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051035', '5000', 'Overall');      /*Fine Red Hanbok*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051036', '5600', 'Overall');      /*Fine Blue Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051040', '7400', 'Overall');      /*Dark Enamel Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051048', '4300', 'Overall');      /*Witch Clothes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051049', '7400', 'Overall');      /*Mrs. Claus Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051050', '5000', 'Overall');      /*Blue Celeberation Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051051', '5600', 'Overall');      /*Pink Celebration Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051059', '3700', 'Overall');      /*Pink Nurse Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051060', '5600', 'Overall');      /*White Nurse Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051061', '6300', 'Overall');      /*Women's Ninja Uniform*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051070', '3700', 'Overall');      /*Bunny Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051071', '6300', 'Overall');      /*Pink Kimono*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051072', '2700', 'Overall');      /*White Kimono*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051073', '7400', 'Overall');      /*Red Kimono*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051074', '5600', 'Overall');      /*Yellow Kimono*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051075', '3200', 'Overall');      /*Blue Swimming Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051076', '7400', 'Overall');      /*Ghost Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051081', '4700', 'Overall');      /*Pink Kimono*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051086', '7400', 'Overall');      /*Ragged Hanbok*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051087', '7400', 'Overall');      /*Pink Mesoranger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051088', '5600', 'Overall');      /*Yellow Mesoranger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051089', '4700', 'Overall');      /*Black Mesoranger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051099', '7400', 'Overall');      /*Prep Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051100', '7400', 'Overall');      /*Western Cowgirl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051108', '5600', 'Overall');      /*Pink Picnic Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051109', '6300', 'Overall');      /*Yellow Picnic Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051110', '6800', 'Overall');      /*Purple Frill One Piece*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051111', '5000', 'Overall');      /*Blue Frill One Piece*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051112', '4700', 'Overall');      /*Boxing Gear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051113', '2700', 'Overall');      /*Wedding Tuxedo (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051114', '4300', 'Overall');      /*Wedding Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051115', '3200', 'Overall');      /*Sea Queen Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051116', '3700', 'Overall');      /*Race Queen Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051117', '7400', 'Overall');      /*Diao Chan Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051118', '6300', 'Overall');      /*Pink Strapless Bikini*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051119', '6800', 'Overall');      /*Blue Strapless Bikini*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051120', '5600', 'Overall');      /*Flight Attendant Uniform*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051121', '6800', 'Overall');      /*Tropical Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051122', '2700', 'Overall');      /*White Cat Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051123', '3700', 'Overall');      /*Violet Strapless Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051124', '3200', 'Overall');      /*Purple Ring One Piece*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051133', '3700', 'Overall 2');      /*Rough Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051134', '2700', 'Overall 2');      /*Leopard Print Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051135', '3200', 'Overall 2');      /*Ruffled Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051136', '4700', 'Overall 2');      /*Royal Palace Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051137', '4700', 'Overall 2');      /*Rabbit Fur Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051138', '4700', 'Overall 2');      /*Lunar Celebration Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051139', '3200', 'Overall 2');      /*White Ribboned Sailor Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051141', '6300', 'Overall 2');      /*Female Shaman Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051142', '7400', 'Overall 2');      /*Vibrant Yellow Dress*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051143', '5600', 'Overall 2');      /*Race Queen Tank Top & Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051144', '6300', 'Overall 2');      /*Elegant Blue One Piece*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051145', '4700', 'Overall 2');      /*Royal Maid Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051146', '6800', 'Overall 2');      /*Royal Nurse Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051147', '6800', 'Overall 2');      /*Street Cred Ensemble*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051148', '3700', 'Overall 2');      /*Navy Blue Au Luxe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051149', '6300', 'Overall 2');      /*Princess Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051154', '3200', 'Overall 2');      /*Princess Isis*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051155', '7400', 'Overall 2');      /*Queen Mary*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051156', '3200', 'Overall 2');      /*Black Female Fur Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051157', '7400', 'Overall 2');      /*White Female Fur Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051158', '3200', 'Overall 2');      /*School Uniform with Hoody Jumper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051159', '5000', 'Overall 2');      /*Girls Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051160', '2700', 'Overall 2');      /*Pink-Striped Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051161', '7400', 'Overall 2');      /*Thai Formal Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051162', '6800', 'Overall 2');      /*Cute Sailor Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051163', '5000', 'Overall 2');      /*Gothic Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051164', '6800', 'Overall 2');      /*Kitty Hoodie*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051166', '3700', 'Overall 2');      /*Dressu Korean Traditional Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051167', '6300', 'Overall 2');      /*Black Rockabilly Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051169', '5600', 'Overall 2');      /*Sky Blue Picnicwear [F]*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051170', '7400', 'Overall 2');      /*Buddy Overall Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051171', '2700', 'Overall 2');      /*Royal Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051173', '3200', 'Overall 2');      /*Purple Dorothy Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051174', '4300', 'Overall 2');      /*Bikini*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051175', '3200', 'Overall 2');      /*Strawberry Milk Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051176', '7400', 'Overall 2');      /*Shin-Hwa High Uniform*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051179', '5000', 'Overall 2');      /*Pretty Girl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051180', '3700', 'Overall 2');      /*Sailor Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051182', '3200', 'Overall 2');      /*Exotic Festival Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051183', '5600', 'Overall 2');      /*Night Fever Ensemble*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051185', '5600', 'Overall 2');      /*Maid Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051188', '6800', 'Overall 2');      /*Blue Daisy Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051189', '2700', 'Overall 2');      /*Yellow Anticipation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051190', '4700', 'Overall 2');      /*Seraphim Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051192', '4700', 'Overall 2');      /*Blue Marine Girl*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051193', '3200', 'Overall 2');      /*Orange Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051195', '3700', 'Overall 2');      /*Cutie Girl Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051196', '6800', 'Overall 2');      /*Black Top Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051197', '6800', 'Overall 2');      /*Nya-ong's Long Hood T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051198', '5600', 'Overall 2');      /*Pink mini dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051200', '4300', 'Overall 2');      /*Bunny Girl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051206', '5600', 'Overall 2');      /*Retro School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051208', '5600', 'Overall 2');      /*Strawberry Girl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051209', '6300', 'Overall 2');      /*Evan Great Suit*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051211', '3700', 'Overall 2');      /*Elizabeth Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051212', '5600', 'Overall 2');      /*Evan Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051218', '6300', 'Overall 2');      /*Maple Girl School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051219', '5000', 'Overall 2');      /*Rainbow Mini Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051220', '2700', 'Overall 2');      /*Elizabeth Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051221', '6300', 'Overall 2');      /*Holiday Party Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051227', '4700', 'Overall 2');      /*Rookie Maple Girl School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051228', '4700', 'Overall 2');      /*Pink Snow Flower Wear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051229', '4700', 'Overall 2');      /*Flower Heiress Hanbok*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051231', '4300', 'Overall 2');      /*Alps Girl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051232', '5600', 'Overall 2');      /*Pink Shock Pop Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051233', '4700', 'Overall 2');      /*Taxi Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051235', '2700', 'Overall 2');      /*Orange Checked Squares*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052049', '4700', 'Overall 2');      /*Yang In*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051252', '6300', 'Overall 2');      /*Pink Angel Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051253', '6300', 'Overall 2');      /*Little Red Riding Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051254', '3200', 'Overall 2');      /*Schoolgirl Formals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051255', '4300', 'Overall 2');      /*Golden Moonlight Dress*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051256', '5000', 'Overall 2');      /*Light Chiffon Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051261', '6300', 'Overall 2');      /*Marchen Fantasy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051262', '6800', 'Overall 2');      /*Maple Doctor's Scrubs (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051264', '2700', 'Overall 2');      /*Silver Angora Fur Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051265', '5000', 'Overall 2');      /*Gold Angora Fur Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051270', '6300', 'Overall 2');      /*Dark Force Mail (F) */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051271', '3200', 'Overall 2');      /*Elven Spirit Coat (F) */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051276', '6800', 'Overall 2');      /*Imperial Garnet Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051277', '2700', 'Overall 2');      /*Cygnus Dress*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051278', '3700', 'Overall 2');      /*Cherry Snow Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051279', '6300', 'Overall 2');      /*Elven Spirit Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051280', '6800', 'Overall 2');      /*Gentle Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051282', '5600', 'Overall 2');      /*Rosemary Tea*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051284', '2700', 'Overall 2');      /*Magic Star Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051285', '6300', 'Overall 2');      /*Princess Charming*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051290', '4300', 'Overall 2');      /*Jett's Outfit(F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051292', '6300', 'Overall 2');      /*Opening Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051294', '4700', 'Overall 2');      /*Lyrical Dress*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051295', '4700', 'Overall 2');      /*Magical Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051296', '3200', 'Overall 2');      /*Cynical Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051297', '3200', 'Overall 2');      /*Little Red Riding Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051301', '4700', 'Overall 2');      /*Saint Luminous*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051302', '6800', 'Overall 2');      /*Pink Fluffy Hanbok*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051304', '7400', 'Overall 2');      /*Halloween Leopard Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051311', '6300', 'Overall 2');      /*Dark Force Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051312', '6800', 'Overall 2');      /*Alps Girl Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051332', '3200', 'Overall 2');      /*Logical Dress*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051333', '2700', 'Overall 2');      /*Miracle Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051345', '7400', 'Overall 2');      /*Fluffy Cat Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051347', '6800', 'Overall 2');      /*Magic Star Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051348', '4300', 'Overall 2');      /*Ellinia Magic Academy Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051349', '4300', 'Overall 2');      /*Succubus Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051350', '6300', 'Overall 2');      /*Golden Bell Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051351', '3700', 'Overall 2');      /*GM Nori's Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051352', '4300', 'Overall 2');      /*Thermidor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051357', '7400', 'Overall 2');      /*Pinky Baseball*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051358', '2700', 'Overall 2');      /*Pink Cutie Bikini*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051359', '3200', 'Overall 2');      /*Beach Babe Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051362', '7400', 'Overall 2');      /*Superstar Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051363', '6800', 'Overall 2');      /*Rising Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051365', '6300', 'Overall 2');      /*Beta Seraphim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051366', '3200', 'Overall 2');      /*Baby Doll Linka*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051367', '4700', 'Overall 2');      /*Fresh Ice*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051368', '7400', 'Overall 2');      /*Fresh Checkered Vacation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051369', '2700', 'Overall 2');      /*Powder Maid's Getup (F)*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051370', '7400', 'Overall 2');      /*Passionate Qi Pao*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051371', '5000', 'Overall 2');      /*Ribbon Girl School Look*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051372', '7400', 'Overall 2');      /*Splash Choco Girl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051373', '3700', 'Overall 2');      /*Bloody Jeanne*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051374', '7400', 'Overall 2');      /*Odette Tutu*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051375', '3200', 'Overall 2');      /*Odile Tutu*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051376', '6800', 'Overall 2');      /*Halloweenroid Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051382', '4700', 'Overall 2');      /*Lovely Shopper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051383', '4700', 'Overall 2');      /*Moonlight Outfit*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051384', '6800', 'Overall 2');      /*Glory Pony*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051385', '5000', 'Overall 2');      /*Plop! Chocolate Girl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051386', '3200', 'Overall 2');      /*Blue Shiny Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051387', '2700', 'Overall 2');      /*Pink Picnic Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051389', '6800', 'Overall 2');      /*White Fiancee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051390', '4300', 'Overall 2');      /*Forsythia Picnic*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051391', '6800', 'Overall 2');      /*Icy Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051392', '5600', 'Overall 2');      /*Party Princess*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051405', '5600', 'Overall 2');      /*Melody Girl*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051406', '4300', 'Overall 2');      /*Hawaiian Couple*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051407', '3700', 'Overall 2');      /*Maple Leaf High Swimsuit (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051408', '4300', 'Overall 2');      /*Shiny Light*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051409', '5600', 'Overall 2');      /*Gentle Ice Girl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051410', '3200', 'Overall 2');      /*In-forest Camping Look*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051411', '3700', 'Overall 2');      /*Lady Rosalia*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051415', '6800', 'Overall 2');      /*Cheer Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051420', '2700', 'Overall 2');      /*Ribbon Angel Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051422', '5600', 'Overall 2');      /*Sweet Patissiere*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051423', '6300', 'Overall 2');      /*Pink Snow Flower Wear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051424', '3200', 'Overall 2');      /*Santa Girl Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051425', '7400', 'Overall 2');      /*Big Ribbon Yellow Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051426', '4300', 'Overall 2');      /*Gothic Girls Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051429', '3200', 'Overall 2');      /*Sweet Snow Flower*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051430', '4700', 'Overall 2');      /*Sierra Grace Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051431', '3700', 'Overall 2');      /*Ms. Love Messenger Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051432', '3200', 'Overall 2');      /*Little Trainer Outfit (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051434', '5000', 'Overall 2');      /*Blooming Leafy Love Outfit*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051435', '7400', 'Overall 2');      /*Starlight Girl Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051436', '5600', 'Overall 2');      /*The Kingdom Dress of Queen*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051437', '4700', 'Overall 2');      /*Pink Romance*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051439', '3200', 'Overall 2');      /*Mint Kitty Tea Party*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051440', '7400', 'Overall 2');      /*Pink Sapphire*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051441', '5600', 'Overall 2');      /*Bunny Romper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051442', '6300', 'Overall 2');      /*Momo Maid Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051444', '3200', 'Overall 2');      /*Baby Doll Linka*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051445', '4300', 'Overall 2');      /*Party Princess*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051446', '5600', 'Overall 2');      /*Pinky Baseball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051447', '3700', 'Overall 2');      /*Odette Tutu*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051448', '5600', 'Overall 2');      /*Yeonhwa School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051450', '3200', 'Overall 2');      /*Kinesis Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051451', '7400', 'Overall 2');      /*Kinesis Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051452', '3700', 'Overall 2');      /*Orange Day*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051453', '6300', 'Overall 2');      /*Teddy Suspenders*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051454', '6800', 'Overall 2');      /*Penguin Doll Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051455', '4700', 'Overall 2');      /*Time Cantabile*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051456', '4700', 'Overall 2');      /*Sparkling Bluebird (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051457', '6800', 'Overall 2');      /*British Marine Outfit (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051458', '4700', 'Overall 2');      /*Ursus Lady's Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051459', '7400', 'Overall 2');      /*Pure Farmer One-piece*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051460', '7400', 'Overall 2');      /*Polka Dot Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051461', '4700', 'Overall 2');      /*Giant Ribbon Outfit (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052042', '2700', 'Overall 2');      /*Pink Robot Pilotgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051463', '4700', 'Overall 2');      /*Bloody Bride*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051464', '3200', 'Overall 2');      /*Banana Outing Clothes*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051465', '2700', 'Overall 2');      /*Shy Traveler*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051466', '7400', 'Overall 2');      /*Blue Marine Uniform (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052050', '5000', 'Overall 2');      /*Red Hip Hop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052051', '3700', 'Overall 2');      /*Blue Hip Hop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051470', '6800', 'Overall 2');      /*Time Mistress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052045', '5600', 'Overall 2');      /*Mink Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052048', '3700', 'Overall 2');      /*Brown Snowboard Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052046', '7400', 'Overall 2');      /*Snowman Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052047', '3700', 'Overall 2');      /*Black Snowboard Overall*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051471', '2700', 'Overall 2');      /*Evan Dragon Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051475', '6300', 'Overall 2');      /*Mystic Phantom Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051477', '6800', 'Overall 2');      /*Winter Aran Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051473', '3200', 'Overall 2');      /*Royal Mercedes Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051479', '6300', 'Overall 2');      /*Chiaroscuro Luminous Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051481', '5600', 'Overall 2');      /*Secret Shade Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052040', '7400', 'Overall 2');      /*Cao Cao Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052041', '5000', 'Overall 2');      /*Sun Quan Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051483', '4300', 'Overall 2');      /*Frilly Bathrobe*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051484', '4300', 'Overall 2');      /*Santa Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051485', '5600', 'Overall 2');      /*Time Cantabile*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051486', '3700', 'Overall 2');      /*Snow Deer Parka*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052044', '6300', 'Overall 2');      /*Scuba Diving Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1051490', '2700', 'Overall 2');      /*Concert Muse (Soprano)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052000', '6300', 'Overall 2');      /*Recycled Box*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052001', '6300', 'Overall 2');      /*Paper Box*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052002', '6300', 'Overall 2');      /*Cardboard Box*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052003', '4300', 'Overall 2');      /*Blue Chinese Undead Costume*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052004', '2700', 'Overall 2');      /*Maroon Chinese Undead Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052005', '7400', 'Overall 2');      /*Yellow Raincoat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052006', '6300', 'Overall 2');      /*Sky Blue Raincoat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052007', '2700', 'Overall 2');      /*Red Raincoat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052008', '6300', 'Overall 2');      /*Green Raincoat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052009', '5000', 'Overall 2');      /*Orange Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052010', '4700', 'Overall 2');      /*Pink Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052011', '4700', 'Overall 2');      /*Blue Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052012', '2700', 'Overall 2');      /*Green Overall*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052013', '5000', 'Overall 2');      /*Graduation Gown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052014', '5600', 'Overall 2');      /*Ducky Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052015', '4300', 'Overall 2');      /*Blue Shinsengumi Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052016', '3200', 'Overall 2');      /*Brown Shinsengumi Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052017', '5000', 'Overall 2');      /*Orange Life-Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052018', '3700', 'Overall 2');      /*Green Life-Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052019', '5000', 'Overall 2');      /*Blue Life-Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052020', '3700', 'Overall 2');      /*White Body Tights*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052021', '4300', 'Overall 2');      /*Black Body Tights*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052022', '7400', 'Overall 2');      /*White Holed Tights*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052023', '3700', 'Overall 2');      /*Black Holed Tights*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052024', '7400', 'Overall 2');      /*Big Kimono*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052025', '2700', 'Overall 2');      /*Denim Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052026', '3200', 'Overall 2');      /*Grey Full Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052027', '3700', 'Overall 2');      /*Red Full Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052028', '3700', 'Overall 2');      /*Forest Samurai Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052029', '5600', 'Overall 2');      /*Premium Trenchcoat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052030', '5600', 'Overall 2');      /*Toga*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052031', '2700', 'Overall 2');      /*Reindeer Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052032', '6800', 'Overall 2');      /*Red Bruma*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052033', '6300', 'Overall 2');      /*Green Bruma*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052034', '5600', 'Overall 2');      /*Blue Bruma*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052035', '4300', 'Overall 2');      /*Guan Yu Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052036', '6300', 'Overall 2');      /*Zhu-Ge-Liang Gown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052037', '7400', 'Overall 2');      /*Patissier Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052038', '3200', 'Overall 2');      /*Blue Robot Pilotgear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052039', '6800', 'Overall 2');      /*Liu Bei Robe*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052043', '3200', 'Overall 2');      /*Hip Hop Sweats*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052052', '3700', 'Overall 3');      /*Musashi Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052053', '2700', 'Overall 3');      /*Teddy Bear Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052054', '4700', 'Overall 3');      /*Welder Look*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052055', '2700', 'Overall 3');      /*Enamer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052056', '5000', 'Overall 3');      /*Soccer Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052057', '5000', 'Overall 3');      /*Soccer Uniform (No.7)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052058', '3200', 'Overall 3');      /*Soccer Uniform (No.10)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052059', '6300', 'Overall 3');      /*Soccer Uniform (No.14)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052060', '5000', 'Overall 3');      /*England Soccer Uniform(No.8)*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052061', '2700', 'Overall 3');      /*Brazil Soccer Uniform(No.9)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052062', '4300', 'Overall 3');      /*France Soccer Uniform(No.10)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052063', '4300', 'Overall 3');      /*USA Soccer Uniform(No.17)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052064', '5600', 'Overall 3');      /*Soccer Uniform(No.4)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052065', '5000', 'Overall 3');      /*Soccer Uniform(No.21)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052066', '4300', 'Overall 3');      /*Mexico Soccer Uniform(No.9)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052067', '3700', 'Overall 3');      /*Mummy Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052068', '4300', 'Overall 3');      /*Skull Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052069', '2700', 'Overall 3');      /*Flamboyant Autumn Gear*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052070', '6800', 'Overall 3');      /*Golden Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052073', '5600', 'Overall 3');      /*White Rabbit Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052074', '2700', 'Overall 3');      /*Nero Bell Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052077', '7400', 'Overall 3');      /*Moon Bunny Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052078', '6800', 'Overall 3');      /*Soap Bubble Bonanza*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052079', '7400', 'Overall 3');      /*Prince of Darkness*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052082', '3700', 'Overall 3');      /*Elf Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052083', '7400', 'Overall 3');      /*Sun Wukong Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052084', '3700', 'Overall 3');      /*Golden Armor*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052085', '5000', 'Overall 3');      /*Red Amorian Apron*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052086', '6300', 'Overall 3');      /*Blue Amorian Apron*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052087', '6800', 'Overall 3');      /*Dark Blue Kimono*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052089', '5600', 'Overall 3');      /*Black Overcoat of Doom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052090', '2700', 'Overall 3');      /*Rompers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052091', '5600', 'Overall 3');      /*Sachiel Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052092', '7400', 'Overall 3');      /*Veamoth Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052093', '3200', 'Overall 3');      /*Janus Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052094', '3200', 'Overall 3');      /*Zhu Ba Jie Overall*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052135', '4700', 'Overall 3');      /*Centaurus Legs*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052136', '3700', 'Overall 3');      /*2nd Anniversary Mushroom Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052137', '4300', 'Overall 3');      /*Tomato Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052142', '2700', 'Overall 3');      /*Shorts with Suspenders*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052143', '6800', 'Overall 3');      /*Sky Blue Padded Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052144', '5000', 'Overall 3');      /*Luxurious Padded Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052145', '4300', 'Overall 3');      /*Christmas Party Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052147', '6800', 'Overall 3');      /*Chinese Lion Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052151', '6800', 'Overall 3');      /*Bosshunter Armor*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052152', '6800', 'Overall 3');      /*Bosshunter Gi*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052153', '3700', 'Overall 3');      /*Red Viska for Transformation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052154', '3700', 'Overall 3');      /*Tiger Cub Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052168', '3200', 'Overall 3');      /*Cutie Birk Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052169', '7400', 'Overall 3');      /*Gaga Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052170', '7400', 'Overall 3');      /*Noob Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052171', '3700', 'Overall 3');      /*Baby Chick Apron*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052172', '5000', 'Overall 3');      /*Pumpkin Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052174', '4700', 'Overall 3');      /*Fox Outfit*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052175', '5600', 'Overall 3');      /*Coastal Winter Wear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052176', '3200', 'Overall 3');      /*Fashionable Checkerwear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052178', '4300', 'Overall 3');      /*Snowflake Knit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052179', '6300', 'Overall 3');      /*Cow Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052180', '5600', 'Overall 3');      /*Denim Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052182', '6800', 'Overall 3');      /*Galactic Hero Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052183', '3700', 'Overall 3');      /*Stealth Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052724', '6300', 'Overall 3');      /*Cutie Horse Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052192', '5000', 'Overall 3');      /*Bombacha*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052193', '3700', 'Overall 3');      /*Honeybee Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052194', '6800', 'Overall 3');      /*Ugabuga*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052195', '6800', 'Overall 3');      /*Aran Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052754', '3200', 'Overall 3');      /*Pink Panda Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052197', '6800', 'Overall 3');      /*Brave Soldier Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052198', '6300', 'Overall 3');      /*Pink Bean Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052199', '3200', 'Overall 3');      /*Blade Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052200', '4700', 'Overall 3');      /*Lolli Pink Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052201', '3200', 'Overall 3');      /*Shiny Sailor Uniform*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052203', '6300', 'Overall 3');      /*One Summer Night */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052204', '6800', 'Overall 3');      /*Marine Girl Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052205', '3200', 'Overall 3');      /*Pluto Hero Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052206', '7400', 'Overall 3');      /*Gaga Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052207', '5600', 'Overall 3');      /*Cursed Golden Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052209', '5600', 'Overall 3');      /*Royal Navy Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052210', '4300', 'Overall 3');      /*Alchemist Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052211', '2700', 'Overall 3');      /*Fire Shadow Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052212', '6800', 'Overall 3');      /*Cherry Blossom Suit*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052213', '5600', 'Overall 3');      /*Chaos Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052214', '6800', 'Overall 3');      /*Maple Racing Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052218', '3700', 'Overall 3');      /*Clown Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052224', '7400', 'Overall 3');      /*Strawberry Baby*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052225', '2700', 'Overall 3');      /*Lolita Butterfly Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052228', '3200', 'Overall 3');      /*Layered Long Skull Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052229', '6300', 'Overall 3');      /*Qi-pao Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052231', '4700', 'Overall 3');      /*Little Prince*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052232', '3700', 'Overall 3');      /*Pink Fur Ribbon Dress*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052233', '5000', 'Overall 3');      /*White Fur Ribbon Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052234', '6300', 'Overall 3');      /*Stylish Layered Plaid*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052236', '7400', 'Overall 3');      /*Petite School Shawl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052245', '5000', 'Overall 3');      /*Green Leaf Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052246', '6300', 'Overall 3');      /*Cat Set Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052248', '2700', 'Overall 3');      /*Dual Blade Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052253', '7400', 'Overall 3');      /*Green Overall Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052255', '7400', 'Overall 3');      /*Hawkeye Captain Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052268', '3700', 'Overall 3');      /*Violet Tunic*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052275', '2700', 'Overall 3');      /*Royal Rainbow Zip-Up Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052282', '6300', 'Overall 3');      /*Oz Magic Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052283', '7400', 'Overall 3');      /*Henesys Academy Uniform (with skirt)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052284', '4700', 'Overall 3');      /*Henesys Academy Uniform (with pants)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052286', '3700', 'Overall 3');      /*Pilot Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052289', '3700', 'Overall 3');      /*Wild Hunter Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052290', '6300', 'Overall 3');      /*Battle Mage Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052291', '4300', 'Overall 3');      /*Hooded Track Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052292', '7400', 'Overall 3');      /*King Crow Suit*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052293', '2700', 'Overall 3');      /*Sanctus Combat Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052294', '2700', 'Overall 3');      /*Sanctus Combat Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052295', '3700', 'Overall 3');      /*Maid Dress (Pink)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052296', '7400', 'Overall 3');      /*Maid Dress (Blue)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052298', '7400', 'Overall 3');      /*Rabbit Ear Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052306', '4700', 'Overall 3');      /*Japanesque Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052309', '4700', 'Overall 3');      /*Trench Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052324', '3200', 'Overall 3');      /*Paypal Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052329', '7400', 'Overall 3');      /*Pitch Dark Outfit*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052330', '3700', 'Overall 3');      /*Blue Mage Gear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052331', '5600', 'Overall 3');      /*Red Mage Gear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052332', '3200', 'Overall 3');      /*Christmas Casual Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052338', '4700', 'Overall 3');      /*Red's Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052339', '6300', 'Overall 3');      /*Lab Gear (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052340', '4300', 'Overall 3');      /*Lab Gear (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052746', '6300', 'Overall 3');      /*Chef Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052345', '6800', 'Overall 3');      /*Winter 2010 Moon Bunny Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052348', '3200', 'Overall 3');      /*Commander Captain*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052349', '3200', 'Overall 3');      /*Belt Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052354', '5000', 'Overall 3');      /*Rising Star Baggy Digs*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052355', '2700', 'Overall 3');      /*Rookie Yellow Raincoat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052356', '6800', 'Overall 3');      /*Military Pop Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052367', '5600', 'Overall 3');      /*Crow Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052368', '3700', 'Overall 3');      /*Starling Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052369', '3700', 'Overall 3');      /* MSE 4 Years & Unstoppable Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052370', '4700', 'Overall 3');      /*Victorian Vampire Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052372', '6300', 'Overall 3');      /*Blue Arabian Outfit*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052373', '3700', 'Overall 3');      /*Red Arabian Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052408', '4700', 'Overall 3');      /*Kerning Engineering School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052410', '7400', 'Overall 3');      /*Ribboned Justice Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052411', '7400', 'Overall 3');      /*Alchemist Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052412', '4300', 'Overall 3');      /*Toy Prince*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052416', '7400', 'Overall 3');      /*Orchid's Black Wing Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052417', '3700', 'Overall 3');      /*Honeybee Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052418', '5600', 'Overall 3');      /*Princely Daywear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052419', '4700', 'Overall 3');      /*Pink Lolita Outfit*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052421', '4700', 'Overall 3');      /*Urban Pirate Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052423', '6300', 'Overall 3');      /*Hades Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052424', '6800', 'Overall 3');      /*Fancy Noblesse Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052425', '6800', 'Overall 3');      /*White Combat Tunic*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052426', '3700', 'Overall 3');      /*White Combat Habit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052435', '5000', 'Overall 3');      /*Princess Hakama*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052438', '5600', 'Overall 3');      /*Ganache Chocolate Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052439', '3200', 'Overall 3');      /*Ellinia Magic School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052440', '5000', 'Overall 3');      /*Mu Lung Dojo Uniform*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052442', '4700', 'Overall 3');      /*Combat Fatigues*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052443', '5600', 'Overall 3');      /*Taisho Romance*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052445', '3200', 'Overall 3');      /*Intergalactic Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052446', '5000', 'Overall 3');      /*Light Chiffon Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052447', '2700', 'Overall 3');      /*Light Cotton Candy Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052448', '5000', 'Overall 3');      /*Tomato Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052449', '2700', 'Overall 3');      /*Sausage Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052455', '2700', 'Overall 3');      /*Honeybee Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052458', '2700', 'Overall 3');      /*Lucia Overall*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052459', '6800', 'Overall 3');      /*Blue Angel Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052474', '4300', 'Overall 3');      /*The Onmyouji Ceremonial Robes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052503', '4700', 'Overall 3');      /*Cool Summer Look*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052531', '6300', 'Overall 3');      /*The Bladed Falcon's Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052536', '3700', 'Overall 3');      /*Marine Stripe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052537', '6300', 'Overall 3');      /*Hyper Honeybee Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052538', '6800', 'Overall 3');      /*Ghost Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052539', '5000', 'Overall 3');      /*Blue Chinese Undead Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052540', '2700', 'Overall 3');      /*Cow Costume*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052541', '3700', 'Overall 3');      /*Tiger Cub Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052542', '4700', 'Overall 3');      /*Angel Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052543', '5000', 'Overall 3');      /*Paper Box*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052544', '3200', 'Overall 3');      /*Hyper Rising Star Baggy Digs*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052549', '7400', 'Overall 3');      /*[MS Custom] Orange Life-Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052550', '4300', 'Overall 3');      /*Seal Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052551', '6800', 'Overall 3');      /*Yellow Bell Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052552', '4700', 'Overall 3');      /*Gray Bell Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052554', '6800', 'Overall 3');      /*Cat Lolita Outfit*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052565', '2700', 'Overall 3');      /*Reindeer Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052566', '5000', 'Overall 3');      /*Snowman Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052567', '5600', 'Overall 3');      /*Santa Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052568', '6300', 'Overall 3');      /*Decked-Out Santa Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052571', '5600', 'Overall 3');      /*Dark Devil Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052574', '5000', 'Overall 3');      /*Flowing Flame Robes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052575', '6800', 'Overall 3');      /*Pious Shaman Robes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052576', '5600', 'Overall 3');      /*Lotus's Black Wings Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052577', '5600', 'Overall 3');      /*Stylish Layered Plaid*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052579', '6800', 'Overall 3');      /*Xenon Neo-Tech Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052585', '6300', 'Overall 3');      /*Fluffy Cat Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052586', '4300', 'Overall 3');      /*Lucia Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052587', '2700', 'Overall 3');      /*Harp Seal Doll Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052594', '2700', 'Overall 3');      /*Green Dinosaur Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052595', '3700', 'Overall 3');      /*Purple Dinosaur Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052598', '6300', 'Overall 3');      /*Hawkeye Captain Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052599', '4300', 'Overall 3');      /*Oz Magic Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052601', '4300', 'Overall 3');      /*Kerning Technical High Uniform*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052602', '4700', 'Overall 3');      /*Black Duster*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052603', '2700', 'Overall 3');      /*Mu Lung Academy Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052604', '3200', 'Overall 3');      /*Blue Love Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052605', '6300', 'Overall 3');      /*Ramling PJs*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052610', '5600', 'Overall 3');      /*Succubus Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052618', '5000', 'Overall 3');      /*Blue Kitty Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052619', '6800', 'Overall 3');      /*Pink Kitty Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052624', '5600', 'Overall 3');      /*GM Haku's Pirate Gear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052626', '5600', 'Overall 3');      /*Splash Wave*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052627', '5000', 'Overall 3');      /*Pirate Captain's Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052628', '6300', 'Overall 3');      /*Blue Officer Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052629', '6800', 'Overall 3');      /*Silver Officer Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052630', '2700', 'Overall 3');      /*Black Officer Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052634', '4700', 'Overall 3');      /*Man's Shirts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052636', '7400', 'Overall 3');      /*Patissier Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052643', '5000', 'Overall 3');      /*Bloody Jeanne*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052644', '4300', 'Overall 3');      /*Shadow Executer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052654', '3200', 'Overall 3');      /*フューチャーロイドネオンアーマー*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052655', '4700', 'Overall 3');      /*Dawn Bear Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052656', '5000', 'Overall 3');      /*White Swan Ballet Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052657', '5000', 'Overall 3');      /*Black Swan Ballet Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052660', '7400', 'Overall 3');      /*Balloon Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052661', '6800', 'Overall 3');      /*Chicken Coataroo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052662', '5000', 'Overall 3');      /*Camellia Flower Lovely Night Clothes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052663', '4700', 'Overall 3');      /*Flowing Wind Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052664', '2700', 'Overall 3');      /*Gentleman Bow Tie Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052665', '2700', 'Overall 3');      /*Gentleman Suit*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052666', '3200', 'Overall 3');      /*Chocoram Doll Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052667', '3200', 'Overall 3');      /*Puffram Onesie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052668', '5600', 'Overall 3');      /*Princess of Time Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052671', '6800', 'Overall 3');      /*Oversized Oxford*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052674', '5600', 'Overall 3');      /*Dark Devil Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052675', '6300', 'Overall 3');      /*Vampire Phantom Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052676', '6300', 'Overall 3');      /*Kirito's Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052677', '6800', 'Overall 3');      /*Asuna's Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052678', '5000', 'Overall 3');      /*Leafa's Dress*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052679', '4300', 'Overall 3');      /*Freud's Robe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052680', '6300', 'Overall 3');      /*Aran's Armor(M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052681', '2700', 'Overall 3');      /*Aran's Armor(F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052682', '3700', 'Overall 3');      /*Brave Aran's Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052684', '6300', 'Overall 3');      /*Pumpkin Bat Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052685', '7400', 'Overall 3');      /*Yui's Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052692', '3200', 'Overall 3');      /*Mr. K's Cat Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052693', '4700', 'Overall 3');      /*Rudi's Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052747', '5000', 'Overall 3');      /*Contemporary Chic Outfit*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052749', '5600', 'Overall 3');      /*Nurse Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052750', '5000', 'Overall 3');      /*Doctor Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052725', '4700', 'Overall 3');      /*Fancy Magician Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052726', '3700', 'Overall 3');      /*Ghost Bride Wedding Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052727', '2700', 'Overall 3');      /*Refreshing Male Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052728', '7400', 'Overall 3');      /*Refreshing Female Cardigan Outfit*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052762', '5000', 'Overall 4');      /*Banana Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052771', '6300', 'Overall 4');      /*Ayame Overall Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052772', '6300', 'Overall 4');      /*2014 유니폼*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052773', '5600', 'Overall 4');      /*暗夜精灵铠甲*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052774', '6300', 'Overall 4');      /*隐武士铠甲*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052779', '5000', 'Overall 4');      /*Peach Camellia Kimono*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052780', '3200', 'Overall 4');      /*Red Wind Robes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052781', '3700', 'Overall 4');      /*Red Pony Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052782', '2700', 'Overall 4');      /*Blue Pony Overalls*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052811', '4300', 'Overall 4');      /*Bright Angel Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052812', '4700', 'Overall 4');      /*Dark Devil Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052837', '6300', 'Overall 4');      /*Gym Teacher's Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052838', '3200', 'Overall 4');      /*Student Swimsuit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052841', '5000', 'Overall 4');      /*Sweet Persimmon Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052842', '2700', 'Overall 4');      /*White Puppy Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052843', '6800', 'Overall 4');      /*Brown Puppy Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052844', '4700', 'Overall 4');      /*Corn Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052845', '3200', 'Overall 4');      /*Loose-fit Homecoming Duds*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052846', '6800', 'Overall 4');      /*Peach Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052852', '5600', 'Overall 4');      /*Raging Lotus Gown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052853', '3200', 'Overall 4');      /*Ill Orchid Gown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052854', '5600', 'Overall 4');      /*Worn Ghost Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052855', '7400', 'Overall 4');      /*Worn Witch Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052856', '4300', 'Overall 4');      /*Worn Skull Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052857', '6300', 'Overall 4');      /*Ghost Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052858', '6300', 'Overall 4');      /*Witch Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052859', '3200', 'Overall 4');      /*Skull Outfit*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052864', '5600', 'Overall 4');      /*Pumpkin Bat Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052865', '5600', 'Overall 4');      /*Dinofrog Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052870', '6800', 'Overall 4');      /*Cadet Corps Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052871', '5000', 'Overall 4');      /*Scout Regiment Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052872', '5000', 'Overall 4');      /*Red Ribbon Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052873', '4300', 'Overall 4');      /*Mikasa's Scout Regiment Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052874', '2700', 'Overall 4');      /*Levi's Scout Regiment Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052876', '2700', 'Overall 4');      /*Eren's Scout Regiment Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052891', '5600', 'Overall 4');      /*Blue Bird Overall*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052892', '4700', 'Overall 4');      /*Blushing Bunny Suspenders*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052894', '4700', 'Overall 4');      /*Romantic Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052895', '2700', 'Overall 4');      /*Silver Wolf Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052896', '5000', 'Overall 4');      /*Cutie Birk Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052897', '7400', 'Overall 4');      /*Snowman Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052898', '5000', 'Overall 4');      /*Scuba Diving Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052899', '6300', 'Overall 4');      /*Black Mouse Hooded Onesie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052901', '7400', 'Overall 4');      /*Hipster*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052902', '4700', 'Overall 4');      /*Jumpsuit*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052903', '4300', 'Overall 4');      /*White Servant Tux*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052904', '3200', 'Overall 4');      /*Lovely Princess Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052909', '3200', 'Overall 4');      /*Honeybee Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052910', '2700', 'Overall 4');      /*Crystal Cat Outfit (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052911', '3200', 'Overall 4');      /*Crystal Cat Outfit (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052912', '4700', 'Overall 4');      /*Quilting Fashion King*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052916', '2700', 'Overall 4');      /*Akarin's Flowery Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052917', '6300', 'Overall 4');      /*Akatsuki's Dark Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052920', '6800', 'Overall 4');      /*Red Mouse Hooded Onesie*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052921', '5000', 'Overall 4');      /*Bubbly Blue Carp Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052922', '4300', 'Overall 4');      /*Bubbly Red Carp Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052923', '4300', 'Overall 4');      /*Feline Blue Sleeves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052924', '5600', 'Overall 4');      /*Noble Blossom Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052925', '4300', 'Overall 4');      /*Pink Blossom Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052926', '2700', 'Overall 4');      /*Cottontail Rabbit Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052939', '6300', 'Overall 4');      /*돼지바 탱글딸기*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052940', '4300', 'Overall 4');      /*Spring Sunlight Pullover*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052941', '5000', 'Overall 4');      /*Dark Lotus Uniform*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052942', '4300', 'Overall 4');      /*Blue Panda Doll Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052946', '5600', 'Overall 4');      /*Schwarzer Cross*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052947', '4700', 'Overall 4');      /*Red Mouse Hooded Onesie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052948', '3200', 'Overall 4');      /*Evening Orchid*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052949', '6300', 'Overall 4');      /*Haku Cloth*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052951', '3200', 'Overall 4');      /*Polka Dot Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052954', '6300', 'Overall 4');      /*Deep Sky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052954', '2700', 'Overall 4');      /*Deep Sky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052955', '5600', 'Overall 4');      /*Assistant Chef Outfit*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052956', '3200', 'Overall 4');      /*Beginner Chef Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052957', '7400', 'Overall 4');      /*Intermediate Chef Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052958', '6300', 'Overall 4');      /*Advanced Chef Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052959', '3200', 'Overall 4');      /*Sous-Chef Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052960', '5600', 'Overall 4');      /*Chef Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052961', '4700', 'Overall 4');      /*Scuba Diving Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052965', '6300', 'Overall 4');      /*Black Sailor Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052966', '3200', 'Overall 4');      /*Hilla Android Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052967', '3200', 'Overall 4');      /*Magnus Android Uniform*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052970', '4300', 'Overall 4');      /*Toy Prince*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052975', '4700', 'Overall 4');      /*Preppy Suspenders*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052976', '6800', 'Overall 4');      /*Clear Blue*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052977', '6300', 'Overall 4');      /*Pink Cardigan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052994', '6300', 'Overall 4');      /*Abyss Burgunt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052995', '2700', 'Overall 4');      /*Nyanya Steward Tuxedo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052996', '2700', 'Overall 4');      /*Undertaker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052998', '4300', 'Overall 4');      /*Show me the Meso*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1052999', '4700', 'Overall 4');      /*Polar Fur-Trimmed Dress*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053000', '5600', 'Overall 4');      /*Enari's Cow Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053001', '6800', 'Overall 4');      /*Flutter-sleeve Bell Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053006', '3700', 'Overall 4');      /*Yarn Bunny Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053014', '2700', 'Overall 4');      /*Urban Pirate Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053015', '6800', 'Overall 4');      /*Reindeer Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053016', '4300', 'Overall 4');      /*Show me the Meso*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053017', '3700', 'Overall 4');      /*Undertaker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053018', '6800', 'Overall 4');      /*Beaky Owl Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053022', '6300', 'Overall 4');      /*Umbral Attire*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053023', '5600', 'Overall 4');      /*Umbral Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053024', '6300', 'Overall 4');      /*Flower Dancer's Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053025', '4300', 'Overall 4');      /*Moon Dancer's Attire*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053028', '4300', 'Overall 4');      /*Baby Binkie Spacesuit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053033', '5000', 'Overall 4');      /*Damien Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053034', '6800', 'Overall 4');      /*Alicia Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053040', '5000', 'Overall 4');      /*Shark Bodysuit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053041', '6300', 'Overall 4');      /*Blue Phoenix Toga*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053042', '3200', 'Overall 4');      /*Red Phoenix Toga*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053045', '7400', 'Overall 4');      /*Kitty Overall (Male)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053046', '7400', 'Overall 4');      /*Winged Kitty Dress (Female)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053047', '7400', 'Overall 4');      /*Mischievous Sweet Pig Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053048', '2700', 'Overall 4');      /*Cunning Sweet Pig Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053051', '5000', 'Overall 4');      /*Chicken Cutie Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053052', '5600', 'Overall 4');      /*Bubble Leaf Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053053', '6300', 'Overall 4');      /*Bubble Leaf Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053056', '3200', 'Overall 4');      /*Blaster Outfit (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053057', '4300', 'Overall 4');      /*Blaster Outfit (F)*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053058', '6300', 'Overall 4');      /*Sky-blue Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053059', '3700', 'Overall 4');      /*Villain's Cool Tights (Outfit)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053060', '4700', 'Overall 4');      /*Colorful Bikini*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053061', '6800', 'Overall 4');      /*Colorful Beach Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053083', '3700', 'Overall 4');      /*Super Miracle Cube Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053084', '6300', 'Overall 4');      /*Violet Cube Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053085', '3700', 'Overall 4');      /*Black Cube Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053086', '3700', 'Overall 4');      /*Kamaitachi Outfit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053087', '5600', 'Overall 4');      /*Formal Brown Shorts*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053088', '6300', 'Overall 4');      /*Formal Brown Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053089', '3700', 'Overall 4');      /*Moon Bunny Outfit (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053090', '3200', 'Overall 4');      /*Moon Bunny Outfit (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053091', '5600', 'Overall 4');      /*Dark Musician Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053092', '3700', 'Overall 4');      /*Chained Princess Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053093', '4700', 'Overall 4');      /*Halloween Festival Costume (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053094', '4700', 'Overall 4');      /*Halloween Festival Costume (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1053095', '4700', 'Overall 4');      /*Bichon Outfit*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040001', '7400', 'Top');      /*Black Blazer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040005', '5000', 'Top');      /*Orange Baseball Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040027', '3200', 'Top');      /*Old School Blazer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040045', '3700', 'Top');      /*Red Rider*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040046', '2700', 'Top');      /*Shine Rider*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040047', '2700', 'Top');      /*Dark Rider*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040051', '7400', 'Top');      /*Blue Striped Trainer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040052', '5600', 'Top');      /*Green Striped Trainer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040053', '3700', 'Top');      /*Orange Striped Trainer*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040054', '5000', 'Top');      /*Green Disco Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040055', '4700', 'Top');      /*Orange Disco Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040056', '4300', 'Top');      /*Original Disco Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040064', '3200', 'Top');      /*Wild Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040065', '5600', 'Top');      /*Brown Wild Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040066', '3200', 'Top');      /*Red Wild Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040077', '2700', 'Top');      /*Cowboy Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040078', '4700', 'Top');      /*Pre-School Uniform Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040101', '4300', 'Top');      /*Skull T-Shirt*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040114', '6300', 'Top');      /*Hawaiian Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040119', '6800', 'Top');      /*Ragged Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040123', '3200', 'Top');      /*Prep School Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040124', '4700', 'Top');      /*Crusader T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040125', '6300', 'Top');      /*Military Cargo Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040126', '5600', 'Top');      /*Yellow Frill Sleeveless*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040127', '6800', 'Top');      /*Blue Heart Tanktop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040128', '5600', 'Top');      /*Blue Line Tanktop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040129', '5600', 'Top');      /*Red Casual Suit*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040130', '7400', 'Top');      /*Green Tie Casual Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040131', '4300', 'Top');      /*Pink Tie Casual Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040132', '3200', 'Top');      /*Palm Tree Tanktop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040133', '5600', 'Top');      /*Long Blue Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040134', '3700', 'Top');      /*Orange Puffy Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040135', '4300', 'Top');      /*Muscle Man T*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040137', '7400', 'Top');      /*Tania Tailored Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040138', '4300', 'Top');      /*Mercury Leather Jacket (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040139', '3200', 'Top');      /*Island Beads (M)*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040140', '6800', 'Top');      /*Pink Mimi Blouse*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040141', '6300', 'Top');      /*Blue Sailor Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040143', '5600', 'Top');      /*Pink Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040144', '2700', 'Top');      /*Bulletproof Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040148', '2700', 'Top');      /*Retro School Uniform Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040154', '3700', 'Top');      /*Pre-School Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040186', '3700', 'Top');      /*Cowboy Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040190', '6800', 'Top');      /*[MS Custom] Orange Striped Trainer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040191', '3700', 'Top');      /*[MS Custom] Orange Disco Shirt*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040192', '6300', 'Top');      /*Green Bunny T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040193', '3700', 'Top');      /*RED T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040194', '4300', 'Top');      /*Guys Pineapple Tank top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040195', '5000', 'Top');      /*Sleeveless Purple Mustache Shirt (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1040196', '5000', 'Top');      /*Smile Seed Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041000', '7400', 'Top');      /*Blue Frill Blouse*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041001', '7400', 'Top');      /*Blue Sailor Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041005', '5600', 'Top');      /*Pink Mimi Blouse*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041009', '4700', 'Top');      /*Red Sailor Shirt*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041070', '5600', 'Top');      /*Sky Blue Mimi Blouse*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041071', '3700', 'Top');      /*Yellow Mimi Blouse*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041072', '4300', 'Top');      /*Cowboy Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041073', '5600', 'Top');      /*Pre-School Uniform Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041090', '6300', 'Top');      /*Pink Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041104', '3700', 'Top');      /*Old School Uniform Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041108', '5600', 'Top');      /*SF Ninja Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041109', '4300', 'Top');      /*Red Trainer Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041110', '7400', 'Top');      /*Sky Blue Trainer Jacket*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041111', '5000', 'Top');      /*Pink Trainer Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041112', '7400', 'Top');      /*Black Trainer Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041113', '3200', 'Top');      /*Pink Frill Pajama Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041114', '4700', 'Top');      /*Hawaiian Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041125', '7400', 'Top');      /*Rainbow Knit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041126', '4700', 'Top');      /*Skull T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041127', '2700', 'Top');      /*Heart Sleeveless*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041128', '7400', 'Top');      /*Cross Sleeveless*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041129', '3200', 'Top');      /*Yellow Frill Camisole*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041130', '5600', 'Top');      /*Blue Frill Camisole*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041131', '2700', 'Top');      /*Pink Ribboned Janie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041132', '5600', 'Top');      /*Pink Frill Camisole*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041133', '3200', 'Top');      /*Grey Cardigan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041134', '2700', 'Top');      /*Angora Mustang*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041135', '2700', 'Top');      /*Tube-Top Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041136', '3700', 'Top');      /*Pink Vest Blouse*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041137', '4700', 'Top');      /*Pink-Dotted Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041138', '3700', 'Top');      /*Tania Bolero*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041139', '4700', 'Top');      /*Mercury Leather Jacket (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041140', '3200', 'Top');      /*Island Beads (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041142', '7400', 'Top');      /*Ribbon Frilled top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041143', '4700', 'Top');      /*Green Tie Casual Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041144', '4300', 'Top');      /*Retro School Uniform Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041146', '5000', 'Top');      /*Old School Blazer [F]*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041147', '7400', 'Top');      /*Muscle Man*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041156', '6800', 'Top');      /*Pre-School Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041189', '4300', 'Top');      /*Cowgirl Shirt*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041193', '7400', 'Top');      /*Tania Tailored Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041194', '4700', 'Top');      /*Pink Bunny T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041195', '6800', 'Top');      /*RED T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041196', '3700', 'Top');      /*Girls Pineapple Tank top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041197', '6800', 'Top');      /*Pink Mustache T-Shirt (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1041198', '3200', 'Top');      /*Smile Seed Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042000', '4300', 'Top');      /*Orange Hooded Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042001', '7400', 'Top');      /*Black Hooded Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042002', '5600', 'Top');      /*Red Hooded Vest*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042004', '3700', 'Top');      /*Pink Hooded Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042005', '6800', 'Top');      /*Pink Camping Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042006', '5600', 'Top');      /*Green Camping Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042007', '4300', 'Top');      /*Blue Camping Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042008', '2700', 'Top');      /*Wildcats Baseball Shirt (Basic)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042009', '4700', 'Top');      /*Baseball Shirt (Home)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042010', '3200', 'Top');      /*Baseball Shirt (Away)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042011', '4700', 'Top');      /*Wildcats Baseball Shirt (Alternate)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042012', '3200', 'Top');      /*Yellow Snowboard Top*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042013', '2700', 'Top');      /*Green Snowboard Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042014', '4700', 'Top');      /*Yellow Layered Combo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042015', '5600', 'Top');      /*Blue Layered Combo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042016', '6800', 'Top');      /*Pink Snowboard Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042017', '3700', 'Top');      /*Sky Blue Snowboard Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042018', '4300', 'Top');      /*Red T-Shirt w/ Heart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042019', '7400', 'Top');      /*M Layered T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042020', '5000', 'Top');      /*Old Military Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042021', '4700', 'Top');      /*Starry Layered Combo*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042022', '4300', 'Top');      /*Camouflaged Uniform*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042023', '4700', 'Top');      /*Blue Polka-Dot Pajama Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042024', '3700', 'Top');      /*Red Polka-Dot Pajama Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042025', '6800', 'Top');      /*Prisoner Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042026', '3200', 'Top');      /*Flowery Dress Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042027', '6300', 'Top');      /*Blue B-Ball Jersey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042028', '3700', 'Top');      /*Orange B-Ball Jersey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042029', '4300', 'Top');      /*Octopus T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042030', '6800', 'Top');      /*Slime T-Shirt*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042031', '2700', 'Top');      /*Orange Mushroom T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042032', '5000', 'Top');      /*Beetle Longsleeve*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042033', '5600', 'Top');      /*Beige Double-Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042034', '4700', 'Top');      /*Green Double-Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042035', '3700', 'Top');      /*Red Double-Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042036', '3200', 'Top');      /*Christmas Padded Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042037', '4700', 'Top');      /*Snowman Padded Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042038', '5000', 'Top');      /*Red Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042039', '4300', 'Top');      /*Sky Blue Allstar*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042040', '6800', 'Top');      /*Pink Allstar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042041', '6300', 'Top');      /*Black Allstar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042042', '7400', 'Top');      /*White Hooded Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042043', '5600', 'Top');      /*Green Striped Rugby Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042044', '3700', 'Top');      /*Pink Striped Rugby Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042045', '3200', 'Top');      /*Bowling Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042046', '3700', 'Top');      /*White Casual Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042047', '6800', 'Top');      /*Star-Patterned Yellow Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042048', '3700', 'Top');      /*Purple Star Shirt*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042049', '4300', 'Top');      /*Short Denim Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042050', '3700', 'Top');      /*Baseball Jumper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042051', '3700', 'Top');      /*Bomber Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042052', '6800', 'Top');      /*Blue Down Parka*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042053', '2700', 'Top');      /*Blue Wool Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042054', '4700', 'Top');      /*Pink Wool Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042055', '3200', 'Top');      /*Pink Down Parka*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042056', '5600', 'Top');      /*Beat Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042058', '5000', 'Top');      /*Red Half*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042059', '5600', 'Top');      /*Preppy Red and White*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042060', '5600', 'Top');      /*Pola Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042061', '3700', 'Top');      /*Ball Zone Jumper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042062', '5000', 'Top');      /*Stitched Leather Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042063', '5600', 'Top');      /*Red Turtleneck Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042064', '2700', 'Top');      /*Football Jersey (Home)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042065', '3700', 'Top');      /*Football Top (Away)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042066', '4700', 'Top');      /*Orange Hooded Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042067', '4300', 'Top');      /*Orange Hooded Zip-Up*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042068', '2700', 'Top');      /*Drill Muffler*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042069', '5000', 'Top');      /*Pink Big-Belt Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042070', '4300', 'Top');      /*Sky Blue Big-Belt Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042071', '6800', 'Top');      /*Pastel Layered Hooded Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042072', '5600', 'Top');      /*Red Layered Hooded Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042073', '4700', 'Top');      /*Navy Blue Dress Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042074', '6800', 'Top');      /*White Longsleeve With Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042075', '5000', 'Top');      /*Pink Pluto T*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042076', '3200', 'Top');      /*Dotted Disco Shirt*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042077', '6300', 'Top');      /*Rainbow T*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042078', '2700', 'Top');      /*White & Blue Sailor Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042080', '6300', 'Top');      /*Red Hot Racer T*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042081', '5600', 'Top');      /*Cherry Layered T*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042082', '7400', 'Top');      /*Black Cardigan Set*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042083', '6800', 'Top');      /*Rainbow Hooded Pancho*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042084', '6800', 'Top');      /*Army General Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042085', '6300', 'Top');      /*Canary Heart T*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042086', '6300', 'Top');      /*Tourist T*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042087', '6300', 'Top');      /*Skull Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042088', '5600', 'Top');      /*Black Skull Hooded Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042089', '6300', 'Top');      /*Blue Skull Hooded Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042090', '2700', 'Top');      /*Red Skull Hooded Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042091', '5000', 'Top');      /*Pink Skull Hooded Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042092', '4300', 'Top');      /*Pelvis Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042093', '6300', 'Top');      /*Pointed Double Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042094', '3200', 'Top');      /*Orange Snowflake Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042095', '4700', 'Top');      /*Vintage Hooded Shirt*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042096', '6300', 'Top');      /*M Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042097', '5600', 'Top');      /*Print Layered Hoody*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042098', '5000', 'Top');      /*Camo Hooded Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042099', '4700', 'Top');      /*Striped Hooded Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042100', '2700', 'Top');      /*Checkered Casual Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042101', '2700', 'Top');      /*Blanc Rose Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042102', '3700', 'Top');      /*Aqua Road T*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042103', '7400', 'Top');      /*White Outlaw Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042104', '2700', 'Top');      /*Lime Green Sleeveless*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042105', '3200', 'Top');      /*Crown Hooded T*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042106', '5000', 'Top');      /*Rainbow-Striped Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042107', '3200', 'Top');      /*Pink Flower T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042108', '6800', 'Top');      /*Purple Tank*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042109', '3200', 'Top');      /*Yellow & Red-Striped Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042110', '2700', 'Top');      /*Red Hooded Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042116', '4300', 'Top');      /*Orange Pea Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042117', '3700', 'Top');      /*Green Baseball Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042118', '5600', 'Top');      /*Red Checkered Shirt*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042119', '7400', 'Top');      /*Vintage Muffler Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042120', '4700', 'Top');      /*Celeste Blue Double Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042121', '6800', 'Top');      /*Opera Pink Double Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042122', '6300', 'Top');      /*Bowtie Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042125', '7400', 'Top');      /*Yellow Longsleeve with Bunny Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042126', '3200', 'Top');      /*Red and Black Blazer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042127', '7400', 'Top');      /*Green Suspenders*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042128', '2700', 'Top');      /*Apple-Green Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042129', '5000', 'Top');      /*"Black Tie Affair" Dress Shirt*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042130', '2700', 'Top');      /*Gold Chainz*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042131', '3200', 'Top');      /*Preppy Black Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042132', '5600', 'Top');      /*Aqua Green Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042133', '5000', 'Top');      /*Striped Hoodie Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042134', '5600', 'Top');      /*Yellow Shirt with Pads*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042135', '7400', 'Top');      /*Dark Master Sergeant for Transformation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042136', '4300', 'Top');      /*Red Legolesse for Transformation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042137', '6300', 'Top');      /*Dark Tech Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042138', '4700', 'Top');      /*The White Tee*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042140', '4700', 'Top');      /*Slick Agent Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042141', '5000', 'Top');      /*Pink Star Glow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042142', '6800', 'Top');      /*Rainbow Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042143', '3700', 'Top');      /*Disco Tank Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042144', '4700', 'Top');      /*Checkered Resort Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042145', '6800', 'Top');      /*Layered Duckie T*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042146', '4700', 'Top');      /*Superstar Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042147', '6300', 'Top');      /*Preppy Knit Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042149', '6800', 'Top');      /*80's Knit Pullover*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042150', '6300', 'Top');      /*Black "Hit Me" Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042151', '6300', 'Top');      /*Brown Argyle Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042152', '4700', 'Top');      /*Rainbow Knitted Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042153', '3700', 'Top');      /*Red Plaid Duffle Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042154', '4300', 'Top');      /*Bohemian Hooded Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042155', '2700', 'Top');      /*Sky Rider Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042156', '4300', 'Top');      /*Galaxy T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042157', '2700', 'Top');      /*Lovely Pink Heart T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042158', '6800', 'Top');      /*Baseball Classic*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042159', '5000', 'Top');      /*Animal One Piece*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042160', '4700', 'Top');      /*Navy Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042161', '4300', 'Top');      /*Yellow Spring Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042162', '2700', 'Top');      /*Blue-Striped Undershirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042163', '5600', 'Top');      /*Pink Heart T-Shirt & Muffler*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042164', '7400', 'Top');      /*Green Tie & Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042165', '7400', 'Top');      /*Pink Bowtie & White Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042166', '6800', 'Top');      /*Leather Biker Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042168', '6300', 'Top');      /*Lightning T-Shirt*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042169', '3200', 'Top');      /*Rainbow Tie-Dye Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042170', '6800', 'Top');      /*Cool Summer Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042171', '4700', 'Top');      /*Idol Star Vest*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042172', '4700', 'Top 2');      /*Preppy Blue Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042173', '6800', 'Top 2');      /*Green Polo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042174', '2700', 'Top 2');      /*Camping Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042176', '3200', 'Top 2');      /*I Love CN Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042177', '4300', 'Top 2');      /*Vintage Hoodie Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042178', '3700', 'Top 2');      /*Puppy Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042181', '5000', 'Top 2');      /*Napoleon Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042182', '7400', 'Top 2');      /*Denim Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042183', '7400', 'Top 2');      /*Pink Argyle Plaid*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042184', '6300', 'Top 2');      /*Tiger-Print Scarf & Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042185', '4300', 'Top 2');      /*JM's Street Gear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042186', '6300', 'Top 2');      /*Fur Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042187', '3200', 'Top 2');      /*Pink Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042188', '5600', 'Top 2');      /*Puffy Raglan Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042189', '5000', 'Top 2');      /*Lamb Wool Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042190', '2700', 'Top 2');      /*Dual-Color Heart Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042192', '2700', 'Top 2');      /*Green Tie Casual Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042193', '5000', 'Top 2');      /*Padded Vest*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042194', '6800', 'Top 2');      /*White Collared Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042198', '2700', 'Top 2');      /*Rainbow Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042199', '6800', 'Top 2');      /*Pink Smiley Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042200', '4300', 'Top 2');      /*Blue Smiley Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042202', '3700', 'Top 2');      /*Penguin Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042203', '7400', 'Top 2');      /*Orange Scarf Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042204', '5600', 'Top 2');      /*Hamburger Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042206', '6300', 'Top 2');      /*Black Rider Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042207', '7400', 'Top 2');      /*Star Trainer Jacket*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042208', '4300', 'Top 2');      /*Elephant Hoody*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042209', '4300', 'Top 2');      /*Mustang Vest Green Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042210', '5000', 'Top 2');      /*Mustang Vest Pink Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042212', '6300', 'Top 2');      /*Blue Spring Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042213', '6800', 'Top 2');      /*Pink Spring Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042214', '5600', 'Top 2');      /*Spring Sweater Set*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042215', '5000', 'Top 2');      /*Jester Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042216', '3200', 'Top 2');      /*Red Viva Baseball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042217', '3700', 'Top 2');      /*Black Viva Baseball*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042218', '4700', 'Top 2');      /*Raspberry Candy T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042219', '4300', 'Top 2');      /*Blue Stars T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042220', '6300', 'Top 2');      /*Shiny Training Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042221', '4300', 'Top 2');      /*Joyous 8th T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042222', '7400', 'Top 2');      /*Lemon Freshness*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042228', '2700', 'Top 2');      /*I Love SG Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042229', '7400', 'Top 2');      /*I Love MY Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042230', '5000', 'Top 2');      /*Cutie Raincoat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042232', '4300', 'Top 2');      /*Bat Costume Sweater*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042235', '3200', 'Top 2');      /*Rabbit Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042236', '2700', 'Top 2');      /*Green Apple Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042237', '3700', 'Top 2');      /*Gold Tailor Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042238', '7400', 'Top 2');      /*Pink Bunny Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042239', '2700', 'Top 2');      /*Cutie Raincoat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042240', '2700', 'Top 2');      /*Colorful T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042241', '7400', 'Top 2');      /*Flying Violet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042242', '7400', 'Top 2');      /*Summer Picnic*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042245', '4700', 'Top 2');      /*Hyper Spring Jealousy*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042246', '5600', 'Top 2');      /*Hyper Green Suspenders*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042247', '3700', 'Top 2');      /*[MS Custom] Baseball Shirt (Away)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042248', '4300', 'Top 2');      /*[MS Custom] Beetle Longsleeve*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042249', '6800', 'Top 2');      /*[MS Custom] Red Double-Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042250', '3700', 'Top 2');      /*Hyper Spring Sweater Set*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042251', '2700', 'Top 2');      /*Slither Style Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042252', '3200', 'Top 2');      /*Cute Sleeveless Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042259', '7400', 'Top 2');      /*Lemon Freshness*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042260', '4700', 'Top 2');      /*Loose Fit Sweater*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042261', '4300', 'Top 2');      /*Black Viva Baseball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042262', '6300', 'Top 2');      /*Colorful T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042263', '4700', 'Top 2');      /*Funky Jumper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042264', '2700', 'Top 2');      /*Colored Golf Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042265', '6800', 'Top 2');      /*Strawberry Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042266', '6800', 'Top 2');      /*Muscle Man*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042267', '7400', 'Top 2');      /*Exciting Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042268', '5600', 'Top 2');      /*Cutie Raincoat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042269', '4300', 'Top 2');      /*Ribbon Days*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042270', '6800', 'Top 2');      /*Pink Bunny Sweater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042271', '5600', 'Top 2');      /*Meow T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042272', '7400', 'Top 2');      /*Slick Agent Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042275', '6300', 'Top 2');      /*Frog Raindrop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042277', '4700', 'Top 2');      /*Star T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042278', '5600', 'Top 2');      /*Denim Hoodie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042279', '4300', 'Top 2');      /*Hun T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042280', '6800', 'Top 2');      /*Min T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042281', '4300', 'Top 2');      /*Jeong T-Shirt*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042282', '7400', 'Top 2');      /*Eum T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042285', '4300', 'Top 2');      /*Pastel Dot Tee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042286', '6300', 'Top 2');      /*Athletic Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042287', '6300', 'Top 2');      /*Red Check Rider*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042290', '3700', 'Top 2');      /*White Cherry Knit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042291', '5600', 'Top 2');      /*Vibrant Yellow Knit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042292', '4700', 'Top 2');      /*Banana Cardigan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042293', '3200', 'Top 2');      /*Guardian Clothing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042294', '4300', 'Top 2');      /*Thumping Heart Vest*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042311', '2700', 'Top 2');      /*Rainbow T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042312', '5000', 'Top 2');      /*Blue Mushroom T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042313', '6800', 'Top 2');      /*Full of Hearts T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042314', '6300', 'Top 2');      /*Rabbit and Bear Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042315', '4700', 'Top 2');      /*Bubbly Elephant Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1048002', '2700', 'Top 2');      /*Carrot T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042319', '6300', 'Top 2');      /*Hoi Poi T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042320', '6800', 'Top 2');      /*Island Travel T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042329', '4300', 'Top 2');      /*Sweet Summer Shirt*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042330', '5600', 'Top 2');      /*Charming Baby*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042332', '3700', 'Top 2');      /*Red Ribbon Kitty Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042333', '3200', 'Top 2');      /*Pink Kitty Sweatshirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042334', '3200', 'Top 2');      /*Green Kitty Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042335', '6300', 'Top 2');      /*Pink Marine T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042336', '4300', 'Top 2');      /*Corny Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042337', '5000', 'Top 2');      /*Teddy Picnic Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042338', '7400', 'Top 2');      /*Brown Teddy Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042339', '3700', 'Top 2');      /*White Kitty Pink Top*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042341', '5000', 'Top 2');      /*Hatchling T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042342', '6300', 'Top 2');      /*Rawrin' Tiger Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042343', '4300', 'Top 2');      /*Black Hip Hop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042344', '6300', 'Top 2');      /*Gold Fur-Lined Jacket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042345', '4300', 'Top 2');      /*Baby Ram Pullover (Blue)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042346', '6300', 'Top 2');      /*Baby Ram Pullover (Pink)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042347', '6300', 'Top 2');      /*Naughty Boy T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042348', '7400', 'Top 2');      /*Boldly Colored Polo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042349', '6300', 'Top 2');      /*All About Black*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1049000', '4300', 'Top 2');      /*Friendship Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042351', '4300', 'Top 2');      /*Hoya T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042351', '5600', 'Top 2');      /*Hoya T-shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042354', '4700', 'Top 2');      /*Duang Effect T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042355', '3700', 'Top 2');      /*Ranbingluan Effect T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042356', '5000', 'Top 2');      /*Chenghuiwan Effect T-Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042357', '4700', 'Top 2');      /*Cloud Prison*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042358', '3700', 'Top 2');      /*Soft Olive Knitwear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042359', '5000', 'Top 2');      /*Hyper Spring Sweater Set*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1042361', '6300', 'Top 2');      /*Red Cloud Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1048000', '2700', 'Top 2');      /*Couple Shirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1048001', '5000', 'Top 2');      /*Bunny Love T-Shirt*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060001', '6800', 'Bottom');      /*Black Suit Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060003', '5000', 'Bottom');      /*Military Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060034', '3200', 'Bottom');      /*Blue Rider Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060035', '2700', 'Bottom');      /*Shine Rider Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060036', '4700', 'Bottom');      /*Dark Rider Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060040', '5600', 'Bottom');      /*Blue Trainer Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060041', '6300', 'Bottom');      /*Green Trainer Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060042', '3200', 'Bottom');      /*Orange Trainer Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060047', '3200', 'Bottom');      /*Original Disco Pants*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060048', '4700', 'Bottom');      /*Green Disco Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060049', '6300', 'Bottom');      /*Blue Disco Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060053', '3700', 'Bottom');      /*Wild Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060054', '4300', 'Bottom');      /*Brown Wild Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060055', '4700', 'Bottom');      /*Red Wild Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060066', '3200', 'Bottom');      /*Cowboy Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060067', '3200', 'Bottom');      /*Pre-School Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060096', '5600', 'Bottom');      /*Old School Uniform Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060103', '7400', 'Bottom');      /*Hawaiian Skirt*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060108', '4300', 'Bottom');      /*Torn-Up Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060112', '3700', 'Bottom');      /*Prep School Uniform Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060113', '3200', 'Bottom');      /*Blue Leggings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060114', '5000', 'Bottom');      /*Washed Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060116', '6300', 'Bottom');      /*Military Cargo Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060117', '5000', 'Bottom');      /*Tropical Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060118', '5600', 'Bottom');      /*Orange Puffy Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060119', '2700', 'Bottom');      /*Denim Wrinkled Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060120', '3700', 'Bottom');      /*Tania Tartan Pants*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060121', '3700', 'Bottom');      /*Mercury Washed Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060122', '5000', 'Bottom');      /*Pink Miniskirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060123', '5600', 'Bottom');      /*Blue Sailor Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060125', '6800', 'Bottom');      /*Blue Skirt (m)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060126', '3200', 'Bottom');      /*Black Wakeboard Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060139', '5000', 'Bottom');      /*Retro School Uniform Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060145', '4700', 'Bottom');      /*Pre-School Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060174', '3200', 'Bottom');      /*Cowboy Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060178', '3700', 'Bottom');      /*[MS Custom] Orange Trainer Pants*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060179', '7400', 'Bottom');      /*Golf Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060180', '6800', 'Bottom');      /*Puffy Puff Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060181', '7400', 'Bottom');      /*Star Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060182', '4700', 'Bottom');      /*Golf Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060187', '4700', 'Bottom');      /*Green Rolled-Up Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060188', '6300', 'Bottom');      /*White Hot Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1060189', '6300', 'Bottom');      /*Smile Seed Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061000', '7400', 'Bottom');      /*Blue Bell Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061001', '3700', 'Bottom');      /*Blue Sailor Skirt*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061004', '5000', 'Bottom');      /*Pink Miniskirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061005', '6300', 'Bottom');      /*Roll-Up Jean*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061007', '6800', 'Bottom');      /*Red Sailor Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061065', '3200', 'Bottom');      /*Sky Blue Miniskirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061066', '7400', 'Bottom');      /*Yellow Mimi Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061067', '4700', 'Bottom');      /*Cowboy Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061068', '5000', 'Bottom');      /*Pre-School Uniform Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061072', '5600', 'Bottom');      /*Red Trainer Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061073', '7400', 'Bottom');      /*Sky Blue Trainer Pants*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061074', '3700', 'Bottom');      /*Pink Trainer Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061075', '5600', 'Bottom');      /*Black Trainer Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061089', '7400', 'Bottom');      /*Blue Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061103', '6300', 'Bottom');      /*Old School Uniform (Skirt)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061107', '2700', 'Bottom');      /*SF Ninja Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061108', '2700', 'Bottom');      /*Red Training Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061109', '7400', 'Bottom');      /*Sky Blue Training Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061110', '4700', 'Bottom');      /*Pink Training Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061111', '3700', 'Bottom');      /*Black Training Shorts*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061112', '5600', 'Bottom');      /*Pink Frill Pajama Bottom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061113', '7400', 'Bottom');      /*Hawaiian Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061124', '5000', 'Bottom');      /*Red Leggings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061126', '4300', 'Bottom');      /*Plitz Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061127', '6300', 'Bottom');      /*Blue Diamond Bootcuts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061128', '2700', 'Bottom');      /*Pink Diamond Bootcuts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061129', '6800', 'Bottom');      /*Butterfly Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061130', '3700', 'Bottom');      /*Green Long Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061131', '4700', 'Bottom');      /*Blue Slit Skirt*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061132', '4300', 'Bottom');      /*Skirt with Tights*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061133', '2700', 'Bottom');      /*Orange Long Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061134', '2700', 'Bottom');      /*Denim Miniskirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061135', '5000', 'Bottom');      /*Pink Layered Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061136', '4300', 'Bottom');      /*Long Khaki Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061137', '5600', 'Bottom');      /*Dark Denim Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061138', '3700', 'Bottom');      /*Pink Heart Hot Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061139', '5600', 'Bottom');      /*Military Cargo Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061140', '3700', 'Bottom');      /*Denim Skirt & Striped Sox*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061141', '7400', 'Bottom');      /*Tania Tartan Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061142', '5600', 'Bottom');      /*Mercury Jean Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061143', '5000', 'Bottom');      /*Amorian Pink Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061144', '6800', 'Bottom');      /*Blue Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061145', '4700', 'Bottom');      /*Retro School Uniform Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061147', '4300', 'Bottom');      /*Old School Uniform Pants (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061148', '2700', 'Bottom');      /*Pink Frill Swim Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061166', '5600', 'Bottom');      /*Pre-School Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061170', '6300', 'Bottom');      /*Bright Frilly Shorts*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061198', '4300', 'Bottom');      /*Cowgirl Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061203', '5000', 'Bottom');      /*Puffy Puff Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061204', '4300', 'Bottom');      /*Golf Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061206', '7400', 'Bottom');      /*Golf Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061207', '7400', 'Bottom');      /*Star Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061210', '6800', 'Bottom');      /*Check Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061211', '4300', 'Bottom');      /*Green Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061212', '4300', 'Bottom');      /*White Hot Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1061213', '6800', 'Bottom');      /*Smile Seed Skirt*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062003', '6800', 'Bottom');      /*Red Hip-Hop Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062005', '4300', 'Bottom');      /*Lined Hip-Hop Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062008', '5600', 'Bottom');      /*Pink Camping Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062009', '5600', 'Bottom');      /*Green Camping Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062010', '6300', 'Bottom');      /*Blue Camping Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062011', '7400', 'Bottom');      /*Wildcats Baseball Pants (Basic)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062012', '3200', 'Bottom');      /*Baseball Pants (Home)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062013', '3200', 'Bottom');      /*Baseball Pants (Away)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062014', '7400', 'Bottom');      /*Wildcats Baseball Pants (Alternate)*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062015', '7400', 'Bottom');      /*Ripped Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062016', '3700', 'Bottom');      /*Yellow Snowboard Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062017', '7400', 'Bottom');      /*Green Snowboard Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062018', '2700', 'Bottom');      /*Bell-Bottomed Faded Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062019', '6800', 'Bottom');      /*Pink Snowboard Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062020', '4300', 'Bottom');      /*Sky Blue Snowboard Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062021', '5600', 'Bottom');      /*Jean Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062022', '6300', 'Bottom');      /*Old Army Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062023', '6300', 'Bottom');      /*Baggy Jeans*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062024', '6800', 'Bottom');      /*Camouflaged Army Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062025', '2700', 'Bottom');      /*Blue Polka-Dot Pajama Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062026', '3700', 'Bottom');      /*Red Polka-Dot Pajama Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062027', '4700', 'Bottom');      /*Prisoner Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062028', '4700', 'Bottom');      /*Picnic Jean Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062029', '7400', 'Bottom');      /*Blue B-Ball Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062030', '5600', 'Bottom');      /*Orange B-Ball Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062031', '3200', 'Bottom');      /*Checkered Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062032', '3200', 'Bottom');      /*Cargo Pants*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062033', '5600', 'Bottom');      /*Red Checkered Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062034', '5600', 'Bottom');      /*White Checkered Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062035', '4700', 'Bottom');      /*Bone Buckled Slacks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062038', '5600', 'Bottom');      /*Hip Hop Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062039', '7400', 'Bottom');      /*White Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062040', '3700', 'Bottom');      /*Washed Denim Cargos*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062041', '3700', 'Bottom');      /*Denim Cargos*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062042', '3200', 'Bottom');      /*Jeans with Chain*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062043', '4700', 'Bottom');      /*Black Leather Pants*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062044', '4300', 'Bottom');      /*Red Starrium*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062045', '3200', 'Bottom');      /*Patched Denim Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062046', '6300', 'Bottom');      /*Vintage Pocket Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062047', '6300', 'Bottom');      /*Brisk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062048', '4700', 'Bottom');      /*Brown Checkered Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062049', '5600', 'Bottom');      /*Football Pants (Home)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062050', '3700', 'Bottom');      /*Football Bottom (Away)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062051', '2700', 'Bottom');      /*All-Star Blue Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062052', '3200', 'Bottom');      /*White Faded Jeans*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062053', '3200', 'Bottom');      /*Pink-Lined Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062054', '6300', 'Bottom');      /*Busy Bee Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062055', '5000', 'Bottom');      /*Jailbird Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062056', '4700', 'Bottom');      /*Military Cargo Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062057', '2700', 'Bottom');      /*Scottish Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062058', '6800', 'Bottom');      /*Inferno Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062059', '4300', 'Bottom');      /*Vintage Black Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062060', '3700', 'Bottom');      /*Blue Skinny Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062061', '7400', 'Bottom');      /*Olive Skinny Jeans*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062062', '3200', 'Bottom');      /*Red Wine Skinny Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062063', '5000', 'Bottom');      /*Dark Rocker Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062064', '5000', 'Bottom');      /*Checks Point Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062065', '3700', 'Bottom');      /*White-Striped Trainer Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062066', '5000', 'Bottom');      /*Vintage Sky Blue Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062067', '2700', 'Bottom');      /*Summer Capris*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062068', '6800', 'Bottom');      /*Rainbow Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062069', '5000', 'Bottom');      /*Brown Chained Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062070', '5600', 'Bottom');      /*Painted Blue Jeans*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062071', '3200', 'Bottom');      /*Low-Rise Ripped Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062072', '7400', 'Bottom');      /*Relaxed Fit Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062073', '7400', 'Bottom');      /*Olive Pumpkin Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062074', '3200', 'Bottom');      /*Brown Pumpkin Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062075', '4300', 'Bottom');      /*Vintage Black Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062076', '3700', 'Bottom');      /*Light Blue Ripped Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062077', '6300', 'Bottom');      /*Brown Bubble Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062080', '7400', 'Bottom');      /*Amorian Pink Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062081', '2700', 'Bottom');      /*Bunny-Padded Snowboard Pants*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062082', '6800', 'Bottom');      /*Red and Black Warm-ups*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062083', '3700', 'Bottom');      /*Brown Pocket Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062084', '4300', 'Bottom');      /*Jewel Chain Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062085', '5000', 'Bottom');      /*"Black Tie Affair" Dress Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062086', '4700', 'Bottom');      /*Dark Master Sergeant Skirt for Transformation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062087', '5600', 'Bottom');      /*Red Legolia Pants for Transformation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062088', '5600', 'Bottom');      /*Dark Night Pants for Transformation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062089', '6300', 'Bottom');      /*Pink Heart Boxers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062091', '6800', 'Bottom');      /*Black Checkered Shorts*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062092', '5600', 'Bottom');      /*Pink 80s Slacks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062093', '2700', 'Bottom');      /*Moss Green Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062094', '3700', 'Bottom');      /*Ruby-Buckled Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062095', '5600', 'Bottom');      /*Milan Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062096', '4700', 'Bottom');      /*Practical Linen Trousers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062097', '4300', 'Bottom');      /*Ella Blue Denim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062098', '2700', 'Bottom');      /*Aqua Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062100', '3700', 'Bottom');      /*Rolled-Up Baggy Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062101', '7400', 'Bottom');      /*Rolled-Up Skinny Jeans*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062102', '5600', 'Bottom');      /*Twinkle Star Blue Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062103', '6800', 'Bottom');      /*Baggy Glow-in-the-Dark Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062104', '6800', 'Bottom');      /*Dark Purple Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062105', '5600', 'Bottom');      /*Plaid Roll-Up Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062106', '4300', 'Bottom');      /*Bunny Frill Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062107', '4300', 'Bottom');      /*Shooting Star Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062108', '7400', 'Bottom');      /*Vintage Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062109', '3700', 'Bottom');      /*Neon Skinny Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062110', '7400', 'Bottom');      /*Baby Pink Pants*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062111', '5600', 'Bottom');      /*Blue Ribbon Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062112', '4300', 'Bottom');      /*Underpants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062113', '4300', 'Bottom');      /*Crayon Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062114', '3700', 'Bottom');      /*Pink Heart Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062116', '6800', 'Bottom');      /*Star Beach Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062117', '3700', 'Bottom');      /*Idol Star Chain Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062118', '6300', 'Bottom');      /*Stone Washed Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062119', '5000', 'Bottom');      /*Technicolour Funky Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062121', '3200', 'Bottom');      /*Tiger-Print Leggings*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062122', '6800', 'Bottom');      /*Plaid-Cuffed Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062123', '3700', 'Bottom');      /*High-Rider*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062124', '6300', 'Bottom');      /*Saruel Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062126', '4700', 'Bottom');      /*Pink Sprite Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062129', '6300', 'Bottom');      /*Red Spotted Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062130', '3700', 'Bottom');      /*Blue Spotted Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062131', '3200', 'Bottom');      /*White Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062133', '3200', 'Bottom');      /*Star Trainer Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062134', '7400', 'Bottom');      /*Super Pop Shorts*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062135', '7400', 'Bottom');      /*Shiny Gold Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062136', '3700', 'Bottom');      /*Layered Denim Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062137', '7400', 'Bottom');      /*Plum Sherbet Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062138', '4700', 'Bottom');      /*Mint Sherbet Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062139', '5600', 'Bottom');      /*Deep Blue Sea Knee Socks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062145', '4700', 'Bottom');      /*Funky Xylophone Leggings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062147', '7400', 'Bottom');      /*Sky Rainbow Shorts [temp]*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062151', '2700', 'Bottom');      /*Rabbit Bottom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062152', '6800', 'Bottom');      /*Neon Pink Pants*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062153', '6300', 'Bottom');      /*Vacation Denim Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062154', '6800', 'Bottom');      /*Layered Denim Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062155', '3200', 'Bottom');      /*Oceanic Sandblasted Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062156', '4700', 'Bottom');      /*Mosaic Purple*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062157', '3700', 'Bottom');      /*Chocolate Strawberry Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062159', '4700', 'Bottom');      /*Hyper Chocolate Strawberry Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062160', '6300', 'Bottom');      /*Hyper Funky Xylophone Leggings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062161', '4300', 'Bottom');      /*[MS Custom] Red Checkered Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062162', '2700', 'Bottom');      /*Hyper Deep Blue Sea Knee Socks*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062163', '5000', 'Bottom');      /*Slither Style Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062170', '6800', 'Bottom');      /*Aqua Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062171', '4300', 'Bottom');      /*Stocking Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062172', '4300', 'Bottom');      /*Checkered Tights*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062173', '3200', 'Bottom');      /*Funky Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062174', '7400', 'Bottom');      /*Hearts Tights*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062175', '3200', 'Bottom');      /*Pink Skinny Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062176', '2700', 'Bottom');      /*Plum Sherbet Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062179', '5600', 'Bottom');      /*Little Bunny Pants*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062182', '5000', 'Bottom');      /*Sapphire Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062183', '2700', 'Bottom');      /*Hot Pink Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062184', '5000', 'Bottom');      /*Cargo Hiphop Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062185', '5000', 'Bottom');      /*Violet Dot Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062188', '6300', 'Bottom');      /*Roll-Up Jean*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062189', '5600', 'Bottom');      /*Guardian Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062203', '3700', 'Bottom');      /*Otherworldly Slacks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062204', '3200', 'Bottom');      /*Rainbow Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062207', '3700', 'Bottom');      /*Hoi Poi Shorts*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062208', '7400', 'Bottom');      /*Bunny Patch Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062209', '3200', 'Bottom');      /*Mini Bunny Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062210', '2700', 'Bottom');      /*Isand Travel Shorts*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062211', '3200', 'Bottom 2');      /*Sweet Summer Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062212', '2700', 'Bottom 2');      /*Heart Hot Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062213', '2700', 'Bottom 2');      /*Baby Purple Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062214', '5600', 'Bottom 2');      /*Teddy Hip Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062216', '5600', 'Bottom 2');      /*Mismatched Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062217', '4700', 'Bottom 2');      /*Polka-Dot A Line Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062218', '2700', 'Bottom 2');      /*Green Speckled Sweatpants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062219', '4700', 'Bottom 2');      /*Colorful Blue Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062220', '3700', 'Bottom 2');      /*White Shorts*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062221', '7400', 'Bottom 2');      /*Teddy Picnic Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062222', '5000', 'Bottom 2');      /*Brown Teddy Capris Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062223', '4700', 'Bottom 2');      /*Pink Kitty Denim Skirt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062225', '7400', 'Bottom 2');      /*Heart Patch Knit Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062226', '3200', 'Bottom 2');      /*Rawrin' Tiger Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062228', '6300', 'Bottom 2');      /*White Rainbow Leggings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062229', '3200', 'Bottom 2');      /*Naughty Boy Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062230', '2700', 'Bottom 2');      /*White Rainbow Leggings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062231', '4700', 'Bottom 2');      /*All About Jeans*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062232', '5600', 'Bottom 2');      /*Hoya Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062232', '3700', 'Bottom 2');      /*Hoya Shorts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062233', '4300', 'Bottom 2');      /*Dark Slate Jeans*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062234', '3200', 'Bottom 2');      /*Saggy Pants*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062235', '3200', 'Bottom 2');      /*Red Cloud Bottom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1062236', '6800', 'Bottom 2');      /*Red Cloud Bottom*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070000', '3200', 'Shoes');      /*Blue Gomushin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070001', '4300', 'Shoes');      /*Black Santa Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070002', '3700', 'Shoes');      /*Kimono Shoes (M)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070003', '7400', 'Shoes');      /*Black Shoes of Death*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070004', '3200', 'Shoes');      /*Blue Western Walkers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070005', '5600', 'Shoes');      /*Santa Boy Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070006', '4300', 'Shoes');      /*Royal Costume Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070007', '3200', 'Shoes');      /*Lunar Celebration Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070008', '7400', 'Shoes');      /*Korean Martial Arts Shoes*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070009', '2700', 'Shoes');      /*Paris Wingtips*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070014', '6800', 'Shoes');      /*Veras Heels [m]*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070015', '5000', 'Shoes');      /*Bunny Boots [m]*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070016', '3200', 'Shoes');      /*Dandy Silver Sneaks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070018', '6300', 'Shoes');      /*Napoleon Shoes */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070019', '4700', 'Shoes');      /*Napoleon Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070020', '5600', 'Shoes');      /*Twinkling Boy Glow Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070024', '3200', 'Shoes');      /*Garnet-Studded Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070028', '6800', 'Shoes');      /*Evergreen Magistrate Pretty Shoes*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070031', '4700', 'Shoes');      /*Alps Boy Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070057', '4300', 'Shoes');      /*Shadow Sandals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070059', '5600', 'Shoes');      /*Rainbow Picnic Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070060', '4700', 'Shoes');      /*[[FROZEN CONTENT]] Kristoff Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070061', '2700', 'Shoes');      /*Glass Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070064', '5600', 'Shoes');      /*Mad Doctor Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070065', '6800', 'Shoes');      /*Blue Macaron Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070066', '7400', 'Shoes');      /*Santa Boy Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070067', '6800', 'Shoes');      /*Cozy Snow Flower*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070068', '2700', 'Shoes');      /*The Kingdom Dress Shoes of King*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070069', '3700', 'Shoes');      /*Soaring Sky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070070', '2700', 'Shoes');      /*Yeonhwa School Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070071', '3200', 'Shoes');      /*Mr. Time Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070072', '7400', 'Shoes');      /*Cutie Farmer Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070073', '2700', 'Shoes');      /*Bloody Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070075', '2700', 'Shoes');      /*Time Master Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070076', '4700', 'Shoes');      /*Red Santa Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070077', '6300', 'Shoes');      /*Mr. Time Shoes*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1070078', '4700', 'Shoes');      /*Concert Muse Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071000', '2700', 'Shoes');      /*Blue Loose Sox*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071001', '6800', 'Shoes');      /*Red Loose Sox*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071002', '5600', 'Shoes');      /*Blue Gomushin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071003', '5000', 'Shoes');      /*Red Santa Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071004', '5000', 'Shoes');      /*Pink Nurse Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071005', '3700', 'Shoes');      /*White Nurse Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071006', '5600', 'Shoes');      /*SF Ninja Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071007', '4300', 'Shoes');      /*Bunny Boots*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071008', '4300', 'Shoes');      /*Kimono Shoes (F)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071009', '4700', 'Shoes');      /*Blue Western Walkers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071010', '3200', 'Shoes');      /*Sea Queen Sandals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071011', '7400', 'Shoes');      /*Race Queen Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071012', '6800', 'Shoes');      /*Diao Chan Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071013', '2700', 'Shoes');      /*White Cat Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071014', '4300', 'Shoes');      /*Black Cat Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071015', '6800', 'Shoes');      /*Maid Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071016', '7400', 'Shoes');      /*Santa Girl Boots*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071017', '5600', 'Shoes');      /*Leopard Print Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071018', '6800', 'Shoes');      /*Brown Leather Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071019', '6300', 'Shoes');      /*Lunar Celebration Pumps*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071020', '4300', 'Shoes');      /*Veras Heels*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071021', '5600', 'Shoes');      /*Gothic Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071024', '6800', 'Shoes');      /*Black Dress Shoes [f]*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071025', '3200', 'Shoes');      /*Paris Wingtips [F]*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071026', '4700', 'Shoes');      /*White High Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071030', '3700', 'Shoes');      /*Twinkling Girl Glow Shoes*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071031', '5600', 'Shoes');      /*Pink Angel Wing Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071032', '7400', 'Shoes');      /*Red Ribbon Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071036', '7400', 'Shoes');      /*Garnet-Studded Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071037', '4700', 'Shoes');      /*Cygnus Sandals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071040', '3200', 'Shoes');      /*Red Ribbon Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071044', '7400', 'Shoes');      /*Pinky Pretty Gomushin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071048', '6800', 'Shoes');      /*Alps Girl Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071074', '6800', 'Shoes');      /*Shadow Garter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071076', '2700', 'Shoes');      /*Colorful Picnic Shoes*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071077', '2700', 'Shoes');      /*[[FROZEN CONTENT]] Elsa Heels*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071078', '4300', 'Shoes');      /*Glass Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071080', '3700', 'Shoes');      /*Ribbon Angel Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071081', '3700', 'Shoes');      /*Pink Macaron Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071082', '5600', 'Shoes');      /*Santa Girl Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071083', '3200', 'Shoes');      /*Cozy Snow Flower*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071084', '6300', 'Shoes');      /*The Kingdom Blue Heels of Queen*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071085', '4700', 'Shoes');      /*Soaring Cloud*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071087', '6300', 'Shoes');      /*Yeonhwa School Shoes*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071088', '7400', 'Shoes');      /*Ms. Time Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071089', '3700', 'Shoes');      /*Pure Farmer Sandals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071090', '7400', 'Shoes');      /*Bloody Heels*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071092', '6300', 'Shoes');      /*Time Mistress Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071093', '4700', 'Shoes');      /*Red Santa Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071094', '3700', 'Shoes');      /*Ms. Time Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1071095', '6800', 'Shoes');      /*Concert Muse Heels*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072010', '6800', 'Shoes');      /*Black Dress Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072013', '6800', 'Shoes');      /*Red Air H's*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072014', '4300', 'Shoes');      /*Camping Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072057', '4300', 'Shoes');      /*Blue Air H's*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072058', '3200', 'Shoes');      /*Black Air H's*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072088', '6800', 'Shoes');      /*Cowboy Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072092', '6800', 'Shoes');      /*Yellow Flippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072093', '3200', 'Shoes');      /*Blue Flippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072094', '6300', 'Shoes');      /*Yellow Rain Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072095', '5600', 'Shoes');      /*Sky Blue Rain Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072096', '5600', 'Shoes');      /*Red Rain Boots*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072097', '5000', 'Shoes');      /*Green Rain Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072098', '6300', 'Shoes');      /*Blue Baseball Cleats*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072099', '3700', 'Shoes');      /*Red Baseball Cleats*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072100', '2700', 'Shoes');      /*Black Baseball Cleats*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072111', '6300', 'Shoes');      /*Black Leather Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072153', '4700', 'Shoes');      /*Transparent Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072175', '5000', 'Shoes');      /*Ninja Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072176', '5600', 'Shoes');      /*Military Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072180', '4300', 'Shoes');      /*Flipper Boots*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072181', '5600', 'Shoes');      /*Green Ting Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072186', '5000', 'Shoes');      /*Gold Kitty Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072187', '4700', 'Shoes');      /*Blue Marble Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072188', '4300', 'Shoes');      /*Red Marble Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072189', '4700', 'Shoes');      /*Bunny Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072190', '4300', 'Shoes');      /*Blue B-ball Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072191', '3700', 'Shoes');      /*Orange B-ball Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072199', '7400', 'Shoes');      /*Ragged Gomushin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072200', '5000', 'Shoes');      /*Brown Dress Shoes*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072201', '5600', 'Shoes');      /*Red Leather Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072202', '4700', 'Shoes');      /*Mesoranger Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072217', '4700', 'Shoes');      /*Beige Golashes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072218', '4300', 'Shoes');      /*Sky Blue Golashes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072219', '5000', 'Shoes');      /*Pink Golashes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072230', '7400', 'Shoes');      /*Black Boxing Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072231', '3200', 'Shoes');      /*Blue Boxing Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072232', '6300', 'Shoes');      /*Red Boxing Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072233', '6800', 'Shoes');      /*Bear Shoes*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072234', '2700', 'Shoes');      /*Bubbling Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072235', '4700', 'Shoes');      /*Slime Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072236', '7400', 'Shoes');      /*Guan Yu Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072237', '6800', 'Shoes');      /*Zhu-Ge-Liang Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072240', '5000', 'Shoes');      /*Big Rabbit Feet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072241', '2700', 'Shoes');      /*Liu Bei Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072242', '6300', 'Shoes');      /*Cao Cao Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072243', '6300', 'Shoes');      /*Sun Quan Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072244', '5000', 'Shoes');      /*Red Enamel Shoes*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072245', '6800', 'Shoes');      /*Blue Enamel Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072246', '6800', 'Shoes');      /*Pink Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072247', '4700', 'Shoes');      /*Hunting Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072250', '6300', 'Shoes');      /*Horoscope Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072251', '6300', 'Shoes');      /*Pro-Cat Sticker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072252', '5600', 'Shoes');      /*Snowboard Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072253', '6300', 'Shoes');      /*Red Santa Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072254', '5600', 'Shoes');      /*Football Cleats (Home)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072255', '4700', 'Shoes');      /*Football Cleats (Away)*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072256', '6800', 'Shoes');      /*Teddy Bear Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072257', '4300', 'Shoes');      /*Puppy Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072258', '6300', 'Shoes');      /*Gray Kitty Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072259', '6800', 'Shoes');      /*Chick Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072260', '5600', 'Shoes');      /*Penguin Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072265', '2700', 'Shoes');      /*Blue Soccer Cleats*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072266', '3200', 'Shoes');      /*Black Soccer Cleats*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072267', '5000', 'Shoes');      /*Red Soccer Cleats*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072270', '2700', 'Shoes');      /*White Rabbit Shoes*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072271', '5600', 'Shoes');      /*Black Cat Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072274', '3200', 'Shoes');      /*Moon Bunny Paws*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072276', '4300', 'Shoes');      /*Booster Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072277', '6300', 'Shoes');      /*Red Elf shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072278', '6800', 'Shoes');      /*Rudolph Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072279', '5000', 'Shoes');      /*Super Booster Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072280', '4300', 'Shoes');      /*Golden Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072281', '5600', 'Shoes');      /*Sachiel Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072282', '5000', 'Shoes');      /*Veamoth Shoes*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072283', '6300', 'Shoes');      /*Janus Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072284', '6800', 'Shoes');      /*Zhu Ba Jie Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072322', '6300', 'Shoes');      /*Rollerskates*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072323', '5600', 'Shoes');      /*Starry Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072324', '7400', 'Shoes');      /*Piggy Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072325', '4700', 'Shoes');      /*Red Slime Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072326', '2700', 'Shoes');      /*Yellow Slime Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072327', '3700', 'Shoes');      /*Tania En Fuego*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072328', '2700', 'Shoes');      /*Mercury Lightning*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072329', '6300', 'Shoes');      /*Flipped Blue High Top*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072330', '4300', 'Shoes');      /*Black Classic Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072331', '4300', 'Shoes');      /*Velcro High Tops*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072332', '5000', 'Shoes');      /*Black Enamel Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072333', '3200', 'Shoes');      /*Green Classic Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072334', '4700', 'Shoes');      /*Red Checkered Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072335', '4300', 'Shoes');      /*Natural Golashes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072336', '6300', 'Shoes');      /*Soccer Cleats*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072337', '5000', 'Shoes');      /*Fluffy Slippers*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072341', '2700', 'Shoes');      /*Orange Sneakz*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072347', '4300', 'Shoes');      /*Olive Green Kicks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072348', '6800', 'Shoes');      /*Elephant Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072349', '2700', 'Shoes');      /*Green Sneakz*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072350', '3700', 'Shoes');      /*Black High Tops*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072351', '2700', 'Shoes');      /*Green Ankle Boots for Transformation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072352', '5600', 'Shoes');      /*Red Silky Boots for Transformation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072353', '4700', 'Shoes');      /*White Ninja Sandals for Transformation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072354', '7400', 'Shoes');      /*Black Voyson Shoes for Transformation*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072367', '5600', 'Shoes');      /*Cutie Birk Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072370', '2700', 'Shoes');      /*Gaga Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072371', '3200', 'Shoes');      /*Custom Blue High Tops*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072373', '4700', 'Shoes');      /*Purple Rainbow Sneaks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072374', '6300', 'Shoes');      /*Lace Long Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072377', '6800', 'Shoes');      /*Treacherous Wolf Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072379', '4700', 'Shoes');      /*Yellow Rainbow Sneaks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072380', '4700', 'Shoes');      /*White & Blue Sandals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072381', '6800', 'Shoes');      /*Aran Combat Shoes*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072382', '5000', 'Shoes');      /*Brave Soldier Shoes */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072384', '5600', 'Shoes');      /*Bling Bling Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072385', '3700', 'Shoes');      /*White Slipshoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072386', '4300', 'Shoes');      /*Black Geda*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072387', '6800', 'Shoes');      /*Pink Geda*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072388', '4700', 'Shoes');      /*Stripe Knee Socks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072389', '3700', 'Shoes');      /*Black Platform Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072392', '6800', 'Shoes');      /*Red Ankle-Strap Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072393', '6800', 'Shoes');      /*We Care! Shoes*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072394', '5000', 'Shoes');      /*Pink Polka-Dotted Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072395', '3200', 'Shoes');      /*Mix-n-Match Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072396', '3200', 'Shoes');      /*Gaga Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072397', '2700', 'Shoes');      /*Idol Star Snickers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072398', '5600', 'Shoes');      /*Cursed Golden shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072404', '4300', 'Shoes');      /*Alchemist Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072405', '3200', 'Shoes');      /*Ninja Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072406', '5600', 'Shoes');      /*Chaos Metallic Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072407', '6300', 'Shoes');      /*Kawaii Kitty Shoes*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072408', '3700', 'Shoes');      /*Maple Racing Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072410', '7400', 'Shoes');      /*Super Booster Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072417', '3700', 'Shoes');      /*Clown Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072425', '3700', 'Shoes');      /*Freud's Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072426', '5600', 'Shoes');      /*Shiny Anklet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072433', '6800', 'Shoes');      /*Passionate Flats*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072437', '5000', 'Shoes');      /*Pink Bean Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072438', '7400', 'Shoes');      /*Green Leaf Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072439', '4300', 'Shoes');      /*Strawberry Shoes*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072440', '3200', 'Shoes');      /*Cat Set Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072441', '4700', 'Shoes');      /*Dual Blade Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072443', '5000', 'Shoes');      /*Evan Golden Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072444', '5600', 'Shoes');      /*Hawkeye Ocean Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072448', '5600', 'Shoes');      /*Rainbow Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072454', '6800', 'Shoes');      /*Oz Magic Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072456', '3700', 'Shoes');      /*Evan Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072457', '2700', 'Shoes');      /*Blue Slip-Ons*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072461', '6300', 'Shoes');      /*Battle Mage Boots*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072462', '3200', 'Shoes');      /*Wild Hunter Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072464', '4700', 'Shoes');      /*Combat Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072465', '3700', 'Shoes');      /*King Crow Kimono Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072466', '6300', 'Shoes');      /*Henesys Academy Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072467', '3200', 'Shoes');      /*Pilot Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072468', '4300', 'Shoes');      /*Lolita Knee Socks Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072469', '5600', 'Shoes');      /*Striped Leggings (Pink)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072470', '5600', 'Shoes');      /*Striped Leggings (Blue)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072478', '6800', 'Shoes');      /*Brown Ankle Boots*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072482', '3200', 'Shoes');      /*Panda Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072483', '4300', 'Shoes');      /*White Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072484', '3200', 'Shoes');      /*Black Kitty Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072495', '5600', 'Shoes');      /*Blue Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072507', '4300', 'Shoes');      /*Pearl Anklet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072508', '2700', 'Shoes');      /*Winter 2010 Moon Bunny Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072509', '5600', 'Shoes');      /*Red's Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072514', '5600', 'Shoes');      /*Pink Winged Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072515', '4700', 'Shoes');      /*Furry Lion Slippers*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072516', '4700', 'Shoes');      /*Rookie Chick Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072517', '4700', 'Shoes');      /*Winged Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072520', '6800', 'Shoes');      /*6th Anniversary Item*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072529', '6800', 'Shoes 2');      /*Pink Elephant Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072531', '5600', 'Shoes 2');      /*Koala Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072532', '5000', 'Shoes 2');      /*MSE 4 Years & Unstoppable Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072536', '3200', 'Shoes 2');      /*Starling Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072537', '5600', 'Shoes 2');      /*Crow Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072609', '5000', 'Shoes 2');      /*Ribboned Justice Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072613', '6800', 'Shoes 2');      /*Western Cowboy Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072622', '5600', 'Shoes 2');      /*Orchid's Black Wing Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072627', '3200', 'Shoes 2');      /*Dark Force Boots */
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072628', '4300', 'Shoes 2');      /*Elven Spirit Boots */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072631', '6800', 'Shoes 2');      /*Urban Pirate Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072632', '6800', 'Shoes 2');      /*Nyanya Steward Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072633', '3200', 'Shoes 2');      /*GM Haku's Pirate Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072637', '2700', 'Shoes 2');      /*Hades Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072646', '3700', 'Shoes 2');      /*Elven Spirit Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072647', '5600', 'Shoes 2');      /*Kerning Engineering School Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072648', '3700', 'Shoes 2');      /*Ellinia Magic School Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072649', '7400', 'Shoes 2');      /*Mu Lung Dojo Training Shoes*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072650', '6800', 'Shoes 2');      /*Blue Dragon Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072651', '2700', 'Shoes 2');      /*Red Dragon Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072652', '5000', 'Shoes 2');      /*Intergalactic Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072658', '4700', 'Shoes 2');      /*Glowing Foot Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072662', '5600', 'Shoes 2');      /*Lucia Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072663', '5000', 'Shoes 2');      /*GM Nori's Wing Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072676', '3700', 'Shoes 2');      /*The Onmyouji's Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072680', '2700', 'Shoes 2');      /*Blue Arabian Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072681', '5600', 'Shoes 2');      /*Red Arabian Shoes*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072708', '6800', 'Shoes 2');      /*Cool Summer Flippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072729', '4700', 'Shoes 2');      /*Jett's Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072742', '6300', 'Shoes 2');      /*Nero Paws*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072748', '5600', 'Shoes 2');      /*Exotic Festival Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072749', '3700', 'Shoes 2');      /*Bubble Bubble Chocolate Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072750', '3200', 'Shoes 2');      /*The Bladed Falcon's Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072756', '5000', 'Shoes 2');      /*Hyper Kitten Mittens*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072757', '2700', 'Shoes 2');      /*[MS Custom] Red Rain Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072758', '5600', 'Shoes 2');      /*Kitty Slippers*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072759', '3700', 'Shoes 2');      /*Chick Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072760', '4300', 'Shoes 2');      /*Halloween Leopard Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072770', '2700', 'Shoes 2');      /*Dark Devil Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072771', '6800', 'Shoes 2');      /*Slither Style High-Tops*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072772', '6800', 'Shoes 2');      /*Pious Shaman Stockings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072773', '6300', 'Shoes 2');      /*Red Strap Clogs*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072778', '6800', 'Shoes 2');      /*Dark Force Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072779', '4300', 'Shoes 2');      /*Featherly Angel Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072780', '5600', 'Shoes 2');      /*Blue Point Kitty Shoes*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072781', '3200', 'Shoes 2');      /*Kitty Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072782', '2700', 'Shoes 2');      /*Xenon Neo-Tech Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072783', '3700', 'Shoes 2');      /*Lotus's Black Wing Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072791', '3700', 'Shoes 2');      /*Green Dinosaur Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072800', '7400', 'Shoes 2');      /*Mid High Golf Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072803', '6300', 'Shoes 2');      /*Purple Dinosaur Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072808', '5000', 'Shoes 2');      /*Ramling Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072809', '4300', 'Shoes 2');      /*Kerning Technical High Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072810', '4300', 'Shoes 2');      /*Ellinia Magic Academy Shoes*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072811', '2700', 'Shoes 2');      /*Mu Lung Academy Training Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072812', '4700', 'Shoes 2');      /*Kimono Sandals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072813', '3700', 'Shoes 2');      /*Kimono Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072816', '6800', 'Shoes 2');      /*Succubus Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072817', '3700', 'Shoes 2');      /*Blavy Angel Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072820', '2700', 'Shoes 2');      /*Funky Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072821', '6300', 'Shoes 2');      /*Golden Bell Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072823', '4700', 'Shoes 2');      /*Golf Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072824', '5000', 'Shoes 2');      /*Angel Wing Shoes*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072830', '5600', 'Shoes 2');      /*GM Daejang's Lucia Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072831', '3200', 'Shoes 2');      /*Flame Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072832', '7400', 'Shoes 2');      /*Pink Bean Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072836', '6800', 'Shoes 2');      /*Baseball Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072838', '4300', 'Shoes 2');      /*Panda Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072839', '7400', 'Shoes 2');      /*Shoes of Life*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072840', '6800', 'Shoes 2');      /*Shoes of Destruction*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072843', '4300', 'Shoes 2');      /*Bubble Flip Flops*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072848', '6300', 'Shoes 2');      /*Bloody Garter*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072851', '5000', 'Shoes 2');      /*Bubble Bubble Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072852', '2700', 'Shoes 2');      /*Superstar Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072854', '5600', 'Shoes 2');      /*フューチャーロイドスキンシューズ*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072855', '2700', 'Shoes 2');      /*フューチャーロイドネオンブーツ*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072856', '6300', 'Shoes 2');      /*Dawn Bear Comfy Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072857', '5600', 'Shoes 2');      /*Odette Ballet Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072858', '7400', 'Shoes 2');      /*Odile Ballet Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072859', '6300', 'Shoes 2');      /*Cobalt Zero Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072860', '6800', 'Shoes 2');      /*Star Winkle*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072862', '3200', 'Shoes 2');      /*Heart Pudding Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072863', '5000', 'Shoes 2');      /*Stirkandbock Sandals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072864', '5600', 'Shoes 2');      /*PSY Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072865', '4700', 'Shoes 2');      /*Camellia Flower Geta*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072866', '7400', 'Shoes 2');      /*Chocoram Doll Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072867', '5000', 'Shoes 2');      /*Puffram Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072868', '4300', 'Shoes 2');      /*Powder Flats*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072869', '7400', 'Shoes 2');      /*Princess of Time Heels*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072871', '3200', 'Shoes 2');      /*Halloweenroid Boots*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072873', '2700', 'Shoes 2');      /*Asuna's Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072875', '2700', 'Shoes 2');      /*Leafa's Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072876', '3200', 'Shoes 2');      /*Cacao Bear Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072877', '6300', 'Shoes 2');      /*Dark Devil Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072878', '4300', 'Shoes 2');      /*Vampire Phantom Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072879', '2700', 'Shoes 2');      /*Freud's Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072880', '4300', 'Shoes 2');      /*Aran's Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072881', '7400', 'Shoes 2');      /*Brave Aran's Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072883', '6300', 'Shoes 2');      /*Heathcliff's Boots*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072884', '3700', 'Shoes 2');      /*Yui's Anklet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072889', '6800', 'Shoes 2');      /*Snake High-tops*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072890', '3700', 'Shoes 2');      /*Mr. K's Cat Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072897', '5000', 'Shoes 2');      /*Blue Moccasin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072901', '3200', 'Shoes 2');      /*Moonlight Marble Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072908', '3700', 'Shoes 2');      /*Pony Wing Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072909', '6300', 'Shoes 2');      /*Purple Rainbow Sneaks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072910', '4700', 'Shoes 2');      /*Cacao Bear Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072913', '5600', 'Shoes 2');      /*Blue Slippers*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072916', '2700', 'Shoes 2');      /*Guardian Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072917', '4700', 'Shoes 2');      /*Cutie Horse Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072918', '2700', 'Shoes 2');      /*Pink Flowery Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072919', '2700', 'Shoes 2');      /*Blue Butterfly Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072920', '5600', 'Shoes 2');      /*Ghost Bride's Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072921', '5600', 'Shoes 2');      /*Fancy Magician Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072922', '5000', 'Shoes 2');      /*Chef Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072923', '3700', 'Shoes 2');      /*Contemporary Chic Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072924', '4700', 'Shoes 2');      /*Nurse Boots*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072925', '6300', 'Shoes 2');      /*Doctor Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072926', '5600', 'Shoes 2');      /*Colorful Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072934', '5000', 'Shoes 2');      /*Rainbow Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072942', '6800', 'Shoes 2');      /*Island Travel Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072943', '3200', 'Shoes 2');      /*Humming Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072944', '4300', 'Shoes 2');      /*暗夜精灵战靴*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072945', '3200', 'Shoes 2');      /*隐武士战靴*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072949', '3700', 'Shoes 2');      /*Red Pony Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072950', '6800', 'Shoes 2');      /*Blue Pony Sneakers*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072951', '7400', 'Shoes 2');      /*Hula Hula Beaded Anklet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072978', '7400', 'Shoes 2');      /*Glowy Leather Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072979', '3700', 'Shoes 2');      /*Bright Angel Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072980', '6300', 'Shoes 2');      /*Dark Devil Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072998', '4300', 'Shoes 2');      /*Rabbit-Bear Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072999', '4700', 'Shoes 2');      /*Ribbon Red Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073008', '3200', 'Shoes 2');      /*Scarlet Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073009', '3200', 'Shoes 2');      /*Corn Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073011', '3200', 'Shoes 2');      /*Cheerleader Shoes*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073013', '3700', 'Shoes 2');      /*Wiggly Puppy Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073014', '6800', 'Shoes 2');      /*Pink Puppy Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073019', '7400', 'Shoes 2');      /*Dinofrog Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073022', '5000', 'Shoes 2');      /*Pink Kitty Blue Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073024', '7400', 'Shoes 2');      /*Red Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073025', '5600', 'Shoes 2');      /*Hatchling Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073027', '6300', 'Shoes 2');      /*ODM Gear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073036', '5000', 'Shoes 2');      /*Blue Bird Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073037', '3700', 'Shoes 2');      /*Cutie Bunny Shoes*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073038', '5600', 'Shoes 2');      /*Soft Pink Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073039', '3700', 'Shoes 2');      /*Cutie Birk Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073040', '2700', 'Shoes 2');      /*Maple Mouse Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073041', '4700', 'Shoes 2');      /*Black Forte Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073044', '5600', 'Shoes 2');      /*-*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073046', '4300', 'Shoes 2');      /*Baby Ram Slippers (Blue)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073047', '5000', 'Shoes 2');      /*Baby Ram Slippers (Pink)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073050', '6800', 'Shoes 2');      /*Ring Sneakers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073051', '5600', 'Shoes 2');      /*Ryan D Shoes*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073052', '3700', 'Shoes 2');      /*Sierra Grace Boots */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073055', '6300', 'Shoes 2');      /*Akarin's Flowery Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073056', '4700', 'Shoes 2');      /*Blooming Spring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073058', '2700', 'Shoes 2');      /*Naughty Boy Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073059', '6300', 'Shoes 2');      /*Cat Knee Socks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073060', '7400', 'Shoes 2');      /*Noble Blossom Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073061', '4300', 'Shoes 2');      /*Pink Blossom Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073062', '3700', 'Shoes 2');      /*Cottontail Rabbit Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073074', '4700', 'Shoes 2');      /*Schwarzer Boots*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073075', '6300', 'Shoes 2');      /*Mint Kitty Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073079', '5000', 'Shoes 2');      /*Jelly Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073080', '5000', 'Shoes 2');      /*Black Sailor Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073082', '3200', 'Shoes 2');      /*Odette Ballet Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073084', '4700', 'Shoes 2');      /*Kinesis Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073085', '6800', 'Shoes 2');      /*Kinesis Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073088', '6300', 'Shoes 2');      /*Bluebird Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073090', '6300', 'Shoes 2');      /*White Ursus Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073091', '2700', 'Shoes 2');      /*Brown Ursus Slippers*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073092', '3700', 'Shoes 2');      /*Black Ursus Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073096', '7400', 'Shoes 2');      /*Little Vampire Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073169', '2700', 'Shoes 2');      /*Bichon Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073105', '2700', 'Shoes 2');      /*Exciting Kicks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073106', '2700', 'Shoes 2');      /*Polar Booties*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073107', '5000', 'Shoes 2');      /*Wooden Bell Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073108', '6800', 'Shoes 2');      /*Flutter Bell Sandals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073115', '2700', 'Shoes 2');      /*Evan Dragon Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073116', '3700', 'Shoes 2');      /*Evan Dragon Boots*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073117', '4300', 'Shoes 2');      /*Royal Mercedes Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073118', '6300', 'Shoes 2');      /*Royal Mercedes Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073119', '6300', 'Shoes 2');      /*Mystic Phantom Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073120', '2700', 'Shoes 2');      /*Mystic Phantom Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073121', '7400', 'Shoes 2');      /*Winter Aran Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073122', '4300', 'Shoes 2');      /*Winter Aran Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073123', '7400', 'Shoes 2');      /*Chiaroscuro Luminous Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073124', '3200', 'Shoes 2');      /*Chiaroscuro Luminous Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073125', '5000', 'Shoes 2');      /*Secret Shade Boots*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073126', '7400', 'Shoes 2');      /*Secret Shade Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073127', '6300', 'Shoes 2');      /*Cozy Fluffy Slippers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073128', '3700', 'Shoes 2');      /*Snow Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073132', '5000', 'Shoes 2');      /*Umbral Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073133', '5000', 'Shoes 2');      /*Umbral Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073134', '6800', 'Shoes 2');      /*Flower Dancer's Sandals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073135', '3700', 'Shoes 2');      /*Moon Dancer's Boots*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073144', '3200', 'Shoes 2');      /*Shark Bite Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073145', '6300', 'Shoes 2');      /*Kitty Follower*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073150', '5600', 'Shoes 2');      /*Chicken Cutie Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073153', '4300', 'Shoes 2');      /*Blaster Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073154', '2700', 'Shoes 2');      /*Blaster Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073155', '6800', 'Shoes 2');      /*Villain Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073156', '5600', 'Shoes 2');      /*Colorful Beach Sandals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073157', '6300', 'Shoes 2');      /*Red Cloud Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073167', '5600', 'Shoes 2');      /*Dark Musician Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1073168', '5600', 'Shoes 2');      /*Chained Princess Shoes*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1080000', '4700', 'Glove');      /*White Ninja Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1080001', '6800', 'Glove');      /*Wedding Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1080007', '4700', 'Glove');      /*크리스토프 장갑*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1080008', '3700', 'Glove');      /*Whip Cream Pon Pon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1080009', '5000', 'Glove');      /*Penguin Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1081000', '4700', 'Glove');      /*Red Ninja Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1081001', '3700', 'Glove');      /*Blue Ninja Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1081002', '4700', 'Glove');      /*Wedding Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1081003', '4300', 'Glove');      /*White Cat Gloves*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1081004', '3700', 'Glove');      /*Black Cat Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1081006', '4300', 'Glove');      /*Elizabeth Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1081007', '5000', 'Glove');      /*Elizabeth Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1081013', '5600', 'Glove');      /*엘사 장갑*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1081014', '2700', 'Glove');      /*Whip Cream Bon Bon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1081015', '4700', 'Glove');      /*Penguin Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082040', '5600', 'Glove');      /*Red Boxing Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082041', '6300', 'Glove');      /*Blue Boxing Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082057', '3700', 'Glove');      /*Brown Baseball Glove*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082058', '6800', 'Glove');      /*Blue Baseball Glove*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082077', '5000', 'Glove');      /*White Bandage*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082078', '6800', 'Glove');      /*Brown Bandage*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082079', '3700', 'Glove');      /*Black Bandage*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082101', '6300', 'Glove');      /*Santa Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082102', '3700', 'Glove');      /*Transparent Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082113', '5000', 'Glove');      /*Hair-Cutter Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082124', '5000', 'Glove');      /*Mesoranger Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082155', '7400', 'Glove');      /*Snowman Gloves*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082156', '7400', 'Glove');      /*Teddy Bear Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082157', '2700', 'Glove');      /*Skull Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082161', '3200', 'Glove');      /*Star Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082162', '4700', 'Glove');      /*Love Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082165', '3700', 'Glove');      /*White Rabbit Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082166', '7400', 'Glove');      /*Nero Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082169', '3200', 'Glove');      /*Moon Bunny Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082170', '5600', 'Glove');      /*Rose Crystal Watch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082171', '3700', 'Glove');      /*Blue Watch*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082172', '3700', 'Glove');      /*Snowflake Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082173', '6800', 'Glove');      /*Lightning Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082224', '6300', 'Glove');      /*Tania Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082225', '5000', 'Glove');      /*Mercury Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082227', '4700', 'Glove');      /*Skull Tattoo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082229', '4300', 'Glove');      /*Heart Ribbon Glove*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082231', '4300', 'Glove');      /*Luxury Wristwatch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082233', '7400', 'Glove');      /*Moomoo Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082247', '3700', 'Glove');      /*Cutie Birk Gloves*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082249', '3200', 'Glove');      /*Neon Amulet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082250', '5000', 'Glove');      /*Treacherous Wolf Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082251', '3200', 'Glove');      /*Rock Chain Armlet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082253', '6800', 'Glove');      /*Neon Sign Amulet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082255', '3700', 'Glove');      /*Maple Racing Glove*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082261', '4300', 'Glove');      /*Freud's Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082263', '5000', 'Glove');      /*Bunny Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082267', '6800', 'Glove');      /*Cat Set Mittens*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082268', '4700', 'Glove');      /*Dual Blade Gloves*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082272', '4300', 'Glove');      /*Evan Golden Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082273', '7400', 'Glove');      /*Hawkeye Ocean Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082274', '2700', 'Glove');      /*Evan Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082282', '6300', 'Glove');      /*Battle Mage Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082310', '5000', 'Glove');      /*Winter 2011 Moon Bunny Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082312', '4700', 'Glove');      /*Rainbow Bracelet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082407', '4700', 'Glove');      /*Dark Force Gloves */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082408', '3700', 'Glove');      /*Elven Spirit Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082421', '5000', 'Glove');      /*Blue Dragon Gloves*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082422', '6800', 'Glove');      /*Red Dragon Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082423', '7400', 'Glove');      /*Intergalactic Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082448', '3200', 'Glove');      /*Arabian Gold Bracelet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082493', '2700', 'Glove');      /*Harp Seal Doll Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082495', '2700', 'Glove');      /*Cat Lolita Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082500', '6800', 'Glove');      /*Dark Devil Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082501', '6300', 'Glove');      /*Dark Force Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082502', '7400', 'Glove');      /*Blue Point Kitty Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082503', '7400', 'Glove');      /*Featherly Angel Gloves*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082504', '3700', 'Glove');      /*Kitty Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082505', '4300', 'Glove');      /*Xenon Neo-Tech Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082511', '6300', 'Glove');      /*Green Dinosaur Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082517', '6800', 'Glove');      /*Golf Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082519', '6800', 'Glove');      /*Purple Dinosaur Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082520', '6800', 'Glove');      /*Ramling Fur Glove*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082694', '5600', 'Glove');      /*Villain Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082524', '2700', 'Glove');      /*Blavy Angel Bangle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082525', '6300', 'Glove');      /*Succubus Gloves*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082527', '4700', 'Glove');      /*Golf Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082702', '4300', 'Glove');      /*Kamaitachi Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082703', '5600', 'Glove');      /*Bichon Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082548', '5000', 'Glove');      /*Star Bracelet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082549', '3200', 'Glove');      /*Chicken Glovaroo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082550', '7400', 'Glove');      /*White Ghostly Cloth*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082551', '2700', 'Glove');      /*Chocoram Doll Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082552', '3200', 'Glove');      /*Puffram Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082554', '6800', 'Glove');      /*Princess of Time Gloves*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082555', '4300', 'Glove');      /*Fairy Spark*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082558', '2700', 'Glove');      /*Kirito's Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082560', '4300', 'Glove');      /*Dark Devil Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082561', '5600', 'Glove');      /*Freud's Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082563', '4700', 'Glove');      /*Heathcliff's Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082564', '5000', 'Glove');      /*Yui's Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082565', '4700', 'Glove');      /*Chocolate Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082571', '6300', 'Glove');      /*Mr. K's Cat Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082580', '6800', 'Glove');      /*Pony Gloves*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082581', '2700', 'Glove');      /*Chocolate Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082585', '5000', 'Glove');      /*Guardian Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082587', '5000', 'Glove');      /*Pink Panda Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082588', '5600', 'Glove');      /*Rainbow Marbles*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082592', '6300', 'Glove');      /*Burning Ghost Wristband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082620', '6800', 'Glove');      /*Aloha Flower Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082623', '4700', 'Glove');      /*Bright Angel Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082624', '5600', 'Glove');      /*Dark Devil Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082631', '2700', 'Glove');      /*Glowing Bracelet*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082632', '4700', 'Glove');      /*Worn Skull Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082633', '7400', 'Glove');      /*Skull Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082634', '6800', 'Glove');      /*Dinofrog Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082641', '6300', 'Glove');      /*Blue Bird Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082642', '6300', 'Glove');      /*Snowman Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082643', '6300', 'Glove');      /*Cutie Birk Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082657', '3200', 'Glove');      /*Blue Panda Doll Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082666', '6300', 'Glove');      /*White Ursus Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082667', '4700', 'Glove');      /*Brown Ursus Gloves*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082668', '6300', 'Glove');      /*Black Ursus Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082683', '5000', 'Glove');      /*Santa Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082684', '7400', 'Glove');      /*Beaky Owl Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082689', '6300', 'Glove');      /*Paw Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082692', '4700', 'Glove');      /*Candybear Watch*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112000', '7400', 'Ring');      /*Sparkling Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112001', '2700', 'Ring');      /*Crush Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112002', '5600', 'Ring');      /*Cloud Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112003', '3200', 'Ring');      /*Cupid Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112005', '7400', 'Ring');      /*Venus Fireworks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112006', '7400', 'Ring');      /*Crossed Hearts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112007', '4700', 'Ring');      /*Mistletoe Crush Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112012', '6800', 'Ring');      /*Rose Crush Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112013', '6800', 'Ring');      /*Firery Love String Couple Ring*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112014', '2700', 'Ring');      /*Flaming Red Lips Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112015', '2700', 'Ring');      /*Illumination Couples Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112016', '3700', 'Ring');      /*Snowflake Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112100', '3700', 'Ring');      /*White Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112101', '3200', 'Ring');      /*Blue Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112102', '6800', 'Ring');      /*Blue Label Ring 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112103', '3700', 'Ring');      /*The Legendary Gold Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112104', '3700', 'Ring');      /*Bubbly Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112105', '5000', 'Ring');      /*Pink-Ribboned Label Ring*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112106', '7400', 'Ring');      /*Blue-Ribboned Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112107', '5000', 'Ring');      /*Skull Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112108', '5600', 'Ring');      /*Butterfly Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112109', '3200', 'Ring');      /*Scoreboard Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112110', '5000', 'Ring');      /*SK Basketball Team Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112111', '6300', 'Ring');      /*KTF Basketball Team Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112112', '3700', 'Ring');      /*Beach Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112113', '6300', 'Ring');      /*Chocolate Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112114', '5000', 'Ring');      /*Pink Candy Label Ring*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112115', '2700', 'Ring');      /*MapleBowl Label Ring */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112116', '7400', 'Ring');      /*White Cloud Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112117', '5000', 'Ring');      /*Rainbow Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112118', '2700', 'Ring');      /*Rainbow Label RingaCoke Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112119', '6300', 'Ring');      /*Coke (Red) Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112120', '5600', 'Ring');      /*Coke (White) Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112121', '7400', 'Ring');      /*Gingerman Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112122', '4700', 'Ring');      /*Deluxe Rainbow Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112123', '5600', 'Ring');      /*Red Pencil Label Ring*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112124', '6800', 'Ring');      /*Blue Pencil Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112125', '6300', 'Ring');      /*Green Pencil Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112126', '4700', 'Ring');      /*Brown Teddy Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112127', '6300', 'Ring');      /*Welcome Back Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112129', '3700', 'Ring');      /*German Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112130', '5600', 'Ring');      /*Dutch Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112131', '2700', 'Ring');      /*French Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112132', '7400', 'Ring');      /*British Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112134', '3200', 'Ring');      /*Bamboo Name Tag Ring*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112135', '4700', 'Ring');      /*Ink-and-Wash Painting Name Tag Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112136', '6800', 'Ring');      /*Sausage Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112137', '2700', 'Ring');      /*Mountain Dew Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112141', '4700', 'Ring');      /*Red Rose Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112142', '4700', 'Ring');      /*Mummy Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112143', '6800', 'Ring');      /*Luxury Pearl Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112144', '3700', 'Ring');      /*Cat-ger Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112145', '3200', 'Ring');      /*Romantic Lace Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112146', '4300', 'Ring');      /*Green Apple Label Ring*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112148', '2700', 'Ring');      /*Mister Mustache Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112149', '5000', 'Ring');      /*Naver Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112150', '5000', 'Ring');      /*Angel Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112151', '6800', 'Ring');      /*Strawberry Cake Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112152', '2700', 'Ring');      /*Blue Strawberry Basket Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112153', '4700', 'Ring');      /*Strawberry Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112154', '4300', 'Ring');      /*Moon Bunny Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112155', '3200', 'Ring');      /*Frog Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112156', '2700', 'Ring');      /*Oink Label Ring*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112157', '3200', 'Ring');      /*Blue Beard Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112159', '2700', 'Ring');      /*Diamond Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112160', '4300', 'Ring');      /*Watermelon Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112161', '4300', 'Ring');      /*Quack Quack Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112162', '5600', 'Ring');      /*Island Travel Name Tag Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112163', '4300', 'Ring');      /*Starring Me Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112164', '5600', 'Ring');      /*Sweet Summer Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112165', '5000', 'Ring');      /*Green Forest Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112166', '6300', 'Ring');      /*Baby Label Ring*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112170', '6800', 'Ring');      /*Star Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112171', '6800', 'Ring');      /*White Puppy Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112172', '3200', 'Ring');      /*Brown Puppy Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112173', '3700', 'Ring');      /*Bunny Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112176', '3200', 'Ring');      /*G Clef Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112177', '5000', 'Ring');      /*Attack on Titan Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112178', '4300', 'Ring');      /*Snow Day Dream Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112179', '4700', 'Ring');      /*Snowy Christmas Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112180', '3200', 'Ring');      /*Kinship Label Ring*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112181', '2700', 'Ring');      /*Sheep Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112182', '3200', 'Ring');      /*Baby Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112183', '6800', 'Ring');      /*Meadow Sheep Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112184', '4700', 'Ring');      /*Squishy Pink Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112190', '2700', 'Ring');      /*Carrot Rabbit Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112191', '7400', 'Ring');      /*Honey Bee Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112192', '6300', 'Ring');      /*Pineapple Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112193', '4300', 'Ring');      /*Princess Diary Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112194', '6300', 'Ring');      /*Black Hat Label Ring*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112195', '6800', 'Ring');      /*Green Hat Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112196', '5000', 'Ring');      /*Blue Hat Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112197', '4300', 'Ring');      /*Good Night Monster Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112198', '4700', 'Ring');      /*Rascally Monster Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112199', '5000', 'Ring');      /*Snowman's Red Scarf Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112200', '5600', 'Ring');      /*Pink Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112201', '4300', 'Ring');      /*Pink-Hearted Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112202', '3200', 'Ring');      /*Blue Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112203', '3200', 'Ring');      /*The Golden Fly Ring*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112204', '6800', 'Ring');      /*Pink-Flowered Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112205', '7400', 'Ring');      /*Blue-Flowered Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112206', '4700', 'Ring');      /*Pink-Ribboned Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112207', '3200', 'Ring');      /*Blue-Ribboned Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112208', '3700', 'Ring');      /*Skull Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112209', '3700', 'Ring');      /*Blue-Hearted Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112210', '3200', 'Ring');      /*Gold-Yellow Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112211', '4300', 'Ring');      /*Pink Lady Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112212', '2700', 'Ring');      /*Silver-Blue Quote Ring*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112213', '6300', 'Ring');      /*Gold-Yellow Quote Ring 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112214', '4700', 'Ring');      /*Pink Lady Quote Ring 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112215', '4300', 'Ring');      /*Blue Marine Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112216', '4300', 'Ring');      /*Kitty Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112217', '5600', 'Ring');      /*Paw-Print Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112218', '6300', 'Ring');      /*Teddy Bear Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112219', '3700', 'Ring');      /*Scoreboard Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112220', '6800', 'Ring');      /*SK Basketball Team Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112221', '5600', 'Ring');      /*KTF Basketball Team Quote Ring*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112222', '4700', 'Ring');      /*Starflower Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112223', '2700', 'Ring');      /*Beach Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112224', '4700', 'Ring');      /*Chocolate Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112225', '3200', 'Ring');      /*Pink Candy Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112226', '7400', 'Ring');      /*White Cloud Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112227', '6300', 'Ring');      /*Rainbow Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112228', '6300', 'Ring');      /*Coke Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112229', '2700', 'Ring');      /*Coke (Red) Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112230', '6800', 'Ring');      /*Coke (White) Quote Ring*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112231', '5600', 'Ring');      /*Gingerman Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112232', '5600', 'Ring');      /*Deluxe Rainbow Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112233', '6800', 'Ring');      /*Red Notebook Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112234', '5600', 'Ring');      /*Blue Notebook Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112235', '6800', 'Ring');      /*Green Notebook Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112236', '4300', 'Ring');      /*Brown Teddy Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112237', '3700', 'Ring');      /*Bamboo Thought Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112238', '5000', 'Ring');      /*Ink-and-Wash Thought Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112240', '5600', 'Ring');      /*Mountain Dew Quote Ring*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112244', '7400', 'Ring');      /*Darkness Bat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112248', '6300', 'Ring');      /*[MS Custom] Pink Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112249', '3200', 'Ring');      /*[MS Custom] Blue-Flowered Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112250', '3700', 'Ring');      /*[MS Custom]Pink-Flowered Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112252', '2700', 'Ring');      /*Red Rose Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112253', '6300', 'Ring');      /*Mummy Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112254', '6800', 'Ring');      /*Luxury Pearl Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112256', '7400', 'Ring');      /*Kitty Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112257', '3200', 'Ring');      /*Romantic Lace Word Bubble Ring*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112258', '2700', 'Ring');      /*Green Apple Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112259', '5600', 'Ring');      /*Mister Mustache Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112260', '6300', 'Ring');      /*Guild Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112261', '4300', 'Ring');      /*Naver Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112262', '3700', 'Ring');      /*Angel Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112263', '6300', 'Ring');      /*Strawberry Cake Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112264', '3200', 'Ring');      /*Blue Strawberry Basket Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112265', '3700', 'Ring');      /*Red Strawberry Basket Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112266', '6300', 'Ring');      /*Moon Bunny Word Bubble Ring*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112267', '6300', 'Ring');      /*Frog Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112268', '5000', 'Ring');      /*Oink Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112269', '7400', 'Ring');      /*Blue Beard Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112271', '5600', 'Ring');      /*Diamond Quote Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112272', '6300', 'Ring');      /*Watermelon Chat Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112273', '3200', 'Ring');      /*Quack Quack Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112274', '5600', 'Ring');      /*Island Travel Speech Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112275', '5600', 'Ring');      /*Me From the Star Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112276', '5600', 'Ring');      /*Sweet Summer Chat Ring*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112277', '4300', 'Ring');      /*Green Forest Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112278', '3700', 'Ring');      /*Baby Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112282', '3200', 'Ring');      /*Star Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112283', '4700', 'Ring');      /*White Puppy Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112284', '6800', 'Ring');      /*Brown Puppy Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112285', '3200', 'Ring');      /*Bunny Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112288', '5600', 'Ring');      /*G Clef Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112289', '3200', 'Ring');      /*Attack on Titan Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112290', '4700', 'Ring');      /*Snow Day Dream Word Bubble Ring*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112291', '3200', 'Ring');      /*Snowy Christmas Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112292', '2700', 'Ring');      /*Silver Guild Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112293', '6300', 'Ring');      /*Kinship Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112294', '7400', 'Ring');      /*Sheep Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112295', '6800', 'Ring');      /*Baby Word Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112296', '3700', 'Ring');      /*Meadow Sheep Chat Bubble Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112724', '5600', 'Ring');      /*I'm New Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112728', '4300', 'Ring');      /*Mapler Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112741', '2700', 'Ring');      /*Welcome Back Ring*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112757', '3700', 'Ring');      /*Grin Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112800', '7400', 'Ring');      /*Friendship Ring: Clover*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112801', '5000', 'Ring');      /*Friendship Ring: Flower Petal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112802', '3700', 'Ring');      /*Friendship Ring: Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112808', '4300', 'Ring');      /*MapleBowl Quote Ring */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112810', '2700', 'Ring');      /*Christmas Night Bells Friendship Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112811', '4700', 'Ring');      /*Christmas Party Friendship Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112812', '5000', 'Ring');      /*Shared Umbrella Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112816', '5000', 'Ring');      /*Snow Dome Friendship Ring*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112817', '3700', 'Ring');      /*Psyche Special Friendship Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112820', '5600', 'Ring');      /*Friendship Ring: Dragon and Pheonix*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112823', '4700', 'Ring');      /*Cloud Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112900', '4700', 'Ring');      /*Lalala Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112901', '4700', 'Ring');      /*Starry Spotlight Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112902', '2700', 'Ring');      /*Baby Blue*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112903', '5000', 'Ring');      /*Amorian Aura Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112904', '4700', 'Ring');      /*Rainbow Star Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112905', '3700', 'Ring');      /*Bright Hot Pink Heart*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112906', '5000', 'Ring');      /*Baby Pink Heart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112908', '2700', 'Ring');      /*Aura Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112909', '6800', 'Ring');      /*Aura Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112910', '5600', 'Ring');      /*Aura Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112916', '4300', 'Ring');      /*Solo Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112917', '3200', 'Ring');      /*I'm New Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112918', '4300', 'Ring');      /*Welcome Back Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112919', '4300', 'Ring');      /*Mapler Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112924', '5600', 'Ring');      /*Lemon Shooting Star Ring*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112925', '3700', 'Ring');      /*Blue Shooting Star Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112926', '4700', 'Ring');      /*Pink Shooting Star Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112928', '5000', 'Ring');      /*Peach Shooting Star Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112929', '5000', 'Ring');      /*Von Leon Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112930', '7400', 'Ring');      /*Tomato Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112935', '6300', 'Ring');      /*Lalala Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112937', '7400', 'Ring');      /*Sleepy Zzz*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112940', '5000', 'Ring');      /*Mapler Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112941', '6300', 'Ring');      /*Welcome Back Ring*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112943', '6300', 'Ring');      /*Fashion Week Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112945', '3200', 'Ring');      /*Always Craving Sweet N' Sour BBQ*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112946', '3200', 'Ring');      /*Rainbow Jewelry*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112948', '6800', 'Ring');      /*Couture Critic Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112949', '6800', 'Ring');      /*Melody Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112953', '7400', 'Ring');      /*Blue Shooting Star Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112954', '5600', 'Ring');      /*Pink Shooting Star Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112955', '3700', 'Ring');      /*Best Friends Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112956', '5000', 'Ring');      /*Shining Star Ring*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112958', '6800', 'Ring');      /*Honey Bee Flower Effect Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112959', '4300', 'Ring');      /*Butterfly Flower Effect Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112960', '6300', 'Ring');      /*Memory Guide Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1112961', '3200', 'Ring');      /*해피몬스터 링*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1113003', '4300', 'Ring');      /*Dark Devil Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1113171', '6300', 'Ring');      /*Grin Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1114000', '5600', 'Ring');      /*Kinship Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115003', '3200', 'Ring');      /*Carrot Rabbit Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115004', '3700', 'Ring');      /*Honey Bee Chat Ring*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115005', '3200', 'Ring');      /*Pineapple Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115006', '6800', 'Ring');      /*Princess Diary Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115007', '3700', 'Ring');      /*Black Hat Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115008', '5600', 'Ring');      /*Green Hat Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115009', '3700', 'Ring');      /*Blue Hat Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115010', '3700', 'Ring');      /*Good Night Monster Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115011', '2700', 'Ring');      /*Rascally Monster Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115012', '3200', 'Ring');      /*MVP Chat Ring (Silver)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115013', '7400', 'Ring');      /*MVP Chat Ring (Gold)*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115014', '5600', 'Ring');      /*MVP Chat Ring (Diamond)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115015', '4700', 'Ring');      /*Snowman's Red Scarf Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115016', '5000', 'Ring');      /*Heroes Slumbering Dragon Island Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115017', '3700', 'Ring');      /*Christmas Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115018', '7400', 'Ring');      /*Mighty Banana Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115019', '2700', 'Ring');      /*Heroes Damien Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115020', '6800', 'Ring');      /*Heroes Transcendence Stone Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115021', '6300', 'Ring');      /*Heroes Black Mage Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115022', '4700', 'Ring');      /*Bunny Chat Ring*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115023', '4300', 'Ring');      /*Sunshine Ranch Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115024', '6300', 'Ring');      /*Colorbug Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115025', '6800', 'Ring');      /*Shark Chat Ring*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115026', '6300', 'Ring 2');      /*Cat Skein Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115027', '6300', 'Ring 2');      /*Red Cloud Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115029', '2700', 'Ring 2');      /*DJ JM Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115030', '4700', 'Ring 2');      /*Pink Bean Chocolate Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115031', '3700', 'Ring 2');      /*Pink Bean Chat Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115100', '5600', 'Ring 2');      /*MVP Label Ring (Silver)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115101', '2700', 'Ring 2');      /*MVP Label Ring (Gold)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115102', '2700', 'Ring 2');      /*MVP Label Ring (Diamond)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115103', '3200', 'Ring 2');      /*Slumbering Dragon Island Label Ring*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115104', '5600', 'Ring 2');      /*Christmas Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115105', '3200', 'Ring 2');      /*Mighty Banana Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115108', '3700', 'Ring 2');      /*Heroes Damien Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115109', '6300', 'Ring 2');      /*Heroes Transcendence Stone Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115110', '6800', 'Ring 2');      /*Heroes Black Mage Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115111', '6800', 'Ring 2');      /*Bunny Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115112', '5600', 'Ring 2');      /*Sunshine Ranch Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115113', '2700', 'Ring 2');      /*Colorbug Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115114', '6300', 'Ring 2');      /*Shark Label Ring*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115115', '4700', 'Ring 2');      /*Cat Skein Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115116', '3200', 'Ring 2');      /*Red Cloud Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115118', '2700', 'Ring 2');      /*DJ JM Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115119', '6300', 'Ring 2');      /*Pink Bean Chocolate Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1115120', '3700', 'Ring 2');      /*Pink Bean Label Ring*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1100000', '4300', 'Cape');      /*Napoleon Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1100001', '2700', 'Cape');      /*Napoleon Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1100004', '6800', 'Cape');      /*Mad Doctor Stethoscope*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1101000', '5600', 'Cape');      /*Ribbon Angel Syringe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102005', '5000', 'Cape');      /*Baby Angel Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102006', '5000', 'Cape');      /*Devil Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102007', '6300', 'Cape');      /*Yellow Star Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102008', '7400', 'Cape');      /*Blue Star Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102009', '6300', 'Cape');      /*Red Star Cape*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102010', '3200', 'Cape');      /*Black Star Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102019', '7400', 'Cape');      /*Korean-Flagged Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102020', '5600', 'Cape');      /*Turtle Shell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102025', '6300', 'Cape');      /*Red Hood*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102036', '3700', 'Cape');      /*Red Landcell Pack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102037', '4300', 'Cape');      /*Black Landcell Pack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102038', '3700', 'Cape');      /*Blue Landcell Pack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102039', '5000', 'Cape');      /*Transparent Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102044', '3700', 'Cape');      /*Red G-Wing Jetpack*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102045', '5600', 'Cape');      /*Blue G-Wing Jetpack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102049', '6800', 'Cape');      /*Blue Nymph Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102050', '4700', 'Cape');      /*Green Nymph Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102051', '6300', 'Cape');      /*Yellow Nymph Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102052', '6800', 'Cape');      /*Pink Nymph Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102058', '6300', 'Cape');      /*Gargoyle Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102059', '5600', 'Cape');      /*Michael Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102060', '4700', 'Cape');      /*Pink Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102062', '2700', 'Cape');      /*Martial Cape*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102063', '6800', 'Cape');      /*Fallen Angel Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102065', '6300', 'Cape');      /*Christmas Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102066', '3700', 'Cape');      /*Dracula Cloak*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102067', '5000', 'Cape');      /*Tiger Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102068', '5000', 'Cape');      /*Harpie Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102069', '7400', 'Cape');      /*Pink Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102070', '4700', 'Cape');      /*Blue Book Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102072', '2700', 'Cape');      /*Yellow-Green Backpack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102073', '5000', 'Cape');      /*Hot Pink Backpack*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102074', '7400', 'Cape');      /*Dragonfly Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102075', '6300', 'Cape');      /*Bat's Bane*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102076', '5000', 'Cape');      /*Newspaper Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102077', '4300', 'Cape');      /*Cotton Blanket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102091', '5600', 'Cape');      /*Summer Kite*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102092', '4300', 'Cape');      /*Cuddle Bear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102093', '4300', 'Cape');      /*Heart Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102094', '3200', 'Cape');      /*Sun Wu Kong Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102095', '6300', 'Cape');      /*Veamoth Wings*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102096', '2700', 'Cape');      /*Sachiel Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102097', '3700', 'Cape');      /*Janus Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102098', '5600', 'Cape');      /*Coffin of Gloom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102107', '6300', 'Cape');      /*Rocket Booster*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102108', '4700', 'Cape');      /*Fallen Angel Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102110', '6800', 'Cape');      /*Chipmunk Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102111', '4300', 'Cape');      /*Elephant Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102112', '5600', 'Cape');      /*Bunny Doll*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102137', '3700', 'Cape');      /*Orange Mushroom Balloon*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102138', '5000', 'Cape');      /*Pink Wing Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102141', '4700', 'Cape');      /*Pepe Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102142', '5000', 'Cape');      /*The Flaming Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102144', '3200', 'Cape');      /*Sage Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102148', '5600', 'Cape');      /*Tania Cloak*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102149', '6300', 'Cape');      /*Mercury Cloak*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102150', '4300', 'Cape');      /*Count Dracula Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102151', '3200', 'Cape');      /*Lost Kitty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102152', '4300', 'Cape');      /*Pirate Emblem Flag*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102153', '2700', 'Cape');      /*Sunfire Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102154', '3200', 'Cape');      /*Zakum Arms*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102155', '2700', 'Cape');      /*My Buddy Rex*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102156', '7400', 'Cape');      /*Aerial Wave Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102157', '5000', 'Cape');      /*Puppet Strings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102158', '6300', 'Cape');      /*Peacock Feather Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102159', '7400', 'Cape');      /*White Monkey Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102160', '3700', 'Cape');      /*Baby Lupin Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102162', '6800', 'Cape');      /*Baby White Monkey Balloon*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102164', '5600', 'Cape');      /*Maple MSX Guitar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102169', '2700', 'Cape');      /*Blue Wing Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102171', '4300', 'Cape');      /*3rd Anniversary Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102175', '5000', 'Cape');      /*Cutie Birk Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102184', '3200', 'Cape');      /*Aurora Happy Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102185', '6800', 'Cape');      /*Rainbow Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102186', '6300', 'Cape');      /*Kitty Parachute*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102187', '5600', 'Cape');      /*Golden Fox Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102188', '5600', 'Cape');      /*Silver Fox Tail*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102196', '3700', 'Cape');      /*Snowflake Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102197', '6800', 'Cape');      /*Yellow Canary*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102202', '2700', 'Cape');      /*Galactic Flame Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102203', '7400', 'Cape');      /*Super Rocket Booster*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102204', '6300', 'Cape');      /*Romantic Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102208', '6300', 'Cape');      /*Slime Effect Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102209', '4700', 'Cape');      /*Baby White Monkey Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102210', '6300', 'Cape');      /*Honeybee's Sting*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102211', '6800', 'Cape');      /*Bound Wings*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102212', '5000', 'Cape');      /*Lost Child*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102213', '3200', 'Cape');      /*Pink Bean Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102214', '5600', 'Cape');      /*Pink Bean Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102215', '7400', 'Cape');      /*Balloon Bouquet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102216', '4700', 'Cape');      /*Brown Dog Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102217', '4300', 'Cape');      /*Goblin Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102218', '4300', 'Cape');      /*Pink Floating Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102220', '2700', 'Cape');      /*Pachinko Marble-box Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102221', '3200', 'Cape');      /*Pluto Flame Cape*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102222', '5000', 'Cape');      /*Seraphim Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102223', '2700', 'Cape');      /*Star Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102224', '3200', 'Cape');      /*Lamby Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102229', '5600', 'Cape');      /*Bear Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102230', '4700', 'Cape');      /*Penguin Sled*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102232', '7400', 'Cape');      /*Celestial Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102233', '6800', 'Cape');      /*Snowman Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102238', '4700', 'Cape');      /*Cat Set Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102239', '7400', 'Cape');      /*Dual Blade Cape*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102240', '5000', 'Cape');      /*Royal Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102242', '6800', 'Cape');      /*Hawkeye Ocean Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102243', '2700', 'Cape');      /*Dunas Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102245', '3700', 'Cape');      /*Sun Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102249', '3200', 'Cape');      /*Oz Magic Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102250', '4300', 'Cape');      /*Murgoth's Feather*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102251', '4700', 'Cape');      /*World Cup Towel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102252', '4300', 'Cape');      /*Phoenix Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102253', '6800', 'Cape');      /*Purple Wings*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102254', '4700', 'Cape');      /*Wild Hunter Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102255', '5000', 'Cape');      /*Battle Mage Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102257', '7400', 'Cape');      /*Marines Maple Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102258', '5000', 'Cape');      /*Teddy Bear Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102259', '5600', 'Cape');      /*Flying Dragon Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102261', '3200', 'Cape');      /*Equalizer Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102267', '3200', 'Cape');      /*Friendly Nine-Tailed Fox Tails*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102270', '5000', 'Cape');      /*Moon and Sun Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102271', '3200', 'Cape');      /*Lovely Chocolate Balloons*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102273', '2700', 'Cape');      /*Lucifer Half Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102285', '3700', 'Cape');      /*Pink Teru Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102286', '5600', 'Cape');      /*Blue Teru Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102287', '6300', 'Cape');      /*Yellow Teru Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102288', '6300', 'Cape');      /*Piggyback Snowman*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102290', '5000', 'Cape');      /*Silken Flower Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102291', '4300', 'Cape');      /*Nekomata*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102292', '3700', 'Cape');      /*Twinkling Rainbow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102296', '2700', 'Cape');      /*Gray Puppy Tail*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102300', '4300', 'Cape');      /*6th B-Day Party Ball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102301', '6300', 'Cape');      /*Traveler's Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102307', '2700', 'Cape');      /*New Sachiel Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102308', '4300', 'Cape');      /*New Veamoth Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102309', '6300', 'Cape');      /*New Janus Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102310', '3700', 'Cape');      /*Fairytale Mantle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102318', '2700', 'Cape');      /*Demon Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102319', '7400', 'Cape');      /*Legends Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102323', '4300', 'Cape');      /*Legends Pink Balloon*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102324', '3200', 'Cape');      /*Legends Twin Balloons*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102325', '3700', 'Cape');      /*Harmony Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102326', '7400', 'Cape');      /*Angelic Feathers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102336', '3200', 'Cape');      /*Alchemist Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102338', '5600', 'Cape');      /*Honeybee Wings */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102343', '3700', 'Cape');      /*Dark Force Cape */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102344', '3200', 'Cape');      /*Elven Spirit Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102349', '5600', 'Cape');      /*Fairy Wing Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102350', '2700', 'Cape');      /*Pink Teru Cape*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102350', '4700', 'Cape');      /*Pink Teru Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102355', '4300', 'Cape');      /*Jewel Blizzard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102356', '4300', 'Cape');      /*Angelic Emerald*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102357', '4700', 'Cape');      /*Pretty Pink Bean Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102357', '7400', 'Cape');      /*Pretty Pink Bean Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102358', '7400', 'Cape');      /*Round-We-Go Mirror Ball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102358', '3700', 'Cape');      /*Round-We-Go Mirror Ball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102359', '3200', 'Cape');      /*Floaty Snowman Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102359', '7400', 'Cape');      /*Floaty Snowman Balloon*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102367', '5600', 'Cape');      /*Elven Spirit Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102368', '7400', 'Cape');      /*Floating Silken Flower Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102373', '3200', 'Cape');      /*Lucia Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102374', '2700', 'Cape');      /*Monkey Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102376', '6300', 'Cape');      /*Psyche Flora*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102377', '5600', 'Cape');      /*Psyche Mystic*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102378', '3200', 'Cape');      /*Psyche Melody*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102380', '7400', 'Cape');      /*Frog Cronies*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102381', '5000', 'Cape');      /*Imperial Duke Wing*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102385', '4300', 'Cape');      /*Lux Cherubim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102386', '5000', 'Cape');      /*Nox Cherubim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102387', '4300', 'Cape');      /*Blue Dragon Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102388', '3700', 'Cape');      /*Red Dragon Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102389', '3700', 'Cape');      /*Aurora Pharady*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102390', '6800', 'Cape');      /*Aurora Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102391', '4300', 'Cape');      /*Honeybee Wings */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102392', '5600', 'Cape');      /*Dainty Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102396', '7400', 'Cape');      /*Ebony Pimpernel Cape*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102419', '3700', 'Cape');      /*Lucia Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102420', '6800', 'Cape');      /*Magic Star Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102421', '5000', 'Cape');      /*Lemon Floating Smile*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102423', '6800', 'Cape');      /*Euro Balloon (PL)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102424', '6800', 'Cape');      /*Euro Balloon (GR)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102425', '7400', 'Cape');      /*Euro Balloon (RU)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102426', '3700', 'Cape');      /*Euro Balloon (CZ)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102427', '6800', 'Cape');      /*Euro Balloon (NL)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102428', '6800', 'Cape');      /*Euro Balloon (DK)*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102429', '6300', 'Cape');      /*Euro Balloon (DE)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102430', '5000', 'Cape');      /*Euro Balloon (PT)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102431', '3700', 'Cape');      /*Euro Balloon (ES)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102432', '6800', 'Cape');      /*Euro Balloon (IT)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102433', '4300', 'Cape');      /*Euro Balloon (IE)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102434', '2700', 'Cape');      /*Euro Balloon (HR)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102435', '4700', 'Cape');      /*Euro Balloon (UA)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102436', '3700', 'Cape');      /*Euro Balloon (SE)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102437', '6300', 'Cape');      /*Euro Balloon (FR)*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102438', '6300', 'Cape');      /*Euro Balloon (GB)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102450', '6300', 'Cape');      /*Heavenly Aura*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102451', '2700', 'Cape');      /*Void Aura*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102452', '4300', 'Cape');      /*Fairy Aura*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102453', '3700', 'Cape');      /*Dryad*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102461', '3200', 'Cape');      /*Valentine's Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102465', '4300', 'Cape');      /*Jett's Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102466', '5000', 'Cape');      /*Flying Nobilitas*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102486', '5600', 'Cape');      /*BasilMarket Billionaire Balloon*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102487', '5600', 'Cape');      /*Luminous Cherubim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102488', '5000', 'Cape');      /*Cupcake Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102491', '4300', 'Cape');      /*Sunny Day*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102496', '2700', 'Cape');      /*Hyper Honeybee Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102501', '6300', 'Cape');      /*[MS Discount] Nine-Tailed Fox Tails*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102503', '5600', 'Cape');      /*Frisky Cat Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102508', '6800', 'Cape');      /*Koala Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102510', '6800', 'Cape');      /*Ribbon Kitty Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102511', '3700', 'Cape');      /*Angel Cherub*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102512', '7400', 'Cape');      /*Dark Force Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102513', '5000', 'Cape');      /*Flying Nobilitas*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102532', '4700', 'Cape');      /*Light Wing Cherubim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102537', '7400', 'Cape');      /*Magic Star Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102542', '5600', 'Cape');      /*Hawkeye Ocean Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102543', '6800', 'Cape');      /*Oz Magic Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102544', '3200', 'Cape');      /*Albatross Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102545', '6300', 'Cape');      /*Albatross Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102546', '6300', 'Cape');      /*Blue Bird Dream Wings*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102547', '3200', 'Cape');      /*Amethyst Dream Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102548', '3200', 'Cape');      /*Leafy Dream Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102549', '4700', 'Cape');      /*Steward Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102550', '3700', 'Cape');      /*Lime Green Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102551', '5600', 'Cape');      /*Sapphire Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102554', '4700', 'Cape');      /*Succubus Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102555', '2700', 'Cape');      /*Angelic White Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102564', '3700', 'Cape');      /*Angel's Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102572', '7400', 'Cape');      /*Gratias Aura*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102574', '6800', 'Cape');      /*Chicky Pile*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102575', '3700', 'Cape');      /*Lux Cherubim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102576', '6800', 'Cape');      /*Nox Cherubim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102577', '5000', 'Cape');      /*Aurora Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102582', '5000', 'Cape');      /*GM Daejang's Lucia Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102583', '7400', 'Cape');      /*Baby Dragon Pobi*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102587', '4300', 'Cape');      /*Heavenly Aura*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102588', '3700', 'Cape');      /*Void Aura*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102589', '4700', 'Cape');      /*Fairy Aura*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102591', '5000', 'Cape');      /*Battle Monster Victory Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102591', '4700', 'Cape');      /*Battle Monster Victory Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102592', '5600', 'Cape');      /*Battle Monster Consolation Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102592', '7400', 'Cape');      /*Battle Monster Consolation Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102593', '6800', 'Cape');      /*Floaty Baseball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102604', '6800', 'Cape');      /*Gear Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102605', '6300', 'Cape');      /*Shadow Peacemaker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102608', '3700', 'Cape');      /*Superstar Mirror Ball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102609', '4700', 'Cape');      /*Psyche Flora*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102610', '6800', 'Cape');      /*Psyche Mystic*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102611', '5000', 'Cape');      /*Psyche Melody*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102613', '5600', 'Cape');      /*Futuroid Tail Sensor*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102614', '6800', 'Cape 2');      /*Futuroid Tail Sensor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102615', '3700', 'Cape 2');      /*Clocktower Wind-up Doll*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102616', '2700', 'Cape 2');      /*Lapis's Spirit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102617', '2700', 'Cape 2');      /*Lazuli's Spirit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102619', '6300', 'Cape 2');      /*Icy Sweet Penguin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102620', '4300', 'Cape 2');      /*My Own Fireworks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102621', '6300', 'Cape 2');      /*Nagging Megaphone*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102622', '6300', 'Cape 2');      /*Princess of Time Pocket Watch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102624', '5000', 'Cape 2');      /*Aeolus Aura*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102625', '3700', 'Cape 2');      /*Snail Shell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102629', '2700', 'Cape 2');      /*Pink Cherubim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102630', '3700', 'Cape 2');      /*Romantic Wing Cherubim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102631', '6800', 'Cape 2');      /*Vampire Phantom Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102632', '4300', 'Cape 2');      /*Shadow Peacemaker*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102640', '6300', 'Cape 2');      /*Aran's Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102641', '3200', 'Cape 2');      /*Yui's Spirit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102642', '7400', 'Cape 2');      /*Yui's Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102643', '5600', 'Cape 2');      /*Golden Age*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102644', '4300', 'Cape 2');      /*Pretty Pixie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102648', '5000', 'Cape 2');      /*Mr. K's Cat Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102650', '4700', 'Cape 2');      /*Eunwol Foxtail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102651', '4300', 'Cape 2');      /*Red Panda Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102652', '3700', 'Cape 2');      /*Chipmunk Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102653', '6300', 'Cape 2');      /*Deluxe Rabbit Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102654', '5600', 'Cape 2');      /*Puppy Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102655', '5000', 'Cape 2');      /*Bear Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102656', '4300', 'Cape 2');      /*Bunny Tail*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102657', '6800', 'Cape 2');      /*Cat o' Nine Tails*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102658', '4700', 'Cape 2');      /*Cute Kitty Tail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102667', '3700', 'Cape 2');      /*Magical Misty Moon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102668', '5000', 'Cape 2');      /*Night Angel Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102669', '6300', 'Cape 2');      /*Royal Spoiled Fairy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102673', '4300', 'Cape 2');      /*Ghost Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102674', '3200', 'Cape 2');      /*Food Escape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102675', '3200', 'Cape 2');      /*Candy Party Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102682', '6300', 'Cape 2');      /*Nurse Syringe*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102683', '5000', 'Cape 2');      /*Rabbit and Bear Book Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102684', '6800', 'Cape 2');      /*Doctor Stethoscope*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102685', '3200', 'Cape 2');      /*Baby Pink Panda Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102688', '5000', 'Cape 2');      /*Boom Boom Fireworks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102694', '2700', 'Cape 2');      /*Mini-Mini Slime*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102695', '6800', 'Cape 2');      /*Spirited Nine Tails*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102697', '5600', 'Cape 2');      /*Ruby Dragonfly Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102698', '5000', 'Cape 2');      /*Emerald Dragonfly Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102699', '4300', 'Cape 2');      /*Magma Wings*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102700', '3200', 'Cape 2');      /*Petit Ciel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102702', '4700', 'Cape 2');      /*Ruby Monarch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102703', '2700', 'Cape 2');      /*Jade Monarch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102705', '7400', 'Cape 2');      /*Island Travel Bags*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102706', '5600', 'Cape 2');      /*Melodic Aurora*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102707', '3200', 'Cape 2');      /*Dreaming Conch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102708', '3700', 'Cape 2');      /*Blushy Conch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102709', '6300', 'Cape 2');      /*Lumina Flutter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102712', '4300', 'Cape 2');      /*Long-awaited Resort*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102723', '5000', 'Cape 2');      /*Giant Bright Angel Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102724', '4300', 'Cape 2');      /*Giant Dark Devil Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102725', '6800', 'Cape 2');      /*Flopping Baby Sea Otter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102726', '3700', 'Cape 2');      /*Carrot Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102729', '2700', 'Cape 2');      /*Glowing Lights*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102730', '4700', 'Cape 2');      /*Glorious Aura*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102747', '6800', 'Cape 2');      /*Cutie Pandas*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102748', '6300', 'Cape 2');      /*Rabbit-Bear Camping Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102749', '3200', 'Cape 2');      /*Starland Balloon*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102754', '6800', 'Cape 2');      /*Idol of the Birds*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102755', '3200', 'Cape 2');      /*Boom Star Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102755', '5000', 'Cape 2');      /*Boom Star Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102756', '6300', 'Cape 2');      /*Corn Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102758', '6800', 'Cape 2');      /*Victory Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102759', '4300', 'Cape 2');      /*Ball Buddies*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102766', '3200', 'Cape 2');      /*Raging Lotus Aura*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102767', '4700', 'Cape 2');      /*Ill Orchid IV*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102768', '4300', 'Cape 2');      /*Worn Witch Cape*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102769', '5000', 'Cape 2');      /*Witch Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102772', '7400', 'Cape 2');      /*Worn Ghost Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102773', '4300', 'Cape 2');      /*Ghost Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102774', '2700', 'Cape 2');      /*Total Lunar Eclipse Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102778', '5000', 'Cape 2');      /*Lolli Lolli Lollipop*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102779', '3700', 'Cape 2');      /*Gold Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102780', '3200', 'Cape 2');      /*With Eren*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102781', '5000', 'Cape 2');      /*With Mikasa*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102782', '6800', 'Cape 2');      /*With Annie*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102783', '3200', 'Cape 2');      /*With Sasha*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102784', '3700', 'Cape 2');      /*With Christa*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102785', '5000', 'Cape 2');      /*With Levi*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102786', '4700', 'Cape 2');      /*Titan Escape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102787', '6800', 'Cape 2');      /*Scout Regiment Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102788', '4700', 'Cape 2');      /*With Armin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102789', '6800', 'Cape 2');      /*Snow Bear Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102798', '6300', 'Cape 2');      /*Blue Bird Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102800', '6800', 'Cape 2');      /*Fluffy Bell Cape*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102801', '6800', 'Cape 2');      /*Silver Wolf Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102802', '5000', 'Cape 2');      /*Round-We-Go Mirror Ball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102802', '5600', 'Cape 2');      /*Round-We-Go Mirror Ball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102803', '6300', 'Cape 2');      /*Pretty Pink Bean Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102803', '4300', 'Cape 2');      /*Pretty Pink Bean Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102804', '6300', 'Cape 2');      /*Pink Teru Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102804', '3200', 'Cape 2');      /*Pink Teru Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102805', '5600', 'Cape 2');      /*Floaty Snowman Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102805', '7400', 'Cape 2');      /*Floaty Snowman Balloon*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102806', '2700', 'Cape 2');      /*Cutie Birk Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102808', '5000', 'Cape 2');      /*Loved Mouse Couple*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102809', '4700', 'Cape 2');      /*Death Waltz Cloak*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102811', '5000', 'Cape 2');      /*Snow Bloom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102812', '6800', 'Cape 2');      /*Blizzard Drive*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102813', '6800', 'Cape 2');      /*Shoulder Blanche*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102815', '5600', 'Cape 2');      /*Lucky Charm Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102816', '4700', 'Cape 2');      /*Fairy Bell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102818', '4300', 'Cape 2');      /*Crystal Cat Star Cape*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102819', '6800', 'Cape 2');      /*Naughty Boy Backpack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102820', '7400', 'Cape 2');      /*Hazy Night Tassel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102822', '6300', 'Cape 2');      /*Flowery Breeze*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102823', '5600', 'Cape 2');      /*Petite Devil Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102824', '4700', 'Cape 2');      /*Halfblood Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102827', '5600', 'Cape 2');      /*The Kingdom Cape of King*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102830', '2700', 'Cape 2');      /*돼지바의 요정*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102831', '6800', 'Cape 2');      /*Soaring High*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102832', '5000', 'Cape 2');      /*Machine Cape*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102835', '6300', 'Cape 2');      /*Schwarzer Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102836', '7400', 'Cape 2');      /*Wonder Kitty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102837', '2700', 'Cape 2');      /*Dreams Within Dreams*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102839', '4300', 'Cape 2');      /*Pink Zakum Arms*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102841', '6800', 'Cape 2');      /*Iris Pearl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102842', '5600', 'Cape 2');      /*Pineapple Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102843', '4300', 'Cape 2');      /*Pink Cherubim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102843', '4700', 'Cape 2');      /*Pink Cherubim*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102844', '3700', 'Cape 2');      /*Run Run Bounce Bounce*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102845', '4300', 'Cape 2');      /*Blue Panda*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102847', '4700', 'Cape 2');      /*Yeonhwa School Guardian Soul Fire*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102848', '6800', 'Cape 2');      /*Gravity*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102849', '7400', 'Cape 2');      /*Gravity*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102857', '6300', 'Cape 2');      /*Legendary Fish Man*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102858', '4700', 'Cape 2');      /*Eternal Clockwork*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102859', '4300', 'Cape 2');      /*Sapphire Snow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102860', '2700', 'Cape 2');      /*British Weather Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102861', '2700', 'Cape 2');      /*Ursus Light*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102863', '2700', 'Cape 2');      /*Sparkly Rainbow Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102864', '4300', 'Cape 2');      /*Farmer's Grace*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102865', '6800', 'Cape 2');      /*Thundercrash Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102868', '3200', 'Cape 2');      /*Triple Bat Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102869', '7400', 'Cape 2');      /*Bloody Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102870', '6800', 'Cape 2');      /*Midnight Black Coffin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102872', '4700', 'Cape 2');      /*Shining Noblesse*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102873', '4700', 'Cape 2');      /*Eternal Noblesse*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102874', '3200', 'Cape 2');      /*Lumin Wings*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102875', '7400', 'Cape 2');      /*Amnesiac Alien*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102876', '6800', 'Cape 2');      /*Selfie Time*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102877', '5000', 'Cape 2');      /*Blue Marine Sunshine*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102884', '6300', 'Cape 2');      /*Angelic Polar Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102885', '3700', 'Cape 2');      /*Fluffy Fox Tail (Gold)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102886', '7400', 'Cape 2');      /*Fluffy Fox Tail (Silver)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102888', '7400', 'Cape 2');      /*Evan Dragon Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102889', '3200', 'Cape 2');      /*Evan Dragon Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102890', '7400', 'Cape 2');      /*Royal Mercedes Cape*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102891', '4700', 'Cape 2');      /*Royal Mercedes Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102892', '3200', 'Cape 2');      /*Mystic Phantom Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102893', '3700', 'Cape 2');      /*Mystic Phantom Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102894', '3200', 'Cape 2');      /*Winter Aran Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102895', '4700', 'Cape 2');      /*Winter Aran Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102896', '3700', 'Cape 2');      /*Chiaroscuro Luminous Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102897', '2700', 'Cape 2');      /*Chiaroscuro Luminous Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102898', '3200', 'Cape 2');      /*Secret Shade Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102899', '6800', 'Cape 2');      /*Secret Shade Cape*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102900', '4300', 'Cape 2');      /*Lumpy Snowflakes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102901', '5600', 'Cape 2');      /*Sparkler*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102902', '6300', 'Cape 2');      /*Dokidoki*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102903', '7400', 'Cape 2');      /*Floating Monkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102904', '6300', 'Cape 2');      /*Eternal Clockwork*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102905', '5600', 'Cape 2');      /*Today's Sunshine Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102906', '2700', 'Cape 2');      /*Snug Black Nero*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102907', '6300', 'Cape 2');      /*Ice Flower Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102910', '6300', 'Cape 2');      /*Smile Seed Cape*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102912', '4300', 'Cape 2');      /*Umbral Cloak*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102913', '7400', 'Cape 2');      /*Flower Dancer's Red Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102914', '2700', 'Cape 2');      /*Moon Dancer's Blue Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102915', '6800', 'Cape 2');      /*Concert Muse*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102916', '3200', 'Cape 2');      /*Baby Binkie Toys*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102917', '6300', 'Cape 2');      /*Angel's Cookie Backpack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102926', '3700', 'Cape 2');      /*Shark Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102927', '6300', 'Cape 2');      /*Windy Paw Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102937', '5000', 'Cape 2');      /*Fantastic Beach Cape*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102938', '5000', 'Cape 2');      /*Fantastic Beach Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102939', '3200', 'Cape 2');      /*Red Cloud Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102953', '3700', 'Cape 2');      /*Kamaitachi Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102954', '5000', 'Cape 2');      /*Owl Balloon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102955', '6800', 'Cape 2');      /*Moon Bunny Cape*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102956', '2700', 'Cape 2');      /*Dark Musician Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102957', '5000', 'Cape 2');      /*Chained Princess Chain*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102958', '4300', 'Cape 2');      /*Light Bulb Wings*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1702585', '3700', 'Transparent');      /*Universal Transparent Weapon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1002186', '6800', 'Transparent');      /*Transparent Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1012057', '6300', 'Transparent');      /*Transparent Face Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1022048', '3700', 'Transparent');      /*Transparent Eye Accessory*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1032024', '7800', 'Transparent');      /*Transparent Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072153', '6500', 'Transparent');      /*Transparent Shoes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1082102', '5700', 'Transparent');      /*Transparent Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1092064', '6500', 'Transparent');      /*Transparent Shield*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102039', '6300', 'Transparent');      /*Transparent Cape*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1342069', '6300', 'Transparent');      /*Transparent Katara*/





/*		APPEARANCE		*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160000', '3700', 'Facial Expressions');      /*Queasy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160001', '6300', 'Facial Expressions');      /*Panicky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160002', '5000', 'Facial Expressions');      /*Sweetness*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160003', '2700', 'Facial Expressions');      /*Smoochies*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160004', '6300', 'Facial Expressions');      /*Wink*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160005', '7400', 'Facial Expressions');      /*Ouch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160006', '6300', 'Facial Expressions');      /*Sparkling Eyes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160007', '4300', 'Facial Expressions');      /*Flaming*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160008', '2700', 'Facial Expressions');      /*Ray*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160009', '2700', 'Facial Expressions');      /*Goo Goo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160010', '2700', 'Facial Expressions');      /*Whoa Whoa*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160011', '3700', 'Facial Expressions');      /*Constant Sigh*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160012', '5600', 'Facial Expressions');      /*Drool*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160013', '3200', 'Facial Expressions');      /*Dragon Breath*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160014', '3200', 'Facial Expressions');      /*Bleh*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160015', '3700', 'Facial Expressions');      /*Dizzy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160016', '2700', 'Facial Expressions');      /*Awkward*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160017', '6800', 'Facial Expressions');      /*Villainous*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160019', '6800', 'Facial Expressions');      /*Queasy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160020', '6300', 'Facial Expressions');      /*Panicky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160021', '4300', 'Facial Expressions');      /*Sweetness*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160022', '7400', 'Facial Expressions');      /*Smoochies*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160023', '5600', 'Facial Expressions');      /*Wink*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160024', '5000', 'Facial Expressions');      /*Ouch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160025', '3700', 'Facial Expressions');      /*Sparkling Eyes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160026', '6300', 'Facial Expressions');      /*Flaming*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160027', '3700', 'Facial Expressions');      /*Ray*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160028', '4300', 'Facial Expressions');      /*Goo Goo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160029', '2700', 'Facial Expressions');      /*Whoa Whoa*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160030', '7400', 'Facial Expressions');      /*Constant Sigh*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160031', '7400', 'Facial Expressions');      /*Drool*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160032', '3200', 'Facial Expressions');      /*Dragon Breath*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160033', '6800', 'Facial Expressions');      /*Bleh*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160034', '5600', 'Facial Expressions');      /*Nosebleed*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160035', '6800', 'Facial Expressions');      /*Awesome*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5160036', '7400', 'Facial Expressions');      /*Troll*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010000', '5000', 'Effect');      /*Sunny Day*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010001', '2700', 'Effect');      /*Moon & the Stars*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010002', '2700', 'Effect');      /*Colorful Rainbow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010003', '4700', 'Effect');      /*Little Devil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010004', '3200', 'Effect');      /*Underwater*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010005', '3700', 'Effect');      /*Looking for Love*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010006', '4700', 'Effect');      /*Baby Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010007', '7400', 'Effect');      /*Fugitive*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010008', '6800', 'Effect');      /*Mr. Jackpot*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010009', '5000', 'Effect');      /*Martial Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010010', '2700', 'Effect');      /*Play with Me*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010011', '2700', 'Effect');      /*Loner*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010012', '3200', 'Effect');      /*Equalizer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010013', '5000', 'Effect');      /*Fireworks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010014', '4700', 'Effect');      /*Stormy Cloud*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010015', '3700', 'Effect');      /*777 Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010016', '2700', 'Effect');      /*Siren*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010017', '3700', 'Effect');      /*Twinkling Star*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010018', '3700', 'Effect');      /*Smile*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010019', '6300', 'Effect');      /*Heart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010020', '6300', 'Effect');      /*Go! Korea!*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010021', '4300', 'Effect');      /*Skeleton of Horror*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010022', '2700', 'Effect');      /*Star Trail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010023', '4700', 'Effect');      /*Pumping Heart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010024', '4300', 'Effect');      /*The Flocking Ducks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010025', '4700', 'Effect');      /*Silent Spectre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010026', '3700', 'Effect');      /*Bat Manager Effect*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010027', '3200', 'Effect');      /*Hot Head*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010028', '5600', 'Effect');      /*Indigo Flames*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010029', '5600', 'Effect');      /*Demonfyre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010030', '4300', 'Effect');      /*Nuclear Fire*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010031', '5000', 'Effect');      /*My Boyfriend*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010032', '4300', 'Effect');      /*My Girlfriend*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010033', '5600', 'Effect');      /*Sheer Fear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010034', '5600', 'Effect');      /*Christmas Tree*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010035', '5000', 'Effect');      /*Snowman*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010038', '6800', 'Effect');      /*Shower Power*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010039', '5000', 'Effect');      /*Spotlight*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010041', '3700', 'Effect');      /*Super Symphony*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010042', '3700', 'Effect');      /*Busy Bee*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010043', '4700', 'Effect');      /*Eyelighter*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010044', '2700', 'Effect');      /*Shadow Style*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010045', '4300', 'Effect');      /*Struck by Lightning*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010046', '5600', 'Effect');      /*Maple Champion*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010048', '4700', 'Effect');      /*Maple Champion*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010049', '6300', 'Effect');      /*Maple Champion*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010051', '7400', 'Effect');      /*O Maplemas Tree*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010052', '3700', 'Effect');      /*Santa Sled*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010053', '6300', 'Effect');      /*Mistletoe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010054', '4700', 'Effect');      /*Jingling Santa*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010055', '5000', 'Effect');      /*UFO*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010056', '5600', 'Effect');      /*Garden Trail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010057', '6800', 'Effect');      /*Flower Fairy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010059', '5600', 'Effect');      /*Trail of Darkness Effect*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010060', '5600', 'Effect');      /*Happy Winter Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010061', '3200', 'Effect');      /*Ace of Hearts*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010064', '4300', 'Effect');      /*Rock Band Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010065', '5000', 'Effect');      /*Scoreboard Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010066', '2700', 'Effect');      /*Disco Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010068', '4300', 'Effect');      /*Return of Angel Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010069', '6800', 'Effect');      /*Seraphim's Dark Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010070', '6300', 'Effect');      /*Sprite Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010073', '2700', 'Effect');      /*Miss Popular*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010074', '4700', 'Effect');      /*Mr. Popular*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010075', '6800', 'Effect');      /*I'm in London*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010076', '4700', 'Effect');      /*PARIS Je T'aime*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010078', '4300', 'Effect');      /*Owl Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010079', '5000', 'Effect');      /*Cygnus Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010080', '3200', 'Effect');      /*Spring Rain*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010081', '3200', 'Effect');      /*Peacock Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010082', '3700', 'Effect');      /*Shining Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010083', '5600', 'Effect');      /*Winter Wonderland*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010095', '3200', 'Effect');      /*[Sale] Winter Wonderland*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010096', '2700', 'Effect');      /*[Sale] Shining Star*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010097', '5600', 'Effect');      /*[Sale] Echo Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010098', '4300', 'Effect');      /*[Sale]  Long Lost Angel Wing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010099', '4300', 'Effect');      /*[Special] Shadow Style*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010100', '5000', 'Effect');      /*Maple Style Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010101', '3200', 'Effect');      /*Rainbow Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010102', '2700', 'Effect');      /*Sorry!*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010103', '7400', 'Effect');      /*Friends Plz*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010104', '2700', 'Effect');      /*Party Plz*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010106', '5600', 'Effect');      /*Shining Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010109', '3200', 'Effect');      /*Je t'aime Paris*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010110', '2700', 'Effect');      /*Rhinne's Protection*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010111', '3700', 'Effect');      /*Tropical Beach*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010112', '5000', 'Effect');      /*London Night Effect*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5010113', '3200', 'Effect');      /*PSY Effect*/





/*		PET		*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000000', '11700', 'Pets');      /*Brown Kitty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000001', '9800', 'Pets');      /*Brown Puppy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000002', '8400', 'Pets');      /*Pink Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000003', '11700', 'Pets');      /*Mini Kargo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000004', '7500', 'Pets');      /*Black Kitty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000005', '7500', 'Pets');      /*White Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000006', '9600', 'Pets');      /*Husky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000007', '6000', 'Pets');      /*Black Pig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000008', '8400', 'Pets');      /*Panda*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000009', '10600', 'Pets');      /*Dino Boy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000010', '9800', 'Pets');      /*Dino Girl*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000011', '7500', 'Pets');      /*Monkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000012', '8700', 'Pets');      /*White Tiger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000013', '9600', 'Pets');      /*Elephant*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000014', '9800', 'Pets');      /*Rudolph*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000015', '7500', 'Pets');      /*Dasher*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000017', '9600', 'Pets');      /*Robot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000018', '8400', 'Pets');      /*Husky*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000020', '10600', 'Pets');      /*Mini Yeti*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000021', '6600', 'Pets');      /*Monkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000022', '8700', 'Pets');      /*Turkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000023', '6600', 'Pets');      /*Penguin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000024', '10600', 'Pets');      /*Jr. Balrog*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000025', '9600', 'Pets');      /*Golden Pig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000026', '10600', 'Pets');      /*Sun Wu Kong*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000028', '14400', 'Pets');      /*Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000029', '6000', 'Pets');      /*Baby Dragon*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000030', '9800', 'Pets');      /*Green Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000031', '10600', 'Pets');      /*Red Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000032', '6000', 'Pets');      /*Blue Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000033', '10600', 'Pets');      /*Black Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000034', '14400', 'Pets');      /*Black Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000036', '8700', 'Pets');      /*Jr. Reaper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000037', '8700', 'Pets');      /*Husky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000038', '9600', 'Pets');      /*White Monkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000039', '9600', 'Pets');      /*Porcupine*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000041', '6000', 'Pets');      /*Snowman*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000042', '9600', 'Pets');      /*Kino*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000044', '6600', 'Pets');      /*Orange Tiger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000045', '7500', 'Pets');      /*Skunk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000047', '9600', 'Pets');      /*Robo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000048', '14400', 'Pets');      /*Baby Robo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000049', '7500', 'Pets');      /*Blue Robo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000050', '6600', 'Pets');      /*Red Robo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000051', '7500', 'Pets');      /*Green Robo*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000052', '9800', 'Pets');      /*Gold Robo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000053', '11700', 'Pets');      /*Gorilla Robo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000054', '8400', 'Pets');      /*Snail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000055', '9800', 'Pets');      /*Crys.Rudolph*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000056', '14400', 'Pets');      /*Toucan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000058', '9600', 'Pets');      /*White Duck*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000060', '14400', 'Pets');      /*Pink Bean*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000066', '6000', 'Pets');      /*Baby Tiger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000067', '6000', 'Pets');      /*Weird Alien*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000070', '7500', 'Pets');      /*Mir*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000071', '11700', 'Pets');      /*Ruby*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000074', '9800', 'Pets');      /*Bing Monkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000076', '14400', 'Pets');      /*Corgi Pup*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000078', '6000', 'Pets');      /*Monkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000079', '11700', 'Pets');      /*Black Kitty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000080', '11700', 'Pets');      /*Penguin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000082', '6000', 'Pets');      /*Baby Tiger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000083', '7500', 'Pets');      /*Persian Cat*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000084', '10600', 'Pets');      /*Esel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000085', '9800', 'Pets');      /*Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000086', '7500', 'Pets');      /*Pie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000087', '14400', 'Pets');      /*Black Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000088', '7500', 'Pets');      /*Black Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000089', '11700', 'Pets');      /*Tiel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000090', '8700', 'Pets');      /*Galiel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000091', '8400', 'Pets');      /*Esel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000092', '9600', 'Pets');      /*Tiel*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000093', '8700', 'Pets');      /*Galiel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000096', '8700', 'Pets');      /*Dummbo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000098', '9800', 'Pets');      /*Shark*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000100', '10600', 'Pets');      /*Kino*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000101', '11700', 'Pets');      /*White Tiger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000102', '6000', 'Pets');      /*Mini Yeti*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000103', '14400', 'Pets');      /*Chroma Bean*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000105', '14400', 'Pets');      /*Baby Tiger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000106', '8700', 'Pets');      /*Ruby*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000107', '6600', 'Pets');      /*Black Pig*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000108', '14400', 'Pets');      /*Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000109', '8400', 'Pets');      /*Pie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000110', '6000', 'Pets');      /*Corgi Pup*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000111', '8700', 'Pets');      /*Persian Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000114', '8400', 'Pets');      /*Rudolph*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000116', '6000', 'Pets');      /*Jr. Reaper*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000117', '9600', 'Pets');      /*White Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000118', '6600', 'Pets');      /*Mir*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000120', '6000', 'Pets');      /*Tiel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000121', '10600', 'Pets');      /*Esel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000122', '9800', 'Pets');      /*Galiel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000130', '11700', 'Pets');      /*Metus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000131', '8700', 'Pets');      /*Mors*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000132', '8400', 'Pets');      /*Invidia*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000133', '9800', 'Pets');      /*Storm Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000134', '9600', 'Pets');      /*Fennec Fox*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000135', '6000', 'Pets');      /*Gingerbready*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000136', '9600', 'Pets');      /*Ice Knight*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000138', '9600', 'Pets');      /*Merlion Pet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000139', '10600', 'Pets');      /*Butterfly*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000141', '8700', 'Pets');      /*Shark*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000142', '10600', 'Pets');      /*Puffram*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000143', '10600', 'Pets');      /*Craw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000144', '6000', 'Pets');      /*Adriano*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000145', '14400', 'Pets');      /*Bonkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000146', '10600', 'Pets');      /*Harp Seal*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000147', '6600', 'Pets');      /*Penguin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000148', '8400', 'Pets');      /*White Duck*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000149', '11700', 'Pets');      /*Silver Husky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000150', '11700', 'Pets');      /*Pink Yeti*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000151', '8700', 'Pets');      /*Bandit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000152', '6000', 'Pets');      /*Miracle Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000155', '10600', 'Pets');      /*Abel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000156', '10600', 'Pets');      /*Axel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000161', '9600', 'Pets');      /*Pink*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000162', '8700', 'Pets');      /*Aaron*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000163', '6000', 'Pets');      /*Mint*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000167', '9600', 'Pets');      /*Starwing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000168', '8400', 'Pets');      /*Stickman*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000170', '14400', 'Pets');      /*PSY*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000171', '6600', 'Pets');      /*MagiCookie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000172', '6000', 'Pets');      /*Mini Yeti*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000176', '8700', 'Pets');      /*Kangaroo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000193', '14400', 'Pets');      /*Von Soup*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000194', '8700', 'Pets');      /*Pink Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000195', '14400', 'Pets');      /*Black Bunny*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000197', '6000', 'Pets');      /*Sassy Snake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000198', '8400', 'Pets');      /*Lil Moonbeam*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000199', '10600', 'Pets');      /*Adel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000201', '14400', 'Pets');      /*Pink Bean*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000202', '10600', 'Pets');      /*Corgi Pup*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000203', '7500', 'Pets');      /*Craw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000204', '6600', 'Pets');      /*Adriano*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000205', '14400', 'Pets');      /*Bonkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000206', '9600', 'Pets');      /*Fennec Fox*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000207', '10600', 'Pets');      /*Corgi Pup*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000209', '9800', 'Pets');      /*Storm Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000210', '8700', 'Pets');      /*Penguin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000211', '14400', 'Pets');      /*Scurvy Bird*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000212', '9800', 'Pets');      /*Metus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000213', '11700', 'Pets');      /*Mors*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000214', '6600', 'Pets');      /*Invidia*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000215', '14400', 'Pets');      /*Chunky */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000216', '6600', 'Pets');      /*Brown Burro*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000217', '10600', 'Pets');      /*Blackheart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000221', '14400', 'Pets');      /*Harp Seal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000225', '8700', 'Pets');      /*Puffram*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000228', '6600', 'Pets');      /*Demon Metus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000229', '10600', 'Pets');      /*Demon Mors*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000230', '9600', 'Pets');      /*Invidia*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000231', '9800', 'Pets');      /*Demon Metus*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000232', '8700', 'Pets');      /*Demon Mors*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000233', '8400', 'Pets');      /*Invidia*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000234', '7500', 'Pets');      /*Metus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000235', '9600', 'Pets');      /*Mors*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000236', '14400', 'Pets');      /*Invidia*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000237', '8700', 'Pets');      /*Starwing*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000238', '9800', 'Pets');      /*Baby Tiger*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000239', '6600', 'Pets');      /*Shark*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000240', '10600', 'Pets');      /*Pink Bean*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000241', '9600', 'Pets');      /*Puffram*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000243', '6000', 'Pets');      /*Pink Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000244', '7500', 'Pets');      /*Ice Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000245', '7500', 'Pets');      /*Red Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000246', '9800', 'Pets');      /*Chroma Bean*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000249', '6000', 'Pets');      /*Fluffy Teddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000250', '6000', 'Pets');      /*Cutie Teddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000251', '11700', 'Pets');      /*Puffy Teddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000254', '9600', 'Pets');      /*Red Elly*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000255', '14400', 'Pets');      /*Blue Burro*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000256', '11700', 'Pets');      /*Pumpkin Jack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000257', '9600', 'Pets');      /*Pumpkin Zack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000258', '14400', 'Pets');      /*Pumpkin Mack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000261', '6000', 'Pets');      /*Royal Thumpy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000262', '6000', 'Pets');      /*Merlion*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000263', '6600', 'Pets');      /*Butterfly*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000264', '8400', 'Pets');      /*Kangaroo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000265', '11700', 'Pets');      /*Crys.Rudolph*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000266', '9800', 'Pets');      /*Pink Bean*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000269', '6000', 'Pets');      /*Hedgehog*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000270', '10600', 'Pets');      /*Fennec Fox*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000271', '14400', 'Pets');      /*Frumpy Koala*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000272', '6600', 'Pets');      /*Grumpy Koala*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000273', '9800', 'Pets');      /*Nerdy Koala*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000275', '7500', 'Pets');      /*Chippermunk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000276', '8400', 'Pets');      /*Chipmunch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000277', '7500', 'Pets');      /*Chubmunk*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000281', '6000', 'Pets');      /*Vile Metus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000282', '7500', 'Pets');      /*Dire Mors*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000283', '8400', 'Pets');      /*Wild Invidia*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000290', '8700', 'Pets');      /*Honey Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000291', '8700', 'Pets');      /*Lime Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000292', '9600', 'Pets');      /*Peach Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000293', '9600', 'Pets');      /*Roo-A*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000294', '9600', 'Pets');      /*Roo-B*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000295', '14400', 'Pets');      /*Roo-C*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000296', '14400', 'Pets');      /*Toasty Devil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000297', '7500', 'Pets');      /*Icy Devil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000298', '8700', 'Pets');      /*Miasma Devil*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000299', '10600', 'Pets');      /*Gingerhead*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000300', '6000', 'Pets');      /*Devil Ipos*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000301', '11700', 'Pets');      /*Devil Shaz*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000302', '10600', 'Pets');      /*Devil Ose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000303', '6000', 'Pets');      /*Devil Iros*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000304', '8700', 'Pets');      /*Devil Maz*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000305', '8700', 'Pets');      /*Devil Fose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000306', '8400', 'Pets');      /*Devil Imos*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000307', '10600', 'Pets');      /*Devil Gaz*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000308', '6600', 'Pets');      /*Devil Tose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000309', '9600', 'Pets');      /*Mini Queen*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000310', '10600', 'Pets');      /*Von Bon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000311', '6000', 'Pets');      /*Pierre*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000312', '14400', 'Pets');      /*Blue Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000314', '9800', 'Pets');      /*Sun Wu Kong*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000316', '11700', 'Pets');      /*Sassy Snake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000317', '10600', 'Pets');      /*Ice Knight*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000318', '6000', 'Pets');      /*Yeti Robot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000320', '6000', 'Pets');      /*Pinkadillo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000321', '8700', 'Pets');      /*Yellowdillo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000322', '9600', 'Pets');      /*Greenadillo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000328', '8700', 'Pets');      /*Von Soup*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000329', '9800', 'Pets');      /*Red Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000330', '9800', 'Pets');      /*Jr. Von Leon*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000331', '9800', 'Pets');      /*Jr. Orchid*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000332', '8400', 'Pets');      /*Jr. Hilla*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000337', '10600', 'Pets');      /*PSY*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000341', '9800', 'Pets');      /*Punchyroo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000342', '11700', 'Pets');      /*Unripe Nut*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000343', '6000', 'Pets');      /*Chestnut*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000344', '8400', 'Pets');      /*Burnt Nut*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000345', '6600', 'Pets');      /*Tiny Gollux*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000352', '9800', 'Pets');      /*White Candle*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000353', '14400', 'Pets');      /*Blue Candle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000354', '6000', 'Pets');      /*Grape Candle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000362', '6600', 'Pets');      /*RED Rudolph*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000363', '8700', 'Pets');      /*RED Yeti*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000364', '9800', 'Pets');      /*RED Penguin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000365', '6600', 'Pets');      /*Kiwi Puff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000366', '11700', 'Pets');      /*Berry Puff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000367', '8400', 'Pets');      /*Mango Puff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000368', '9800', 'Pets');      /*Happy Bean*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000369', '8700', 'Pets');      /*Li'l Lai*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000370', '7500', 'Pets');      /*Li'l Fort*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000371', '10600', 'Pets');      /*L'il Arby*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000375', '6600', 'Pets');      /*Pink Pengy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000376', '9800', 'Pets');      /*Purple Pengy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000377', '6600', 'Pets');      /*Blue Pengy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000381', '11700', 'Pets');      /*Toto*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000382', '8700', 'Pets');      /*Frankie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000383', '10600', 'Pets');      /*Lil Moonbeam*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000384', '6600', 'Pets');      /*Petite Mario*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000385', '7500', 'Pets');      /*Abel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000386', '11700', 'Pets');      /*Axel*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000387', '6600', 'Pets 2');      /*Adel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000402', '14400', 'Pets 2');      /*Ballet Lyn*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000403', '9600', 'Pets 2');      /*Soldier Hong*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000404', '6600', 'Pets 2');      /*Soldier Chun*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000405', '6000', 'Pets 2');      /*Green Chad*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000406', '9800', 'Pets 2');      /*Pink Mel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000407', '7500', 'Pets 2');      /*Orange Leon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000408', '6000', 'Pets 2');      /*Jr. Sierra*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000409', '8700', 'Pets 2');      /*Jr. Ryan*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000414', '10600', 'Pets 2');      /*Lil' Bobble*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000415', '14400', 'Pets 2');      /*Lil' Lotus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000416', '6600', 'Pets 2');      /*Ill Orchid*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000417', '9600', 'Pets 2');      /*Gelimer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000424', '9800', 'Pets 2');      /*Sheep*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000428', '9600', 'Pets 2');      /*Holoyeti*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000429', '6000', 'Pets 2');      /*Pink Seal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000430', '14400', 'Pets 2');      /*New Seal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000431', '6600', 'Pets 2');      /*Newer Seal*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000432', '9600', 'Pets 2');      /*Pinker Seal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000433', '14400', 'Pets 2');      /*War Sheep*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000434', '9600', 'Pets 2');      /*Mage Sheep*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000435', '9800', 'Pets 2');      /*Cleric Sheep*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000437', '6000', 'Pets 2');      /*Orange*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000442', '6600', 'Pets 2');      /*Gelimer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000443', '9600', 'Pets 2');      /*Furry Elwin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000444', '10600', 'Pets 2');      /*Fluffy Lily*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000445', '9800', 'Pets 2');      /*Baby Nero*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000446', '6600', 'Pets 2');      /*Strawbear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000447', '14400', 'Pets 2');      /*Bananabear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000448', '7500', 'Pets 2');      /*Cookiebear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000449', '9800', 'Pets 2');      /*Gengerbready*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000451', '8700', 'Pets 2');      /*Gorilla Robo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000452', '10600', 'Pets 2');      /*Squishy Bean*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000456', '6000', 'Pets 2');      /*Macha Man*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000457', '7500', 'Pets 2');      /*Lady Hot Tea*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000458', '6000', 'Pets 2');      /*Captain Cafe*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000459', '8700', 'Pets 2');      /*Black Kitty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000460', '6000', 'Pets 2');      /*Sailor Seal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000461', '10600', 'Pets 2');      /*Admiral Seal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000462', '9800', 'Pets 2');      /*Steward Seal*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000463', '8700', 'Pets 2');      /*Burnt Nut*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000464', '8700', 'Pets 2');      /*Gingerhead*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000465', '6600', 'Pets 2');      /*Orange*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000466', '10600', 'Pets 2');      /*Ducky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000468', '11700', 'Pets 2');      /*Starwing*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000469', '6600', 'Pets 2');      /*Tiny Nero*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000470', '9600', 'Pets 2');      /*Cheesy Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000471', '6600', 'Pets 2');      /*Samson Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000473', '11700', 'Pets 2');      /*Little Ursus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000474', '9600', 'Pets 2');      /*Moist Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000475', '6000', 'Pets 2');      /*Nutty Pie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000476', '11700', 'Pets 2');      /*Sweet Candy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000479', '9800', 'Pets 2');      /*Lil Zakum*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000480', '11700', 'Pets 2');      /*Ice Dragon*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000482', '7500', 'Pets 2');      /*Galiel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000483', '8700', 'Pets 2');      /*Mouse Monkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000484', '10600', 'Pets 2');      /*Lil Evan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000485', '11700', 'Pets 2');      /*Lil Aran*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000486', '14400', 'Pets 2');      /*Lil Phantom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000490', '10600', 'Pets 2');      /*Lil Luminous*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000491', '9600', 'Pets 2');      /*Lil Mercedes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000492', '14400', 'Pets 2');      /*Lil Shade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000493', '11700', 'Pets 2');      /*Persian Cat*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000494', '9600', 'Pets 2');      /*Lil Damien*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000495', '6000', 'Pets 2');      /*Lil Alicia*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000496', '9600', 'Pets 2');      /*Lil Lilin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000497', '11700', 'Pets 2');      /*Invidia*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000498', '9800', 'Pets 2');      /*Fennec Fox*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000499', '7500', 'Pets 2');      /*Stickman*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000500', '9800', 'Pets 2');      /*Turkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000501', '9600', 'Pets 2');      /*Pumpkin Jack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000502', '7500', 'Pets 2');      /*Pumpkin O'*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000503', '7500', 'Pets 2');      /*Pumpkin L*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000505', '11700', 'Pets 2');      /*Frankie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000507', '10600', 'Pets 2');      /*Blue Husky*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000508', '10600', 'Pets 2');      /*Crys.Rudolph*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000509', '14400', 'Pets 2');      /*Snowman*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000510', '10600', 'Pets 2');      /*Fluffram*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000511', '14400', 'Pets 2');      /*Jr. Von Leon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000513', '7500', 'Pets 2');      /*Jr. Hilla*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000514', '6000', 'Pets 2');      /*Macha Man*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000515', '9800', 'Pets 2');      /*Lady Hot Tea*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000516', '6600', 'Pets 2');      /*Captain Cafe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000517', '8700', 'Pets 2');      /*Hekaton*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000518', '6600', 'Pets 2');      /*Hekaton S*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000519', '6000', 'Pets 2');      /*Hekaton E*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000520', '6600', 'Pets 2');      /*Hekaton A*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000521', '7500', 'Pets 2');      /*Lil' Ninja*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000522', '9600', 'Pets 2');      /*Toucan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000524', '11700', 'Pets 2');      /*Alpaca*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000525', '10600', 'Pets 2');      /*Lil' Lotus*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000526', '8400', 'Pets 2');      /*Ill Orchid*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000527', '9800', 'Pets 2');      /*Gelimer*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000528', '7500', 'Pets 2');      /*Meerkat Mob*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000529', '8400', 'Pets 2');      /*Pudgycat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000530', '6600', 'Pets 2');      /*War Sheep*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000531', '7500', 'Pets 2');      /*Mage Sheep*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000532', '7500', 'Pets 2');      /*Cleric Sheep*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000533', '6000', 'Pets 2');      /*Furry Elwin*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000534', '8400', 'Pets 2');      /*Fluffy Lily*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000535', '6600', 'Pets 2');      /*Baby Nero*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000536', '7500', 'Pets 2');      /*Strawbear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000537', '11700', 'Pets 2');      /*Bananabear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000538', '6600', 'Pets 2');      /*Cookiebear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000545', '14400', 'Pets 2');      /*Black Bean*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000546', '8700', 'Pets 2');      /*Skunk*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000547', '11700', 'Pets 2');      /*Porcupine*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000548', '11700', 'Pets 2');      /*Roo-A*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000549', '9800', 'Pets 2');      /*Roo-B*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000550', '11700', 'Pets 2');      /*Roo-C*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000551', '14400', 'Pets 2');      /*Pink Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000552', '8400', 'Pets 2');      /*Ice Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000553', '7500', 'Pets 2');      /*Red Dragon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000554', '6600', 'Pets 2');      /*Kiwi Puff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000555', '14400', 'Pets 2');      /*Berry Puff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000556', '7500', 'Pets 2');      /*Mango Puff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000557', '10600', 'Pets 2');      /*Tiny Nero*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000558', '9800', 'Pets 2');      /*Cheesy Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000559', '6000', 'Pets 2');      /*Samson Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000561', '9600', 'Pets 2');      /*Monkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000563', '6600', 'Pets 2');      /*Mouse Monkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000568', '8400', 'Pets 2');      /*Purple Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000569', '7500', 'Pets 2');      /*Stjartmes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000570', '9800', 'Pets 2');      /*Lil Tutu*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000571', '7500', 'Pets 2');      /*Lil Nene*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000572', '6600', 'Pets 2');      /*Lil Lingling*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000573', '9600', 'Pets 2');      /*Lil Evan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000574', '6000', 'Pets 2');      /*Lil Aran*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000575', '6000', 'Pets 2');      /*Lil Phantom*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000576', '6600', 'Pets 2');      /*Lil Luminous*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000577', '10600', 'Pets 2');      /*Lil Mercedes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000578', '6000', 'Pets 2');      /*Lil Shade*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000579', '9600', 'Pets 2');      /*Gorilla Robo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000580', '14400', 'Pets 2');      /*Lil Damien*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000581', '10600', 'Pets 2');      /*Lil Alicia*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000582', '10600', 'Pets 2');      /*Lil Lilin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000585', '14400', 'Pets 2');      /*Lil Tengu*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000586', '7500', 'Pets 2');      /*Beagle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000587', '7500', 'Pets 2');      /*Salem Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000588', '8700', 'Pets 2');      /*Binx Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000589', '14400', 'Pets 2');      /*Kit Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000590', '8700', 'Pets 2');      /*Bichon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000600', '7500', 'Pets 2');      /*Ursie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000601', '7500', 'Pets 2');      /*Punch Cat*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000602', '8400', 'Pets 2');      /*Iron Rabbit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000604', '6000', 'Pets 2');      /*Brown Kitty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000605', '8700', 'Pets 2');      /*Lil Zakum*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000606', '9600', 'Pets 2');      /*Stjartmes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000607', '8400', 'Pets 2');      /*Lil Tutu*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000608', '6600', 'Pets 2');      /*Lil Nene*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000609', '8400', 'Pets 2');      /*Lil Lingling*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000610', '10600', 'Pets 2');      /*Moist Cake*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000611', '7500', 'Pets 2');      /*Purple Cake*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000612', '6000', 'Pets 2');      /*Lil Moonbeam*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000613', '11700', 'Pets 2');      /*Jr. Hilla*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000614', '11700', 'Pets 2');      /*Jr. Orchid*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000615', '9600', 'Pets 2');      /*Jr. Von Leon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000617', '8400', 'Pets 2');      /*Stjartmes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000618', '9600', 'Pets 2');      /*Stjartmes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000623', '8700', 'Pets 2');      /*Esel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000626', '9600', 'Pets 2');      /*Lil Damien*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000627', '14400', 'Pets 2');      /*Lil Alicia*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000628', '6000', 'Pets 2');      /*Lil Lilin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000629', '14400', 'Pets 2');      /*Merlion Pet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000630', '9800', 'Pets 2');      /*Toucan*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000631', '10600', 'Pets 2');      /*Lil Tengu*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000632', '14400', 'Pets 2');      /*Beagle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000636', '6000', 'Pets 2');      /*Salem Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000637', '6600', 'Pets 2');      /*Binx Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000638', '6000', 'Pets 2');      /*Kit Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000639', '6600', 'Pets 2');      /*Bichon*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000644', '6000', 'Pets 2');      /*Craw*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000645', '8700', 'Pets 2');      /*Adriano*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000646', '9800', 'Pets 2');      /*Bonkey*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000647', '14400', 'Pets 2');      /*Mage Sheep*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000648', '11700', 'Pets 2');      /*War Sheep*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000649', '10600', 'Pets 2');      /*Cleric Sheep*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000650', '8400', 'Pets 2');      /*Furry Elwin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000651', '9600', 'Pets 2');      /*Fluffy Lily*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5000652', '9600', 'Pets 2');      /*Baby Nero*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802000', '2700', 'Pet Appearance');      /*Red Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802001', '7400', 'Pet Appearance');      /*Yellow Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802002', '7400', 'Pet Appearance');      /*Red Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802003', '3200', 'Pet Appearance');      /*Black Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802004', '3700', 'Pet Appearance');      /*Pink Laced Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802005', '5000', 'Pet Appearance');      /*Sky Blue Lace Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802006', '4700', 'Pet Appearance');      /*Blue Top Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802007', '4300', 'Pet Appearance');      /*Red Top Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802008', '5600', 'Pet Appearance');      /*Rudolph's Hat*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802009', '6300', 'Pet Appearance');      /*Tree Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802010', '6300', 'Pet Appearance');      /*Mushroom Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802011', '4700', 'Pet Appearance');      /*Red Fur Coat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802012', '6800', 'Pet Appearance');      /*Chestnut Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802013', '3200', 'Pet Appearance');      /*Red Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802014', '7400', 'Pet Appearance');      /*Mini Kargo Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802015', '2700', 'Pet Appearance');      /*Dino King & Queen*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802016', '3700', 'Pet Appearance');      /*Husky's Yellow Tights*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802017', '5000', 'Pet Appearance');      /*Monkey Sack*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802018', '7400', 'Pet Appearance');      /*Panda's Clown Costume*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802019', '4300', 'Pet Appearance');      /*Rudolph's Sleigh*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802020', '4300', 'Pet Appearance');      /*White Tiger's Thief Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802021', '6800', 'Pet Appearance');      /*Elephant Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802022', '6800', 'Pet Appearance');      /*Aladin Vest*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802023', '6300', 'Pet Appearance');      /*Pelvis Hair*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802024', '5600', 'Pet Appearance');      /*White Tiger the Wizard*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802025', '5600', 'Pet Appearance');      /*Bunny Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802026', '3200', 'Pet Appearance');      /*Prince Pepe*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802027', '6800', 'Pet Appearance');      /*Husky's Bare Bones*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802028', '2700', 'Pet Appearance');      /*Dino Ghosty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802029', '5000', 'Pet Appearance');      /*Panda's Pet-o-Lantern*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802030', '5000', 'Pet Appearance');      /*Penguin Earmuff Set*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802031', '4700', 'Pet Appearance');      /*Cowboy Kargo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802032', '4300', 'Pet Appearance');      /*Snowboard Gear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802033', '2700', 'Pet Appearance');      /*Crimson Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802034', '6800', 'Pet Appearance');      /*White Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802035', '5600', 'Pet Appearance');      /*Cute Beggar Overall*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802036', '6300', 'Pet Appearance');      /*Golden Pig Fortune Pouch*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802037', '5600', 'Pet Appearance');      /*Husky's Oinker Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802038', '4300', 'Pet Appearance');      /*Mini Celestial Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802039', '6800', 'Pet Appearance');      /*Golden Pig Lucky Sack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802042', '6800', 'Pet Appearance');      /*Baby Turkey Carriage*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802044', '7400', 'Pet Appearance');      /*Dragon's soul*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802045', '7400', 'Pet Appearance');      /*Jr. Reaper's Guitar */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802046', '7400', 'Pet Appearance');      /*Rabbit Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802047', '5600', 'Pet Appearance');      /*Porcupine Sunglasses*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802048', '7400', 'Pet Appearance');      /*Dragon Armor*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802049', '6800', 'Pet Appearance');      /*Jr. Reaper's Sign (I'm with stoopid)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802050', '3700', 'Pet Appearance');      /*Jr. Reaper's Sign (<--Noob)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802051', '4300', 'Pet Appearance');      /*Jr. Reaper's Sign (cc plz)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802052', '4700', 'Pet Appearance');      /*Jr. Reaper's Sign (I love pie)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802053', '2700', 'Pet Appearance');      /*Snowman Gear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802054', '6300', 'Pet Appearance');      /*Kino's Green Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802055', '3200', 'Pet Appearance');      /*Gas Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802059', '3200', 'Pet Appearance');      /*Jail Bird Pet Costume*/
/*Page 7*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802060', '6300', 'Pet Appearance');      /*Crystal Rudolph's Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802061', '3700', 'Pet Appearance');      /*Scuba Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802062', '4700', 'Pet Appearance');      /*Starry Stereo Headset*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802063', '3700', 'Pet Appearance');      /*Baby Tiger Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802064', '4300', 'Pet Appearance');      /*Alien's Pet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802065', '5600', 'Pet Appearance');      /*Baby Tiger Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802066', '5000', 'Pet Appearance');      /*Dragon Egg Shell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802067', '6800', 'Pet Appearance');      /*Scuba Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802068', '2700', 'Pet Appearance');      /*Gas Mask*/
/*Page 8*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802070', '2700', 'Pet Appearance');      /*Pilot's Cat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802071', '4700', 'Pet Appearance');      /*Pink Oxygen Tank*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802072', '4700', 'Pet Appearance');      /*Caught Fish*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802073', '6800', 'Pet Appearance');      /*Blue Birdy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802077', '6800', 'Pet Appearance');      /*Mango Creampuff Wing's*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802078', '6300', 'Pet Appearance');      /*Esel's Coronet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802079', '3200', 'Pet Appearance');      /*B-Day Candle*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802080', '6300', 'Pet Appearance');      /*Tiel's Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802081', '4300', 'Pet Appearance');      /*Galiel's Angel Star*/
/*Page 9*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802082', '6800', 'Pet Appearance');      /*Pink Yeti's Blue BFF*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802083', '5600', 'Pet Appearance');      /*Silver Husky's Hip Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802084', '2700', 'Pet Appearance');      /*Dummbo's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802085', '6800', 'Pet Appearance');      /*Red Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802086', '3700', 'Pet Appearance');      /*Mini Kargo Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802087', '6800', 'Pet Appearance');      /*Blue Top Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802088', '5000', 'Pet Appearance');      /*Red Top Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802089', '3700', 'Pet Appearance');      /*Yellow Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802090', '5000', 'Pet Appearance');      /*Red Hat*/
/*Page 10*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802091', '4700', 'Pet Appearance');      /*Black Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802092', '4300', 'Pet Appearance');      /*Pink Laced Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802093', '6300', 'Pet Appearance');      /*Sky Blue Laced Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802094', '5000', 'Pet Appearance');      /*Red Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802095', '3200', 'Pet Appearance');      /*Mushroom Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802096', '5600', 'Pet Appearance');      /*Husky Yellow Tights*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802097', '7400', 'Pet Appearance');      /*Dino King & Queen*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802098', '7400', 'Pet Appearance');      /*Bunny Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802099', '6300', 'Pet Appearance');      /*Monkey Sack*/
/*Page 11*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802100', '4700', 'Pet Appearance');      /*Pet Collar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802101', '4700', 'Pet Appearance');      /*Pet Label Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802300', '4700', 'Pet Appearance');      /*Bare Bones*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802301', '3200', 'Pet Appearance');      /*Ghosty*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802302', '4700', 'Pet Appearance');      /*Pet-o-Lantern*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802303', '5600', 'Pet Appearance');      /*Clown Dress*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802304', '5600', 'Pet Appearance');      /*Penguin Earmuff Set*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802305', '4300', 'Pet Appearance');      /*White Tiger Suit*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802306', '3200', 'Pet Appearance');      /*Oinker Suit*/
/*Page 12*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802307', '3200', 'Pet Appearance');      /*Pelvis Hair*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802308', '6800', 'Pet Appearance');      /*Prince Pepe*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802309', '6300', 'Pet Appearance');      /*Crimson Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802310', '7400', 'Pet Appearance');      /*Cowboy Kargo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802311', '7400', 'Pet Appearance');      /*White Angel*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802312', '5600', 'Pet Appearance');      /*Guitar */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802313', '4700', 'Pet Appearance');      /*Cute Beggar Overall*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802314', '7400', 'Pet Appearance');      /*Baby Turkey Carriage*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802315', '3200', 'Pet Appearance');      /*Dragon Armor*/
/*Page 13*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802316', '6800', 'Pet Appearance');      /*Porcupine Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802317', '3200', 'Pet Appearance');      /*Jr. Reaper Sign (I'm with stoopid)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802318', '2700', 'Pet Appearance');      /*Jr. Reaper Sign (cc plz)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802319', '4300', 'Pet Appearance');      /*Snowman Gear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802320', '6300', 'Pet Appearance');      /*Jr. Reaper Sign (<--Noob)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802321', '3200', 'Pet Appearance');      /*Jr. Reaper Sign (I love pie)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802322', '4300', 'Pet Appearance');      /*Chestnut Cap*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802323', '4700', 'Pet Appearance');      /*Gas Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802324', '5000', 'Pet Appearance');      /*Jail Bird Pet Costume*/
/*Page 14*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802325', '5000', 'Pet Appearance');      /*Scuba Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802326', '3200', 'Pet Appearance');      /*Kino's Green Mushroom Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802327', '6800', 'Pet Appearance');      /*Starry Stereo Headset*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802328', '2700', 'Pet Appearance');      /*Baby Tiger Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802329', '6300', 'Pet Appearance');      /*Alien's Pet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802330', '6800', 'Pet Appearance');      /*Dragon Egg Shell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802331', '2700', 'Pet Appearance');      /*Rabbit Ears*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802332', '5600', 'Pet Appearance');      /*Pink Oxygen Tank*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802333', '3200', 'Pet Appearance');      /*B-Day Candle*/
/*Page 15*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802334', '4700', 'Pet Appearance');      /*Fish*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802335', '4300', 'Pet Appearance');      /*Blue Birdy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802336', '5000', 'Pet Appearance');      /*Mini Celestial Wand*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802337', '5600', 'Pet Appearance');      /*Tube*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802338', '4300', 'Pet Appearance');      /*Pink Bean's Headset*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802339', '3700', 'Pet Appearance');      /*Blue Birdy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802340', '5000', 'Pet Appearance');      /*Craw's Pirate Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802341', '2700', 'Pet Appearance');      /*Adriano's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802342', '6800', 'Pet Appearance');      /*Bonkey's Ammunition Box*/
/*Page 16*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802343', '6800', 'Pet Appearance');      /*Starry Muffler*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802344', '5600', 'Pet Appearance');      /*Parrot Admiral Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802345', '4300', 'Pet Appearance');      /*Penguin Earmuff Set*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802346', '5600', 'Pet Appearance');      /*Ghost of Fear*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802347', '6800', 'Pet Appearance');      /*Ghost of Death*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802348', '6800', 'Pet Appearance');      /*Ghost of Jealousy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802349', '5600', 'Pet Appearance');      /*Dragon Orb*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802350', '2700', 'Pet Appearance');      /*Caught Fish*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802351', '5000', 'Pet Appearance');      /*Bean's Headset*/
/*Page 17*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802352', '4700', 'Pet Appearance');      /*Bandit Goggles*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802353', '6800', 'Pet Appearance');      /*Sanchito's Carrot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802354', '2700', 'Pet Appearance');      /*Black-hearted Earrings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802365', '5000', 'Pet Appearance');      /*Harp Seal Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802366', '4700', 'Pet Appearance');      /*Puffram's Golden Horn*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802367', '2700', 'Pet Appearance');      /*Gingerbready Bow Tie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802368', '5600', 'Pet Appearance');      /*Frost Mallet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802369', '5600', 'Pet Appearance');      /*Tiny Fright*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802370', '5600', 'Pet Appearance');      /*Tiny Sadness*/
/*Page 18*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802371', '6800', 'Pet Appearance');      /*Tiny Envy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802372', '3200', 'Pet Appearance');      /*Sunglass*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802373', '6300', 'Pet Appearance');      /*Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802375', '4300', 'Pet Appearance');      /*Starwing's Star Trail*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802378', '3200', 'Pet Appearance');      /*Shark's Mini Tube*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802380', '4700', 'Pet Appearance');      /*Blue Light Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802381', '5600', 'Pet Appearance');      /*Golden Light Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802382', '7400', 'Pet Appearance');      /*Purple Light Ring*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802384', '6300', 'Pet Appearance');      /*Fluffy Teddy's Bunny Ears*/
/*Page 19*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802385', '3200', 'Pet Appearance');      /*Cutie Teddy's Baby Bonnet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802386', '6300', 'Pet Appearance');      /*Puffy Teddy's Crown*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802387', '6300', 'Pet Appearance');      /*Red Elly's Dress Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802388', '6800', 'Pet Appearance');      /*Blue Burro's Toy Carrot*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802389', '3700', 'Pet Appearance');      /*Pumpkin Jack's Magic Lantern*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802390', '2700', 'Pet Appearance');      /*Pumpkin Zack's Magic Lantern*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802391', '7400', 'Pet Appearance');      /*Pumpkin Mack's Magic Lantern*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802392', '3200', 'Pet Appearance');      /*Boxing Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802394', '5600', 'Pet Appearance');      /*Baby Frumpy Koala*/
/*Page 20*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802395', '3700', 'Pet Appearance');      /*Baby Grumpy Koala*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802396', '2700', 'Pet Appearance');      /*Baby Nerdy Koala*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802418', '4700', 'Pet Appearance');      /*Chippermunk's Acorn */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802419', '4700', 'Pet Appearance');      /*Chipmunch's Acorn*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802420', '2700', 'Pet Appearance');      /*Chubmunk's Acorn*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802424', '5000', 'Pet Appearance');      /*Honey Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802425', '5600', 'Pet Appearance');      /*Lime Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802426', '6300', 'Pet Appearance');      /*Peach Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802427', '4700', 'Pet Appearance');      /*Roo-A Baby Bonnet*/
/*Page 21*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802428', '7400', 'Pet Appearance');      /*Roo-B Baby Bonnet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802429', '4700', 'Pet Appearance');      /*Roo-C Baby Bonnet*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802430', '5000', 'Pet Appearance');      /*Yellow Devil's Collar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802431', '4700', 'Pet Appearance');      /*Red Devil's Collar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802432', '4300', 'Pet Appearance');      /*Blue Devil's Collar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802433', '4300', 'Pet Appearance');      /*Blazing Horns*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802434', '3200', 'Pet Appearance');      /*Chilling Horns*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802435', '4300', 'Pet Appearance');      /*Miasmic Horns*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802436', '2700', 'Pet Appearance');      /*Gingerbread Bow Tie*/
/*Page 22*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802444', '7400', 'Pet Appearance');      /*Alluring Mirror*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802445', '4700', 'Pet Appearance');      /*Von Bon's Staff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802446', '3200', 'Pet Appearance');      /*Pierre's Umbrella*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802447', '3700', 'Pet Appearance');      /*Snake's Pink Bow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802448', '3700', 'Pet Appearance');      /*Ice Stick*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802449', '7400', 'Pet Appearance');      /*Yeti Robot Antenna*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802450', '6800', 'Pet Appearance');      /*Pinkadillo Star Ball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802451', '5600', 'Pet Appearance');      /*Yellowdillow Circus Ball*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802452', '3200', 'Pet Appearance');      /*Greenadillo Soccer Ball*/
/*Page 23*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802458', '2700', 'Pet Appearance');      /*Hot Pot Von Bon's Staff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802459', '5600', 'Pet Appearance');      /*Ifia's Rose*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802460', '7400', 'Pet Appearance');      /*Orchid's Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802461', '6800', 'Pet Appearance');      /*Hilla's Blackheart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802462', '5000', 'Pet Appearance');      /*Gentleman Bow Tie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802463', '5000', 'Pet Appearance');      /*Kangaroo Boxing Gloves*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802464', '5600', 'Pet Appearance');      /*Unripe Chestnut Leaf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802465', '6800', 'Pet Appearance');      /*Chestnut Leaf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802466', '5000', 'Pet Appearance');      /*Burnt Chestnut Leaf*/
/*Page 24*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802467', '6800', 'Pet Appearance');      /*Gollux's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802471', '2700', 'Pet Appearance');      /*Purple Kid Pumpkin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802472', '2700', 'Pet Appearance');      /*Green Kid Pumpkin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802473', '4700', 'Pet Appearance');      /*Black Kid Pumpkin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802474', '4300', 'Pet Appearance');      /*Little RED Admin*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802475', '6300', 'Pet Appearance');      /*Kiwi Puff Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802476', '2700', 'Pet Appearance');      /*Berry Puff Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802477', '4700', 'Pet Appearance');      /*Mango Puff Wings*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802478', '6800', 'Pet Appearance');      /*Happy Bean's Hat*/
/*Page 25*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802479', '6800', 'Pet Appearance');      /*Li'l Lai's Necklace*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802480', '6800', 'Pet Appearance');      /*Li'l Fort's Scarf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802481', '6800', 'Pet Appearance');      /*Li'l Arby's Bell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802482', '6800', 'Pet Appearance');      /*Pink Pengy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802483', '5000', 'Pet Appearance');      /*Purple Pengy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802484', '4300', 'Pet Appearance');      /*Blue Pengy Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802488', '4700', 'Pet Appearance');      /*Cloud Bag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802489', '7400', 'Pet Appearance');      /*Frankie's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802490', '6800', 'Pet Appearance');      /*Devil Bat*/
/*Page 26*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802491', '6800', 'Pet Appearance');      /*Lil Moonbeam's Hairband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802492', '7400', 'Pet Appearance');      /*Helium Filled Dreams*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802493', '3700', 'Pet Appearance');      /*Cute Rabbit Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802497', '4300', 'Pet Appearance');      /*Moon Miho*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802500', '6300', 'Pet Appearance');      /*Lyn's Tiara*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802501', '4300', 'Pet Appearance');      /*Hong's Heart*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802502', '4300', 'Pet Appearance');      /*Chun's Ambition*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802503', '7400', 'Pet Appearance');      /*Chameleon's Rainbow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802504', '2700', 'Pet Appearance');      /*Orange Electronic Display*/
/*Page 27*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802505', '5000', 'Pet Appearance');      /*Purple Electronic Display*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802509', '3700', 'Pet Appearance');      /*Lil' Bobble Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802510', '4300', 'Pet Appearance');      /*Lotus's Aura*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802511', '6800', 'Pet Appearance');      /*Orchid's Tiny IV*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802512', '6300', 'Pet Appearance');      /*Gelimer's Teddy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802519', '4700', 'Pet Appearance');      /*Fluffram Ribbon (Pet Equip)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802520', '2700', 'Pet Appearance');      /*Matcha Man's Leaf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802521', '5600', 'Pet Appearance');      /*Lady Hot Tea's Spoon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802522', '4300', 'Pet Appearance');      /*Captain Cafe's Whipped Cream*/
/*Page 28*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802524', '6300', 'Pet Appearance');      /*New Pink Harp Seal Hat*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802526', '4300', 'Pet Appearance');      /*Warrior Sheep Sword*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802527', '3200', 'Pet Appearance');      /*Mage Sheep Cane*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802528', '4300', 'Pet Appearance');      /*Cleric Sheep Staff*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802529', '2700', 'Pet Appearance');      /*Orange Leaf*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802530', '4300', 'Pet Appearance');      /*Furry Elwin's Necklace*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802531', '7400', 'Pet Appearance');      /*Fluffy Lily's Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802532', '6300', 'Pet Appearance');      /*Baby Nero's Ball of Yarn*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802534', '6300', 'Pet Appearance');      /*Strawbear Fork*/
/*Page 29*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802535', '7400', 'Pet Appearance');      /*Bananabear Fork*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802536', '6300', 'Pet Appearance');      /*Cookiebear Fork*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802537', '7400', 'Pet Appearance');      /*Fancy Fox Mask*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802538', '5000', 'Pet Appearance 2');      /*Fox Mask*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802539', '6300', 'Pet Appearance 2');      /*Sailor Seal Star Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802540', '7400', 'Pet Appearance 2');      /*Admiral Seal Star Glasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802541', '5600', 'Pet Appearance 2');      /*Steward Seal Star Glass*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802542', '7400', 'Pet Appearance 2');      /*Ducky's Suave Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802543', '6800', 'Pet Appearance 2');      /*Tiny Nero's Transformation Set*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802544', '3700', 'Pet Appearance 2');      /*Cheesy Cat's Purple Yarn*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802545', '3700', 'Pet Appearance 2');      /*Samson Cat's Emerald Yarn*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802546', '7400', 'Pet Appearance 2');      /*Meerkat Instrument*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802547', '5600', 'Pet Appearance 2');      /*Pudgycat Fancytie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802548', '5600', 'Pet Appearance 2');      /*Cake Temptation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802549', '4700', 'Pet Appearance 2');      /*Pie Temptation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802550', '6300', 'Pet Appearance 2');      /*Candy Temptation*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802551', '6800', 'Pet Appearance 2');      /*Lil Zakum's Black Sunglasses*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802552', '3200', 'Pet Appearance 2');      /*Mousy Overalls*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802553', '7400', 'Pet Appearance 2');      /*Evan's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802554', '3200', 'Pet Appearance 2');      /*Aran's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802555', '6300', 'Pet Appearance 2');      /*Phantom's Halo*/
/*Page 3*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802556', '4300', 'Pet Appearance 2');      /*Luminous's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802557', '4700', 'Pet Appearance 2');      /*Mercedes's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802558', '6300', 'Pet Appearance 2');      /*Shade's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802559', '6800', 'Pet Appearance 2');      /*Damien's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802560', '2700', 'Pet Appearance 2');      /*Alicia's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802561', '5000', 'Pet Appearance 2');      /*Lilin's Halo*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802562', '2700', 'Pet Appearance 2');      /*Ursie's Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802563', '4300', 'Pet Appearance 2');      /*Gym Cat Dumbbell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802564', '6300', 'Pet Appearance 2');      /*Iron Rabbit Engine*/
/*Page 4*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802565', '4700', 'Pet Appearance 2');      /*Cloud's Lollipop Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802566', '5600', 'Pet Appearance 2');      /*Moss's Lollipop Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802567', '6300', 'Pet Appearance 2');      /*Pinkie's Lollipop Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802568', '6300', 'Pet Appearance 2');      /*Mini Stjartmes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802569', '5600', 'Pet Appearance 2');      /*Lingling's Bell*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802570', '4700', 'Pet Appearance 2');      /*Nene's Flower*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802571', '5600', 'Pet Appearance 2');      /*TuTu's Umbrella*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802572', '2700', 'Pet Appearance 2');      /*Blue Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802573', '7400', 'Pet Appearance 2');      /*Pink Ribbon*/
/*Page 5*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802574', '5600', 'Pet Appearance 2');      /*Purple Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802575', '4700', 'Pet Appearance 2');      /*Baby Chickie*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802576', '5600', 'Pet Appearance 2');      /*Anguish Crow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802577', '6800', 'Pet Appearance 2');      /*Fallen Angel Headband*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802578', '3700', 'Pet Appearance 2');      /*Fondue's Ribbon Collar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802579', '6800', 'Pet Appearance 2');      /*Sasha's Ribbon Collar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802580', '3700', 'Pet Appearance 2');      /*Coco's Ribbon Collar*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802581', '6800', 'Pet Appearance 2');      /*Witch's Red Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802582', '6800', 'Pet Appearance 2');      /*Witch's Purple Ribbon*/
/*Page 6*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802583', '3700', 'Pet Appearance 2');      /*Witch's Pink Ribbon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1802584', '7400', 'Pet Appearance 2');      /*Red Bow Tie*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5380000', '2700', 'Pet Use');      /*The Rock of Evolution*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5170000', '2700', 'Pet Use');      /*Pet Name Tag*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5180000', '3700', 'Pet Use');      /*Water of Life*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5689000', '4100', 'Pet Use');      /*Premium Water of Life*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5460000', '3300', 'Pet Use');      /*Pet Snack*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781000', '5700', 'Pet Use');      /*Bean Dye Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781001', '5700', 'Pet Use');      /*Pink Bean Dye Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781002', '5700', 'Pet Use');      /*Demon Pet Dye Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781004', '5700', 'Pet Use');      /*Roo-bot Paint Coupon*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781006', '5700', 'Pet Use');      /*Dillo Dye Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781007', '5700', 'Pet Use');      /*Chestnut Dye Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781008', '5700', 'Pet Use');      /*Candle Pet Dye Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781009', '5700', 'Pet Use');      /*Creampuff Pet Dye Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781010', '5700', 'Pet Use');      /*Pengy Pet Dye Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781011', '5700', 'Pet Use');      /*Chihuahua Dye Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781013', '5700', 'Pet Use');      /*Chameleon Pet Dye Coupon*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5781014', '5700', 'Pet Use');      /*Chubmunk Pet Dye Coupon*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5249000', '1000', 'Pet Food');      /*Premium Pet Food*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5249000', '5400', 'Pet Food', '6');      /*Premium Pet Food (6)*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190000', '6800', 'Pet Skills');      /*Item Pick-up Skill*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190001', '5000', 'Pet Skills');      /*Auto HP Potion Skill*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190002', '5600', 'Pet Skills');      /*Expanded Auto Move Skill*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190003', '7400', 'Pet Skills');      /*Auto Move Skill*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190004', '3200', 'Pet Skills');      /*Expired Pickup Skill*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190005', '4700', 'Pet Skills');      /*Ignore Item Skill */
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190006', '4300', 'Pet Skills');      /*Auto MP Potion Skill*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190009', '6800', 'Pet Skills');      /*Auto All Cure Skill*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190010', '3200', 'Pet Skills');      /*Auto Buff Skill*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190011', '7400', 'Pet Skills');      /*Auto Feed and Movement Skill*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190012', '7400', 'Pet Skills');      /*Fatten Up Skill*/





/*		MESSENGER AND SOCIAL		*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120000', '4300', 'Weather Effects');      /*Snowy Snow*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120001', '6800', 'Weather Effects');      /*Sprinkled Flowers*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120002', '4700', 'Weather Effects');      /*Soap Bubbles*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120003', '3700', 'Weather Effects');      /*Snowflakes*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120004', '7400', 'Weather Effects');      /*Sprinkled Presents*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120005', '3700', 'Weather Effects');      /*Sprinkled Chocolate*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120006', '3700', 'Weather Effects');      /*Sprinkled Flower Petals*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120007', '5000', 'Weather Effects');      /*Sprinkled Candy*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120008', '2700', 'Weather Effects');      /*Sprinkled Maple Leaves*/
/*Page 2*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120009', '4700', 'Weather Effects');      /*Fireworks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120010', '3700', 'Weather Effects');      /*Sprinkled Coke*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120011', '4700', 'Weather Effects');      /*Spirit Haunt*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120012', '4700', 'Weather Effects');      /*Holiday Sock*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120014', '3200', 'Weather Effects');      /*Christmas Socks*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5120015', '6800', 'Weather Effects');      /*Chinese Lantern Firecrackers*/