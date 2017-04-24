package farkenberg;

import java.util.ArrayList;
import java.util.List;

import farkenberg.gui.GameScreen;
import farkenberg.gui.GameWindow;
import farkenberg.gui.ScorecardScreen;

public class Game {
	// game settings
	public final Settings settings;
	public int die1 = 0;
	public int die2 = 0;
	public Scoring scoring;
	
	// game player variables
	private ArrayList<Player> players;
	
	// current player
	private int currentPlayer_idx = -1;
	private Hand currentPlayer_hand = null;
	private int currentPlayer_numDiceLeft = 0;
	
	// GameScreen for this game.
	public GameScreen screen = null;
	
	public Game() {
		this.settings = new Settings();
		this.settings.read_file();
		
		this.players = new ArrayList<Player>();
	}
	
	public void init() {
		this.scoring = new Scoring(this.settings, this.die1, this.die2);
	}
	
	public void addPlayer(Player player) {
		players.add(player);
		System.out.println("Added Player -> name:" + player.get_playerName() + ", icon:" + player.get_playerIcon().getName());
	}

	public List<Player> getPlayers() {
		ArrayList<Player> clonedPlayers = new ArrayList<>();
		for (Player p : players) {
			clonedPlayers.add(p);
		}
		return clonedPlayers;
	}

	public void nextTurn() {
		currentPlayer_idx++;
		if (currentPlayer_idx >= players.size()) {
			currentPlayer_idx = 0;
		}
		
		currentPlayer_numDiceLeft = settings.get_property(Settings.PROPERTY_NUMDICE);
		
		Player player = players.get(currentPlayer_idx);
		screen.setCurrentPlayer(player);
		
		currentPlayer_hand = new Hand(currentPlayer_numDiceLeft, settings.get_property(Settings.PROPERTY_NUMSIDES));
	}

	public void process_rollAction() {
		currentPlayer_hand.roll_hand();
		screen.updateHand(currentPlayer_hand);
		
		List<ScoringOption> options = scoring.getNonZeroOptions(currentPlayer_hand.get_nums());
		
		screen.setOptionsAvailable(!options.isEmpty());
	}

	public void process_bankAction(ScoringOption option) {
		// bank the points
		players.get(currentPlayer_idx).set_playerBank(option.points);
		
		// check if game over
		if (players.get(currentPlayer_idx).get_playerBank() >= settings.get_property(Settings.PROPERTY_NUMWINPOINTS)) {
			GameWindow.set_currentScreen(new ScorecardScreen(this, screen, true));
		}
	}
}
