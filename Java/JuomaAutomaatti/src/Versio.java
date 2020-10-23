import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Versio extends Ikkuna{

	public Versio(int width, int height, String title) {
		super(width, height, title);
		
		//p��ikkunan asettelu
		BorderLayout asettelu = new BorderLayout();
		getContentPane().setLayout(asettelu);
		
		//S�ili� tietos�ili�lle
		JLabel tietoLabel = new JLabel();
		BorderLayout labelAsettelu = new BorderLayout();
		tietoLabel.setLayout(labelAsettelu);
		tietoLabel.setBorder(new EmptyBorder(new Insets(30, 50, 30, 50)));	
		
		//tietos�ili�
		JPanel tiedot = new JPanel();
		BorderLayout paneeli = new BorderLayout();
		tiedot.setLayout(paneeli);
		tiedot.setBackground(Color.YELLOW);
		
		//tiedot
		JLabel tekija = teeLabel("Tekij�: Mikko Karhavirta", 15);
		tekija.setBorder(new EmptyBorder(new Insets(50, 50, 0, 0)));		
		JLabel ohjelma = teeLabel("Juoma-automaatti", 30);	
		ohjelma.setBorder(new EmptyBorder(new Insets(30, 10, 0, 0)));
		JLabel versio = teeLabel("versio 4", 10);
		
		//lis�t��n halutut sis�ll�t
		tiedot.add(ohjelma, BorderLayout.NORTH);
		tiedot.add(tekija, BorderLayout.CENTER);
		tiedot.add(versio, BorderLayout.SOUTH);
		tietoLabel.add(tiedot, BorderLayout.CENTER);
		
		//nappi p��ohjelmaan paluuta varten	
		JButton nappi = new JButton("Palaa"); 	
		nappi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				piilota();				
			}			
		});
		
		//Asetellaan sis�ll�t p��ikkunaan
		getContentPane().add(tietoLabel, BorderLayout.CENTER);
		getContentPane().add(nappi, BorderLayout.SOUTH);
		
	}
	
	//Sis�lt�jen luominen ja tekstin koon muotoilu
	public JLabel teeLabel(String sisalto, double koko) {
		JLabel label = new JLabel(sisalto);
		label.setFont(label.getFont().deriveFont((float) koko));
		return label;
	}		
	
}
