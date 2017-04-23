package farkenberg;

public class Bank {
	private int bankedPoints;

	public Bank() {
		this.bankedPoints = 0;
	}

	public void set_bankedPoints(int points) {
		this.bankedPoints = points;
	}

	public int get_bankedPoints() {
		return this.bankedPoints;
	}

}