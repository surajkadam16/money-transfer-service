--This script is used for unit test cases, DO NOT CHANGE!

DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer (CustomerId LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
 CustomerName VARCHAR(30) NOT NULL,
 EmailAddress VARCHAR(30) NOT NULL);

CREATE UNIQUE INDEX idx_ue on Customer(CustomerName,EmailAddress);

INSERT INTO Customer (CustomerName, EmailAddress) VALUES ('suraj','suraj_test1@gmail.com');
INSERT INTO Customer (CustomerName, EmailAddress) VALUES ('nakul','nakul_test1@gmail.com');
INSERT INTO Customer (CustomerName, EmailAddress) VALUES ('pooja','pooja_test1@gmail.com');

DROP TABLE IF EXISTS Account;

CREATE TABLE Account (AccountId LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
CustomerName VARCHAR(30),
Balance DECIMAL(19,4),
CurrencyCode VARCHAR(30)
);

CREATE UNIQUE INDEX idx_acc on Account(CustomerName,CurrencyCode);

INSERT INTO Account (CustomerName,Balance,CurrencyCode) VALUES ('suraj',100.0000,'USD');
INSERT INTO Account (CustomerName,Balance,CurrencyCode) VALUES ('nakul',200.0000,'USD');
INSERT INTO Account (CustomerName,Balance,CurrencyCode) VALUES ('suraj',500.0000,'EUR');
INSERT INTO Account (CustomerName,Balance,CurrencyCode) VALUES ('nakul',500.0000,'EUR');
INSERT INTO Account (CustomerName,Balance,CurrencyCode) VALUES ('suraj',500.0000,'GBP');
INSERT INTO Account (CustomerName,Balance,CurrencyCode) VALUES ('nakul',500.0000,'GBP');
