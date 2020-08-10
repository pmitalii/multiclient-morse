package com.pkg.morsecode.application.starter;

import java.util.Scanner;

import com.pkg.morsecode.application.bo.MorseConverterBO;
import com.pkg.morsecode.application.bo.impl.MorseConverterBOImpl;
import com.pkg.morsecode.application.common.ApplicationConstant;

/**
 * @author Mitali 
 * 
 * This class is used to provide input 
 * to convert morse code to english and
 * vice a versa through console.
 *
 */
public class MorseCodeConsoleStarter {

	//add validations and make changes in this client as per the swing client 
	//on 22-july-2020

	//add sound into this application

	// modify this project to maven

	// convert to spring project and test with postman

	public static void main(String[] args) {

		MorseConverterBO obj1 = new MorseConverterBOImpl();

		System.out.println("Please Enter Your String To Convert : ");
		String message;
		Scanner sc = new Scanner(System.in);
		message = sc.nextLine();
		String result;
		//
		//		if(message.contains(".") || message.contains("-")) {
		//			obj1.toEnglishLanguage(message);
		//		}else {
		//			message = message.toLowerCase();
		//			obj1.toMorseCode(message);
		//		}

		if(message.contains(".") || message.contains("-")) {
			if(message.matches(ApplicationConstant.MORSE_REGEX)){
				result = obj1.toEnglishLanguage(message);
			}
			else {
				System.out.println(ApplicationConstant.MORSE_FIELD_ERROR);
				result = ApplicationConstant.MORSE_FIELD_ERROR;
			}
		} else {
			if(!message.matches(ApplicationConstant.MORSE_REGEX)) {
				message = message.toLowerCase();
				result = obj1.toMorseCode(message);
			} else {
				System.out.println(ApplicationConstant.ENGLISH_FIELD_ERROR);
				result = ApplicationConstant.ENGLISH_FIELD_ERROR;
			}
		}	
		sc.close();	
	}

}


