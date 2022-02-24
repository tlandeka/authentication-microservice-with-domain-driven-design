package com.tomo.mcauthentication.domain.user_registrations.rules;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationStatus;

public class UserRegistrationMustBeUniqueRule implements BusinessRule {

    private UserRegistrationRepository repository;
    private String email;

    public UserRegistrationMustBeUniqueRule(UserRegistrationRepository usersCounter, String email) {
        this.repository = usersCounter;
        this.email = email;
    }

    @Override
    public Boolean isBroken() {
        return repository.countByEmailAndStatus(email, UserRegistrationStatus.Confirmed) > 0;
    }

    @Override
    public String message() {
        return "User login must be unique";
    }
}