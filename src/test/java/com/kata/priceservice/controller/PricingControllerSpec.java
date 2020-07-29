package com.kata.priceservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.kata.priceservice.exception.CityNotFoundException;
import com.kata.priceservice.service.ShippingPriceCordinator;

import model.City;
import model.Price;

@RunWith(MockitoJUnitRunner.class)
public class PricingControllerSpec {
	
	@Mock
	private ShippingPriceCordinator mockShippingPriceCordinator;
	
	public PricingController controller;
	
	@Before
	public void setup() {
		controller = new PricingController(mockShippingPriceCordinator);
	}
	

	@Test
	public void returnsBackDefaultPrice() throws Exception {
		ResponseEntity<Price> response = controller.getPrice();
		assertThat(response.getBody().toString()).isEqualTo("Price =100.0");		
	}
	
	@Test
	public void returnsTheShippingPrice() throws Exception {
		
		mockTheServiceResponce();
		
		ResponseEntity<Price> response = controller.getShippingChargesFor("Chennai", "Hyderabad");
		assertThat(response.getBody()).isEqualTo(new Price(50));	
	}

	private void mockTheServiceResponce() {
		when(mockShippingPriceCordinator.findShippingPriceBetween(any(), any())).thenReturn(50);
	}
	
	@Test(expected = CityNotFoundException.class)
	public void validatesTheCity() throws Exception {
		controller.validate("Chennai","Dubai");
	}
}

