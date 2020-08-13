package com.kata.priceservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.kata.priceservice.model.City;
import com.kata.priceservice.model.ShippingPriceRequest;

public class InternationalShippingPriceCalculatorTest {
	@Test
	public void findsTheShippingPriceForIntenationalCities() throws Exception {
		ShippingPriceCalculator calculator = new InternationalShippingPriceCalculator(new MockShippingChargesService());
		
		ShippingPriceRequest request = ShippingPriceRequest.builder()
				.fromCity(City.CHENNAI)
				.toCity(City.SINGAPORE)
				.build();
		
		int shippingPrice = calculator.getPrice(request);
		
		assertThat(shippingPrice).isEqualTo(250);
	}
}
