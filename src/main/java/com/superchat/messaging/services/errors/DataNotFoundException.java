package com.superchat.messaging.services.errors;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends DataRuntimeException {
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

	public DataNotFoundException(HttpStatus status, String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}
