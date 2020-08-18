package com.kata.priceservice.exception;

public class InvalidWeightException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	

	public InvalidWeightException(double weight) {
		super("Given weight "+ weight+" is invalid");
	}
	
}
