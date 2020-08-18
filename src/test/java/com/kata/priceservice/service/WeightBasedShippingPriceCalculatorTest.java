package com.kata.priceservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.kata.priceservice.model.ShippingPriceRequest;
@RunWith(MockitoJUnitRunner.class)
public class WeightBasedShippingPriceCalculatorTest {

	private WeightBasedShippingPriceCalculator calculator;

	@Mock
	private ShippingPriceCalculator mockShippingPriceCalculator;

	@Before
	public void setUp() {
		calculator = new WeightBasedShippingPriceCalculator();
	}

	@Test
	public void shouldReturn0WhenWeightIsGivenAs0() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(0).build();

		assertThat(calculator.getPrice(request)).isEqualTo(0);
	}
	
	@Test
	public void shouldReturn0WhenWeightGivenAs100() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(100).build();

		assertThat(calculator.getPrice(request)).isEqualTo(0);
	}
	
	@Test
	public void shouldReturn10WhenWeightGivenAs250() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(250).build();

		assertThat(calculator.getPrice(request)).isEqualTo(10);
	}
	
	@Test
	public void shouldReturn25WhenWeightGivenAs251() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(251).build();

		assertThat(calculator.getPrice(request)).isEqualTo(25);
	}
	
	@Test
	public void shouldReturn25WhenWeightGivenAs500() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(500).build();

		assertThat(calculator.getPrice(request)).isEqualTo(25);
	}
	
	@Test
	public void shouldReturn50WhenWeightGivenAs501() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(501).build();

		assertThat(calculator.getPrice(request)).isEqualTo(50);
	}
	
	
	@Test
	public void shouldReturn50WhenWeightGivenAs2000() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(2000).build();

		assertThat(calculator.getPrice(request)).isEqualTo(50);
	}
	
	@Test
	public void shouldReturn50WhenWeightGivenAs2001() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(2001).build();

		assertThat(calculator.getPrice(request)).isEqualTo(75);
	}
	
	@Test
	public void shouldReturn50WhenWeightGivenAs10000() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(10000).build();

		assertThat(calculator.getPrice(request)).isEqualTo(75);
	}
	
	@Test
	public void shouldReturn50WhenWeightGivenAs10001() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(10001).build();

		assertThat(calculator.getPrice(request)).isEqualTo(150);
	}
	
	@Test
	public void shouldReturn50WhenWeightGivenAs10001AndNextCalculatorIsNull() {

		ShippingPriceRequest request = ShippingPriceRequest.builder().weight(10001).build();
		calculator.setNextCalculator(null);

		assertThat(calculator.getPrice(request)).isEqualTo(150);
	}
	

}
