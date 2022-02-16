package com.tomo.mcauthentication.domain.user_registrations.rules;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;

public class UserCannotBeCreatedWhenRegistrationIsNotConfirmedRule implements BusinessRule {
    @Override public Boolean isBroken() {
        return null;
    }

    @Override public String message() {
        return null;
    }
}
