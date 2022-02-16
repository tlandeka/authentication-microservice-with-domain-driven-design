package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;
import com.tomo.mcauthentication.domain.users.UserRespository;

import java.util.UUID;

public class UserRespositoryJpaAdapter implements UserRespository {

    UserJpaRepository jpaRepository;

    public UserRespositoryJpaAdapter(UserJpaRepository userJpaRepository) {
        this.jpaRepository = userJpaRepository;
    }

    @Override
    public User save(User anUser) {
        return jpaRepository.save(anUser);
    }

    @Override
    public UserId nextIdentity() {
        return new UserId(UUID.randomUUID());
    }

    @Override
    public User findByEmail(String anEmail) {
        return jpaRepository.findUserByEmail(anEmail);
    }
}
