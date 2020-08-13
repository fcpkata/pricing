package com.kata.priceservice.service;

import org.springframework.stereotype.Component;

import com.kata.priceservice.model.ShippingPriceRequest;

@Component
public class InternationalShippingPriceCalculator implements ShippingPriceCalculator {
	
	private ShippingChargesService shippingChargesService;
	private ShippingPriceCalculator nextCalculator;

	public InternationalShippingPriceCalculator(ShippingChargesService shippingChargesService) {
		this.shippingChargesService = shippingChargesService;
	}

	@Override
	public void setNextCalculator(ShippingPriceCalculator calculator) {
		this.nextCalculator = calculator;
	}

	@Override
	public int getPrice(ShippingPriceRequest request) {
		if(request.getFromCity().isInternational() || request.getToCity().isInternational()) {
			return shippingChargesService.fetchIntenationalShippingCharges();
		}
		
		return nextCalculator.getPrice(request);
	}
}
