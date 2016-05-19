package gen3check.predictor;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JToggleButton;

public class StatButton extends JToggleButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2752815490797667940L;
	public StatButton(int row, int col, final IVPredictorWindow ivpw) {
		super();
		this.row = row;
		this.col = col;
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ivpw.buttonChanged(StatButton.this.row, StatButton.this.col);
			}
		});
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		int outerRoundRectSize = 6;
		int innerRoundRectSize = 4;
		int h = getHeight();
		int w = getWidth();
		if (!this.isEnabled()){
			GP = new GradientPaint(0, 0, getBackground(), 0, h, getBackground(), true);
		}
		else if (model.isPressed()){
			GP = new GradientPaint(0, 0, Color.YELLOW, 0, h, Color.YELLOW, true);
		}
		else if (this.isSelected()){
				GP = new GradientPaint(0, 0, Color.GREEN, 0, h, Color.GREEN, true);
		}
		else {
				GP = new GradientPaint(0, 0, Color.LIGHT_GRAY, 0, h, Color.LIGHT_GRAY, true);
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
		String s = getText();
		g.drawString(s,
                (w - g.getFontMetrics().stringWidth(s)) / 2 + 1,
                (h + g.getFontMetrics().getAscent()) / 2 - 1);
	}

	public void setValue(int value){
		this.value = value;
		this.setText(String.valueOf(this.value));
	}
	
	public int getValue(){
		return this.value;
	}
	private int value;
	private final int row;
	private final int col;
	private GradientPaint GP;
}
