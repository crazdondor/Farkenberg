package farkenberg.gui;

import javax.swing.JPanel;

/**
 * A game screen.
 */
public interface Screen {
	
	/**
	 * Returns the top-level JPanel of this screen. All components of this Screen
	 * should be under this JPanel.
	 * 
	 * @return the screen's top-level JPanel
	 */
	public JPanel getParent();
	
}
