import javax.swing.ImageIcon;

public class Kortti {

	private int arvo;
	private int maa;
	private ImageIcon kuvaIso;

	private String kuvaLahde;
	
	
	//Kortin luova parametriton konstruktori
	public Kortti() {
		this.maa = 0;
		this.arvo = 0;
		this.kuvaLahde = "/kuvat" + this.getMaa() + this.arvo;
	}
	
	
	//Konstruktori, jossa annetaan kortille maa ja arvo, joiden  avulla haetaan myös kortin kuva
	public Kortti (int maa, int arvo) {
		this.maa = maa;
		this.arvo = arvo;
		this.kuvaLahde = "/kuvat/" + this.getMaa().toLowerCase() + this.arvo + ".png";
		try { //yritetään ladata kortin kuva
			this.kuvaIso = new ImageIcon(this.getClass().getResource(kuvaLahde));
		}
		catch (Exception e) {
			
		}
	}
	
	
	//Kortin arvon palauttava getteri
	public int getArvo() {
		return this.arvo;
	}
	
	//Kortin maan palauttava getteri
	public String getMaa(){
	    if (this.maa == 0)
	    {
	        return "ruutu";
	    }
	    else if (this.maa == 1)
	    {
	        return "risti";
	    }
	    else if (this.maa == 2)
	    {
	        return "hertta";
	    }
	    else
	    {
	        return "pata";
	    }
	}
	
	
	//Kortin kuvan kivaikonina palauttava getteri
	public ImageIcon getKuva() {
		return this.kuvaIso;
	}

	
	//Kortin kuvan sijainnin tekstimuodossa palauttava getteri
	public String getKuvaLahde() {
		return this.kuvaLahde;
	}
	

	//Kortin tietojen palauttaminen tekstimuodossa
	@Override
	public String toString() {
		return this.getMaa() + " " + this.arvo;
	}
}
