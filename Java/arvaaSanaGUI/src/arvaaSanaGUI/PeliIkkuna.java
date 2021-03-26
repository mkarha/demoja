package arvaaSanaGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PeliIkkuna {
	
	private JFrame ikkuna;
	private JLabel ratkaistava; //-----
	private JLabel ohje1;	//Ohjeteksti
	private JLabel ohje2;
	private JTextField arvaus; //kirjoitusalue
	private JLabel kuva; //Kuva-alue
	private JLabel arvatut; //Arvatut kirjaimet
	private Kuva kuvat;
	private Sana sanat;
	private Paaikkuna paaikkuna;
	private JButton poistu, uusiPeli;
	private Tarkastaja tarkastaja;
	
	public void luoPeli(String sana) { 
		this.kuvat = new Kuva();
		this.sanat = new Sana();
		this.paaikkuna = new Paaikkuna();
		this.tarkastaja = new Tarkastaja();
				
		//Luodaan 800x500 pikselin kokoinen ikkuna,
		//Sijoitetaan ikkuna keskelle n�ytt��
		this.ikkuna = new JFrame();  
		this.ikkuna.setSize(800, 500);  
		this.ikkuna.setLocationRelativeTo(null);
		this.ikkuna.setTitle("Hirsipuu");
		
		//Luodaan paneeli kirjaimen valitsemiselle ja siihen liittyv�lle ohjeelle sek� tarkistusnapille
		JPanel paneeli = new JPanel();
		BoxLayout paneeliasettelu = new BoxLayout(paneeli, BoxLayout.Y_AXIS);
		paneeli.setLayout(paneeliasettelu);
		
		//Luodaan alapaneeli arvatuille kirjaimille ja poistumisnapille
		JPanel alapaneeli = new JPanel();
		BorderLayout alaAsettelu = new BorderLayout();
		alapaneeli.setLayout(alaAsettelu);
		
		String piilosana = sanat.luoPiilosana(sana.length()); //luodaan arvotun sanan mittainen "viivasana"
			
		//Luodaan sis�ll�t	
		this.ratkaistava = new JLabel(piilosana);
		this.ratkaistava.setFont(ratkaistava.getFont().deriveFont((float) 60.0)); //----- koko
		this.ratkaistava.setBorder(new EmptyBorder(new Insets(10, 200, 0, 0))); //------n ymp�rille tyhj�� reunaa
		
		this.ohje1 = new JLabel("sy�t� arvattava kirjain");		
		this.ohje1.setFont(ratkaistava.getFont().deriveFont((float) 20.0)); 
		this.ohje2 = new JLabel("tekstikentt��n");		
		this.ohje2.setFont(ratkaistava.getFont().deriveFont((float) 20.0)); 
		
		this.arvaus = new JTextField(1);
		this.arvaus.setSize(40, 20);
		this.arvaus.setFont(ratkaistava.getFont().deriveFont((float) 30.0)); 
		
		this.kuva = new JLabel(kuvat.getKuva());
		
		this.arvatut = new JLabel("Arvattu: " + sanat.getArvatut());
		this.arvatut.setFont(arvatut.getFont().deriveFont((float) 30.0)); //arvattujen koko
		this.arvatut.setBorder(new EmptyBorder(new Insets(0, 20, 10, 0))); //reunat arvattujen ymp�rille
		
		JPanel nappipaneeli = new JPanel();
		GridLayout nappipaneeliasettelu = new GridLayout(1, 2);
		nappipaneeli.setLayout(nappipaneeliasettelu);
		this.poistu = new JButton("Palaa alkuun"); //Paluu nappi
		this.uusiPeli = new JButton("Uusi peli");
		nappipaneeli.add(uusiPeli);	
		nappipaneeli.add(poistu);	
		
		//M��ritell��n tyhj�t reunat tekstinsy�t�n ymp�rille ja lis�t��n sis�lt� paneeliin
		paneeli.setBorder(new EmptyBorder(new Insets(100, 80, 150, 20)));
		paneeli.add(ohje1);
		paneeli.add(ohje2);
		paneeli.add(arvaus);
	
			
		//Lis�t��n sis�lt� alapaneeliin
		alapaneeli.add(arvatut, BorderLayout.WEST);
		alapaneeli.add(nappipaneeli, BorderLayout.EAST);	
		
		//Mik�li arvauskent�ss� painetaan entteri�, suoritetaan tarkasta napin painallista vastaava toiminnallisuus
		this.arvaus.addKeyListener
	      (new KeyAdapter() {
	          public void keyPressed(KeyEvent k) {
	        	  
	        	  kirjainToiminnallisuus(sana, k);
	            
	         }
	      });
		
			
		//m��ritell��n poistu napin toiminnallisuus
		this.poistu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paaikkuna.nayta();
				ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				ikkuna.setVisible(false);			
			}			
		});
		
		//m��ritell��n uusiPeli napin toiminnallisuus
		this.uusiPeli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paaikkuna.uusiPeli();
				ikkuna.setVisible(false);			
			}			
		});
		
		//Lis�t��n sis�lt� peli-ikkunaan
		this.ikkuna.add(ratkaistava, BorderLayout.NORTH);
		this.ikkuna.add(paneeli, BorderLayout.WEST);
		this.ikkuna.add(kuva, BorderLayout.EAST);
		this.ikkuna.add(alapaneeli, BorderLayout.SOUTH);
					
		this.ikkuna.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		this.ikkuna.setVisible(true);		
	}
	
	
	
	//Viieisen arvauksen j�lkeisen painalluksen m��rittely
	private void poistuminen(String sana) {
		if (sana.equals(this.sanat.getPiilosana()) || this.kuvat.getVirheet()==10) {
			this.arvaus.addKeyListener
		      (new KeyAdapter() {
		          public void keyPressed(KeyEvent k) {
		        	  poistu.doClick(20);		            
		         }
		      });
		}	
	}
	
	
	private void kirjainToiminnallisuus(String sana, KeyEvent k) {
        char merk = k.getKeyChar();
        String arvaukset = sanat.getArvatut();
        arvaus.setText(""); //Tyhjennet��n arvaus-kent�n teksti
        if (sanat.tarkastaSana(merk, 1)==false) {
			ohje1.setText("Sy�t� ainoastaan kirjaimia");
			ohje2.setText("a-�:n v�lilt�.");
			return;
		}
		else if(tarkastaja.onkoKirjainta(arvaukset.length(), merk, arvaukset)) {
			ohje1.setText("Olet jo arvannut " + k.getKeyChar() + ":n.");
			ohje2.setText("Ole hyv� ja valitse toinen kirjain");
			return;
		}
		else {
			this.ohje1.setText("sy�t� arvattava kirjain");	
			this.ohje2.setText("tekstikentt��n");		
		}
        
        
		if (this.sanat.onkoKirjainta(sana.length(), merk, sana)==false) {
			kuvat.lisaaVirhe();
			kuva.setIcon(kuvat.getKuva());
			arvatut.setText("Arvattu: " + sanat.getArvatut());
		}else {
			ratkaistava.setText(sanat.getPiilosana());
			arvatut.setText("Arvattu: " + sanat.getArvatut());
		}
		
		
		//Jos sana vastaa arvauksista muodostunutta sanaa
		if(sana.equals(sanat.getPiilosana())) {
			kuva.setIcon(kuvat.voitto()); //Haetaan voitto-kuva
			poistuminen(sana);
		}
		
		//jos virheraja saavutetaan
		if(kuvat.getVirheet()==10) {
			ratkaistava.setText(sana);
			arvaus.setActionMap(null);
			poistuminen(sana);
		}
	}

	

}