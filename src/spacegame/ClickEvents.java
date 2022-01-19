package spacegame;
//import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import spacegame.GameScreen.ResumeMenuClose;

public class ClickEvents extends GameMenu{
	
	
	public ClickEvents() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	
	private static String filepath = "sound_files/mouseClick.wav";
	
	private static final long serialVersionUID = 1L;

	public static class TitleScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			musicStuff musicObject;
			try {
				musicObject = new musicStuff(filepath,0.1);
				musicObject.playSound();

			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
				try {
					GameScreen.gameScreen();
					main_screen_sound.stopSound();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static class StartMenu implements ActionListener{
		public void actionPerformed(ActionEvent event) {

			musicStuff musicObject;
			try {
				musicObject = new musicStuff(filepath,0.1);
				musicObject.playSound();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}

			titleNamePanel.setVisible(true);
			startMenuButtonPanel.setVisible(true);
			mainTextPanel.setVisible(false);
			mainTextPanel.setEnabled(false);
		}
	}
	
	public static class StartMenuMultiplayer implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			musicStuff musicObject;
			try {
				musicObject = new musicStuff(filepath,0.1);
				musicObject.playSound();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
			
			MultiplayerScreen.multiplayerScreen();
		}
	}

	public static class StartMenuScore implements ActionListener{
		public void actionPerformed(ActionEvent event) {

			musicStuff musicObject;
			try {
				musicObject = new musicStuff(filepath,0.1);
				musicObject.playSound();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}

			ScoresScreen.scoresScreen();
		}
	}

	public static class StartMenuCredits implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			musicStuff musicObject;
			try {
				musicObject = new musicStuff(filepath,0.1);
				musicObject.playSound();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CreditsScreen.creditsScreen();
		}
	}
	
	public static class StartMenuQuit implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			musicStuff musicObject;
			try {
				musicObject = new musicStuff(filepath,0.1);
				musicObject.playSound();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			main_screen_sound.stopSound();
			System.exit(0);
		}
	}
	
	public static class GameScreenResume implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			musicStuff musicObject;
			try {
				musicObject = new musicStuff(filepath,0.1);
				musicObject.playSound();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
			GamePanel.setPaused(false);
			PauseScreen.close();
			GameScreen.game_panel.timer.start();
		}
	}
	
	public static class GameBackMenu implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			musicStuff musicObject;
			try {
				musicObject = new musicStuff(filepath,0.1);
				musicObject.playSound();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
			window.setVisible(true);
			GamePanel.setPaused(false);
			ResumeMenuClose.rmClose();
			PauseScreen.close();
			startMenuButtonPanel.setVisible(true);
			titleNamePanel.setVisible(true);
			con.setVisible(true);
		}
	}
}
