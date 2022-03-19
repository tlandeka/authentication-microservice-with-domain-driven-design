package com.tomo.mcauthentication.domain.users.rules;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;
import com.tomo.mcauthentication.domain.users.UserRepository;

public class UserEmailMustBeUnique implements BusinessRule {

    UserRepository userRespository;
    String email;

    public UserEmailMustBeUnique(UserRepository userRespository, String email) {
        this.userRespository = userRespository;
        this.email = email;
    }

    @Override public Boolean isBroken() {
        return userRespository.findByEmail(this.email) != null;
    }

    @Override public String message() {
        return "User with this email already exists.";
    }
}
