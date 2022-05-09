package com.superchat.messaging.services.errors;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class DataRuntimeException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private HttpStatus status;

	public DataRuntimeException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	
}
