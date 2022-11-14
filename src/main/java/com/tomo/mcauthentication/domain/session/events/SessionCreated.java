package com.tomo.mcauthentication.domain.session.events;

import com.tomo.ddd.domain.BaseDomainEvent;
import com.tomo.mcauthentication.domain.session.SessionId;
import com.tomo.mcauthentication.domain.users.UserId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SessionCreated extends BaseDomainEvent  {
    private SessionId sessionId;
    private UserId userId;

    public SessionCreated(SessionId sessionId, UserId userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }
}
