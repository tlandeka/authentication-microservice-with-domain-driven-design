package com.tomo.mcauthentication.domain.registration.rules;

import com.tomo.ddd.domain.BusinessRule;
import com.tomo.mcauthentication.domain.registration.UserRegistration;

public class PasswordRecoveryCodeShouldNotExpired implements BusinessRule {

    private UserRegistration userRegistration;

    public PasswordRecoveryCodeShouldNotExpired(UserRegistration aUserRegistration) {
        this.userRegistration = aUserRegistration;
    }

    @Override
    public Boolean isRuleComplied() {
        return userRegistration.isRecoveryCodeUnexpired();
    }

    @Override
    public String message() {
        return "User recovery code is not expired yet. You can't get new one after the current code expire.";
    }
}

