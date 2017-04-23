package farkenberg;

/**
 * Bank - Player Bank
 */
public class Bank {
	private int bankedPoints;
	
	/**
	 * Construct a new Bank object with no points (zero).
	 */
	public Bank() {
		this.bankedPoints = 0;
	}
	
	/**
	 * Set this Bank's points to the given number of points.
	 * 
	 * @param points new bank balance
	 */
	public void set_bankedPoints(int points) {
		this.bankedPoints = points;
	}
	
	/**
	 * Get the number of points in this Bank.
	 * 
	 * @return number of points
	 */
	public int get_bankedPoints() {
		return this.bankedPoints;
	}

}