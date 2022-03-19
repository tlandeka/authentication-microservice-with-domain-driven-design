package com.tomo.mcauthentication.domain.registration.rules;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;

import java.time.LocalDateTime;

public class UserRegistrationCannotBeConfirmedAfterExpirationRule implements BusinessRule {

    public static final int CONFIRMATION_LINK_DURATION = 8;

    LocalDateTime registerDate;

    public UserRegistrationCannotBeConfirmedAfterExpirationRule(LocalDateTime aRegisterDate) {
        this.registerDate = aRegisterDate;
    }

    @Override public Boolean isBroken() {
        return LocalDateTime.now().isAfter(this.registerDate.plusDays(CONFIRMATION_LINK_DURATION));
    }

    @Override public String message() {
        return null;
    }
}
