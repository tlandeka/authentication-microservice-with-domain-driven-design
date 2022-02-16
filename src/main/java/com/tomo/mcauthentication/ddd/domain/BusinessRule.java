package com.tomo.mcauthentication.ddd.domain;

public interface BusinessRule {
    Boolean isBroken();

    String message();
}
