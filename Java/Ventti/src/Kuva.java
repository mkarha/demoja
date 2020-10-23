import javax.swing.ImageIcon;

public class Kuva {
	
	private String lahde;
	private ImageIcon kuva;

	public Kuva(String lahde) {
		this.lahde = lahde;
		try { //yritet‰‰n ladatakuva
			this.kuva = new ImageIcon(this.getClass().getResource("/kuvat" + this.lahde));
		}
		catch (Exception e) {
			
		}
	}
	
	public String getKuvalahde() {
		return this.lahde;
	}
	
	public ImageIcon getKuva() {
		return this.kuva;
	}
	
	public void setKuva(String lahde) {
		this.lahde = lahde;
	}

}
