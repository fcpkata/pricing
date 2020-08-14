package com.kata.priceservice.exception;

public class CityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CityNotFoundException(String city) {
		super("City "+ city+" Not Found");
	}
	
	public CityNotFoundException() {
		super("City Not Found");
	}
}
