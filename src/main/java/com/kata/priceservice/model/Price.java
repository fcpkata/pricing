package com.kata.priceservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Price {
	double value;

	@Override
	public String toString() {
		return "Price =" + value;
	}
	
}
