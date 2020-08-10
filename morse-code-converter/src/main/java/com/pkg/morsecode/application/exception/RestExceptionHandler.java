package com.pkg.morsecode.application.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(Exception.class) 
	protected ResponseEntity<Object> handleApplicationException(Exception ex, WebRequest request) { 
		ErrorResponse errorRes = new ErrorResponse(); 
		String message = ex.getMessage() != null ?
				ex.getMessage() : ex.getClass().getName(); 
		errorRes.setErrorMessage(message);
		return new ResponseEntity<>(errorRes, HttpStatus.INTERNAL_SERVER_ERROR); 
	}



	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid
	(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorRes = new ErrorResponse();

		List<String> details = new ArrayList<>();
		
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			details.add(String.format(error.getDefaultMessage(), error.getField()));
		}
		errorRes.setErrorMessage(details.toString());
		return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
	}


}
