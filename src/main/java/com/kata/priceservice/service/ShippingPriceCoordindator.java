package com.kata.priceservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kata.priceservice.model.ShippingPriceRequest;

@Service
public class ShippingPriceCoordindator {
	private ShippingPriceCalculator calculator;
	
	@Autowired
	public ShippingPriceCoordindator(@Qualifier("locationBasedShippingPriceCalculator") ShippingPriceCalculator locationBasedShippingCoordinator) {
		this.calculator = locationBasedShippingCoordinator;
	}
	
	public int getPrice(ShippingPriceRequest request) {
		return calculator.getPrice(request);
	}
}
