package com.tomo.mcauthentication.domain.session;

import com.tomo.ddd.domain.DomainEventPublisher;
import com.tomo.ddd.domain.RootEntity;
import com.tomo.mcauthentication.domain.DomainRegistry;
import com.tomo.mcauthentication.domain.session.events.SessionCreated;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Session extends RootEntity {

    public static Long EXPIRATION_MSEC = 15000L;

    public enum TokenType {
        CLIENT_SECRET_JWT,
        PRIVATE_KEY_JWT,
        BASIC,
        API_KEY
    }

    @EmbeddedId
    private SessionId sessionId;
    private String accessToken;
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;
    private String refreshToken;
    private String userAgent;
    private String ipAddress;
    private LocalDateTime lastActivity;

    @Embedded
    @AttributeOverride(name="id", column = @Column(name="user_id"))
    private UserId userId;

    public Session(SessionId sessionId, User user, TokenProvider tokenProvider, Boolean rememberMe, String userAgent, String ipAddress)
    {
        this.sessionId = sessionId;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.tokenType = tokenProvider.getTokenType();
        this.userId = user.getUserId();
        this.expirationDate = LocalDateTime.now().plus(EXPIRATION_MSEC, ChronoField.MILLI_OF_DAY.getBaseUnit());
        this.accessToken = tokenProvider.createToken(user);

        if (Boolean.TRUE.equals(rememberMe)) {
            this.refreshToken = tokenProvider.createRefreshToken(user);
        }

        DomainEventPublisher.instance().publish(
                new SessionCreated(this.getSessionId(), this.getUserId())
        );
    }

    public boolean isExpired() {
        return !expirationDate.isAfter(LocalDateTime.now());
    }

    private void protectedAccessToken(String anToken) {
        this.assertArgumentNotEmpty(anToken, "Access token cannot be empty.");
        this.setAccessToken(DomainRegistry.encryptionService().encryptedValue(anToken));
    }

    private void protectedRefreshToken(String anToken) {
        this.assertArgumentNotEmpty(anToken, "Refresh token cannot be empty.");
        this.setRefreshToken(DomainRegistry.encryptionService().encryptedValue(anToken));
    }
}
