package arvaaSanaGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Paaikkuna{
	
	private JFrame paaikkuna;
	
	public Paaikkuna() {

		
		Sana sanat = new Sana();
		
		sanat.lataa(); //Ladataan sanat tiedostosta
		
		
		Syottoikkuna syotto = new Syottoikkuna();
		PeliIkkuna peli = new PeliIkkuna();
	

		//Luodaan 800x500 pikselin kokoinen p‰‰ikkuna
		//Sijoitetaan ikkuna keskelle n‰yttˆ‰
		this.paaikkuna = new JFrame();  
		this.paaikkuna.setSize(800, 500); 
		this.paaikkuna.setLocationRelativeTo(null);
		this.paaikkuna.setTitle("Hirsipuu");
		
				
		//M‰‰ritell‰‰n p‰‰ikkunan layoutiksi BorderLayout
		BorderLayout sijoittelija = new BorderLayout();  
		this.paaikkuna.setLayout(sijoittelija);
		
		
		JPanel nappipaneeli = new JPanel();
		GridLayout napit = new GridLayout(1,3);
		nappipaneeli.setLayout(napit);
		
		JPanel ohjepaneeli = new JPanel();
		BoxLayout ohjeet = new BoxLayout(ohjepaneeli, BoxLayout.Y_AXIS);
		ohjepaneeli.setLayout(ohjeet);
		
		
		
		//Esittelyteksti	
		JLabel rivi0 = new JLabel("Tervetuloa Hirsipuu-peliin!");
		JLabel rivi1 = new JLabel("Pelin ideana on arvata kirjaimia ja niiden avulla");
		JLabel rivi2 = new JLabel("selvitt‰‰ piilossa oleva sana.");
		JLabel rivi3 = new JLabel("Mik‰li arvaamaasi kirjainta ei lˆydy sanasta, virhetilisi karttuu.");
		JLabel rivi4 = new JLabel("Yli 10 v‰‰r‰‰ arvausta ja kuolo korjaa.\n");
		JLabel rivi5 = new JLabel("Onnea peliin!");
		rivi0.setFont(rivi0.getFont().deriveFont((float) 20.0));
		rivi1.setFont(rivi1.getFont().deriveFont((float) 20.0));
		rivi2.setFont(rivi2.getFont().deriveFont((float) 20.0));
		rivi3.setFont(rivi3.getFont().deriveFont((float) 20.0));
		rivi4.setFont(rivi4.getFont().deriveFont((float) 20.0));
		rivi5.setFont(rivi5.getFont().deriveFont((float) 20.0));
		
		//Lis‰t‰‰n sis‰ltˆ ohjepaneeliin
		ohjepaneeli.setBorder(new EmptyBorder(new Insets(30, 100, 0, 30)));
		ohjepaneeli.add(rivi0);
		ohjepaneeli.add(rivi1);
		ohjepaneeli.add(rivi2);
		ohjepaneeli.add(rivi3);
		ohjepaneeli.add(rivi4);
		ohjepaneeli.add(rivi5);
		
		//Luodaan sis‰llˆt
		JLabel otsikko = new JLabel("Hirsipuu");
		otsikko.setFont(otsikko.getFont().deriveFont((float) 30.0));
		otsikko.setBorder(new EmptyBorder(new Insets(30, 100, 0, 30)));
		JButton poistu = new JButton("Poistu");
		JButton arvoSana = new JButton("Arvo sana");
		JButton omaSana = new JButton("Lis‰‰ oma sana");
		
		//M‰‰ritell‰‰n poistu-napin toiminnallisuus
		poistu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//paaikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				paaikkuna.setVisible(true);
				System.exit(0);
			}
			
		});

		//M‰‰ritell‰‰n arvo sana-napin toiminnallisuus
		arvoSana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sana = sanat.arvoSana(); //arvotaan satunnainen sana
				peli.luoPeli(sana); //Luodaan peli-ikkuna
				//peli.nayta();
				//paaikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				paaikkuna.setVisible(false); //Suljetaan p‰‰ikkuna
				
			}
			
		});
		
		//M‰‰ritell‰‰n uuden sanan luomisen toiminnallisuus
		omaSana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				syotto.luoIkkuna();
				paaikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				paaikkuna.setVisible(false);
			}
			
		});
		
		//Lis‰t‰‰n napit paneeliin
		nappipaneeli.add(arvoSana);
		nappipaneeli.add(omaSana);
		nappipaneeli.add(poistu);
				
		//Lis‰t‰‰n sis‰ltˆ p‰‰ikkunaan
		this.paaikkuna.add(otsikko, BorderLayout.NORTH);
		this.paaikkuna.add(ohjepaneeli, BorderLayout.CENTER);
		this.paaikkuna.add(nappipaneeli, BorderLayout.SOUTH);
		
	}
	
	public void nayta () {
		this.paaikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.paaikkuna.setVisible(true);
	}
	
	



}

