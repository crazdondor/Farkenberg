package farkenberg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Settings - holds game settings. Use {@link #read_file()} to read from the settings.
 * Use <b><code>set_*</code></b> to make changes and {@link #write_file()} to save those changes.
 */
public class Settings {
	private int num_sides;
	private int num_dice;
	private int num_turns;
	private String filename;
	
	public Settings() {
		this.filename = "yahtzeeConfig.txt";
	}
	
	/**
	 * Loads configuration from the config file.
	 */
	public void read_file() {
		try {
			FileReader reader = new FileReader(filename);
			BufferedReader br = new BufferedReader(reader);

			String Sides_temp = br.readLine();
			num_sides = Integer.parseInt(Sides_temp);
			String Dice_temp = br.readLine();
			num_dice = Integer.parseInt(Dice_temp);
			String Turns_temp = br.readLine();
			num_turns = Integer.parseInt(Turns_temp);

			br.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		} catch (IOException ex) {
			System.out.println("Error reading file");
		}
	}
	
	/**
	 * Writes the configuration file using the current settings
	 */
	public void write_file() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write(Integer.toString(num_sides));
			bw.newLine();
			
			bw.write(Integer.toString(num_dice));
			bw.newLine();
			
			bw.write(Integer.toString(num_turns));
			bw.newLine();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		} catch (IOException ex) {
			System.out.println("Error reading file");
		}
	}
	
	/**
	 * Returns config value for: number of sides on each die
	 * @return num sides
	 */
	public int get_numSides() {
		return num_sides;
	}
	
	/**
	 * Changes config value for: number of sides on each die<br/>
	 * Use write_file() to save changes.
	 * 
	 * @param num_sides new value
	 */
	public void set_numSides(int num_sides) {
		this.num_sides = num_sides;
	}
	
	/**
	 * Returns config value for: number of dice in each hand
	 * @return num dice
	 */
	public int get_numDice() {
		return num_dice;
	}
	
	/**
	 * Changes config value for: number of dice in each hand<br/>
	 * Use write_file() to save changes.
	 * 
	 * @param num_dice new value
	 */
	public void set_numDice(int num_dice) {
		this.num_dice = num_dice;
	}
	
	/**
	 * Returns config value for: number of turns (or rolls) per round
	 * @return num turns
	 */
	public int get_numTurns() {
		return num_turns;
	}
	
	/**
	 * Changes config value for: number of turns (or rolls) per round.<br/>
	 * Use write_file() to save changes.
	 * 
	 * @param num_turns new value
	 */
	public void set_numTurns(int num_turns) {
		this.num_turns = num_turns;
	}

}
