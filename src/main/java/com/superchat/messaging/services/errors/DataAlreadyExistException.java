package com.superchat.messaging.services.errors;

import org.springframework.http.HttpStatus;

public class DataAlreadyExistException extends DataRuntimeException {
	private static final long serialVersionUID = 1L;

	public DataAlreadyExistException(String message) {
		super(HttpStatus.ALREADY_REPORTED, message);
	}

	public DataAlreadyExistException(HttpStatus status, String message) {
		super(HttpStatus.ALREADY_REPORTED, message);
	}
}
