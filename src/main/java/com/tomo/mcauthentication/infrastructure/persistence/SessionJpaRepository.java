package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.ddd.infrastructure.persistence.springdata.jpa.McCrudRepository;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionId;
import com.tomo.mcauthentication.domain.users.UserId;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionJpaRepository extends McCrudRepository<Session, SessionId> {

    List<Session> findAllByUserId(UserId userId);

    Session findSessionByAccessToken(String accessToken);
}
