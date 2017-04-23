package farkenberg;

import java.io.*;
import java.util.*;

public class Settings {
	private int num_sides;
	private int num_dice;
	private int num_turns;
	private String filename;

	// constructor
	public Settings() {
		filename = "yahtzeeConfig.txt";
	}

	// reads the file
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
		}

		catch (FileNotFoundException ex) {
			System.out.println("File not found");
		}

		catch (IOException ex) {
			System.out.println("Error reading file");
		}
	}

	// writes to the file
	public void write_file() {
		Scanner input = new Scanner(System.in);
		try {
			FileWriter writer = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(writer);

			System.out.println("Number of sides: ");
			num_sides = input.nextInt();
			String Sides_temp = Integer.toString(num_sides);
			bw.write(Sides_temp);
			bw.newLine();
			System.out.println("Number of dice: ");
			num_dice = input.nextInt();
			String Dice_temp = Integer.toString(num_dice);
			bw.write(Dice_temp);
			bw.newLine();
			System.out.println("Number of rolls: ");
			num_turns = input.nextInt();
			String Turns_temp = Integer.toString(num_turns);
			bw.write(Turns_temp);
			bw.newLine();
			bw.close();
		}

		catch (FileNotFoundException ex) {
			System.out.println("File not found");
		}

		catch (IOException ex) {
			System.out.println("Error reading file");
		}
	}

	// returns the number of sides of each die
	public int get_numSides() {
		return num_sides;
	}

	// returns the number of dice in play
	public int get_numDice() {
		return num_dice;
	}

	// returns the number of turns per round
	public int get_numTurns() {
		return num_turns;
	}

}
