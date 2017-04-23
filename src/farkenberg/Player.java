package farkenberg;

public class Player {
	private Bank playerBank;
	private String playerName;
	private PlayerIcon playerIcon;

	// constructor
	public Player(String name_of_player, PlayerIcon icon_of_player) {
		this.playerBank = new Bank();
		this.playerName = name_of_player;
		this.playerIcon = icon_of_player;
	}
	
	public String get_playerName() {
		return playerName;
	}

	public void set_playerName(String name) {
		playerName = name;
	}

	public void set_playerBank(int points) {
		playerBank.set_bankedPoints(playerBank.get_bankedPoints() + points);
	}

	public int get_playerBank() {
		return playerBank.get_bankedPoints();
	}

	public void set_icon(PlayerIcon name) {
		this.playerIcon = name;
	}

	public PlayerIcon get_icon() {
		return playerIcon;
	}

}