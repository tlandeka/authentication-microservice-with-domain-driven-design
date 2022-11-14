package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.ddd.infrastructure.persistence.springdata.jpa.McCrudRepository;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;

import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends McCrudRepository<User, UserId> {

    User findUserByEmail(String email);
}
