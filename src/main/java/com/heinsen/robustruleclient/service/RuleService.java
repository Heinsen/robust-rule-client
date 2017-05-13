package com.heinsen.robustruleclient.service;

import com.heinsen.robustruleclient.domain.Rule;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * Created by JCH on 13/05/2017.
 */
@Service
public class RuleService {

    private final RestTemplate restTemplate;

    public RuleService(RestTemplate rest) {
        this.restTemplate = rest;
    }

    public List<Rule> ruleList() {
        URI uri = URI.create("http://localhost:8080/rules");

        //Calling unreliable service
        ResponseEntity<List<Rule>> ruleResponse = this.restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Rule>>() {
        });

        return ruleResponse.getBody();
    }
}