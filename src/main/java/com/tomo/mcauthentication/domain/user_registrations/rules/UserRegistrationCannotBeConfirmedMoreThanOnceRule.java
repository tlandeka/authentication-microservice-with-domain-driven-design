package com.tomo.mcauthentication.domain.user_registrations.rules;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationStatus;

public class UserRegistrationCannotBeConfirmedMoreThanOnceRule implements BusinessRule {

    UserRegistrationStatus status;

    public UserRegistrationCannotBeConfirmedMoreThanOnceRule(UserRegistrationStatus aStatus) {
        this.status = aStatus;
    }

    @Override
    public Boolean isBroken() {
        return this.status.equals(UserRegistrationStatus.Confirmed);
    }

    @Override
    public String message() {
        return "User Registration cannot be confirmed more than once";
    }
}
