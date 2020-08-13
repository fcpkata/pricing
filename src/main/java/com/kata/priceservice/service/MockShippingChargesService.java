package com.kata.priceservice.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kata.priceservice.model.City;

@Component
public class MockShippingChargesService implements ShippingChargesService {
	
	private Map<String, Integer> shippingCharges;
	
	public MockShippingChargesService() {
		shippingCharges = new HashMap<String, Integer>();
		prepareEntryForSmallDistanceCities(City.CHENNAI, City.BENGALURU);
		prepareEntryForSmallDistanceCities(City.CHENNAI, City.HYDERABAD);
		prepareEntryForSmallDistanceCities(City.HYDERABAD, City.KOLKATA);
		prepareEntryForSmallDistanceCities(City.MUMBAI, City.DELHI);
		prepareEntryForSmallDistanceCities(City.KOLKATA, City.DELHI);
		
		prepareEntryForMediumDistanceCities(City.CHENNAI, City.MUMBAI);
		prepareEntryForMediumDistanceCities(City.CHENNAI, City.KOLKATA);
		prepareEntryForMediumDistanceCities(City.BENGALURU, City.MUMBAI);
		prepareEntryForMediumDistanceCities(City.BENGALURU, City.KOLKATA);
		prepareEntryForMediumDistanceCities(City.BENGALURU, City.DELHI);
		prepareEntryForMediumDistanceCities(City.MUMBAI, City.KOLKATA);
		prepareEntryForMediumDistanceCities(City.HYDERABAD, City.DELHI);
		prepareEntryForMediumDistanceCities(City.HYDERABAD, City.MUMBAI);
		
		prepareEntryForLongDistanceCities(City.CHENNAI, City.DELHI);
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