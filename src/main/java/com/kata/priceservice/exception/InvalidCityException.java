package com.kata.priceservice.exception;

public class InvalidCityException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidCityException(String city) {
		super("Invalid City "+ city);
	}
	
	public InvalidCityException() {
		super("Invalid City");
	}
}
