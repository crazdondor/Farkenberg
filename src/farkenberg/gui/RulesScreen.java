package farkenberg.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import farkenberg.Settings;

public class RulesScreen implements Screen {
	JPanel parent;
	Screen previousScreen;
	
	public RulesScreen(Screen previousScreen) {
		this.previousScreen = previousScreen;
		
		parent = new JPanel();
		parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
		parent.setBorder(new LineBorder(new Color(0,0,0,0), 10));
		init();
	}
	
	private void init() {
		JButton backButton = new JButton("BACK");
		backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameWindow.set_currentScreen(previousScreen);
			}
			
		});
		

		JLabel rulesText = new JLabel("<html>" + Settings.getRulesHTML() + "</html>");
		rulesText.setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, new Color(0,0,0,0)));
		rulesText.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		parent.add(backButton);
		parent.add(rulesText);
	}
	
	@Override
	public JPanel getParent() {
		return parent;
	}

}
