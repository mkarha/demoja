#include <iostream>
#include <array>

#include "kasi.h"
#include "kortti.h"

std::array<Kortti, 11> kasi;


Kasi::Kasi()
{

}

//Jaetaan kortti pelaajan käteen
void Kasi::lisaaKortti(Kortti kortti, int monesko)
{
    kasi[monesko] = kortti;
    if(monesko>0) //Mikäli ei ensimmäinen kortti, järjestetään pienin kortti kädessä viimeiseksi.
        //Tarkoituksena saada ässä viimeiseen laskettavaan soluun, jotta voidaan määrittää ässälle
        //vaihtoehtoiset arvot 1 tai 14
    {
        //tarkistetaan onko jaettu kortti pienempi kuin edellisessä solussa oleva kortti
        for(int k=0; k<monesko; k++)
        {
            if(kasi[monesko].getArvo()>kasi[k].getArvo())
            {
                Kortti korttiPieni = kasi[k];
                Kortti korttiSuuri = kasi[monesko];
                kasi[k] = korttiSuuri;
                kasi[monesko] = korttiPieni;
            }
        }

    }

}

//Lasketaan pelaajan käden arvo
int Kasi::laskeArvo(int kortteja)
{
    arvo = 0;
    for(int i=0; i<kortteja; i++)
    {
        kortti = kasi[i];
        //Mikäli kortti on ässä, tarkistetaan kuinka toimitaan
        if(kortti.getArvo()==1)
        {
            //Jos käden arvo on 7 tai alle ja kyseessä on viimeinen kortti annetaan
            //ässälle arvo 14. Muuten 1.
            if(21-arvo>=14)// && i==kortteja-1)
            {
                arvo += 14;
            }
            else
            {
                arvo += 1;
            }
        }
        //Muiden korttien arvo lisätään käten sellaisenaan
        else
        {
            arvo += kortti.getArvo();
        }
    }

    return arvo;
  }

//Mikäli halutaan asettaa käteen jokin tietty arvo
void Kasi::setArvo(int arvo)
{
    this->arvo = arvo;
}

//Kuormitettu funktio. Mikäli ei anneta muuttujaa, asetetaan käden arvoksi nolla.
//Käytössä kun ylitetään 21.
void Kasi::setArvo()
{
    this->arvo = 0;
}

//Käden arvon noutaminen
int Kasi::getKasiArvo()
{
    return this->arvo;
}

//Tietyn kortin noutaminen kädestä
Kortti Kasi::kortit(int indeksi)
{
    return kasi[indeksi];
}




