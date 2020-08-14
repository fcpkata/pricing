package com.kata.priceservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.kata.priceservice.model.ShippingPriceRequest;

@RunWith(MockitoJUnitRunner.class)
public class VolumeBasedShippingPriceCalculatorTest {
	
	private VolumeBasedShippingPriceCalculator calculator;
	@Mock
	private ShippingPriceCalculator mockShippingPriceCalculator;
	@Before
	public void setup() {
		calculator = new VolumeBasedShippingPriceCalculator();
	}
	
	@Test
	public void shouldReturn0_WhenVolumeIs49() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(49)
														   .build();
		assertThat(calculator.getPrice(request)).isEqualTo(0);
	}
	
	@Test
	public void shouldReturn10_WhenVolumeIs999() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(999)
														   .build();
		
		assertThat(calculator.getPrice(request)).isEqualTo(10);
	}
	
	@Test
	public void shouldReturn0_WhenVolumeIs49AndNextCalculatorIsNull() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(49)
														   .build();
		calculator.setNextCalculator(null);
		
		assertThat(calculator.getPrice(request)).isEqualTo(0);
	}
	
	@Test
	public void shouldReturn20_WhenVolumeIs49AndNextCalculatorIsPresent() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(49)
														   .build();
		calculator.setNextCalculator(mockShippingPriceCalculator);
		when(mockShippingPriceCalculator.getPrice(Mockito.any())).thenReturn(20);
		
		assertThat(calculator.getPrice(request)).isEqualTo(20);
	}
	
	@Test
	public void shouldReturn10_WhenVolumeIs50() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(50)
														   .build();
		assertThat(calculator.getPrice(request)).isEqualTo(10);
	}
	
	@Test
	public void shouldReturn25_WhenVolumeIs1000() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(1000)
														   .build();
		assertThat(calculator.getPrice(request)).isEqualTo(25);
	}
	
	@Test
	public void shouldReturn25_WhenVolumeIs199999() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(199999)
														   .build();
		assertThat(calculator.getPrice(request)).isEqualTo(25);
	}
	
	@Test
	public void shouldReturn50_WhenVolumeIs200000() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(200000)
														   .build();
		assertThat(calculator.getPrice(request)).isEqualTo(50);
	}
	
	@Test
	public void shouldReturn50_WhenVolumeIs499999() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(499999)
														   .build();
		assertThat(calculator.getPrice(request)).isEqualTo(50);
	}
	
	@Test
	public void shouldReturn75_WhenVolumeIs500000() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(500000)
														   .build();
		assertThat(calculator.getPrice(request)).isEqualTo(75);
	}
	
	@Test
	public void shouldReturn75_WhenVolumeIs999999() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(999999)
														   .build();
		assertThat(calculator.getPrice(request)).isEqualTo(75);
	}
	
	@Test
	public void shouldReturn150_WhenVolumeIs1000000() {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .volume(1000000)
														   .build();
		assertThat(calculator.getPrice(request)).isEqualTo(150);
	}
}
