package com.kata.priceservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kata.priceservice.exception.CityNotFoundException;
import com.kata.priceservice.model.ShippingPriceRequest;

@Component
public class DomesticShippingPriceCalculator implements ShippingPriceCalculator {

	private ShippingPriceCalculator nextCalculator;
	private ShippingChargesService shippingChargesService;
	
	@Autowired
	public DomesticShippingPriceCalculator(ShippingChargesService shippingChargesService) {
		this.shippingChargesService = shippingChargesService;
	}

	@Override
	public void setNextCalculator(ShippingPriceCalculator calculator) {
		this.nextCalculator = calculator;
	}

	@Override
	public int getPrice(ShippingPriceRequest request) {
		 int price = Optional.ofNullable(shippingChargesService.fetchDomesticShippingCharges().get(request.getFromCity()+"-"+request.getToCity()))
				.orElse(shippingChargesService.fetchDomesticShippingCharges().get(request.getToCity()+"-"+request.getFromCity()));
		 
		 return Optional.ofNullable(price).orElseThrow(() -> new CityNotFoundException());
	}
}
