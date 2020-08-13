package com.kata.priceservice.service;

public class VolumeBasedShippingPriceCalculator {

	public int getShippingPrice(int volume) {
		if (volume < 50) {
			return 0;
		} else if (volume < 100) {
			return 10;
		}
		return 0;
	}

}
