package farkenberg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Settings - holds game settings. Use {@link #read_file()} to read from the settings.
 * Use <b><code>set_*</code></b> to make changes and {@link #write_file()} to save those changes.
 */
public class Settings {
	private int num_sides = 6; 				// 5 - 9
	private int num_dice = 5; 				// 5 - 10
	private int num_players = 4; 			// 1 - 6
	private int num_winpoints = 10000; 		// 7,000 - 20,000
	private String filename;
	
	public static final int PROPERTY_NUMSIDES = 0;
	public static final int PROPERTY_NUMDICE = 1;
	public static final int PROPERTY_NUMPLAYERS = 2;
	public static final int PROPERTY_NUMWINPOINTS = 3;
	
	public Settings() {
		this.filename = "gameConfig.dat";
	}
	
	public static String getRulesHTML() {
		try {
			String html = "";
			BufferedReader br = new BufferedReader(new FileReader("rulesHTML.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				html += line;
			}
			return html;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Loads configuration from the config file.
	 */
	public void read_file() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			num_sides = Integer.parseInt(br.readLine());
			num_dice = Integer.parseInt(br.readLine());
			num_players = Integer.parseInt(br.readLine());
			num_winpoints = Integer.parseInt(br.readLine());
			
			br.close();
		} catch (FileNotFoundException ex) {
			// Create the config file if it doesn't exist
			write_file();
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
			
			bw.write(Integer.toString(num_players));
			bw.newLine();
			
			bw.write(Integer.toString(num_winpoints));
			bw.newLine();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		} catch (IOException ex) {
			System.out.println("Error reading file");
		}
	}
	
	/**
	 * Get the value of the given property.
	 * 
	 * @param property PROPERTY_NUMSIDES, PROPERTY_NUMDICE, or PROPERTY_NUMTURNS
	 * @return property value
	 */
	public int get_property(int property) {
		switch (property) {
			case PROPERTY_NUMSIDES:
				return num_sides;
			case PROPERTY_NUMDICE:
				return num_dice;
			case PROPERTY_NUMPLAYERS:
				return num_players;
			case PROPERTY_NUMWINPOINTS:
				return num_winpoints;
			default:
				throw new RuntimeException("Settings: Unknown property type!");
		}
	}
	
	/**
	 * Sets the value of the given property. Use {@link #write_file()} to save.
	 * 
	 * @param property PROPERTY_NUMSIDES, PROPERTY_NUMDICE, or PROPERTY_NUMTURNS
	 * @param new_value new property value
	 * 
	 * @return returns back 'new_value', which may be different if the new value
	 * passed in does not fall within the acceptable number range.
	 */
	public int set_property(int property, int new_value) {
		switch (property) {
			case PROPERTY_NUMSIDES:
				return num_sides = limit(new_value, 5, 9);
			case PROPERTY_NUMDICE:
				return num_dice = limit(new_value, 5, 10);
			case PROPERTY_NUMPLAYERS:
				return num_players = limit(new_value, 1, 6);
			case PROPERTY_NUMWINPOINTS:
				return num_winpoints = limit(new_value, 7000, 20000);
			default:
				throw new RuntimeException("Settings: Unknown property type!");
		}
	}
	
	private int limit(int value, int lower, int upper) {
		if (value < lower) {
			return lower;
		}
		if (value > upper) {
			return upper;
		}
		return value;
	}

}
