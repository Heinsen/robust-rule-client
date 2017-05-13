package com.heinsen.robustruleclient;

import com.heinsen.robustruleclient.domain.Rule;
import com.heinsen.robustruleclient.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@EnableCircuitBreaker
@RestController
@SpringBootApplication
public class RobustRuleClientApplication {

	@Autowired
	private RuleService ruleService;

	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder.build();
	}

	@RequestMapping("/rules")
	public List<Rule> currentRules() {
		return ruleService.ruleList();
	}

	public static void main(String[] args) {
		SpringApplication.run(RobustRuleClientApplication.class, args);
	}
}
