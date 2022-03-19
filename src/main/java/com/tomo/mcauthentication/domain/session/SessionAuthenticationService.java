package com.tomo.mcauthentication.domain.session;

import com.tomo.mcauthentication.ddd.domain.BusinessRuleValidator;
import com.tomo.mcauthentication.domain.EncryptionService;
import com.tomo.mcauthentication.domain.session.rule.SessionIsExpiredAndRefreshTokenIsMissing;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SessionAuthenticationService extends BusinessRuleValidator {

    TokenProvider tokenProvider;
    EncryptionService encryptionService;
    SessionRepository sessionRepository;
    UserRepository userRepository;

    public SessionAuthenticationService(
            TokenProvider tokenProvider,
            EncryptionService encryptionService,
            SessionRepository sessionRepository,
            @Qualifier("userRespositoryJpaAdapter") UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.encryptionService = encryptionService;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public Session authenticate(String anAuthToken) {
        assertArgumentNotEmpty(anAuthToken, "Session token cannot be empty.");

        Session session = sessionRepository.findByAccessToken(encryptionService.encryptedValue(anAuthToken));
        if (session == null) {
            throw new IllegalStateException(String.format("Session with access code %s doesn't exist.", anAuthToken));
        }

        checkRule(new SessionIsExpiredAndRefreshTokenIsMissing(session));

        if (session.isExpired()) {
            return new Session(
                    sessionRepository.nextIdentity(),
                    userRepository.findById(session.getUserId()),
                    tokenProvider,
                    true,
                    session.getUserAgent(),
                    session.getIpAddress());
        }

        return session;
    }
}
