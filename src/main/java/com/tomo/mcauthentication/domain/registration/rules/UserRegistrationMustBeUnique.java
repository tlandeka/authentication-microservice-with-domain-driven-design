package com.tomo.mcauthentication.domain.registration.rules;

import com.tomo.ddd.domain.BusinessRule;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;

public class UserRegistrationMustBeUnique implements BusinessRule {

    private UserRegistrationRepository repository;
    private String email;

    public UserRegistrationMustBeUnique(UserRegistrationRepository usersCounter, String email) {
        this.repository = usersCounter;
        this.email = email;
    }

    @Override
    public Boolean isRuleComplied() {
        return repository.countByEmailAndStatus(email, UserRegistrationStatus.Confirmed) < 1;
    }

    @Override
    public String message() {
        return "User login must be unique";
    }
}
