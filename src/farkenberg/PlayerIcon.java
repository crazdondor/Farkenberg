package farkenberg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
	public final String name;
	public final String file;
	
	private final String icon_folder = "res/";
	
	PlayerIcon(String name, String file) {
		this.name = name;
		this.file = file;
	}
	
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
