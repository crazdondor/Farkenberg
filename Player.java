public class Player {

	//attributes
	private Bank playerBank;
	private String playerName;
	private String fileName;

	//constructor
	public Player (String name_of_player, String name_of_file)
	{

		playerName = name;
		playerBank = new Bank();
		fileName = name_of_file;

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
	
	public void set_fileName(String name)
	{
		fileName = name;
	}

	public String get_fileName()
	{
		return fileName;
	}

}