package spacegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AlienShip extends Ship{
	
	private int alien_y_increaser = 2;
	private int alien_x_increaser = 2;
	private int alienBounty;
	private boolean Shielded = true;
	public AlienShip () {
		super();
	}
	
	public AlienShip(int hP,int xCord,int yCord,String image_name,String damaged_ship_image_name,int alienBounty,boolean Shielded) throws FileNotFoundException, IOException {
		this.hP = hP;
		this.xCord = xCord;
		this.yCord = yCord;
		this.image_name = image_name;
		this.damaged_ship_image_name = damaged_ship_image_name;
		this.ship_image = ImageIO.read(new FileImageInputStream(new File(image_name)));
		this.damaged_ship_image = ImageIO.read(new FileImageInputStream(new File(damaged_ship_image_name)));
		this.ship_image_width = ship_image.getWidth();
		this.ship_image_height = ship_image.getHeight();
		this.alienBounty = alienBounty;
		this.Shielded = Shielded;
	}

	
	
	public void TakingDamage () {
		if (Shielded) {
			setShip_image(damaged_ship_image);
			Shielded = false;
		}
		else {
			hP -= 25;
			try {
				musicStuff takingHit_sound = new musicStuff("sound_files/hit_sound.wav",0.01);
				takingHit_sound.playSound();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public int getAlien_y_increaser() {
		return alien_y_increaser;
	}

	public void setAlien_y_increaser(int alien_y_increaser) {
		this.alien_y_increaser = alien_y_increaser;
	}

	public int getAlien_x_increaser() {
		return alien_x_increaser;
	}

	public void setAlien_x_increaser(int alien_x_increaser) {
		this.alien_x_increaser = alien_x_increaser;
	}

	public int getAlienBounty() {
		return alienBounty;
	}

	public void setAlienBounty(int alienBounty) {
		this.alienBounty = alienBounty;
	}
	
}
