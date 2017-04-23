package farkenberg;

public class Hand {
	private Die[] hand; // array of Die objects that represents the hand
	private int numDie; // amount of die per hand
	private int[] nums; // array of the sides up of each Die
	
	/**
	 * Construct a new Hand with the given configuration.
	 * 
	 * @param number_of_die the number of die this hand should have
	 * @param number_of_sides_on_die the number of sides on each die
	 */
	public Hand(int number_of_die, int number_of_sides_on_die) {
		this.numDie = number_of_die;
		
		this.hand = new Die[numDie];
		this.nums = new int[numDie];
		
		// populate the 'hand' array with new Die objects
		for (int i = 0; i < numDie; i++) {
			hand[i] = new Die(number_of_sides_on_die);
		}
	}
	
	/**
	 * Rolls all the dice in this hand.
	 */
	public void roll_hand() {
		for (int i = 0; i < numDie; i++) {
			if (hand[i].get_isKept() == false) {
				hand[i].roll_die();
				nums[i] = hand[i].get_sideUp();
			}
		}
	}
	
	/**
	 * Returns an array of the sideUps of each die.
	 * 
	 * @return int array representing the sideUp value of each die.
	 */
	public int[] get_nums() {
		return nums;
	}
	
	/**
	 * Displays the hand to console.
	 */
	public void display() {
		for (int i = 0; i < numDie; i++)
			System.out.print(hand[i].get_sideUp() + " ");
		System.out.print("\n");
	}
	
	/**
	 * Set the isKept status of the dice in the hand.
	 * 
	 * @param keep
	 * 		<p>a string in the format of "ynyny" where each character represents the new state of a die in that index.</p>
	 *		<p><b>y</b> is to keep and <b>n</b> is to not keep.</p>
	 */
	public void set_kept(String keep) {
		for (int i = 0; i < numDie; i++) {
			try {
				if (keep.charAt(i) == 'y')
					hand[i].set_isKept(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}