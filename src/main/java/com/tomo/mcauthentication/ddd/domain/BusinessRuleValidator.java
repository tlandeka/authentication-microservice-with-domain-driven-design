package com.tomo.mcauthentication.ddd.domain;

import com.tomo.mcauthentication.ddd.AssertionConcern;

public abstract class BusinessRuleValidator extends AssertionConcern {
    protected void checkRule(BusinessRule rule) throws BusinessRuleValidationException {
        if (rule.isBroken()) {
            throw new BusinessRuleValidationException(rule);
        }
    }
}
