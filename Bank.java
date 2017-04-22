public class Bank {

	//attributes
	private int bankedPoints;

	public Bank()
	{
		bankedPoints = 0;
	}

	public void set_bankedPoints(int points)
	{
		bankedPoints += points;
	}

	public int get_bankedPoints()
	{
		return bankedPoints;
	}

}