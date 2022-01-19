package spacegame;
import java.awt.Color;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import spacegame.ClickEvents.StartMenu;

public class CreditsScreen extends GameMenu{
	static JButton menuButton;
	static StartMenu sMenu = new StartMenu();

	public CreditsScreen() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static void creditsScreen() {
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

		mainTextArea = new JTextArea("Designed and Created by \n" + "\n" +"      Deniz Erk Barut" + "\n" + "         Burak Barlas");
		mainTextArea.setBounds(140, 80, 400, 160);
		mainTextArea.setBackground(Color.black);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextArea.setEnabled(false);

		mainTextPanel.add(mainTextArea);
		mainTextPanel.add(menuButton);


	}
}