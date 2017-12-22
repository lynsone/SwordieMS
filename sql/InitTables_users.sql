DROP TABLE IF EXISTS characters;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
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
	characters int,
	PRIMARY KEY (id),
    foreign key (characters) references characters(id)
);

INSERT INTO `users` (`username`, `password`, `gmLevel`, `chatUnblockDate`, `creationDate`) VALUES ('admin', 'admin', '7', '0', '0');