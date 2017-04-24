package tests;

import org.junit.*;

import farkenberg.Settings;

import static org.junit.Assert.*;

public class Test_Settings {
	
	@Test
	public void test_settings() {
		Settings s = new Settings();
		
		s.read_file();
		s.set_property(Settings.PROPERTY_NUMDICE, 6);
		s.write_file();
		
		Settings s2 = new Settings();
		s2.read_file();
		assertTrue(s2.get_property(Settings.PROPERTY_NUMDICE) == 6);
		
	}
}
