package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionId;
import com.tomo.mcauthentication.domain.session.SessionRepository;
import com.tomo.mcauthentication.domain.users.UserId;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SessionRepositoryJpaAdapter extends BaseJpaAdapter<Session, SessionId, SessionJpaRepository> implements SessionRepository {

    public SessionRepositoryJpaAdapter(SessionJpaRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public SessionId nextIdentity() {
        return new SessionId(UUID.randomUUID());
    }

    @Override
    public List<Session> findByUserId(UserId userId) {
        return jpaRepository.findAllByUserId(userId);
    }

    @Override
    public Session findByAccessToken(String anAccessToken) {
        return jpaRepository.findSessionByAccessToken(anAccessToken);
    }
}
