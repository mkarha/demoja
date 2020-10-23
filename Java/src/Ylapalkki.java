import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Ylapalkki {
	
	private JMenuBar ylapalkki;
	private JMenuItem i1, i2, i3, i4, i5, i6;

	public Ylapalkki() {
		//Luodaan yl‰palkki ja yl‰valikko asetuksille
		ylapalkki = new JMenuBar();
		JMenu ylavalikko = new JMenu("Asetukset");
				
				
		i1 = new JMenuItem("Lis‰‰ rahaa");
		i2 = new JMenuItem("Nosta rahaa");
		i3 = new JMenuItem("Joku itemi");
		i4 = new JMenuItem("Lippuikoni Suomi");
		i5 = new JMenuItem("Lippuikoni englanti");
		i6 = new JMenuItem("Lopeta pelaaminen");
			
		ylavalikko.add(i1);
		ylavalikko.add(i2);
		ylavalikko.add(i3);
		ylavalikko.add(i4);
		ylavalikko.add(i5);
		ylavalikko.add(i6);
		
		i6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}			
		});
		
		ylapalkki.add(ylavalikko);
	}
	
	public JMenuBar getYlapalkki() {
		return this.ylapalkki;
	}
	
	public void painaExit() {
		this.i6.doClick();
	}
	
}
