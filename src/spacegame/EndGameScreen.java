package spacegame;
import java.awt.Color;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EndGameScreen extends GameScreen{
	
	
	public EndGameScreen() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		super();
	}

	private static final long serialVersionUID = 1L;

	public static void endGameScreen(String score) {
		game_interface.setVisible(false);


		game_end = new GameLayout("Star Field");


		game_end.setFocusable(false);
		game_end.setResizable(false);
		game_end.setSize(400,300);
		game_end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_end.setLayout(null);
		game_end.getContentPane().setBackground(Color.black);
		game_end.setVisible(true);
		game_end.setLocationRelativeTo(null);
		
		startMenuButtonPanel = new JPanel();
		startMenuButtonPanel.setBounds(115, 150, 150, 100);
		startMenuButtonPanel.setBackground(Color.black);
		startMenuButtonPanel.add(restartButton);
		startMenuButtonPanel.add(quitButton);

		scoreArea = new JLabel(score);

		mainTextArea = new JTextArea("Last Score : "+ score + "\nHighest Score : "+ GameMenu.getHighestScore() + "\nTime : "
		+ (int)GamePanel.getElapsedTimeSec());
		mainTextArea.setBounds(80, 30, 400, 120);
		mainTextArea.setBackground(Color.black);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setEnabled(false);
		game_end.add(mainTextArea);

		game_end.add(scoreArea);
		game_end.add(startMenuButtonPanel);

	}

}
