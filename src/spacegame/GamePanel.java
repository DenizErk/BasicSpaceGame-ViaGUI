package spacegame;

import java.awt.*;


import java.io.FileWriter;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.Timer;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class Fire{
	private double x,y;
	private int fire_y_increaser = 10;
	private int angle = 0;

	public Fire(double x, double y, int angle) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public int getIncreaser() {
		return fire_y_increaser;
	}

	public int getfireAngle() { return angle; }

	public void setfireAngle(int angle) { this.angle = angle; }

}




public class GamePanel extends JPanel implements KeyListener,ActionListener{
	private static final long serialVersionUID = 1L;

	Timer timer = new Timer(1,this);
	private static int score = 0;
	long start;
	long elapsedTimeMillis;
	static float elapsedTimeSec;
	int remainingShield = 3;
	private String scoreString = new String("Score : ");
	private static int CurrentLevel = 1;
	private static String laser_fire_sound = "sound_files/laser_fire_sound.wav";
	private static String alienShipDestroyed_sound = "sound_files/alienShipDestroyed_sound.wav";
	private static String inGame_sound = "sound_files/inGame_sound.wav";
	private static String remainingShield_sound = "sound_files/remainingShield_sound.wav";
	private static boolean isPaused = false;
	
	
	public static float getElapsedTimeSec() {
		return elapsedTimeSec;
	}

	public static boolean isPaused() {
		return isPaused;
	}

	public static void setPaused(boolean isPaused) {
		GamePanel.isPaused = isPaused;
	}

	private ArrayList<Fire> BulletFire = new ArrayList<Fire>();
	private ArrayList<AlienShip> AlienClubLevel_1 = new ArrayList<AlienShip>();
	private ArrayList<AlienShip> AlienClubLevel_2 = new ArrayList<AlienShip>();
	private ArrayList<AlienShip> AlienClubLevel_3 = new ArrayList<AlienShip>();
	
	
	SpaceShip SpaceShip;
	AlienShip testAlienShip;
	
	private BufferedImage fire_image;
	private BufferedImage Level_1_Space_image;
	private BufferedImage Level_2_Space_image;
	private BufferedImage Level_3_Space_image;
	private static musicStuff AlienDestroyed;
	private static musicStuff inGameMusic;
	
	public static void wait(int ms)
	{
	    try
	    {
	        Thread.sleep(ms);
	    }
	    catch(InterruptedException ex)
	    {
	        Thread.currentThread().interrupt();
	    }
	}
	
	public static int getScore() {
		return score;
	}

	public boolean Hit_Check(AlienShip Alien) {
		
		for (Fire fire : BulletFire) {
			if ((new Rectangle((int)fire.getX(),(int)fire.getY(),20,20)).intersects(new Rectangle(Alien.getxCord(),Alien.getyCord(),60,60))) {
				BulletFire.remove(fire);
				return true;
			}
			
		}
		
		return false;
		
	}

	public static BufferedImage rotate(BufferedImage bimg, double angle) {

		int w = bimg.getWidth();
		int h = bimg.getHeight();

		BufferedImage rotated = new BufferedImage(w, h, bimg.getType());
		Graphics2D graphic = rotated.createGraphics();
		graphic.rotate(Math.toRadians(angle), w/2, h/2);
		graphic.drawImage(bimg, null, 0, 0);
		graphic.dispose();
		return rotated;
	}
	
	public GamePanel() throws FileNotFoundException, IOException  {
		start = System.currentTimeMillis();
		CurrentLevel = 1;
		SpaceShip = new SpaceShip(100,0,670,"image_files/Spaceship.png");
		try {
			inGameMusic = new musicStuff(inGame_sound,0.05);
			inGameMusic.playSound();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		//LEVEL 1
		for (int i = 1;i <= 6; i++) {
			AlienClubLevel_1.add(new AlienShip(50,ThreadLocalRandom.current().nextInt(50, 750 + 1),
					-90-i*400,"image_files/Alien.png","image_files/Alien.png",45,false));
			if (i % 2 == 0){
				AlienClubLevel_1.add(new AlienShip(50,ThreadLocalRandom.current().nextInt(50, 750 + 1),
						-90-i*320,"image_files/Alien.png","image_files/Alien.png",45,false));
			}
			if (i == 6){
				AlienClubLevel_1.add(new AlienShip(50,80,
				-90-i*520,"image_files/shieldAlien.png","image_files/Alien.png",120,true));
				AlienClubLevel_1.add(new AlienShip(50,700,
						-90-i*520,"image_files/shieldAlien.png","image_files/Alien.png",120,true));
			}
		}
		//LEVEL 2
		for (int i = 1;i <= 6; i++) {
			AlienClubLevel_2.add(new AlienShip(50,ThreadLocalRandom.current().nextInt(50, 750 + 1),
					-90-i*900,"image_files/Alien.png","image_files/Alien.png",45,false));
			if (i % 2 == 0){
				AlienClubLevel_2.add(new AlienShip(50,ThreadLocalRandom.current().nextInt(50, 750 + 1),
						-90-i*600,"image_files/shieldAlien.png","image_files/Alien.png",120,true));
			}
			if (i % 3 == 0){
				AlienClubLevel_2.add(new AlienShip(75,ThreadLocalRandom.current().nextInt(50, 750 + 1),
						-90-i*200,"image_files/Alien2.png","image_files/Alien2.png",170,false));
			}
		}
		//LEVEL 3
		for (int i = 1;i <= 10; i++){
			if (i % 2 != 0){
				AlienClubLevel_3.add(new AlienShip(75,ThreadLocalRandom.current().nextInt(50, 750 + 1),
						-90-i*450,"image_files/Alien2.png","image_files/Alien2.png",170,false));
			}
			else {
				AlienClubLevel_3.add(new AlienShip(100,ThreadLocalRandom.current().nextInt(50, 750 + 1),
						-90-i*450,"image_files/Alien3.png","image_files/Alien3.png",215,false));
			}
		}
		for (int i = 7;i <= 11; i++) {
			AlienClubLevel_3.add(new AlienShip(100,ThreadLocalRandom.current().nextInt(50, 750 + 1),
					-90-i*700,"image_files/Alien3.png","image_files/Alien3.png",215,false));
		}
		try {
			fire_image = ImageIO.read(new FileImageInputStream(new File("image_files/fire.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Level_1_Space_image = ImageIO.read(new FileImageInputStream(new File("image_files/Level_1_Space.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Level_2_Space_image = ImageIO.read(new FileImageInputStream(new File("image_files/Level_2_Space.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Level_3_Space_image = ImageIO.read(new FileImageInputStream(new File("image_files/Level_3_Space.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		timer.start();
	}
	@Override
	public void paintComponent(Graphics g1) {
		if (GameMenu.getHighestScore() <= score){
			GameMenu.setHighestScore(score);
		}
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		if (CurrentLevel == 1) {
			g.drawImage(Level_1_Space_image, 0, 0, Level_1_Space_image.getWidth(), Level_1_Space_image.getHeight(),this);
		}
		else if (CurrentLevel == 2) {
			g.drawImage(Level_2_Space_image, 0, 0, Level_2_Space_image.getWidth(), Level_2_Space_image.getHeight(),this);
		}
		else if (CurrentLevel == 3) {
			g.drawImage(Level_3_Space_image, 0, 0, Level_3_Space_image.getWidth(), Level_3_Space_image.getHeight(),this);
		}
		else if (CurrentLevel == 4) {
			inGameMusic.stopSound();
			EndGameScreen.endGameScreen("" + score);
			if (GameMenu.getHighestScore() <= score){
				try {
					FileWriter myWriter = new FileWriter("highestScore.txt");
					myWriter.write(GameMenu.getHighestScoreString());
					myWriter.close();
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			}
			CurrentLevel = 5;
			timer.stop();
		}	
		if (AlienClubLevel_1.size() == 0) {
			CurrentLevel = 2;
			AlienClubLevel_1.add(testAlienShip);
			System.out.println("Level 1 is Complated!");			
		}
		if (AlienClubLevel_2.size() == 0) {
			CurrentLevel = 3;
			AlienClubLevel_2.add(testAlienShip);
			System.out.println("Level 2 is Complated!");
		}
		if (AlienClubLevel_3.size() == 0) {
			CurrentLevel = 4;
			AlienClubLevel_3.add(testAlienShip);
			System.out.println("All Levels Are Complated!");
		}
		SpaceShip.setVelocity((SpaceShip.getSpaceShip_x_increaser()-10)*10);
		elapsedTimeMillis  = System.currentTimeMillis()-start;
		elapsedTimeSec = (elapsedTimeMillis/1000F)-5;
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman",Font.PLAIN,17));
		g.drawString(scoreString+ score, 840, 20);
		g.drawString("Highest Score : " + GameMenu.getHighestScore() , 840, 35);
		g.drawString("velocity : " + SpaceShip.getVelocity() , 840, 50);
		g.drawString("Angle : " + SpaceShip.getspaceAngel() , 840, 65);
		g.setColor(Color.green);
		g.setFont(new Font("TimesRoman",Font.PLAIN,20));
		g.drawString("Time : " + (int)elapsedTimeSec , 15, 25);
		g.setColor(Color.red);
		g.drawString("Health : " + remainingShield , 880, 735);

		if (CurrentLevel == 1) {
			g.drawImage(rotate(SpaceShip.getShip_image(),SpaceShip.getspaceAngel()), SpaceShip.getxCord(), SpaceShip.getyCord(), SpaceShip.getShip_image_width()/10, SpaceShip.getShip_image_height()/10,this);
			for (AlienShip Alien : AlienClubLevel_1) {
				g.drawImage(Alien.getShip_image(), Alien.getxCord(), Alien.getyCord(),
						Alien.getShip_image_width()/20, Alien.getShip_image_height()/20,this);
				
			}			
			for (int i = 0 ; i < AlienClubLevel_1.size(); i++) {
				if(Hit_Check(AlienClubLevel_1.get(i))) {
					AlienClubLevel_1.get(i).TakingDamage();
					if(AlienClubLevel_1.get(i).gethP() <= 0) {
						score += AlienClubLevel_1.get(i).getAlienBounty();
						try {
							AlienDestroyed = new musicStuff(alienShipDestroyed_sound,0.1);
							AlienDestroyed.playSound();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							e1.printStackTrace();
						}
						AlienClubLevel_1.get(i).setShip_image_height(0);
						AlienClubLevel_1.get(i).setShip_image_width(0);
						AlienClubLevel_1.remove(AlienClubLevel_1.get(i));
					}	
				}
			}
		}
		else if (CurrentLevel == 2) {
			g.drawImage(rotate(SpaceShip.getShip_image(),SpaceShip.getspaceAngel()), SpaceShip.getxCord(), SpaceShip.getyCord(), SpaceShip.getShip_image_width()/10, SpaceShip.getShip_image_height()/10,this);
			for (AlienShip Alien : AlienClubLevel_2) {
				g.drawImage(Alien.getShip_image(), Alien.getxCord(), Alien.getyCord(),
						Alien.getShip_image_width()/20, Alien.getShip_image_height()/20,this);	
			}
			for (int i = 0 ; i < AlienClubLevel_2.size(); i++) {
				if(Hit_Check(AlienClubLevel_2.get(i))) {
					AlienClubLevel_2.get(i).TakingDamage();
					if(AlienClubLevel_2.get(i).gethP() <= 0) {
						score += AlienClubLevel_2.get(i).getAlienBounty();
						try {
							AlienDestroyed = new musicStuff(alienShipDestroyed_sound,0.1);
							AlienDestroyed.playSound();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						AlienClubLevel_2.get(i).setShip_image_height(0);
						AlienClubLevel_2.get(i).setShip_image_width(0);
						AlienClubLevel_2.remove(AlienClubLevel_2.get(i));
					}			
				}
			}
		}
		else if (CurrentLevel == 3) {
			g.drawImage(rotate(SpaceShip.getShip_image(),SpaceShip.getspaceAngel()), SpaceShip.getxCord(), SpaceShip.getyCord(), SpaceShip.getShip_image_width()/10, SpaceShip.getShip_image_height()/10,this);
			for (AlienShip Alien : AlienClubLevel_3) {
				g.drawImage(Alien.getShip_image(), Alien.getxCord(), Alien.getyCord(),
						Alien.getShip_image_width()/20, Alien.getShip_image_height()/20,this);
			}
			for (int i = 0 ; i < AlienClubLevel_3.size(); i++) {
				if(Hit_Check(AlienClubLevel_3.get(i))) {
					AlienClubLevel_3.get(i).TakingDamage();
					if(AlienClubLevel_3.get(i).gethP() <= 0) {
						score += AlienClubLevel_3.get(i).getAlienBounty();
						try {
							AlienDestroyed = new musicStuff(alienShipDestroyed_sound,0.1);
							AlienDestroyed.playSound();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							e1.printStackTrace();
						}
						AlienClubLevel_3.get(i).setShip_image_height(0);
						AlienClubLevel_3.get(i).setShip_image_width(0);
						AlienClubLevel_3.remove(AlienClubLevel_3.get(i));
					}
				}
			}
		}
		for (int i = 0 ; i < BulletFire.size() ; i ++) {
			if(BulletFire.get(i).getY() <= 0 || BulletFire.get(i).getX() <= 0 || BulletFire.get(i).getX() >= 1000) {
				try {
					BulletFire.remove(BulletFire.get(i));
				} catch (Exception e) {
				}
			}
		}
		g.setColor(Color.blue);
		for (Fire fire : BulletFire) {
			g.drawImage(rotate(fire_image,fire.getfireAngle()),(int)fire.getX(),(int)fire.getY(), fire_image.getWidth()/5, fire_image.getHeight()/5,this);
			
		}
	}
	@Override
	public void repaint() {
		super.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for (Fire fire : BulletFire) {
			if(fire.getfireAngle() == 0) {
				fire.setY(fire.getY()-(fire.getIncreaser()+fire.getIncreaser()*Math.sin(Math.toRadians(fire.getfireAngle()))));
				fire.setX(fire.getX()-(fire.getIncreaser()-fire.getIncreaser()*Math.cos(Math.toRadians(fire.getfireAngle()))));
			}
			else if(fire.getfireAngle() == 30) {
				fire.setY(fire.getY()-(fire.getIncreaser()+fire.getIncreaser()*-Math.sin(Math.toRadians(fire.getfireAngle())*0.7)));
				fire.setX(fire.getX()+(fire.getIncreaser()-fire.getIncreaser()*Math.cos(Math.toRadians(fire.getfireAngle())*1.6)));
			}
			else if(fire.getfireAngle() == 60) {
				fire.setY(fire.getY()-(fire.getIncreaser()+fire.getIncreaser()*-Math.sin(Math.toRadians(fire.getfireAngle()*0.5))));
				fire.setX(fire.getX()+(fire.getIncreaser()-fire.getIncreaser()*Math.cos(Math.toRadians(fire.getfireAngle())*1.37)));
			}
			else if(fire.getfireAngle() == -30) {
				fire.setY(fire.getY()-(fire.getIncreaser()+fire.getIncreaser()*Math.sin(Math.toRadians(fire.getfireAngle())*0.7)));
				fire.setX(fire.getX()-(fire.getIncreaser()-fire.getIncreaser()*Math.cos(Math.toRadians(fire.getfireAngle())*1.6)));
			}

			else {
				fire.setY(fire.getY()-(fire.getIncreaser()+fire.getIncreaser()*Math.sin(Math.toRadians(fire.getfireAngle())*0.5)));
				fire.setX(fire.getX()-(fire.getIncreaser()-fire.getIncreaser()*Math.cos(Math.toRadians(fire.getfireAngle())*1.37)));
			}
		}
		if (CurrentLevel == 1) {
			for (int i = 0 ; i < AlienClubLevel_1.size(); i++) {
				AlienClubLevel_1.get(i).setyCord(AlienClubLevel_1.get(i).getyCord() + AlienClubLevel_1.get(i).getAlien_y_increaser());
				if(AlienClubLevel_1.get(i).getyCord() >= 760) {
					AlienClubLevel_1.remove(AlienClubLevel_1.get(i));
					musicStuff musicObject;
					try {
						musicObject = new musicStuff(remainingShield_sound,0.1);
						musicObject.playSound();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					remainingShield--;
					if (remainingShield == 0){
						CurrentLevel = 4;
					}
				}
			}
		}
		else if (CurrentLevel == 2) {
			for (int i = 0 ; i < AlienClubLevel_2.size(); i++) {
				AlienClubLevel_2.get(i).setAlien_y_increaser(3);
				AlienClubLevel_2.get(i).setyCord(AlienClubLevel_2.get(i).getyCord() + AlienClubLevel_2.get(i).getAlien_y_increaser());
				if(AlienClubLevel_2.get(i).getyCord() >= 760) {
					AlienClubLevel_2.remove(AlienClubLevel_2.get(i));
					musicStuff musicObject;
					try {
						musicObject = new musicStuff(remainingShield_sound,0.1);
						musicObject.playSound();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					remainingShield--;
					if (remainingShield == 0){
						CurrentLevel = 4;
					}
				}
			}
		}
		else if (CurrentLevel == 3) {
			for (int i = 0 ; i < AlienClubLevel_3.size(); i++) {
				AlienClubLevel_3.get(i).setAlien_y_increaser(4);
				AlienClubLevel_3.get(i).setyCord(AlienClubLevel_3.get(i).getyCord() + AlienClubLevel_3.get(i).getAlien_y_increaser());
				if(AlienClubLevel_3.get(i).getyCord() >= 760) {
					AlienClubLevel_3.remove(AlienClubLevel_3.get(i));
					musicStuff musicObject;
					try {
						musicObject = new musicStuff(remainingShield_sound,0.1);
						musicObject.playSound();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					remainingShield--;
					if (remainingShield == 0){
						CurrentLevel = 4;
					}
				}
			}
		}
		repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		
		if(!isPaused) {
			if(c == KeyEvent.VK_LEFT) {
				if(SpaceShip.getDirection() != "LEFT") {
					SpaceShip.setSpaceShip_x_increaser(10);
					if (SpaceShip.getxCord() <= 0) {
						SpaceShip.setxCord(0);
					}
					else {
						SpaceShip.setxCord(SpaceShip.getxCord()-SpaceShip.getSpaceShip_x_increaser());	
					}	
				}
				else {
					if (SpaceShip.getxCord() <= 0) {
						SpaceShip.setSpaceShip_x_increaser(10);
						SpaceShip.setxCord(0);
					}
					else {
						SpaceShip.setSpaceShip_x_increaser(SpaceShip.getSpaceShip_x_increaser()+1);
						SpaceShip.setxCord(SpaceShip.getxCord()-SpaceShip.getSpaceShip_x_increaser());	
					}	
				}
				SpaceShip.setDirection("LEFT");
			}
			else if (c == KeyEvent.VK_RIGHT) {
	
				if(SpaceShip.getDirection() != "RIGHT") {
					SpaceShip.setSpaceShip_x_increaser(10);
					if (SpaceShip.getxCord() >= 905) {
						SpaceShip.setxCord(905);
					}
					else {
						SpaceShip.setxCord(SpaceShip.getxCord()+SpaceShip.getSpaceShip_x_increaser());	
					}	
				}
				else {
					if (SpaceShip.getxCord() >= 905) {
						SpaceShip.setSpaceShip_x_increaser(10);
						SpaceShip.setxCord(905);
					}
					else {
						SpaceShip.setSpaceShip_x_increaser(SpaceShip.getSpaceShip_x_increaser()+1);
						SpaceShip.setxCord(SpaceShip.getxCord()+SpaceShip.getSpaceShip_x_increaser());	
					}	
				}
				SpaceShip.setDirection("RIGHT");
			}
			else if(c == KeyEvent.VK_DOWN) {
	          if(SpaceShip.getyCord() >= 670) {
	        	  SpaceShip.setyCord(670);
	          }
	          else {
	        	  SpaceShip.setyCord(SpaceShip.getyCord()+SpaceShip.getSpaceShip_y_increaser());
	          }
			}
	
			else if(c == KeyEvent.VK_UP) {
	          if(SpaceShip.getyCord() <= 500) {
	        	  SpaceShip.setyCord(500);
	          }
	          else {
	        	  SpaceShip.setyCord(SpaceShip.getyCord()-SpaceShip.getSpaceShip_y_increaser());
	          }
			}
	        else if (c == KeyEvent.VK_SPACE) {
				if(SpaceShip.getspaceAngel()==0) {
					BulletFire.add(new Fire(SpaceShip.getxCord()+30, SpaceShip.getyCord()-20, SpaceShip.getspaceAngel()));
				}
	
				else if(SpaceShip.getspaceAngel() == 30) {
					BulletFire.add(new Fire(SpaceShip.getxCord()+30+Math.cos(Math.toRadians(SpaceShip.getspaceAngel()))*25,
							SpaceShip.getyCord()-20+Math.sin(Math.toRadians(SpaceShip.getspaceAngel()))*8, SpaceShip.getspaceAngel()));
	
				}
				else if(SpaceShip.getspaceAngel() == 60) {
					BulletFire.add(new Fire(SpaceShip.getxCord()+30+Math.cos(Math.toRadians(SpaceShip.getspaceAngel()))*77,
							SpaceShip.getyCord()-20+Math.sin(Math.toRadians(SpaceShip.getspaceAngel()))*20, SpaceShip.getspaceAngel()));
				}
				else if(SpaceShip.getspaceAngel() == -30) {
					BulletFire.add(new Fire(SpaceShip.getxCord()+30-Math.cos(Math.toRadians(SpaceShip.getspaceAngel()))*25,
							SpaceShip.getyCord()-20-Math.sin(Math.toRadians(SpaceShip.getspaceAngel()))*8, SpaceShip.getspaceAngel()));
				}
				else if(SpaceShip.getspaceAngel() == -60) {
					BulletFire.add(new Fire(SpaceShip.getxCord()+30-Math.cos(Math.toRadians(SpaceShip.getspaceAngel()))*77,
							SpaceShip.getyCord()-20-Math.sin(Math.toRadians(SpaceShip.getspaceAngel()))*20, SpaceShip.getspaceAngel()));
				}
	
				musicStuff musicObject;
				try {
					musicObject = new musicStuff(laser_fire_sound,0.1);
					musicObject.playSound();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (c == KeyEvent.VK_D) {
				if(SpaceShip.getspaceAngel() >= 60) {
					SpaceShip.setspaceAngel(60);
				}
				else {
					SpaceShip.setspaceAngel(SpaceShip.getspaceAngel()+30);
				}
			}
			else if (c == KeyEvent.VK_A) {
				if(SpaceShip.getspaceAngel() <= -60) {
					SpaceShip.setspaceAngel(-60);
				}
				else {
					SpaceShip.setspaceAngel(SpaceShip.getspaceAngel()-30);
				}
			}	
			else if (c == KeyEvent.VK_ESCAPE) {
				PauseScreen.pauseScreen();
				isPaused = true;
				timer.stop();		
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
