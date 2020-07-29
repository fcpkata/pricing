package com.kata.priceservice.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import model.City;

@Component
public class MockShippingChargesService implements ShippingChargesService {
	
	private Map<String, Integer> shippingCharges;
	
	public MockShippingChargesService() {
		shippingCharges = new HashMap<String, Integer>();
		prepareEntryForSmallDistanceCities(City.Chennai, City.Bengaluru);
		prepareEntryForSmallDistanceCities(City.Chennai, City.Hyderabad);
		prepareEntryForSmallDistanceCities(City.Hyderabad, City.Kolkata);
		prepareEntryForSmallDistanceCities(City.Mumbai, City.Delhi);
		prepareEntryForSmallDistanceCities(City.Kolkata, City.Delhi);
		
		prepareEntryForMediumDistanceCities(City.Chennai, City.Mumbai);
		prepareEntryForMediumDistanceCities(City.Chennai, City.Kolkata);
		prepareEntryForMediumDistanceCities(City.Bengaluru, City.Mumbai);
		prepareEntryForMediumDistanceCities(City.Bengaluru, City.Kolkata);
		prepareEntryForMediumDistanceCities(City.Bengaluru, City.Delhi);
		prepareEntryForMediumDistanceCities(City.Mumbai, City.Kolkata);
		prepareEntryForMediumDistanceCities(City.Hyderabad, City.Delhi);
		prepareEntryForMediumDistanceCities(City.Hyderabad, City.Mumbai);
		
		prepareEntryForLongDistanceCities(City.Chennai, City.Delhi);
	}
	
	private void prepareEntryForLongDistanceCities(City from, City to) {
		prpareEntryFor(from, to, 150);
	}
	
	private void prepareEntryForMediumDistanceCities(City from, City to) {
		prpareEntryFor(from, to, 100);
	}

	private void prepareEntryForSmallDistanceCities(City from, City to) {
		prpareEntryFor(from, to, 50);
	}

	private void prpareEntryFor(City from, City to, Integer price) {
		shippingCharges.put(from+"-"+to, price);
	}
	
	@Override
	public Map<String, Integer> fetchDomesticShippingCharges() {
		return Collections.unmodifiableMap(shippingCharges);
	}

	@Override
	public int fetchIntenationalShippingCharges() {
		return 250;
	}

	@Override
	public int fetchLocalShippingCharges() {
		return 0;
	}

}