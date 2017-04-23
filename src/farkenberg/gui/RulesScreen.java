package farkenberg.gui;

import javax.swing.JPanel;

public class RulesScreen implements Screen {
	JPanel parent;
	
	public RulesScreen() {
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
