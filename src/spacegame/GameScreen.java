package spacegame;
//import java.awt.Color;
//
//import spacegame.GameLayout;
//import spacegame.GamePanel;
//
//import java.awt.Font;
//import java.awt.FontMetrics;
//import java.awt.Graphics;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.Paint;
//import java.awt.Rectangle;
//import java.awt.Shape;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//
//import javax.imageio.ImageIO;
//import javax.imageio.stream.FileImageInputStream;
//import java.awt.image.BufferedImage;
//import java.awt.image.ImageObserver;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import spacegame.ClickEvents.*;
//import java.text.AttributedCharacterIterator;
//import spacegame.PauseScreen.gameScreenResume;
  
public class GameScreen extends GameMenu {
	static GameLayout game_interface;
	static GameLayout game_end;
	static GamePanel game_panel;
	static JButton quitButton, restartButton, resumeButton, menuButton;
	static JLabel scoreArea;
	protected String currentScore ;
	static StartMenuQuit smQuit;
	static TitleScreenHandler tsHandler;
	static EndMenuClose emClose;
	static GameScreenResume gsResume;
	static GameBackMenu gbMenu;

	public GameScreen() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	public static void gameScreen() throws FileNotFoundException, IOException{
		smQuit = new StartMenuQuit();
		tsHandler = new TitleScreenHandler();
		emClose = new EndMenuClose();
		gsResume = new GameScreenResume();
		gbMenu = new GameBackMenu();
		
		titleNamePanel.setVisible(false);
		startMenuButtonPanel.setVisible(false);
		con.setVisible(false);
		
		game_interface = new GameLayout("Star Field");
		
		game_interface.setResizable(false);
		game_interface.setFocusable(false);
		
		game_interface.setSize(1000,800);
		
		game_interface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_interface.setLocationRelativeTo(null);
		
		game_panel = new GamePanel();
		
		game_panel.requestFocus();		
		game_panel.addKeyListener(game_panel);		
		game_panel.setFocusable(true);		
		game_panel.setFocusTraversalKeysEnabled(false);
		
		game_interface.add(game_panel);
		game_interface.setVisible(true);

		quitButton = new JButton("QUIT");
		quitButton.setBackground(Color.black);
		quitButton.setForeground(Color.white);
		quitButton.setBorder(BorderFactory.createLineBorder(Color.black));
		quitButton.setFont(normalFont);
		quitButton.addActionListener(smQuit);

		restartButton = new JButton("RESTART");
		restartButton.setBackground(Color.black);
		restartButton.setForeground(Color.white);
		restartButton.setBorder(BorderFactory.createLineBorder(Color.black));
		restartButton.setFont(normalFont);
		restartButton.addActionListener(tsHandler);

		restartButton.addActionListener(emClose);
		
		window.setVisible(false);

		resumeButton = new JButton("RESUME");
		resumeButton.setBackground(Color.black);
		resumeButton.setForeground(Color.white);
		resumeButton.setBorder(BorderFactory.createLineBorder(Color.black));
		resumeButton.setFont(normalFont);
		resumeButton.addActionListener(gsResume);
		
		menuButton = new JButton("MAIN MENU");
		menuButton.setBackground(Color.black);
		menuButton.setForeground(Color.white);
		menuButton.setBorder(BorderFactory.createLineBorder(Color.black));
		menuButton.setFont(normalFont);
		menuButton.addActionListener(gbMenu);
	}
	public static class ResumeMenuClose{
		public static void rmClose() {
			game_interface.setVisible(false);
			game_interface.setEnabled(false);
		}
	}

	public static class EndMenuClose implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			game_end.setVisible(false);
			game_end.setEnabled(false);
		}
	}
}
