package com.kata.priceservice.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.kata.priceservice.model.City;
import com.kata.priceservice.model.ShippingPriceRequest;

@RunWith(MockitoJUnitRunner.class)
public class LocationBasedShippingPriceCalculatorTest {
	@Mock
	private ShippingPriceCalculator mockShippingPriceCalculator;
	
	@Mock
	private ShippingPriceCalculator nextShippingPriceCalculator;
	
	private ShippingPriceCalculator calculator;
	
	private ShippingPriceRequest request;
	
	@Before
	public void setup() {
		calculator = new LocationBasedShippingPriceCalculator(mockShippingPriceCalculator, 
				mockShippingPriceCalculator, 
				mockShippingPriceCalculator);
		
		request = ShippingPriceRequest.builder()
				.fromCity(City.CHENNAI)
				.toCity(City.HYDERABAD)
				.build();
	}
	
	@Test
	public void shouldReturnLocationShippingChargeOnlyWhenNextCalculatorIsNull() {
		calculator.setNextCalculator(null);
		Mockito.when(mockShippingPriceCalculator.getPrice(Mockito.any())).thenReturn(10);
		
		int price = calculator.getPrice(request);
		
		Assertions.assertThat(price).isEqualTo(10);
	}
	
	@Test
	public void shouldReturnLocationShippingChargePlusNextShippingCharge() {
		calculator.setNextCalculator(nextShippingPriceCalculator);
		
		Mockito.when(mockShippingPriceCalculator.getPrice(Mockito.any())).thenReturn(10);
		Mockito.when(nextShippingPriceCalculator.getPrice(Mockito.any())).thenReturn(20);
		
		int price = calculator.getPrice(request);
		
		Assertions.assertThat(price).isEqualTo(30);
	}
}
