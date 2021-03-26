package arvaaSanaGUI;

public class Tarkastaja {
	
	/*tarkistetaan sana laittomien merkkien osalta*/
	public boolean tarkastaSana(String sana, int pituus) {
		int i = 0;
		int tark = 0;
			for (i=0; i<pituus; i++) {
				char c = sana.charAt(i);
                if ( c == 'å' || c == 'ä' || c == 'ö' )
                {
                	tark = 0;
                }
                else if (c < 0x2d || (c >= 0x2e && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a) {
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
 	public String onkoKirjainta(int pituus, char kirjain, String merkkijono, String piilosana) {
 		StringBuilder piilo = new StringBuilder(piilosana);
 		int i = 0;
 		char syote = Character.toLowerCase(kirjain);
		for (i=0; i<pituus; i++) {
			if (merkkijono.charAt(i) == syote) {
				String k = Character.toString(syote);
				piilo.replace(i, i+1, k);
			}
		}
		
		return piilo.toString();
 	}
 	
 	public boolean onkoKirjainta(int pituus, char kirjain, String merkkijono) {
 		int tarkistus = 0;
 		int i = 0;
		for (i=0; i<pituus; i++) {
			if (merkkijono.charAt(i) == kirjain) {
				tarkistus = 1;
			}
		}
		if (tarkistus == 0) {
			return false;
		}else return true;
 	}
	
}
