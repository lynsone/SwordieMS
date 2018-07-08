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


insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('2000000', 'Favorites', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1000000', 'Equips', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1000100', 'Cap', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1000200', 'Overall', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1000300', 'Shoes', '0', '100');
insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('1000400', 'Swords', '0', '100');

insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1000008', '1340', 'cap'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1050019', '2000', 'overall'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1072260', '1000', 'shoes'); 

insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1102601', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232059', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232060', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232061', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232071', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232075', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232078', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232081', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232084', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232092', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232093', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232095', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232096', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232109', '1337', 'swords'); 
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('1232110', '1337', 'swords'); 