package com.pkg.morsecode.application.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Value;

import com.pkg.morsecode.application.bo.MorseConverterBO;
import com.pkg.morsecode.application.bo.impl.MorseConverterBOImpl;
import com.pkg.morsecode.application.common.ApplicationConstant;
import com.pkg.morsecode.application.player.MorseAudioPlayer;

/**
 * @author Mitali 
 * 
 * This class is used to provide input 
 * to convert morse code to english and
 * vice a versa through swing client.
 *
 */
public class ApplicationUILoader implements ActionListener {

	@Value("${enable.morsecode.application.sound}")
	private Boolean enableSound;

	private MorseConverterBO converterHelper;  
	private JLabel englishLabel, morseLable, headerText, errorString;
	private JTextField englishTextField, morseTextField;
	private JButton convertButton;
	MorseAudioPlayer morseAudioPlayer;

	/**
	 * Initializes applications Swing UI
	 * 
	 */
	public ApplicationUILoader() {
		converterHelper = new MorseConverterBOImpl();
		morseAudioPlayer = new MorseAudioPlayer();
		loadApplicationUI();
	}

	/**
	 * This method is to load the swing UI
	 * of this application
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void loadApplicationUI() {
		errorString = new JLabel("");
		errorString.setBounds(50,225,200,30);
		errorString.setForeground(Color.RED);


		JFrame f = new JFrame("MORSE");

		headerText = new JLabel("INTERNATIONAL MORSE CODE CONVERTER");
		headerText.setBounds(30, -20, 300, 90);

		englishLabel = new JLabel("English :");
		englishLabel.setBounds(50, 40, 100, 25);
		englishTextField = new JTextField();
		englishTextField.setBounds(50, 70, 200, 30);

		englishTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				morseTextField.setText("");
				convertButton.setLabel("Convert to Morse");
			}
		});

		morseLable = new JLabel("Morse :");
		morseLable.setBounds(50, 170, 100, 25);
		morseTextField = new JTextField();
		morseTextField.setBounds(50,200,200,30);

		morseTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				englishTextField.setText("");
				convertButton.setLabel("Convert to English");
			}
		});

		convertButton = new JButton("Convert");
		convertButton.setBounds(50,125,200,40);

		convertButton.addActionListener(this);

		f.add(headerText);
		f.add(englishLabel);
		f.add(morseLable);
		f.add(convertButton);
		f.add(englishTextField);
		f.add(morseTextField);
		f.add(errorString);

		f.setSize(320,300);

		f.setLayout(null);
		f.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		errorString.setText("");
		String englishString = englishTextField.getText();
		String morseCode = morseTextField.getText();

		if(e.getSource() == convertButton) {

			if(null != morseTextField && 
					(null != morseCode && !morseCode.isEmpty())) {
				if(morseCode.matches(ApplicationConstant.MORSE_REGEX)
						&& (morseCode.contains(".") || morseCode.contains("-"))) {
					String translate = converterHelper.toEnglishLanguage(morseCode);
					englishTextField.setText(translate);
				} else {
					errorString.setText(ApplicationConstant.MORSE_FIELD_ERROR);
					System.out.println(ApplicationConstant.MORSE_FIELD_ERROR);
				}
			} else {
				if(null != englishString) {
					if(!englishString.contains(".") 
							&& !englishString.contains("-") &&
							englishString.matches(ApplicationConstant.ENGLISH_NUMBER_REGEX)) {
						englishString = englishString.toLowerCase();
						String translate = converterHelper.toMorseCode(englishString);
						morseTextField.setText(translate);
						if(false) {
							morseAudioPlayer.playSound(translate);
							System.out.println("hello");
						}
					} else {
						errorString.setText(ApplicationConstant.ENGLISH_FIELD_ERROR);
						System.out.println(ApplicationConstant.ENGLISH_FIELD_ERROR);
					}
				}
			}
		}

	}


}
