import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TervetuloIkkuna extends Ikkuna {
	private PelaajatIkkuna pelaajat;
	
	
	
	/*Luodaan tervetuloikkuna
	 * Ikkunalla aloitetaan peli.
	 * Ikkunassa valitaan osallistuvien pelaajien lukum‰‰r‰.
	 */
	public TervetuloIkkuna(int width, int height, String title) {
		super(width, height, title);
		
		//Aluksi luodaan ikkunan komponentit ja muotoillaan asettelu
		BorderLayout paaAsettelu = new BorderLayout();
		this.setLayout(paaAsettelu);
		
		Ylapalkki ylapalkki = new Ylapalkki();
		JMenuBar menu = ylapalkki.getYlapalkki();
		
		JPanel labelPaneeli = new JPanel();
		BoxLayout boksiAsettelu = new BoxLayout(labelPaneeli, BoxLayout.Y_AXIS);
		labelPaneeli.setLayout(boksiAsettelu);
		
		JLabel tervetuloa = new JLabel("Tervetuloa Ventti-peliin!");
		tervetuloa.setBorder(new EmptyBorder(30, 30, 0, 0));
		tervetuloa.setFont(tervetuloa.getFont().deriveFont((float) 20.0));
		JLabel valitse = new JLabel("Valitse pelaajien m‰‰r‰ pankin lis‰ksi (1-3)");
		valitse.setBorder(new EmptyBorder(20, 50, 0, 0));
		
		JPanel valinta = new JPanel();
		BoxLayout valintaAsettelu = new BoxLayout(valinta, BoxLayout.X_AXIS);
		valinta.setLayout(valintaAsettelu);
		valinta.setBorder(new EmptyBorder(15, 100, 200, 200));
		
		JButton pelaajia1 = new JButton("1 pelaaja");
		JButton pelaajia2 = new JButton("2 pelaajaa");
		JButton pelaajia3 = new JButton("3 pelaajaa");
		
		//Komponenttien toiminnallsuuksien m‰‰rittely
		pelaajia1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				luoPelaaja(1);
			}			
		});
		
		pelaajia2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				luoPelaaja(2);
			}			
		});
		
		pelaajia3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				luoPelaaja(3);
			}			
		});
		
		
		//Komponenttien lis‰‰minen paneeleihin
		valinta.add(pelaajia1);
		valinta.add(pelaajia2);
		valinta.add(pelaajia3);

        labelPaneeli.add(tervetuloa);
        labelPaneeli.add(valitse);
        
        this.add(menu, BorderLayout.NORTH);
        this.add(labelPaneeli, BorderLayout.CENTER);
		this.add(valinta, BorderLayout.SOUTH);
	}
	
	
	/*Metodilla luodaan valittu m‰‰r‰ pelaajia
	 * Metodi saa parametrin‰‰n valitun pelaajien lukum‰‰r‰n.
	 * Pelaajatunnukset lis‰t‰‰n taulukkoon.
	 * Monesko muuttuja kertoo metodin kutsumien pelaajan luonti-ikkunoiden
	 * yl‰palkissa monennenko pelaajan luominen on menossa.
	 * Metodi kutsii pelaajatIkkuna.metodia ja antaa sille parametreina
	 * ikkunan koon, yl‰palkin tekstin sek‰ monettako pelaajaa ollaan
	 * luomassa,montako pelaajaa on yhteens‰ ja taulukon, johon lis‰t‰‰n
	 * luodut/ladatut pelaajat.
	 */
	public void luoPelaaja(int lkm) {	
		HashMap<Integer, Pelaaja> pelaajaTunnukset = new HashMap<>();
		//pelaajaTunnukset = new ArrayList<>();
		int monesko = 0;
		pelaajat = new PelaajatIkkuna(600, 400, "Pelaaja" + (monesko+1), monesko, lkm, pelaajaTunnukset);
		pelaajat.nayta();
		this.piilota();			
	}
	
}
