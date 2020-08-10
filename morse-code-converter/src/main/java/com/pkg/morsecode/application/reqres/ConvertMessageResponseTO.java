package com.pkg.morsecode.application.reqres;

import com.pkg.morsecode.application.exception.ErrorResponse;

public class ConvertMessageResponseTO extends ErrorResponse {
	
	private Boolean morse;
	private String message;

	public Boolean getMorse() {
		return morse;
	}
	public String getMessage() {
		return message;
	}
	public void setMorse(Boolean morse) {
		this.morse = morse;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
