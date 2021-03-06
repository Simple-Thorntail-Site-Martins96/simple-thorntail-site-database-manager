CREATE TABLE `SIMPLE_SITE_USERS` (
	`ID` INT NOT NULL AUTO_INCREMENT,
	`USERNAME` VARCHAR(50) NOT NULL,
	`PASSWORD` VARCHAR(255) NOT NULL,
	`ROLES` VARCHAR(100) NULL DEFAULT NULL,
	`NAME` VARCHAR(255) NULL DEFAULT NULL,
	`SURNAME` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`ID`),
	UNIQUE INDEX `ID` (`ID`)
)
COLLATE='latin1_swedish_ci'
;
SELECT `DEFAULT_COLLATION_NAME` FROM `information_schema`.`SCHEMATA` WHERE `SCHEMA_NAME`='simple_site';
SHOW TABLE STATUS FROM `simple_site`;
SHOW FUNCTION STATUS WHERE `Db`='simple_site';
SHOW PROCEDURE STATUS WHERE `Db`='simple_site';
SHOW TRIGGERS FROM `simple_site`;

/*-----------------------------------------------------------------------------------------------------*/

INSERT INTO `simple_site`.`SIMPLE_SITE_USERS` (`USERNAME`, `PASSWORD`, `ROLES`, `NAME`, `SURNAME`) 
VALUES ('luca', '5B11618C2E44027877D0CD0921ED166B9F176F50587FC91E7534DD2946DB77D6', 'dev,test', 'Luca', 'Martinelli');
INSERT INTO `simple_site`.`SIMPLE_SITE_USERS` (`USERNAME`, `PASSWORD`, `ROLES`, `NAME`, `SURNAME`) 
VALUES ('developer', '5B11618C2E44027877D0CD0921ED166B9F176F50587FC91E7534DD2946DB77D6', 'dev', 'Dev', 'Eleoper');
INSERT INTO `simple_site`.`SIMPLE_SITE_USERS` (`USERNAME`, `PASSWORD`, `ROLES`, `NAME`, `SURNAME`) 
VALUES ('tester', '5b11618c2e44027877d0cd0921ed166b9f176f50587fc91e7534dd2946db77d6', 'test', 'Test', 'Re-test');