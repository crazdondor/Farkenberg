package farkenberg.gui;

import javax.swing.JPanel;

public class GameScreen implements Screen {
	JPanel parent;
	
	public GameScreen() {
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
