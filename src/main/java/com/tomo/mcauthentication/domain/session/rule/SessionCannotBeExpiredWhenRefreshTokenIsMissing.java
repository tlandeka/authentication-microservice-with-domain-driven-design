package com.tomo.mcauthentication.domain.session.rule;

import com.tomo.mcauthentication.ddd.domain.BusinessRule;
import com.tomo.mcauthentication.domain.session.Session;

public class SessionCannotBeExpiredWhenRefreshTokenIsMissing implements BusinessRule {

    Session session;

    public SessionCannotBeExpiredWhenRefreshTokenIsMissing(Session session) {
        this.session = session;
    }

    @Override
    public Boolean isBroken() {
        return session.isExpired() && session.getRefreshToken() == null;
    }

    @Override
    public String message() {
        return String.format("Session token is expired and refresh token is missing.");
    }
}
