#ifndef KORTTI_H
#define KORTTI_H

#include "string"

class Kortti
{
public:

    Kortti();
    Kortti(int maa, int arvo);
    int getArvo();
    std::string getMaa();

private:
   int arvo;
   int maa;
};

#endif // KORTTI_H
