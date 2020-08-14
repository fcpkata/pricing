package com.kata.priceservice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kata.priceservice.model.ShippingPriceRequest;

@Component
public class LocationBasedShippingPriceCalculator implements ShippingPriceCalculator {
	
	private ShippingPriceCalculator calculator;
	private ShippingPriceCalculator nextCalculator;

	public LocationBasedShippingPriceCalculator(
			@Qualifier("localShippingPriceCalculator") ShippingPriceCalculator localShippingPriceCalculator,
			@Qualifier("internationalShippingPriceCalculator") ShippingPriceCalculator internationalShippingPriceCalculator,
			@Qualifier("domesticShippingPriceCalculator") ShippingPriceCalculator domesticShippingPriceCalculator
			) {
		localShippingPriceCalculator.setNextCalculator(internationalShippingPriceCalculator);
		internationalShippingPriceCalculator.setNextCalculator(domesticShippingPriceCalculator);
		
		this.calculator = localShippingPriceCalculator;
	}

	@Override
	public int getPrice(ShippingPriceRequest request) {		
		int shippingPrice = calculator.getPrice(request);
		if(nextCalculator != null) {
			return shippingPrice + nextCalculator.getPrice(request);
		}
		return shippingPrice;
	}

	@Override
	public void setNextCalculator(ShippingPriceCalculator calculator) {
		this.nextCalculator = calculator;
	}
}
