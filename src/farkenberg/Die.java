package farkenberg;

import java.util.*;

/**
 * Die - represents a Die.
 */
public class Die {
	private static Random rand = new Random();
	
	private boolean isKept;
	private int sideUp;
	private int numSides;
	
	/**
	 * Construct a new Die with the given number of sides
	 * @param sides number of sides
	 */
	public Die(int sides) {
		numSides = sides;
	}
	
	/**
	 * Roll the die.
	 */
	public void roll_die() {
		sideUp = rand.nextInt(numSides) + 1;
	}
	
	/**
	 * Get the side up of this die.
	 * 
	 * @return int
	 */
	public int get_sideUp() {
		return sideUp;
	}
	
	/**
	 * Set the 'isKept' state
	 * 
	 * @param keep new state
	 */
	public void set_isKept(boolean keep) {
		isKept = keep;
	}
	
	/**
	 * Get the 'isKept' state
	 */
	public boolean get_isKept() {
		return isKept;
	}

}