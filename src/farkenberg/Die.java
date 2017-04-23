package farkenberg;

import java.util.*;

public class Die {
	private static Random rand = new Random();
	
	private boolean isKept;
	private int sideUp;
	private int numSides;

	// constructor
	public Die(int sides) {
		numSides = sides;
	}

	// rolls the die to a value between 0 and numSides, and adds 1
	public void roll_die() {
		sideUp = rand.nextInt(numSides) + 1;
	}

	// returns the value of sideUp
	public int get_sideUp() {
		return sideUp;
	}

	// sets the value of isKept
	public void set_isKept(boolean keep) {
		isKept = keep;
	}

	// gets the value of isKept
	public boolean get_isKept() {
		return isKept;
	}

}