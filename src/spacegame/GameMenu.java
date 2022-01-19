package spacegame;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import spacegame.ClickEvents.*;

public class GameMenu extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static JFrame window;
	static Container con;
	static JPanel titleNamePanel, startMenuButtonPanel,startMenuButtonPanel2, mainTextPanel;
	JLabel titleNameLabel;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 80);
	static Font normalFont = new Font("Times New Roman",Font.PLAIN, 30);
	JButton startButton, multiplayerButton, scoreButton,creditsButton, quitButton;
	static JTextArea mainTextArea;
	private static String filepath = "sound_files/mainScreen.wav";
	protected static musicStuff main_screen_sound;

	static int highestScore = 0;
	private static String highestScoreString = "";
	public GameMenu() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		main_screen_sound = new musicStuff(filepath,0.01);
		main_screen_sound.playSound();
		try {
			File myObj = new File("highestScore.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				highestScoreString += data;
			}
			myReader.close();
			highestScore = Integer.parseInt(highestScoreString);

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


		TitleScreenHandler tsHandler = new TitleScreenHandler();

		StartMenuQuit smQuit = new StartMenuQuit();
		StartMenuMultiplayer mScreen = new StartMenuMultiplayer();
		StartMenuScore smScore = new StartMenuScore();
		StartMenuCredits smCredits = new StartMenuCredits();

		window = new JFrame("Star Field");
		window.setSize(800,700);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setVisible(true);
		window.setSize(800,701);
		window.setLocationRelativeTo(null);
		con = window.getContentPane();

		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 600, 150);
		titleNamePanel.setBackground(Color.black);
		titleNameLabel = new JLabel("Star Field");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);

		startMenuButtonPanel = new JPanel();
		startMenuButtonPanel.setBounds(300, 300, 207, 300);
		startMenuButtonPanel.setBackground(Color.black);

		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setBorder(BorderFactory.createLineBorder(Color.black));
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);

		multiplayerButton = new JButton("MULTIPLAYER");
		multiplayerButton.setBackground(Color.black);
		multiplayerButton.setForeground(Color.white);
		multiplayerButton.setBorder(BorderFactory.createLineBorder(Color.black));
		multiplayerButton.setFont(normalFont);
		multiplayerButton.addActionListener(mScreen);

		scoreButton = new JButton("SCORES");
		scoreButton.setBackground(Color.black);
		scoreButton.setForeground(Color.white);
		scoreButton.setBorder(BorderFactory.createLineBorder(Color.black));
		scoreButton.setFont(normalFont);
		scoreButton.addActionListener(smScore);

		creditsButton = new JButton("CREDITS");
		creditsButton.setBackground(Color.black);
		creditsButton.setForeground(Color.white);
		creditsButton.setBorder(BorderFactory.createLineBorder(Color.black));
		creditsButton.setFont(normalFont);
		creditsButton.addActionListener(smCredits);

		quitButton = new JButton("QUIT");
		quitButton.setBackground(Color.black);
		quitButton.setForeground(Color.white);
		quitButton.setBorder(BorderFactory.createLineBorder(Color.black));
		quitButton.setFont(normalFont);
		quitButton.addActionListener(smQuit);

		titleNamePanel.add(titleNameLabel);
		startMenuButtonPanel.add(startButton);
		startMenuButtonPanel.add(multiplayerButton);
		startMenuButtonPanel.add(scoreButton);
		startMenuButtonPanel.add(creditsButton);
		startMenuButtonPanel.add(quitButton);

		con.add(titleNamePanel);
		con.add(startMenuButtonPanel);
	}

	public static void windowControler(boolean x) {
			window.setVisible(x);
	}

	public static int getHighestScore() {
		return highestScore;
	}
	public static String getHighestScoreString(){
		return highestScoreString;
	}
	public static void setHighestScore(int highestScore2) {
		highestScoreString = "";
		highestScore = highestScore2;
		highestScoreString += highestScore;
	}
}


