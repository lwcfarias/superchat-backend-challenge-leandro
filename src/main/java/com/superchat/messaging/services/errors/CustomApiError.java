package com.superchat.messaging.services.errors;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class CustomApiError {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;

	private int code;
	private String status;
	private String message;

	public CustomApiError() {
		timestamp = new Date();
	}

	public CustomApiError(HttpStatus httpStatus, String message) {
		this();

		this.code = httpStatus.value();
		this.status = httpStatus.name();
		this.message = message;
	}
}
