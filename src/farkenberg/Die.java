package farkenberg;

import java.util.*;

/**
 * Die - represents a Die.
 */
public class Die {
	private static Random rand = new Random();
	
	private boolean isKept = false;
	private int sideUp = 0;
	private int numSides;
	
	/**
	 * Construct a new Die with the given number of sides
	 * @param sides number of sides
	 */
	public Die(int sides) {
		this.numSides = sides;
	}

	/**
	 * Construct a new Die with the given number of sides and specific sideUp
	 * @param sides number of sides
	 * @param sideUp side up
	 */
	public Die(int sides, int sideUp) {
		this.numSides = sides;
		this.sideUp = sideUp;
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

	public int get_numSides() {
		return numSides;
	}

	public static class DieComparator implements Comparator<Die> {
	    @Override
	    public int compare(Die a, Die b) {
	        return a.sideUp < b.sideUp ? -1 : a.sideUp == b.sideUp ? 0 : 1;
	    }
	}
	
	@Override
	public String toString() {
		return Integer.toString(sideUp);
	}
}