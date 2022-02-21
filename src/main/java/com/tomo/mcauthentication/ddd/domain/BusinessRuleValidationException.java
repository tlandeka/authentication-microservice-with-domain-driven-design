package com.tomo.mcauthentication.ddd.domain;

import lombok.Getter;

@Getter
public class BusinessRuleValidationException extends RuntimeException {

    private BusinessRule brokenRule;

    public BusinessRuleValidationException(BusinessRule brokenRule) {
        this.brokenRule = brokenRule;
    }
}
