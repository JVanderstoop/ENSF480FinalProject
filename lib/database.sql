DROP DATABASE IF EXISTS ENSF480;
CREATE DATABASE ENSF480; 
USE ENSF480;
    
DROP TABLE IF EXISTS Password; 
CREATE TABLE Password (
	username		varchar(16) not null, 
    password		varchar(16) not null,  
    name			varchar(16) not null,
    accountType		int not null,
    recentSearch	varchar(256),
    email			varchar(48),
    primary key		(username)
    );

DROP TABLE IF EXISTS Property; 
CREATE TABLE Property (
	landlordUsername	varchar(16) not null, 
    ID					int not null, 
    address				varchar(30) not null, 
	rooms 				int not null, 
    bathrooms			double not null, 
    furnished			boolean not null, 
    quadrant			char(2) not null, 
    propertyType 		varchar(16) not null, 
    
    postedOnline		boolean not null, 
    lastPayment			date not null, 
    paymentPeriod		int not null, 
    rent				double not null, 
    datePosted			date, 
    rented				boolean not null, 
    feeAmount			double not null, 
    primary key		(ID),
    key				(landlordUsername)
    );
    


