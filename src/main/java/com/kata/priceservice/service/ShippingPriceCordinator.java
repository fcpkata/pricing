package com.kata.priceservice.service;

import org.springframework.stereotype.Component;

import model.City;

@Component
public class ShippingPriceCordinator {
	
	private ShippingChargesService shippingChargesService;
	
	private ShippingChargeCalculator shippingChargeCalculator;

	public ShippingPriceCordinator(ShippingChargesService shippingChargesService) {
		this.shippingChargesService = shippingChargesService;
		this.shippingChargeCalculator = new LocalShippingChargeCalculator(
												new InternationalShippingChargeCalculator(
														new DomesticShippingChargeCalculator()));
	}

	public int findShippingPriceBetween(City fromCity, City toCity) {		
		return shippingChargeCalculator.fetchShippingCharges(fromCity, toCity, shippingChargesService);
	}

}
