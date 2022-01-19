package spacegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;


public class SpaceShip extends Ship{

	private int spaceShip_x_increaser = 20;
	
	private int spaceShip_y_increaser = 10;
	
	public SpaceShip() {
		super();
	}
	
	public SpaceShip(int hP,int xCord,int yCord,String image_name) throws FileNotFoundException, IOException {
		this.hP = hP;
		this.xCord = xCord;
		this.yCord = yCord;
		this.image_name = image_name;
		this.ship_image = ImageIO.read(new FileImageInputStream(new File(image_name)));
		this.ship_image_width = ship_image.getWidth();
		this.ship_image_height = ship_image.getHeight();
	}
	
	
	
	public void TakingDamage () {
		
	}

	public int getSpaceShip_x_increaser() {
		return spaceShip_x_increaser;
	}

	public void setSpaceShip_x_increaser(int spaceShip_x_increaser) {
		this.spaceShip_x_increaser = spaceShip_x_increaser;
	}

	public int getSpaceShip_y_increaser() {
		return spaceShip_y_increaser;
	}

	public void setSpaceShip_y_increaser(int spaceShip_y_increaser) {
		this.spaceShip_y_increaser = spaceShip_y_increaser;
	}
}
