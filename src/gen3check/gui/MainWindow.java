package gen3check.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import gen3check.Controller;
import gen3check.pokemon.data.Nature;


public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static final ImageIcon JFRAME_ICON = new ImageIcon(MainWindow.class.getResource("/image/raikouicon.png"));
	private int width = 750;
	private int height = 480;
	private Controller controller;
	private IVCheckPanel ivcheckpanel;
	private RestPanel restpanel;
	
	public MainWindow (final Controller c){
		super("Pokemon Gen 3 Starter Manip");
		this.setIconImage(JFRAME_ICON.getImage());
		this.controller = c;
		this.restpanel = new RestPanel(this, c);
		this.ivcheckpanel = new IVCheckPanel(this, c, this.restpanel);
		this.add(ivcheckpanel, BorderLayout.WEST);
		this.add(restpanel, BorderLayout.CENTER);
		this.setJMenuBar(new MenuBar(this,controller));
		this.setParams();
	}
	
	/**
	 * Sets various parameters to default values
	 */
	private void setParams() {
		this.setSize(this.width, this.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.setVisible(true);
		this.setResizable(false);
	}
}
