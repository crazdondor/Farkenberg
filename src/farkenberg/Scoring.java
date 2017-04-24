package farkenberg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scoring {
	private Settings settings;
	private int score_die1;
	private int score_die2;

	// Constructor
	public Scoring(Settings settings, int die1, int die2) {
		this.settings = settings;
		this.score_die1 = die1;
		this.score_die2 = die2;
	}

	// returns the amount of die1 occurring in the hand
	public int get_die1_count(int hand[]) {
		int count = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] == score_die1)
				count++;
		}
		return count;
	}

	// returns the amount of die1 occurring in the hand
	public int get_die2_count(int hand[]) {
		int count = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] == score_die2)
				count++;
		}
		return count;
	}

	// determines if a 3 of a kind has been found for a specific number
	public boolean threeKFound(int hand[], int num_to_find) {
		int count = 0;
		
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] == num_to_find)
				count++;
		}
		
		return count >= 3;
	}
	
	// returns the count of the die occurring most in the hand
	public int maxOfAKind(int hand[]) {
		Arrays.sort(hand);
		int maxCount = 0;
		int currentCount;
		for (int dieValue = 1; dieValue <= settings.get_property(Settings.PROPERTY_NUMSIDES); dieValue++) {
			currentCount = 0;
			for (int i = 0; i < hand.length; i++) {
				if (hand[i] == dieValue)
					currentCount++;
			}
			if (currentCount > maxCount)
				maxCount = currentCount;
		}
		return maxCount;
	}
	
	// returns the length of the longest straight found
	public int maxStraightFound(int hand[]) {
		Arrays.sort(hand);
		int maxLength = 1;
		int curLength = 1;
		for (int counter = 0; counter < hand.length - 1; counter++) {
			if (hand[counter] + 1 == hand[counter + 1]) // jump of 1
				curLength += 1;
			else if (hand[counter] + 1 < hand[counter + 1]) // jump of >=2
				curLength = 1;
			if (curLength > maxLength)
				maxLength = curLength;
		}
		return maxLength;
	}
	
	public List<ScoringOption> getNonZeroOptions(int hand[]) {
		List<ScoringOption> optionList = new ArrayList<>();
		
		for (ScoringOption o : getOptions(hand)) {
			if (o.points != 0) {
				optionList.add(o);
			}
		}
		
		return optionList;
	}

	// displays possible scores
	public List<ScoringOption> getOptions(int hand[]) {
		List<ScoringOption> optionList = new ArrayList<>();
		
		// --- possible scores for chosen die
		int die1_count = get_die1_count(hand);
		optionList.add(new ScoringOption(die1_count * 50, "Score " + (die1_count * 50) + " for " + score_die1));
		int die2_count = get_die2_count(hand);
		optionList.add(new ScoringOption(die2_count * 100, "Score " + (die2_count * 100) + " for " + score_die1));

		// --- possible scores for 3 of a kinds
		for (int i = 0; i < hand.length; i++) {
			if (threeKFound(hand, i)) {
				if (i == 1 || i == 2)
					optionList.add(new ScoringOption(300, "Score " + 300 + " for three of a kind with " + i + "'s"));
				else if (i == 3 || i == 4)
					optionList.add(new ScoringOption(400, "Score " + 400 + " for three of a kind with " + i + "'s"));
				else if (i == 5 || i == 6)
					optionList.add(new ScoringOption(500, "Score " + 500 + " for three of a kind with " + i + "'s"));
				else if (i == 7 || i == 8)
					optionList.add(new ScoringOption(600, "Score " + 600 + " for three of a kind with " + i + "'s"));
				else if (i == 9 || i == 10)
					optionList.add(new ScoringOption(700, "Score " + 700 + " for three of a kind with " + i + "'s"));
			} else {
				optionList.add(new ScoringOption(0, "Score 0 for three of a kind with " + i + "'s"));
			}
		}

		// --- possible scores for 4-10 of a kind
		int maxOfAKind = maxOfAKind(hand);
		if (maxOfAKind >= 4)
			optionList.add(new ScoringOption(700, "Score 700 for a four of a kind"));
		else
			optionList.add(new ScoringOption(0, "Score 0 for a four of a kind"));
		if (maxOfAKind >= 5)
			optionList.add(new ScoringOption(1000, "Score 1000 for a five of a kind"));
		else
			optionList.add(new ScoringOption(0, "Score 0 for a five of a kind"));
		if (maxOfAKind >= 6)
			optionList.add(new ScoringOption(2000, "Score 2000 for a six of a kind"));
		else
			optionList.add(new ScoringOption(0, "Score 0 for a six of a kind"));
		if (maxOfAKind >= 7)
			optionList.add(new ScoringOption(3000, "Score 3000 for a seven of a kind"));
		else
			optionList.add(new ScoringOption(0, "Score 0 for a seven of a kind"));
		if (maxOfAKind >= 8)
			optionList.add(new ScoringOption(3500, "Score 3500 for a eight of a kind"));
		else
			optionList.add(new ScoringOption(0, "Score 0 for a eight of a kind"));
		if (maxOfAKind >= 9)
			optionList.add(new ScoringOption(4000, "Score 4000 for a nine of a kind"));
		else
			optionList.add(new ScoringOption(0, "Score 0 for a nine of a kind"));
		if (maxOfAKind >= 10)
			optionList.add(new ScoringOption(5000, "Score 5000 for a ten of a kind"));
		else
			optionList.add(new ScoringOption(0, "Score 0 for a ten of a kind"));

		// --- possible scores for a straight of 4 and a straight of 5
		int maxStraightFound = maxStraightFound(hand);
		if (maxStraightFound >= 4)
			optionList.add(new ScoringOption(1000, "Score 1000 for a small straight"));
		else
			optionList.add(new ScoringOption(0, "Score 0 for a small straight"));
		if (maxStraightFound >= 5)
			optionList.add(new ScoringOption(1500, "Score 1500 for a large straight"));
		else
			optionList.add(new ScoringOption(0, "Score 0 for a large straight"));
		
		return optionList;
	}
}
