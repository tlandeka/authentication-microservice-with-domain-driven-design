package com.tomo.mcauthentication.domain.registration.rules;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;

public class UserRegistrationCannotBeConfirmedMoreThanOnce implements BusinessRule {

    UserRegistrationStatus status;

    public UserRegistrationCannotBeConfirmedMoreThanOnce(UserRegistrationStatus aStatus) {
        this.status = aStatus;
    }

    @Override
    public Boolean isRuleComplied() {
        return !this.status.equals(UserRegistrationStatus.Confirmed);
    }

    @Override
    public String message() {
        return "User Registration cannot be confirmed more than once";
    }
}
