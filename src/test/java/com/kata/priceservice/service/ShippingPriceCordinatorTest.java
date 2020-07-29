package com.kata.priceservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import model.City;

@RunWith(MockitoJUnitRunner.class)
public class ShippingPriceCordinatorTest {

	ShippingPriceCordinator calculator = new ShippingPriceCordinator(new MockShippingChargesService());
	
	@Test
	public void findsTheShippingPriceBetweenSameCity() {
		int shippingPrice = calculator.findShippingPriceBetween(City.Chennai, City.Chennai);
		assertThat(shippingPrice).isEqualTo(0);
	}
	
	@Test
	public void findsTheShippingPriceBetweenShortDistanceCities() throws Exception {
		int shippingPrice = calculator.findShippingPriceBetween(City.Chennai, City.Hyderabad);
		assertThat(shippingPrice).isEqualTo(50);
	}
	
	@Test
	public void findsTheShippingPriceBetweenMediumDistanceCities() throws Exception {
		int shippingPrice = calculator.findShippingPriceBetween(City.Mumbai, City.Kolkata);
		assertThat(shippingPrice).isEqualTo(100);
	}
	
	@Test
	public void findsTheShippingPriceBetweenLongDistanceCities() throws Exception {
		int shippingPrice = calculator.findShippingPriceBetween(City.Chennai, City.Delhi);
		assertThat(shippingPrice).isEqualTo(150);
	}
	
	@Test
	public void findsTheShippingPriceForIntenationalCities() throws Exception {
		int shippingPrice = calculator.findShippingPriceBetween(City.Chennai, City.Singapore);
		assertThat(shippingPrice).isEqualTo(250);
	}
	
	@Test
	public void findsTheShippingPriceBetweenShortDistanceCities_whenFlipped() throws Exception {
		int shippingPrice = calculator.findShippingPriceBetween(City.Hyderabad, City.Chennai);
		assertThat(shippingPrice).isEqualTo(50);
	}

}
