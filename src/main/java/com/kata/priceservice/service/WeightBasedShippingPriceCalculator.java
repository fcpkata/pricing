package com.kata.priceservice.service;

import org.springframework.stereotype.Component;

import com.kata.priceservice.model.ShippingPriceRequest;

@Component
public class WeightBasedShippingPriceCalculator implements ShippingPriceCalculator{

	private ShippingPriceCalculator nextCalculator;

	@Override
	public void setNextCalculator(ShippingPriceCalculator calculator) {
		this.nextCalculator = calculator;
	}

	@Override
	public int getPrice(ShippingPriceRequest request) {

		double weight = request.getWeight();
		int price = 0;
		if (weight >= 0 && weight <= 100)
			return price;
		else if (weight > 100 && weight <= 250)
			price = 10;
		else if (weight > 250 && weight <= 500)
			price = 25;
		else if (weight > 500 && weight <= 2000)
			price = 50;
		else if (weight > 2000 && weight <= 10000)
			price = 75;
		else
			price = 150;

		if (nextCalculator != null) {
			return price + nextCalculator.getPrice(request);
		}
		return price;
	}

}
