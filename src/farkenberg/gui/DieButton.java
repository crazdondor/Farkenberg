package farkenberg.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class DieButton extends JButton {
	private static final long serialVersionUID = 8925623045360459019L;
	private int num_dots;
	private boolean is_selected = false;
	

	public DieButton(int num_dots) {
		this(num_dots, null);
	}
	
	public DieButton(int num_dots, Function<DieButton,Boolean> listener) {
		super();
		
		this.num_dots = num_dots;
		
    	setFocusPainted(false);
    	setPreferredSize(new Dimension(70,70));
    	setBorder(new LineBorder(new Color(0,0,0,0), 5));
    	setContentAreaFilled(false);
    	setRolloverEnabled(false);
    	setMargin(new Insets(0,0,0,0));
    	setOpaque(true);
    	setForeground(Color.BLACK);
    	setBackground(UIManager.getColor("Panel.background"));
    	setText("");
    	
    	addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (listener != null && listener.apply(DieButton.this) == false) {
						return;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				DieButton.this.set_selected(!is_selected);
			}
    		
    	});
	}
	
	public int get_numDots() {
		return num_dots;
	}
	
	public void set_numDots(int dots) {
		this.num_dots = dots;
	}
	
	public boolean is_selected() {
		return is_selected;
	}
	
	public void set_selected(boolean state) {
		is_selected = state;
		
		if (is_selected) {
			DieButton.this.setBorder(new LineBorder(Color.YELLOW, 5));
		} else {
			DieButton.this.setBorder(new LineBorder(new Color(0,0,0,0), 5));
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(5, 5, 59, 59, 7, 7); // white fill
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(5, 5, 59, 59, 7, 7); // outline
		
		switch (num_dots) {
		case 0:
			break;
		case 1:
			drawCenteredCircle(g2d, 30, 30);
			break;
		case 2:
			drawCenteredCircle(g2d, 15, 45);
			drawCenteredCircle(g2d, 45, 15);
			break;
		case 3:
			drawCenteredCircle(g2d, 30, 30);
			
			drawCenteredCircle(g2d, 15, 45);
			drawCenteredCircle(g2d, 45, 15);
			break;
		case 4:
			drawCenteredCircle(g2d, 15, 15);
			drawCenteredCircle(g2d, 15, 45);
			drawCenteredCircle(g2d, 45, 15);
			drawCenteredCircle(g2d, 45, 45);
			break;
		case 5:
			drawCenteredCircle(g2d, 30, 30);
			
			drawCenteredCircle(g2d, 15, 15);
			drawCenteredCircle(g2d, 15, 45);
			drawCenteredCircle(g2d, 45, 15);
			drawCenteredCircle(g2d, 45, 45);
			break;
		case 6:
			drawCenteredCircle(g2d, 15, 30);
			drawCenteredCircle(g2d, 45, 30);
			
			drawCenteredCircle(g2d, 15, 15);
			drawCenteredCircle(g2d, 15, 45);
			drawCenteredCircle(g2d, 45, 15);
			drawCenteredCircle(g2d, 45, 45);
			break;
		case 7:
			drawCenteredCircle(g2d, 30, 30);
			
			drawCenteredCircle(g2d, 15, 30);
			drawCenteredCircle(g2d, 45, 30);
			
			drawCenteredCircle(g2d, 15, 15);
			drawCenteredCircle(g2d, 15, 45);
			drawCenteredCircle(g2d, 45, 15);
			drawCenteredCircle(g2d, 45, 45);
			break;
		case 8:
			drawCenteredCircle(g2d, 15, 30);
			drawCenteredCircle(g2d, 45, 30);
			
			drawCenteredCircle(g2d, 30, 15);
			drawCenteredCircle(g2d, 30, 45);
			
			drawCenteredCircle(g2d, 15, 15);
			drawCenteredCircle(g2d, 15, 45);
			drawCenteredCircle(g2d, 45, 15);
			drawCenteredCircle(g2d, 45, 45);
			break;
		case 9:
			drawCenteredCircle(g2d, 30, 30);
			
			drawCenteredCircle(g2d, 15, 30);
			drawCenteredCircle(g2d, 45, 30);
			
			drawCenteredCircle(g2d, 30, 15);
			drawCenteredCircle(g2d, 30, 45);
			
			drawCenteredCircle(g2d, 15, 15);
			drawCenteredCircle(g2d, 15, 45);
			drawCenteredCircle(g2d, 45, 15);
			drawCenteredCircle(g2d, 45, 45);
			break;
		}
	}
	
	public void drawCenteredCircle(Graphics2D g, int x, int y) {
		x += 5;
		y += 5;
		
		int r = 15;
		x = x-(r/2);
		y = y-(r/2);
		g.fillOval(x,y,r,r);
	}
	
}