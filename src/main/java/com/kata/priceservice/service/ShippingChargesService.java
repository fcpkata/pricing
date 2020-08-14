package com.kata.priceservice.service;

import java.util.Map;

public interface ShippingChargesService {
	
	int fetchLocalShippingCharges();
	
	Map<String, Integer> fetchDomesticShippingCharges();
	
	int fetchIntenationalShippingCharges();

}
