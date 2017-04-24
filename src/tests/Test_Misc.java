package tests;

import org.junit.*;

import farkenberg.PlayerIcon;

import static org.junit.Assert.*;

public class Test_Misc {
	
	@Test
	public void test_misc() {
		assertTrue(PlayerIcon.values()[0].getImage() != null);
	}
	
}
