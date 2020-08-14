package com.kata.priceservice.service;

import com.kata.priceservice.model.ShippingPriceRequest;

public interface ShippingPriceCalculator {
	public void setNextCalculator(ShippingPriceCalculator calculator);
	public int getPrice(ShippingPriceRequest request);
}
