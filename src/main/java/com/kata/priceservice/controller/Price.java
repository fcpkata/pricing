package com.kata.priceservice.controller;

import java.util.Currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Price {
	double value;
	Currency currency;
	
	@Override
	public String toString() {
		return "Price =" + value + " " + currency;
	}
	
}
