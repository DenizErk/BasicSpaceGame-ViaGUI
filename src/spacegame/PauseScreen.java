package spacegame;

import java.awt.Color;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class PauseScreen extends GameScreen {
	public PauseScreen() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		super();
	}
	private static final long serialVersionUID = 1L;
	public static void pauseScreen() {

		game_end = new GameLayout("Star Field");
		
		game_end.setFocusable(false);
		game_end.setResizable(false);
		game_end.setSize(400,300);
		game_end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_end.setLayout(null);
		game_end.getContentPane().setBackground(Color.black);
		game_end.setVisible(true);
		game_end.setLocationRelativeTo(null);


		startMenuButtonPanel2 = new JPanel();
		startMenuButtonPanel2.setBounds(115, 150, 175, 100);
		startMenuButtonPanel2.setBackground(Color.black);
		startMenuButtonPanel2.add(resumeButton);
		startMenuButtonPanel2.add(menuButton);
		
		scoreArea = new JLabel();

		mainTextArea = new JTextArea("Game Paused \n"+ "Current Score: " + spacegame.GamePanel.getScore());
		mainTextArea.setBounds(80, 50, 400, 80);
		mainTextArea.setBackground(Color.black);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setEnabled(false);
		game_end.add(mainTextArea);

		
		game_end.add(scoreArea);
		game_end.add(startMenuButtonPanel2);

	}
	
    public static void close() {
    	game_end.setVisible(false);
    	game_end.setEnabled(false);
    }
}
