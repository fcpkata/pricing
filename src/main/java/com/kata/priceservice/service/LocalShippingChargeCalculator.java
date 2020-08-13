package com.kata.priceservice.service;

import model.City;

public class LocalShippingChargeCalculator implements ShippingChargeCalculator {
	
	private ShippingChargeCalculator nextShippingChargeCalculator;
	
	public LocalShippingChargeCalculator(ShippingChargeCalculator shippingChargeCalculator) {
		this.nextShippingChargeCalculator = shippingChargeCalculator;
	}

	@Override
	public int fetchShippingCharges(City fromCity, City toCity, ShippingChargesService shippingCharges) {
		if(fromCity.equals(toCity)) {
			return 0;			
		}
		return nextShippingChargeCalculator.fetchShippingCharges(fromCity, toCity, shippingCharges);
	}

}
