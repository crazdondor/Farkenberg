package farkenberg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
	public ScoringOption getOption_die1(Hand hand) {
		int count = 0;
		
		for (Die d : hand) {
			if (d.get_sideUp() == score_die1) {
				d.set_isKept(false);
				count++;
			} else {
				d.set_isKept(true);
			}
		}
		
		return new ScoringOption(count * 50, "Score " + (count * 50) + " for " + count + " " + score_die1 + "'s", hand);
	}

	// returns the amount of die1 occurring in the hand
	public ScoringOption getOption_die2(Hand hand) {
		int count = 0;
		
		for (Die d : hand) {
			if (d.get_sideUp() == score_die2) {
				d.set_isKept(false);
				count++;
			} else {
				d.set_isKept(true);
			}
		}
		
		return new ScoringOption(count * 100, "Score " + (count * 100) + " for " + count + " " + score_die2 + "'s", hand);
	}

	// determines if a 3 of a kind has been found for a specific number
	public ScoringOption getOption_threeK(Hand hand, int num_to_find, int score) {
		int count = 0;
		
		for (Die d : hand) {
			if (d.get_sideUp() == num_to_find) {
				d.set_isKept(false);
				count++;
			} else {
				d.set_isKept(true);
			}
		}
		
		score = count >= 3 ? score : 0;
		
		return new ScoringOption(score, "Score " + score + " for three of a kind with " + num_to_find + "'s", hand);
	}
	
	// returns the count of the die occurring most in the hand
	public ArrayList<ScoringOption> getOptions_maxKind(Hand hand) {
		HashMap<Integer,ScoringOption> optionMap = new HashMap<Integer,ScoringOption>();
		
		for (int dieValue = 1; dieValue <= settings.get_property(Settings.PROPERTY_NUMSIDES); dieValue++) {
			int count = 0;
			
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).get_sideUp() == dieValue) {
					count++;
					hand.get(i).set_isKept(false);
				} else {
					hand.get(i).set_isKept(true);
				}
			}
			
			int score = 0;
			switch (count) {
			case 4:
				score = 700;
				break;
			case 5:
				score = 1000;
				break;
			case 6:
				score = 2000;
				break;
			case 7:
				score = 3000;
				break;
			case 8:
				score = 3500;
				break;
			case 9:
				score = 4000;
				break;
			case 10:
				score = 5000;
				break;
			}
			
			optionMap.put(dieValue, new ScoringOption(score, "Score "+score+" for a "+count+" of a kind", hand));
		}
		
		
		return new ArrayList<ScoringOption>(optionMap.values());
	}
	
	// returns the length of the longest straight found
	public ArrayList<ScoringOption> getOptions_maxStraight(Hand hand) {
		ArrayList<ScoringOption> ret = new ArrayList<>();
		ArrayList<Die> sortedReversed = hand.sorted(true); // true -> remove duplicates
		Collections.reverse(sortedReversed);
		
		// Note: the result of hand.sorted() uses the same dice objects that are in hand
		
		for (int x = 4; x <= 5; x++) {
			for (Die d : sortedReversed)
				d.set_isKept(true);
			ScoringOption opt = new ScoringOption(0, x == 4 ? "Score 0 for a small straight" : "Score 0 for a large straight", hand);
			
			if (sortedReversed.size() >= x) {
				for (int i = 0; i <= (sortedReversed.size() - x); i++) {
					for (Die d : sortedReversed)
						d.set_isKept(true);
					
					int offset_max = sortedReversed.get(i).get_sideUp();
					boolean fail = false;
					
					for (int j = 0; j < x; j++) {
						if ((offset_max-j) != sortedReversed.get(i+j).get_sideUp()) {
							fail = true;
							break;
						}
						sortedReversed.get(i+j).set_isKept(false);
					}
					
					if (!fail) {
						opt = new ScoringOption(x == 4 ? 1000 : 1500, x == 4 ? "Score 1000 for a small straight" : "Score 1500 for a large straight", hand);
						break;
					}
				}
			}
			
			ret.add(opt);
		}
		
		return ret;
	}
	
	// --------------------------------------------------------------------------------
	
	// get possible scoring options that are not 0
	public List<ScoringOption> getNonZeroOptions(Hand hand) {
		List<ScoringOption> optionList = new ArrayList<>();
		
		for (ScoringOption o : getOptions(hand)) {
			if (o.points != 0) {
				optionList.add(o);
			}
		}
		
		return optionList;
	}

	// get possible scoring options
	public List<ScoringOption> getOptions(Hand hand) {
		List<ScoringOption> optionList = new ArrayList<>();
		
		// --- possible scores for chosen die
		optionList.add(getOption_die1(hand));
		optionList.add(getOption_die2(hand));

		// --- possible scores for 3 of a kinds
		for (int i = 0; i < hand.size(); i++) {
			int score = 0;
			
			if (i == 1 || i == 2)
				score = 300;
			else if (i == 3 || i == 4)
				score = 400;
			else if (i == 5 || i == 6)
				score = 500;
			else if (i == 7 || i == 8)
				score = 600;
			else if (i == 9 || i == 10)
				score = 700;
			
			optionList.add(getOption_threeK(hand, i, score));
		}

		// --- possible scores for 4-10 of a kind
		optionList.addAll(getOptions_maxKind(hand));
		
		// --- possible scores for a straight of 4 and a straight of 5
		optionList.addAll(getOptions_maxStraight(hand));
		
		return optionList;
	}
}
