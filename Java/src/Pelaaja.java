import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Pelaaja {
	
	private String tiedosto = "pelaajat.txt";
	private String kayttaja, nimi;
	private int arvo; //pelaajan käden arvo
	private int lkm; //pelaajan kortttien lkm
	private double raha; //pelaajan pinkka
	private String kuvaLahde;
	private ImageIcon kuva;
	
	
	//Parametriton konstruktori
	public Pelaaja() {
		this.lkm = 0;
		this.arvo = 0;
		this.raha = 0;
	}

	//Pelaajan luominen nimen avulla
	public Pelaaja(String nimi) {
		this.nimi = nimi;
		this.lkm = 0;
		this.arvo = 0;
		this.raha = 0;
		try { //yritetään ladata pelaajan kuva
			this.kuva = new ImageIcon(this.getClass().getResource("/kuvat/" + kuvaLahde));
		}
		catch (Exception e) {
			
		}
	}
	
	
	//Pelaajan luominen antamalla nimi, kädesä olevien korttien määrän ja käden arvon kanssa
	public Pelaaja(String nimi, int lkm, int arvo) {
		this.nimi = nimi;
		this.lkm = lkm;
		this.arvo = arvo;
		this.raha = 0;
		try { //yritetään ladata pelaajan kuva
			this.kuva = new ImageIcon(this.getClass().getResource("/kuvat/" + kuvaLahde));
		}
		catch (Exception e) {
			
		}
	}
	
	
	//Käyttäjätunnuksen palauttava getteri
	public String getKayttaja() {
		return this.kayttaja;
	}
	
	
	//Käytäjätunnuksen asettav setteri
	public void setKayttaja(String kayttaja) {
		this.kayttaja = kayttaja;
	}
	
	
	//Nimen palauttava getteri
	public String getNimi() {
		return this.nimi;
	}
	
	
	//Nimen asettava setteri
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	
	//Kuvan ikonina palauttava getteri
	public ImageIcon getKuva() {
		return this.kuva;
	}
	
	
	//Kuvalähteen tekstimuodossa asettava setteri
	public void setKuva(String kuvaLahde) {
		this.kuvaLahde = kuvaLahde;
	}
	
	
	//Kuvalähteen tekstimuodossa palauttava getteri
	public String getKuvaLahde() {
		return this.kuvaLahde;
	}
	
	
	//Rahamäärän asettava setteri
	public void setRaha(double raha) {
		this.raha = raha;
	}
	
	
	//Rahamäärän palauttava getteri
	public double getRaha() {
		return this.raha;
	}
	
	
	//Lisää kortin pelaajalle
	public void otaKortti(int arvo) {
		this.lkm += 1;
		this.arvo += arvo;
	}
	
	
	//Korttien määärän kädessä palauttava getteri
	public int getKorttienMaara() {
		return this.lkm;
	}
	
	
	//Korttien arvon kädessä palauttava getteri
	public int getArvo() {
		return this.arvo;
	}
	
	
	//Käden arvon asettaminen 
	public void setArvo(int arvo) {
		this.arvo = arvo;
	}
	
	/*Aiemmin talletetun pelaajan lataaminen tiedostosta
	 * Ponnautetaan ikkuna, johon voi syöttää haetun pelaajan käyttäjätunnuksen, 
	 * mikäli käyttäjä valitsee valmiiksi tallennetun pelaajan lataamisen.
	 * Luetaan tiedostoa niin kauan kun rivejä riittää.
	 * Pilkotaan rivi =-merkistä, jolloin saadaa ensimmäiseksi palaksi pelaajan käyttäjätunnus.
	 * Mikäli tiedostosta löytyy haettu käyttäjätunnus, käydään rivi läpi ja 
	 * pilkotaan tiedot pilkkujen kohdalta, jolloin saadaan pelaajan tiedot ulos.
	 * asetetaan pelaajalle tiedot Pelaaja-olion metodeilla ja palautetaan onnistyneesti
	 * ladattu pelaaja-olio.
	 * Mikäli lataus ei onnistu tai pelaajaa ei löydy ilmoitetaan käyttäjälle ponnahdusikkunalla
	 * ja palautetaan null.
	 */
	public Pelaaja lataaPelaaja(String pelaajanTunnus) {
		JFrame ponnahdus = new JFrame();
		Pelaaja pelaaja = new Pelaaja();
		try(Scanner tiedostonLukija = new Scanner(Paths.get(tiedosto))){  
			
			while(tiedostonLukija.hasNextLine()){
				String rivi = tiedostonLukija.nextLine();
				String[] palat = rivi.split("= "); 
				String p = palat[0];
				
				if (p.equals(pelaajanTunnus)) {			//mikäli tiedostosta löytyy käyttäjätunnus, jonka nimi täsmää haettavaan,			      
					pelaaja.setKayttaja(p);
					String tiedot = palat[1];				//ladataan kyseisen pelaajan tiedot
					String[] palaset = tiedot.split(", ");	//erotetaan käyttäjän tiedot toisistaan					
					
					//Asetetaan ladatut tiedot
					pelaaja.setNimi(palaset[0]);			
					pelaaja.setKuva(palaset[1]);
					try {
						Double rahamaara = Double.valueOf(palaset[2]);
						pelaaja.setRaha(rahamaara);
					}
					catch (Exception x) {
						System.out.println(x);
					}
						
					//Palautetaan pelaaja, kun lataus onnistuu
					return pelaaja;
		
				}
			}
		} 		
		catch (Exception x){  
			
        	JOptionPane.showMessageDialog(ponnahdus,"Tiedoston lataaminen epäonnistui","Alert",JOptionPane.WARNING_MESSAGE);
        	ponnahdus.setVisible(false);            
        }
		//Mikäli lataus ei onnistu palatetaan null
		return null;	
		
	}
	
	
	/*Pelaajan tallentaminen tiedostoon
	 *Tarkistetaan onko tiedostossa olemassa jo samalla käyttäjänimellä tallennettua pelaajaa.
	 *Mikäli sama käyttäjänimi löytyy, pelaajaa ei tallenneta vaan ilmoitetaan nimen varattuna 
	 *olemisesta käyttäjälle ponnahdusikkunalla.
	 *Mikäli tunnus on vapaana, yritetään kirjoittaa pelaajan tiedot tiedostoon.
	 *Tiedot muotoillaan standardilla yhdelle riville.
	 *=-merkki ja välilyönti erottaa käyttäjänimen tiedoista. Loput tiedot erotetaan toisistaan pilkulla ja välilyönnillä.
	 *Lopuksi vaihdetaan riviä uuden lisäyksen odottamista varten.
	 *Lopuksi ilmoitetaan ponnahdusikkunalla onnistuiko tallennus.
	 *Suoritteen laukaisseelle ohjelmalle palautetaan kuittauksen aboolean arvo.
	 *True, mikäli tallennus onnistui ja false, jos ei.
	 *
	 */
	public void tallennaPelaaja(Pelaaja pelaaja) {
				JFrame ponnahdus = new JFrame();
				if (onkoPelaajaJoOlemassa(pelaaja.getKayttaja())==true) {
					JOptionPane.showMessageDialog(ponnahdus,"Käyttäjätunnus on jo käytössä. Ole hyväja valitse toinen käyttäjätunnus.","Alert",JOptionPane.WARNING_MESSAGE);
					ponnahdus.setVisible(false);
				        return; 		            	
				}            
				String lisattava = "";
				//yritetään kirjoittaa tiedostoon
				try {
					FileWriter fileWriter = new FileWriter(tiedosto, true);
				    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);     
				    //tallenteen muotoilua
				    lisattava += pelaaja.getKayttaja() +"= ";  //erotetaan pelaajan tunnus kuvasta ja rahamäärästä välilyönnillä ja =-merkillä
				    lisattava += pelaaja.getNimi() + ", " + pelaaja.getKuvaLahde() + ", " + pelaaja.getRaha() +"\n";		//Lisätään kuva ja rahamäärä pilkulla erotettuna
				           
				    bufferedWriter.write(lisattava); 								//kirjoitetaan tiedot tiedostoon
				    bufferedWriter.close();                							//suljetaan kirjoittaja
				    JOptionPane.showMessageDialog(ponnahdus,"Tallennus onnistui","Alert",JOptionPane.WARNING_MESSAGE);
				    return;                                        
				}            		
				catch (Exception x){
				  	JOptionPane.showMessageDialog(ponnahdus,"Tallennus epäonnistui","Alert",JOptionPane.WARNING_MESSAGE);   
				  	x.printStackTrace();
				}
	}
	
	
	
	/*Metodi tarkistaa onko pelaajaa tietyllä käyttäjätunnuksella olemassa.
	 * Luetaan ennalat määritelty tiedosto rivi riviltä.
	 * Pilkotaan jokainen rivi =-merkillä ja tarkistetaan nimimerkit.
	 * Mikäli nimi löytyy palauetaan true. mikäli ei, false.
	 */
	public boolean onkoPelaajaJoOlemassa(String nimi){
    	try(Scanner tiedostonLukija = new Scanner(Paths.get(tiedosto))){
            while(tiedostonLukija.hasNextLine()){
                String rivi = tiedostonLukija.nextLine();
                String[] palat = rivi.split("= ");                               
                String p = palat[0];						
                	if (p.equals(nimi)) {		//verrataan automaatin nimeä käyttäjän syöttämään nimeen
                		return true;
                	}
            }
        } catch (Exception e){  
        	
        	return false; 	
        }    	
        return false;    		
	}
}
