package com.tomo.mcauthentication.domain.session;

import com.tomo.ddd.domain.BaseRepository;
import com.tomo.mcauthentication.domain.users.UserId;

import java.util.List;

public interface SessionRepository extends BaseRepository<Session, SessionId> {
    SessionId nextIdentity();
    List<Session> findByUserId(UserId userId);
    Session findByAccessToken(String anAccessToken);
}
