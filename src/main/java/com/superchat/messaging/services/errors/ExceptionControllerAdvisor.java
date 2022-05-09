package com.superchat.messaging.services.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ExceptionControllerAdvisor {
	@ExceptionHandler(DataRuntimeException.class)
	public ResponseEntity<CustomApiError> handleInternalExceptions(DataRuntimeException e) {
		
		log.error("Error during data processing: ", e);
		
		return new ResponseEntity<>(new CustomApiError(e.getStatus(), e.getMessage()), e.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomApiError> handleUnexpectedExceptions(Exception e) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = "An error occured during processing";
		log.error(message + ": ", e);
		
		return new ResponseEntity<>(new CustomApiError(status, message), status);
	}

}
