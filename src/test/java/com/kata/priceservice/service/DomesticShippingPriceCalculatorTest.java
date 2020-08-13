package com.kata.priceservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.kata.priceservice.model.City;
import com.kata.priceservice.model.ShippingPriceRequest;

@RunWith(MockitoJUnitRunner.class)
public class DomesticShippingPriceCalculatorTest {

	ShippingPriceCalculator calculator = new DomesticShippingPriceCalculator(new MockShippingChargesService());
	
	@Test
	public void findsTheShippingPriceBetweenShortDistanceCities() throws Exception {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
				.fromCity(City.CHENNAI)
				.toCity(City.HYDERABAD)
				.build();
		
		int shippingPrice = calculator.getPrice(request);
		assertThat(shippingPrice).isEqualTo(50);
	}
	
	@Test
	public void findsTheShippingPriceBetweenMediumDistanceCities() throws Exception {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
				.fromCity(City.MUMBAI)
				.toCity(City.KOLKATA)
				.build();
		
		int shippingPrice = calculator.getPrice(request);
		
		assertThat(shippingPrice).isEqualTo(100);
	}
	
	@Test
	public void findsTheShippingPriceBetweenLongDistanceCities() throws Exception {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
				.fromCity(City.CHENNAI)
				.toCity(City.DELHI)
				.build();
		
		int shippingPrice = calculator.getPrice(request);
		
		assertThat(shippingPrice).isEqualTo(150);
	}
	
	@Test
	public void findsTheShippingPriceBetweenShortDistanceCities_whenFlipped() throws Exception {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
				.fromCity(City.HYDERABAD)
				.toCity(City.CHENNAI)
				.build();
		
		int shippingPrice = calculator.getPrice(request);
		
		assertThat(shippingPrice).isEqualTo(50);
	}
}
