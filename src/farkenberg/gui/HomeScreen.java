package farkenberg.gui;

import javax.swing.JPanel;

public class HomeScreen implements Screen {
	JPanel parent;
	
	public HomeScreen() {
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
