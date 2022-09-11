package com.customer.rewards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> exception(EntityNotFoundException exception) {
		HttpStatus status = HttpStatus.NOT_FOUND; // 404
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(status, exception.getMessage()), status);
	}
	
	@ExceptionHandler(value = RecordExistsException.class)
	public ResponseEntity<ErrorResponse> exception(RecordExistsException exception) {
		HttpStatus status = HttpStatus.CONFLICT; // 409
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(status, exception.getMessage()), status);
	}
}