package com.kata.priceservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.kata.priceservice.model.City;
import com.kata.priceservice.model.ShippingPriceRequest;

public class LocalShippingPriceCalculatorTest {
	
	@Test
	public void findsTheShippingPriceBetweenSameCity() {
		ShippingPriceCalculator calculator = new LocalShippingPriceCalculator();
		ShippingPriceRequest request = ShippingPriceRequest.builder()
				.fromCity(City.CHENNAI)
				.toCity(City.CHENNAI)
				.build();
		
		int shippingPrice = calculator.getPrice(request);
		
		assertThat(shippingPrice).isEqualTo(0);
	}
}
