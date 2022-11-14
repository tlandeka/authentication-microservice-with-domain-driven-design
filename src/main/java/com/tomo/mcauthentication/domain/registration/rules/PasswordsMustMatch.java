package com.tomo.mcauthentication.domain.registration.rules;

import com.tomo.ddd.domain.BusinessRule;

public class PasswordsMustMatch implements BusinessRule {

    private String providedPassoword;
    private String storedPassword;

    public PasswordsMustMatch(String storedPassword, String providedPassoword) {
        this.storedPassword = storedPassword;
        this.providedPassoword = providedPassoword;
    }

    @Override
    public Boolean isRuleComplied() {
        return providedPassoword.equals(storedPassword);
    }

    @Override
    public String message() {
        return "Passwords dont match.";
    }
}
