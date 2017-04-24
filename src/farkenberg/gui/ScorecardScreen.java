package farkenberg.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import farkenberg.Game;
import farkenberg.Player;

public class ScorecardScreen implements Screen {
	JPanel parent;
	Game game;
	Screen previous_screen;
	boolean is_game_over;
	
	public ScorecardScreen(Game game, Screen previous_screen, boolean is_game_over) {
		this.game = game;
		this.previous_screen = previous_screen;
		this.is_game_over = is_game_over;
		
		parent = new JPanel();
		parent.setLayout(new GridBagLayout());
		
		init();
	}
	
	private void init() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 10, 5, 10);
		
		JLabel title = new JLabel(is_game_over ? "Game Over" : "Scorecard");
		title.setFont(title.getFont().deriveFont(24f));
		title.setPreferredSize(new Dimension(500, 30));
		
		JPanel listPanel = new JPanel(new GridBagLayout());
		createScoreboard(listPanel);
		
		JButton backButton = new JButton("BACK");
		backButton.setPreferredSize(new Dimension(500, 28));
		backButton.setFont(backButton.getFont().deriveFont(18f));
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.set_currentScreen(previous_screen);
			}
			
		});
		
		parent.add(title, gbc);
		
		gbc.gridy += 1;
		parent.add(listPanel, gbc);
		
		if (!is_game_over) {
			gbc.gridy += 1;
			parent.add(backButton, gbc);
		}
	}
	
	private void createScoreboard(JPanel container) {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(5, 10, 5, 10);
		gbc.weighty = 1.0;
		
		for (Player p : game.getPlayers()) {
			String nameLabel = p.get_playerName();
			String scoreLabel = NumberFormat.getNumberInstance(Locale.US).format(p.get_playerBank());
			
			gbc.gridx = 0;
			gbc.weightx = 0.1;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.fill = GridBagConstraints.BOTH;
			container.add(createLabel(nameLabel, 18), gbc);
			
			gbc.gridx = 1;
			gbc.weightx = 1.0;
			gbc.anchor = GridBagConstraints.EAST;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			container.add(createLabel(scoreLabel, 18), gbc);
			
			gbc.gridy += 1;
		}
		
		JPanel spacer = new JPanel();
		gbc.gridx = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.NONE;
		spacer.setPreferredSize(new Dimension(500, 10));
		container.add(spacer, gbc);
		gbc.gridy += 1;
	}

	// create a JLabel with the given text and font size
	private JLabel createLabel(String text, float font_size) {
		JLabel label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(font_size));
		return label;
	}

	@Override
	public JPanel getParent() {
		return parent;
	}

}
