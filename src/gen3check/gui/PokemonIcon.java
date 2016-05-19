package gen3check.gui;

import java.awt.Image;

import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class PokemonIcon extends ImageIcon{
	public PokemonIcon(int n){
		super(getImage(n));
	}
	
	public PokemonIcon(int n, int scale){
		super(getImage(n, scale));
	}
	
	
	
	public static Image getImage(int n){
		Image img = new ImageIcon(PokemonIcon.class.getResource("/image/pokemon/" + n + ".png")).getImage();
		Image resizedImage = img.getScaledInstance(WIDTH, HEIGHT, java.awt.Image.SCALE_SMOOTH);
		return resizedImage;
	}
	
	public static Image getImage(int n, int scale){
		Image img = new ImageIcon(PokemonIcon.class.getResource("/image/pokemon/" + n + ".png")).getImage();
		Image resizedImage = img.getScaledInstance(scale, scale, java.awt.Image.SCALE_SMOOTH);
		return resizedImage;
	}
	
	public static final int WIDTH = 64;
	public static final int HEIGHT = 64;
}
