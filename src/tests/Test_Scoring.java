package tests;

import org.junit.*;

import farkenberg.Die;
import farkenberg.Hand;
import farkenberg.Scoring;
import farkenberg.ScoringOption;
import farkenberg.Settings;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class Test_Scoring {
	public static final int ANY_DIE1 = 50;
	public static final int ANY_DIE2 = 100;
	public static final int THREEK_1 = 300;
	public static final int THREEK_2 = 300;
	public static final int THREEK_3 = 400;
	public static final int THREEK_4 = 400;
	public static final int THREEK_5 = 500;
	public static final int THREEK_6 = 500;
	public static final int THREEK_7 = 600;
	public static final int THREEK_8 = 600;
	public static final int THREEK_9 = 700;
	public static final int FOUR_OF_A_KIND = 700;
	public static final int FIVE_OF_A_KIND = 1000;
	public static final int SIX_OF_A_KIND = 2000;
	public static final int SEVEN_OF_A_KIND = 3000;
	public static final int EIGHT_OF_A_KIND = 3500;
	public static final int NINE_OF_A_KIND = 4000;
	public static final int TEN_OF_A_KIND = 5000;
	public static final int STRAIGHT_OF_4 = 1000;
	public static final int STRAIGHT_OF_5 = 1500;
	
	Settings settings = new Settings();
	Scoring scoring;
	
	@Test
	public void test_scoring() {
		settings.set_property(Settings.PROPERTY_NUMDICE, 5);
		settings.set_property(Settings.PROPERTY_NUMSIDES, 6);
		scoring = new Scoring(settings, 5, 6);
		
		// --- check die1 & die2 scoring
		assertTrue(checkForScore(ANY_DIE1, 5, 1, 1, 1, 1));
		assertTrue(checkForScore(ANY_DIE2, 6, 1, 1, 1, 1));
		assertFalse(checkForScore(ANY_DIE1, 1, 2, 3, 4, 6));
		assertFalse(checkForScore(ANY_DIE2, 1, 2, 3, 4, 5));
		assertTrue(checkForScore(ANY_DIE1*2, 5, 5, 1, 1, 1));
		assertTrue(checkForScore(ANY_DIE2*2, 6, 6, 1, 1, 1));
		
		// --- check 3 of kinds
		checkThreeK(THREEK_1, 1, 9);
		checkThreeK(THREEK_2, 2, 9);
		checkThreeK(THREEK_3, 3, 9);
		checkThreeK(THREEK_4, 4, 9);
		checkThreeK(THREEK_5, 5, 1);
		checkThreeK(THREEK_6, 6, 1);

		settings.set_property(Settings.PROPERTY_NUMSIDES, 9);
		checkThreeK(THREEK_7, 7, 1);
		checkThreeK(THREEK_8, 8, 1);
		checkThreeK(THREEK_9, 9, 1);

		settings.set_property(Settings.PROPERTY_NUMSIDES, 6);
		settings.set_property(Settings.PROPERTY_NUMDICE, 10);
		
		// --- check x of kinds
		for (int i = 1; i <= 0; i++) {
			assertTrue(checkForScore(FOUR_OF_A_KIND, i,i,i,i));
			assertTrue(checkForScore(FOUR_OF_A_KIND, i,i,i,i,i));
		}
		
		assertTrue(checkForScore(FOUR_OF_A_KIND, 	1,1,1,1,1));
		assertTrue(checkForScore(FIVE_OF_A_KIND, 	1,1,1,1,1));

		assertTrue(checkForScore(FOUR_OF_A_KIND, 	1,1,1,1,1,1));
		assertTrue(checkForScore(FIVE_OF_A_KIND, 	1,1,1,1,1,1));
		assertTrue(checkForScore(SIX_OF_A_KIND, 	1,1,1,1,1,1));
		
		assertTrue(checkForScore(FOUR_OF_A_KIND, 	1,1,1,1,1,1,1));
		assertTrue(checkForScore(FIVE_OF_A_KIND, 	1,1,1,1,1,1,1));
		assertTrue(checkForScore(SIX_OF_A_KIND, 	1,1,1,1,1,1,1));
		assertTrue(checkForScore(SEVEN_OF_A_KIND, 	1,1,1,1,1,1,1));
		
		assertTrue(checkForScore(FOUR_OF_A_KIND, 	1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(FIVE_OF_A_KIND, 	1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(SIX_OF_A_KIND, 	1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(SEVEN_OF_A_KIND, 	1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(EIGHT_OF_A_KIND, 	1,1,1,1,1,1,1,1));
		
		assertTrue(checkForScore(FOUR_OF_A_KIND, 	1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(FIVE_OF_A_KIND, 	1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(SIX_OF_A_KIND, 	1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(SEVEN_OF_A_KIND, 	1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(EIGHT_OF_A_KIND, 	1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(NINE_OF_A_KIND, 	1,1,1,1,1,1,1,1,1));
		
		assertTrue(checkForScore(FOUR_OF_A_KIND, 	1,1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(FIVE_OF_A_KIND, 	1,1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(SIX_OF_A_KIND, 	1,1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(SEVEN_OF_A_KIND, 	1,1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(EIGHT_OF_A_KIND, 	1,1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(NINE_OF_A_KIND, 	1,1,1,1,1,1,1,1,1,1));
		assertTrue(checkForScore(TEN_OF_A_KIND, 	1,1,1,1,1,1,1,1,1,1));
		
		// --- check for straights
		assertTrue(checkForScore(STRAIGHT_OF_4, 	1,2,3,4));
		assertTrue(checkForScore(STRAIGHT_OF_4, 	1,2,3,3,4));
		assertTrue(checkForScore(STRAIGHT_OF_4, 	1,1,1,4,1,2,2,2,10,3,3,3,4));
		assertTrue(checkForScore(STRAIGHT_OF_5, 	1,2,3,4,5));
		assertTrue(checkForScore(STRAIGHT_OF_5, 	1,2,3,3,4,5));
		assertTrue(checkForScore(STRAIGHT_OF_5, 	1,1,1,2,5,3,30,3,4,5,6));
	}
	
	// n -> number for three k score
	// o -> any other number not in the same scoring group
	public void checkThreeK(int score, int n, int o) {
		assertTrue(checkForScore(score, n,n,n));
		assertTrue(checkForScore(score, o,n,n,n,o));
		assertTrue(checkForScore(score, n,n,n,n,n));
		assertTrue(checkForScore(score, o,n,o,n,n));
	}
	
	public boolean checkForScore(int score, int...is) {
		return checkForScore(score, createOptions(is));
	}
	
	public boolean checkForScore(int score, List<ScoringOption> options) {
		for (ScoringOption o : options) {
			if (o.points == score) {
				return true;
			}
		}
		return false;
	}
	
	public List<ScoringOption> createOptions(int...is) {
		Hand hand = createHand(is);
		return scoring.getNonZeroOptions(hand);
	}
	
	public Hand createHand(int... is) {
		ArrayList<Die> list = new ArrayList<Die>();
		
		int sides = settings.get_property(Settings.PROPERTY_NUMSIDES);
		
		for (int i = 0; i < is.length; i++) {
			list.add(new Die(sides, is[i]));
		}
		
		return new Hand(list, sides);
	}
}