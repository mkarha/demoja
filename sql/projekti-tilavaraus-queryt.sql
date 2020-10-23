/*vuoden 2020 varaukset*/
SELECT varaus.VarausNumero, varaus.Pvm, Varaus.aloitusaika, varaus.lopetusaika, 
Asiakas.Etunimi, Asiakas.Sukunimi, Asiakas.sposti, varaus.tila
FROM varaus INNER JOIN asiakas
ON varaus.asiakas = asiakas.asiakasID
WHERE Pvm > 20200101;

/*Onko varauksia 13.12.2019?*/
SELECT pvm, aloitusaika, lopetusaika, varausid FROM varaus
WHERE pvm ='20191213';

/*Milloin Tommi Mäkinen olikaan varannut tilaisuude ja mitä siihen kuului?*/
SELECT varaus.VarausNumero, varaus.Pvm, Varaus.aloitusaika, varaus.lopetusaika, 
Asiakas.Etunimi, Asiakas.Sukunimi, varaus.tila FROM Varaus INNER JOIN asiakas
ON varaus.asiakas = asiakas.asiakasID
WHERE Etunimi = 'Tommi' AND Sukunimi = 'Mäkinen';

/*Tilavaraukset asiakastiedoilla pvm ja tilan nimen mukaan järjestettynä*/
CREATE VIEW Tilavaraukset AS
SELECT Tila.tilanimi AS Tila, varaus.VarausNumero, varaus.Pvm, Varaus.aloitusaika, varaus.lopetusaika, 
Asiakas.Etunimi, Asiakas.Sukunimi 
FROM (Varaus INNER JOIN tila ON varaus.tila = tila.tilaid)
INNER JOIN asiakas ON varaus.asiakas = asiakas.asiakasid
ORDER BY varaus.pvm, tila;
SELECT * FROM Tilavaraukset
WHERE Tila = "neukkari"; /* ajotila, pelitila, neukkari, sisäkenttä, ulkokenttä */
DROP VIEW Tilavaraukset RESTRICT;

/*Monelleko hengelle Ella jokinen AsiakasID 103 oli tehnyt varauksen*/
SELECT varaus.VarausNumero, varaus.Pvm, Varaus.aloitusaika, varaus.lopetusaika, varaus.hlomaara,
Asiakas.Etunimi, Asiakas.Sukunimi FROM Varaus INNER JOIN asiakas
ON varaus.asiakas = asiakas.asiakasID
WHERE asiakas = 103;

/*Ajotilan tai pelitilan varaukset maaliskuun 2020 ensimmäisellä viikolla*/
SELECT varaus.Pvm, Varaus.aloitusaika, varaus.lopetusaika, tila.tilanimi 
FROM varaus INNER JOIN tila
ON varaus.tila = tila.tilaid
WHERE tila.tilaid IN (1, 2) AND /*arvot 1-5 */
varaus.pvm BETWEEN 20200301 AND 20200307;

/*Listaa asiakkaiden yhteystiedot*/
SELECT sukunimi, etunimi, asiakasid AS Asiakasnumero, 
Sposti AS "E-mail", Puhelin
FROM Asiakas;

/*Eri tilojen yhteenlasketut varaustunnit*/
SELECT tila.tilanimi, ROUND(SUM(TIME_TO_SEC(TIMEDIFF(varaus.lopetusaika, varaus.aloitusaika))/3600),2) AS Aika
FROM varaus LEFT JOIN tila ON varaus.tila = tila.tilaid
GROUP BY tila.tilanimi
ORDER BY Aika;

/*Keskimääräinen ryhmäkoko*/
CREATE VIEW Henkilomaara AS
SELECT varausnumero, AVG(hlomaara) AS Henkilömäärä
FROM varaus
GROUP BY varausnumero
; 
SELECT ROUND(AVG(Henkilömäärä),2) AS Henkilömäärä
FROM Henkilomaara;
DROP VIEW Henkilomaara RESTRICT;

/*Tarkista asiakkaan varausnumero*/
SELECT DISTINCT varaus.varausnumero, asiakas.*
FROM varaus INNER JOIN asiakas ON varaus.asiakas = asiakas.asiakasid
ORDER BY asiakas.asiakasid;

/*Listaa varaukseen kuuluvat tilat*/
SELECT varaus.VarausNumero, Tila.tilanimi 
FROM Varaus INNER JOIN tila ON varaus.tila = tila.tilaid
WHERE varaus.varausnumero = 1 /*muuta varausnumeroa*/
ORDER BY varaus.varausnumero, tila.tilanimi;

/*Yli kahden tunnin tilaisuudet*/
SELECT DISTINCT varaus.varausnumero, asiakas.*, 
(TIME_TO_SEC(TIMEDIFF(varaus.lopetusaika, varaus.aloitusaika))/3600) AS Aika
FROM varaus INNER JOIN asiakas ON varaus.asiakas = asiakas.asiakasid
HAVING Aika > 2
ORDER BY asiakas.asiakasid;


