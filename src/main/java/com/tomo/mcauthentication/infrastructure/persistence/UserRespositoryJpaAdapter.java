package com.tomo.mcauthentication.infrastructure.persistence;

import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRespositoryJpaAdapter extends BaseJpaAdapter<User, UserId, UserJpaRepository> implements UserRepository {

    public UserRespositoryJpaAdapter(UserJpaRepository userJpaRepository) {
        super(userJpaRepository);
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
