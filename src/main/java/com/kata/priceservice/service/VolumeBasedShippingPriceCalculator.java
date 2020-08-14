package com.kata.priceservice.service;

import org.springframework.stereotype.Component;

import com.kata.priceservice.model.ShippingPriceRequest;

@Component
public class VolumeBasedShippingPriceCalculator implements ShippingPriceCalculator{
	private ShippingPriceCalculator nextCalculator;

	@Override
	public void setNextCalculator(ShippingPriceCalculator calculator) {
		this.nextCalculator = calculator;
		
	}

	@Override
	public int getPrice(ShippingPriceRequest request) {
		int price = calculatePrice(request);
		price += calculateNextPrice(request);
		return price;
	}

	private int calculateNextPrice(ShippingPriceRequest request) {
		if (nextCalculator != null) {
			return nextCalculator.getPrice(request);
		}
		return 0;
	}

	private int calculatePrice(ShippingPriceRequest request) {
		int price = 0;
		if (request.getVolume() < 50) {
			price = 0;
		} else if (request.getVolume() < 1000) {
			price = 10;
		} else if (request.getVolume() < 200000) {
			price = 25;
		} else if (request.getVolume() < 500000) {
			price = 50;
		} else if (request.getVolume() < 1000000) {
			price = 75;
		} else {
			price = 150;
		}
		return price;
	}

}
