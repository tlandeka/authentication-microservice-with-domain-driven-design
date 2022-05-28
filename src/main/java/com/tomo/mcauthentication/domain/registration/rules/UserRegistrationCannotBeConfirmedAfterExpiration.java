package com.tomo.mcauthentication.domain.registration.rules;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;

import java.time.LocalDateTime;

public class UserRegistrationCannotBeConfirmedAfterExpiration implements BusinessRule {

    public static final int CONFIRMATION_LINK_DURATION = 8;

    LocalDateTime registerDate;

    public UserRegistrationCannotBeConfirmedAfterExpiration(LocalDateTime aRegisterDate) {
        this.registerDate = aRegisterDate;
    }

    @Override
    public Boolean isRuleComplied() {
        return LocalDateTime.now().isBefore(this.registerDate.plusDays(CONFIRMATION_LINK_DURATION));
    }

    @Override
    public String message() {
        return null;
    }
}
