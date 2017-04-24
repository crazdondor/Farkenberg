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
	private int currentPlayer_turnScore = 0;
	
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
		
		currentPlayer_turnScore = 0;
		
		Player player = players.get(currentPlayer_idx);
		screen.setCurrentPlayer(player);
		screen.clearBankTurnInfo();
		
		System.out.println("Next Turn -> " + player.get_playerName());
		
		currentPlayer_hand = new Hand(settings.get_property(Settings.PROPERTY_NUMDICE), settings.get_property(Settings.PROPERTY_NUMSIDES));
		screen.updateHand(currentPlayer_hand);
		screen.clearAvailableOptions();
	}

	public void process_rollAction() {
		System.out.print("Roll -> ");
		currentPlayer_hand.roll_all();
		screen.updateHand(currentPlayer_hand);
		List<ScoringOption> options = scoring.getNonZeroOptions(currentPlayer_hand);
		screen.updateOptionsAvailable(options);
	}

	public void process_bankAction(ScoringOption option) {
		boolean turnAgain = false;
		
		// bank the points
		currentPlayer_turnScore += option.points;
		screen.updateBankTurnInfo(currentPlayer_turnScore);
		
		// remove dice from the hand that caused the score
		currentPlayer_hand = option.get_futureHand().get_kept().copy(false);
		screen.updateHand(currentPlayer_hand);
		
		// check if there's still available options
		List<ScoringOption> options = scoring.getNonZeroOptions(currentPlayer_hand);
		if (options.isEmpty()) {
			screen.clearAvailableOptions();
		} else {
			screen.updateOptionsAvailable(options);
		}
		
		// if banked all dice
		if (currentPlayer_hand.size() == 0) {
			players.get(currentPlayer_idx).set_playerBank(currentPlayer_turnScore);
			System.out.println("Banked Points -> for:"+players.get(currentPlayer_idx).get_playerName()+" amount:"+currentPlayer_turnScore);
			turnAgain = true;
		}
		
		// check if game over
		if (players.get(currentPlayer_idx).get_playerBank() >= settings.get_property(Settings.PROPERTY_NUMWINPOINTS)) {
			GameWindow.set_currentScreen(new ScorecardScreen(this, screen, true));
			return;
		}
		
		// should the player be allowed another turn?
		if (turnAgain) {
			screen.setCurrentPlayer(players.get(currentPlayer_idx));
			screen.clearBankTurnInfo();

			currentPlayer_hand = new Hand(settings.get_property(Settings.PROPERTY_NUMDICE), settings.get_property(Settings.PROPERTY_NUMSIDES));
			screen.updateHand(currentPlayer_hand);
			screen.clearAvailableOptions();
		}
	}
}
