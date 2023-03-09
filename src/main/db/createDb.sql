CREATE DATABASE ProjetJavaEE;
USE ProjetJavaEE;

CREATE TABLE Team (
  idTeam INT PRIMARY KEY,
  Name VARCHAR(255)
);
INSERT INTO Team VALUES(0,'Equipe1');
INSERT INTO Team VALUES(1,'Equipe2');
INSERT INTO Team VALUES(2,'Equipe3');
INSERT INTO Team VALUES(3,'Equipe4');
INSERT INTO Team VALUES(4,'Equipe5');

CREATE TABLE Formation (
  idFormation INT PRIMARY KEY,
  Name VARCHAR(255)
);
INSERT INTO Formation VALUES(0,'P1');
INSERT INTO Formation VALUES(1,'P2');
INSERT INTO Formation VALUES(2,'E3e');
INSERT INTO Formation VALUES(3,'E4e');
INSERT INTO Formation VALUES(4,'E5e');

CREATE TABLE Site (
  idSite INT PRIMARY KEY,
  Name VARCHAR(255)
);
INSERT INTO Site VALUES(0,'Angers');
INSERT INTO Site VALUES(1,'Paris');
INSERT INTO Site VALUES(2,'Dijon');

CREATE TABLE Gender (
  idGender INT PRIMARY KEY,
  Name VARCHAR(255)
);
INSERT INTO Gender VALUES(0,'h');
INSERT INTO Gender VALUES(1,'f');

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
INSERT INTO Student VALUES(0,'AUMONT', 'Maxime', 0, 0, 2, 0);
INSERT INTO Student VALUES(1,'BLOIS', 'Hugo', 0, 0, 2, 0);
