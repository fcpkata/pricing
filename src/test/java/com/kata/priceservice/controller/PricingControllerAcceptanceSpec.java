package com.kata.priceservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricingControllerAcceptanceSpec {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldFetchTheDefaultPrice_WhenCalledWithoutAnyInput() throws Exception {
		
		mockMvc.perform(get("/pricingservice/api/v1/price"))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.value").value(100));
		
	}
	
	@Test
	public void shouldFetchTheShippingCharges_WhenCalledWithTwoDifferentLocations() throws Exception {
		
		mockMvc.perform(get("/pricingservice/api/v1/shippingprice?fromCity=Chennai&toCity=Delhi&weight=10&volume=49"))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.value").value(150));
	}
	
	@Test
	public void shouldReturnNotFound_WhenCalledWithUnKnownLocations() throws Exception {
		
		mockMvc.perform(get("/pricingservice/api/v1/shippingprice?fromCity=Chennai&toCity=Dubai&weight=10&volume=0"))
		.andDo(print()).andExpect(status().isNotFound())
		.andExpect(jsonPath("$").value("City Dubai Not Found"));
	}
	
	@Test
	public void shouldReturnBadRequest_whenVolumeIsNegative() throws Exception {
		
		mockMvc.perform(get("/pricingservice/api/v1/shippingprice?fromCity=Chennai&toCity=Hyderabad&weight=10&volume=-1"))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$").value("Valid volume parameter required"));
	}
	
	@Test
	public void shouldFetchTheShippingCharges_WhenCalledWithTwoDifferentLocationsAndWeightAndVolume() throws Exception {
		
		mockMvc.perform(get("/pricingservice/api/v1/shippingprice?fromCity=Chennai&toCity=Delhi&weight=300&volume=500"))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.value").value(185));
	}
	
	@Test
	public void shouldThrow400BadRequestWhenCalledWithInvalidWeight() throws Exception {
		
		mockMvc.perform(get("/pricingservice/api/v1/shippingprice?fromCity=Chennai&toCity=Delhi&weight=-1&volume=-1"))
		.andDo(print()).andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$").value("Given weight -1.0 is invalid"));
	}

}
