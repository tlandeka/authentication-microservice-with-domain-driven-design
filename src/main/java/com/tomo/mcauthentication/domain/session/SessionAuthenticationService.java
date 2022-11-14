package com.tomo.mcauthentication.domain.session;

import com.tomo.ddd.domain.BusinessRuleValidator;
import com.tomo.mcauthentication.domain.session.rule.SessionCannotBeExpiredWhenRefreshTokenIsMissing;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionAuthenticationService extends BusinessRuleValidator {

    TokenProvider tokenProvider;
    SessionRepository sessionRepository;
    UserRepository userRepository;

    public SessionAuthenticationService(
            TokenProvider tokenProvider,
            SessionRepository sessionRepository,
            @Qualifier("userRepositoryJpaAdapter") UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public Session authenticate(String anAccessToken) {
        assertArgumentNotEmpty(anAccessToken, "Session token cannot be empty.");

        Session session = sessionRepository.findByAccessToken(anAccessToken);
        if (session == null) {
            throw new IllegalStateException(String.format("Session with access code %s doesn't exist.", anAccessToken));
        }

        checkRule(new SessionCannotBeExpiredWhenRefreshTokenIsMissing(session));

        if (session.isExpired()) {
            return new Session(
                    sessionRepository.nextIdentity(),
                    userRepository.findById(session.getUserId()),
                    tokenProvider,
                    true,
                    session.getUserAgent(),
                    session.getIpAddress());
        }

        session.setLastActivity(LocalDateTime.now());
        return session;
    }

    public Session logout(String anAccessToken) {
        assertArgumentNotEmpty(anAccessToken, "Session token cannot be empty.");

        Session session = sessionRepository.findByAccessToken(anAccessToken);
        if (session == null) {
            throw new IllegalStateException(String.format("Session with access code %s doesn't exist.", anAccessToken));
        }

        session.setExpirationDate(LocalDateTime.now());
        session.setRefreshToken(null);

        return sessionRepository.save(session);
    }
}
