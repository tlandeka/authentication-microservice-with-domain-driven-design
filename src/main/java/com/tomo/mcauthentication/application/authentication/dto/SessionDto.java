package com.tomo.mcauthentication.application.authentication.dto;

import com.tomo.mcauthentication.application.users.dto.BaseUserDto;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionId;
import com.tomo.mcauthentication.domain.users.UserId;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SessionDto extends BaseUserDto {
    private String sessionId;
    private String accessToken;
    private LocalDateTime expirationDate;
    private String tokenType;
    private String refreshToken;
    private String userAgent;
    private String ipAddress;
    private LocalDateTime lastActivity;

    public void setSessionId(SessionId sessionId) {
        this.sessionId = sessionId.toString();
    }

    public void setTokenType(Session.TokenType tokenType) {
        this.tokenType = tokenType.toString();
    }
}
