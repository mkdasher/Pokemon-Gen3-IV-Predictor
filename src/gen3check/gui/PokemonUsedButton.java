package gen3check.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;


public class PokemonUsedButton extends JButton{
	private static final long serialVersionUID = 7539968900234313780L;
	public PokemonUsedButton(){
		this.setContentAreaFilled(false);
		this.setUsed(false);
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setUsed(!PokemonUsedButton.this.used);
			}
		});
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		int outerRoundRectSize = 6;
		int innerRoundRectSize = 4;
		int h = getHeight();
		int w = getWidth();
		ButtonModel model = getModel();
		if (!model.isEnabled()){
			return;
		}
		if (model.isPressed()){
			GP = new GradientPaint(0, 0, Color.YELLOW, 0, h, Color.YELLOW, true);
		}
		else if (this.used){
				GP = new GradientPaint(0, 0, Color.GREEN, 0, h, Color.GREEN, true);
		}
		else {
				GP = new GradientPaint(0, 0, Color.GRAY, 0, h, Color.GRAY, true);
		}
		
		GradientPaint p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,
				new Color(0, 0, 0));
		GradientPaint p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,
				h - 3, new Color(0, 0, 0, 50));
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,
				h - 1, outerRoundRectSize, outerRoundRectSize);
				
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setPaint(GP);
		Shape clip = g2d.getClip();
		g2d.clip(r2d);
		g2d.fillRect(0, 0, w, h);
		g2d.setClip(clip);
		g2d.setPaint(p1);
		g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
				outerRoundRectSize);
		g2d.setPaint(p2);
		g2d.drawRoundRect(1, 1, w - 3, h - 3, innerRoundRectSize,
				innerRoundRectSize);
		g2d.dispose();
		super.paintComponent(g);
	}
	
	public void setUsed(boolean used){
		this.used = used;
		this.revalidate();
	}
	
	public boolean isUsed(){
		return used;
	}
	
	private boolean used;
	private GradientPaint GP;
}
