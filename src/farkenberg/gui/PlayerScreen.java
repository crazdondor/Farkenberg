package farkenberg.gui;

import javax.swing.JPanel;

public class PlayerScreen implements Screen {
	JPanel parent;
	
	public PlayerScreen() {
		parent = new JPanel();
		init();
	}
	
	private void init() {
		
	}
	
	@Override
	public JPanel getParent() {
		return parent;
	}

}
