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
	private int arvo; //pelaajan k�den arvo
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
		try { //yritet��n ladata pelaajan kuva
			this.kuva = new ImageIcon(this.getClass().getResource("/kuvat/" + kuvaLahde));
		}
		catch (Exception e) {
			
		}
	}
	
	
	//Pelaajan luominen antamalla nimi, k�des� olevien korttien m��r�n ja k�den arvon kanssa
	public Pelaaja(String nimi, int lkm, int arvo) {
		this.nimi = nimi;
		this.lkm = lkm;
		this.arvo = arvo;
		this.raha = 0;
		try { //yritet��n ladata pelaajan kuva
			this.kuva = new ImageIcon(this.getClass().getResource("/kuvat/" + kuvaLahde));
		}
		catch (Exception e) {
			
		}
	}
	
	
	//K�ytt�j�tunnuksen palauttava getteri
	public String getKayttaja() {
		return this.kayttaja;
	}
	
	
	//K�yt�j�tunnuksen asettav setteri
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
	
	
	//Kuval�hteen tekstimuodossa asettava setteri
	public void setKuva(String kuvaLahde) {
		this.kuvaLahde = kuvaLahde;
	}
	
	
	//Kuval�hteen tekstimuodossa palauttava getteri
	public String getKuvaLahde() {
		return this.kuvaLahde;
	}
	
	
	//Raham��r�n asettava setteri
	public void setRaha(double raha) {
		this.raha = raha;
	}
	
	
	//Raham��r�n palauttava getteri
	public double getRaha() {
		return this.raha;
	}
	
	
	//Lis�� kortin pelaajalle
	public void otaKortti(int arvo) {
		this.lkm += 1;
		this.arvo += arvo;
	}
	
	
	//Korttien m���r�n k�dess� palauttava getteri
	public int getKorttienMaara() {
		return this.lkm;
	}
	
	
	//Korttien arvon k�dess� palauttava getteri
	public int getArvo() {
		return this.arvo;
	}
	
	
	//K�den arvon asettaminen 
	public void setArvo(int arvo) {
		this.arvo = arvo;
	}
	
	/*Aiemmin talletetun pelaajan lataaminen tiedostosta
	 * Ponnautetaan ikkuna, johon voi sy�tt�� haetun pelaajan k�ytt�j�tunnuksen, 
	 * mik�li k�ytt�j� valitsee valmiiksi tallennetun pelaajan lataamisen.
	 * Luetaan tiedostoa niin kauan kun rivej� riitt��.
	 * Pilkotaan rivi =-merkist�, jolloin saadaa ensimm�iseksi palaksi pelaajan k�ytt�j�tunnus.
	 * Mik�li tiedostosta l�ytyy haettu k�ytt�j�tunnus, k�yd��n rivi l�pi ja 
	 * pilkotaan tiedot pilkkujen kohdalta, jolloin saadaan pelaajan tiedot ulos.
	 * asetetaan pelaajalle tiedot Pelaaja-olion metodeilla ja palautetaan onnistyneesti
	 * ladattu pelaaja-olio.
	 * Mik�li lataus ei onnistu tai pelaajaa ei l�ydy ilmoitetaan k�ytt�j�lle ponnahdusikkunalla
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
				
				if (p.equals(pelaajanTunnus)) {			//mik�li tiedostosta l�ytyy k�ytt�j�tunnus, jonka nimi t�sm�� haettavaan,			      
					pelaaja.setKayttaja(p);
					String tiedot = palat[1];				//ladataan kyseisen pelaajan tiedot
					String[] palaset = tiedot.split(", ");	//erotetaan k�ytt�j�n tiedot toisistaan					
					
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
			
        	JOptionPane.showMessageDialog(ponnahdus,"Tiedoston lataaminen ep�onnistui","Alert",JOptionPane.WARNING_MESSAGE);
        	ponnahdus.setVisible(false);            
        }
		//Mik�li lataus ei onnistu palatetaan null
		return null;	
		
	}
	
	
	/*Pelaajan tallentaminen tiedostoon
	 *Tarkistetaan onko tiedostossa olemassa jo samalla k�ytt�j�nimell� tallennettua pelaajaa.
	 *Mik�li sama k�ytt�j�nimi l�ytyy, pelaajaa ei tallenneta vaan ilmoitetaan nimen varattuna 
	 *olemisesta k�ytt�j�lle ponnahdusikkunalla.
	 *Mik�li tunnus on vapaana, yritet��n kirjoittaa pelaajan tiedot tiedostoon.
	 *Tiedot muotoillaan standardilla yhdelle riville.
	 *=-merkki ja v�lily�nti erottaa k�ytt�j�nimen tiedoista. Loput tiedot erotetaan toisistaan pilkulla ja v�lily�nnill�.
	 *Lopuksi vaihdetaan rivi� uuden lis�yksen odottamista varten.
	 *Lopuksi ilmoitetaan ponnahdusikkunalla onnistuiko tallennus.
	 *Suoritteen laukaisseelle ohjelmalle palautetaan kuittauksen aboolean arvo.
	 *True, mik�li tallennus onnistui ja false, jos ei.
	 *
	 */
	public void tallennaPelaaja(Pelaaja pelaaja) {
				JFrame ponnahdus = new JFrame();
				if (onkoPelaajaJoOlemassa(pelaaja.getKayttaja())==true) {
					JOptionPane.showMessageDialog(ponnahdus,"K�ytt�j�tunnus on jo k�yt�ss�. Ole hyv�ja valitse toinen k�ytt�j�tunnus.","Alert",JOptionPane.WARNING_MESSAGE);
					ponnahdus.setVisible(false);
				        return; 		            	
				}            
				String lisattava = "";
				//yritet��n kirjoittaa tiedostoon
				try {
					FileWriter fileWriter = new FileWriter(tiedosto, true);
				    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);     
				    //tallenteen muotoilua
				    lisattava += pelaaja.getKayttaja() +"= ";  //erotetaan pelaajan tunnus kuvasta ja raham��r�st� v�lily�nnill� ja =-merkill�
				    lisattava += pelaaja.getNimi() + ", " + pelaaja.getKuvaLahde() + ", " + pelaaja.getRaha() +"\n";		//Lis�t��n kuva ja raham��r� pilkulla erotettuna
				           
				    bufferedWriter.write(lisattava); 								//kirjoitetaan tiedot tiedostoon
				    bufferedWriter.close();                							//suljetaan kirjoittaja
				    JOptionPane.showMessageDialog(ponnahdus,"Tallennus onnistui","Alert",JOptionPane.WARNING_MESSAGE);
				    return;                                        
				}            		
				catch (Exception x){
				  	JOptionPane.showMessageDialog(ponnahdus,"Tallennus ep�onnistui","Alert",JOptionPane.WARNING_MESSAGE);   
				  	x.printStackTrace();
				}
	}
	
	
	
	/*Metodi tarkistaa onko pelaajaa tietyll� k�ytt�j�tunnuksella olemassa.
	 * Luetaan ennalat m��ritelty tiedosto rivi rivilt�.
	 * Pilkotaan jokainen rivi =-merkill� ja tarkistetaan nimimerkit.
	 * Mik�li nimi l�ytyy palauetaan true. mik�li ei, false.
	 */
	public boolean onkoPelaajaJoOlemassa(String nimi){
    	try(Scanner tiedostonLukija = new Scanner(Paths.get(tiedosto))){
            while(tiedostonLukija.hasNextLine()){
                String rivi = tiedostonLukija.nextLine();
                String[] palat = rivi.split("= ");                               
                String p = palat[0];						
                	if (p.equals(nimi)) {		//verrataan automaatin nime� k�ytt�j�n sy�tt�m��n nimeen
                		return true;
                	}
            }
        } catch (Exception e){  
        	
        	return false; 	
        }    	
        return false;    		
	}
}
