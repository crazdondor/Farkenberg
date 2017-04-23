package farkenberg;

import farkenberg.gui.GameWindow;
import farkenberg.gui.HomeScreen;

public class Main {
	
	public static void main(String[] args) {
		GameWindow.set_currentScreen(new HomeScreen());
		GameWindow.show();
	}
	
}
