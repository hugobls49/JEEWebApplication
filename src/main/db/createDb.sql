CREATE DATABASE ProjetJavaEE;
USE ProjetJavaEE;

CREATE TABLE Student (
  idStudent INT PRIMARY KEY,
  Name VARCHAR(255),
  FirstName VARCHAR(255),
  idGender INT,
  idSite INT,
  idFormation INT,
  idTeam INT,
  FOREIGN KEY (idGender) REFERENCES Gender(idGender),
  FOREIGN KEY (idSite) REFERENCES Site(idSite),
  FOREIGN KEY (idFormation) REFERENCES Formation(idFormation),
  FOREIGN KEY (idTeam) REFERENCES Team(idTeam)
);

CREATE TABLE Team (
  idTeam INT PRIMARY KEY,
  Name VARCHAR(255)
);

CREATE TABLE Formation (
  idFormation INT PRIMARY KEY,
  Name VARCHAR(255)
);

CREATE TABLE Site (
  idSite INT PRIMARY KEY,
  Name VARCHAR(255)
);

CREATE TABLE Gender (
  idGender INT PRIMARY KEY,
  Name VARCHAR(255)
);
