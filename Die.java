/**
 * @author Bennett Falkenberg
 * Date Created: 2/2/2017
 * Last Modified: 3/1/2017
 * @param isKept boolean that decides whether the die is kept or not
 * @param sideUp integer value representig which side is up
 * @param numSides the amount of sides on each die
 */

import java.util.*;

public class Die {
	
	//class attributes
	private boolean isKept;
	private int sideUp;
	private int numSides;
	
	//constructor
	public Die(int sides)
	{
		numSides = sides;
	}
	
	//rolls the die to a value between 0 and numSides, and adds 1
	public void roll_die()
	{
		Random generator = new Random();
		sideUp = generator.nextInt(numSides) + 1;
	}
	
	//returns the value of sideUp
	public int get_sideUp()
	{
		return sideUp;
	}
	
	//sets the value of isKept
	public void set_isKept(boolean keep)
	{
		isKept = keep;
	}
	
	//gets the value of isKept
	public boolean get_isKept()
	{
		return isKept;
	}

}