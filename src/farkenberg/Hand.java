package farkenberg;

public class Hand {
	static Die[] hand; // array of Die objects that represents the hand
	private int numDie; // amount of die per hand
	private int[] nums; // array of the sides up of each Die

	// constructor
	public Hand(int die, int sides) {
		numDie = die;
		hand = new Die[numDie];
		nums = new int[numDie];
		for (int i = 0; i < numDie; i++) {
			hand[i] = new Die(sides);
		}
	}

	// rolls the die
	public void roll_hand() {
		for (int i = 0; i < numDie; i++) {
			if (hand[i].get_isKept() == false) {
				hand[i].roll_die();
				nums[i] = hand[i].get_sideUp();
			}
		}
	}

	// returns the nums array
	public int[] get_nums() {
		return nums;
	}

	// displays the hand
	public void display() {
		for (int i = 0; i < numDie; i++)
			System.out.print(hand[i].get_sideUp() + " ");
		System.out.print("\n");
	}

	// sets each individual die's isKept to true or false
	public void set_kept(String keep) {
		for (int i = 0; i < numDie; i++) {
			if (keep.charAt(i) == 'y')
				hand[i].set_isKept(true);
		}
	}
}