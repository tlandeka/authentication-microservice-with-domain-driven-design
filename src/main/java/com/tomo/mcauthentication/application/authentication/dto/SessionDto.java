package com.tomo.mcauthentication.application.authentication.dto;

import com.tomo.mcauthentication.application.users.dto.BaseUserDto;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionId;
import com.tomo.mcauthentication.domain.users.UserId;

import org.modelmapper.ModelMapper;

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
    int expirationDateMilis;
    private String tokenType;
    private String refreshToken;
    private String userAgent;
    private String ipAddress;
    private LocalDateTime lastActivity;
    private String userId;

    public static SessionDto create(Session session, ModelMapper mapper) {
        SessionDto dto = mapper.map(session, SessionDto.class);
        dto.setTokenType(session.getTokenType());
        dto.setUserId(session.getUserId());
        return dto;
    }

    public void setSessionId(SessionId sessionId) {
        this.sessionId = sessionId.toString();
    }

    public void setTokenType(Session.TokenType tokenType) {
        this.tokenType = tokenType.toString();
    }

    public void setUserId(UserId userId) {
        this.userId = userId.id().toString();
    }
}
