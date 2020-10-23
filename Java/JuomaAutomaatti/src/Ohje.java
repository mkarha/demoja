import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Ohje extends Ikkuna{
	
	public Ohje(int width, int height, String title) {
		super(width, height, title);
		
		//Ohjetiedoston nimi
		String tiedosto = "ReadME";
		
		//Tekstialue, johon ohjeet tuutataan
		JTextArea ohje = new JTextArea();
		String ohjeTeksti = "";
		
		//Luetaa ohjetiedsto rivi kerrallaan
		try(Scanner tiedostonLukija = new Scanner(Paths.get(tiedosto))){            		
			while(tiedostonLukija.hasNextLine()){
				String rivi = tiedostonLukija.nextLine(); 
				ohjeTeksti += rivi + "\n";
        	}
        } 		
		catch (Exception e){          	
			e.printStackTrace();	
    	}
		
		//Lis‰t‰‰n tiedostosta luettu ohje tekstialueelle ja muotoillaan
		ohje.setText(ohjeTeksti);
		ohje.setBorder(new EmptyBorder(new Insets(50, 30, 0, 50)));			
		
		//Paluunappi
		JButton nappi = new JButton("Palaa");
		nappi.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				piilota();				
			}			
		});
		
		//P‰‰ikkunan asetelu ja sis‰llˆn lis‰‰minen
		BorderLayout asettelu = new BorderLayout();
		getContentPane().setLayout(asettelu);
		getContentPane().add(ohje, BorderLayout.CENTER);
		getContentPane().add(nappi, BorderLayout.SOUTH);
		
	}

		
}
