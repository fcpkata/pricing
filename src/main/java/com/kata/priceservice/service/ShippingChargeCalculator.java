package com.kata.priceservice.service;

import model.City;

public interface ShippingChargeCalculator {
	
	
	int fetchShippingCharges(City fromCity, City toCity, ShippingChargesService shippingCharges);

}
