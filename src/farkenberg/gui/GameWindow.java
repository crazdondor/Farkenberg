package farkenberg.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GameWindow {
	private static JFrame frame;
	private static Screen current_screen = null;
	
	static {
		frame = new JFrame("Farkenberg");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1300,900));
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// oh well
		}
	}
	
	/**
	 * Swaps the current screen (if exists) with the given screen.
	 * 
	 * @param screen the new screen
	 * @return the previous screen
	 */
	public static Screen set_currentScreen(Screen screen) {
		Screen previous_screen = current_screen;
		
		// remove previous screen
		if (previous_screen != null) {
			frame.remove(previous_screen.getParent());
		}
		
		// update current screen
		current_screen = screen;
		
		// add current screen to content pane
		frame.getContentPane().add(current_screen.getParent(), BorderLayout.CENTER);
		
		// tell Swing to do stuff
		frame.getContentPane().revalidate();
		current_screen.getParent().revalidate();
		frame.getContentPane().repaint();
		
		return previous_screen;
	}
	
	/**
	 * Returns the current screen.
	 * @return current screen
	 */
	public static Screen get_currentScreen() {
		return current_screen;
	}
	
	/**
	 * Makes the frame visible, and moves to center of the screen.
	 */
	public static void show() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Hide the frame.
	 */
	public static void hide() {
		frame.setVisible(false);
	}
	
	/**
	 * Close the game window and exit the program.
	 */
	public static void close() {
		WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	    
	    frame.setVisible(false);
		frame.dispose();
		
		System.exit(0);
	}
}
