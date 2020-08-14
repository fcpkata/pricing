package com.kata.priceservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kata.priceservice.exception.CityNotFoundException;
import com.kata.priceservice.exception.InvalidCityException;
import com.kata.priceservice.model.City;
import com.kata.priceservice.model.Price;
import com.kata.priceservice.model.ShippingPriceRequest;
import com.kata.priceservice.service.LocationBasedShippingPriceCalculator;

@RestController
@RequestMapping("/pricingservice/api/v1")
public class PricingController {

	private LocationBasedShippingPriceCalculator shippingPriceCordinator;

	@Autowired
	public PricingController(LocationBasedShippingPriceCalculator shippingPriceCordinator) {
		this.shippingPriceCordinator = shippingPriceCordinator;
	}

	@GetMapping(path = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Price> getPrice() {
		Price defaultPrice = Price.builder().value(100d).build();
		ResponseEntity<Price> response = new ResponseEntity<Price>(defaultPrice, HttpStatus.OK);
		return response;
	}

	@GetMapping(path = "/shippingprice")
	public ResponseEntity<Price> getShippingChargesFor(@RequestParam(name= "fromCity", required = true) String fromCity,
			@RequestParam(name= "toCity", required = true) String toCity) {
		
		validate(fromCity, toCity);
		ShippingPriceRequest request = buildShippingPriceRequest(fromCity, toCity);
		int shippingPrice = shippingPriceCordinator.getPrice(request);
		ResponseEntity<Price> response = buildResponse(shippingPrice);
		
		return response;
	}

	private ResponseEntity<Price> buildResponse(int shippingPrice) {
		Price price = Price.builder()
								   .value(shippingPrice)
								   .build();
		return new ResponseEntity<Price>(price, HttpStatus.OK);
	}

	private ShippingPriceRequest buildShippingPriceRequest(String fromCity, String toCity) {
		ShippingPriceRequest request = ShippingPriceRequest.builder()
														   .fromCity(City.valueOf(fromCity.toUpperCase()))
														   .toCity(City.valueOf(toCity.toUpperCase()))
														   .build();
		return request;
	}

	public void validate(String fromCity, String toCity) {
		this.validate(fromCity);
		this.validate(toCity);
	}
	
	private void validate(String city) {
		try {
			if(city == null || city.trim() == null) {
				throw new NullPointerException();
			}
			City.valueOf(city.toUpperCase());			
		} catch (IllegalArgumentException ex) {
			throw new CityNotFoundException(city);
		} catch (Exception ex) {
			throw new InvalidCityException(city);
		}
	}

}
