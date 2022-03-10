package com.tomo.mcauthentication.domain.session;

import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;

public interface TokenProvider {
    String createToken(User user);
    String createRefreshToken(User user);
    UserId getUserIdFromToken(String anAuthToken);
    boolean validateToken(String anAuthToken);
    Session.TokenType getTokenType();
}
