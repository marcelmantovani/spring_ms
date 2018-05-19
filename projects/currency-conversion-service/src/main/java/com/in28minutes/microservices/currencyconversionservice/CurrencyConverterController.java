package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConverterController {
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/amount/{amount}")
	public CurrencyConverterBean convertCurrency(@PathVariable String from,  @PathVariable String to, @PathVariable BigDecimal amount) {
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConverterBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000//currency-exchange/from/{from}/to/{to}", CurrencyConverterBean.class, uriVariables);
		CurrencyConverterBean response = responseEntity.getBody();
		
		return new CurrencyConverterBean(response.getId(), from, to, response.getConversionMultiple(), 
				amount, amount.multiply(response.getConversionMultiple()), response.getPort());
	}
}
