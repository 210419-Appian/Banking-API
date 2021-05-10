/*
 * This script should let you rebuild your bank database from scratch.
 * Note: If you don't have a bank database, please uncomment the
 * relevant line.
 */

--No longer needed
--CREATE DATABASE bank;

--Drop old tables
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS user_table;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS account_status;
DROP TABLE IF EXISTS account_type;

--Create tables
CREATE TABLE user_role(
	role_id SERIAL PRIMARY KEY,
	role_title varchar(50) NOT NULL UNIQUE
);

CREATE TABLE user_table(
	user_id SERIAL PRIMARY KEY,
	username varchar(50) NOT NULL UNIQUE,
	user_password varchar(50) NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	role_id int REFERENCES user_role(role_id)
);

CREATE TABLE account_status(
	status_id SERIAL,
	status varchar(50) NOT NULL UNIQUE,
	PRIMARY KEY (status_id)
);

CREATE TABLE account_type(
	type_id SERIAL PRIMARY KEY,
	type_name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE account(
	account_id SERIAL PRIMARY KEY,
	balance double PRECISION NOT NULL,
	status_id int REFERENCES account_status(status_id),
	type_id int REFERENCES account_type(type_id),
	user_id int REFERENCES user_table(user_id)
);

--Default rows
INSERT INTO account_type (type_name) 
	VALUES ('Checking'), ('Savings');

INSERT INTO account_status (status) 
	VALUES ('Pending'), ('Open'), ('Closed'), ('Denied');

INSERT INTO user_role (role_title) 
	VALUES ('Admin'), ('Employee'), ('Standard'), ('Premium');

INSERT INTO user_table (username, user_password, first_name, last_name, email, role_id)
	VALUES ('admin', 'password', 'John', 'Adminson', 'addmin@admin.com', 1);

INSERT INTO account (balance, status_id, type_id, user_id)
	VALUES (100000, 2, 2, 1);
INSERT INTO account (balance, status_id, type_id, user_id)
	VALUES (100000, 1, 1, 1);

INSERT INTO user_table (username, user_password, first_name, last_name, email, role_id)
	VALUES ('employee', 'password', 'Bob', 'Bobson', 'Bob@website.com', 2);

INSERT INTO account (balance, status_id, type_id, user_id)
	VALUES (10000, 2, 2, 2);

INSERT INTO user_table (username, user_password, first_name, last_name, email, role_id)
	VALUES ('user', 'password', 'Bill', 'Billson', 'Bill@website.com', 3);

INSERT INTO account (balance, status_id, type_id, user_id)
	VALUES (1000, 2, 2, 3);













