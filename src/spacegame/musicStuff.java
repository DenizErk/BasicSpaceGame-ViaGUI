package spacegame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class musicStuff {
	private File musicPath;
	private AudioInputStream audioInput;
	private Clip clip;
	private FloatControl gainControl;
	private double soundVolume;
	
	public musicStuff(String filepath,double soundVolume) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.musicPath = new File (filepath);
		audioInput = AudioSystem.getAudioInputStream(musicPath);
		clip = AudioSystem.getClip();
		this.soundVolume = soundVolume;
	}
	
	void playSound() {

		try {
			if (musicPath.exists()) {
				clip.open(audioInput);
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				float dB = (float) (Math.log(soundVolume) / Math.log(10.0) * 20.0);
				gainControl.setValue(dB);
				clip.start();
			}
			else {
				System.out.println("File does not exist!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	void stopSound() {
		try {
			if (musicPath.exists()) {
				clip.stop();
			}
			else {
				System.out.println("File does not exist!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
