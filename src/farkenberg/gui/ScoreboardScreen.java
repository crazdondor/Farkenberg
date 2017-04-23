package farkenberg.gui;

import javax.swing.JPanel;

public class ScoreboardScreen implements Screen {
	JPanel parent;
	
	public ScoreboardScreen() {
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
