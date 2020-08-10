package com.pkg.morsecode.application.bo;

/**
 * @author Mitali
 *
 */
public interface MorseConverterBO {

//	String morseDashDotArray[] = { ".- ", "-... ", "-.-. ", "-.. ", ". ", "..-. ", "--. ", ".... ", ".. ", 
//			".--- ", "-.- ", ".-.. ", "-- ", "-. ", "--- ", ".---. ", "--.- ", ".-. ",
//			"... ", "- ", "..- ", "...- ", ".-- ", "-..- ", "-.-- ", "--.. "};
//
//	String[] morseAlphabetsArray = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
//			"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", 
//			"y", "z"};
//
//	String[] engDashDotArray = {"-...","-.-.","....","..-.",".---",".-..",".--.","--.-","...-","-..-",
//			"-.--","--..","-..","--.","...","-.-","---",".-.","..-",".--",".-","..","--","-.",".","-"," ","  "};
//
//	String[] englishAlphabetsArray = { "b","c","h","f","j","l","p","q","v","x","y","z","d","g","s","k",
//			"o","r","u","w","a","i","m","n","e","t",""," "};
	
	String morseDashDotArray[] = { ".---- ", "..--- ", "...-- ", "....- ", "..... ", "-.... ", "--... ", "---.. ", "----. ", "----- ", ".- ", "-... ", "-.-. ", "-.. ", ". ", "..-. ", "--. ", ".... ", ".. ", 
			".--- ", "-.- ", ".-.. ", "-- ", "-. ", "--- ", ".---. ", "--.- ", ".-. ",
			"... ", "- ", "..- ", "...- ", ".-- ", "-..- ", "-.-- ", "--.. "};
	
	String[] morseAlphabetsArray = {"1","2","3","4","5","6","7","8","9","0","a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
			"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", 
			"y", "z"};
	
	String[] engDashDotArray = {".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----",
            "-...", "-.-.", "....", "..-.", ".---", ".-..", ".--.", "--.-", "...-", "-..-", "-.--", "--..", "-..", "--.", "...", "-.-", "---", ".-.", 
            "..-", ".--", ".-", "..", "--", "-.", ".", "-"," ","  "};
	
	String[] englishAlphabetsArray = {"1","2","3","4","5","6","7","8","9","0", "b","c","h","f","j","l","p","q","v","x","y","z","d","g","s","k",
			"o","r","u","w","a","i","m","n","e","t",""," "};

	/**
	 * This method is to convert english
	 * word to morse code
	 * 
	 * @param message
	 * @return morse code message
	 */
	public String toMorseCode(String message);


	/**
	 * This method is to convert morse code
	 * into english words
	 * 
	 * @param message
	 * @return message in english 
	 */
	public String toEnglishLanguage(String message);


}
