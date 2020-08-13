package com.kata.priceservice.service;

import org.springframework.stereotype.Component;

import com.kata.priceservice.model.ShippingPriceRequest;

@Component
public class LocalShippingPriceCalculator implements ShippingPriceCalculator {
	
	private ShippingPriceCalculator nextCalculator;

	@Override
	public void setNextCalculator(ShippingPriceCalculator calculator) {
		this.nextCalculator = calculator;
	}

	@Override
	public int getPrice(ShippingPriceRequest request) {
		if(request.getFromCity().equals(request.getToCity())) {
			return 0;			
		}
		return nextCalculator.getPrice(request);
	}
}
