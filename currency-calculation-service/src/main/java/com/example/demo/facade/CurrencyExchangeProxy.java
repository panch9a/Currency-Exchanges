package com.example.demo.facade;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Currencycalculation;

@FeignClient(name="currency-exchange-service", url="http://localhost:8000/")
public interface CurrencyExchangeProxy {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public Currencycalculation getExchangeValue(@PathVariable ("from") String from, @PathVariable ("to") String to);
	
}
