package com.kata.priceservice.service;

import com.kata.priceservice.model.ShippingPriceRequest;

public class DefaultShippingPriceCalculator implements ShippingPriceCalculator {

	@Override
	public void setNextCalculator(ShippingPriceCalculator calculator) {
	}

	@Override
	public int getPrice(ShippingPriceRequest request) {
		return 0;
	}
}
