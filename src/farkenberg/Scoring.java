/**
 *@author Bennett Falkenberg
 *Date Created: 2/2/2017
 *Last Modified: 3/1/2017
 *@param hand an array of the sides up of die
 *@param num_sides the amount of sides on each die
 *@param num_dice the amount of die in the hand
 *@param num_turns the amount of turns in the round
 */

public class Scoring {

	//Scoring attributes
	private int hand[];
	private int num_sides;
	private int num_dice;
	private int score_die1;
	private int score_die2;
	
	//Constructor
	public Scoring(int numSides, int numDice, int die1, int die2)
	{
		num_sides = numSides;
		num_dice = numDice;
		hand = new int[num_dice];
		score_die1 = die1;
		score_die2 = die2;
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

	//returns the amount of die1 occurring in the hand
	public int get_die1_count(int hand[])
	{
		int count = 0;
		for(int diePosition = 0; diePosition < numDice; i++)
		{
			if(hand[diePosition] = score_die1)
				count++;
		}
		return count;
	}

	//returns the amount of die1 occurring in the hand
	public int get_die2_count(int hand[])
	{
		int count = 0;
		for(int diePosition = 0; diePosition < numDice; i++)
		{
			if(hand[diePosition] = score_die2)
				count++;
		}
		return count;
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

	//displays possible scores
	public void displayScore(int hand[])
	{
		//displays possible scores for chosen die
		System.out.println("Score " + (get_die1_count(hand) * 50) + " for " + score_die1);
		System.out.println("Score " + (get_die2_count(hand) * 100) + " for " + score_die2);

		//displays possible scores for 3 of a kinds
		for(int i = 0; i < numDice; i++)
		{
			if(threeKFound(hand, i))
			{
				if(i == 1 || i == 2)
					System.out.println("Score " + 300 + " for three of a kind with " + i + "'s");
				else if(i == 3 || i == 4)
					System.out.println("Score " + 400 + " for three of a kind with " + i + "'s");
				else if(i == 5 || i == 6)
					System.out.println("Score " + 500 + " for three of a kind with " + i + "'s");
				else if(i == 7 || i == 8)
					System.out.println("Score " + 600 + " for three of a kind with " + i + "'s");
				else if(i == 9 || i == 10)
					System.out.println("Score " + 700 + " for three of a kind with " + i + "'s");
			}
			else
			{
				System.out.println("Score 0 for three of a kind with " + i + "'s");
			}
		}

		//displays possible scores for 4-10 of a kind
		if(maxOfAKind(hand) >= 4)
			System.out.println("Score 700 for a four of a kind");
		else
			System.out.println("Score 0 for a four of a kind");
		if(maxOfAKind(hand) >= 5)
			System.out.println("Score 1000 for a five of a kind");
		else
			System.out.println("Score 0 for a five of a kind");
		if(maxOfAKind(hand) >= 6)
			System.out.println("Score 2000 for a six of a kind");
		else
			System.out.println("Score 0 for a six of a kind");
		if(maxOfAKind(hand) >= 7)
			System.out.println("Score 3000 for a seven of a kind");
		else
			System.out.println("Score 0 for a seven of a kind");
		if(maxOfAKind(hand) >= 8)
			System.out.println("Score 3500 for a eight of a kind");
		else
			System.out.println("Score 0 for a eight of a kind");
		if(maxOfAKind(hand) >= 9)
			System.out.println("Score 4000 for a nine of a kind");
		else
			System.out.println("Score 0 for a nine of a kind");
		if(maxOfAKind(hand) >= 10)
			System.out.println("Score 5000 for a ten of a kind");
		else
			System.out.println("Score 0 for a ten of a kind");

		//displays possible scores for a straight of 4 and a straight of 5
		if(maxStraightFound(hand) >= 4)
			System.out.println("Score 1000 for a small straight");
		else
			System.out.println("Score 0 for a small straight");
		if(maxStraightFound(hand) >= 5)
			System.out.println("Score 1500 for a large straight");
		else
			System.out.println("Score 0 for a large straight");
	}

	//determines if a 3 of a kind has been found for a specific number
	public boolean threeKFound(int hand[], int num_to_find)
	{
		int count = 0;
		for(int diePosition = 0; diePosition < numDice; i++)
		{
			if(hand[diePosition] == num_to_find)
				count++;
		}
		if(count >= 3)
			return True;
		else
			return False;
	}
}
