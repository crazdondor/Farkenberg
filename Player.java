public class Player {

	//attributes
	private Bank playerBank;
	private String playerName;

	//constructor
	public Player (String name)
	{

		playerName = name;
		playerBank = new Bank();

	}

	public String get_playerName()
	{
		return playerName;
	}

	public void set_playerName(String name)
	{
		playerName = name;
	}

	public void set_playerBank(int points)
	{
		int prev_points = playerBank.get_bankedPoints();
		prev_points += points;
		playerBank.set_bankedPoints(prev_points);
	}

	public int get_playerBank()
	{
		return playerBank.get_bankedPoints();
	}

}