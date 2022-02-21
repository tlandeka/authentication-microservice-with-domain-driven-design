package com.tomo.mcauthentication.domain.users.rules;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;
import com.tomo.mcauthentication.domain.users.UserRespository;

public class UserEmailMustBeUnique implements BusinessRule {

    UserRespository userRespository;
    String email;

    public UserEmailMustBeUnique(UserRespository userRespository, String email) {
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
