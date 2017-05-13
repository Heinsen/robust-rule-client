package com.heinsen.robustruleclient.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by JCH on 13/05/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rule {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    private long id;
    private String rule;

    public Rule(long id, String rule) {
        this.id = id;
        this.rule = rule;
    }

    public Rule() {
    }

}