package arvaaSanaGUI;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Kuva{
	
	private ArrayList<ImageIcon> vaarat;
	private int virhe;

	//Alustetaan virhekuvat Araylistiin
	public Kuva() {
		vaarat = new ArrayList<>();
		try {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/tyhja.png"));
			vaarat.add(imageIcon);
			imageIcon = new ImageIcon(this.getClass().getResource("/kuva1.png"));
			vaarat.add(imageIcon);
			imageIcon = new ImageIcon(this.getClass().getResource("/kuva2.png"));
			vaarat.add(imageIcon);
			imageIcon = new ImageIcon(this.getClass().getResource("/kuva3.png"));
			vaarat.add(imageIcon);
			imageIcon = new ImageIcon(this.getClass().getResource("/kuva4.png"));
			vaarat.add(imageIcon);
			imageIcon = new ImageIcon(this.getClass().getResource("/kuva5.png"));
			vaarat.add(imageIcon);
			imageIcon = new ImageIcon(this.getClass().getResource("/kuva6.png"));
			vaarat.add(imageIcon);
			imageIcon = new ImageIcon(this.getClass().getResource("/kuva7.png"));
			vaarat.add(imageIcon);
			imageIcon = new ImageIcon(this.getClass().getResource("/kuva8.png"));
			vaarat.add(imageIcon);
			imageIcon = new ImageIcon(this.getClass().getResource("/kuva9.png"));
			vaarat.add(imageIcon);
			imageIcon = new ImageIcon(this.getClass().getResource("/kuva10.png"));
			vaarat.add(imageIcon);
		}
		catch (Exception e) {
			System.out.println("Tiedostoa ei löytynyt");
		}
		this.virhe = 0;
			  
	}
	
	//Palautteaan kuva
	public ImageIcon getKuva() {
		return vaarat.get(this.virhe);
	}
	
	//Palautetaan voitto-kuva ja nollataan virheet (turhaa, mikäli ei uusi peli mahdollisuutta)
	public ImageIcon voitto() {
		this.virhe = 0;
		return new ImageIcon(this.getClass().getResource("/voitto.png"));
	}
	
	//Lisätään virheiden lkm yhdellä
	public void lisaaVirhe() {
		this.virhe++;
	}
	
	//Palautetaan virheiden määrä
	public int getVirheet() {
		return this.virhe;
	}
}
