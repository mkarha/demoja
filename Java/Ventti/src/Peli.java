import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class Peli {
	
	private int pelaajia, vuoro, monesko, isoinArvo, verrokki;
	private Pelaaja pelaaja;
	private String arvo, isoinPelaaja;
	private ArrayList<Pelaaja> kiertavat;
	private HashMap<Integer, Pelaaja> pelaajat;
	private HashMap<Integer, Kasi> pelaajienKadet;
	private ArrayList<JLabel> korttiRuudut;
	private Pakka pakka;
	private Kortti kortti;
	private Kasi kasi;
	private PeliIkkuna peliIkkuna;
	private ImageIcon kuva;
	private boolean loppu;
	
	
	//Luodaan uusi peli pelaajat taulukon pelaajilla
	public Peli(HashMap<Integer, Pelaaja> pelaajat) {
		this.pelaajia = pelaajat.size(); //montako pelaajaa
		this.pelaajat = pelaajat; //taulukko myˆs alametodien k‰yttˆˆn
		alustus();
	}
	
	public void alustus() {		
		this.kiertavat = new ArrayList<>();
		for (int i=0; i<pelaajat.size(); i++) {
			this.kiertavat.add(pelaajat.get(i));
		}
		this.isoinArvo = 0;
		this.verrokki = 0;
		this.isoinPelaaja = "";
		
		//luodaan pelaajille nimikohtaiset k‰det 
		this.pelaajienKadet = new HashMap<>();
		for (int i=0; i<this.pelaajia; i++) {	//luodaan jokaiselle pelaajalle oma k‰si
			this.kasi = new Kasi();
			this.kasi.setArvo(0); //Asetetaan kunkin pelaajan k‰den arvoksi 0
			this.pelaajienKadet.put(this.pelaajat.get(i).getMonesko(), this.kasi); //pelaajat ja k‰det talteen
		}
		
		//Luodaan n‰ytett‰ville korteille ruudut, joita hallitaan pelist‰
		korttiRuudut = new ArrayList<>();
		for (int i=1; i<10; i++) { //max korttim‰‰r‰ 9
			JLabel label = luoTyhjaRuutu(); //alustetaan kaikki ruudut tyhjiksi
			korttiRuudut.add(label); //korttiruudut talteen
		}
			
		this.pakka = new Pakka(); //otetaan uusi pakka
		this.vuoro = 0; //vuoro alussa 0
		this.monesko = 0; //korttim‰‰r‰ alussa 0
		this.arvo = ""; //arvo palautetaan tekstimuodossa lab elille. Alku arvo tyhj‰ + int arvo 0
		this.peliIkkuna = new PeliIkkuna(1280, 800, "Ventti", kiertavat, korttiRuudut, this);  //luodaan uusi peli-ikkuna 
		this.loppu = false;
		peliIkkuna.nappienPaivitys(loppu);
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
		
		
		public JLabel lisaaPelaajanKuva(Pelaaja pelaaja) {
			try { //yritet‰‰n ladata kuva
				kuva = pelaaja.getKuva();
			}
			catch (Exception e) {
				
			}
			JLabel label = new JLabel(kuva);
			return label;
		}
	
		
	//Ajetaan uusi peli-ikkuna ruutu
	public void naytaPeli() {
		peliIkkuna.nayta();
	}
	
	
	/*Pelimekaniikka
	*
	*Uuden kortin nostaminen. Suoritetaan kun pelaaja painaa peli-ikkunasa "nosta"-nappia
	*Jakaja suorittaa metodin jokaisen kortin kohdalla
	**/
	public void nostaKortti() {
		
		this.pelaaja = this.kiertavat.get(0);						//Pelaaja pelaajat vuorossa oleva pelaaja
		int indeksi = (int)(Math.random()*(this.pakka.getKorttejaPakassa()));	//Arvotaan indeksi pakassa j‰ljell‰ olevista korteista
		this.kortti = this.pakka.jaaKortti(indeksi);						//jaetan kortti
		this.peliIkkuna.korttiPaivitys(monesko, this.kortti.getKuva());
		monesko++;														//lis‰t‰‰n k‰dess‰ olevien korttien m‰‰r‰‰
		this.pakka.poistaKorttiPakasta(indeksi);							//poistetaan jaettu kortti pakasta
		this.kasi = this.pelaajienKadet.get(this.pelaaja.getMonesko());				//ladataan kierroksen pelaajan k‰si
		this.kasi.lisaaKortti(this.kortti);									//Lis‰t‰‰n k‰teen jaettu kortti
		this.pelaajienKadet.put(this.pelaaja.getMonesko(), this.kasi);				//Talletetaan pelaajatunnuksen mukaan k‰si
		this.kasi = this.pelaajienKadet.get(this.pelaaja.getMonesko());				//ladataan kierroksen pelaajan k‰si p‰ivitettyn‰
		this.peliIkkuna.tulosPaivitys(""+getSyote(this.kasi.getArvo()));
		
		System.out.println("Pelaajan " + this.pelaaja.getKayttaja() + ", K‰den arvo " + this.kasi.getArvo());
		
		//mik‰li p‰ivitetyn k‰den arvo on yli 21 vaihdetaan vuoroa
		if (this.kasi.getArvo()>21) {
				lopetaVuoro();
		}
	}
	
	
	
	/*Vuoron lopetus
	*suoritetaan kun pelaaja painaa"j‰‰"-nappia peli-ikkunassa
	*
	*Katsotaan kuka on seuraavana vuorossa. Jos vuorossa on jakaja, suoritetaan jakajan toiminnot.
	*Uusi vuoro. Lis‰t‰‰n vuoro-muuttujan arvoa ja kerrotaan seuraava pelaaja ponnahdusikkunassa.
	*J‰rjestet‰‰n pelaajien j‰rjestys uusiksi peli-ikkunan paikkojen vaihtoa varten
    *Siirret‰‰n ensimm‰inen pelaaja taulukon loppuun.
    *Lopuksi m‰‰ritet‰‰n k‰dess‰ olevien korttien m‰‰r‰ksi 0 ja alustetaan korttiruudut tyhjiksi.
	**/
	public void lopetaVuoro() {
		this.verrokki = this.pelaajienKadet.get(vuoro).getArvo();
		if (this.verrokki < 22 && this.verrokki > this.isoinArvo) {
				this.isoinArvo = this.verrokki;
				this.isoinPelaaja = this.kiertavat.get(0).getKayttaja();
			} else if (this.verrokki < 22 && this.verrokki == this.isoinArvo) {
				if (vuoro == 0 || this.kiertavat.get(0).getKayttaja().equals("Jakaja")) {
					this.isoinPelaaja = this.kiertavat.get(0).getKayttaja();
				}else {
					this.isoinPelaaja += " ja " + this.pelaajat.get(0);
				}
			}
		
		pelaaja = this.kiertavat.get(1); 

		if(this.vuoro<this.pelaajia-1) { 
			this.vuoro++;
	
    			JFrame ponnahdus = new JFrame(); 
    			JOptionPane.showMessageDialog(ponnahdus, pelaaja.getKayttaja(), "Alert",JOptionPane.WARNING_MESSAGE);
    			ponnahdus.setVisible(false); 
    		
			this.kiertavat.add(this.kiertavat.get(0));			
			this.kiertavat.remove(0);
			this.monesko = 0;
       		for (int i=0; i<korttiRuudut.size(); i++) {
       			ImageIcon kuva = null;
       			try { //yritet‰‰n ladata tyhj‰ kuva
       				kuva = new ImageIcon(this.getClass().getResource("/kuvat/tyhja.png"));
       			}
       			catch (Exception e) {
       				
       			}
       			JLabel label = new JLabel(kuva);
       			label.setBorder(new EmptyBorder(50, 20, 50, 10));
       			korttiRuudut.remove(0);
       			korttiRuudut.add(label);
       			
       			
       		}  
       		
       		if (this.kiertavat.get(0).getKayttaja().equals("Jakaja")) {
       			jakajanVuoro();
       		}
       		
       		peliIkkuna.sulje();
       		peliIkkuna = new PeliIkkuna(1280, 800, "Ventti", kiertavat, korttiRuudut, this);
    		naytaPeli();
		}
		
		
	}
	
	
	//Jakajan toiminta
	/*
	 * Mik‰li isoin  arvo = 0, eli kaikki muut pelaajat ovat mennet yli, nostaa jakaja vain yhden kortin.
	 * Muuten jakaja nostaa kortteja siihen saakka, kunnes jakajan k‰den arvo on sama tai suurempi kuin 
	 * isoin arvo tai jakaja menee yli 21.
	 * Lopuksi k‰ynnistet‰‰n voittaja-metodi.
	 */
		public void jakajanVuoro() {
			System.out.println("Jakan vuoro metodin alku");		
			if (this.isoinArvo == 0) {
				nostaKortti();
			}else {
			
				while (this.isoinArvo > this.pelaajienKadet.get(pelaajia-1).getArvo() && this.pelaajienKadet.get(pelaajia-1).getArvo()<22){
					nostaKortti();
				}
			}
			
			//Asetetaan muuttuja loppu todeksi. Muuttaa nappien toiminnallisuutta poistumiseksi ja uudeksi peliksi
			setLoppu(true);
			
			peliIkkuna.sulje();
       		peliIkkuna = new PeliIkkuna(1280, 800, "Ventti", kiertavat, korttiRuudut, this);
    		naytaPeli();

			if (this.pelaajienKadet.get(pelaajia-1).getArvo()>=this.isoinArvo && this.pelaajienKadet.get(pelaajia-1).getArvo()<22) {
				
				voittaja("Jakaja");
			}else if (this.isoinArvo == this.pelaajienKadet.get(pelaajia-1).getArvo() ) {
				voittaja("Jakaja");
			}else {
				voittaja(this.isoinPelaaja);
			}
		}
		
		/*Tarkistetaan voittaja.
		 * Alustetaan isoin arvo, verrokki ja isoimman pelaajan nimi.
		 * K‰yd‰‰n l‰pi koko pelaajat-taulukko. 
		 * Mik‰li kaksi pelaajaa p‰‰tyv‰t samaan k‰den arvoon, jaetaan voitto.
		 * Paitsi, jos jaettu voitto tulee samalla k‰denarvolla kuin jakajalla, jolloin jakaja voittaa.
		 */
		public void voittaja(String pelaaja) {
			//Ponnautetaan ikkuna, josta selvi‰‰ voittaja
			//Ilmoituksen alle napit alkuun paluuta ja uutta peli‰ samoilla pelaajilla varten
		
		    JFrame ponnahdus = new JFrame();
		    JOptionPane.showMessageDialog(ponnahdus, pelaaja + " voitti!", "Confirm",JOptionPane.DEFAULT_OPTION);
			ponnahdus.setVisible(false); 
   			
		}
		
	
	//Kortti olion getteri	
	public Kortti getKortti() {
		return this.kortti;
	}
	
	
	//Vuoron numeron getteri. Ensimm‰inen vuoro on 0
	public int getVuoro() {
		return this.vuoro;
	}
	
	
	//K‰si olion getteri
	public Kasi getKasi() {
		return this.kasi;  
	}
	
	
	//Tietyn pelaajan k‰den hakeminen
	public Kasi getPelaajanKasi(Pelaaja pelaaja) {
		return this.pelaajienKadet.get(pelaaja.getMonesko());
	}
	
	
	//K‰den arvon muuttaminen syˆtteeksi ylimenevi‰ tuloksia varten
	public String getSyote (int arvo) {
		if (arvo < 22) {
			this.arvo = "" + arvo;
		} else {
			this.arvo = "yli";
		}
		return this.arvo;
	}
	
	
	//Monesko kortti jaossa kysely
	public int moneskoKortti() {
		if (monesko<1) {
			return 1;
		}
		return this.monesko;
	}

	
	//Loppumuuttujan setteri
	public void setLoppu (boolean loppu) {
		this.loppu = loppu;
	}
	
	
	//Loppumuuttujan getteri
	public boolean getLoppu () {
		return this.loppu;
	}

}	
