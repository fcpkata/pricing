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
		int shippingPrice = calculator.findShippingPriceBetween(City.CHENNAI, City.CHENNAI);
		assertThat(shippingPrice).isEqualTo(0);
	}
	
	@Test
	public void findsTheShippingPriceBetweenShortDistanceCities() throws Exception {
		int shippingPrice = calculator.findShippingPriceBetween(City.CHENNAI, City.HYDERABAD);
		assertThat(shippingPrice).isEqualTo(50);
	}
	
	@Test
	public void findsTheShippingPriceBetweenMediumDistanceCities() throws Exception {
		int shippingPrice = calculator.findShippingPriceBetween(City.MUMBAI, City.KOLKATA);
		assertThat(shippingPrice).isEqualTo(100);
	}
	
	@Test
	public void findsTheShippingPriceBetweenLongDistanceCities() throws Exception {
		int shippingPrice = calculator.findShippingPriceBetween(City.CHENNAI, City.DELHI);
		assertThat(shippingPrice).isEqualTo(150);
	}
	
	@Test
	public void findsTheShippingPriceForIntenationalCities() throws Exception {
		int shippingPrice = calculator.findShippingPriceBetween(City.CHENNAI, City.SINGAPORE);
		assertThat(shippingPrice).isEqualTo(250);
	}
	
	@Test
	public void findsTheShippingPriceBetweenShortDistanceCities_whenFlipped() throws Exception {
		int shippingPrice = calculator.findShippingPriceBetween(City.HYDERABAD, City.CHENNAI);
		assertThat(shippingPrice).isEqualTo(50);
	}

}
