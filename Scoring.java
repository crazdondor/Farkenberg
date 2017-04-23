/**
 *@author Bennett Falkenberg
 *Date Created: 2/2/2017
 *Last Modified: 3/1/2017
 *@param hand an array of the sides up of die
 *@param num_sides the amount of sides on each die
 *@param num_dice the amount of die in the hand
 *@param num_turns the amount of turns in the round
 */

package guiYahtzee;

public class Scoring {

	//Scoring attributes
	private int hand[];
	private int num_sides;
	private int num_dice;
	
	//Constructor
	public Scoring(int numSides, int numDice)
	{
		num_sides = numSides;
		num_dice = numDice;
		hand = new int[num_dice];
	}
	
	//get the hand array
	public int[] get_hand()
	{
		return hand;
	}
	
	//Sort the hand using bubble sort
	public int[] sortHand(int array[])
	{
		boolean swap;
		int temp;
		int size = array.length;
		
		do
		{
			swap = false;
			for(int count = 0; count < (size - 1); count++)
			{
				if(array[count] > array[count+1])
				{
					temp = array[count];
					array[count] = array[count+1];
					array[count+1] = temp;
					swap = true;
				}
			}
		} while(swap);
		
		return array;
	}
	
	//returns the count of the die occurring most in the hand
	public int maxOfAKind(int hand[])
	{
		int maxCount = 0;
		int currentCount;
		for(int dieValue = 1; dieValue <= num_sides; dieValue++)
		{
			currentCount = 0;
			for(int diePosition = 0; diePosition < num_dice; diePosition++)
			{
				if(hand[diePosition] == dieValue)
					currentCount++;
			}
			if(currentCount > maxCount)
				maxCount = currentCount;
		}
		return maxCount;
	}
	
	//returns the total value of all die in the hand
	public int totalAllDice(int hand[])
	{
		int total = 0;
		for(int diePosition = 0; diePosition < num_dice; diePosition++)
			total += hand[diePosition];
		return total;
	}
	
	//returns the length of the longest straight found
	public int maxStraightFound(int hand[])
	{
		int maxLength = 1;
		int curLength = 1;
		for(int counter = 0; counter < num_dice-1; counter++)
		{
			if(hand[counter] + 1 == hand[counter+1]) //jump of 1
				curLength += 1;
			else if(hand[counter] + 1 < hand[counter+1]) // jump of >=2
				curLength = 1;
			if(curLength > maxLength)
				maxLength = curLength;
		}
		return maxLength;
	}
	
	//returns true if a full house is found, false otherwise
	public boolean fullHouseFound(int hand[])
	{
		boolean foundFH = false;
		boolean found3K = false;
		boolean found2K = false;
		int found3 = 0;
		int currentCount;
		for(int dieValue = 1; dieValue <= num_sides; dieValue++)
		{
			currentCount = 0;
			for(int diePosition = 0; diePosition < num_dice; diePosition++)
			{
				if(hand[diePosition] == dieValue)
					currentCount += 1;
				if(currentCount >= 5)
				{
					foundFH = true;
					return foundFH;
				}
			}
		}
		for(int dieValue = 1; dieValue <= num_sides; dieValue++)
		{
			currentCount = 0;
			for(int diePosition = 0; diePosition < num_dice; diePosition++)
			{
				if(hand[diePosition] == dieValue)
					currentCount += 1;
				if(currentCount >= 3)
				{
					found3K = true;
					found3 = dieValue;
				}
			}
		}
		if(found3K)
		{
			for(int dieValue = 1; dieValue <= num_sides; dieValue++)
			{
				currentCount = 0;
				for(int diePosition = 0; diePosition < num_dice; diePosition++)
				{
					if(hand[diePosition] == dieValue && hand[diePosition] != found3)
						currentCount += 1;
					if(currentCount >= 2)
					{
						found2K = true;
						if(found3K && found2K)
							foundFH = true;
						return foundFH;
					}
				}
			}
		}
		else
			foundFH = false;
		return foundFH;
	}
	
	public void displayScore(int hand[])
	{
		//upper scorecard
		for(int dieValue = 1; dieValue <= num_sides; dieValue++)
		{
			int currentCount = 0;
			for(int diePosition = 0; diePosition < num_dice; diePosition++)
			{
				if(hand[diePosition] == dieValue)
					currentCount++;
			}
			System.out.print("Score " + (dieValue * currentCount) + " on the ");
			System.out.print(dieValue + " line\n");
		}
		
		//lower scorecard
		if(maxOfAKind(hand) >= 3)
		{
			System.out.print("Score " + totalAllDice(hand) + " on the ");
			System.out.print("3 of a Kind line\n");
		}
		else
			System.out.println("Score 0 on the 3 of a Kind line");
		if(maxOfAKind(hand) >= 4)
		{
			System.out.print("Score " + totalAllDice(hand) + " on the ");
			System.out.print("4 of a Kind line\n");
		}
		else
			System.out.println("Score 0 on the 4 of a Kind line");
		if(fullHouseFound(hand))
			System.out.println("Score 25 on the Full House Line");
		else
			System.out.println("Score 0 on the Full House Line");
		if(maxStraightFound(hand) >= 4)
			System.out.println("Score 30 on the Small Straight Line");
		else
			System.out.println("Score 0 on the Small Straight Line");
		if(maxStraightFound(hand) >= 5)
			System.out.println("Score 40 on the Large Straight Line");
		else
			System.out.println("Score 0 on the Large Straight Line");
		if(maxOfAKind(hand) >= 5)
			System.out.println("Score 50 on the Yahtzee Line");
		else
			System.out.println("Score 0 on the Yahtzee Line");
		System.out.print("Score " + totalAllDice(hand) + " on the ");
		System.out.print("Chance Line\n");
	}
}
