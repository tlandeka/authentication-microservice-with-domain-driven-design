package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionId;
import com.tomo.mcauthentication.domain.session.SessionRepository;
import com.tomo.mcauthentication.domain.users.UserId;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Qualifier("SessionRespositoryJpaAdapter")
public class SessionRespositoryJpaAdapter extends BaseJpaAdapter<Session, SessionId, SessionJpaRepository> implements SessionRepository {

    public SessionRespositoryJpaAdapter(SessionJpaRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public SessionId nextIdentity() {
        return new SessionId(UUID.randomUUID());
    }

    @Override public List<Session> findByUserId(UserId userId) {
        return jpaRepository.findAllByUserId(userId);
    }
}