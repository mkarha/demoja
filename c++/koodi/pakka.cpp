#include <cstdlib>
#include <ctime>
#include <iostream>


#include "pakka.h"
#include "kortti.h"

//Pakan alustus
Pakka::Pakka()
{
    //maat
    int k=0;
    for(int i=0; i<4; i++)
    {
        //arvot
        for(int j=1; j<14; j++)
        {
            kortti = Kortti(i, j);
            korttipakka[k] = kortti;
            k++;
        }
    }
}

//Jako
Kortti Pakka::jaaKortti()
{
    //Satunnaisluvun alustus koneen aikaan perustuen
    srand((unsigned) time(0));
    int randomNumber;
    randomNumber = (rand() % length) + 1; //max arvo, korttipakan koko
    kortti = getPakka(randomNumber);
    //siirretään kortteja pakassa alaspäin jaetun kortin jälkeen
    for (int i=randomNumber-1; i<length-1; i++)
    {
         korttipakka[i] = korttipakka[i + 1];
    }
    length--; //pienennetään satunnaisotannan kokoa yhdellä
    return kortti;
}

Kortti Pakka::getPakka(int indeksi)
{
    return korttipakka[indeksi-1];
}


