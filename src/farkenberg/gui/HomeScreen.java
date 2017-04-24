package farkenberg.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import farkenberg.Game;
import farkenberg.Settings;

/**
 * HomeScreen - this screen is the first screen that the user(s) see upon starting the game.
 * This screen allows the user to configure the game before starting.<br/>
 * A {@link Game} object must be passed in to the constructor, this is the Game object that
 * will be configured.
 */
public class HomeScreen implements Screen {
	JPanel parent;
	Game game;
	HashMap<Integer, JTextField> propertyFieldMap = new HashMap<Integer, JTextField>();
	
	JButton nextButton;
	
	JPanel diceSelectionPanel;
	int dice_num_selected = 0;
	ArrayList<DieButton> diceSelectionList = new ArrayList<>();
	
	/**
	 * Construct a new HomeScreen
	 * @param game game to be configured
	 */
	public HomeScreen(Game game) {
		this.game = game;
		
		parent = new JPanel();
		parent.setLayout(new BorderLayout());
		init();
	}
	
	// Initializes the 'parent' panel
	private void init() {
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		
		left.setLayout(new GridBagLayout());
		right.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10,10,10,10);
		
		// --- Left
		{
			// titles
			left.add(createLabel("Farkenberg", 64), gbc);
			gbc.gridy++;
			left.add(createLabel("How do you want to play?", 24), gbc);
			gbc.gridy++;
			
			// property fields
			JPanel propertyPanel = new JPanel();
			propertyPanel.setLayout(new GridBagLayout());
			GridBagConstraints propertyGbc = new GridBagConstraints();
			propertyGbc.gridx = 0;
			propertyGbc.gridy = 0;
			propertyGbc.gridheight = 1;
			propertyGbc.gridwidth = 1;
			propertyGbc.insets = new Insets(5,5,5,5);
			propertyGbc.weighty = 1.0;
			
			createPropertyField(propertyPanel, propertyGbc, "Dice in Play:", Settings.PROPERTY_NUMDICE);
			createPropertyField(propertyPanel, propertyGbc, "Sides:", Settings.PROPERTY_NUMSIDES);
			createPropertyField(propertyPanel, propertyGbc, "Points to win:", Settings.PROPERTY_NUMWINPOINTS);
			createPropertyField(propertyPanel, propertyGbc, "Number of Players:", Settings.PROPERTY_NUMPLAYERS);
			
			left.add(propertyPanel, gbc);
			gbc.gridy++;
			
			// dice selection panel title
			left.add(createLabel("Select 2 dice of value:", 24), gbc);
			gbc.gridy++;
			
			// dice selection panel
			diceSelectionPanel = new JPanel();
			left.add(diceSelectionPanel, gbc);
			gbc.gridy++;
			
			// "next" button
			JPanel nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			
			nextButton = new JButton();
			nextButton.setText("NEXT");
			nextButton.setFont(nextButton.getFont().deriveFont(20f));
			nextButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					HomeScreen.this.updateSettings();
					GameWindow.set_currentScreen(new PlayerScreen(game, 1));
				}
				
			});
			
			nextButton.setEnabled(false);
			nextButtonPanel.add(nextButton);
			left.add(nextButtonPanel, gbc);
			gbc.gridy++;
			
			// reset dice selection panel
			resetDiceSelectionPanel();
		}
		
		// --- Right
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		{
			// "Rules" title
			JLabel right_title = createLabel("Rules", 26);
			right.add(right_title, gbc);
			gbc.gridy++;
			
			// Rules text
			JLabel rulesText = new JLabel("<html>" + Settings.getRulesHTML() + "</html>");
			rulesText.setFont(rulesText.getFont().deriveFont(15f));
			right.add(rulesText, gbc);
			
			right.setMaximumSize(new Dimension(9999, 300));
		}
		
		parent.add(left, BorderLayout.CENTER);
		parent.add(right, BorderLayout.EAST);
	}
	
	public void resetDiceSelectionPanel() {
		diceSelectionPanel.removeAll();
		diceSelectionList.clear();
		
		for (int i = 0; i < game.settings.get_property(Settings.PROPERTY_NUMSIDES); i++) {
			DieButton button = new DieButton(i+1, (source_button) -> {
				if (source_button.is_selected() == true) {
					dice_num_selected--;
				} else {
					dice_num_selected++;
					
					// check if over 2, we can't exceed 2
					if (dice_num_selected > 2) {
						dice_num_selected = 2;
						return false; // set cancelled
					}
				}
				
				if (dice_num_selected == 2) {
					// update game config
					game.die1 = 0;
					for (DieButton d : diceSelectionList) {
						if (d.is_selected() == true || (d == source_button && !source_button.is_selected())) {
							if (game.die1 == 0) {
								game.die1 = d.get_numDots();
							} else {
								game.die2 = d.get_numDots();
								break;
							}
						}
					}
					
					System.out.println("Dice Worth Points changed -> " + game.die1 + "," + game.die2);
					
					nextButton.setEnabled(true);
				} else {
					nextButton.setEnabled(false);
				}
				
				return true;
			});
			
			diceSelectionList.add(button);
			diceSelectionPanel.add(button);
		}
		
		nextButton.setEnabled(false);
		diceSelectionPanel.revalidate();
		diceSelectionPanel.repaint();
	}
	
	// update the Game settings using the current field values
	private void updateSettings() {
		for (Map.Entry<Integer, JTextField> entry : propertyFieldMap.entrySet()) {
			int property = entry.getKey();
			int new_value = Integer.parseInt(entry.getValue().getText());
			
			game.settings.set_property(property, new_value);
		}
		game.settings.write_file();
	}
	
	// creates a field for the given game settings property
	private void createPropertyField(JPanel container, GridBagConstraints gbc, String field_name, int gameSettingsProperty) {
		JTextField field = new JTextField();
		field.setFont(field.getFont().deriveFont(18f));
		field.setText(Integer.toString(game.settings.get_property(gameSettingsProperty))); // set value to current/default property
		
		field.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {}

			@Override
			public void focusLost(FocusEvent arg0) {
				// this listener handles validation of the property field when focus is lost
				
				int previous_value = game.settings.get_property(gameSettingsProperty);
				int value = 0;
				
				// check if valid int
				try {
					value = Integer.parseInt(field.getText());
				} catch(NumberFormatException e) {
					// if not an integer, set back to whatever the current setting for property is
					field.setText(Integer.toString(HomeScreen.this.game.settings.get_property(gameSettingsProperty)));
				}
				
				// check if within acceptable range
				int real_value = HomeScreen.this.game.settings.set_property(gameSettingsProperty, value);
				if (real_value != value) {
					field.setText(Integer.toString(real_value));
				}
				
				// if different (i.e. value was actually changed)
				if (previous_value != real_value) {
					if (gameSettingsProperty == Settings.PROPERTY_NUMSIDES) {
						resetDiceSelectionPanel();
					}
				}
			}
			
		});
		
		propertyFieldMap.put(gameSettingsProperty, field); // add to property-field map
		
		// --- add label to container
		gbc.gridx = 0;
		gbc.weightx = 0.1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		container.add(createLabel(field_name, 16), gbc);
		
		// --- add field to container
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		container.add(field, gbc);
		
		gbc.gridy++;
	}
	
	// create a JLabel with the given text and font sizes
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
