package com.tomo.mcauthentication.ddd.domain;

public interface BusinessRule {
    Boolean isRuleComplied();

    String message();
}
