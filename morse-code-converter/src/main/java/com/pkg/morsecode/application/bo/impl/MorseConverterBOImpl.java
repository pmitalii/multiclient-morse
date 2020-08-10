package com.pkg.morsecode.application.bo.impl;

import org.springframework.stereotype.Service;

import com.pkg.morsecode.application.bo.MorseConverterBO;

/**
 * @author Mitali
 *
 * This class is used to convert morse code to english and
 * vice a versa through swing client.
 * 
 */
@Service(value = "morseConverterBOImpl")
public class MorseConverterBOImpl implements MorseConverterBO {

	/* (non-Javadoc)
	 * @see com.morsecodeconverterwithinterface.MorseConverterBO#toMcode(java.lang.String)
	 */
	@Override
	public String toMorseCode(String message) {

		for(int i=0; i < morseDashDotArray.length; i++) {
			message = message.replace(morseAlphabetsArray[i], morseDashDotArray[i]);
		}
		message = message.trim();
		System.out.println(message);
		return message;

	}

	/* (non-Javadoc)
	 * @see com.morsecodeconverterwithinterface.MorseConverterBO#toEng(java.lang.String)
	 */
	@Override
	public String toEnglishLanguage(String message) {

		for(int i=0; i < engDashDotArray.length; i++) {
			message = message.replace(engDashDotArray[i], englishAlphabetsArray[i]);
		}
		System.out.println(message);
		return message;
	}

}
