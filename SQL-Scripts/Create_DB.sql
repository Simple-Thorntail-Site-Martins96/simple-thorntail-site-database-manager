CREATE DATABASE `simple_site` /*!40100 COLLATE 'utf8_bin' */;
SHOW DATABASES;
/* Collegamento alla sessione “Local MySql” */
SELECT `DEFAULT_COLLATION_NAME` FROM `information_schema`.`SCHEMATA` WHERE `SCHEMA_NAME`='simple_site';
SHOW TABLE STATUS FROM `simple_site`;
SHOW FUNCTION STATUS WHERE `Db`='simple_site';
SHOW PROCEDURE STATUS WHERE `Db`='simple_site';
SHOW TRIGGERS FROM `simple_site`;

CREATE USER 'simple_site_user'@'localhost' IDENTIFIED BY 'Me6aKI6ENoCi';
GRANT SELECT, SHOW VIEW, SHOW DATABASES, PROCESS, EXECUTE  ON *.* TO 'simple_site_user'@'localhost';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'simple_site_user'@'localhost';