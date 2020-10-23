import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class PeliIkkuna extends Ikkuna{
	
	private ImageIcon kuva;
	private String pTunnus, jakaja;
	private Pelaaja pelaaja, p;
	private JPanel vasen, p2, jakajaPaneeli, oikeaYla, oikeaKeski, oikeaAla;
	private JMenuBar menu;
	
	private JButton nosta, jaa;
	private JTextArea teksti;
	
	public PeliIkkuna(int width, int height, String title, ArrayList<String> pelaajat, HashMap <Integer, JLabel> korttiRuudut, Peli peli) {
		super(width, height, title);

		this.pelaaja = new Pelaaja();
		//M‰‰ritell‰‰n p‰‰ikkunan layout
		BorderLayout paaikkunaAsettelu = new BorderLayout();
		this.setLayout(paaikkunaAsettelu);
		
		//Luodaan yl‰palkki ja yl‰valikko asetuksille
		Ylapalkki ylapalkki = new Ylapalkki();
		menu = ylapalkki.getYlapalkki();
				
		//Ensin p‰‰ikkunan vasen puoli
		vasen = new JPanel();
		BorderLayout vasenAsettelu = new BorderLayout();
		vasen.setLayout(vasenAsettelu);
		
		//Luodaan vasen-paneeliin kaksi JPanelia
		//keskialueella n‰kyy vuorossa olevan pelaajan kuvake ja nimi sek‰ alla summa, josta pelataan
		//Ei aktiiviset pelaajat sis‰lt‰‰ tiedon muista pelaajista
		JPanel eiAktiivisetPelaajat = new JPanel();
		GridLayout eiAktiivisetPelaajatAsettelu = new GridLayout(3, 1);
		eiAktiivisetPelaajat.setLayout(eiAktiivisetPelaajatAsettelu);
			
		new ArrayList<>();
		
		//Keskialueen alustus ja muotoilun m‰‰rittely
		JPanel keskiAlue = new JPanel();
		GridLayout keskiAlueAsettelu = new GridLayout(2,1);
		keskiAlue.setLayout(keskiAlueAsettelu);
		keskiAlue.setBorder(new EmptyBorder(50, 50, 100 ,50));
			
		//k‰yd‰‰n ikkunan k‰ynnistyksen yhteydess‰ pelist‰ toimitettu pelaajataulukko l‰pi ja p‰ivitet‰‰n 
		//pelaajaruudut
		
		//k‰yd‰‰n l‰pi kaikki alkiot
		for (int i=0; i<pelaajat.size(); i++) {
			pTunnus = pelaajat.get(i);						//haetaan pelaajatunnus String-muodossa
			if(pTunnus.equals("Jakaja")) {					//jos pelaajatunnus on jakaja
				jakaja = pelaajat.get(i);					//asetetaan jakajaksi i:nnen alkion nimi
				Kasi jakajanKasi = peli.getPelaajanKasi("Jakaja");
				String arvo = peli.getSyote(jakajanKasi.getArvo());
				Pelaaja jakajaP = new Pelaaja(jakaja, jakajanKasi.getKorttienMaara(), jakajanKasi.getArvo()); //jakaja ei ole talletettuna tiedostoon, joten luodaan uusi jakaja-niminen pelaaja
				jakajaPaneeli = luoPelaajaRuutu();			//luodaan jakajan tiedoille JPaneeli
				JLabel jakajaNaama = lisaaPelaajanKuva("/kuvat/banker.jpg"); //asetetaan jakajalle kuvake
				JLabel jakajaNimi = new JLabel(jakaja + ": ");	//asetetaan jakajan nimi
				JLabel jakajaTulos = new JLabel("" + arvo); //haetaan jakajan kaden arvo
				JPanel nimiJaTulosjakaja = new JPanel();	//luodaan jakajan nimi ja tulos label
				nimiJaTulosjakaja = nimiJaTulosAsettelu(nimiJaTulosjakaja, jakajaNimi, jakajaTulos); //k‰yd‰‰n t‰ytt‰m‰ss‰ label muotoiluineen
				jakajaPaneeli.add(nimiJaTulosjakaja);		//lis‰t‰‰n nimi ja tulos paneeliin
				jakajaPaneeli.add(jakajaNaama);				//lis‰t‰‰n kuvake paneeliin
				if (i==0) {
					keskiAlue.add(jakajaPaneeli);			//mik‰li vuorossa, asetetaan keskialue-paneeliin
				}else {
					eiAktiivisetPelaajat.add(jakajaPaneeli);	//jos ei, lis‰t‰‰n eiaktiiviset-paneeliin
				}
				//pelaajanTulosRuudut.add(jakajaTulos);		
			}else {											//muut pelaajat kuin jakaja
				pelaaja = pelaaja.lataaPelaaja(pTunnus);	//ladataan pelaajan nimi
				p2 = luoPelaajaRuutu();						//k‰ytet‰‰n luo ruutu-metodia pelaaja-paneelin luontiin
				Kasi pelaajanKasi = peli.getPelaajanKasi(pTunnus);
				String arvo = peli.getSyote(pelaajanKasi.getArvo());
				JLabel p2Naama = lisaaPelaajanKuva(pelaaja.getKuvaLahde()); //luodaan pelaajan kuva
				JLabel p2Nimi = new JLabel(pelaaja.getKayttaja() + ": ");				//luodaan label pelaajan nimelle
				JLabel p2Tulos = new JLabel("" + arvo);		//luodaan label pelaajan tulokselle
				JPanel nimiJaTulos2 = new JPanel();							//luodaan paneeli nimelle ja tulokselle
				nimiJaTulos2 = nimiJaTulosAsettelu(nimiJaTulos2, p2Nimi, p2Tulos); //lis‰t‰‰ nimi ja tulos paneeliin
				p2.add(nimiJaTulos2);								//lis‰t‰‰n nimi ja tulos-paneeli pelaajapaneeliin
				p2.add(p2Naama);									//ja alle kuva
				if(i==0) {
					keskiAlue.add(p2);			//mik‰li vuorossa oleva pelaaja, lis‰t‰‰n keskipaneeliin
				}else {
					eiAktiivisetPelaajat.add(p2);	//muuten ei aktiivisiin
				}
			}
		}
		
		vasen.add(eiAktiivisetPelaajat, BorderLayout.WEST);
		vasen.add(keskiAlue, BorderLayout.EAST);
		
		//P‰‰ikkunan oikea puoli
		JPanel oikea = new JPanel();
		BoxLayout oikeaAsettelu = new BoxLayout(oikea, BoxLayout.Y_AXIS);
		oikea.setLayout(oikeaAsettelu);
		
		/*Luodaan p‰‰ikkunan oikealle puolelle 3 paneelia p‰‰llekk‰in
		*Ylin paneeli sis‰lt‰‰ 5 ruutua vierekk‰in jaettavien korttien ikoneja varten
		*Keskimm‰inen paneeli 4 ruutua kortteja varten
		*alimmaisessa ruudussa on pelin k‰yttˆnapit "nosta lis‰‰" ja "j‰‰"
		*
		*ylimm‰n komanneksen t‰yttˆ ja muotoilu
		**/
		oikeaYla = new JPanel();
		BoxLayout oikeaYlaAsettelu = new BoxLayout(oikeaYla, BoxLayout.X_AXIS);
		oikeaYla.setLayout(oikeaYlaAsettelu);
		
		JLabel yla1 = korttiRuudut.get(1);
		JLabel yla2 = korttiRuudut.get(2);
		JLabel yla3 = korttiRuudut.get(3);
		JLabel yla4 = korttiRuudut.get(4);
		JLabel yla5 = korttiRuudut.get(5);
		
		oikeaYla.add(yla1);
		oikeaYla.add(yla2);
		oikeaYla.add(yla3);
		oikeaYla.add(yla4);
		oikeaYla.add(yla5);
		
		
		
		//keskimm‰isen kolmanneksen t‰yttˆ ja muotoilu
		oikeaKeski = new JPanel();
		BoxLayout oikeaKeskiAsettelu = new BoxLayout(oikeaKeski, BoxLayout.X_AXIS);
		oikeaKeski.setLayout(oikeaKeskiAsettelu);
		
		JLabel keski1 = korttiRuudut.get(6);		
		JLabel keski2 = korttiRuudut.get(7);
		JLabel keski3 = korttiRuudut.get(8);
		JLabel keski4 = korttiRuudut.get(9);
		
		oikeaKeski.add(keski1);
		oikeaKeski.add(keski2);
		oikeaKeski.add(keski3);
		oikeaKeski.add(keski4);
		
		//Alakolmanneksen t‰yttˆ ja muotoilu
		oikeaAla = new JPanel();
		GridLayout oikeaAlaAsettelu = new GridLayout(1, 3);
		oikeaAla.setLayout(oikeaAlaAsettelu);
		
			
		
		//Lis‰t‰‰n alariville pelinapit ja tekstialue
		JPanel oikeaAlaOikea = new JPanel();
		oikeaAlaOikea.setBorder(new EmptyBorder(50, 100, 100 ,100));
		JPanel oikeaAlaVasen = new JPanel();
		oikeaAlaVasen.setBorder(new EmptyBorder(50, 100, 100 ,100));
		JButton nosta = new JButton("Nosta"); //mahdollisesti kortti-ikoni olisi selke‰mpi
		teksti = new JTextArea("Ventti\n\nNosta-napilla nostat lis‰‰ kortteja.\nJ‰‰-nappi lopettaa vuoron.");
		teksti.setBorder(new EmptyBorder(50, 50, 50 ,50));
		JButton jaa = new JButton("J‰‰");	  //mahdollisesti stoppi- tai muu ikoni
		
		//Nappien toiminnallisuuden m‰‰rittely
		

		nosta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				peli.nostaKortti();
				sulje();	
			}			
		});
	
		jaa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (peli.getLoppu()) {
					ylapalkki.painaExit();
				}
				peli.lopetaVuoro();
				sulje();	

			}			
		});
		
		oikeaAlaOikea.add(jaa);
		oikeaAlaVasen.add(nosta);
		
		oikeaAla.add(oikeaAlaVasen);
		oikeaAla.add(teksti);
		oikeaAla.add(oikeaAlaOikea);
		
		//Lis‰t‰‰n sis‰llˆt oikealle ikkunapuoliskolle		
		oikea.add(oikeaYla);
		oikea.add(oikeaKeski);
		oikea.add(oikeaAla);
		
		//Lis‰t‰‰n ja asemoidaan komponentit p‰‰ikkunaan
		this.add(menu, BorderLayout.NORTH);
		this.add(vasen, BorderLayout.WEST);
		this.add(oikea, BorderLayout.CENTER);
		
					
		
	
	}

	
	
	//Luodaan passiivisille pelaajille s‰ilˆt
	public JPanel luoPelaajaRuutu() {
		JPanel panel = new JPanel();
		BoxLayout panelAsettelu = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(panelAsettelu);
		return panel;
	}
	
	
	//Asetellaan nimi ja tulos pelaajaruutuun
	public JPanel nimiJaTulosAsettelu(JPanel p, JLabel l1, JLabel l2) {
		JPanel paneeli = p;
		BoxLayout asettelu = new BoxLayout(paneeli, BoxLayout.X_AXIS);
		paneeli.setLayout(asettelu);
		paneeli.add(l1);
		paneeli.add(l2);
		return paneeli;
	}
	
	//Luodaan tyhj‰t ruudut korttien paikoille ennen jakoa
	public JLabel luoTyhjaRuutu() {
		try { //yritet‰‰n ladata tyhj‰ kuva
			kuva = new ImageIcon(this.getClass().getResource("/kuvat/tyhja.png"));
		}
		catch (Exception e) {
			
		}
		JLabel label = new JLabel(kuva);
		label.setBorder(new EmptyBorder(50, 20, 50, 10));
		return label;
	}
	
	//Lis‰t‰‰n pelaajaruutuun pelaajan kuva, mik‰li sellainen on m‰‰ritelty
	public JLabel lisaaPelaajanKuva(String kuvaLahde) {
		try { //yritet‰‰n ladata kuva
			kuva = new ImageIcon(this.getClass().getResource(kuvaLahde));
		}
		catch (Exception e) {
			
		}
		JLabel label = new JLabel(kuva);
		return label;
	}
	
	
	//J‰‰ napin painamista imitoiva metodi
	public void painaJaa () {
		jaa.doClick();
	}
	
	//Nosta napin painamista imitoiva metodi
	public void painaNosta() {
		nosta.doClick();
	}
	
}
