#ifndef PAKKA_H
#define PAKKA_H

#include <array>
#include <kortti.h>


class Pakka : public Kortti
{
public:
    Pakka();
    Kortti jaaKortti();
    Kortti getPakka(int indeksi);



private:
    Kortti kortti;
    std::array<Kortti, 52> korttipakka;
    int length = korttipakka.size();
    //Kortti korttiPakka[];
};

#endif // PAKKA_H
