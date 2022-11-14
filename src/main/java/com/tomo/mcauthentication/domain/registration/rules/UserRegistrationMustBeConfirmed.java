package com.tomo.mcauthentication.domain.registration.rules;

import com.tomo.ddd.domain.BusinessRule;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;

public class UserRegistrationMustBeConfirmed implements BusinessRule {

    private UserRegistrationStatus userRegistrationStatus;

    public UserRegistrationMustBeConfirmed(UserRegistrationStatus anUserRegistrationStatus) {
        this.userRegistrationStatus = anUserRegistrationStatus;
    }

    @Override
    public Boolean isRuleComplied() {
        return userRegistrationStatus.equals(UserRegistrationStatus.Confirmed);
    }

    @Override
    public String message() {
        return "User registration is not confirmed yet.";
    }
}
