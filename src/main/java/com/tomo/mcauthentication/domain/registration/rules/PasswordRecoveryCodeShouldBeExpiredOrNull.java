package com.tomo.mcauthentication.domain.registration.rules;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;
import com.tomo.mcauthentication.domain.registration.UserRegistration;

public class PasswordRecoveryCodeShouldBeExpiredOrNull implements BusinessRule {

    private UserRegistration userRegistration;

    public PasswordRecoveryCodeShouldBeExpiredOrNull(UserRegistration aUserRegistration) {
        this.userRegistration = aUserRegistration;
    }

    @Override
    public Boolean isRuleComplied() {
        return userRegistration.isRecoveryCodeExpired() || userRegistration.getRecoveryCode() == null;
    }

    @Override
    public String message() {
        return "User recovery code is not expired yet. You can't get new one after the current code expire.";
    }
}

