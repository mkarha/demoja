import javax.swing.ImageIcon;

//Juoma-luokka toimii juomien yliluokkana
public class Juoma {
	
	private String nimi;
	private int lkm;
	private ImageIcon kuva;

	//komstruktorissa alustetaan juomalle nimi, m‰‰r‰ ja kuva
	public Juoma (String nimi, int lkm) {
		this.nimi = nimi;
		this.lkm = lkm;
		try { //yritet‰‰n ladata juoman kuva
			kuva = new ImageIcon(this.getClass().getResource("/" + this.nimi.toLowerCase() + ".jpg"));
		}
		catch (Exception e) {
			
		}
	}
	
	//osta v‰hent‰‰ juoman m‰‰r‰‰ yhdell‰, mik‰li juomia on v‰hint‰‰n 1
	public void osta() {
		if(this.lkm>0) {
			this.lkm -= 1;
		}
	}

	//lataa lis‰‰ yll‰pidon m‰‰rittelem‰n m‰‰r‰n annoksia automaattiin
	public void lataa (int lkm) {
		this.lkm += lkm;
	}
	
	//setLkm m‰‰rittelee tietyn juomam‰‰r‰n automaattiin
	//m‰‰r‰ ei voi olla negatiivinen
	public void setLkm(int lkm) {
		if(lkm>0 ) {
			this.lkm = lkm;
		}
	}	
	
	//getLkm palauttaa juomien m‰‰r‰
	public int getLkm() {
		return this.lkm;
	}
	
	//getNimi palauttaa juoman nimen
	public String getNimi() {
		return this.nimi;
	}
	
	//setNimi-metodilla voi p‰ivitt‰‰ juoman nimen
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	//getKuva palauttaa juoman kuvakkeen
	public ImageIcon getKuva() {
		return this.kuva;
	}
	
}
