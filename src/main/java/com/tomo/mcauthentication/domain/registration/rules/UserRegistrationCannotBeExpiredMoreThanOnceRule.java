package com.tomo.mcauthentication.domain.registration.rules;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;

public class UserRegistrationCannotBeExpiredMoreThanOnceRule implements BusinessRule {
    @Override public Boolean isBroken() {
        return null;
    }

    @Override public String message() {
        return null;
    }
}
