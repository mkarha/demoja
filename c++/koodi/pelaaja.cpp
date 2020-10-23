#include "iostream"
#include "string.h"
#include "pelaaja.h"
#include "kortti.h"
#include "pakka.h"

//Luodaan pelaaja ilman syötettyjä muuttujia
pelaaja::pelaaja()
{
    this->nimi = "";
    this->arvo = 0;
    this->lkm = 0;
}

//Luodaan pelaaja, jolle syötetään valmiiksi nimi
pelaaja::pelaaja(std::string nimi)
{
    this->nimi = nimi;
    this->arvo = 0;
    this->lkm = 0;
}

//Nimetään luotu pelaaja uudelleen
void pelaaja::setNimi(std::string nimi)
{
    this->nimi = nimi;
}

//Syötetään montako korttia pelaajalla on
void pelaaja::montakoKorttia(int lkm)
{
    this->lkm = lkm;
}

int pelaaja::getKorttienMaara()
{
    return this->lkm;
}

//Syötetään pelaajan käden arvo
void pelaaja::kadenArvo(int arvo)
{
    this->arvo = arvo;
}

//Haetaan pelaajan käden arvo
int pelaaja::getArvo()
{
    return this->arvo;
}

//Haetaan pelaajan nimi
std::string pelaaja::getNimi()
{
    return this->nimi;
}

//Verrataan pelaajan käden arvoa toisen pelaajan käden arvoon.
//Palautetaan vertailun tulos
int pelaaja::vertailu(int arvo)
{
    if(this->arvo<arvo)
    {
        return 0;
    }
    else
    {
        return 1;
    }
}


