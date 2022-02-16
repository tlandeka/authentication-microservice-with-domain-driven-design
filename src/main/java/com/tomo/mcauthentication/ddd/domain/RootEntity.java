package com.tomo.mcauthentication.ddd.domain;

import com.tomo.mcauthentication.ddd.AssertionConcern;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class RootEntity extends AssertionConcern {

    protected void checkRule(BusinessRule rule) throws BusinessRuleValidationException {
        if (rule.isBroken()) {
            throw new BusinessRuleValidationException(rule);
        }
    }
}
