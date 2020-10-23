import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Automaatti extends Ikkuna{
	
	//alimetodien tarvitsemat muuttujat
	private Kahvi kahvi;
	private Tee tee;
	private Kaakao kaakao;
	private JLabel kahviMaara;
	private JLabel teeMaara;
	private JLabel kaakaoMaara;
	private String tiedosto;
		
	public Automaatti(int width, int height, String title) {
		super(width, height, title); //k‰ytt‰‰ ikkuna-luokan konstruktoria
		
		//muuttujien alustelut
		kahvi = new Kahvi("Kahvi", 50);
		tee = new Tee("Tee", 50);
		kaakao = new Kaakao("Kaakao", 50);
		tiedosto = "juomaautomaatit";

		//Yl‰palkin ja -valikon luominen ja t‰ytt‰minen olioilla
		JMenuBar ylapalkki = new JMenuBar();
		JMenu ylavalikko = new JMenu("Yll‰pito");
		JMenu tietoja = new JMenu("Tietoja");
		JMenuItem i1, i2, i3, i4, i5, i6, i7, versio, ohje;
		i1 = new JMenuItem("Aseta kahvin m‰‰r‰");
		i2 = new JMenuItem("Aseta teen m‰‰r‰");
		i3 = new JMenuItem("Aseta kaakaon m‰‰r‰");
		i4 = new JMenuItem("Tallenna automaatin tila");
		i5 = new JMenuItem("Lataa automaatti");
		i6 = new JMenuItem("Alusta automaatti");
		i7 = new JMenuItem("Sulje automaatti");
		versio = new JMenuItem("Versio");
		ohje = new JMenuItem("Ohje");
		
		//yl‰valikon komponenttien lis‰ys yl‰valikkoon
		ylavalikko.add(i1);
		ylavalikko.add(i2);
		ylavalikko.add(i3);
		ylavalikko.add(i4);
		ylavalikko.add(i5);
		ylavalikko.add(i6);
		ylavalikko.add(i7);
		tietoja.add(versio);
		tietoja.add(ohje);
		
		//yl‰valikon lis‰ys yl‰palkkiin
		ylapalkki.add(ylavalikko);	
		ylapalkki.add(tietoja);
		
		//ikkunoiden muotoilua
		JPanel automaattiOsio = new JPanel();
		BoxLayout automaattiEtulevy = new BoxLayout(automaattiOsio, BoxLayout.Y_AXIS);
		automaattiOsio.setLayout(automaattiEtulevy);
			
		//kaikkien BorderLayouttia k‰ytt‰bien paneelien alustus omassa metodissa
		JPanel infoPaneeli = borderPaneeli();
		JPanel tuoteKahvi = borderPaneeli();
		JPanel tuoteTee = borderPaneeli();
		JPanel tuoteKaakao = borderPaneeli();
		
		//Otsikkorivien muotoilu
		JLabel tuotteet = new JLabel("Tuotteet");
		tuotteet.setBorder(new EmptyBorder(new Insets(0, 137, 0, 20)));
		tuotteet.setFont(tuotteet.getFont().deriveFont((float) 20.0));
		JLabel annoksia = new JLabel("Annoksia");
		annoksia.setBorder(new EmptyBorder(new Insets(0, 20, 0, 20)));
		annoksia.setFont(annoksia.getFont().deriveFont((float) 20.0));
		
		//Muuttujien muotoilu omissa metodeissa Juoma-luokkaa hyˆdynt‰viss‰ metodeissa
		JButton kahviNappi = juomanappi(kahvi);
		JLabel kahviLabel = juomalabel(kahvi);
		kahviMaara = juomamaara(kahvi);
		JButton teeNappi = juomanappi(tee);
		JLabel teeLabel = juomalabel(tee);
		teeMaara = juomamaara(tee);
		JButton kaakaonappi = juomanappi(kaakao);
		JLabel kaakaoLabel = juomalabel(kaakao);
		kaakaoMaara = juomamaara(kaakao);
		
		//yl‰valikon komponenttien toiminnalisuuksien m‰‰rittely
		i1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lisays(kahvi);
				kahviPaivitys();
			}
		});
		
		i2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lisays(tee);
				teePaivitys();
			}
		});
				
		i3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lisays(kaakao);
				kaakaoPaivitys();
			}
		});
		
		i4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tallennus();
			}
		});
		
		i5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					lataa();
				}
				catch (NullPointerException n){
					
				}
			}
		});
		
		i6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				piilota();
				Automaatti automaatti = new Automaatti(500, 750, "Juoma-Automaatti");
				automaatti.nayta();
			}
		});
		
		i7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				poistu();
			}
		});
		
		versio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Versio versioIkkuna = new Versio(400, 300, "Versiotiedot");
				versioIkkuna.nayta();
			}			
		});
		
		ohje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ohje ohjeIkkuna = new Ohje(500, 400, "Ohjeet");
				ohjeIkkuna.nayta();				
			}
			
		});
		
		//Kuvakkeiden toiminnallisuus
		kahviNappi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kahvi.osta();
				kahviPaivitys();
			}			
		});
		
		teeNappi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tee.osta();
				teePaivitys();
			}			
		});
		
		kaakaonappi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kaakao.osta();
				kaakaoPaivitys();
			}		
		});
		
		
		//ikkunoiden komponenttien lis‰ys ja kokonaisuuden muotoilu
		
		//otsikko
		infoPaneeli.add(tuotteet, BorderLayout.CENTER);
		infoPaneeli.add(annoksia, BorderLayout.EAST);
		//tuotteet ja m‰‰r‰t
		tuoteKahvi.add(kahviNappi, BorderLayout.CENTER);
		tuoteKahvi.add(kahviLabel, BorderLayout.SOUTH);
		tuoteKahvi.add(kahviMaara, BorderLayout.EAST);
		tuoteTee.add(teeNappi, BorderLayout.CENTER);
		tuoteTee.add(teeLabel, BorderLayout.SOUTH);
		tuoteTee.add(teeMaara, BorderLayout.EAST);
		tuoteKaakao.add(kaakaonappi, BorderLayout.CENTER);
		tuoteKaakao.add(kaakaoLabel, BorderLayout.SOUTH);
		tuoteKaakao.add(kaakaoMaara, BorderLayout.EAST);
		//otsikon ja tuotteiden lis‰ys automaattiin
		automaattiOsio.add(infoPaneeli);
		automaattiOsio.add(tuoteKahvi);
		automaattiOsio.add(tuoteTee);
		automaattiOsio.add(tuoteKaakao);
		//p‰‰ikkunan muotoilu		
		BorderLayout ikkunaAsettelu = new BorderLayout();
		getContentPane().setLayout(ikkunaAsettelu);
		//yl‰palkin ja automaatin yhdist‰minen p‰‰ikkunaan
		getContentPane().add(ylapalkki, BorderLayout.NORTH);
		getContentPane().add(automaattiOsio, BorderLayout.CENTER);
		
	}
	
	//BorderLayout paneelien m‰‰ittely
	public JPanel borderPaneeli() {
		JPanel panel = new JPanel();
		BorderLayout infoLayout = new BorderLayout();
		panel.setLayout(infoLayout);
		return panel;
	}
	
	
	//Juomanapin m‰‰rittely
	public JButton juomanappi(Juoma juoma) {
		JButton nappi = new JButton("");
		nappi.setSize(250, 190);
		nappi.setIcon(juoma.getKuva());
		return nappi;
	}
	
	//Juomalabel m‰‰rittely
	public JLabel juomalabel(Juoma juoma) {
		JLabel label = new JLabel(juoma.getNimi());
		int kohdistus = 160 - juoma.getNimi().length()*4;
		label.setBorder(new EmptyBorder(new Insets(0, kohdistus, 10, 0)));
		label.setFont(label.getFont().deriveFont((float) 20.0));
		return label;
	}
	
	//Juomam‰‰r‰n ilmoittaminen
	public JLabel juomamaara(Juoma juoma) {
		JLabel label = new JLabel("" + juoma.getLkm());
		label.setFont(label.getFont().deriveFont((float) 20.0));
		label.setBorder(new EmptyBorder(new Insets(0, 50, 0, 50)));
		return label;
	}
	
	//Juomien m‰‰rien p‰ivitykset
	public void kahviPaivitys() {
		if (kahvi.getLkm()<20) {
			kahviMaara.setForeground(Color.RED);
		}else {
			kahviMaara.setForeground(Color.BLACK);
		}
		if (kahvi.getLkm()<10) {
			kahviMaara.setText(" " + kahvi.getLkm() + " ");
		}else
			kahviMaara.setText("" + kahvi.getLkm());				
	}
	
	public void teePaivitys() {
		if (tee.getLkm()<20) { //mik‰li alle 20 yksikkˆ‰, teksti punaiseksi
			teeMaara.setForeground(Color.RED);
		}else {
			teeMaara.setForeground(Color.BLACK);// muuten mustaksi
		}
		if (tee.getLkm()>9) {
			teeMaara.setText("" + tee.getLkm()); //kun alle 10 yksikkˆ‰ ja m‰‰r‰ ilmotetaan yhdell‰ merkill‰, muotoillaan
		}else
			teeMaara.setText(" " + tee.getLkm() +" "); //kahden merkin tulostusmuotoilu
		//Ei ota huomioon yli 100n yksikˆn muotoilua. 
		//Voidaan joko rajoittaa 99iin tai muokata muotoilua
	}
	
	public void kaakaoPaivitys() {
		if (kaakao.getLkm()<20) {
			kaakaoMaara.setForeground(Color.RED);
		}else {
			kaakaoMaara.setForeground(Color.BLACK);
		}
		if (kaakao.getLkm()>9) {
			kaakaoMaara.setText("" + kaakao.getLkm());
		}else
			kaakaoMaara.setText(" " + kaakao.getLkm() + " ");
	}
		
		
	//Juomien lataaminen
	public int lisays(Juoma juoma) {
		JFrame ponnahdus = new JFrame();
		//Teksti luetaan String-muodossa ja tarvitaan kokonaisluku, joten otetaan tieto talteen k‰ytt‰en Integer.valueOf:ia
		//Mik‰li syˆte ei ole kokonaisluku kopataan virhe ja ponnautetaan virheilmoitus
		try {
			int lisattavaMaara = Integer.valueOf(JOptionPane.showInputDialog(ponnahdus, "Syˆt‰ lis‰tt‰vien annosten m‰‰r‰. (Vain positiiviset kokonaisluvut)"));
			if (lisattavaMaara>0) {
				juoma.lataa(lisattavaMaara);
				ponnahdus.setVisible(false);
				return lisattavaMaara;
			}else
				JOptionPane.showMessageDialog(ponnahdus,"Vain positiivisia kokonaislukuja","Alert",JOptionPane.WARNING_MESSAGE);
				return 0;			
		}
		catch (Exception x) {					
			JOptionPane.showMessageDialog(ponnahdus,"Vain positiivisia kokonaislukuja","Alert",JOptionPane.WARNING_MESSAGE); 
		}
		return 0;
	}
	
	
	//Juoma-automaatin tallennus tiedostoon
	public void tallennus() {
		JFrame ponnahdus = new JFrame();
		String automaatti = JOptionPane.showInputDialog(ponnahdus, "Anna tallennettavan automaatin nimi.");
		
		//Tarkistetaan onko jo olemassa kyseisen nimist‰ automaattia
		if (onkoTiedostoJoOlemassa(tiedosto)==true) {
			JOptionPane.showMessageDialog(ponnahdus,"saman niminen automaatti on jo olemassa.","Alert",JOptionPane.WARNING_MESSAGE);
			ponnahdus.setVisible(false);
            return; 		            	
		}            
        String lisattava = "";
        //yritet‰‰n kirjoittaa tiedostoon
        try {
        	FileWriter fileWriter = new FileWriter(tiedosto, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);     
            //tallenteen muotoilua
        	lisattava += automaatti +" = ";  //erotetaan automaatin nimi tuotteista ja lukum‰‰rist‰ v‰lilyˆnneill‰ ja =-merkill‰
        	lisattava += kahvi.getNimi() + ": " + kahvi.getLkm() +", ";		//Otetaan jokainen juoma ylˆs erikseen
        	lisattava += tee.getNimi() + ": " + tee.getLkm() +", "; 		//erotetaan juoman nimi m‰‰r‰st‰ :eell‰
       		lisattava += kaakao.getNimi() + ": " + kaakao.getLkm() +"\n";   //kun kaikki on otettu talteen, lis‰t‰‰n rivinvaihto         
            bufferedWriter.write(lisattava); 								//kirjoitetaan tiedot tiedostoon
            bufferedWriter.close();                							//suljetaan kirjoittaja
            JOptionPane.showMessageDialog(ponnahdus,"Tallennus onnistui","Alert",JOptionPane.WARNING_MESSAGE);
            return;                                        
        }            		
        catch (Exception x){
        	JOptionPane.showMessageDialog(ponnahdus,"Tallennus ep‰onnistui","Alert",JOptionPane.WARNING_MESSAGE);   
       	}		
	}
	
	
	//Juoma-automaatin lataaminen tiedostosta
	public void lataa() {
		JFrame ponnahdus = new JFrame();
		String automaatti = JOptionPane.showInputDialog(ponnahdus, "Anna ladattavan automaatin nimi.");
		
		if (automaatti.length()>0) { //Mik‰li ei anneta nime‰, ei l‰hdet‰ yritt‰m‰‰n latausta
			try(Scanner tiedostonLukija = new Scanner(Paths.get(tiedosto))){            		
				while(tiedostonLukija.hasNextLine()){
					String rivi = tiedostonLukija.nextLine(); //luetaan tiedostosta automaattien nimet
					String[] palat = rivi.split(" = "); 
					String tomaatti = palat[0];
					if (tomaatti.equals(automaatti)) {			//mik‰li tiedostosta lˆytyy automaatti, jonka nimi t‰sm‰‰ haettavaan,			      
						String tuotteet = palat[1];				//ladataan kyseisen automaatin tiedot
						System.out.println(tuotteet);						
						String[] palaset = tuotteet.split(", ");	//erotetaan eri juomat toisistaan
						System.out.println(palaset);						
						ArrayList<String> pt = new ArrayList<>();	//tallennetaan eri juomat listaan				
						pt.add(palaset[0]);
						pt.add(palaset[1]);
						pt.add(palaset[2]);
						
						for (int i=0; i<pt.size(); i++) {			//k‰yd‰‰n juomat l‰pi
							String p = pt.get(i);
							String[] t = p.split(": ");				//erotetaan nimi ja m‰‰r‰
							String nimi = t[0];
							int lkm = Integer.valueOf(t[1]);
							System.out.println(nimi);
							System.out.println(lkm);
							if (nimi.equals("Kahvi")) {				//p‰ivitet‰‰n ladatut tiedot k‰yttˆliittym‰‰n
								kahvi.setLkm(lkm);
								kahviPaivitys();
							}else if(nimi.equals("Tee")) {
								tee.setLkm(lkm);
								teePaivitys();
							}else
								kaakao.setLkm(lkm);
	            				kaakaoPaivitys();
						}
					}
            	}
            } 		
			catch (Exception e){          	
            		JOptionPane.showMessageDialog(ponnahdus,""+tiedosto+" nimist‰ tiedostoa ei ole olemassa.","Alert",JOptionPane.WARNING_MESSAGE);
            		ponnahdus.setVisible(false);            
        	}
		}		
	}
	
	
	//Tarkistaa onko automaatin nime‰ vastaava automaatti jo olemassa
    public boolean onkoTiedostoJoOlemassa(String automaatti){
    	try(Scanner tiedostonLukija = new Scanner(Paths.get(tiedosto))){
            while(tiedostonLukija.hasNextLine()){
                String rivi = tiedostonLukija.nextLine();
                String[] palat = rivi.split(" = ");                               
                String tomaatti = palat[0];						
                	if (tomaatti.equals(automaatti)) {		//verrataan automaatin nime‰ k‰ytt‰j‰n syˆtt‰m‰‰n nimeen
                		return true;
                	}
            }
        } catch (Exception e){  
        	
        	return false; 	
        }    	
        return false;    		
	}
	
	
}
