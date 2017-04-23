package farkenberg;

/**
 * Player - represents a game player
 */
public class Player {
	private Bank playerBank;
	private String playerName;
	private PlayerIcon playerIcon;
	
	/**
	 * Construct a new Player object.
	 * 
	 * @param name_of_player the player's chosen name
	 * @param icon_of_player the player's chosen icon
	 */
	public Player(String name_of_player, PlayerIcon icon_of_player) {
		this.playerBank = new Bank();
		this.playerName = name_of_player;
		this.playerIcon = icon_of_player;
	}
	
	/**
	 * Retrieve this Player's name.
	 * @return player name
	 */
	public String get_playerName() {
		return playerName;
	}
	
	/**
	 * Change this Player's name
	 * @param name new name
	 */
	public void set_playerName(String name) {
		playerName = name;
	}
	
	/**
	 * Add points to this Player's bank.
	 * @param points number of points to add
	 */
	public void set_playerBank(int points) {
		playerBank.set_bankedPoints(playerBank.get_bankedPoints() + points);
	}
	
	/**
	 * Get the number of points in this Player's bank.
	 * @return number of points
	 */
	public int get_playerBank() {
		return playerBank.get_bankedPoints();
	}
	
	/**
	 * Change this Player's icon
	 * @param name new icon
	 */
	public void set_playerIcon(PlayerIcon icon) {
		this.playerIcon = icon;
	}

	/**
	 * Get this Player's icon. See {@link PlayerIcon#getImage()}
	 * 
	 * @return icon
	 */
	public PlayerIcon get_playerIcon() {
		return playerIcon;
	}

}