package com.pkg.morsecode.application.player;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.springframework.stereotype.Component;

@Component
public class MorseAudioPlayer {

	// to store current position 
	Long currentFrame; 
	static Clip clip; 

	// current status of clip 
	String status; 

	static AudioInputStream audioInputStream; 
	static String filePath; 

	public void playSound(String message) {
		try { 
			audioInputStream =  
					AudioSystem.getAudioInputStream(new File("C:\\Users\\user\\Downloads\\dash.wav").getAbsoluteFile()); 

			// create clip reference 
			clip = AudioSystem.getClip(); 

			// open audioInputStream to the clip 
			clip.open(audioInputStream); 
			message.chars().forEach(aChar -> {
				System.out.println("printing chars  >>>   "+aChar);
				try {
					clip.stop();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					if(46 == aChar) {                //(46 = ".")
						clip.start();
						Thread.sleep(400);
					} else if(45 == aChar) {        //(45 = "-")
						clip.start();
						Thread.sleep(625);
					} else if(32 == aChar) {       //(32 = " ")
						clip.stop();
						Thread.sleep(900);
					}
				} catch (Exception e) {

				}
			});

		} catch (Exception e) {
			System.out.println("error in audio player");
		} finally {
			clip.stop();
			clip.close();
		}
	}

}
