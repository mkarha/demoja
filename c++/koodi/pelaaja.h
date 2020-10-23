#ifndef PELAAJA_H
#define PELAAJA_H

#include <string.h>
#include <kasi.h>
//#include <kortti.h>

class pelaaja : public Kasi
{
public:
    pelaaja();
    pelaaja(std::string nimi);
    void setNimi(std::string nimi);
    int pelaa();
    void montakoKorttia(int lkm);
    int getKorttienMaara();
    void kadenArvo(int arvo);
    int getArvo();
    std::string getNimi();
    int vertailu(int arvo);

private:
    std::string nimi;
    int lkm;
    int arvo;
};

#endif // PELAAJA_H
