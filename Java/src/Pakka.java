import java.util.ArrayList;

public class Pakka {

	private Kortti kortti;
	private ArrayList<Kortti> kortit;
	private int korttejaPakassa;
	
	
	/*Luodaan pakka parametrittomalla konstruktorilla
	 * K‰yd‰‰n ensin 4:n suuruisella (0-3) for-lauseella l‰pi maat ja
	 * 13 suuruisella for-lauseella arvot (1-13) l‰pi ja lis‰t‰‰n kukin
	 * kortti arraylist-muotoiseen pakkaan Kortti-muodossa.
	 * Lopuksi alustetaan korttipakan koko.
	 */
	public Pakka() {
		this.kortit = new ArrayList<>();
		//maat
	    for(int i=0; i<4; i++)
	    {
	    	//arvot
	        for(int j=1; j<14; j++)
	        {
	        	this.kortti = new Kortti(i, j);
	            this.kortit.add(kortti);	           
	        }
	    }		
		this.korttejaPakassa = this.kortit.size();
	}

	
	//Pakassa olevien korttien m‰‰r‰n palauttava getteri
	public int getKorttejaPakassa() {
		return this.korttejaPakassa;
	}
	
	
	/*Kortin jako
	 * Palauttaa kortin annetusta indeksist‰
	 * Indeksi arvotaan peli-ohjelmassa
	 */
	public Kortti jaaKortti(int indeksi) { 
		Kortti jaettava = this.kortit.get(indeksi);
		return jaettava;
	}
	
	/*Kortin poistaminen pakasta
	 *Poistaa kortin m‰‰ritellyst‰ indeksista ja p‰ivitt‰‰ korttipakan koon
	 */
	public void poistaKorttiPakasta(int indeksi) {
		this.kortit.remove(indeksi);
		this.korttejaPakassa = this.kortit.size();
	}
	
}
