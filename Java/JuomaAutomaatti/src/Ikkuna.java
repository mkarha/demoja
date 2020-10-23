import javax.swing.JFrame;

//ikkuna on automaatti-luokan yliluokka

public class Ikkuna extends JFrame{
	
	private int width;
	private int height;	
	private String title;
	
	//konstruktori
	public Ikkuna (int width, int height, String title) {
		this.setSize(width, height);;
		this.width = width;
		this.height = height;
		this.title = title;
		this.setTitle(title);
		this.setLocationRelativeTo(null);
	}
	
	//n‰yt‰-metodilla n‰ytet‰‰n ikkuna
	public void nayta() {
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		this.setVisible(true);
	}	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void piilota() {
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		this.setVisible(false);
	}
	
	public void poistu() {
		System.exit(0);
	}
}
