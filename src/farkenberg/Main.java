package farkenberg;

import farkenberg.gui.GameWindow;
import farkenberg.gui.HomeScreen;

public class Main {
	
	public static void main(String[] args) {
		// load player icons in background
		new Thread() {
			@Override
			public void run() {
				for (PlayerIcon icon : PlayerIcon.values()) {
					icon.getImage();
				}
			}
		}.run();
		
		// start game
		new Thread() {
			@Override
			public void run() {
				HomeScreen screen = new HomeScreen(new Game());
				GameWindow.set_currentScreen(screen);
				GameWindow.show();
			}
		}.run();
	}
	
}
