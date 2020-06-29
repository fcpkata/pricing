package library;
import java.util.Currency;

import org.junit.Test;

public class CurrencySpec {

	@Test
	public void fetchesAllListOfCurrencies() {
		Currency.getAvailableCurrencies().forEach(System.out::println);
	}
	
	@Test
	public void identifiesCurrencyBasedOnCurrencyCode() throws Exception {
		System.out.println(Currency.getInstance("USD"));
	}

}
