package farkenberg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * PlayerIcon - this enumeration holds the types of icons the player
 * can choose from.
 */
public enum PlayerIcon {
	BEAR("Bear", "bear.jpg"),
	BUNNY("Bunny", "bunny.jpg"),
	CAT("Cat", "cat.jpg"),
	DOG("Dog", "dog.jpg"),
	ELEPHANT("Elephant", "elephant.jpg"),
	FISH("Fish", "fish.jpg"),
	FISH2("Fish2", "fish2.jpg"),
	FROG("Frog", "frog.jpg"),
	HORSE("Horse", "horse.jpg"),
	MONKEY("Monkey", "monkey.jpg"),
	MOUSE("Mouse", "mouse.jpg"),
	PIG("Pig", "pig.jpg"),
	SEAHORSE("Seahorse", "seahorse.jpg"),
	SHEEP("Sheep", "sheep.jpg"),
	TIGER("Tiger", "tiger.jpg"),
	;
	
	final String name;
	final String file;
	
	final String icon_folder = "res/";
	
	PlayerIcon(String name, String file) {
		this.name = name;
		this.file = file;
	}
	
	/**
	 * Get a human-friendly name for this icon.
	 * @return icon name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the BufferedImage for this icon.
	 * @return icon image
	 */
	public BufferedImage getImage() {
		return getImageFor(this);
	}
	
	/**
	 * Returns the BufferedImage for the given icon.
	 * @param icon the given icon
	 * @return icon image
	 */
	public BufferedImage getImageFor(PlayerIcon icon) {
		File img = new File(icon_folder + icon.file);
		
		try {
			BufferedImage image = ImageIO.read(img);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
