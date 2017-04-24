package farkenberg.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import farkenberg.Game;
import farkenberg.Hand;
import farkenberg.Player;
import farkenberg.ScoringOption;
import farkenberg.Settings;

public class GameScreen implements Screen {
	JPanel parent;
	Game game;
	
	ArrayList<DieButton> dice_list = new ArrayList<>();
	ScoringOption chosen_option = null;
	
	// bank center
	JPanel bankCenter;
	
	// bank labels
	JLabel bankScoreLabel;
	JLabel totalScoreLabel;
	
	// player bar
	JPanel playerBar;
	
	// main game panel
	JPanel gamePanel;
	JPanel gameDicePanel;
	JPanel gameButtonPanel;
	JButton rollButton;
	static final String ROLLBUTTONTEXT_OPTIONS_AVAILABLE = "ROLL";
	static final String ROLLBUTTONTEXT_NO_OPTIONS_AVAILABLE = "NEXT PLAYER";
	JButton bankButton;
	
	public GameScreen(Game game) {
		this.game = game;
		game.screen = this;
		
		parent = new JPanel();
		parent.setLayout(new BorderLayout());
		
		init();
		
		this.game.init();
		this.game.nextTurn();
	}
	
	public void setCurrentPlayer(Player player) {
		// --- create player bar
		playerBar.removeAll();
		
		PlayerIconButton playerIcon = new PlayerIconButton(player.get_playerIcon());
		playerBar.add(playerIcon);
		playerBar.add(createLabel(player.get_playerName() + "'s Turn", 24));
		
		// --- update bank stuff
		bankScoreLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(player.get_playerBank()));
	}
	
	private void init() {
		// --- GAME PANEL
		JPanel left = new JPanel(new BorderLayout());
		{
			// --- PLAYER BAR : shows current turn's player's name and icon
			playerBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
			left.add(playerBar, BorderLayout.NORTH);
			
			// --- MAIN PANEL : dice and game buttons
			
			gamePanel = new JPanel(new GridBagLayout());
			{
				GridBagConstraints gbc = new GridBagConstraints();
				
				gameDicePanel = new JPanel();
				resetHand(game.settings.get_property(Settings.PROPERTY_NUMDICE));
				
				gameButtonPanel = new JPanel();
				gameButtonPanel.setLayout(new BoxLayout(gameButtonPanel, BoxLayout.Y_AXIS));
				
				rollButton = new JButton(ROLLBUTTONTEXT_OPTIONS_AVAILABLE);
				rollButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
				rollButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (rollButton.getText().equals(ROLLBUTTONTEXT_NO_OPTIONS_AVAILABLE)) {
							game.nextTurn();
						} else {
							game.process_rollAction();
						}
					}
					
				});
				
				bankButton = new JButton("BANK");
				bankButton.setForeground(Color.LIGHT_GRAY);
				bankButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
				bankButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (chosen_option != null) {
							game.process_bankAction(chosen_option); // TODO
						}
					}
					
				});
				
				gameButtonPanel.add(rollButton);
				gameButtonPanel.add(bankButton);
				
				gamePanel.add(gameDicePanel, gbc);
				gamePanel.add(gameButtonPanel, gbc);
			}
			left.add(gamePanel, BorderLayout.CENTER);
			
			// --- FOOTER : rules and scorecard butotns
			
			JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
			footer.setBorder(BorderFactory.createMatteBorder(0, 50, 0, 0, new Color(0,0,0,0)));
			{
				JButton rulesButton = new JButton("Rules");
				JButton scorecardButton = new JButton("Scorecard");
				
				rulesButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GameWindow.set_currentScreen(new RulesScreen(GameScreen.this));
					}
				});
				
				scorecardButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GameWindow.set_currentScreen(new ScorecardScreen(game, GameScreen.this, false));
					}
				});
				
				footer.add(rulesButton);
				footer.add(scorecardButton);
			}
			left.add(footer, BorderLayout.SOUTH);			
		}
		parent.add(left, BorderLayout.CENTER);
		
		// --- BANK PANEL
		JPanel right = new JPanel(new BorderLayout());
		{
			right.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
			
			JLabel title = new JLabel("BANK");
			title.setBorder(new LineBorder(new Color(0,0,0,0), 10));
			title.setPreferredSize(new Dimension(280, 40));
			title.setFont(title.getFont().deriveFont(Font.BOLD, 28));
			right.add(title, BorderLayout.NORTH);
			
			bankCenter = new JPanel();
			bankCenter.setLayout(new BoxLayout(bankCenter, BoxLayout.Y_AXIS));
			right.add(bankCenter, BorderLayout.CENTER);
			
			JPanel bottom = new JPanel(new GridLayout(4, 1));
			bottom.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
			bottom.add(createLabel("Bank Score:", 20));
			bottom.add(bankScoreLabel = createLabel("0", 18));
			bottom.add(createLabel("Total Score:", 20));
			bottom.add(totalScoreLabel = createLabel(NumberFormat.getNumberInstance(Locale.US).format(game.settings.get_property(Settings.PROPERTY_NUMWINPOINTS)), 18));
			right.add(bottom, BorderLayout.SOUTH);
		}
		parent.add(right, BorderLayout.EAST);
	}
	
	public void setOptionsAvailable(boolean state) {
		if (state) {
			rollButton.setText(ROLLBUTTONTEXT_OPTIONS_AVAILABLE);
		} else {
			rollButton.setText(ROLLBUTTONTEXT_NO_OPTIONS_AVAILABLE);
		}
		bankCenter.removeAll();
		bankCenter.revalidate();
		bankCenter.repaint();
	}
	
	public void updateHand(Hand hand) {
		int[] nums = hand.get_nums();
		
		if (nums.length != dice_list.size()) {
			resetHand(nums.length);
		}
		
		for (int i = 0; i < nums.length; i++) {
			dice_list.get(i).set_numDots(nums[i]);
		}
		
		gameDicePanel.revalidate();
		gameDicePanel.repaint();
	}

	public void resetHand(int num_dice) {
		dice_list.clear();
		
		for (int i = 0; i < num_dice; i++) {
			DieButton die = new DieButton(0, (source_button) -> {
				System.out.println("Selected Die -> " + source_button.get_numDots());
				source_button.set_selected(!source_button.is_selected());
				showOptionsForSelectedDice();
				return false;
			});
			
			dice_list.add(die);
			gameDicePanel.add(die);
		}
	}
	
	public void showOptionsForSelectedDice() {
		chosen_option = null;
		bankButton.setForeground(Color.LIGHT_GRAY);
		
		ArrayList<Integer> numsList = new ArrayList<>();
		for (DieButton d : dice_list) {
			if (d.is_selected()) {
				numsList.add(d.get_numDots());
			}
		}
		
		int[] nums = new int[numsList.size()];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = numsList.get(i);
		}
		
		bankCenter.removeAll();
		
		List<ScoringOption> options = game.scoring.getNonZeroOptions(nums);
		for (ScoringOption o : options) {
			o.set_listener((option) -> {
				for (ScoringOption o2 : options) {
					if (o2 == option)
						continue;
					o2.set_selected(false);
				}
				
				if (option.is_selected()) {
					bankButton.setForeground(Color.BLACK);
				} else {
					bankButton.setForeground(Color.LIGHT_GRAY);
				}
				
				chosen_option = option;
			});
			bankCenter.add(o);
		}
		
		bankCenter.revalidate();
		bankCenter.repaint();
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
