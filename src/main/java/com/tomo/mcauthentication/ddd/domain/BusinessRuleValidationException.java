package com.tomo.mcauthentication.ddd.domain;

public class BusinessRuleValidationException extends RuntimeException {
    private BusinessRule brokenRule;

    public BusinessRuleValidationException(BusinessRule brokenRule) {
        this.brokenRule = brokenRule;
    }

}
