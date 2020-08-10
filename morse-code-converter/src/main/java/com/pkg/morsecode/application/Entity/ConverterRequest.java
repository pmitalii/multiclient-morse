package com.pkg.morsecode.application.Entity;

import javax.validation.constraints.Pattern;

import com.pkg.morsecode.application.common.ApplicationConstant;

public class ConverterRequest {

	@Pattern(regexp = ApplicationConstant.ENGLISH_NUMBER_REGEX, message = ApplicationConstant.ENGLISH_FIELD_ERROR)
	private String englishMessage;

	@Pattern(regexp = ApplicationConstant.MORSE_REGEX, message = ApplicationConstant.MORSE_FIELD_ERROR)
	private String morseCode;

	public String getEnglishMessage() {
		return englishMessage;
	}

	public void setEnglishMessage(String englishMessage) {
		this.englishMessage = englishMessage;
	}

	public String getMorseCode() {
		return morseCode;
	}

	public void setMorseCode(String morseCode) {
		this.morseCode = morseCode;
	}


}
