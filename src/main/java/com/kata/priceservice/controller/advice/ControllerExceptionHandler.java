package com.kata.priceservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kata.priceservice.exception.CityNotFoundException;
import com.kata.priceservice.exception.InvalidVolumeException;
import com.kata.priceservice.exception.InvalidWeightException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { CityNotFoundException.class })
	public ResponseEntity<String> handleException(CityNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { InvalidVolumeException.class })
	public ResponseEntity<String> handleVolumeException(InvalidVolumeException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { InvalidWeightException.class })
	public ResponseEntity<String> handleWeightException(InvalidWeightException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
