package arvaaSanaGUI;

import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Sana {
	
	//Sana-luokan yksityiset muuttujat sanalista, sanatiedosto, sanapiilo ja jo arvatut kirjaimet
	private ArrayList<String> sanat;
    private String tiedosto = "sanat.txt";
    private String piilosana;
    private String arvatut;
    
    //Sana-luokan kostruktori eli listan alustus
    public Sana(){
        this.sanat = new ArrayList<>();
        this.piilosana ="";
        this.arvatut = "";
    }
    
    
    //Ladataan sanat tiedostosta listaan tai lisätään listaan sana "varasija"
    public void lataa(){
        try(Scanner tiedostonLukija = new Scanner(Paths.get(this.tiedosto))){
            while(tiedostonLukija.hasNextLine()){
                String rivi = tiedostonLukija.nextLine();
                String[] palat = rivi.split(",");
                String sana = palat[0];
                
                onkoSanaJoOlemassa(sana);
            }
        } catch (Exception e){
        	            
        }        
    }
    
    //tallentaa sanat tiedostoon, mikäli oma sana lisätty
    public boolean tallenna(){
        String lisattava = "";
        try (PrintWriter kirjoittaja = new PrintWriter(this.tiedosto)){
            for(int i=0; i<this.sanat.size(); i++){                
                lisattava = lisattava + this.sanat.get(i) +",\n";                
            }
        kirjoittaja.print(lisattava);
        kirjoittaja.close();    
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    //Tarkistaa onko omaa sanaa vastaava sana jo olemassa
    public boolean onkoSanaJoOlemassa(String sana){
        if(this.sanat.contains(sana)){
               return true;           
        }
        this.sanat.add(sana);
        return false;
    		
	}

    /*Arvotaan sana merkkijonotaulukosta*/
	public String arvoSana() {
		String sana = null;
		Random rand = new Random();
		int arvottuSana = rand.nextInt(this.sanat.size());
		sana = this.sanat.get(arvottuSana);
		
		return sana;
	}
    
	/*Luodaan pelaajalle näytettävä piilotettu sana, jonka pituus on vastaava kuin sanataulukon.
 	 * Taulukon sisällöksi syötetään _ alaviivaa ja kirjaimia arvattaessa oikein paljastetaan
 	 * kyseinen kirjain kopioimalla se sanataulukosta näytettävään taulukkoon.
 	 */
 	public String luoPiilosana(int pituus) {
		this.piilosana = "";
 		int i = 0;
		for (i=0; i<pituus; i++) {
			this.piilosana += '-';
		}
		return this.piilosana;
	}
 	
 	/*tarkistetaan syöte laittomien merkkien osalta*/
	public boolean tarkastaSana(int c, int pituus) {
		int i = 0;
		int tark = 0;
			for (i=0; i<pituus; i++) {
				//char c = sana.charAt(i);
	            if (c < 0x2d || (c >= 0x2e && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a) {
	            	tark = 1;    		            	
	            }
	       	}							
			if (tark == 0) {
				return true;
			}else
				return false;
		}
	
 	
 	/*Tarkistetaan löytyykö arvattua kirjainta.
 	 * Mikäli löytyy paljastetaan kyseiset kirjaimet.
 	 * Mikäli ei lisätään virheen määrää yhdellä.
 	 */
 	public boolean onkoKirjainta(int pituus, char kirjain, String merkkijono) {
 		StringBuilder piilo = new StringBuilder(this.piilosana);
 		this.arvatut += " " + (Character.toString(kirjain));
 		int tarkistus = 0;
 		int i = 0;
 		char syote = Character.toLowerCase(kirjain);
		for (i=0; i<pituus; i++) {
			if (merkkijono.charAt(i) == syote) {
				String k = Character.toString(syote);
				piilo.replace(i, i+1, k);
				this.piilosana = piilo.toString();
				tarkistus = 1;
			}
		}
		if (tarkistus == 0) {
			return false;
		}else 
			return true;
 	}
 	
 	
 	//Arvattujen kirjainten palautus
 	public String getArvatut() {
 		return this.arvatut.toString();
 	}
 	
 	//piilosanan palautus
 	public String getPiilosana() {
 		return this.piilosana;
 	}
 	
}
