import java.util.ArrayList;

public class Kasi {
	
	private int arvo, monesko;
	private ArrayList<Kortti> kortit;
	private Kortti kortti;
	
	public Kasi() {
		this.arvo = 0;
		this.monesko = 0;
		this.kortit = new ArrayList<>();
	}
	
	
	//Lis‰t‰‰n k‰des‰ olevien korttien m‰‰r‰‰ ja j‰rjestet‰‰n kortit suurimmasta pienimp‰‰n
	public void lisaaKortti(Kortti kortti) {
		
		this.kortit.add(kortti);
	    if(this.monesko>0)  {
	    	/*Mik‰li ei ensimm‰inen kortti, j‰rjestet‰‰n pienin kortti k‰dess‰ viimeiseksi.
	        *Tarkoituksena saada ‰ss‰ viimeiseen laskettavaan soluun, jotta voidaan m‰‰ritt‰‰ ‰ss‰lle
	        *vaihtoehtoiset arvot 1 tai 14
	    	*
	        *tarkistetaan onko jaettu kortti pienempi kuin edellisess‰ solussa oleva kortti
	        **/
	        if(kortit.get(this.monesko).getArvo()>kortit.get(this.monesko-1).getArvo()) {
	            Kortti korttiPieni = kortit.get(this.monesko-1);
	            kortit.remove(this.monesko-1);
	            kortit.add(korttiPieni);
	        }
	    }
	    this.monesko++;		
	}
	
	
	//Korttien m‰‰r‰n palauttava getteri
	public int getKorttienMaara() {
		monesko = this.kortit.size();
		return monesko;
	}
	
	//Tietyss‰ indeksiss‰ olevan kortin palauttava korttigetteri
	public Kortti getKortti(int indeksi) {
		this.kortti = this.kortit.get(indeksi);
		return this.kortti;
	}
	
	
	//K‰den arvon laskeminen
	public void laskeArvo(int arvo) {
		this.arvo += arvo;
		
	}
	
	
	//Setteri k‰den arvolle
	public void setArvo(int arvo) {
		this.arvo = arvo;
	}

	
	//K‰den arvon palauttava getteri
	public int getArvo() {
		arvo = 0;
	    for(int i=0; i<this.kortit.size(); i++)
	    {
	        this.kortti = this.kortit.get(i);
	        /*Mik‰li kortti on ‰ss‰, tarkistetaan kuinka toimitaan
	        *Jos k‰den arvo on 7 tai alle ja kyseess‰ on viimeinen kortti annetaan
            *‰ss‰lle arvo 14. Muuten 1.
            **/
	        if(this.kortti.getArvo()==1)
	        {
	            if(21-arvo>=14)
	            {
	                arvo += 14;
	            }
	            else
	            {
	                arvo += 1;
	            }
	        }
	        //Muiden korttien arvo lis‰t‰‰n k‰teen sellaisenaan
	        else
	        {
	            arvo += kortti.getArvo();
	        }
	    }

	    return arvo;
	}
	
	
	//K‰den taulukkomuodossa palauttava getteri  
	public ArrayList<Kortti> palautaKasi() {
		return this.kortit;
	}

}
