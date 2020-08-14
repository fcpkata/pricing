package com.kata.priceservice.exception;

public class InvalidVolumeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidVolumeException() {
		super("Valid volume parameter required");
	}

}
