package com.tomo.mcauthentication.domain.session;

import com.tomo.mcauthentication.ddd.domain.RootEntity;
import com.tomo.mcauthentication.domain.DomainRegistry;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Session extends RootEntity {

    public enum TokenType {
        CLIENT_SECRET_JWT,
        PRIVATE_KEY_JWT,
        BASIC,
        API_KEY
    }

    @EmbeddedId
    private SessionId sessionId;
    private String accessToken;
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

        String accestToken = tokenProvider.createToken(user);
        this.protectedAccessToken(accestToken);

        if (Boolean.TRUE.equals(rememberMe)) {
            refreshToken = tokenProvider.createRefreshToken(user);
            this.protectedRefreshToken(refreshToken);
        }
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
