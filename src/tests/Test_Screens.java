package tests;

import org.junit.*;

import farkenberg.gui.GameWindow;
import farkenberg.gui.Screen;

import static org.junit.Assert.*;

import javax.swing.JPanel;

public class Test_Screens {

	@Test
	public void testScreens() {
		DummyScreen1 a = new DummyScreen1();
		DummyScreen1 b = new DummyScreen1();
		DummyScreen2 c = new DummyScreen2();
		
		GameWindow.set_currentScreen(a);
		
		assertTrue(GameWindow.get_currentScreen() == a);
		assertFalse(GameWindow.get_currentScreen() == b);
		assertFalse(GameWindow.get_currentScreen() == c);

		GameWindow.set_currentScreen(c);
		
		assertFalse(GameWindow.get_currentScreen() == a);
		assertFalse(GameWindow.get_currentScreen() == b);
		assertTrue(GameWindow.get_currentScreen() == c);
	}
	
	public static class DummyScreen1 implements Screen {
		JPanel parent = new JPanel();
		
		@Override
		public JPanel getParent() {
			return parent;
		}
	}

	public static class DummyScreen2 implements Screen {
		JPanel parent = new JPanel();
		
		@Override
		public JPanel getParent() {
			return parent;
		}
	}
	
}
