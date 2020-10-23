#include "kortti.h"
#include "string"
#include "iostream"

using namespace std;


Kortti::Kortti()
{

}

//Luodaan kortti, jolla on maa sekä arvo
Kortti::Kortti(int maa,int arvo)
{
    this->maa = maa;
    this->arvo = arvo;
}

//Kortin arvon hakeminen
int Kortti::getArvo()
{
    return this->arvo;
}

//Kortin maan hakeminen
string Kortti::getMaa()
{
    if (this->maa == 0)
    {
        return "ruutu";
    }
    else if (this->maa == 1)
    {
        return "risti";
    }
    else if (this->maa == 2)
    {
        return "hertta";
    }
    else
    {
        return "pata";
    }
}

