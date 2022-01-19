package spacegame;
import java.awt.Color;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import spacegame.ClickEvents.StartMenu;


public class MultiplayerScreen extends GameMenu{
	static JButton menuButton;
	static StartMenu sMenu = new StartMenu();

	public MultiplayerScreen() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		super();
	}
	private static final long serialVersionUID = 1L;

	public static void multiplayerScreen() {
		titleNamePanel.setVisible(false);
		startMenuButtonPanel.setVisible(false);
		
		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100, 100, 1000, 1000);
		mainTextPanel.setBackground(Color.black);
		mainTextPanel.setLayout(null);
		con.add(mainTextPanel);

		menuButton = new JButton("BACK");
		menuButton.setBounds(0,460, 100, 40);
		menuButton.setBackground(Color.black);
		menuButton.setForeground(Color.white);
		menuButton.setBorder(BorderFactory.createLineBorder(Color.black));
		menuButton.setFont(normalFont);
		menuButton.addActionListener(sMenu);

		mainTextArea = new JTextArea("Not Avaiable");
		mainTextArea.setBounds(200, 100, 200, 40);
		mainTextArea.setBackground(Color.black);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setEnabled(false);

		mainTextPanel.add(mainTextArea);
		mainTextPanel.add(menuButton);
	}
}