package com.kata.priceservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class VolumeBasedShippingPriceCalculatorTest {
	
	private VolumeBasedShippingPriceCalculator calculator;
	@Before
	public void setup() {
		calculator = new VolumeBasedShippingPriceCalculator();
	}
	
	@Test
	public void shouldReturn0_WhenVolumeIs49() {
		assertThat(calculator.getShippingPrice(49)).isEqualTo(0);
	}
	
	@Test
	public void shouldReturn10_WhenVolumeIs99() {
		assertThat(calculator.getShippingPrice(99)).isEqualTo(10);
	}

}
