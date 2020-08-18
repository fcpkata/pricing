package com.kata.priceservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import com.kata.priceservice.model.ShippingPriceRequest;

@RunWith(Parameterized.class)
public class WeightBasedShippingPriceCalculatorTest {

	private WeightBasedShippingPriceCalculator calculator;

	@Mock
	private ShippingPriceCalculator mockShippingPriceCalculator;

	private ShippingPriceRequest shippingPriceRequest;

	private int expectedResult;

	@Before
	public void initialize() {
		calculator = new WeightBasedShippingPriceCalculator();
	}

	@Parameterized.Parameters
	public static Collection itemWeights() {
		return Arrays.asList(new Object[][] { { ShippingPriceRequest.builder().weight(0).build(), 0 },
				{ ShippingPriceRequest.builder().weight(100).build(), 0 },
				{ ShippingPriceRequest.builder().weight(250).build(), 10 },
				{ ShippingPriceRequest.builder().weight(251).build(), 25 },
				{ ShippingPriceRequest.builder().weight(500).build(), 25 },
				{ ShippingPriceRequest.builder().weight(501).build(), 50 },
				{ ShippingPriceRequest.builder().weight(2000).build(), 50 },
				{ ShippingPriceRequest.builder().weight(2001).build(), 75 },
				{ ShippingPriceRequest.builder().weight(10000).build(), 75 },
				{ ShippingPriceRequest.builder().weight(10001).build(), 150 },
				{ ShippingPriceRequest.builder().weight(10001).build(), 150 } });
	}

	public WeightBasedShippingPriceCalculatorTest(ShippingPriceRequest request, int expectedResult) {
		this.shippingPriceRequest = request;
		this.expectedResult = expectedResult;
	}

	@Test
	public void shouldReturnExpectedPriceForGivenWeight() {
		assertEquals(calculator.getPrice(shippingPriceRequest), expectedResult);
	}

}
