package arvaaSanaGUI;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Syottoikkuna {
	
	private JFrame ikkuna;
	
	public void luoIkkuna() {

		Sana sanat = new Sana();
		sanat.lataa();
		Paaikkuna paaikkuna = new Paaikkuna();
		PeliIkkuna peli = new PeliIkkuna();
		Tarkastaja tarkastaja = new Tarkastaja();
		
		//Luodaan 300x100 pikselin kokoinen ikkuna,
		//Sijoitetaan ikkuna keskelle näyttöä
		this.ikkuna = new JFrame();  
		this.ikkuna.setSize(350, 120);  
		this.ikkuna.setLocationRelativeTo(null);
		this.ikkuna.setTitle("Oma sana");
		
		//Asettelu ikkunaan
		BorderLayout boksi = new BorderLayout();
		ikkuna.setLayout(boksi);
		
		//paneeli napeille ja asettelu
		JPanel paneeli = new JPanel();
		BoxLayout vaakaboksi = new BoxLayout(paneeli, BoxLayout.X_AXIS);
		paneeli.setLayout(vaakaboksi);
		
		//Määritellään sisällöt
		JLabel ohje = new JLabel("Syötä sanasi");
		ohje.setBorder(new EmptyBorder(new Insets(0, 120, 0, 0)));
		JTextArea omasana = new JTextArea();
		omasana.setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
		JButton tallenna = new JButton("Tallenna");
		JButton palaa = new JButton("Poistu");
		JButton testaa = new JButton("Testaa oma sana");
		
		//lisätään napit paneeliin
		paneeli.setBorder(new EmptyBorder(new Insets(10, 30, 0, 0)));
		paneeli.add(tallenna);
		paneeli.add(palaa);
		paneeli.add(testaa);
		
		omasana.addKeyListener
	      (new KeyAdapter() {
	          public void keyPressed(KeyEvent k) {
	            int key = k.getKeyCode();
	            
	            if (key == KeyEvent.VK_ENTER) {
	            	String syote = omasana.getText().replace("\n", "");
	            	omasana.setText(syote);
	            	tallenna.doClick();
	            }
	         }
	      
	      });
		
		//tallenna-napin toiminnallisuus
		tallenna.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sana = omasana.getText();
				if(sana.length()<3) {
					ohje.setText("Sanassa tulee olla vähintään 3 kirjainta");
				}
				else if(tarkastaja.tarkastaSana(sana, sana.length())==false) {
					ohje.setText("Sana saa sisältää vain kirjaimia a-z");
				}
				else if (sanat.onkoSanaJoOlemassa(sana)==false) {
					if(sanat.tallenna()==true) {
						ohje.setText("Sana lisätty onnistuneesti");
						
						//Onnistuneen lisäyksen jälkeen määritellään testaa-napin toiminnallisuus
						testaa.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								peli.luoPeli(sana); //Luodaan peli-ikkuna
								ikkuna.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
								//peli.setVisible(true);
								ikkuna.setVisible(false);
							}
						});
					}
				} else {
					ohje.setText("Sana oli jo olemassa. Syötetäänkö uusi sana");
				}
			}
		});
		
		//palaa-napin toiminnallisuus
		palaa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paaikkuna.nayta();
				//paaikkuna.setVisible(true);
				//ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				ikkuna.setVisible(false);				
			}
			
		});
		
		//lisätään sisällöt ikkunaan
		ikkuna.add(ohje, BorderLayout.NORTH);
		ikkuna.add(omasana, BorderLayout.CENTER);
		ikkuna.add(paneeli, BorderLayout.SOUTH);
	
		this.ikkuna.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		this.ikkuna.setVisible(true);
	}
}
