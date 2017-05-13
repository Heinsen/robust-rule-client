package com.heinsen.robustruleclient.service;

import com.heinsen.robustruleclient.domain.Rule;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JCH on 13/05/2017.
 */
@Service
public class RuleService {

    private final RestTemplate restTemplate;
    private final List<Rule> reliableRuleList;

    public RuleService(RestTemplate rest) {
        this.restTemplate = rest;
        reliableRuleList = new ArrayList<>();
        reliableRuleList.add(new Rule(1, "fallbackRuleOne"));
        reliableRuleList.add(new Rule(2, "fallbackRuleTwo"));
    }

    @HystrixCommand(fallbackMethod = "reliableRuleList")
    public List<Rule> ruleList() {
        URI uri = URI.create("http://localhost:8080/rules");

        //Calling unreliable service
        ResponseEntity<List<Rule>> ruleResponse = this.restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Rule>>() {
        });

        return ruleResponse.getBody();
    }

    public List<Rule> reliableRuleList() {
        return this.reliableRuleList;
    }
}