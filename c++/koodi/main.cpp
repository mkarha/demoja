#include <iostream>
#include <array>

#include <kasi.h>
#include <kortti.h>
#include <pakka.h>
#include <pelaaja.h>

using namespace std;

int main()
{

    //Alustetaan muuttujat
    string syote; //syote string-muodossa, jotta vältytään virhepainalluksien aiheuttamilta kaatumisilta
    Pakka pakka;
    Kortti kortti;
    Kasi pelaaja;
    class pelaaja pelaaja1("");
    class pelaaja pelaaja2("");
    class pelaaja pelaaja3("");
    class pelaaja jakaja("jakaja");


    //Ohjelma alkaa.
    //Pysytään valikossa, kunnes käyttäjä haluaa poistua
    while (true)
    {
        cout << "VENTTI" << endl;
        cout << endl;
        cout << "Ventti on korttipeli, jossa on tarkoitus saada nostettujen korttien" << endl;
        cout << "arvoksi mahdollisimman tarkasti 21." << endl;
        cout << "Ässän arvo voi olla joko 1 tai 14." << endl;
        cout << "Peli toimii yhden korttipakan jaolla." << endl;
        cout << endl;
        cout << "Anna valintasi" << endl;
        cout << "1: Uusi peli" << endl;
        cout << "0: Poistu" << endl;
        cin >> syote;
        system("cls");
        if (syote.compare("0")==0) //poistuminen
        {
            break;
        }

        if (syote.compare("1")==0) //uusi peli
        {
            pakka = Pakka(); //täytetään pakka

            //valitaan pelaajien lukumäärä
            int pelaajia;
            string nimi;
            //Luodaan taulukko pelaajia varten
            array<class pelaaja, 4> pelaajat;
            //pysytään valikossa, kunnes käyttäjä syöttää sallitun pelaajamääärn
            while (true)
            {

                cout << "Anna pelaajien lukumäärä ilman jakajaa (1-3):" << endl;
                cin >> syote;

                if (syote.compare("1")==0 || syote.compare("2")==0 || syote.compare("3")==0)
                {
                    pelaajia = stoi(syote); //muunnetaan syöte numeeriseen muotoon kokonaisluvuksi
                    break;
                }
            }

            //kysytään pelaajien nimet ja täytetään taulukko
            cout << "Anna pelaajan 1 nimi:" << endl;
            cin >> nimi;
            pelaaja1.setNimi(nimi);
            pelaajat[0] = pelaaja1;

            if (pelaajia > 1)
            {
                cout << "Anna pelaajan 2 nimi:" << endl;
                cin >> nimi;
                pelaaja2.setNimi(nimi);
                pelaajat[1] = pelaaja2;
            }
            if (pelaajia == 3)
            {
                cout << "Anna pelaajan 3 nimi:" << endl;
                cin >> nimi;
                pelaaja3.setNimi(nimi);
                pelaajat[2] = pelaaja3;
            }
            pelaajat[pelaajia] = jakaja;
            system("cls");

            //pelataan venttiä
            int lkm;
            int jatketaanko;

            //pelaajien vuorot
            for (int i=0; i<pelaajia; i++)
            {
                lkm = 0;

                do{

                    kortti = pakka.jaaKortti(); //jaetaan kortti pakasta
                    pelaajat[i].lisaaKortti(kortti, lkm); //lisätään kortti pelaajan käteen
                    lkm++; //korttien lukumäärää lisätään
                    //lasketaan kädessä olevien korttien yhteenlaskettu arvo

                    pelaajat[i].setArvo(pelaajat[i].laskeArvo(lkm));

                    cout << endl;

                   //Vuorossa olevan pelaajan tilanteen tulostus
                   cout << pelaajat[i].getNimi() << " Kortit:" << endl;
                   cout << endl;
                   for(int j=0; j<lkm; j++)
                   {
                        cout << pelaajat[i].kortit(j).getMaa() << " " << pelaajat[i].kortit(j).getArvo();
                        if(j<lkm-1)
                        {
                              cout << ", ";
                        }
                    }


                    //tarkistetaan menikö yli
                    if(pelaajat[i].getKasiArvo()>21)
                    {
                        cout << endl;
                        cout << "Korttien yhteenlaskettu summa: " << pelaajat[i].getKasiArvo() << endl;
                        pelaajat[i].setArvo();
                        cout << "Voi harmi. Yli meni." << endl;
                        system("pause");
                        system("cls");
                        jatketaanko = 0;
                        cout << endl;
                    }
                    else
                    {
                        while(true)
                        {
                            cout << endl;
                            cout << "Korttien yhteenlaskettu summa: " << pelaajat[i].getKasiArvo() << endl;
                            cout << pelaajat[i].getNimi() << " Haluatko nostaa uuden kortin? (e=ei, k=kyllä)" << endl;
                            cin >> syote;
                            string ei = "e";
                            string kyl = "k";
                            system("cls");
                            transform(syote.begin(), syote.end(), syote.begin(), ::tolower); //syöte pieniksi kirjaimiksi
                            if( ei.compare(syote)==0)
                            {
                                jatketaanko = 0;
                                cout << endl;
                                break;
                            }
                            else if(kyl.compare(syote)==0)
                            {
                                jatketaanko = 1;
                                cout << endl;
                                break;
                            }
                        }


                    }

                    //poistuminen
                    if(jatketaanko == 0)
                    {
                        break;
                    }
                }while(true);
            }


            //jakajan vuoro
            lkm = 0;
            do
            {
                jatketaanko = 1;
                kortti = pakka.jaaKortti();
                pelaajat[pelaajia].lisaaKortti(kortti, lkm);
                lkm++;

                pelaajat[pelaajia].kadenArvo(pelaajat[pelaajia].laskeArvo(lkm));
                pelaajat[pelaajia].montakoKorttia(lkm);

                cout << pelaajat[pelaajia].getNimi() << " Kortit:" << endl;
                for(int j=0; j<lkm; j++)
                {
                     cout << pelaajat[pelaajia].kortit(j).getMaa() << " " << pelaajat[pelaajia].kortit(j).getArvo();
                     if(j<lkm-1)
                     {
                           cout << ", ";
                     }
                 }
                 cout << endl;
                 cout << "Korttien yhteenlaskettu summa: " << pelaajat[pelaajia].getKasiArvo() << endl;
                 cout << endl;
                //verrataan muiden pelaajien käsien arvoihin
                int suurin =1;
                for(int k=0; k<pelaajia; k++)
                {
                    suurin *= pelaajat[pelaajia].vertailu(pelaajat[k].getKasiArvo());
                }
                if (suurin != 0)
                {
                    jatketaanko = 0;
                }

                //tarkistetaan menikö yli
                if (pelaajat[pelaajia].getKasiArvo()>21)
                {
                    pelaajat[pelaajia].setArvo();
                    jatketaanko = 0;
                }

                //poistuminen
                if(jatketaanko == 0)
                {
                    break;
                }
            }while(true);


            //Tarkastetaan lopputulos
            //Järjestetään pelaajat käsien mukaan suuruusjärjestykseen taulukossa
            //Käydään vertailu läpi kuhunkin aiempaan pelaajaan nähden
            for(int m=1; m<pelaajia+1; m++)
            {                
                if(m>1)
                {
                    for(int p=m; p>0; p--)
                    {
                        if(pelaajat[m-p].vertailu(pelaajat[m].getKasiArvo()) == 0)
                        {
                            class pelaaja pelaajaPieni = pelaajat[m-p];
                            class pelaaja pelaajaSuuri = pelaajat[m];
                            pelaajat[m-p] = pelaajaSuuri;
                            pelaajat[m] = pelaajaPieni;
                        }
                    }

                }
                else if(pelaajat[0].vertailu(pelaajat[1].getKasiArvo()) == 0)
                {
                    class pelaaja pelaajaPieni = pelaajat[m-1];
                    class pelaaja pelaajaSuuri = pelaajat[m];
                    pelaajat[m-1] = pelaajaSuuri;
                    pelaajat[m] = pelaajaPieni;
                }

            }


            for(int n=0; n<pelaajia+1; n++)
            {
               cout << pelaajat[n].getNimi() << ": Korttien yhteenlaskettu summa: " << pelaajat[n].getKasiArvo() << endl;
            }
            cout << endl;

            int voittotulos = pelaajat[0].getKasiArvo();

            if(pelaajat[1].getKasiArvo()==voittotulos)
            {
                if (pelaajat[0].getNimi().compare("jakaja")==0)
                {
                    cout << pelaajat[0].getNimi() << " voitti" << endl;
                }
                else
                {
                    cout << "Tasapeli!" << endl;
                }
            }
            else
            {
                cout << pelaajat[0].getNimi() << " voitti" << endl;
            }

            system("pause");
            system("cls");

        }

    }

    return 0;
}

