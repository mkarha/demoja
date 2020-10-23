#include "maa.h"

#include "kortti.h"

Maa::Maa(Kortti kortti)
{

    this->kortit[kortti.getArvo()] = kortti;
}

char Maa::getMaa()
{
    return this->maa;
}
