package com.kata.priceservice.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.kata.priceservice.controller.Price;
import com.kata.priceservice.controller.PricingController;

@RunWith(MockitoJUnitRunner.class)
public class PricingControllerSpec {
	
	public PricingController controller = new PricingController();

	@Test
	public void returnsBackDefaultPrice() throws Exception {
		ResponseEntity<Price> response = controller.getPrice();
		assertThat(response.getBody().toString()).isEqualTo("Price =100.0 INR");		
	}
}
