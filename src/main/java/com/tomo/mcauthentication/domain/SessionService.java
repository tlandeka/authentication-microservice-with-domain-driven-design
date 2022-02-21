package com.tomo.mcauthentication.domain;

import com.tomo.mcauthentication.domain.users.User;

public class SessionService {

    public Session login(User user) {
        return new Session();
    }
}
