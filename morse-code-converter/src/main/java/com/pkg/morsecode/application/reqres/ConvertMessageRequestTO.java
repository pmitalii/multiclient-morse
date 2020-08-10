package com.pkg.morsecode.application.reqres;

public class ConvertMessageRequestTO {
	
	private String englishMessage;
	
	private String morseCode;

	public String getEnglishMessage() {
		return englishMessage;
	}

	public String getMorseCode() {
		return morseCode;
	}

	public void setEnglishMessage(String englishMessage) {
		this.englishMessage = englishMessage;
	}

	public void setMorseCode(String morseCode) {
		this.morseCode = morseCode;
	}

}
