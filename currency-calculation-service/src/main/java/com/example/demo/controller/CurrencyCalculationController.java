package com.example.demo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.facade.CurrencyExchangeProxy;
import com.example.demo.model.Currencycalculation;
import com.example.demo.model.GetUserData;


@RestController
public class CurrencyCalculationController {

	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy ;
	
	@GetMapping("/currency-Calculation-feign/from/{from}/to/{to}/quantity/{quantity}")
	public Currencycalculation calculatedAmountfeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
	
		Currencycalculation currencycalculation= currencyExchangeProxy.getExchangeValue(from,to);
		return new  Currencycalculation(currencycalculation.getId(),currencycalculation.getFrom(),currencycalculation.getTo(),
				currencycalculation.getConversionMultiple(),quantity,quantity.multiply(currencycalculation.getConversionMultiple()),currencycalculation.getPort());
		
	}
	
	//for dynamic Currency Conversion
	
	@GetMapping("/dynamicConversion")
	public String dynamicConversion(@RequestBody GetUserData getdata) throws IOException, InterruptedException {
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.apilayer.com/exchangerates_data/convert?to="+getdata.getTo()+"&from="+getdata.getFrom()+"&amount="+getdata.getAmount()))
				.header("apikey", "JcjZXqMJQDAidYcr7CH5PIyGK51vPiq9")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
		
	}
	
	
}
