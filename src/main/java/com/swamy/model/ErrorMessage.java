package com.swamy.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {

	private String message;
	private String date;
	private String module;
	private HttpStatus status;
	private Integer statusCode;
}
