create database onlinevotingsystem;
use onlinevotingsystem;

create table admin(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,name varchar(50),
adharno long,mobileno long,userid int,password varchar(50));

create table candidate(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,election varchar(45),cvoterid long,cname varchar(50),age int,partyname varchar(50),
cadharno long,cmobileno long,address varchar(50),username varchar(15),password varchar(50));


create table voters(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,voterid long,name varchar(50),age int,
adharno long,mobileno long,username varchar(15),password varchar(50));

create table election(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,electionname varchar(50), date varchar(10));

create table voteresult(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,votername varchar(50),voterid long,electionname varchar(50), cname varchar(50),partyname varchar(20),vote varchar(10));