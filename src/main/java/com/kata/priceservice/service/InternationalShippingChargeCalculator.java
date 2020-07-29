package com.kata.priceservice.service;

import model.City;

public class InternationalShippingChargeCalculator implements ShippingChargeCalculator {
	
	private ShippingChargeCalculator nextShippingChargeCalculator;

	public InternationalShippingChargeCalculator(ShippingChargeCalculator shippingChargeCalculator) {
		this.nextShippingChargeCalculator = shippingChargeCalculator;
	}

	@Override
	public int fetchShippingCharges(City fromCity, City toCity, ShippingChargesService shippingCharges) {
		
		if(fromCity.isInternational() || toCity.isInternational()) {
			return shippingCharges.fetchIntenationalShippingCharges();
		}
		
		return nextShippingChargeCalculator.fetchShippingCharges(fromCity, toCity, shippingCharges);
	}

}
