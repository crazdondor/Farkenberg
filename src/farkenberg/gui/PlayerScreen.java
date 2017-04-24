package farkenberg.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import farkenberg.Game;
import farkenberg.Player;
import farkenberg.PlayerIcon;
import farkenberg.Settings;

/**
 * PlayerScreen - represents the screen where a player enters their display name
 * and chooses and icon.<br/><br/>
 * 
 * The constructor for this class requires a 'player_number' parameter; starting at 1,
 * this number is incremented and a new PlayerScreen is created until it reaches
 * 'num_players' defined in the game settings.
 */
public class PlayerScreen implements Screen {
	JPanel parent;
	Game game;
	int player_number;
	
	PlayerIcon iconSelected = null;
	JTextField nameField;
	
	/**
	 * 
	 * @param game the game, passed in by HomeScreen
	 * @param player_number player number
	 */
	public PlayerScreen(Game game, int player_number) {
		this.game = game;
		this.player_number = player_number;
		
		parent = new JPanel();
		parent.setLayout(new GridBagLayout());
		init();
	}
	
	private void init() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(10,50,10,10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JLabel title = new JLabel("Players");
		title.setFont(title.getFont().deriveFont(30f));
		
		JLabel nameFieldTitle = new JLabel("Enter Player " + player_number + " name:");
		nameFieldTitle.setFont(nameFieldTitle.getFont().deriveFont(20f));
		
		nameField = new JTextField();
		nameField.setFont(nameField.getFont().deriveFont(18f));
		
		JLabel iconPanelTitle = new JLabel("Choose an icon:");
		iconPanelTitle.setFont(title.getFont().deriveFont(20f));
		
		JPanel iconPanel = new JPanel();
		createIconChooserGrid(iconPanel);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(createNextButton());
		
		parent.add(title, gbc);
		
		gbc.gridy += 1;
		parent.add(nameFieldTitle, gbc);
		
		gbc.gridy += 1;
		parent.add(nameField, gbc);
		
		gbc.gridy += 2;
		parent.add(iconPanelTitle, gbc);
		
		gbc.gridy += 1;
		parent.add(iconPanel, gbc);
		
		gbc.gridy += 1;
		parent.add(buttonPanel, gbc);
	}
	
	private void createIconChooserGrid(JPanel container) {
		container.setLayout(new GridLayout(3, 5));
		
		ArrayList<PlayerIconButton> buttonList = new ArrayList<>();
		PlayerIcon[] iconList = PlayerIcon.values();
		
		
		for (int i = 0; i < iconList.length; i++) {
			PlayerIconButton iconButton = new PlayerIconButton(iconList[i]);
			
			iconButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					PlayerIconButton source = (PlayerIconButton) e.getSource();
					
					// do nothing if already selected
					if (source.is_selected()) {
						return;
					}
					
					// set all buttons to not-selected
					for (PlayerIconButton b : buttonList) {
						b.set_selected(false);
					}
					
					// select this icon
					source.set_selected(true);
					iconSelected = source.getPlayerIcon();
				}
	    		
	    	});
			
			buttonList.add(iconButton);
			container.add(iconButton);
		}
	}
	
	private JButton createNextButton() {
		JButton button = new JButton();
		
		if (player_number == game.settings.get_property(Settings.PROPERTY_NUMPLAYERS)) {
			button.setText("Start game");
		} else {
			button.setText("Next Player");
		}
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// return if no name entered
				if (nameField.getText().length() == 0) {
					return;
				}
				
				// return if no icon chosen
				if (iconSelected == null) {
					return;
				}
				
				// add player to game
				Player gamePlayer = new Player(nameField.getText(), iconSelected);
				game.addPlayer(gamePlayer);
				
				// go to next screen
				nextScreen();
			}
			
		});
		
		return button;
	}
	
	// This function determines if there is another player left to configure,
	// if there is then we set the next current screen to a new PlayerScreen
	// otherwise we move on to GameScreen
	private void nextScreen() {
		if (player_number == game.settings.get_property(Settings.PROPERTY_NUMPLAYERS)) {
			// if player_number equals the total number of players then we're done
			// move on to GameScreen
			
			GameWindow.set_currentScreen(new GameScreen(game));
			return;
		}

		GameWindow.set_currentScreen(new PlayerScreen(game, player_number + 1));
	}
	
	@Override
	public JPanel getParent() {
		return parent;
	}

}
