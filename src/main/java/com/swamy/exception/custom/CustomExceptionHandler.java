package com.swamy.exception.custom;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.swamy.exception.CompanyNotFoundException;
import com.swamy.model.ErrorMessage;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<ErrorMessage>processCompanyNotFoundException(CompanyNotFoundException cnfe){
		
		ErrorMessage errorMessage = ErrorMessage.builder()
		.message(cnfe.getMessage())
		.date(new Date().toString())
		.module("COMPANY")
		.status(HttpStatus.NOT_FOUND)
		.statusCode(HttpStatus.NOT_FOUND.value())
		.build();
		
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}
}

