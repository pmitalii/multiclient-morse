package com.pkg.morsecode.application.common;

public class ApplicationConstant {
	
	public static final String MORSE_REGEX = "[.-]{1,5}(?> [.-]{1,5})*(?>   [.-]{1,5}(?> [.-]{1,5})*)*";
	public static final String MORSE_FIELD_ERROR = "ERROR: Expected morse in %s field";
	public static final String ENGLISH_FIELD_ERROR = "ERROR: Expected alphabets in %s field";
	public static final String ALPHABET_REGEX = "^[A-Za-z]+$";
	public static final String ENGLISH_NUMBER_REGEX = "^[A-Za-z0-9]+$";

}
