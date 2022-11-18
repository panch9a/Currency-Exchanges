package com.example.currencyexchange.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.currencyexchange.model.ExchangeValue;
import com.example.currencyexchange.repository.ExchangeValueRepository;

@RestController
public class ExchangeValueController {
	
	@Autowired
	public Environment environment; 
	
	@Autowired 
	private ExchangeValueRepository reposiotry;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
		// new ExchangeValue(1000L,"USD","to",BigDecimal.valueOf(70));
		ExchangeValue exchangeValue=reposiotry.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
	

}
