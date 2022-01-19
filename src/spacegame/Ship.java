package spacegame;

import java.awt.image.BufferedImage;

public abstract class  Ship {

	protected int hP;
	protected int xCord;
	protected int yCord;
	protected int velocity = 0;
	protected int spaceShip_x_increaser;
	protected int spaceShip_y_increaser;
	protected int spaceAngle = 0;
	protected BufferedImage ship_image;
	protected BufferedImage damaged_ship_image;
	protected int ship_image_width;
	protected int ship_image_height;
	protected String direction = "";
	
	String image_name;
	String damaged_ship_image_name;
	
	public Ship() {
		
	}
	
	public abstract void TakingDamage ();

	public int gethP() {
		return hP;
	}

	public void sethP(int hP) {
		this.hP = hP;
	}

	public int getxCord() {
		return xCord;
	}

	public void setxCord(int xCord) {
		this.xCord = xCord;
	}

	public int getyCord() {
		return yCord;
	}

	public void setyCord(int yCord) {
		this.yCord = yCord;
	}

	public int getspaceAngel() {
		return spaceAngle;
	}

	public void setspaceAngel(int spaceAngel) {
		this.spaceAngle = spaceAngel;
	}

	public BufferedImage getShip_image() {
		return ship_image;
	}

	public void setShip_image(BufferedImage ship_image) {
		this.ship_image = ship_image;
	}

	public int getShip_image_width() {
		return ship_image_width;
	}

	public void setShip_image_width(int ship_image_width) {
		this.ship_image_width = ship_image_width;
	}

	public int getShip_image_height() {
		return ship_image_height;
	}

	public void setShip_image_height(int ship_image_height) {
		this.ship_image_height = ship_image_height;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
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

	public BufferedImage getDamaged_ship_image() {
		return damaged_ship_image;
	}

	public void setDamaged_ship_image(BufferedImage damaged_ship_image) {
		this.damaged_ship_image = damaged_ship_image;
	}

	public String getDamaged_ship_image_name() {
		return damaged_ship_image_name;
	}

	public void setDamaged_ship_image_name(String damaged_ship_image_name) {
		this.damaged_ship_image_name = damaged_ship_image_name;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
}


