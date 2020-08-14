package com.kata.priceservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.kata.priceservice.exception.CityNotFoundException;
import com.kata.priceservice.model.City;
import com.kata.priceservice.model.Price;
import com.kata.priceservice.model.ShippingPriceRequest;
import com.kata.priceservice.service.LocationBasedShippingPriceCalculator;

@RunWith(MockitoJUnitRunner.class)
public class PricingControllerSpec {
	
	private static final String TO_CITY = "Hyderabad";

	private static final String FROM_CITY = "Chennai";

	@Mock
	private LocationBasedShippingPriceCalculator mockShippingPriceCordinator;
	
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
		
		ShippingPriceRequest request = ShippingPriceRequest.builder()
															.fromCity(City.CHENNAI)
															.toCity(City.HYDERABAD)
															.build();
		
		mockTheServiceResponce(request);
		
		ResponseEntity<Price> response = controller.getShippingChargesFor(FROM_CITY, TO_CITY);
		assertThat(response.getBody()).isEqualTo(new Price(50));	
	}

	private void mockTheServiceResponce(ShippingPriceRequest request) {
		when(mockShippingPriceCordinator.getPrice(any())).thenReturn(50);
	}
	
	@Test(expected = CityNotFoundException.class)
	public void validatesTheCity() throws Exception {
		controller.validate(FROM_CITY,"Dubai");
	}
}

