drop database banking_app;
create database banking_app;

use banking_app;

CREATE TABLE user_info (
user_id VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL,
mobile VARCHAR(100) NOT NULL,
name VARCHAR(100) NOT NULL,
account_number VARCHAR(100) NOT NULL,
PRIMARY KEY ( user_id )
);

CREATE TABLE account_info (
user_id VARCHAR(100) NOT NULL,
account_number VARCHAR(100) NOT NULL,
balance DECIMAL NOT NULL,
PRIMARY KEY ( user_id )
);

CREATE TABLE registered_user (
user_id VARCHAR(100) NOT NULL,
password VARCHAR(100) NOT NULL,
PRIMARY KEY ( user_id )
);

DELIMITER $$

CREATE PROCEDURE GET_USER_DETAILS(IN uid VARCHAR(100))
BEGIN
	SELECT 	*
	FROM user_info
	where user_id = uid;
END;

CREATE PROCEDURE GET_ACCOUNT_DETAILS(IN acc VARCHAR(100))
BEGIN
	SELECT 	*
	FROM account_info
	where account_number = acc;
END;

CREATE PROCEDURE GET_REGISTERED_USER(IN uid VARCHAR(100))
BEGIN
	SELECT 	*
	FROM registered_user
	where user_id = uid;
END;

CREATE PROCEDURE INSERT_USER_DETAILS(IN user_idx VARCHAR(100),
						 IN emailx VARCHAR(100),
						 IN mobilex VARCHAR(100),
						 IN namex VARCHAR(100),
						 IN account_numberx VARCHAR(100))
BEGIN
	INSERT INTO user_info values (user_idx,emailx,mobilex,namex,account_numberx);
END;

CREATE PROCEDURE INSERT_ACCOUNT_DETAILS(IN user_idx VARCHAR(100),
						 IN account_numberx VARCHAR(100),
						 IN balancex DECIMAL)
BEGIN
	INSERT INTO account_info values (user_idx,account_numberx,balancex);
END;

CREATE PROCEDURE INSERT_REGISTERED_USER(IN user_idx VARCHAR(100),
						 IN passwordx VARCHAR(100))
BEGIN
	INSERT INTO registered_user values (user_idx,passwordx);
END;

CREATE PROCEDURE UPDATE_BALANCE(IN account_numberx VARCHAR(100),IN balancex DECIMAL)
BEGIN
	UPDATE account_info
	SET balance = balancex
	WHERE 	account_number = account_numberx;
END;

$$
DELIMETER ;