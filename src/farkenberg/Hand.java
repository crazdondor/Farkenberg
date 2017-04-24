package farkenberg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Hand implements Iterable<Die> {
	private List<Die> hand; // array of Die objects that represents the hand
	private int numSides;
	
	/**
	 * Construct a new Hand with the given configuration.
	 * 
	 * @param number_of_die the number of die this hand should have
	 * @param number_of_sides_on_die the number of sides on each die
	 */
	public Hand(int number_of_die, int number_of_sides_on_die) {
		hand = new ArrayList<Die>(number_of_die);
		
		this.numSides = number_of_sides_on_die;
		
		// populate the 'hand' array with new Die objects
		for (int i = 0; i < number_of_die; i++) {
			hand.add(new Die(numSides));
		}
	}
	
	/**
	 * Construct a new Hand from a given list of Die
	 * @param hand
	 */
	public Hand(List<Die> hand, int num_sides) {
		this.hand = hand;
		this.numSides = num_sides;
	}
	
	/**
	 * Copy this Hand.
	 * @return
	 */
	public Hand copy(boolean copyKeptStatus) {
		ArrayList<Die> new_hand = new ArrayList<>();
		for (Die d : hand) {
			Die copy_die = new Die(numSides, d.get_sideUp());
			if (copyKeptStatus)
				copy_die.set_isKept(d.get_isKept());
			new_hand.add(copy_die);
		}
		return new Hand(new_hand, this.numSides);
	}

	/**
	 * Copy this Hand.
	 * @return
	 */
	public Hand copy() {
		return copy(true);
	}
	
	/**
	 * Returns a sorted list of this Hand's dice
	 * @param removeDuplicates if true, duplicates will be removed
	 * @return sorted dice list
	 */
	public ArrayList<Die> sorted(boolean removeDuplicates) {
		ArrayList<Die> sorted = new ArrayList<>();
		for (Die d : hand) {
			sorted.add(d);
		}
		Collections.sort(sorted, new Die.DieComparator());
		
		ArrayList<Die> ret = new ArrayList<>();
		Die prev = null;
		for (Die d : sorted) {
			if (prev == null) {
				ret.add(prev = d);
				continue;
			}
			
			if (prev.get_sideUp() == d.get_sideUp()) {
				continue;
			} else {
				ret.add(prev = d);
			}
		}
		
		return sorted;
	}
	
	/**
	 * Returns a sorted list of this Hand's dice
	 * @return sorted dice list
	 */
	public ArrayList<Die> sorted() {
		return sorted(false);
	}
	
	/**
	 * Returns a Hand of only die in this hand that are kept
	 * @return
	 */
	public Hand get_kept() {
		List<Die> new_hand = new ArrayList<Die>();
		for (Die d : hand) {
			if (d.get_isKept()) {
				new_hand.add(new Die(numSides, d.get_sideUp()));
			}
		}
		return new Hand(new_hand, this.numSides);
	}
	
	/**
	 * Rolls all the dice in this hand that are not kept.
	 */
	public void roll_hand() {
		int rolled = 0;
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).get_isKept() == false) {
				hand.get(i).roll_die();
				rolled++;
			}
		}
		System.out.println("Rolled " + rolled + " of " + hand.size() + " dice");
	}

	/**
	 * Rolls all the dice in this hand
	 */
	public void roll_all() {
		int rolled = 0;
		for (int i = 0; i < hand.size(); i++) {
			hand.get(i).set_isKept(false);
			hand.get(i).roll_die();
			rolled++;
		}
		System.out.println("Rolled " + rolled + " of " + hand.size() + " dice");
	}
	
	/**
	 * Returns this hand as a Die array.
	 * @return Die array
	 */
	public Die[] get_array() {
		return hand.toArray(new Die[hand.size()]);
	}
	
	/**
	 * Get specific die
	 * @param i index
	 * @return Die
	 */
	public Die get(int i) {
		return hand.get(i);
	}
	
	/**
	 * Get number of dice in this hand
	 * @return dice count
	 */
	public int size() {
		return hand.size();
	}
	
	/**
	 * Returns an array of the sideUps of each die.
	 * 
	 * @return int array representing the sideUp value of each die.
	 */
	public int[] get_nums() {
		int[] nums = new int[hand.size()];
		
		int i = 0;
		for (Die d : hand) {
			nums[i++] = d.get_sideUp();
		}
		return nums;
	}
	
	/**
	 * Set the isKept status of the dice in the hand.
	 * 
	 * @param keep
	 * 		<p>a string in the format of "ynyny" where each character represents the new state of a die in that index.</p>
	 *		<p><b>y</b> is to keep and <b>n</b> is to not keep.</p>
	 */
	public void set_kept(String keep) {
		for (int i = 0; i < hand.size(); i++) {
			try {
				if (keep.charAt(i) == 'y')
					hand.get(i).set_isKept(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Iterator<Die> iterator() {
		return new Iterator<Die>() {
			
			int index = 0;

			@Override
			public boolean hasNext() {
				return index < hand.size();
			}

			@Override
			public Die next() {
				return hand.get(index++);
			}
			
		};
	}
}