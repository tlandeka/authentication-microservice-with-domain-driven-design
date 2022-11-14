package com.tomo.mcauthentication.domain.registration.rules;

import com.tomo.ddd.domain.BusinessRule;

public class RecoveryCodeMustMatch implements BusinessRule {

    private String providedCode;
    private String storedCode;

    public RecoveryCodeMustMatch(String aStoredCode, String aProvidedCode) {
        this.storedCode = aStoredCode;
        this.providedCode = aProvidedCode;
    }

    @Override
    public Boolean isRuleComplied() {
        return providedCode.equals(storedCode);
    }

    @Override
    public String message() {
        return "Passwords dont match.";
    }
}
