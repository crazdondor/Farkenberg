package farkenberg;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Scoring {
	private int hand[];
	private int num_sides;
	private int num_dice;

	// Constructor
	public Scoring(int numSides, int numDice) {
		num_sides = numSides;
		num_dice = numDice;
		hand = new int[num_dice];
	}

	/**
	 * Get the hand array
	 * @return int array
	 */
	public int[] get_hand() {
		return hand;
	}
	
	/**
	 * Sort the hand.
	 * 
	 * @param hand the hand
	 * @return sorted habd
	 */
	public int[] sortHand(int hand[]) {
		hand = Arrays.copyOf(hand, hand.length);
		Arrays.sort(hand);

		return hand;
	}
	
	/**
	 * Returns the count of the die with the most occurences
	 * @param hand the hand
	 * @return die count
	 */
	public int maxOfAKind(int hand[]) {
		int maxCount = 0;
		int currentCount;
		for (int dieValue = 1; dieValue <= num_sides; dieValue++) {
			currentCount = 0;
			for (int diePosition = 0; diePosition < num_dice; diePosition++) {
				if (hand[diePosition] == dieValue)
					currentCount++;
			}
			if (currentCount > maxCount)
				maxCount = currentCount;
		}
		return maxCount;
	}
	
	/**
	 * Returns the summation of all die in the given hand.
	 * 
	 * @param hand the hand
	 * @return total sum
	 */
	public int totalAllDice(int hand[]) {
		return IntStream.of(hand).sum();
	}
	
	/**
	 * Returns the length of the longest straight found.
	 * 
	 * @param hand the hand
	 * @return length of longest straight
	 */
	public int maxStraightFound(int hand[]) {
		int maxLength = 1;
		int curLength = 1;
		for (int counter = 0; counter < num_dice - 1; counter++) {
			if (hand[counter] + 1 == hand[counter + 1]) // jump of 1
				curLength += 1;
			else if (hand[counter] + 1 < hand[counter + 1]) // jump of >=2
				curLength = 1;
			if (curLength > maxLength)
				maxLength = curLength;
		}
		return maxLength;
	}
	
	/**
	 * Checks if the hand contains a full house.
	 * 
	 * @param hand the hand
	 * @return true if found, false otherwise
	 */
	public boolean fullHouseFound(int hand[]) {
		boolean foundFH = false;
		boolean found3K = false;
		boolean found2K = false;
		int found3 = 0;
		int currentCount;
		for (int dieValue = 1; dieValue <= num_sides; dieValue++) {
			currentCount = 0;
			for (int diePosition = 0; diePosition < num_dice; diePosition++) {
				if (hand[diePosition] == dieValue)
					currentCount += 1;
				if (currentCount >= 5) {
					foundFH = true;
					return foundFH;
				}
			}
		}
		for (int dieValue = 1; dieValue <= num_sides; dieValue++) {
			currentCount = 0;
			for (int diePosition = 0; diePosition < num_dice; diePosition++) {
				if (hand[diePosition] == dieValue)
					currentCount += 1;
				if (currentCount >= 3) {
					found3K = true;
					found3 = dieValue;
				}
			}
		}
		if (found3K) {
			for (int dieValue = 1; dieValue <= num_sides; dieValue++) {
				currentCount = 0;
				for (int diePosition = 0; diePosition < num_dice; diePosition++) {
					if (hand[diePosition] == dieValue && hand[diePosition] != found3)
						currentCount += 1;
					if (currentCount >= 2) {
						found2K = true;
						if (found3K && found2K)
							foundFH = true;
						return foundFH;
					}
				}
			}
		} else
			foundFH = false;
		return foundFH;
	}

	public void displayScore(int hand[]) {
		// upper scorecard
		for (int dieValue = 1; dieValue <= num_sides; dieValue++) {
			int currentCount = 0;
			for (int diePosition = 0; diePosition < num_dice; diePosition++) {
				if (hand[diePosition] == dieValue)
					currentCount++;
			}
			System.out.print("Score " + (dieValue * currentCount) + " on the ");
			System.out.print(dieValue + " line\n");
		}

		// lower scorecard
		if (maxOfAKind(hand) >= 3) {
			System.out.print("Score " + totalAllDice(hand) + " on the ");
			System.out.print("3 of a Kind line\n");
		} else
			System.out.println("Score 0 on the 3 of a Kind line");
		if (maxOfAKind(hand) >= 4) {
			System.out.print("Score " + totalAllDice(hand) + " on the ");
			System.out.print("4 of a Kind line\n");
		} else
			System.out.println("Score 0 on the 4 of a Kind line");
		if (fullHouseFound(hand))
			System.out.println("Score 25 on the Full House Line");
		else
			System.out.println("Score 0 on the Full House Line");
		if (maxStraightFound(hand) >= 4)
			System.out.println("Score 30 on the Small Straight Line");
		else
			System.out.println("Score 0 on the Small Straight Line");
		if (maxStraightFound(hand) >= 5)
			System.out.println("Score 40 on the Large Straight Line");
		else
			System.out.println("Score 0 on the Large Straight Line");
		if (maxOfAKind(hand) >= 5)
			System.out.println("Score 50 on the Yahtzee Line");
		else
			System.out.println("Score 0 on the Yahtzee Line");
		System.out.print("Score " + totalAllDice(hand) + " on the ");
		System.out.print("Chance Line\n");
	}
}
