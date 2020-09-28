package com.pkg.morsecode.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pkg.morsecode.application.Entity.ConverterRequest;
import com.pkg.morsecode.application.Entity.ConverterResponse;
import com.pkg.morsecode.application.bo.MorseConverterBO;
import com.pkg.morsecode.application.common.ApplicationConstant;


@RestController
//@Controller
//@ResponseBody
public class MorseController {

	
	@Autowired
	MorseConverterBO morseConverterBO;


	@GetMapping(value = "/convert/{message}", produces = "application/json")
	public /* @ResponseBody */ String convertInput(@Valid @PathVariable(value = "message") String message) {
		String response = null;

		if(message.contains(".") || message.contains("-")) {
			if(message.matches(ApplicationConstant.MORSE_REGEX)){
				response = morseConverterBO.toEnglishLanguage(message);
			}
			else {
				System.out.println(ApplicationConstant.MORSE_FIELD_ERROR);
				response = ApplicationConstant.MORSE_FIELD_ERROR;
			}
		} else {
			if(!message.matches(ApplicationConstant.MORSE_REGEX)) {
				message = message.toLowerCase();
				response = morseConverterBO.toMorseCode(message);
			} else {
				System.out.println(ApplicationConstant.ENGLISH_FIELD_ERROR);
				response = ApplicationConstant.ENGLISH_FIELD_ERROR;
 			}
		}	
		return response;

	}

	@PostMapping(value = "/convert", consumes = "application/json", produces = "application/json")
	public /* @ResponseBody */ ResponseEntity<ConverterResponse> convertMessage(@Valid @RequestBody ConverterRequest converterRequest) {

		ConverterResponse res = new ConverterResponse(); 
		boolean isMorse = false;
		String response = null;
		if(converterRequest.getMorseCode() != null &&
				(converterRequest.getMorseCode().contains(".") 
						|| converterRequest.getMorseCode().contains("-"))) {
			response = morseConverterBO.toEnglishLanguage(converterRequest.getMorseCode());
		} else {
			if(converterRequest.getEnglishMessage() != null) {
				converterRequest.setEnglishMessage(converterRequest.getEnglishMessage().toLowerCase());
				response = morseConverterBO.toMorseCode(converterRequest.getEnglishMessage());
				isMorse = true;
			}
		}
		res.setMessage(response);
		res.setMorse(isMorse);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}