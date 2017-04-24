package farkenberg.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import farkenberg.PlayerIcon;

public class PlayerIconButton extends JButton {
	private static final long serialVersionUID = -5528762109534599684L;
	
	private boolean is_selected = false;

	private PlayerIcon playerIcon;
	private BufferedImage iconImage;
	
	public PlayerIconButton(PlayerIcon icon) {
		super();
		
		this.playerIcon = icon;
		
    	setFocusPainted(false);
    	setPreferredSize(new Dimension(80,80));

		setDefaultBorder(new Color(0,0,0,0));
    	setContentAreaFilled(false);
    	setRolloverEnabled(false);
    	setMargin(new Insets(0,0,0,0));
    	setOpaque(true);
    	setText("");
    	
    	iconImage = icon.getImage();
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(iconImage, 10, 10, 60, 60, null);
	}
	
	public boolean is_selected() {
		return is_selected;
	}
	
	public void set_selected(boolean state) {
		is_selected = state;
		if (state) {
			setDefaultBorder(Color.YELLOW);
		} else {
			setDefaultBorder(new Color(0,0,0,0));
		}
	}
	
	private void setDefaultBorder(Color innerColor) {
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(0,0,0,0), 5),
				BorderFactory.createLineBorder(innerColor, 5)));
	}

	public PlayerIcon getPlayerIcon() {
		return playerIcon;
	}

}
