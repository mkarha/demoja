CREATE TABLE Asiakas(
AsiakasID int NOT NULL,
Etunimi varchar(24) NOT NULL,
Sukunimi varchar(42) NOT NULL,
Sposti varchar(80) NOT NULL,
Puhelin varchar(16) NOT NULL,
PRIMARY KEY (AsiakasID)
);

CREATE TABLE Tila(
TilaID int NOT NULL,
TilaNimi varchar(16) NOT NULL,
PRIMARY KEY (TilaID)
);

CREATE TABLE Varaus(
VarausID int NOT NULL,
VarausNumero int NOT NULL,
Asiakas int NOT NULL,
Tila int NOT NULL,
Pvm Date NOT NULL,
AloitusAika Time NOT NULL,
LopetusAika Time NOT NULL,
HloMaara int,
PRIMARY KEY (VarausID),
FOREIGN KEY (Asiakas) REFERENCES Asiakas(AsiakasID),
FOREIGN KEY (Tila) REFERENCES Tila(TilaID)
);




