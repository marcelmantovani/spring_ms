package com.in28minutes.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfigurations() {
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
		
	}
	
	
	@GetMapping("/fault-tolerant-example")
	@HystrixCommand(fallbackMethod="fallbackRetrieveConfigurations")
	public LimitConfiguration retrieveConfigurations() {
		throw new RuntimeException("Not available"); // just to mimic an exception due to resource not available
		
	}
	
	public LimitConfiguration fallbackRetrieveConfigurations() {
		return new LimitConfiguration(999, 9); // the default
		
	}
}
