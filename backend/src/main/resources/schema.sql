DROP TABLE IF EXISTS Location;
DROP TABLE IF EXISTS Weather_Details;

CREATE TABLE Location (
    Location_ID INT AUTO_INCREMENT  PRIMARY KEY,
    City_Name VARCHAR(50) NOT NULL,
    City_ID INT(8) NULL,
    Country_Name VARCHAR(50) NOT NULL,
    Country_Code INT(8) NULL,
    State_Code INT(8) NULL
);

CREATE TABLE Weather_Details (
  Weather_Details_ID INT AUTO_INCREMENT  PRIMARY KEY,
  Temp DOUBLE NOT NULL,
  Feels_Like DOUBLE NULL,
  Temp_Min DOUBLE NULL,
  Temp_Max DOUBLE NULL,
  Pressure INT NULL,
  Humidity INT NULL,
  Description VARCHAR(500) NULL,
  Action_Date_Time DATETIME NOT NULL,
  Location_Id BIGINT NULL
);

ALTER TABLE Weather_Details ADD CONSTRAINT FK_WEATHERDETAILS_ON_LOCATION FOREIGN KEY (Location_Id) REFERENCES Location (Location_ID);