#ifndef KASI_H
#define KASI_H

#include <kortti.h>


class Kasi : public Kortti
{
public:
    Kasi();
    int laskeArvo(int kortteja);
    void lisaaKortti(Kortti kortti, int monesko);
    Kortti kortit(int indeksi);
    void setArvo();
    void setArvo(int arvo);
    int getKasiArvo();



private:
    int arvo;
    Kortti kortti;


};

#endif // KASI_H
